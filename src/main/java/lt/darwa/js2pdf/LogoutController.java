package lt.darwa.js2pdf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    if (req.getSession().getAttribute("user") != null) {
      req.getSession().removeAttribute("user");
    }
    res.sendRedirect(req.getContextPath() + "/login");
  }

}
