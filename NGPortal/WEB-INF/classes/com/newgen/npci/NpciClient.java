/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : NpciClient.java
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
package com.newgen.npci;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.npci.aadhaar.AadhaarStatusRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NpciClient {

	
	
public static void main(String[] args) {
	
	
String response =	NpciClient.npciRes("920535325769", "", "ALLA123456", "2017-05-21 13:38:50.01");
System.out.println(response);

}
	//731945271914   123456789123  666122203644

	public static  String npciRes(String aadharNumber, String mobileNumber, String requestNumber, String requestedDateTime){
		
		String resp = null;
		String status="N";
		Date date=new Date();
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyy-mm-dd HH:mm:ss.SS");
		requestedDateTime=sdfDate.format(date);
		try 
    	{
			String url = "https://nach.npci.org.in/CMAadhaar/AadhaarStatusService";
			AadhaarStatusRequest req = new AadhaarStatusRequest();
			req.setAadhaarNumber(aadharNumber);
	    	req.setMobileNumber(mobileNumber);
	    	req.setRequestNumber(requestNumber);
	    	req.setRequestedDateTimeStamp(requestedDateTime);
    	
			resp = ncpiWSCall(req, url);
			System.out.println("Response :: \n"+resp);
			//buildHtml(response, resp);
			
		
			
		}
		catch (Exception e) 
    	{e.printStackTrace();
    	
    	}
		
		return resp;
	}
	
	
	// Calling WebService ncpi aadhar
    public static String ncpiWSCall(AadhaarStatusRequest req, String url) throws Exception 
    {
    	 String UWResponse_xml = "";
            
            String serviceEndPoint = "https://nach.npci.org.in/CMAadhaar/AadhaarStatusService";
//            String serviceEndPoint = url;
            System.out.println("URL-----"+serviceEndPoint);
            System.out.println("url come from FORM "+url);
            String envelope = "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:aad='http://aadhaar.npci.org/'>"
                    + "<soapenv:Header/>"
                    + "<soapenv:Body>"
                    + "<aad:getAadhaarStatus>"
                    + "<arg0>"
                    + "<aadhaarNumber>" + req.getAadhaarNumber() + "</aadhaarNumber>"
                    + "<mobileNumber>" + req.getMobileNumber() + "</mobileNumber>"
                    + "<requestNumber>" + req.getRequestNumber() + "</requestNumber>"
                    + "<requestedDateTimeStamp>" + req.getRequestedDateTimeStamp() + "</requestedDateTimeStamp>"
                    + "</arg0>"
                    + "</aad:getAadhaarStatus>"
                    + "</soapenv:Body>"
                    + "</soapenv:Envelope>";
            
            WSCall cf = new WSCall();
            UWResponse_xml = cf.invoke(url, envelope, "null");            
            
            return UWResponse_xml;
    }
	
	
	private void buildHtml(HttpServletResponse response, String resp) throws IOException {
		response.setContentType("text/html");

		  PrintWriter out = response.getWriter();
		  String title = "Response from NCPI";
			
		  String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " +
		     "transitional//en\">\n";
		
		  System.out.println("RESPONSE  "+resp);
		  
		  
		  out.println(docType + "<html>\n" +
		     "<head><title>" + title + "</title></head>\n" +
		     "<body bgcolor=\"#f0f0f0\">\n" +
		     "<h1 align=\"center\">" + title + "</h1>\n" +
		     "<ul>\n" +
		     "  <li><b>Response</b> : "
		     + resp + "\n" +
		     "</ul>\n" +
		     "</body></html>");
	}	
	
	public String displayErrorForWeb(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		String stackTrace = sw.toString();
		return stackTrace.replace(System.getProperty("line.separator"), "<br/>\n");
	}
	
	
	
	
}
