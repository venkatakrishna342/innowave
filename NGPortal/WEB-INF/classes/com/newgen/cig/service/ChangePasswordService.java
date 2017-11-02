/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ChangePasswordService.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 27, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.CommonEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;

public class ChangePasswordService {
	
	 InitialContext Context;
	 DataSource ds;
	 DatabaseQuery databaseQ = new DatabaseQuery();
	 
	public HashMap changePassword(CommonEntity commonEntity,HashMap resultMap)
	{
		  if (commonEntity == null) {
	            resultMap.put("resultCode", -2);
	            resultMap.put("resultDetails", "Invalid input");
	            return resultMap;
	        } 
	        	try
	        	{
	        		AadhaarRegistrationEntity aadhaarRegistrationEntity = new AadhaarRegistrationEntity();
                    String selectUser = "";
                    byte[] salt;
                    byte[] encryptedPasswordOld;
                    byte[] encryptedPasswordNew;
                    boolean auth;
                    salt = AppConstants.SALT; 
                    encryptedPasswordOld = getEncryptedPassword(commonEntity.getOldpassword(), salt);
                   
	        		
		        	Context = new javax.naming.InitialContext();
	                ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	                try(Connection conn = ds.getConnection();){
		                String validatePass = databaseQ.getUserPasswordCount();
		                String updatePass = databaseQ.getUpdateUserPassword();
		                PreparedStatement ps_select = conn.prepareStatement(validatePass);
		                ps_select.setString(1, commonEntity.getUserId());
		                ps_select.setBytes(2, encryptedPasswordOld);
	                
	                try(ResultSet rs = ps_select.executeQuery();){
		                if(rs.next())
		                {
		                	int rs_count=rs.getInt("passCount");
		                	if(rs_count>0)
		                	{
		                		 encryptedPasswordNew = getEncryptedPassword(commonEntity.getNewpassword(), salt);
		                        PreparedStatement ps = conn.prepareStatement(updatePass);
		                        ps.setBytes(1, encryptedPasswordNew);
		                        ps.setString(2, commonEntity.getUserId());
		                        System.out.println("Password...  "+ps.toString());
		                        int i = ps.executeUpdate();
		                        if(i>0)
		                        {
		                        	resultMap.put("resultCode", 0);
		                        }
		                        else
		                        	resultMap.put("resultCode", -3);
		                	}
		                	else
		                		resultMap.put("resultCode", -2);
	                	}
	               }
              }
		        	
    	}
    	catch(Exception e)
    	{
    		resultMap.put("resultCode", -1);
    		e.printStackTrace();
    		
    	}
	        	
	        
		
		
			return resultMap;
	}
	
	   private byte[] getEncryptedPassword(String password, byte[] salt) {
	        try {
	            // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
	            // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
	            String algorithm = "PBKDF2WithHmacSHA1";
	            // SHA-1 generates 160 bit hashes, so that's what makes sense here
	            int derivedKeyLength = 160;
	            // Pick an iteration count that works for you. The NIST recommends at
	            // least 1,000 iterations:
	            int iterations = 20000;
	            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
	            SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
	            return f.generateSecret(spec).getEncoded();
	        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
	            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }

	   public HashMap setNewPassword(CommonEntity commonEntity, HashMap resultMap) {
			
			if (commonEntity == null) {
	            resultMap.put("resultCode", -2);
	            resultMap.put("resultDetails", "Invalid input");
	        } 
			else {
	        	try {
	                byte[] salt;
	                byte[] encryptedPasswordNew;
	                salt = AppConstants.SALT;
	        		
		        	Context = new javax.naming.InitialContext();
	                ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	               try( Connection conn = ds.getConnection();){
	                String updatePass = databaseQ.getUpdateUserPassword();

	                		
	                		encryptedPasswordNew = getEncryptedPassword(commonEntity.getNewpassword(), salt);
	                        try(PreparedStatement ps = conn.prepareStatement(updatePass);){
	                        ps.setBytes(1, encryptedPasswordNew);
	                        ps.setString(2, commonEntity.getUserId());
	                        	System.out.println("Password...  "+ps.toString());
	                        int i = ps.executeUpdate();
	                        if(i>0) {
	                        	resultMap.put("resultCode", 0);

	                        }
	                        else
	                        	resultMap.put("resultCode", -1);
	                        }
	               }

	        	} catch(Exception e) {
	        		resultMap.put("resultCode", -1);
	        		e.printStackTrace();	
	        	}
	        }
			
			return resultMap;
		}
}
