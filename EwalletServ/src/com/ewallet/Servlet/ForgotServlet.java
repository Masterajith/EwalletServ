package com.ewallet.Servlet;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;



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
        String dbpass=null,dbemail = null;
        boolean st =false;
      try
      {
      //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver").newInstance();

	 //creating connection with the database 
        Connection con=DriverManager.getConnection
                       ("jdbc:mysql://localhost:3306/ewallet","root","root");
        
        java.sql.Statement stmt = con.createStatement();
        String getQuery = "select * from Users";
        ResultSet resultSet = stmt.executeQuery(getQuery);
       
			       while (resultSet.next()) {
        	dbemail=resultSet.getString(4);
        	
        		if(fgemailid!=null && fgemailid.equals(dbemail))
        		{
        		dbpass=resultSet.getString(5);
        		System.out.println(dbpass);
        		out.print("your password is "+dbpass);
        		break;
        		}
        		
        
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