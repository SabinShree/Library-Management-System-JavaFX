package MainClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RunBooksAdder extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("InsertBook.fxml"));
        primaryStage.getIcons().add(new Image("Image/addBooks.png"));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Add Book");
        primaryStage.setScene(new Scene(root, 389, 408));
        primaryStage.show();


    }
}
