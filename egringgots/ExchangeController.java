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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ExchangeController implements Initializable {


    @FXML
    private Label AmountAfterLbl;

    @FXML
    private TextField AmountField;

    @FXML
    private Label AmountLbl;

    @FXML
    private Label AmountValue;

    @FXML
    private AnchorPane Base;

    @FXML
    private Button CalcBtn;

    @FXML
    private Button ClearBtn;

    @FXML
    private Label FeeLbl;

    @FXML
    private Label FeeValue;

    @FXML
    private ChoiceBox<?> FromCB;

    @FXML
    private Label FromLbl;

    @FXML
    private VBox LLeftVbox;

    @FXML
    private HBox LowerHbox;

    @FXML
    private HBox LowestHbox;

    @FXML
    private VBox Panel;

    @FXML
    private Button ProceedBtn;

    @FXML
    private VBox RightVbox;

    @FXML
    private Label Title;

    @FXML
    private ChoiceBox<?> ToCB;

    @FXML
    private Label ToLbl;

    @FXML
    private HBox UpperHbox;

    @FXML
    private VBox leftVbox;

    @FXML
    void Calc_Btn(ActionEvent event) {

    }

    @FXML
    void Clear_Btn(ActionEvent event) {

    }

    @FXML
    void Proceed_Btn(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
