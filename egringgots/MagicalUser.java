/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

/**
 *
 * @author User
 */
public class MagicalUser<T> {
    private T userId;
    private String username;
    private String password;
    private String email;
    private String address;

    // Constructor
    public MagicalUser(T userId, String username, String password, String email, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}
