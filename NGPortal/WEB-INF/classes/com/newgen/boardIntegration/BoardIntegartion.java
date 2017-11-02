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
* File Name           : BoardIntegration.java
* Author              : Swarnadip Ghosh/Sunny 
* Date written
* (DD/MM/YYYY)        : May 10, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
* 
 ************************************************************************************************************/
package com.newgen.boardIntegration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.json.JSONObject;
import JSON.JSONObject;
import JSON.XML;
//import org.json.XML;

public class BoardIntegartion {

    /**
     * @param args the command line arguments
     */
    //----for format the json----//
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
       
    	 /*String doctype       = "SSCER";
         String name          = "Alugade Prilam Krishnarao";
         String year          = "2009";
         String rollno        = "F142313";
         String exsession     = "MAR";
         String totalmarks    = "535";*/   
    	/* 
    	 String doctype       = "SSCER";
         String name          = "Kanifnath Yewale";
         String year          = "2008";
         String rollno        = "C144823";
         String exsession     = "MAR";
         String totalmarks    = "447";
    	*/        
    	
    	String doctype       = "SSC";
        String name          = "Alugade Prilam Krishnarao";
        String year          = "2013";
        String rollno        = "C034070";
        String exsession     = "MAR";
        String totalmarks    = "424";
    	
         JSONObject obj =  BoardIntegartion.sendReuest(doctype, name, year, rollno, exsession, totalmarks);
        System.out.println("output from : "+obj);
    
    }
    
    //change on 17th July 2017(URL change)
    public static JSONObject sendReuest(String doctype, String name, String year, String rollno, String session, String totmark) {
       
    	JSONObject jsonObject = null;
    	try {
            String output = null;
            //Date ts = Date.from(Instant.EPOCH);
            //String  ts = "2014-01-08 18:31:19.37";
            //URL url = new URL("http://boardmarksheet.maharashtra.gov.in/APIServices/api/1/pull/uri");
            
            String ts = "2017-06-23T13:52:06+05:30";
            //String orgid = "dbtmh";
            URL url = new URL("https://boardmarksheet.maharashtra.gov.in/DBTPortal/api/1/pull/uri");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/xml");
            
                    //+ "<PullURIRequest xmlns:ns2=\"http://tempuri.org/\" ver=\"1.0\" ts=\"" + ts + "\" txn=\"1496138040\" orgId=\"in.gov.maharashtra.mahahsscboard\" keyhash=\"b3dac3aaa19189a4bcf3f3d7678e37dae6653ff877cedf3116197943ebefcf7d\"> <DocDetails>\n"
            		//+ "  <FullName>" + name + "</FullName>\n"
            String input = "<?xml version=\"1.0\"?>\n"
							+ "<PullURIRequest xmlns:ns2=\"http://tempuri.org/\" ver=\"1.0\" orgid=\"dbtmh\" ts=\"" + ts + "\" txn=\"1495614126\">"
							+ "<DocDetails>\n"
							+ "  <DocType>" + doctype + "</DocType>\n"
							+ "  <year>" + year + "</year>\n"
							+ "  <rollnumber>" + rollno + "</rollnumber>\n"
							+ "  <exsession>" + session + "</exsession>\n"
							+ "  <totalmarks>" + totmark + "</totalmarks></DocDetails>\n"
							+ "</PullURIRequest>";
            System.out.println("Input :: " + input);
            
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            while ((output = br.readLine()) != null) {
                JSONObject json   = XML.toJSONObject(output);
                //System.out.println("output \n" + output);
            	
                //jsonObject = json.getJSONObject("PullURIResponse").getJSONObject("ResponseStatus");
                jsonObject = json.getJSONObject("PullURIResponse").getJSONObject("DocDetails");
                
                System.out.println("DocDetails :: " + jsonObject);
              }
        } catch (Exception ex) {
            Logger.getLogger(BoardIntegartion.class.getName()).log(Level.SEVERE, null, ex);
        }
		return jsonObject;
    }
}
