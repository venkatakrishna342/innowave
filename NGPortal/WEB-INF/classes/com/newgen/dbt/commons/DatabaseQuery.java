/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DatabaseQuery.java
* Author              : Varun Saddi
* Date written
* (DD/MM/YYYY)        : June 04, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/


/**
 * Provides the classes used to define constant parameters
 */
package com.newgen.dbt.commons;

/**
 * <h1>DatabaseQuery</h1>
 * <p>
 * This file covers all the queries used in various transactions.
 * 
 * @author Varun Saddi
 * @version 1.0
 * @since   2017-06-04
 *
 */
public class DatabaseQuery {
	
	
	//change on 13th July 2017 
	//change on 20th July 2017
	public String getIntegrationDetails_AutoPopulate() {
		return "{call dbt_setIntegrationDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		
		
		/*return "{call dbt_setIntegrationDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";*/
	}
	
	public String getSetFormDetails() {
	    return "{call dbt_setFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdateFormDetails() {
	    return "{call dbt_updateFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	//change on 20th July 2017
	//change on 25th July 2017
	public String getSetPostMatricFormDetails() {
		return "{call dbt_setpostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		/*return "{call dbt_setpostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";*/
		
	    /*return "{call dbt_setpostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";*/
	}
	
	public String getUpdatePostMatricFormDetails() {
	    return "{call dbt_updatepostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
	    		+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getSetTribalPostMatricFormDetails() {
	    return "{call dbt_setTribalpostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdateTribalPostMatricFormDetails() {
	    return "{call dbt_updateTribalpostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getSetHTPostMatricFormDetails() {
	    return "{call dbt_setHigherAndTechnicalPostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdateHTPostMatricFormDetails() {
	    return "{call dbt_updateHigherAndTechnicalPostMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getSetSEFormDetails() {
	    return "{call dbt_setSchoolEducationFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdateSEFormDetails() {
	    return "{call dbt_updateSchoolEducationFormDetails(?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getSetPreMatricFormDetails() {
	    return "{call dbt_setpreMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdatePreMatricFormDetails() {
	    return "{call dbt_updatePreMatricFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getSetTribalPreMatricFormDetails() {
	    return "{call dbt_setpreMatricTribalFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdateTribalPreMatricFormDetails() {
	    return "{call dbt_updatePreMatricTribalFormDetails(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	}
	
	public String getUpdateESCApplicationTrackerDetails() {
	    return "update esc_application_tracker_dtl set app_flag = 'U' where app_id = ?";
	}
	
	public String getUpdateESCApproverDashboardDetails() {
	    return "update esc_application_approver_dashboard_dtl set app_status = 'COMPLETED', authority_remarks = ?"
	    		+ " where app_id = ? and authority_user_id = ?";
	}
	
	public String getUserApplicationDraftCount() {
	    return "select count(1) from dbt_user_applications_draft where app_id = ?";
	}
	
	public String getSetScholarshipDraftDetails() {
	    return "{call dbt_setScholarshipDraftDetails(?,?,?,?,?)}";
	}
	
	public String getUpdateScholarshipDraftDetails() {
	    return "{call dbt_updateScholarshipDraftDetails(?,?,?,?,?)}";
	}
	
	//change on 9th July
	public String getSetScholarshipTrackerDetails() {
	    return "{call dbt_setScholarshipTrackerDetails(?,?,?,?,?)}";
		//return "{call dbt_setScholarshipTrackerDetails(?,?,?,?)}";
	}
	
	public String getDeleteUserApplicationDraft() {
	    return "delete from dbt_user_applications_draft where app_id= ?";
	}
	
	public String getDeleteTempAppId() {
	    return "call dbt_deleteTempApp_ID(?)";
	}
	
	public String getUpdateAppDocDetails() {
	    return "update dbt_document_dtl set app_id = ? where user_id = ? and app_id = ?";
	}
	
	public String getUserPasswordCount() {
	    return "select count(user_password) as passCount from dbt_usercredential_dtl where user_id = ? and user_password = ?";
	}
	
	public String getUpdateUserPassword() {
	    return "update dbt_usercredential_dtl set user_password=? where user_id=?";
	}
	
	public String getProfileDocMasterData() {
	    return "select doc_type,doc_id,doc_name, poi, poa, por, dob from doc_master_table order by doc_name";
	}
	
	public String getUserProfileDocData() {
	    return "select doc_master_table.doc_type, doc_num, reg_date, reg_authority from doc_repository_dtl,"
	    		+ " doc_master_table where user_id=? and id=? and doc_repository_dtl.id=doc_master_table.doc_id";
	}
	
	public String getSetUserCredentials() {
	    return "insert into dbt_usercredential_dtl(user_id, user_password, useralive, creation_dt,user_type) values(?,?,'Y',current_timestamp(),'A')";
	}
	
	public String getUserCredentials() {
	    return "select user_id,password from user_credintial_dtl where user_id = ?";
	}
	
	public String getSetUserAadhaarProfileDetails() {
	    return "INSERT INTO dbt_userprofile_dtl( user_id, aadhaar_no, full_name, relationType,careof, dob, age, mobile_no, email_id, gender, house, street,"
	    		+ " village_town, post_office, landmark, sub_district, district, state, pincode, profile_pic, profile_type, npci_status)"
	    		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public String getSetUserProfileDetails() {
	    return "insert into dbt_userprofile_dtl( user_id, eid_pce_no, full_name, careof, dob, age, mobile_no,"
	    		+ " email_id, gender, house, street, village_town, post_office, landmark, sub_district, district,"
	    		+ " state, pincode, profile_pic, c_house, c_street, c_village_town, c_locality, c_post_office,"
	    		+ " c_landmark, c_sub_district, c_district, c_state, c_pincode, father, isFatherAlive, mother,"
	    		+ " isMotherAlive, guardian,sameAddress,relationType) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	}
	
	public String validateDuplicacyNonAadhaar() {
	    	    return "select lower(trim(full_name)),lower(trim(dob)) from dbt_userprofile_dtl where full_name = ?  and dob = ? and mobile_no = ?";
	}
	public String getSetUserAadhaarBankDetails() {
		return "insert into dbt_bankdetails_dtl(branch_name, user_id) values(?, ?)";
	}
	
	public String getSetUserBankDetails() {
	    return "insert into dbt_bankdetails_dtl( bank_acc,branch_name,ifsc_code,bankAdd,user_id) values(?,?,?,?,?)";
	}
	
	public String getUpdateUserBankDetails() {
	    return "update dbt_bankdetails_dtl set bank_acc=?, branch_name=?, ifsc_code=?, bankAdd=? where user_id=?";
	}
	
	public String getSetUserDocDetails() {
	    return "insert into doc_repository_dtl(doc_id, doc_path, user_id, doc_num) values(?,?,?,?)";
	}
	
	public String getUpdateUserDocDetails() {
	    return "update  doc_repository_dtl set doc_path=?, doc_num=?, reg_date=?, reg_authority=? where id=? and user_id=?";
	}
	
	public String getProfileDocId() {
	    return "select doc_id from doc_master_table where doc_type=?";
	}
	
	public String getSetUserNotification() {
	    return "insert into dbt_user_notification_dtl (user_id, notification_type, subject, message, date_time, deleted_flag)"
	    		+ " Values(?, ?, ?, ?, ?, 'N')";
	}
	
	public String getUserNotifications() {
	    return "SELECT slno, user_id, notification_type, subject, message, date_time, deleted_flag from"
	    		+ " dbt_user_notification_dtl WHERE user_id = ? and deleted_flag = 'N'";
	}
	
	public String getDeleteUserNotification() {
	    return "update dbt_user_notification_dtl set deleted_flag = 'Y' where slno = ?";
	}
	
	public String getCheckUserCredentials() {
	    return "select uc.user_id, uc.user_password, ud.full_name, ud.profile_pic, ud.user_type from dbt_usercredential_dtl uc,"
	    		+ " dbt_userprofile_dtl ud  where uc.user_id = ud.user_id and uc.user_id = ?";
	}
	
	public String getSetUserConnection() {
	    return "insert into dbt_userconnection_dtl(user_id, user_type, user_password, client_ip, login_datetime)"
	    		+ " values(?, ?, ?, ?, current_timestamp())";
	}
	
	public String getSetUserConnectionHistory() {
	    return "insert into dbt_connection_history_dtl(user_id, client_ip, login_datetime) values(?, ?, current_timestamp())";
	}
	
	public String getDeleteUserConnection() {
	    return "delete from dbt_userconnection_dtl where  user_id=?";
	}
	
	public String getCheckUserProfileType() {
	    return "select user_id,npci_status,profile_type from dbt_userprofile_dtl where user_id=? ";
	}
	
	public String getUserAadhaarProfileDetails() {
	    return "select aadhaar_no, full_name, careof, dob, age, mobile_no, email_id, gender, house, street, village_town,"
	    		+ " post_office, landmark, district, state, pincode,sameAddress, c_house, c_street, c_village_town, c_post_office,"
	    		+ " c_landmark, c_district, c_state, c_pincode, eid_pce_no, profile_pic, profile_type, poi_doc, poa_doc,"
	    		+ " por_doc, dob_doc, npci_status, mother, isMotherAlive, father, isFatherAlive,relationType, guardian"
	    		+ " from dbt_userprofile_dtl  where dbt_userprofile_dtl.user_id = ?";
	}
	
	public String getUserNonAadhaarProfileDetails() {
	    return "select aadhaar_no, full_name, careof, dob, age, mobile_no, email_id, gender, house, street, village_town,"
	    		+ " post_office, landmark, district, state, pincode,sameAddress, c_house, c_street, c_village_town, c_post_office,"
	    		+ " c_landmark, c_district, c_state, c_pincode, eid_pce_no, profile_pic, profile_type, poi_doc, poa_doc,"
	    		+ " por_doc, dob_doc, npci_status, mother, isMotherAlive, father, isFatherAlive, relationType,guardian,"
	    		+ " dbt_bankdetails_dtl.bank_acc, dbt_bankdetails_dtl.branch_name, dbt_bankdetails_dtl.ifsc_code,"
	    		+ " dbt_bankdetails_dtl.bankAdd  from dbt_userprofile_dtl, dbt_bankdetails_dtl"
	    		+ "  where dbt_userprofile_dtl.user_id = ? and dbt_bankdetails_dtl.user_id = dbt_userprofile_dtl.user_id";
	
	}
	
	public String getUserProfileDocDataForEditProfile() {
	   return "select doc_master_table.doc_type, doc_num, reg_date, reg_authority, doc_path from doc_repository_dtl,"
	    		+ " doc_master_table where user_id=? and doc_repository_dtl.doc_id=? and"
	    		+ " doc_repository_dtl.doc_id = doc_master_table.doc_id";
	}
	
	public String getUpdateUserProfile() {
	    return "update dbt_userprofile_dtl set full_name=?, careof=?, dob=?, age=?, mobile_no=?, email_id=?, gender=?,"
	    		+ " house=?, street=?, village_town=?, post_office=?, landmark=?, district=?, state=?, pincode=?,"
	    		+ " c_house=?, c_street=?, c_village_town=?, c_locality=?, c_post_office=?, c_landmark=?, c_district=?,"
	    		+ " c_state=?, c_pincode=?, father=?, mother=?, guardian=?, profile_pic=? where user_id=?";
	}
	
	public String getSelectinfoCheck(){
		return "select lower(trim(full_name)),lower(trim(dob)) from dbt_userprofile_dtl where full_name = ?  and dob = ? and mobile_no = ?";
	}
	
	public String getEscApplicationData() {
	    return "{call dbt_residentDetails_get(?,?,?,?)}";
	}
	
	public String getUpdateApplicationStatus() {
	    return "UPDATE esc_application_approver_dashboard_dtl SET authority_decision = ?, authority_remarks = ?,"
	    		+ " authority_action_datetime = NOW(), app_status = 'RUNNING', verification_status = ?"
	    		+ " where ibps_registration_no = ? and app_id = ? and app_scheme_id = ? and authority_level = ?";
	}
	
	public String getCheckUserId() {
	    return "select user_id from dbt_usercredential_dtl where user_id = ?";
	}
	
	public String getCheckAadhaar() {
	    return "select aadhaar_no from dbt_userprofile_dtl where aadhaar_no = ?";
	}
	
	public String getCheckEID() {
	    return "select aadhaar_no from dbt_userprofile_dtl where eid_pce_no = ?";
	}
	
	public String getCategoryDetails() {
	    return "select schemeid,SchemeName,schemetype,SchemeStatus, Department from dbt_scheme_master order by Department, SchemeName";
	}
	
	public String getDepartmentDetails() {
	    return "select schemeid,SchemeName,schemetype,SchemeStatus, Department from dbt_scheme_master where Department = ? order by Department,SchemeName";
	}
	
	public String getUserPendingCases() {
	    return "select a.app_id, a.app_status, b.SchemeName, b.SchemeId, c.scheme_type, c.app_serviceCategorty,"
	    		+ " DATE_FORMAT(c.app_submit_datetime, '%Y-%m-%d') app_submit_datetime, a.ibps_registration_no from esc_application_approver_dashboard_dtl a inner join"
	    		+ " dbt_scheme_master b ON b.SchemeId = a.app_scheme_id inner join dbt_user_application_status_dashboard_dtl c"
	    		+ " on c.app_id = a.app_id where lower(a.app_status) = lower('Modify') and a.authority_user_id = ?";
	}
	
	public String getApplicationStatus() {
	    return "SELECT dbtr.app_id, scm.SchemeName, scm.SchemeId, dbtr.scheme_type, dbtr.app_serviceCategorty,"
	    		+ " DATE_FORMAT(dbtr.app_submit_datetime, '%d/%m/%Y') app_submit_date, dbte.app_stage_code app_status from"
	    		+ " dbt_user_application_status_dashboard_dtl dbtr INNER JOIN dbt_scheme_master scm ON"
	    		+ " scm.SchemeId = dbtr.app_scheme_id INNER JOIN esc_application_tracker_dtl dbte ON"
	    		+ " dbte.app_id = dbtr.app_id where dbtr.user_id = ? AND dbtr.app_id = ?";
	}
	
	public String getUserRepositoryDetails() {
	    return " select drd.doc_id, drd.doc_path, drd.doc_num, drd.reg_date, drd.reg_authority,drd.document_id, "+
				   " (select dmt.doc_type from doc_master_table dmt where dmt.doc_id = drd.doc_id) as docType, "+
				   " (select dmt.doc_name from doc_master_table dmt where dmt.doc_id = drd.doc_id) as docName "+
				   " from doc_repository_dtl drd where user_id = ?";
	}
	/* changed by swarnadip : 22/07/2017 */
	public String getApplicationWorkflow() {
	    //return "select scheme_id, stage_code, stage_name from esc_app_workflow_m where scheme_id = ?";
		return "select scheme_id, stage_code, stage_name from esc_app_workflow_m";
	}
	
	public String getUserDraftsCases() {
	    return "select app_id, scheme_id, scheme_type, scheme_name, app_serviceCategory, DATE_FORMAT(app_submit_datetime, '%Y-%m-%d') app_submit_datetime from"
	    		+ " dbt_user_applications_draft where user_id = ? order by app_submit_datetime desc "; 
	}
	
	public String getSetRTSDocDetails() {
	    return "insert into dbt_document_dtl(app_id, document_type, document_path, user_id, document_flag, rts_flag)"
	    		+ " values(?, ?, ?, ?, 'N', ?);"; 
	}
	
	public String getCheckUserEmailId() {
	    return "select user_id, mobile_no, email_id, dob from dbt_userprofile_dtl where email_id = ?";
	}
	
	public String getCheckUserMobile() {
	    return "select user_id, mobile_no, email_id, dob from dbt_userprofile_dtl where mobile_no = ?";
	}
	
	public String getSetVerificationOTPDetails() {
	    return "insert into dbt_user_otp_details (user_id, otp_id, otp_value, otp_generation_time, otp_expiry_time)"
	    		+ " values(?, ?, ?, NOW(), NOW() + INTERVAL (select expiry_time_min from dbt_otp_m  where "
	    		+ "otp_id = ?) MINUTE)";
	}
	
	public String getVerifyOTP() {
	    return "select otp_value, otp_expiry_time from dbt_user_otp_details where user_id = ? and"
	    		+ " otp_id = (select otp_id from dbt_otp_m where LOWER(otp_type) = LOWER(?) )"
	    		+ " order by otp_generation_time desc Limit 1";
	}
	
	public String getOTPExpiry() {
	    return "select count(*) from dbt_user_otp_details where NOW() < ?";
	}
	
	//***********************query for notification count,pending app count and applied app ****************************//
	public String getNotificationCount() {
	    return "select count(*) as count from dbt_user_notification_dtl where user_id=? and deleted_flag='N' ";
	}
	public String getPendinAppCount() {
		return "select count(a.app_id) as count from esc_application_approver_dashboard_dtl a "
				+ "inner join dbt_scheme_master b ON b.SchemeId = a.app_scheme_id inner join dbt_user_application_status_dashboard_dtl c "
				+ "on c.app_id = a.app_id where lower(a.app_status) = lower('Modify') and a.authority_user_id = ?";
	}
	public String getAppliedAppCount() {
	
		return "select count(*) as count from dbt_user_application_status_dashboard_dtl where user_id = ?";

	}
	public String getDraftCount() {
		
		return "select count(*) as count from dbt_user_applications_draft where user_id = ?";
		
	}
	public String getlastLogin() {
		
		return "SELECT DISTINCT DATE_FORMAT(login_datetime,'%d/%m/%Y %H:%m:%s') as lastlogin FROM dbt_connection_history_dtl where user_id = ? ORDER BY DATE_FORMAT(login_datetime,'%d/%m/%Y %H:%m:%s') DESC LIMIT 1,1;";
		
	}




	//*****************************************************************************************************************//
	//***************************************login details ************************************************//
	public String getCurrentUserConnectionDtl() {
	    return "select user_id from dbt_userconnection_dtl where user_id=?";
	}
	public String deleteCurrentUserConnectionDtl(){
		return "delete from dbt_userconnection_dtl where user_id=?";
	}
	public String insertUserConnectionDtl(){
		return "insert into dbt_userconnection_dtl(user_id,user_type,user_password,client_ip,login_datetime) values(?,?,?,?,current_timestamp())";
	}
	public String updateUserConnectionDtl(){
		return "update dbt_userconnection_dtl set login_datetime=current_timestamp() where user_id=?";
	}
	public String insertHistroyDtl(){
		return "insert into dbt_connection_history_dtl(user_id,client_ip,login_datetime) values(?,?,current_timestamp())";
	}
	
	//****************************************************************************************************//
	
	public String getUserAppliedSchemeDetails() {	
		return "SELECT dbtr.app_id, DATE_FORMAT(dbtr.app_submit_datetime, '%d/%m/%y %H:%i') app_submit_date, dbtr.user_id, "+
					" dbtr.pref_flag, sm.SchemeName SchemeName, dbtr.app_scheme_id FROM  dbt_user_application_status_dashboard_dtl dbtr "+ 
					" INNER JOIN dbt_scheme_master sm ON dbtr.app_scheme_id = sm.SchemeId WHERE dbtr.user_id= ?"+
					" ORDER BY dbtr.app_submit_datetime DESC";	
	}
	
	public String getRepositoryDocTypeMasterList() {
	    return "select doc_id, doc_type, doc_name from doc_master_table";
	}
	// changes on 13th july 2017
	public String getAddUserRepositoryDocDetails() {
	    return "insert into doc_repository_dtl (doc_path, doc_id, user_id, doc_num, reg_date, reg_authority, document_id)"+
				   " Values(?, (select doc_id from doc_master_table where doc_type = ?), ?, '', (select CURDATE()), '', null)";
	}
	
	public String getMaxSchemePref() {
	    return "select max(pref_flag) as HighestPref from dbt_user_application_status_dashboard_dtl where user_id ="
	    		+ " (Select user_id from dbt_user_application_status_dashboard_dtl where app_id = ?)";
	}
	
	public String getSameSchemePrefCount() {
	    return "select count(app_id) as SimilarPrefCount from dbt_user_application_status_dashboard_dtl where pref_flag = ?"
	    		+ " and user_id = (Select user_id from dbt_user_application_status_dashboard_dtl where app_id = ?)";
	}
	
	public String getLowerSchemePrefUpdate() {
	    return "update dbt_user_application_status_dashboard_dtl set pref_flag = pref_flag - 1 where pref_flag > ? and"
	    		+ " user_id = (Select user_id from dbt_user_application_status_dashboard_dtl where app_id = ?)";
	}
	
	public String getAllAppIdPref() {
	    return "select app_id, pref_flag from dbt_user_application_status_dashboard_dtl where user_id = (Select user_id from dbt_user_application_status_dashboard_dtl where app_id = ?)";
	}
	
	public String getInstNotificationCount() {
	    return "select count(*) as count from dbt_user_notification_dtl where user_id=? and deleted_flag='N' ";
	}
	
	public String getInstPendingCount(){
		/*return "SELECT  count(eapp.app_id) as count FROM esc_application_approver_dashboard_dtl eapp  "+
				"INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId "+ 
				"Inner JOIN dbt_user_application_status_dashboard_dtl sd ON sd.app_id = eapp.app_id "+
				"INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id "+
				"WHERE eapp.authority_user_id= ? AND (eapp.app_status = 'NEW' OR eapp.app_status = 'MODIFY') ;";*/
		//return "SELECT  count(eapp.app_id ) as count FROM esc_application_approver_dashboard_dtl eapp  INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id WHERE eapp.authority_user_id= ? AND (eapp.app_status = 'NEW' OR eapp.app_status = 'MODIFY')";
		return "SELECT  count(eapp.app_id ) as count FROM esc_application_approver_dashboard_dtl eapp  WHERE eapp.authority_user_id= ? AND (eapp.app_status = 'NEW' OR eapp.app_status = 'MODIFY')";
	}
	
	public String getApprovedAppCount(){
		return "select count(1) as count from (select authority_user_id, authority_id from dbt_user_application_status_dashboard_dtl sd  "
				+" INNER JOIN esc_application_approver_dashboard_dtl eapp on eapp.app_id = sd.app_id and sd.app_status = 'Approve') "
				+" p inner join  "
				+" (select user_id, role_id from dbt_usercredential_dtl a  "
				+" inner join dbt_institute_userprofile b on a.user_index = b.user_index  "
				+" where b.institute_id = ? and role_id = ?) "
				+"  x on p.authority_user_id = x.user_id and p.authority_id = x.role_id ";
	}
	
	public String getSanctionedAppCount(){
		return "select count(1) as count from (select authority_user_id, authority_id from dbt_user_application_status_dashboard_dtl sd  "
				+" INNER JOIN esc_application_approver_dashboard_dtl eapp on eapp.app_id = sd.app_id and sd.app_status = 'Sanction') "
				+" p inner join  "
				+" (select user_id, role_id from dbt_usercredential_dtl a  "
				+" inner join dbt_institute_userprofile b on a.user_index = b.user_index  "
				+" where b.institute_id = ? and role_id = ?) "
				+"  x on p.authority_user_id = x.user_id and p.authority_id = x.role_id ";
		}
	
	public String getDisbursedAppCount(){
		return "select count(1) as count from (select authority_user_id, authority_id from dbt_user_application_status_dashboard_dtl sd  "
				+" INNER JOIN esc_application_approver_dashboard_dtl eapp on eapp.app_id = sd.app_id and sd.app_status = 'Disburse') "
				+" p inner join  "
				+" (select user_id, role_id from dbt_usercredential_dtl a  "
				+" inner join dbt_institute_userprofile b on a.user_index = b.user_index  "
				+" where b.institute_id = ? and role_id = ?) "
				+"  x on p.authority_user_id = x.user_id and p.authority_id = x.role_id ";
		}
	
	public String getRejectedAppCount(){
		return "select count(1) as count from (select authority_user_id, authority_id from dbt_user_application_status_dashboard_dtl sd  "
				+" INNER JOIN esc_application_approver_dashboard_dtl eapp on eapp.app_id = sd.app_id and sd.app_status = 'Reject') "
				+" p inner join  "
				+" (select user_id, role_id from dbt_usercredential_dtl a  "
				+" inner join dbt_institute_userprofile b on a.user_index = b.user_index  "
				+" where b.institute_id = ? and role_id = ?) "
				+"  x on p.authority_user_id = x.user_id and p.authority_id = x.role_id ";
		}
	
	public String get_CheckApp_VerifyRemarks(){
		return "SELECT count(app_id) as count FROM esc_application_verification_remarks eapp where app_id = ? ";
	}

	public String get_InsertCheckApp_VerifyRemarks() {
		return "insert into esc_application_verification_remarks (personal_dtl, income_dtl, personal_eligibility_dtl, spouse_dtl, caste_dtl, parent_dtl, course_dtl,"
				+ " caste_validity_dtl, education_dtl, school_dtl, hostel_dtl, app_id) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	public String get_UpdateApp_VerifyRemarks() {
		return "update esc_application_verification_remarks set personal_dtl = ?, income_dtl = ?, personal_eligibility_dtl = ?, spouse_dtl = ?, caste_dtl = ?,"
				+ " parent_dtl = ?, course_dtl = ?, caste_validity_dtl = ?, education_dtl = ?, school_dtl = ?, hostel_dtl = ? where app_id = ? ";
	}
	
	public String get_InstituteProcessedApplications() {
		/*return "select ibps_registration_no, authority_user_id, authority_id, authority_decision, authority_remarks,"
				+ "authority_action_datetime from esc_application_approver_dashboard_dtl where app_status = ? LIMIT 10 ";*/
		return "select ibps_registration_no, authority_user_id, authority_id, authority_decision, authority_remarks,"
				+ " authority_action_datetime, verification_status, escRem.personal_dtl as personalremark,"
				+ " escRem.spouse_dtl as spouseremark, escRem.income_dtl as incomeremark, escRem.personal_eligibility_dtl"
				+ " as eligibilityremark, escRem.caste_dtl as casteremark, escRem.permanent_add_dtl as addressremark,"
				+ " escRem.corres_add_dtl as corresaddressremark, escRem.parent_dtl as parentremark,"
				+ " escRem.course_dtl as courseremark, escRem.caste_validity_dtl as castevalidityremark,"
				+ " escRem.fees_dtl as feesremark, escRem.education_dtl as educationremark,"
				+ " escRem.school_dtl as schoolremark, escRem.hostel_dtl as hostelremark from"
				+ " esc_application_approver_dashboard_dtl escApp LEFT JOIN esc_application_verification_remarks escRem"
				+ " ON escApp.app_id = escRem.app_id where app_status = ? LIMIT 10";
	}

	public String update_ProcessedApplicationStatus() {
		return "update esc_application_approver_dashboard_dtl set app_status = ? where authority_id = ? and"
				+ " ibps_registration_no = ?";
	}
	
	public String getAutoPopulateDataSP(){
		return "{call dbt_autopopulatedata_get(?)}";
	}
	
	public String getAutoPopulateData(){
		return "SELECT "+
				" upro.aadhaar_no,upro.full_name,upro.mobile_no,upro.email_id,upro.dob,upro.gender,upro.house,upro.street, "+
				" upro.post_office,upro.landmark,upro.state,upro.district,upro.pincode,upro.village_town,upro.sub_district, "+
				" upro.age,upro.npci_status,upro.sameAddress,upro.c_house,upro.c_street,upro.c_post_office,upro.c_landmark,upro.c_state,upro.c_district, "+
				" upro.c_pincode,upro.c_village_town,upro.c_sub_district,upro.isFatherAlive,upro.isMotherAlive,upro.father,upro.mother,upro.guardian, "+ 
				" "+
				" income.is_rts_seeded_data income_is_rts_seeded_data,income.cert_barcode income_cert_barcode, "+
				" income.name_on_cert income_name_on_cert,DATE_FORMAT(income.cert_date, '%d/%m/%Y') income_cert_date, "+
				" income.cert_number income_cert_number,TRIM(income.issuing_authority) income_issuing_authority, "+
				" income.family_income income_family_income,income.verification_status income_verification_status, "+
				" income.is_income_certificate income_is_income_certificate, "+
				"  "+
				" dom.is_rts_seeded_data domicile_is_rts_seeded_data,dom.cert_barcode domicile_cert_barcode, "+
				" dom.name_on_cert domicile_name_on_cert,DATE_FORMAT(dom.cert_date, '%d/%m/%Y') domicile_cert_date,dom.cert_number domicile_cert_number, "+
				" dom.issuing_authority domicile_issuing_authority,dom.is_domicile domicile_is_domicile, "+
				" dom.verification_status domicile_verification_status, "+
				" dom.is_domicile_certificate domicile_is_domicile_certificate, "+
				"  "+
				" dis.is_rts_seeded_data disaility_is_rts_seeded_data,dis.cert_barcode disaility_cert_barcode, "+
				" dis.name_on_cert disaility_name_on_cert,DATE_FORMAT(dis.cert_date, '%d/%m/%Y') disaility_cert_date, "+
				" dis.cert_number disaility_cert_number,dis.issuing_authority disaility_issuing_authority, "+
				" dis.is_disabled disaility_is_disabled,dis.disability_type disaility_type, "+
				" dis.disability_category disaility_category,dis.disability_percent disaility_percent, "+
				" dis.disability_validity_year disaility_validity_year,dis.reader_opted disaility_reader_opted, "+
				" dis.verification_status disaility_verification_status, "+
				" dis.is_disability_certificate disaility_is_disability_certificate, "+
				"  "+
				" caste.is_rts_seeded_data caste_is_rts_seeded_data,caste.cert_barcode caste_cert_barcode, "+
				" caste.name_on_cert caste_name_on_cert,DATE_FORMAT(caste.cert_date, '%d/%m/%Y') caste_cert_date, "+
				" caste.cert_number caste_cert_number,caste.issuing_authority caste_issuing_authority, "+
				" caste.caste_category caste_category,caste.caste caste_caste, "+
				" caste.verification_status caste_verification_status, "+
				" caste.is_caste_certificate caste_is_caste_certificate, "+
				"  "+
				" ssc.total_marks ssc_total_marks,ssc.final_result ssc_final_result,ssc.integration_flag ssc_integration_flag, "+
				" ssc.ssc_board ssc_board,ssc.ssc_other_board ssc_other_board, "+
				" ssc.ssc_pass_year ssc_pass_year,ssc.ssc_pass_month ssc_pass_month,ssc.ssc_seat_number ssc_seat_number, "+
				" ssc.ssc_marks_obtained ssc_marks_obtained, "+
				" ssc.ssc_marks_percent ssc_marks_percent,ssc.verification_status ssc_verification_status,ssc.namessccertificate ssc_namessccertificate, "+ 
				"  "+
				" hsc.total_marks hsc_total_marks,hsc.final_result hsc_final_result,hsc.integration_flag hsc_integration_flag, "+
				" hsc.hsc_board hsc_board,hsc.hsc_other_board hsc_other_board, "+
				" hsc.hsc_pass_year hsc_pass_year,hsc.hsc_pass_month hsc_pass_month,hsc.hsc_seat_number hsc_seat_number, "+
				" hsc.hsc_marks_obtained hsc_marks_obtained, "+
				" hsc.hsc_marks_percent hsc_marks_percent,hsc.verification_status hsc_verification_status, "+  
				"  "+
				" casteval.castevalidity_number cval_castevalidity_number,casteval.barti_flag cval_barti_flag, "+
				" casteval.issuing_date cval_issuing_date,casteval.verification_status cval_verification_status "+
				"  "+
				" FROM dbt_userprofile_dtl upro "+
				" left join dbt_userincome_dtl income on upro.user_id = income.user_id  "+
				" left join dbt_userdomicile_dtl dom on upro.user_id = dom.user_id  "+
				" left join dbt_userdisability_dtl dis on upro.user_id = dis.user_id "+ 
				" left join dbt_usercaste_dtl caste on upro.user_id = caste.user_id "+ 
				" left join dbt_userssc_dtl ssc on upro.user_id = ssc.user_id "+ 
				" left join dbt_userhsc_dtl hsc on upro.user_id = hsc.user_id "+
				" left join dbt_castevalidity_dtl casteval on upro.user_id = casteval.user_id "+
				" where upro.user_id = ? ";
	}
	
	/* change done by ankit */
	public String getDisabilityType(){
		return "select type from dbt_disabilitytype_m where isactive = 1 order by type";
	}
	
	//change on 19th July
	public String getCasteCategory(){
		return "select caste from dbt_caste_category_m where caste <> 'OTH' and caste <> 'OPEN' and isactive = 1";
		//return "select caste from dbt_caste_category_m where caste <> 'OTH' and isactive = 1";
	}
	
	public String getCaste(){
		return "select caste_name from dbt_caste_m a inner join dbt_caste_category_m b on a.caste_category_id = b.caste_id and a.isactive = 1 and b.caste = ? and b.isactive = 1 order by caste_name";
	}
	
	public String getStates(){
		return "select state_name from dbt_state_m where isactive = 1 order by state_name";
	}
	
	//change on 23rd July 2017
	public String getDistrict(){
		return "select district_name from dbt_district_m a inner join dbt_state_m b on "
				+ "a.state_id = b.state_id where b.state_name = ? and b.isactive = 1 and a.isactive = 1 order by district_name";
	}
	
	//change on 22nd July 2017
	public String getTaluka(){

		return "select taluka_name from dbt_taluka_m a "
				+ "inner join dbt_district_m b on a.district_id = b.district_id "
				+ "inner join dbt_state_m c on b.state_id = c.state_id "
				+ "where c.state_name = ? and c.isactive = 1 and b.district_name = ? "
				+ "and b.isactive = 1 and a.isactive = 1 order by taluka_name;";
		//return "select taluka_name from dbt_taluka_m a inner join dbt_district_m b on a.district_id = b.district_id and district_name = ? and a.isactive = 1 and b.isactive = 1 order by taluka_name";
	}
	
	public String getVillage(){
		return "select village_name from dbt_village_m a inner join dbt_taluka_m b on a.taluka_id = b.taluka_id inner join dbt_district_m c on a.district_id = b.district_id where b.taluka_name = ? and b.isactive = 1 and c.district_name = ? and c.isactive = 1 and a.isactive = 1 order by village_name";
	}
	
	public String getOccupation(){
		return "select profession_name from dbt_profession_m where isactive = 1 order by profession_name";
	}
	
	public String getOccupationForUnclean(){
		return "select profession_name from dbt_profession_m where isunclean = 1 and isactive = 1 order by profession_name";
	}
	
	public String getOccupationFor9and10(){
		return "select profession_name from dbt_profession_m where (profession_name = 'Service' or profession_name = 'Businessman' or profession_name = 'ServiceBusiness') and isactive = 1 order by profession_name";
	}
	
/*	public String getInstitutePendingAppList(){
		return "SELECT  eapp.app_id, scm.SchemeName, scm.SchemeType, dcom.caste, dcom.casteCategory, dcom.district,"
				+ " date(eapp.app_entry_datetime) dateOfApp, eapp.ibps_registration_no registration_no,"
				+ " eapp.authority_level, eapp.app_status, eapp.verification_status, scm.SchemeId FROM esc_application_approver_dashboard_dtl eapp"
				+ " INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId"
				+ " INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id"
				+ " WHERE eapp.authority_user_id = ? AND (eapp.app_status = 'NEW' OR eapp.app_status = 'MODIFY') ;";
	}*/
	public String getInstitutePendingAppList(){//DATE_FORMAT(eapp.app_entry_datetime, '%d/%m/%Y')dateOfApp format
		return "SELECT  eapp.app_id, scm.SchemeName, sd.scheme_type, dcom.caste, dcom.casteCategory,dcom.district, "+
				"DATE_FORMAT(eapp.app_entry_datetime, '%Y-%m-%d')dateOfApp,eapp.ibps_registration_no registration_no, "+
				"eapp.authority_level, eapp.app_status, eapp.verification_status,scm.SchemeId,"
				+ "dpost.current_course_CourseName, dpost.current_courseYear,dcom.isapplicantionRenewable "
				+ "FROM esc_application_approver_dashboard_dtl eapp  "+
				"INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId "+ 
				"Inner JOIN dbt_user_application_status_dashboard_dtl sd ON sd.app_id = eapp.app_id "+
				"INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id "+
				"INNER JOIN dbt_socialjusticepost_course_dtl dpost ON dpost.app_id = eapp.app_id "+
				"WHERE eapp.authority_user_id= ? AND (eapp.app_status = 'NEW' OR eapp.app_status = 'MODIFY') order by eapp.app_entry_datetime desc ;";
	}
	public String getInstitutePendingAppListforHeadMaster(){//DATE_FORMAT(eapp.app_entry_datetime, '%d/%m/%Y')dateOfApp format
		return "SELECT  eapp.app_id, scm.SchemeName, sd.scheme_type, dcom.caste, dcom.casteCategory,dcom.district, "+
				"DATE_FORMAT(eapp.app_entry_datetime, '%Y-%m-%d')dateOfApp,eapp.ibps_registration_no registration_no, "+
				"eapp.authority_level, eapp.app_status, eapp.verification_status,scm.SchemeId,"
				+ "dpre.standard as current_course_CourseName, dpre.academicYear as current_courseYear,dcom.isapplicantionRenewable "
				+ "FROM esc_application_approver_dashboard_dtl eapp  "+
				"INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId "+ 
				"Inner JOIN dbt_user_application_status_dashboard_dtl sd ON sd.app_id = eapp.app_id "+
				"INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id "+
				"INNER JOIN dbt_socialjusticepre_dtl dpre ON dpre.app_id = eapp.app_id "+
				"WHERE eapp.authority_user_id= ? AND (eapp.app_status = 'NEW' OR eapp.app_status = 'MODIFY') order by eapp.app_entry_datetime desc;";
	}
	//change on 6th July 2017
	public String getSetDocumentDetails() {
	    return "{call dbt_setdocumentdetails(?,?,?,?,?,?,?)}";
	}
	
	//change on 7th July 2017
	public String getInsertAttendanceDetails(){
		return "insert into dbt_attendance_dtl(app_id,user_index,modify_date,attendance_percent) values(?,?,NOW(),?);";
	}
	
	public String get_check_AttendanceDetails(){
		return "select count(1) from dbt_attendance_dtl where app_id = ?;";
	}
	
	public String getUpdateAttendanceDetails(){
		return "update dbt_attendance_dtl set attendance_percent = ?, modify_date = NOW() where app_id = ? and user_index = ?";
	}
	
	//change on 13th July
	//change on 27th July 
	public String getSSCDistrict(){
		return "select district_name from dbt_district_m where (district_name = 'Satara' "
				+ "or district_name = 'Aurangabad' or district_name = 'Dhule' or district_name = 'Amravati')";
		//return "select district_name from dbt_district_m where (district_id = 2 || district_id = 7 || district_id =19)";
	}
	
	public String getCommunityForMinority(){
		return "select community_name from dbt_community_m where community_name <> 'Hindu'";
	}
	
	public String getCommunityForOther(){
		return "select community_name from dbt_community_m";
	}
	
	//change on 25th July 2017
	public String getDuration(){
		/*return "select a.duration_inmonths from dbt_course_m a inner join dbt_scheme_master b on "
    			+ "a.department_id = b.departmentid and "
    			+ "a.course_name = ? and b.schemeid = ?";*/
		
		/*return "select f.duration_inmonths from dbt_college_m a "
			+ "inner join dbt_state_m b on a.state_id = b.state_id "
			+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
			+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
			+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
			+ "inner join dbt_course_m f on e.course_id = f.course_id "
			+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
			+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
			+ "where a.college_name = ? and "
			+ "f.course_name = ? and "
			+ "e.isactive = 1 and e.is_disable = 0 and "
			+ "b.state_name= ? and b.isactive = 1 and "
			+ "c.district_name= ? and c.isactive = 1 "
			+ "and d.taluka_name= ? and d.isactive =1 "
			+ "and f.isactive = 1 and g.isactive = 1 "
			+ "and h.schemeId = ?;";*/
		
		return "select distinct f.duration_inmonths from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_university_m g on e.university_id = g.university_id "
				+ "inner join dbt_college_grant_type_m h on e.is_granted = h.college_grant_type_id "
				+ "inner join dbt_scheme_master i on a.department_id = i.departmentid "
				+ "where a.college_name = ? "
				+ "and f.course_name = ? and "
				+ "g.university_name = ? and "
				+ "h.type = ? and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.isactive = 1 "
				+ "and i.schemeId = ?;";
	}
	
	//change on 24th July 2017
	public String getStateForTechnical(){
		return "select state_name from dbt_state_m where "
				+ "(state_name = 'Maharashtra' or state_name = 'Karnataka') and isactive = 1 order by state_name";
	}
	
	//change on 24th July 2017
	public String getStateForMaharashtra(){
		return "select state_name from dbt_state_m where state_name = 'Maharashtra' and isactive = 1";
	}
	
	//change on 24th July 2017
	public String getStateForDelhi(){
		return "select state_name from dbt_state_m where state_name = 'Delhi' and isactive = 1";
	}
	
	//change on 24th July 2017
	public String getStateForAll(){
		return "select state_name from dbt_state_m where isactive = 1 order by state_name;";
	}
	
	//change on 23rd July 2017
	public String getSainikSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 and a.school_name like '%sainik%';";
		
		/*return "select a.school_name from dbt_school_m a inner join dbt_district_m b on "
				+ "a.district_id = b.district_id inner join dbt_taluka_m c on "
				+ "a.taluka_id = c.taluka_id where b.district_name = ? and "
				+ "c.taluka_name = ? and a.state_id = 27 and a.school_name like '%sainik%'";*/
		
		//return "select school_name from dbt_school_m where school_name like '%sainik%'";
	}
		
	//change on 23rd July 2017
	public String getOtherSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 10 order by school_name;";
		
		/*return "select a.school_name from dbt_school_m a inner join dbt_district_m b on "
				+ "a.district_id = b.district_id inner join dbt_taluka_m c on "
				+ "a.taluka_id = c.taluka_id where b.district_name = ? and "
				+ "c.taluka_name = ? and a.state_id = 27";*/
		//return "select school_name from dbt_school_m limit 20";
	}
		
	//change on 23rd July 2017
	public String getPurvauchaSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 10 and a.highest_class >= 6 order by school_name;";
	}
	
	//change on 23rd July 2017
	public String getNineTenthSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 10 and a.highest_class >= 9 order by school_name;";
	}
		
	//change on 23rd July 2017
	public String getAttendanceAllowanceSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 4 and a.highest_class >= 1 order by school_name;";
	}
	
	//change on 23rd July 2017
	public String getPreMeritSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 10 and a.highest_class >= 5 order by school_name;";
	}
		
	//change on 23rd July 2017
	public String getPreMatricHandicapSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 10 and a.highest_class >= 8 order by school_name;";
	}
	
	//change on 23rd July 2017
	public String getPreMatricSanskritSchools(){
		return "select a.school_name, a.udise_code from dbt_school_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and c.district_name = ? "
				+ "and c.isactive = 1 and d.taluka_name = ? and d.isactive = 1 "
				+ "and a.lowest_class <= 10 and a.highest_class >= 9 order by school_name;";
	}
	
	public String getDiplomaCollege(){
		return "select distinct(a.college_name) from dbt_college_m a inner join dbt_college_course_m b on "
				+ "a.college_id=b.college_id inner join dbt_state_m c on a.state_id = c.state_id inner join dbt_district_m d on "
				+ "a.district_id = d.district_id inner join dbt_taluka_m e on "
				+ "a.taluka_id = e.taluka_id inner join dbt_course_m g on g.course_id = b.course_id "
				+ "inner join dbt_scheme_master f on a.department_id = f.departmentId "
				+ "where b.isactive = 1 and b.is_disable = 0 and "
				+ "c.state_name= ? and d.district_name= ? and e.taluka_name= ? "
				+ "and f.schemeId = ? and (g.coursetype_id = '2' || g.coursetype_id = '4' || g.coursetype_id = '9');";
	}
	
	public String getStandard(){
		return "select course_name from dbt_course_m where course_id >= 1291 and course_id <= 1295";
	}
	
	public String getStandardFortweleth(){
		return "select course_name from dbt_course_m where course_id >= 1286 and course_id <= 1299";
	}
	
	public String getStandardForSixth(){
		return "select course_name from dbt_course_m where course_id >= 1294 and course_id <= 1297";
	}
	
	//change on 23rd July 2017
	public String getStandardForAll(){
		return "{call dbt_getstandardlist(?,?,?)};"; 
		/*return "select course_name from dbt_course_m where "
			+ "course_id between ((select min(course_id) from dbt_course_m where course_name = 'I (1st)' and isactive = 1) + ? -1) and "
			+ "((select min(course_id) from dbt_course_m where course_name = 'I (1st)' and isactive = 1) + ? -1);";*/
		//return "select course_name from dbt_course_m where course_id >= 1286 and course_id <= 1295";
	}
	
	public String getBeneficiary(){
		return "select type from dbt_beneficiary_type_m";
	}
	
	public String getBoard(){
		return "select educationboard_name from dbt_education_board_m";
	}
	
	public String getProfessionalCourse(){
		return "select distinct(is_professional) from dbt_course_m a inner join dbt_college_course_m b "
        		+ "on a.course_id = b.course_id inner join dbt_college_m c "
        		+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
        		+ "on a.department_id = d.departmentid where "
        		+ "c.college_name = ? "
        		+ "and b.isactive = '1' and b.is_disable = '0' and "
        		+ "d.schemeId = ? and a.course_name = ?";
	}
	
	public String getOtherCourseType(){
		return "select course_type from dbt_course_type_m where course_type_id in ( "
         		+ "select distinct(a.coursetype_id) from dbt_course_m a inner join dbt_college_course_m b "
         		+ "on a.course_id = b.course_id inner join dbt_college_m c "
         		+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
         		+ "on a.department_id = d.departmentid where "
         		+ "c.college_name = ? "
         		+ "and b.isactive = '1' and b.is_disable = '0' and "
         		+ "d.schemeId = ? and a.course_name = ?);";
	}
	
	//change on 25th July 2017
	public String getGrantType(){
		/*return "select type from dbt_college_grant_type_m where college_grant_type_id in "
				+ "(select is_granted from dbt_college_course_m a inner join "
				+ "dbt_college_m b on a.college_id = b.college_id inner join "
				+ "dbt_course_m c on a.course_id = c.course_id inner join "
				+ "dbt_scheme_master d on b.department_id = d.departmentid where "
				+ "b.college_name = ? and "
				+ "c.course_name = ? and "
				+ "a.isactive = '1' and a.is_disable = '0' and d.schemeId = ?);";*/
		
		return "select distinct type from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_university_m g on e.university_id = g.university_id "
				+ "inner join dbt_college_grant_type_m h on e.is_granted = h.college_grant_type_id "
				+ "inner join dbt_scheme_master i on a.department_id = i.departmentid "
				+ "where a.college_name = ? and "
				+ "f.course_name = ? and "
				+ "g.university_name = ? and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 and h.isactive = 1 "
				+ "and i.schemeId = ? order by type;";
	}
	
	//change on 20th July 2017
	public String getDistrictForMaharshtra(){
		return "select district_name from dbt_district_m a inner join dbt_state_m b on a.state_id = b.state_id "
				+ "where b.state_name = 'Maharashtra' and b.isactive = 1 and a.isactive = 1 order by district_name;";
		/*return "select district_name from dbt_district_m where state_id = "
				+ "(select state_id from dbt_state_m where state_name = 'Maharashtra' and isactive = 1) and isactive = 1";*/
	}
	
	public String getLastCourse(){
		return "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
				+ "on a.course_id = b.course_id inner join dbt_college_m c "
				+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
				+ "on a.department_id = d.departmentid where "
				+ "c.college_name = ? "
				+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = ? and "
				+ "(a.course_name like '%Post Graduate%' && ((a.course_name like '%Graduate%' &&  a.course_name not like '%Post Graduate%') "
				+ "|| (a.course_name like '%Bachelor of%') && (a.course_name like '%Law%' || a.course_name like '%Commerce%' || "
				+ "a.course_name like '%Arts%' || a.course_name like '%Science%'))";
	}
	
	//change on 25th July 2017
	public String getAidedColleges(){
		/*return "select distinct(a.college_name) from dbt_college_m a "
				+ "inner join dbt_college_course_m b on a.college_id=b.college_id inner join "
				+ "dbt_state_m c on a.state_id = c.state_id inner join dbt_district_m d on "
				+ "a.district_id = d.district_id inner join dbt_taluka_m e on "
				+ "a.taluka_id = e.taluka_id inner join dbt_scheme_master f on a.department_id = f.departmentId "
				+ "where b.isactive = 1 and b.is_disable = 0 and "
				+ "c.state_name= ? and d.district_name= ? and e.taluka_name= ? "
				+ "and f.schemeId = ? and a.college_type = 'Aided';";*/
		return "select distinct(a.college_name) from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where (trim(f.course_name) like '11%' or trim(f.course_name) like '12%') "
				+ "and g.course_type = 'Under Graduate Course' and "
				+ "e.isactive = 1 and e.is_disable = 0 and e.is_granted = 3 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by a.college_name;";
	}
	
	//change on 24th July 2017
	public String getColleges(){
		return "select distinct(a.college_name) from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_scheme_master f on a.department_id = f.departmentid "
				+ "where e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.schemeId = ? order by a.college_name;";
		
		
		/*return "select distinct(a.college_name) from dbt_college_m a "
				+ "inner join dbt_college_course_m b on a.college_id=b.college_id inner join "
				+ "dbt_state_m c on a.state_id = c.state_id inner join dbt_district_m d on "
				+ "a.district_id = d.district_id inner join dbt_taluka_m e on "
				+ "a.taluka_id = e.taluka_id inner join dbt_scheme_master f on a.department_id = f.departmentId "
				+ "where b.isactive = 1 and b.is_disable = 0 and "
				+ "c.state_name= ? and d.district_name= ? and e.taluka_name= ? "
				+ "and f.schemeId = ? ;";*/
	}
		
	//change on 24th July 2017
	public String getEleventhTwelvethColleges(){
		return "select distinct(a.college_name) from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where (trim(f.course_name) like '11%' or trim(f.course_name) like '12%') "
				+ "and g.course_type = 'Under Graduate Course' and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by a.college_name;";
	}
		
	//change on 24th July 2017
	public String getCollegesAboveEleventhAndTwelveth(){
		return "select distinct(a.college_name) from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where (trim(f.course_name) not like '11%' and trim(f.course_name) not like '12%') and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by a.college_name;";
	}
		
	//change on 25th July 2017
	public String getCollegesForLawCommerce(){
		return "select distinct(a.college_name) from dbt_college_m a "
		+ "inner join dbt_state_m b on a.state_id = b.state_id "
		+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
		+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
		+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
		+ "inner join dbt_course_m f on e.course_id = f.course_id "
		+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
		+ "inner join dbt_course_category_m i on i.course_category_id = f.course_category_id "
		+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
		+ "where (trim(f.course_name) not like '11%' and trim(f.course_name) not like '12%') "
		+ "and course_type = 'Post Graduate Course' "
		+ "and (course_category_name = 'Law' or course_category_name = 'Commerce' or course_category_name = 'Arts' or course_category_name = 'Science') and "
		+ "e.isactive = 1 and e.is_disable = 0 and "
		+ "b.state_name= ? and b.isactive = 1 and "
		+ "c.district_name= ? and c.isactive = 1 "
		+ "and d.taluka_name= ? and d.isactive =1 "
		+ "and f.isactive = 1 and g.isactive = 1 "
		+ "and h.schemeId = ? order by a.college_name;";
	}
	
	public String getDocumentPath(){
		return "select document_path from dbt_document_dtl where app_id = ?";
	}
	
	public String getCollegeType(){
		return "select distinct(a.college_type) from dbt_college_m a "
				+ "inner join dbt_college_course_m b on a.college_id=b.college_id inner join "
				+ "dbt_state_m c on a.state_id = c.state_id inner join dbt_district_m d on "
				+ "a.district_id = d.district_id inner join dbt_taluka_m e on "
				+ "a.taluka_id = e.taluka_id inner join dbt_scheme_master f on a.department_id = f.departmentId "
				+ "where b.isactive = 1 and b.is_disable = 0 and "
				+ "c.state_name= ? and d.district_name= ? and e.taluka_name= ? "
				+ "and f.schemeId = ? and a.college_name = ? ";
	}
	
	
	//***************added by ankit katoch for class DisplayDocTypeService.java *******************//
		public String getselectUserdoc(String field){
			return "select doc_type,doc_id,doc_name from doc_master_table where "+field+"='y'";
		}
		public String getselectDocType(String field){
			return "select "+field+"_doc as doc from dbt_userprofile_dtl where user_id=? ";
		}
	//****************added by ankit katoch for class UserLoginLogoutService.java*************************************************//
		public String updateUserHistory1(){
			return "update dbt_connection_history_dtl set logout_datetime=current_timestamp()  where user_id=? and login_datetime=(select max(login_datetime) from dbt_connection_history_dtl where  user_id=? )";
		}
		public String subQuery(){
			return "select max(login_datetime) as maxDate from dbt_connection_history_dtl where  user_id=? ";
		}
		public String updateUserHistory(){
			return "update dbt_connection_history_dtl set logout_datetime=current_timestamp()  where user_id=? and login_datetime=? ";
		}
	//****************added by ankit katoch for class UserLoginService.java*************************************************//
		public String selectForApplicant(){
			return "select uc.user_id,uc.user_index,uc.user_type,uc.user_password,ud.full_name,ud.profile_pic,ud.user_type,ud.profile_type,ud.npci_status from dbt_usercredential_dtl uc,dbt_userprofile_dtl ud  where uc.user_id=ud.user_id and uc.user_id=? and uc.user_type='A'";
		}
		public String selectForInst(){
			return "select uc.user_id,uc.user_index,uc.user_type,uc.user_password,ud.role_id,ud.district_id,ud.taluka_id,ud.institute_id from dbt_usercredential_dtl uc,dbt_institute_userprofile ud where uc.user_index=ud.user_index and uc.user_id=? and uc.user_type='I'";
		}
	//****************added by ankit katoch for class DB_GetUserData.java for updateResidentProfile()*************************************************//
		public String insertDocRep(){
			return  "insert into doc_repository_dtl(id,doc_path,user_id,doc_num,reg_date,reg_authority) values(?,?,?,?,?,?) ";
		}
		public String updateDocRep(){
			return   "update  doc_repository_dtl set doc_path=?, doc_num=?,reg_date=?,reg_authority=? where id=? and user_id=? ";
		}
		public String selectQuery(){
			return  "select doc_id from doc_master_table where doc_type=?";
		}
		public String selectQueryCheck(){
			return  "select count(id) as count from doc_repository_dtl where id=? and user_id=?";
		}
		//****************added by Swarnadip Ghosh for class DB_GetUserData.java for GetStudentDataPostLoginAsUser()*************************************************//
		public String selectStudentAppData(){//DATE_FORMAT(c.app_submit_datetime, '%d/%m/%Y')
			return "SELECT dbtr.app_id, scm.SchemeName, scm.SchemeId, dbtr.scheme_type,dbtr.app_serviceCategorty, "
				+ "DATE_FORMAT(dbtr.app_submit_datetime, '%Y-%m-%d') app_submit_date,"
				+ "dbte.app_stage_code app_status,dbtr.pref_flag "
				+ "FROM  dbt_user_application_status_dashboard_dtl dbtr "
				+ "INNER JOIN dbt_scheme_master scm ON scm.SchemeId = dbtr.app_scheme_id "
				+ "INNER JOIN esc_application_tracker_dtl dbte ON dbte.app_id = dbtr.app_id "
				+ "WHERE dbtr.user_id=? ORDER BY dbtr.app_submit_datetime DESC";
		}
	//****************added by ankit katoch for class DB_GetUserData.java for getDataOnclickAppID()*************************************************//
		public String dataOnclickAppID(){
			return "select * from dbt_scholarship_postmatric_table a inner join application_status_dashboard b on a.app_id = b.application_id where a.app_id = ?";
			
		}
	//****************added by ankit katoch for class DB_GetUserData.java for getDataOnclickAppIDPrinciple()*************************************************//
		public String dataOnclickAppIDPrinciple(){
			return "select b.remarks remarks,* from dbt_scholarship_postmatric_table a inner join application_status_dashboard b on a.app_id = b.application_id where a.app_id = ?";
		}
	//****************added by ankit katoch for class DB_GetUserData.java for getInstituteListFromMaster()*************************************************//
		public String instituteListFromMaster(){
			return "select functionality,key1,value from dbt_param_m where functionality = ? and key1 = ?";
		}
	//****************added by ankit katoch for class DB_GetUserData.java for updateUserAppliedSchemePreference()*************************************************//
		public String userAppliedSchemePreference(){
			return "UPDATE dbt_user_application_status_dashboard_dtl SET pref_flag = ? WHERE app_id=? ";
		}
	//****************added by ankit katoch for class collegeclerkdataImp.java for getApprovedSchemePost()*************************************************//
		public String commonSchemePost(){
			return "select app_id,SchemeName,scheme_type,SchemeId,authority_level,caste,casteCategory,district,dateOfApp,registration_no,verification_status,current_course_CourseName,current_courseYear,isapplicantionRenewable,status"
					+" from (select eat.app_stage_code as status, scm.SchemeId, scm.SchemeName, eapp.app_id, authority_user_id, authority_id, "
					+" DATE_FORMAT(eapp.app_entry_datetime, '%Y-%m-%d')dateOfApp,eapp.ibps_registration_no registration_no,eapp.authority_level, "
					+" eapp.app_status,eapp.verification_status, dcom.caste,dpost.current_course_CourseName, dpost.current_courseYear, dcom.casteCategory,dcom.district, dcom.isapplicantionRenewable, sd.scheme_type" 
					+" from dbt_user_application_status_dashboard_dtl sd "
					+" INNER JOIN esc_application_approver_dashboard_dtl eapp on eapp.app_id = sd.app_id and sd.app_status = ?"
					+" INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId"
					+" INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id"
					+" INNER JOIN dbt_socialjusticepost_course_dtl dpost ON dpost.app_id = eapp.app_id"
					+ " INNER JOIN esc_application_tracker_dtl eat ON sd.app_id = eat.app_id) p inner join "
					+" (select user_id, role_id from dbt_usercredential_dtl a inner join dbt_institute_userprofile b on a.user_index = b.user_index "
					+" where b.institute_id = ? and role_id = ?) x on p.authority_user_id = x.user_id and p.authority_id = x.role_id";

		}
		//****************added by ankit katoch for class collegeclerkdataImp.java for getApprovedSchemePre()*************************************************//
		public String commonSchemePre(){
			return "select app_id,SchemeName,scheme_type,SchemeId,authority_level,caste,casteCategory,district,dateOfApp,registration_no,verification_status,current_course_CourseName,current_courseYear,isapplicantionRenewable,status"
					+" from (select eat.app_stage_code as status, scm.SchemeId, scm.SchemeName, eapp.app_id, authority_user_id, authority_id, "
					+" DATE_FORMAT(eapp.app_entry_datetime, '%Y-%m-%d')dateOfApp,eapp.ibps_registration_no registration_no,eapp.authority_level, "
					+" eapp.app_status,eapp.verification_status, dcom.caste,dpre.standard as current_course_CourseName, dpre.academicYear as current_courseYear, dcom.casteCategory,dcom.district, dcom.isapplicantionRenewable, sd.scheme_type" 
					+" from dbt_user_application_status_dashboard_dtl sd "
					+" INNER JOIN esc_application_approver_dashboard_dtl eapp on eapp.app_id = sd.app_id and sd.app_status = ? "
					+" INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId"
					+" INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id"
					+" INNER JOIN dbt_socialjusticepre_dtl dpre ON dpre.app_id = eapp.app_id"
					+" INNER JOIN esc_application_tracker_dtl eat ON sd.app_id = eat.app_id) p inner join "
					+" (select user_id, role_id from dbt_usercredential_dtl a inner join dbt_institute_userprofile b on a.user_index = b.user_index "
					+" where b.institute_id = ? and role_id = ?) x on p.authority_user_id = x.user_id and p.authority_id = x.role_id";

		}

		//****************added by ankit katoch for class collegeclerkdataImp.java for getRejectedApp()*************************************************//
		public String getAppTrackPost(){
			return "select app_id,SchemeName,scheme_type,SchemeId,authority_level,caste,casteCategory,district,dateOfApp,registration_no,"
					+" verification_status,current_course_CourseName,current_courseYear,isapplicantionRenewable,status from "
					+" (select eat.app_stage_code as status, scm.SchemeId, scm.SchemeName, eapp.app_id, authority_user_id, authority_id,  "
					+" DATE_FORMAT(eapp.app_entry_datetime, '%Y-%m-%d')dateOfApp,eapp.ibps_registration_no registration_no,eapp.authority_level, " 
					+" eapp.app_status,eapp.verification_status, dcom.caste,dpost.current_course_CourseName, dpost.current_courseYear,"
					+" dcom.casteCategory,dcom.district, dcom.isapplicantionRenewable, sd.scheme_type "
					+" from dbt_user_application_status_dashboard_dtl sd  INNER JOIN esc_application_approver_dashboard_dtl eapp on "
					+" eapp.app_id = sd.app_id  INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId "
					+" INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id "
					+" INNER JOIN dbt_socialjusticepost_course_dtl dpost ON dpost.app_id = eapp.app_id"
					+" INNER JOIN esc_application_tracker_dtl eat ON sd.app_id = eat.app_id) p "
					+" inner join  (select user_id, role_id from dbt_usercredential_dtl a "
					+" inner join dbt_institute_userprofile b on a.user_index = b.user_index "  
					+" where b.institute_id = ? and role_id = ?) x on p.authority_user_id = x.user_id and p.authority_id = x.role_id;";

		}
		//****************added by ankit katoch for class collegeclerkdataImp.java for getRejectedApp()*************************************************//
		public String getAppTrackPre(){
			return "select app_id,SchemeName,scheme_type,SchemeId,authority_level,caste,casteCategory,district,dateOfApp,registration_no, "
					 +" verification_status,current_course_CourseName,current_courseYear,isapplicantionRenewable,status from  "
					 +" (select eat.app_stage_code as status, scm.SchemeId, scm.SchemeName, eapp.app_id, authority_user_id, authority_id, "
					 +" DATE_FORMAT(eapp.app_entry_datetime, '%Y-%m-%d')dateOfApp,eapp.ibps_registration_no registration_no,eapp.authority_level, "  
					 +" eapp.app_status,eapp.verification_status, dcom.caste,dpre.standard as current_course_CourseName, dpre.academicYear as current_courseYear," 
					 +" dcom.casteCategory,dcom.district, dcom.isapplicantionRenewable, sd.scheme_type " 
					 +" from dbt_user_application_status_dashboard_dtl sd  INNER JOIN esc_application_approver_dashboard_dtl eapp on "
					 +" eapp.app_id = sd.app_id   INNER JOIN dbt_scheme_master scm ON eapp.app_scheme_id = scm.SchemeId  "
					 +" INNER JOIN dbt_commonformfields_dtl dcom ON dcom.app_id = eapp.app_id  "
					 +" INNER JOIN dbt_socialjusticepre_dtl dpre ON dpre.app_id = eapp.app_id"
					 +" INNER JOIN esc_application_tracker_dtl eat ON sd.app_id = eat.app_id) p  "
					 +" inner join  (select user_id, role_id from dbt_usercredential_dtl a  "
					 +" inner join dbt_institute_userprofile b on a.user_index = b.user_index   "
					 +" where b.institute_id = ? and role_id = ?) x on p.authority_user_id = x.user_id and p.authority_id = x.role_id ";

	}
		
		//***************added by Swarnaip ghosh for scheme Existence *************** //	
		public String getSchemeExistenceStatus() {
		    return "SELECT a.app_id appid "+
		    		"FROM dbt_user_application_status_dashboard_dtl a LEFT JOIN dbt_user_applications_draft b "+
					"ON a.app_id = b.app_id "+
					"WHERE b.app_id IS NULL AND a.app_id=? "+
					"UNION "+
					"SELECT b.app_id appid "+
					"FROM dbt_user_application_status_dashboard_dtl a RIGHT JOIN dbt_user_applications_draft b "+
					"ON a.app_id = b.app_id "+
					"WHERE a.app_id IS NULL AND b.app_id= ?";
		}
		
		
	public String getCheckUserEmailId_DOB() {
	    return "select user_id, mobile_no, email_id, dob from dbt_userprofile_dtl where email_id = ? and dob = ?";
	}
	
	public String getCheckUserEmailId_DOB_UserId() {
	    return "select user_id, mobile_no, email_id, dob from dbt_userprofile_dtl where email_id = ? and dob = ?"
	    		+ " and user_id = ?";
	}
	
	public String getCheckUserMobile_DOB() {
	    return "select user_id, mobile_no, email_id, dob from dbt_userprofile_dtl where mobile_no = ? and dob = ?";
	}
	
	public String getCheckUserMobile_DOB_UserId() {
	    return "select user_id, mobile_no, email_id, dob from dbt_userprofile_dtl where mobile_no = ? and dob = ?"
	    		+ " and user_id = ?";
	}
	public String getCourseCondition1() {
		/*return "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
				+ "on a.course_id = b.course_id inner join dbt_college_m c "
				+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
				+ "on a.department_id = d.departmentid where "
				+ "c.college_name = ? "
				+ "and b.isactive = '1' and b.is_disable = '0' and "
				+ "d.schemeId = ?";*/
		return "select distinct f.course_name, g.course_type, f.is_professional from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where a.college_name = ? and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by course_name;";
	}
	
	//change on 24th July 2017
	public String getCourseCondition2() {
		return "select distinct f.course_name, g.course_type, f.is_professional from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where a.college_name = ? "
				+ "and (trim(f.course_name) not like '11%' and trim(f.course_name) not like '12%') and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by course_name;";
		
		/*return "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
				+ "on a.course_id = b.course_id inner join dbt_college_m c "
				+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
				+ "on a.department_id = d.departmentid where "
				+ "c.college_name = ? "
				+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = ? and (a.course_name not like '%12th %' && a.course_name not like '%12th%' && "
				+ "course_name not like '%11th %' && course_name not like '%11th%' && course_name not like '%XII (12 th)%' && course_name not like '%XI (11 th)%')";*/
	}
	public String getCourseCondition3() {
		/*return "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
				+ "on a.course_id = b.course_id inner join dbt_college_m c "
				+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
				+ "on a.department_id = d.departmentid where "
				+ "c.college_name = ?"
				+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = ? and "
				+ "(a.course_name like '%P.H.D%' || a.course_name like '%Ph.D%' || a.course_name like '%PHD%' || a.course_name like '%PH.D%')";*/
		return "select distinct f.course_name, g.course_type, f.is_professional from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where a.college_name = ? "
				+ "and (f.course_name not like '11%' and f.course_name not like '12%') and course_type = 'P.H.D' and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by course_name;";
	}
	
	public String getCourseCondition4() {
		/*return "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
				+ "on a.course_id = b.course_id inner join dbt_college_m c "
				+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
				+ "on a.department_id = d.departmentid where "
				+ "c.college_name = ? "
				+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = ? and "
				+ "(a.course_name like '%Post Graduate%' && (a.course_name like '%Law%' || a.course_name like '%Commerce%' || "
				+ "a.course_name like '%Arts%' || a.course_name like '%Science%'))";*/
		
		return "select distinct f.course_name, g.course_type, f.is_professional from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where a.college_name = ? "
				+ "and (f.course_name not like '11%' and f.course_name not like '12%') and course_type = 'Post Graduate Course' and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by course_name;";
	}
	
	//change on 25th July 2017
	public String getCourseCondition5() {
		/*return "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
				+ "on a.course_id = b.course_id inner join dbt_college_m c "
				+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
				+ "on a.department_id = d.departmentid where "
				+ "c.college_name = ? "
				+ "and b.isactive = '1' and b.is_disable = '0' and "
				+ "d.schemeId = ? and b.is_granted = 3";*/
		return "select distinct f.course_name, g.course_type, f.is_professional from dbt_college_m a "
		+ "inner join dbt_state_m b on a.state_id = b.state_id inner join "
		+ "dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
		+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
		+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
		+ "inner join dbt_course_m f on e.course_id = f.course_id "
		+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
		+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
		+ "where a.college_name = ? "
		+ "and (trim(f.course_name) like '11%' or trim(f.course_name) like '12%') and course_type = 'Under Graduate Course' and "
		+ "e.isactive = 1 and e.is_disable = 0 and e.is_granted = 3 and "
		+ "b.state_name= ? and b.isactive = 1 and "
		+ "c.district_name= ? and c.isactive = 1 "
		+ "and d.taluka_name= ? and d.isactive =1 "
		+ "and f.isactive = 1 and g.isactive = 1 "
		+ "and h.schemeId = ? order by course_name;";
	}
	
	//change on 25th July 2017 
	public String getCourseEleventhAndTewelve(){
		return "select distinct f.course_name, g.course_type, f.is_professional from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id inner join "
				+ "dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where a.college_name = ? "
				+ "and (trim(f.course_name) like '11%' or trim(f.course_name) like '12%') and course_type = 'Under Graduate Course' and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by course_name;";
	}
	
	//change on 25th July 2017
	public String getCourseLawCommerce(){
		return "select f.course_name, g.course_type, f.is_professional from dbt_college_m a "
			+ "inner join dbt_state_m b on a.state_id = b.state_id "
			+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
			+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
			+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
			+ "inner join dbt_course_m f on e.course_id = f.course_id "
			+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
			+ "inner join dbt_course_category_m i on i.course_category_id = f.course_category_id "
			+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
			+ "where a.college_name = ? and "
			+ "(trim(f.course_name) not like '11%' and trim(f.course_name) not like '12%') "
			+ "and course_type = 'Post Graduate Course' "
			+ "and (course_category_name = 'Law' or course_category_name = 'Commerce' or course_category_name = 'Arts' or course_category_name = 'Science') and "
			+ "e.isactive = 1 and e.is_disable = 0 and "
			+ "b.state_name= ? and b.isactive = 1 and "
			+ "c.district_name= ? and c.isactive = 1 "
			+ "and d.taluka_name= ? and d.isactive =1 "
			+ "and f.isactive = 1 and g.isactive = 1 "
			+ "and h.schemeId = ? order by a.college_name;";
	}
	
	public String getPreference() {
		return "SELECT count(*) countFromDB FROM dbt_user_application_status_dashboard_dtl WHERE user_id= ?";
	}
	
	//change on 20th July 2017
	public String casteCategory() {
		return "select caste_name from dbt_caste_m a inner join dbt_caste_category_m "
				+ "b on a.caste_category_id = b.caste_id "
				+ "and b.caste = ? and b.isactive = 1 and a.isactive = 1 order by a.caste_name;";
		/*return "select caste_name from dbt_caste_m where caste_category_id = "
				+ "(select caste_id from dbt_caste_category_m where caste = ?)";*/
	}
	
	//change on 20th July 2017
	public String casteCategoryFromDB() {
		return "select caste from dbt_caste_category_m where caste <> 'OTH' and caste <> 'OPEN' and isactive = 1 order by caste_id;";
		//return "select caste from dbt_caste_category_m where caste <> 'OTH' and caste <> 'ORPH'";
	}

	public String get_DuplicateCheck_For_UpdateProfile(){
		return "select lower(trim(full_name)),lower(trim(dob)) from dbt_userprofile_dtl where full_name = ? and dob = ? and mobile_no = ? and user_id != ?";
	}
	
	public String get_UpdateUserProfile_To_Aadhaar() {
		
		return "update dbt_userprofile_dtl set aadhaar_no=?, full_name=?, careof=?, dob=?, age=?, mobile_no=?, email_id=?, gender=?,"
	    		+ " house=?, street=?, village_town=?, post_office=?, landmark=?, sub_district=?, district=?, state=?, pincode=?,"
	    		+ " sameAddress=?, c_house=?, c_street=?, c_village_town=?, c_locality=?, c_post_office=?, c_landmark=?,"
	    		+ " c_sub_district=?, c_district=?, c_state=?, c_pincode=?, father=?, isFatherAlive=?, mother=?, isMotherAlive=?, guardian=?, profile_pic=?,"
	    		+ " profile_type=?, npci_status=? where user_id=?";
	}

	public String get_GroupingSchemesId_For_SchemeId() {
		return "select grouped_scheme_id FROM dbtportal.dbt_schemes_grouping_m where scheme_id = ?";
	}
	//***************added by Swarnaip Ghosh for Getting Application Information *************** //
	public String getPortalAppliedAppDetails(){
		return "SELECT dbt.app_id,dbt.app_scheme_id,dbt.scheme_type,scheme.SchemeName,dbt.user_id,esc.app_flag "+
				"FROM dbt_user_application_status_dashboard_dtl dbt "+
				"INNER JOIN esc_application_tracker_dtl esc ON dbt.app_id = esc.app_id "+
				"INNER JOIN dbt_scheme_master scheme ON dbt.app_scheme_id = scheme.SchemeId "+
				"WHERE dbt.scheme_type IS NOT NULL "+
				"AND esc.app_flag = 'N' "+
				"LIMIT ?";
	}
	
	//***************added by Swarnaip ghosh for updting application flag *************** //
	public String updateApplicationTracker(){
		return "UPDATE esc_application_tracker_dtl SET app_flag = 'Y' "+
				"WHERE app_id = ?";
	}
	//***************added by Swarnaip Ghosh for Getting Updated Application Information *************** //
	public String getPortalAppliedAppDetails_Update(){
		return "SELECT dbt.app_id,dbt.app_scheme_id,dbt.scheme_type,scheme.SchemeName,dbt.user_id,esc.app_flag "+
				"FROM dbt_user_application_status_dashboard_dtl dbt "+
				"INNER JOIN esc_application_tracker_dtl esc ON dbt.app_id = esc.app_id "+
				"INNER JOIN dbt_scheme_master scheme ON dbt.app_scheme_id = scheme.SchemeId "+
				"WHERE dbt.scheme_type IS NOT NULL "+
				"AND esc.app_flag = 'U' "+
				"LIMIT ?";
	}
	
	//***************added by Swarnaip Ghosh for Getting Updated Document Information *************** //
	public String getUpdatedDocument_Details(){
		return "SELECT document_type,document_path FROM dbt_document_dtl "+ 
				"WHERE app_id = ? "+
				"AND document_flag = 'U'";
	}	
	//change on 17th July 2017
	public String getSchoolDistrictTaluka(){
		return "select b.district_name, c.taluka_name from dbt_school_m a inner join dbt_district_m b on "
				+ "a.district_id = b.district_id inner join dbt_taluka_m c on "
				+ "a.taluka_id = c.taluka_id and b.district_id = c.district_id where a.udise_code = ?";
	}
	
	//change on 24th July 2017
	public String getUniversityForAll(){
		/*return "select distinct(a.university_name) from dbt_university_m a inner join dbt_college_course_m b "
				+ "on a.university_id = b.university_id inner join dbt_college_m c on b.college_id = c.college_id "
				+ "where c.college_name = ? and b.isactive = '1' and b.is_disable = '0'";*/
		
		/*return "select university_name, type from dbt_college_m a "
			+ "inner join dbt_state_m b on a.state_id = b.state_id "
			+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
			+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
			+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
			+ "inner join dbt_course_m f on e.course_id = f.course_id "
			+ "inner join dbt_university_m g on e.university_id = g.university_id "
			+ "inner join dbt_college_grant_type_m h on e.is_granted = h.college_grant_type_id "
			+ "inner join dbt_scheme_master i on a.department_id = i.departmentid "
			+ "where a.college_name = ? and "
			+ "f.course_name = ? and "
			+ "e.isactive = 1 and e.is_disable = 0 and "
			+ "b.state_name= ? and b.isactive = 1 and "
			+ "c.district_name= ? and c.isactive = 1 "
			+ "and d.taluka_name= ? and d.isactive =1 "
			+ "and f.isactive = 1 and g.isactive = 1 and h.isactive = 1 "
			+ "and i.schemeId = ?;";*/
			
		return "select distinct university_name from dbt_college_m a "
			+ "inner join dbt_state_m b on a.state_id = b.state_id "
			+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
			+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
			+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
			+ "inner join dbt_course_m f on e.course_id = f.course_id "
			+ "inner join dbt_university_m g on e.university_id = g.university_id "
			+ "inner join dbt_college_grant_type_m h on e.is_granted = h.college_grant_type_id "
			+ "inner join dbt_scheme_master i on a.department_id = i.departmentid "
			+ "where a.college_name = ? and "
			+ "f.course_name = ? and "
			+ "e.isactive = 1 and e.is_disable = 0 and "
			+ "b.state_name= ? and b.isactive = 1 and "
			+ "c.district_name= ? and c.isactive = 1 "
			+ "and d.taluka_name= ? and d.isactive =1 "
			+ "and f.isactive = 1 and g.isactive = 1 and h.isactive = 1 "
			+ "and i.schemeId = ? order by university_name;";
	}
	
	//change on 24th July 2017
	public String getUniversityForSpecific(){
		return "select university_name from dbt_university_m where (university_id = '38' || university_id = '84' "
				+ "|| university_id = '89' || university_id = '88' || university_id = '8' || university_id = '9')";
	}
	
	//change on 25th July 2017
	public String getCollegesForEdu(){
		return "select distinct a.college_name from dbt_college_m a "
				+ "inner join dbt_state_m b on a.state_id = b.state_id "
				+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
				+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
				+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
				+ "inner join dbt_course_m f on e.course_id = f.course_id "
				+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
				+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
				+ "where (trim(f.course_name) not like '11%' and trim(f.course_name) not like '12%') "
				+ "and course_type = ? and "
				+ "e.isactive = 1 and e.is_disable = 0 and "
				+ "b.state_name= ? and b.isactive = 1 and "
				+ "c.district_name= ? and c.isactive = 1 "
				+ "and d.taluka_name= ? and d.isactive =1 "
				+ "and f.isactive = 1 and g.isactive = 1 "
				+ "and h.schemeId = ? order by college_name;";
	}
		
	public String getCoursesForEdu(){
		return "select distinct f.course_name from dbt_college_m a "
			+ "inner join dbt_state_m b on a.state_id = b.state_id "
			+ "inner join dbt_district_m c on a.district_id = c.district_id and b.state_id = c.state_id "
			+ "inner join dbt_taluka_m d on a.taluka_id = d.taluka_id and c.district_id = d.district_id "
			+ "inner join dbt_college_course_m e on a.college_id = e.college_id "
			+ "inner join dbt_course_m f on e.course_id = f.course_id "
			+ "inner join dbt_course_type_m g on f.coursetype_id = g.course_type_id "
			+ "inner join dbt_scheme_master h on a.department_id = h.departmentid "
			+ "where a.college_name = ? and "
			+ "(trim(f.course_name) not like '11%' and trim(f.course_name) not like '12%') "
			+ "and course_type = ? and "
			+ "e.isactive = 1 and e.is_disable = 0 and "
			+ "b.state_name= ? and b.isactive = 1 and "
			+ "c.district_name= ? and c.isactive = 1 "
			+ "and d.taluka_name= ? and d.isactive =1 "
			+ "and f.isactive = 1 and g.isactive = 1 "
			+ "and h.schemeId = ? order by course_name;";
	}
	
	
	// Update Application Status .
	public String updateApprovedSchemeStatus(){
		return "UPDATE dbt_user_application_status_dashboard_dtl SET app_status = ? "+ 
				"WHERE app_id = ?";
	}
	
	// Update Application Stage Code .
	public String updateApprovedSchemeStageCode(){
		return "UPDATE esc_application_tracker_dtl SET app_stage_code = ? "+
				"WHERE app_id = ?";
	}
	
}
