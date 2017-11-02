/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Non Aadhaar Registration Service 
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

import java.io.File;
import java.io.InputStream;
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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition.FormDataContentDispositionBuilder;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.sendMail.EmailTemplate;
import com.newgen.sendMail.SendMailAuthentication;
import com.newgen.utility.AppConstants;
import com.newgen.utility.DateFormatConverter;
import com.newgen.utility.InitConfigProp;
import com.newgen.utility.SaveMultipartUtility;
import com.newgen.utility.SendEmailUtility;
import com.newgen.utility.SendMessageUtility;
/*import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;*/

import JSON.JSONObject;

public class NonAadhaarRegistrationService {
	InitialContext Context;
    DataSource ds;
   // Connection conn = null ;
	PreparedStatement ps = null;
	public HashMap createResidentProfile(JSONObject jsonData,FormDataMultiPart formParams,HashMap hashMap ) throws Exception
	{
		System.out.println(jsonData.toString()+" Inside updat Profile");
		String flag="error";
		String userId	= "";				
		String auidno = "";					
		String name = "";			
		String careoff		= "";					
		String dob 	= "";						
		String age 	= "";						
		String mobile = "";					
		String email		= "";					
		String gender 	= "";					
		String house= "";				
		String street	= "";				
		String villagetowncity= "";				
		String locality	= "";			
		String postoffice	= "";			
		String landmark	= "";			
		String taluka	= "";			
		String district	= "";			
		String state	= "";			
		String pincode	= "";	
		String c_house  = "";				
		String c_street	= "";				
		String c_villagetowncity= "";				
		String c_locality	= "";			
		String c_postoffice	= "";			
		String c_landmark	= "";			
		String c_taluka = "";
		String c_district	= "";			
		String c_state	= "";			
		int c_pincode	= 0;	
		String father	= "";
		String isFatherAlive = "";
		String mother	= "";	
		String isMotherAlive = "";
		String relationType	= "";	
		String guardian	= "";	
		String cardNum	= "";	
		String reg_date	= "";	
		String reg_authority = "";	
		String bankAcc = "";	
		String branch = "";	
		String ifsc = "";	
		String bankAdd = "";	
		String POI = "";	
		String POA = "";	
		String POR = "";	
		String attachmentData = "";	
		String setdob = "";	
		String password = "";	
		String eid = "";	
		String photo = "";	
		String poiValue = "";	
		String poaValue = "";	
		String dobValue = "";	
		String porValue = "";	
		String copyAddress = "";	
			
		
		String insertDocRep="";
		String updateDocRep="";
		String selectQuery="";
		String selectQueryCheck="";
		String docTypeInsert="";
	    DatabaseQuery databaseQ = new DatabaseQuery();

		String[] docType={"poiValue","poaValue","porValue","dobValue","userbankpassbookValue",};
		int count=0;
		//*****************************qureies***************************//
		String validateDuplicacy=databaseQ.validateDuplicacyNonAadhaar();
		String insertProfile = databaseQ.getSetUserProfileDetails();
		//*****************************qureies***************************//
		
		DateFormatConverter updatedDate=new DateFormatConverter();
		SaveMultipartUtility smu=new SaveMultipartUtility();
		Context = new javax.naming.InitialContext();
		ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
		try	(Connection conn= ds.getConnection();
			PreparedStatement ps_validateDuplicacy=conn.prepareStatement(validateDuplicacy);)
		{
			conn.setAutoCommit(false);
			try{userId=jsonData.getString("username").toLowerCase();}catch(Exception e){userId="";}
			try{eid=jsonData.getString("eid");}catch(Exception e){eid="";}
			try{name=jsonData.getString("name");}catch(Exception e){name="";}
			try{careoff=jsonData.getString("careoff");}catch(Exception e){careoff="";}
			try{dob=jsonData.getString("dob");}catch(Exception e){dob="";}
			try{age=jsonData.getString("age");}catch(Exception e){age="0";}
			try{mobile=jsonData.getString("mobile");}catch(Exception e){mobile="";}
			try{email=jsonData.getString("email");}catch(Exception e){email="";}
			try{gender=jsonData.getString("gender");}catch(Exception e){gender="";}
			try{house=jsonData.getString("house");}catch(Exception e){house="";}
			try{street=jsonData.getString("street");}catch(Exception e){street="";}
			try{villagetowncity=jsonData.getString("villagetowncity");}catch(Exception e){villagetowncity="";}
			try{locality=jsonData.getString("locality");}catch(Exception e){locality="";}
			try{postoffice=jsonData.getString("postoffice");}catch(Exception e){postoffice="";}
			try{landmark=jsonData.getString("landmark");}catch(Exception e){landmark="";}
			try{taluka=jsonData.getString("taluka");}catch(Exception e){taluka="";}

			try{district=jsonData.getString("district");}catch(Exception e){district="";}
			try{state=jsonData.getString("state");}catch(Exception e){state="";}
			try{pincode=jsonData.getString("pincode");}catch(Exception e){pincode="";}
			try{photo=jsonData.getString("photo");}catch(Exception e){photo="";}
			
			try{c_house=jsonData.getString("c_house");}catch(Exception e){c_house="";}
			try{c_street=jsonData.getString("c_street");}catch(Exception e){c_street="";}
			try{c_villagetowncity=jsonData.getString("c_villagetowncity");}catch(Exception e){c_villagetowncity="";}
			try{c_locality=jsonData.getString("c_locality");}catch(Exception e){c_locality="";}
			try{c_postoffice=jsonData.getString("c_postoffice");}catch(Exception e){c_postoffice="";}
			try{c_landmark=jsonData.getString("c_landmark");}catch(Exception e){c_landmark="";}
			try{c_taluka=jsonData.getString("c_taluka");}catch(Exception e){c_taluka="";}

			try{c_district=jsonData.getString("c_district");}catch(Exception e){c_district="";}
			try{c_state=jsonData.getString("c_state");}catch(Exception e){c_state="";}
			try{c_pincode=Integer.parseInt(jsonData.getString("c_pincode"));}catch(Exception e){c_pincode=0;}
			try{father=jsonData.getString("father");}catch(Exception e){father="";}
			try{mother=jsonData.getString("mother");}catch(Exception e){mother="";}
			try{
				isMotherAlive=jsonData.getString("applicantMotherAlive");
				if("Yes".equalsIgnoreCase(isMotherAlive))
					isMotherAlive="Y";
				else
					isMotherAlive="N";
				}catch(Exception e){isMotherAlive="";}
			try{
				isFatherAlive=jsonData.getString("applicantFatherAlive");
				if("Yes".equalsIgnoreCase(isFatherAlive))
					isFatherAlive="Y";
				else
					isFatherAlive="N";
				}catch(Exception e){isFatherAlive="";}
			try{relationType=jsonData.getString("relationType");}catch(Exception e){relationType="";}
			//set doctype in case of guaridan
			try{guardian=jsonData.getString("guardian");}catch(Exception e){guardian="";}
			try{bankAcc=jsonData.getString("bankAcc");}catch(Exception e){bankAcc="";}
			try{branch=jsonData.getString("branch");}catch(Exception e){branch="";}
			try{ifsc=jsonData.getString("ifsc");}catch(Exception e){ifsc="";}
			try{bankAdd=jsonData.getString("bankAdd");}catch(Exception e){bankAdd="";}
			try{bankAdd=jsonData.getString("bankAdd");}catch(Exception e){bankAdd="";}
			try{password=jsonData.getString("password");}catch(Exception e){password="";}
			try{copyAddress=jsonData.getString("copyAddress");}catch(Exception e){copyAddress="";}
			
			try{poiValue=jsonData.getString("poiValue");}catch(Exception e){poiValue="";}
			try{poaValue=jsonData.getString("poaValue");}catch(Exception e){poaValue="";}
			try{dobValue=jsonData.getString("dobValue");}catch(Exception e){dobValue="";}
			try{porValue=jsonData.getString("porValue");}catch(Exception e){porValue="";}
			
//			String compareValues = name+father+dob;
//			int dataCount = 0;
			ps_validateDuplicacy.setString(1, name.trim().toLowerCase());
			ps_validateDuplicacy.setString(2,updatedDate.dateFormatConverter(dob.replace("/", "-"), "DD/MM/YYYY", "YYYY-MM-DD"));
			ps_validateDuplicacy.setString(3,mobile);
			try(ResultSet rs_validity = ps_validateDuplicacy.executeQuery();)
			{
				System.out.println(ps_validateDuplicacy.toString());
				if (rs_validity.next()) {
					
					hashMap.put("resultCode", -3);
					 flag = "error";
	            	 return hashMap;
	             }
			}

			 byte[] salt;
             byte[] encryptedpassword;
             salt = AppConstants.SALT;  
             encryptedpassword = getEncryptedPassword(password, salt);
			
         	String insertCredential = "";
	     	insertCredential = databaseQ.getSetUserCredentials() ;  
     		try (PreparedStatement ps_credential = conn.prepareStatement(insertCredential);){
	    	
     			ps_credential.setString(1,userId);
             	ps_credential.setBytes(2,encryptedpassword);
             	System.out.println(ps_credential.toString());
             	int credentialResultSet=ps_credential.executeUpdate();
             	if(credentialResultSet == 0){
             		hashMap.put("resultCode", -1);
					 flag = "error";
	            	 return hashMap;
             	}
	    	}
            
             int x=1;

			if("".equalsIgnoreCase(dob))
			{
				dob=null;
			}
			else
			{
				setdob=updatedDate.dateFormatConverter(dob.replace("/", "-"), "DD/MM/YYYY", "YYYY-MM-DD");

			}
			
			/*String insertProfile=" insert into dbt_userprofile_dtl"
					+ "( user_id, eid_pce_no, full_name, careof, dob, age, mobile_no, email_id, gender, house, street, village_town, post_office, landmark,sub_district ,district, state, pincode, profile_pic, c_house,c_street,c_village_town,c_locality,c_post_office,c_landmark,c_sub_district,c_district,c_state,c_pincode,father,isFatherAlive,mother,isMotherAlive,guardian)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(insertProfile);*/
			try(PreparedStatement ps_insertProfile=conn.prepareStatement(insertProfile);){
			ps_insertProfile.setString(x++, userId);
			ps_insertProfile.setString(x++, eid);
			ps_insertProfile.setString(x++, name);
			ps_insertProfile.setString(x++, careoff);
			ps_insertProfile.setString(x++, setdob);
			ps_insertProfile.setString(x++, age);
			ps_insertProfile.setString(x++, mobile);
			ps_insertProfile.setString(x++, email);
			ps_insertProfile.setString(x++, gender);
			ps_insertProfile.setString(x++, house);
			ps_insertProfile.setString(x++, street);
			ps_insertProfile.setString(x++, villagetowncity);
			ps_insertProfile.setString(x++, postoffice);
			ps_insertProfile.setString(x++, landmark);
			ps_insertProfile.setString(x++, taluka);
			ps_insertProfile.setString(x++, district);
			ps_insertProfile.setString(x++, state);
			ps_insertProfile.setString(x++, pincode);
			ps_insertProfile.setString(x++, photo);
			ps_insertProfile.setString(x++, c_house);
			ps_insertProfile.setString(x++, c_street);
			ps_insertProfile.setString(x++, c_villagetowncity);
			ps_insertProfile.setString(x++, c_locality);
			ps_insertProfile.setString(x++, c_postoffice);
			ps_insertProfile.setString(x++, c_landmark);
			ps_insertProfile.setString(x++, c_taluka);
			ps_insertProfile.setString(x++, c_district);
			ps_insertProfile.setString(x++, c_state);
			ps_insertProfile.setInt(x++, c_pincode);
			ps_insertProfile.setString(x++, father);
			ps_insertProfile.setString(x++, isFatherAlive);
			ps_insertProfile.setString(x++, mother);
			ps_insertProfile.setString(x++, isMotherAlive);
			ps_insertProfile.setString(x++, guardian);
			ps_insertProfile.setString(x++, copyAddress);
			ps_insertProfile.setString(x++, relationType);
			//total x=30
			int insertProfileCount=ps_insertProfile.executeUpdate();
			if(insertProfileCount>0)
			{
				System.out.println("successfully inserted in user profile");
				//PreparedStatement ps_credential=null;
		     	
		/*
			    		 if(credentialResultSet>0)
			    		 {*/
			    			 String insertBankDetails = databaseQ.getSetUserBankDetails();
			    			 try(PreparedStatement ps_insertBankDetails=conn.prepareStatement(insertBankDetails);)
			    			 {
			    			 	
			    				 ps_insertBankDetails.setString(1, bankAcc);
			    				 ps_insertBankDetails.setString(2, branch);
			    				 ps_insertBankDetails.setString(3, ifsc);
			    				 ps_insertBankDetails.setString(4, bankAdd);
			    				 ps_insertBankDetails.setString(5, userId);
			    				int insertBankDetailsCount= ps_insertBankDetails.executeUpdate();
			    				if(insertBankDetailsCount>0)
			    				{
			    					System.out.println(" successfully insert into bank account details in non aadhaar registration");
			    					flag="success";
			    					int pancardCount = 0;
			    					int voteridCount = 0;
			    					int passportCount = 0;
			    					int rationcardCount = 0;
			    					int drivnigLicenseCount = 0;
			    					int passbookCount = 0;
			    					int birthCertificateCount = 0;
			    					int mnregacardCount = 0;
			    					int marriageCertificateCount= 0;
			    					int markSheetCount = 0;
			    					int govtIssuedIdCount = 0;
			    					int nregsCardCount = 0;
			    					
			    					int photIdByInstCount = 0;
			    					int armsLicenseCount = 0;
			    					int photoBankAtmCount = 0;
			    					int photoCreditCardCount = 0;
			    					int pensionerCardCount = 0;
			    					int freedomFighterCount = 0;
			    					int kissanPassbookCount = 0;
			    					int cghsPhotoCardCount = 0;
			    					int addressingCardOfPostCount = 0;
			    					int certificateOfIdentityCount = 0;
			    					int disabilityIdCardCount = 0;
			    					int armyCanteenCardCount = 0;
			    					int centralStateGovtIssuedDocCount = 0;
			    					int sslcBookCount = 0;
			    					int centralStatePensionCount = 0;
			    					int centralStateGovtHealthDocCount = 0;
			    					int postOfficePassbookCount = 0;
			    					int electricBillCount = 0;
			    					int waterBillCount = 0;
			    					int landlineBillCount = 0;
			    					int propertyTaxReciptCount = 0;
			    				
			    					 int creditCardStatementCount = 0;
			    					 int insurancePolicyCount = 0;
			    					 int signedLetterOfBankCount = 0;
			    					 int signedLetterByCompanyCount = 0;
			    					 int signedLetterByInstituteCount = 0;
			    					 int addressIssuedByMpCount = 0;
			    					 int addressIssuedByPanchyatCount = 0;
			    					 int incomeTaxAssessmentCount = 0;
			    					 int vehicalRegCertCount = 0;
			    					 int saleLeaseAgreementCount = 0;
			    					 int castDomicileCertCount = 0;
			    					 int gasBillCount = 0;
			    					 int passportSpouseCount = 0;
			    					 int passportParentCount = 0;
			    					 int letterOfAccommodationCount = 0;
			    					 int guardianCertificateCount = 0;
			    					
			    					
			    					String doc_id = "";
			    					String error = "";
			    					String setDate = "";
			    					selectQuery = databaseQ.getProfileDocId();
			    					insertDocRep = databaseQ.getSetUserDocDetails();
			    					updateDocRep = databaseQ.getUpdateUserDocDetails();	
			    					/**Inserting Document details submitted as proof**/			
			    					String proofType="";
			    					for(int i=0; i<docType.length; i++) {
			    						
			    						//Get Proof Type
			    						try{proofType = jsonData.getString(docType[i]);}catch(Exception e){continue;}
			    						 if((proofType.equalsIgnoreCase("PanCard") && pancardCount == 0 ) || (proofType.equalsIgnoreCase("VoterId") && voteridCount == 0 ) || 
			    								(proofType.equalsIgnoreCase("Passport") && passportCount == 0 ) ||  (proofType.equalsIgnoreCase("RationCard") && rationcardCount == 0 ) ||
			    						 		(proofType.equalsIgnoreCase("DrivingLicense") && drivnigLicenseCount == 0 )||(proofType.equalsIgnoreCase("Passbook") && passbookCount == 0 ) || 
			    						 		(proofType.equalsIgnoreCase("BirthCertificate") && birthCertificateCount == 0 ) || (proofType.equalsIgnoreCase("MnregaCard") && mnregacardCount == 0 )||
			    						 		(proofType.equalsIgnoreCase("MarriageCertificate") && marriageCertificateCount == 0 )||(proofType.equalsIgnoreCase("Marksheet") && markSheetCount == 0 )||
			    						 		(proofType.equalsIgnoreCase("GovtIssuedId") && govtIssuedIdCount == 0 ) || (proofType.equalsIgnoreCase("UserBankPassBook") ||
			    						 		(proofType.equalsIgnoreCase("NregsCard") && nregsCardCount == 0) ||(proofType.equalsIgnoreCase("PhotIdByInst") && photIdByInstCount == 0) ||
			    						 		(proofType.equalsIgnoreCase("ArmsLicense") && armsLicenseCount == 0) ||(proofType.equalsIgnoreCase("PhotoBankAtm") && photoBankAtmCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("photoCreditCard") && photoCreditCardCount == 0) ||(proofType.equalsIgnoreCase("pensionerCard") && pensionerCardCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("freedomFighter") && freedomFighterCount == 0) ||(proofType.equalsIgnoreCase("kissanPassbook") && kissanPassbookCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("cghsPhotoCard") && cghsPhotoCardCount == 0) ||(proofType.equalsIgnoreCase("addressingCardOfPost") && addressingCardOfPostCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("certificateOfIdentity") && certificateOfIdentityCount == 0) ||(proofType.equalsIgnoreCase("disabilityIdCard") && disabilityIdCardCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("ArmyCanteenCard") && armyCanteenCardCount == 0) ||(proofType.equalsIgnoreCase("CentralStateGovtIssuedDoc") && centralStateGovtIssuedDocCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("SslcBook") && sslcBookCount == 0) ||(proofType.equalsIgnoreCase("CentralStatePension") && centralStatePensionCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("CentralStateGovtHealthDoc") && centralStateGovtHealthDocCount == 0) ||(proofType.equalsIgnoreCase("PostOfficePassbook") && postOfficePassbookCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("ElectricBill") && electricBillCount == 0) ||(proofType.equalsIgnoreCase("WaterBill") && waterBillCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("LandlineBill") && landlineBillCount == 0) ||(proofType.equalsIgnoreCase("PropertyTaxRecipt") && propertyTaxReciptCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("CreditCardStatement") && creditCardStatementCount == 0) ||(proofType.equalsIgnoreCase("InsurancePolicy") && insurancePolicyCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("SignedLetterOfBank") && signedLetterOfBankCount == 0) ||(proofType.equalsIgnoreCase("SignedLetterByCompany") && signedLetterByCompanyCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("SignedLetterByInstitute") && signedLetterByInstituteCount == 0) ||(proofType.equalsIgnoreCase("AddressIssuedByMp") && addressIssuedByMpCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("AddressIssuedByPanchyat") && addressIssuedByPanchyatCount == 0) ||(proofType.equalsIgnoreCase("IncomeTaxAssessment") && incomeTaxAssessmentCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("VehicalRegCert") && vehicalRegCertCount == 0) ||(proofType.equalsIgnoreCase("SaleLeaseAgreement") && saleLeaseAgreementCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("CastDomicileCert") && castDomicileCertCount == 0) ||(proofType.equalsIgnoreCase("GasBill") && gasBillCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("PassportSpouse") && passportSpouseCount == 0) ||(proofType.equalsIgnoreCase("PassportParent") && passportParentCount == 0) ||
			    							 	(proofType.equalsIgnoreCase("LetterOfAccommodation") && letterOfAccommodationCount == 0) || proofType.equalsIgnoreCase("GuardianCertificate") && guardianCertificateCount == 0 )){
			    							 
			    							 //Increase Doc Type count 
			    							if(proofType.equalsIgnoreCase("PanCard"))
			    								pancardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("VoterId"))
			    				   		 		voteridCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("Passport"))
			    				   		 		passportCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("RationCard"))
			    				   		 		rationcardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("DrivingLicense"))
			    				   		 		drivnigLicenseCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("Passbook"))
			    				   			 	passbookCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("BirthCertificate"))
			    				   		 		birthCertificateCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("MnregaCard"))
			    				   		 		mnregacardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("MarriageCertificate"))
			    				   		 		marriageCertificateCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("Marksheet"))
			    				   		 		markSheetCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("GovtIssuedId"))
			    				   		 		govtIssuedIdCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("NregsCard"))
			    				   		 		nregsCardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("PhotIdByInst"))
			    				   		 		photIdByInstCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("ArmsLicense"))
			    				   		 		armsLicenseCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("PhotoBankAtm"))
			    				   		 		photoBankAtmCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("photoCreditCard"))
			    				   		 		photoCreditCardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("pensionerCard"))
			    				   		 		pensionerCardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("freedomFighter"))
			    				   		 		freedomFighterCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("kissanPassbook"))
			    				   		 		kissanPassbookCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("cghsPhotoCard"))
			    				   		 		cghsPhotoCardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("addressingCardOfPost"))
			    				   		 		addressingCardOfPostCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("certificateOfIdentity"))
			    				   		 		certificateOfIdentityCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("disabilityIdCard"))
			    				   		 		disabilityIdCardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("ArmyCanteenCard"))
			    				   		 		armyCanteenCardCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("CentralStateGovtIssuedDoc"))
			    				   		 		centralStateGovtIssuedDocCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("SslcBook"))
			    				   		 		sslcBookCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("CentralStatePension"))
			    				   		 		centralStatePensionCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("CentralStateGovtHealthDoc"))
			    				   		 		centralStateGovtHealthDocCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("PostOfficePassbook"))
			    				   		 		postOfficePassbookCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("ElectricBill"))
			    				   		 		electricBillCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("WaterBill"))
			    				   		 		waterBillCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("LandlineBill"))
			    				   		 		landlineBillCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("PropertyTaxRecipt"))
			    				   		 		propertyTaxReciptCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("CreditCardStatement"))
			    				   		 		creditCardStatementCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("InsurancePolicy"))
			    				   		 		insurancePolicyCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("SignedLetterOfBank"))
			    				   		 		signedLetterOfBankCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("SignedLetterByCompany"))
			    				   		 		signedLetterByCompanyCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("SignedLetterByInstitute"))
			    				   		 		signedLetterByInstituteCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("AddressIssuedByMp"))
			    				   		 		addressIssuedByMpCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("AddressIssuedByPanchyat"))
			    				   		 		addressIssuedByPanchyatCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("IncomeTaxAssessment"))
			    				   		 		incomeTaxAssessmentCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("VehicalRegCert"))
			    				   		 		vehicalRegCertCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("SaleLeaseAgreement"))
			    				   		 		saleLeaseAgreementCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("CastDomicileCert"))
			    				   		 		castDomicileCertCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("GasBill"))
			    				   		 		gasBillCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("PassportSpouse"))
			    				   		 		passportSpouseCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("PassportParent"))
			    				   		 		passportParentCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("LetterOfAccommodation"))
			    				   		 		letterOfAccommodationCount++;
			    				   		 	else if(proofType.equalsIgnoreCase("GuardianCertificate"))
			    				   		 		guardianCertificateCount++;
			    							
			    							//Get the Doc Id of selected POI doc type
			    							try(PreparedStatement ps_selectQuery=conn.prepareStatement(selectQuery))
			    							{
			    							
				    							//ps_selectQuery=conn.prepareStatement(selectQuery);
				    							ps_selectQuery.setString(1, proofType);
				    				        	System.out.println(ps_selectQuery.toString());
					    				        try(ResultSet rs1=ps_selectQuery.executeQuery();){
					    				        	while(rs1.next())
					    				        	{
					    				        		doc_id = rs1.getString("doc_id");
					    				        	}
					    				        }
				    				        	//ps.close();
				    				        	//rs1.close();
				    							
				    							//Save Doc Type Details
				    				        	FormDataBodyPart field=null;
				    							InputStream uploadedInputStream=null;
				    							FormDataContentDisposition fileDetial=null;
				    							String filePath="";
				    							try{
				    				    			field = formParams.getField(proofType.toLowerCase());        //get the file details with key type values
			    				    				System.out.println(proofType+" ::::::::::::::::::::::::::::key");
				    				    			uploadedInputStream = field.getEntityAs(InputStream.class);
				    				    			fileDetial = field.getFormDataContentDisposition();
				    					        	String fileName = fileDetial.getFileName();
				    					        	String[] fileType=fileName.split("\\.");
				    					        	String customFileName=proofType.replaceAll(" ","")+"."+fileType[1];
				    					        	
				    					        	File docDirectory = new File(InitConfigProp.getProperty("documentpath") + File.separatorChar + "Repository" + File.separatorChar + userId + File.separatorChar + "Documents");
				    					        	String fileLocation = ""; 
				    					        	fileLocation = docDirectory + File.separator + customFileName;
				    					        	System.out.println("Location :: " + fileLocation+ " file Name :"+fileName);
				    					        	
				    					        	filePath=smu.saveOnDisk(uploadedInputStream, customFileName, userId,docDirectory,fileLocation);			    							
				    							}
				    							catch(Exception e){
				    								e.printStackTrace();
				    								hashMap.put("resultCode", -4);
				    								conn.rollback();
				    								return hashMap;
				    							}
			    					        	//Insert Doc Type info in user profile table
			    					        	JSONObject value = null;
			    					        	if(!docType[i].equalsIgnoreCase("userbankpassbookValue") && !"guardiancertificate".equalsIgnoreCase(proofType))
			    					        		value = jsonData.getJSONObject(proofType.toLowerCase());
					    							try(PreparedStatement ps_insertDocRep = conn.prepareStatement(insertDocRep);)
					    							{
					    									ps_insertDocRep.setString(1, doc_id);
					    									ps_insertDocRep.setString(2, filePath);
					    									ps_insertDocRep.setString(3, userId);
					    									if(docType[i].equalsIgnoreCase("userbankpassbookValue"))
					    										ps_insertDocRep.setString(4, bankAcc);
					    									else if("guardiancertificate".equalsIgnoreCase(proofType))
					    										ps_insertDocRep.setString(4, "");
					    									else
					    										ps_insertDocRep.setString(4, value.getString("cardNum"));
							    			//	    		ps.setString(4, value.getString("cardNum"));
							    			//	    		ps.setString(5, setDate);
							    			//	    		ps.setString(6, value.getString("reg_authority"));
							    				    		int check = ps_insertDocRep.executeUpdate();
							    				    		if(check>0) {
							    				    			System.out.println("insertion in doc repositry successfuly in non aadhaar registration");
							    				    			hashMap.put("resultCode", 0);
							    				    			flag="success";
							    				    		}
							    				    		else{
							    				    			hashMap.put("resultCode", -1);
							    				    			flag="error";
							    				    			conn.rollback();
							    				    		}
					    							}
				    							
			    					        	if("porValue".equalsIgnoreCase(docType[i]))
			    					        	{
			    					        		docTypeInsert="por_doc";
			    					        	}
			    					        	else if("poiValue".equalsIgnoreCase(docType[i]))
			    					        	{
			    					        		docTypeInsert="poi_doc";
			    					        	}
			    					        	else if("poaValue".equalsIgnoreCase(docType[i]))
			    					        	{
			    					        		docTypeInsert="poa_doc";
			    					        	}
		
			    					        	else if("dobValue".equalsIgnoreCase(docType[i]))
			    					        	{
			    					        		docTypeInsert="dob_doc";
			    					        	}
			    					        	else
			    					        	{
			    					        		docTypeInsert="bankpassbook_doc";
			    					        	}
			    					        	
			    					        	String updateProfileDoc = "update dbt_userprofile_dtl set "+docTypeInsert+" =?  where user_id=?";
			    								try(PreparedStatement ps_updateProfileDoc = conn.prepareStatement(updateProfileDoc);){
			    									ps_updateProfileDoc.setString(1, doc_id);
			    									ps_updateProfileDoc.setString(2, userId);
			    									int output=ps_updateProfileDoc.executeUpdate();
				    								if(output>0)
				    								{
				    									System.out.println("successfully updated column in userprofile table");
				    									hashMap.put("resultCode", 0);
				    									flag="success";
				    								}
			    								}
			    							}
			    						 }
			    					}
			    				}
			    				else
			    				{
			    					System.out.println(" problem in insertion/updation in non aadhaar registration");
			    					hashMap.put("resultCode", -1);
			    					flag="error";
			    					conn.rollback();
			    				}
			    			 }
			    		/* }
	    				else
	    				{
	    					System.out.println("Insert int dbt credential table fail");
	    					hashMap.put("resultCode", -1);
	    					flag="error";
	    				}*/
				}
				else
				{
					System.out.println("Insert into profile table fail");
					hashMap.put("resultCode", -1);
					flag="error";
					conn.rollback();
				}
				if("success".equalsIgnoreCase(flag))
				{
					conn.commit();
				}
			  }
			}
			 catch(  Exception e) {
				 e.printStackTrace();
				 hashMap.put("resultCode", -1);
				 flag="error";
			 }
		if("success".equalsIgnoreCase(flag))
		{
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");	
			Date dateobj = new Date();
			
			 SendMessageUtility smutility = new SendMessageUtility();
			 SendEmailUtility emu = new SendEmailUtility();
			 String messageTest="Successfully Registered UserName  "+userId;
			 String emailMessage=new EmailTemplate().getEmailTextMessage(userId, name, password);
			 String emailSubject = InitConfigProp.getProperty("email_subject");
			 System.out.println("calling message utitlity at time :: " + df.format(dateobj));
			 boolean message=smutility.sendMessage(mobile, messageTest, "1", "1", "Registration", "", "");
			df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");	
			dateobj = new Date();
			 		System.out.println("ending of message utility and calling email utitlity at time :: " + df.format(dateobj));
			 boolean emailStatus = emu.sendMessage("1", InitConfigProp.getProperty("dbtDefaultEmail"), email, emailSubject, emailMessage);
			 df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");	
			 dateobj = new Date();		
			 System.out.println("ending of email utitlity at time :: " + df.format(dateobj));

		}
		return hashMap; 
	}

    private byte[] getEncryptedPassword(String password, byte[] salt) {
        try {
            // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
            // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
            String algorithm = "PBKDF2WithHmacSHA1";
            // SHA-1 generates 160 bit hashes, so that's what makes sense here
            int derivedKeyLength = 160;
            // Pick an iteration count that works for you. The NIST recommends at
            // least 1,000 iterations:
            int iterations = 20000;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
            SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
            return f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            //Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
	
	

}
