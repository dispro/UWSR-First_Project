import Model.Link;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String find = req.getParameter("find");
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
        ResultSet listOfLinks = statement.executeQuery("SELECT * FROM links WHERE DescriptionLink LIKE \"%"+find+"%\"");
            ArrayList<Link> findedLinks = new ArrayList<Link>();
            while (listOfLinks.next()) {
                Link link = new Link();
                link = new Link(listOfLinks.getInt(1), listOfLinks.getString(3), listOfLinks.getString(2), listOfLinks.getString(4), listOfLinks.getInt(5), listOfLinks.getInt(6));
                findedLinks.add(link);
            }
            System.out.println(findedLinks.size());
            req.setAttribute("findedLinks", findedLinks);
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    }
}
