import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@WebServlet("/DeleteLink")
public class DeleteLinkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String Id = req.getParameter("Id");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/UWSR" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=Europe/Moscow";
            Connection connection = DriverManager.getConnection(url, "root", "123456789");
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM links WHERE Id = " + Id);
            statement.execute("DELETE FROM comments WHERE LinkId = " + Id);
            resp.sendRedirect("index.jsp");
        }catch (SQLException | ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }
}
