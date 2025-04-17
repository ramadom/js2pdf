package lt.darwa.js2pdf;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/print")
public class PrintController extends HttpServlet {

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

}
