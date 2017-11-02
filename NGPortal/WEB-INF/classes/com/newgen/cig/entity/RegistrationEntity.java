/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : RegistrationEntity.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Feb 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.cig.entity;

import java.sql.Date;

public class RegistrationEntity {

    private String usertype;
    private int userid;
    private String username;
    private String password;
    private String aadhaar;
    private String firstname;
    private String middlename;
    private String lastname;
    private String mobileno;
    private String emailid;
    private int castecategoryid;
    private int religionid;
    private Date createdon;
    private String clientCaptcha;
    private byte[] encyptedpassword;
       
    public String getClientCaptcha() {
        return clientCaptcha;
    }

    public void setClientCaptcha(String clientCaptcha) {
        this.clientCaptcha = clientCaptcha;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public int getCastecategoryid() {
        return castecategoryid;
    }

    public void setCastecategoryid(int castecategoryid) {
        this.castecategoryid = castecategoryid;
    }

    public int getReligionid() {
        return religionid;
    }

    public void setReligionid(int religionid) {
        this.religionid = religionid;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public byte[] getEncyptedpassword() {
        return encyptedpassword;
    }

    public void setEncyptedpassword(byte[] encyptedpassword) {
        this.encyptedpassword = encyptedpassword;
    }   
}
