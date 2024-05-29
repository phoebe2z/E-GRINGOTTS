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
    public static Account getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(Account account) {
        currentUser.set(account);
    }

    public static ObjectProperty<Account> currentUserProperty() {
        return currentUser;
    }


    public static void clearSession() {
        currentUser.set(null);
        
    }
    
    
}
