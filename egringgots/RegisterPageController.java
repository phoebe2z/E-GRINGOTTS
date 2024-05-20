/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;


import egringgots.Database;
import egringgots.EGringgots;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class RegisterPageController implements Initializable {


    @FXML
    private TextField AddressField;

    @FXML
    private Label AddressText;

    @FXML
    private Button BackButton;

    @FXML
    private AnchorPane BasePanel;

    @FXML
    private DatePicker DobField;

    @FXML
    private Label DobText;

    @FXML
    private TextField EmailField;

    @FXML
    private Label EmailText;

    @FXML
    private TextField MobileField;

    @FXML
    private Label MobileText;

    @FXML
    private TextField NameField;

    @FXML
    private Label NameText;

    @FXML
    private Button NextButton;

    @FXML
    private Label PersonalText;

    @FXML
    private AnchorPane RegisterPanel;

    @FXML
    private StackPane StackPanel;

    @FXML
    private Label Title;

    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;
    
    @FXML
    void BackBtn(ActionEvent event) {
        Stage stage = (Stage) RegisterPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    @FXML
    void NextBtn(ActionEvent event) {
        Stage stage = (Stage) RegisterPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showAccCreationWindow();
 
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
