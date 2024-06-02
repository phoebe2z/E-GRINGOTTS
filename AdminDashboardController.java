/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package egringgots;

import Database.Database;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AdminDashboardController implements Initializable {

    @FXML
    private AnchorPane Base;

    @FXML
    private VBox BaseVbox;

    @FXML
    private HBox DividerHbox;

    @FXML
    private Label GraphTitle;

    @FXML
    private AnchorPane GraphTitlePane;

    @FXML
    private VBox GraphVbox;

    @FXML
    private HBox HboxGraph;

    @FXML
    private Button LeftBtn;

    @FXML
    private AnchorPane LeftPane;

    @FXML
    private AnchorPane Panel;

    @FXML
    private PieChart PieGraph;

    @FXML
    private Button RightBtn;

    @FXML
    private AnchorPane RightPane;

    @FXML
    private Text TotOfUserText;

    @FXML
    private DatePicker TotTransDate;

    @FXML
    private Label TotTransLabel;

    @FXML
    private AnchorPane TotTransPanel;

    @FXML
    private Text TotTransText;

    @FXML
    private Label TotUserLabel;

    @FXML
    private AnchorPane TotUserPane;

    @FXML
    private VBox TransVbox;

    @FXML
    private VBox UserVbox;

    @FXML
    void Left_Btn(ActionEvent event) {

    }

    @FXML
    void Righ_Btn(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int userCount =Database.getUserCount();
        String count = Integer.toString(userCount);
        TotOfUserText.setText(count);
    }    
    
}
