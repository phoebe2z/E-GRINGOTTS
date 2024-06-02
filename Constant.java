/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Constant {
    
    public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/egringgots";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Happy123.";
    public static final String DB_USERS_TABLE_NAME = "USERS_INFORMATION";
    public static final String DB_ADMIN_TABLE_NAME = "ADMIN_INFORMATION";
    public static final String DB_USERS_TABLE_CARD = "user_card";
    public static final String DB_CURRENCY = "CURRENCY";
    public static final String DB_EXCHANGE_CURRENCY = "EXCHANGECURRENCY";
    

}
