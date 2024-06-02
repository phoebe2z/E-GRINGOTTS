/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import Database.Constant;
import java.sql.Connection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAvatar {
    
    private int userId;
    private String imagePath;
    public static final String BASE_DIR = "src/UsersProfile/";
    public static final String ADMIN_BASE_DIR = "src/AdminProfile/";
    
    
    public UserAvatar(){
    }
    
    
    public UserAvatar(String imagePath, int userId) {      
        this.imagePath=imagePath;
        this.userId=userId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setImagePath(String imagePath) {
        this.imagePath=imagePath;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static void saveUserProfilePicture(File pictureFile, int userId) {
        String filename = "profile_" + userId + ".png"; // Example filename
        Path source = pictureFile.toPath();
        Path destination = Paths.get(BASE_DIR + filename);
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            updateUserImagePathInDatabase(userId, BASE_DIR + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  //CHECKED
    
    public static void saveAdminProfilePicture(File pictureFile, int adminId) {
        String filename = "profile_" + adminId + ".png"; // Example filename
        Path source = pictureFile.toPath();
        Path destination = Paths.get(ADMIN_BASE_DIR + filename);
        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            updateAdminImagePathInDatabase(adminId, ADMIN_BASE_DIR + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  //CHECKED

    public static void deleteUserProfilePicture(int userId) {
        String filename = "profile_" + userId + ".png";
        Path oldPicture = Paths.get(BASE_DIR + filename);

        for (int i = 0; i < 5; i++) {
            try {
                if (Files.deleteIfExists(oldPicture)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(100); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    public static void deleteAdminProfilePicture(int adminId) {
        String filename = "profile_" + adminId + ".png";
        Path oldPicture = Paths.get(ADMIN_BASE_DIR + filename);

        for (int i = 0; i < 5; i++) {
            try {
                if (Files.deleteIfExists(oldPicture)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(100); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


    public String populateAvatarDataFromUserDB(int userId) {
        String query = "SELECT avatar FROM user_avatar WHERE usersId = ?";
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    this.imagePath = resultSet.getString("avatar");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return this.imagePath;
    }
    
    public String populateAvatarDataFromAdminDB(int adminId) {
        String query = "SELECT avatar FROM admin_avatar WHERE adminId = ?";
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    this.imagePath = resultSet.getString("avatar");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return this.imagePath;
    }

    private static void updateUserImagePathInDatabase(int userId, String imagePath) {
        String query = "UPDATE user_avatar SET avatar = ? WHERE usersId = ?";

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, imagePath);
            statement.setInt(2, userId);
            statement.executeUpdate();
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                String insertQuery = "INSERT INTO user_avatar (usersId, avatar) VALUES (?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, userId);
                    insertStatement.setString(2, imagePath);
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors appropriately
        }
    }
    
        private static void updateAdminImagePathInDatabase(int adminId, String imagePath) {
        String query = "UPDATE admin_avatar SET avatar = ? WHERE adminId = ?";

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, imagePath);
            statement.setInt(2, adminId);
            statement.executeUpdate();
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                String insertQuery = "INSERT INTO admin_avatar (adminId, avatar) VALUES (?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, adminId);
                    insertStatement.setString(2, imagePath);
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors appropriately
        }
        }
}
