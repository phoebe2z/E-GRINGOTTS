/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author User
 */
public class Transfer {
    private final StringProperty name;
    private final StringProperty mobileNum;
    private final StringProperty pfpPath;
    private final StringProperty friendPath;
    
    public Transfer(String name, String mobileNum, String pfpPath, String friendPath){
        this.name = new SimpleStringProperty(this, "Name", name);
        this.mobileNum = new SimpleStringProperty(this, "Mobile Number", mobileNum);
        this.pfpPath = new SimpleStringProperty(this, "Pfp Path", pfpPath);
        this.friendPath = new SimpleStringProperty(this, "Friend Path", friendPath);
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public StringProperty mobileNumProperty(){
        return mobileNum;
    }
    
    public StringProperty pfpPathProperty(){
        return pfpPath;
    }
    
    public StringProperty friendPathProperty(){
        return friendPath;
    }
    
}
