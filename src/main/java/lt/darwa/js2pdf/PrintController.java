package lt.darwa.js2pdf;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;

@WebServlet("/print")
public class PrintController extends HttpServlet {

  // HTTP kliento testas, Kreipiasi i /plan puslapi ir grazina HTML
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

    if (req.getSession().getAttribute("user") == null) {
      res.sendRedirect(req.getContextPath() + "/login");
      return;
    }

    String protocol = req.getScheme();
    String server = req.getServerName();
    int port = req.getServerPort();
    String context = req.getContextPath();
    String localURL = protocol + "://" + server + ":" + port + context;

    String sessionId = req.getSession().getId();

    String html = getPlanPage(localURL, sessionId);

    res.setContentType("text/plain");

    res.getWriter().write(html);
  }

  private String getPlanPage(String url, String sessionId) {

    URI planURI = URI.create(url + "/plan");

    String html = null;

    try {
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
              //.uri(URI.create("http://www.google.com"))
              .uri(planURI)
              .header("Cookie", "JSESSIONID=" + sessionId) // Su session ID vekia, be session ID neveikia
              .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      html = response.body();

    } catch (IOException | InterruptedException ex) {
      html = ex.getMessage();
    }

    return html;
  }

  // Priima HTML'a , grazina PDF'a
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    String html = req.getParameter("html");

    // Formatuoja input HTMLa i XHTMLa

    String validXhtml = toValidXhtml(html);

    String formattedHtml = """
      <!DOCTYPE html>
      <html xmlns="http://www.w3.org/1999/xhtml">
      <head>
        <meta charset="UTF-8"/>
      </head>
      <body>
        """ + validXhtml + """
      </body>
      </html>
    """;

    try {
      byte[] pdfBytes = PdfUtils.html2pdf(formattedHtml);
      res.setContentType("application/pdf");
      res.setHeader("Content-Disposition", "inline; filename=\"export.pdf\"");
      res.getOutputStream().write(pdfBytes);
    } catch (Exception ex) {
      res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "PDF generation failed: " + ex.getMessage());
    }
  }

  public static String toValidXhtml(String htmlFragment) {
    Document doc = Jsoup.parseBodyFragment(htmlFragment);
    doc.outputSettings()
            .syntax(Document.OutputSettings.Syntax.xml) // forces self-closing tags like <img />
            .escapeMode(Entities.EscapeMode.xhtml) // escape characters properly
            .charset(StandardCharsets.UTF_8);

    return doc.body().html();
  }

}
