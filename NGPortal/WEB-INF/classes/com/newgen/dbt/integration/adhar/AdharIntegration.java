/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AdharIntegration.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 26, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dbt.integration.adhar;

import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

public class AdharIntegration {
	public static String getFinalXML(String aadharNumber, String appcode, String saAuaValue, String data, String hmac, String skey ,  String pip , String lov, String ci, String ts) throws Exception{
	 	
		String finalXML = "<KUAData xmlns=\"http://kua.maharashtra.gov.in/kyc/gom-kyc-request\">\n"
				+ "<uid>"+aadharNumber+"</uid>\n"
				+ "<appCode>"+appcode+"</appCode>\n"
				+ "<sa>"+saAuaValue+"</sa>\n"
				+ "<saTxn>KUA-kyc-"+ts+"</saTxn>\n"
				+ "<Data type=\"X\">"+data+"</Data>\n"
				+ "<Hmac>"+hmac+"</Hmac>\n"
				+ "<Skey ci=\""+ci+"\">"+skey+"</Skey>\n"
				+ "<Uses otp=\"y\" pin=\"n\"  bio=\"n\" pfa=\"n\" pa=\"n\"  pi=\"n\"/>\n"
				+ "<tokenNumber></tokenNumber>\n"
				+ "<tokenType>001</tokenType>\n"
				+ "<Meta udc=\""+ConfigProperty.getProperty("DEVICEID")+"\" fdc=\"NC\" pip=\""+pip+"\" lot=\"P\" lov=\""+lov+"\" idc=\"NA\"/>\n"
				+ "<rc>Y</rc>\n"
				+ "<lr>Y</lr>\n"
				+ "<mec>Y</mec>\n"
				+ "<ts>"+ts+"</ts>\n"
				+ "</KUAData>";
			
		
		
		return finalXML;
	}
	
	
	
	public static JSONObject generateAdharXml(String uid, String otp_Pin) throws Exception {
		byte[] bEncryptSsnBase64 = null;
		{
            Security.addProvider(new BouncyCastleProvider());
        }
		// Take value from propertiy file
		
		System.out.println("Certificate path : "+ConfigProperty.getProperty("certificatePath"));
		Encrypter utilObj			= new Encrypter(ConfigProperty.getProperty("certificatePath"));
		HashGenerator hashGenerator	= new HashGenerator();
	
		CustomBase64 base64 		= new CustomBase64();
		
		GetIP g 					= new GetIP();
		DateFormat format 			= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String date 				= format.format(new Date());
		String pid                	= "<Pid ts=\""+date+"\" ver=\"1.0\">"
									+ "<Pv otp=\""+otp_Pin+"\"/>"
									+ "</Pid>";
		
		//get skey value
		byte[] bkey					= utilObj.generateSessionKey();
		SecretKeySpec skey			= new SecretKeySpec(bkey, "AES");
		
		
		//get encrypted data
		byte[] bdata				= utilObj.encryptUsingSessionKey(bkey, pid.getBytes());
        byte[] bdataBase64			= base64.encode(bdata);
        
        
        //get HMAC from data
		byte[] bhmac 				= hashGenerator.generateSha256Hash(pid.getBytes());
		byte[] encryptedHmacBytes 	= utilObj.encryptUsingSessionKey(bkey, bhmac);
		byte[] bhmacBase64			= base64.encode(encryptedHmacBytes);
		
		
		//get sKey encrypted, encoded
		byte[] bEncryptSsn 			= utilObj.encryptUsingPublicKey(skey.getEncoded());
		bEncryptSsnBase64			= base64.encode(bEncryptSsn);
		
		
		//get public ip Address	 
   	    String publicIP 			= g.getPublicIP();
   	    
   	    
   	    //get certificate
   	    String ci					= utilObj.getCertExpiryDate();
   	    
   	    
   	    String getXML 		        = getFinalXML(uid, ConfigProperty.getProperty("KYCApp"), ConfigProperty.getProperty("SUB-kUA-CODE"), new String(bdataBase64, "UTF-8"), new String(bhmacBase64, "UTF-8"), new String(bEncryptSsnBase64, "UTF-8") , "124.124.39.3" , ConfigProperty.getProperty("PINCODE"), ci, date);
		System.out.println("your xml "+getXML);
		// send this string to client
   	    
   	    JSONObject  obj             =     ClientgetXml.sendXml(getXML);
   	    
   	    
   	    return obj;
	}
}
