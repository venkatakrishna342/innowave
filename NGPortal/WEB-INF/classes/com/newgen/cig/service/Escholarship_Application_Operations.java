/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Escholarship_Application_Operations.java
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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonObject;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;

/**
 * @author Varun Saddi
 *
 */
public class Escholarship_Application_Operations {

	InitialContext Context;
    DataSource ds;
    DatabaseQuery databaseQ = new DatabaseQuery();
    PreparedStatement ps = null;
    
	public JsonObject upadate_Pref_AppId(String applicationId, int applicationPrefOrder, String applicationStatus) {
		
		//Initializing return object
    	JsonObject resultObj = new JsonObject();
    	
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	        
	        try (Connection conn = ds.getConnection();) {
	        	conn.setAutoCommit(false);
				
	        	/**
	        	 * Get Max Preference Set for User Applied Schemes
	        	 */
	        	int max_Pref = 0; 
				String maxPref_Query = databaseQ.getMaxSchemePref();
				ps = conn.prepareStatement(maxPref_Query);
				ps.setString(1, applicationId);		
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					max_Pref = rs.getInt("HighestPref");
					if (rs.wasNull()) {
						resultObj.addProperty("ResulCode", -3);
						resultObj.addProperty("Message", "No appications & preferenes found related to this application id.");
						return  resultObj;
					}
				}
				
				/**
				 * Check whether any other scheme has same preference order as of rejected scheme
				 */
				int same_Pref_Count = 0; 
				String samePrefCount_Query = databaseQ.getSameSchemePrefCount();
				ps = conn.prepareStatement(samePrefCount_Query);
				ps.setInt(1, applicationPrefOrder);
				ps.setString(2, applicationId);
				rs = ps.executeQuery();
				while(rs.next()) {
					same_Pref_Count = rs.getInt("SimilarPrefCount");
					if (same_Pref_Count < 1) {
						resultObj.addProperty("ResulCode", -4);
						resultObj.addProperty("Message", "No applications found with given preference and application id.");
						return  resultObj;
					}
				}
				
				/**
				 * If Similar Preference Count is equal to 1 than find all applications with preference greater than
				 * given application id and decrease their preference order by 1 else don't do anything
				 */
				if (same_Pref_Count == 1) {
					
					String lowerPrefUpdate_Query = databaseQ.getLowerSchemePrefUpdate();
					ps = conn.prepareStatement(lowerPrefUpdate_Query);
					ps.setInt(1, applicationPrefOrder);
					ps.setString(2, applicationId);
					int updatePrefCount = ps.executeUpdate();
					if(updatePrefCount > 0) {	
						//Do Nothing
					}
					else {
						resultObj.addProperty("ResulCode", -5);
						resultObj.addProperty("Message", "Not able to update preferences order of other applications.");
						return  resultObj;
					}
					
					/*while(rs.next()) {
						
						//Iterate through all rows and update their preferences order and set preference order of 
						//current application to 0
						String app_id = rs.getString("app_id");
						int prefOrder = rs.getInt("pref_flag");
						prefOrder =  prefOrder - 1;
						
						String updateAppIdPref_Query = databaseQ.getLowerSchemePrefAppId();
						ps = conn.prepareStatement(lowerPrefAppId_Query);
						ps.setInt(1, applicationPrefOrder);
						ps.setString(2, applicationId);
						int updatePrefCount = ps.executeUpdate();
						if(updatePrefCount > 0) {	
							update test_acid set p_order = p_order - 1 where p_order > 2 and username = 'dheeraj';
						}
						else {
							resultObj.addProperty("ResulCode", -5);
							resultObj.addProperty("Message", "Not able to update preferences order of other applications.");
							return  resultObj;
						}
					}*/
				}
				else {
					//Do Nothing
				}
				
				/**
				 * Get Application Id's and respective preference orders for user whose application got rejected 
				 */
				String allAppIdPref_Query = databaseQ.getAllAppIdPref();
				ps = conn.prepareStatement(allAppIdPref_Query);
				ps.setString(1, applicationId);
				rs = ps.executeQuery();
				
				if(rs != null && rs.next()) {
					
					//Initializing Json Array object
			    	JsonObject appIdArrObj = new JsonObject();
			    	//Create Json Array of all Applications id's and their preference orders.
					while(rs.next()) {
						JsonObject appPrefObj = new JsonObject();
						appPrefObj.addProperty("Preference", rs.getInt("pref_flag"));
						appIdArrObj.add(rs.getString("app_id"), appPrefObj);
					}
					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message", "Success");
					resultObj.add("AppIdResultset", appIdArrObj);
				}
				else {
					resultObj.addProperty("ResulCode", -6);
					resultObj.addProperty("Message", "Problem in getting application id's and preference for user.");
				}
				
	        } catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		return resultObj;
	}
	/**
	 * This function is used to update application status and stage code .
	 * @param app_id
	 * @param status
	 * @return
	 */
	public JsonObject updateApprovedSchemePreference(String app_id, String status, String stageflag) {

		// Initializing return object
		JsonObject resultObj = new JsonObject();
		int stageCode = 0;
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());

			try (Connection conn = ds.getConnection();) {
				/**
				 * update scheme status respect to Application ID.
				 */
				String schemeStatus = databaseQ.updateApprovedSchemeStatus();
				ps = conn.prepareStatement(schemeStatus);
				ps.setString(1, status);
				ps.setString(2, app_id);
				System.out.println("Query : "+ps.toString());
				int updateCount = ps.executeUpdate();
				if (updateCount > 0) {
					resultObj.addProperty("ResulCodeStatus", 0);
					resultObj.addProperty("MessageStatus", "AppID = "+app_id+","+"Status = "+status);
				} else {
					resultObj.addProperty("ResulCodeStatus", -2);
					resultObj.addProperty("MessageStatus", "Not able to update application status .");
				}
			} catch (SQLException ex) {
				resultObj.addProperty("ResulCodeStatus", -1);
				resultObj.addProperty("MessageStatus", "Problem Occured while updating application Status.");
			}

			/**
			 * update scheme stage code respect to Application ID.
			 */
			try (Connection conn = ds.getConnection();) {
				String schemeStatus = databaseQ.updateApprovedSchemeStageCode();
				
				if (status.equalsIgnoreCase("Reject")) {
					if (stageflag.equalsIgnoreCase("institute"))
						stageCode = 3;
					else if (stageflag.equalsIgnoreCase("department"))
						stageCode = 5;
				}
					
				ps = conn.prepareStatement(schemeStatus);
				ps.setInt(1, stageCode);
				ps.setString(2, app_id);
				
				int updateCount = ps.executeUpdate();
				if (updateCount > 0) {
					resultObj.addProperty("ResulCodeStageCode", 0);
					resultObj.addProperty("MessageStageCode", "AppID = "+app_id+","+"Stage Code = "+stageCode);
				} else {
					resultObj.addProperty("ResulCodeStageCode", -2);
					resultObj.addProperty("MessageStageCode", "Not able to update application Stage Code.");
				}

			} catch (SQLException ex) {
				resultObj.addProperty("ResulCodeStageCode", -1);
				resultObj.addProperty("MessageStageCode", "Problem Occured while updating application Status.");
			}
			/**
			 * Get Application Id's and respective preference .
			 */
			try (Connection conn = ds.getConnection();) {
				conn.setAutoCommit(false);

				String allAppIdPref_Query = databaseQ.getAllAppIdPref();
				ps = conn.prepareStatement(allAppIdPref_Query);
				ps.setString(1, app_id);
				ResultSet rs = ps.executeQuery();

				if (rs != null && rs.next()) {

					// Initializing Json Array object
					JsonObject appIdArrObj = new JsonObject();
					// Create Json Array of all Applications id's and their
					// preference orders.
					while (rs.next()) {
						JsonObject appPrefObj = new JsonObject();
						appPrefObj.addProperty("Preference", rs.getInt("pref_flag"));
						appIdArrObj.add(rs.getString("app_id"), appPrefObj);
					}
					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message", "Success");
					resultObj.add("AppIdResultset", appIdArrObj);
				} else {
					resultObj.addProperty("ResulCode", -2);
					resultObj.addProperty("Message", "Problem in getting application id's and preference for user.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
				resultObj.addProperty("ResulCode", -1);
				resultObj.addProperty("Message", "Problem in getting application id's and preference for user.");
			}
		} catch (NamingException e) {
			e.printStackTrace();
			resultObj.addProperty("ResulCode", -3);
			resultObj.addProperty("Message", "Exception Occured while processing .");
		}

		return resultObj;
	}
    
    
}
