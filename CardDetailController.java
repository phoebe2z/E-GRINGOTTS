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

            loadCCData(Model.getInstance().getUserId(), SessionManager.getCurrentCreditCard());
            SessionManager.currentUserCreditCardProperty().addListener((obs, oldCC, newCC) -> {
                if(newCC != null){
                    loadCCData(Model.getInstance().getUserId(), newCC);
                } else { unbindCCUI();
                        setCCDefault();
                }
            });

        loadDCData(Model.getInstance().getUserId(), SessionManager.getCurrentDebitCard());
        SessionManager.currentUserDebitCardProperty().addListener((obs, oldDC, newDC) -> {
            if(newDC != null){
                loadDCData(Model.getInstance().getUserId(), newDC);
            } else { 
                unbindDCUI();
                setDCDefault();
            }     
        });
    }
      
      private void setCCDefault(){
            CCNum.setText("XXXX XXXX XXXX XXXX");
            CCCvvNum.setText("XXX");
            CCDate.setText("mm/yy");
      }
      
      private void setDCDefault(){
            DCNum.setText("XXXX XXXX XXXX XXXX");
            DCCvvNum.setText("XXX");
            DCDate.setText("mm/yy");
      }
      
    private void loadCCData(int userId, Card card) {
        if (checkCardInDb(userId, "Credit Card")) {
            bindCreditCardUI(card);
        } else {
            System.out.println("Unbinding cc data");
            unbindCCUI(); // Unbind GUI components
            setCCDefault(); // Set default values
        }
    }

    private void loadDCData(int userId, Card card) {
        if (checkCardInDb(userId, "Debit Card")) {
            bindDebitCardUI(card);
        } else {
            System.out.println("Unbinding dc data");
            unbindDCUI(); // Unbind GUI components
            setDCDefault(); // Set default values
        }
    }

     private void bindCreditCardUI(Card card) {
        if (card != null) {
            CCNum.textProperty().bind(card.cardNumberProperty().asString());
            CCCvvNum.textProperty().bind(card.cVVProperty().asString());
            CCDate.textProperty().bind(card.expiryDateProperty());
        }
    }

    private void bindDebitCardUI(Card card) {
        if (card != null) {
            DCNum.textProperty().bind(card.cardNumberProperty().asString());
            DCCvvNum.textProperty().bind(card.cVVProperty().asString());
            DCDate.textProperty().bind(card.expiryDateProperty());
        }
    }
    
    private void unbindDCUI(){
        DCNum.textProperty().unbind();
        DCCvvNum.textProperty().unbind();
        DCDate.textProperty().unbind();
    }
    
    private void unbindCCUI(){
        CCNum.textProperty().unbind();
        CCCvvNum.textProperty().unbind();
        CCDate.textProperty().unbind();
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
    
}
