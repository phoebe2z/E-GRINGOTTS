/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Database;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.sql.SQLException;
public class EditProfileButtonController implements Initializable {
    @FXML
    private AnchorPane AddressDataPane;

    @FXML
    private TextField AddressField;

    @FXML
    private HBox AddressHbox;

    @FXML
    private AnchorPane AddressPane;

    @FXML
    private Text AddressText;

    @FXML
    private AnchorPane BasePane;

    @FXML
    private Button CancelBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private AnchorPane DOBDataPane;

    @FXML
    private HBox DOBHbox;

    @FXML
    private AnchorPane DOBPane;

    @FXML
    private Text DOBText;

    @FXML
    private DatePicker DobField;

    @FXML
    private AnchorPane EmailDataPane;

    @FXML
    private TextField EmailField;

    @FXML
    private HBox EmailHbox;

    @FXML
    private AnchorPane EmailPane;

    @FXML
    private Text EmailText;

    @FXML
    private VBox InformationVbox;

    @FXML
    private AnchorPane MobileDataPane;

    @FXML
    private HBox MobileHbox;

    @FXML
    private TextField MobileNumField;

    @FXML
    private AnchorPane MobilePane;

    @FXML
    private Text MobileText;

    @FXML
    private AnchorPane NameDataPane;

    @FXML
    private TextField NameField;

    @FXML
    private HBox NameHbox;

    @FXML
    private AnchorPane NamePane;

    @FXML
    private Text NameText;

    @FXML
    private AnchorPane UsernameDataPane;

    @FXML
    private TextField UsernameField;

    @FXML
    private HBox UsernameHbox;

    @FXML
    private AnchorPane UsernamePane;

    @FXML
    private Text UsernameText;
    
    private Alert alert;

    @FXML
    void Cancel_Btn(ActionEvent event) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.PROFILE);
    }

    @FXML
    void Confirm_Btn(ActionEvent event) {
        Account account = SessionManager.getCurrentUser();
        if (account != null) {
            try{
            if (!NameField.getText().isEmpty()) {
                account.setName(NameField.getText());
            }

            if (DobField.getValue() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateString = DobField.getValue().format(formatter); 
                account.setDob(dateString);
            }

            if (!MobileNumField.getText().isEmpty()) {
                account.setMobileNum(MobileNumField.getText());
            }

            if (!EmailField.getText().isEmpty()) {
                account.setEmail(EmailField.getText());
            }

            if (!AddressField.getText().isEmpty()) {
                account.setAddress(AddressField.getText());
            }

            if (!UsernameField.getText().isEmpty()) {
                account.setUsername(UsernameField.getText());
            }
                Database.saveUserAccount(account);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully  update the new Information!");
                alert.showAndWait();
                Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.PROFILE);
        }  catch (SQLException e) {
            e.printStackTrace(); 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while updating the information. Please try again.");
            alert.showAndWait();
        }
        
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Account account = SessionManager.getCurrentUser();
        if (account != null) {
            // Set initial values from the account to the fields
            NameField.setText(account.getName());
            MobileNumField.setText(account.getMobileNum());
            EmailField.setText(account.getEmail());
            AddressField.setText(account.getAddress());
            UsernameField.setText(account.getUsername());
        }
    }    
    
}
