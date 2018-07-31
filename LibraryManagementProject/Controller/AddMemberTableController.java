package Controller;

import DatabaseHandler.DataSource;
import MainData.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMemberTableController implements Initializable {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, Integer> id;
    @FXML
    private TableColumn<Member, String> name;
    @FXML
    private TableColumn<Member, Integer> phone;
    @FXML
    private TableColumn<Member, String> email;
    @FXML
    private TableColumn<Member, String> password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeForBooksTable();
        tableView.getItems().setAll(DataSource.getDataSource().loadMembersToDatabase());
    }

    public void initializeForBooksTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
}