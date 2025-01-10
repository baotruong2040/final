package View;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Signup {
    @FXML
    TextField emailField;
    @FXML
    TextField passwordField;
    @FXML
    TextField nameField;
    @FXML
    Button signupButton;
    @FXML
    Button cancelButton;

    public void signup(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        Connection connection = DB.Connect.getConnection();
        try {
            PreparedStatement checkIfExist = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            checkIfExist.setString(1, email);
            ResultSet rs = checkIfExist.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("Email already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("email already exists!");
                alert.show();
            }else {
                PreparedStatement insertUser = connection.prepareStatement("INSERT INTO users(name, email) VALUES(?, ?)");
                insertUser.setString(1, name);
                insertUser.setString(2, email);
                insertUser.executeUpdate();
                int idUser = Utilites.getIDUser(email);
                PreparedStatement insertAccount = connection.prepareStatement("INSERT INTO accounts(email, password, user_id) VALUES(?, ?, ?)");
                insertAccount.setString(1, email);
                insertAccount.setString(2, password);
                insertAccount.setInt(3, idUser);
                insertAccount.executeUpdate();
                Utilites.switchToScene1(event, "Login.fxml",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel(ActionEvent event) {
        try {
            Utilites.switchToScene1(event, "Login.fxml",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
