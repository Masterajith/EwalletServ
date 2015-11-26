package com.ewallet.Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  

String n=request.getParameter("name");  
String p=request.getParameter("mobileno");  
String e=request.getParameter("email");  
String c=request.getParameter("password");  
          
try{  
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/ewallet1","root","root");  
 java.sql.Statement stmt = con.createStatement();
 String getQuery = "select * from Users";
 ResultSet resultSet = stmt.executeQuery(getQuery);
 int size= 0;
 if (resultSet != null)   
 {  
	 resultSet.beforeFirst();  
	 resultSet.last();  
	 size = resultSet.getRow();  
 }
 
PreparedStatement ps=con.prepareStatement(  
"insert into Users values(?,?,?,?,?)"); 
ps.setInt(1,size+1);  
ps.setString(2,n);  
ps.setString(3,p);  
ps.setString(4,e);  
ps.setString(5,c);  
          
int i=ps.executeUpdate();  
if(i>0)  
out.print("You are successfully registered...");  
          
}catch (Exception e2) {System.out.println(e2);}  
          
out.close();  
}  
  
}  

