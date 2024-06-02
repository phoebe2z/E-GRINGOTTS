/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

/**
 *
 * @author User
 */
public class TransferCellFactory extends ListCell<Transfer> {
    
    protected void updateItem(Transfer transfer, boolean empty){
        super.updateItem(transfer, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/TransferCell.fxml"));
            TransferCellController controller = new TransferCellController(transfer);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
