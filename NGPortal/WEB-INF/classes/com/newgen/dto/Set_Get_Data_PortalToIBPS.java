/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Set_Get_Data_EditProfile.java
* Author              : Swarnadip Ghosh.
* Date written
* (DD/MM/YYYY)        : Jul 18, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dto;

import com.newgen.cig.entity.ApplySchemeEntity;

public class Set_Get_Data_PortalToIBPS {
	
	private String appid;
	private String schemeid;
	private String schemetype;
	private String scheme_name;
	private String userIndex;
	private String application_flag;
	private String ResultCode;
	private String Message;
	private ApplySchemeEntity applicationData;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSchemeid() {
		return schemeid;
	}
	public void setSchemeid(String schemeid) {
		this.schemeid = schemeid;
	}
	public String getSchemetype() {
		return schemetype;
	}
	public void setSchemetype(String schemetype) {
		this.schemetype = schemetype;
	}
	public String getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(String userIndex) {
		this.userIndex = userIndex;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public String getApplication_flag() {
		return application_flag;
	}
	public void setApplication_flag(String application_flag) {
		this.application_flag = application_flag;
	}
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public ApplySchemeEntity getApplicationData() {
		return applicationData;
	}
	public void setApplicationData(ApplySchemeEntity applicationData) {
		this.applicationData = applicationData;
	}
	
	
}
