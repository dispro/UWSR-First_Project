import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/AddComment")
public class AddCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        Cookie cookie = new Cookie("UserName", name);
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
        String comment = req.getParameter("comment");
        String Id = req.getParameter("Id");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/UWSR"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=Europe/Moscow";
        try {
            Connection connection = DriverManager.getConnection(url, "root", "123456789");
            Statement statement = connection.createStatement();
            statement.execute(String.format("INSERT INTO comments(AuthorName, CommentText, LinkId) VALUE (\"%s\",\"%s\",%s)", name, comment, Id));
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        resp.sendRedirect("Comment.jsp?Id="+Id);
    }
}
