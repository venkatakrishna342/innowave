/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Set_Get_Data_ApplicationForm.java
* Author              : Ankit Bhasin
* Date written
* (DD/MM/YYYY)        : Jan 22, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dto;

public class Set_Get_Data_ApplicationForm {

	private String aadhaarNo; 
	private String name;
	private String mobileNo;
	private String emailID;
	private String dateofBirth;
	private String gender;
	private String image_location;
	
	private String courseName;
	private String grantType;
	private String applicantHouse;
	private String applicantStreet;
	private String applicantPostOffice;
	private String applicantLandmark;
	private String applicantState;
	private String applicantDistrict;
	private String applicantTaluka;
	private String applicantCity;
	private String applicantPinCode;
	private String npciStatus;
	
	private int applicantAge;
	
	private String income_is_rts_seeded_data;
	private String income_cert_barcode;
	private String income_name_on_cert;
	private String income_cert_date;
	private String income_cert_number;
	private String income_issuing_authority;
	private String income_family_income;
	private String income_verification_status;
	
	private String domicile_is_rts_seeded_data;
	private String domicile_cert_barcode;
	private String domicile_name_on_cert;
	private String domicile_cert_date;
	private String domicile_cert_number;
	private String domicile_issuing_authority;
	private String domicile_is_domicile;
	private String domicile_verification_status;
	
	private String disaility_is_rts_seeded_data;
	private String disaility_cert_barcode;
	private String disaility_name_on_cert;
	private String disaility_cert_date;
	private String disaility_cert_number;
	private String disaility_issuing_authority;
	private String disaility_is_disabled;
	private String disaility_type;
	private String disaility_category;
	private String disaility_percent;
	private int disaility_validity_year;
	private String disaility_reader_opted;
	private String disaility_verification_status;
	
	private String caste_is_rts_seeded_data;
	private String caste_cert_barcode;
	private String caste_name_on_cert;
	private String caste_cert_date;
	private String caste_cert_number;
	private String caste_issuing_authority;
	private String caste_category;
	private String caste_caste;
	private String caste_verification_status;
	
	private String ssc_total_marks;
	private String ssc_final_result;
	private String ssc_integration_flag;
	private String ssc_board;
	private String ssc_other_board;
	private int ssc_pass_year;
	private String ssc_pass_month;
	private String ssc_seat_number;
	private String ssc_marks_obtained;
	private String ssc_marks_percent;
	private String ssc_verification_status;
	
	private String hsc_total_marks;
	private String hsc_final_result;
	private String hsc_integration_flag;
	private String hsc_board;
	private String hsc_other_board;
	private int hsc_pass_year;
	private String hsc_pass_month;
	private String hsc_seat_number;
	private String hsc_marks_obtained;
	private String hsc_marks_percent;
	private String hsc_verification_status;
	
	private String cval_castevalidity_number;
	private String cval_barti_flag;
	private String cval_issuing_date;
	private String cval_verification_status;
	
	private String sameAddress;
	private String c_house;
	private String c_street;
	private String c_post_office;
	private String c_landmark;
	private String c_state;
	private String c_district;
	private String c_pincode;
	private String c_village_town;
	private String c_sub_district;
	private String isFatherAlive;
	private String isMotherAlive;
	private String father;
	private String mother;
	private String guardian;
	
	
	//change on 11th July 2017
	private String income_is_income_certificate;
	private String disaility_is_disability_certificate;
	private String caste_is_caste_certificate;
	private String domicile_is_domicile_certificate;
	
	
	private String bpl_is_rts_seeded_data;
	private String bpl_cert_barcode;
	private String bpl_name_on_cert;
	private String bpl_cert_date;
	private String bpl_cert_number;
	private String bpl_issuing_authority;
	private String bpl_verification_status;
	private String bpl_is_bpl;
	private String bpl_is_bpl_certificate;
	
	private String domicileCertificateImagePath;
	private String casteCertificateImagePath;
	private String familyIncomeCertificateImagePath;
	private String disabilityCertificateImagePath;
	private String bplCardImagePath;
	
	//change on 22nd July 2017
	private String schoolName;
	private String udiseCode;
	private String lowestClass;
	private String highestClass;
	
	private String ssc_namessccertificate;
	
	//change on 24th July 2017
	private String currentCourseName;
	private String currentCourseType;
	private String isProfessional;
	
	private String ccUniversityName;
	private String ccType;
	
	public String getCurrentCourseName() {
		return currentCourseName;
	}
	public void setCurrentCourseName(String currentCourseName) {
		this.currentCourseName = currentCourseName;
	}
	public String getCurrentCourseType() {
		return currentCourseType;
	}
	public void setCurrentCourseType(String currentCourseType) {
		this.currentCourseType = currentCourseType;
	}
	public String getIsProfessional() {
		return isProfessional;
	}
	public void setIsProfessional(String isProfessional) {
		this.isProfessional = isProfessional;
	}
	public String getCcUniversityName() {
		return ccUniversityName;
	}
	public void setCcUniversityName(String ccUniversityName) {
		this.ccUniversityName = ccUniversityName;
	}
	public String getCcType() {
		return ccType;
	}
	public void setCcType(String ccType) {
		this.ccType = ccType;
	}
	public String getSsc_namessccertificate() {
		return ssc_namessccertificate;
	}
	public void setSsc_namessccertificate(String ssc_namessccertificate) {
		this.ssc_namessccertificate = ssc_namessccertificate;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getUdiseCode() {
		return udiseCode;
	}
	public void setUdiseCode(String udiseCode) {
		this.udiseCode = udiseCode;
	}
	public String getLowestClass() {
		return lowestClass;
	}
	public void setLowestClass(String lowestClass) {
		this.lowestClass = lowestClass;
	}
	public String getHighestClass() {
		return highestClass;
	}
	public void setHighestClass(String highestClass) {
		this.highestClass = highestClass;
	}
	public String getBpl_is_rts_seeded_data() {
		return bpl_is_rts_seeded_data;
	}
	public void setBpl_is_rts_seeded_data(String bpl_is_rts_seeded_data) {
		this.bpl_is_rts_seeded_data = bpl_is_rts_seeded_data;
	}
	public String getBpl_cert_barcode() {
		return bpl_cert_barcode;
	}
	public void setBpl_cert_barcode(String bpl_cert_barcode) {
		this.bpl_cert_barcode = bpl_cert_barcode;
	}
	public String getBpl_name_on_cert() {
		return bpl_name_on_cert;
	}
	public void setBpl_name_on_cert(String bpl_name_on_cert) {
		this.bpl_name_on_cert = bpl_name_on_cert;
	}
	public String getBpl_cert_date() {
		return bpl_cert_date;
	}
	public void setBpl_cert_date(String bpl_cert_date) {
		this.bpl_cert_date = bpl_cert_date;
	}
	public String getBpl_cert_number() {
		return bpl_cert_number;
	}
	public void setBpl_cert_number(String bpl_cert_number) {
		this.bpl_cert_number = bpl_cert_number;
	}
	public String getBpl_issuing_authority() {
		return bpl_issuing_authority;
	}
	public void setBpl_issuing_authority(String bpl_issuing_authority) {
		this.bpl_issuing_authority = bpl_issuing_authority;
	}
	public String getBpl_verification_status() {
		return bpl_verification_status;
	}
	public void setBpl_verification_status(String bpl_verification_status) {
		this.bpl_verification_status = bpl_verification_status;
	}
	public String getBpl_is_bpl() {
		return bpl_is_bpl;
	}
	public void setBpl_is_bpl(String bpl_is_bpl) {
		this.bpl_is_bpl = bpl_is_bpl;
	}
	public String getBpl_is_bpl_certificate() {
		return bpl_is_bpl_certificate;
	}
	public void setBpl_is_bpl_certificate(String bpl_is_bpl_certificate) {
		this.bpl_is_bpl_certificate = bpl_is_bpl_certificate;
	}
	public String getDomicileCertificateImagePath() {
		return domicileCertificateImagePath;
	}
	public void setDomicileCertificateImagePath(String domicileCertificateImagePath) {
		this.domicileCertificateImagePath = domicileCertificateImagePath;
	}
	public String getCasteCertificateImagePath() {
		return casteCertificateImagePath;
	}
	public void setCasteCertificateImagePath(String casteCertificateImagePath) {
		this.casteCertificateImagePath = casteCertificateImagePath;
	}
	public String getFamilyIncomeCertificateImagePath() {
		return familyIncomeCertificateImagePath;
	}
	public void setFamilyIncomeCertificateImagePath(String familyIncomeCertificateImagePath) {
		this.familyIncomeCertificateImagePath = familyIncomeCertificateImagePath;
	}
	public String getDisabilityCertificateImagePath() {
		return disabilityCertificateImagePath;
	}
	public void setDisabilityCertificateImagePath(String disabilityCertificateImagePath) {
		this.disabilityCertificateImagePath = disabilityCertificateImagePath;
	}
	public String getBplCardImagePath() {
		return bplCardImagePath;
	}
	public void setBplCardImagePath(String bplCardImagePath) {
		this.bplCardImagePath = bplCardImagePath;
	}
	public String getCaste_is_caste_certificate() {
		return caste_is_caste_certificate;
	}
	public void setCaste_is_caste_certificate(String caste_is_caste_certificate) {
		this.caste_is_caste_certificate = caste_is_caste_certificate;
	}
	public String getDomicile_is_domicile_certificate() {
		return domicile_is_domicile_certificate;
	}
	public void setDomicile_is_domicile_certificate(String domicile_is_domicile_certificate) {
		this.domicile_is_domicile_certificate = domicile_is_domicile_certificate;
	}
	public String getDisaility_is_disability_certificate() {
		return disaility_is_disability_certificate;
	}
	public void setDisaility_is_disability_certificate(String disaility_is_disability_certificate) {
		this.disaility_is_disability_certificate = disaility_is_disability_certificate;
	}
	public String getIncome_is_income_certificate() {
		return income_is_income_certificate;
	}
	public void setIncome_is_income_certificate(String income_is_income_certificate) {
		this.income_is_income_certificate = income_is_income_certificate;
	}
	public String getSameAddress() {
		return sameAddress;
	}
	public void setSameAddress(String sameAddress) {
		this.sameAddress = sameAddress;
	}
	public String getC_house() {
		return c_house;
	}
	public void setC_house(String c_house) {
		this.c_house = c_house;
	}
	public String getC_street() {
		return c_street;
	}
	public void setC_street(String c_street) {
		this.c_street = c_street;
	}
	public String getC_post_office() {
		return c_post_office;
	}
	public void setC_post_office(String c_post_office) {
		this.c_post_office = c_post_office;
	}
	public String getC_landmark() {
		return c_landmark;
	}
	public void setC_landmark(String c_landmark) {
		this.c_landmark = c_landmark;
	}
	public String getC_state() {
		return c_state;
	}
	public void setC_state(String c_state) {
		this.c_state = c_state;
	}
	public String getC_district() {
		return c_district;
	}
	public void setC_district(String c_district) {
		this.c_district = c_district;
	}
	public String getC_pincode() {
		return c_pincode;
	}
	public void setC_pincode(String c_pincode) {
		this.c_pincode = c_pincode;
	}
	public String getC_village_town() {
		return c_village_town;
	}
	public void setC_village_town(String c_village_town) {
		this.c_village_town = c_village_town;
	}
	public String getC_sub_district() {
		return c_sub_district;
	}
	public void setC_sub_district(String c_sub_district) {
		this.c_sub_district = c_sub_district;
	}
	public String getIsFatherAlive() {
		return isFatherAlive;
	}
	public void setIsFatherAlive(String isFatherAlive) {
		this.isFatherAlive = isFatherAlive;
	}
	public String getIsMotherAlive() {
		return isMotherAlive;
	}
	public void setIsMotherAlive(String isMotherAlive) {
		this.isMotherAlive = isMotherAlive;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	public String getIncome_is_rts_seeded_data() {
		return income_is_rts_seeded_data;
	}
	public void setIncome_is_rts_seeded_data(String income_is_rts_seeded_data) {
		this.income_is_rts_seeded_data = income_is_rts_seeded_data;
	}
	public String getIncome_cert_barcode() {
		return income_cert_barcode;
	}
	public void setIncome_cert_barcode(String income_cert_barcode) {
		this.income_cert_barcode = income_cert_barcode;
	}
	public String getIncome_name_on_cert() {
		return income_name_on_cert;
	}
	public void setIncome_name_on_cert(String income_name_on_cert) {
		this.income_name_on_cert = income_name_on_cert;
	}
	public String getIncome_cert_date() {
		return income_cert_date;
	}
	public void setIncome_cert_date(String income_cert_date) {
		this.income_cert_date = income_cert_date;
	}
	public String getIncome_cert_number() {
		return income_cert_number;
	}
	public void setIncome_cert_number(String income_cert_number) {
		this.income_cert_number = income_cert_number;
	}
	public String getIncome_issuing_authority() {
		return income_issuing_authority;
	}
	public void setIncome_issuing_authority(String income_issuing_authority) {
		this.income_issuing_authority = income_issuing_authority;
	}
	public String getIncome_family_income() {
		return income_family_income;
	}
	public void setIncome_family_income(String income_family_income) {
		this.income_family_income = income_family_income;
	}
	public String getIncome_verification_status() {
		return income_verification_status;
	}
	public void setIncome_verification_status(String income_verification_status) {
		this.income_verification_status = income_verification_status;
	}
	public String getDomicile_is_rts_seeded_data() {
		return domicile_is_rts_seeded_data;
	}
	public void setDomicile_is_rts_seeded_data(String domicile_is_rts_seeded_data) {
		this.domicile_is_rts_seeded_data = domicile_is_rts_seeded_data;
	}
	public String getDomicile_cert_barcode() {
		return domicile_cert_barcode;
	}
	public void setDomicile_cert_barcode(String domicile_cert_barcode) {
		this.domicile_cert_barcode = domicile_cert_barcode;
	}
	public String getDomicile_name_on_cert() {
		return domicile_name_on_cert;
	}
	public void setDomicile_name_on_cert(String domicile_name_on_cert) {
		this.domicile_name_on_cert = domicile_name_on_cert;
	}
	public String getDomicile_cert_date() {
		return domicile_cert_date;
	}
	public void setDomicile_cert_date(String domicile_cert_date) {
		this.domicile_cert_date = domicile_cert_date;
	}
	public String getDomicile_cert_number() {
		return domicile_cert_number;
	}
	public void setDomicile_cert_number(String domicile_cert_number) {
		this.domicile_cert_number = domicile_cert_number;
	}
	public String getDomicile_issuing_authority() {
		return domicile_issuing_authority;
	}
	public void setDomicile_issuing_authority(String domicile_issuing_authority) {
		this.domicile_issuing_authority = domicile_issuing_authority;
	}
	public String getDomicile_is_domicile() {
		return domicile_is_domicile;
	}
	public void setDomicile_is_domicile(String domicile_is_domicile) {
		this.domicile_is_domicile = domicile_is_domicile;
	}
	public String getDomicile_verification_status() {
		return domicile_verification_status;
	}
	public void setDomicile_verification_status(String domicile_verification_status) {
		this.domicile_verification_status = domicile_verification_status;
	}
	public String getDisaility_is_rts_seeded_data() {
		return disaility_is_rts_seeded_data;
	}
	public void setDisaility_is_rts_seeded_data(String disaility_is_rts_seeded_data) {
		this.disaility_is_rts_seeded_data = disaility_is_rts_seeded_data;
	}
	public String getDisaility_cert_barcode() {
		return disaility_cert_barcode;
	}
	public void setDisaility_cert_barcode(String disaility_cert_barcode) {
		this.disaility_cert_barcode = disaility_cert_barcode;
	}
	public String getDisaility_name_on_cert() {
		return disaility_name_on_cert;
	}
	public void setDisaility_name_on_cert(String disaility_name_on_cert) {
		this.disaility_name_on_cert = disaility_name_on_cert;
	}
	public String getDisaility_cert_date() {
		return disaility_cert_date;
	}
	public void setDisaility_cert_date(String disaility_cert_date) {
		this.disaility_cert_date = disaility_cert_date;
	}
	public String getDisaility_cert_number() {
		return disaility_cert_number;
	}
	public void setDisaility_cert_number(String disaility_cert_number) {
		this.disaility_cert_number = disaility_cert_number;
	}
	public String getDisaility_issuing_authority() {
		return disaility_issuing_authority;
	}
	public void setDisaility_issuing_authority(String disaility_issuing_authority) {
		this.disaility_issuing_authority = disaility_issuing_authority;
	}
	public String getDisaility_is_disabled() {
		return disaility_is_disabled;
	}
	public void setDisaility_is_disabled(String disaility_is_disabled) {
		this.disaility_is_disabled = disaility_is_disabled;
	}
	public String getDisaility_type() {
		return disaility_type;
	}
	public void setDisaility_type(String disaility_type) {
		this.disaility_type = disaility_type;
	}
	public String getDisaility_category() {
		return disaility_category;
	}
	public void setDisaility_category(String disaility_category) {
		this.disaility_category = disaility_category;
	}
	public String getDisaility_percent() {
		return disaility_percent;
	}
	public void setDisaility_percent(String disaility_percent) {
		this.disaility_percent = disaility_percent;
	}
	public int getDisaility_validity_year() {
		return disaility_validity_year;
	}
	public void setDisaility_validity_year(int disaility_validity_year) {
		this.disaility_validity_year = disaility_validity_year;
	}
	public String getDisaility_reader_opted() {
		return disaility_reader_opted;
	}
	public void setDisaility_reader_opted(String disaility_reader_opted) {
		this.disaility_reader_opted = disaility_reader_opted;
	}
	public String getDisaility_verification_status() {
		return disaility_verification_status;
	}
	public void setDisaility_verification_status(String disaility_verification_status) {
		this.disaility_verification_status = disaility_verification_status;
	}
	public String getCaste_is_rts_seeded_data() {
		return caste_is_rts_seeded_data;
	}
	public void setCaste_is_rts_seeded_data(String caste_is_rts_seeded_data) {
		this.caste_is_rts_seeded_data = caste_is_rts_seeded_data;
	}
	public String getCaste_cert_barcode() {
		return caste_cert_barcode;
	}
	public void setCaste_cert_barcode(String caste_cert_barcode) {
		this.caste_cert_barcode = caste_cert_barcode;
	}
	public String getCaste_name_on_cert() {
		return caste_name_on_cert;
	}
	public void setCaste_name_on_cert(String caste_name_on_cert) {
		this.caste_name_on_cert = caste_name_on_cert;
	}
	public String getCaste_cert_date() {
		return caste_cert_date;
	}
	public void setCaste_cert_date(String caste_cert_date) {
		this.caste_cert_date = caste_cert_date;
	}
	public String getCaste_cert_number() {
		return caste_cert_number;
	}
	public void setCaste_cert_number(String caste_cert_number) {
		this.caste_cert_number = caste_cert_number;
	}
	public String getCaste_issuing_authority() {
		return caste_issuing_authority;
	}
	public void setCaste_issuing_authority(String caste_issuing_authority) {
		this.caste_issuing_authority = caste_issuing_authority;
	}
	public String getCaste_category() {
		return caste_category;
	}
	public void setCaste_category(String caste_category) {
		this.caste_category = caste_category;
	}
	public String getCaste_caste() {
		return caste_caste;
	}
	public void setCaste_caste(String caste_caste) {
		this.caste_caste = caste_caste;
	}
	public String getCaste_verification_status() {
		return caste_verification_status;
	}
	public void setCaste_verification_status(String caste_verification_status) {
		this.caste_verification_status = caste_verification_status;
	}
	public String getSsc_total_marks() {
		return ssc_total_marks;
	}
	public void setSsc_total_marks(String ssc_total_marks) {
		this.ssc_total_marks = ssc_total_marks;
	}
	public String getSsc_final_result() {
		return ssc_final_result;
	}
	public void setSsc_final_result(String ssc_final_result) {
		this.ssc_final_result = ssc_final_result;
	}
	public String getSsc_integration_flag() {
		return ssc_integration_flag;
	}
	public void setSsc_integration_flag(String ssc_integration_flag) {
		this.ssc_integration_flag = ssc_integration_flag;
	}
	public String getSsc_board() {
		return ssc_board;
	}
	public void setSsc_board(String ssc_board) {
		this.ssc_board = ssc_board;
	}
	public String getSsc_other_board() {
		return ssc_other_board;
	}
	public void setSsc_other_board(String ssc_other_board) {
		this.ssc_other_board = ssc_other_board;
	}
	public int getSsc_pass_year() {
		return ssc_pass_year;
	}
	public void setSsc_pass_year(int ssc_pass_year) {
		this.ssc_pass_year = ssc_pass_year;
	}
	public String getSsc_pass_month() {
		return ssc_pass_month;
	}
	public void setSsc_pass_month(String ssc_pass_month) {
		this.ssc_pass_month = ssc_pass_month;
	}
	public String getSsc_seat_number() {
		return ssc_seat_number;
	}
	public void setSsc_seat_number(String ssc_seat_number) {
		this.ssc_seat_number = ssc_seat_number;
	}
	public String getSsc_marks_obtained() {
		return ssc_marks_obtained;
	}
	public void setSsc_marks_obtained(String ssc_marks_obtained) {
		this.ssc_marks_obtained = ssc_marks_obtained;
	}
	public String getSsc_marks_percent() {
		return ssc_marks_percent;
	}
	public void setSsc_marks_percent(String ssc_marks_percent) {
		this.ssc_marks_percent = ssc_marks_percent;
	}
	public String getSsc_verification_status() {
		return ssc_verification_status;
	}
	public void setSsc_verification_status(String ssc_verification_status) {
		this.ssc_verification_status = ssc_verification_status;
	}
	public String getHsc_total_marks() {
		return hsc_total_marks;
	}
	public void setHsc_total_marks(String hsc_total_marks) {
		this.hsc_total_marks = hsc_total_marks;
	}
	public String getHsc_final_result() {
		return hsc_final_result;
	}
	public void setHsc_final_result(String hsc_final_result) {
		this.hsc_final_result = hsc_final_result;
	}
	public String getHsc_integration_flag() {
		return hsc_integration_flag;
	}
	public void setHsc_integration_flag(String hsc_integration_flag) {
		this.hsc_integration_flag = hsc_integration_flag;
	}
	public String getHsc_board() {
		return hsc_board;
	}
	public void setHsc_board(String hsc_board) {
		this.hsc_board = hsc_board;
	}
	public String getHsc_other_board() {
		return hsc_other_board;
	}
	public void setHsc_other_board(String hsc_other_board) {
		this.hsc_other_board = hsc_other_board;
	}
	public int getHsc_pass_year() {
		return hsc_pass_year;
	}
	public void setHsc_pass_year(int hsc_pass_year) {
		this.hsc_pass_year = hsc_pass_year;
	}
	public String getHsc_pass_month() {
		return hsc_pass_month;
	}
	public void setHsc_pass_month(String hsc_pass_month) {
		this.hsc_pass_month = hsc_pass_month;
	}
	public String getHsc_seat_number() {
		return hsc_seat_number;
	}
	public void setHsc_seat_number(String hsc_seat_number) {
		this.hsc_seat_number = hsc_seat_number;
	}
	public String getHsc_marks_obtained() {
		return hsc_marks_obtained;
	}
	public void setHsc_marks_obtained(String hsc_marks_obtained) {
		this.hsc_marks_obtained = hsc_marks_obtained;
	}
	public String getHsc_marks_percent() {
		return hsc_marks_percent;
	}
	public void setHsc_marks_percent(String hsc_marks_percent) {
		this.hsc_marks_percent = hsc_marks_percent;
	}
	public String getHsc_verification_status() {
		return hsc_verification_status;
	}
	public void setHsc_verification_status(String hsc_verification_status) {
		this.hsc_verification_status = hsc_verification_status;
	}
	public String getCval_castevalidity_number() {
		return cval_castevalidity_number;
	}
	public void setCval_castevalidity_number(String cval_castevalidity_number) {
		this.cval_castevalidity_number = cval_castevalidity_number;
	}
	public String getCval_barti_flag() {
		return cval_barti_flag;
	}
	public void setCval_barti_flag(String cval_barti_flag) {
		this.cval_barti_flag = cval_barti_flag;
	}
	public String getCval_issuing_date() {
		return cval_issuing_date;
	}
	public void setCval_issuing_date(String cval_issuing_date) {
		this.cval_issuing_date = cval_issuing_date;
	}
	public String getCval_verification_status() {
		return cval_verification_status;
	}
	public void setCval_verification_status(String cval_verification_status) {
		this.cval_verification_status = cval_verification_status;
	}
	public int getApplicantAge() {
		return applicantAge;
	}
	public void setApplicantAge(int applicantAge) {
		this.applicantAge = applicantAge;
	}
	
	public String getApplicantState() {
		return applicantState;
	}
	public void setApplicantState(String applicantState) {
		this.applicantState = applicantState;
	}
	public String getApplicantDistrict() {
		return applicantDistrict;
	}
	public void setApplicantDistrict(String applicantDistrict) {
		this.applicantDistrict = applicantDistrict;
	}
	public String getApplicantTaluka() {
		return applicantTaluka;
	}
	public void setApplicantTaluka(String applicantTaluka) {
		this.applicantTaluka = applicantTaluka;
	}
	public String getApplicantCity() {
		return applicantCity;
	}
	public void setApplicantCity(String applicantCity) {
		this.applicantCity = applicantCity;
	}
	public String getApplicantPinCode() {
		return applicantPinCode;
	}
	public void setApplicantPinCode(String applicantPinCode) {
		this.applicantPinCode = applicantPinCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	/**
	 * @return the applicantHouse
	 */
	public String getApplicantHouse() {
		return applicantHouse;
	}
	/**
	 * @param applicantHouse the applicantHouse to set
	 */
	public void setApplicantHouse(String applicantHouse) {
		this.applicantHouse = applicantHouse;
	}
	/**
	 * @return the applicantStreet
	 */
	public String getApplicantStreet() {
		return applicantStreet;
	}
	/**
	 * @param applicantStreet the applicantStreet to set
	 */
	public void setApplicantStreet(String applicantStreet) {
		this.applicantStreet = applicantStreet;
	}
	/**
	 * @return the applicantPostOffice
	 */
	public String getApplicantPostOffice() {
		return applicantPostOffice;
	}
	/**
	 * @param applicantPostOffice the applicantPostOffice to set
	 */
	public void setApplicantPostOffice(String applicantPostOffice) {
		this.applicantPostOffice = applicantPostOffice;
	}
	/**
	 * @return the applicantLandmark
	 */
	public String getApplicantLandmark() {
		return applicantLandmark;
	}
	/**
	 * @param applicantLandmark the applicantLandmark to set
	 */
	public void setApplicantLandmark(String applicantLandmark) {
		this.applicantLandmark = applicantLandmark;
	}
	public String getImage_location() {
		return image_location;
	}
	public void setImage_location(String image_location) {
		this.image_location = image_location;
	}
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNpciStatus() {
		return npciStatus;
	}
	public void setNpciStatus(String npciStatus) {
		this.npciStatus = npciStatus;
	}
}
