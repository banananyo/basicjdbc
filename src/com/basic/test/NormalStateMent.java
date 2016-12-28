package com.basic.test;


import com.basic.driverManager.oracle.CustomDriverManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NormalStateMent {
    static CustomDriverManager d;
    static Connection connect;
    static Statement stm;
    public static void main(String[] args) {

        //deleteTest("xxx");
        //insertTest(2,"jackson","K",33); //insert
        //queryTest(); //query
        //updateTest(2,"jacksonx","M",44); // update
    }
    public static void queryTest(){
        d = new CustomDriverManager();
        connect = d.getConnection();
        try {
            stm = connect.createStatement();
            ResultSet result = stm.executeQuery("select c_id, firstname, lastname, age from Customer");
            while(result.next()){
                StringBuffer stringBuf = new StringBuffer();
                stringBuf.append("ID: ");
                stringBuf.append(result.getInt("c_id"));
                stringBuf.append(" FIRSTNAME: ");
                stringBuf.append(result.getString("firstname"));
                stringBuf.append(" LASTNAME: ");
                stringBuf.append(result.getString("lastname"));
                stringBuf.append(" AGE: ");
                stringBuf.append(result.getInt("age"));
                System.out.println(stringBuf);
            }
            result.close();
            stm.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertTest(int c_id,String fname, String lname, int age){
        d = new CustomDriverManager();
        connect = d.getConnection();
        try {
            connect.setAutoCommit(false);// 1 transaction
            stm = connect.createStatement();
            int id = stm.executeUpdate("Insert into Customer (c_id,firstname,lastname,age) " + "values ('" +c_id+ "','"+fname+"','"+lname+"','"+age+"')");
            connect.commit();
            System.out.println(id);
            stm.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateTest(int c_id, String fname, String lname, int age){
        d = new CustomDriverManager();
        connect = d.getConnection();
        try {
            connect.setAutoCommit(false);// 1 transaction
            stm = connect.createStatement();
            int id = stm.executeUpdate("Update Customer set firstname='"+fname+"' lastname='"+lname+"',age='"+age+"' where c_id='"+c_id+"'");
            connect.commit();
            System.out.println(id);
            stm.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTest(String fname){
        d = new CustomDriverManager();
        connect = d.getConnection();
        try {
            connect.setAutoCommit(false);// 1 transaction
            stm = connect.createStatement();
            int id = stm.executeUpdate("Delete from Customer where firstname='"+fname+"'");
            connect.commit();
            System.out.println(id);
            stm.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
