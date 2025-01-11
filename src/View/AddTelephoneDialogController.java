package View;

import DAO.TelephoneDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTelephoneDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField telephoneField;

    private Stage dialogStage;
    private boolean okClicked = false;
    private int userId;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleAdd() {
        if (isInputValid()) {
            TelephoneDAO telephoneDAO = new TelephoneDAO();
            telephoneDAO.addTelephone(userId, telephoneField.getText(), nameField.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().isEmpty()) {
            errorMessage += "No valid name!\n";
        }
        if (telephoneField.getText() == null || telephoneField.getText().isEmpty()) {
            errorMessage += "No valid telephone number!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
