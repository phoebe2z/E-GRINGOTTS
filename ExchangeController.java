/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Constant;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ChoiceBox<String> FromCB;

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
    private ChoiceBox<String> ToCB;

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
        loadCurrencies();
    }    
    
    
        private void loadCurrencies() {
        String sql = "SELECT currencyname FROM currency";
        ObservableList<String> currencies = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                currencies.add(rs.getString("currencyname"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        FromCB.setItems(currencies);
        ToCB.setItems(currencies);
        }
}
