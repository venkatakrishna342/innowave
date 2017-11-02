/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ApplySchemeEntity.java
* Author              : Ankit Bhasin/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Feb 07, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.cig.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import JSON.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplySchemeEntity {
	
	/*user Index*/
	private String userIndex;
	
	/*user Details*/
	private String userID;
	
	/*scheme Details*/
	private String schemeID;
	
	/*scheme Details*/
	private String schemeType;
	
	private String appID;
		
	/* Personal Details Fields */
	private String applicantDomicile;
	private String bankSeededAadhaar;
	private String applicantDomicileCertificateNo;
	private int applicantDomicileCertificateYear;
	private String applicantSARALNO;
	private String applicantGRNNO;
	private String applicantAadhaarUID;
	private String applicantFullName;
	private String applicantMobileNo;
	private String applicantEmailID;
	private String applicantDOB;
	private String applicantGender;
	private String familyIncome;
	private String applicantMotherTongue;
	
	/* Caste Details Fields */
	private String applicantCasteCategory;
	private String applicantCaste;
	private String applicantSubCaste;
	private String applicantCasteCertificateNo;
	private String applicantCasteCertificateDate;
	private int applicantCasteCertificateYear;
	private String applicantCasteDistrict;
	private String applicantCasteTaluka;
	private String applicantCasteCertificateSubdivision;
	private String applicantCasteCertificateAuthority;
	private String applicantReligion;
	
	/* Permanent Address Details */
	private String applicantAddress;
	private String applicantState;
	private String applicantDistrict;
	private String applicantTaluka;
	private String applicantCity;
	private int applicantPinCode;
	private String addressIsPermanent;
	
	/* Correspondence Address Details */
	private String applicantAddress_correspondence;
	private String applicantState_correspondence;
	private String applicantDistrict_correspondence;
	private String applicantTaluka_correspondence;
	private String applicantCity_correspondence;
	private int applicantPinCode_correspondence;
	
	/* Parents Details */
	private String applicantFatherAlive;
	private String fatherName;
	private String fatherDOB;
	private String fatherOccupation;
	private String fatherPanNo;
	private String fatherIncomeCertificateNo;
	private String applicantFatherSalaried;
	private String applicantMotherAlive;
	private String motherName;
	private String motherDOB;
	private String motherOccupation;
	private String motherPanNo;
	private String motherIncomeCertificateNo;
	private String applicantMotherSalaried;
	
	/* Personal Eligibility Details */
	private String applicantIsSalaried;
	private String applicantJobType;
	private String applicantIncome;
	private String applicantPanNo;
	private String applicantIsPhysicallyHandicapped;
	private String applicantDisabilityType;
	private String applicantDisabilityPercentage;
	private String applicantChildNo;
	
	
	/* Professional Eligibility Details */
	private String courseType;
	
	/* Caste Validity Details */
	private String applicantCasteValidityNo;
	private String applicantCasteValidityCertificateDate;
	private int applicantCasteValidityCertificateYear;
	private String casteValidityDistrict;
	private String applicantCasteValidityTaluka;
	
	/* Course Details */
	private int joiningYear; 
	private String currentCourseCollegeRegisterNo;
	private String currentCourseState;
	private String currentCourseDistrict;
	private String currentCourseTaluka;
	private String currentCourseCollegeName;
	private String currentCourseCollegeType;
	private String currentCourseCourseName;
	private String currentCourseUniversityName;
	private int currentCourseYear;
	private String currentCourseGrantType;
	private String currentCourseCap;
	private String currentCourseCollegeAdmissionNo;
	private String currentCourseAdmissionDate;
	private String isCurrentCourseFeesPaid;
	private String isdiplomaPassed;
	
	private String examMonth;
	
	/* Fees Details */
	private float currentCourseExamFees;
	private float currentCourseTutionFees;
	private float currentCourseOtherFees;
	
	/* Education Details */
	//SSC
	private String applicant10thPassingBoard;
	private String applicantSSCBoard1;
	private String applicantSSCDivision;
	private int applicant10thPassingYear;
	private String applicant10thPassingMonth;
	private String applicant10thRollNo;
	private String applicant10thMarksObtained;
	private String applicant10thcertificateNumber;
	private String applicant10thSchoolName;
	private String applicant10thSchoolDistrict;
	private String applicant10thSchoolTaluka;
	
	//11th
	private String applicant11thPassingBoard;
	private String applicant11thBoard1;
	private String applicant11thDivision;
	private int applicant11thPassingYear;
	private String applicant11thPassingMonth;
	private String applicant11thRollNo;
	//private float applicant11thMarksObtained;  //change in DB ankit
	private String applicant11thMarksObtained;
	private String applicant11thcertificateNumber;
	
	//HSC
	private String applicant12thPassingBoard;
	private String applicantHSCBoard1;
	private String applicantHSCDivision;
	private int applicant12thPassingYear;
	private String applicant12thPassingMonth;
	private String applicant12thRollNo;
	private String applicant12thMarksObtained;
	private String applicant12thcertificateNumber;
	
	//1st Year Diploma Details
	private int diplomaAcademicYearPassed;
	private String diplomaCollegeName;
	private String diplomaCourseName;
	private String diplomaUniversityName;
	private String diplomaYear;
	private String diplomaResult;
	private String diplomaMarksObtained;
	
	//2nd Year Diploma Details
	private int sdiplomaAcademicYearPassed;
	private String sdiplomaCollegeName;
	private String sdiplomaCourseName;
	private String sdiplomaUniversityName;
	private String sdiplomaYear;
	private String sdiplomaResult;
	private String sdiplomaMarksObtained;
	
	//3rd Year Diploma Details
	private int tdiplomaAcademicYearPassed;
	private String tdiplomaCollegeName;
	private String tdiplomaCourseName;
	private String tdiplomaUniversityName;
	private String tdiplomaYear;
	private String tdiplomaResult;
	private String tdiplomaMarksObtained;
	
	//1st Year College Details
	private int fYearAcademicYearPassed;
	private String fYearCollegeName;
	private String fYearCourseName;
	private String fYearUniversityName;
	private String fYearYear; //ankit
	private String fYearResult;
	private String fYearMarksObtained;
	
	//2nd Year College Details
	private int sYearAcademicYearPassed;
	private String sYearCollegeName;
	private String sYearCourseName;
	private String sYearUniversityName;
	private String sYearYear;
	private String sYearResult;
	private String sYearMarksObtained;
	
	//3rd Year College Details
	private int tYearAcademicYearPassed;
	private String tYearCollegeName;
	private String tYearCourseName;
	private String tYearUniversityName;
	private String tYearYear;
	private String tYearResult;
	private String tYearMarksObtained;
	
	//Graduation Details
	private int gYearAcademicYearPassed;
	private String gYearCollegeName;
	private String gYearCourseName;
	private String gYearUniversityName;
	private String gYearYear;
	private String gYearResult;
	private String gYearMarksObtained;
	
	//Post Graduation Details
	private int pgYearAcademicYearPassed;
	private String pgYearCollegeName;
	private String pgYearCourseName;
	private String pgYearUniversityName;
	private String pgYearYear;
	private String pgYearResult;
	private String pgYearMarksObtained;
	
	
	//School Details
	private String applicantSchoolName;
	private String applicantSchoolIntake;
	private int applicantAcademicYear;
	private String applicantSchoolAdmissionDate;
	private String applicantSchoolStandard;
	private String applicantlastYearResult;
	private String applicantSchoolGrade;
	private String applicantAttendance;
	private String applicantBehaviour;
	private String applicantSchoolMarks; 
	private String doYouHaveGRNNo;
	private String applicantUDISECode;
	
	/* Hostel Details */
	private String applicantDayScholarDetails;
	private String applicantHostelDistrict;
	private String applicantHostelTaluka;
	private String hostelType;
	private String applicantHostelName;
	private String hostelAided;
	private String applicantHostelAddress;
	private String collegeRemarks;

	/* Certificate */
	private String domicileCertificateImagePath;
	private String casteCertificateImagePath;
	private String fatherIncomeCertificateImagePath;
	private String motherIncomeCertificateImagePath;
	private String ssccertificateImagePath;
	private String hsccertificateImagePath;
	private String casteValiityCertificateImagePath;
	
	/*Tribal Details*/
	private String applicantMaritalStatus;
	private String spouseName;
	private String spouseRelation;
	private String spouseAge;
	private String spouseProfession;
	private String spouseAddress;
	private String spouseState;
	private String spouseDistrict;
	private String spouseTaluka;
	private String spouseCity;
	private String spousePinCode;
	
	/*Last Year School Details*/
	private String lastSchoolName;
	private int lastAcademicYear;
	private String lastSchoolAdmissionDate;
	private String lastSchoolStandard;
	private String lastYearResult;
	private String lastSchoolGrade;
	private String lastSchoolMarks;
	
	/*Higher And Technical Fields*/
	private String applicantType;
	private int applicantSiblings;
	private String applicantFatherExFreedomFighter;
	private String fatherStruggleName;
	private String fatherFreedomFighterType;
	private String applicantFatherExServiceMen;
	private String applicantFatherPosted;
	private String applicantFatherServiceType;
	private String applicantMotherExFreedomFighter;
	private String motherStruggleName;
	private String motherFreedomFighterType;
	private String applicantMotherExServiceMen;
	private String applicantMotherPosted;
	private String applicantMotherServiceType;
	
	private int fatherIncome;
	private int motherIncome;
	private int guardianIncome;
	private String applicantIsBPL;
	
	private String gaurdianName;
	private String applicantGuardinSalaried;
	private String guardianDOB;
	private String guardianOccupation;	
	private String guardianPanNo;
	
	private String guardianIncomeCertificateImagePath;
	private String applicantGuardianExFreedomFighter;
	private String GuardianStruggleName;
	private String GuardianFreedomFighterType;
	private String applicantGuardianExServiceMen;
	private String applicantGuardianPosted;
	private String applicantGuardianServiceType;
	private String isOtherScholarshipApplied;
	
	private String fatherExServiceMenCertificateImagePath;
	private String motherExServiceMenCertificateImagePath;
	private String GuardianExServiceMenCertificateImagePath;
	private String pgGapCertificateImagePath;
	private String grGapCertificateImagePath;
	private String tGapCertificateImagePath;
	private String sGapCertificateImagePath;
	private String fGapCertificateImagePath;
	private String sdGapCertificateImagePath;
	private String fdgapCertificateImagePath;
	
	/*school form details*/
	private String applicantDisabilityPercentageOther;
	private String otherScholarshipName;
	private String applicantExamSeatNo;
	private String applicantSanskritMarks;
	private String applicantSet;
	
	private String tdiplomaState;
	private String tdiplomaDistrict;
	private String tdiplomaTaluka;
	
	/*Save Type Flag*/
	private String saveType;
	private String tempAppID;
	
	private String rtsflagcastecertificate;
	
	
	/*Old Application ID*/
	private String applicantRenewable;
	private String oldApplicationID;
	
	/*Document Repository*/
	private String checkDomDoc;
	private String fatherCerDoc;
	private String motherCerDoc;
	private String casteValCerDoc;
	private String sscCerDoc;
	private String hscCerDoc;
	
	private String domicileRadio;
	private String fatherRadio;
	private String motherRadio;
	private String casteValRadio;
	private String sscCerRadio;
	private String hscCerRadio;
	
	
	private String capApplicationID;
	private String applicantBPlNo;
	private String sanskritOpted;
	private String applicantDisableType;
	private int applicantDisabilityYear;

	private String gaurdianAddress;
	private int applicant10thTotalMarksObtained;
	private int applicant10thTotalMarks;
	private int applicant12thTotalMarksObtained;
	private int applicant12thTotalMarks;
	
	//change by ankit 9th june
	private String applicantHindiSubject;
	private float applicant10thSanskritMarks;
	private float applicant10thSanskritMarksPerCent;
	private float applicant8thSanskritMarks;
	private float applicant8thSanskritMarksPerCent;
	private String applicantHaveCasteBarCode;
	private String applicantCasteCertificateBarCode; 
	private String applicantBarcodeName;
	private String applicantCasteCertificateDate_barcode;
	private String applicantCasteCertificateAuthority_barcode;
		
	/*changes on 14th june 2017*/
	private String applicantHaveDomicileBarCode;
	private String applicantDomicileCertificateBarCode;
	private String applicantDomicileBarcodeName;
	private String applicantDomicileCertificateDate_barcode;
	private String applicantDomicileCertificateAuthority_barcode;
	private int applicantAge;
	private String applicantHaveIncomeBarCode;
	private String applicantIncomeCertificateBarCode;
	private String applicantIncomeBarcodeName;
	private String applicantIncomeCertificateDate_barcode;
	private String applicantIncomeCertificateAuthority_barcode;
	private String applicantIncomeCertificateNo;
	private String applicantIncomeCertificateDate;
	private String applicantIncomeCertificateAuthority;
	private String readerOpted;
	private String applicantHaveDisabilityBarCode;
	private String applicantDisabilityCertificateBarCode;
	private String applicantDisabilityBarcodeName;
	private String applicantDisabilityCertificateDate_barcode;
	private String applicantDisabilityCertificateAuthority_barcode;
	private String applicantDisabilityCertificateNo;
	private String applicantDisabilityCertificateAuthority;
	private String applicantDisabilityCertificateDate;
	
	private int applicant11thTotalMarksObtained;
	private int applicant11thTotalMarks;
	private String applicant11thGap;
	private String applicant12thStream;
	private float applicant12thScienceMarksObtained;
	private float applicant12thMathsMarksObtained;
	private float applicant12thPhysicsMarksObtained;
	private String applicant12thGap;
	private String applicanttdiplomaGap;
	private String applicantdiplomaGap;
	private String sdiplomaGap;
	private String applicant1styearGap;
	private String applicant2ndyearGap;
	private String applicant3rdyearGap;
	private String applicantGyearGap;
	private String applicantPGyearGap;
	private String parikshaExam;
	private String applicantlastYearResultEighth;
	private String applicantSpouseSalaried;
	
	//17 june 2017
	private String applicantDHERecommended;
	private String applicantJNUStudent;
	private String sanskritOptedPre;
	
	//Added By Swarnadip at 18-June-2017
	private String familyIncomeCertificateImagePath;
	private String readerCertificateImagePath;
	private String disabilityCertificateImagePath;
	private String rationCardImagePath;
	private String bplCardImagePath;
	private String fatherOccupationCertificateImagePath;
	private String fatherDeathCertificateImagePath;
	private String fatherExFreedomFighterCertificateImagePath;
	private String motherOccupationCertificateImagePath;
	private String motherDeathCertificateImagePath;
	private String motherExFreedomFighterCertificateImagePath;
	private String guardianCertificateImagePath; 
	private String gurdianOccupationCertificateImagePath; 
	private String dheletterImagePath; 
	private String jnucertificateImagePath; 
	private String admissionReceiptImagePath; 
	private String guideClgImagePath; 
	private String bonafideCertificateClgImagePath; 
	private String sscTclCertificateImagePath; 
	private String eleventhCertificateImagePath; 
	private String eleventhGapCertificateImagePath;
	private String hscTclCertificateImagePath; 
	private String hscgapCertificateImagePath; 
	private String tdiplomaGapCertificateImagePath; 
	
	private String lastExamCerForFirstYearImagePath; ; 
	private String lastExamCerForSecondYearImagePath; 
	private String lastExamCerForThirdYearImagePath;  
	private String lastExamCerForGraduationYearImagePath; 
	private String lastExamCerForPGraduationYearImagePath; 
	private String marksheetCertificateImagePath; 
	private String hostelCertificateImagePath; 
	private String wardenNOCCertificateImagePath;
	
	//change on 25th june
	private String applicant10thResult;
	private String applicant11thResult;
	private String applicant12thResult;
	private String sscIntegrationFlag;
	private String hscIntegrationFlag;
	private String casteValidityIntegrationFlag;
	
	
	//change on 2nd July
	private String fdDiplomaCertificateImagePath;
	private String sdDiplomaCertificateImagePath;
	
	//change on 7th July
	private String tdiplomaCertificateImagePath;
	
	private String income_is_rts_seeded_data;
	private String income_verification_status;
	private String domicile_is_rts_seeded_data;
	private String domicile_verification_status;
	private String disaility_is_rts_seeded_data;
	private String disaility_verification_status;
	private String caste_is_rts_seeded_data;
	private String caste_verification_status;
	private String ssc_verification_status;
	private String hsc_verification_status;
	private String cval_verification_status;

	//change on 11th July
	private String applicantHaveIncomeCertificate;
	private String applicantHaveDisabilityCertificate;
	private String applicantHaveCasteCertificate;
	private String applicantHaveCertificateDomicile;
	
	//change on 13th July
	private String applicantHaveBPLCertificate;
	private String applicantHaveBPLBarCode;
	private String applicantBPLBarCode;
	private String applicantBPLBarcodeName;
	private String applicantBPLAuthority; 
	private String applicantBPLDate;
	private String applicantSchoolDistrict;
	private String applicantSchoolTaluka;
	private String applicantDomicileState;
	
	// Document List
	private ArrayList<DocumentEntity> documentList;
	
	//change on 19th July 2017
	private String annualIncome;
	private String incomeCertifcateFile;
	private String collegeResultCount;
	private String domicileCertificateFile1;
	private String readerDeclartionCertificateFile;
	private String disabilityCertificateFile;
	private String rationCardCertificateFile;
	private String bplCardCertificateFile;
	private String casteCertificateFile;
	private String fatherOccupationCertificateFile;
	private String fatherDeathCertificateFile;
	private String fatherExFreedomFighterCertificateFile;
	private String fatherExServiceMenCertificateFile;
	private String motherOccupationCertificateFile;
	private String motherDeathCertificateFile;
	private String motherExFreedomFighterCertificateFile;
	private String motherExServiceMenCertificateFile;
	private String gurdianCertificateFile;
	private String gurdianOccupationCertificateFile;
	private String dheLetterCertificateFile;
	private String jnuCertificateCertificateFile;
	private String admissionReceiptCertificateFile;
	private String guideLetterCertificateFile;
	private String bonafideCertificateFile;
	private String casteValidityCertificateFile;
	private String SSCTCCertificateFile;
	private String eleventhCertificateFile;
	private String eleventhGAPCertificateFile;
	private String HSCTCCertificateFile;
	private String HSCGAPCertificateFile;
	private String thirdyearDiplomaCertificateFile;
	private String thirdyearDiplomaGAPCertificateFile;
	private String firstyearDiplomaCertificateFile;
	private String firstyeardiplomaGapCertificateFile;
	private String secondyearDiplomaCertificateFile;
	private String secondyearDiplomaGAPCertificateFile;
	private String firstyearcollegeGapCertificateFile;
	private String firastyearcolleegelastexamCertificateFile;
	private String secondyearcollegeGapCertificateFile;
	private String secondyearcollegelastexamCertificateFile;
	private String thirdyearcollegeGapCertificateFile;
	private String thirdyearcollegelastexamCertificateFile;
	private String graduationGapCertificateFile;
	private String grauationlastexamCertificateFile;
	private String postGraduationGapCertificateFile;
	private String postGraduationlastyearexamCertificateFile;
	private String lastExamMarksheetCertificateFile;
	private String hostelCertificateFile;
	private String wardenCertificateFile;
	
	
	//not required with form
	private String prefTable;
	
	//for data-file-model directive. Need to be removed later
	private JSONObject uploadedDocumentCV;
	private JSONObject uploadedDocumentDom;
	private JSONObject uploadedDocumentSSC;
	private JSONObject uploadedDocumentHSC;
	
	//change on 20th July 2017
	private String sscCertificateName;
	
	//change on 25th July 2017
	private String fYearDistrict;
	private String fYearTaluka;
	private String sYearDistrict;
	private String sYearTaluka;
	private String tYearDistrict;
	private String tYearTaluka;
	private String gYearDistrict;
	private String gYearTaluka;
	private String pgYearDistrict;
	private String pgYearTaluka;
	private String diplomaYearDistrict;
	private String diplomaYearTaluka;
	private String sdiplomaYearDistrict;
	private String sdiplomaYearTaluka;
	
	//change on 25th July 2017
	private String isProfessional;
	
	//added on 26th July 2017(Swarnadip Ghosh)
	private String attendance;
	
	public String getIsProfessional() {
		return isProfessional;
	}
	public void setIsProfessional(String isProfessional) {
		this.isProfessional = isProfessional;
	}
	public String getfYearDistrict() {
		return fYearDistrict;
	}
	public void setfYearDistrict(String fYearDistrict) {
		this.fYearDistrict = fYearDistrict;
	}
	public String getfYearTaluka() {
		return fYearTaluka;
	}
	public void setfYearTaluka(String fYearTaluka) {
		this.fYearTaluka = fYearTaluka;
	}
	public String getsYearDistrict() {
		return sYearDistrict;
	}
	public void setsYearDistrict(String sYearDistrict) {
		this.sYearDistrict = sYearDistrict;
	}
	public String getsYearTaluka() {
		return sYearTaluka;
	}
	public void setsYearTaluka(String sYearTaluka) {
		this.sYearTaluka = sYearTaluka;
	}
	public String gettYearDistrict() {
		return tYearDistrict;
	}
	public void settYearDistrict(String tYearDistrict) {
		this.tYearDistrict = tYearDistrict;
	}
	public String gettYearTaluka() {
		return tYearTaluka;
	}
	public void settYearTaluka(String tYearTaluka) {
		this.tYearTaluka = tYearTaluka;
	}
	public String getgYearDistrict() {
		return gYearDistrict;
	}
	public void setgYearDistrict(String gYearDistrict) {
		this.gYearDistrict = gYearDistrict;
	}
	public String getgYearTaluka() {
		return gYearTaluka;
	}
	public void setgYearTaluka(String gYearTaluka) {
		this.gYearTaluka = gYearTaluka;
	}
	public String getPgYearDistrict() {
		return pgYearDistrict;
	}
	public void setPgYearDistrict(String pgYearDistrict) {
		this.pgYearDistrict = pgYearDistrict;
	}
	public String getPgYearTaluka() {
		return pgYearTaluka;
	}
	public void setPgYearTaluka(String pgYearTaluka) {
		this.pgYearTaluka = pgYearTaluka;
	}
	public String getDiplomaYearDistrict() {
		return diplomaYearDistrict;
	}
	public void setDiplomaYearDistrict(String diplomaYearDistrict) {
		this.diplomaYearDistrict = diplomaYearDistrict;
	}
	public String getDiplomaYearTaluka() {
		return diplomaYearTaluka;
	}
	public void setDiplomaYearTaluka(String diplomaYearTaluka) {
		this.diplomaYearTaluka = diplomaYearTaluka;
	}
	public String getSdiplomaYearDistrict() {
		return sdiplomaYearDistrict;
	}
	public void setSdiplomaYearDistrict(String sdiplomaYearDistrict) {
		this.sdiplomaYearDistrict = sdiplomaYearDistrict;
	}
	public String getSdiplomaYearTaluka() {
		return sdiplomaYearTaluka;
	}
	public void setSdiplomaYearTaluka(String sdiplomaYearTaluka) {
		this.sdiplomaYearTaluka = sdiplomaYearTaluka;
	}
	public String getSscCertificateName() {
		return sscCertificateName;
	}
	public void setSscCertificateName(String sscCertificateName) {
		this.sscCertificateName = sscCertificateName;
	}
	
	public JSONObject getUploadedDocumentDom() {
		return uploadedDocumentDom;
	}
	public void setUploadedDocumentDom(JSONObject uploadedDocumentDom) {
		this.uploadedDocumentDom = uploadedDocumentDom;
	}
	public JSONObject getUploadedDocumentSSC() {
		return uploadedDocumentSSC;
	}
	public void setUploadedDocumentSSC(JSONObject uploadedDocumentSSC) {
		this.uploadedDocumentSSC = uploadedDocumentSSC;
	}
	public JSONObject getUploadedDocumentHSC() {
		return uploadedDocumentHSC;
	}
	public void setUploadedDocumentHSC(JSONObject uploadedDocumentHSC) {
		this.uploadedDocumentHSC = uploadedDocumentHSC;
	}
	public JSONObject getUploadedDocumentCV() {
		return uploadedDocumentCV;
	}
	public void setUploadedDocumentCV(JSONObject uploadedDocumentCV) {
		this.uploadedDocumentCV = uploadedDocumentCV;
	}
	public String getPrefTable() {
		return prefTable;
	}
	public void setPrefTable(String prefTable) {
		this.prefTable = prefTable;
	}
	public String getCollegeResultCount() {
		return collegeResultCount;
	}
	public void setCollegeResultCount(String collegeResultCount) {
		this.collegeResultCount = collegeResultCount;
	}
	public String getDomicileCertificateFile1() {
		return domicileCertificateFile1;
	}
	public void setDomicileCertificateFile1(String domicileCertificateFile1) {
		this.domicileCertificateFile1 = domicileCertificateFile1;
	}
	public String getReaderDeclartionCertificateFile() {
		return readerDeclartionCertificateFile;
	}
	public void setReaderDeclartionCertificateFile(String readerDeclartionCertificateFile) {
		this.readerDeclartionCertificateFile = readerDeclartionCertificateFile;
	}
	public String getDisabilityCertificateFile() {
		return disabilityCertificateFile;
	}
	public void setDisabilityCertificateFile(String disabilityCertificateFile) {
		this.disabilityCertificateFile = disabilityCertificateFile;
	}
	public String getRationCardCertificateFile() {
		return rationCardCertificateFile;
	}
	public void setRationCardCertificateFile(String rationCardCertificateFile) {
		this.rationCardCertificateFile = rationCardCertificateFile;
	}
	public String getBplCardCertificateFile() {
		return bplCardCertificateFile;
	}
	public void setBplCardCertificateFile(String bplCardCertificateFile) {
		this.bplCardCertificateFile = bplCardCertificateFile;
	}
	public String getCasteCertificateFile() {
		return casteCertificateFile;
	}
	public void setCasteCertificateFile(String casteCertificateFile) {
		this.casteCertificateFile = casteCertificateFile;
	}
	public String getFatherOccupationCertificateFile() {
		return fatherOccupationCertificateFile;
	}
	public void setFatherOccupationCertificateFile(String fatherOccupationCertificateFile) {
		this.fatherOccupationCertificateFile = fatherOccupationCertificateFile;
	}
	public String getFatherDeathCertificateFile() {
		return fatherDeathCertificateFile;
	}
	public void setFatherDeathCertificateFile(String fatherDeathCertificateFile) {
		this.fatherDeathCertificateFile = fatherDeathCertificateFile;
	}
	public String getFatherExFreedomFighterCertificateFile() {
		return fatherExFreedomFighterCertificateFile;
	}
	public void setFatherExFreedomFighterCertificateFile(String fatherExFreedomFighterCertificateFile) {
		this.fatherExFreedomFighterCertificateFile = fatherExFreedomFighterCertificateFile;
	}
	public String getFatherExServiceMenCertificateFile() {
		return fatherExServiceMenCertificateFile;
	}
	public void setFatherExServiceMenCertificateFile(String fatherExServiceMenCertificateFile) {
		this.fatherExServiceMenCertificateFile = fatherExServiceMenCertificateFile;
	}
	public String getMotherOccupationCertificateFile() {
		return motherOccupationCertificateFile;
	}
	public void setMotherOccupationCertificateFile(String motherOccupationCertificateFile) {
		this.motherOccupationCertificateFile = motherOccupationCertificateFile;
	}
	public String getMotherDeathCertificateFile() {
		return motherDeathCertificateFile;
	}
	public void setMotherDeathCertificateFile(String motherDeathCertificateFile) {
		this.motherDeathCertificateFile = motherDeathCertificateFile;
	}
	public String getMotherExFreedomFighterCertificateFile() {
		return motherExFreedomFighterCertificateFile;
	}
	public void setMotherExFreedomFighterCertificateFile(String motherExFreedomFighterCertificateFile) {
		this.motherExFreedomFighterCertificateFile = motherExFreedomFighterCertificateFile;
	}
	public String getMotherExServiceMenCertificateFile() {
		return motherExServiceMenCertificateFile;
	}
	public void setMotherExServiceMenCertificateFile(String motherExServiceMenCertificateFile) {
		this.motherExServiceMenCertificateFile = motherExServiceMenCertificateFile;
	}
	public String getGurdianCertificateFile() {
		return gurdianCertificateFile;
	}
	public void setGurdianCertificateFile(String gurdianCertificateFile) {
		this.gurdianCertificateFile = gurdianCertificateFile;
	}
	public String getGurdianOccupationCertificateFile() {
		return gurdianOccupationCertificateFile;
	}
	public void setGurdianOccupationCertificateFile(String gurdianOccupationCertificateFile) {
		this.gurdianOccupationCertificateFile = gurdianOccupationCertificateFile;
	}
	public String getDheLetterCertificateFile() {
		return dheLetterCertificateFile;
	}
	public void setDheLetterCertificateFile(String dheLetterCertificateFile) {
		this.dheLetterCertificateFile = dheLetterCertificateFile;
	}
	public String getJnuCertificateCertificateFile() {
		return jnuCertificateCertificateFile;
	}
	public void setJnuCertificateCertificateFile(String jnuCertificateCertificateFile) {
		this.jnuCertificateCertificateFile = jnuCertificateCertificateFile;
	}
	public String getAdmissionReceiptCertificateFile() {
		return admissionReceiptCertificateFile;
	}
	public void setAdmissionReceiptCertificateFile(String admissionReceiptCertificateFile) {
		this.admissionReceiptCertificateFile = admissionReceiptCertificateFile;
	}
	public String getGuideLetterCertificateFile() {
		return guideLetterCertificateFile;
	}
	public void setGuideLetterCertificateFile(String guideLetterCertificateFile) {
		this.guideLetterCertificateFile = guideLetterCertificateFile;
	}
	public String getBonafideCertificateFile() {
		return bonafideCertificateFile;
	}
	public void setBonafideCertificateFile(String bonafideCertificateFile) {
		this.bonafideCertificateFile = bonafideCertificateFile;
	}
	public String getCasteValidityCertificateFile() {
		return casteValidityCertificateFile;
	}
	public void setCasteValidityCertificateFile(String casteValidityCertificateFile) {
		this.casteValidityCertificateFile = casteValidityCertificateFile;
	}
	public String getSSCTCCertificateFile() {
		return SSCTCCertificateFile;
	}
	public void setSSCTCCertificateFile(String sSCTCCertificateFile) {
		SSCTCCertificateFile = sSCTCCertificateFile;
	}
	public String getEleventhCertificateFile() {
		return eleventhCertificateFile;
	}
	public void setEleventhCertificateFile(String eleventhCertificateFile) {
		this.eleventhCertificateFile = eleventhCertificateFile;
	}
	public String getEleventhGAPCertificateFile() {
		return eleventhGAPCertificateFile;
	}
	public void setEleventhGAPCertificateFile(String eleventhGAPCertificateFile) {
		this.eleventhGAPCertificateFile = eleventhGAPCertificateFile;
	}
	public String getHSCTCCertificateFile() {
		return HSCTCCertificateFile;
	}
	public void setHSCTCCertificateFile(String HSCTCCertificateFile) {
		HSCTCCertificateFile = HSCTCCertificateFile;
	}
	public String getHSCGAPCertificateFile() {
		return HSCGAPCertificateFile;
	}
	public void setHSCGAPCertificateFile(String hSCGAPCertificateFile) {
		HSCGAPCertificateFile = hSCGAPCertificateFile;
	}
	public String getThirdyearDiplomaCertificateFile() {
		return thirdyearDiplomaCertificateFile;
	}
	public void setThirdyearDiplomaCertificateFile(String thirdyearDiplomaCertificateFile) {
		this.thirdyearDiplomaCertificateFile = thirdyearDiplomaCertificateFile;
	}
	public String getThirdyearDiplomaGAPCertificateFile() {
		return thirdyearDiplomaGAPCertificateFile;
	}
	public void setThirdyearDiplomaGAPCertificateFile(String thirdyearDiplomaGAPCertificateFile) {
		this.thirdyearDiplomaGAPCertificateFile = thirdyearDiplomaGAPCertificateFile;
	}
	public String getFirstyearDiplomaCertificateFile() {
		return firstyearDiplomaCertificateFile;
	}
	public void setFirstyearDiplomaCertificateFile(String firstyearDiplomaCertificateFile) {
		this.firstyearDiplomaCertificateFile = firstyearDiplomaCertificateFile;
	}
	public String getFirstyeardiplomaGapCertificateFile() {
		return firstyeardiplomaGapCertificateFile;
	}
	public void setFirstyeardiplomaGapCertificateFile(String firstyeardiplomaGapCertificateFile) {
		this.firstyeardiplomaGapCertificateFile = firstyeardiplomaGapCertificateFile;
	}
	public String getSecondyearDiplomaCertificateFile() {
		return secondyearDiplomaCertificateFile;
	}
	public void setSecondyearDiplomaCertificateFile(String secondyearDiplomaCertificateFile) {
		this.secondyearDiplomaCertificateFile = secondyearDiplomaCertificateFile;
	}
	public String getSecondyearDiplomaGAPCertificateFile() {
		return secondyearDiplomaGAPCertificateFile;
	}
	public void setSecondyearDiplomaGAPCertificateFile(String secondyearDiplomaGAPCertificateFile) {
		this.secondyearDiplomaGAPCertificateFile = secondyearDiplomaGAPCertificateFile;
	}
	public String getFirstyearcollegeGapCertificateFile() {
		return firstyearcollegeGapCertificateFile;
	}
	public void setFirstyearcollegeGapCertificateFile(String firstyearcollegeGapCertificateFile) {
		this.firstyearcollegeGapCertificateFile = firstyearcollegeGapCertificateFile;
	}
	public String getFirastyearcolleegelastexamCertificateFile() {
		return firastyearcolleegelastexamCertificateFile;
	}
	public void setFirastyearcolleegelastexamCertificateFile(String firastyearcolleegelastexamCertificateFile) {
		this.firastyearcolleegelastexamCertificateFile = firastyearcolleegelastexamCertificateFile;
	}
	public String getSecondyearcollegeGapCertificateFile() {
		return secondyearcollegeGapCertificateFile;
	}
	public void setSecondyearcollegeGapCertificateFile(String secondyearcollegeGapCertificateFile) {
		this.secondyearcollegeGapCertificateFile = secondyearcollegeGapCertificateFile;
	}
	public String getSecondyearcollegelastexamCertificateFile() {
		return secondyearcollegelastexamCertificateFile;
	}
	public void setSecondyearcollegelastexamCertificateFile(String secondyearcollegelastexamCertificateFile) {
		this.secondyearcollegelastexamCertificateFile = secondyearcollegelastexamCertificateFile;
	}
	public String getThirdyearcollegeGapCertificateFile() {
		return thirdyearcollegeGapCertificateFile;
	}
	public void setThirdyearcollegeGapCertificateFile(String thirdyearcollegeGapCertificateFile) {
		this.thirdyearcollegeGapCertificateFile = thirdyearcollegeGapCertificateFile;
	}
	public String getThirdyearcollegelastexamCertificateFile() {
		return thirdyearcollegelastexamCertificateFile;
	}
	public void setThirdyearcollegelastexamCertificateFile(String thirdyearcollegelastexamCertificateFile) {
		this.thirdyearcollegelastexamCertificateFile = thirdyearcollegelastexamCertificateFile;
	}
	public String getGraduationGapCertificateFile() {
		return graduationGapCertificateFile;
	}
	public void setGraduationGapCertificateFile(String graduationGapCertificateFile) {
		this.graduationGapCertificateFile = graduationGapCertificateFile;
	}
	public String getGrauationlastexamCertificateFile() {
		return grauationlastexamCertificateFile;
	}
	public void setGrauationlastexamCertificateFile(String grauationlastexamCertificateFile) {
		this.grauationlastexamCertificateFile = grauationlastexamCertificateFile;
	}
	public String getPostGraduationGapCertificateFile() {
		return postGraduationGapCertificateFile;
	}
	public void setPostGraduationGapCertificateFile(String postGraduationGapCertificateFile) {
		this.postGraduationGapCertificateFile = postGraduationGapCertificateFile;
	}
	public String getPostGraduationlastyearexamCertificateFile() {
		return postGraduationlastyearexamCertificateFile;
	}
	public void setPostGraduationlastyearexamCertificateFile(String postGraduationlastyearexamCertificateFile) {
		this.postGraduationlastyearexamCertificateFile = postGraduationlastyearexamCertificateFile;
	}
	public String getLastExamMarksheetCertificateFile() {
		return lastExamMarksheetCertificateFile;
	}
	public void setLastExamMarksheetCertificateFile(String lastExamMarksheetCertificateFile) {
		this.lastExamMarksheetCertificateFile = lastExamMarksheetCertificateFile;
	}
	public String getHostelCertificateFile() {
		return hostelCertificateFile;
	}
	public void setHostelCertificateFile(String hostelCertificateFile) {
		this.hostelCertificateFile = hostelCertificateFile;
	}
	public String getWardenCertificateFile() {
		return wardenCertificateFile;
	}
	public void setWardenCertificateFile(String wardenCertificateFile) {
		this.wardenCertificateFile = wardenCertificateFile;
	}
	public String getIncomeCertifcateFile() {
		return incomeCertifcateFile;
	}
	public void setIncomeCertifcateFile(String incomeCertifcateFile) {
		this.incomeCertifcateFile = incomeCertifcateFile;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public ArrayList<DocumentEntity> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(ArrayList<DocumentEntity> documentList) {
		this.documentList = documentList;
	}
	public String getApplicantHaveCertificateDomicile() {
		return applicantHaveCertificateDomicile;
	}
	public void setApplicantHaveCertificateDomicile(String applicantHaveCertificateDomicile) {
		this.applicantHaveCertificateDomicile = applicantHaveCertificateDomicile;
	}
	public String getApplicantDomicileState() {
		return applicantDomicileState;
	}
	public void setApplicantDomicileState(String applicantDomicileState) {
		this.applicantDomicileState = applicantDomicileState;
	}
	public String getApplicantSchoolDistrict() {
		return applicantSchoolDistrict;
	}
	public void setApplicantSchoolDistrict(String applicantSchoolDistrict) {
		this.applicantSchoolDistrict = applicantSchoolDistrict;
	}
	public String getApplicantSchoolTaluka() {
		return applicantSchoolTaluka;
	}
	public void setApplicantSchoolTaluka(String applicantSchoolTaluka) {
		this.applicantSchoolTaluka = applicantSchoolTaluka;
	}
	public String getApplicantHaveBPLCertificate() {
		return applicantHaveBPLCertificate;
	}
	public void setApplicantHaveBPLCertificate(String applicantHaveBPLCertificate) {
		this.applicantHaveBPLCertificate = applicantHaveBPLCertificate;
	}
	public String getApplicantHaveBPLBarCode() {
		return applicantHaveBPLBarCode;
	}
	public void setApplicantHaveBPLBarCode(String applicantHaveBPLBarCode) {
		this.applicantHaveBPLBarCode = applicantHaveBPLBarCode;
	}
	public String getApplicantBPLBarCode() {
		return applicantBPLBarCode;
	}
	public void setApplicantBPLBarCode(String applicantBPLBarCode) {
		this.applicantBPLBarCode = applicantBPLBarCode;
	}
	public String getApplicantBPLBarcodeName() {
		return applicantBPLBarcodeName;
	}
	public void setApplicantBPLBarcodeName(String applicantBPLBarcodeName) {
		this.applicantBPLBarcodeName = applicantBPLBarcodeName;
	}
	public String getApplicantBPLAuthority() {
		return applicantBPLAuthority;
	}
	public void setApplicantBPLAuthority(String applicantBPLAuthority) {
		this.applicantBPLAuthority = applicantBPLAuthority;
	}
	public String getApplicantBPLDate() {
		return applicantBPLDate;
	}
	public void setApplicantBPLDate(String applicantBPLDate) {
		this.applicantBPLDate = applicantBPLDate;
	}
	public String getApplicantHaveCasteCertificate() {
		return applicantHaveCasteCertificate;
	}
	public void setApplicantHaveCasteCertificate(String applicantHaveCasteCertificate) {
		this.applicantHaveCasteCertificate = applicantHaveCasteCertificate;
	}
	public String getApplicantHaveDisabilityCertificate() {
		return applicantHaveDisabilityCertificate;
	}
	public void setApplicantHaveDisabilityCertificate(String applicantHaveDisabilityCertificate) {
		this.applicantHaveDisabilityCertificate = applicantHaveDisabilityCertificate;
	}
	public String getApplicantHaveIncomeCertificate() {
		return applicantHaveIncomeCertificate;
	}
	public void setApplicantHaveIncomeCertificate(String applicantHaveIncomeCertificate) {
		this.applicantHaveIncomeCertificate = applicantHaveIncomeCertificate;
	}
	public String getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(String userIndex) {
		this.userIndex = userIndex;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getSchemeID() {
		return schemeID;
	}
	public void setSchemeID(String schemeID) {
		this.schemeID = schemeID;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getAppID() {
		return appID;
	}
	public void setAppID(String appID) {
		this.appID = appID;
	}
	public String getApplicantDomicile() {
		return applicantDomicile;
	}
	public void setApplicantDomicile(String applicantDomicile) {
		this.applicantDomicile = applicantDomicile;
	}
	public String getBankSeededAadhaar() {
		return bankSeededAadhaar;
	}
	public void setBankSeededAadhaar(String bankSeededAadhaar) {
		this.bankSeededAadhaar = bankSeededAadhaar;
	}
	public String getApplicantDomicileCertificateNo() {
		return applicantDomicileCertificateNo;
	}
	public void setApplicantDomicileCertificateNo(String applicantDomicileCertificateNo) {
		this.applicantDomicileCertificateNo = applicantDomicileCertificateNo;
	}
	public int getApplicantDomicileCertificateYear() {
		return applicantDomicileCertificateYear;
	}
	public void setApplicantDomicileCertificateYear(int applicantDomicileCertificateYear) {
		this.applicantDomicileCertificateYear = applicantDomicileCertificateYear;
	}
	public String getApplicantSARALNO() {
		return applicantSARALNO;
	}
	public void setApplicantSARALNO(String applicantSARALNO) {
		this.applicantSARALNO = applicantSARALNO;
	}
	public String getApplicantGRNNO() {
		return applicantGRNNO;
	}
	public void setApplicantGRNNO(String applicantGRNNO) {
		this.applicantGRNNO = applicantGRNNO;
	}
	public String getApplicantAadhaarUID() {
		return applicantAadhaarUID;
	}
	public void setApplicantAadhaarUID(String applicantAadhaarUID) {
		this.applicantAadhaarUID = applicantAadhaarUID;
	}
	public String getApplicantFullName() {
		return applicantFullName;
	}
	public void setApplicantFullName(String applicantFullName) {
		this.applicantFullName = applicantFullName;
	}
	public String getApplicantMobileNo() {
		return applicantMobileNo;
	}
	public void setApplicantMobileNo(String applicantMobileNo) {
		this.applicantMobileNo = applicantMobileNo;
	}
	public String getApplicantEmailID() {
		return applicantEmailID;
	}
	public void setApplicantEmailID(String applicantEmailID) {
		this.applicantEmailID = applicantEmailID;
	}
	public String getApplicantDOB() {
		return applicantDOB;
	}
	public void setApplicantDOB(String applicantDOB) {
		this.applicantDOB = applicantDOB;
	}
	public String getApplicantGender() {
		return applicantGender;
	}
	public void setApplicantGender(String applicantGender) {
		this.applicantGender = applicantGender;
	}
	public String getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(String familyIncome) {
		this.familyIncome = familyIncome;
	}
	public String getApplicantMotherTongue() {
		return applicantMotherTongue;
	}
	public void setApplicantMotherTongue(String applicantMotherTongue) {
		this.applicantMotherTongue = applicantMotherTongue;
	}
	public String getApplicantCasteCategory() {
		return applicantCasteCategory;
	}
	public void setApplicantCasteCategory(String applicantCasteCategory) {
		this.applicantCasteCategory = applicantCasteCategory;
	}
	public String getApplicantCaste() {
		return applicantCaste;
	}
	public void setApplicantCaste(String applicantCaste) {
		this.applicantCaste = applicantCaste;
	}
	public String getApplicantSubCaste() {
		return applicantSubCaste;
	}
	public void setApplicantSubCaste(String applicantSubCaste) {
		this.applicantSubCaste = applicantSubCaste;
	}
	public String getApplicantCasteCertificateNo() {
		return applicantCasteCertificateNo;
	}
	public void setApplicantCasteCertificateNo(String applicantCasteCertificateNo) {
		this.applicantCasteCertificateNo = applicantCasteCertificateNo;
	}
	public String getApplicantCasteCertificateDate() {
		return applicantCasteCertificateDate;
	}
	public void setApplicantCasteCertificateDate(String applicantCasteCertificateDate) {
		this.applicantCasteCertificateDate = applicantCasteCertificateDate;
	}
	public int getApplicantCasteCertificateYear() {
		return applicantCasteCertificateYear;
	}
	public void setApplicantCasteCertificateYear(int applicantCasteCertificateYear) {
		this.applicantCasteCertificateYear = applicantCasteCertificateYear;
	}
	public String getApplicantCasteDistrict() {
		return applicantCasteDistrict;
	}
	public void setApplicantCasteDistrict(String applicantCasteDistrict) {
		this.applicantCasteDistrict = applicantCasteDistrict;
	}
	public String getApplicantCasteTaluka() {
		return applicantCasteTaluka;
	}
	public void setApplicantCasteTaluka(String applicantCasteTaluka) {
		this.applicantCasteTaluka = applicantCasteTaluka;
	}
	public String getApplicantCasteCertificateSubdivision() {
		return applicantCasteCertificateSubdivision;
	}
	public void setApplicantCasteCertificateSubdivision(String applicantCasteCertificateSubdivision) {
		this.applicantCasteCertificateSubdivision = applicantCasteCertificateSubdivision;
	}
	public String getApplicantCasteCertificateAuthority() {
		return applicantCasteCertificateAuthority;
	}
	public void setApplicantCasteCertificateAuthority(String applicantCasteCertificateAuthority) {
		this.applicantCasteCertificateAuthority = applicantCasteCertificateAuthority;
	}
	public String getApplicantReligion() {
		return applicantReligion;
	}
	public void setApplicantReligion(String applicantReligion) {
		this.applicantReligion = applicantReligion;
	}
	public String getApplicantAddress() {
		return applicantAddress;
	}
	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
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
	public int getApplicantPinCode() {
		return applicantPinCode;
	}
	public void setApplicantPinCode(int applicantPinCode) {
		this.applicantPinCode = applicantPinCode;
	}
	public String getAddressIsPermanent() {
		return addressIsPermanent;
	}
	public void setAddressIsPermanent(String addressIsPermanent) {
		this.addressIsPermanent = addressIsPermanent;
	}
	public String getApplicantAddress_correspondence() {
		return applicantAddress_correspondence;
	}
	public void setApplicantAddress_correspondence(String applicantAddress_correspondence) {
		this.applicantAddress_correspondence = applicantAddress_correspondence;
	}
	public String getApplicantState_correspondence() {
		return applicantState_correspondence;
	}
	public void setApplicantState_correspondence(String applicantState_correspondence) {
		this.applicantState_correspondence = applicantState_correspondence;
	}
	public String getApplicantDistrict_correspondence() {
		return applicantDistrict_correspondence;
	}
	public void setApplicantDistrict_correspondence(String applicantDistrict_correspondence) {
		this.applicantDistrict_correspondence = applicantDistrict_correspondence;
	}
	public String getApplicantTaluka_correspondence() {
		return applicantTaluka_correspondence;
	}
	public void setApplicantTaluka_correspondence(String applicantTaluka_correspondence) {
		this.applicantTaluka_correspondence = applicantTaluka_correspondence;
	}
	public String getApplicantCity_correspondence() {
		return applicantCity_correspondence;
	}
	public void setApplicantCity_correspondence(String applicantCity_correspondence) {
		this.applicantCity_correspondence = applicantCity_correspondence;
	}
	public int getApplicantPinCode_correspondence() {
		return applicantPinCode_correspondence;
	}
	public void setApplicantPinCode_correspondence(int applicantPinCode_correspondence) {
		this.applicantPinCode_correspondence = applicantPinCode_correspondence;
	}
	public String getApplicantFatherAlive() {
		return applicantFatherAlive;
	}
	public void setApplicantFatherAlive(String applicantFatherAlive) {
		this.applicantFatherAlive = applicantFatherAlive;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getFatherDOB() {
		return fatherDOB;
	}
	public void setFatherDOB(String fatherDOB) {
		this.fatherDOB = fatherDOB;
	}
	public String getFatherOccupation() {
		return fatherOccupation;
	}
	public void setFatherOccupation(String fatherOccupation) {
		this.fatherOccupation = fatherOccupation;
	}
	public String getFatherPanNo() {
		return fatherPanNo;
	}
	public void setFatherPanNo(String fatherPanNo) {
		this.fatherPanNo = fatherPanNo;
	}
	public String getFatherIncomeCertificateNo() {
		return fatherIncomeCertificateNo;
	}
	public void setFatherIncomeCertificateNo(String fatherIncomeCertificateNo) {
		this.fatherIncomeCertificateNo = fatherIncomeCertificateNo;
	}
	public String getApplicantFatherSalaried() {
		return applicantFatherSalaried;
	}
	public void setApplicantFatherSalaried(String applicantFatherSalaried) {
		this.applicantFatherSalaried = applicantFatherSalaried;
	}
	public String getApplicantMotherAlive() {
		return applicantMotherAlive;
	}
	public void setApplicantMotherAlive(String applicantMotherAlive) {
		this.applicantMotherAlive = applicantMotherAlive;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMotherDOB() {
		return motherDOB;
	}
	public void setMotherDOB(String motherDOB) {
		this.motherDOB = motherDOB;
	}
	public String getMotherOccupation() {
		return motherOccupation;
	}
	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}
	public String getMotherPanNo() {
		return motherPanNo;
	}
	public void setMotherPanNo(String motherPanNo) {
		this.motherPanNo = motherPanNo;
	}
	public String getMotherIncomeCertificateNo() {
		return motherIncomeCertificateNo;
	}
	public void setMotherIncomeCertificateNo(String motherIncomeCertificateNo) {
		this.motherIncomeCertificateNo = motherIncomeCertificateNo;
	}
	public String getApplicantMotherSalaried() {
		return applicantMotherSalaried;
	}
	public void setApplicantMotherSalaried(String applicantMotherSalaried) {
		this.applicantMotherSalaried = applicantMotherSalaried;
	}
	public String getApplicantIsSalaried() {
		return applicantIsSalaried;
	}
	public void setApplicantIsSalaried(String applicantIsSalaried) {
		this.applicantIsSalaried = applicantIsSalaried;
	}
	public String getApplicantJobType() {
		return applicantJobType;
	}
	public void setApplicantJobType(String applicantJobType) {
		this.applicantJobType = applicantJobType;
	}
	public String getApplicantIncome() {
		return applicantIncome;
	}
	public void setApplicantIncome(String applicantIncome) {
		this.applicantIncome = applicantIncome;
	}
	public String getApplicantPanNo() {
		return applicantPanNo;
	}
	public void setApplicantPanNo(String applicantPanNo) {
		this.applicantPanNo = applicantPanNo;
	}
	public String getApplicantIsPhysicallyHandicapped() {
		return applicantIsPhysicallyHandicapped;
	}
	public void setApplicantIsPhysicallyHandicapped(String applicantIsPhysicallyHandicapped) {
		this.applicantIsPhysicallyHandicapped = applicantIsPhysicallyHandicapped;
	}
	public String getApplicantDisabilityType() {
		return applicantDisabilityType;
	}
	public void setApplicantDisabilityType(String applicantDisabilityType) {
		this.applicantDisabilityType = applicantDisabilityType;
	}
	public String getApplicantDisabilityPercentage() {
		return applicantDisabilityPercentage;
	}
	public void setApplicantDisabilityPercentage(String applicantDisabilityPercentage) {
		this.applicantDisabilityPercentage = applicantDisabilityPercentage;
	}
	public String getApplicantChildNo() {
		return applicantChildNo;
	}
	public void setApplicantChildNo(String applicantChildNo) {
		this.applicantChildNo = applicantChildNo;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getApplicantCasteValidityNo() {
		return applicantCasteValidityNo;
	}
	public void setApplicantCasteValidityNo(String applicantCasteValidityNo) {
		this.applicantCasteValidityNo = applicantCasteValidityNo;
	}
	public String getApplicantCasteValidityCertificateDate() {
		return applicantCasteValidityCertificateDate;
	}
	public void setApplicantCasteValidityCertificateDate(String applicantCasteValidityCertificateDate) {
		this.applicantCasteValidityCertificateDate = applicantCasteValidityCertificateDate;
	}
	public int getApplicantCasteValidityCertificateYear() {
		return applicantCasteValidityCertificateYear;
	}
	public void setApplicantCasteValidityCertificateYear(int applicantCasteValidityCertificateYear) {
		this.applicantCasteValidityCertificateYear = applicantCasteValidityCertificateYear;
	}
	public String getCasteValidityDistrict() {
		return casteValidityDistrict;
	}
	public void setCasteValidityDistrict(String casteValidityDistrict) {
		this.casteValidityDistrict = casteValidityDistrict;
	}
	public String getApplicantCasteValidityTaluka() {
		return applicantCasteValidityTaluka;
	}
	public void setApplicantCasteValidityTaluka(String applicantCasteValidityTaluka) {
		this.applicantCasteValidityTaluka = applicantCasteValidityTaluka;
	}
	public int getJoiningYear() {
		return joiningYear;
	}
	public void setJoiningYear(int joiningYear) {
		this.joiningYear = joiningYear;
	}
	public String getCurrentCourseCollegeRegisterNo() {
		return currentCourseCollegeRegisterNo;
	}
	public void setCurrentCourseCollegeRegisterNo(String currentCourseCollegeRegisterNo) {
		this.currentCourseCollegeRegisterNo = currentCourseCollegeRegisterNo;
	}
	public String getCurrentCourseState() {
		return currentCourseState;
	}
	public void setCurrentCourseState(String currentCourseState) {
		this.currentCourseState = currentCourseState;
	}
	public String getCurrentCourseDistrict() {
		return currentCourseDistrict;
	}
	public void setCurrentCourseDistrict(String currentCourseDistrict) {
		this.currentCourseDistrict = currentCourseDistrict;
	}
	public String getCurrentCourseTaluka() {
		return currentCourseTaluka;
	}
	public void setCurrentCourseTaluka(String currentCourseTaluka) {
		this.currentCourseTaluka = currentCourseTaluka;
	}
	public String getCurrentCourseCollegeName() {
		return currentCourseCollegeName;
	}
	public void setCurrentCourseCollegeName(String currentCourseCollegeName) {
		this.currentCourseCollegeName = currentCourseCollegeName;
	}
	public String getCurrentCourseCollegeType() {
		return currentCourseCollegeType;
	}
	public void setCurrentCourseCollegeType(String currentCourseCollegeType) {
		this.currentCourseCollegeType = currentCourseCollegeType;
	}
	public String getCurrentCourseCourseName() {
		return currentCourseCourseName;
	}
	public void setCurrentCourseCourseName(String currentCourseCourseName) {
		this.currentCourseCourseName = currentCourseCourseName;
	}
	public String getCurrentCourseUniversityName() {
		return currentCourseUniversityName;
	}
	public void setCurrentCourseUniversityName(String currentCourseUniversityName) {
		this.currentCourseUniversityName = currentCourseUniversityName;
	}
	public int getCurrentCourseYear() {
		return currentCourseYear;
	}
	public void setCurrentCourseYear(int currentCourseYear) {
		this.currentCourseYear = currentCourseYear;
	}
	public String getCurrentCourseGrantType() {
		return currentCourseGrantType;
	}
	public void setCurrentCourseGrantType(String currentCourseGrantType) {
		this.currentCourseGrantType = currentCourseGrantType;
	}
	public String getCurrentCourseCap() {
		return currentCourseCap;
	}
	public void setCurrentCourseCap(String currentCourseCap) {
		this.currentCourseCap = currentCourseCap;
	}
	public String getCurrentCourseCollegeAdmissionNo() {
		return currentCourseCollegeAdmissionNo;
	}
	public void setCurrentCourseCollegeAdmissionNo(String currentCourseCollegeAdmissionNo) {
		this.currentCourseCollegeAdmissionNo = currentCourseCollegeAdmissionNo;
	}
	public String getCurrentCourseAdmissionDate() {
		return currentCourseAdmissionDate;
	}
	public void setCurrentCourseAdmissionDate(String currentCourseAdmissionDate) {
		this.currentCourseAdmissionDate = currentCourseAdmissionDate;
	}
	public String getIsCurrentCourseFeesPaid() {
		return isCurrentCourseFeesPaid;
	}
	public void setIsCurrentCourseFeesPaid(String isCurrentCourseFeesPaid) {
		this.isCurrentCourseFeesPaid = isCurrentCourseFeesPaid;
	}
	public String getIsdiplomaPassed() {
		return isdiplomaPassed;
	}
	public void setIsdiplomaPassed(String isdiplomaPassed) {
		this.isdiplomaPassed = isdiplomaPassed;
	}
	public String getExamMonth() {
		return examMonth;
	}
	public void setExamMonth(String examMonth) {
		this.examMonth = examMonth;
	}
	public float getCurrentCourseExamFees() {
		return currentCourseExamFees;
	}
	public void setCurrentCourseExamFees(float currentCourseExamFees) {
		this.currentCourseExamFees = currentCourseExamFees;
	}
	public float getCurrentCourseTutionFees() {
		return currentCourseTutionFees;
	}
	public void setCurrentCourseTutionFees(float currentCourseTutionFees) {
		this.currentCourseTutionFees = currentCourseTutionFees;
	}
	public float getCurrentCourseOtherFees() {
		return currentCourseOtherFees;
	}
	public void setCurrentCourseOtherFees(float currentCourseOtherFees) {
		this.currentCourseOtherFees = currentCourseOtherFees;
	}
	public String getApplicant10thPassingBoard() {
		return applicant10thPassingBoard;
	}
	public void setApplicant10thPassingBoard(String applicant10thPassingBoard) {
		this.applicant10thPassingBoard = applicant10thPassingBoard;
	}
	public String getApplicantSSCBoard1() {
		return applicantSSCBoard1;
	}
	public void setApplicantSSCBoard1(String applicantSSCBoard1) {
		this.applicantSSCBoard1 = applicantSSCBoard1;
	}
	public String getApplicantSSCDivision() {
		return applicantSSCDivision;
	}
	public void setApplicantSSCDivision(String applicantSSCDivision) {
		this.applicantSSCDivision = applicantSSCDivision;
	}
	public int getApplicant10thPassingYear() {
		return applicant10thPassingYear;
	}
	public void setApplicant10thPassingYear(int applicant10thPassingYear) {
		this.applicant10thPassingYear = applicant10thPassingYear;
	}
	public String getApplicant10thPassingMonth() {
		return applicant10thPassingMonth;
	}
	public void setApplicant10thPassingMonth(String applicant10thPassingMonth) {
		this.applicant10thPassingMonth = applicant10thPassingMonth;
	}
	public String getApplicant10thRollNo() {
		return applicant10thRollNo;
	}
	public void setApplicant10thRollNo(String applicant10thRollNo) {
		this.applicant10thRollNo = applicant10thRollNo;
	}
	public String getApplicant10thMarksObtained() {
		return applicant10thMarksObtained;
	}
	public void setApplicant10thMarksObtained(String applicant10thMarksObtained) {
		this.applicant10thMarksObtained = applicant10thMarksObtained;
	}
	public String getApplicant10thcertificateNumber() {
		return applicant10thcertificateNumber;
	}
	public void setApplicant10thcertificateNumber(String applicant10thcertificateNumber) {
		this.applicant10thcertificateNumber = applicant10thcertificateNumber;
	}
	public String getApplicant10thSchoolName() {
		return applicant10thSchoolName;
	}
	public void setApplicant10thSchoolName(String applicant10thSchoolName) {
		this.applicant10thSchoolName = applicant10thSchoolName;
	}
	public String getApplicant10thSchoolDistrict() {
		return applicant10thSchoolDistrict;
	}
	public void setApplicant10thSchoolDistrict(String applicant10thSchoolDistrict) {
		this.applicant10thSchoolDistrict = applicant10thSchoolDistrict;
	}
	public String getApplicant10thSchoolTaluka() {
		return applicant10thSchoolTaluka;
	}
	public void setApplicant10thSchoolTaluka(String applicant10thSchoolTaluka) {
		this.applicant10thSchoolTaluka = applicant10thSchoolTaluka;
	}
	public String getApplicant11thPassingBoard() {
		return applicant11thPassingBoard;
	}
	public void setApplicant11thPassingBoard(String applicant11thPassingBoard) {
		this.applicant11thPassingBoard = applicant11thPassingBoard;
	}
	public String getApplicant11thBoard1() {
		return applicant11thBoard1;
	}
	public void setApplicant11thBoard1(String applicant11thBoard1) {
		this.applicant11thBoard1 = applicant11thBoard1;
	}
	public String getApplicant11thDivision() {
		return applicant11thDivision;
	}
	public void setApplicant11thDivision(String applicant11thDivision) {
		this.applicant11thDivision = applicant11thDivision;
	}
	public int getApplicant11thPassingYear() {
		return applicant11thPassingYear;
	}
	public void setApplicant11thPassingYear(int applicant11thPassingYear) {
		this.applicant11thPassingYear = applicant11thPassingYear;
	}
	public String getApplicant11thPassingMonth() {
		return applicant11thPassingMonth;
	}
	public void setApplicant11thPassingMonth(String applicant11thPassingMonth) {
		this.applicant11thPassingMonth = applicant11thPassingMonth;
	}
	public String getApplicant11thRollNo() {
		return applicant11thRollNo;
	}
	public void setApplicant11thRollNo(String applicant11thRollNo) {
		this.applicant11thRollNo = applicant11thRollNo;
	}
	public String getApplicant11thMarksObtained() {
		return applicant11thMarksObtained;
	}
	public void setApplicant11thMarksObtained(String applicant11thMarksObtained) {
		this.applicant11thMarksObtained = applicant11thMarksObtained;
	}
	public String getApplicant11thcertificateNumber() {
		return applicant11thcertificateNumber;
	}
	public void setApplicant11thcertificateNumber(String applicant11thcertificateNumber) {
		this.applicant11thcertificateNumber = applicant11thcertificateNumber;
	}
	public String getApplicant12thPassingBoard() {
		return applicant12thPassingBoard;
	}
	public void setApplicant12thPassingBoard(String applicant12thPassingBoard) {
		this.applicant12thPassingBoard = applicant12thPassingBoard;
	}
	public String getApplicantHSCBoard1() {
		return applicantHSCBoard1;
	}
	public void setApplicantHSCBoard1(String applicantHSCBoard1) {
		this.applicantHSCBoard1 = applicantHSCBoard1;
	}
	public String getApplicantHSCDivision() {
		return applicantHSCDivision;
	}
	public void setApplicantHSCDivision(String applicantHSCDivision) {
		this.applicantHSCDivision = applicantHSCDivision;
	}
	public int getApplicant12thPassingYear() {
		return applicant12thPassingYear;
	}
	public void setApplicant12thPassingYear(int applicant12thPassingYear) {
		this.applicant12thPassingYear = applicant12thPassingYear;
	}
	public String getApplicant12thPassingMonth() {
		return applicant12thPassingMonth;
	}
	public void setApplicant12thPassingMonth(String applicant12thPassingMonth) {
		this.applicant12thPassingMonth = applicant12thPassingMonth;
	}
	public String getApplicant12thRollNo() {
		return applicant12thRollNo;
	}
	public void setApplicant12thRollNo(String applicant12thRollNo) {
		this.applicant12thRollNo = applicant12thRollNo;
	}
	public String getApplicant12thMarksObtained() {
		return applicant12thMarksObtained;
	}
	public void setApplicant12thMarksObtained(String applicant12thMarksObtained) {
		this.applicant12thMarksObtained = applicant12thMarksObtained;
	}
	public String getApplicant12thcertificateNumber() {
		return applicant12thcertificateNumber;
	}
	public void setApplicant12thcertificateNumber(String applicant12thcertificateNumber) {
		this.applicant12thcertificateNumber = applicant12thcertificateNumber;
	}
	public int getDiplomaAcademicYearPassed() {
		return diplomaAcademicYearPassed;
	}
	public void setDiplomaAcademicYearPassed(int diplomaAcademicYearPassed) {
		this.diplomaAcademicYearPassed = diplomaAcademicYearPassed;
	}
	public String getDiplomaCollegeName() {
		return diplomaCollegeName;
	}
	public void setDiplomaCollegeName(String diplomaCollegeName) {
		this.diplomaCollegeName = diplomaCollegeName;
	}
	public String getDiplomaCourseName() {
		return diplomaCourseName;
	}
	public void setDiplomaCourseName(String diplomaCourseName) {
		this.diplomaCourseName = diplomaCourseName;
	}
	public String getDiplomaUniversityName() {
		return diplomaUniversityName;
	}
	public void setDiplomaUniversityName(String diplomaUniversityName) {
		this.diplomaUniversityName = diplomaUniversityName;
	}
	public String getDiplomaYear() {
		return diplomaYear;
	}
	public void setDiplomaYear(String diplomaYear) {
		this.diplomaYear = diplomaYear;
	}
	public String getDiplomaResult() {
		return diplomaResult;
	}
	public void setDiplomaResult(String diplomaResult) {
		this.diplomaResult = diplomaResult;
	}
	public String getDiplomaMarksObtained() {
		return diplomaMarksObtained;
	}
	public void setDiplomaMarksObtained(String diplomaMarksObtained) {
		this.diplomaMarksObtained = diplomaMarksObtained;
	}
	public int getSdiplomaAcademicYearPassed() {
		return sdiplomaAcademicYearPassed;
	}
	public void setSdiplomaAcademicYearPassed(int sdiplomaAcademicYearPassed) {
		this.sdiplomaAcademicYearPassed = sdiplomaAcademicYearPassed;
	}
	public String getSdiplomaCollegeName() {
		return sdiplomaCollegeName;
	}
	public void setSdiplomaCollegeName(String sdiplomaCollegeName) {
		this.sdiplomaCollegeName = sdiplomaCollegeName;
	}
	public String getSdiplomaCourseName() {
		return sdiplomaCourseName;
	}
	public void setSdiplomaCourseName(String sdiplomaCourseName) {
		this.sdiplomaCourseName = sdiplomaCourseName;
	}
	public String getSdiplomaUniversityName() {
		return sdiplomaUniversityName;
	}
	public void setSdiplomaUniversityName(String sdiplomaUniversityName) {
		this.sdiplomaUniversityName = sdiplomaUniversityName;
	}
	public String getSdiplomaYear() {
		return sdiplomaYear;
	}
	public void setSdiplomaYear(String sdiplomaYear) {
		this.sdiplomaYear = sdiplomaYear;
	}
	public String getSdiplomaResult() {
		return sdiplomaResult;
	}
	public void setSdiplomaResult(String sdiplomaResult) {
		this.sdiplomaResult = sdiplomaResult;
	}
	public String getSdiplomaMarksObtained() {
		return sdiplomaMarksObtained;
	}
	public void setSdiplomaMarksObtained(String sdiplomaMarksObtained) {
		this.sdiplomaMarksObtained = sdiplomaMarksObtained;
	}
	public int getTdiplomaAcademicYearPassed() {
		return tdiplomaAcademicYearPassed;
	}
	public void setTdiplomaAcademicYearPassed(int tdiplomaAcademicYearPassed) {
		this.tdiplomaAcademicYearPassed = tdiplomaAcademicYearPassed;
	}
	public String getTdiplomaCollegeName() {
		return tdiplomaCollegeName;
	}
	public void setTdiplomaCollegeName(String tdiplomaCollegeName) {
		this.tdiplomaCollegeName = tdiplomaCollegeName;
	}
	public String getTdiplomaCourseName() {
		return tdiplomaCourseName;
	}
	public void setTdiplomaCourseName(String tdiplomaCourseName) {
		this.tdiplomaCourseName = tdiplomaCourseName;
	}
	public String getTdiplomaUniversityName() {
		return tdiplomaUniversityName;
	}
	public void setTdiplomaUniversityName(String tdiplomaUniversityName) {
		this.tdiplomaUniversityName = tdiplomaUniversityName;
	}
	public String getTdiplomaYear() {
		return tdiplomaYear;
	}
	public void setTdiplomaYear(String tdiplomaYear) {
		this.tdiplomaYear = tdiplomaYear;
	}
	public String getTdiplomaResult() {
		return tdiplomaResult;
	}
	public void setTdiplomaResult(String tdiplomaResult) {
		this.tdiplomaResult = tdiplomaResult;
	}
	public String getTdiplomaMarksObtained() {
		return tdiplomaMarksObtained;
	}
	public void setTdiplomaMarksObtained(String tdiplomaMarksObtained) {
		this.tdiplomaMarksObtained = tdiplomaMarksObtained;
	}
	public int getfYearAcademicYearPassed() {
		return fYearAcademicYearPassed;
	}
	public void setfYearAcademicYearPassed(int fYearAcademicYearPassed) {
		this.fYearAcademicYearPassed = fYearAcademicYearPassed;
	}
	public String getfYearCollegeName() {
		return fYearCollegeName;
	}
	public void setfYearCollegeName(String fYearCollegeName) {
		this.fYearCollegeName = fYearCollegeName;
	}
	public String getfYearCourseName() {
		return fYearCourseName;
	}
	public void setfYearCourseName(String fYearCourseName) {
		this.fYearCourseName = fYearCourseName;
	}
	public String getfYearUniversityName() {
		return fYearUniversityName;
	}
	public void setfYearUniversityName(String fYearUniversityName) {
		this.fYearUniversityName = fYearUniversityName;
	}
	public String getfYearYear() {
		return fYearYear;
	}
	public void setfYearYear(String fYearYear) {
		this.fYearYear = fYearYear;
	}
	public String getfYearResult() {
		return fYearResult;
	}
	public void setfYearResult(String fYearResult) {
		this.fYearResult = fYearResult;
	}
	public String getfYearMarksObtained() {
		return fYearMarksObtained;
	}
	public void setfYearMarksObtained(String fYearMarksObtained) {
		this.fYearMarksObtained = fYearMarksObtained;
	}
	public int getsYearAcademicYearPassed() {
		return sYearAcademicYearPassed;
	}
	public void setsYearAcademicYearPassed(int sYearAcademicYearPassed) {
		this.sYearAcademicYearPassed = sYearAcademicYearPassed;
	}
	public String getsYearCollegeName() {
		return sYearCollegeName;
	}
	public void setsYearCollegeName(String sYearCollegeName) {
		this.sYearCollegeName = sYearCollegeName;
	}
	public String getsYearCourseName() {
		return sYearCourseName;
	}
	public void setsYearCourseName(String sYearCourseName) {
		this.sYearCourseName = sYearCourseName;
	}
	public String getsYearUniversityName() {
		return sYearUniversityName;
	}
	public void setsYearUniversityName(String sYearUniversityName) {
		this.sYearUniversityName = sYearUniversityName;
	}
	public String getsYearYear() {
		return sYearYear;
	}
	public void setsYearYear(String sYearYear) {
		this.sYearYear = sYearYear;
	}
	public String getsYearResult() {
		return sYearResult;
	}
	public void setsYearResult(String sYearResult) {
		this.sYearResult = sYearResult;
	}
	public String getsYearMarksObtained() {
		return sYearMarksObtained;
	}
	public void setsYearMarksObtained(String sYearMarksObtained) {
		this.sYearMarksObtained = sYearMarksObtained;
	}
	public int gettYearAcademicYearPassed() {
		return tYearAcademicYearPassed;
	}
	public void settYearAcademicYearPassed(int tYearAcademicYearPassed) {
		this.tYearAcademicYearPassed = tYearAcademicYearPassed;
	}
	public String gettYearCollegeName() {
		return tYearCollegeName;
	}
	public void settYearCollegeName(String tYearCollegeName) {
		this.tYearCollegeName = tYearCollegeName;
	}
	public String gettYearCourseName() {
		return tYearCourseName;
	}
	public void settYearCourseName(String tYearCourseName) {
		this.tYearCourseName = tYearCourseName;
	}
	public String gettYearUniversityName() {
		return tYearUniversityName;
	}
	public void settYearUniversityName(String tYearUniversityName) {
		this.tYearUniversityName = tYearUniversityName;
	}
	public String gettYearYear() {
		return tYearYear;
	}
	public void settYearYear(String tYearYear) {
		this.tYearYear = tYearYear;
	}
	public String gettYearResult() {
		return tYearResult;
	}
	public void settYearResult(String tYearResult) {
		this.tYearResult = tYearResult;
	}
	public String gettYearMarksObtained() {
		return tYearMarksObtained;
	}
	public void settYearMarksObtained(String tYearMarksObtained) {
		this.tYearMarksObtained = tYearMarksObtained;
	}
	public int getgYearAcademicYearPassed() {
		return gYearAcademicYearPassed;
	}
	public void setgYearAcademicYearPassed(int gYearAcademicYearPassed) {
		this.gYearAcademicYearPassed = gYearAcademicYearPassed;
	}
	public String getgYearCollegeName() {
		return gYearCollegeName;
	}
	public void setgYearCollegeName(String gYearCollegeName) {
		this.gYearCollegeName = gYearCollegeName;
	}
	public String getgYearCourseName() {
		return gYearCourseName;
	}
	public void setgYearCourseName(String gYearCourseName) {
		this.gYearCourseName = gYearCourseName;
	}
	public String getgYearUniversityName() {
		return gYearUniversityName;
	}
	public void setgYearUniversityName(String gYearUniversityName) {
		this.gYearUniversityName = gYearUniversityName;
	}
	public String getgYearYear() {
		return gYearYear;
	}
	public void setgYearYear(String gYearYear) {
		this.gYearYear = gYearYear;
	}
	public String getgYearResult() {
		return gYearResult;
	}
	public void setgYearResult(String gYearResult) {
		this.gYearResult = gYearResult;
	}
	public String getgYearMarksObtained() {
		return gYearMarksObtained;
	}
	public void setgYearMarksObtained(String gYearMarksObtained) {
		this.gYearMarksObtained = gYearMarksObtained;
	}
	public int getPgYearAcademicYearPassed() {
		return pgYearAcademicYearPassed;
	}
	public void setPgYearAcademicYearPassed(int pgYearAcademicYearPassed) {
		this.pgYearAcademicYearPassed = pgYearAcademicYearPassed;
	}
	public String getPgYearCollegeName() {
		return pgYearCollegeName;
	}
	public void setPgYearCollegeName(String pgYearCollegeName) {
		this.pgYearCollegeName = pgYearCollegeName;
	}
	public String getPgYearCourseName() {
		return pgYearCourseName;
	}
	public void setPgYearCourseName(String pgYearCourseName) {
		this.pgYearCourseName = pgYearCourseName;
	}
	public String getPgYearUniversityName() {
		return pgYearUniversityName;
	}
	public void setPgYearUniversityName(String pgYearUniversityName) {
		this.pgYearUniversityName = pgYearUniversityName;
	}
	public String getPgYearYear() {
		return pgYearYear;
	}
	public void setPgYearYear(String pgYearYear) {
		this.pgYearYear = pgYearYear;
	}
	public String getPgYearResult() {
		return pgYearResult;
	}
	public void setPgYearResult(String pgYearResult) {
		this.pgYearResult = pgYearResult;
	}
	public String getPgYearMarksObtained() {
		return pgYearMarksObtained;
	}
	public void setPgYearMarksObtained(String pgYearMarksObtained) {
		this.pgYearMarksObtained = pgYearMarksObtained;
	}
	public String getApplicantSchoolName() {
		return applicantSchoolName;
	}
	public void setApplicantSchoolName(String applicantSchoolName) {
		this.applicantSchoolName = applicantSchoolName;
	}
	public String getApplicantSchoolIntake() {
		return applicantSchoolIntake;
	}
	public void setApplicantSchoolIntake(String applicantSchoolIntake) {
		this.applicantSchoolIntake = applicantSchoolIntake;
	}
	public int getApplicantAcademicYear() {
		return applicantAcademicYear;
	}
	public void setApplicantAcademicYear(int applicantAcademicYear) {
		this.applicantAcademicYear = applicantAcademicYear;
	}
	public String getApplicantSchoolAdmissionDate() {
		return applicantSchoolAdmissionDate;
	}
	public void setApplicantSchoolAdmissionDate(String applicantSchoolAdmissionDate) {
		this.applicantSchoolAdmissionDate = applicantSchoolAdmissionDate;
	}
	public String getApplicantSchoolStandard() {
		return applicantSchoolStandard;
	}
	public void setApplicantSchoolStandard(String applicantSchoolStandard) {
		this.applicantSchoolStandard = applicantSchoolStandard;
	}
	public String getApplicantlastYearResult() {
		return applicantlastYearResult;
	}
	public void setApplicantlastYearResult(String applicantlastYearResult) {
		this.applicantlastYearResult = applicantlastYearResult;
	}
	public String getApplicantSchoolGrade() {
		return applicantSchoolGrade;
	}
	public void setApplicantSchoolGrade(String applicantSchoolGrade) {
		this.applicantSchoolGrade = applicantSchoolGrade;
	}
	public String getApplicantAttendance() {
		return applicantAttendance;
	}
	public void setApplicantAttendance(String applicantAttendance) {
		this.applicantAttendance = applicantAttendance;
	}
	public String getApplicantBehaviour() {
		return applicantBehaviour;
	}
	public void setApplicantBehaviour(String applicantBehaviour) {
		this.applicantBehaviour = applicantBehaviour;
	}
	public String getApplicantSchoolMarks() {
		return applicantSchoolMarks;
	}
	public void setApplicantSchoolMarks(String applicantSchoolMarks) {
		this.applicantSchoolMarks = applicantSchoolMarks;
	}
	public String getDoYouHaveGRNNo() {
		return doYouHaveGRNNo;
	}
	public void setDoYouHaveGRNNo(String doYouHaveGRNNo) {
		this.doYouHaveGRNNo = doYouHaveGRNNo;
	}
	public String getApplicantUDISECode() {
		return applicantUDISECode;
	}
	public void setApplicantUDISECode(String applicantUDISECode) {
		this.applicantUDISECode = applicantUDISECode;
	}
	public String getApplicantDayScholarDetails() {
		return applicantDayScholarDetails;
	}
	public void setApplicantDayScholarDetails(String applicantDayScholarDetails) {
		this.applicantDayScholarDetails = applicantDayScholarDetails;
	}
	public String getApplicantHostelDistrict() {
		return applicantHostelDistrict;
	}
	public void setApplicantHostelDistrict(String applicantHostelDistrict) {
		this.applicantHostelDistrict = applicantHostelDistrict;
	}
	public String getApplicantHostelTaluka() {
		return applicantHostelTaluka;
	}
	public void setApplicantHostelTaluka(String applicantHostelTaluka) {
		this.applicantHostelTaluka = applicantHostelTaluka;
	}
	public String getHostelType() {
		return hostelType;
	}
	public void setHostelType(String hostelType) {
		this.hostelType = hostelType;
	}
	public String getApplicantHostelName() {
		return applicantHostelName;
	}
	public void setApplicantHostelName(String applicantHostelName) {
		this.applicantHostelName = applicantHostelName;
	}
	public String getHostelAided() {
		return hostelAided;
	}
	public void setHostelAided(String hostelAided) {
		this.hostelAided = hostelAided;
	}
	public String getApplicantHostelAddress() {
		return applicantHostelAddress;
	}
	public void setApplicantHostelAddress(String applicantHostelAddress) {
		this.applicantHostelAddress = applicantHostelAddress;
	}
	public String getCollegeRemarks() {
		return collegeRemarks;
	}
	public void setCollegeRemarks(String collegeRemarks) {
		this.collegeRemarks = collegeRemarks;
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
	public String getFatherIncomeCertificateImagePath() {
		return fatherIncomeCertificateImagePath;
	}
	public void setFatherIncomeCertificateImagePath(String fatherIncomeCertificateImagePath) {
		this.fatherIncomeCertificateImagePath = fatherIncomeCertificateImagePath;
	}
	public String getMotherIncomeCertificateImagePath() {
		return motherIncomeCertificateImagePath;
	}
	public void setMotherIncomeCertificateImagePath(String motherIncomeCertificateImagePath) {
		this.motherIncomeCertificateImagePath = motherIncomeCertificateImagePath;
	}
	public String getSsccertificateImagePath() {
		return ssccertificateImagePath;
	}
	public void setSsccertificateImagePath(String ssccertificateImagePath) {
		this.ssccertificateImagePath = ssccertificateImagePath;
	}
	public String getHsccertificateImagePath() {
		return hsccertificateImagePath;
	}
	public void setHsccertificateImagePath(String hsccertificateImagePath) {
		this.hsccertificateImagePath = hsccertificateImagePath;
	}
	public String getCasteValiityCertificateImagePath() {
		return casteValiityCertificateImagePath;
	}
	public void setCasteValiityCertificateImagePath(String casteValiityCertificateImagePath) {
		this.casteValiityCertificateImagePath = casteValiityCertificateImagePath;
	}
	public String getApplicantMaritalStatus() {
		return applicantMaritalStatus;
	}
	public void setApplicantMaritalStatus(String applicantMaritalStatus) {
		this.applicantMaritalStatus = applicantMaritalStatus;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpouseRelation() {
		return spouseRelation;
	}
	public void setSpouseRelation(String spouseRelation) {
		this.spouseRelation = spouseRelation;
	}
	public String getSpouseAge() {
		return spouseAge;
	}
	public void setSpouseAge(String spouseAge) {
		this.spouseAge = spouseAge;
	}
	public String getSpouseProfession() {
		return spouseProfession;
	}
	public void setSpouseProfession(String spouseProfession) {
		this.spouseProfession = spouseProfession;
	}
	public String getSpouseAddress() {
		return spouseAddress;
	}
	public void setSpouseAddress(String spouseAddress) {
		this.spouseAddress = spouseAddress;
	}
	public String getSpouseState() {
		return spouseState;
	}
	public void setSpouseState(String spouseState) {
		this.spouseState = spouseState;
	}
	public String getSpouseDistrict() {
		return spouseDistrict;
	}
	public void setSpouseDistrict(String spouseDistrict) {
		this.spouseDistrict = spouseDistrict;
	}
	public String getSpouseTaluka() {
		return spouseTaluka;
	}
	public void setSpouseTaluka(String spouseTaluka) {
		this.spouseTaluka = spouseTaluka;
	}
	public String getSpouseCity() {
		return spouseCity;
	}
	public void setSpouseCity(String spouseCity) {
		this.spouseCity = spouseCity;
	}
	public String getSpousePinCode() {
		return spousePinCode;
	}
	public void setSpousePinCode(String spousePinCode) {
		this.spousePinCode = spousePinCode;
	}
	public String getLastSchoolName() {
		return lastSchoolName;
	}
	public void setLastSchoolName(String lastSchoolName) {
		this.lastSchoolName = lastSchoolName;
	}
	public int getLastAcademicYear() {
		return lastAcademicYear;
	}
	public void setLastAcademicYear(int lastAcademicYear) {
		this.lastAcademicYear = lastAcademicYear;
	}
	public String getLastSchoolAdmissionDate() {
		return lastSchoolAdmissionDate;
	}
	public void setLastSchoolAdmissionDate(String lastSchoolAdmissionDate) {
		this.lastSchoolAdmissionDate = lastSchoolAdmissionDate;
	}
	public String getLastSchoolStandard() {
		return lastSchoolStandard;
	}
	public void setLastSchoolStandard(String lastSchoolStandard) {
		this.lastSchoolStandard = lastSchoolStandard;
	}
	public String getLastYearResult() {
		return lastYearResult;
	}
	public void setLastYearResult(String lastYearResult) {
		this.lastYearResult = lastYearResult;
	}
	public String getLastSchoolGrade() {
		return lastSchoolGrade;
	}
	public void setLastSchoolGrade(String lastSchoolGrade) {
		this.lastSchoolGrade = lastSchoolGrade;
	}
	public String getLastSchoolMarks() {
		return lastSchoolMarks;
	}
	public void setLastSchoolMarks(String lastSchoolMarks) {
		this.lastSchoolMarks = lastSchoolMarks;
	}
	public String getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(String applicantType) {
		this.applicantType = applicantType;
	}
	public int getApplicantSiblings() {
		return applicantSiblings;
	}
	public void setApplicantSiblings(int applicantSiblings) {
		this.applicantSiblings = applicantSiblings;
	}
	public String getApplicantFatherExFreedomFighter() {
		return applicantFatherExFreedomFighter;
	}
	public void setApplicantFatherExFreedomFighter(String applicantFatherExFreedomFighter) {
		this.applicantFatherExFreedomFighter = applicantFatherExFreedomFighter;
	}
	public String getFatherStruggleName() {
		return fatherStruggleName;
	}
	public void setFatherStruggleName(String fatherStruggleName) {
		this.fatherStruggleName = fatherStruggleName;
	}
	public String getFatherFreedomFighterType() {
		return fatherFreedomFighterType;
	}
	public void setFatherFreedomFighterType(String fatherFreedomFighterType) {
		this.fatherFreedomFighterType = fatherFreedomFighterType;
	}
	public String getApplicantFatherExServiceMen() {
		return applicantFatherExServiceMen;
	}
	public void setApplicantFatherExServiceMen(String applicantFatherExServiceMen) {
		this.applicantFatherExServiceMen = applicantFatherExServiceMen;
	}
	public String getApplicantFatherPosted() {
		return applicantFatherPosted;
	}
	public void setApplicantFatherPosted(String applicantFatherPosted) {
		this.applicantFatherPosted = applicantFatherPosted;
	}
	public String getApplicantFatherServiceType() {
		return applicantFatherServiceType;
	}
	public void setApplicantFatherServiceType(String applicantFatherServiceType) {
		this.applicantFatherServiceType = applicantFatherServiceType;
	}
	public String getApplicantMotherExFreedomFighter() {
		return applicantMotherExFreedomFighter;
	}
	public void setApplicantMotherExFreedomFighter(String applicantMotherExFreedomFighter) {
		this.applicantMotherExFreedomFighter = applicantMotherExFreedomFighter;
	}
	public String getMotherStruggleName() {
		return motherStruggleName;
	}
	public void setMotherStruggleName(String motherStruggleName) {
		this.motherStruggleName = motherStruggleName;
	}
	public String getMotherFreedomFighterType() {
		return motherFreedomFighterType;
	}
	public void setMotherFreedomFighterType(String motherFreedomFighterType) {
		this.motherFreedomFighterType = motherFreedomFighterType;
	}
	public String getApplicantMotherExServiceMen() {
		return applicantMotherExServiceMen;
	}
	public void setApplicantMotherExServiceMen(String applicantMotherExServiceMen) {
		this.applicantMotherExServiceMen = applicantMotherExServiceMen;
	}
	public String getApplicantMotherPosted() {
		return applicantMotherPosted;
	}
	public void setApplicantMotherPosted(String applicantMotherPosted) {
		this.applicantMotherPosted = applicantMotherPosted;
	}
	public String getApplicantMotherServiceType() {
		return applicantMotherServiceType;
	}
	public void setApplicantMotherServiceType(String applicantMotherServiceType) {
		this.applicantMotherServiceType = applicantMotherServiceType;
	}
	public int getFatherIncome() {
		return fatherIncome;
	}
	public void setFatherIncome(int fatherIncome) {
		this.fatherIncome = fatherIncome;
	}
	public int getMotherIncome() {
		return motherIncome;
	}
	public void setMotherIncome(int motherIncome) {
		this.motherIncome = motherIncome;
	}
	public int getGuardianIncome() {
		return guardianIncome;
	}
	public void setGuardianIncome(int guardianIncome) {
		this.guardianIncome = guardianIncome;
	}
	public String getApplicantIsBPL() {
		return applicantIsBPL;
	}
	public void setApplicantIsBPL(String applicantIsBPL) {
		this.applicantIsBPL = applicantIsBPL;
	}
	public String getGaurdianName() {
		return gaurdianName;
	}
	public void setGaurdianName(String gaurdianName) {
		this.gaurdianName = gaurdianName;
	}
	public String getApplicantGuardinSalaried() {
		return applicantGuardinSalaried;
	}
	public void setApplicantGuardinSalaried(String applicantGuardinSalaried) {
		this.applicantGuardinSalaried = applicantGuardinSalaried;
	}
	public String getGuardianDOB() {
		return guardianDOB;
	}
	public void setGuardianDOB(String guardianDOB) {
		this.guardianDOB = guardianDOB;
	}
	public String getGuardianOccupation() {
		return guardianOccupation;
	}
	public void setGuardianOccupation(String guardianOccupation) {
		this.guardianOccupation = guardianOccupation;
	}
	public String getGuardianPanNo() {
		return guardianPanNo;
	}
	public void setGuardianPanNo(String guardianPanNo) {
		this.guardianPanNo = guardianPanNo;
	}
	public String getGuardianIncomeCertificateImagePath() {
		return guardianIncomeCertificateImagePath;
	}
	public void setGuardianIncomeCertificateImagePath(String guardianIncomeCertificateImagePath) {
		this.guardianIncomeCertificateImagePath = guardianIncomeCertificateImagePath;
	}
	public String getApplicantGuardianExFreedomFighter() {
		return applicantGuardianExFreedomFighter;
	}
	public void setApplicantGuardianExFreedomFighter(String applicantGuardianExFreedomFighter) {
		this.applicantGuardianExFreedomFighter = applicantGuardianExFreedomFighter;
	}
	public String getGuardianStruggleName() {
		return GuardianStruggleName;
	}
	public void setGuardianStruggleName(String guardianStruggleName) {
		GuardianStruggleName = guardianStruggleName;
	}
	public String getGuardianFreedomFighterType() {
		return GuardianFreedomFighterType;
	}
	public void setGuardianFreedomFighterType(String guardianFreedomFighterType) {
		GuardianFreedomFighterType = guardianFreedomFighterType;
	}
	public String getApplicantGuardianExServiceMen() {
		return applicantGuardianExServiceMen;
	}
	public void setApplicantGuardianExServiceMen(String applicantGuardianExServiceMen) {
		this.applicantGuardianExServiceMen = applicantGuardianExServiceMen;
	}
	public String getApplicantGuardianPosted() {
		return applicantGuardianPosted;
	}
	public void setApplicantGuardianPosted(String applicantGuardianPosted) {
		this.applicantGuardianPosted = applicantGuardianPosted;
	}
	public String getApplicantGuardianServiceType() {
		return applicantGuardianServiceType;
	}
	public void setApplicantGuardianServiceType(String applicantGuardianServiceType) {
		this.applicantGuardianServiceType = applicantGuardianServiceType;
	}
	public String getIsOtherScholarshipApplied() {
		return isOtherScholarshipApplied;
	}
	public void setIsOtherScholarshipApplied(String isOtherScholarshipApplied) {
		this.isOtherScholarshipApplied = isOtherScholarshipApplied;
	}
	public String getFatherExServiceMenCertificateImagePath() {
		return fatherExServiceMenCertificateImagePath;
	}
	public void setFatherExServiceMenCertificateImagePath(String fatherExServiceMenCertificateImagePath) {
		this.fatherExServiceMenCertificateImagePath = fatherExServiceMenCertificateImagePath;
	}
	public String getMotherExServiceMenCertificateImagePath() {
		return motherExServiceMenCertificateImagePath;
	}
	public void setMotherExServiceMenCertificateImagePath(String motherExServiceMenCertificateImagePath) {
		this.motherExServiceMenCertificateImagePath = motherExServiceMenCertificateImagePath;
	}
	public String getGuardianExServiceMenCertificateImagePath() {
		return GuardianExServiceMenCertificateImagePath;
	}
	public void setGuardianExServiceMenCertificateImagePath(String guardianExServiceMenCertificateImagePath) {
		GuardianExServiceMenCertificateImagePath = guardianExServiceMenCertificateImagePath;
	}
	public String getPgGapCertificateImagePath() {
		return pgGapCertificateImagePath;
	}
	public void setPgGapCertificateImagePath(String pgGapCertificateImagePath) {
		this.pgGapCertificateImagePath = pgGapCertificateImagePath;
	}
	public String getGrGapCertificateImagePath() {
		return grGapCertificateImagePath;
	}
	public void setGrGapCertificateImagePath(String grGapCertificateImagePath) {
		this.grGapCertificateImagePath = grGapCertificateImagePath;
	}
	public String gettGapCertificateImagePath() {
		return tGapCertificateImagePath;
	}
	public void settGapCertificateImagePath(String tGapCertificateImagePath) {
		this.tGapCertificateImagePath = tGapCertificateImagePath;
	}
	public String getsGapCertificateImagePath() {
		return sGapCertificateImagePath;
	}
	public void setsGapCertificateImagePath(String sGapCertificateImagePath) {
		this.sGapCertificateImagePath = sGapCertificateImagePath;
	}
	public String getfGapCertificateImagePath() {
		return fGapCertificateImagePath;
	}
	public void setfGapCertificateImagePath(String fGapCertificateImagePath) {
		this.fGapCertificateImagePath = fGapCertificateImagePath;
	}
	public String getSdGapCertificateImagePath() {
		return sdGapCertificateImagePath;
	}
	public void setSdGapCertificateImagePath(String sdGapCertificateImagePath) {
		this.sdGapCertificateImagePath = sdGapCertificateImagePath;
	}
	public String getFdgapCertificateImagePath() {
		return fdgapCertificateImagePath;
	}
	public void setFdgapCertificateImagePath(String fdgapCertificateImagePath) {
		this.fdgapCertificateImagePath = fdgapCertificateImagePath;
	}
	public String getApplicantDisabilityPercentageOther() {
		return applicantDisabilityPercentageOther;
	}
	public void setApplicantDisabilityPercentageOther(String applicantDisabilityPercentageOther) {
		this.applicantDisabilityPercentageOther = applicantDisabilityPercentageOther;
	}
	public String getOtherScholarshipName() {
		return otherScholarshipName;
	}
	public void setOtherScholarshipName(String otherScholarshipName) {
		this.otherScholarshipName = otherScholarshipName;
	}
	public String getApplicantExamSeatNo() {
		return applicantExamSeatNo;
	}
	public void setApplicantExamSeatNo(String applicantExamSeatNo) {
		this.applicantExamSeatNo = applicantExamSeatNo;
	}
	public String getApplicantSanskritMarks() {
		return applicantSanskritMarks;
	}
	public void setApplicantSanskritMarks(String applicantSanskritMarks) {
		this.applicantSanskritMarks = applicantSanskritMarks;
	}
	public String getApplicantSet() {
		return applicantSet;
	}
	public void setApplicantSet(String applicantSet) {
		this.applicantSet = applicantSet;
	}
	public String getTdiplomaState() {
		return tdiplomaState;
	}
	public void setTdiplomaState(String tdiplomaState) {
		this.tdiplomaState = tdiplomaState;
	}
	public String getTdiplomaDistrict() {
		return tdiplomaDistrict;
	}
	public void setTdiplomaDistrict(String tdiplomaDistrict) {
		this.tdiplomaDistrict = tdiplomaDistrict;
	}
	public String getTdiplomaTaluka() {
		return tdiplomaTaluka;
	}
	public void setTdiplomaTaluka(String tdiplomaTaluka) {
		this.tdiplomaTaluka = tdiplomaTaluka;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getTempAppID() {
		return tempAppID;
	}
	public void setTempAppID(String tempAppID) {
		this.tempAppID = tempAppID;
	}
	public String getRtsflagcastecertificate() {
		return rtsflagcastecertificate;
	}
	public void setRtsflagcastecertificate(String rtsflagcastecertificate) {
		this.rtsflagcastecertificate = rtsflagcastecertificate;
	}
	public String getApplicantRenewable() {
		return applicantRenewable;
	}
	public void setApplicantRenewable(String applicantRenewable) {
		this.applicantRenewable = applicantRenewable;
	}
	public String getOldApplicationID() {
		return oldApplicationID;
	}
	public void setOldApplicationID(String oldApplicationID) {
		this.oldApplicationID = oldApplicationID;
	}
	public String getCheckDomDoc() {
		return checkDomDoc;
	}
	public void setCheckDomDoc(String checkDomDoc) {
		this.checkDomDoc = checkDomDoc;
	}
	public String getFatherCerDoc() {
		return fatherCerDoc;
	}
	public void setFatherCerDoc(String fatherCerDoc) {
		this.fatherCerDoc = fatherCerDoc;
	}
	public String getMotherCerDoc() {
		return motherCerDoc;
	}
	public void setMotherCerDoc(String motherCerDoc) {
		this.motherCerDoc = motherCerDoc;
	}
	public String getCasteValCerDoc() {
		return casteValCerDoc;
	}
	public void setCasteValCerDoc(String casteValCerDoc) {
		this.casteValCerDoc = casteValCerDoc;
	}
	public String getSscCerDoc() {
		return sscCerDoc;
	}
	public void setSscCerDoc(String sscCerDoc) {
		this.sscCerDoc = sscCerDoc;
	}
	public String getHscCerDoc() {
		return hscCerDoc;
	}
	public void setHscCerDoc(String hscCerDoc) {
		this.hscCerDoc = hscCerDoc;
	}
	public String getDomicileRadio() {
		return domicileRadio;
	}
	public void setDomicileRadio(String domicileRadio) {
		this.domicileRadio = domicileRadio;
	}
	public String getFatherRadio() {
		return fatherRadio;
	}
	public void setFatherRadio(String fatherRadio) {
		this.fatherRadio = fatherRadio;
	}
	public String getMotherRadio() {
		return motherRadio;
	}
	public void setMotherRadio(String motherRadio) {
		this.motherRadio = motherRadio;
	}
	public String getCasteValRadio() {
		return casteValRadio;
	}
	public void setCasteValRadio(String casteValRadio) {
		this.casteValRadio = casteValRadio;
	}
	public String getSscCerRadio() {
		return sscCerRadio;
	}
	public void setSscCerRadio(String sscCerRadio) {
		this.sscCerRadio = sscCerRadio;
	}
	public String getHscCerRadio() {
		return hscCerRadio;
	}
	public void setHscCerRadio(String hscCerRadio) {
		this.hscCerRadio = hscCerRadio;
	}
	public String getCapApplicationID() {
		return capApplicationID;
	}
	public void setCapApplicationID(String capApplicationID) {
		this.capApplicationID = capApplicationID;
	}
	public String getApplicantBPlNo() {
		return applicantBPlNo;
	}
	public void setApplicantBPlNo(String applicantBPlNo) {
		this.applicantBPlNo = applicantBPlNo;
	}
	public String getSanskritOpted() {
		return sanskritOpted;
	}
	public void setSanskritOpted(String sanskritOpted) {
		this.sanskritOpted = sanskritOpted;
	}
	public String getApplicantDisableType() {
		return applicantDisableType;
	}
	public void setApplicantDisableType(String applicantDisableType) {
		this.applicantDisableType = applicantDisableType;
	}
	public int getApplicantDisabilityYear() {
		return applicantDisabilityYear;
	}
	public void setApplicantDisabilityYear(int applicantDisabilityYear) {
		this.applicantDisabilityYear = applicantDisabilityYear;
	}
	public String getGaurdianAddress() {
		return gaurdianAddress;
	}
	public void setGaurdianAddress(String gaurdianAddress) {
		this.gaurdianAddress = gaurdianAddress;
	}
	public int getApplicant10thTotalMarksObtained() {
		return applicant10thTotalMarksObtained;
	}
	public void setApplicant10thTotalMarksObtained(int applicant10thTotalMarksObtained) {
		this.applicant10thTotalMarksObtained = applicant10thTotalMarksObtained;
	}
	public int getApplicant10thTotalMarks() {
		return applicant10thTotalMarks;
	}
	public void setApplicant10thTotalMarks(int applicant10thTotalMarks) {
		this.applicant10thTotalMarks = applicant10thTotalMarks;
	}
	public int getApplicant12thTotalMarksObtained() {
		return applicant12thTotalMarksObtained;
	}
	public void setApplicant12thTotalMarksObtained(int applicant12thTotalMarksObtained) {
		this.applicant12thTotalMarksObtained = applicant12thTotalMarksObtained;
	}
	public int getApplicant12thTotalMarks() {
		return applicant12thTotalMarks;
	}
	public void setApplicant12thTotalMarks(int applicant12thTotalMarks) {
		this.applicant12thTotalMarks = applicant12thTotalMarks;
	}
	public String getApplicantHindiSubject() {
		return applicantHindiSubject;
	}
	public void setApplicantHindiSubject(String applicantHindiSubject) {
		this.applicantHindiSubject = applicantHindiSubject;
	}
	public float getApplicant10thSanskritMarks() {
		return applicant10thSanskritMarks;
	}
	public void setApplicant10thSanskritMarks(float applicant10thSanskritMarks) {
		this.applicant10thSanskritMarks = applicant10thSanskritMarks;
	}
	public float getApplicant10thSanskritMarksPerCent() {
		return applicant10thSanskritMarksPerCent;
	}
	public void setApplicant10thSanskritMarksPerCent(float applicant10thSanskritMarksPerCent) {
		this.applicant10thSanskritMarksPerCent = applicant10thSanskritMarksPerCent;
	}
	public float getApplicant8thSanskritMarks() {
		return applicant8thSanskritMarks;
	}
	public void setApplicant8thSanskritMarks(float applicant8thSanskritMarks) {
		this.applicant8thSanskritMarks = applicant8thSanskritMarks;
	}
	public float getApplicant8thSanskritMarksPerCent() {
		return applicant8thSanskritMarksPerCent;
	}
	public void setApplicant8thSanskritMarksPerCent(float applicant8thSanskritMarksPerCent) {
		this.applicant8thSanskritMarksPerCent = applicant8thSanskritMarksPerCent;
	}
	public String getApplicantHaveCasteBarCode() {
		return applicantHaveCasteBarCode;
	}
	public void setApplicantHaveCasteBarCode(String applicantHaveCasteBarCode) {
		this.applicantHaveCasteBarCode = applicantHaveCasteBarCode;
	}
	public String getApplicantCasteCertificateBarCode() {
		return applicantCasteCertificateBarCode;
	}
	public void setApplicantCasteCertificateBarCode(String applicantCasteCertificateBarCode) {
		this.applicantCasteCertificateBarCode = applicantCasteCertificateBarCode;
	}
	public String getApplicantBarcodeName() {
		return applicantBarcodeName;
	}
	public void setApplicantBarcodeName(String applicantBarcodeName) {
		this.applicantBarcodeName = applicantBarcodeName;
	}
	public String getApplicantCasteCertificateDate_barcode() {
		return applicantCasteCertificateDate_barcode;
	}
	public void setApplicantCasteCertificateDate_barcode(String applicantCasteCertificateDate_barcode) {
		this.applicantCasteCertificateDate_barcode = applicantCasteCertificateDate_barcode;
	}
	public String getApplicantCasteCertificateAuthority_barcode() {
		return applicantCasteCertificateAuthority_barcode;
	}
	public void setApplicantCasteCertificateAuthority_barcode(String applicantCasteCertificateAuthority_barcode) {
		this.applicantCasteCertificateAuthority_barcode = applicantCasteCertificateAuthority_barcode;
	}
	public String getApplicantHaveDomicileBarCode() {
		return applicantHaveDomicileBarCode;
	}
	public void setApplicantHaveDomicileBarCode(String applicantHaveDomicileBarCode) {
		this.applicantHaveDomicileBarCode = applicantHaveDomicileBarCode;
	}
	public String getApplicantDomicileCertificateBarCode() {
		return applicantDomicileCertificateBarCode;
	}
	public void setApplicantDomicileCertificateBarCode(String applicantDomicileCertificateBarCode) {
		this.applicantDomicileCertificateBarCode = applicantDomicileCertificateBarCode;
	}
	public String getApplicantDomicileBarcodeName() {
		return applicantDomicileBarcodeName;
	}
	public void setApplicantDomicileBarcodeName(String applicantDomicileBarcodeName) {
		this.applicantDomicileBarcodeName = applicantDomicileBarcodeName;
	}
	public String getApplicantDomicileCertificateDate_barcode() {
		return applicantDomicileCertificateDate_barcode;
	}
	public void setApplicantDomicileCertificateDate_barcode(String applicantDomicileCertificateDate_barcode) {
		this.applicantDomicileCertificateDate_barcode = applicantDomicileCertificateDate_barcode;
	}
	public String getApplicantDomicileCertificateAuthority_barcode() {
		return applicantDomicileCertificateAuthority_barcode;
	}
	public void setApplicantDomicileCertificateAuthority_barcode(String applicantDomicileCertificateAuthority_barcode) {
		this.applicantDomicileCertificateAuthority_barcode = applicantDomicileCertificateAuthority_barcode;
	}
	public int getApplicantAge() {
		return applicantAge;
	}
	public void setApplicantAge(int applicantAge) {
		this.applicantAge = applicantAge;
	}
	public String getApplicantHaveIncomeBarCode() {
		return applicantHaveIncomeBarCode;
	}
	public void setApplicantHaveIncomeBarCode(String applicantHaveIncomeBarCode) {
		this.applicantHaveIncomeBarCode = applicantHaveIncomeBarCode;
	}
	public String getApplicantIncomeCertificateBarCode() {
		return applicantIncomeCertificateBarCode;
	}
	public void setApplicantIncomeCertificateBarCode(String applicantIncomeCertificateBarCode) {
		this.applicantIncomeCertificateBarCode = applicantIncomeCertificateBarCode;
	}
	public String getApplicantIncomeBarcodeName() {
		return applicantIncomeBarcodeName;
	}
	public void setApplicantIncomeBarcodeName(String applicantIncomeBarcodeName) {
		this.applicantIncomeBarcodeName = applicantIncomeBarcodeName;
	}
	public String getApplicantIncomeCertificateDate_barcode() {
		return applicantIncomeCertificateDate_barcode;
	}
	public void setApplicantIncomeCertificateDate_barcode(String applicantIncomeCertificateDate_barcode) {
		this.applicantIncomeCertificateDate_barcode = applicantIncomeCertificateDate_barcode;
	}
	public String getApplicantIncomeCertificateAuthority_barcode() {
		return applicantIncomeCertificateAuthority_barcode;
	}
	public void setApplicantIncomeCertificateAuthority_barcode(String applicantIncomeCertificateAuthority_barcode) {
		this.applicantIncomeCertificateAuthority_barcode = applicantIncomeCertificateAuthority_barcode;
	}
	public String getApplicantIncomeCertificateNo() {
		return applicantIncomeCertificateNo;
	}
	public void setApplicantIncomeCertificateNo(String applicantIncomeCertificateNo) {
		this.applicantIncomeCertificateNo = applicantIncomeCertificateNo;
	}
	public String getApplicantIncomeCertificateDate() {
		return applicantIncomeCertificateDate;
	}
	public void setApplicantIncomeCertificateDate(String applicantIncomeCertificateDate) {
		this.applicantIncomeCertificateDate = applicantIncomeCertificateDate;
	}
	public String getApplicantIncomeCertificateAuthority() {
		return applicantIncomeCertificateAuthority;
	}
	public void setApplicantIncomeCertificateAuthority(String applicantIncomeCertificateAuthority) {
		this.applicantIncomeCertificateAuthority = applicantIncomeCertificateAuthority;
	}
	public String getReaderOpted() {
		return readerOpted;
	}
	public void setReaderOpted(String readerOpted) {
		this.readerOpted = readerOpted;
	}
	public String getApplicantHaveDisabilityBarCode() {
		return applicantHaveDisabilityBarCode;
	}
	public void setApplicantHaveDisabilityBarCode(String applicantHaveDisabilityBarCode) {
		this.applicantHaveDisabilityBarCode = applicantHaveDisabilityBarCode;
	}
	public String getApplicantDisabilityCertificateBarCode() {
		return applicantDisabilityCertificateBarCode;
	}
	public void setApplicantDisabilityCertificateBarCode(String applicantDisabilityCertificateBarCode) {
		this.applicantDisabilityCertificateBarCode = applicantDisabilityCertificateBarCode;
	}
	public String getApplicantDisabilityBarcodeName() {
		return applicantDisabilityBarcodeName;
	}
	public void setApplicantDisabilityBarcodeName(String applicantDisabilityBarcodeName) {
		this.applicantDisabilityBarcodeName = applicantDisabilityBarcodeName;
	}
	public String getApplicantDisabilityCertificateDate_barcode() {
		return applicantDisabilityCertificateDate_barcode;
	}
	public void setApplicantDisabilityCertificateDate_barcode(String applicantDisabilityCertificateDate_barcode) {
		this.applicantDisabilityCertificateDate_barcode = applicantDisabilityCertificateDate_barcode;
	}
	public String getApplicantDisabilityCertificateAuthority_barcode() {
		return applicantDisabilityCertificateAuthority_barcode;
	}
	public void setApplicantDisabilityCertificateAuthority_barcode(String applicantDisabilityCertificateAuthority_barcode) {
		this.applicantDisabilityCertificateAuthority_barcode = applicantDisabilityCertificateAuthority_barcode;
	}
	public String getApplicantDisabilityCertificateNo() {
		return applicantDisabilityCertificateNo;
	}
	public void setApplicantDisabilityCertificateNo(String applicantDisabilityCertificateNo) {
		this.applicantDisabilityCertificateNo = applicantDisabilityCertificateNo;
	}
	public String getApplicantDisabilityCertificateAuthority() {
		return applicantDisabilityCertificateAuthority;
	}
	public void setApplicantDisabilityCertificateAuthority(String applicantDisabilityCertificateAuthority) {
		this.applicantDisabilityCertificateAuthority = applicantDisabilityCertificateAuthority;
	}
	public String getApplicantDisabilityCertificateDate() {
		return applicantDisabilityCertificateDate;
	}
	public void setApplicantDisabilityCertificateDate(String applicantDisabilityCertificateDate) {
		this.applicantDisabilityCertificateDate = applicantDisabilityCertificateDate;
	}
	public int getApplicant11thTotalMarksObtained() {
		return applicant11thTotalMarksObtained;
	}
	public void setApplicant11thTotalMarksObtained(int applicant11thTotalMarksObtained) {
		this.applicant11thTotalMarksObtained = applicant11thTotalMarksObtained;
	}
	public int getApplicant11thTotalMarks() {
		return applicant11thTotalMarks;
	}
	public void setApplicant11thTotalMarks(int applicant11thTotalMarks) {
		this.applicant11thTotalMarks = applicant11thTotalMarks;
	}
	public String getApplicant11thGap() {
		return applicant11thGap;
	}
	public void setApplicant11thGap(String applicant11thGap) {
		this.applicant11thGap = applicant11thGap;
	}
	public String getApplicant12thStream() {
		return applicant12thStream;
	}
	public void setApplicant12thStream(String applicant12thStream) {
		this.applicant12thStream = applicant12thStream;
	}
	public float getApplicant12thScienceMarksObtained() {
		return applicant12thScienceMarksObtained;
	}
	public void setApplicant12thScienceMarksObtained(float applicant12thScienceMarksObtained) {
		this.applicant12thScienceMarksObtained = applicant12thScienceMarksObtained;
	}
	public float getApplicant12thMathsMarksObtained() {
		return applicant12thMathsMarksObtained;
	}
	public void setApplicant12thMathsMarksObtained(float applicant12thMathsMarksObtained) {
		this.applicant12thMathsMarksObtained = applicant12thMathsMarksObtained;
	}
	public float getApplicant12thPhysicsMarksObtained() {
		return applicant12thPhysicsMarksObtained;
	}
	public void setApplicant12thPhysicsMarksObtained(float applicant12thPhysicsMarksObtained) {
		this.applicant12thPhysicsMarksObtained = applicant12thPhysicsMarksObtained;
	}
	public String getApplicant12thGap() {
		return applicant12thGap;
	}
	public void setApplicant12thGap(String applicant12thGap) {
		this.applicant12thGap = applicant12thGap;
	}
	public String getApplicanttdiplomaGap() {
		return applicanttdiplomaGap;
	}
	public void setApplicanttdiplomaGap(String applicanttdiplomaGap) {
		this.applicanttdiplomaGap = applicanttdiplomaGap;
	}
	public String getApplicantdiplomaGap() {
		return applicantdiplomaGap;
	}
	public void setApplicantdiplomaGap(String applicantdiplomaGap) {
		this.applicantdiplomaGap = applicantdiplomaGap;
	}
	public String getSdiplomaGap() {
		return sdiplomaGap;
	}
	public void setSdiplomaGap(String sdiplomaGap) {
		this.sdiplomaGap = sdiplomaGap;
	}
	public String getApplicant1styearGap() {
		return applicant1styearGap;
	}
	public void setApplicant1styearGap(String applicant1styearGap) {
		this.applicant1styearGap = applicant1styearGap;
	}
	public String getApplicant2ndyearGap() {
		return applicant2ndyearGap;
	}
	public void setApplicant2ndyearGap(String applicant2ndyearGap) {
		this.applicant2ndyearGap = applicant2ndyearGap;
	}
	public String getApplicant3rdyearGap() {
		return applicant3rdyearGap;
	}
	public void setApplicant3rdyearGap(String applicant3rdyearGap) {
		this.applicant3rdyearGap = applicant3rdyearGap;
	}
	public String getApplicantGyearGap() {
		return applicantGyearGap;
	}
	public void setApplicantGyearGap(String applicantGyearGap) {
		this.applicantGyearGap = applicantGyearGap;
	}
	public String getApplicantPGyearGap() {
		return applicantPGyearGap;
	}
	public void setApplicantPGyearGap(String applicantPGyearGap) {
		this.applicantPGyearGap = applicantPGyearGap;
	}
	public String getParikshaExam() {
		return parikshaExam;
	}
	public void setParikshaExam(String parikshaExam) {
		this.parikshaExam = parikshaExam;
	}
	public String getApplicantlastYearResultEighth() {
		return applicantlastYearResultEighth;
	}
	public void setApplicantlastYearResultEighth(String applicantlastYearResultEighth) {
		this.applicantlastYearResultEighth = applicantlastYearResultEighth;
	}
	public String getApplicantSpouseSalaried() {
		return applicantSpouseSalaried;
	}
	public void setApplicantSpouseSalaried(String applicantSpouseSalaried) {
		this.applicantSpouseSalaried = applicantSpouseSalaried;
	}
	public String getApplicantDHERecommended() {
		return applicantDHERecommended;
	}
	public void setApplicantDHERecommended(String applicantDHERecommended) {
		this.applicantDHERecommended = applicantDHERecommended;
	}
	public String getApplicantJNUStudent() {
		return applicantJNUStudent;
	}
	public void setApplicantJNUStudent(String applicantJNUStudent) {
		this.applicantJNUStudent = applicantJNUStudent;
	}
	public String getSanskritOptedPre() {
		return sanskritOptedPre;
	}
	public void setSanskritOptedPre(String sanskritOptedPre) {
		this.sanskritOptedPre = sanskritOptedPre;
	}
	public String getFamilyIncomeCertificateImagePath() {
		return familyIncomeCertificateImagePath;
	}
	public void setFamilyIncomeCertificateImagePath(String familyIncomeCertificateImagePath) {
		this.familyIncomeCertificateImagePath = familyIncomeCertificateImagePath;
	}
	public String getReaderCertificateImagePath() {
		return readerCertificateImagePath;
	}
	public void setReaderCertificateImagePath(String readerCertificateImagePath) {
		this.readerCertificateImagePath = readerCertificateImagePath;
	}
	public String getDisabilityCertificateImagePath() {
		return disabilityCertificateImagePath;
	}
	public void setDisabilityCertificateImagePath(String disabilityCertificateImagePath) {
		this.disabilityCertificateImagePath = disabilityCertificateImagePath;
	}
	public String getRationCardImagePath() {
		return rationCardImagePath;
	}
	public void setRationCardImagePath(String rationCardImagePath) {
		this.rationCardImagePath = rationCardImagePath;
	}
	public String getBplCardImagePath() {
		return bplCardImagePath;
	}
	public void setBplCardImagePath(String bplCardImagePath) {
		this.bplCardImagePath = bplCardImagePath;
	}
	public String getFatherOccupationCertificateImagePath() {
		return fatherOccupationCertificateImagePath;
	}
	public void setFatherOccupationCertificateImagePath(String fatherOccupationCertificateImagePath) {
		this.fatherOccupationCertificateImagePath = fatherOccupationCertificateImagePath;
	}
	public String getFatherDeathCertificateImagePath() {
		return fatherDeathCertificateImagePath;
	}
	public void setFatherDeathCertificateImagePath(String fatherDeathCertificateImagePath) {
		this.fatherDeathCertificateImagePath = fatherDeathCertificateImagePath;
	}
	public String getFatherExFreedomFighterCertificateImagePath() {
		return fatherExFreedomFighterCertificateImagePath;
	}
	public void setFatherExFreedomFighterCertificateImagePath(String fatherExFreedomFighterCertificateImagePath) {
		this.fatherExFreedomFighterCertificateImagePath = fatherExFreedomFighterCertificateImagePath;
	}
	public String getMotherOccupationCertificateImagePath() {
		return motherOccupationCertificateImagePath;
	}
	public void setMotherOccupationCertificateImagePath(String motherOccupationCertificateImagePath) {
		this.motherOccupationCertificateImagePath = motherOccupationCertificateImagePath;
	}
	public String getMotherDeathCertificateImagePath() {
		return motherDeathCertificateImagePath;
	}
	public void setMotherDeathCertificateImagePath(String motherDeathCertificateImagePath) {
		this.motherDeathCertificateImagePath = motherDeathCertificateImagePath;
	}
	public String getMotherExFreedomFighterCertificateImagePath() {
		return motherExFreedomFighterCertificateImagePath;
	}
	public void setMotherExFreedomFighterCertificateImagePath(String motherExFreedomFighterCertificateImagePath) {
		this.motherExFreedomFighterCertificateImagePath = motherExFreedomFighterCertificateImagePath;
	}
	public String getGuardianCertificateImagePath() {
		return guardianCertificateImagePath;
	}
	public void setGuardianCertificateImagePath(String guardianCertificateImagePath) {
		this.guardianCertificateImagePath = guardianCertificateImagePath;
	}
	public String getGurdianOccupationCertificateImagePath() {
		return gurdianOccupationCertificateImagePath;
	}
	public void setGurdianOccupationCertificateImagePath(String gurdianOccupationCertificateImagePath) {
		this.gurdianOccupationCertificateImagePath = gurdianOccupationCertificateImagePath;
	}
	public String getDheletterImagePath() {
		return dheletterImagePath;
	}
	public void setDheletterImagePath(String dheletterImagePath) {
		this.dheletterImagePath = dheletterImagePath;
	}
	public String getJnucertificateImagePath() {
		return jnucertificateImagePath;
	}
	public void setJnucertificateImagePath(String jnucertificateImagePath) {
		this.jnucertificateImagePath = jnucertificateImagePath;
	}
	public String getAdmissionReceiptImagePath() {
		return admissionReceiptImagePath;
	}
	public void setAdmissionReceiptImagePath(String admissionReceiptImagePath) {
		this.admissionReceiptImagePath = admissionReceiptImagePath;
	}
	public String getGuideClgImagePath() {
		return guideClgImagePath;
	}
	public void setGuideClgImagePath(String guideClgImagePath) {
		this.guideClgImagePath = guideClgImagePath;
	}
	public String getBonafideCertificateClgImagePath() {
		return bonafideCertificateClgImagePath;
	}
	public void setBonafideCertificateClgImagePath(String bonafideCertificateClgImagePath) {
		this.bonafideCertificateClgImagePath = bonafideCertificateClgImagePath;
	}
	public String getSscTclCertificateImagePath() {
		return sscTclCertificateImagePath;
	}
	public void setSscTclCertificateImagePath(String sscTclCertificateImagePath) {
		this.sscTclCertificateImagePath = sscTclCertificateImagePath;
	}
	public String getEleventhCertificateImagePath() {
		return eleventhCertificateImagePath;
	}
	public void setEleventhCertificateImagePath(String eleventhCertificateImagePath) {
		this.eleventhCertificateImagePath = eleventhCertificateImagePath;
	}
	public String getEleventhGapCertificateImagePath() {
		return eleventhGapCertificateImagePath;
	}
	public void setEleventhGapCertificateImagePath(String eleventhGapCertificateImagePath) {
		this.eleventhGapCertificateImagePath = eleventhGapCertificateImagePath;
	}
	public String getHscTclCertificateImagePath() {
		return hscTclCertificateImagePath;
	}
	public void setHscTclCertificateImagePath(String hscTclCertificateImagePath) {
		this.hscTclCertificateImagePath = hscTclCertificateImagePath;
	}
	public String getHscgapCertificateImagePath() {
		return hscgapCertificateImagePath;
	}
	public void setHscgapCertificateImagePath(String hscgapCertificateImagePath) {
		this.hscgapCertificateImagePath = hscgapCertificateImagePath;
	}
	public String getTdiplomaGapCertificateImagePath() {
		return tdiplomaGapCertificateImagePath;
	}
	public void setTdiplomaGapCertificateImagePath(String tdiplomaGapCertificateImagePath) {
		this.tdiplomaGapCertificateImagePath = tdiplomaGapCertificateImagePath;
	}
	public String getLastExamCerForFirstYearImagePath() {
		return lastExamCerForFirstYearImagePath;
	}
	public void setLastExamCerForFirstYearImagePath(String lastExamCerForFirstYearImagePath) {
		this.lastExamCerForFirstYearImagePath = lastExamCerForFirstYearImagePath;
	}
	public String getLastExamCerForSecondYearImagePath() {
		return lastExamCerForSecondYearImagePath;
	}
	public void setLastExamCerForSecondYearImagePath(String lastExamCerForSecondYearImagePath) {
		this.lastExamCerForSecondYearImagePath = lastExamCerForSecondYearImagePath;
	}
	public String getLastExamCerForThirdYearImagePath() {
		return lastExamCerForThirdYearImagePath;
	}
	public void setLastExamCerForThirdYearImagePath(String lastExamCerForThirdYearImagePath) {
		this.lastExamCerForThirdYearImagePath = lastExamCerForThirdYearImagePath;
	}
	public String getLastExamCerForGraduationYearImagePath() {
		return lastExamCerForGraduationYearImagePath;
	}
	public void setLastExamCerForGraduationYearImagePath(String lastExamCerForGraduationYearImagePath) {
		this.lastExamCerForGraduationYearImagePath = lastExamCerForGraduationYearImagePath;
	}
	public String getLastExamCerForPGraduationYearImagePath() {
		return lastExamCerForPGraduationYearImagePath;
	}
	public void setLastExamCerForPGraduationYearImagePath(String lastExamCerForPGraduationYearImagePath) {
		this.lastExamCerForPGraduationYearImagePath = lastExamCerForPGraduationYearImagePath;
	}
	public String getMarksheetCertificateImagePath() {
		return marksheetCertificateImagePath;
	}
	public void setMarksheetCertificateImagePath(String marksheetCertificateImagePath) {
		this.marksheetCertificateImagePath = marksheetCertificateImagePath;
	}
	public String getHostelCertificateImagePath() {
		return hostelCertificateImagePath;
	}
	public void setHostelCertificateImagePath(String hostelCertificateImagePath) {
		this.hostelCertificateImagePath = hostelCertificateImagePath;
	}
	public String getWardenNOCCertificateImagePath() {
		return wardenNOCCertificateImagePath;
	}
	public void setWardenNOCCertificateImagePath(String wardenNOCCertificateImagePath) {
		this.wardenNOCCertificateImagePath = wardenNOCCertificateImagePath;
	}
	public String getApplicant10thResult() {
		return applicant10thResult;
	}
	public void setApplicant10thResult(String applicant10thResult) {
		this.applicant10thResult = applicant10thResult;
	}
	public String getApplicant11thResult() {
		return applicant11thResult;
	}
	public void setApplicant11thResult(String applicant11thResult) {
		this.applicant11thResult = applicant11thResult;
	}
	public String getApplicant12thResult() {
		return applicant12thResult;
	}
	public void setApplicant12thResult(String applicant12thResult) {
		this.applicant12thResult = applicant12thResult;
	}
	public String getSscIntegrationFlag() {
		return sscIntegrationFlag;
	}
	public void setSscIntegrationFlag(String sscIntegrationFlag) {
		this.sscIntegrationFlag = sscIntegrationFlag;
	}
	public String getHscIntegrationFlag() {
		return hscIntegrationFlag;
	}
	public void setHscIntegrationFlag(String hscIntegrationFlag) {
		this.hscIntegrationFlag = hscIntegrationFlag;
	}
	public String getCasteValidityIntegrationFlag() {
		return casteValidityIntegrationFlag;
	}
	public void setCasteValidityIntegrationFlag(String casteValidityIntegrationFlag) {
		this.casteValidityIntegrationFlag = casteValidityIntegrationFlag;
	}
	public String getFdDiplomaCertificateImagePath() {
		return fdDiplomaCertificateImagePath;
	}
	public void setFdDiplomaCertificateImagePath(String fdDiplomaCertificateImagePath) {
		this.fdDiplomaCertificateImagePath = fdDiplomaCertificateImagePath;
	}
	public String getSdDiplomaCertificateImagePath() {
		return sdDiplomaCertificateImagePath;
	}
	public void setSdDiplomaCertificateImagePath(String sdDiplomaCertificateImagePath) {
		this.sdDiplomaCertificateImagePath = sdDiplomaCertificateImagePath;
	}
	public String getTdiplomaCertificateImagePath() {
		return tdiplomaCertificateImagePath;
	}
	public void setTdiplomaCertificateImagePath(String tdiplomaCertificateImagePath) {
		this.tdiplomaCertificateImagePath = tdiplomaCertificateImagePath;
	}
	public String getIncome_is_rts_seeded_data() {
		return income_is_rts_seeded_data;
	}
	public void setIncome_is_rts_seeded_data(String income_is_rts_seeded_data) {
		this.income_is_rts_seeded_data = income_is_rts_seeded_data;
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
	public String getCaste_verification_status() {
		return caste_verification_status;
	}
	public void setCaste_verification_status(String caste_verification_status) {
		this.caste_verification_status = caste_verification_status;
	}
	public String getSsc_verification_status() {
		return ssc_verification_status;
	}
	public void setSsc_verification_status(String ssc_verification_status) {
		this.ssc_verification_status = ssc_verification_status;
	}
	public String getHsc_verification_status() {
		return hsc_verification_status;
	}
	public void setHsc_verification_status(String hsc_verification_status) {
		this.hsc_verification_status = hsc_verification_status;
	}
	public String getCval_verification_status() {
		return cval_verification_status;
	}
	public void setCval_verification_status(String cval_verification_status) {
		this.cval_verification_status = cval_verification_status;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	

	
}
