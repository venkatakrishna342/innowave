/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ConstantConfig.java
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
package com.newgen.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConstantConfig {

	public static long getExperiationTime()
	{
		long expirationTime=0;
		 // ResourceBundle rsb = ResourceBundle.getBundle("config.configFile");
		   // expirationTime=Long.parseLong(rsb.getString("1800000"));
			String directory = System.getProperty("user.dir");
	       directory = directory + File.separator + "DBTConfig"+File.separator+" configFile.properties";
	       System.out.println(directory);
	       
			Properties prop = new Properties();
			InputStream input = null;
		    try{
		    	   File confFile = new File(directory);
		           if (!confFile.exists()) {
		             System.out.println("configFile.properties file absent.");
		           }
		    	
		           else{
		           input = new FileInputStream("directory");
		           prop.load(input);

		           expirationTime=Long.parseLong(prop.getProperty("1800000"));
		           }
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
		    return expirationTime;
		  
	}

}
