package UIMaker;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;

import java.util.Optional;

public class AlertMaker {
    public static void successAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(content);
        alert.setContentText("Press cancel to return");
        alert.showAndWait();
    }

    public static void errorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(content);
        alert.setContentText("Press cancel to return");
        alert.showAndWait();
    }

    public static void warningAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(content);
        alert.setContentText("Press cancel to return");
        alert.showAndWait();
    }

    public boolean confirmationAlert(String title, String content, StackPane stackPane) throws Exception {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(stackPane.getScene().getWindow());
        dialog.setTitle(title);
        dialog.setContentText(content);
//        try {
//            dialog.getDialogPane().setContent(fxmlLoader.load());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("e.getMessage() " + e.getMessage());
//        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> getOk = dialog.showAndWait();
        if (getOk.isPresent() && getOk.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }


}
