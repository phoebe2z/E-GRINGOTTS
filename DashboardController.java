/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import Database.Constant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 *
 * @author User
 */
public class DashboardController implements Initializable{

    @FXML
    private Text AccNumberText;
    
    @FXML
    private Text AccountText;

    @FXML
    private StackPane BasePane;

    @FXML
    private AnchorPane BasePanel;

    @FXML
    private Text C1Text;

    @FXML
    private Text C2Text;

    @FXML
    private Text C3Text;


    @FXML
    private Button ClearBtn;

    @FXML
    private Text DateText;

    @FXML
    private HBox DetailHbox;

    @FXML
    private VBox DetailVbox;
    
    @FXML
    private TableColumn<?, ?> BalanceColumn;

    @FXML
    private TableView<?> BalanceTable;

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
    

    private List<Transaction> transactionList = new ArrayList<>();

    @FXML
    void Clear_Btn(ActionEvent event) {

    }

    @FXML
    void Filter_Btn(ActionEvent event) {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("11111111111111");
        int userId = Model.getInstance().getUserId();
       
        try {
            System.out.println("DDDDDDDDDDDD");
            String accountNumber = AccountNumber.getOrCreateAccountNumber(userId);
             System.out.println("Initialize");
             if(accountNumber!=null){
            AccNumberText.setText(accountNumber);
             }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setDateLabel();
        loadTransactionsFromDatabase(Model.getInstance().getUserId());
        populateListView();
        
        
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
                    Timestamp transactionDate = rs.getTimestamp("date");

                    Transaction transaction = new Transaction(transactionId, senderId, receiverId, amount, currency, message, category, transactionDate);
                    transactionList.add(transaction);
                }

        } 
    }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private void populateListView() {
        ObservableList<AnchorPane> observableList = FXCollections.observableArrayList();

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
    }
}
    

