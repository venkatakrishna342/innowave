/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : CustomChangePassUtility.java
* Author              : Ankit Katoch
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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.cig.service.RegistrationService;

public class CustomChangePassUtility {
	InitialContext Context;
	 DataSource ds;
	public static void main(String[] args) {
		
		CustomChangePassUtility chngpwd=new CustomChangePassUtility();
			String flag=chngpwd.changePassword("gvs_2137","system123#");//changePassword(userId,Password)
			System.out.println("final flag :::::: "+flag);
		}
	
	public String changePassword(String user_id,String password)
	{
		 byte[] salt;
         byte[] encryptedPasswordNew;
         salt = AppConstants.SALT; 
         String returnFlag="Password not changed";
         System.out.println("user password:::::::::New "+password);
         try{
        	 Connection conn= null;
        	 
        	 Class.forName("com.mysql.jdbc.Driver");
        	 //conn = DriverManager.getConnection( "jdbc:mysql://192.168.42.151:3306/dbtportal" , "root", "scott" );
        	 conn = DriverManager.getConnection( "jdbc:mysql://10.10.46.165:3306/dbtportal" , "root", "Wh0@m!=reign" );
        	 
	         /*Context = new javax.naming.InitialContext();
	         ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	         Connection conn = ds.getConnection();*/
	         String updatePass="update dbt_usercredential_dtl set user_password=? where user_id=? ";
	         
	         encryptedPasswordNew = getEncryptedPassword(password, salt);
	         PreparedStatement ps = conn.prepareStatement(updatePass);
             ps.setBytes(1, encryptedPasswordNew);
             ps.setString(2, user_id);
             System.out.println("Password...  "+ps.toString());
             int i = ps.executeUpdate();
             if(i>0)
             {
             	System.out.println("Psssword has been changed");
             	returnFlag="Psssword has been changed";
             }
             else{
             	System.out.println("Password not changed");
             	returnFlag="Password not changed";
             }
         }
         catch(Exception e)
         {
        	 e.printStackTrace();
         }
		
		return returnFlag;
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

}
