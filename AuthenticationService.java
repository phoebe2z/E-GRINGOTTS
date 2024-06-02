/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import Database.Constant;
import Database.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class AuthenticationService {
    
    private final Database databaseService;

    public AuthenticationService(Database databaseService) {
        this.databaseService = databaseService;
    }
    
        public Account getAccountByUsername(String username) {
        Account account = new Account();
        String query = "SELECT * FROM " + Constant.DB_USERS_TABLE_NAME + " WHERE USERNAME = ?";
        
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    account.setName(resultSet.getString("NAME"));
                    account.setDob(resultSet.getString("DOB"));
                    account.setMobileNum(resultSet.getString("MOBILENUM"));
                    account.setEmail(resultSet.getString("EMAIL"));
                    account.setAddress(resultSet.getString("ADDRESS"));
                    account.setUsername(resultSet.getString("USERNAME"));
                    account.setPassword(resultSet.getString("PASSWORD"));
                    account.setSafetyPin(resultSet.getString("SAFETYPIN"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }         
        return account;
    }

    /**public boolean authenticateUser(String username, String password) {
        Account account = databaseService.getUserByUsernameAndPassword(username, password);
        if (account != null && account.getPassword().equals(password)) {
            SessionManager.setCurrentUser(account);
            return true;
        }
        return false;
    } */
    
}
