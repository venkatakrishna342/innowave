/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DocumentRepositoryDetails.java
* Author              : Varun Saddi
* Date written
* (DD/MM/YYYY)        : April 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.activityclass;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition.FormDataContentDispositionBuilder;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import JSON.JSONObject;

import com.google.gson.JsonObject;
import com.newgen.cig.entity.DocumentEntity;
import com.newgen.daoImpl.DB_GetUserData;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;
import com.newgen.utility.InitConfigProp;
import com.newgen.utility.SaveMultipartUtility;
/*import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;*/


/**
 * @author Varun Saddi
 *
 */
public class DocumentRepositoryDetails {
	
	DatabaseQuery databaseQ = new DatabaseQuery();
	InitialContext Context;
	DataSource ds;
	
	public ArrayList<DocumentEntity> getDocumentRepositoryDocuments(String userName) {
		
		ArrayList<DocumentEntity> documentList = null;
		try {
			DB_GetUserData dao = new DB_GetUserData();
			documentList = dao.getUserDocuments(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentList;
	}
	
	public ArrayList<DocumentEntity> get_DocumentRepository_MasterDocTypeList() {

		ArrayList<DocumentEntity> documentTypelist = new ArrayList<DocumentEntity>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(databaseQ.getRepositoryDocTypeMasterList());){
				try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
		
						DocumentEntity docEnt = new DocumentEntity();
						docEnt.setDocId(rs.getInt("doc_id"));
						docEnt.setType(rs.getString("doc_type"));
						docEnt.setDocName(rs.getString("doc_name"));
						documentTypelist.add(docEnt);
					}
				}
			} catch (Exception sec) {
				sec.printStackTrace();
			}
		} catch (Exception sec) {
			sec.printStackTrace();
		}
		
		return documentTypelist;
	}
	
	public JsonObject addDocumentTo_UserDocumentRepository(String userId, JSONObject docDetails, FormDataMultiPart formParams) {
	
		String docType = "";
		JsonObject resultObj = new JsonObject();
		
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			//try{userId = docDetails.getString("newDocumentRepositorySelection");}catch(Exception e){docType = "";}
			//Get Doc Type
			try{docType = docDetails.getString("newDocumentRepositorySelection");}catch(Exception e){docType = "";}

			//Save Doc Type Details
        	FormDataBodyPart field = null;
			InputStream uploadedInputStream = null;
			FormDataContentDisposition fileDetial = null;
			String filePath="";
			
			field = formParams.getField("documentRepCert");        //get the file details with key type values
			uploadedInputStream = field.getEntityAs(InputStream.class);
			fileDetial = field.getFormDataContentDisposition();
        	
			String fileName = fileDetial.getFileName();
        	String[] fileType = fileName.split("\\.");
        	String customFileName = docType.replaceAll(" ","")+"."+fileType[1];
        	
        	File docDirectory = new File(InitConfigProp.getProperty("documentpath") + File.separatorChar + "Repository" + File.separatorChar + userId + File.separatorChar + "Documents");
        	String fileLocation = ""; 
        	fileLocation = docDirectory + File.separator + customFileName;
        	
        	System.out.println("Location :: " + fileLocation+ " file Name :"+fileName);
        	SaveMultipartUtility smu = new SaveMultipartUtility();
        	
        	filePath = smu.saveOnDisk(uploadedInputStream, customFileName, userId, docDirectory, fileLocation);			    							
			
        	//Insert Doc Type info in doc_repository_dtl table
        	
        	if(fileLocation.contains("../../..")){
        		fileLocation = fileLocation.replaceAll("../../..","/usr");
			}else if(fileLocation.contains("\\")){
				fileLocation = fileLocation.replaceAll("\\\\","/");
			}
        	
        	System.out.println("fileLocation ---"+fileLocation);
        	System.out.println("docType ---"+docType);
        	
        	JSONObject value = null;
        	int updateCount = -1;
        	try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(databaseQ.getAddUserRepositoryDocDetails());){
        		ps.setString(1, fileLocation);
        		ps.setString(2, docType);
        		ps.setString(3, userId);
				updateCount = ps.executeUpdate();
				if(updateCount > 0) {	
					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message", "Succesfully Added document to repository.");
					return  resultObj;
				}
				else {
					resultObj.addProperty("ResulCode", -1);
					resultObj.addProperty("Message", "Not able to add document to repository ! Try after some time .");
					return  resultObj;
				}
			}
			
		} catch (Exception sec) {
			sec.printStackTrace();
			resultObj.addProperty("ResulCode", -2);
			resultObj.addProperty("Message", "Some exception came while adding new document.");
			return  resultObj;
		}
	}

}
