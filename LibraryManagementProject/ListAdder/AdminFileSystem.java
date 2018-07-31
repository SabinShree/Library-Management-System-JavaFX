package ListAdder;

import MainData.Admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


//Singleton Class.
public class AdminFileSystem {
    private static AdminFileSystem adminFileSystem = new AdminFileSystem();
    private ObservableList<Admin> admins = FXCollections.observableArrayList();

    public AdminFileSystem getAdminFileSystem() {
        return adminFileSystem;
    }

    public void addAdminToFileSystem(Admin admin) {
        this.admins.add(admin);
    }

}
