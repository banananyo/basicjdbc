package com.basic.driverManager.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomDriverManager {

    String driverName = "oracle.jdbc.OracleDriver"; //Oracle
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:49161:xe","baze","oracle"); //Oracle
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
