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
import javafx.scene.shape.Line;

public class PastPensieveCellController implements Initializable {


    @FXML
    private Label AmountLbl;

    @FXML
    private Label CategoryLbl;

    @FXML
    private Label DateLbl;

    @FXML
    private Line Line1;

    @FXML
    private Line Line2;

    @FXML
    private Line Line3;

    @FXML
    private Label ReceiverLbl;

    @FXML
    private Button ViewBtn;

    @FXML
    private AnchorPane cell_container;
    
    private final PensievePast pensievePast;

    public PastPensieveCellController(PensievePast pensievePast) {
        this.pensievePast = pensievePast;
    }
    
    

    @FXML
    void View_Btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
