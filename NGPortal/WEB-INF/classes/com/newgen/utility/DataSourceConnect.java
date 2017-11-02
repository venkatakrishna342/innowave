/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DataSourceConnect.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 26, 2017
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
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceConnect {
	InitialContext Context;
    DataSource ds;
    Connection conn=null;
	public Connection getDataSourceConnection() throws NamingException, SQLException
	{
		try{
		  Context = new javax.naming.InitialContext();
          ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
           conn = ds.getConnection();
		}
		catch(Exception e){
			e.printStackTrace();
		}
          return conn;
	}
	

}
