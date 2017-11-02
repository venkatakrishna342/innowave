/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : NotificationService.java
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.newgen.cig.entity.NotificationEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.dto.Set_Get_Data_PostLogIn;
import com.newgen.utility.AppConstants;

/**
 * @author Varun Saddi
 *
 */
public class NotificationService {

	InitialContext Context;
    DataSource ds;
    PreparedStatement ps = null;
	ResultSet rs = null ;
	String token = null;
	DatabaseQuery databaseQ = new DatabaseQuery();
	
	public ArrayList<NotificationEntity> getNotification(String userName, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
    	
		ArrayList<NotificationEntity> listData = new ArrayList<NotificationEntity>();

		if (httpServletRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
            	return listData;
            }
        }
		
    	try {
    		Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            try (Connection conn = ds.getConnection();) {
            	conn.setAutoCommit(false);
            	String sql = databaseQ.getUserNotifications();
            	try(PreparedStatement ps = conn.prepareStatement(sql);){
	            	ps.setString(1, userName);
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							NotificationEntity notEntity = new NotificationEntity();
							notEntity.setId(rs.getString("slno"));
							notEntity.setType(rs.getString("notification_type"));
							notEntity.setSubject(rs.getString("subject"));
							notEntity.setMessage(rs.getString("message"));
							notEntity.setDateTime(rs.getString("date_time"));
							listData.add(notEntity);
						}
					}
            	}
            }
    	}catch(Exception ex){
    		System.out.println("Error occurred in notification insertion :: " + ex);
    		ex.printStackTrace();
    	}
    	httpServletResponse.setHeader("token", token);
    	return listData;
    }
	
    public HashMap setNotification(NotificationEntity notificationEntity_data, HashMap resultMap,
    		HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    	
    	if (httpServletRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
                resultMap.put("resultCode", -1);
                resultMap.put("resultDetails", "Invalid Token");
                return resultMap;
            }
        }
    	
        if (notificationEntity_data == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid Input");
            return resultMap;
        }
        
    	try {
    		Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            try (Connection conn = ds.getConnection();) {
            	conn.setAutoCommit(false);
            	
            	Date dt = new Date();
            	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String currentTime = sdf.format(dt);
            	String sql = databaseQ.getSetUserNotification();
            	try(PreparedStatement ps = conn.prepareStatement(sql);){
	            	ps.setString(1, notificationEntity_data.getUserId());
	            	ps.setString(2, notificationEntity_data.getType());
	            	ps.setString(3, notificationEntity_data.getSubject());
	            	ps.setString(4, notificationEntity_data.getMessage());
	            	ps.setString(5, currentTime);
	            	int insertNotificationCount = ps.executeUpdate();
	    			if(insertNotificationCount > 0) {
	    				conn.commit();
	    				resultMap.put("resultCode", 0);
	                    resultMap.put("resultDetails", "Notification Sent Successfully");
	                    return resultMap;
	    			}
            	}
            }
    	}catch(Exception ex){
    		System.out.println("Error occurred in notification insertion :: " + ex);
    		ex.printStackTrace();
    		resultMap.put("resultCode", -3);
            resultMap.put("resultDetails", "NotificationService DB Exception: " + ex.toString());
    	}
    	
    	return resultMap;
    }

	public HashMap deleteNotification(String notificationSlNo, HashMap resultMap, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		if (httpServletRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
                resultMap.put("resultCode", -1);
                resultMap.put("resultDetails", "Invalid Token");
                return resultMap;
            }
        }
    	
        if (notificationSlNo == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid Input");
            return resultMap;
        }
        
    	try {
    		Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            try (Connection conn = ds.getConnection();) {
            	conn.setAutoCommit(false);
            	
            	Date dt = new Date();
            	SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String currentTime = sdf.format(dt);
            	String sql = databaseQ.getDeleteUserNotification();
            	try(PreparedStatement ps = conn.prepareStatement(sql);){
	            	ps.setInt(1, Integer.parseInt(notificationSlNo));
	            	int deleteNotificationCount = ps.executeUpdate();
	    			if(deleteNotificationCount > 0) {
	    				conn.commit();
	    				resultMap.put("resultCode", 0);
	                    resultMap.put("resultDetails", "Notification Deleted Successfully");
	                    return resultMap;
	    			}
            	}
    			ps.close();
            }
    	}catch(Exception ex){
    		System.out.println("Error occurred in notification deletion :: " + ex);
    		ex.printStackTrace();
    		resultMap.put("resultCode", -3);
            resultMap.put("resultDetails", "NotificationService DB Exception: " + ex.toString());
    	}
    	
    	return resultMap;
	}
}
