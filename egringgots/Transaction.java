
package egringgots;
import java.util.Date;

public class Transaction {
    private String userId;
    private String sender;
    private String receiver;
    private String currency;
    private double amount;
    private double balance;
    private Date dateOfTrans;
    private String category;
    private String additionalMessage;

    public Transaction(String userId, String sender, String receiver, String currency, double amount, double balance, Date dateOfTrans, String category, String additionalMessage) {
        this.userId = userId;
        this.sender = sender;
        this.receiver = receiver;
        this.currency = currency;
        this.amount = amount;
        this.balance = balance;
        this.dateOfTrans = dateOfTrans;
        this.category = category;
        this.additionalMessage = additionalMessage;
    }

    public String getUserId() {
        return userId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public Date getDateOfTrans() {
        return dateOfTrans;
    }

    public String getCategory() {
        return category;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setDateOfTrans(Date dateOfTrans) {
        this.dateOfTrans = dateOfTrans;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAdditionalMessage(String additionalMessage) {
        this.additionalMessage = additionalMessage;
    }

    
}

