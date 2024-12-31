package Model;

public class Users {
    private int id;
    private String name;
    private String email;
    private String telephoneNumber;
    
    public Users(int id, String name, String email, String telephoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", username=" + name + ", email=" + email + ", telephoneNumber="
                + telephoneNumber + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    
}
