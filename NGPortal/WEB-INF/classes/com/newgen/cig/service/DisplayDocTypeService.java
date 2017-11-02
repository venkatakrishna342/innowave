/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DisplayDocTypeService.java
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
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.dto.Set_Get_Data_EditProfile;
import com.newgen.utility.AppConstants;

import JSON.JSONArray;
import JSON.JSONObject;

public class DisplayDocTypeService {
	
	 InitialContext Context;
	 DataSource ds;
	 Connection conn;
	 DatabaseQuery databaseQ = new DatabaseQuery();
	 
	 public ArrayList<Set_Get_Data_EditProfile> getDocTypeData(String field,String user_id) throws Exception
	 {
		   Context = new javax.naming.InitialContext();
           ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
           ArrayList<Set_Get_Data_EditProfile> listData = new ArrayList<Set_Get_Data_EditProfile>();
           try( Connection conn = ds.getConnection();){
	              String selectUserdoc =databaseQ.getselectUserdoc(field);
	              try( PreparedStatement ps= conn.prepareStatement(selectUserdoc);){
	            	  try(ResultSet rs = ps.executeQuery();){
			               while (rs.next()) 
			               {
			            	   Set_Get_Data_EditProfile dto=new Set_Get_Data_EditProfile();
			            	   dto.setDoc_type(rs.getString("doc_type"));
			            	   dto.setDoc_id(rs.getString("doc_id"));
			            	   dto.setDoc_id(rs.getString("doc_name"));
			            	   listData.add(dto);
			               }
	            	  }
               }
           }
           catch(Exception e)
           {
        	  e.printStackTrace(); 
           }
        
           
		return listData;
			
	 }
	 public String callDocType(String user_id) throws Exception
	 {
		 Context = new javax.naming.InitialContext();
		 ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//		 PreparedStatement ps=null;
//		 PreparedStatement ps1=null;
//		 ResultSet rs=null;
//		 ResultSet rs1=null;
		 ArrayList<Set_Get_Data_EditProfile> listData = new ArrayList<Set_Get_Data_EditProfile>();
		 JSONObject jsonObjFinal = new JSONObject();
		
		 try( Connection conn = ds.getConnection();){
					
				JSONArray  poaArr = new JSONArray();
				JSONArray  poiArr = new JSONArray();
				JSONArray  porArr = new JSONArray();
				JSONArray  dobArr = new JSONArray();
				String selectUsername = databaseQ.getProfileDocMasterData();
				try(PreparedStatement ps= conn.prepareStatement(selectUsername);){
					try(ResultSet rs = ps.executeQuery();){
			 
						while (rs.next()) {
							JSONObject  poaObj = new JSONObject();
							JSONObject  poiObj = new JSONObject();
							JSONObject  porObj = new JSONObject();
							JSONObject  dobObj = new JSONObject();
									 
							 if(rs.getString("poa").equalsIgnoreCase("Y")) {
								 poaObj.put("DocId", rs.getString("doc_id"));
								 poaObj.put("DocType", rs.getString("doc_type"));
								 poaObj.put("DocName", rs.getString("doc_name"));
								 poaArr.put(poaObj);
							 }
							 
							 if(rs.getString("poi").equalsIgnoreCase("Y")) {
								 poiObj.put("DocId", rs.getString("doc_id"));
								 poiObj.put("DocType", rs.getString("doc_type"));
								 poiObj.put("DocName", rs.getString("doc_name"));
								 poiArr.put(poiObj);
							 }
							 
							 if(rs.getString("por").equalsIgnoreCase("Y")) {
								 porObj.put("DocId", rs.getString("doc_id"));
								 porObj.put("DocType", rs.getString("doc_type"));
								 porObj.put("DocName", rs.getString("doc_name"));
								 porArr.put(porObj);
							 }
							 
							 if(rs.getString("dob").equalsIgnoreCase("Y")) {
								 dobObj.put("DocId", rs.getString("doc_id"));
								 dobObj.put("DocType", rs.getString("doc_type"));
								 dobObj.put("DocName", rs.getString("doc_name"));
								 dobArr.put(dobObj);
							 }
						}
				
						jsonObjFinal.put("POIList", poiArr);
						jsonObjFinal.put("POAList", poaArr);
						jsonObjFinal.put("PORList", porArr);
						jsonObjFinal.put("DOBList", dobArr);
					}
				}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace(); 
		 }
		 
		 //System.out.println(" :::::::::::::::::::::::  " +jsonObjFinal.toString());
		 return jsonObjFinal.toString();
		 
	 }
	 
	 public String getDataDocsTypeValues(String field,String user_id) throws Exception
	 {
		 Context = new javax.naming.InitialContext();
		 ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//		 PreparedStatement ps=null;
//		 PreparedStatement ps1=null;
//		 ResultSet rs=null;
//		 ResultSet rs1=null;
		 ArrayList<Set_Get_Data_EditProfile> listData = new ArrayList<Set_Get_Data_EditProfile>();
		 JSONObject jsonObjMain = new JSONObject();/// main json object eg POI
		 try(Connection conn = ds.getConnection();){
			 
			 String selectData = databaseQ.getUserProfileDocData();
			 String selectDocType = databaseQ.getselectDocType(field); 
			 String doc="";
			 
			 String[] splitId={};
			   try(PreparedStatement ps= conn.prepareStatement(selectDocType);){
			   		ps.setString(1, user_id);
			   		try(ResultSet rs = ps.executeQuery();){
		        	   if(rs.next())
		        	   {
	        		   		   doc=rs.getString("doc");
		        			   splitId=doc.split(",");
		        			   JSONObject inner = new JSONObject();
		        			   for(int i=0;i<splitId.length;i++)
		        			   {
		        				   
		        				  /// inner json object eg voter id
		        				   try(PreparedStatement ps1= conn.prepareStatement(selectData);){
			        				   ps1.setString(1, user_id);
			        				   ps1.setString(2, splitId[i]);
			        				   try(ResultSet rs1 = ps1.executeQuery();){
				        				   if(rs1.next())
				        	        	   {
				        					   JSONObject innervalues = new JSONObject();
				        	            	   innervalues.put("reg_date", rs1.getString("reg_date"));
				        	            	   innervalues.put("reg_authority", rs1.getString("reg_authority"));
				        	            	   innervalues.put("cardNum", rs1.getString("doc_num"));
				        	            	   inner.put(rs1.getString("doc_type"), innervalues); // inserting element  to inner json object
				        	        	   }
			        				   }
		        				   }
		        			   }
			            	   jsonObjMain.put(field.toUpperCase(), inner);// inserting inner json to main json  object
			            	   System.err.println(jsonObjMain.toString()+ "returned json");
		        	   }
			   		}
			   }
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace(); 
		 }
		 
		 
		 return jsonObjMain.toString();
		 
	 }

}
