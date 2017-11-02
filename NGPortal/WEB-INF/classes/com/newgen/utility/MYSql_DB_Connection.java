/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : MYSql_DB_Connection.java
* Author              : Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Apr 23, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MYSql_DB_Connection {
	
	public static Connection getConnection() throws SQLException
	{
		ResourceBundle rsb = ResourceBundle.getBundle("com.newgen.utility.db_Credential_MySql");
		Connection conn = null;
		/* DB Credential */
		String hostIP = rsb.getString("host_IP_PORT");
		String db_Name = rsb.getString("db_Name");
		
		String db_Url = "jdbc:mysql://"+hostIP+"/"+db_Name+"";
		String db_userName = rsb.getString("db_userName");
		String db_password = rsb.getString("db_password");
		
		try
		{
			/* Driver Registration */
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection( db_Url , db_userName, db_password );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return conn ;		
	}


}
