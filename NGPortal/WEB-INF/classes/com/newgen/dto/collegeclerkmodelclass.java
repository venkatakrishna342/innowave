/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : collegeclerkmodelclass.java
* Author              : Ankit Katoch/Ankit Bhasin/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dto;

public class collegeclerkmodelclass {
	private String application_id;
	private String aplication_type;
	private String scheme_id;
	private String date_of_application;
	private String registration_no;
	private String entrydatetime;
	private String CurrentCourseCollegeName;
	private String Caste;
	private String CasteCategory;
	private String District;
	private String school_name;
	
	public collegeclerkmodelclass(){}
	
	public collegeclerkmodelclass(String application_id, String aplication_type, String scheme_id,
			String date_of_application, String registration_no, String entrydatetime) {
		super();
		this.application_id = application_id;
		this.aplication_type = aplication_type;
		this.scheme_id = scheme_id;
		this.date_of_application = date_of_application;
		this.registration_no = registration_no;
		this.entrydatetime = entrydatetime;
	}
	
	
	public String getschool_name() {
		return school_name;
	}

	public void setschool_name(String school_name) {
		this.school_name = school_name;
	}
	
	
	public String getCurrentCourseCollegeName() {
		return CurrentCourseCollegeName;
	}

	public void setCurrentCourseCollegeName(String CurrentCourseCollegeName) {
		this.CurrentCourseCollegeName = CurrentCourseCollegeName;
	}
	
	public String getCaste() {
		return Caste;
	}

	public void setCaste(String Caste) {
		this.Caste = Caste;
	}
	
	public String getDistrict() {
		return District;
	}

	public void setDistrict(String District) {
		this.District = District;
	}
	
	public String getCasteCategory() {
		return CasteCategory;
	}

	public void setCasteCategory(String CasteCategory) {
		this.CasteCategory = CasteCategory;
	}
	
	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getAplication_type() {
		return aplication_type;
	}

	public void setAplication_type(String aplication_type) {
		this.aplication_type = aplication_type;
	}

	public String getScheme_id() {
		return scheme_id;
	}

	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}

	public String getDate_of_application() {
		return date_of_application;
	}

	public void setDate_of_application(String date_of_application) {
		this.date_of_application = date_of_application;
	}

	public String getRegistration_no() {
		return registration_no;
	}

	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}

	public String getEntrydatetime() {
		return entrydatetime;
	}

	public void setEntrydatetime(String entrydatetime) {
		this.entrydatetime = entrydatetime;
	}
	
	
	
}
