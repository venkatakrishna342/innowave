/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : InstituteUserActionCount.java
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

import com.newgen.cig.entity.CommonEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;

public class InstituteUserActionCount {

	 InitialContext Context;
	 DataSource ds;
	 DatabaseQuery databaseQ = new DatabaseQuery();
	
	public HashMap getInstituteDataCountService(CommonEntity commonEntity,HashMap resultMap){
		String sql_getNotification=databaseQ.getNotificationCount();
		String sql_getPendingApp = databaseQ.getInstPendingCount();
		String sql_getApprovedApp = databaseQ.getApprovedAppCount();
		String sql_getSanctionedApp = databaseQ.getSanctionedAppCount();
		String sql_getDisbursedApp = databaseQ.getDisbursedAppCount();
		String sql_getRejectedApp = databaseQ.getRejectedAppCount();
		String sql_getlastLogin = databaseQ.getlastLogin();

		String notificationCount = null;
		String pendingAppCount = null;
		String approvedAppCount = null;
		String sanctionedAppCount = null;
		String disbursedAppCount = null;
		String lastLoginTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date date=new Date();
		try{
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();){
					try(PreparedStatement ps_notification = conn.prepareStatement(sql_getNotification);){
					ps_notification.setString(1, commonEntity.getUserId());
						try(ResultSet rs_notification = ps_notification.executeQuery();){
							while(rs_notification.next())
							{
								notificationCount = rs_notification.getString("count");
							}
							resultMap.put("InstNotificationCount", notificationCount);
						}
					}
			System.out.println(notificationCount+" ,,,,  "+commonEntity.getUserId());
					try(PreparedStatement ps_getPendingApp = conn.prepareStatement(sql_getPendingApp);){
						ps_getPendingApp.setString(1, commonEntity.getUserId());
					//	System.out.println(ps_getPendingApp.toString());
						try(ResultSet rs_pendingApp = ps_getPendingApp.executeQuery();){
							while(rs_pendingApp.next())
							{
								pendingAppCount = rs_pendingApp.getString("count");
							}
							resultMap.put("InstPendingAppCount", pendingAppCount);
						}
					}
					
			//********* for Approved App Count ************************************/		
					try(PreparedStatement ps_getApprovedApp = conn.prepareStatement(sql_getApprovedApp);){
						ps_getApprovedApp.setString(1, commonEntity.getInstituteId());
						ps_getApprovedApp.setString(2, commonEntity.getRoleId());
						System.out.println(sql_getApprovedApp.toString());
						try(ResultSet rs_getApprovedApp = ps_getApprovedApp.executeQuery();){
							while(rs_getApprovedApp.next())
							{
								approvedAppCount = rs_getApprovedApp.getString("count");
							}
							resultMap.put("ApprovedAppCount", approvedAppCount);
						}
					}
					
					//********* for sanctioned App Count ************************************/		
					try(PreparedStatement ps_getSanctionedApp = conn.prepareStatement(sql_getSanctionedApp);){
						ps_getSanctionedApp.setString(1, commonEntity.getInstituteId());
						ps_getSanctionedApp.setString(2, commonEntity.getRoleId());
						try(ResultSet rs_getSanctionedApp = ps_getSanctionedApp.executeQuery();){
							while(rs_getSanctionedApp.next())
							{
								sanctionedAppCount = rs_getSanctionedApp.getString("count");
							}
							resultMap.put("SanctionedAppCount", sanctionedAppCount);
						}
					}
					
					//********* for disbursed App Count ************************************/		
					try(PreparedStatement ps_getDisbursedApp = conn.prepareStatement(sql_getDisbursedApp);){
						ps_getDisbursedApp.setString(1, commonEntity.getInstituteId());
						ps_getDisbursedApp.setString(2, commonEntity.getRoleId());
						try(ResultSet rs_getDisbursedApp = ps_getDisbursedApp.executeQuery();){
							while(rs_getDisbursedApp.next())
							{
								disbursedAppCount = rs_getDisbursedApp.getString("count");
							}
							resultMap.put("DisbursedAppCount", disbursedAppCount);
						}
					}
					
					//********* for Rejected App Count ************************************/		
					try(PreparedStatement ps_getRejectedApp = conn.prepareStatement(sql_getRejectedApp);){
						ps_getRejectedApp.setString(1, commonEntity.getInstituteId());
						ps_getRejectedApp.setString(2, commonEntity.getRoleId());
						try(ResultSet rs_getDisbursedApp = ps_getRejectedApp.executeQuery();){
							while(rs_getDisbursedApp.next())
							{
								disbursedAppCount = rs_getDisbursedApp.getString("count");
							}
							resultMap.put("RejectedAppCount", disbursedAppCount);
						}
					}
					//********* for last login time ************************************/		
					try(PreparedStatement ps_getlastLogin = conn.prepareStatement(sql_getlastLogin);){
						ps_getlastLogin.setString(1, commonEntity.getUserId());
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
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultMap;
	}



}
