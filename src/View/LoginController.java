package View;

import java.io.IOException;

import DAO.UserDAO;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    TextField usernameField;
    @FXML
    TextField passwordField;
    public void checkLogin(ActionEvent event) {
        String email = usernameField.getText();
        String password = passwordField.getText();
        UserDAO userDAO = new UserDAO();
        Users user = userDAO.checkLogin(email, password);
        if (user != null) {
            try {
                Utilites.switchToScene1(event, "Main.fxml");
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        } else {
            System.out.println("Login failed");
        }
    }

    
}