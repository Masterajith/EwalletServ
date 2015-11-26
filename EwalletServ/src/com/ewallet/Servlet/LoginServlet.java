package com.ewallet.Servlet;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ewallet.DAO.Validate;

import java.sql.*;

public class LoginServlet extends HttpServlet implements Serializable{
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String loginemail=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("loginid");
        String password = request.getParameter("password");
 
        
     
       
        if(Validate.checkUser(email, password))
        {
        	 
        	 //sending to another servlet
        	HttpSession session = request.getSession(true);
        	session.setAttribute("loginid", email);
            RequestDispatcher rs = request.getRequestDispatcher("DashBoard.html");
            rs.forward(request,response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("Login.html");
           rs.include(request, response);
        }
        
       
    }

	
}