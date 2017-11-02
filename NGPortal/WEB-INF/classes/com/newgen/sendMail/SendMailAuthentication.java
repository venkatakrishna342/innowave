/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : SendMailAuthentication.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Jan 29, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.sendMail;


import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import com.newgen.utility.InitConfigProp;


public class SendMailAuthentication {
	

	public void postMail( String recipients[], String subject,String message , String from) throws MessagingException
	{
		try{
			//for online
			final  String SMTP_HOST_NAME = InitConfigProp.getProperty("smtp_host_name");
			final  String SMTP_AUTH_USER = InitConfigProp.getProperty("smtp_auth_user");
			final  String SMTP_AUTH_PWD  = InitConfigProp.getProperty("smtp_auth_pwd");
			boolean debug = false;
			//Set the host smtp address
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "true");
	
			// Authenticator auth = new SMTPAuthenticator();
			Authenticator auth = null; //default: no authentication            
			auth = new Authenticator () {                    
					public PasswordAuthentication getPasswordAuthentication() 
					{                        
						String username = SMTP_AUTH_USER;
						String password = SMTP_AUTH_PWD;
						return new PasswordAuthentication(username, password);                    
					}                
				};    
		
			Session session = Session.getInstance(props, auth);
			session.setDebug(debug);
	
			// create a message
			Message msg = new MimeMessage(session);
	
			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(from);
			msg.setFrom(addressFrom);
	
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++)
			{
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
	
			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setContent(message, "text/html");
			Transport.send(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
