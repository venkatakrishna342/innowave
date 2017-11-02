/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : UserLoginLogoutService.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : may 2, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;

public class UserLoginLogoutService {
	InitialContext Context;
    DataSource ds;
    DatabaseQuery databaseQ = new DatabaseQuery();
    
	public HashMap logoutService(AadhaarRegistrationEntity getLogoutEntity,HashMap resultMap)
	{
		System.out.println("logout service");
		
		String deleteUserConn = databaseQ.getDeleteUserConnection();
		String updateUserHistory1 = databaseQ.updateUserHistory1();
		String updateUserHistory = databaseQ.updateUserHistory();
		String subQuery = databaseQ.subQuery();
		String maxData=null;
		try{
		 
	          if (getLogoutEntity == null)
	          {
	              resultMap.put("resultCode", -2);
	              resultMap.put("resultDetails", "Invalid input");
	              return resultMap;
	          }
  
        	  Context = new javax.naming.InitialContext();
              ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
              
              
            	  try(Connection conn = ds.getConnection();){
            	  conn.setAutoCommit(false);
        		  try(PreparedStatement psDelete = conn.prepareStatement(deleteUserConn);){
        			  psDelete.setString(1,getLogoutEntity.getUsername());
        			  
        			  int countFlag=psDelete.executeUpdate();
        			  if(countFlag>0)
        			  {
        				  //String subQuery= databaseQ.subQuery();
        				  try(PreparedStatement psselect = conn.prepareStatement(subQuery);){
        					  psselect.setString(1,getLogoutEntity.getUsername());
        					  try(ResultSet rs=psselect.executeQuery();){
        						  if(rs.next())
        						  {
        							  maxData=rs.getString("maxDate");
        						  }
        					  }
        				  }
        			  }
        		  }
	
					  try(PreparedStatement psupdate = conn.prepareStatement(updateUserHistory);){
						  psupdate.setString(1,getLogoutEntity.getUsername());
						  psupdate.setString(2,maxData);
						  System.out.println(psupdate.toString());
						  int countFlag1=psupdate.executeUpdate();
						  System.out.println(countFlag1);
						  if(countFlag1>0)
						  {
							  conn.commit();
							  resultMap.put("resultCode", 0);
							  resultMap.put("resultDetails", "Successfully logout");
						  }
					  }
		  			}
	  			 }
		
              catch(Exception e){
            	  e.printStackTrace();
              }
		return resultMap;
	}
	
	
	
	
	

}
