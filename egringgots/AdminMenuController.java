/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AdminMenuController implements Initializable {
    


    @FXML
    private VBox AdminBase;

    @FXML
    private AnchorPane BasePane;

    @FXML
    private Button CardButton;

    @FXML
    private Button DashboardButton;

    @FXML
    private Button DepoButton;

    @FXML
    private Line Line_Divider;

    @FXML
    private Button LogoutButton;

    @FXML
    private VBox MenuPanel;

    @FXML
    private Button ProfileButton;

    @FXML
    private Text Title;

    @FXML
    private VBox TitlePanel;

    @FXML
    private Button ViewButton;
    
    @FXML
    private Button AddCurrencyBtn;

    @FXML
    void Card_Btn() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.USERCARDDETAIL);
    }

    @FXML
    void Dashboard_Btn() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.DASHBOARD);
    }

    @FXML
    void Depo_Btn() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.DEPOSIT);
    }

    @FXML
    void Logout_Btn() { 
        Account account = SessionManager.getCurrentUser();
        account.clearUserData();
        SessionManager.clearSession();
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.LOGOUT);
        Stage stage = (Stage) MenuPanel.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        
    }

    @FXML
    void Profile_Btn() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.PROFILE);
    }

    @FXML
    void View_Btn() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOption.VIEWUSER);
    }
    
        @FXML
    void Add_Btn(ActionEvent event) {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }    

    private void addListeners() {
        DashboardButton.setOnAction(event->Dashboard_Btn());
        DepoButton.setOnAction(event->Depo_Btn());
        CardButton.setOnAction(event->Card_Btn());
        ViewButton.setOnAction(event->View_Btn());
        ProfileButton.setOnAction(event->Profile_Btn());
        LogoutButton.setOnAction(event->Logout_Btn());
    }
    
    
}
