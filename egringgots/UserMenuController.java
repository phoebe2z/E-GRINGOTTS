/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author User
 */
public class UserMenuController implements Initializable{
    
    @FXML
    private VBox Base;

    @FXML
    private AnchorPane BasePane;

    @FXML
    private Button CardButton;

    @FXML
    private Button DashboardButton;

    @FXML
    private Button ExchangeButton;

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
    private Button TransactionButton;

    @FXML
    void Card_Btn() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.CARDDETAIL);
    }

    @FXML
    void Dashboard_Btn() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.DASHBOARD);

    }

    @FXML
    void Exchange_Btn(ActionEvent event) {

    }

    @FXML
    void Logout_Btn() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.LOGOUT);
    }

    @FXML
    void Profile_Btn() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.PROFILE);
    }

    @FXML
    void Transaction_Btn() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.TRANSACTION);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addListeners();
    }

    private void addListeners() {
        DashboardButton.setOnAction(event->Dashboard_Btn());
        TransactionButton.setOnAction(event->Transaction_Btn());
        CardButton.setOnAction(event->Card_Btn());
        ProfileButton.setOnAction(event->Profile_Btn());
        LogoutButton.setOnAction(event->Logout_Btn());
    }




    
}
