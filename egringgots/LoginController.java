/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package egringgots;


import Database.Database;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


/**
 *
 * @author User
 */
public class LoginController implements Initializable {
    
    
    
    private AuthenticationService authenticationService;
    
    @FXML
    private AnchorPane BasePanel;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Hyperlink ForgetText;

    @FXML
    private AnchorPane LoginPanel;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Label PasswordText;

    @FXML
    private Button RegisterButton;

    @FXML
    private StackPane StackPanel;

    @FXML
    private Label Title;

    @FXML
    private ChoiceBox<AccountType> UserType;

    @FXML
    private TextField UsernameField;

    @FXML
    private Label UsernameText;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;
 
    @FXML
    void ConfirmBtn(ActionEvent event) {
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.USER) {
            int userId = Database.validateAndGetUserIdLogin(UsernameField.getText(), PasswordField.getText());
            System.out.println("Id Login : " + userId);
            if (userId>=0) {
                Model.getInstance().setUserId(userId);
                Account currentAccount = new Account<>();
                currentAccount.populateDataFromUserDB(userId);
                SessionManager.setCurrentUser(currentAccount);
                Stage stage = (Stage) LoginPanel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showVerificationWindow();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username or password");
                alert.showAndWait();
            }
        } else {
            int adminId = Database.validateAndGetAdminIdLogin(UsernameField.getText(), PasswordField.getText());
            if (adminId >= 0) {
                Model.getInstance().setUserId(adminId);
                Account currentAccount = new Account<>();
                currentAccount.populateDataFromAdminDB(adminId);
                SessionManager.setCurrentUser(currentAccount);
                Stage stage = (Stage) LoginPanel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showVerificationWindow();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wrong username or password");
                alert.showAndWait();
            }

        }

    }

    @FXML
    void RegisterBtn(ActionEvent event) {
        Stage stage = (Stage) LoginPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showRegisterWindow();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserType.setItems(FXCollections.observableArrayList(AccountType.USER, AccountType.ADMIN));
        UserType.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        UserType.valueProperty().addListener(observable->Model.getInstance().getViewFactory().setLoginAccountType(UserType.getValue()));
    }
    
    
}
