/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Set_Get_Reg_Form.java
* Author              : Ankit Katoch/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
 ************************************************************************************************************/
package com.newgen.dto;

public class Set_Get_Reg_Form {
	
	private String field_Type;
	public String getField_Type() {
		return field_Type;
	}
	public void setField_Type(String field_Type) {
		this.field_Type = field_Type;
	}
	public String getField_Id() {
		return field_Id;
	}
	public void setField_Id(String field_Id) {
		this.field_Id = field_Id;
	}
	public String getField_Name() {
		return field_Name;
	}
	public void setField_Name(String field_Name) {
		this.field_Name = field_Name;
	}
	public String getField_Label() {
		return field_Label;
	}
	public void setField_Label(String field_Label) {
		this.field_Label = field_Label;
	}
	public String getField_Class() {
		return field_Class;
	}
	public void setField_Class(String field_Class) {
		this.field_Class = field_Class;
	}
	public String getField_Required() {
		return field_Required;
	}
	public void setField_Required(String field_Required) {
		this.field_Required = field_Required;
	}
	private String field_Id;
	private String field_Name;
	private String field_Label;
	private String field_Class;
	private String field_Required;



}
