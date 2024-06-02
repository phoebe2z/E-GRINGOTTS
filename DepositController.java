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
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.sql.Timestamp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DepositController implements Initializable {

    
    @FXML
    private Label AccOrUsername;

    @FXML
    private TextField AccUserField;

    @FXML
    private TextField AdditionalField;

    @FXML
    private Label AdditionalLabel;

    @FXML
    private TextField AmountField;

    @FXML
    private Label AmountLabel;

    @FXML
    private AnchorPane Base;

    @FXML
    private HBox ButtonHbox;

    @FXML
    private Button ClearBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private ChoiceBox<String> CurrencyChoiceBox;

    @FXML
    private Label CurrencyLabel;

    @FXML
    private Label DepoTitle;

    @FXML
    private VBox DepoVbox;

    @FXML
    private AnchorPane Panel;

    @FXML
    void Clear_Btn(ActionEvent event) {
        AccUserField.clear();
        AmountField.clear();
        CurrencyChoiceBox.setValue(null);
        AdditionalField.clear();
    }

    @FXML
    void Confirm_Btn(ActionEvent event) throws SQLException {
        String accNumOrUsername = AccUserField.getText().trim();
        String amountStr = AmountField.getText().trim();
        String currency = CurrencyChoiceBox.getValue();
        String message = AdditionalField.getText().trim();
        
                // Validate input
        if (accNumOrUsername.isEmpty() || amountStr.isEmpty() || currency.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill all required fields.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid amount format.");
            return;
        }
        
        Integer senderId = Model.getInstance().getUserId();
        Integer receiverId = getUserIdByUsernameOrAccountNumber(accNumOrUsername);
        
        if (receiverId == null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Account number or username does not exist.");
            return;
        }
        
        double currentBalance = Database.getCurrentBalance(receiverId, "user_account", AccUserField.getText(), currency, "accountnumber");
        double newBalance = currentBalance+Double.parseDouble(AmountField.getText());
        
        Database.updateUserBalance(receiverId, "user_account", AccUserField.getText(), currency, newBalance, "accountnumber");
        boolean success = Transaction.insertTransaction(senderId, receiverId, currency, amount, "Deposit", message);
        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Transaction recorded successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to record the transaction.");
        }
    }
    
    private Integer getUserIdByUsernameOrAccountNumber(String accNumOrUsername) {
        String queryAccNum = "SELECT usersid FROM user_account WHERE accountnumber = ?";
        
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement stmt = connection.prepareStatement(queryAccNum)) {
            stmt.setString(1, accNumOrUsername);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("usersid");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String queryUsername = "SELECT usersid FROM users_information WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement stmt = connection.prepareStatement(queryUsername)) {
            stmt.setString(1, accNumOrUsername);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("usersid");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCurrencies();
    }  
    
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
    
}
