/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AccountCreationController implements Initializable {
    
    @FXML
    private Label AccCreationText;

    @FXML
    private AnchorPane AccountPanel;

    @FXML
    private Button BackToRegButton;

    @FXML
    private Button ConfirmButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Label PasswordText;

    @FXML
    private Label Pin;

    @FXML
    private PasswordField RepeatField;

    @FXML
    private Label RepeatText;

    @FXML
    private PasswordField SafetyField;

    @FXML
    private Label Title;

    @FXML
    private TextField UsernameField;

    @FXML
    private Label UsernameText;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private RegisterPageController registerPageController;
    
    private Alert alert;
    @FXML
    void BackBtn(ActionEvent event) {
        Stage stage = (Stage) AccountPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showRegisterWindow();
    }

    @FXML
    void ConfirmBtn(ActionEvent event) {
        if (validateUserInput(UsernameField.getText(), PasswordField.getText(), RepeatField.getText(), SafetyField.getText())) {
            int userId = RegisterPageController.getInstance().getUserId();
            System.out.println("ConfirmBtn - userId: " + userId);
            if (userId > 0) {
                if (Database.registerAccount(userId, UsernameField.getText(), PasswordField.getText(), SafetyField.getText())) {
                    Stage stage = (Stage) AccountPanel.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                    Model.getInstance().getViewFactory().showLoginWindow();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to create account");
                    alert.showAndWait();
                }        
            } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid userId");
                    alert.showAndWait();
            }

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Username are taken or password mismatch");
                alert.showAndWait();
        }
    }

    @FXML
    void FileBtn(ActionEvent event) {

    }
    
    private boolean validateUserInput(String username, String password, String repeat, String safetyPin){
        if(!password.equals(repeat)||username.length()<6||safetyPin.length()>6)
            return false;
        return true;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
    }    
    
}
