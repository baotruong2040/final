package View;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utilites {
    
    public static void switchToScene1(ActionEvent event, String fxml) throws IOException {
    Parent root;
    Stage stage;
    Scene scene;
    root = FXMLLoader.load(Utilites.class.getResource(fxml));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
 }
}