package com.ewallet.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ParseConversionEvent;

import com.ewallet.DAO.AccountServices;
import com.ewallet.DAO.DBcode;
import com.mysql.jdbc.PreparedStatement;

public class MoneyRetrive extends HttpServlet implements Serializable {

		private static final long serialVersionUID = 1L;
		 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        HttpSession session = request.getSession(true);
		        PrintWriter out=response.getWriter();
		        response.setContentType("text/html");
		        int bal=0;
		        String accemail = (String)session.getAttribute("loginid");
		        int amount=Integer.parseInt(request.getParameter("Amount"));
		        int acno=Integer.parseInt(request.getParameter("acno"));
		        DBcode dbcode = new DBcode();
		        AccountServices as=new AccountServices();
		        try {
		        	java.sql.PreparedStatement ps3;
		        	
					ps3 = dbcode.DB().prepareStatement("select balance from accounts where accountemail=?");
			
		         ps3.setString(1, accemail);
		        ResultSet rt=ps3.executeQuery();
		        rt.next();
		         bal=rt.getInt(1);
		         
					 if(amount<bal)
					 {
					 bal=bal-amount;
					 java.sql.PreparedStatement ps=dbcode.DB().prepareStatement("select accountid from accounts where accountemail=?");
					ps.setString(1,accemail);
					 ResultSet rs1 = ps.executeQuery();
					 rs1.next();
					 String dbaccid=rs1.getString(1);
					 
					 java.sql.PreparedStatement ps2 = dbcode.DB().prepareStatement("update accounts set balance=? where accountid=?");
			         ps2.setInt(1, bal);
			         ps2.setString(2, dbaccid);
			         int i = ps2.executeUpdate();
			         if (i > 0)
			         System.out.println("updated successfully");
					 out.print("\namount"+amount+"is tranfered to A/c No:" +acno);
					 out.print("\nRemaining balance is"+bal);
					 }
					 else
					 {
						 out.print("insuffieint balance!"); 
					 }
						 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	        }           
		        
		        
}

