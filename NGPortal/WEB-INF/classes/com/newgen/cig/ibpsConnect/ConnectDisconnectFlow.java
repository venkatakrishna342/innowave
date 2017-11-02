/********************************************************************
 *  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 * Group               : CIG
 * Product / Project   : NRC Assam
 * Module              : CSVBulkUploadMain (-ERC-)
 * File Name           : BaseNRC.java
 * Author              : Awesome Dheeraj in Guwahati
 * Date written
 * (DD/MM/YYYY)        : Sept 29, 2015
 * Description         : Connect to Server
 *
 * CHANGE HISTORY
  ************************************************************************************************************
 * Date				Change By		Change Description (Bug No. (If Any))
 *
 ************************************************************************************************************/
package com.newgen.cig.ibpsConnect;

import java.io.IOException;

/*import com.newgen.dmsapi.DMSCallBroker;
import com.newgen.dmsapi.DMSInputXml;
import com.newgen.dmsapi.DMSXmlResponse;*/
import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.ibpsConnect.ConfProperty;
/*import com.newgen.omni.wf.util.app.NGEjbClient;
import com.newgen.omni.wf.util.excp.NGException;*/


public class ConnectDisconnectFlow {/*
	
	public static NGEjbClient ngEJBClient;
	public static String engineName 	= "";
	String dmsUserName 	= "";
    String dmsUserPswd 	= "";
    public static String dmsUserType    = "";
    public static String serverIP		= "";
    public static String serverPort		= "";
    public static String serverType		= "";
    public static String jtsPort		= "";
	
	public ConnectDisconnectFlow(AadhaarRegistrationEntity dataEntity) throws Exception{
		engineName 		= ConfProperty.getProperty("EngineName");
	    dmsUserName 	= dataEntity.getUsername();
	    dmsUserPswd 	= dataEntity.getPassword();
	    dmsUserType		= ConfProperty.getProperty("DMSUserType");
	    serverIP		= ConfProperty.getProperty("ServerIP");
	    serverPort		= ConfProperty.getProperty("ServerPort");
	    serverType		= ConfProperty.getProperty("ServerType");
	    jtsPort			= ConfProperty.getProperty("JTSPort");
	    
	    
	}
	
	public String connectFlow(){
        String outputXml = null;
        DMSXmlResponse xmlResponse = null;
        String sessionId = "";
        System.out.println(dmsUserName+" 99999999999 "+dmsUserPswd);
        String inputXml = "<?xml version=\"1.0\"?>"
		                + "<WMConnect_Input>"
		                + "<Option>WMConnect</Option>"
		                + "<EngineName>" + engineName + "</EngineName>"
		                + "<ApplicationInfo></ApplicationInfo>"
		                + "<Participant>"
		                + "<Name>" + dmsUserName + "</Name>"
		                + "<Password>" + dmsUserPswd + "</Password>"
		                + "<Scope>ADMIN</Scope>"
		                + "<UserExist>N</UserExist>"
		                + "<ParticipantType>U</ParticipantType>"
		                + "</Participant>"
		                + "</WMConnect_Input>";
      
        String inputXml = "<?xml version=\"1.0\"?>"
                + "<NGOConnectCabinet_Input>"
                + "<Option>NGOConnectCabinet</Option>"
                + "<CabinetName>" + engineName + "</CabinetName>"
                + "<UserName>" + dmsUserName + "</UserName>"
                + "<UserPassword>" + dmsUserPswd + "</UserPassword>"
                + "<UserExist>N</UserExist>"
                + "<UserType>" + dmsUserType + "</UserType>"
                + "</NGOConnectCabinet_Input>";

        System.out.println(inputXml);
        
        try{
        	//System.out.println("Creating flow server connection..");
        	//System.out.println("INPUT: " + inputXml);
        	
        	if(ConfProperty.getProperty("IsNGejbAllow").equalsIgnoreCase("Y")){
        		outputXml = executeWithEjbClient(inputXml, "");
            } else {
            	outputXml = executeWithCallBroker(inputXml, "");
            }
        	
        	//System.out.println("OUTPUT: " + outputXml);
        	
            xmlResponse = new DMSXmlResponse(outputXml);
            
            //if (xmlResponse.getVal("MainCode").equalsIgnoreCase("0")){
            if (xmlResponse.getVal("Status").equalsIgnoreCase("0")){
                //sessionId = xmlResponse.getVal("SessionId");
            	sessionId = xmlResponse.getVal("UserDBId");
            	System.out.println(sessionId+" ......... ");
                return "success@@"+sessionId;
            }else{
            	System.out.println("Error in making successful flow server connection");
                return "failure@@"+xmlResponse.getVal("Error");
            }
        }catch(Exception e){
        	System.out.println("Error while executing product connection call."+ e);
            return null;
        }finally{
            if(xmlResponse != null){
                xmlResponse = null;
            }
        }
    }
	
	public boolean disconnectFlow(String sessionID) throws Exception{
        boolean flag = false;
        DMSInputXml xml = new DMSInputXml();
        String outputXml = null;
        DMSXmlResponse xmlResponse = null;
        
        String inputXml = xml.getDisconnectCabinetXml(engineName, sessionID);
        
        
        try{
        	//System.out.println("Disconnecting flow server..");
        	//System.out.println("INPUT: " + inputXml);

        	if(ConfProperty.getProperty("IsNGejbAllow").equalsIgnoreCase("Y")){
        		outputXml = executeWithEjbClient(inputXml, sessionID);
            } else {
            	outputXml = executeWithCallBroker(inputXml, sessionID);
            }
        	
        	//System.out.println("OUTPUT: " + outputXml);

        	xmlResponse = new DMSXmlResponse(outputXml);
            if (xmlResponse.getVal("Status").equalsIgnoreCase("0")){
                flag = true; 
            }else{
            	System.out.println("Error in disconnecting flow server. Session: "+sessionID);
                flag = false;
            }
            
        }catch(Exception e){
        	System.out.println("Error while executing product call."+e.getMessage());
            return false;
        }finally{
            if(xmlResponse != null){
            	xmlResponse = null;
            }
            if(xml != null){
            	xml = null;
            }
        }
        return flag;
    }
	

	public static String executeWithCallBroker(String inputXml, String sessionId) throws NGException,IOException,Exception{
		if(sessionId == null || sessionId.isEmpty())
			sessionId = "TBD";
		
		String outputXml = "";
		//if(ConfProperty.getProperty("XMLLogs").equalsIgnoreCase("Y"))
			ConfProperty.webservicelog("[INFO]: [SessionID:"+sessionId+"] INPUT --> "+inputXml);
		
		outputXml = DMSCallBroker.execute(inputXml, serverIP, Short.parseShort(jtsPort), 0);
		
		//if(ConfProperty.getProperty("XMLLogs").equalsIgnoreCase("Y"))
			ConfProperty.webservicelog("[INFO]: [SessionID:"+sessionId+"] OUTPUT --> "+outputXml);
		
		return outputXml;
	}

	public static String executeWithEjbClient(String inputXml, String sessionId) throws NGException, Exception{
		String outputXml = "";
		ngEJBClient = NGEjbClient.getSharedInstance();
		if(sessionId == null || sessionId.isEmpty())
			sessionId = "TBD";
		
		//if(ConfProperty.getProperty("XMLLogs").equalsIgnoreCase("Y"))
			ConfProperty.webservicelog("[INFO]: [SessionID:"+sessionId+"] INPUT --> "+inputXml);
        outputXml = ngEJBClient.makeCall(serverIP, serverPort, serverType, inputXml);
        
        //if(ConfProperty.getProperty("XMLLogs").equalsIgnoreCase("Y"))
        	ConfProperty.webservicelog("[INFO]: [SessionID:"+sessionId+"] OUTPUT --> "+outputXml);
        
		return outputXml;
	}
*/}
