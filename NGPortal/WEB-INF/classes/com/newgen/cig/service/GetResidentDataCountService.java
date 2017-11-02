/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : GetResidentDataCountService.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 30, 2017
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.CommonEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;

public class GetResidentDataCountService {
	 InitialContext Context;
	 DataSource ds;
	 DatabaseQuery databaseQ = new DatabaseQuery();
	
	public HashMap getResidentDataCountService(Connection conn ,AadhaarRegistrationEntity commonEntity){
		HashMap resultMap = new HashMap();
		String sql_getNotification=databaseQ.getNotificationCount();
		String sql_getPendingApp = databaseQ.getPendinAppCount();
		String sql_getAppliedApp = databaseQ.getAppliedAppCount();
		String sql_getdraftApp = databaseQ.getDraftCount();
		String sql_getlastLogin = databaseQ.getlastLogin();
		String notificationCount = null;
		String pendingAppCount = null;
		String draftCount = null;
		String appliedAppCount = null;
		String lastLoginTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date date=new Date();
		try{
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
					try(PreparedStatement ps_notification = conn.prepareStatement(sql_getNotification);){
					ps_notification.setString(1, commonEntity.getUsername());
						try(ResultSet rs_notification = ps_notification.executeQuery();){
							while(rs_notification.next())
							{
								notificationCount = rs_notification.getString("count");
							}
							resultMap.put("NotificationCount", notificationCount);
						}
					}
			
					try(PreparedStatement ps_getPendingApp = conn.prepareStatement(sql_getPendingApp);){
						ps_getPendingApp.setString(1, commonEntity.getUsername());
						try(ResultSet rs_pendingApp = ps_getPendingApp.executeQuery();){
							while(rs_pendingApp.next())
							{
								pendingAppCount = rs_pendingApp.getString("count");
							}
							//resultMap.put("PendingAppCount", pendingAppCount);
						}
					}
					try(PreparedStatement ps_getdraftApp = conn.prepareStatement(sql_getdraftApp);){
						ps_getdraftApp.setString(1, commonEntity.getUsername());
						try(ResultSet rs_getdraftApp = ps_getdraftApp.executeQuery();){
							while(rs_getdraftApp.next())
							{
								draftCount = rs_getdraftApp.getString("count");
							}
							resultMap.put("PendingAppCount", (Integer.parseInt(draftCount)+Integer.parseInt(pendingAppCount)));
						}
					}
					
					try(PreparedStatement ps_AppliedApp = conn.prepareStatement(sql_getAppliedApp);){
						ps_AppliedApp.setString(1, commonEntity.getUsername());
						try(ResultSet rs_appliedApp = ps_AppliedApp.executeQuery();){
							while(rs_appliedApp.next())
							{
								appliedAppCount = rs_appliedApp.getString("count");
							}
							resultMap.put("appliedAppCount", appliedAppCount);
						}
					}
					
					try(PreparedStatement ps_getlastLogin = conn.prepareStatement(sql_getlastLogin);){
						ps_getlastLogin.setString(1, commonEntity.getUsername());
						try(ResultSet rs_getlastLogin = ps_getlastLogin.executeQuery();){
							while(rs_getlastLogin.next())
							{
								lastLoginTime = rs_getlastLogin.getString("lastlogin");
							}
							if("".equalsIgnoreCase(lastLoginTime) || lastLoginTime == null)
								resultMap.put("lastLoginTime", sdf.format(date));
							else
								resultMap.put("lastLoginTime", lastLoginTime);
						}
					}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultMap;
	}
	
	public HashMap getResidentDataCountServiceFromWeb(CommonEntity commonEntity,HashMap resultMap){
		String sql_getNotification=databaseQ.getNotificationCount();
		String sql_getPendingApp = databaseQ.getPendinAppCount();
		String sql_getAppliedApp = databaseQ.getAppliedAppCount();
		String sql_getdraftApp = databaseQ.getDraftCount();
		String sql_getlastLogin = databaseQ.getlastLogin();
		String notificationCount = null;
		String pendingAppCount = null;
		String draftCount = null;
		String appliedAppCount = null;
		String lastLoginTime = null;
		try{
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn =ds.getConnection()){
					try(PreparedStatement ps_notification = conn.prepareStatement(sql_getNotification);){
				ps_notification.setString(1, commonEntity.getUserId());
				try(ResultSet rs_notification = ps_notification.executeQuery();){
					while(rs_notification.next())
					{
						notificationCount = rs_notification.getString("count");
					}
					resultMap.put("NotificationCount", notificationCount);
				}
			}
			
			try(PreparedStatement ps_getPendingApp = conn.prepareStatement(sql_getPendingApp);){
				ps_getPendingApp.setString(1, commonEntity.getUserId());
				try(ResultSet rs_pendingApp = ps_getPendingApp.executeQuery();){
					while(rs_pendingApp.next())
					{
						pendingAppCount = rs_pendingApp.getString("count");
					}
					//resultMap.put("PendingAppCount", pendingAppCount);
				}
			}
			try(PreparedStatement ps_getdraftApp = conn.prepareStatement(sql_getdraftApp);){
				ps_getdraftApp.setString(1, commonEntity.getUserId());
				try(ResultSet rs_getdraftApp = ps_getdraftApp.executeQuery();){
					while(rs_getdraftApp.next())
					{
						draftCount = rs_getdraftApp.getString("count");
					}
					resultMap.put("PendingAppCount", (Integer.parseInt(draftCount)+Integer.parseInt(pendingAppCount)));
				}
			}
			
			try(PreparedStatement ps_AppliedApp = conn.prepareStatement(sql_getAppliedApp);){
				ps_AppliedApp.setString(1, commonEntity.getUserId());
				try(ResultSet rs_appliedApp = ps_AppliedApp.executeQuery();){
					while(rs_appliedApp.next())
					{
						appliedAppCount = rs_appliedApp.getString("count");
					}
					resultMap.put("appliedAppCount", appliedAppCount);
				}
			}
			
			try(PreparedStatement ps_getlastLogin = conn.prepareStatement(sql_getlastLogin);){
				ps_getlastLogin.setString(1, commonEntity.getUserId());
				try(ResultSet rs_getlastLogin = ps_getlastLogin.executeQuery();){
					while(rs_getlastLogin.next())
					{
						lastLoginTime = rs_getlastLogin.getString("lastlogin");
					}
					resultMap.put("lastLoginTime", lastLoginTime);
				}
			}
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultMap;
	}

}
