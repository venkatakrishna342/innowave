/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Resident REgistration Service having Aadhaar 
* File Name           : Set_Get_Data_PostLogin.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Jan 21, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.RegistrationEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.npci.NpciClient;
import com.newgen.sendMail.EmailTemplate;
import com.newgen.sendMail.SendMailAuthentication;
import com.newgen.utility.AppConstants;
import com.newgen.utility.DateFormatConverter;
import com.newgen.utility.InitConfigProp;
import com.newgen.utility.MYSql_DB_Connection;
import com.newgen.utility.SendEmailUtility;
import com.newgen.utility.SendMessageUtility;
import com.newgen.utility.Utility;

public class RegistrationServiceAadhaar {

	InitialContext Context;
	DataSource ds;
	DatabaseQuery databaseQ = new DatabaseQuery();

	public HashMap userRegistraion(AadhaarRegistrationEntity aadharRegistrationEntity_data, HashMap resultMap) {
		if (aadharRegistrationEntity_data == null) {
			resultMap.put("resultCode", -2);
			resultMap.put("resultDetails", "Invalid input");
			return resultMap;
		}
		try {
			String resultVal = checkMandatory(aadharRegistrationEntity_data);
			if (!AppConstants.ALL_MANDAT_ENTERED.equals(resultVal)) {
				resultMap.put("resultCode", -3);
				resultMap.put("resultDetails", resultVal);
				return resultMap;
			}
			String selectUsername = "";
			String insertUser = "";
			byte[] salt;
			byte[] encryptedpassword;
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			DateFormatConverter dateConverter = new DateFormatConverter();

			String formatedDob = dateConverter.dateFormatConverter(
					aadharRegistrationEntity_data.getDob().replaceAll("-", "/"), "dd/mm/yyyy", "yyyy-mm-dd"); // ps_insertUser.setString(1,
																												// //
																												// aadharRegistrationEntity_data.getUsername());
			try (Connection conn = ds.getConnection();) {
				conn.setAutoCommit(false);
				
				/************************* Inserting user credential*******************/
				salt = AppConstants.SALT;
				String pass = aadharRegistrationEntity_data.getPassword();
				encryptedpassword = getEncryptedPassword(pass, salt);
					String insertCredential = "";
					insertCredential = databaseQ.getSetUserCredentials();
					try (PreparedStatement ps_credential = conn.prepareStatement(insertCredential);) {
						ps_credential.setString(1, aadharRegistrationEntity_data.getUsername());
						ps_credential.setBytes(2, encryptedpassword);
						System.out.println(ps_credential.toString());
						int credentialResultSet = ps_credential.executeUpdate();							
						if (credentialResultSet == 0) {	
							resultMap.put("resultCode", -1);
							resultMap.put("resultDetails", "Not inserted credential table");
							System.out.println("User Created in aadhaar reg");
							return resultMap;
						}						
					}
				
				/***************************************duplicate check*************************************************/
				
				//String careoff = ""; 
				String relationType = "";
				/*if(aadharRegistrationEntity_data.getCareoff().contains(":"))
				{
					careoff = aadharRegistrationEntity_data.getCareoff().split(":")[1].trim();
					relationType = aadharRegistrationEntity_data.getCareoff().split(":")[0].trim();
				}
				else if(aadharRegistrationEntity_data.getCareoff().contains("/O"))
				{
					String[] splitFatherName = aadharRegistrationEntity_data.getCareoff().split(" ", aadharRegistrationEntity_data.getCareoff().indexOf(' '));
					relationType = splitFatherName[0].trim();
					careoff = splitFatherName[1].trim();
				}
				else{*/
				//	String careoff = aadharRegistrationEntity_data.getCareoff();
				//}
				// selectUsername = "select user_id from dbt_userprofile_dtl
				// where user_id = ?";
				selectUsername = databaseQ.getSelectinfoCheck();
				int dataCount = 0;
				try (PreparedStatement ps = conn.prepareStatement(selectUsername);) {
					// ps.setString(1, userName);
					ps.setString(1, aadharRegistrationEntity_data.getName().trim().toLowerCase());
					ps.setString(2, formatedDob);
					ps.setString(3, aadharRegistrationEntity_data.getMobile());
					System.out.println(ps.toString() + "  :: validation check in aadhar registration service");
					try (ResultSet rs1 = ps.executeQuery();) {
						if (rs1.next()) {

							resultMap.put("resultCode", -3);
							resultMap.put("resultDetails", "Record already exist with same user name");
							System.out.println("Record already exist with same user name in aadhaar registration");
							return resultMap;

						}

					}
				}
		/******************************************************************************************/
					insertUser = databaseQ.getSetUserAadhaarProfileDetails();
					String npciStatus = "N";
					try (PreparedStatement ps_insertUser = conn.prepareStatement(insertUser);) {

						//if (aadharRegistrationEntity_data.getNpciStatus().equalsIgnoreCase("Active")) {
						if ("Active".equalsIgnoreCase(aadharRegistrationEntity_data.getNpciStatus())) {
							npciStatus = "A";
						} else if ("Inactive".equalsIgnoreCase(aadharRegistrationEntity_data.getNpciStatus())) {
							npciStatus = "I";
						} else {
							npciStatus = "N";
						}
						int x = 1;
						
						// DateFormatConverter dateConverter=new
						// DateFormatConverter();
						// String
						// formatedDob=dateConverter.dateFormatConverter(aadharRegistrationEntity_data.getDob().replaceAll("-",
						// "/"), "dd/mm/yyyy", "yyyy-mm-dd"); //
						// ps_insertUser.setString(1,
						// aadharRegistrationEntity_data.getUsername());
						// ps_insertUser.setBytes(2, encryptedpassword);

						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getUsername().toLowerCase());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getAuidno());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getName());
						ps_insertUser.setString(x++, relationType);
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getCareoff());
						ps_insertUser.setString(x++, formatedDob);
						ps_insertUser.setInt(x++, aadharRegistrationEntity_data.getAge());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getMobile());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getEmail());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getGender());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getHouse());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getStreet());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getVillagetowncity());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getPostoffice());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getLandmark());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getSubdistrict());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getDistrict());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getState());
						ps_insertUser.setInt(x++, aadharRegistrationEntity_data.getPincode());
						ps_insertUser.setString(x++, aadharRegistrationEntity_data.getPhoto());
						ps_insertUser.setString(x++, "aadhaar");
						ps_insertUser.setString(x++, npciStatus);

						int insertResultSet = ps_insertUser.executeUpdate();
						if (insertResultSet > 0) {
							resultMap.put("resultCode", 0);
							resultMap.put("resultDetails", "Record inserted successfully");
							System.out.println("Record inserted successfully in aadhaar registraion");
							if (!npciStatus.equalsIgnoreCase("N")) {								
								String insertBankDetails = databaseQ.getSetUserAadhaarBankDetails();
								try (PreparedStatement ps_insertBankDetails = conn
										.prepareStatement(insertBankDetails);) {
									ps_insertBankDetails.setString(1, aadharRegistrationEntity_data.getBankName());
									ps_insertBankDetails.setString(2, aadharRegistrationEntity_data.getUsername());
									int insertBankDetailsCount = ps_insertBankDetails.executeUpdate();
									if (insertBankDetailsCount > 0) {
										System.out.println(
												" successfully insert into bank account details in aadhaar registraion");
									}
								}

							}
							
							try{
								conn.commit();
								resultMap.put("resultCode", 0);
								resultMap.put("resultDetails", "Record inserted successfully");
								
								SendMessageUtility smu = new SendMessageUtility();
								SendEmailUtility emu = new SendEmailUtility();
								String messageTest = "Successfully Registered UserName  "
										+ aadharRegistrationEntity_data.getUsername();
								String emailMessage = new EmailTemplate().getEmailTextMessage(
										aadharRegistrationEntity_data.getUsername(),
										aadharRegistrationEntity_data.getName(),
										aadharRegistrationEntity_data.getPassword());
								String emailSubject = InitConfigProp.getProperty("email_subject");
								DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");	
								Date dateobj = new Date();
							    System.out.println("calling message utitlity at time :: " + df.format(dateobj));
								boolean message = smu.sendMessage(aadharRegistrationEntity_data.getMobile(),messageTest, "1", "1", "Registration", "", "");
						 		System.out.println("ending of message utility and calling email utitlity at time :: " + df.format(dateobj));
								boolean emailStatus = emu.sendMessage("1",InitConfigProp.getProperty("dbtDefaultEmail"),aadharRegistrationEntity_data.getEmail(), emailSubject, emailMessage);						
								df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");	
								 dateobj = new Date();
								System.out.println("ending of email utitlity at time :: " + df.format(dateobj));

								resultMap.put("resultDetails", "Record inserted successfully");
								if (message && emailStatus) {
									System.out.println("SMS and Email Sent Successfully");
								} 
								else if (message && !emailStatus) {
									System.out.println("SMS Sent Successfully, problem in email.");
								}
								else if (!message && emailStatus) {
									System.out.println("Email Sent Successfully, problem in sms.");
								}
								else if (!message && !emailStatus) {
									System.out.println("SMS and Email Sent failure");
								}
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
		} catch (NamingException | SQLException ex) {
			if(ex.getMessage().contains("Duplicate entry"))
			{
				resultMap.put("resultCode", -2);
				resultMap.put("resultDetails", "RegistrationService DB Exception: " + ex.toString());
				System.out.println("RegistrationService DB Exception: " + ex.toString());
				return resultMap;
			}
			else{
				resultMap.put("resultCode", -1);
				resultMap.put("resultDetails", "RegistrationService DB Exception: " + ex.toString());
				System.out.println("RegistrationService DB Exception: " + ex.toString());
				return resultMap;
			}
		}
		return resultMap;
	}

	/**
	 * @param aadharRegistrationEntity_data
	 */
	private String checkMandatory(AadhaarRegistrationEntity aadharRegistrationEntity_data) {
		String message = AppConstants.ALL_MANDAT_ENTERED;
		if (aadharRegistrationEntity_data.getUsername() == null
				|| "".equals(aadharRegistrationEntity_data.getUsername())) {
			message = " Please Enter UserName,";
		} /*
			 * else if (aadharRegistrationEntity_data.getPassword() == null ||
			 * "".equals(aadharRegistrationEntity_data.getPassword())) { message
			 * = " Please Enter Password,"; } else if
			 * (aadharRegistrationEntity_data.getUsertype() == null ||
			 * "".equals(aadharRegistrationEntity_data.getUsertype())) { message
			 * = " Please Select User Type,"; } else if
			 * (aadharRegistrationEntity_data.getFirstname() == null ||
			 * "".equals(aadharRegistrationEntity_data.getFirstname())) {
			 * message = " Please Enter First Name,"; } else if
			 * (aadharRegistrationEntity_data.getLastname() == null ||
			 * "".equals(aadharRegistrationEntity_data.getLastname())) { message
			 * = " Please Enter Last Name,"; } else if
			 * (aadharRegistrationEntity_data.getEmailid() == null ||
			 * "".equals(aadharRegistrationEntity_data.getEmailid())) { message
			 * = " Please Enter Email,"; } else if
			 * (aadharRegistrationEntity_data.getMobileno() == null ||
			 * "".equals(aadharRegistrationEntity_data.getMobileno())) { message
			 * = " Please Enter Mobile Number"; }
			 */
		return message;
	}

	/**
	 * @param password
	 * @param salt
	 * @return
	 */
	// --- for password encryption---//
	private byte[] getEncryptedPassword(String password, byte[] salt) {
		try {
			// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
			// specifically names SHA-1 as an acceptable hashing algorithm for
			// PBKDF2
			String algorithm = "PBKDF2WithHmacSHA1";
			// SHA-1 generates 160 bit hashes, so that's what makes sense here
			int derivedKeyLength = 160;
			// Pick an iteration count that works for you. The NIST recommends
			// at
			// least 1,000 iterations:
			int iterations = 20000;
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
			SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
			return f.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
			Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
