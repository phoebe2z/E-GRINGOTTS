/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Constant;
import Database.Database;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import egringgots.Account;
import egringgots.Model;
import static egringgots.UserAvatar.BASE_DIR;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EditProfileController implements Initializable {

    
    
    @FXML
    private AnchorPane AddressDataPane;

    @FXML
    private Text AddressDataText;

    @FXML
    private HBox AddressHbox;

    @FXML
    private AnchorPane AddressPane;

    @FXML
    private Text AddressText;

    @FXML
    private AnchorPane BasePane;


    @FXML
    private Button ChangePassBtn;

    @FXML
    private Button ChangeProfileBtn;

    @FXML
    private AnchorPane DOBDataPane;

    @FXML
    private Text DOBDataText;

    @FXML
    private HBox DOBHbox;

    @FXML
    private AnchorPane DOBPane;

    @FXML
    private Text DOBText;

    @FXML
    private Button EditBtn;

    @FXML
    private AnchorPane EmailDataPane;

    @FXML
    private HBox EmailHbox;

    @FXML
    private AnchorPane EmailPane;

    @FXML
    private Text EmailText;

    @FXML
    private AnchorPane InformationPane;

    @FXML
    private VBox InformationVbox;

    @FXML
    private AnchorPane MobileDataPane;

    @FXML
    private Text MobileDataText;

    @FXML
    private HBox MobileHbox;

    @FXML
    private AnchorPane MobilePane;

    @FXML
    private Text MobileText;

    @FXML
    private AnchorPane NameDataPane;

    @FXML
    private Text NameDataText;

    @FXML
    private HBox NameHbox;

    @FXML
    private AnchorPane NamePane;

    @FXML
    private Text NameText;

    @FXML
    private Text PointsText;

    @FXML
    private AnchorPane ProfilePanel;

    @FXML
    private ImageView ProfilePic;

    @FXML
    private Text EmailDataText;

    @FXML
    private Text TierText;

    @FXML
    private AnchorPane UsernameDataPane;

    @FXML
    private Text UsernameDataText;

    @FXML
    private HBox UsernameHbox;

    @FXML
    private AnchorPane UsernamePane;

    @FXML
    private Text UsernameText;
    


    @FXML
    void ChangePass_Btn(ActionEvent event) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CHANGEPASSBUTTON);
    }

    @FXML
    void ChangeProfile_Btn(ActionEvent event) {
        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.USER){
            changeUserPfp();
        } else{
            changeAdminPfp();
        }
    }

    @FXML
    void Edit_Btn(ActionEvent event) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.EDITBUTTON);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.USER){
        bindUserData(SessionManager.getCurrentUser());
        SessionManager.currentUserProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser != null) {
                bindUserData(newUser);
                try {
                    updateUserProfilePicture(Model.getInstance().getUserId());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                clearUserData();
            }
        });} else {
            SessionManager.currentUserProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser != null) {
                bindUserData(newUser);
                try {
                    updateAdminProfilePicture(Model.getInstance().getUserId());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                clearUserData();
            }
        });}   
    }

    private void changeUserPfp(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) ChangeProfileBtn.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            try {
                int currentUserId = Model.getInstance().getUserId();
                ProfilePic.setImage(null);
                UserAvatar.deleteUserProfilePicture(currentUserId);
                UserAvatar.saveUserProfilePicture(selectedFile, currentUserId);

                String filename = "src/UsersProfile/profile_" + currentUserId + ".png";
                UserAvatar userAvatar = new UserAvatar(filename, currentUserId);
                userAvatar.populateAvatarDataFromUserDB(userAvatar.getUserId());
                String imagePath = userAvatar.getImagePath();
                if (imagePath != null) {
                    File file = new File(imagePath);
                    if (file.exists()) {
                        ProfilePic.setImage(new Image(file.toURI().toString()));
                        System.out.println("Image loaded: " + file.toURI().toString()); // Debugging statement
                    } else {
                        String defaultImage = "src/Image/default_pfp.png";
                        File defaultFile = new File(defaultImage);
                        ProfilePic.setImage(new Image(defaultFile.toURI().toString()));
                        System.out.println("File does not exist: " + imagePath); // Debugging statement
                    }
                } else {
                    System.out.println("Image path is null"); // Debugging statement
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to save the image. Please try again.");
                alert.showAndWait();
            }
        }
    }
    
    
        private void changeAdminPfp(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) ChangeProfileBtn.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            try {
                int currentAdminId = Model.getInstance().getUserId();
                ProfilePic.setImage(null);
                UserAvatar.deleteAdminProfilePicture(currentAdminId);
                UserAvatar.saveAdminProfilePicture(selectedFile, currentAdminId);

                String filename = "src/AdminProfile/profile_" + currentAdminId + ".png";
                UserAvatar userAvatar = new UserAvatar(filename, currentAdminId);
                userAvatar.populateAvatarDataFromUserDB(userAvatar.getUserId());
                String imagePath = userAvatar.getImagePath();
                if (imagePath != null) {
                    File file = new File(imagePath);
                    if (file.exists()) {
                        ProfilePic.setImage(new Image(file.toURI().toString()));
                        System.out.println("Image loaded: " + file.toURI().toString()); // Debugging statement
                    } else {
                        String defaultImage = "src/Image/default_pfp.png";
                        File defaultFile = new File(defaultImage);
                        ProfilePic.setImage(new Image(defaultFile.toURI().toString()));
                        System.out.println("File does not exist: " + imagePath); // Debugging statement
                    }
                } else {
                    System.out.println("Image path is null"); // Debugging statement
                }
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to save the image. Please try again.");
                alert.showAndWait();
            }
        }
    }
    
    private void bindUserData(Account account) {
        if (account != null) {
            NameDataText.textProperty().bind(account.nameProperty());
            DOBDataText.textProperty().bind(account.dobProperty());
            MobileDataText.textProperty().bind(account.mobileNumProperty());
            EmailDataText.textProperty().bind(account.emailProperty());
            AddressDataText.textProperty().bind(account.addressProperty());
            UsernameDataText.textProperty().bind(account.usernameProperty());
        }
    }

    private void clearUserData() {
        NameDataText.textProperty().unbind();
        DOBDataText.textProperty().unbind();
        MobileDataText.textProperty().unbind();
        EmailDataText.textProperty().unbind();
        AddressDataText.textProperty().unbind();
        UsernameDataText.textProperty().unbind();
        
        NameDataText.setText("");
        DOBDataText.setText("");
        MobileDataText.setText("");
        EmailDataText.setText("");
        AddressDataText.setText("");
        UsernameDataText.setText("");
    }
    
    private void updateUserProfilePicture(int userId) throws FileNotFoundException {
        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setUserId(userId);
        String imageUrl = userAvatar.populateAvatarDataFromUserDB(userId);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                ProfilePic.setImage(new Image(new FileInputStream(imageUrl)));
            } catch (FileNotFoundException ex) {
                ProfilePic.setImage(new Image(new FileInputStream("src/Image/default_pfp.png")));
            }
        } else {
            ProfilePic.setImage(new Image(new FileInputStream("src/Image/default_pfp.png")));
        }
    }
    
        private void updateAdminProfilePicture(int adminId) throws FileNotFoundException {
        UserAvatar userAvatar = new UserAvatar();
        userAvatar.setUserId(adminId);
        String imageUrl = userAvatar.populateAvatarDataFromAdminDB(adminId);
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                ProfilePic.setImage(new Image(new FileInputStream(imageUrl)));
            } catch (FileNotFoundException ex) {
                ProfilePic.setImage(new Image(new FileInputStream("src/Image/default_pfp.png")));
            }
        } else {
            ProfilePic.setImage(new Image(new FileInputStream("src/Image/default_pfp.png")));
        }
    }
    }

