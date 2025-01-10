package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DB.Connect;
import Model.Telephone;

public class TelephoneDAO {
    public List<Telephone> getTelephonesByUserId(int userId) {
        List<Telephone> telephones = new ArrayList<>();
        Connection connection = Connect.getConnection();
        try {
            String query = "SELECT * FROM telephone WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Telephone telephone = new Telephone();
                telephone.setId(rs.getInt("telephone_id"));
                telephone.setTelephone(rs.getString("telephone_number"));
                telephone.setIdUser(rs.getInt("user_id"));
                telephone.setName(rs.getString("owner_name"));
                telephones.add(telephone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return telephones;
    }

    public void deleteTelephone(int id) {
        Connection connection = Connect.getConnection();
        try {
            String query = "DELETE FROM telephone WHERE telephone_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTelephone(int id, String number, String name) {
        Connection connection = Connect.getConnection();
        try {
            String query = "INSERT INTO telephone(telephone_number, owner_name, user_id) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, number);
            statement.setString(2, name);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}