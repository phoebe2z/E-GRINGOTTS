package egringgots;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VerificationPageController implements Initializable {

    @FXML
    private Label AccCreationText;

    @FXML
    private Button BackButton;

    @FXML
    private AnchorPane BasePane;

    @FXML
    private Button NextButton;

    @FXML
    private PasswordField PinField;

    @FXML
    private Label Title;

    @FXML
    private StackPane VerificationPaneBase;

    @FXML
    private AnchorPane VerificationPanel;
    
        
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;

    @FXML
    void BackBtn(ActionEvent event) {
        Model.getInstance().getViewFactory().showLoginWindow();
        Stage stage = (Stage) VerificationPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    @FXML
    void NextBtn(ActionEvent event) {    
        if(Model.getInstance().getViewFactory().getLoginAccountType()== AccountType.USER){
            Model.getInstance().getViewFactory().showUserWindow();
        } else {
            Model.getInstance().getViewFactory().showAdminWindow();
        }
        Stage stage = (Stage) VerificationPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
