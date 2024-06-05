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

    }

    @FXML
    void Search_Btn(ActionEvent event) {

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
                controller.setUserId(user.getId());
                
                listItems.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ListViewUser.setItems(listItems);
    }
    
    }
    
