import java.sql.*;

public class JDBC{

    public static Connection getConnection() throws SQLException {
        String userName = "root";
        String pass = "123456789";
        String connectionUrl = "jdbc:mysql://localhost:3306/UWSR";
        String connectionString = String.format(connectionUrl, userName, pass);
        Connection connection = DriverManager.getConnection(connectionString);
        return connection;
    }
}