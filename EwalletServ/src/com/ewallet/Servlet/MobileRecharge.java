package com.ewallet.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ewallet.DAO.DBcode;

public class MobileRecharge extends HttpServlet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(true);
	        PrintWriter out=response.getWriter();
	        response.setContentType("text/html");
	        String accemail = (String)session.getAttribute("loginid");
	        String mobileno=request.getParameter("mobileno");
	        int amount=Integer.parseInt(request.getParameter("Amount"));
	        int bal=0;
	        DBcode dbcode = new DBcode();
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
			         out.print("Recharge is successful\n");
					 out.print("\namount"+amount+"is recharged with mobile no" +mobileno);
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

