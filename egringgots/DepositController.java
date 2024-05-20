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

public class DepositController implements Initializable {

    
    @FXML
    private Label AccOrUsername;

    @FXML
    private TextField AccUserField;

    @FXML
    private TextField AdditionalField;

    @FXML
    private Label AdditionalLabel;

    @FXML
    private TextField AmountField;

    @FXML
    private Label AmountLabel;

    @FXML
    private AnchorPane Base;

    @FXML
    private HBox ButtonHbox;

    @FXML
    private Button ClearBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private ChoiceBox<?> CurrencyChoiceBox;

    @FXML
    private Label CurrencyLabel;

    @FXML
    private Label DepoTitle;

    @FXML
    private VBox DepoVbox;

    @FXML
    private AnchorPane Panel;

    @FXML
    void Clear_Btn(ActionEvent event) {

    }

    @FXML
    void Confirm_Btn(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
