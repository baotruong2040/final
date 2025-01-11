package View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DB.Connect;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utilites {
    
    public static void switchToScene1(ActionEvent event, String fxml, Users user, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utilites.class.getResource("/View/" + fxml));
        Parent root = loader.load();
        
        if (fxml.equals("Main.fxml")) {
            MainController mainController = loader.getController();
            mainController.setUser(user);
        }
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static int getIDUser(String email) {
        Connection connection = Connect.getConnection();
        int idUser = 0;
        try {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement exQuery = connection.prepareStatement(query);
            exQuery.setString(1, email);
            ResultSet rs = exQuery.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt(1);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (idUser != 0) {
            return idUser;
        } else {
            return 0;
        }
    }
}