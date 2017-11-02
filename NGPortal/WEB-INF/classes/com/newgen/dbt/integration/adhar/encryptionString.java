/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : encryptionString.java
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


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class encryptionString 
{
    public byte[] run(String text, SecretKeySpec key) 
    {
    	byte[] encrypted = null;
        try 
        {   
            
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypted = cipher.doFinal(text.getBytes());   
            
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
		
		
		return encrypted;
        
    }
	
}
