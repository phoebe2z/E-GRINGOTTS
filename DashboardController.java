/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import Database.Constant;
import Database.Database;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;


/**
 *
 * @author User
 */
public class DashboardController implements Initializable{

    
    @FXML
    private ChoiceBox<String> FilterChoiceBox;
    
    @FXML
    private ListView<String> ListViewBalance;
        
    @FXML
    private Text AccNumberText;
    
    @FXML
    private Text AccountText;

    @FXML
    private StackPane BasePane;

    @FXML
    private AnchorPane BasePanel;


    @FXML
    private Button ClearBtn;

    @FXML
    private Text DateText;

    @FXML
    private HBox DetailHbox;

    @FXML
    private VBox DetailVbox;

    @FXML
    private HBox DividerHbox;

    @FXML
    private Button FilterBtn;

    @FXML
    private AnchorPane MyAccPanel;

    @FXML
    private Text MyAccText;

    @FXML
    private VBox MyAccountVBox;

    @FXML
    private Text PastText;

    @FXML
    private ListView<AnchorPane> PensieveList;

    @FXML
    private ScrollBar PensieveScroll;

    @FXML
    private AnchorPane RightPanel;

    @FXML
    private Text SendText;

    @FXML
    private Button TransferBtn;

    @FXML
    private Text ValueText;

    @FXML
    private Button ViewBtn;

    @FXML
    private AnchorPane firstCurrency;

    @FXML
    private AnchorPane secCurrency;

    @FXML
    private AnchorPane thirdCurrency;
    
    @FXML
    private AnchorPane FilterInputPane;
    

    private List<Transaction> transactionList = new ArrayList<>();
    

    @FXML
    void Clear_Btn(ActionEvent event) {
    // Check if the FilterChoiceBox is not null and it has a selection
    if (FilterChoiceBox != null && FilterChoiceBox.getValue() != null) {
        // Clear the selection of the FilterChoiceBox
        FilterChoiceBox.getSelectionModel().clearSelection();
    }
    // Additionally, clear the filter inputs and repopulate the list
    FilterInputPane.setVisible(false);
    FilterInputPane.getChildren().clear();
    loadTransactionsFromDatabase(Model.getInstance().getUserId());
    populateListView();

    }

    @FXML
    void Filter_Btn(ActionEvent event) {
        String selectedFilter = (String) FilterChoiceBox.getValue();
        ObservableList<Transaction> filteredTransactions = FXCollections.observableArrayList(transactionList);

        switch (selectedFilter) {
            case "Threshold":
                TextField minField = (TextField) ((VBox) FilterInputPane.getChildren().get(0)).getChildren().get(0);
                TextField maxField = (TextField) ((VBox) FilterInputPane.getChildren().get(0)).getChildren().get(1);
                double minValue = Double.parseDouble(minField.getText());
                double maxValue = Double.parseDouble(maxField.getText());
                filteredTransactions = filteredTransactions.filtered(t -> t.getAmount() >= minValue && t.getAmount() <= maxValue);
                break;
            case "Date":
                DatePicker startDatePicker = (DatePicker) ((VBox) FilterInputPane.getChildren().get(0)).getChildren().get(0);
                DatePicker endDatePicker = (DatePicker) ((VBox) FilterInputPane.getChildren().get(0)).getChildren().get(1);
                LocalDate startDate = startDatePicker.getValue();
                LocalDate endDate = endDatePicker.getValue();
                filteredTransactions = filteredTransactions.filtered(t -> {
                LocalDate transactionLocalDate = t.getTransactionDate().toLocalDate();
                return !transactionLocalDate.isBefore(startDate) && !transactionLocalDate.isAfter(endDate);
            });
                break;
            case "Category":
                ChoiceBox<String> categoryChoiceBox = (ChoiceBox<String>) ((VBox) FilterInputPane.getChildren().get(0)).getChildren().get(0);
                String selectedCategory = categoryChoiceBox.getValue();
                filteredTransactions = filteredTransactions.filtered(t -> t.getCategory().equals(selectedCategory));
                break;
        }
        populateFilteredListView(filteredTransactions);
        FilterInputPane.setVisible(false);

    }
    
    private void populateFilteredListView(ObservableList<Transaction> filteredTransactions) {
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();

        for (Transaction transaction : filteredTransactions) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/PastPensieveCell.fxml"));
                AnchorPane cell = loader.load();

                PastPensieveCellController controller = loader.getController();
                controller.setTransaction(transaction);

                observableList.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PensieveList.setItems(observableList);
    }

    @FXML
    void Transfer_Btn(ActionEvent event) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.TRANSFER);
    }

    @FXML
    void View_Btn(ActionEvent event) {
        
    }
    
    @FXML
    void ViewReceipt_Btn(ActionEvent event) {

    }
    
    @FXML
    void handleFilterChoiceBox(ActionEvent event){
    if (FilterChoiceBox != null) {
        String selectedFilter = FilterChoiceBox.getValue();
        if (selectedFilter != null) {
            FilterInputPane.getChildren().clear();
            switch (selectedFilter) {
                case "Threshold":
                    addThresholdInputs();
                    break;
                case "Date":
                    addDateInputs();
                    break;
                case "Category":
                    addCategoryInputs();
                    break;
            }
            // Make the FilterInputPane visible after selecting a filter
            FilterInputPane.setVisible(true);
        }
    }
    }
    
    private void addThresholdInputs() {
        TextField minField = new TextField();
        minField.setPromptText("Min Value");

        TextField maxField = new TextField();
        maxField.setPromptText("Max Value");

        VBox vbox = new VBox(10, minField, maxField);
         vbox.setPadding(new Insets(10, 0, 0, 0));
        FilterInputPane.getChildren().add(vbox);
    }
    
    private void addDateInputs() {
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        VBox vbox = new VBox(10, startDatePicker, endDatePicker);
        vbox.setPadding(new Insets(10, 0, 0, 0));
        FilterInputPane.getChildren().add(vbox);
    }

    private void addCategoryInputs() {
        ChoiceBox<String> categoryChoiceBox = new ChoiceBox<>();
        categoryChoiceBox.setItems(FXCollections.observableArrayList("Leisure Luxe", "Tasty Treats", "Enigmatic Essentials", "Utility Utopia", "Mystic Magic","Assorted Awe"));

        VBox vbox = new VBox(10, categoryChoiceBox);
        vbox.setPadding(new Insets(10, 0, 0, 0));
        FilterInputPane.getChildren().add(vbox);
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int userId = Model.getInstance().getUserId();
        try {
            String accountNumber = AccountNumber.getOrCreateAccountNumber(userId);
             if(accountNumber!=null){
            AccNumberText.setText(accountNumber);
             }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        setDateLabel();
        loadTransactionsFromDatabase(Model.getInstance().getUserId());
        populateListView();
        populateBalance();
        FilterInputPane.setVisible(false);
        FilterChoiceBox.setItems(FXCollections.observableArrayList("Threshold", "Date", "Category"));
        
        SessionManager.currentAddedTransactionProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                transactionList.add(newValue);
            loadTransactionsFromDatabase(Model.getInstance().getUserId());
            populateListView();
            populateBalance();
            }
        });
    }
    
    
    private void setDateLabel(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = currentDate.format(formatter);
        DateText.setText(formattedDate);
    }
    
    private void loadTransactionsFromDatabase(int usersId) {
        String query = "SELECT * FROM transaction WHERE sender = ? OR receiver = ?";

        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
                PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, usersId);
            stmt.setInt(2, usersId);

            try (ResultSet rs = stmt.executeQuery()) {
                transactionList.clear();
                while (rs.next()) {
                    String transactionId = rs.getString("transactionid");
                    int senderId = rs.getInt("sender");
                    int receiverId = rs.getInt("receiver");
                    double amount = rs.getDouble("amount");
                    String currency = rs.getString("currency");
                    String message = rs.getString("additional");
                    String category = rs.getString("category");
                    Timestamp timestamp = rs.getTimestamp("date");
                    LocalDateTime transactionDate = timestamp.toLocalDateTime();

                    Transaction transaction = new Transaction(transactionId, senderId, receiverId, amount, currency, message, category, transactionDate);
                    transactionList.add(transaction);
                }

        } 
    }catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("load transaction for db");
    }
    
    private void populateBalance(){
        
        ObservableList<String> currencies = Database.loadCurrencyIntoList();
        int userId = Model.getInstance().getUserId();
        
        ListViewBalance.setCellFactory(param -> new ListCell<String>() {
       
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item);
                setFont(Font.font("System", FontWeight.BOLD, 12));
                setTextAlignment(TextAlignment.CENTER);
            }
        }
        
    });
        ListViewBalance.getItems().clear(); // Clear existing items
        for (String currency : currencies) {
            double balance = getBalanceForCurrency(currency, userId);
            ListViewBalance.getItems().add(currency + ": " + balance);
        }

        System.out.println("Populated balances");
    }
    
    private double getBalanceForCurrency(String currency, int userId) {
        double balance = 0.0;
        try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT " + currency + " FROM user_account WHERE usersId = ?");
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    balance = resultSet.getDouble(currency);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
    
    
    private void populateListView() {
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();
        transactionList.sort(Comparator.comparing(Transaction::getTransactionDate).reversed());
        
        for (Transaction transaction : transactionList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/PastPensieveCell.fxml"));
                AnchorPane cell = loader.load();

                PastPensieveCellController controller = loader.getController();
                controller.setTransaction(transaction);

                observableList.add(cell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PensieveList.setItems(observableList);
        
        System.out.println("Populating list view");
    }
}
    

