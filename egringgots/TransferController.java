/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class TransferController implements Initializable {


    @FXML
    private Button BackBtn;

    @FXML
    private Button EnterBtn;

    @FXML
    private ListView<?> ListTransfer;

    @FXML
    private TextField SearchFriend;

    @FXML
    private ScrollBar TranferScroll;

    @FXML
    private AnchorPane TransferBase;

    @FXML
    private HBox TransferHbox;

    @FXML
    private AnchorPane TransferPanel;

    @FXML
    void Back_Btn(ActionEvent event) {

    }

    @FXML
    void Enter_Btn(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
