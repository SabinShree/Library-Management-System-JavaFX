package Controller;

import DatabaseHandler.DataSource;
import UIMaker.AlertMaker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class IssuedBookController {
//    @FXML
//    private BorderPane borderPane;
    @FXML
    private HBox bookPage;
    @FXML
    private HBox memberPage;
    @FXML
    private JFXTextField bookID;
    @FXML
    private Text bookName;
    @FXML
    private Text authorName;
    @FXML
    private Text bookStatus;

    @FXML
    private JFXTextField memberIdTextField;
    @FXML
    private Text emailTextField;
    @FXML
    private Text memberNameTextField;
    @FXML
    private Text contactTextField;
    @FXML
    private JFXTextField getBookId;

    private ObservableList<String> forBooks = FXCollections.observableArrayList();
    private ObservableList<String> forMember = FXCollections.observableArrayList();
    private ObservableList<String> forIssuedBooks = FXCollections.observableArrayList();

    @FXML
    private JFXListView<String> memberView;
    @FXML
    private JFXListView<String> bookView;
    @FXML
    private JFXListView<String> issuedBookView;

    private boolean isOnListView = false;

    @FXML
    public void initialize() {
        JFXDepthManager.setDepth(bookPage, 1);
        JFXDepthManager.setDepth(memberPage, 1);

    }

    public void getDetailsFromBookID() {
        String checkId = bookID.getText().trim();
        boolean found = true;

        try {
            int checkIdInInt = Integer.parseInt(checkId);
            final String FIND_BOOK = "SELECT * FROM BOOKS WHERE  id = '" + checkIdInInt + "'";

            ResultSet set = DataSource.getDataSource().getAction(FIND_BOOK);
            while (set.next()) {
                String bookText = set.getString(2);
                String authorText = set.getString(3);
                int availableText = set.getInt(5);
                String booleanText = availableText == 1 ? " Available " : " Not Available ";
                this.bookName.setText(" Book name : " + bookText);
                this.authorName.setText("Book author : " + authorText);
                this.bookStatus.setText("Book status : " + booleanText);
                found = false;
            }
            if (found) {
                bookName.setText("This book is not available");
                authorName.setText("");
                bookStatus.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getDetailsFromMemberID() {
        String checkId = memberIdTextField.getText().trim();
        int checkIdInInt = 0;
        boolean found = true;
        try {
            checkIdInInt = Integer.parseInt(checkId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        final String FIND_MEMBER_ID = "SELECT * FROM MEMBERS WHERE  id = '" + checkIdInInt + "'";
        try {
            ResultSet set = DataSource.getDataSource().getAction(FIND_MEMBER_ID);
            while (set.next()) {
                String nameText = set.getString(2);
                String phoneText = set.getString(3);
                String emailText = set.getString(4);
                this.memberNameTextField.setText(" Member Name : " + nameText);
                this.contactTextField.setText("Member Phone : " + phoneText);
                this.emailTextField.setText("Member Email : " + emailText);
                found = false;
            }
            if (found) {
                memberNameTextField.setText("No Such member Id  : " + memberIdTextField.getText());
                contactTextField.setText("");
                emailTextField.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadOnIssueBook() {
        try {
            String bookId = bookID.getText().trim();
            String memberId = memberIdTextField.getText().trim();
            String bookUpdate = "UPDATE " + DataSource.TABLE_BOOK + " SET " + DataSource.TABLE_BOOK_AVAILABLE + " = " + DataSource.booksNotAvailable + " WHERE id = " + bookId;
            String insertIntoIssuedBook = "INSERT INTO " + DataSource.TABLE_NAME_ISSUED_BOOK + "( " + DataSource.TABLE_ISSUED_BOOK_ID + ", " + DataSource.TABLE_ISSUED_BOOK_MEMBER_ID + ", " + DataSource.TABLE_ISSUED_BOOK_ISSUETIME + " ) VALUES (" + bookId + ", " + memberId + ", " + " DateTime('now', 'localtime') ) ";
            if (DataSource.getDataSource().getIssuedAction(bookUpdate) && DataSource.getDataSource().getIssuedAction(insertIntoIssuedBook)) {
                AlertMaker.successAlert("Successfully issued", "Book handover to member ID : " + memberId);
            } else {
                AlertMaker.errorAlert("Error", "Cannot issued the book.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.errorAlert("Error", "Cannot issued the book.");
        }
    }

    public void loadAllDetails() throws SQLException {
        String getId = getBookId.getText().trim();
        int getIdInInt;
        try {
            getIdInInt = Integer.parseInt(getId);
        } catch (NumberFormatException e) {
            AlertMaker.errorAlert("Error at ID", "Check ID again");
            e.printStackTrace();
            return;
        }
        String firstQuery = "SELECT * FROM BOOKS WHERE ID = " + getIdInInt + " AND Available = 0";
        ResultSet resultSet = DataSource.getDataSource().getAction(firstQuery);
        while (resultSet.next()) {
            String name = resultSet.getString("TITLE");
            String author = resultSet.getString("AUTHOR");
            String publisher = resultSet.getString("PUBLISHER");
            System.out.println(name + " " + author + " " + publisher);
            forBooks.add("Book Name : " + name);
            forBooks.add("Author : " + author);
            forBooks.add("Publisher : " + publisher);
        }

        String issuedBookQuery = "SELECT * FROM ISSUED_BOOK WHERE bookID = " + getIdInInt;
        int memberId = 0;
        ResultSet resultSet1 = DataSource.getDataSource().getAction(issuedBookQuery);
        while (resultSet1.next()) {
            memberId = resultSet1.getInt("memberID");
            int renewCount = resultSet1.getInt("renewCount");
            String date = resultSet1.getString("issueTime");
            forIssuedBooks.add("Member ID : " + memberId);
            forIssuedBooks.add("Issued Date : " + date);
            forIssuedBooks.add("Total Count : " + renewCount);
        }

        String secondQuery = "SELECT * FROM MEMBERS WHERE ID = " + memberId;
        ResultSet memberResult = DataSource.getDataSource().getAction(secondQuery);
        while (memberResult.next()) {
            String name = memberResult.getString("NAME");
            int phone = memberResult.getInt("PHONE");
            String email = memberResult.getString("EMAIL");
            forMember.add("Member Id : " + memberId);
            forMember.add("Name : " + name);
            forMember.add("Phone : " + phone);
            forMember.add("Email : " + email);
        }
        memberView.setItems(forMember);
        bookView.setItems(forBooks);
        issuedBookView.setItems(forIssuedBooks);
        isOnListView = true;
    }

    public void submitBook() {

        if (!isOnListView) {
            AlertMaker.warningAlert("No any book to submit", "Please enter only issued Books.");
        } else {
//            Stage stage = ((Stage) getBookId.getScene().getWindow());
//            JFXAlert alert = new JFXAlert(stage);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Continue ?");
            alert.setContentText("Do you want to return this book ?");
            Optional<ButtonType> optionalButtonType = alert.showAndWait();
            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.OK) {
                String getId = getBookId.getText().trim();
                int getIdInInt;
                try {
                    getIdInInt = Integer.parseInt(getId);
                } catch (NumberFormatException e) {
                    AlertMaker.errorAlert("Error at ID", "Check ID again to continue.");
                    e.printStackTrace();
                    return;
                }
                String deleteFromIssuedBook = "DELETE FROM ISSUED_BOOK WHERE bookID = " + getIdInInt;
                String updateToBooks = "UPDATE BOOKS SET AVAILABLE = 1 WHERE ID = " + getIdInInt;
                if (DataSource.getDataSource().getIssuedAction(deleteFromIssuedBook) && DataSource.getDataSource().getIssuedAction(updateToBooks)) {
                    AlertMaker.successAlert("Submitted ", "Book have been submitted successfully.");
                } else {
                    AlertMaker.errorAlert("Error", "Cannot submit the book.");
                }
            }
        }
    }


    @FXML
    public void renewBooks() {
        if (!isOnListView) {
            AlertMaker.warningAlert("No any book to submit", "Please enter only issued Books.");
        } else {
//            Stage stage = ((Stage) getBookId.getScene().getWindow());
//            JFXAlert alert = new JFXAlert(stage);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Continue ?");
            alert.setContentText("Do you want to renew this book ?");
            Optional<ButtonType> optionalButtonType = alert.showAndWait();
            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.OK) {
                String getId = getBookId.getText().trim();
                int getIdInInt;
                try {
                    getIdInInt = Integer.parseInt(getId);
                } catch (NumberFormatException e) {
                    AlertMaker.errorAlert("Error at ID", "Check ID again .");
                    e.printStackTrace();
                    return;
                }
                String updateIssuedBooks = "UPDATE ISSUED_BOOK SET issueTime = DateTime('now', 'localtime'), renewCount = renewCount + 1 WHERE bookID = '" + getIdInInt + "'";
                if (DataSource.getDataSource().getIssuedAction(updateIssuedBooks)) {
                    AlertMaker.successAlert("Renewed", "Book is successfully renewed.");
                } else {
                    AlertMaker.errorAlert("Error" , "Error while renewing the book.\nCheck Again ! ");
                }
            }
        }
    }
}
