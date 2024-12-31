package DB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Connect {
    public static Connection getConnection() {
        final String url = "jdbc:mysql://localhost:3306/cuoiki";
        final String username = "root";
        final String password = "";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connect successfully!");    
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return connection;

    }
}
