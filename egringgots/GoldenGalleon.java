/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

/**
 *
 * @author User
 */
public class GoldenGalleon<T> extends MagicalUser<T> {
    
    private T type;

    public GoldenGalleon(T userId, String username, String password, String email, String address, T type) {
        super(userId, username, password, email, address);
        this.type=type;
    }

   
    
}
