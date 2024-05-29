/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class UserController implements Initializable{
    public BorderPane user_parent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observableValue, oldVal, newVal)->{
            switch(newVal){
                case TRANSACTION -> user_parent.setCenter(Model.getInstance().getViewFactory().getTransactionView());
                case PROFILE -> user_parent.setCenter(Model.getInstance().getViewFactory().getEditUserProfileView());
                case CARDDETAIL -> user_parent.setCenter(Model.getInstance().getViewFactory().getCardDetailView());
                case EDITCARDDETAIL -> user_parent.setCenter(Model.getInstance().getViewFactory().getEditCardDetailView());
                case EXCHANGE -> user_parent.setCenter(Model.getInstance().getViewFactory().getExchangeView());
                case TRANSFER -> user_parent.setCenter(Model.getInstance().getViewFactory().getMarauderView());
                case EDITBUTTON -> user_parent.setCenter(Model.getInstance().getViewFactory().getEditUserProfileButton());
                case CHANGEPASSBUTTON -> user_parent.setCenter(Model.getInstance().getViewFactory().getChangeUserPassPinButton());
                case LOGOUT -> {Stage stage = (Stage) user_parent.getScene().getWindow();
                                Model.getInstance().getViewFactory().closeStage(stage);
                                Model.getInstance().getViewFactory().showLoginWindow();}
                default -> user_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });

    }
    
    
}
