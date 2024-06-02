
package egringgots;
import Database.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Transaction {
    private final StringProperty transactionId;
    private final IntegerProperty senderId;
    private final IntegerProperty receiverId;
    private final DoubleProperty amount;
    private final StringProperty currency;
    private final StringProperty message;
    private final StringProperty category;
    private Date transactionDate;

    public Transaction(String transactionId, int senderId, int receiverId, double amount, String currency, String message, String category, Date transactionDate) {
        this.transactionId = new SimpleStringProperty(transactionId);
        this.senderId = new SimpleIntegerProperty(senderId);
        this.receiverId = new SimpleIntegerProperty(receiverId);
        this.amount = new SimpleDoubleProperty(amount);
        this.currency = new SimpleStringProperty(currency);
        this.message = new SimpleStringProperty(message);
        this.category  = new SimpleStringProperty(category);
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId.get();
    }

    public Integer getSenderId() {
        return senderId.get();
    }

    public Integer getReceiverId() {
        return receiverId.get();
    }

    public Double getAmount() {
        return amount.get();
    }

    public String getCurrency() {
        return currency.get();
    }

    public String getMessage() {
        return message.get();
    }

    public String getCategory() {
        return category.get();
    }
    

    public void setTransactionId(String transactionId){
        this.transactionId.set(transactionId);
    }
    
    public void setSenserId(int senderId){
        this.senderId.set(senderId);
    }
    
    public void setReceiverId(int receiverId){
        this.receiverId.set(receiverId);
    }
    
    public void setAmount(double amount){
        this.amount.set(amount);
    }
    
    public void setCurrency(String currency){
        this.currency.set(currency);
    }
    
    public void setMessage(String message){
        this.message.set(message);
    }
    
    public void setCategory(String category){
    this.category.set(category);
}
    
    public StringProperty transactionIdProperty() {
        return transactionId;
    }

    public IntegerProperty senderIdProperty() {
        return senderId;
    }

    public IntegerProperty receiverIdProperty() {
        return receiverId;
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public StringProperty messageProperty() {
        return message;
    }

    public StringProperty categoryProperty() {
        return category;
    }
        

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    
        public static boolean insertTransaction(int senderId, int receiverId,  String currency, double amount, String category, String message) {
        String insertQuery = "INSERT INTO transaction (transactionid, sender, receiver, currency, amount, date, category, additional) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            
            stmt.setString(1, generateTransactionId());
            stmt.setInt(2, senderId);
            stmt.setInt(3, receiverId);
            stmt.setString(4, currency);
            stmt.setDouble(5, amount);
            LocalDateTime now = LocalDateTime.now();
            stmt.setTimestamp(6, Timestamp.valueOf(now));
            stmt.setString(7, category);
            stmt.setString(8, message);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    private static String generateTransactionId() {
        // Generate a unique transaction ID (e.g., using UUID or a custom logic)
        return java.util.UUID.randomUUID().toString();
    }

}

