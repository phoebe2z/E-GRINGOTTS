
package egringgots;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String userId;
    private double amount;
    private double balance;
    private Date dateOfTrans;
    private String category;

    public Transaction(String userId, double amount, double balance, String category) {
        this.userId = userId;
        this.amount = amount;
        this.balance = balance;
        this.dateOfTrans = new Date();
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }


    public double getAmount() {
        return amount;
    }


    public double getBalance() {
        return balance;
    }


    public String getDateOfTrans() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yy");
        return dateFormat.format(dateOfTrans);
    }


    public String getCategory() {
        return category;
    }

    public boolean isValid() {
        return amount > 0 && amount <= balance;
    }

}

