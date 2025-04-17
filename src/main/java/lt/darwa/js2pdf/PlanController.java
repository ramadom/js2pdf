package lt.darwa.js2pdf;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/plan")
public class PlanController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

    if (req.getSession().getAttribute("user") == null) {
      res.sendRedirect(req.getContextPath() + "/login");
      return;
    }

    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/plan.jsp");
    dispatcher.forward(req, res);
  }

}
