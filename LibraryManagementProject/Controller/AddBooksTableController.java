package Controller;

import DatabaseHandler.DataSource;
import MainData.Books;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class AddBooksTableController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Books> tableView;
    @FXML
    private TableColumn<Books, Integer> id;
    @FXML
    private TableColumn<Books, String> title;
    @FXML
    private TableColumn<Books, String> author;
    @FXML
    private TableColumn<Books, String> publisher;
    @FXML
    private TableColumn<Books, Integer> availability;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeForBooksTable();
        tableView.setItems(DataSource.getDataSource().loadBooksToDatabase());
    }

    public void initializeForBooksTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }
}
