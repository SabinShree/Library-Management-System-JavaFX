package Controller;

import DatabaseHandler.DataSource;
import UIMaker.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private JFXTextField noOfDays;
    @FXML
    private JFXTextField finePerDays;
    @FXML
    private JFXTextField adminName;
    @FXML
    private JFXTextField adminPassword;

    private String defaultAdminName = "admin";
    private String defaultAdminPassword = "password";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void changeAdminInfo() {
        try {
            String name = adminName.getText().trim();
            String pasword = adminPassword.getText().trim();
            if (name.isEmpty()) {
                name = defaultAdminName;
            }
            if (pasword.isEmpty()) {
                pasword = defaultAdminPassword;
            }
            String updateAdminInfo = "UPDATE ADMIN SET USERNAME = '" + name + "', PASSWORD = '" + pasword + "'";
            if (DataSource.getDataSource().getIssuedAction(updateAdminInfo)) {
                AlertMaker.successAlert("Info Changed", "User name and password successfully changed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelButtonAction() {
        ((Stage) adminName.getScene().getWindow()).close();
    }
}
