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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditCardButtonController implements Initializable {

    
    @FXML
    private AnchorPane BasePane;

    @FXML
    private HBox BtnHbox;

    @FXML
    private AnchorPane ButtonLeftPane;

    @FXML
    private AnchorPane ButtonRightPane;

    @FXML
    private Button CancelBtn;

    @FXML
    private AnchorPane CardNumFPane;

    @FXML
    private TextField CardNumField;

    @FXML
    private HBox CardNumHbox;

    @FXML
    private AnchorPane CardNumPane;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private AnchorPane CvvFPane;

    @FXML
    private HBox CvvHbox;

    @FXML
    private TextField CvvNumField;

    @FXML
    private AnchorPane CvvPane;

    @FXML
    private AnchorPane ExpFPane;

    @FXML
    private TextField ExpField;

    @FXML
    private HBox ExpHbox;

    @FXML
    private AnchorPane ExpPane;

    @FXML
    private HBox GapHbox;

    @FXML
    private VBox InformationVbox;

    @FXML
    private Text NewPassText;

    @FXML
    private Text OldPassText;

    @FXML
    private Text RepeatPassText;

    @FXML
    private Text TitleText;
    
    private Alert alert;
    
      private static final Logger logger = Logger.getLogger(EditCardButtonController.class.getName());

    @FXML
    void Cancel_Btn(ActionEvent event) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);
    }

    @FXML
    void Confirm_Btn(ActionEvent event) {
        if(!ExpField.getText().isEmpty()&&!CardNumField.getText().isEmpty()&&!CvvNumField.getText().isEmpty()){
        if(CardDetailController.editCreditCard()){
            System.out.println(CardDetailController.editCreditCard());
            populateCreditCardIntoDb();
        } else{
            populateDebitCardIntoDb();
        }
        
        } else {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Please fill all field!");
        alert.showAndWait();
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SessionManager.currentUserProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser != null) {
                if (CardDetailController.editCreditCard()) {
                    TitleText.setText("Edit Credit Card Detail");
                }
            } else {
                System.out.println("Error in editing card detail!");
            }
         });  
        
                SessionManager.currentUserProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser != null) {
                if (CardDetailController.editDebitCard()) {
                    TitleText.setText("Edit Debit Card Detail");
                }
            } else {
                System.out.println("Error in editing card detail!");
            }
         });
    }
    
    private void populateCreditCardIntoDb(){
        
        Card creditCard = new Card();
        logger.log(Level.INFO, "Card details before update: " + creditCard);
        try{
            creditCard.setCardType("Credit Card");
            creditCard.setUserId(Model.getInstance().getUserId());
            
            if(!CardNumField.getText().isEmpty()&&CardNumField.getText().length()==6){
                String text = CardNumField.getText();
                int value = Integer.parseInt(text);
                creditCard.setCardNumber(value);
            }
            
            if(!CvvNumField.getText().isEmpty()&&CvvNumField.getText().length()==3){
                String text = CvvNumField.getText();
                int value = Integer.parseInt(text);
                creditCard.setcVV(value);
                System.out.println("populate: " + value);
            }
            if(!ExpField.getText().isEmpty()){
                creditCard.setExpiryDate(ExpField.getText());
            }
            
                Database.saveUserCard(creditCard);
                           
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully  update the new Information!");
                alert.showAndWait();
                Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);
                logger.log(Level.INFO, "Card details updated successfully");
        }  catch (SQLException e) {
            e.printStackTrace(); 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while updating the information. Please try again.");
            alert.showAndWait();
        }
    }
        
    private void populateDebitCardIntoDb(){
        Card debitCard = new Card();
        try{
            debitCard.setCardType("Debit Card");
            debitCard.setUserId(Model.getInstance().getUserId());
            
            if(!CardNumField.getText().isEmpty()&&CardNumField.getText().length()==16){
                String text = CardNumField.getText();
                long value = Long.parseLong(text);
                debitCard.setCardNumber(value);
                System.out.println("populate: " + value);
            }
            
            if(!CvvNumField.getText().isEmpty()&&CvvNumField.getText().length()==3){
                String text = CvvNumField.getText();
                int value = Integer.parseInt(text);
                debitCard.setcVV(value);
            }
            if(!ExpField.getText().isEmpty()){
                debitCard.setExpiryDate(ExpField.getText());
            }
            
                Database.saveUserCard(debitCard);
                           
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully  update the new Information!");
                alert.showAndWait();
                Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);
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
