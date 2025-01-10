package View;

import java.io.IOException;

import DAO.UserDAO;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    @FXML
    Button loginButton;
    @FXML
    Button signupButton;
    Users user = null;
    public void checkLogin(ActionEvent event) {
        String email = usernameField.getText();
        String password = passwordField.getText();
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkLogin(email, password);
        if (user != null) {
            try {
                Utilites.switchToScene1(event, "Main.fxml", user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login failed!");
            alert.setContentText("email or password is incorrect!");
            alert.setHeaderText("password or email is incorrect!");
            alert.show();
            System.out.println("Login failed");
        }
    }
    public Users getUser() {
        return user;
    }
    public void switchToSignup(ActionEvent event) {
        try {
            Utilites.switchToScene1(event, "Signup.fxml", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}