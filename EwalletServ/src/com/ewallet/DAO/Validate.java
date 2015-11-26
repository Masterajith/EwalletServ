package com.ewallet.DAO;

import java.sql.*;

public class Validate
 {
     public static boolean checkUser(String email,String password) 
     {
      boolean st =false;
      try{

	 //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");

 	 //creating connection with the database 
         Connection con=DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/ewallet1","root","root");
         PreparedStatement ps =con.prepareStatement
                             ("select * from Users where Email=? and Password=?");
         
     
        /* ps.equals(email);
         ps.equals(password);*/
       
         ps.setString(1, email);
         ps.setString(2, password);
         ResultSet rs =ps.executeQuery();
         st = rs.next();
        
      }catch(Exception e)
      {
          e.printStackTrace();
      }
         return st;                 
  }   
}