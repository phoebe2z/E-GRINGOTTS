/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Constant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * FXML Controller class
 *
 * @author User
 */
public class CardDetailController implements Initializable {
    
        @FXML
    private AnchorPane BasePanel;

    @FXML
    private AnchorPane CCCard;

    @FXML
    private Text CCCvv;

    @FXML
    private Text CCCvvNum;

    @FXML
    private Text CCDate;

    @FXML
    private Text CCED;

    @FXML
    private Text CCNum;

    @FXML
    private Rectangle CCRec;

    @FXML
    private Text CCTitle;

    @FXML
    private AnchorPane DCCard;

    @FXML
    private Text DCCvv;

    @FXML
    private Text DCCvvNum;

    @FXML
    private Text DCDate;

    @FXML
    private Text DCEd;

    @FXML
    private Text DCNum;

    @FXML
    private Rectangle DCRec;

    @FXML
    private Text DCTitle;

    @FXML
    private Button EditCCButton;

    @FXML
    private Button EditDBButton;
    
    private static Boolean editCreditCard;
    private static Boolean editDebitCard;
    
    
        @FXML
    void EditCC_Btn(ActionEvent event) {
        editCreditCard = true;   
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.EDITCARDDETAIL);
    }

    @FXML
    void EditDB_Btn(ActionEvent event) {
        editDebitCard = true;
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.EDITCARDDETAIL);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SessionManager.currentUserProperty().addListener((obs, oldUser, newUser) -> {
            if (newUser != null) {
                int userId = Model.getInstance().getUserId();
                if (checkCardInDb(userId, "Credit Card")) {
                        updateUserCard(userId, "Credit Card");
                        System.out.println("Credit Card updated");
                } 
                if (checkCardInDb(userId, "Debit Card")) {
                        updateUserCard(userId, "Debit Card");
                }
            } else {
                System.out.println("Error in displaying card detail!");
            }
         });  
        
    }    
    
    public static boolean editCreditCard(){
        return editCreditCard;
    }
    
    public static boolean editDebitCard(){
        return editDebitCard;
    }
    
    private void updateUserCard(int userId, String typeCard){
        Card card = new Card();
        card.setUserId(userId);
        card.populateCardDataFromDB(userId, typeCard);

        String cardNum = formatCardNumber(card.getCardNumber());
        String cvv = Integer.toString(card.getcVV());
        String expiryDate = card.getExpiryDate();

        if (typeCard.equals("Credit Card")) {
            CCNum.setText(cardNum);
            CCCvvNum.setText(cvv);
            CCED.setText(expiryDate);
        } else {
            DCNum.setText(cardNum);
            DCCvvNum.setText(cvv);
            DCEd.setText(expiryDate);
        }
}
    
    private boolean checkCardInDb(int userId, String type){
        try {
            Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);

            PreparedStatement checkCardExists = connection.prepareStatement("SELECT * FROM " + Constant.DB_USERS_TABLE_CARD + " WHERE USERSID = ? AND CARDTYPE = ?");
            checkCardExists.setInt(1, userId);
            checkCardExists.setString(2, type);

            ResultSet resultSet = checkCardExists.executeQuery();
            if (!resultSet.isBeforeFirst()) { 
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String formatCardNumber(long number) {
        String str = Long.toString(number);
        StringBuilder formatted = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append(' ');
            }
            formatted.append(str.charAt(i));
        }
        return formatted.toString();
    }
    
    
}
