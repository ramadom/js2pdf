<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>JS 2 PDF - Plan</title>

    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" />
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

  </head>

  <body>

    <div class="container" style="width: 90%; margin: 50px auto;">
      <div class="row" style="gap: 20px;">

        <!-- Proceduru sarasas -->

        <div class="col" style="padding: 12px; border-radius: 6px;">
          <button id="addBtn" class="btn btn-primary mb-3">Add procedure</button>
          <div id="plan_list2" style="border: 1px solid #007bff;">
            <div id="procedureList"></div>
          </div>
        </div>

        <!-- PDF preview'as ir dokumentas -->

        <div class="col" style="padding: 12px; border-radius: 6px;">
          <button id="printBtn" class="btn btn-primary mb-3">Print plan</button>
          <div id="pdfViewer" style="height: 500px; overflow: hidden; border: 1px solid #28a745;">
            <embed id="pdfEmbed" type="application/pdf" width="100%" style="height: 100%;" />
          </div>
        </div>

      </div>
    </div>

    <script>
      $(document).ready(function () {

        // Encodintas logo jpg image

        const image = "data:image/jpg;base64,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";

        // Pradinis list'as

        $('#procedureList').empty();

        var numbers = 10;
        for (let i = 1; i <= 10; i++) {
          const $div = $('<div>');
          const $img = $('<img />', {
            src: image,
            alt: 'Darwa',
            width: 90,
            height: 20
          }).css('margin-right', '8px');
          const $span = $('<span style="font-family: Arial, sans-serif;">').text(`Procedūra Nr ` + i);

          $div.append($img).append($span);
          $('#procedureList').append($div);
        }

        // Prideda naujus list item'us

        $('#addBtn').on('click', function () {
          numbers++;

          const $div = $('<div>');
          const $img = $('<img />', {
            src: image,
            alt: 'Darwa',
            width: 90,
            height: 20
          }).css('margin-right', '8px');

          const $span = $('<span>').css('font-family', 'Arial, sans-serif').text(`Procedūra Nr ` + numbers);
          $div.append($img).append($span);

          $('#procedureList').append($div);
        });

        // Siuncia list'a i backenda konvertavimui, grazina PDF dokumenta

        $('#printBtn').on('click', function (e) {
          e.preventDefault();

           var htmlString = $('#procedureList').html();

          $.ajax({
            url: '/js2pdf/print',
            type: 'POST',
            data: {html: htmlString},

            xhrFields: {
              responseType: 'blob'
            },
            success: function (pdf) {

              var pdfBlob = new Blob([pdf], {type: 'application/pdf'});
              var url = URL.createObjectURL(pdfBlob);
              $('#pdfEmbed').attr('src', url);

              //window.URL.revokeObjectURL(url); // Revoke the object URL after some time to free up memory
            },
            error: function (e) {
              console.error('Failed to generate PDF', e);
            }
          });

        });

        // PDF view'io dydis pagal lango dydi

        const topOffset = $('#pdfViewer').offset().top;
        const availableHeight = window.innerHeight - topOffset - 20;
        $('#pdfViewer').css('height', availableHeight + 'px');

      });
    </script>

  </body>
</html>
