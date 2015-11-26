package com.ewallet.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountServices {
	
	
	 DBcode dbcode=new DBcode();
	 
	
	//getting current balance
	public int Balance(String accemail,int bal) throws SQLException
	{
		
		PreparedStatement ps3;
	
			ps3 = dbcode.DB().prepareStatement("select balance from accounts where accountemail=?");
	
         ps3.setString(1, accemail);
        ResultSet rt=ps3.executeQuery();
        rt.next();
         int currentbal=rt.getInt(1);
         System.out.println("currentbal is"+currentbal);
         		 bal=currentbal;
         	
         		 return bal;
		}
		
		
		
	//setting values to account db
	public void Insert(String dbname,String dbemail,int bal) throws SQLException
	{
		String sql1 = "insert into accounts(accountname,accountemail,balance) values(?,?,?)";
        PreparedStatement ps = dbcode.DB().prepareStatement(sql1);
        ps.setString(1, dbname);
        ps.setString(2, dbemail);
        ps.setInt(3, bal);
        int i1 = ps.executeUpdate();
        if (i1 > 0) 
            System.out.println("excuted");
       }
	//updating values in account db
	public void Update(int bal,String dbaccid) throws SQLException
	{
				 
		 PreparedStatement ps2 = dbcode.DB().prepareStatement("update accounts set balance=? where accountid=?");
         ps2.setInt(1, bal);
         ps2.setString(2, dbaccid);
         int i = ps2.executeUpdate();
         if (i > 0)
         System.out.println("updated successfully");
	}

	
	
	
}
