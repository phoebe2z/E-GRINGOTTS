/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package egringgots;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author User
 */
public class Account<E> {
    private String userId;
    private String name, username, password, email, DOB, address;
    private long phoneNum, safetyPin, point;
    private String[] idList, nameList, usernameList, passwordList, emailList, DOBList, addressList, phoneList, pinList, pointList;
    final String fileName = "src/user/usersData.xlsx";
    int userIndex;
    

    public Account() {
        updateAccount();
    }
   
    
    public boolean createAccount(String userId, String name, String username, String password, String email, String DOB, String address, long phoneNum, long safetyPin, long point){
        for (int i = 0; i < usernameList.length; i++){
            if (username.equals(usernameList[i]) || email.equals(emailList[i])) {
                return false;
            }
        }
        
            try
        {
            FileInputStream fis = new FileInputStream(new File(fileName));
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(userId);
            row.createCell(1).setCellValue(name);
            row.createCell(2).setCellValue(username);  
            row.createCell(3).setCellValue(password);          
            row.createCell(4).setCellValue(email);
            row.createCell(5).setCellValue(DOB);
            row.createCell(6).setCellValue(address);
            row.createCell(7).setCellValue(phoneNum);
            row.createCell(8).setCellValue(safetyPin);
            row.createCell(9).setCellValue("0");

            
            try (FileOutputStream fileOut = new FileOutputStream(fileName))
            {
                wb.write(fileOut);
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
            updateAccount();
            UsernameEmailCheck(username, password);
        return true;
    }
    
    public void updateAccount(){
    
        try 
        {
            FileInputStream fis = new FileInputStream(new File(fileName));
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            
            int lastIndex = sheet.getLastRowNum();
            usernameList = new String[lastIndex];
            emailList = new String[lastIndex];
            passwordList = new String[lastIndex];
            
            for(int i = 0; i < lastIndex; i++)
            {
                Row row = sheet.getRow(i + 1);
                Cell cell = row.getCell(0);
                idList[i] = cell.getStringCellValue();
                cell = row.getCell(1);
                nameList[i] = cell.getStringCellValue();
                cell = row.getCell(2);
                usernameList[i] = cell.getStringCellValue();
                cell = row.getCell(3);
                cell.setCellType(CellType.STRING);
                passwordList[i] = cell.getStringCellValue();
                cell = row.getCell(4);
                emailList[i] = cell.getStringCellValue();
                cell = row.getCell(5);
                DOBList[i] = cell.getStringCellValue();
                cell = row.getCell(6);
                addressList[i] = cell.getStringCellValue();
                cell = row.getCell(7);
                phoneList[i] = cell.getStringCellValue();
                cell = row.getCell(8);
                pinList[i] = cell.getStringCellValue();
                cell = row.getCell(9);
                pointList[i] = cell.getStringCellValue();
            }
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println(ex);
        } 
        catch (IOException ioe) 
        {
            System.out.println(ioe);
        }
    }

    private boolean UsernameEmailCheck(String username, String password) {
            for (int i = 0; i < usernameList.length; i++)
        {
            if ((username.equals(usernameList[i]) || username.equals(emailList[i])) && password.equals(passwordList[i]))
            {
                try 
                {
                    FileInputStream fis = new FileInputStream(new File("src/user/usersData.xlsx"));
                    XSSFWorkbook wb = new XSSFWorkbook(fis);
                    XSSFSheet sheet = wb.getSheetAt(0);
                    
                    Row row = sheet.getRow(i + 1);
                    userIndex = i + 1;
                    
                    userId = row.getCell(0).getStringCellValue();
                    name = row.getCell(1).getStringCellValue();
                    username = row.getCell(2).getStringCellValue();
                    Cell cell = row.getCell(3);
                    cell.setCellType(CellType.STRING);
                    password = cell.getStringCellValue();
                    email = row.getCell(4).getStringCellValue();
                    DOB = row.getCell(5).getStringCellValue();
                    address = row.getCell(6).getStringCellValue();
                    phoneNum = (int) row.getCell(7).getNumericCellValue();
                    safetyPin = (int) row.getCell(8).getNumericCellValue();
                    point = (long) row.getCell(9).getNumericCellValue();

                    

                }
                catch (FileNotFoundException ex) 
                {
                    System.out.println(ex);
                }
                catch (IOException ioe) 
                {
                    System.out.println(ioe);
                }
                
                return true;
            }
        }
        
        return false;
    }
    
    public boolean EditProfile(String userId, String name, String username, String email, String DOB, String address){
        for (int i = 0; i < usernameList.length; i++) {
            if (username.equals(usernameList[i]) || email.equals(emailList[i])) {
                return false;
            }
        }
        
        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);

            Row row = sheet.getRow(userIndex);

            row.getCell(1).setCellValue(name);
            row.getCell(2).setCellValue(username);
            row.getCell(4).setCellValue(email);
            row.getCell(5).setCellValue(DOB);
            row.getCell(6).setCellValue(address);
            this.name=name;
            this.username = username;
            this.email = email;
            this.DOB = DOB;
            this.address = address;
            nameList[userIndex - 1] = name;
            usernameList[userIndex - 1] = username;
            emailList[userIndex - 1] = email;
            DOBList[userIndex - 1] = DOB;
            addressList[userIndex - 1] = address;
            

            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                wb.write(fileOut);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return true;
    }
    }
    
    

    
    
    
    

