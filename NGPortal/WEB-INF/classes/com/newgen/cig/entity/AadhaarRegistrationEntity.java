/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarRegistrationEntity.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Feb 12, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.cig.entity;

public class AadhaarRegistrationEntity {

	private String photo;
	private String auidno;
	private String name;
	private String careoff;
	private String dob;
	private int age;
	private String mobile;
	private String email;
	private String gender;
	private String house;
	private String street;
	private String villagetowncity;
	private String postoffice;
	private String landmark;
	private String subdistrict;
	private String district;
	private String state;
	private int pincode;
	private String username;
	private String password;
    private byte[] encryptedpassword;
    private String userType;
    private String ipAdderess;
    private String eid_pce_no;
    private String bankName;
    private String npciStatus;
    private int roleId;
    private int districtId;
    private int talukaId;
    private int instituteId;
    private int userIndex;
    private String userRole;
    private String profileType;
	
	public String getIpAdderess() {
		return ipAdderess;
	}
	public void setIpAdderess(String ipAdderess) {
		this.ipAdderess = ipAdderess;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAuidno() {
		return auidno;
	}
	public void setAuidno(String auidno) {
		this.auidno = auidno;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getCareoff() {
		return careoff;
	}
	public void setCareoff(String careoff) {
		this.careoff = careoff;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getVillagetowncity() {
		return villagetowncity;
	}
	public void setVillagetowncity(String villagetowncity) {
		this.villagetowncity = villagetowncity;
	}
	public String getPostoffice() {
		return postoffice;
	}
	public void setPostoffice(String postoffice) {
		this.postoffice = postoffice;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getSubdistrict() {
		return subdistrict;
	}
	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public byte[] getEncryptedpassword() {
		return encryptedpassword;
	}
	public void setEncryptedpassword(byte[] encryptedpassword) {
		this.encryptedpassword = encryptedpassword;
	}
	public String getEid_pce_no() {
		return eid_pce_no;
	}
	public void setEid_pce_no(String eid_pce_no) {
		this.eid_pce_no = eid_pce_no;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getNpciStatus() {
		return npciStatus;
	}
	public void setNpciStatus(String npciStatus) {
		this.npciStatus = npciStatus;
	}
	public int getUserIndex() {
		return userIndex;
	}
	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public int getTalukaId() {
		return talukaId;
	}
	public void setTalukaId(int talukaId) {
		this.talukaId = talukaId;
	}
	public int getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}



}
