package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.Connect;
import Model.Users;

public class UserDAO {
    public Users checkLogin(String email, String password) {
        Connection connection = Connect.getConnection();
        Users user = null;
        try {
            String checkLogQuery = "SELECT * FROM accounts WHERE email = ? AND password = ?";
            PreparedStatement exLogin = connection.prepareStatement(checkLogQuery);
            exLogin.setString(1, email);
            exLogin.setString(2, password);
            ResultSet rs = exLogin.executeQuery();

            while (rs.next()) {
                int idUser = rs.getInt(4);
                System.out.println(idUser);
                String query = "select * from users join accounts on accounts.user_id = users.user_id and users.user_id = ? ";
                PreparedStatement exQuery = connection.prepareStatement(query);
                exQuery.setInt(1, idUser);
                ResultSet rs1 = exQuery.executeQuery();
                if (rs1.next()) {
                    user = new Users(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(4));
                }
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        if (user != null) {
            return user;
        } else {
            return null;
            
        }

    }
}
