/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ClientgetXml.java
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
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.newgen.npci.NpciClient;
/*import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;*/

public class ClientgetXml {

	 @SuppressWarnings("unchecked")
	public static JSONObject sendXml(String value){	
		 String output = null , getRes  = null, resp=null;
		// String finalData = null;
		    JSONObject finalData = new JSONObject();
	    	try {
	    		// load property file
	    		
	    		 try {
                     
	    			 // configure the SSLContext with a TrustManager
	    		        SSLContext ctx = SSLContext.getInstance("TLS");
	    		        ctx.init(new KeyManager[0], new TrustManager[]{new ClientgetXml.DefaultTrustManager()}, new SecureRandom());
	    		        SSLContext.setDefault(ctx);
	    			 
	    			 
	    		      //URL url = new URL("https://kuaqa.maharashtra.gov.in/KUA/rest/kyc");
	    		        URL url = new URL(ConfigProperty.getProperty("GetDataUrl"));
	    				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    				conn.setDoOutput(true);
	    				conn.setRequestMethod("POST");
	    				conn.setRequestProperty("Content-Type", "application/xml");

	    				String input  = value;

	    				OutputStream os = conn.getOutputStream();
	    				os.write(input.getBytes());
	    				os.flush();

	    				BufferedReader br = new BufferedReader(new InputStreamReader(
	    						(conn.getInputStream())));

	    				System.out.println("Output from Server .... \n");
	    				while ((output = br.readLine()) != null) {
	    					System.out.println("output Xml details of user "+output);
	    					
	    					int startPosition = output.indexOf("ret=") + "ret=".length();
		    				int endPosition = output.indexOf(">", startPosition);
		    				String subS = output.substring(startPosition, endPosition);
	    					System.out.println(subS);
	    					
		    			if(subS.equalsIgnoreCase("\"Y\"")){
	    				System.out.println("otp validation pass");
	    				// get valuable data

	    				
	    				// Parse XML
	    				String UID      = "";
	    				String Mobile   = "";
	    				String Sex      = "";
	    				String DOB      = "";
	    				String name     = "";
	    				String Email    = "";
	    				String	PO      = "";
	    				String  PIN     = "";
	    				String  state   = "";
	    				String  District = "";
	    				String  SubDist  = "";
	    				String  VTC      = "";
	    				String   LM      = "";
	    				String  Father   = "";
	    				String  co   = "";
	    				String Image     = "";
	    				int    Age       =0 ;
	    				String House     = "";
	    				String street    = "";
	    				String npciStatus="N";
	    				String bankName="";
	    				
	    				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	    				DocumentBuilder b = f.newDocumentBuilder();
	    				Document doc = b.parse(new ByteArrayInputStream(output.getBytes("UTF-8")));
	    				// Find Aadhar Number
	    				NodeList uid = doc.getElementsByTagName("UidData");
	    				Node uidNumber = uid.item(0);
	    				System.out.println("\nCurrent Element :" + uidNumber.getNodeName());
                       if (uidNumber.getNodeType() == Node.ELEMENT_NODE) 
                       {
                           Element eElement = (Element) uidNumber;
                           UID = eElement.getAttribute("uid");
                           System.out.println("uid : " + eElement.getAttribute("uid"));
	    				}
	    				
                       // find POI Data
                       	NodeList Poi = doc.getElementsByTagName("Poi");
	    				Node PoiData = Poi.item(0);
	    				System.out.println("\nCurrent Element :" + PoiData.getNodeName());
	    				if (PoiData.getNodeType() == Node.ELEMENT_NODE)
	    				{
	    					Element eElement = (Element) PoiData;
	    					Mobile = eElement.getAttribute("phone");
	    					Sex    = eElement.getAttribute("gender");
	    					DOB    = eElement.getAttribute("dob");
	    					name   = eElement.getAttribute("name");
	    					Email  = eElement.getAttribute("email");
                          
	    					// get Age
  	    					String[] dob = DOB.replaceAll("\"", " ").trim().split("-");
  	    					String age = dob[2];
  	    					System.out.println(age);
  	    					Calendar now = Calendar.getInstance();   
  	    					int year = now.get(Calendar.YEAR);        
  	    					Age = year - Integer.parseInt(age);
  	    		        
	                        System.out.println("phone : " + eElement.getAttribute("phone"));
	                        System.out.println("gender : " + eElement.getAttribute("gender"));
	                        System.out.println("dob : " + eElement.getAttribute("dob"));
	                        System.out.println("name : " + eElement.getAttribute("name"));
	                        System.out.println("email : " + eElement.getAttribute("email"));
	                        System.out.println("Age "+Age);
	    				}
	    				
                   // find Poa Data
	    				NodeList Poa = doc.getElementsByTagName("Poa");
	    				Node PoaData = Poa.item(0);
	    				System.out.println("\nCurrent Element :" + PoaData.getNodeName());
	    				if (PoaData.getNodeType() == Node.ELEMENT_NODE) 
	    				{
	    					Element eElement = (Element) PoaData;
	    					PO         = eElement.getAttribute("po");
	    					PIN        = eElement.getAttribute("pc");
	    					state      = eElement.getAttribute("state");
	    					District   = eElement.getAttribute("dist");
	    					SubDist    = eElement.getAttribute("subdist");
	    					VTC        = eElement.getAttribute("vtc");
	    					LM         = eElement.getAttribute("lm");
	    					Father     = eElement.getAttribute("co");
	    					House      = eElement.getAttribute("house");
	    					street     = eElement.getAttribute("street");
                         
	    					System.out.println("po : " + eElement.getAttribute("po"));
	    					System.out.println("pc : " + eElement.getAttribute("pc"));
	    					System.out.println("state : " + eElement.getAttribute("state"));
	    					System.out.println("dist : " + eElement.getAttribute("dist"));
	    					System.out.println("subdist : " + eElement.getAttribute("subdist"));
	    					System.out.println("vtc : " + eElement.getAttribute("vtc"));
	    					System.out.println("lm : " + eElement.getAttribute("lm"));
	    					System.out.println("co : " + eElement.getAttribute("co"));
	    					System.out.println("father : " + eElement.getAttribute("father"));
	    					System.out.println("street : " + eElement.getAttribute("street"));
                         	System.out.println("House : " + eElement.getAttribute("House"));
                          
	    				}
	    				
                        // Photo
                        Image =   doc.getElementsByTagName("Pht").item(0).getTextContent();
                        System.out.println("Image : " + Image);
	    				// end xml parser
	    				try
	    				{
	                        resp= NpciClient.npciRes(UID, Mobile, "ALLA123456", "2017-05-21 13:38:50.01");
	                        System.out.println("response for npci in clientxml "+resp);
	                        if(("ConnectError").equalsIgnoreCase(resp)){
	                        	finalData.put("type", "NpciError");
	                        	return finalData;
	                        }
	                        if(("SocketError").equalsIgnoreCase(resp)){
	                        	finalData.put("type", "NpciError");
	                        	return finalData;
	                        }
	                  		DocumentBuilderFactory npci = DocumentBuilderFactory.newInstance();
	                  		DocumentBuilder bnpci = npci.newDocumentBuilder();
	                  		Document npcidoc = bnpci.parse(new ByteArrayInputStream(resp.getBytes("UTF-8")));
	                  		npciStatus = npcidoc.getElementsByTagName("status").item(0).getTextContent();
	                  		bankName = npcidoc.getElementsByTagName("bankName").item(0).getTextContent();
	                  		finalData.put("NpciStatus", npciStatus);
		    				finalData.put("bankName", bankName);
	    				}
	    				catch(Exception e)
	    				{
	    					e.printStackTrace();
	    				}
                  		
        			
	    				finalData.put("type", "success");
	    				finalData.put("name", name.replaceAll("\"", " ").trim());
	    				finalData.put("mobile", Mobile.replaceAll("\"", " ").trim());
	    				finalData.put("Sex", Sex.replaceAll("\"", " ").trim());
	    				finalData.put("DOB", DOB.replaceAll("\"", " ").trim());
	    				finalData.put("pin", PIN.replaceAll("\"", " ").trim());
	    				finalData.put("state", state.replaceAll("\"", " ").trim());
	    				finalData.put("street", street.replaceAll("\"", " ").trim());
	    				finalData.put("PO", PO.replaceAll("\"", " ").trim());
	    				finalData.put("District", District.replaceAll("\"", " ").trim());
	    				finalData.put("SubDist", SubDist.replaceAll("\"", " ").trim());
	    				finalData.put("VTC", VTC.replaceAll("\"", " ").trim());
	    				finalData.put("LM", LM.replaceAll("\"", " ").trim());
	    				finalData.put("House", House.replaceAll("\"", " ").trim());
	    				finalData.put("Image", Image);
	    				finalData.put("Email", Email.replaceAll("\"", " ").trim());
	    				finalData.put("Father", Father.replaceAll("\"", " ").trim());
	    				finalData.put("uid", UID.replaceAll("\"", " ").trim());
	    				finalData.put("Age", Age);
	    				
	    				
	    				//System.out.println("final data pass "+finalData);	
	    				}
	    				else if(subS.equalsIgnoreCase("\"N\"")){
	    				
	    					
	    					/*int startPosition1 = output.indexOf("err=") + "err=".length();
		    				int endPosition1 = output.indexOf("txn=", startPosition1);
		    				String subS1 = output.substring(startPosition1, endPosition1);
		    				System.out.println("otp validation fail"+subS1.trim());
		    				// error code
		    				String val = ConfigProperty.getProperty(subS1);
		    				System.out.println(val);*/
	    					finalData.put("type", "failure");
		    				finalData.put("value", "validation fail");
		    				System.out.println("final data fail "+finalData);
	    				}
	    				else{
	    					finalData.put("type", "failure");
		    				finalData.put("value", "validation fail");
	    					System.out.println("validation failure");
	    				}
		    			
	    				
	    				}
	    				
	    				
                       conn.disconnect();

	    			  } catch (MalformedURLException e) {

	    				e.printStackTrace();

	    			  } catch (IOException e) {

	    				e.printStackTrace();
	    				finalData.put("type", "error");

	    			 }
	    		
	    		
		             } catch (Exception e) {
		        	e.printStackTrace();
		         }
		    
		    return finalData;
		
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
