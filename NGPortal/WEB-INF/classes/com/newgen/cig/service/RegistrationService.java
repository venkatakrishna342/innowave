/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : RegistrationService.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : may 2, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.newgen.cig.entity.RegistrationEntity;
import com.newgen.utility.AppConstants;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class RegistrationService {

    InitialContext Context;
    DataSource ds;

    public HashMap userRegistraion(RegistrationEntity registrationEntity_i, HashMap resultMap) {
        if (registrationEntity_i == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid input");
            return resultMap;
        } else {
            try {
                String resultVal = checkMandatory(registrationEntity_i);
                if (!AppConstants.ALL_MANDAT_ENTERED.equals(resultVal)) {
                    resultMap.put("resultCode", -3);
                    resultMap.put("resultDetails", resultVal);
                    return resultMap;
                } else {
                    String selectUsername ="";
                    String insertUser = "";
                     byte[] salt;
                     byte[] encryptedpassword;
                    Context = new javax.naming.InitialContext();
                    ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
                    try (Connection conn = ds.getConnection();) {
                        String usernameEntity = "";
                        String userName = registrationEntity_i.getUsername();
                        selectUsername = "select username from userprofile where username = ?";
                        try (PreparedStatement ps = conn.prepareStatement(selectUsername);) {
                            ps.setString(1, userName);
                            ResultSet rs1 = ps.executeQuery();
                            while (rs1.next()) {
                                usernameEntity = rs1.getString("username");
                            }
                        }
                        if (!usernameEntity.equals(userName)) {
                            insertUser = "insert into userprofile (userid,username,password,usertype,aadhaar,firstname,middlename,lastname,mobileno,emailid,createdon) VALUES (0,?,?,?,?,?,?,?,?,?,current_timestamp())";
                            try (PreparedStatement ps_insertUser = conn.prepareStatement(insertUser);) {
                                salt = AppConstants.SALT;    
                                String pass = registrationEntity_i.getPassword();
                                encryptedpassword = getEncryptedPassword(pass, salt);
                                ps_insertUser.setString(1, registrationEntity_i.getUsername());
                                ps_insertUser.setBytes(2, encryptedpassword);
                                ps_insertUser.setString(3, registrationEntity_i.getUsertype());
                                ps_insertUser.setString(4, registrationEntity_i.getAadhaar());
                                ps_insertUser.setString(5, registrationEntity_i.getFirstname());
                                ps_insertUser.setString(6, registrationEntity_i.getMiddlename());
                                ps_insertUser.setString(7, registrationEntity_i.getLastname());
                                ps_insertUser.setString(8, registrationEntity_i.getMobileno());
                                ps_insertUser.setString(9, registrationEntity_i.getEmailid());
                                int insertResultSet = ps_insertUser.executeUpdate();
                                if (insertResultSet > 0) {
                                    resultMap.put("resultCode", 0);
                                    resultMap.put("resultDetails", "Record inserted successfully");
                                }
                            }
                        } else {
                            resultMap.put("resultCode", -3);
                            resultMap.put("resultDetails", "Record already exist with same user name");
                            return resultMap;
                        }
                    }
                }
            } catch (NamingException | SQLException ex) {
                resultMap.put("resultCode", -1);
                resultMap.put("resultDetails", "RegistrationService DB Exception: " + ex.toString());
                return resultMap;
            }
        }
        return resultMap;
    }

    /**
     * @param registrationEntity_i
     */
    private String checkMandatory(RegistrationEntity registrationEntity_i) {
        String message = AppConstants.ALL_MANDAT_ENTERED;
        if (registrationEntity_i.getUsername() == null || "".equals(registrationEntity_i.getUsername())) {
            message = " Please Enter UserName,";
        } else if (registrationEntity_i.getPassword() == null || "".equals(registrationEntity_i.getPassword())) {
            message = " Please Enter Password,";
        } else if (registrationEntity_i.getUsertype() == null || "".equals(registrationEntity_i.getUsertype())) {
            message = " Please Select User Type,";
        } else if (registrationEntity_i.getFirstname() == null || "".equals(registrationEntity_i.getFirstname())) {
            message = " Please Enter First Name,";
        } else if (registrationEntity_i.getLastname() == null || "".equals(registrationEntity_i.getLastname())) {
            message = " Please Enter Last Name,";
        } else if (registrationEntity_i.getEmailid() == null || "".equals(registrationEntity_i.getEmailid())) {
            message = " Please Enter Email,";
        } else if (registrationEntity_i.getMobileno() == null || "".equals(registrationEntity_i.getMobileno())) {
            message = " Please Enter Mobile Number";
        }
        return message;
    }
    
    /**
     * @param password
     * @param salt
     * @return
     */
    //--- for password encryption---//
    private byte[] getEncryptedPassword(String password, byte[] salt) {
        try {
            // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
            // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
            String algorithm = "PBKDF2WithHmacSHA1";
            // SHA-1 generates 160 bit hashes, so that's what makes sense here
            int derivedKeyLength = 160;
            // Pick an iteration count that works for you. The NIST recommends at
            // least 1,000 iterations:
            int iterations = 20000;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
            SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
            return f.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
