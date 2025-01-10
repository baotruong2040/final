package View;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAO.TelephoneDAO;
import Model.Telephone;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    public void addTelephone() {
        TelephoneDAO telephoneDAO = new TelephoneDAO();
        telephoneDAO.addTelephone(user.getId(), "New Number", "New Name");
        loadTelephones(user.getId());
    }
}