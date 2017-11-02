/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : buildNCPIEnv.java
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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.npci.aadhaar.AadhaarStatusRequest;
 
public class buildNCPIEnv extends HttpServlet
{
	private static Logger loggerXml = Logger.getLogger("xmlLogger");

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside--"+request.getParameter("localAadhaarNumber"));
		PrintWriter out = response.getWriter();
		try 
    	{
			String aadharNumber = request.getParameter("localAadhaarNumber");
			String mobileNumber = request.getParameter("localMobileNumber");
			String requestNumber = request.getParameter("localRequestNumber");
			String requestedDateTime = request.getParameter("localRequestedDateTimeStamp");
			String url = request.getParameter("url");
//			String requestedDateTime = "16-09-2014 11:50";		-- format for request

			System.out.println("Request number "+requestNumber);
			
			AadhaarStatusRequest req = new AadhaarStatusRequest();
			req.setAadhaarNumber(aadharNumber);
	    	req.setMobileNumber(mobileNumber);
	    	req.setRequestNumber(requestNumber);
	    	req.setRequestedDateTimeStamp(requestedDateTime);
    	
			String resp = ncpiWSCall(req, url);
			buildHtml(response, resp);
			
		}
    	catch (Exception e) 
    	{
    		
    		buildHtml(response, "Error "+e.getMessage());
    		out.println("---------------Full Stack Trace -----------<br>");
    		out.println(displayErrorForWeb(e));
		}
	}
	
	// Calling WebService ncpi aadhar
    public static String ncpiWSCall(AadhaarStatusRequest req, String url) throws Exception 
    {
    	 String UWResponse_xml = "";
            loggerXml.info("\nCalling NGWebServiceClient\n");
//            String serviceEndPoint = "https://103.14.161.34/Aadhaar/AadhaarQueryService/AadhaarQueryService";
           // String serviceEndPoint = "http://103.14.161.34/Aadhaar/AadhaarQueryService/AadhaarQueryService";
            
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
        
            System.out.println("XML is "+envelope);
            
            loggerXml.info("\nCalled NGWebServiceClient\n");
            loggerXml.info("SOAPResponse_xml::::::::::::::::::::::::::::::::::" + UWResponse_xml);
      
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
