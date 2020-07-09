import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Comment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/EditComment")
public class EditCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String Id = request.getParameter("Id");
        String action = request.getParameter("action");
        if (action.equals("del")){
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
                Comment comment = Comment.getCommentFromDb(Integer.parseInt(request.getParameter("Id")));
                statement.execute("DELETE FROM comments WHERE Id = " + Id);
                response.sendRedirect("Comment.jsp?Id="+comment.get_LinkId());
            }catch (SQLException | ClassNotFoundException | IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String Id = request.getParameter("Id");
        String CommentText = request.getParameter("comment");
        Comment comment = null;
        try {
            comment = Comment.getCommentFromDb(Integer.parseInt(request.getParameter("Id")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            statement.execute(String.format("UPDATE comments SET CommentText = \"" + CommentText + "\" WHERE Id = " + Id));
            statement.close();
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        response.sendRedirect("Comment.jsp?Id="+comment.get_LinkId());
    }
}
