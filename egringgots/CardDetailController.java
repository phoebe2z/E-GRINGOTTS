/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author User
 */
public class CardDetailController implements Initializable {
    
        @FXML
    private AnchorPane BasePanel;

    @FXML
    private AnchorPane CCCard;

    @FXML
    private Text CCCvv;

    @FXML
    private Text CCCvvNum;

    @FXML
    private Text CCDate;

    @FXML
    private Text CCED;

    @FXML
    private Text CCNum;

    @FXML
    private Rectangle CCRec;

    @FXML
    private Text CCTitle;

    @FXML
    private AnchorPane DCCard;

    @FXML
    private Text DCCvv;

    @FXML
    private Text DCCvvNum;

    @FXML
    private Text DCDate;

    @FXML
    private Text DCEd;

    @FXML
    private Text DCNum;

    @FXML
    private Rectangle DCRec;

    @FXML
    private Text DCTitle;

    @FXML
    private Button EditCCButton;

    @FXML
    private Button EditDBButton;
    
    
        @FXML
    void EditCC_Btn(ActionEvent event) {

    }

    @FXML
    void EditDB_Btn(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
