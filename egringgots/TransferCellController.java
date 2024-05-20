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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author User
 */
public class TransferCellController implements Initializable {


    @FXML
    private AnchorPane Base;

    @FXML
    private HBox CellBase;

    @FXML
    private VBox CellVbox;

    @FXML
    private Button FriendsBtn;

    @FXML
    private ImageView FriendsIcon;

    @FXML
    private Button transferBtn;

    @FXML
    private ImageView userPfp;
    
    private final Transfer transfer;

    public TransferCellController(Transfer transfer) {
        this.transfer = transfer;
    }
    
    

    @FXML
    void Friends_Btn(ActionEvent event) {

    }

    @FXML
    void Transfer_Btn(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
