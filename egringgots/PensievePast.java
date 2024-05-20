/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class PensievePast {
    private final StringProperty receiver, sender,  category;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;

    public PensievePast(LocalDate date, String receiver, String sender, double amount,  String category) {
        
        this.date = new SimpleObjectProperty<>(this, "Date", date);
        this.receiver = new SimpleStringProperty(this, "Receiver", receiver);
        this.sender = new SimpleStringProperty(this, "Sender", sender);
        this.amount = new SimpleDoubleProperty(this, "Amount", amount);
        this.category = new SimpleStringProperty(this, "Category", category); 
    }
    
    public StringProperty receiverProperty() {
        return this.receiver;
    }
    
    public StringProperty senderProperty() {
        return this.sender;
    }
    
    public DoubleProperty amountProperty(){
        return this.amount;
    }
    
    public ObjectProperty<LocalDate> dateProperty(){
        return this.date;
    }
    
    public StringProperty categoryProperty() {
        return this.category;
    }
    
    
    
    
    
    
}
