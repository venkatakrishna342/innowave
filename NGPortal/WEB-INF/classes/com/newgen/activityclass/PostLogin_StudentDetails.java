/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : PostLogin_StudentDetails.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : April 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.activityclass;

import java.util.ArrayList;

import com.newgen.cig.entity.ApplySchemeEntity;
import com.newgen.daoImpl.DB_GetPostMatricData;
import com.newgen.daoImpl.DB_GetUserData;
import com.newgen.daoImpl.collegeclerkdataImp;
import com.newgen.dto.Set_Get_Data_EditProfile;
import com.newgen.dto.Set_Get_Data_OnAppID;
import com.newgen.dto.Set_Get_Data_PendingCases;
import com.newgen.dto.Set_Get_Data_PostLogIn;
import com.newgen.dto.Set_Get_Data_User;
import com.newgen.dto.Set_Get_Reg_Form;

import JSON.JSONObject;

public class PostLogin_StudentDetails 
{
	public ArrayList<Set_Get_Data_PendingCases> getResidentDraftData(String userName) 
	{
		ArrayList<Set_Get_Data_PendingCases> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getResidentDraftData(userName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}


	/**
	 * Created by Swarnadip Ghosh
	 * @param userName
	 * @return
	 */
	public ArrayList<Set_Get_Data_PostLogIn> getPostLoginStudentDetailsAsUser(String userName) 
	{
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.GetStudentDataPostLoginAsUser(userName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<Set_Get_Data_PostLogIn> getPostLoginStudentDetailsAsUser1(String userName,String userrole) {
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try {
			collegeclerkdataImp dao = new collegeclerkdataImp();
			listData=dao.GetStudentDataPostLoginAsUser1(userName,userrole);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}
	
	
		//********************get Common App*********************************//
	public ArrayList<Set_Get_Data_PostLogIn> getCommonApplicaitonData(JSONObject jObj,javax.servlet.http.HttpServletRequest httpRequest) 

	{
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			collegeclerkdataImp dao = new collegeclerkdataImp();
			listData=dao.getCommonApplicaitonValues(jObj,httpRequest);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	//*******************************************************************//
	
	//********************get Inst App*********************************//
	public ArrayList<Set_Get_Data_PostLogIn> getTrackingApplicaiton(JSONObject jObj,javax.servlet.http.HttpServletRequest httpRequest) 

	{
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			collegeclerkdataImp dao = new collegeclerkdataImp();
			listData=dao.getTrackingApp(jObj,httpRequest);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	//*******************************************************************//
	public ArrayList<Set_Get_Data_OnAppID> getDataOnclickAppID(String appID) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_OnAppID> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getDataOnclickAppID(appID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	public ArrayList<Set_Get_Data_PostLogIn> getStudentDataPostLoginAsUserPrinciple(String userName) 
	{
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			collegeclerkdataImp dao = new collegeclerkdataImp();
			listData=dao.GetStudentDataPostLoginAsUserPrinciple(userName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	public ArrayList<Set_Get_Data_OnAppID> getDataOnclickAppIDPrinciple(String appID) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_OnAppID> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getDataOnclickAppIDPrinciple(appID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<ApplySchemeEntity> getDataOnclickAppIDStudent(String appID,String schemetype,int schemeid, String userIndex) {
		// TODO Auto-generated method stub
		ArrayList<ApplySchemeEntity> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getDataOnclickAppIDStudent(appID, schemetype, schemeid, userIndex);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public int updateClerkStatus(String appId, String regNo, String authLvl, String schemeid, String username, String action,
			String comment, int verify_status, String verify_Remarks, String attendance_percent, String user_index) {
		
		int updateStatus = -1;
		try {
			DB_GetUserData dao = new DB_GetUserData();
			updateStatus = dao.updateClerkStatus(appId, regNo, authLvl, schemeid, username, action, comment, verify_status,
					verify_Remarks, attendance_percent,user_index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateStatus;

	}
	
	public ArrayList<Set_Get_Data_PostLogIn> getCategoryDetails() {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getCategoryDetails();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<Set_Get_Data_PostLogIn> getDeptDetails(String dept) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getDeptDetails(dept);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;

	}
	
	/************************************************************************************************************/
	public ArrayList<Set_Get_Data_PendingCases> getPendingCasesDataFromDB(String userName) 
	{
		ArrayList<Set_Get_Data_PendingCases> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getPendingCasesData(userName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	/**
	 * Created by Swarnadip Ghosh
	 * @param userName
	 * @return
	 */
	public ArrayList<Set_Get_Data_PostLogIn> appTracking(String userName, String appid) 
	{
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.appTracking(userName, appid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<Set_Get_Data_PostLogIn> getWorkFlowName(String schemeid, String stagecode) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_PostLogIn> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.getWorkFlowName( schemeid, stagecode);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;

	}
	public String setCasteDataINDoc(String appid, String doctype, String docPath, String userID, String docFlag,String rtsFlag) 
	{
		// TODO Auto-generated method stub
		String data = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			data=dao.setCasteDataINDoc(appid,doctype,docPath,userID,docFlag,rtsFlag);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return data;
	}
	
	public ArrayList<String> updateUserAppliedSchemePreference(String app_id, String preference) {
		// TODO Auto-generated method stub
		ArrayList<String> listData = null;
		try 
		{
			DB_GetUserData dao = new DB_GetUserData();
			listData=dao.updateUserAppliedSchemePreference(app_id,preference);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
}

