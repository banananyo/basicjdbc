package com.basic.test;

import com.basic.driverManager.oracle.CustomDriverManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Logger;

public class CustomPreparedStatement {
    static CustomDriverManager d;
    static Connection connect;
    public static void main(String[] args) {
        //queryTest();

        //insertTest();

        deleteTest();

    }
    public static void queryTest(){
        //query
		d = new CustomDriverManager();
		connect = d.getConnection();
		try {
            PreparedStatement s = connect.prepareStatement("select * from Customer where firstname =?");
            s.setString(1,"baze");
			ResultSet result = s.executeQuery();
			while(result.next()){
				System.out.println(result.getString("C_ID")+" : "+result.getString("FIRSTNAME")+" : "+result.getString("LASTNAME")+" : "+result.getString("AGE"));
			}
			result.close();
			s.close();
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void insertTest(){
        d = new CustomDriverManager();
        connect = d.getConnection();
        try {
			PreparedStatement psmt = connect.prepareStatement("Insert into Customer (C_ID,FIRSTNAME,LASTNAME,AGE) values (?,?,?,?)");

			psmt.setInt(1,3);
			psmt.setString(2, "new");
			psmt.setString(3, "name");
			psmt.setInt(4, 44);
			//psmt.setDate(5, new java.sql.Date(new Date().getTime()));
			psmt.executeUpdate();
            psmt.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTest(){
        d = new CustomDriverManager();
        connect = d.getConnection();
        try {
            PreparedStatement psmt = connect.prepareStatement("delete FROM Customer WHERE c_id=99");
            psmt.executeUpdate();
            psmt.close();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
