package MainData;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Member {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleIntegerProperty phone = new SimpleIntegerProperty();
    private SimpleStringProperty email = new SimpleStringProperty("");
    private SimpleStringProperty password = new SimpleStringProperty("");

    public Member(int id, String userName, int mobileNumber, String email, String password) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(userName);
        this.phone = new SimpleIntegerProperty(mobileNumber);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }

    public Member() {

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPhone() {
        return phone.get();
    }

    public SimpleIntegerProperty phoneProperty() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone.set(phone);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}

