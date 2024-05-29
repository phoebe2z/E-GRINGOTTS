/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Database;
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
import java.time.LocalDate;
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
    
    private static RegisterPageController instance;
    private int userId; 
    
    private Alert alert;
    
    @FXML
    void BackBtn(ActionEvent event) {
        Stage stage = (Stage) RegisterPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    @FXML
    void NextBtn(ActionEvent event) {
        if(validateUserInput(NameField.getText(), DobField.getValue(), MobileField.getText(), EmailField.getText(), AddressField.getText())){
            userId = Database.addUserInformation(NameField.getText(), DobField.getValue(), MobileField.getText(), EmailField.getText(), AddressField.getText());
            System.out.println("NextBtn - userId: " + userId); 
            if (userId > 0) {
                RegisterPageController.getInstance().setUserId(userId);
                Stage stage = (Stage) RegisterPanel.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showAccCreationWindow();
            } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Failed to create user");
            alert.showAndWait();
        }
        }
    }
    
    private boolean validateUserInput(String name, LocalDate dob, String mobileNum, String email, String address){
       if (name.length()==0||mobileNum.length()==0||dob==null||address.length()==0||email.length()==0)
           return false;
       if((!email.contains("@"))||mobileNum.contains("-"))
           return false;
        return true;
    }
    
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public static RegisterPageController getInstance() {
        if (instance == null) {
            instance = new RegisterPageController();
        }
        return instance;
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
