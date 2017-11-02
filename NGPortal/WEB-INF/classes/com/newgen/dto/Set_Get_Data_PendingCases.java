/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Set_Get_Data_PendingCases.java
* Author              : Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 24, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dto;

public class Set_Get_Data_PendingCases {

	private String appID;
	private String appStatus;
	private String schemeName;
	private String schemeId;
	private String schemeType;
	private String appServiceCategory;
	private String appSubmitDate;
	private String ibpsRegNo;
	
	
	
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getAppServiceCategory() {
		return appServiceCategory;
	}
	public void setAppServiceCategory(String appServiceCategory) {
		this.appServiceCategory = appServiceCategory;
	}
	public String getAppSubmitDate() {
		return appSubmitDate;
	}
	public void setAppSubmitDate(String appSubmitDate) {
		this.appSubmitDate = appSubmitDate;
	}
	public String getIbpsRegNo() {
		return ibpsRegNo;
	}
	public void setIbpsRegNo(String ibpsRegNo) {
		this.ibpsRegNo = ibpsRegNo;
	}
	
}
