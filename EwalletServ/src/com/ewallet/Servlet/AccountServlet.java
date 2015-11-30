package com.ewallet.Servlet;

import com.ewallet.DAO.AccountServices;
import com.ewallet.DAO.DBcode;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountServlet extends HttpServlet
implements Serializable {
    private static final long serialVersionUID = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        PrintWriter out=response.getWriter();
        String accemail = (String)session.getAttribute("loginid");
        System.out.println(accemail);
        int bal = 0;
        bal = Integer.parseInt(request.getParameter("Amount"));
        DBcode dbcode = new DBcode();
        AccountServices as=new AccountServices();
        try {
            Statement st = dbcode.DB().createStatement();
            String sql = "select name,email from users";
            ResultSet rs = st.executeQuery(sql);
            block2 : while (rs.next()) {
                String dbname = rs.getString(1);
                String dbemail = rs.getString(2);
                if (!accemail.equals(dbemail)) continue;
                PreparedStatement ps1 = dbcode.DB().prepareStatement("select count(accountid) from accounts where accountemail=? ");
                ps1.setString(1, accemail);
                ResultSet r = ps1.executeQuery();
                int count=0;
                if(r.next())
                 count=r.getInt(1);
                if(count==0)
                {
                	as.Insert(dbname, dbemail, bal);//setting values to accounts db
               	break;
                }
                else
                {
                	Statement st1 = dbcode.DB().createStatement();
                    String sql2 = "select accountid, accountemail,balance from accounts";
                    ResultSet rs1 = st1.executeQuery(sql2);
                    while (rs1.next()) {
                        String dbaccid = rs1.getString(1);
                        String dbaccemail = rs1.getString(2);
                        if (!dbaccemail.equals(accemail)) continue;
                        int dbbal = rs1.getInt(3);
                        System.out.println("balance is" + (bal += dbbal));
                      
                       as.Update(bal, dbaccid);//updating accounts db
                        break;
                        
                }
                break;
               } 
            }
            //Retrieving balance
            as.Balance(accemail,bal);
            out.print("current bal is"+bal);
        }           
        
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}