/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author User
 */
public class UserAvatar<E> {
    private E userId;
    private String imagePath;
    int userIndex;
    final String fileName = "src/user/usersData.xlsx";

    public UserAvatar(E userId, String imagePath) {
        this.userId = userId;
        this.imagePath = imagePath;
    }
    
    public void EditUserAvatar(String imagePath){
        this.imagePath = imagePath;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            Row row = sheet.getRow(userIndex);
            row.getCell(10).setCellValue(imagePath);

            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                wb.write(fileOut);
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }
    
    
    
}
