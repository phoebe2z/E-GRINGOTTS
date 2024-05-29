/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

/**
 *
 * @author User
 */
public class Model {
    private int userId;
    private static Model model;
    private final ViewFactory viewFactory;
    private RegisterPageController registerPageController;
    
    
    private Model(){
        this.viewFactory = new ViewFactory();
        registerPageController = new RegisterPageController();
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
    
    public RegisterPageController getRegisterPageController() {
        return registerPageController;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    

    
    
    
}
