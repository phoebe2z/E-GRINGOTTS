/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Database;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import Database.Constant;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewUserController implements Initializable {


    @FXML
    private Button ClearBtn;

    @FXML
    private ListView<AnchorPane> ListViewUser;

    @FXML
    private Button SearchBtn;

    @FXML
    private TextField SearchUser;

    @FXML
    private AnchorPane ViewUserBase;

    @FXML
    private HBox ViewUserHbox;

    @FXML
    private AnchorPane ViewUserPanel;

    @FXML
    private ScrollBar ViewUserScroll;

    @FXML
    void Clear_Btn(ActionEvent event) {
        SearchUser.clear();
        ListViewUser.setItems(getUsers());
    }

    @FXML
    void Search_Btn(ActionEvent event) {{
        String searchKey = SearchUser.getText().toLowerCase();
        ObservableList<UserAvatar> filteredUser = FXCollections.observableArrayList();
        for (UserAvatar user : getUsers()) {
            String userinfo = userInfo.get(user.getUserId()).toLowerCase();
            if (userinfo.toLowerCase().contains(searchKey)) {
                filteredUser.add(user);
            }
        }
        ListViewUser.setItems(filteredUser);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateListView();
    }   
    
    public void populateListView(){
    List<Account> users = Database.fetchUsers();
    Map<Integer, String> accountNumbersMap = Database.fetchAccountNumbers();
    Map<Integer, String> profileImagesMap = Database.fetchProfileImages();
    
    ObservableList<AnchorPane> listItems = FXCollections.observableArrayList();
            for (Account user : users) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/ViewUserCell.fxml"));
                AnchorPane cell = loader.load();

                ViewUserCellController controller = loader.getController();
                String accountNumber = accountNumbersMap.getOrDefault(user.getId(), "XXXXXXXXXX");
                String imagePath = profileImagesMap.getOrDefault(user.getId(), "src/Image/default_pfp.png");

                controller.setUserInformationDisplay(imagePath, user.getUsername(), accountNumber);

                listItems.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ListViewUser.setItems(listItems);
    }

    public static ObservableList<UserAvatar> getUsers() {
        ObservableList<UserAvatar> users = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT usersid, username, mobilenum FROM users_information")) {

            while (resultSet.next()) {
                int usersid = resultSet.getInt("usersid");
                String name = resultSet.getString("username");
                int mobilenum = resultSet.getInt("mobilenum");
                userInfo.put(usersid, name + " \t" + mobilenum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT usersId, avatar FROM user_avatar")) {
            while (resultSet.next()) {
                String imagepath = resultSet.getString("avatar");
                int userid = resultSet.getInt("usersId");
                users.add(new UserAvatar(imagepath, userid));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users.stream()
                .sorted((u1, u2) -> userInfo.get(u1.getUserId()).compareToIgnoreCase(userInfo.get(u2.getUserId())))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    public void deleteUser(UserAvatar user) {
        int userId = user.getUserId();

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement deleteUserStmt = connection.prepareStatement("DELETE FROM users_information WHERE usersid = ?");
             PreparedStatement deleteAvatarStmt = connection.prepareStatement("DELETE FROM user_avatar WHERE usersId = ?")) {

            deleteUserStmt.setInt(1, userId);
            deleteUserStmt.executeUpdate();

            deleteAvatarStmt.setInt(1, userId);
            deleteAvatarStmt.executeUpdate();

            ListViewUser.getItems().remove(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void viewUserDetails(Account user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/EditProfile.fxml"));
            Parent root = loader.load();
            EditProfileController controller = loader.getController();
            if (user != null) {
                controller.initData(user);
            }// Pass the user data to the profile controller

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("User Profile");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
