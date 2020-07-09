import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/owner")
public class OwnerModeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("p");
        LocalDate date = LocalDate.now();
        int p = date.getMonthValue() + date.getDayOfMonth() + date.getYear();
        if (Integer.parseInt(password) == p){
            Cookie coockie = new Cookie("UserName", password);
            coockie.setMaxAge(600);
            resp.addCookie(coockie);
        }else{
            Cookie cookie = new Cookie("UserName", "0");
            resp.addCookie(cookie);
        }
        resp.sendRedirect("index.jsp");
    }
}
