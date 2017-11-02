/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : PreMatricFormDetails.java
* Author              : Ankit Bhasin/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 23, 2017
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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.newgen.utility.PGAdmin_DB_Connection;

import JSON.JSONObject;

public class PreMatricFormDetails {

	public String addPreMatricDetails(JSONObject jsonObj){
		
		String sqlQuery = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tempVal = -1;
		int tempVal2 = -1;
		int c = -1;
		int applicationID = 20170000;
		String status = "false";
		
		try{
		    conn = PGAdmin_DB_Connection.getConnection();	
		    stmt = conn.createStatement();
		    
		    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date date = new Date();
		    
		    sqlQuery = "select max(app_Id),max(id) from dbt_Application_Tracker";
		    rs = stmt.executeQuery(sqlQuery);
		    while(rs.next()){
		    	tempVal = Integer.parseInt(rs.getString(1));
		    	tempVal2 = Integer.parseInt(rs.getString(2));
		    }
			if(tempVal == -1 || tempVal2 == -1){
				c++;
				applicationID++;
			}else{
				tempVal++;
				applicationID = tempVal;
				tempVal2++;
				c = tempVal2;
			}
			sqlQuery = "insert into dbt_Application_Tracker (ID, app_Id, app_Submit_Date, app_Current_Stage, app_Remarks, app_SchemeID, app_SchemeType, user_ID, app_flag)"
					+ "values('" + c +"', '" + applicationID + "','"+ dateFormat.format(date) + "', 'cur002', 'InProcess', 'sch-002', 'PreMatric', 'ankit.bhasin', 'N');";
			stmt.executeUpdate(sqlQuery);
		    	
				
				String applicantSchoolName = (String)jsonObj.get("applicantSchoolName");
				String academicYear = (String)jsonObj.get("applicantAcademicYear");
			    String applicantDomicile = (String)jsonObj.get("applicantDomicile");
				String applicantFirstName = (String)jsonObj.get("applicantFirstName");
				String applicantMiddleName = "";
				try{
					 applicantMiddleName = (String)jsonObj.get("applicantMiddleName");
				}catch(NullPointerException npe){
					applicantMiddleName = null;
				}
				String applicantLastName = (String)jsonObj.get("applicantLastName");
				String saralNo = (String)jsonObj.get("SARALNO");
				String applicantAadhaarUID = (String)jsonObj.get("applicantAadhaarUID");
				String applicantMobileNo = (String)jsonObj.get("applicantMobileNo");
				String applicantEmailID = (String)jsonObj.get("applicantEmailID");
				String applicantDOB = (String)jsonObj.get("applicantDOB");
				String applicantGender = (String)jsonObj.get("applicantGender");
				String lastYearResult = (String)jsonObj.get("applicantlastYearResult");
				String studyStd = (String)jsonObj.get("applicantStudyingInStd");
				String parentAnnualIncome = (String)jsonObj.get("applicantParentAnnualIncome");
				String applicantAddress = (String)jsonObj.get("applicantAddress");
				String applicantDistrict = (String)jsonObj.get("applicantDistrict");
				String applicantTaluka = (String)jsonObj.get("applicantTaluka");
				String applicantCity = (String)jsonObj.get("applicantCity");
				String applicantPinCode = (String)jsonObj.get("applicantPinCode");
				String applicantCasteCategory = (String)jsonObj.get("applicantCasteCategory");
				String applicantCaste = (String)jsonObj.get("applicantCaste");
				String applicantSubCaste = (String)jsonObj.get("applicantSubCaste");
				//String applicantCasteCertificateNo = (String)jsonObj.get("applicantCasteCertificateNo");
				String applicantIsPhysicallyHandicapped = (String)jsonObj.get("applicantIsPhysicallyHandicapped");
				String applicantDisabilityPercentage = (String)jsonObj.get("applicantDisabilityPercentage");
				String fatherFirstName = (String)jsonObj.get("fatherFirstName");
				String fatherMiddleName = (String)jsonObj.get("fatherMiddleName");
				String fatherLastName = (String)jsonObj.get("fatherLastName");
				String fatherDOB = (String)jsonObj.get("fatherDOB");
				String fatherOccupation = (String)jsonObj.get("fatherOccupation");
				String fatherMaritalStatus = (String)jsonObj.get("fatherMaritalStatus");
				String fatherPanNo = (String)jsonObj.get("fatherPanNo");
				String fatherIncome = (String)jsonObj.get("fatherIncome");
				String fatherIncomeCertificateNo = (String)jsonObj.get("fatherIncomeCertificateNo");
				String fatherCasteCertificateNo = (String)jsonObj.get("fatherCasteCertificateNo");
				String motherFirstName = (String)jsonObj.get("motherFirstName");
				String motherMiddleName = (String)jsonObj.get("motherMiddleName");
				String motherLastName = (String)jsonObj.get("motherLastName");
				String motherDOB = (String)jsonObj.get("motherDOB");
				String motherOccupation = (String)jsonObj.get("motherOccupation");
				String motherMaritalStatus = (String)jsonObj.get("motherMaritalStatus");
				String motherPanNo = (String)jsonObj.get("motherPanNo");
				String motherIncome = (String)jsonObj.get("motherIncome");
				String motherIncomeCertificateNo = (String)jsonObj.get("motherIncomeCertificateNo");
				String motherCasteCertificateNo = (String)jsonObj.get("motherCasteCertificateNo");
				String applicantHostelDetails = (String)jsonObj.get("applicantHostelDetails");
				String applicantPanNo = (String)jsonObj.get("applicantPanNo");
				String bankName = (String)jsonObj.get("bankName");
				String bankBranchName = (String)jsonObj.get("bankBranchName");
				String accountType = (String)jsonObj.get("accountType");
				String bankAccountNo = (String)jsonObj.get("bankAccountNo");
				String bankDistrict = (String)jsonObj.get("bankDistrict");
				String bankTaluka = (String)jsonObj.get("bankTaluka");
				String bankBranchIFSCCode = (String)jsonObj.get("bankBranchIFSCCode");
				String applicantIncomeCertificateNo = (String)jsonObj.get("applicantIncomeCertificateNo");
				String applicantIncomeCertificateYear = (String)jsonObj.get("applicantIncomeCertificateYear");
				String applicantCasteCertificateYear = (String)jsonObj.get("applicantCasteCertificateYear");
				String applicantCasteCertificateNo1 = (String)jsonObj.get("applicantCasteCertificateNo1");
				String applicantDomicileYear = (String)jsonObj.get("applicantDomicileYear");
				String applicantDomicileState = (String)jsonObj.get("applicantDomicileState");
				String applicantDomicileCertificateNo = (String)jsonObj.get("applicantDomicileCertificateNo");
			
				sqlQuery = "INSERT INTO dbt_scholarship_prematric_table("
						+ "school_name, academic_year, isdomicileofmaharashtra, firstname, middlename,"
						+ " lastname, saral_no, adhaar_no, mobile_no, emailid, dateofbirth, gender, "
						+ "last_year_result, studying_in_std, parent_annual_income, "
						+ "castecategory, caste, subcaste, district, taluka, villagecitytown, pincode, "
						+ "isphysicallyhandicapped, disabilitypercentage, hostelaccommodation, fatherfirstname, fathermiddlename, "
						+ "fatherlastname, fatherdateofbirth, fatheroccupation, fathermaritalstatus, fatherpanno, fatherincome, "
						+ "fatherincomecertificateno, fathercastecertificateno, motherfirstname, mothermiddlename, motherlastname, "
						+ "motherdateofbirth, motheroccupation, mothermaritalstatus, motherpanno, motherincome, motherincomecertificateno, "
						+ "mothercastecertificateno, bankname, bankbranchname, accounttype, bankaccountno, bankdistrict, banktaluka, "
						+ "bankbranchifsccode, pan_no, incomecertificateno, applicantincomecertificarteyear, castecertificateno, "
						+ "applicantcastecertificateyear, applicantdomicileyear, applicantdomicilestate, applicantdomicilecertificateno, address, applicantID) values('"
						+ applicantSchoolName + "', '" + academicYear + "', '" + applicantDomicile + "', '" + applicantFirstName + "', '" + applicantMiddleName + "', '"
						+ applicantLastName + "', '" + saralNo + "', '" + applicantAadhaarUID + "', '" + applicantMobileNo + "', '" + applicantEmailID + "', '" + applicantDOB + "', '"
						+ applicantGender + "', '" + lastYearResult + "', '" + studyStd + "', '" + parentAnnualIncome + "', '" + applicantCasteCategory + "', '" + applicantCaste + "', '"
						+ applicantSubCaste + "', '" + applicantDistrict + "', '" + applicantTaluka + "', '" + applicantCity + "', '" + applicantPinCode + "', '" + applicantIsPhysicallyHandicapped + "', '"
						+ applicantDisabilityPercentage + "', '" + applicantHostelDetails + "', '" + fatherFirstName + "', '" + fatherMiddleName + "', '" + fatherLastName + "', '" + fatherDOB + "', '"
						+ fatherOccupation + "', '" + fatherMaritalStatus + "', '" + fatherPanNo + "', '" + fatherIncome + "', '" + fatherIncomeCertificateNo + "', '" + fatherCasteCertificateNo + "', '"
						+ motherFirstName + "', '" + motherMiddleName + "', '" + motherLastName + "', '" + motherDOB + "', '" + motherOccupation + "', '" + motherMaritalStatus + "', '" + motherPanNo + "', '" 
						+ motherIncome + "', '" + motherIncomeCertificateNo + "', '" + motherCasteCertificateNo + "', '" + bankName + "', '" + bankBranchName + "', '" + accountType + "', '" + bankAccountNo + "', '"
						+ bankDistrict + "', '" + bankTaluka + "', '" + bankBranchIFSCCode + "', '" + applicantPanNo + "', '" + applicantIncomeCertificateNo + "', '" + applicantIncomeCertificateYear + "', '" 
						+ applicantCasteCertificateNo1 + "', '" + applicantCasteCertificateYear + "', '" + applicantDomicileYear + "', '" + applicantDomicileState + "', '" + applicantDomicileCertificateNo + "', '" + applicantAddress + "', '" + applicationID + "');";
				System.out.println(sqlQuery);
				stmt.executeUpdate(sqlQuery);
				status = "true";
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return status;
	}
}
