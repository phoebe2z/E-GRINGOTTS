/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


import egringgots.Account;
import egringgots.Card;
import egringgots.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Database {
    
    public static int addUserInformation(String name, LocalDate dob, String mobileNum, String email, String address){
       int userId = -1;
        try{
        if(!(checkEmail(email)&&checkMobileNumber(mobileNum))){
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            
            PreparedStatement insertUser = connection.prepareStatement("INSERT INTO " + Constant.DB_USERS_TABLE_NAME + "(name, dob, mobilenum, email, address)" + "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            Date sqlDob = Date.valueOf(dob);
            insertUser.setString(1, name);
            insertUser.setDate(2, sqlDob);
            insertUser.setString(3, mobileNum);
            insertUser.setString(4, email);
            insertUser.setString(5, address);
            
            int affectedRows = insertUser.executeUpdate();
            
            
            if (affectedRows > 0) {
                ResultSet keys = insertUser.getGeneratedKeys();
                if (keys.next()) {
                    userId = keys.getInt(1);
                    System.out.println("Generated UserID: " + userId); 
                }
            }         
        }
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return userId;
    }
    
    
    public static boolean registerAccount(int userId, String username, String password, String safetyPin){
        try{
        if(!(checkUsername(username))){
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement insertUser = connection.prepareStatement("UPDATE " + Constant.DB_USERS_TABLE_NAME + " SET username = ?, password = ?, safetyPin = ? WHERE usersid = ?");
            insertUser.setString(1, username);
            insertUser.setString(2, password);
            insertUser.setString(3, safetyPin);
            insertUser.setInt(4, userId);
            
            int affectedRows = insertUser.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Updating user failed, no rows affected.");
            }
            return true;          
        }
        }catch(SQLException e){
            e.printStackTrace();;
        }
        
        return false;
    }
    
    public static boolean checkEmail(String email){
        try{
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            
            PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE EMAIL = ?");
            checkUserExists.setString(1, email);
            
            ResultSet resultSet = checkUserExists.executeQuery();
            
            if(!resultSet.isBeforeFirst()){
                return false;
            }
    } catch(SQLException e){
        e.printStackTrace();
    }
        return true;
    }
    
    public static boolean checkMobileNumber(String mobileNum){
        try{
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            
            PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE MOBILENUM = ?");
            checkUserExists.setString(1, mobileNum);
            
            ResultSet resultSet = checkUserExists.executeQuery();
            
            if(!resultSet.isBeforeFirst()){
                return false;
            }
    } catch(SQLException e){
        e.printStackTrace();
    }
        return true;
    }
    
        public static boolean checkUsername(String username){
        try{
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            
            PreparedStatement checkUserExists = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERNAME = ?");
            checkUserExists.setString(1, username);
            
            ResultSet resultSet = checkUserExists.executeQuery();
            
            if(!resultSet.isBeforeFirst()){
                return false;
            }
    } catch(SQLException e){
        e.printStackTrace();
    }
        return true;
    }
        
        public static int validateAndGetUserIdLogin(String username, String password){
            try {
                Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?");
                validateUser.setString(1, username);
                validateUser.setString(2, password);
                
           try (ResultSet resultSet = validateUser.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("usersid");
                } else {
                    return -1; // No matching user found
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        
        public static int validateAndGetAdminIdLogin(String username, String password){
            try {
                Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement validateAdmin = connection.prepareStatement("SELECT * FROM " + Constant.DB_ADMIN_TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?");
                validateAdmin.setString(1, username);
                validateAdmin.setString(2, password);
                
           try (ResultSet resultSet = validateAdmin.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("adminid");
                } else {
                    return -1; // No matching user found
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        
    public static boolean validateSafetyPin(String safetyPin, int usersId) {
        try {
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERSID = ? AND SAFETYPIN = ?");
            validateUser.setInt(1, usersId);
            validateUser.setString(2, safetyPin);

            ResultSet resultSet = validateUser.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
        public static boolean validateUserPassword(String password, int usersId) {
        try {
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement validateUser = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERSID = ? AND PASSWORD = ?");
            validateUser.setInt(1, usersId);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
        public static boolean validateAdminSafetyPin(String safetyPin, int adminId) {
        try {
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement validateAdmin = connection.prepareStatement("SELECT * FROM " + Constant.DB_ADMIN_TABLE_NAME + " WHERE ADMINID = ? AND SAFETYPIN = ?");
            validateAdmin.setInt(1, adminId);
            validateAdmin.setString(2, safetyPin);

            ResultSet resultSet = validateAdmin.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
        
    public Account getUserByUsernameAndPassword(String username, String password) {
                Account account = null;
                String query = "SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERNAME = ? AND PASSWORD = ?";
                try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                        PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            account = new Account();
                            account.setId(resultSet.getInt("USERSID"));
                            account.setName(resultSet.getString("NAME"));
                            account.setDob(resultSet.getString("DOB"));
                            account.setMobileNum(resultSet.getString("MOBILENUM"));
                            account.setEmail(resultSet.getString("EMAIL"));
                            account.setAddress(resultSet.getString("ADDRESS"));
                            account.setUsername(resultSet.getString("USERNAME"));
                            account.setPassword(resultSet.getString("PASSWORD"));
                            account.setSafetyPin(resultSet.getString("SAFETYPIN"));
                            // Set other user details
                        }
                    }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    
     public static void saveUserAccount(Account account) throws SQLException {
        String updateQuery = "UPDATE " + Constant.DB_USERS_TABLE_NAME + " SET name = ?, dob = ?, mobileNum = ?, email = ?, address = ?, username = ? WHERE usersId = ?";

        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, account.getName());
            stmt.setString(2, account.getDob());
            stmt.setString(3, account.getMobileNum());
            stmt.setString(4, account.getEmail());
            stmt.setString(5, account.getAddress());
            stmt.setString(6, account.getUsername());
            stmt.setInt(7, Model.getInstance().getUserId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     public static void saveUserPasswordPin(Account account) throws SQLException{
        String updateQuery = "UPDATE " + Constant.DB_USERS_TABLE_NAME + " SET password = ?, safetypin = ? WHERE usersId = ?";

        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, account.getPassword());
            stmt.setString(2, account.getSafetyPin());
            stmt.setInt(3, Model.getInstance().getUserId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
     
        public static void saveUserCard(Card card) throws SQLException{
            String updateQuery = "INSERT INTO " + Constant.DB_USERS_TABLE_CARD + " (cardnumber, cvv, expirydate, cardtype, usersId) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setLong(1, card.getCardNumber());
                System.out.println("cardNum: " + card.getCardNumber());
            stmt.setInt(2, card.getcVV());
            stmt.setString(3, card.getExpiryDate());
            stmt.setString(4, card.getCardType());
            stmt.setInt(5, Model.getInstance().getUserId());

        System.out.println("Executing update with card details: " + card);
        int rowsUpdated = stmt.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        }
        
        public static void updateUserCard(Card card) throws SQLException {
        String updateQuery = "UPDATE " + Constant.DB_USERS_TABLE_CARD + " SET cardnumber = ?, cvv = ?, expirydate = ? WHERE usersId = ? AND cardtype = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setLong(1, card.getCardNumber());
            stmt.setInt(2, card.getcVV());
            stmt.setString(3, card.getExpiryDate());
            stmt.setInt(4, card.getUserId());
            stmt.setString(5, card.getCardType());

            System.out.println("Executing update with card details: " + card);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        }
        
    public static boolean checkCardExists(int userId, String cardType) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + Constant.DB_USERS_TABLE_CARD + " WHERE usersId = ? AND cardtype = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, cardType);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count > 0, card exists; otherwise, it doesn't exist
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return false; 
    }
    
    public List<String> getCurrencies() {
        List<String> currencies = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT currencyname FROM currency")) {
            while (resultSet.next()) {
                currencies.add(resultSet.getString("currencyname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }
    
    public static int getUserCount(){
        int rowCount = 0;
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + Constant.DB_USERS_TABLE_NAME)) {
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return rowCount;
    }
    
    //private static boolean checkBalance(int id, String from, String currency, double amount)    

    public static double getCurrentBalance(int userId, String tableName, String number, String currency, String columnName) throws SQLException {
        String balanceQuery = "SELECT " + currency + " FROM " + tableName + " WHERE " + columnName + " = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(balanceQuery)) {
            int numbers = Integer.parseInt(number);
            stmt.setInt(1, numbers);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble(currency);
                }
            }
        }
        throw new SQLException("Error retrieving balance.");
    }

    public static void updateUserBalance(int userId, String tableName, String number, String currency, double newBalance, String columnName) throws SQLException {
        String updateQuery = "UPDATE " + tableName + " SET " + currency + " = ? WHERE " + columnName + " = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
            stmt.setDouble(1, newBalance);
            int numbers = Integer.parseInt(number);
            stmt.setInt(2, numbers);
            stmt.executeUpdate();
        }
    }
    
    public static int checkAccountNumberUsersId(String accNumber) throws SQLException{
        String checkQuery = "SELECT usersid FROM user_account WHERE accountnumber = ?";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
            long accNumbers = Long.parseLong(accNumber);
            stmt.setLong(1, accNumbers);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("usersid");
                }
            }
        }
        throw new SQLException("Error retrieving receiver id.");
    }
    
    
}
            

