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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class UserCardDetailCellsController implements Initializable {


    @FXML
    private AnchorPane Base;

    @FXML
    private Label CCLabel;

    @FXML
    private HBox CellHbox;

    @FXML
    private Label DCLabel;

    @FXML
    private Label NameLabel;

    @FXML
    private Button SetBtn;

    @FXML
    void Set_Btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
