/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import egringgots.Database;
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
    
    private Alert alert;
    @FXML
    void BackBtn(ActionEvent event) {
        Stage stage = (Stage) AccountPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showRegisterWindow();
    }

    @FXML
    void ConfirmBtn(ActionEvent event) {
        Stage stage = (Stage) AccountPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showVerificationWindow();
        
    }

    @FXML
    void FileBtn(ActionEvent event) {

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
