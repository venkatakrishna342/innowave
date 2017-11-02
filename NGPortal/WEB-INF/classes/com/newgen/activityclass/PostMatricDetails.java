/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : PostMatricDetails.java
* Author              : Ankit Katoch/Ankit Bhasin/Swarnadip Ghosh
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

import java.util.ArrayList;

import com.newgen.daoImpl.DB_GetPostMatricData;
import com.newgen.dto.Set_Get_Data_ApplicationForm;

public class PostMatricDetails {
	
	
	public String getSchoolDistTaluka(String udiseCode) 
	{
		String jsonStr = "";
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			jsonStr=dao.getSchoolDistTaluka(udiseCode);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return jsonStr;
	}
	
	public ArrayList<String> getPostMatricDetailCollegesDiploma(String state, String district, String taluka, String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBCollegeDiploma(state,district,taluka,schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	
	public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDetailSchoolName(String schemeID, String district, String taluka) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBSchoolName(schemeID,district,taluka);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getPostMatricDetailsCourseYear(String schemeID, String  collegeName, String  state, String  district, 
			String  taluka, String  university,String  grantType, String courseName) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getCourseYearFromDB(schemeID, collegeName, state, district, taluka, university, grantType, courseName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getSpecificStatesFromDB(String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getSpecificStates(schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getBeneficiary() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getBeneficiaryFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	public ArrayList<String> getSSC_District() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getSSCDistrictFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getReligionFromMasterDB(String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getReligionFromDB(schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getPreMatricDetailStandard(String schemeID, String udiseCode) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getSTDFromDB(schemeID,udiseCode);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	/*public ArrayList<String> getSubDivision() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getSubDivisionFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}*/
	
	public ArrayList<String> getPostMatricDetailsBoard() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getBoardsFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDetailsUniversities(String collegeName, String schemeID, String courseName,
			String state, String district, String taluka) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getUniversityFromDB(collegeName,schemeID,courseName,state,district,taluka);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getStatesFromDB() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getStates();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getVillagesDB(String district, String taluka) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getVillagesFromDB(district,taluka);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	
	public ArrayList<String> getCasteCategory() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getCasteCategoryFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getCaste(String casteCategory) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getCasteFromDB(casteCategory);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	/*public ArrayList<String> getSubCaste(String caste) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getSubCasteFromDB(caste);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}*/
	
	public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDetailsGrantType(String collegeName, String schemeID, String state, String district, 
			String taluka, String university, String courseName) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getGrantTypeFromDB(collegeName,schemeID,state,district,taluka,university,courseName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	
	
	public ArrayList<String> getPostMatricDetailCourseTypes(String courseName, String collegeName, String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getCourseTypeFromDB(courseName,collegeName,schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getDistrict(String state) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getDistrictFromDB(state);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	/*public ArrayList<String> getStateDB() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getState();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}*/
	
	
	public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDetailsCourses(String collegeName, String schemeID, 
			String state, String district, String taluka) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getCourseFromDB(collegeName,schemeID,state,district,taluka);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	
	public ArrayList<String> getDisabilityType() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getDisabilityFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	
	
	public ArrayList<String> getPostMatricDetails() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDB();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	
	public ArrayList<String> getPostMatricDetailsLastYearCourses(String collegeName, String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getLastCourseFromDB(collegeName,schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getPostMatricDetailsTaluka(String districtName, String stateName) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBTaluka(districtName,stateName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	/*public ArrayList<String> getPostMatricDetailsColleges() 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBColleges();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}*/
	
	public ArrayList<String> getPostMatricDetailColleges(String state, String district, String taluka, String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBCollege(state,district,taluka,schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDetailsAutoPopulate(String userName) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBAutoPopulate(userName);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public String addDocumentsDetails(String schemeID, String userID, String documentPath, String documentType, 
			String appid, String schemeType, String userIndex, String fullName, String rts_flag){
		String entryStatus = "false";
		try{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			entryStatus = dao.setDocumentsDetails(schemeID,userID,documentPath,documentType,appid,schemeType,userIndex,fullName,rts_flag);
		}catch(Exception ex){
			ex.printStackTrace();
			entryStatus = "false";
		}		
		return entryStatus;
	}
	
	public ArrayList<String> getPostMatricDetailsOccupation(String schemeID) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPostMatricDataFromDBOccupation(schemeID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<Set_Get_Data_ApplicationForm> getDocumentDetails(String appID) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getImageViewFromDB(appID);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	public ArrayList<Set_Get_Data_ApplicationForm> getImageViewForDocs(String filePath) 
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getImageViewForDocs(filePath);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getPostMatricDetailsCollegeTypes(String collegeName, String schemeID, String state, String district, String taluka) 
	{
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getCollegeTypeFromDB(collegeName,schemeID,state,district,taluka);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	public ArrayList<String> getPreferenceDropDownValue(String user_Name) {
		// TODO Auto-generated method stub
		ArrayList<String> listData = null;
		try 
		{
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData=dao.getPreferenceDropDownValue(user_Name);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return listData;
	}
}
