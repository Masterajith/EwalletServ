																																																package com.ewallet.Servlet;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ewallet.DAO.DBcode;

import java.sql.*;

public class ForgotServlet extends HttpServlet implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String fgemailid = request.getParameter("EmailId");
        String dbpass=null;
  
        DBcode dbcode=new DBcode();
      try
      {
    
        if(fgemailid!=null)
        {
       PreparedStatement ps=dbcode.DB().prepareStatement("select password from users where email=?");
       ps.setString(1, fgemailid);
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        	dbpass=resultSet.getString(1);
          out.print("your password is"+dbpass);   
        }

        
      }
      catch(Exception e)
      {
    	  e.printStackTrace();
      }
        
     /*    if(ForgotDB.checkUser(EmailId))
        {
        	 

            RequestDispatcher rs = request.getRequestDispatcher("Forgot password.html");
            rs.forward(request, response);
            System.out.print("password");
            
        }
        else
        {
           out.println("Your Email id is incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("Forgot password.html");
           rs.include(request, response);
        }*/
    }  
}