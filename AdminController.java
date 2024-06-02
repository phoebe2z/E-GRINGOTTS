/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal)->{
            switch(newVal){
                case VIEWUSER -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminUserView());
                case DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDepositView());
                case USERCARDDETAIL -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminUserCardView());
                case ADDCURRENCY -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminAddCurrencyView());
                case PROFILE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getEditUserProfileView());
                case LOGOUT -> {Stage stage = (Stage) admin_parent.getScene().getWindow();
                                Model.getInstance().getViewFactory().closeStage(stage);
                                Model.getInstance().getViewFactory().showLoginWindow();}
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDashboardView());
            }
        });
    }    
    
}
