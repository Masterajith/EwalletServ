package com.ewallet.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcode {
	public Connection DB() throws SQLException
	{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/ewallet1","root","root");  
		return con;
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		System.out.println(e2);
	}
	return null;  

	
}
}
