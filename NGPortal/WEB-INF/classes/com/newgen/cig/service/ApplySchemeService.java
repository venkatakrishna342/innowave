/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ApplySchemeService.java
* Author              : Ankit Bhasin
* Date written
* (DD/MM/YYYY)        : Feb 10, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* June 25, 2017					 Varun Saddi		  Change Application ID creation parameters. 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.JsonObject;
import com.newgen.cig.entity.ApplySchemeEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;
import com.newgen.utility.DateFormatConverter;

public class ApplySchemeService {

	InitialContext Context;
	DataSource ds;
	DatabaseQuery databaseQ = new DatabaseQuery();

	public HashMap applyScheme(ApplySchemeEntity applySchemeEntity_data, HashMap resultMap, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
    	
    	DateFormatConverter dateConverter=new DateFormatConverter();
    	
    	String applicationID = null;
    	int currentAcademicYear = 0;
    	String status = "";
    	String formatedFatherDob = "";
    	String formatedMotherDob = "";
    	String formatedCourseAdmissionDate = "";
    	String passingBoard = "";
    	String formatedCasteCertificateDate = "";
    	String formatedCasteValidityCertificateDate = "";
    	String formatedSchoolAdmissionDate = "";
    	String formatedLastSchoolAdmissionDate = "";
    	
    	String userID = "";
    	String schemeID = "";
    	String schemeType = "";
    	String dobYear1 = "";
    	String dobYear2 = "";
    	String dobYear3 = "";
    	String firstName = "";
    	String middleName = ""; 
    	String lastName = "";		
    	
    	String tempApp_ID = "";
    	String formatedBarcodeCasteCertificateDate = "";
    	
    	String formatedDomicileDate = "";
    	String formatedIncomeDate_barcode = "";
    	String formatedIncomeDate = "";
    	String formatedDisabilityDate_barcode = "";
    	String formatedDisabilityDate = "";
    	String appRequestType = "Processing";
    	String actionType = "D";
    	
    	int count = 0;
		ResultSet rs = null;
		String token=null;
    	int app_count = -1;
    	
    	//change on 13th July 2017
    	String formatedBPLDate = "";
    	
    	// updated by ankit @6 june
		if (httpRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
            	resultMap.put("resultCode", -20);
                resultMap.put("resultDetails", "Invalid input");
                return resultMap;
            }
        } 
		else {
	        		
        	resultMap.put("resultCode", -20);
            resultMap.put("resultDetails", "Invalid input");
            return resultMap;
        }
		
    	if (applySchemeEntity_data == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid input");
            httpResponse.setHeader("token", token);
            return resultMap;
        }
    	
    		
    	try{
    		
    		//Initialize Datasource Object
    		Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
        	try (Connection conn = ds.getConnection();) {
            	conn.setAutoCommit(false);
        	
            	userID = applySchemeEntity_data.getUserID();
            	schemeID = applySchemeEntity_data.getSchemeID();
            	schemeType = applySchemeEntity_data.getSchemeType();
            	
            	//System.out.println("ID: " + userID + " SID:" + schemeID + " ST:"+schemeType);
        		//System.out.println("app ID: " + applicationID);
        		
	    		//Check its new request or update request for applicaiton
            	actionType = applySchemeEntity_data.getSaveType();
	    		if(actionType.equalsIgnoreCase("D")) {
	    			
	    			if(!"".equalsIgnoreCase(applySchemeEntity_data.getAppID())
		    				&& !"null".equalsIgnoreCase(applySchemeEntity_data.getAppID())
		    				&& applySchemeEntity_data.getAppID() != null) {
	    				
	    				applicationID = applySchemeEntity_data.getAppID();
		    			appRequestType = "Processing";
		    			actionType = "D";
		    			System.out.println("app ID if not null: " + applicationID);
	    			}
	    			else {
	    				/*Generate New Application*/
	            		applicationID = getNewApplicationId(schemeType, applySchemeEntity_data.getApplicantFullName(),
	            				schemeID, applySchemeEntity_data.getUserIndex());
	            		appRequestType = "Processing";
	            		actionType = "D";
	            		System.out.println("app ID after generation: " + applicationID);
	    			}
	    		}
	    		else if(actionType.equalsIgnoreCase("N")){
	    			applicationID = applySchemeEntity_data.getAppID();
	    			appRequestType = "Processing";
	    			actionType = "N";
	    		}
	    		else if(actionType.equalsIgnoreCase("M")){
	    			applicationID = applySchemeEntity_data.getAppID();
	    			appRequestType = "Modified";
	    			actionType = "M";
	    		}
            	
	    		System.out.println("actionType >>>"+actionType);
	    		//Set Temp Id for saving in drafts
	    		tempApp_ID = applicationID;

            	if("".equalsIgnoreCase(applicationID) || "null".equalsIgnoreCase(applicationID) || applicationID == null) {
                	
            		resultMap.put("resultCode", -3);
            		resultMap.put("resultDetails", "Application ID is found null !!");
            		httpResponse.setHeader("token", token);
            		return resultMap;
            	}
            	
            	String set_formFields = databaseQ.getSetFormDetails();
            	try(CallableStatement callableStatement = conn.prepareCall(set_formFields);){
                	callableStatement.setString(1,applicationID);
                	callableStatement.setString(2,applySchemeEntity_data.getApplicantDomicile());
                	callableStatement.setString(3,applySchemeEntity_data.getApplicantFullName());
                	String aadhaarUID = applySchemeEntity_data.getApplicantAadhaarUID();
                	callableStatement.setString(4,aadhaarUID);
                	callableStatement.setString(5,applySchemeEntity_data.getApplicantMobileNo());
                	String emailID = applySchemeEntity_data.getApplicantEmailID();
                	if("".equalsIgnoreCase(emailID) || "null".equalsIgnoreCase(emailID)){
                		emailID = null;
                	}
                	callableStatement.setString(6,emailID);
                	String aplicantDob=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantDOB(),
                			"dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(7,aplicantDob);
                	callableStatement.setString(8,applySchemeEntity_data.getApplicantGender());
                	callableStatement.setString(9,applySchemeEntity_data.getBankSeededAadhaar());
                	callableStatement.setString(10,applySchemeEntity_data.getApplicantAddress());
                	callableStatement.setString(11,applySchemeEntity_data.getApplicantState());
                	
                	String tempDistrict = applySchemeEntity_data.getApplicantDistrict();
                	callableStatement.setString(12,tempDistrict);
                	String tempTaluka = applySchemeEntity_data.getApplicantTaluka();
                	callableStatement.setString(13,tempTaluka);
                	callableStatement.setString(14,applySchemeEntity_data.getApplicantCity());
                	callableStatement.setInt(15,applySchemeEntity_data.getApplicantPinCode());
                	callableStatement.setString(16,applySchemeEntity_data.getApplicantAddress_correspondence());
                	callableStatement.setString(17,applySchemeEntity_data.getApplicantState_correspondence());
                	callableStatement.setString(18,applySchemeEntity_data.getApplicantDistrict_correspondence());
                	callableStatement.setString(19,applySchemeEntity_data.getApplicantTaluka_correspondence());
                	callableStatement.setString(20,applySchemeEntity_data.getApplicantCity_correspondence());
                	callableStatement.setInt(21,applySchemeEntity_data.getApplicantPinCode_correspondence());
	                	
                	String tempCasteCategory = applySchemeEntity_data.getApplicantCasteCategory();
                	callableStatement.setString(22,tempCasteCategory);
                	callableStatement.setString(23,applySchemeEntity_data.getApplicantCaste());
                	callableStatement.setString(24,applySchemeEntity_data.getApplicantSubCaste());
                	callableStatement.setString(25,applySchemeEntity_data.getApplicantIsSalaried());
                	callableStatement.setString(26,applySchemeEntity_data.getApplicantJobType());
                	
                	System.out.println("Income :: " + applySchemeEntity_data.getApplicantIncome());
                	if("".equalsIgnoreCase(applySchemeEntity_data.getApplicantIncome())
                			|| "null".equalsIgnoreCase(applySchemeEntity_data.getApplicantIncome())){
                		callableStatement.setString(27,null);
                	}else{
                		callableStatement.setString(27,applySchemeEntity_data.getApplicantIncome());
                	}
                	callableStatement.setString(28,applySchemeEntity_data.getApplicantPanNo());
                	callableStatement.setString(29,applySchemeEntity_data.getFatherName());
                	
                	formatedFatherDob=dateConverter.dateFormatConverter(applySchemeEntity_data.getFatherDOB(),
                			"dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(30,formatedFatherDob);
                	callableStatement.setString(31,applySchemeEntity_data.getFatherOccupation());
                	callableStatement.setString(32,applySchemeEntity_data.getFatherIncomeCertificateNo());
                	callableStatement.setString(33,applySchemeEntity_data.getFatherPanNo());
                	callableStatement.setString(34,applySchemeEntity_data.getMotherName());
                	
                	formatedMotherDob=dateConverter.dateFormatConverter(applySchemeEntity_data.getMotherDOB(),
                			"dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(35,formatedMotherDob);
                	callableStatement.setString(36,applySchemeEntity_data.getMotherOccupation());
                	callableStatement.setString(37,applySchemeEntity_data.getMotherIncomeCertificateNo());
                	callableStatement.setString(38,applySchemeEntity_data.getMotherPanNo());
	                	
                	System.out.println("FamilyIncome :: " + applySchemeEntity_data.getFamilyIncome());
                	if("".equalsIgnoreCase(applySchemeEntity_data.getFamilyIncome())
                			|| "null".equalsIgnoreCase(applySchemeEntity_data.getFamilyIncome()) || applySchemeEntity_data.getFamilyIncome() == null){
                		callableStatement.setString(39,null);
                	}else{
                		callableStatement.setString(39,applySchemeEntity_data.getFamilyIncome());
                	}
                	callableStatement.setString(40,applySchemeEntity_data.getApplicantIsPhysicallyHandicapped());
                	System.out.println("Disablity :: " + applySchemeEntity_data.getApplicantDisabilityType());
                	callableStatement.setString(41,applySchemeEntity_data.getApplicantDisabilityType());
                	/*if(schemeID.equalsIgnoreCase("4") || schemeID.equalsIgnoreCase("13") || schemeID.equalsIgnoreCase("21")){
                		callableStatement.setString(42,applySchemeEntity_data.getApplicantDisabilityPercentageOther());
                	}else{*/
                	/*}*/
                	
                	//change on 18th July 2017
                	System.out.println("Disability Percent :: " + applySchemeEntity_data.getApplicantDisabilityPercentage());
                	if(!"".equalsIgnoreCase(applySchemeEntity_data.getApplicantDisabilityPercentage()) && 
                			!"null".equalsIgnoreCase(applySchemeEntity_data.getApplicantDisabilityPercentage()) && applySchemeEntity_data.getApplicantDisabilityPercentage() != null){
                		callableStatement.setString(42,applySchemeEntity_data.getApplicantDisabilityPercentage());
                	}else{
                		callableStatement.setString(42,null);
                	}
                	
                	callableStatement.setInt(43,applySchemeEntity_data.getApplicantDomicileCertificateYear());
                	callableStatement.setString(44,applySchemeEntity_data.getApplicantDomicileCertificateNo());
                	callableStatement.setString(45,userID);
                	callableStatement.setString(46,schemeID);
                	callableStatement.setString(47,appRequestType);
                	callableStatement.setString(48,applySchemeEntity_data.getAddressIsPermanent());
                	callableStatement.setString(49,applySchemeEntity_data.getApplicantFatherAlive());
                	callableStatement.setString(50,applySchemeEntity_data.getApplicantMotherAlive());
                	callableStatement.setString(51,applySchemeEntity_data.getApplicantFatherSalaried());
                	callableStatement.setString(52,applySchemeEntity_data.getApplicantMotherSalaried());
                	callableStatement.setString(53,applySchemeEntity_data.getApplicantChildNo());
                	callableStatement.setInt(54,applySchemeEntity_data.getFatherIncome());
                	callableStatement.setInt(55,applySchemeEntity_data.getMotherIncome());
                	callableStatement.setInt(56,applySchemeEntity_data.getGuardianIncome());
                	callableStatement.setString(57,applySchemeEntity_data.getGaurdianName());
                	callableStatement.setString(58,applySchemeEntity_data.getApplicantGuardinSalaried());
                	callableStatement.setString(59,applySchemeEntity_data.getGuardianOccupation());
                	callableStatement.setString(60,applySchemeEntity_data.getGuardianPanNo());
                	callableStatement.setString(61,applySchemeEntity_data.getIsOtherScholarshipApplied());
                	callableStatement.setString(62,applySchemeEntity_data.getOtherScholarshipName());
                	callableStatement.setString(63,actionType);
                	callableStatement.setString(64,applySchemeEntity_data.getApplicantRenewable());
                	callableStatement.setString(65,applySchemeEntity_data.getOldApplicationID());
                	
                	callableStatement.setString(66,applySchemeEntity_data.getApplicantDisableType());//change ankit
                	callableStatement.setInt(67,applySchemeEntity_data.getApplicantDisabilityYear());
                	callableStatement.setString(68,applySchemeEntity_data.getGaurdianAddress());
            
                	/*changed on 14june*/
                	callableStatement.setString(69,applySchemeEntity_data.getApplicantHaveDomicileBarCode());
                	callableStatement.setString(70,applySchemeEntity_data.getApplicantDomicileCertificateBarCode());
                	callableStatement.setString(71,applySchemeEntity_data.getApplicantDomicileBarcodeName());
                	
                	formatedDomicileDate =
                			dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantDomicileCertificateDate_barcode(),
                					"dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(72,formatedDomicileDate);
                	callableStatement.setString(73,applySchemeEntity_data.getApplicantDomicileCertificateAuthority_barcode());
                	callableStatement.setInt(74,applySchemeEntity_data.getApplicantAge());
                	
                	callableStatement.setString(75,applySchemeEntity_data.getApplicantHaveIncomeBarCode());
                	callableStatement.setString(76,applySchemeEntity_data.getApplicantIncomeCertificateBarCode());
                	callableStatement.setString(77,applySchemeEntity_data.getApplicantIncomeBarcodeName());
                	
                	/*formatedIncomeDate_barcode=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantIncomeCertificateDate_barcode(), "dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(78,formatedIncomeDate_barcode);
                	callableStatement.setString(79,applySchemeEntity_data.getApplicantIncomeCertificateAuthority_barcode());*/
                	
                	callableStatement.setString(78,applySchemeEntity_data.getApplicantIncomeCertificateNo());
                	
                	formatedIncomeDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantIncomeCertificateDate(),
                			"dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(79,formatedIncomeDate);
                	callableStatement.setString(80,applySchemeEntity_data.getApplicantIncomeCertificateAuthority());
                	callableStatement.setString(81,applySchemeEntity_data.getReaderOpted());
                	
                	callableStatement.setString(82,applySchemeEntity_data.getApplicantHaveDisabilityBarCode());
                	callableStatement.setString(83,applySchemeEntity_data.getApplicantDisabilityCertificateBarCode());
                	callableStatement.setString(84,applySchemeEntity_data.getApplicantDisabilityBarcodeName());
                	
                	/*formatedDisabilityDate_barcode=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantDisabilityCertificateDate_barcode(), "dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(87,formatedDisabilityDate_barcode);
                	callableStatement.setString(88,applySchemeEntity_data.getApplicantDisabilityCertificateAuthority_barcode());*/
                	callableStatement.setString(85,applySchemeEntity_data.getApplicantDisabilityCertificateNo());
                	callableStatement.setString(86,applySchemeEntity_data.getApplicantDisabilityCertificateAuthority());
                	
                	formatedDisabilityDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantDisabilityCertificateDate(),
                			"dd/mm/yyyy", "yyyy-mm-dd");
                	callableStatement.setString(87,formatedDisabilityDate);
                	
                	//change on 11th July
                	callableStatement.setString(88,applySchemeEntity_data.getApplicantHaveIncomeCertificate());
                	callableStatement.setString(89,applySchemeEntity_data.getApplicantHaveDisabilityCertificate());
                	callableStatement.setString(90,applySchemeEntity_data.getApplicantHaveCertificateDomicile());
                	callableStatement.setString(91,applySchemeEntity_data.getApplicantDomicileState());
                	
                	
                	callableStatement.executeUpdate();
                	//conn.commit();
                	status = callableStatement.getString(92);
                	System.out.println("Status in Setting Form Details:: " + status);
            	}
                    	
            	if(!"".equalsIgnoreCase(status) && !"null".equalsIgnoreCase(status)
            			&& status != null && status.equalsIgnoreCase("success")) {
            		
            		if(!"".equalsIgnoreCase(schemeType) && !"null".equalsIgnoreCase(schemeType) && schemeType != null && 
            				("PostMatric".equalsIgnoreCase(schemeType) || "postmatric".equalsIgnoreCase(schemeType))) {
            			String set_postMatricFormFields = databaseQ.getSetPostMatricFormDetails();
            			try(CallableStatement callableStatement = conn.prepareCall(set_postMatricFormFields);) {
	                    	
	                    	callableStatement.setString(1,applicationID);
	                    	callableStatement.setString(2,applySchemeEntity_data.getCourseType());
	                    	callableStatement.setInt(3,applySchemeEntity_data.getJoiningYear());
	                    	callableStatement.setString(4,applySchemeEntity_data.getCurrentCourseCollegeRegisterNo());
	                    	callableStatement.setString(5,applySchemeEntity_data.getCurrentCourseState());
	                    	callableStatement.setString(6,applySchemeEntity_data.getCurrentCourseDistrict());
	                    	callableStatement.setString(7,applySchemeEntity_data.getCurrentCourseTaluka());
	                    	callableStatement.setString(8,applySchemeEntity_data.getCurrentCourseCollegeName());
	                    	callableStatement.setString(9,applySchemeEntity_data.getCurrentCourseCollegeType());
	                    	callableStatement.setString(10,applySchemeEntity_data.getCurrentCourseCourseName());
	                    	callableStatement.setString(11,applySchemeEntity_data.getCurrentCourseUniversityName());
	                    	
	                    	callableStatement.setInt(12,applySchemeEntity_data.getCurrentCourseYear());
	                    	callableStatement.setString(13,applySchemeEntity_data.getCurrentCourseGrantType());
	                    	callableStatement.setString(14,applySchemeEntity_data.getCurrentCourseCap());
	                    	callableStatement.setString(15,applySchemeEntity_data.getCurrentCourseCollegeAdmissionNo());
	                    	formatedCourseAdmissionDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getCurrentCourseAdmissionDate(),
	                    			"dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement.setString(16,formatedCourseAdmissionDate);
	                    	
	                    	passingBoard = applySchemeEntity_data.getApplicant10thPassingBoard();
	                    	callableStatement.setString(17,passingBoard);
	                    	callableStatement.setString(18,applySchemeEntity_data.getApplicantSSCDivision());
	                    	callableStatement.setInt(19,applySchemeEntity_data.getApplicant10thPassingYear());
	                    	callableStatement.setString(20,applySchemeEntity_data.getApplicant10thPassingMonth());
	                    	callableStatement.setString(21,applySchemeEntity_data.getApplicant10thRollNo());
	                    	System.out.println("1oth Marks:: " + applySchemeEntity_data.getApplicant10thMarksObtained());
	                    	if("".equalsIgnoreCase(applySchemeEntity_data.getApplicant10thMarksObtained())
	                    			|| "null".equalsIgnoreCase(applySchemeEntity_data.getApplicant10thMarksObtained())){
	                    		callableStatement.setString(22,null);
	                    	}else{
	                    		callableStatement.setString(22,applySchemeEntity_data.getApplicant10thMarksObtained());
	                    	}
	                    	callableStatement.setString(23,applySchemeEntity_data.getApplicant10thcertificateNumber());
	                    	
	                    	passingBoard = applySchemeEntity_data.getApplicant11thPassingBoard();
	                    	callableStatement.setString(24,passingBoard);
	                    	callableStatement.setString(25,applySchemeEntity_data.getApplicant11thDivision());
	                    	callableStatement.setInt(26,applySchemeEntity_data.getApplicant11thPassingYear());                    
	                    	callableStatement.setString(27,applySchemeEntity_data.getApplicant11thPassingMonth());
	                    	callableStatement.setString(28,applySchemeEntity_data.getApplicant11thRollNo());
	                    	System.out.println("11th Marks:: " + applySchemeEntity_data.getApplicant11thMarksObtained());
	                    	if("".equalsIgnoreCase(applySchemeEntity_data.getApplicant11thMarksObtained())
	                    			|| "null".equalsIgnoreCase(applySchemeEntity_data.getApplicant11thMarksObtained())){
	                    		callableStatement.setString(29,null);
	                    	}else{
	                    		callableStatement.setString(29,applySchemeEntity_data.getApplicant11thMarksObtained());
	                    	}
	                    	callableStatement.setString(30,applySchemeEntity_data.getApplicant11thcertificateNumber());
		                    	
	                    	passingBoard = applySchemeEntity_data.getApplicant12thPassingBoard();
	                    	callableStatement.setString(31,passingBoard);
	                    	callableStatement.setString(32,applySchemeEntity_data.getApplicantHSCDivision());
	                    	callableStatement.setInt(33,applySchemeEntity_data.getApplicant12thPassingYear());
	                    	callableStatement.setString(34,applySchemeEntity_data.getApplicant12thPassingMonth());
	                    	callableStatement.setString(35,applySchemeEntity_data.getApplicant12thRollNo());
	                    	System.out.println("12th Marks:: " + applySchemeEntity_data.getApplicant12thMarksObtained());
	                    	if("".equalsIgnoreCase(applySchemeEntity_data.getApplicant12thMarksObtained())
	                    			|| "null".equalsIgnoreCase(applySchemeEntity_data.getApplicant12thMarksObtained())){
	                    		callableStatement.setString(36,null);
	                    	}else{
	                    		callableStatement.setString(36,applySchemeEntity_data.getApplicant12thMarksObtained());
	                    	}
	                    	callableStatement.setString(37,applySchemeEntity_data.getApplicant12thcertificateNumber());
	                    	
	                    	callableStatement.setInt(38,applySchemeEntity_data.getDiplomaAcademicYearPassed());
	                    	callableStatement.setString(39,applySchemeEntity_data.getDiplomaCollegeName());
	                    	callableStatement.setString(40,applySchemeEntity_data.getDiplomaCourseName());
	                    	callableStatement.setString(41,applySchemeEntity_data.getDiplomaUniversityName());
	                    	callableStatement.setString(42,applySchemeEntity_data.getDiplomaYear());
	                    	callableStatement.setString(43,applySchemeEntity_data.getDiplomaResult());
	                    	callableStatement.setString(44,applySchemeEntity_data.getDiplomaMarksObtained());
	                    	
	                    	callableStatement.setInt(45,applySchemeEntity_data.getSdiplomaAcademicYearPassed());
	                    	callableStatement.setString(46,applySchemeEntity_data.getSdiplomaCollegeName());
	                    	callableStatement.setString(47,applySchemeEntity_data.getSdiplomaCourseName());
	                    	callableStatement.setString(48,applySchemeEntity_data.getSdiplomaUniversityName());
	                    	callableStatement.setString(49,applySchemeEntity_data.getSdiplomaYear());
	                    	callableStatement.setString(50,applySchemeEntity_data.getSdiplomaResult());
	                    	callableStatement.setString(51,applySchemeEntity_data.getSdiplomaMarksObtained());
	                    	
	                    	
	                    	callableStatement.setInt(52,applySchemeEntity_data.getTdiplomaAcademicYearPassed());
	                    	callableStatement.setString(53,applySchemeEntity_data.getTdiplomaCollegeName());
	                    	callableStatement.setString(54,applySchemeEntity_data.getTdiplomaCourseName());
	                    	callableStatement.setString(55,applySchemeEntity_data.getTdiplomaUniversityName());
	                    	callableStatement.setString(56,applySchemeEntity_data.getTdiplomaYear());
	                    	callableStatement.setString(57,applySchemeEntity_data.getTdiplomaResult());
	                    	callableStatement.setString(58,applySchemeEntity_data.getTdiplomaMarksObtained());
	                    	
	                    	callableStatement.setInt(59,applySchemeEntity_data.getfYearAcademicYearPassed());
	                    	callableStatement.setString(60,applySchemeEntity_data.getfYearCollegeName());
	                    	callableStatement.setString(61,applySchemeEntity_data.getfYearCourseName());
	                    	callableStatement.setString(62,applySchemeEntity_data.getfYearUniversityName());
	                    	callableStatement.setString(63,applySchemeEntity_data.getfYearYear());
	                    	callableStatement.setString(64,applySchemeEntity_data.getfYearResult());
	                    	callableStatement.setString(65,applySchemeEntity_data.getfYearMarksObtained());
	                    	
	                    	callableStatement.setInt(66,applySchemeEntity_data.getsYearAcademicYearPassed());
	                    	callableStatement.setString(67,applySchemeEntity_data.getsYearCollegeName());
	                    	callableStatement.setString(68,applySchemeEntity_data.getsYearCourseName());
	                    	callableStatement.setString(69,applySchemeEntity_data.getsYearUniversityName());
	                    	callableStatement.setString(70,applySchemeEntity_data.getsYearYear());
	                    	callableStatement.setString(71,applySchemeEntity_data.getsYearResult());
	                    	callableStatement.setString(72,applySchemeEntity_data.getsYearMarksObtained());
	                    	
	                    	callableStatement.setInt(73,applySchemeEntity_data.gettYearAcademicYearPassed());
	                    	callableStatement.setString(74,applySchemeEntity_data.gettYearCollegeName());
	                    	callableStatement.setString(75,applySchemeEntity_data.gettYearCourseName());
	                    	callableStatement.setString(76,applySchemeEntity_data.gettYearUniversityName());
	                    	callableStatement.setString(77,applySchemeEntity_data.gettYearYear());
	                    	callableStatement.setString(78,applySchemeEntity_data.gettYearResult());
	                    	callableStatement.setString(79,applySchemeEntity_data.gettYearMarksObtained());
	                    	
	                    	callableStatement.setInt(80,applySchemeEntity_data.getgYearAcademicYearPassed());
	                    	callableStatement.setString(81,applySchemeEntity_data.getgYearCollegeName());
	                    	callableStatement.setString(82,applySchemeEntity_data.getgYearCourseName());
	                    	callableStatement.setString(83,applySchemeEntity_data.getgYearUniversityName());
	                    	callableStatement.setString(84,applySchemeEntity_data.getgYearYear());
	                    	callableStatement.setString(85,applySchemeEntity_data.getgYearResult());
	                    	callableStatement.setString(86,applySchemeEntity_data.getgYearMarksObtained());
	                    	
	                    	callableStatement.setInt(87,applySchemeEntity_data.getPgYearAcademicYearPassed());
	                    	callableStatement.setString(88,applySchemeEntity_data.getPgYearCollegeName());
	                    	callableStatement.setString(89,applySchemeEntity_data.getPgYearCourseName());
	                    	callableStatement.setString(90,applySchemeEntity_data.getPgYearUniversityName());
	                    	callableStatement.setString(91,applySchemeEntity_data.getPgYearYear());
	                    	callableStatement.setString(92,applySchemeEntity_data.getPgYearResult());
	                    	callableStatement.setString(93,applySchemeEntity_data.getPgYearMarksObtained());
	                    	
	                    	callableStatement.setString(94,applySchemeEntity_data.getApplicantCasteCertificateNo());
	                    	formatedCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate(),
	                    			"dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement.setString(95,formatedCasteCertificateDate);
	                    	callableStatement.setInt(96,applySchemeEntity_data.getApplicantCasteCertificateYear());
	                    	callableStatement.setString(97,applySchemeEntity_data.getApplicantCasteDistrict());
	                    	callableStatement.setString(98,applySchemeEntity_data.getApplicantCasteTaluka());
	                    	callableStatement.setString(99,applySchemeEntity_data.getApplicantCasteCertificateSubdivision());
	                    	callableStatement.setString(100,applySchemeEntity_data.getApplicantCasteCertificateAuthority());
	                    	callableStatement.setString(101,applySchemeEntity_data.getApplicantCasteValidityNo());
	                    	
	                    	formatedCasteValidityCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteValidityCertificateDate(),
	                    			"dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement.setString(102,formatedCasteValidityCertificateDate);
	                    	callableStatement.setInt(103,applySchemeEntity_data.getApplicantCasteValidityCertificateYear());
	                    	callableStatement.setString(104,applySchemeEntity_data.getCasteValidityDistrict());
	                    	callableStatement.setString(105,applySchemeEntity_data.getApplicantCasteValidityTaluka());
	                    	callableStatement.setString(106,applySchemeEntity_data.getApplicantDayScholarDetails());
	                    	callableStatement.setString(107,applySchemeEntity_data.getApplicantHostelDistrict());
	                    	callableStatement.setString(108,applySchemeEntity_data.getApplicantHostelTaluka());
	                    	callableStatement.setString(109,applySchemeEntity_data.getHostelType());
	                    	callableStatement.setString(110,applySchemeEntity_data.getApplicantHostelName());
	                    	callableStatement.setString(111,applySchemeEntity_data.getHostelAided());
	                    	callableStatement.setString(112,applySchemeEntity_data.getApplicantHostelAddress());
	                    	callableStatement.setString(113,applySchemeEntity_data.getIsdiplomaPassed());
	                    	callableStatement.setString(114,applySchemeEntity_data.getApplicantReligion());
	                    	callableStatement.setString(115,applySchemeEntity_data.getExamMonth());
	                    	
	                    	callableStatement.setFloat(116,applySchemeEntity_data.getCurrentCourseExamFees());
	                    	callableStatement.setFloat(117,applySchemeEntity_data.getCurrentCourseTutionFees());
	                    	callableStatement.setFloat(118,applySchemeEntity_data.getCurrentCourseOtherFees());
	                    	
	                    	callableStatement.setString(119,applySchemeEntity_data.getApplicantSSCBoard1());
	                    	callableStatement.setString(120,applySchemeEntity_data.getApplicant11thBoard1());
	                    	callableStatement.setString(121,applySchemeEntity_data.getApplicantHSCBoard1());
	                    	
	                    	callableStatement.setString(122,applySchemeEntity_data.getTdiplomaState());
	                    	callableStatement.setString(123,applySchemeEntity_data.getTdiplomaDistrict());
	                    	callableStatement.setString(124,applySchemeEntity_data.getTdiplomaTaluka());
	                    	callableStatement.setString(125,actionType);
	                    	
	                    	callableStatement.setInt(126,applySchemeEntity_data.getApplicant10thTotalMarksObtained());//change ankit
	                    	callableStatement.setInt(127,applySchemeEntity_data.getApplicant10thTotalMarks());
	                    	callableStatement.setInt(128,applySchemeEntity_data.getApplicant12thTotalMarksObtained());
	                    	callableStatement.setInt(129,applySchemeEntity_data.getApplicant12thTotalMarks());
	                    	callableStatement.setString(130,applySchemeEntity_data.getCapApplicationID());
	                    	
	                    	callableStatement.setString(131,applySchemeEntity_data.getApplicantHaveCasteBarCode()); //9th June
	                    	callableStatement.setString(132,applySchemeEntity_data.getApplicantCasteCertificateBarCode());
	                    	callableStatement.setString(133,applySchemeEntity_data.getApplicantBarcodeName());
	                    	
	                    	/*formatedBarcodeCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate_barcode(), "dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement.setString(134,formatedBarcodeCasteCertificateDate);
	                    	callableStatement.setString(135,applySchemeEntity_data.getApplicantCasteCertificateAuthority_barcode());*/ //19june
	                    	
	                    	//14 june
	                    	callableStatement.setInt(134,applySchemeEntity_data.getApplicant11thTotalMarksObtained());
	                    	callableStatement.setInt(135,applySchemeEntity_data.getApplicant11thTotalMarks());
	                    	callableStatement.setString(136,applySchemeEntity_data.getApplicant11thGap());
	                    	callableStatement.setString(137,applySchemeEntity_data.getApplicant12thStream());
	                    	callableStatement.setFloat(138,applySchemeEntity_data.getApplicant12thScienceMarksObtained());
	                    	callableStatement.setFloat(139,applySchemeEntity_data.getApplicant12thMathsMarksObtained());
	                    	callableStatement.setFloat(140,applySchemeEntity_data.getApplicant12thPhysicsMarksObtained());
	                    	callableStatement.setString(141,applySchemeEntity_data.getApplicant12thGap());
	                    	callableStatement.setString(142,applySchemeEntity_data.getApplicanttdiplomaGap());
	                    	callableStatement.setString(143,applySchemeEntity_data.getApplicantdiplomaGap());
	                    	callableStatement.setString(144,applySchemeEntity_data.getSdiplomaGap());
	                    	callableStatement.setString(145,applySchemeEntity_data.getApplicant1styearGap());
	                    	callableStatement.setString(146,applySchemeEntity_data.getApplicant2ndyearGap());
	                    	callableStatement.setString(147,applySchemeEntity_data.getApplicant3rdyearGap());
	                    	callableStatement.setString(148,applySchemeEntity_data.getApplicantGyearGap());
	                    	callableStatement.setString(149,applySchemeEntity_data.getApplicantPGyearGap());
		                    	
	                    	//change by ankit 17th june
	                    	callableStatement.setString(150,applySchemeEntity_data.getApplicantDHERecommended());
	                    	callableStatement.setString(151,applySchemeEntity_data.getApplicantJNUStudent());
	                    	
	                    	//change on 25th june
	                    	callableStatement.setString(152,applySchemeEntity_data.getApplicant10thResult());
	                    	callableStatement.setString(153,applySchemeEntity_data.getApplicant11thResult());
	                    	callableStatement.setString(154,applySchemeEntity_data.getApplicant12thResult());
	                    	callableStatement.setString(155,applySchemeEntity_data.getSscIntegrationFlag());
	                    	callableStatement.setString(156,applySchemeEntity_data.getHscIntegrationFlag());
	                    	callableStatement.setString(157,applySchemeEntity_data.getCasteValidityIntegrationFlag());
	                    	
	                    	//change on 13th July 2017
	                    	callableStatement.setString(158,applySchemeEntity_data.getApplicantHaveCasteCertificate());
	                    	
	                    	//change on 20th July 2017
	                    	callableStatement.setString(159,applySchemeEntity_data.getSscCertificateName());
	                    	
	                    	//change on 25th July 2017
	                    	callableStatement.setString(160,applySchemeEntity_data.getfYearDistrict());
	                    	callableStatement.setString(161,applySchemeEntity_data.getfYearTaluka());
	                    	callableStatement.setString(162,applySchemeEntity_data.getsYearDistrict());
	                    	callableStatement.setString(163,applySchemeEntity_data.getsYearTaluka());
	                    	callableStatement.setString(164,applySchemeEntity_data.gettYearDistrict());
	                    	callableStatement.setString(165,applySchemeEntity_data.gettYearTaluka());
	                    	callableStatement.setString(166,applySchemeEntity_data.getgYearDistrict());
	                    	callableStatement.setString(167,applySchemeEntity_data.getgYearTaluka());
	                    	callableStatement.setString(168,applySchemeEntity_data.getPgYearDistrict());
	                    	callableStatement.setString(169,applySchemeEntity_data.getPgYearTaluka());
	                    	callableStatement.setString(170,applySchemeEntity_data.getDiplomaYearDistrict());
	                    	callableStatement.setString(171,applySchemeEntity_data.getDiplomaYearTaluka());
	                    	callableStatement.setString(172,applySchemeEntity_data.getSdiplomaYearDistrict());
	                    	callableStatement.setString(173,applySchemeEntity_data.getSdiplomaYearTaluka());
	                    	callableStatement.setString(174,applySchemeEntity_data.getIsProfessional());
	                    	
	                    	callableStatement.executeUpdate();
	                    	//conn.commit();
	                    	status = callableStatement.getString(175);
	                    	System.out.println("Status :: " + status);
                    	}
    	                    	
                    	/*if(schemeID.equalsIgnoreCase("16") || schemeID.equalsIgnoreCase("17") || schemeID.equalsIgnoreCase("18") || 
	            			schemeID.equalsIgnoreCase("19") || schemeID.equalsIgnoreCase("20") || schemeID.equalsIgnoreCase("21")){*/
	            		String set_tribalPostMatricFormFields = databaseQ.getSetTribalPostMatricFormDetails();
	                	try(CallableStatement callableStatement = conn.prepareCall(set_tribalPostMatricFormFields);){
	        	
	                    	callableStatement.setString(1,applicationID);
	                    	callableStatement.setString(2,applySchemeEntity_data.getApplicantMaritalStatus());
	                    	callableStatement.setString(3,applySchemeEntity_data.getSpouseName());
	                    	callableStatement.setString(4,applySchemeEntity_data.getSpouseRelation());
	                    	callableStatement.setString(5,applySchemeEntity_data.getSpouseAge());
	                    	callableStatement.setString(6,applySchemeEntity_data.getSpouseProfession());
	                    	callableStatement.setString(7,applySchemeEntity_data.getSpouseAddress());
	                    	callableStatement.setString(8,applySchemeEntity_data.getSpouseState());
	                    	callableStatement.setString(9,applySchemeEntity_data.getSpouseDistrict());
	                    	callableStatement.setString(10,applySchemeEntity_data.getSpouseTaluka());
	                    	callableStatement.setString(11,applySchemeEntity_data.getSpouseCity());
	                    	callableStatement.setString(12,applySchemeEntity_data.getSpousePinCode());
	                    	callableStatement.setString(13,actionType);
	                    	
	                    	//14 june
	                    	callableStatement.setString(14,applySchemeEntity_data.getApplicantSpouseSalaried());
	                    	
	                    	callableStatement.executeUpdate();
	                    	//conn.commit();
	                    	status = callableStatement.getString(15);
	                    	System.out.println("Status :: " + status);
	                    }
        	                    	
    	                    	/*}else*/ 
    	                    	
	                	//change by ankit on 15th July 2017
	                    if(schemeID.equalsIgnoreCase("22") || schemeID.equalsIgnoreCase("23") || 
	                    	schemeID.equalsIgnoreCase("24") || schemeID.equalsIgnoreCase("25") || 
	                    	schemeID.equalsIgnoreCase("26") || schemeID.equalsIgnoreCase("27") || 
	                    	schemeID.equalsIgnoreCase("28") || schemeID.equalsIgnoreCase("29") || 
	                    	schemeID.equalsIgnoreCase("30") || schemeID.equalsIgnoreCase("31") ||
	                    	schemeID.equalsIgnoreCase("32") || schemeID.equalsIgnoreCase("33") || 
	                    	schemeID.equalsIgnoreCase("36") || schemeID.equalsIgnoreCase("37")) { //changed  by ankit    	                    		
	                    		
	                    	String set_HigherAndTechnicalPostMatricFormDetails = databaseQ.getSetHTPostMatricFormDetails();
		                    try(CallableStatement callableStatement4 = conn.prepareCall(set_HigherAndTechnicalPostMatricFormDetails);) {
	        	                    	
		                    	callableStatement4.setString(1,applicationID);
		                    	callableStatement4.setString(2,applySchemeEntity_data.getApplicantMotherTongue());
		                    	callableStatement4.setString(3,applySchemeEntity_data.getApplicant10thSchoolName());
		                    	callableStatement4.setString(4,applySchemeEntity_data.getApplicant10thSchoolDistrict());
		                    	callableStatement4.setString(5,applySchemeEntity_data.getApplicant10thSchoolTaluka());
		                    	callableStatement4.setString(6,applySchemeEntity_data.getApplicantType());
		                    	callableStatement4.setInt(7,applySchemeEntity_data.getApplicantSiblings());
		                    	callableStatement4.setString(8,applySchemeEntity_data.getApplicantFatherExFreedomFighter());
		                    	callableStatement4.setString(9,applySchemeEntity_data.getFatherStruggleName());
		                    	callableStatement4.setString(10,applySchemeEntity_data.getFatherFreedomFighterType());
		                    	callableStatement4.setString(11,applySchemeEntity_data.getApplicantFatherExServiceMen());
		                    	callableStatement4.setString(12,applySchemeEntity_data.getApplicantFatherPosted());
		                    	callableStatement4.setString(13,applySchemeEntity_data.getApplicantFatherServiceType());
		                    	callableStatement4.setString(14,applySchemeEntity_data.getApplicantMotherExFreedomFighter());
		                    	callableStatement4.setString(15,applySchemeEntity_data.getMotherStruggleName());
		                    	callableStatement4.setString(16,applySchemeEntity_data.getMotherFreedomFighterType());
		                    	callableStatement4.setString(17,applySchemeEntity_data.getApplicantMotherExServiceMen());
		                    	callableStatement4.setString(18,applySchemeEntity_data.getApplicantMotherPosted());
		                    	callableStatement4.setString(19,applySchemeEntity_data.getApplicantMotherServiceType());
		                    	callableStatement4.setString(20,applySchemeEntity_data.getApplicantGuardianExFreedomFighter());
		                    	callableStatement4.setString(21,applySchemeEntity_data.getGuardianStruggleName());
		                    	callableStatement4.setString(22,applySchemeEntity_data.getGuardianFreedomFighterType());
		                    	callableStatement4.setString(23,applySchemeEntity_data.getApplicantGuardianExServiceMen());
		                    	callableStatement4.setString(24,applySchemeEntity_data.getApplicantGuardianPosted());
		                    	callableStatement4.setString(25,applySchemeEntity_data.getApplicantGuardianServiceType());
		                    	callableStatement4.setString(26,actionType);
		                    	callableStatement4.setString(27,applySchemeEntity_data.getApplicantHindiSubject()); //change 9th june
		                    	callableStatement4.executeUpdate();
		                    	//conn.commit();
		                    	status = callableStatement4.getString(28);
	    	                    	System.out.println("Status :: " + status);	
		                    }
	                    }else if(schemeID.equalsIgnoreCase("38") || schemeID.equalsIgnoreCase("39") || schemeID.equalsIgnoreCase("40")) {
	    	                    		
	                		String set_SchoolEducationFormDetails = databaseQ.getSetSEFormDetails();
	                		try(CallableStatement callableStatement4 = conn.prepareCall(set_SchoolEducationFormDetails);) {
	        	                    	
		                    	callableStatement4.setString(1,applicationID);
		                    	callableStatement4.setString(2,applySchemeEntity_data.getApplicantExamSeatNo());
		                    	callableStatement4.setFloat(3,applySchemeEntity_data.getApplicant10thSanskritMarks());
		                    	callableStatement4.setString(4,applySchemeEntity_data.getApplicantSet());
		                    	callableStatement4.setString(5,applySchemeEntity_data.getApplicantUDISECode());
		                    	callableStatement4.setString(6,actionType);
		                    	callableStatement4.setString(7,applySchemeEntity_data.getSanskritOpted());//change ankit        	                    	
		                    	callableStatement4.setFloat(8,applySchemeEntity_data.getApplicant10thSanskritMarksPerCent());//9th june
		                    	callableStatement4.setFloat(9,applySchemeEntity_data.getApplicant8thSanskritMarks());
		                    	callableStatement4.setFloat(10,applySchemeEntity_data.getApplicant8thSanskritMarksPerCent());     	
		                    	//14 june
		                    	callableStatement4.setString(11,applySchemeEntity_data.getParikshaExam());
		                    	callableStatement4.setString(12,applySchemeEntity_data.getApplicantlastYearResultEighth());
	        	                    	
		                    	//change by ankit 17 june
		                    	//callableStatement4.setString(13,applySchemeEntity_data.getSanskritOptedPre());
		                    	
		                    	callableStatement4.executeUpdate();
		                    	//conn.commit();
		                    	status = callableStatement4.getString(13);
		                    	System.out.println("Status :: " + status);
	                    	}
	                	}
            		}else if(!"".equalsIgnoreCase(schemeType) && !"null".equalsIgnoreCase(schemeType) && schemeType != null
	            			&& ("PreMatric".equalsIgnoreCase(schemeType) && (schemeID.equalsIgnoreCase("5")
	            					|| schemeID.equalsIgnoreCase("6") || schemeID.equalsIgnoreCase("7")
	            					|| schemeID.equalsIgnoreCase("8") || schemeID.equalsIgnoreCase("9")
	            					|| schemeID.equalsIgnoreCase("12") || schemeID.equalsIgnoreCase("13")
	            					|| schemeID.equalsIgnoreCase("34") || schemeID.equalsIgnoreCase("40") || schemeID.equalsIgnoreCase("35") 
	            					|| schemeID.equalsIgnoreCase("15")
	        						|| schemeID.equalsIgnoreCase("21")))){
	                    			
	            		String set_preMatricFormFields = databaseQ.getSetPreMatricFormDetails();
	            		try(CallableStatement callableStatement2 = conn.prepareCall(set_preMatricFormFields);){
	            			callableStatement2.setString(1,applicationID);
	            			callableStatement2.setString(2,applySchemeEntity_data.getApplicantCasteCertificateNo());
	    	                    	
		                	if(applySchemeEntity_data.getApplicantCasteCertificateDate().contains("/")){
		                		formatedCasteCertificateDate =
		                				dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate(),
		                						"dd/mm/yyyy", "yyyy-mm-dd");
		                	}else{
		                		formatedCasteCertificateDate =
		                				dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate(),
		                						"dd-mm-yyyy", "yyyy-mm-dd");
		                	}
	    	                    	
		                	callableStatement2.setString(3,formatedCasteCertificateDate);
		                	callableStatement2.setInt(4,applySchemeEntity_data.getApplicantCasteCertificateYear());
		                	callableStatement2.setString(5,applySchemeEntity_data.getApplicantCasteDistrict());
		                	callableStatement2.setString(6,applySchemeEntity_data.getApplicantCasteTaluka());
		                	callableStatement2.setString(7,applySchemeEntity_data.getApplicantCasteCertificateSubdivision());
		                	callableStatement2.setString(8,applySchemeEntity_data.getApplicantCasteCertificateAuthority());
		                	callableStatement2.setString(9,applySchemeEntity_data.getApplicantDayScholarDetails());
		                	callableStatement2.setString(10,applySchemeEntity_data.getApplicantHostelDistrict());
		                	callableStatement2.setString(11,applySchemeEntity_data.getApplicantHostelTaluka());
		                	callableStatement2.setString(12,applySchemeEntity_data.getHostelType());
		                	callableStatement2.setString(13,applySchemeEntity_data.getApplicantHostelName());
		                	callableStatement2.setString(14,applySchemeEntity_data.getHostelAided());
		                	callableStatement2.setString(15,applySchemeEntity_data.getApplicantHostelAddress());
	    	                    	
	                    	callableStatement2.setString(16,applySchemeEntity_data.getApplicantSARALNO());
	                    	callableStatement2.setString(17,applySchemeEntity_data.getApplicantGRNNO());
	                    	callableStatement2.setString(18,applySchemeEntity_data.getApplicantSchoolName());
	                    	callableStatement2.setInt(19,applySchemeEntity_data.getApplicantAcademicYear());
	                    	
	                    	formatedSchoolAdmissionDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantSchoolAdmissionDate(),
	                    			"dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement2.setString(20,formatedSchoolAdmissionDate);
	                    	callableStatement2.setString(21,applySchemeEntity_data.getApplicantSchoolStandard());
	                    	callableStatement2.setString(22,applySchemeEntity_data.getApplicantlastYearResult());
	                    	callableStatement2.setString(23,applySchemeEntity_data.getApplicantSchoolGrade());
	                    	callableStatement2.setString(24,applySchemeEntity_data.getApplicantUDISECode());
	                    	callableStatement2.setString(25,applySchemeEntity_data.getDoYouHaveGRNNo());
	                    	callableStatement2.setString(26,applySchemeEntity_data.getApplicantSchoolMarks());
	                    	callableStatement2.setString(27,applySchemeEntity_data.getApplicantIsBPL());
	                    	callableStatement2.setString(28,actionType);
	                    	
	                    	callableStatement2.setString(29,applySchemeEntity_data.getApplicantBPlNo());//change ankit
	                    	callableStatement2.setString(30,applySchemeEntity_data.getApplicantHaveCasteBarCode()); //9th June
	                    	callableStatement2.setString(31,applySchemeEntity_data.getApplicantCasteCertificateBarCode());
	                    	callableStatement2.setString(32,applySchemeEntity_data.getApplicantBarcodeName());
	    	                    	
	                    	/*formatedBarcodeCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate_barcode(), "dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement2.setString(33,formatedBarcodeCasteCertificateDate);
	                    	callableStatement2.setString(34,applySchemeEntity_data.getApplicantCasteCertificateAuthority_barcode());*/
	                    	
	                    	//change on 13th July 2017
	                    	callableStatement2.setString(33,applySchemeEntity_data.getApplicantHaveCasteCertificate());
	                    	callableStatement2.setString(34,applySchemeEntity_data.getApplicantHaveBPLCertificate());
	                    	callableStatement2.setString(35,applySchemeEntity_data.getApplicantHaveBPLBarCode());
	                    	callableStatement2.setString(36,applySchemeEntity_data.getApplicantBPLBarCode());
	                    	callableStatement2.setString(37,applySchemeEntity_data.getApplicantBPLBarcodeName());
	                    	callableStatement2.setString(38,applySchemeEntity_data.getApplicantBPLAuthority());
	                    	
	                    	formatedBPLDate=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantBPLDate(), "dd/mm/yyyy", "yyyy-mm-dd");
	                    	callableStatement2.setString(39,formatedBPLDate);
	                    	callableStatement2.setString(40,applySchemeEntity_data.getApplicantSchoolDistrict());
	                    	callableStatement2.setString(41,applySchemeEntity_data.getApplicantSchoolTaluka());
	                    	
	                    	callableStatement2.executeUpdate();
	                    	//conn.commit();
	                    	status = callableStatement2.getString(42);
	                    	System.out.println("Status :: " + status);
	            		}
	    	                    	
	                	if(schemeID.equalsIgnoreCase("34") || schemeID.equalsIgnoreCase("40") || schemeID.equalsIgnoreCase("35")) {
	                		String set_SchoolEducationFormDetails = databaseQ.getSetSEFormDetails();
	                		try(CallableStatement callableStatement4 = conn.prepareCall(set_SchoolEducationFormDetails);) {
	                    	
	                    	callableStatement4.setString(1,applicationID);
	                    	callableStatement4.setString(2,applySchemeEntity_data.getApplicantExamSeatNo());
	                    	callableStatement4.setFloat(3,applySchemeEntity_data.getApplicant10thSanskritMarks());
	                    	callableStatement4.setString(4,applySchemeEntity_data.getApplicantSet());
	                    	callableStatement4.setString(5,null);
	                    	callableStatement4.setString(6,actionType);
	                    	callableStatement4.setString(7,applySchemeEntity_data.getSanskritOptedPre());//change ankit
	                    	
	                    	callableStatement4.setFloat(8,applySchemeEntity_data.getApplicant10thSanskritMarksPerCent());//9th june
	                    	callableStatement4.setFloat(9,applySchemeEntity_data.getApplicant8thSanskritMarks());
	                    	callableStatement4.setFloat(10,applySchemeEntity_data.getApplicant8thSanskritMarksPerCent());
	                    	
	                    	//14 june
	                    	callableStatement4.setString(11,applySchemeEntity_data.getParikshaExam());
	                    	callableStatement4.setString(12,applySchemeEntity_data.getApplicantlastYearResultEighth());
	        	                    	
	        	                    	
		                	//change by ankit 17 june
		                	//callableStatement4.setString(13,applySchemeEntity_data.getSanskritOptedPre());
		                	
		                	callableStatement4.executeUpdate();
		                	//conn.commit();
		                	status = callableStatement4.getString(13);
		                	System.out.println("Status :: " + status);
		            		}
		            	}
	    	                   	
	        		}
	            	/*else if(!"".equalsIgnoreCase(schemeType) && !"null".equalsIgnoreCase(schemeType) && schemeType != null
	        				&& ("PreMatric".equalsIgnoreCase(schemeType) && (schemeID.equalsIgnoreCase("15")
	        						|| schemeID.equalsIgnoreCase("21")))){
	                    			
		    			String set_preMatricTribalFormFields = databaseQ.getSetTribalPreMatricFormDetails();
		    			try(CallableStatement callableStatement2 = conn.prepareCall(set_preMatricTribalFormFields);){
		    			
			            	callableStatement2.setString(1,applicationID);
			            	callableStatement2.setString(2,applySchemeEntity_data.getApplicantCasteCertificateNo());
			            	
			            	if(applySchemeEntity_data.getApplicantCasteCertificateDate().contains("/")){
			            		formatedCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate(), "dd/mm/yyyy", "yyyy-mm-dd");
			            	}else{
			            		formatedCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate(), "dd-mm-yyyy", "yyyy-mm-dd");
			            	}
		    	                    	
		                	callableStatement2.setString(3,formatedCasteCertificateDate);
		                	callableStatement2.setInt(4,applySchemeEntity_data.getApplicantCasteCertificateYear());
		                	callableStatement2.setString(5,applySchemeEntity_data.getApplicantCasteDistrict());
		                	callableStatement2.setString(6,applySchemeEntity_data.getApplicantCasteTaluka());
		                	callableStatement2.setString(7,applySchemeEntity_data.getApplicantCasteCertificateSubdivision());
		                	callableStatement2.setString(8,applySchemeEntity_data.getApplicantCasteCertificateAuthority());
		                	callableStatement2.setString(9,applySchemeEntity_data.getApplicantDayScholarDetails());
		                	callableStatement2.setString(10,applySchemeEntity_data.getApplicantHostelDistrict());
		                	callableStatement2.setString(11,applySchemeEntity_data.getApplicantHostelTaluka());
		                	callableStatement2.setString(12,applySchemeEntity_data.getHostelType());
		                	callableStatement2.setString(13,applySchemeEntity_data.getApplicantHostelName());
		                	callableStatement2.setString(14,applySchemeEntity_data.getHostelAided());
		                	callableStatement2.setString(15,applySchemeEntity_data.getApplicantHostelAddress());
		                	
		                	callableStatement2.setString(16,applySchemeEntity_data.getApplicantSARALNO());
		                	callableStatement2.setString(17,applySchemeEntity_data.getApplicantGRNNO());
		                	callableStatement2.setString(18,applySchemeEntity_data.getApplicantSchoolName());
		                	
		                	callableStatement2.setInt(19,applySchemeEntity_data.getApplicantAcademicYear());
		    	                    	
		                	formatedSchoolAdmissionDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantSchoolAdmissionDate(), "dd/mm/yyyy", "yyyy-mm-dd");
		                	callableStatement2.setString(20,formatedSchoolAdmissionDate);
		                	
		                	callableStatement2.setString(21,applySchemeEntity_data.getApplicantSchoolStandard());
		                	callableStatement2.setString(22,applySchemeEntity_data.getLastSchoolName());
		                	callableStatement2.setInt(23,applySchemeEntity_data.getLastAcademicYear());
		                	formatedLastSchoolAdmissionDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getLastSchoolAdmissionDate(), "dd/mm/yyyy", "yyyy-mm-dd");
		                	callableStatement2.setString(24,formatedLastSchoolAdmissionDate);
		                	callableStatement2.setString(25,applySchemeEntity_data.getLastSchoolStandard());
		                	callableStatement2.setString(26,applySchemeEntity_data.getApplicantlastYearResult());
		                	callableStatement2.setString(27,applySchemeEntity_data.getApplicantSchoolGrade());			//change ankit 16june
		                	callableStatement2.setString(28,applySchemeEntity_data.getApplicantSchoolMarks());
		                	callableStatement2.setString(29,applySchemeEntity_data.getApplicantUDISECode());
		                	callableStatement2.setString(30,applySchemeEntity_data.getDoYouHaveGRNNo());
		                	callableStatement2.setString(31,actionType);
		                	
		                	callableStatement2.setString(32,applySchemeEntity_data.getApplicantHaveCasteBarCode()); //9th June
		                	callableStatement2.setString(33,applySchemeEntity_data.getApplicantCasteCertificateBarCode());
		                	callableStatement2.setString(34,applySchemeEntity_data.getApplicantBarcodeName());
		    	                    	
		                	formatedBarcodeCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate_barcode(), "dd/mm/yyyy", "yyyy-mm-dd");
		                	callableStatement2.setString(35,formatedBarcodeCasteCertificateDate);
		                	callableStatement2.setString(36,applySchemeEntity_data.getApplicantCasteCertificateAuthority_barcode());
		                	
		                	//change on 13th July 2017
	                    	callableStatement2.setString(35,applySchemeEntity_data.getApplicantHaveCasteCertificate());
	                    	callableStatement2.setString(36,applySchemeEntity_data.getApplicantSchoolDistrict());
	                    	callableStatement2.setString(37,applySchemeEntity_data.getApplicantSchoolTaluka());
		                	
		                	callableStatement2.executeUpdate();
		                	//conn.commit();
		                	status = callableStatement2.getString(38);
		                	System.out.println("Status :: " + status);
		    			}
		        	}*/
	        		else{
	        			resultMap.put("resultCode", -9);
	        			resultMap.put("resultDetails", "Scheme Applied Failed.");
	        			httpResponse.setHeader("token", token);
	        	    	return resultMap;
		        	}
            		
	            	if(status.equalsIgnoreCase("success")) {
	            		System.out.println("Temp : " + tempApp_ID + " applicationID : " + applicationID);
		                    		
	            		if(actionType.equalsIgnoreCase("D")) {
	            			PreparedStatement stmt3 = null;
	            			String sql3 = databaseQ.getUserApplicationDraftCount();
	            			stmt3 = conn.prepareStatement(sql3);
	            			stmt3.setString(1, tempApp_ID);
	            			rs = stmt3.executeQuery();
	                		
	                		while(rs.next()){
	                			count = rs.getInt(1);
	                		}
			                    		
	                		if(count == 0){
	                			String set_ScholarshipDraftDetails = databaseQ.getSetScholarshipDraftDetails();
	                			try(CallableStatement callableStatement4 = conn.prepareCall(set_ScholarshipDraftDetails);){	                    		
		                        	callableStatement4.setString(1,tempApp_ID);
		                        	callableStatement4.setString(2,userID);
		                        	callableStatement4.setString(3,schemeID);
		                        	callableStatement4.setString(4,schemeType);			                        	
		                        	callableStatement4.executeUpdate();
		                        	conn.commit();
		                        	status = callableStatement4.getString(5);
		                        	System.out.println("Status :: " + status);
	                			}
	                		}else{
	                			String update_ScholarshipDraftDetails = databaseQ.getUpdateScholarshipDraftDetails();
	                			try(CallableStatement callableStatement5 = conn.prepareCall(update_ScholarshipDraftDetails);){
		                    		
		                        	callableStatement5.setString(1,tempApp_ID);
		                        	callableStatement5.setString(2,userID);
		                        	callableStatement5.setString(3,schemeID);
		                        	callableStatement5.setString(4,schemeType);
		                        	
		                        	callableStatement5.executeUpdate();
		                        	conn.commit();
		                        	status = callableStatement5.getString(5);
		                        	System.out.println("Status :: " + status);
	                			}
	                		}
	            		}
            			else if(actionType.equalsIgnoreCase("N")){
	            			String set_TrackerDetails = databaseQ.getSetScholarshipTrackerDetails();
	            			try(CallableStatement callableStatement3 = conn.prepareCall(set_TrackerDetails);) {
			                    		
	                        	callableStatement3.setString(1,applicationID);
	                        	callableStatement3.setString(2,userID);
	                        	callableStatement3.setString(3,schemeID);
	                        	callableStatement3.setString(4,schemeType); //change on 9th July
	                        	
	                        	callableStatement3.executeUpdate();
	                        	//conn.commit();
	                        	//status = callableStatement3.getString(4);
	                        	status = callableStatement3.getString(5); //change on 9th July
	                        	System.out.println("Status :: " + status);
	                		}
			                        	
	                    	//PreparedStatement stmt5 = null;
	            			String sql5 = databaseQ.getDeleteUserApplicationDraft();
	            			try(PreparedStatement stmt5 = conn.prepareStatement(sql5);){
	            				stmt5.setString(1, tempApp_ID);
	            				stmt5.executeUpdate();
	            			}
		                        	
	                		/*String deleteTempApp_ID = databaseQ.getDeleteTempAppId();
	                		try(CallableStatement callableStatement3 = conn.prepareCall(deleteTempApp_ID);){
			                    		
	                			callableStatement3.setString(1, tempApp_ID);
	                			callableStatement3.executeUpdate();
	                		}*/ //change for removal of temp id concept on 29th june
                		
	                		String setIntegrationDetails_AutoPopulate = databaseQ.getIntegrationDetails_AutoPopulate();
	                		try(CallableStatement callableStatement34 = conn.prepareCall(setIntegrationDetails_AutoPopulate);) {
				                    		
	                        	callableStatement34.setString(1,applySchemeEntity_data.getApplicantHaveIncomeBarCode());
	                        		System.out.println(applySchemeEntity_data.getApplicantHaveIncomeBarCode());
	                        	
	                        	callableStatement34.setString(2,applySchemeEntity_data.getApplicantIncomeCertificateBarCode());
	                        		System.out.println(applySchemeEntity_data.getApplicantIncomeCertificateBarCode());
	                        	callableStatement34.setString(3,applySchemeEntity_data.getApplicantIncomeBarcodeName());
	                        		System.out.println(applySchemeEntity_data.getApplicantIncomeBarcodeName());
	                        	callableStatement34.setString(4,applySchemeEntity_data.getApplicantIncomeCertificateNo());
	                        		System.out.println(applySchemeEntity_data.getApplicantIncomeCertificateNo());
	                        	
	                        	formatedIncomeDate=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantIncomeCertificateDate(), "dd/mm/yyyy", "yyyy-mm-dd");
	                        	callableStatement34.setString(5,formatedIncomeDate);
	                        		System.out.println(formatedIncomeDate);
	                        	
	                        	callableStatement34.setString(6,applySchemeEntity_data.getApplicantIncomeCertificateAuthority());
	                        		System.out.println(applySchemeEntity_data.getApplicantIncomeCertificateAuthority());
	                        		System.out.println("FamilyIncome :: " + applySchemeEntity_data.getFamilyIncome());
				                        	
	                        	if("".equalsIgnoreCase(applySchemeEntity_data.getFamilyIncome()) || "null".equalsIgnoreCase(applySchemeEntity_data.getFamilyIncome()) || applySchemeEntity_data.getFamilyIncome() == null){
	                        		callableStatement34.setString(7,null);
	                        	}else{
	                        		callableStatement34.setString(7,applySchemeEntity_data.getFamilyIncome());
	                        	}
				                        	
	                        	callableStatement34.setString(8,applySchemeEntity_data.getApplicantDomicile());
	                        	callableStatement34.setString(9,applySchemeEntity_data.getApplicantHaveDomicileBarCode());
	                        	callableStatement34.setString(10,applySchemeEntity_data.getApplicantDomicileCertificateBarCode());
	                        	callableStatement34.setString(11,applySchemeEntity_data.getApplicantDomicileBarcodeName());
	                        	
	                        	formatedDomicileDate=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantDomicileCertificateDate_barcode(), "dd/mm/yyyy", "yyyy-mm-dd");
	                        	callableStatement34.setString(12,formatedDomicileDate);
	                        	callableStatement34.setString(13,applySchemeEntity_data.getApplicantDomicileCertificateAuthority_barcode());
	                        	callableStatement34.setString(14,applySchemeEntity_data.getApplicantIsPhysicallyHandicapped());
	                        	callableStatement34.setString(15,applySchemeEntity_data.getApplicantHaveDisabilityBarCode());
	                        	callableStatement34.setString(16,applySchemeEntity_data.getApplicantDisabilityCertificateBarCode());
	                        	callableStatement34.setString(17,applySchemeEntity_data.getApplicantDisabilityBarcodeName());
	                        	callableStatement34.setString(18,applySchemeEntity_data.getApplicantDisabilityCertificateNo());
	                        	callableStatement34.setString(19,applySchemeEntity_data.getApplicantDisabilityCertificateAuthority());
	                        	
	                        	formatedDisabilityDate=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantDisabilityCertificateDate(), "dd/mm/yyyy", "yyyy-mm-dd");
	                        	callableStatement34.setString(20,formatedDisabilityDate);
	                        	callableStatement34.setString(21,applySchemeEntity_data.getApplicantDisableType());
	                        	callableStatement34.setString(22,applySchemeEntity_data.getApplicantDisabilityType());
	                        	
	                        	System.out.println("Per Cent :: " + applySchemeEntity_data.getApplicantDisabilityPercentage());
	                        	//change on 18th July 2017
	                        	System.out.println("Per Cent :: " + applySchemeEntity_data.getApplicantDisabilityPercentage());
	                        	if(!"".equalsIgnoreCase(applySchemeEntity_data.getApplicantDisabilityPercentage()) && 
	                        			!"null".equalsIgnoreCase(applySchemeEntity_data.getApplicantDisabilityPercentage()) && applySchemeEntity_data.getApplicantDisabilityPercentage() != null){
	                        		callableStatement34.setString(23,applySchemeEntity_data.getApplicantDisabilityPercentage());
	                        	}else{
	                        		callableStatement34.setString(23,null);
	                        	}
	                        	
	                        	callableStatement34.setInt(24,applySchemeEntity_data.getApplicantDisabilityYear());
	                        	callableStatement34.setString(25,applySchemeEntity_data.getReaderOpted());
	                        	callableStatement34.setString(26,applySchemeEntity_data.getApplicantCasteCategory());
	                        	callableStatement34.setString(27,applySchemeEntity_data.getApplicantCaste());
	                        	callableStatement34.setString(28,applySchemeEntity_data.getApplicantHaveCasteBarCode());
		                    	callableStatement34.setString(29,applySchemeEntity_data.getApplicantCasteCertificateBarCode());
		                    	callableStatement34.setString(30,applySchemeEntity_data.getApplicantBarcodeName());
		                    	formatedCasteCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteCertificateDate(), "dd/mm/yyyy", "yyyy-mm-dd");
		                    	callableStatement34.setString(31,formatedCasteCertificateDate);
		                    	callableStatement34.setString(32,applySchemeEntity_data.getApplicantCasteCertificateNo());
		                    	callableStatement34.setString(33,applySchemeEntity_data.getApplicantCasteCertificateAuthority());               	
			    	                    	
		                    	callableStatement34.setInt(34,applySchemeEntity_data.getApplicant10thTotalMarks());
		                    	callableStatement34.setString(35,applySchemeEntity_data.getApplicant10thResult());
		                    	callableStatement34.setString(36,applySchemeEntity_data.getApplicant10thPassingBoard());
		                    	callableStatement34.setString(37,applySchemeEntity_data.getApplicantSSCBoard1());
		                    	callableStatement34.setInt(38,applySchemeEntity_data.getApplicant10thPassingYear());
		                    	callableStatement34.setString(39,applySchemeEntity_data.getApplicant10thPassingMonth());
		                    	callableStatement34.setString(40,applySchemeEntity_data.getApplicant10thRollNo());
		                    	callableStatement34.setInt(41,applySchemeEntity_data.getApplicant10thTotalMarksObtained());
		                    	
		                    	if("".equalsIgnoreCase(applySchemeEntity_data.getApplicant10thMarksObtained()) || "null".equalsIgnoreCase(applySchemeEntity_data.getApplicant10thMarksObtained())){
		                    		callableStatement34.setString(42,null);
		                    	}else{
		                    		callableStatement34.setString(42,applySchemeEntity_data.getApplicant10thMarksObtained());
		                    	}
		                    	
		                    	callableStatement34.setString(43,applySchemeEntity_data.getSscIntegrationFlag());
		                    	callableStatement34.setInt(44,applySchemeEntity_data.getApplicant12thTotalMarks());
		                    	callableStatement34.setString(45,applySchemeEntity_data.getApplicant12thResult());
		                    	callableStatement34.setString(46,applySchemeEntity_data.getApplicant12thPassingBoard());
		                    	callableStatement34.setString(47,applySchemeEntity_data.getApplicantHSCBoard1());
		                    	callableStatement34.setInt(48,applySchemeEntity_data.getApplicant12thPassingYear());
		                    	callableStatement34.setString(49,applySchemeEntity_data.getApplicant12thPassingMonth());
		                    	callableStatement34.setString(50,applySchemeEntity_data.getApplicant12thRollNo());
		                    	callableStatement34.setInt(51,applySchemeEntity_data.getApplicant12thTotalMarksObtained());
			    	                    	
		                    	if("".equalsIgnoreCase(applySchemeEntity_data.getApplicant12thMarksObtained()) || "null".equalsIgnoreCase(applySchemeEntity_data.getApplicant12thMarksObtained())){
		                    		callableStatement34.setString(52,null);
		                    	}else{
		                    		callableStatement34.setString(52,applySchemeEntity_data.getApplicant12thMarksObtained());
		                    	}
			    	                    	
		                    	callableStatement34.setString(53,applySchemeEntity_data.getHscIntegrationFlag());	
			    	                    	
		                    	callableStatement34.setString(54,applySchemeEntity_data.getApplicantCasteValidityNo());
		                    	formatedCasteValidityCertificateDate = dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantCasteValidityCertificateDate(), "dd/mm/yyyy", "yyyy-mm-dd");
		                    	callableStatement34.setString(55,formatedCasteValidityCertificateDate);
		                    	callableStatement34.setString(56,applySchemeEntity_data.getCasteValidityIntegrationFlag());
		                    	
		                    	callableStatement34.setString(57,applySchemeEntity_data.getApplicantDomicileCertificateNo());
	                        	callableStatement34.setString(58,userID);
	                        	System.out.println(userID);
	                        	
	                        	//change on 11th July
	                        	callableStatement34.setString(59,applySchemeEntity_data.getApplicantHaveIncomeCertificate());
	                        	callableStatement34.setString(60,applySchemeEntity_data.getApplicantHaveDisabilityCertificate());
	                        	callableStatement34.setString(61,applySchemeEntity_data.getApplicantHaveCasteCertificate());
	                        	System.out.println(applySchemeEntity_data.getApplicantHaveCertificateDomicile());
	                        	callableStatement34.setString(62,applySchemeEntity_data.getApplicantHaveCertificateDomicile());
	                        	callableStatement34.setString(63,applySchemeEntity_data.getApplicantHaveBPLCertificate());
	                        	callableStatement34.setString(64,applySchemeEntity_data.getApplicantHaveBPLBarCode());
	                        	callableStatement34.setString(65,applySchemeEntity_data.getApplicantBPLBarCode());
	                        	callableStatement34.setString(66,applySchemeEntity_data.getApplicantBPLBarcodeName());
	                        	callableStatement34.setString(67,applySchemeEntity_data.getApplicantBPLAuthority());
	                        	
	                        	formatedBPLDate=dateConverter.dateFormatConverter(applySchemeEntity_data.getApplicantBPLDate(), "dd/mm/yyyy", "yyyy-mm-dd");
	                        	callableStatement34.setString(68,formatedBPLDate);
	                        	callableStatement34.setString(69,applySchemeEntity_data.getApplicantIsBPL());
	                        	callableStatement34.setString(70,applySchemeEntity_data.getApplicantBPlNo());
	                        	
	                        	//change on 20th July 2017
		                    	callableStatement34.setString(71,applySchemeEntity_data.getSscCertificateName());
	                        	callableStatement34.executeUpdate();
	                    	}      		
                		
	                    	//PreparedStatement stmt4 = null;
	            			String sql4 = databaseQ.getUpdateAppDocDetails();
	            			try(PreparedStatement stmt4 = conn.prepareStatement(sql4);){
		            			stmt4.setString(1, applicationID);
		            			stmt4.setString(2, userID);
		            			stmt4.setString(3, schemeID);
		            			stmt4.executeUpdate();
	            			}
		    	                    		
	            			conn.commit();
		                    		
		                    		/*Insert info for uploaded documents from repository*/
//		                    		PreparedStatement p1 = null;
//		                    		PreparedStatement p2 = null;
//		                    		ResultSet r1 = null;
	            			String doc = null;
			                    		
	                		/* Domicile Certificate */
	            			if(	!"".equalsIgnoreCase(applySchemeEntity_data.getCheckDomDoc())
	            					&& !"null".equalsIgnoreCase(applySchemeEntity_data.getCheckDomDoc())
	            					&& applySchemeEntity_data.getCheckDomDoc() != null) {
	        					try(PreparedStatement p1 = conn.prepareStatement(" SELECT drd.doc_path doc_path,dmt.doc_type doc_type "+
																	   " FROM doc_repository_dtl drd "+
																	   " INNER JOIN doc_master_table dmt on dmt.doc_id = drd.doc_id "+
																	   " WHERE user_id = '" + userID + "' "+
																	   " AND drd.doc_id = '"+applySchemeEntity_data.getCheckDomDoc()+"';");){
	                    			
	        						try(ResultSet r1 = p1.executeQuery();){
	                    				while(r1.next()) {
		            						System.out.println(">>>"+r1.getString("doc_path"));
		            						if(r1.getString("doc_path").contains("../../..")){
		            							doc = r1.getString("doc_path").replaceAll("../../..","/usr");
		            						}else if(r1.getString("doc_path").contains("\\")){
		            							doc = r1.getString("doc_path").replaceAll("\\\\","/");
		            						}
	                    						
	                						try(PreparedStatement p2 = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
	                								+ "('" + applicationID + "', '" + r1.getString("doc_type") + "', '" + doc + "',"
	                								+ "'" + userID + "','N','N','R');");){
	                							p2.executeUpdate();
	                						}
	                					}
	                				}
	                    					
	//                    					p1.close();
	//                    					p2.close();
	//                    					r1.close();
	                    			conn.commit();
			                    }
		                    }
            			
	            			/* Father Income Certificate */
	            			if(	!"".equalsIgnoreCase(applySchemeEntity_data.getFatherCerDoc()) && !"null".equalsIgnoreCase(applySchemeEntity_data.getFatherCerDoc()) && applySchemeEntity_data.getFatherCerDoc() != null) {
	            				try(PreparedStatement p1 = conn.prepareStatement(" SELECT drd.doc_path doc_path,dmt.doc_type doc_type "+
																	   " FROM doc_repository_dtl drd "+
																	   " INNER JOIN doc_master_table dmt on dmt.doc_id = drd.doc_id "+
																	   " WHERE user_id = '" + userID + "' "+
																	   " AND drd.doc_id = '"+applySchemeEntity_data.getFatherCerDoc()+"';");){
	                    					
	            					try(ResultSet r1 = p1.executeQuery();){
	                    					
	            						while(r1.next()) {
	            						
	            							System.out.println(">>>"+r1.getString("doc_path"));
		            						if(r1.getString("doc_path").contains("../../..")){
		            							doc = r1.getString("doc_path").replaceAll("../../..","/usr");
		            						}else if(r1.getString("doc_path").contains("\\")){
		            							doc = r1.getString("doc_path").replaceAll("\\\\","/");
		            						}
	                    						
	                						try(PreparedStatement p2 = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
	                								+ "('" + applicationID + "', '" + r1.getString("doc_type") + "', '" + doc + "',"
	                								+ "'" + userID + "','N','N','R');");){
	                							p2.executeUpdate();
	                						}
	                    				}
	                    			}
	                    		}
	                    					
	//                    					p1.close();
	//                    					p2.close();
	//                    					r1.close();
	        					conn.commit();
	                		}
	                    			
	            			/* Mother Income Certificate */
		                    if(	!"".equalsIgnoreCase(applySchemeEntity_data.getMotherCerDoc()) && !"null".equalsIgnoreCase(applySchemeEntity_data.getMotherCerDoc()) && applySchemeEntity_data.getMotherCerDoc() != null) {
	                    					
		                    	try(PreparedStatement p1 = conn.prepareStatement(" SELECT drd.doc_path doc_path,dmt.doc_type doc_type "+
																	   " FROM doc_repository_dtl drd "+
																	   " INNER JOIN doc_master_table dmt on dmt.doc_id = drd.doc_id "+
																	   " WHERE user_id = '" + userID + "' "+
																	   " AND drd.doc_id = '"+applySchemeEntity_data.getMotherCerDoc()+"';");){
	                    					
		                    		try(ResultSet r1 = p1.executeQuery();){
	                    					
		                    			while(r1.next()) {
	                						System.out.println(">>>"+r1.getString("doc_path"));
	                						if(r1.getString("doc_path").contains("../../..")){
	                							doc = r1.getString("doc_path").replaceAll("../../..","/usr");
	                						}else if(r1.getString("doc_path").contains("\\")){
	                							doc = r1.getString("doc_path").replaceAll("\\\\","/");
	                						}
	                    						
	                    					try (PreparedStatement p2 = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
	                    								+ "('" + applicationID + "', '" + r1.getString("doc_type") + "', '" + doc + "',"
	                    								+ "'" + userID + "','N','N','R');");){
	                    						p2.executeUpdate();
	                    					}
	                    				}
	                    			}
	                    		}
	//                    					p1.close();
	//                    					p2.close();
	//                    					r1.close();
	        					conn.commit();
	        				}
		                    			
		                    /* Caste Validity Certificate */
		                    if(	!"".equalsIgnoreCase(applySchemeEntity_data.getCasteValCerDoc()) && !"null".equalsIgnoreCase(applySchemeEntity_data.getCasteValCerDoc()) && applySchemeEntity_data.getCasteValCerDoc() != null) {
		                    	
		                    	try(PreparedStatement p1 = conn.prepareStatement(" SELECT drd.doc_path doc_path,dmt.doc_type doc_type "+
																	   " FROM doc_repository_dtl drd "+
																	   " INNER JOIN doc_master_table dmt on dmt.doc_id = drd.doc_id "+
																	   " WHERE user_id = '" + userID + "' "+
																	   " AND drd.doc_id = '"+applySchemeEntity_data.getCasteValCerDoc()+"';");){
	                    					
		                    		try(ResultSet r1 = p1.executeQuery();){
	                    					
		                    			while(r1.next()) {
		            						System.out.println(">>>"+r1.getString("doc_path"));
		            						if(r1.getString("doc_path").contains("../../..")){
		            							doc = r1.getString("doc_path").replaceAll("../../..","/usr");
		            						}else if(r1.getString("doc_path").contains("\\")){
		            							doc = r1.getString("doc_path").replaceAll("\\\\","/");
		            						}
	                    						
	                    					try(PreparedStatement p2 = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
	                    								+ "('" + applicationID + "', '" + r1.getString("doc_type") + "', '" + doc + "',"
	                    								+ "'" + userID + "','N','N','R');");){
	                    						p2.executeUpdate();
	                    					}
	                    				}
	                    			}
	                    		}
	                    					
	//                    					p1.close();
	//                    					p2.close();
	//                    					r1.close();
	        					conn.commit();
	                		}
	                    			
		                    /* SSC Certificate */
		                    if(	!"".equalsIgnoreCase(applySchemeEntity_data.getSscCerDoc()) && !"null".equalsIgnoreCase(applySchemeEntity_data.getSscCerDoc()) && applySchemeEntity_data.getSscCerDoc() != null) {
	                    					
		                    	try(PreparedStatement p1 = conn.prepareStatement(" SELECT drd.doc_path doc_path,dmt.doc_type doc_type "+
																	   " FROM doc_repository_dtl drd "+
																	   " INNER JOIN doc_master_table dmt on dmt.doc_id = drd.doc_id "+
																	   " WHERE user_id = '" + userID + "' "+
																	   " AND drd.doc_id = '"+applySchemeEntity_data.getSscCerDoc()+"';");){
	                    					
		                    		try(ResultSet r1 = p1.executeQuery();){
	                    					
		                    			while(r1.next()) {
	                						System.out.println(">>>"+r1.getString("doc_path"));
	                						if(r1.getString("doc_path").contains("../../..")){
	                							doc = r1.getString("doc_path").replaceAll("../../..","/usr");
	                						}else if(r1.getString("doc_path").contains("\\")){
	                							doc = r1.getString("doc_path").replaceAll("\\\\","/");
	                						}
	                						
	                						try(PreparedStatement p2 = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
	                								+ "('" + applicationID + "', '" + r1.getString("doc_type") + "', '" + doc + "',"
	                								+ "'" + userID + "','N','N','R');");){
	                							p2.executeUpdate();
	                						}
	                    				}
	                    			}
	                    		}
	//                    					
	//                    					p1.close();
	//                    					p2.close();
	//                    					r1.close();
	        					conn.commit();
	                		}
	                    			
	                    			
		                    /* HSC Certificate */
		                    if(	!"".equalsIgnoreCase(applySchemeEntity_data.getHscCerDoc()) && !"null".equalsIgnoreCase(applySchemeEntity_data.getHscCerDoc()) && applySchemeEntity_data.getHscCerDoc() != null) {
	                    		try(PreparedStatement p1 = conn.prepareStatement(" SELECT drd.doc_path doc_path,dmt.doc_type doc_type "+
																	   " FROM doc_repository_dtl drd "+
																	   " INNER JOIN doc_master_table dmt on dmt.doc_id = drd.doc_id "+
																	   " WHERE user_id = '" + userID + "' "+
																	   " AND drd.doc_id = '"+applySchemeEntity_data.getHscCerDoc()+"';");){
	                    					
	                    			try(ResultSet r1 = p1.executeQuery();){
	                    					
	                    				while(r1.next()) {
	                						System.out.println(">>>"+r1.getString("doc_path"));
	                						if(r1.getString("doc_path").contains("../../..")){
	                							doc = r1.getString("doc_path").replaceAll("../../..","/usr");
	                						}else if(r1.getString("doc_path").contains("\\")){
	                							doc = r1.getString("doc_path").replaceAll("\\\\","/");
	                						}
	                						
	                						try(PreparedStatement p2 = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
	                								+ "('" + applicationID + "', '" + r1.getString("doc_type") + "', '" + doc + "',"
	                								+ "'" + userID + "','N','N','R');");){
	                							p2.executeUpdate();
	                						}
	                    				}
	                    			}
	                    		}
//                    					p1.close();
//                    					p2.close();
//                    					r1.close();
	                    		conn.commit();
	                		}
	            		}
            			else if(actionType.equalsIgnoreCase("M")){
            				
                    			//PreparedStatement stmt3 = null;
                    			String sql3 = databaseQ.getUpdateESCApplicationTrackerDetails();
                    			try(PreparedStatement stmt3 = conn.prepareStatement(sql3);){
                    			stmt3.setString(1, applicationID);
	                    		stmt3.executeUpdate();
	                    		conn.commit();
                    			}
	                    		//try{if(stmt3 != null){stmt3.close();}}catch(Exception ex){ex.printStackTrace();}
                    			
	                    		//PreparedStatement stmt2 = null;
                    			String sql2 = databaseQ.getUpdateESCApproverDashboardDetails();
                    			try(PreparedStatement stmt2 = conn.prepareStatement(sql2);){
                    			stmt2.setString(1, applySchemeEntity_data.getCollegeRemarks());
                    			stmt2.setString(2, applicationID);
                    			stmt2.setString(3, userID);
                    			stmt2.executeUpdate(); 	
	                    		conn.commit();
                    			}
	                    		//try{if(stmt2 != null){stmt2.close();}}catch(Exception ex){ex.printStackTrace();}
	                    		
	                        	resultMap.put("resultCode", 7);
	                            resultMap.put("resultDetails", "Scheme Updated Successfully.");
	                            httpResponse.setHeader("token", token);
	                        	return resultMap;
                    		
            			}
            		}else{
	        			resultMap.put("resultCode", -9);
	                    resultMap.put("resultDetails", "Scheme Applied Failed.");
	                    httpResponse.setHeader("token", token);
                    	return resultMap;
	        		}
                    	
            		resultMap.put("resultCode", 0);
            		resultMap.put("resultDetails", applicationID);
            	}
        	}
        		
    	}catch(Exception ex){
    		System.out.println("Error occurred in submit form :: " + ex);
    		ex.printStackTrace();
    		resultMap.put("resultCode", -1);
    		if(ex.getMessage().contains("Duplicate entry"))
            	resultMap.put("resultDetails", "Same Scheme can't be applied again.");
    		else
    			resultMap.put("resultDetails", ex.getMessage());
            httpResponse.setHeader("token", token);
            return resultMap;
    	}
    	httpResponse.setHeader("token", token);
    	return resultMap;
	}
	
	public JsonObject checkSchemeExistance(String schemeType, String userFullName, String schemeId, String userIndex) {

		ResultSet rs = null;
		String applicationID = null;
		applicationID = getNewApplicationId(schemeType, userFullName, schemeId, userIndex);

		JsonObject resultObj = new JsonObject();

		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());

			try (Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(databaseQ.getSchemeExistenceStatus());) {
				ps.setString(1, applicationID);
				ps.setString(2, applicationID);
				rs = ps.executeQuery();
				int counter = 0;
				while (rs.next()) {
					counter++;
				}

				if (counter != 0) {
					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message",
							"Scheme is either Applied or is saved in Draft . Please complete action .");
					return resultObj;
				} else {
					resultObj.addProperty("ResulCode", -1);
					resultObj.addProperty("Message", "New Application ID.");
					return resultObj;
				}
			}

		} catch (Exception sec) {
			sec.printStackTrace();
			resultObj.addProperty("ResulCode", -2);
			resultObj.addProperty("Message", "Some exception came while adding new document.");
			return resultObj;
		}
	}

	public String getNewApplicationId(String schemeType, String userFullName, String schemeId, String userIndex) {
		
		System.out.println();
		int currentAcademicYear = Calendar.getInstance().get(Calendar.YEAR)%100;
		String prefix = "";
		String applicantName_Initials = "";
		String applicantId = "";
		
		//Get 2 characters from Applicant Name
		int spaceCount = userFullName.length() - userFullName.replaceAll(" ", "").length();
		
		if(spaceCount == 2){
			applicantName_Initials = userFullName.split(" ")[0].substring(0,1);
			applicantName_Initials = applicantName_Initials + userFullName.split(" ")[2].substring(0,1);
    	}else if(spaceCount == 1){
    		applicantName_Initials = userFullName.split(" ")[0].substring(0,1);
			applicantName_Initials = applicantName_Initials + userFullName.split(" ")[1].substring(0,1);
		}else if(spaceCount == 0){
			//applicantName_Initials = userFullName.split(" ")[0].substring(0,2); //change on 4th July
			applicantName_Initials = userFullName.substring(0,2);
		}
		applicantName_Initials = applicantName_Initials.toUpperCase(); // change on 7th July
		
		if(schemeType.equalsIgnoreCase("PostMatric") || schemeType.equalsIgnoreCase("postmatric")){
			prefix = "POS";
			applicantId = prefix + applicantName_Initials + getPadding(schemeId, 3) + currentAcademicYear
					+ getPadding(userIndex, 10);
		}
		else if(schemeType.equalsIgnoreCase("PreMatric") || schemeType.equalsIgnoreCase("prematric")){
			prefix = "PRE";
			applicantId = prefix + applicantName_Initials + getPadding(schemeId, 3) + currentAcademicYear
					+ getPadding(userIndex, 10);
		}
		
		
		return applicantId;
	}
	
	private String getPadding(String input, int paddingLimit) {
		
		String output = "";
		output = String.format("%0" + paddingLimit + "d", Integer.parseInt(input));
			System.out.println(output);
		return output;
	}

}
