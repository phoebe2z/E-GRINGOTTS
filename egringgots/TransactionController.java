/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TransactionController implements Initializable {


    @FXML
    private TextField AdditionalFields;

    @FXML
    private HBox AdditionalHbox;

    @FXML
    private Text AdditionalText;

    @FXML
    private TextField AmountField;

    @FXML
    private Text AmountOneText;

    @FXML
    private Text AmountText;

    @FXML
    private Text AmountThreeText;

    @FXML
    private Text AmountTwoText;

    @FXML
    private Text AvailableText;

    @FXML
    private AnchorPane BasePanel;

    @FXML
    private ChoiceBox<?> CategoryChoiceBox;

    @FXML
    private Text CategoryText;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private ChoiceBox<?> CurrencyChoiceBox;

    @FXML
    private HBox CurrencyHbox;

    @FXML
    private Text CurrencyOneText;

    @FXML
    private AnchorPane CurrencyPane;

    @FXML
    private Text CurrencyText;

    @FXML
    private AnchorPane CurrencyThreePane;

    @FXML
    private Text CurrencyThreeText;

    @FXML
    private AnchorPane CurrencyTwoPane;

    @FXML
    private Text CurrencyTwoText;

    @FXML
    private VBox CurrencyVbox;

    @FXML
    private HBox FillHbox;

    @FXML
    private ChoiceBox<?> FromChoiceBox;

    @FXML
    private Text FromText;

    @FXML
    private VBox Panel;

    @FXML
    private VBox ReceiverVbox;

    @FXML
    private Button ResetBtn;

    @FXML
    private TextField ToField;

    @FXML
    private Text ToText;

    @FXML
    private Text TransText;

    @FXML
    private VBox ValueVbox;

    @FXML
    private AnchorPane currencyOnePane;

    @FXML
    void Confirm_Btn(ActionEvent event) {

    }

    @FXML
    void Reset_Btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
