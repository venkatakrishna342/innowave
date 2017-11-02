/**
 * 
 */
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : IBPS_Supported_Operations.java
* Author              : Varun Saddi, Swarnaip Ghosh
* Date written
* (DD/MM/YYYY)        : may 2, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.ibpsConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonObject;
import com.newgen.activityclass.PostLogin_StudentDetails;
import com.newgen.cig.entity.ApplySchemeEntity;
import com.newgen.cig.entity.DocumentEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.dto.Set_Get_Data_PortalToIBPS;
import com.newgen.utility.AppConstants;

/**
 * @author Varun Saddi
 * @author Swarnadip Ghosh
 */
public class IBPS_Supported_Operations {

	InitialContext Context;
	DataSource ds;
	DatabaseQuery databaseQ = new DatabaseQuery();
	PreparedStatement ps = null;

	public JsonObject get_InstituteProcessedApplications(String appStatus) {

		// Initializing return object
		JsonObject resultObj = new JsonObject();

		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());

			try (Connection conn = ds.getConnection();) {
				conn.setAutoCommit(false);

				/**
				 * Get All transactions with provided status
				 */
				String instituteProcessed_Query = databaseQ.get_InstituteProcessedApplications();
				ps = conn.prepareStatement(instituteProcessed_Query);
				ps.setString(1, appStatus);
				ResultSet rs = ps.executeQuery();
				if (rs != null && rs.next()) {
					// Initializing Json Array object
					JsonObject appIdArrObj = new JsonObject();
					while (rs.next()) {
						JsonObject appObj = new JsonObject();
						appObj.addProperty("Registration_No", rs.getString("ibps_registration_no"));
						appObj.addProperty("Authority_User_Id", rs.getString("authority_user_id"));
						appObj.addProperty("Authority_Id", rs.getString("authority_id"));
						appObj.addProperty("Authority_Decision", rs.getString("authority_decision"));
						appObj.addProperty("Authority_Remarks", rs.getString("authority_remarks"));
						appObj.addProperty("Authority_Action_Datetime", rs.getString("authority_action_datetime"));
						appObj.addProperty("Authority_Verification_Status", rs.getString("verification_status"));
						appObj.addProperty("Personal_VerifyRemark", rs.getString("personalremark"));
						appObj.addProperty("Spouse_VerifyRemark", rs.getString("spouseremark"));
						appObj.addProperty("Income_VerifyRemark", rs.getString("incomeremark"));
						appObj.addProperty("Eligibility_VerifyRemark", rs.getString("eligibilityremark"));
						appObj.addProperty("Caste_VerifyRemark", rs.getString("casteremark"));
						appObj.addProperty("Address_VerifyRemark", rs.getString("addressremark"));
						appObj.addProperty("Corres_Address_VerifyRemark", rs.getString("corresaddressremark"));
						appObj.addProperty("Parent_VerifyRemark", rs.getString("parentremark"));
						appObj.addProperty("Course_VerifyRemark", rs.getString("courseremark"));
						appObj.addProperty("Caste_Validity_VerifyRemark", rs.getString("castevalidityremark"));
						appObj.addProperty("Fees_VerifyRemark", rs.getString("feesremark"));
						appObj.addProperty("Education_VerifyRemark", rs.getString("educationremark"));
						appObj.addProperty("School_VerifyRemark", rs.getString("schoolremark"));
						appObj.addProperty("Hostel_VerifyRemark", rs.getString("hostelremark"));

						appIdArrObj.add(rs.getString("ibps_registration_no"), appObj);
					}

					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message", "Success");
					resultObj.add("AppIdResultset", appIdArrObj);
				} else {
					JsonObject appIdArrObj = new JsonObject();
					resultObj.addProperty("ResulCode", 1);
					resultObj.addProperty("Message", "No applications with given status");
					resultObj.add("AppIdResultset", appIdArrObj);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return resultObj;
	}

	public JsonObject update_ProcessedApplicationStatus(String appStatus, String authorityId,
			String ibps_registrationNo) {

		// Initializing return object
		JsonObject resultObj = new JsonObject();

		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());

			try (Connection conn = ds.getConnection();) {
				// conn.setAutoCommit(false);

				/**
				 * Get All transactions with provided status
				 */
				String instituteProcessed_Query = databaseQ.update_ProcessedApplicationStatus();
				ps = conn.prepareStatement(instituteProcessed_Query);
				ps.setString(1, appStatus);
				ps.setString(2, authorityId);
				ps.setString(3, ibps_registrationNo);
				int status = ps.executeUpdate();
				if (status > 0) {
					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message", "Success");
				} else {
					resultObj.addProperty("ResulCode", -2);
					resultObj.addProperty("Message", "Status not updated.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return resultObj;
	}

	/**
	 * This web service is using to pull the application details from PORTAL,
	 * Need for IBPS process .
	 * 
	 * @author : Swarnadip Ghosh
	 * @since : 2017-07-18
	 */

	public ArrayList<Set_Get_Data_PortalToIBPS> getPortalApplicationDetailsForIBPS(String limit) throws Exception {
		Context = new javax.naming.InitialContext();
		ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
		ArrayList<Set_Get_Data_PortalToIBPS> listData = new ArrayList<Set_Get_Data_PortalToIBPS>();

		try (Connection conn = ds.getConnection();) {

			String selectUserdoc = databaseQ.getPortalAppliedAppDetails();
			try (PreparedStatement ps = conn.prepareStatement(selectUserdoc);) {
				ps.setInt(1, Integer.parseInt(limit));
				System.out.println(ps.toString());
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						Set_Get_Data_PortalToIBPS dto = new Set_Get_Data_PortalToIBPS();

						dto.setAppid(rs.getString("app_id"));
						dto.setSchemeid(rs.getString("app_scheme_id"));
						dto.setScheme_name(rs.getString("SchemeName"));
						dto.setSchemetype(rs.getString("scheme_type"));
						dto.setUserIndex(rs.getString("user_id"));
						dto.setApplication_flag(rs.getString("app_flag"));

						dto.setResultCode("0");
						dto.setMessage("Success");

						PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
						ArrayList<ApplySchemeEntity> appData = plsd.getDataOnclickAppIDStudent(rs.getString("app_id"),
								rs.getString("scheme_type"), Integer.parseInt(rs.getString("app_scheme_id")),
								rs.getString("user_id"));
						dto.setApplicationData(appData.get(0));

						listData.add(dto);
					}
				}
			}
		} catch (Exception e) {
			Set_Get_Data_PortalToIBPS dto = new Set_Get_Data_PortalToIBPS();

			dto.setResultCode("1");
			dto.setMessage("Exception occured while processing .");

			listData.add(dto);

			e.printStackTrace();
		}
		return listData;
	}

	/**
	 * This web service is using to esc_application_tracker flag .
	 * @author : Swarnadip Ghosh
	 * @since : 2017-07-18
	 * @param app_id
	 * @return
	 */
	public JsonObject updatTrackerTableFlag(String app_id) {
		// TODO Auto-generated method stub
		JsonObject resultObj = new JsonObject();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int updateCount = -1;
			try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(databaseQ.updateApplicationTracker());) {
				ps.setString(1, app_id);
				updateCount = ps.executeUpdate();
				if (updateCount > 0) {
					resultObj.addProperty("ResulCode", 0);
					resultObj.addProperty("Message", "Succesfully updated application flag.");
					return resultObj;
				} else {
					resultObj.addProperty("ResulCode", -2);
					resultObj.addProperty("Message", "Not able to update application flag .");
					return resultObj;
				}
			}

		} catch (Exception sec) {
			sec.printStackTrace();
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Some exception occured while updating application flag.");
			return resultObj;
		}
	}
	/**
	 * This web service is using to pull updated application details from PORTAL, need for IBPS process . 
	 * @author : Swarnadip Ghosh
	 * @since : 2017-07-18
	 */

	public ArrayList<Set_Get_Data_PortalToIBPS> getPortalApplicationDetailsForIBPS_Update(String limit) throws Exception {
		Context = new javax.naming.InitialContext();
		ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
		ArrayList<Set_Get_Data_PortalToIBPS> listData = new ArrayList<Set_Get_Data_PortalToIBPS>();

		try (Connection conn = ds.getConnection();) {

			String select = databaseQ.getPortalAppliedAppDetails_Update();
			try (PreparedStatement ps = conn.prepareStatement(select);) {
				ps.setInt(1, Integer.parseInt(limit));
				System.out.println(ps.toString());
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						Set_Get_Data_PortalToIBPS dto = new Set_Get_Data_PortalToIBPS();

						dto.setAppid(rs.getString("app_id"));
						dto.setSchemeid(rs.getString("app_scheme_id"));
						dto.setScheme_name(rs.getString("SchemeName"));
						dto.setSchemetype(rs.getString("scheme_type"));
						dto.setUserIndex(rs.getString("user_id"));
						dto.setApplication_flag(rs.getString("app_flag"));

						dto.setResultCode("0");
						dto.setMessage("Success");

						PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
						ArrayList<ApplySchemeEntity> appData = plsd.getDataOnclickAppIDStudent(rs.getString("app_id"),
								rs.getString("scheme_type"), Integer.parseInt(rs.getString("app_scheme_id")),
								rs.getString("user_id"));
						
						/* Following code is using for override the updated document list */ 
						ArrayList<DocumentEntity> documentList = new ArrayList<DocumentEntity>();
						documentList = getUpdatedDocumentDetails(rs.getString("app_id"));
						appData.get(0).setDocumentList(documentList);
						
						dto.setApplicationData(appData.get(0));

						listData.add(dto);
					}
				}
			}
		} catch (Exception e) {
			Set_Get_Data_PortalToIBPS dto = new Set_Get_Data_PortalToIBPS();

			dto.setResultCode("-1");
			dto.setMessage("Exception occured while processing .");

			listData.add(dto);

			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<DocumentEntity> getUpdatedDocumentDetails(String appID) {
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		ArrayList<DocumentEntity> documentList = new ArrayList<DocumentEntity>();

		try (Connection conn = ds.getConnection();) {

			String selectUserdoc = databaseQ.getUpdatedDocument_Details();
			try (PreparedStatement ps = conn.prepareStatement(selectUserdoc);) {
				ps.setString(1, appID);
				System.out.println(ps.toString());
				try (ResultSet rs = ps.executeQuery();) {
					while (rs.next()) {
						DocumentEntity docObj = new DocumentEntity();

						docObj.setType(rs.getString("document_type"));
						docObj.setPath(rs.getString("document_path"));

						documentList.add(docObj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentList;
	}	

}
