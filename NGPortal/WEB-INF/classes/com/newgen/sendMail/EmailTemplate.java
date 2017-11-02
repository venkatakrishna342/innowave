/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : EmailTemplate.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : May 15, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.sendMail;

public class EmailTemplate {
	StringBuffer emailMsgTxt=new StringBuffer();
	public String getEmailTextMessage(String userName,String userId,String userPassword)
	{
	try{
		emailMsgTxt.append( "<html><head><title>Use custom Title here</title></head><body>");
		emailMsgTxt.append("Welcome "+userName+"<br><br>");

		emailMsgTxt.append( "Thank you for registering with us <br><br>");

		emailMsgTxt.append( "Your account has been created and you can log in by using your USER ID and PASSWORD by visiting website: <br><br>");

		emailMsgTxt.append( "Your details are as follows:<br>");
		emailMsgTxt.append( "Name            : "+userName+"<br>");
		emailMsgTxt.append( "Login ID        : "+userId+"<br>");
		emailMsgTxt.append( "Password        : "+userPassword+" <br><br>");


		emailMsgTxt.append("Upon logging in, you will be able to access other services like Application Status tracking <br><br>");

		emailMsgTxt.append("Thanks,<br>");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}

		return emailMsgTxt.toString();
	}
	
	/**
	 * 
	 * @param userId
	 * @param userPassword
	 * @return SG
	 */
	public String getForgotPassText(String userId,String userPassword)
	{
		try
		{
			emailMsgTxt.append( "<html><head><title>Use custom Title here</title></head><body>" );
			emailMsgTxt.append( "Welcome "+userId+"<br><br>" );
	
	
			emailMsgTxt.append( "Your account details is as follows  :  <br><br>" );
		
			emailMsgTxt.append( "Login ID        : "+userId+"<br>" );
			emailMsgTxt.append( "Password        : "+userPassword+" <br><br>" );
	
			emailMsgTxt.append( "Thanks,<br>" );
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	
			return emailMsgTxt.toString();
	}
	
}
