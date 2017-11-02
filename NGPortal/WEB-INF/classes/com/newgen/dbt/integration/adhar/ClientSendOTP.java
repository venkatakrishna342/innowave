/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ClientSendOTP.java
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.security.SecureRandom;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
public class ClientSendOTP {
  
	
	  @SuppressWarnings("unchecked")
	public static JSONObject sendOTP(String uid){	
		    String output = null , getRes  = null;
		    JSONObject obj = new JSONObject();
	    	try {
	    		// load property file
	    		
	    		 try {

	    			 // configure the SSLContext with a TrustManager
	    		        SSLContext ctx = SSLContext.getInstance("TLS");
	    		        ctx.init(new KeyManager[0], new TrustManager[]{new ClientSendOTP.DefaultTrustManager()}, new SecureRandom());
	    		        SSLContext.setDefault(ctx);
	    			 
	    				//URL url = new URL("https://auaqa.maharashtra.gov.in/aua/rest/authreqv1");
	    		        URL url = new URL(ConfigProperty.getProperty("SendOtpUrl"));
	    				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    				conn.setDoOutput(true);
	    				conn.setRequestMethod("POST");
	    				conn.setRequestProperty("Content-Type", "application/xml");

	    				String input  = "<Auth xmlns= \"http://aua.maharashtra.gov.in/auth/gom-auth-request\">\n"
	    				  		+ "<Txn>AUA-kyc-OTP-2017-06-15T13:09:34"
	    				  		+ "</Txn>\n<Ver>1.1</Ver>"
	    				  		+ " <SubAUACode>"+ConfigProperty.getProperty("SUB-AUA-CODE")+"</SubAUACode>\n"
	    				  		+ " <ReqType>otp</ReqType>\n"
	    				  		+ " <DeviceId>"+ConfigProperty.getProperty("DEVICEIDforOtp")+"</DeviceId>\n"
	    				  		+ " <UID>"+uid+"</UID>\n"
	    				  		+ " <Ch>01</Ch>\n"
	    				  		+ " </Auth>";
	    				OutputStream os = conn.getOutputStream();
	    				os.write(input.getBytes());
	    				os.flush();
	    				System.out.println("waiting for response .... \n");

	    				/*if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
	    					throw new RuntimeException("Failed : HTTP error code : "
	    						+ conn.getResponseCode());
	    				}*/

	    				BufferedReader br = new BufferedReader(new InputStreamReader(
	    						(conn.getInputStream())));

	    				System.out.println("Output from Server .... \n");
	    				while ((output = br.readLine()) != null) {
	    					System.out.println(output);
	    					//System.out.println(output.contains("<OtpRet>"));
	    					if(output.contains(">1007<"))
	    					{System.out.println("response code found");
	    						throw new Exception();
	    					}
	    					
	    					
	    					int startPosition = output.indexOf("<OtpRet>") + "<OtpRet>".length();
		    				int endPosition = output.indexOf("</OtpRet>", startPosition);
		    				String subS = output.substring(startPosition, endPosition);
	    					System.out.println(subS);
		    				if(subS.equalsIgnoreCase("Y")){
		    					
		    					getRes = "OTP SEND TO REGISTERED MOBILE NUMBER"; 
		    					obj.put("Value", "success");
		    				}
		    				else if(subS.equalsIgnoreCase("n")){
		    				
		    					int startPosition1 = output.indexOf("<OtpErrorCode>") + "<OtpErrorCode>".length();
			    				int endPosition1 = output.indexOf("</OtpErrorCode>", startPosition1);
			    				String errorCode = output.substring(startPosition1, endPosition1);
		    					System.out.println("error code "+errorCode);
		    					String errorType = "";
		    					if(errorCode.equals("998")){
		    						errorType= "You have entered wrong Aadhaar number"	;
		    					}
		    					else  if(errorCode.equals("111")){
		    						errorType= "Aadhaar number does not have verified mobile"	;
		    					}
								else  if(errorCode.equals("110")){
									errorType= "Aadhaar number does not have verified mobile/email"	;	    					
									}
								else  if(errorCode.equals("112")){
									
									errorType= "Aadhaar number does not have both email and mobile"	;
									}
                               else  if(errorCode.equals("950")){
									
									errorType= "Could not generate and/or send OTP"	;
									}
                               else  if(errorCode.equals("566")){
									
									errorType= "Error ! license  has expired or is invalid"	;
									}
                               else  if(errorCode.equals("940")){
                            	   
                            	   errorType= "Error ! External Server Technical Issue"	;
                               }
								else{
									errorType= "Error during sending OTP ! Please try again";
								}
		    					//getRes                   = ConfigProperty.getProperty(errorCode);
		    					//System.out.println(": "+getRes);
		    					obj.put("Value", errorType);
		    					
		    				}
		    				
	    				}
	    				
	    				

	    				conn.disconnect();

	    			  } catch (MalformedURLException e) {

	    				e.printStackTrace();

	    			  } catch (IOException e) {

	    				e.printStackTrace();

	    			 }
	    		
	    		
		             } catch (Exception e) {
		            	 obj.put("Value", "Not able to get response from UIDAI!");
		         }
				 
			    
			
			return obj;

				
			}
	  
	  private static class DefaultTrustManager implements X509TrustManager {

	        @Override
	        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	        }

	        @Override
	        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
	        }

	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }
	    }
	  
}




	  