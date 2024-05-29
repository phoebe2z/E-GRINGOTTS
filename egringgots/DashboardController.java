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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 *
 * @author User
 */
public class DashboardController implements Initializable{

    
    @FXML
    private Text AccountText;

    @FXML
    private StackPane BasePane;

    @FXML
    private AnchorPane BasePanel;

    @FXML
    private Text C1Text;

    @FXML
    private Text C2Text;

    @FXML
    private Text C3Text;


    @FXML
    private Button ClearBtn;

    @FXML
    private Text DateText;

    @FXML
    private HBox DetailHbox;

    @FXML
    private VBox DetailVbox;

    @FXML
    private HBox DividerHbox;

    @FXML
    private Button FilterBtn;

    @FXML
    private AnchorPane MyAccPanel;

    @FXML
    private Text MyAccText;

    @FXML
    private VBox MyAccountVBox;

    @FXML
    private Text PastText;

    @FXML
    private ListView<?> PensieveList;

    @FXML
    private ScrollBar PensieveScroll;

    @FXML
    private AnchorPane RightPanel;

    @FXML
    private Text SendText;

    @FXML
    private Button TransferBtn;

    @FXML
    private Text ValueText;

    @FXML
    private Button ViewBtn;
    

    @FXML
    private AnchorPane firstCurrency;

    @FXML
    private AnchorPane secCurrency;

    @FXML
    private AnchorPane thirdCurrency;

    @FXML
    void Clear_Btn(ActionEvent event) {

    }

    @FXML
    void Filter_Btn(ActionEvent event) {

    }

    @FXML
    void Transfer_Btn(ActionEvent event) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOption.TRANSFER);
    }

    @FXML
    void View_Btn(ActionEvent event) {
        
    }
    
    @FXML
    void ViewReceipt_Btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
