/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Set_Get_Data_User.java
* Author              : Ankit Katoch/Ankit Bhasin/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 24, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
 ************************************************************************************************************/
package com.newgen.dto;

public class Set_Get_Data_User {
	
	private String user_Name;
	private String user_Password;
	private String user_FullName;
	private String user_DOB;
	private String user_Gender;
	private String user_MobileNO;
	private String user_EmailID;

	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Password() {
		return user_Password;
	}
	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}
	public String getUser_FullName() {
		return user_FullName;
	}
	public void setUser_FullName(String user_FullName) {
		this.user_FullName = user_FullName;
	}
	public String getUser_DOB() {
		return user_DOB;
	}
	public void setUser_DOB(String user_DOB) {
		this.user_DOB = user_DOB;
	}
	public String getUser_Gender() {
		return user_Gender;
	}
	public void setUser_Gender(String user_Gender) {
		this.user_Gender = user_Gender;
	}
	public String getUser_MobileNO() {
		return user_MobileNO;
	}
	public void setUser_MobileNO(String user_MobileNO) {
		this.user_MobileNO = user_MobileNO;
	}
	public String getUser_EmailID() {
		return user_EmailID;
	}
	public void setUser_EmailID(String user_EmailID) {
		this.user_EmailID = user_EmailID;
	}
	
	

}
