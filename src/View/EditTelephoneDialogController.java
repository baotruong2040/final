package View;
import DAO.TelephoneDAO;
import Model.Telephone;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class EditTelephoneDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField telephoneField;
    private Stage dialogStage;
    private boolean isOkClicked;
    private Telephone telephone;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
        nameField.setText(telephone.getName());
        telephoneField.setText(telephone.getTelephone());
    }
    public boolean isOkClicked() {
        return isOkClicked;
    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            telephone.setName(nameField.getText());
            telephone.setTelephone(telephoneField.getText());
            TelephoneDAO telephoneDAO = new TelephoneDAO();
            telephoneDAO.editTelephone(telephone.getId(), telephone.getName(), telephone.getTelephone());
            isOkClicked = true;
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
