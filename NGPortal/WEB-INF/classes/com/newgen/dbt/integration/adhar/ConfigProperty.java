/********************************************************************
 *  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
 * Group               : CIG
 * Product / Project   : BOT
 * Module              : BulkFileCreation (-ERC-)
 * File Name           : ConfProperty.java
 * Author              : dheeraj
 * Date written
 * (DD/MM/YYYY)        : Dec 17, 2016
 * Description         : Initializing configurations
 *
 * CHANGE HISTORY
  ************************************************************************************************************
 * Date				Change By		Change Description (Bug No. (If Any))
 * 
 ************************************************************************************************************/
package com.newgen.dbt.integration.adhar;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigProperty {

	private	static Properties prop 					= new Properties();
	public static String csvName					= "";
	
	public	static Connection conMain 				= null;
	public	static Connection conAlternate 			= null;
	
	public	static final int CONN_MAIN				= 1;
	public	static final int CONN_ALTERNATE			= 2;
	
	static{
		//1
		try{
			//FileInputStream fis = new FileInputStream("D:\\my\\NGPortalWS\\AadharIntConfig\\input.properties");
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+File.separator+"AadharIntConfig"+File.separator+"input.properties");
			System.out.println("prop "+System.getProperty("user.dir")+File.separator+"AadharIntConfig"+File.separator+"input.properties");
			prop.load(fis);
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String val = prop.get(key) != null ? prop.get(key).toString(): null;

				if(val == null || val.trim().equals("")){
					System.out.print("Invalid conf file entry for key :"+key);
					System.exit(0);
				}else{
					prop.setProperty(key.trim().toLowerCase(), val.trim());
				}
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("prop1 "+System.getProperty("user.dir")+File.separator+"AadharIntConfig"+File.separator+"input.properties");
			System.exit(0);
		}

		
		
	}

	
	public static String getProperty(String key)throws Exception{
		String val = null;
		val = prop.getProperty(key.toLowerCase().trim());

		if(val == null)
			throw new Exception("No value found for Key:"+key.toLowerCase().trim());
		else
			return val;
	}
	
		
}
