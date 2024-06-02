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
        CardDetailController.setEditCreditCard(false);
        CardDetailController.setEditDebitCard(false);
        clearFields();
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);
    }

    @FXML
    void Confirm_Btn(ActionEvent event) throws SQLException {
        if(!ExpField.getText().isEmpty()&&!CardNumField.getText().isEmpty()&&!CvvNumField.getText().isEmpty()){
        if(CardDetailController.editCreditCard()){
            if(!Database.checkCardExists(Model.getInstance().getUserId(), "Card Credit")){
            System.out.println(CardDetailController.editCreditCard());
            populateCreditCardIntoDb();
            }
        } else{
            populateDebitCardIntoDb();
        }
        
        clearFields();
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
        updateUIBasedOnCardType(); 
    }
    
    private void updateUIBasedOnCardType() {
        if (CardDetailController.editCreditCard()) {
            TitleText.setText("Edit Credit Card Detail");
        } else if (CardDetailController.editDebitCard()) {
            TitleText.setText("Edit Debit Card Detail");
        }
    }
    
    private void clearFields() {
        TitleText.setText("");
        CardNumField.clear();
        CvvNumField.clear();
        ExpField.clear();
    }
    
    
    private void populateCreditCardIntoDb() {
        try {
            int userId = Model.getInstance().getUserId();
            String type = "Credit Card";
            long cardNumber = 0;
            int cvv = 0;
            String expDate = "";

            // Validate and parse the card number
            if (isValidCardNumber(CardNumField.getText())) {
                cardNumber = Long.parseLong(CardNumField.getText());
                System.out.println("populate: " + cardNumber);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Card number must be 16 digits in length!");
                alert.showAndWait();
                return; // Exit the method if card number is invalid
            }

            // Validate and parse the CVV number
            if (isValidCvv(CvvNumField.getText())) {
                cvv = Integer.parseInt(CvvNumField.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("CVV number must be 3 digits in length!");
                alert.showAndWait();
                return; // Exit the method if CVV is invalid
            }

            // Validate and get the expiry date
            if (!ExpField.getText().isEmpty()) {
                expDate = ExpField.getText();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Expiry date cannot be empty!");
                alert.showAndWait();
                return; // Exit the method if expiry date is empty
            }

            // Create a new Card object with the provided details
            Card creditCard = new Card(userId, cardNumber, cvv,  type, expDate);
            System.out.println("Card details to save: " + creditCard);

            // Save or update the card details in the database
            if (!Database.checkCardExists(Model.getInstance().getUserId(), "Debit Card")) {
                Database.saveUserCard(creditCard);
            } else {
                Database.updateUserCard(creditCard);
            }

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully updated the new information!");
            alert.showAndWait();

            // Update the view to reflect the changes
            Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while updating the information. Please try again.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid number format. Please check your input.");
            alert.showAndWait();
        }
    }
        
    private void populateDebitCardIntoDb() {
        try {
            int userId = Model.getInstance().getUserId();
            String type = "Debit Card";
            long cardNumber = 0;
            int cvv = 0;
            String expDate = "";

            // Validate and parse the card number
            if (isValidCardNumber(CardNumField.getText())) {
                cardNumber = Long.parseLong(CardNumField.getText());
                System.out.println("populate: " + cardNumber);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Card number must be 16 digits in length!");
                alert.showAndWait();
                return; // Exit the method if card number is invalid
            }

            // Validate and parse the CVV number
            if (isValidCvv(CvvNumField.getText())) {
                cvv = Integer.parseInt(CvvNumField.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("CVV number must be 3 digits in length!");
                alert.showAndWait();
                return; // Exit the method if CVV is invalid
            }

            // Validate and get the expiry date
            if (!ExpField.getText().isEmpty()) {
                expDate = ExpField.getText();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Expiry date cannot be empty!");
                alert.showAndWait();
                return; // Exit the method if expiry date is empty
            }

            // Create a new Card object with the provided details
            Card debitCard = new Card(userId, cardNumber, cvv,  type, expDate);
            System.out.println("Card details to save: " + debitCard);

            // Save or update the card details in the database
            if (!Database.checkCardExists(Model.getInstance().getUserId(), "Debit Card")) {
                Database.saveUserCard(debitCard);
            } else {
                Database.updateUserCard(debitCard);
            }

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully updated the new information!");
            alert.showAndWait();

            // Update the view to reflect the changes
            Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while updating the information. Please try again.");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid number format. Please check your input.");
            alert.showAndWait();
        }
    }
    
    private boolean isValidCardNumber(String cardNumber) {
        return !cardNumber.isEmpty() && cardNumber.length() == 16;
    }

    private boolean isValidCvv(String cvv) {
        return !cvv.isEmpty() && cvv.length() == 3;
    }
    
 
}
