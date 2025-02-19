package Controller;

import java.io.IOException;

import DAO.UserDAO;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    Label loginLabel;
    @FXML
    Label emailLabel;
    @FXML
    Label passwordLabel;
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
        if(email.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login failed!");
            alert.setContentText("pls fill in all fields!");
            alert.setHeaderText("pls fill in all fields!");
            alert.show();
            return;
        }else{
            UserDAO userDAO = new UserDAO();
            user = userDAO.checkLogin(email, password);
            if (user != null) {
                try {
                    Utilites.switchToScene1(event, "Main.fxml", user, "Danh bạ điện thoại");
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
            
    }
    public Users getUser() {
        return user;
    }
    public void switchToSignup(ActionEvent event) {
        try {
            Utilites.switchToScene1(event, "Signup.fxml", null, "Signup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void onEnter(ActionEvent ae){
   
        checkLogin(ae);
    }
}