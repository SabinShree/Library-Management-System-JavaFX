package MainClass;

import DatabaseHandler.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    // 54 33 89
    //   lite color = 68 27 36
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.getIcons().add(new Image("Image/newLogo.png"));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, 925, 681));
        primaryStage.show();
//        List<Books> booksList = DataSource.getDataSource().viewBooks();
//        if (booksList == null) {
//            System.out.println("No books in the table");
//        } else {
//            for (Books books : booksList) {
//                System.out.println(books.getId() + books.getUserName() + " " + books.getAuthor() + books.getPublisher() + " " + books.getAvailability());
//            }
//        }
    }

    @Override
    public void init() {
        DataSource.getDataSource();
        DataSource.getDataSource().openConnection();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
