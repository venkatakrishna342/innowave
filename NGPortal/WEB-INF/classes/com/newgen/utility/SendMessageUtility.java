/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : SendMessageUtility.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : May 23, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.utility;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.newgen.utility.InitConfigProp;

public class SendMessageUtility {
	public boolean sendMessage(String mobileno,String smstext,String priority,String templateid,String function,String department,String scheme)
	{
		boolean result=false;
		
		try{
			String input  = "{"
					+ "\"mobileno\":\""+mobileno+"\","
					+ "\"smstext\":\""+smstext+"\","
					+ "\"priority\":"+priority+","
					+ "\"templateid\":"+templateid+","
					+ "\"function\":\""+function+"\","
					+ "\"department\":\""+department+"\","
					+ "\"scheme\":\""+scheme+"\"}";
			
			System.out.println("Message input: "+input);
			System.out.println("Message url : "+InitConfigProp.getProperty("sms_service") );
			URL url = new URL(InitConfigProp.getProperty("sms_service"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(25000);
			conn.setReadTimeout(25000);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			conn.disconnect();
//			if(conn.getResponseCode()==200)
//			{
//				result=true;
//			}
//			else
//			{
//				result=false;
//			}

				
		} catch (java.net.SocketTimeoutException e) {
			   return false;
		} catch(Exception e) {
			e.printStackTrace();		
		}
		return result;
	}
}
