package Controller;

import DatabaseHandler.DataSource;
import LinksController.VariousLinks;
import UIMaker.AlertMaker;
import UIMaker.TrayNotificationMaker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private JFXTextField loginUserName;
    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    public void loginToLibrary() throws Exception {
        String userName = loginUserName.getText().trim();
        String password = loginPassword.getText().trim();

        if (userName.isEmpty()) {
            AlertMaker.warningAlert("Error in username", "No text in username");
            return;
        }

        if (password.isEmpty()) {
            AlertMaker.warningAlert("Error in password", "No text in password.");
            return;
        }

        if (DataSource.getDataSource().checkAdminRegistration(userName, password)) {
            disposeCurrentWindow();
            TrayNotificationMaker.welcomeAdmin();
            loadMainWindow();
        }
    }

    @FXML
    public void loadMainWindow() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../MainClass/HomePage.fxml"));
            primaryStage.getIcons().add(new Image("Image/HomePagButtonIcon/HomePage.png"));
            primaryStage.setTitle("Home Page");
            primaryStage.setScene(new Scene(root, 1350, 927));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.errorAlert("Error at main window", "Cannot open home page");
        }
    }

    public void disposeCurrentWindow() {
        ((Stage) loginUserName.getScene().getWindow()).close();
    }

    public void goToFacebook() {
        VariousLinks.getVariousLink().GoFacebook();
    }

    public void goToGooglePlus() {
        try {
            VariousLinks.getVariousLink().GoGooglePlus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToTwitter() {
        VariousLinks.getVariousLink().GoTwitter();
    }

}
