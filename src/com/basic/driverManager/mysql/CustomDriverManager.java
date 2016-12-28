package com.basic.driverManager.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomDriverManager {

    //String driverName = "oracle.jdbc.OracleDriver"; //Oracle
    String driverName = "com.mysql.jdbc.Driver"; //MySQL
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            //connection = DriverManager.getConnection("jdbc:oracle:thin:@172.16.0.32:1521:TWCMT1","SCOTT","TIGER"); //Oracle
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_train","root", ""); //MySQL
            System.out.println("Connect success!!");
        }catch(ClassNotFoundException e){
            System.out.println("Connect error Driver!!");
        }
        catch(SQLException e){
            System.out.println("Connect error!!");
        }
        return connection;
    }

}
