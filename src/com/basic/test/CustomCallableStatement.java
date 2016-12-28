package com.basic.test;


import java.sql.*;

import com.basic.driverManager.oracle.CustomDriverManager;
import oracle.jdbc.driver.OracleTypes;

public class CustomCallableStatement {
    static CustomDriverManager d;
    static Connection connect;
    public static void main(String[] args) {

        callTestProcedure();
        callTestFunction();

    }
    public static void callTestProcedure(){
        //query
        d = new CustomDriverManager();
        connect = d.getConnection();
        CallableStatement callableStatement = null;
        try {
			callableStatement = connect.prepareCall("{call procedureTest(?,?,?)}");
            callableStatement.setString(1,"baze");
            callableStatement.registerOutParameter(2, Types.VARCHAR);
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
			callableStatement.executeQuery();
            String returnString = callableStatement.getString(2);
            ResultSet resultSet = (ResultSet) callableStatement.getObject(3);
            System.out.println("Result: "+returnString);
            while(resultSet.next()){
                System.out.println("id: "+resultSet.getInt(1));
                System.out.println("    firstname: "+resultSet.getString(2));
                System.out.println("    lastname: "+resultSet.getString(3));
                System.out.println("    age: "+resultSet.getInt(4));
                System.out.println();
            }
            callableStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callTestFunction(){
        //query
        d = new CustomDriverManager();
        connect = d.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = connect.prepareCall("{? = call functionTest(?,?)}");
            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.setString(2,"baze");
            callableStatement.setString(3,"softplus");
            ResultSet resultSet = callableStatement.executeQuery();
            String resultString = callableStatement.getString(1);
            System.out.println("ResultString: "+resultString);
//            while(resultSet.next()){
//                System.out.println("id: "+resultSet.getInt(1));
//                System.out.println("    firstname: "+resultSet.getString(2));
//                System.out.println("    lastname: "+resultSet.getString(3));
//                System.out.println("    age: "+resultSet.getInt(4));
//                System.out.println();
//            }
            callableStatement.close();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
