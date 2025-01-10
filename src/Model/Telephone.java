package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class Telephone {
    private SimpleIntegerProperty id;
    private SimpleStringProperty telephone;
    private SimpleIntegerProperty idUser;
    private SimpleStringProperty name;

    public Telephone(int id, String telephone, int idUser, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.telephone = new SimpleStringProperty(telephone);
        this.idUser = new SimpleIntegerProperty(idUser);
        this.name = new SimpleStringProperty(name);
    }

    public Telephone() {
        this.id = new SimpleIntegerProperty();
        this.telephone = new SimpleStringProperty();
        this.idUser = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public int getIdUser() {
        return idUser.get();
    }

    public void setIdUser(int idUser) {
        this.idUser.set(idUser);
    }

    public IntegerProperty idUserProperty() {
        return idUser;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
}
