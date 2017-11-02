/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : PostMatricFormDetails.java
* Author              : Swarnadip Ghosh/Ankit Bhasin
* Date written
* (DD/MM/YYYY)        : Jan 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.activityclass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.newgen.utility.PGAdmin_DB_Connection;

import JSON.JSONException;
import JSON.JSONObject;

public class PostMatricFormDetails {
	
	public String addPostMatricDetails(JSONObject jsonObj){
		 
		 String status = "false";
		 Connection conn = null;
		 String sqlQuery = "";
		 
		 Statement stmt = null;
		 /*Blob applicantImage = null;
		 Blob applicantIncomeCertificateImage = null;
		 Blob applicantCasteCertificateImage = null;
		 Blob applicantHSCImage = null;
		 Blob applicantSSCImage = null;
		 Blob applicantDomicileImage = null;*/
		 
		 int applicationID = 20170000;
		 ResultSet rs = null;
		 int tempVal = -1;
		 //int c = 5;
		 try{
			String userID = (String)jsonObj.get("userID");
			System.out.println("userID :: " + userID);
		    conn = PGAdmin_DB_Connection.getConnection();	
		    stmt = conn.createStatement();
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date date = new Date();
		    
		    //sqlQuery = "select max(\"app_Id\") from \"dbt_Application_Tracker\"";
		    sqlQuery = "select max(app_Id) from dbt_Application_Tracker";
		    rs = stmt.executeQuery(sqlQuery);
		    while(rs.next()){
		    	tempVal = rs.getInt(1);
		    }
			if(tempVal == -1){
				applicationID++;
			}else{
				//c++;
				tempVal++;
				applicationID = tempVal;
			}
			/*sqlQuery = "insert into \"dbt_Application_Tracker\"(\"ID\", \"app_Id\", \"app_Submit_Date\", \"app_Current_Stage\", \"app_Remarks\", \"app_SchemeID\", \"app_SchemeType\", \"user_ID\", \"app_flag\") "
					+ "values('" + c +"', '" + applicationID + "','"+ dateFormat.format(date) + "', 'cur001', 'InProcess', 'sch-001', 'PostMatric', 'ankit.bhasin', 'N');";
			stmt.executeUpdate(sqlQuery);*/
			sqlQuery = "insert into dbt_Application_Tracker(app_Id, app_Submit_Date, app_Current_Stage, app_Remarks, app_SchemeID, app_SchemeType, user_ID, app_flag) "
					+ "values('" + applicationID + "','"+ dateFormat.format(date) + "', 'Verified', 'InProcess', '1', 'PostMatric', '" + userID + "', 'N');";
			stmt.executeUpdate(sqlQuery);
			
			
			/**Updating Document Table**/
			sqlQuery = "update dbt_document set app_id = '" +  applicationID + "' where user_id = '" + userID + "' and app_id is null";
			stmt.executeUpdate(sqlQuery);
			/****/
			
			
		    Boolean applicantDomicile = (Boolean)jsonObj.get("applicantDomicile");
			String applicantFirstName = (String)jsonObj.get("applicantFirstName");
			String applicantMiddleName = "";
			try{
				 applicantMiddleName = (String)jsonObj.get("applicantMiddleName");
			}catch(NullPointerException npe){
				applicantMiddleName = "";
			}
			String applicantLastName = (String)jsonObj.get("applicantLastName");
			String applicantAadhaarUID = "";
			try{
				applicantAadhaarUID = (String)jsonObj.get("applicantAadhaarUID");
			}catch(Exception ex){
				applicantAadhaarUID = "";
			}
			String applicantMobileNo = (String)jsonObj.get("applicantMobileNo");
			String applicantEmailID = (String)jsonObj.get("applicantEmailID");
			String applicantDOB = (String)jsonObj.get("applicantDOB");
			if(applicantDOB.contains("T")){
				int len = applicantDOB.indexOf("T");
				applicantDOB = applicantDOB.substring(0,len);
				System.out.println(applicantDOB);
			}
			/*if(!applicantDOB.contains("T")){
				SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd" );
				try{
					date = originalFormat.parse(applicantDOB);
				    System.out.println("Old Format :   " + originalFormat.format(date));
				    System.out.println("New Format :   " + targetFormat.format(date));
				    applicantDOB = targetFormat.format(date);
				    System.out.println("applicantDOB :: " + applicantDOB);
				}catch(ParseException pse){
					applicantDOB = "1992-27-12";
				}
			}*/
			String applicantGender = (String)jsonObj.get("applicantGender");
			Boolean bankAadhaarSeeded = (Boolean)jsonObj.get("bankSeededAadhaar");
			System.out.println("bankAadhaarSeeded" + bankAadhaarSeeded);
			String applicantAddress = (String)jsonObj.get("applicantAddress");
			String applicantDistrict = (String)jsonObj.get("applicantDistrict");
			if(applicantDistrict.contains("#")){
				applicantDistrict = applicantDistrict.split("#")[1];
			}
			String applicantTaluka = (String)jsonObj.get("applicantTaluka");
			if(applicantTaluka.contains("#")){
				applicantTaluka = applicantTaluka.split("#")[1];
			}
			String applicantCity = (String)jsonObj.get("applicantCity");
			String applicantPinCode = (String)jsonObj.get("applicantPinCode");
			String applicantIsSalaried = (String)jsonObj.get("applicantIsSalaried");
			String applicantOccupation = "";
			try{
				applicantOccupation = (String)jsonObj.get("applicantOccupation");
				if(applicantOccupation.contains("#")){
					applicantOccupation = applicantOccupation.split("#")[1];
				}
			}catch(Exception ex){
				applicantOccupation = "";
			}
			String applicantIncome = "";
			try{
				applicantIncome = (String)jsonObj.get("applicantIncome");
			}catch(Exception ex){
				applicantIncome = "";
			}
			//String applicantCasteCategory = (String)jsonObj.get("applicantCasteCategory");
			String applicantIsPhysicallyHandicapped = (String)jsonObj.get("applicantIsPhysicallyHandicapped");
			String applicantDisabilityPercentage = "";
			try{
				applicantDisabilityPercentage = (String)jsonObj.get("applicantDisabilityPercentage");
			}catch(Exception ex){
				applicantDisabilityPercentage = "";
			}
			String applicantCaste = (String)jsonObj.get("applicantCaste");
			String applicantSubCaste = (String)jsonObj.get("applicantSubCaste");
			String applicantCasteCertificateNo = (String)jsonObj.get("applicantCasteCertificateNo");
			String applicantCasteOfficeName = "";
			try{
				applicantCasteOfficeName = (String)jsonObj.get("applicantCasteCertificateOfficeName");;
			}catch(Exception ex){
				applicantCasteOfficeName = "";
			}
			String applicantCasteDate = (String)jsonObj.get("applicantCasteCertificateDate");
			if(applicantCasteDate.contains("T")){
				int len = applicantCasteDate.indexOf("T");
				applicantCasteDate = applicantCasteDate.substring(0,len);
				System.out.println(applicantCasteDate);
			}
			
			Integer applicantCasteYear = (Integer)jsonObj.get("applicantCasteCertificateYear");
			String applicantCasteDistrict = (String)jsonObj.get("applicantCasteDistrict");
			if(applicantCasteDistrict.contains("#")){
				applicantCasteDistrict = applicantCasteDistrict.split("#")[1];
			}
			String applicantCasteTaluka = (String)jsonObj.get("applicantCasteTaluka");
			if(applicantCasteTaluka.contains("#")){
				applicantCasteTaluka = applicantCasteTaluka.split("#")[1];
			}
			String applicantCasteSubdivision = "";
			try{
				applicantCasteSubdivision = (String)jsonObj.get("applicantCasteCertificateSubdivision");
			}catch(Exception ex){
				applicantCasteSubdivision = "";
			}
			String applicantCasteIssueAuthority = "";
			try{
				applicantCasteIssueAuthority = (String)jsonObj.get("applicantCasteIssueAuthority");
			}catch(Exception ex){
				applicantCasteIssueAuthority = "";
			}
			
			String applicantCasteValidityNo = (String)jsonObj.get("applicantCasteValidityNo");
			String applicantValidityCertificateDate = (String)jsonObj.get("applicantCasteValidityCertificateDate");
			if(applicantValidityCertificateDate.contains("T")){
				int len = applicantValidityCertificateDate.indexOf("T");
				applicantValidityCertificateDate = applicantValidityCertificateDate.substring(0,len);
				System.out.println(applicantValidityCertificateDate);
			}
			
			
			//String applicantValidityCertificateYear = "2000";
			Integer applicantValidityCertificateYear = (Integer)jsonObj.get("applicantCasteValidityCertificateYear");
			String casteValidityDistrict = (String)jsonObj.get("casteValidityDistrict");
			if(casteValidityDistrict.contains("#")){
				casteValidityDistrict = casteValidityDistrict.split("#")[1];
			}
			String casteValidityTaluka = (String)jsonObj.get("applicantCasteValidityTaluka");
			if(casteValidityTaluka.contains("#")){
				casteValidityTaluka = casteValidityTaluka.split("#")[1];
			}
			String courseAppliedFor = (String)jsonObj.get("courseAppliedFor");
			if(courseAppliedFor.contains("#")){
				courseAppliedFor = courseAppliedFor.split("#")[1];
			}
			String courseType = (String)jsonObj.get("courseType");
			if(courseType.contains("#")){
				courseType = courseType.split("#")[1];
			}
			String appliedForYear = (String)jsonObj.get("appliedForYear");
			String fatherFirstName = (String)jsonObj.get("fatherFirstName");
			String fatherMiddleName = "";
			try{
				fatherMiddleName = (String)jsonObj.get("fatherMiddleName");
			}catch(Exception ex){
				fatherMiddleName = "";
			}
			String fatherLastName = (String)jsonObj.get("fatherLastName");
			String fatherDOB = (String)jsonObj.get("fatherDOB");
			if(fatherDOB.contains("T")){
				int len = fatherDOB.indexOf("T");
				fatherDOB = fatherDOB.substring(0,len);
				System.out.println(fatherDOB);
			}
			String fatherOccupation = (String)jsonObj.get("fatherOccupation");
			if(fatherOccupation.contains("#")){
				fatherOccupation = fatherOccupation.split("#")[1];
			}
			//String fatherMaritalStatus = (String)jsonObj.get("fatherMaritalStatus");
			String fatherPanNo = "";
			try{
				fatherPanNo = (String)jsonObj.get("fatherPanNo");
			}catch(Exception ex){
				fatherPanNo = "";
			}
			
			String fatherIncome = "";
			try{
				fatherIncome = (String)jsonObj.get("fatherIncome");
			}catch(Exception ex){
				fatherIncome = "";
			}
			String fatherIncomeCertificateNo = "";
			try{
				fatherIncomeCertificateNo = (String)jsonObj.get("fatherIncomeCertificateNo");
			}catch(Exception ex){
				fatherIncomeCertificateNo = "";
			}
			/*String fatherCasteCertificateNo = "";
			try{
				fatherCasteCertificateNo = (String)jsonObj.get("fatherCasteCertificateNo");
			}catch(Exception ex){
				fatherCasteCertificateNo = "";
			}*/
			String motherFirstName = (String)jsonObj.get("motherFirstName");
			String motherMiddleName = ""; 
			try{
				motherMiddleName = (String)jsonObj.get("motherMiddleName");
			}catch(Exception ex){
				motherMiddleName = "";
			}
			String motherLastName = (String)jsonObj.get("motherLastName");
			String motherDOB = (String)jsonObj.get("motherDOB");
			if(motherDOB.contains("T")){
				int len = motherDOB.indexOf("T");
				motherDOB = motherDOB.substring(0,len);
				System.out.println(motherDOB);
			}
			
			String motherOccupation = (String)jsonObj.get("motherOccupation");
			if(motherOccupation.contains("#")){
				motherOccupation = motherOccupation.split("#")[1];
			}
			//String motherMaritalStatus = (String)jsonObj.get("motherMaritalStatus");
			String motherPanNo = "";
			try{
				motherPanNo = (String)jsonObj.get("motherPanNo");
			}catch(Exception ex){
				motherPanNo = "";
			}
			String motherIncome = "";
			try{
				motherIncome = (String)jsonObj.get("motherIncome");
			}catch(Exception ex){
				motherIncome = "";
			}
			String motherIncomeCertificateNo = "";
			try{
				motherIncomeCertificateNo = (String)jsonObj.get("motherIncomeCertificateNo");
			}catch(Exception ex){
				motherIncomeCertificateNo = "";
			}
			/*String motherCasteCertificateNo = "";
			try{
				motherCasteCertificateNo = (String)jsonObj.get("motherCasteCertificateNo");
			}catch(Exception ex){
				motherCasteCertificateNo = "";
			}*/
			String joiningYear = "";
			try{
				joiningYear = (String)jsonObj.get("joiningYear");
			}catch(Exception ex){
				joiningYear = "";
			}
			String currentCourseState = (String)jsonObj.get("currentCourseState");
			String currentCourseDistrict = (String)jsonObj.get("currentCourseDistrict");
			if(currentCourseDistrict.contains("#")){
				currentCourseDistrict = currentCourseDistrict.split("#")[1];
			}
			String currentCourseTaluka = (String)jsonObj.get("currentCourseTaluka");
			if(currentCourseTaluka.contains("#")){
				currentCourseTaluka = currentCourseTaluka.split("#")[1];
			}
			String currentCourseCollegeName = (String)jsonObj.get("currentCourseCollegeName");
			if(currentCourseCollegeName.contains("#")){
				currentCourseCollegeName = currentCourseCollegeName.split("#")[1];
			}
			String currentCourseCollegeType = (String)jsonObj.get("currentCourseCollegeType");
			String currentCourseCourseName = (String)jsonObj.get("currentCourseCourseName");
			if(currentCourseCourseName.contains("#")){
				currentCourseCourseName = currentCourseCourseName.split("#")[1];
			}
			String currentCourseUniversityName = (String)jsonObj.get("currentCourseUniversityName");
			String currentCourseYear = (String)jsonObj.get("currentCourseYear");
			String isCurrentCourseFeesPaid = (String)jsonObj.get("isCurrentCourseFeesPaid");
			String currentCourseFeesPaid = "";
			try{
				currentCourseFeesPaid = (String)jsonObj.get("currentCourseFeesPaid");
			}catch(Exception ex){
				currentCourseFeesPaid = "";
			}
			String feesReceiptNo = "";
			try{
				feesReceiptNo = (String)jsonObj.get("feesReceiptNo");
			}catch(Exception ex){
				feesReceiptNo = "";
			}
			String currentCourseGrantType = "";
			try{
				currentCourseGrantType = (String)jsonObj.get("currentCourseGrantType");
				if(currentCourseGrantType.contains("#")){
					currentCourseGrantType = currentCourseGrantType.split("#")[1];
				}
			}catch(Exception ex){
				currentCourseGrantType = "";
			}
			String currentCourseCap = "";
			try{
				currentCourseCap = (String)jsonObj.get("currentCourseCap");
			}catch(Exception ex){
				currentCourseCap = "";
			}
			String currentCourseCollegeAdmissionNo = (String)jsonObj.get("currentCourseCollegeAdmissionNo");
			String currentCourseAdmissionDate = "";
			try{
				currentCourseAdmissionDate = (String)jsonObj.get("currentCourseAdmissionDate");
				if(currentCourseAdmissionDate.contains("T")){
					int len = currentCourseAdmissionDate.indexOf("T");
					currentCourseAdmissionDate = currentCourseAdmissionDate.substring(0,len);
					System.out.println(currentCourseAdmissionDate);
				}
			}catch(Exception ex){
				currentCourseAdmissionDate = "";
			}
			String applicant10thSchoolName = (String)jsonObj.get("applicant10thSchoolName");
			String applicant10thRollNo = (String)jsonObj.get("applicant10thRollNo");
			String applicant10thPassingBoard = (String)jsonObj.get("applicant10thPassingBoard");
			Integer applicant10thPassingYear = (Integer)jsonObj.get("applicant10thPassingYear");
			String applicant10thcertificateNumber = (String)jsonObj.get("applicant10thcertificateNumber");
			String applicant10thMarksObtained = (String)jsonObj.get("applicant10thMarksObtained");
			
			String applicant11thSchoolName = (String)jsonObj.get("applicant11thSchoolName");
			String applicant11thRollNo = (String)jsonObj.get("applicant11thRollNo");
			String applicant11thPassingBoard = (String)jsonObj.get("applicant11thPassingBoard");
			Integer applicant11thPassingYear = (Integer)jsonObj.get("applicant11thPassingYear");
			String applicant11thcertificateNumber = (String)jsonObj.get("applicant11thcertificateNumber");
			String applicant11thMarksObtained = (String)jsonObj.get("applicant11thMarksObtained");
			
			String applicant12thSchoolName = (String)jsonObj.get("applicant12thSchoolName");
			String applicant12thRollNo = (String)jsonObj.get("applicant12thRollNo");
			String applicant12thPassingBoard = (String)jsonObj.get("applicant12thPassingBoard");
			Integer applicant12thPassingYear = (Integer)jsonObj.get("applicant12thPassingYear");
			String applicant12thcertificateNumber = (String)jsonObj.get("applicant12thcertificateNumber");
			String applicant12thMarksObtained = (String)jsonObj.get("applicant12thMarksObtained");
			String dayScholarDetails = (String)jsonObj.get("applicantDayScholarDetails");
			String applicantHostelDetails = "";
			try{
				applicantHostelDetails = (String)jsonObj.get("applicantHostelDetails");
			}catch(Exception ex){
				applicantHostelDetails = "";
			}
			String hostelMaintenance = "";
			try{
				hostelMaintenance = (String)jsonObj.get("hostelMaintain");
			}catch(Exception ex){
				hostelMaintenance = "";
			}
			String hostelType = "";
			try{
				hostelType = (String)jsonObj.get("hostelType");
			}catch(Exception ex){
				hostelType = "";
			}
			String ishostelAided = "";
			try{
				ishostelAided = (String)jsonObj.get("hostelAided");
			}catch(Exception ex){
				ishostelAided = "";
			}
			String applicantHostelName = "";
			try{
				applicantHostelName = (String)jsonObj.get("applicantHostelName");
			}catch(Exception ex){
				applicantHostelName = "";
			}
			String applicantHostelAddress = "";
			try{
				applicantHostelAddress = (String)jsonObj.get("applicantHostelAddress");
			}catch(Exception ex){
				applicantHostelAddress = "";
			}
			String applicantHostelDistrict = "";
			try{
				applicantHostelDistrict = (String)jsonObj.get("applicantHostelDistrict");
				if(applicantHostelDistrict.contains("#")){
					applicantHostelDistrict = applicantHostelDistrict.split("#")[1];
				}
			}catch(Exception ex){
				applicantHostelDistrict = "";
			}
			String applicantHostelTaluka = "";
			try{
				applicantHostelTaluka = (String)jsonObj.get("applicantHostelTaluka");
				if(applicantHostelTaluka.contains("#")){
					applicantHostelTaluka = applicantHostelTaluka.split("#")[1];
				}
			}catch(Exception ex){
				applicantHostelTaluka = "";
			}
			String applicantIncomeCertificateNo = (String)jsonObj.get("applicantIncomeCertificateNo");
			Integer applicantIncomeCertificateYear = (Integer)jsonObj.get("applicantIncomeCertificateYear");
			Integer domicilecertificateyear = (Integer)jsonObj.get("applicantDomicileCertificateYear");
			String domicilecertificatestate = (String)jsonObj.get("applicantDomicileCertificateState");	
			String applicantDomicileCertificateNo = (String)jsonObj.get("applicantDomicileCertificateNo");
			
			sqlQuery = "INSERT INTO public.dbt_scholarship_postmatric_table("
					   + "\"IsDomicileofMaharashtra\", \"FirstName\", \"MiddleName\", \"LastName\", \"ADHAARNo\", \"MobileNo\", \"EmailID\", \"DateofBirth\", "
					   + "\"Gender\", \"IncomeCertificateNo\", \"CasteCertificateNo\", \"IsPhysicallyHandicapped\", \"DisabilityPercentage\", "
					   + "\"HostelAccommodation\", \"Salaried\", \"Occupation\", \"Income\", \"10thPassingBoard\", \"10thPassingYear\", "
					   + "\"10thcertificateNumber\", \"10thMarksObtained\", \"11thPassingBoard\", \"11thPassingYear\", \"11thcertificateNumber \", "
					   + "\"11thMarksObtained\", \"12thPassingBoard\", \"12thPassingYear\", \"12thcertificateNumber \", \"12thMarksObtained\", "
					   + "\"Caste\", \"SubCaste\"," 
					   + "\"District\", \"Taluka\", \"VillageCityTown\", "
					   + "\"PinCode\", \"CurrentCourseDistrict\", \"CurrentCourseTaluka\", \"CurrentCourseCollegeName\", \"CurrentCourseCourseName\", "
					   + "\"CurrentCourseUniversityName\", \"CurrentCourseYear\", \"CurrentCourseFeesPaid\", \"CurrentCourseGrantType\", \"CurrentCourseCAP\", "
					   + "\"CurrentCourseCollegeAdmissionNo\", \"CurrentCourseAdmissionDate\", \"CourseAppliedFor\", \"CourseType\", \"AppliedForYear\", "
					   + "\"JoiningYear\", \"FatherFirstName\", \"FatherMiddleName\", \"FatherLastName\", \"FatherDateofBirth\", \"FatherOccupation\", "
					   + "\"FatherPANNo\", \"FatherIncome\", \"FatherIncomeCertificateNo\", "
					   + "\"MotherFirstName\", \"MotherMiddleName\", \"MotherLastName\", \"MotherDateofBirth\", \"MotherOccupation\", "
					   + "\"MotherPANNo\", \"MotherIncome\", \"MotherIncomeCertificateNo\", "
					   + "\"ApplicantIncomeCertificarteYear\", "
					   + "\"User_Id\", \"app_id\", \"bankseededaadhaar\", \"applicantaddress\", \"casteofficename\", "
					   + "\"castecertificatedate\", \"castecertificateyear\", \"castecertificatedistrict\", \"castecertificatetaluka\", \"castecertificatesubdivision\", \"castecertificateauthority\", "
					   + "\"castevalidityno\", \"castevaliditydate\", \"castevalidityyear\", \"castevaliditydistrict\", \"castevaliditytaluka\", \"currentcoursecollegetype\", \"currentcoursestate\", \"currentcoursefeesreceiptno\", "
					   + "\"10thSchoolName\", \"10thRollNo\", \"11thSchoolName\", \"11thRollNo\", \"12thSchoolName\", \"12thRollNo\", \"isdayscholar\", \"ishostelmaintenance\", "
					   + "\"hosteltype\", \"ishostelaided\" , \"hostelname\", \"hosteladdress\", \"hosteldistrict\", \"hosteltaluka\", \"incomecertificateyear\", \"domicilecertificateno\", \"domicilecertificateyear\", \"domicilecertificatestate\", \"isfeespaid\""
					   + ")values('" + applicantDomicile + "', '" + applicantFirstName + "', '"
					   + applicantMiddleName + "','" + applicantLastName + "', '" + applicantAadhaarUID + "', '" + applicantMobileNo + "', '" + applicantEmailID + "', '" + applicantDOB + "', '"
					   + applicantGender + "', '" + applicantIncomeCertificateNo + "', '" + applicantCasteCertificateNo + "', '" + applicantIsPhysicallyHandicapped + "', '" 
					   + applicantDisabilityPercentage + "', '" + applicantHostelDetails + "', '" + applicantIsSalaried + "', '" + applicantOccupation + "', '" + applicantIncome + "', '"
					   + applicant10thPassingBoard + "', '" + applicant10thPassingYear + "', '" + applicant10thcertificateNumber + "', '" + applicant10thMarksObtained + "', '" 
					   + applicant11thPassingBoard + "', '" + applicant11thPassingYear + "', '" + applicant11thcertificateNumber + "', '" + applicant11thMarksObtained + "', '"
					   + applicant12thPassingBoard + "', '" + applicant12thPassingYear + "', '" + applicant12thcertificateNumber + "', '" + applicant12thMarksObtained + "', '"
					   + applicantCaste + "', '" + applicantSubCaste + "', '"
					   + applicantDistrict + "', '" + applicantTaluka + "', '"
					   + applicantCity + "', '" + applicantPinCode + "', '" + currentCourseDistrict + "', '" + currentCourseTaluka + "', '" + currentCourseCollegeName + "', '"
					   + currentCourseCourseName  + "', '" + currentCourseUniversityName + "', '" + currentCourseYear + "', '" + currentCourseFeesPaid + "', '" + currentCourseGrantType + "', '" 
					   + currentCourseCap + "', '" + currentCourseCollegeAdmissionNo + "', '" + currentCourseAdmissionDate + "', '" + courseAppliedFor + "', '" + courseType + "', '"
					   + appliedForYear + "', '" + joiningYear + "', '" 
					   + fatherFirstName + "', '" + fatherMiddleName + "', '" + fatherLastName + "', '" + fatherDOB + "', '" + fatherOccupation + "', '" + fatherPanNo + "', '" + fatherIncome + "', '" + fatherIncomeCertificateNo + "', '"
					   + motherFirstName + "', '" + motherMiddleName + "', '" + motherLastName + "', '" + motherDOB + "', '" + motherOccupation + "', '" + motherPanNo + "', '" + motherIncome + "', '" + motherIncomeCertificateNo + "', '"
					   + applicantIncomeCertificateYear + "', '" 
					   + userID + "', '" + applicationID + "', '" + bankAadhaarSeeded + "', '" + applicantAddress + "', '" + applicantCasteOfficeName + "', '"
					   + applicantCasteDate + "', '" + applicantCasteYear + "', '" + applicantCasteDistrict + "', '" + applicantCasteTaluka + "', '" + applicantCasteSubdivision + "', '" + applicantCasteIssueAuthority + "', '"
					   + applicantCasteValidityNo + "', '" + applicantValidityCertificateDate + "', '" + applicantValidityCertificateYear + "', '" + casteValidityDistrict + "', '" + casteValidityTaluka + "', '" + currentCourseCollegeType + "', '"
					   + currentCourseState + "', '" + feesReceiptNo + "', '" + applicant10thSchoolName + "', '" + applicant10thRollNo + "', '" + applicant11thSchoolName + "', '" + applicant11thRollNo +  "', '" + applicant12thSchoolName + "', '" + applicant12thRollNo + "', '"  
					   + dayScholarDetails + "', '" + hostelMaintenance + "', '" + hostelType + "', '" + ishostelAided + "', '" + applicantHostelName + "', '" + applicantHostelAddress + "', '" + applicantHostelDistrict + "', '" + applicantHostelTaluka + "', '"
					   + applicantIncomeCertificateYear + "', '" + applicantDomicileCertificateNo + "', '" + domicilecertificateyear + "', '" + domicilecertificatestate + "', '" + isCurrentCourseFeesPaid 
					   + "')"; 
				
				System.out.println(sqlQuery);
				if(sqlQuery.contains("''")){
					sqlQuery = sqlQuery.replaceAll("''","null");
				}
			System.out.println(sqlQuery);
			stmt.executeUpdate(sqlQuery);
			status = "true";
		}catch(JSONException jsonExp){
			System.out.println("Some error occurred :: " + jsonExp);
			jsonExp.printStackTrace();
			status = "false";
			sqlQuery = "delete from dbt_application_tracker where \"app_id\" = '" + applicationID + "';";
			try {
				stmt.executeUpdate(sqlQuery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(Exception ex){
			System.out.println("error :: " + ex);
			ex.printStackTrace();
			status = "false";
			sqlQuery = "delete from dbt_application_tracker where \"app_id\" = '" + applicationID + "';";
			try {
				stmt.executeUpdate(sqlQuery);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally{
			try
			{		
				conn.close();
				stmt.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return status;
	}
}
