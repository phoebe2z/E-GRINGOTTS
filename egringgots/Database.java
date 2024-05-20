/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/egringgots", "root", "");
            
            return connect;
        
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
