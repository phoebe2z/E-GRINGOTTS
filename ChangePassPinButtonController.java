/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Database;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ChangePassPinButtonController implements Initializable {


    @FXML
    private HBox BtnHbox;

    @FXML
    private AnchorPane ButtonLeftPane;

    @FXML
    private AnchorPane ButtonRightPane;

    @FXML
    private Button CancelBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private VBox InformationVbox;

    @FXML
    private PasswordField NewField;

    @FXML
    private AnchorPane NewPassDataPane;

    @FXML
    private HBox NewPassHbox;

    @FXML
    private AnchorPane NewPassPane;

    @FXML
    private Text NewPassText;

    @FXML
    private PasswordField OldField;

    @FXML
    private AnchorPane OldPassDataPane;

    @FXML
    private HBox OldPassHbox;

    @FXML
    private AnchorPane OldPassPane;

    @FXML
    private Text OldPassText;

    @FXML
    private AnchorPane RepeatDataPane;

    @FXML
    private PasswordField RepeatField;

    @FXML
    private HBox RepeatHbox;

    @FXML
    private AnchorPane RepeatPane;

    @FXML
    private Text RepeatPassText;

    @FXML
    private AnchorPane SafetyDataPane;

    @FXML
    private PasswordField SafetyField;

    @FXML
    private HBox SafetyHbox;

    @FXML
    private AnchorPane SafetyPane;

    @FXML
    private Text SafetyPinText;

    @FXML
    private AnchorPane basePane;
    
    private Alert alert;

    @FXML
    void Cancel_Btn(ActionEvent event) {
        OldField.clear();
        NewField.clear();
        RepeatField.clear();
        SafetyField.clear();
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.PROFILE);
    }

    @FXML
    void Confirm_Btn(ActionEvent event) {
        Account account = SessionManager.getCurrentUser();
        if (account != null) {
            boolean valid = true; 
            StringBuilder errorMessage = new StringBuilder(); // To gather error messages

            try {
                // Validate and update password
                if (!OldField.getText().isEmpty() && NewField.getText().equals(RepeatField.getText())) {
                    if (Database.validateUserPassword(OldField.getText(), Model.getInstance().getUserId())) {
                        account.setPassword(NewField.getText());
                    } else {
                        valid = false;
                        errorMessage.append("Wrong current password!\n");
                    }
                } else if (!NewField.getText().equals(RepeatField.getText())) {
                    valid = false;
                    errorMessage.append("New password and repeat password do not match!\n");
                }

                // Validate and update safety pin
                if (!SafetyField.getText().isEmpty()) {
                    if (SafetyField.getText().length() == 6) {
                        account.setSafetyPin(SafetyField.getText());
                    } else {
                        valid = false;
                        errorMessage.append("Safety pin must have 6 digits!\n");
                    }
                }
                if (valid) {
                    Database.saveUserPasswordPin(account);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated the information!");
                    alert.showAndWait();
                    
                    OldField.clear();
                    NewField.clear();
                    RepeatField.clear();
                    SafetyField.clear();
                    Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.PROFILE);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(errorMessage.toString());
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace(); 
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while updating the information. Please try again.");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
