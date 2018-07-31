package Controller;

import DatabaseHandler.DataSource;
import ListAdder.LibraryFileSystem;
import MainData.Books;
import UIMaker.AlertMaker;
import UIMaker.TrayNotificationMaker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class BookController {
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextField idTextField;
    @FXML
    private JFXTextField authorTextField;
    @FXML
    private JFXTextField publisherTextField;
    @FXML
    private AnchorPane anchorPane;

    public void addBookToDatabase() {
        String title = titleTextField.getText().trim();
        int id = Integer.parseInt(idTextField.getText().trim());
        String author = authorTextField.getText().trim();
        String publisher = publisherTextField.getText().trim();

        if (title.isEmpty() || id <= 0 || author.isEmpty() || publisher.isEmpty()) {
            AlertMaker.errorAlert(" Error in text field", " Check the value properly before inserting into the database.");
            return;
        }
        Books books = new Books(id, title, author, publisher, DataSource.booksAvailable);
        LibraryFileSystem.getLibraryFileSystem().addBooksToTable(books);
        try {
            DataSource.getDataSource().addBooksToTable(books.getId(), books.getTitle(), books.getAuthor(), books.getPublisher(), DataSource.booksAvailable);
            TrayNotificationMaker.addBooks("Successfully added : " + title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelButton() {
        ((Stage) publisherTextField.getScene().getWindow()).close();

    }

}
