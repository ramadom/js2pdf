<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>JS 2 PDF - Plan</title>

    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" />
    <script type="text/javascript" src="//cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
  </head>

  <body>

    <div class="mx-auto" style="width: 90%; max-width: 40%; margin-top:50px; margin-bottom: 50px;">

      <div id="printBtn" class="btn btn-primary" style="margin-bottom:20px">
        Print plan
      </div>

      <div id="plan_list">
        <div id="summernote">
          <ol>
            <li>Procedūra ...</li>
            <li>Procedūra ...</li>
            <li>Procedūra ...</li>
            <li>Procedūra ...</li>
            <li>Procedūra ...</li>
          </ol>
        </div>
      </div>

      <div id="printArea"  style="margin-top:50px">
        HTML PDF area ....
      </div>

    </div>

    <script>
      $(document).ready(function () {
        $('#summernote').summernote();
      });
    </script>

    <script>
      $(document).ready(function () {

        $('#printBtn').on('click', function (e) {
          e.preventDefault();

          var htmlString = $('#plan_list').html();

          console.log("PRINTING:");
          console.log(htmlString);

          $('#printArea').html(htmlString);

        });
      });
    </script>

  </body>
</html>
