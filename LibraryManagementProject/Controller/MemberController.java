package Controller;

import DatabaseHandler.DataSource;
import ListAdder.MemberFileSystem;
import MainData.Member;
import UIMaker.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class MemberController {

    // RegisterPage
    @FXML
    private JFXTextField idRegister;
    @FXML
    private JFXTextField nameRegister;
    @FXML
    private JFXTextField emailRegister;
    @FXML
    private JFXTextField mobileRegister;
    @FXML
    private JFXPasswordField passwordRegister;
    @FXML
    private JFXButton registerButton;


    public void addMembersToDatabase() throws Exception {
        try {
            String name = nameRegister.getText().trim();
            int mobileNumber = 0;
            int id = 0;
            try {
                mobileNumber = Integer.parseInt(mobileRegister.getText().trim());
                id = Integer.parseInt(idRegister.getText().trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String email = emailRegister.getText().trim();
            String password = passwordRegister.getText().trim();

            if (name.isEmpty() || mobileNumber <= 0 || email.isEmpty() || password.isEmpty()) {
                AlertMaker.errorAlert("Error in the text field", "TextField is not correctly entered.");
                return;
            }
            Member member = new Member(id, name, mobileNumber, email, password);
            MemberFileSystem.getMemberFileSystem().addMembersToDatabase(member);
            try {
                DataSource.getDataSource().addMembersToTable(id, name, mobileNumber, email, password);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            AlertMaker.errorAlert("Error catch", "Check the textfield properly");
        }
    }

    public void exitOnCancelButton() {
        ((Stage) registerButton.getScene().getWindow()).close();
    }


}

