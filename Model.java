/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import javafx.beans.property.IntegerProperty;

/**
 *
 * @author User
 */
public class Model {
    private int userId;
    private static Model model;
    private final ViewFactory viewFactory;
    
    
    private Model(){
        this.viewFactory = new ViewFactory();
    }
    
    public static synchronized Model getInstance(){
        if (model==null){
            model = new Model();
        }
        return model;
    }
    
    public ViewFactory getViewFactory(){
        return viewFactory;
    }
    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public IntegerProperty userIdProperty() {
//        return userId;
//    }
    

    
    
    
}
