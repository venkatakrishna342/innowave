/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : HashGenerator.java
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

import java.security.MessageDigest;
/**
 * A utility class to create SHA-256 hashes
 * 
 * @author UIDAI
 *
 */
public class HashGenerator {

	public byte[] generateSha256Hash(byte[] message) {
		String algorithm = "SHA-256";
		String SECURITY_PROVIDER = "BC";

		byte[] hash = null;

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(algorithm, SECURITY_PROVIDER);
			digest.reset();
			hash = digest.digest(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hash;
	}

}
