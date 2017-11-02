/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : SendEmailUtility.java
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

import com.newgen.npci.NpciClient;

/**
 * @author Varun Saddi
 *
 */
public class SendEmailUtility {
	public static void main(String[] args) {
		
		SendEmailUtility smut=new SendEmailUtility();
		boolean response =	smut.sendMessage("1", "noreply@mahadbt.co.in", "ankit.katoch@newgen.co.in", "testmail","testing");
		System.out.println(response);

		}
	
	public boolean sendMessage(String priority, String fromAddress, String toAddress, String subject,String mailBody) {
		
		boolean result = false;
		
		try{
			String input  = "{"
					+ "\"priority\":\""+priority+"\","
					+ "\"fromId\":\""+fromAddress+"\","
					+ "\"toId\":\""+toAddress+"\","
					+ "\"subject\":\""+subject+"\","
					+ "\"message\":\""+mailBody+"\"}";
			System.out.println(input);
			//System.out.println("Message url : "+InitConfigProp.getProperty("email_service") );
			URL url = new URL(InitConfigProp.getProperty("email_service"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(25000);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
//			if(conn.getResponseCode() == 200) {
//				result=true;
//			}else {
//				result=false;
//			}
			conn.disconnect();

				
		} catch(Exception e) {
			e.printStackTrace();		
		}
		return result;
	}

}
