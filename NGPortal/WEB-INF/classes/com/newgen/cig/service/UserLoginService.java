/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Login Module 
* File Name           : Set_Get_Data_PostLogin.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Jan 21, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.RegistrationEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;
import com.newgen.utility.MYSql_DB_Connection;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserLoginService {


    InitialContext Context;
    DataSource ds;
    DatabaseQuery databaseQ = new DatabaseQuery();
    @SuppressWarnings("unchecked")
	public HashMap userLoginService(AadhaarRegistrationEntity registrationEntity_i, HashMap resultMap,HttpServletResponse response) {
       System.out.println("Inside User Login Service");
    	if (registrationEntity_i == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid input");
        } else {
            String resultVal = validateLogin(registrationEntity_i);
            if (!AppConstants.ALL_MANDAT_ENTERED.equals(resultVal)) {
                resultMap.put("resultCode", -3);
                resultMap.put("resultDetails", resultVal);
            } else {
                try {
                    AadhaarRegistrationEntity aadhaarRegistrationEntity = new AadhaarRegistrationEntity();
                    ArrayList<AadhaarRegistrationEntity> responseEntity=new ArrayList<AadhaarRegistrationEntity>();
                    String selectUser = "";
                    byte[] salt;
                    byte[] encryptedPassword;
                    boolean auth;
                    String token = null;
                    Long currentTimeInMilisecond = System.currentTimeMillis();
                    Context = new javax.naming.InitialContext();
                    ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
                    
                 if("A".equalsIgnoreCase(registrationEntity_i.getUserType()))
                        selectUser =databaseQ.selectForApplicant();
                 else{
                	 	selectUser =databaseQ.selectForInst();
                 }
                	
                    try (Connection conn = ds.getConnection();) {
                            try(PreparedStatement ps = conn.prepareStatement(selectUser);){
		                    	System.out.println("After creating conneciton in  Login Service");
		                        String username = registrationEntity_i.getUsername();
		                        String attemptedPassword = registrationEntity_i.getPassword();
		                        int argIndex = 1;
		                        if (username != null || !"".equals(username)) {
		                        	ps.setString(argIndex++, username.toLowerCase());
		                        	System.out.println(ps.toString());
		                        }
		                        try (ResultSet rs = ps.executeQuery();) {
		                        	if (rs.next()) {
	                                encryptedPassword = (byte[]) (rs.getBytes("user_password"));
	                                salt = AppConstants.SALT;
	                                if (rs.getString(1) == null) {
	                                	resultMap.put("resultCode", -3);
	                                	resultMap.put("resultDetails", "Incorrect username or password");
	                                }
	                                System.out.println("decrypting password");
	                                auth = authenticate(attemptedPassword, encryptedPassword, salt);
	                                System.out.println("decrypted successfully");
	                                if (auth != true) {
	                                	resultMap.put("resultCode", -3);
	                                	resultMap.put("resultDetails", "Incorrect username or password");
	                                } 
	                                else {
	                                		aadhaarRegistrationEntity.setUsername(rs.getString("user_id"));
	                                		if("A".equalsIgnoreCase(registrationEntity_i.getUserType())){
	                                			aadhaarRegistrationEntity.setName(rs.getString("full_name"));
	                                			aadhaarRegistrationEntity.setPhoto(rs.getString("profile_pic"));
	                                			aadhaarRegistrationEntity.setProfileType(rs.getString("profile_type"));
		                                		aadhaarRegistrationEntity.setNpciStatus(rs.getString("npci_status"));
	                                		}
	                                		
	                                		aadhaarRegistrationEntity.setUserType(rs.getString("user_type"));
	                                		aadhaarRegistrationEntity.setUserIndex(rs.getInt("user_index"));
	                                		if("I".equalsIgnoreCase(registrationEntity_i.getUserType())){
		                                		aadhaarRegistrationEntity.setRoleId(rs.getInt("role_id"));
		                                		aadhaarRegistrationEntity.setDistrictId(rs.getInt("district_id"));
		                                		aadhaarRegistrationEntity.setTalukaId(rs.getInt("taluka_id"));
		                                		aadhaarRegistrationEntity.setInstituteId(rs.getInt("institute_id"));
		                                		
		                                		String userRoleName = "select role_type from dbt_institute_user_role where role_id = ?";
		                                		try(PreparedStatement ps_userRoleName = conn.prepareStatement(userRoleName);){
		                                			ps_userRoleName.setInt(1, aadhaarRegistrationEntity.getRoleId());
		                                			try(ResultSet rs_userRoleName = ps_userRoleName.executeQuery()){
		                                				if(rs_userRoleName.next()){
		                                					aadhaarRegistrationEntity.setUserRole(rs_userRoleName.getString("role_type"));
		                                					
		                                				}
		                                			}
		                                			
		                                		}
	                                		}
	                                		responseEntity.add(aadhaarRegistrationEntity);
	                                		System.out.println("calling inserting method in connection and history tables table");
	                                		
	                                		boolean flag=insertConnectionDetails(conn,registrationEntity_i);
	                                		
	                                		System.out.println("insertion in history and conneciton table :"+flag);
	                                		GetResidentDataCountService  residentCount = new GetResidentDataCountService();
	                                		HashMap<String, HashMap> sendCount = new HashMap<String, HashMap>();
	                                		if("I".equalsIgnoreCase(registrationEntity_i.getUserType())){//count for inst all data
	                                			//sendCount = residentCount.getResidentDataCountService(conn,aadhaarRegistrationEntity);
		                                		//resultMap.put("ResidentCount", sendCount);
	                                		}
	                                		else{
		                                		sendCount = residentCount.getResidentDataCountService(conn,aadhaarRegistrationEntity);
		                                		resultMap.put("ResidentCount", sendCount);
	                                		}
	                                		
	                                		if(flag)
		                                	{
	                                			if("I".equalsIgnoreCase(registrationEntity_i.getUserType())){
	                                				token = Jwts.builder()
	    		                                            .claim("userid", aadhaarRegistrationEntity.getUsername())
	    		                                            .claim("userrole", aadhaarRegistrationEntity.getUserRole())
	    		                                            .claim("usertype", aadhaarRegistrationEntity.getUserType())
	    		                                            .setIssuedAt(new Date(currentTimeInMilisecond))
	    		                                            .setExpiration(new Date(currentTimeInMilisecond + AppConstants.TOKEN_TIMEOUT_INMILISECOND))
	    		                                            .signWith(SignatureAlgorithm.HS512, AppConstants.JWT_SECRET)
	    		                                            .compact();
	                                			}
	                                			else{
			                                		token = Jwts.builder()
		                                            .claim("userid", aadhaarRegistrationEntity.getUsername())
		                                            .claim("username", aadhaarRegistrationEntity.getName())
		                                            .claim("usertype", aadhaarRegistrationEntity.getUserType())
		                                            .setIssuedAt(new Date(currentTimeInMilisecond))
		                                            .setExpiration(new Date(currentTimeInMilisecond + AppConstants.TOKEN_TIMEOUT_INMILISECOND))
		                                            .signWith(SignatureAlgorithm.HS512, AppConstants.JWT_SECRET)
		                                            .compact();
		                                		
	                                			}
		                                		
		                                		resultMap.put("resultCode", 0);
		                                		response.setHeader("token", token);
		                                		resultMap.put("resultDetails", responseEntity);
		                                	}
		                                	else
		                                	{
		                                		resultMap.put("resultCode", -3);
		                                		resultMap.put("resultDetails", "Internal server data insertion problem");
		                                	}
	                                	}
		                        	}
                            else{
                            	 resultMap.put("resultCode", -3);
                                 resultMap.put("resultDetails", "Incorrect username or password");
                            }
                        }
                      }
                    }
                } catch (NamingException | SQLException ex) {
                    resultMap.put("resultCode", -1);
                    resultMap.put("resultDetails", "UserLoginService DB Exception: " + ex.toString());
                }
            }
        }
    	System.err.println("returning result in user login service");
        return resultMap;
    }

    /**
     * @param registrationEntity_i
     */
    private String validateLogin(AadhaarRegistrationEntity registrationEntity_i) {
        String message = AppConstants.ALL_MANDAT_ENTERED;
//        if (registrationEntity_i.getUsertype() == null || "".equals(registrationEntity_i.getUsertype())) {
//            message = "Please Select User Type";
//        } else 
        if (registrationEntity_i.getUsername() == null || "".equals(registrationEntity_i.getUsername())) {
            message = "Please Enter Login UserName";
        } else if (registrationEntity_i.getPassword() == null || "".equals(registrationEntity_i.getPassword())) {
            message = "Please Enter Login Password";
        }
        return message;
    }

    /**
     * @param password
     * @param salt
     * @return
     */
    //--- for password encryption---//
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

    /**
     * @param attemptedPassword
     * @param encryptedPassword
     * @param salt
     * @return
     */
    //---for authentication---//
    private boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) {
    	
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        System.out.println(attemptedPassword+" ^^^^^^^^  "+ encryptedAttemptedPassword);
        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        System.out.println(Arrays.equals(encryptedPassword, encryptedAttemptedPassword));
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }
    
    
  public boolean insertConnectionDetails(Connection conn,AadhaarRegistrationEntity registrationEntity_i) {
	  boolean flag=false;
//	  PreparedStatement pstmt=null;
//	  PreparedStatement psDelete=null;
//	  PreparedStatement psinsert=null;
//	  PreparedStatement psinserthistory=null;
//	  ResultSet rs=null;
	  String countUserCurrent="";
	  String insertUserDtl="";
  	  String updateUserDtl="";
  	  String updateUserHistryDtl="";
  	  String insertHistryDtl="";
  	  String deleteUserCurrent="";
  	  byte[] salt = AppConstants.SALT; 
  	  byte[] encryptedpassword;
	  try
    	{
		  	conn.setAutoCommit(false);
//******************insertion and updation to check current connection in userconnection table
		  	countUserCurrent=databaseQ.getCurrentUserConnectionDtl(); //"select user_id from dbt_userconnection_dtl where user_id=?";
		  	deleteUserCurrent=databaseQ.deleteCurrentUserConnectionDtl();//"delete from dbt_userconnection_dtl where user_id=? ";
		  	insertUserDtl=databaseQ.insertUserConnectionDtl();//"insert into dbt_userconnection_dtl(user_id,user_type,user_password,client_ip,login_datetime) values(?,?,?,?,current_timestamp())";
		  	updateUserDtl=databaseQ.updateUserConnectionDtl();//"update dbt_userconnection_dtl set login_datetime=current_timestamp() where user_id=?";
		  //	updateUserHistryDtl="update dbt_connection_history_dtl set login_datetime=current_timestamp() where user_id=?";
		  	insertHistryDtl=databaseQ.insertHistroyDtl();//"insert into dbt_connection_history_dtl(user_id,client_ip,login_datetime) values(?,?,current_timestamp())";
		  	String pass=registrationEntity_i.getPassword();
		  	encryptedpassword = getEncryptedPassword(pass, salt);
		  	try(PreparedStatement pstmt = conn.prepareStatement(countUserCurrent);){//find if user's entry already exist
      		pstmt.setString(1, registrationEntity_i.getUsername());
      		try(ResultSet rs = pstmt.executeQuery();){
      			if(rs.next())
	      		{
	      			try(PreparedStatement psDelete = conn.prepareStatement(deleteUserCurrent);){//if yes then delete the entry
	      			psDelete.setString(1,registrationEntity_i.getUsername());
	      			int countInsert = psDelete.executeUpdate();
		      			if(countInsert == 0)
		      			{
		      				flag = false;
		      				 return flag;
		      			}
	      			}
	      			
      				try(PreparedStatement psinsert = conn.prepareStatement(insertUserDtl);){//if entry deleted then  insert into connection table
	          			psinsert.setString(1,registrationEntity_i.getUsername());
	          			psinsert.setString(2,registrationEntity_i.getUserType());
	          			psinsert.setBytes(3,encryptedpassword);
	          			psinsert.setString(4,registrationEntity_i.getIpAdderess());
	          			int countInsert1 = psinsert.executeUpdate();
	      				if(countInsert1 == 0)
	      				{
	      					flag = false;
	      					 return flag;
	      				}
      				}
  					try(PreparedStatement psinserthistory = conn.prepareStatement(insertHistryDtl);){//if values are inserted successfully then enter data to history table
	  					psinserthistory.setString(1,registrationEntity_i.getUsername());
	  					psinserthistory.setString(2,registrationEntity_i.getIpAdderess());
	          			int countInsert2 = psinserthistory.executeUpdate();
	      				if(countInsert2>0)
	      				{
	      					flag=true;
	      					conn.commit();
	      				}
	      				else{
	      					flag=true;
	      					 return flag;
	      				}
  					}
	      		}
      			else
	      		{
	      			//pstmt.close();
	      			//rs.close();
	      			try(PreparedStatement psinsert = conn.prepareStatement(insertUserDtl);){ //insert into connection table
		      			psinsert.setString(1,registrationEntity_i.getUsername());
		      			psinsert.setString(2,registrationEntity_i.getUserType());
		      			psinsert.setBytes(3,encryptedpassword);
		      			psinsert.setString(4,registrationEntity_i.getIpAdderess());
		      			int countInsert = psinsert.executeUpdate();
		      			if(countInsert>0)
		      			{
		      				//psinsert.close();
		      				try(PreparedStatement psinserthistory = conn.prepareStatement(insertHistryDtl);){//insert into history table
		      				psinserthistory.setString(1,registrationEntity_i.getUsername());
		      				psinserthistory.setString(2,registrationEntity_i.getIpAdderess());
		          			int countInsert1 = psinserthistory.executeUpdate();
		      				if(countInsert1>0)
		      				{
		      					flag=true;
		      					conn.commit();
		      				}
		      				else
		      					flag=true;
		      				}
		      			}
		      			else
		      				flag=false;
	      			}
	      		}
      		
      		}
		  	}
    		  
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		flag=false;
    	}
	  finally{
		   countUserCurrent=null;
		  	 insertUserDtl=null;
		  	 updateUserDtl=null;
		  	 updateUserHistryDtl=null;
		  	 insertHistryDtl=null;
		  
	  }
    	
       return flag;
    }
    
 

//    //--- salt (secure random)---//
//    private byte[] generateSalt() {
//        // VERY important to use SecureRandom instead of just Random
//        SecureRandom random = null;
//        try {
//            random = SecureRandom.getInstance("SHA1PRNG");
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
//        byte[] salt = new byte[8];
//        random.nextBytes(salt);
//        return salt;
//        
//    }
    
    
    
    


}
