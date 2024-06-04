/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import Database.Constant;

public class ViewUserCellController extends ListCell<UserAvatar> implements Initializable {


    @FXML
    private AnchorPane Base;
    
    @FXML
    private AnchorPane Pane1;
    
    @FXML
    private AnchorPane Pane2;
    
    @FXML
    private AnchorPane Pane3;

    @FXML
    private HBox CellHbox;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Label NameLabel;

    @FXML
    private Label AccLabel;

    @FXML
    private ImageView UserPfp;

    @FXML
    private Button ViewBtn;
    private Account user;
    private ViewUserController parentController;
    private FXMLLoader loader;
    private Map<Integer, String> userInfoName = new HashMap<>();
    private Map<Integer, Integer> userInfoNum = new HashMap<>();

    

    @FXML
    void Delete_Btn(ActionEvent event) {

    }

    @FXML
    void View_Btn(ActionEvent event) {

    }

    public ViewUserCellController(ViewUserController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/ViewUserCell.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAccountsFromDatabase();
        ViewBtn.setOnAction(event -> {
            System.out.println("View button clicked for user: " + NameLabel.getText());
            if (parentController != null) {
                parentController.viewUserDetails(user);
            }
        });
    }    

    private void loadAccountsFromDatabase() {
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT usersid, username, mobilenum FROM users_information")) {

            while (resultSet.next()) {
                int usersid = resultSet.getInt("usersid");
                String name = resultSet.getString("username");
                int mobilenum = resultSet.getInt("mobilenum");
                userInfoName.put(usersid, name + "\t");
                userInfoNum.put(usersid, mobilenum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void updateItem(UserAvatar user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/FXMLFiles/ViewUserCell.fxml"));
                loader.setController(this);

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            HBox hBox = new HBox(10);
            String username = userInfoName.get(user.getUserId());
            NameLabel.setText(username != null ? username.toLowerCase() : "");
            NoAccLabel.setText("| " + userInfoName);
            String imagePath = "file:" + user.getImagePath();
            ImageView imageView = new ImageView();
            try {
                Image image = new Image(imagePath);
                imageView.setImage(image);
            } catch (IllegalArgumentException e) {
                System.err.println("Error loading image: " + e.getMessage());
            }
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            Circle circle = new Circle(18, 18, 18);
            imageView.setClip(circle);
            setGraphic(Base);
            DeleteBtn.setOnAction(event -> {
                System.out.println("Delete button clicked for user: " + NoAccLabel.getText());
                if (parentController != null) {
                    parentController.deleteUser(user);
                }
            });
            NameLabel = new Label(userInfoName.get(user.getUserId()));
            NoAccLabel = new Label(userInfoNum.get(user.getUserId()) + "");
            hBox.getChildren().addAll(imageView, NameLabel, NoAccLabel, ViewBtn, DeleteBtn);
            setGraphic(hBox);

        }
    }
    public void setUserInformationDisplay(String imagePath, String username, String accountNumber){
        File file = new File(imagePath);
        UserPfp.setImage(new Image(file.toURI().toString()));
        NameLabel.setText(username);
        AccLabel.setText(accountNumber);
    }
    
}
