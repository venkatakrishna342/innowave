/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ReadPropertiesFile.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 27, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dbt.integration.adhar;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertiesFile {
	    
	    public static Properties loadPropertiesFile() throws Exception {
        
		Properties prop = new Properties();
		InputStream in = new FileInputStream("other/input.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	    
	    public static void main(String[] args) throws Exception {
			
	    	Properties prop = ReadPropertiesFile.loadPropertiesFile();
	    	System.out.println(prop.getProperty("certificatePath"));
	    	System.out.println(prop.getProperty("KYCApp"));
	    	System.out.println(prop.getProperty("SUB-AUA-CODE"));
	    	System.out.println(prop.getProperty("PINCODE"));
	    	System.out.println(prop.getProperty("SendOtpUrl"));
	    	System.out.println(prop.getProperty("GetDataUrl"));
		}
}