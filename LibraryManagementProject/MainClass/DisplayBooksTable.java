package MainClass;

import DatabaseHandler.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DisplayBooksTable extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DataSource.getDataSource().openConnection();
        Parent root = FXMLLoader.load(getClass().getResource("BooksTable.fxml"));
        primaryStage.getIcons().add(new Image("Image/displayBooksTable.png"));
        primaryStage.sizeToScene();
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Show Books");
        primaryStage.setScene(new Scene(root, 897, 400));
        primaryStage.show();
    }
}
