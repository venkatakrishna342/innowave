/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AppConstants.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 25, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.utility;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.RegistrationEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

/**
 *
 * @author kiran
 */
public class AppConstants {

    //Added by siddu for file upload and services
    public static final String IN_DATA_PATH = "D:/DBTAppBinaries/";
    public static final String UPLOAD_FILE_EXTENSIONS = ".jpg|.png|.pdf";
    public static final String ALL_MANDAT_ENTERED = "ENTERED";
    //JDBC Context

    public static String getContextLookup() {
        return "java:/comp/env/jdbc/DBTPortal";
    }
    
    //---added by Tushar for salt (secure key) and jwt key---//  
    public static final byte[] SALT = {127, 97, 52, -29, -33, 110, 111, -70};
    public static final String JWT_SECRET = "SecretKeySpec@fa775142";
    public final static long TOKEN_TIMEOUT_INMILISECOND = 60000000;
   // public final static long TOKEN_TIMEOUT_INMILISECOND = ConstantConfig.getExperiationTime();
    public final static String TOKEN_ISSUER = "auth0";

    //--- for  validate the JWT---//
    public static String validateJWT(String jwtToken) {
        Long currentTimeInMilisecond = System.currentTimeMillis();
       // RegistrationEntity registrationEntity = new RegistrationEntity();
       AadhaarRegistrationEntity aadhaarRegistrationEntity=new AadhaarRegistrationEntity();
        Claims claims;
        try {

        	  claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(jwtToken).getBody();
        	  aadhaarRegistrationEntity.setUsername(claims.get("userid",String.class));
        	  aadhaarRegistrationEntity.setName(claims.get("username", String.class));
        	  aadhaarRegistrationEntity.setUserType(claims.get("usertype", String.class));
        	  aadhaarRegistrationEntity.setUserRole(claims.get("userrole", String.class));
        	  String newjwtToken = Jwts.builder()
                       .claim("userid", aadhaarRegistrationEntity.getUsername())
                       .claim("username", aadhaarRegistrationEntity.getName())
                       .claim("usertype", aadhaarRegistrationEntity.getUserType())
                       .claim("userrole", aadhaarRegistrationEntity.getUserRole())
                       .setIssuedAt(new Date(currentTimeInMilisecond))
                       .setExpiration(new Date(currentTimeInMilisecond + AppConstants.TOKEN_TIMEOUT_INMILISECOND))
                       .signWith(SignatureAlgorithm.HS512, AppConstants.JWT_SECRET)
                       .compact();
        	
        	   return newjwtToken;
        	
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
            return "Invalid Token" + ex.toString();
        }
    }
    public static String validateJWTInstitute(String jwtToken) {
    	Long currentTimeInMilisecond = System.currentTimeMillis();
    	// RegistrationEntity registrationEntity = new RegistrationEntity();
    	AadhaarRegistrationEntity aadhaarRegistrationEntity=new AadhaarRegistrationEntity();
    	Claims claims;
    	try {
    		
    		claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(jwtToken).getBody();
    		aadhaarRegistrationEntity.setUsername(claims.get("userid",String.class));
    		String newjwtToken = Jwts.builder()
    				.claim("userid", aadhaarRegistrationEntity.getUsername())
    				.setIssuedAt(new Date(currentTimeInMilisecond))
    				.setExpiration(new Date(currentTimeInMilisecond + AppConstants.TOKEN_TIMEOUT_INMILISECOND))
    				.signWith(SignatureAlgorithm.HS512, AppConstants.JWT_SECRET)
    				.compact();
    		
    		return newjwtToken;
    		
    	} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {
    		return "Invalid Token" + ex.toString();
    	}
    }
    public static  HashMap createJWTInstitute(AadhaarRegistrationEntity aadhaarRegistrationEntity,HashMap resultMap) {
    	Long currentTimeInMilisecond = System.currentTimeMillis();
    	// RegistrationEntity registrationEntity = new RegistrationEntity();
    	try {
    		String newjwtToken = Jwts.builder()
    				.claim("userid", aadhaarRegistrationEntity.getUsername())
    				.setIssuedAt(new Date(currentTimeInMilisecond))
    				.setExpiration(new Date(currentTimeInMilisecond + AppConstants.TOKEN_TIMEOUT_INMILISECOND))
    				.signWith(SignatureAlgorithm.HS512, AppConstants.JWT_SECRET)
    				.compact();
    		 resultMap.put("resultCode", 0);
             resultMap.put("resultDetails", newjwtToken);
             resultMap.put("usertype", "institute");
             
    		return resultMap;
    } catch (Exception ex) {
    	
		ex.printStackTrace();
		return resultMap;
	}
    	
    }
}
