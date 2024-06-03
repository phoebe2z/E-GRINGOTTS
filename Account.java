/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import Database.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Account<T> {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty dob = new SimpleStringProperty();
    private StringProperty mobileNum = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty safetyPin = new SimpleStringProperty();
    private StringProperty accountNumber = new SimpleStringProperty();

    
    public Account(int userId, String username) {
        this.id = new SimpleIntegerProperty(userId);
        this.username = new SimpleStringProperty(username);

    }

    public Account() {
       
    }
 
    public void populateDataFromUserDB(int userId) {
        String query = "SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERSID = ?";
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    setName(resultSet.getString("NAME"));
                    setDob(resultSet.getString("DOB"));
                    setMobileNum(resultSet.getString("MOBILENUM"));
                    setEmail(resultSet.getString("EMAIL"));
                    setAddress(resultSet.getString("ADDRESS"));
                    setUsername(resultSet.getString("USERNAME"));
                    setPassword(resultSet.getString("PASSWORD"));
                    setSafetyPin(resultSet.getString("SAFETYPIN"));       
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        public void populateDataFromAdminDB(int adminId) {
        String query = "SELECT * FROM " + Constant.DB_ADMIN_TABLE_NAME + " WHERE ADMINID = ?";
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, adminId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    
                    setName(resultSet.getString("NAME"));
                    setDob(resultSet.getString("DOB"));
                    setMobileNum(resultSet.getString("MOBILENUM"));
                    setEmail(resultSet.getString("EMAIL"));
                    setAddress(resultSet.getString("ADDRESS"));
                    setUsername(resultSet.getString("USERNAME"));
                    setPassword(resultSet.getString("PASSWORD"));
                    setSafetyPin(resultSet.getString("SAFETYPIN"));  
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
        public void clearUserData(){
            setName(null);
            setDob(null);
            setMobileNum(null);
            setEmail(null);
            setAddress(null);
            setUsername(null);
            setPassword(null);
            setSafetyPin(null);
        }

    public int getId() {
        return id.get();
    }   

    public String getName() {
        return name.get();
    }

    public String getDob() {
        return dob.get();
    }

    public String getMobileNum() {
        return mobileNum.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getSafetyPin() {
        return safetyPin.get();
    }
    
    public String getAccountNumber(){
        return accountNumber.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    
    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setDob(String dob) {
         this.dob.set(dob);
    }
    
    public StringProperty dobProperty() {
        return dob;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum.set(mobileNum);
    }
    
    public StringProperty mobileNumProperty() {
        return mobileNum;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public StringProperty emailProperty() {
        return email;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public StringProperty addressProperty() {
        return address;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }
    
    public StringProperty usernameProperty() {
        return username;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public StringProperty passwordProperty() {
        return password;
    }

    public void setSafetyPin(String safetyPin) {
        this.safetyPin.set(safetyPin);
    }

    public StringProperty safetyPinProperty() {
        return safetyPin;
    }
    
    public void setAccountNumber(String accountNumber){
        this.accountNumber.set(accountNumber);
    }
    
    public StringProperty accountNumber(){
        return accountNumber;
    }

    
}
    
    

    
    
    
    

