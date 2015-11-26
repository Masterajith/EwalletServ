package com.ewallet.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class program1 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("driver is loaded");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ewallet1","root","root");
		System.out.print("connection is opened");
		Statement stmt=con.createStatement();
		System.out.print("Statement object created");
		int i= stmt.executeUpdate("insert into student values(10101,ram,`600`)");
		System.out.println(i+"row is inserted");
		stmt.close();
		con.close();
		System.out.print("connection is closed");
	}

}
