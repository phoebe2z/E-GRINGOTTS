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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author User
 */
public class AccountNumber {
    private int userId;
    private String accNum;
    
    private static final int ACCOUNT_NUMBER_LENGTH = 10;
    private static final Random RANDOM = new Random();

    
    public AccountNumber(){
    }
    
    public AccountNumber(int userId, String accNum) {
        this.userId = userId;
        this.accNum = accNum;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
 
        public static String generateUniqueAccountNumber() {
        while (true) {
            String accountNumber = String.format("%010d", ThreadLocalRandom.current().nextInt(1_000_000_000));
            System.out.println("Generated account number: " + accountNumber);
            if (!doesAccountNumberExist(accountNumber)) {
                System.out.println("Unique account number found: " + accountNumber);
                return accountNumber;
            }
        }
    }

    private static boolean doesAccountNumberExist(String accountNumber) {
        String query = "SELECT 1 FROM user_account WHERE accountnumber = ?";
        System.out.println("Checking existence for account number: " + accountNumber);

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);

            try (ResultSet resultSet = stmt.executeQuery()) {
                boolean exists = resultSet.isBeforeFirst();
                System.out.println("Does account number exist: " + exists);
                return exists;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static AccountNumber getUserById(int userId) {
        String query = "SELECT usersid, accountnumber FROM user_account WHERE usersid = ?";
        AccountNumber accountNumber = new AccountNumber();
        System.out.println("Fetching user by ID: " + userId);

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.println("Connection established successfully.");
            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("Query executed.");
                if (rs.next()) {
                    accountNumber.setUserId(rs.getInt("usersid"));
                    accountNumber.setAccNum(rs.getString("accountnumber"));
                    System.out.println("User found: " + rs.getInt("usersid") + ", Account number: " + rs.getString("accountnumber"));
                } else {
                    System.out.println("User not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return accountNumber;
    }

    public static void updateAccountNumber(int userId, String accountNumber) {
        String update = "INSERT INTO user_account (accountnumber, usersid) VALUES (?, ?) ON DUPLICATE KEY UPDATE accountnumber = VALUES(accountnumber)";
        System.out.println("Updating account number for user ID: " + userId + " with account number: " + accountNumber);

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(update)) {
            stmt.setString(1, accountNumber);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
            System.out.println("Account number updated successfully");
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred during update: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getOrCreateAccountNumber(int userId) throws SQLException {
        AccountNumber accNum = getUserById(userId);
        
        System.out.println("Retrieved account number: " + accNum.getAccNum());

        if (accNum.getUserId() != 0 && accNum.getAccNum() != null && !accNum.getAccNum().isEmpty()) {
            System.out.println("Account number is not null: " + accNum.getAccNum());
            return accNum.getAccNum();
        } else {
            String newAccountNumber = generateUniqueAccountNumber();
            System.out.println("Generating new account number: " + newAccountNumber);
            updateAccountNumber(userId, newAccountNumber);
            return newAccountNumber;
        }
    }
    
} 
