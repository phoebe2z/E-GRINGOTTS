/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Constant;
import Database.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TransactionController implements Initializable {


    @FXML
    private TextField AdditionalFields;

    @FXML
    private HBox AdditionalHbox;

    @FXML
    private Text AdditionalText;

    @FXML
    private TextField AmountField;

    @FXML
    private Text AmountOneText;

    @FXML
    private Text AmountText;

    @FXML
    private Text AmountThreeText;

    @FXML
    private Text AmountTwoText;

    @FXML
    private Text AvailableText;

    @FXML
    private AnchorPane BasePanel;

    @FXML
    private ChoiceBox<String> CategoryChoiceBox;

    @FXML
    private Text CategoryText;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private ChoiceBox<String> CurrencyChoiceBox;

    @FXML
    private HBox CurrencyHbox;

    @FXML
    private Text CurrencyOneText;

    @FXML
    private AnchorPane CurrencyPane;

    @FXML
    private Text CurrencyText;

    @FXML
    private AnchorPane CurrencyThreePane;

    @FXML
    private Text CurrencyThreeText;

    @FXML
    private AnchorPane CurrencyTwoPane;

    @FXML
    private Text CurrencyTwoText;

    @FXML
    private VBox CurrencyVbox;

    @FXML
    private HBox FillHbox;

    @FXML
    private ChoiceBox<String> FromChoiceBox;

    @FXML
    private Text FromText;

    @FXML
    private VBox Panel;

    @FXML
    private VBox ReceiverVbox;

    @FXML
    private Button ResetBtn;

    @FXML
    private TextField ToField;

    @FXML
    private Text ToText;

    @FXML
    private Text TransText;

    @FXML
    private VBox ValueVbox;

    @FXML
    private AnchorPane currencyOnePane;

    @FXML
    void Confirm_Btn(ActionEvent event) throws SQLException {
        storeTransaction();
//        String from;
//        double amount;
//        String currency;
//        String to;
//        String category;
//        String additionalNotes;
//        
//        if(checkIfEmpty()){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Please fill all compulsory fields!");
//            alert.showAndWait();
//        } else{
//            
//            //Database.checkBalance(id, from, currency, amount);
//        
//        
//        
//        
//        
//        }
        
        
        

    }

    @FXML
    void Reset_Btn(ActionEvent event) {
        FromChoiceBox.setValue(null);
        AmountField.clear();
        CurrencyChoiceBox.setValue(null);
        CategoryChoiceBox.setValue(null);
        AdditionalFields.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int userId = Model.getInstance().getUserId();
        loadCurrencies();
        loadCategories();
        transactionMethod(FromChoiceBox, userId);
    }  
    
    private void storeTransaction() throws SQLException {
    String from = FromChoiceBox.getValue();
    double amount = Double.parseDouble(AmountField.getText());
    String currency = CurrencyChoiceBox.getValue();
    String category = CategoryChoiceBox.getValue();
    String additionalNotes = AdditionalFields.getText();
    int receiverId = Database.checkAccountNumberUsersId(ToField.getText());

    try {
        // Get the user ID
        int userId = Model.getInstance().getUserId();

        // Get the balance of the selected account or card
        double currentBalance = 0.0;
        String[] fromSplit = from.split(": ");
        if (fromSplit[0].contains("Account")) {
            String accountNumber = fromSplit[1];
            currentBalance = Database.getCurrentBalance(userId, "user_account", accountNumber, currency, "accountnumber");
        } else {
            String cardNumber = fromSplit[1];
            currentBalance = Database.getCurrentBalance(userId, "user_card", cardNumber, currency, "cardnumber");
        }

        // Check if the balance is enough for the transaction

        if (currentBalance >= amount) {
                    double receiverCurrentBalance = Database.getCurrentBalance(receiverId, "user_account", ToField.getText(), currency, "accountnumber");
            // Deduct the amount from the balance
            double newBalance = currentBalance - amount;
            double receiverNewBalance = receiverCurrentBalance+amount;

            // Update the balance in the database
            if (fromSplit[0].contains("Account")) {
                String accountNumber = fromSplit[1];
                Database.updateUserBalance(userId, "user_account", accountNumber, currency, newBalance, "accountnumber");
            } else {
                String cardNumber = fromSplit[1];
                Database.updateUserBalance(userId, "user_card", cardNumber, currency, newBalance, "cardnumber");
            }
            
            Database.updateUserBalance(receiverId, "user_account", ToField.getText(), currency, receiverNewBalance, currency);

            // Store the transaction details in the database
            
            Transaction.insertTransaction(userId, receiverId, currency, amount, category, additionalNotes);

            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Transaction completed successfully!");
            alert.showAndWait();
        } else {
            // Show an error message if the balance is insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient balance!");
            alert.showAndWait();
        }
    } catch (NumberFormatException e) {
        // Show an error message if the amount is not a valid number
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid amount!");
        alert.showAndWait();
    } 
}
    
    
//    private boolean checkIfEmpty(){
//        if(ToField.getText().isEmpty()||AmountField.getText().isEmpty()||FromChoiceBox.getItems().isEmpty()||CurrencyChoiceBox.getItems().isEmpty()||CategoryChoiceBox.getItems().isEmpty()){
//            return true;
//        } else{
//            return false;
//        }
//    }
    
    private void loadCurrencies() {
        String sql = "SELECT currencyname FROM currency";
        ObservableList<String> currencies = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                currencies.add(rs.getString("currencyname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        CurrencyChoiceBox.setItems(currencies);
    }

    private void transactionMethod(ChoiceBox<String> FromChoiceBox, int userId) {
        ObservableList<String> transactionOptions = FXCollections.observableArrayList();

        // Load account numbers
        String accountQuery = "SELECT accountnumber FROM user_account WHERE usersid = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement accountStmt = conn.prepareStatement(accountQuery)) {
            accountStmt.setInt(1, userId);
            try (ResultSet rs = accountStmt.executeQuery()) {
                while (rs.next()) {
                    String accountNumber = rs.getString("accountnumber");
                    transactionOptions.add("Account: " + accountNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load card numbers
        String cardQuery = "SELECT cardnumber, cardtype FROM user_card WHERE usersid = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement cardStmt = conn.prepareStatement(cardQuery)) {
            cardStmt.setInt(1, userId);
            try (ResultSet rs = cardStmt.executeQuery()) {
                while (rs.next()) {
                    String cardNumber = rs.getString("cardnumber");
                    String cardType = rs.getString("cardtype");
                    transactionOptions.add(cardType + ": " + cardNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FromChoiceBox.setItems(transactionOptions);
    }
    
    private void loadCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList(
        "Leisure Luxe",
        "Tasty Treats",
        "Enigmatic Essentials",
        "Utility Utopia",
        "Mystic Magic",
        "Assorted Awe"
    ); 
        CategoryChoiceBox.setItems(categories);
        }
    }
    
    

