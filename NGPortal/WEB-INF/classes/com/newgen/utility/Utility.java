/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Utility.java
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

import java.util.Random;

public class Utility {
	
public static String generateCaptchaTextMethod1() 	 {
	
	   	Random rdm=new Random();
		int rl=rdm.nextInt(); // Random numbers are generated. 
		String hash1 = Integer.toHexString(rl); // Random numbers are converted to Hexa Decimal.
		
		return hash1;
		
}


public static String generateCaptchaTextMethod2(int captchaLength) 	 {
	
	 String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	 StringBuffer captchaStrBuffer = new StringBuffer();
	        java.util.Random rnd = new java.util.Random();
	        
	        // build a random captchaLength chars salt        
	        while (captchaStrBuffer.length() < captchaLength)
	        {
	            int index = (int) (rnd.nextFloat() * saltChars.length());
	            captchaStrBuffer.append(saltChars.substring(index, index+1));
	        }

	    return captchaStrBuffer.toString();
	    
}


}