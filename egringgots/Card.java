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

/**
 *
 * @author User
 */
public class Card {
    
    private String expiryDate, cardType;
    private int userId, cVV;
    private long cardNumber;
    
    
    public Card(){
    
    }
    
    
    public Card(int userId, long cardNumber, int cVV, String expiryDate, String cardType){
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cVV = cVV;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
    }  

    public int getcVV() {
        return cVV;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCardType() {
        return cardType;
    }

    public int getUserId() {
        return userId;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setcVV(int cVV) {
        this.cVV = cVV;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void populateCardDataFromDB(int usersId, String typeCard){
        
            String query = "SELECT * FROM user_card WHERE usersId = ?, cardtype = ?";
            try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usersId);
            statement.setString(2, typeCard);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    setCardNumber(resultSet.getLong("cardnumber"));
                    setcVV(resultSet.getInt("cvv"));
                    setExpiryDate(resultSet.getString("expirydate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }
 
}
