/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Constant;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.net.URL;
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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
public class AddCurrencyController implements Initializable {

    @FXML
    private Button AddBtn;

    @FXML
    private Label AddTitle;

    @FXML
    private AnchorPane AnchorBase;

    @FXML
    private Button ClearBtn;

    @FXML
    private Button ConfirmBtn;

    @FXML
    private TextField ExchangeField;

    @FXML
    private HBox ExchangeHbox;

    @FXML
    private Label ExchangeLabel;

    @FXML
    private AnchorPane ExchangePane;

    @FXML
    private ChoiceBox<String> FromCB;

    @FXML
    private Label FromLabel;

    @FXML
    private AnchorPane FromPane;

    @FXML
    private TextField InsertField;

    @FXML
    private AnchorPane InsertPane;

    @FXML
    private VBox InsertVbox;
    
    @FXML
    private TextField FeeField;
    
    @FXML
    private Label ProcessingLabel;

    @FXML
    private VBox PanelVbox;

    @FXML
    private AnchorPane SetCurrencyPane;

    @FXML
    private Label SetTitle;

    @FXML
    private ChoiceBox<String> ToCB;

    @FXML
    private Label ToLabel;

    @FXML
    private AnchorPane ToPane;

    @FXML
    private HBox btnHbox;

    @FXML
    private Label insertTitle;
    
    private Alert alert;

    @FXML
    void Add_Btn(ActionEvent event) {
        addCurrencyToDb(InsertField.getText());
    }

    @FXML
    void Clear_Btn(ActionEvent event) {
        FromCB.setValue(null);
        ToCB.setValue(null);
        FeeField.clear();
        ExchangeField.clear();
    }

    @FXML
    void Confirm_Btn(ActionEvent event) {
        String fromCurrency = FromCB.getValue();
        String toCurrency = ToCB.getValue();
        
        String rate = ExchangeField.getText().trim();
        double rateValue = Double.parseDouble(rate);
        
        String fee = FeeField.getText().trim();
        double feeValue = Double.parseDouble(fee);
        
        addCurrencyRateToDb(fromCurrency, toCurrency, rateValue, feeValue);
        
        FromCB.setValue(null);
        ToCB.setValue(null);
        FeeField.clear();
        ExchangeField.clear();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCurrencies();
    }    
    
    
    private void addCurrencyRateToDb(String from, String to, Double rate, Double fee) {
        
        String selectSql = "SELECT * FROM " + Constant.DB_EXCHANGE_CURRENCY + " WHERE fromcurrency = ? AND tocurrency = ?";
        String updateSql = "UPDATE " + Constant.DB_EXCHANGE_CURRENCY + " SET exchangerate = ?, processingfee = ? WHERE fromcurrency = ? AND tocurrency = ?";
        String insertSql = "INSERT INTO " + Constant.DB_EXCHANGE_CURRENCY + " (fromcurrency, tocurrency, exchangerate, processingfee) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
         PreparedStatement selectStmt = conn.prepareStatement(selectSql);
         PreparedStatement updateStmt = conn.prepareStatement(updateSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

        selectStmt.setString(1, from);
        selectStmt.setString(2, to);

        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {
            // Update existing row
            updateStmt.setDouble(1, rate);
            updateStmt.setDouble(2, fee);
            updateStmt.setString(3, from);
            updateStmt.setString(4, to);
            updateStmt.executeUpdate();
        } else {
            // Insert new row
            insertStmt.setString(1, from);
            insertStmt.setString(2, to);
            insertStmt.setDouble(3, rate);
            insertStmt.setDouble(4, fee);
            insertStmt.executeUpdate();
        }


            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Currency rate added or updated successfully!");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add exchange rate into database!");
            alert.showAndWait();

        }

    }
    
    
    private void addCurrencyToDb(String currencyName){
        String updateQuery = "INSERT INTO " + Constant.DB_CURRENCY + " (currencyname) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, currencyName);
            System.out.println("Currency Name: " + currencyName);
            stmt.executeUpdate();
            InsertField.clear();
            loadCurrencies();
        } catch (SQLException e) {
            e.printStackTrace();
        }
          addCurrencyColumnToTable("user_account", currencyName);
          addCurrencyColumnToTable("user_card", currencyName);
    }
    
    
    private void addCurrencyColumnToTable(String tableName, String columnName) {
        if (!doesColumnExistInTable(tableName, columnName)) {
            addColumnToTable(tableName, columnName);
        }
    }
    
    private boolean doesColumnExistInTable(String tableName, String columnName) {
        boolean columnExists = false;
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD)) {
            DatabaseMetaData metaData = conn.getMetaData();
            try (ResultSet rs = metaData.getColumns(null, null, tableName, columnName)) {
                if (rs.next()) {
                    columnExists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnExists;
    }
       

    private void addColumnToTable(String tableName, String columnName) {
        String alterTableQuery = "ALTER TABLE " + tableName + " ADD " + columnName + " DOUBLE DEFAULT 0";
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(alterTableQuery);
            System.out.println("Added column: " + columnName + " to " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void addCurrencyColumns() {
        List<String> currencies = getCurrenciesFromDb();
        for (String currency : currencies) {
            addCurrencyColumnToTable("user_account", currency);
            addCurrencyColumnToTable("user_card", currency);
        }
    }

    private List<String> getCurrenciesFromDb() {
        List<String> currencies = new ArrayList<>();
        String selectQuery = "SELECT currencyname FROM " + Constant.DB_CURRENCY;
        try (Connection conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                currencies.add(rs.getString("currencyname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
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
        ToCB.setItems(currencies);
        FromCB.setItems(currencies);
        }

    
}
