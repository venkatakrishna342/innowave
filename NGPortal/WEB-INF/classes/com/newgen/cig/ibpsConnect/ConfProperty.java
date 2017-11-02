/********************************************************************
 *  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 * Group               : CIG
 * Product / Project   : NRC Assam
 * Module              : CSVBulkUploadMain (-ERC-)
 * File Name           : ConfProperty.java
 * Author              : Dheeraj in Guwahati
 * Date written
 * (DD/MM/YYYY)        : Sept 29, 2015
 * Description         : Initializing configurations and cacching dataclass information
 *
 * CHANGE HISTORY
  ************************************************************************************************************
 * Date				Change By		Change Description (Bug No. (If Any))
 *
 ************************************************************************************************************/
package com.newgen.cig.ibpsConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;

public class ConfProperty {

	private static Properties prop = new Properties();
	
	public String objSessionID = "TBD";
	public static HashMap<String,String> appFormDCFields = new HashMap<String,String>();
	public static HashMap<String,String> photographDCFields = new HashMap<String,String>();
	public static HashMap<String,String> suppDocDCFields = new HashMap<String,String>();

	public ConfProperty() throws IOException, Exception{
		webservicelog("[INFO]: [SessionID:"+objSessionID+"] Request process initiated");
		//Read configurations
		try{
			webservicelog("[INFO]: [SessionID:"+objSessionID+"] Reading configurations");
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+File.separator+"DBTIntegrationWS"+File.separator+"conf.properties");
			System.out.println(fis);
			prop.load(fis);
			Enumeration<String> e = (Enumeration<String>) prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String val = prop.get(key) != null ? prop.get(key).toString(): null;

				if(val == null || val.trim().equals("")){
					throw new Exception("Invalid conf file entry for key :"+key);
				}else{
					prop.setProperty(key.trim().toLowerCase(), val.trim());
				}
			}
		}
		catch(IOException ioe){
			throw new Exception("Error while reading configurations. Error: IOException while reading the configuration file conf.properties "+ioe.getMessage());
		}catch(Exception e){
			throw new Exception("Error while reading configurations. Error: "+e.getMessage());
		}
		
	
	}

	/*----------------------------------------------------------------
    // Function Name : getProperty
    // Date Written : Jun 2, 2015
    // Author : awesome dheeraj
    // Input Parameters :
    // Output Parameters : none
    // Return Values : String
    // Description : for fetching conf property
    // ----------------------------------------------------------------*/
	public static String getProperty(String key)throws Exception{
		String val = null;
		System.out.println(System.getProperty("user.dir")+File.separator+"DBTIntegrationWS"+File.separator+"conf.properties");
		//val = prop.getProperty(key.toLowerCase().trim());
		System.out.println(key);
		val = prop.getProperty(key);
		System.out.println(val+" 0 0 00 00 000 0 0");
		if(val == null)
			throw new Exception("No value found for Key:"+key);
		else
			return val;
	}
	
	public static void webservicelog(String strLog) {
        try {
            File objDirs = null;
            String sLogFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "DBTIntegrationWSLog";
            objDirs = new File(sLogFilePath);
            objDirs.mkdirs();
            GregorianCalendar cal = new GregorianCalendar();
            int logcount = 0;
            sLogFilePath = sLogFilePath + System.getProperty("file.separator") + "DMSIntegrationWebservice_" + cal.get(5) + "-" + (cal.get(2) + 1) + "-" + cal.get(1) + "_" + logcount + ".log";

            File logfile = new File(sLogFilePath);
            if (logfile.length() > 5242880L) {
                logcount++;
            }

            FileOutputStream fos = new FileOutputStream(sLogFilePath, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println("................................................................");
            pw.print(new Date() + "  ");
            pw.println(strLog+"\n");

            pw.close();
        } catch (Exception ex) {
            System.out.println("Error in creating log " + ex.getMessage());
        }
    }
	
}
