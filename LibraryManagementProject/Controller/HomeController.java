package Controller;

import UIMaker.AlertMaker;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeController {
    @FXML
    private Label dateTimeLabel;
    @FXML
    private StackPane stackPane;
    private AlertMaker alertMaker = new AlertMaker();

    @FXML
    public void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy, hh:mm:ss a");
            Calendar cal = Calendar.getInstance();
            dateTimeLabel.setText(dateFormat.format(cal.getTime()));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void loadNewBookAdderWindow() {
        windowLoader("../MainClass/InsertBook.fxml", "Image/HomePagButtonIcon/AddBooks.png", "Add New Books.", false);
    }

    @FXML
    public void loadNewMemberAdderWindow() {
        windowLoader("../MainClass/RegisterMember.fxml", "Image/HomePagButtonIcon/addMembers.png", "Add New Member.", true);
    }

    private void windowLoader(String linkToResource, String iconPath, String title, boolean resizable) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(linkToResource));
            primaryStage.getIcons().add(new Image(iconPath));
            primaryStage.setTitle(title);
            primaryStage.setResizable(resizable);
            primaryStage.setScene(new Scene(root));
            primaryStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.errorAlert("Error", "Cannot open new member adder frame");
        }
    }

    @FXML
    public void listNewBookWindow() {
        windowLoader("../MainClass/BooksTable.fxml", "Image/HomePagButtonIcon/ListBooks.png", "Books Table", true);
    }

    @FXML
    public void listNewMemberWindow() {
        windowLoader("../MainClass/MembersTable.fxml", "Image/HomePagButtonIcon/ListBooks.png", "Books Table", true);
    }

    @FXML
    public void issueBookWindow() {
        windowLoader("../MainClass/IssuedBookPage.fxml", "Image/HomePagButtonIcon/AddBooks.png", "Issued Book", true);
    }

    @FXML
    public void loadSettingWindow() {
        windowLoader("../MainClass/Setting.fxml", "Image/HomePagButtonIcon/Setting.png", "Setting", true);
    }

    @FXML
    public void logOutUser() {
        closeCurrentWindow();
    }

    @FXML
    public void closeCurrentWindow() {
        try {
            if (alertMaker.confirmationAlert("Exit", "Do you sure want to exit? ", stackPane)) {
                ((Stage) dateTimeLabel.getScene().getWindow()).close();
                loadToLoginPage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadToLoginPage() {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../MainClass/Login.fxml"));
            primaryStage.getIcons().add(new Image("Image/newLogo.png"));
            primaryStage.setResizable(false);
            primaryStage.sizeToScene();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setTitle("Login Page");
            primaryStage.setScene(new Scene(root, 925, 681));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
