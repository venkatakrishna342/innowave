/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : SaveMultiPartUtility.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : May 10, 2017
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*import com.sun.jersey.core.header.FormDataContentDisposition;*/

public class SaveMultipartUtility {
	
	
	
	
	
	
	public String saveOnDisk(InputStream uploadedInputStream,String  customFileName, String userID,File docDirectory,String fileLocation) {
		
		
		
		
		
	//	String fileName = fileDetail.getFileName();
	//	System.out.println("FileName :: " + fileName);
		
		//File docDirectory = new File(System.getProperty("user.dir") + File.separatorChar + "dbt" + File.separatorChar + "Applications" + File.separatorChar + userID + File.separatorChar + "Documents");
//		File docDirectory = new File(".." + File.separatorChar + ".." + File.separatorChar + ".." + File.separatorChar + "dbt" + File.separatorChar + "Applications" + File.separatorChar + userID + File.separatorChar + "Documents");
//		String fileLocation = docDirectory + File.separator + fileName;
		System.out.println("fileLocation :: " + fileLocation);
		if (!docDirectory.exists()) {
		    boolean result = false;
		    try{
		    	docDirectory.mkdirs();
		        result = true;
		    }catch(SecurityException se){
		        System.out.println("Exception occurred !!");
		    	System.out.println("Failed to create directory !!");
		    	se.printStackTrace();
		    }        
		    if(result){    
		        System.out.println("Documents Directory created sussessfully !!");  
		    }
		}
		try {
			OutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileLocation;
	}
	

}
