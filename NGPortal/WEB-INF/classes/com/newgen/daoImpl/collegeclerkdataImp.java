/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : collegeclerkdataImp.java
* Author              : Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 21, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
 ************************************************************************************************************/
package com.newgen.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.dto.Set_Get_Data_PostLogIn;
import com.newgen.dto.collegeclerkmodelclass;
import com.newgen.utility.AppConstants;
import com.newgen.utility.PGAdmin_DB_Connection;

import JSON.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class collegeclerkdataImp {

	InitialContext Context;
    DataSource ds;
	Connection conn = null ;
	PreparedStatement ps = null;
	ResultSet rs = null ;
	DatabaseQuery databaseQ = new DatabaseQuery();
	
	public ArrayList<collegeclerkmodelclass> Getcollegeclerkdata(String userName) throws Exception
	{
		ArrayList<collegeclerkmodelclass> listData = new ArrayList<collegeclerkmodelclass>();
		try
		{
			conn = PGAdmin_DB_Connection.getConnection();
			
			ps = conn.prepareStatement("select a.application_id, b.app_schemetype, b.app_submit_date from college_clerk_table a inner join dbt_Application_Tracker b on a.application_id = b.app_id where b.user_id='" + userName + "';");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				collegeclerkmodelclass dto = new collegeclerkmodelclass();
				
				dto.setApplication_id(rs.getString("application_id"));
				dto.setAplication_type(rs.getString("app_schemetype"));
				dto.setDate_of_application(rs.getString("app_submit_date"));				
				
				String temp2 = dto.getAplication_type();
				String temp1 = dto.getApplication_id();
				
				if(temp2.equalsIgnoreCase("PostMetric")){
					ps = conn.prepareStatement("select \"CurrentCourseCollegeName\", \"Caste\", \"CasteCategory\", \"District\" "
							+ "from "
							+ "dbt_scholarship_postmatric_table "
							+ "where app_Id = '" + temp1 + "'");
					ResultSet rs1 = ps.executeQuery();
					while(rs1.next()){
						 dto.setCurrentCourseCollegeName(rs.getString("CurrentCourseCollegeName"));
						 dto.setCaste(rs.getString("Caste"));
						 dto.setCasteCategory(rs.getString("CasteCategory"));
						 dto.setDistrict(rs.getString("District"));						
					}	
				}else{
					ps = conn.prepareStatement("select \"school_name\", \"caste\", \"castecategory\", \"district\" from dbt_scholarship_prematric_table where applicantid = '" + temp1 + "'");
					ResultSet rs1 = ps.executeQuery();
					while(rs1.next())
					{
						 dto.setschool_name(rs.getString("school_name"));
						 dto.setCaste(rs.getString("caste"));
						 dto.setCasteCategory(rs.getString("castecategory"));
						 dto.setDistrict(rs.getString("district"));						
					}
				}
				
				listData.add(dto);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally 

		{
			try
			{
				conn.close();
				ps.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return listData;
	}

	/**
	 * Created by Swarnadip Ghosh
	 * @param userName
	 * @return
	 */
	public ArrayList<Set_Get_Data_PostLogIn> GetStudentDataPostLoginAsUser1(String userName,String userrole) throws Exception {

		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {

			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			String institutePendingAppList = "";
            if("College_Principal".equalsIgnoreCase(userrole) || "College_Clerk".equalsIgnoreCase(userrole))
            	institutePendingAppList = databaseQ.getInstitutePendingAppList();
            else if("Head_Master".equalsIgnoreCase(userrole))
            	institutePendingAppList = databaseQ.getInstitutePendingAppListforHeadMaster();

			try(Connection conn = ds.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(institutePendingAppList);){
				ps.setString(1, userName);

				try(ResultSet rs = ps.executeQuery();){
			
					while(rs.next())
					{
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
						
						dto.setAppid(rs.getString("app_id"));
						dto.setSchemeType(rs.getString("scheme_type")== null?"":rs.getString("scheme_type"));
						dto.setSchemeId(rs.getInt("SchemeId"));
						dto.setScheme(rs.getString("SchemeName"));
						dto.setCaste(rs.getString("caste"));
						dto.setCategory(rs.getString("casteCategory"));
						dto.setDistrict(rs.getString("district"));
						//dto.setInstitute(rs.getString("CurrentCourseCollegeName"));
						dto.setDateOfApp(rs.getString("dateOfApp"));
						dto.setRegistration_no(rs.getString("registration_no"));
						dto.setAuthority_lvl(rs.getString("authority_level"));

						dto.setVerification_Status(rs.getString("verification_status"));
						dto.setCourseName(rs.getString("current_course_CourseName"));
						dto.setCourseYear(rs.getString("current_courseYear"));
						if("Yes".equalsIgnoreCase(rs.getString("isapplicantionRenewable")))
							dto.setAppStatus("Renewal");
						else
							dto.setAppStatus("Fresh");
						listData.add(dto);
					}
				}
			}
		}
	
		catch(SQLException sec)
		{
			sec.printStackTrace();
		}
		return listData;		
	}
	//***********************Institute Application Tracking Schemes****************************//
	public ArrayList<Set_Get_Data_PostLogIn> getTrackingApp(JSONObject jObj,javax.servlet.http.HttpServletRequest httpRequest) throws Exception
	{
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		String userName = (String) jObj.getString("userName");
		String districtId = (String) jObj.getString("districtId");
		String talukaId = (String) jObj.getString("talukaId");
		String instituteId = (String) jObj.getString("instituteId");
		String roleId = (String) jObj.getString("roleId");
		Claims claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(httpRequest.getHeader("token")).getBody();
		String userid = claims.get("userid", String.class);
		String userrole = claims.get("userrole", String.class);
		String dataTableList = "";
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            if("College_Principal".equalsIgnoreCase(userrole) || "College_Clerk".equalsIgnoreCase(userrole))
            	 dataTableList = databaseQ.getAppTrackPost();
            else if("Head_Master".equalsIgnoreCase(userrole))
            	 dataTableList = databaseQ.getAppTrackPre();
			try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(dataTableList);){
				ps.setString(1, instituteId);
				ps.setString(2, roleId);
				try(ResultSet rs = ps.executeQuery();){

					while(rs.next())
					{
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();

						dto.setAppid(rs.getString("app_id"));
						dto.setSchemeType(rs.getString("scheme_type")== null?"":rs.getString("scheme_type"));
						dto.setSchemeId(rs.getInt("SchemeId"));
						dto.setScheme(rs.getString("SchemeName"));
						dto.setCaste(rs.getString("caste"));
						dto.setCategory(rs.getString("casteCategory"));
						dto.setDistrict(rs.getString("district"));
						//dto.setInstitute(rs.getString("CurrentCourseCollegeName"));
						dto.setDateOfApp(rs.getString("dateOfApp"));
						dto.setRegistration_no(rs.getString("registration_no"));
						dto.setAuthority_lvl(rs.getString("authority_level"));

						dto.setVerification_Status(rs.getString("verification_status"));
						dto.setCourseName(rs.getString("current_course_CourseName"));
						dto.setCourseYear(rs.getString("current_courseYear"));
						dto.setStatus(rs.getString("status"));
						if("Yes".equalsIgnoreCase(rs.getString("isapplicantionRenewable")))
							dto.setAppStatus("Renewal");
						else
							dto.setAppStatus("Fresh");
						listData.add(dto);
					}
				}
			}
		}
		
		catch(SQLException sec)
		{
			sec.printStackTrace();
		}
		return listData;		
	}
	
	//**************************************************//

	//***********************Rejected Schemes****************************//


	public ArrayList<Set_Get_Data_PostLogIn> getCommonApplicaitonValues(JSONObject jObj,javax.servlet.http.HttpServletRequest httpRequest) throws Exception

	{
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		String userName = (String) jObj.getString("userName");
		String districtId = (String) jObj.getString("districtId");
		String talukaId = (String) jObj.getString("talukaId");
		String instituteId = (String) jObj.getString("instituteId");
		String roleId = (String) jObj.getString("roleId");
		String validityFor = (String) jObj.getString("validityFor");
		Claims claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(httpRequest.getHeader("token")).getBody();
		String userid = claims.get("userid", String.class);
		String userrole = claims.get("userrole", String.class);
		String dataTableList = "";
		try
		{
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			if("College_Principal".equalsIgnoreCase(userrole) || "College_Clerk".equalsIgnoreCase(userrole))
				dataTableList = databaseQ.commonSchemePost();
			else if("Head_Master".equalsIgnoreCase(userrole))
				dataTableList = databaseQ.commonSchemePre();
			try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(dataTableList);){
				ps.setString(1, validityFor);
				ps.setString(2, instituteId);
				ps.setString(3, roleId);
				System.out.println(">>>>>>>>>>> "+ps.toString());
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();

						dto.setAppid(rs.getString("app_id"));
						dto.setSchemeType(rs.getString("scheme_type")== null?"":rs.getString("scheme_type"));
						dto.setSchemeId(rs.getInt("SchemeId"));
						dto.setScheme(rs.getString("SchemeName"));
						dto.setCaste(rs.getString("caste"));
						dto.setCategory(rs.getString("casteCategory"));
						dto.setDistrict(rs.getString("district"));
						//dto.setInstitute(rs.getString("CurrentCourseCollegeName"));
						dto.setDateOfApp(rs.getString("dateOfApp"));
						dto.setRegistration_no(rs.getString("registration_no"));
						dto.setAuthority_lvl(rs.getString("authority_level"));
						
						dto.setVerification_Status(rs.getString("verification_status"));
						dto.setCourseName(rs.getString("current_course_CourseName"));
						dto.setCourseYear(rs.getString("current_courseYear"));
						dto.setStatus(rs.getString("status"));
						if("Yes".equalsIgnoreCase(rs.getString("isapplicantionRenewable")))
							dto.setAppStatus("Renewal");
						else
							dto.setAppStatus("Fresh");
						listData.add(dto);
					}
				}
			}
		}
		
		catch(SQLException sec)
		{
			sec.printStackTrace();
		}
		return listData;		
	}
	
	//**************************************************//
	
	
//**************************************** not in use  *******************************************************//	
	public ArrayList<Set_Get_Data_PostLogIn> GetStudentDataPostLoginAsUserPrinciple(String userName) throws Exception
	{

		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try
		{
			conn = PGAdmin_DB_Connection.getConnection();
			
			ps = conn.prepareStatement("select distinct(att.scheme_id),att.application_type app_schemetype,"
					+ "schtt.nameofscholarship nameofscholarship,att.application_id app_id,spt.\"Caste\" Caste,"
					+ "spt.\"CasteCategory\" CasteCategory,spt.\"District\" District,spt.\"CurrentCourseCollegeName\" CurrentCourseCollegeName,"
					+ "att.application_entry_datetime app_submit_date,att.registration_no registration_no "
					+ "from public.dbt_scholarship_postmatric_table spt "
					+ "inner join application_status_dashboard att on spt.app_id = att.application_id "
					+ "inner join schemetable schtt on att.scheme_id = schtt.schemeid "
					+ "where att.authority_id = '2' "
					+ "and att.authority_level = 'Principal' ;");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
				
				dto.setAppid(rs.getString("app_id"));
				dto.setType(rs.getString("app_schemetype"));
				dto.setScheme(rs.getString("nameofscholarship"));
				dto.setCaste(rs.getString("Caste"));
				dto.setCategory(rs.getString("CasteCategory"));
				dto.setDistrict(rs.getString("District"));
				dto.setInstitute(rs.getString("CurrentCourseCollegeName"));
				dto.setDateOfApp(rs.getString("app_submit_date"));
				dto.setRegistration_no(rs.getString("registration_no"));

				listData.add(dto);
			}
		}
		catch(SQLException sec)
		{
			sec.printStackTrace();
		}
		finally  /* Mandatory Code */
		{
			try
			{
				conn.close();
				ps.close();
			}
			catch(SQLException sef)
			{
				sef.printStackTrace();
			}
		}
		
		return listData;	
	
	}


}
