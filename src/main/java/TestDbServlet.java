import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/test")
public class TestDbServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/UWSR"+
                    "?verifyServerCertificate=false"+
                    "&useSSL=false"+
                    "&requireSSL=false"+
                    "&useLegacyDatetimeCode=false"+
                    "&amp"+
                    "&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root" , "123456789");
            Statement statement = connection.createStatement();
            ResultSet links = statement.executeQuery("SELECT * FROM Links");
            PrintWriter pw = response.getWriter();
            while(links.next()){
                pw.println("<a href=\""+links.getString("Ref")+"\">"+links.getString("Title")+"</a><br>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }
}
