/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import Database.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class Card {
    
    private StringProperty expiryDate;
    private StringProperty cardType;
    private IntegerProperty userId;
    private IntegerProperty cVV;
    private LongProperty cardNumber;
    private StringProperty creditCardNumber;
    private StringProperty debitCardNumber;
    
    
    public Card(int userId, long cardNumber, int cVV, String cardType, String expiryDate){
        this.userId = new SimpleIntegerProperty(userId);
        this.cardNumber = new SimpleLongProperty(cardNumber);
        this.cVV = new SimpleIntegerProperty(cVV);
        this.cardType = new SimpleStringProperty(cardType);
       this.expiryDate = new SimpleStringProperty(expiryDate);  
    }

    public int getcVV() {
        return cVV.get();
    }

    public String getExpiryDate() {
        return expiryDate.get();
    }

    public String getCardType() {
        return cardType.get();
    }

    public int getUserId() {
        return userId.get();
    }

    public long getCardNumber() {
        return cardNumber.get();
    }

    public void setcVV(int cVV) {
        this.cVV.set(cVV);
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate.set(expiryDate);
    }

    public void setCardType(String cardType) {
        this.cardType.set(cardType);
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber.set(cardNumber);
    }
    
    public StringProperty expiryDateProperty(){
        return expiryDate;
    }
  
    public IntegerProperty cVVProperty(){
        return cVV;
    }
    
    public StringProperty cardTypeProperty(){
        return cardType;
    }
    
    public IntegerProperty userIdProperty(){
        return userId;
    }
    
    public LongProperty cardNumberProperty(){
        return cardNumber;
    }
    
        public static Card populateCardDetails(int userId, String type) {
        Card card = null;
        String query = "SELECT * FROM user_card WHERE usersId = ? AND cardtype = ?";
        System.out.println("Populating card data from db");
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, type);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long cardNumber = rs.getLong("cardnumber");
                    int cVV = rs.getInt("cvv");
                    String expiryDate = rs.getString("expirydate");
                    String cardType = rs.getString("cardtype");

                    card = new Card(userId, cardNumber, cVV, cardType, expiryDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            System.out.println("returning: " + card);
        return card;
    }
        
        
    public Card() {
        this.creditCardNumber = new SimpleStringProperty("XXXXXXXXXXXXXXX");
        this.debitCardNumber = new SimpleStringProperty("XXXXXXXXXXXXXXX");
    }

    public Card(String creditCardNumber, String debitCardNumber) {
        this.creditCardNumber = new SimpleStringProperty(creditCardNumber);
        this.debitCardNumber = new SimpleStringProperty(debitCardNumber);
    }

    public void setCreditCardNumber(String cardNumber) {
        this.creditCardNumber.set(cardNumber);
        
    }

    public void setDebitCardNumber(String cardNumber) {
        this.debitCardNumber.set(cardNumber);
    }
    
    public String getCreditCardNumber(){
        return creditCardNumber.get();
    }
    
    public String getDebitCardNumber(){
        return debitCardNumber.get();
    }
    
    public StringProperty creditCardNumberProperty(){
        return creditCardNumber;
    } 
    
    public StringProperty debitCardNumberProperty(){
        return debitCardNumber;
    } 
    
    
 
}
