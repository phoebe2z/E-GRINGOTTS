/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author User
 */
public class SessionManager {    

    private static ObjectProperty<Account> currentUser = new SimpleObjectProperty<>();
    private static ObjectProperty<Card> currentUserCreditCard = new SimpleObjectProperty<>();
    private static ObjectProperty<Card> currentUserDebitCard = new SimpleObjectProperty<>();
    
    public static Account getCurrentUser() {
        return currentUser.get();
    }
    
    public static Card getCurrentDebitCard(){
        return currentUserDebitCard.get();
    }
    
    public static Card getCurrentCreditCard(){
        return currentUserDebitCard.get();
    }

    public static void setCurrentUser(Account account) {
        currentUser.set(account);
    }
    
    public static void setCurrentDebitCard(Card card){
        currentUserDebitCard.set(card);
        System.out.println("Setter Debit method being invoke");
    }
    
    public static void setCurrentCreditCard(Card card){
        currentUserDebitCard.set(card);
        System.out.println("Setter Credit method being invoke");
    }

    public static ObjectProperty<Account> currentUserProperty() {
        return currentUser;
    }
    
    public static ObjectProperty<Card> currentUserDebitCardProperty(){
        return currentUserDebitCard;
    }
    
    public static ObjectProperty<Card> currentUserCreditCardProperty(){
        return currentUserDebitCard;
    }
    
    


    public static void clearSession() {
        currentUser.set(null);
        currentUserCreditCard.set(null);
        currentUserDebitCard.set(null); 
    }
    
    
}
