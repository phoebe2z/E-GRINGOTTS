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
    
    private Transaction transaction;
    

    @FXML
    void View_Btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setTransaction(Transaction transaction) {
        
        String name = checkForNameInDatabase(Model.getInstance().getUserId(), transaction.getReceiverId(), transaction.getSenderId());
        
        
        this.transaction = transaction;
        DateLbl.setText(transaction.getTransactionDate().toString());
        ReceiverLbl.setText(name);
        AmountLbl.setText(transaction.getAmount() + " " + transaction.getCurrency());
        CategoryLbl.setText(transaction.getCategory());
    }
    
    
public String checkForNameInDatabase(int userId, int receiverId, int senderId) {
    int name;
    if (userId == receiverId) {
        name = senderId;
    } else {
        name = receiverId;
    }

    String username = null;
    String query = "SELECT * FROM users_information WHERE usersid = ?";

    try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
         PreparedStatement stmt = connection.prepareStatement(query)) {
        
        stmt.setInt(1, name);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                username = rs.getString("username");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return username;
}
    
}
