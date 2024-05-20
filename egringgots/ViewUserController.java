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

public class ViewUserController implements Initializable {


    @FXML
    private Button ClearBtn;

    @FXML
    private ListView<?> ListViewUser;

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
        // TODO
    }    
    
}
