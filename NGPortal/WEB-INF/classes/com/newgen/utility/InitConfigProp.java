/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : InitConfigProp.java
* Author              : Ankit Bhasin
* Date written
* (DD/MM/YYYY)        : Mar 30, 2017
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
import java.util.Properties;
 
 public class InitConfigProp
 {
   private static Properties prop = new Properties();
 
   static{
     try{
       String directory = System.getProperty("user.dir");
       directory = directory + File.separator + "DBTConf.properties";
       
       File confFile = new File(directory);
       if (!confFile.exists()) {
         System.out.println("DBTConf.properties file absent.");
       }
       FileInputStream fis = new FileInputStream(confFile);
       prop.load(fis);
     }catch(Exception ex){
       ex.printStackTrace();
     }     
   }
 
   public static String getProperty(String key) throws Exception {
     String val = null;
     val = prop.getProperty(key.trim());
     if (val == null) {
       throw new Exception("No value found for Key:" + key.toLowerCase().trim());
     }
     return val;
   }
 }