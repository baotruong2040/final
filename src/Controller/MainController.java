package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import DAO.TelephoneDAO;
import Model.Telephone;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
    @FXML
    TableView<Telephone> tableView;
    @FXML
    TableColumn<Telephone, String> telephoneColumn;
    @FXML
    TableColumn<Telephone, String> nameColumn;
    @FXML
    Label nameLabel;
    @FXML
    Label phoneLabel;
    @FXML
    Button deleteButton;
    @FXML
    Button addButton;
    @FXML
    Button editButton;
    private ObservableList<Telephone> telephones;
    Users user = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        telephones = FXCollections.observableArrayList();
        tableView.setItems(telephones);
        telephoneColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {//copy
            if (newValue != null) {
                nameLabel.setText(newValue.getName());
                phoneLabel.setText(newValue.getTelephone());
            }else {
                nameLabel.setText("");
                phoneLabel.setText("");
            }
        });
    }

    public void setUser(Users user) {
        this.user = user;
        if(user != null) {
        loadTelephones(user.getId());
        }
    }
    private void loadTelephones(int userId) {
        TelephoneDAO telephoneDAO = new TelephoneDAO();
        telephones.clear();
        List<Telephone> list = telephoneDAO.getTelephonesByUserId(userId);
        telephones.setAll(list);
    }

    //delete
    public void deleteTelephone() {
        Telephone telephone = tableView.getSelectionModel().getSelectedItem();
        if (telephone != null) {
            TelephoneDAO telephoneDAO = new TelephoneDAO();
            telephoneDAO.deleteTelephone(telephone.getId());
            loadTelephones(user.getId());
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Select a telephone to delete!");
            alert.show();
        }
    }
    //add
    public void addTelephone(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddTelephoneDialog.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Telephone");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(addButton.getScene().getWindow());
            Scene scene = new Scene(loader.load());
            dialogStage.setScene(scene);

            AddTelephoneDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUserId(user.getId());

            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                loadTelephones(user.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editTelephone(ActionEvent event) {
        Telephone telephone = tableView.getSelectionModel().getSelectedItem();
        if (telephone != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EditTelephone.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Telephone");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(editButton.getScene().getWindow());
                Scene scene = new Scene(loader.load());
                dialogStage.setScene(scene);

                EditTelephoneDialogController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setTelephone(telephone);

                dialogStage.showAndWait();

                if (controller.isOkClicked()) {
                    loadTelephones(user.getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Select a telephone to edit!");
            alert.show();
        }
    }
}