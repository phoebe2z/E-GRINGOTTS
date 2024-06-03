/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import Database.Database;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class UserCardDetailController implements Initializable {

    
    @FXML
    private Button BackBtn;

    @FXML
    private AnchorPane Base;

    @FXML
    private HBox CardHbox;

    @FXML
    private ListView<AnchorPane> CardList;

    @FXML
    private AnchorPane CardPanel;

    @FXML
    private ScrollBar CardScroll;

    @FXML
    private Label CreditLabel;

    @FXML
    private Label DebitLabel;

    @FXML
    private Label LimitLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private Button SearchBtn;

    @FXML
    private TextField SearchField;

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
    
    
        public void populateListView() {
        List<Account> users = Database.fetchUsers();
        Map<Integer, Card> cardDetailsMap = Database.fetchCardDetails();

        ObservableList<AnchorPane> listItems = FXCollections.observableArrayList();

        for (Account user : users) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/UserCardDetailCells.fxml"));
                AnchorPane cell = loader.load();

                UserCardDetailCellsController controller = loader.getController();
                Card cardDetails = cardDetailsMap.getOrDefault(user.getId(), new Card("XXXXXXXXXXXXXXX", "XXXXXXXXXXXXXXX"));

                controller.setUserCardDetails(user.getUsername(), cardDetails.getCreditCardNumber(), cardDetails.getDebitCardNumber());

                listItems.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        CardList.setItems(listItems);
    }
}

