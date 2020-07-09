package Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {

    private int _id;
    private String _CommentText;
    private int _LinkId;
    private String _date;
    private String _author;

    public int get_id() {
        return _id;
    }

    public Comment(int id, int LinkId, String comment, String date, String author){
        this._id = id;
        this._CommentText = comment;
        this._LinkId = LinkId;
        this._date = date;
        this._author = author;
    }

    public static Boolean existCookieName(String name, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie: cookies){
            if (cookie.getName() == "UserName" && cookie.getValue() == name){
                return true;
            }
        }
        return false;
    }

    public String get_CommentText() {
        return _CommentText;
    }

    public int get_LinkId() {
        return _LinkId;
    }

    public String get_date() {
        return _date;
    }

    public String get_author() {
        return _author;
    }

    public static ArrayList<Comment> getCommentsFromDb(int LinkId) throws SQLException, ClassNotFoundException{
        ArrayList<Comment> resultSet = new ArrayList<Comment>();
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/UWSR"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=Europe/Moscow";
        Connection connection = DriverManager.getConnection(url, "root", "123456789");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM comments WHERE LinkId = " + LinkId);
        while(result.next()){
            Comment comment = new Comment(result.getInt("Id"), result.getInt("LinkId"), result.getString("CommentText"), (result.getString("DateComment")), result.getString("AuthorName"));
            resultSet.add(comment);
        }
        statement.close();
        connection.close();
        return resultSet;
    }

    public static Comment getCommentFromDb(int Id) throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/UWSR"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=Europe/Moscow";
        Connection connection = DriverManager.getConnection(url, "root", "123456789");
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM comments WHERE Id = " + Id);
        Comment comment = null;
        while(result.next()){
            comment = new Comment(result.getInt("Id"), result.getInt("LinkId"), result.getString("CommentText"), (result.getString("DateComment")), result.getString("AuthorName"));
        }
        statement.close();
        connection.close();
        return comment;
    }
}
