package lt.darwa.js2pdf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
      req.getRequestDispatcher("login_form.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");

        if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
            req.getSession().setAttribute("user", user);
            res.sendRedirect(req.getContextPath() + "/plan");
        } else {
            req.getRequestDispatcher("login_form.jsp").forward(req, res);
        }
    }
}
