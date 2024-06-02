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
import javafx.util.converter.LongStringConverter;


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
    
    private static boolean editCreditCard;
    private static boolean editDebitCard;
    
    
        @FXML
    void EditCC_Btn(ActionEvent event) {
        setEditCreditCard(true);
        setEditDebitCard(false);
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.EDITCARDDETAIL);
    }

    @FXML
    void EditDB_Btn(ActionEvent event) {
        setEditCreditCard(false);
        setEditDebitCard(true);
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.EDITCARDDETAIL);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }    
    
    public void invokeCardDetailController(){
        System.out.println("Initialize method");
        addCardCreditListener();
        addCardDebitListener();

    }
    
        private void addCardCreditListener() {
    if(checkCardInDb(Model.getInstance().getUserId(), "Credit Card")){
        Card creditCard = Card.getCardDetails(Model.getInstance().getUserId(), "Credit Card");
        updateCreditCardUI(creditCard);
    } else {
        System.out.println("No data cc in database");
    }
    }
        
        private void addCardDebitListener(){
            if(checkCardInDb(Model.getInstance().getUserId(), "Debit Card")){
        Card debitCard = Card.getCardDetails(Model.getInstance().getUserId(), "Debit Card");
        updateDebitCardUI(debitCard);
        } else {
        System.out.println("No data dc in database");
    }
        }
     
    public static void setEditCreditCard(boolean cond){
        editCreditCard = cond;
    }

    public static void setEditDebitCard(boolean cond){
        editDebitCard = cond;
    }
        
    public static boolean editCreditCard(){
        return editCreditCard;
    }
    
    public static boolean editDebitCard(){
        return editDebitCard;
    }
    
    private void updateCreditCardUI(Card creditCard) {
        System.out.println("Updating cc");
        CCNum.setText(formatCardNumber(creditCard.getCardNumber()));
        CCCvvNum.setText(String.valueOf(creditCard.getcVV()));
        CCDate.setText(creditCard.getExpiryDate());
    }

    private void updateDebitCardUI(Card debitCard) {
        System.out.println("Updating Debit card");
        DCNum.setText(formatCardNumber(debitCard.getCardNumber()));
        DCCvvNum.setText(String.valueOf(debitCard.getcVV()));
        DCDate.setText(debitCard.getExpiryDate());
    }
    
    private static boolean checkCardInDb(int userId, String type){
        try {
            System.out.println("Checking card in db");
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
    
        public Card populateCardDataFromDB(int usersId, String typeCard){
            Card card = new Card();
            SessionManager.setCurrentCreditCard(card);
            System.out.println("Populating card data from db");
            String query = "SELECT * FROM user_card WHERE usersId = ? AND cardtype = ?";
            try (Connection connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USERNAME, Constant.DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, usersId);
            statement.setString(2, typeCard);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    card.setCardNumber(resultSet.getLong("cardnumber"));
                    card.setcVV(resultSet.getInt("cvv"));
                    card.setExpiryDate(resultSet.getString("expirydate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
            return card;
    }
}
