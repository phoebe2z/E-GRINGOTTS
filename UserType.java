/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

/**
 *
 * @author User
 */
public class UserType<T> {
    protected Account<T> account;
    private int points;
    
    public UserType(Account<T> account, int points) {
        this.account = account;
        this.points=points;
    }

    // Getters and Setters
    public Account<T> getAccount() {
        return account;
    }

    public void setAccount(Account<T> account) {
        this.account = account;
    }
}
