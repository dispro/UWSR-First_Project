import Model.Link;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AddLink")
public class AddLinkServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Id = req.getParameter("Id");
        Boolean action = Boolean.parseBoolean(req.getParameter("action"));
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/UWSR" +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "123456789");
            Statement statement = connection.createStatement();

            System.out.println(action);
            if (action) {
                try {
                    statement.executeUpdate("update links set IsUseful = IsUseful + 1 where Id =" + Id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (!action){
                try {
                    statement.executeUpdate("update links set IsUseless = IsUseless + 1 where Id =" + Id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        String path = "/";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String Ref = req.getParameter("Ref");
        String Title = req.getParameter("Title");
        String Description = req.getParameter("DescriptionLink");
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
            statement.execute("INSERT INTO Links (Title, Ref, DescriptionLink) Values(\""+Title+"\",\""+Ref+"\",\""+Description+"\")");
            String path = "/";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
