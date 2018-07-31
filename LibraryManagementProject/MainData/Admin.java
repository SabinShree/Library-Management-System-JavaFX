package MainData;

import javafx.beans.property.SimpleStringProperty;

public class Admin {
    private SimpleStringProperty userName = new SimpleStringProperty("");
    private SimpleStringProperty password = new SimpleStringProperty("");

    public Admin(String userName, String password) {
        this.userName = new SimpleStringProperty(userName);
        this.password = new SimpleStringProperty(password);
    }

    public String getUserName() {
        return userName.get();
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}