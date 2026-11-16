import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<form method='post' action='login'>"
                + "Логин: <input name='user'> "
                + "Құпиясез: <input type='password' name='pass'> <button>Кіру</button></form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u = req.getParameter("user"), p = req.getParameter("pass");
        
        Map<String, String> users = new HashMap<>();
        users.put("admin", "1234");
        users.put("sarbinaz", "qwerty");

        if (users.containsKey(u) && users.get(u).equals(p)) {
            req.getSession().setAttribute("user", u);
            req.getSession().setMaxInactiveInterval(60); // Сессия уақыты 1 минут
            resp.sendRedirect(req.getContextPath() + "/cabinet");
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<p style='color:red'>Логин немесе құпиясез қате</p>");
        }
    }
}
