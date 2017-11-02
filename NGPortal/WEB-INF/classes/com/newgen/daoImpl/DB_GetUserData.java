/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DB_GetUserData.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Feb 06, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
 ************************************************************************************************************/
package com.newgen.daoImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import JSON.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.ApplySchemeEntity;
import com.newgen.cig.entity.DocumentEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.dto.Set_Get_Data_OnAppID;
import com.newgen.dto.Set_Get_Data_PendingCases;
import com.newgen.dto.Set_Get_Data_PostLogIn;
import com.newgen.utility.AppConstants;
import com.newgen.utility.DataSourceConnect;
import com.newgen.utility.DateFormatConverter;
import com.newgen.utility.ImageEncoderUtility;
import com.newgen.utility.InitConfigProp;
import com.newgen.utility.PGAdmin_DB_Connection;
import com.newgen.utility.SaveMultipartUtility;
import com.newgen.utility.SendEmailUtility;
import com.newgen.utility.SendMessageUtility;

public class DB_GetUserData {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	CallableStatement callable = null;
	InitialContext Context;
	DataSource ds;
	DatabaseQuery databaseQ = new DatabaseQuery();

	// **********auto data edit profile*****************//

	public String getProfileDataAutoPopulate(String userName) throws Exception {
		ArrayList<AadhaarRegistrationEntity> listData = new ArrayList<AadhaarRegistrationEntity>();
		JSONObject autodataProfile = new JSONObject();
		try {

			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			// conn = PGAdmin_DB_Connection.getConnection();
			String locaiton = "";
			String query="";
			String getBankDetails="Y";
			String queryCheck = databaseQ.getCheckUserProfileType();

			try(Connection conn = ds.getConnection();
					PreparedStatement ps2 = conn.prepareStatement(queryCheck);){
					ps2.setString(1, userName);
					try(ResultSet rs2 = ps2.executeQuery();){
						if(rs2.next())
						{
							String npciStatus=rs2.getString("npci_status");
							String profile_type=rs2.getString("profile_type");
							if("N".equalsIgnoreCase(npciStatus)) {
								getBankDetails="N";
							}
							else if("I".equalsIgnoreCase(npciStatus)) {
								getBankDetails="Y";
							}
							else if("A".equalsIgnoreCase(npciStatus)) {
								getBankDetails="Y";
							}
									
							if("N".equalsIgnoreCase(getBankDetails))
							{
								query = databaseQ.getUserAadhaarProfileDetails();
			
							}
							else
							{
								 query = databaseQ.getUserNonAadhaarProfileDetails();
			
							}
						}
					}
			}
			System.out.println("Deatils>>>>>"+query);
			String selectData = databaseQ.getUserProfileDocDataForEditProfile();

			PreparedStatement ps1 = null;
			ResultSet rs1 = null;
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(query);){
					ps.setString(1, userName);
					try(ResultSet rs = ps.executeQuery();){
							System.out.println(ps.toString()+" data query");
							String poi = "";
							String poa = "";
							String por = "";
							String dob = "";
							String[] poi_doc = {};
							String[] poa_doc = {};
							String[] por_doc = {};
							String[] dob_doc = {};
							if (rs.next()) {
								// AadhaarRegistrationEntity dto = new
								// AadhaarRegistrationEntity();
								DateFormatConverter dateConverter = new DateFormatConverter();
								String formatedDob = dateConverter.dateFormatConverter(rs.getString("dob"), "yyyy-mm-dd", "dd/mm/yyyy");
				
								autodataProfile.put("photo", rs.getString("profile_pic"));
								autodataProfile.put("auidno", rs.getString("aadhaar_no"));
								autodataProfile.put("name", rs.getString("full_name"));
								autodataProfile.put("careoff", rs.getString("careof"));
								autodataProfile.put("dob", formatedDob);
								autodataProfile.put("age", rs.getInt("age"));
								autodataProfile.put("mobile", rs.getString("mobile_no"));
								autodataProfile.put("email", rs.getString("email_id"));
								autodataProfile.put("gender", rs.getString("gender"));
								autodataProfile.put("house", rs.getString("house"));
								autodataProfile.put("street", rs.getString("street"));
								autodataProfile.put("villagetowncity", rs.getString("village_town"));
								autodataProfile.put("postoffice", rs.getString("post_office"));
								autodataProfile.put("landmark", rs.getString("landmark"));
								autodataProfile.put("district", rs.getString("district"));
								autodataProfile.put("state", rs.getString("state"));
								autodataProfile.put("pincode", rs.getInt("pincode"));
								autodataProfile.put("sameAddress", rs.getString("sameAddress"));
								autodataProfile.put("eidNum", rs.getString("eid_pce_no"));
								autodataProfile.put("profileType", rs.getString("profile_type"));
								autodataProfile.put("c_house", rs.getString("c_house"));
								autodataProfile.put("c_street", rs.getString("c_street"));
								autodataProfile.put("c_villagetowncity", rs.getString("c_village_town"));
								autodataProfile.put("c_postoffice", rs.getString("c_post_office"));
								autodataProfile.put("c_landmark", rs.getString("c_landmark"));
								autodataProfile.put("c_district", rs.getString("c_district"));
								autodataProfile.put("c_state", rs.getString("c_state"));
								autodataProfile.put("c_pincode", rs.getString("c_pincode"));
								autodataProfile.put("npci_status", rs.getString("npci_status"));
								autodataProfile.put("father", rs.getString("father"));
								autodataProfile.put("isFatherAlive", rs.getString("isFatherAlive"));
								autodataProfile.put("mother", rs.getString("mother"));
								autodataProfile.put("isMotherAlive", rs.getString("isMotherAlive"));
								autodataProfile.put("relationType", rs.getString("relationType"));
								autodataProfile.put("guardian", rs.getString("guardian"));
								
								if("Y".equalsIgnoreCase(getBankDetails))
								{
									autodataProfile.put("bankAcc", rs.getString("bank_acc"));
									autodataProfile.put("branch", rs.getString("branch_name"));
									autodataProfile.put("ifsc", rs.getString("ifsc_code"));
									autodataProfile.put("bankAdd", rs.getString("bankAdd"));
								}
				
								poi = rs.getString("poi_doc");
								if ( poi != null) {
									poi_doc = poi.split(",");
									System.out.println(poi.toString()+" ::::::::::::::::::::::::: poi");
									// JSONObject innerMain = new JSONObject();
									JSONObject inner = new JSONObject();/// inner json object eg
																		/// voter id
									System.out.println(poi_doc.length+" poi_length");
									System.out.println(poi_doc[0]+" :::::::::::::::::");
									for (int i = 0; i < poi_doc.length; i++) {
										// System.out.println(poi_doc.length+"
										System.out.println(i+"::0000000000");
										try(PreparedStatement ps1_selectData = conn.prepareStatement(selectData);){
											ps1_selectData.setString(1, userName);
											ps1_selectData.setString(2, poi_doc[i]);
												try(ResultSet rs1_selectData = ps1_selectData.executeQuery();){
													if (rs1_selectData.next()) {
							
														JSONObject innervalues = new JSONObject();
														innervalues.put("selected", true);
														innervalues.put("reg_date", dateConverter.dateFormatConverter(rs1_selectData.getString("reg_date"),
																"yyyy-mm-dd", "dd/mm/yyyy"));
														innervalues.put("reg_authority", rs1_selectData.getString("reg_authority"));
														innervalues.put("cardNum", rs1_selectData.getString("doc_num"));
														innervalues.put("docPath", rs1_selectData.getString("doc_path"));
														inner.put(rs1_selectData.getString("doc_type"), innervalues); // inserting
																											// element
																											// to
																											// inner
																											// json
																											// object
														// jsonObjMain.put(field.toUpperCase(), inner);//
														// inserting inner json to main json object
							
													}
												}
										}
									}
									autodataProfile.put("POI", inner);
								
								}
				
								poa = rs.getString("poa_doc");
								if ( poa != null) {
									poa_doc = poa.split(",");
									JSONObject inner = new JSONObject();/// inner json object eg
																		/// voter id
									for (int i = 0; i < poa_doc.length; i++) {
				
										try(PreparedStatement ps1_docData = conn.prepareStatement(selectData);){
											ps1_docData.setString(1, userName);
											ps1_docData.setString(2, poa_doc[i]);
											try(ResultSet rs1_docData = ps1_docData.executeQuery();){
												if (rs1_docData.next()) {
													JSONObject innervalues = new JSONObject();
													innervalues.put("selected", true);
													innervalues.put("reg_date", dateConverter.dateFormatConverter(rs1_docData.getString("reg_date"),
															"yyyy-mm-dd", "dd/mm/yyyy"));
													innervalues.put("reg_authority", rs1_docData.getString("reg_authority"));
													innervalues.put("cardNum", rs1_docData.getString("doc_num"));
													innervalues.put("docPath", rs1_docData.getString("doc_path"));
													inner.put(rs1_docData.getString("doc_type"), innervalues); // inserting
																										// element
																										// to
																										// inner
																										// json
																										// object
													// jsonObjMain.put(field.toUpperCase(), inner);//
													// inserting inner json to main json object
						
												}
											}
										}
				
									}
									autodataProfile.put("POA", inner);
									
								}
				
								por = rs.getString("por_doc");
								if ( por != null) {
									por_doc = por.split(",");
									JSONObject inner = new JSONObject();/// inner json object eg
																		/// voter id
									for (int i = 0; i < por_doc.length; i++) {
				
										try(PreparedStatement ps1_pordoc = conn.prepareStatement(selectData);){
											ps1_pordoc.setString(1, userName);
											ps1_pordoc.setString(2, por_doc[i]);
												try(ResultSet rs1_pordoc = ps1_pordoc.executeQuery();){
													if (rs1_pordoc.next()) {
														JSONObject innervalues = new JSONObject();
														innervalues.put("selected", true);
														innervalues.put("reg_date", dateConverter.dateFormatConverter(rs1_pordoc.getString("reg_date"),
																"yyyy-mm-dd", "dd/mm/yyyy"));
														innervalues.put("reg_authority", rs1_pordoc.getString("reg_authority"));
														innervalues.put("cardNum", rs1_pordoc.getString("doc_num"));
														innervalues.put("docPath", rs1_pordoc.getString("doc_path"));
							
														inner.put(rs1_pordoc.getString("doc_type"), innervalues); // inserting
																											// element
																											// to
																											// inner
																											// json
																											// object
														// jsonObjMain.put(field.toUpperCase(), inner);//
														// inserting inner json to main json object
							
													}
												}
										}
				
									}
									autodataProfile.put("POR", inner);
								}
								dob = rs.getString("dob_doc");
								if ( dob != null) {
									dob_doc = dob.split(",");
									JSONObject inner = new JSONObject();/// inner json object eg
																		/// voter id
									for (int i = 0; i < dob_doc.length; i++) {
				
										try(PreparedStatement ps1_dob_doc = conn.prepareStatement(selectData);){
											ps1_dob_doc.setString(1, userName);
											ps1_dob_doc.setString(2, dob_doc[i]);
											try(ResultSet rs1_dob_doc = ps1_dob_doc.executeQuery();){
												if (rs1_dob_doc.next()) {
													JSONObject innervalues = new JSONObject();
													innervalues.put("selected", true);
													innervalues.put("reg_date", dateConverter.dateFormatConverter(rs1_dob_doc.getString("reg_date"),
															"yyyy-mm-dd", "dd/mm/yyyy"));
													innervalues.put("reg_authority", rs1_dob_doc.getString("reg_authority"));
													innervalues.put("cardNum", rs1_dob_doc.getString("doc_num"));
													innervalues.put("docPath", rs1_dob_doc.getString("doc_path"));
						
													inner.put(rs1_dob_doc.getString("doc_type"), innervalues); // inserting
																										// element
																										// to
																										// inner
																										// json
																										// object
													// jsonObjMain.put(field.toUpperCase(), inner);//
													// inserting inner json to main json object
						
												}
											}
										}
				
									}
									autodataProfile.put("DOB", inner);
								}
				
								System.out.println(autodataProfile.toString() + " ");
				
							}
					}
			}

		} catch (Exception e) {
			throw e;
		} 
		return autodataProfile.toString();
	}



	@SuppressWarnings("finally")

	public String updateResidentProfile(JSONObject jsonData, FormDataMultiPart formParams) throws Exception {
		

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String flag = "failure";
		String userId = "";
		String auidno = "";
		String name = "";
		String careoff = "";
		String dob = "";
		String age = "";
		String mobile = "";
		String email = "";
		String gender = "";
		String house = "";
		String street = "";
		String villagetowncity = "";
		String locality = "";
		String postoffice = "";
		String landmark = "";
		String district = "";
		String state = "";
		String pincode = "";
		String c_house = "";
		String c_street = "";
		String c_villagetowncity = "";
		String c_locality = "";
		String c_postoffice = "";
		String c_landmark = "";
		String c_district = "";
		String c_state = "";
		String c_pincode = "";
		String father = "";
		String mother = "";
		String guardian = "";
		String cardNum = "";
		String reg_date = "";
		String reg_authority = "";
		String bankAcc = "";
		String branch = "";
		String ifsc = "";
		String bankAdd = "";
		String POI = "";
		String POA = "";
		String POR = "";
		String attachmentData = "";
		String setdob = "";
		String profile_pic = "";

		String insertDocRep = "";
		String updateDocRep = "";
		String selectQuery = "";
		String selectQueryCheck = "";
		String[] docType = { "POI", "POA", "POR", "DOB" };
		int count = 0;
		DateFormatConverter updatedDate = new DateFormatConverter();
		SaveMultipartUtility smu = new SaveMultipartUtility();
		try {
			try {
				userId = jsonData.getString("userid");
			} catch (Exception e) {
				userId = "";
			}

			try {
				name = jsonData.getString("name");
			} catch (Exception e) {
				name = "";
			}
			try {
				careoff = jsonData.getString("careoff");
			} catch (Exception e) {
				careoff = "";
			}
			try {
				dob = jsonData.getString("dob");
			} catch (Exception e) {
				dob = "";
			}
			try {
				age = jsonData.getString("age");
			} catch (Exception e) {
				age = "";
			}
			try {
				mobile = jsonData.getString("mobile");
			} catch (Exception e) {
				mobile = "";
			}
			try {
				email = jsonData.getString("email");
			} catch (Exception e) {
				email = "";
			}
			try {
				gender = jsonData.getString("gender");
			} catch (Exception e) {
				gender = "";
			}
			try {
				house = jsonData.getString("house");
			} catch (Exception e) {
				house = "";
			}
			try {
				street = jsonData.getString("street");
			} catch (Exception e) {
				street = "";
			}
			try {
				villagetowncity = jsonData.getString("villagetowncity");
			} catch (Exception e) {
				villagetowncity = "";
			}
			try {
				locality = jsonData.getString("locality");
			} catch (Exception e) {
				locality = "";
			}
			try {
				postoffice = jsonData.getString("postoffice");
			} catch (Exception e) {
				postoffice = "";
			}
			try {
				landmark = jsonData.getString("landmark");
			} catch (Exception e) {
				landmark = "";
			}
			try {
				district = jsonData.getString("district");
			} catch (Exception e) {
				district = "";
			}
			try {
				state = jsonData.getString("state");
			} catch (Exception e) {
				state = "";
			}
			try {
				pincode = jsonData.getString("pincode");
			} catch (Exception e) {
				pincode = "";
			}

			try {
				c_house = jsonData.getString("c_house");
			} catch (Exception e) {
				c_house = "";
			}
			try {
				c_street = jsonData.getString("c_street");
			} catch (Exception e) {
				c_street = "";
			}
			try {
				c_villagetowncity = jsonData.getString("c_villagetowncity");
			} catch (Exception e) {
				c_villagetowncity = "";
			}
			try {
				c_locality = jsonData.getString("c_locality");
			} catch (Exception e) {
				c_locality = "";
			}
			try {
				c_postoffice = jsonData.getString("c_postoffice");
			} catch (Exception e) {
				c_postoffice = "";
			}
			try {
				c_landmark = jsonData.getString("c_landmark");
			} catch (Exception e) {
				c_landmark = "";
			}
			try {
				c_district = jsonData.getString("c_district");
			} catch (Exception e) {
				c_district = "";
			}
			try {
				c_state = jsonData.getString("c_state");
			} catch (Exception e) {
				c_state = "";
			}
			try {
				c_pincode = jsonData.getString("c_pincode");
			} catch (Exception e) {
				c_pincode = "0";
			}
			try {
				father = jsonData.getString("father");
			} catch (Exception e) {
				father = "";
			}
			try {
				mother = jsonData.getString("mother");
			} catch (Exception e) {
				mother = "";
			}
			try {
				guardian = jsonData.getString("guardian");
			} catch (Exception e) {
				guardian = "";
			}
			try {
				bankAcc = jsonData.getString("bankAcc");
			} catch (Exception e) {
				bankAcc = "";
			}
			try {
				branch = jsonData.getString("branch");
			} catch (Exception e) {
				branch = "";
			}
			try {
				ifsc = jsonData.getString("ifsc");
			} catch (Exception e) {
				ifsc = "";
			}
			try {
				bankAdd = jsonData.getString("bankAdd");
			} catch (Exception e) {
				bankAdd = "";
			}
			try {
				profile_pic = jsonData.getString("photo");
			} catch (Exception e) {
				profile_pic = "";
			}

			int x = 1;
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			
			conn.setAutoCommit(false);
			setdob = updatedDate.dateFormatConverter(dob, "DD/MM/YYYY", "YYYY-MM-DD");

			String updateProfile = databaseQ.getUpdateUserProfile();
				try(Connection conn = ds.getConnection();){
					try(PreparedStatement ps = conn.prepareStatement(updateProfile);){
						ps.setString(x++, name);
						ps.setString(x++, careoff);
						ps.setString(x++, setdob);
						ps.setString(x++, age);
						ps.setString(x++, mobile);
						ps.setString(x++, email);
						ps.setString(x++, gender);
						ps.setString(x++, house);
						ps.setString(x++, street);
						ps.setString(x++, villagetowncity);
						ps.setString(x++, postoffice);
						ps.setString(x++, landmark);
						ps.setString(x++, district);
						ps.setString(x++, state);
						ps.setString(x++, pincode);
						ps.setString(x++, c_house);
						ps.setString(x++, c_street);
						ps.setString(x++, c_villagetowncity);
						ps.setString(x++, c_locality);
						ps.setString(x++, c_postoffice);
						ps.setString(x++, c_landmark);
						ps.setString(x++, c_district);
						ps.setString(x++, c_state);
						ps.setString(x++, c_pincode);
						ps.setString(x++, father);
						ps.setString(x++, mother);
						ps.setString(x++, guardian);
						ps.setString(x++, profile_pic);
						ps.setString(x++, userId);
						int updateCount = ps.executeUpdate();
						if (updateCount > 0) {
							System.out.println("successfully updated in user profile");
							conn.commit();
							flag = "success";
						} else {
							System.out.println("Problem in updation");
							flag = "error";
						}
					}
					int y = 1;
					String updateBank = databaseQ.getUpdateUserBankDetails();
		
					try(PreparedStatement ps = conn.prepareStatement(updateBank);){
						ps.setString(y++, bankAcc);
						ps.setString(y++, branch);
						ps.setString(y++, ifsc);
						ps.setString(y++, bankAdd);
						ps.setString(y++, userId);
						int updateBankCount = ps.executeUpdate();
						if (updateBankCount > 0) {
							System.out.println(" successfully update bank account details");
						} else {
							System.out.println(" problem in updation");
							
						}
					}

					String error = "";
					String doc_id = "";

					String setDate = "";
					try {
						JSONObject jsonObj = null;
						for (int i = 0; i < docType.length; i++) {
							try {
								attachmentData = jsonData.getString(docType[i]);
								jsonObj = new JSONObject(attachmentData);
							} catch (Exception e) {
								continue; // loop will continew if json string not found
							}
							StringBuffer sb = new StringBuffer();
							insertDocRep =databaseQ.insertDocRep();
							updateDocRep =databaseQ.updateDocRep();
							selectQuery =databaseQ.selectQuery(); 
							selectQueryCheck =databaseQ.selectQueryCheck(); 
							// ResultSet rs=null;
							Iterator keys = jsonObj.keys(); // iterating keys for
															// getting condition type
							while (keys.hasNext()) {
								String key = (String) keys.next();
								String val = null;
								try {
		
									JSONObject value = jsonObj.getJSONObject(key);
									try {
										cardNum = value.getString("cardNum");
									} catch (Exception e) {
										cardNum = "";
									}
		
									// if("".equalsIgnoreCase(cardNum) || cardNum ==
									// null)
									if ("".equalsIgnoreCase(value.getString("cardNum")) || value.getString("cardNum") == null)
										continue;
		
									// try{
									FormDataBodyPart field = formParams.getField(docType[i] + "_" + key); // get
																											// the
																											// file
																											// details
																											// with
																											// key
																											// type
																											// values
									InputStream uploadedInputStream = field.getEntityAs(InputStream.class);
									FormDataContentDisposition fileDetial = field.getFormDataContentDisposition();
									String fileName = fileDetial.getFileName();
									String[] fileType = fileName.split("\\.");
									String customFileName = key.replaceAll(" ", "") + "." + fileType[1];
		
									// File docDirectory = new File(File.separatorChar +
									// "usr" + File.separatorChar + "dbt" +
									// File.separatorChar + "Repository" +
									// File.separatorChar + userId + File.separatorChar
									// + "Documents");
									File docDirectory = new File(InitConfigProp.getProperty("documentpath") + File.separatorChar
											+ "Repository" + File.separatorChar + userId + File.separatorChar + "Documents");
		
									String fileLocation = "";
									fileLocation = docDirectory + File.separator + customFileName;
									System.out.println("Location :: " + fileLocation);
		
									String filePath = smu.saveOnDisk(uploadedInputStream, customFileName, userId, docDirectory,
											fileLocation);
									// }
									//
		
									try(PreparedStatement ps_selectQuery = conn.prepareStatement(selectQuery);){
										ps_selectQuery.setString(1, key);
										System.out.println(ps_selectQuery.toString());
										try(ResultSet rs1 = ps_selectQuery.executeQuery();){
											while (rs1.next()) {
												doc_id = rs1.getString("doc_id");
											}
										}
									}
								
									try(PreparedStatement ps_selectQueryCheck = conn.prepareStatement(selectQueryCheck);){
										ps_selectQueryCheck.setString(1, doc_id);
										ps_selectQueryCheck.setString(2, userId);
										System.out.println(ps_selectQueryCheck.toString());
										try(ResultSet rs_selectQueryCheck = ps_selectQueryCheck.executeQuery();){
											if (rs_selectQueryCheck.next()) {
												count = rs_selectQueryCheck.getInt("count");
											}
										}
									}
		
									setDate = updatedDate.dateFormatConverter(value.getString("reg_date"), "DD/MM/YYYY",
											"YYYY-MM-DD");
		
									// int count = ps.executeUpdate();
		
									if (count > 0) {
		
										try(PreparedStatement ps_updateDocRep = conn.prepareStatement(updateDocRep);){
											ps_updateDocRep.setString(1, filePath);
											ps_updateDocRep.setString(2, value.getString("cardNum"));
											ps_updateDocRep.setString(3, setDate);
											ps_updateDocRep.setString(4, value.getString("reg_authority"));
											ps_updateDocRep.setString(5, doc_id);
											ps_updateDocRep.setString(6, userId);
											System.out.println(ps_updateDocRep.toString() + " updateDocReport");
										
											int check = ps_updateDocRep.executeUpdate();
											if (check > 0) {
												System.out.println("update in doc repositry successfuly");
											}
										}
									} else {
										try(PreparedStatement ps_insertDocRep = conn.prepareStatement(insertDocRep);){
											ps_insertDocRep.setString(1, doc_id);
											ps_insertDocRep.setString(2, filePath);
											ps_insertDocRep.setString(3, userId);
											ps_insertDocRep.setString(4, value.getString("cardNum"));
											ps_insertDocRep.setString(5, setDate);
											ps_insertDocRep.setString(6, value.getString("reg_authority"));
											int check = ps_insertDocRep.executeUpdate();
											if (check > 0) {
												System.out.println("insertion in doc repositry successfuly");
			
											}
										}
									}
		
									// ps.close();
		
									// parse(value,out);
								} catch (Exception e) {
									e.printStackTrace();
									val = jsonObj.getString(key);
									error = "error";
								}
		
								if (val != null) {
									// out.put(key,val);
								}
								sb = sb.append(doc_id + ",");// appending document id
																// for particular type
																// with comma separated
																// values
							}
		
							String updateProfileDoc = "update dbt_userprofile_dtl set " + docType[i]
									+ "_doc =?  where user_id=?";
				
							try(PreparedStatement ps = conn.prepareStatement(updateProfileDoc);){
							ps.setString(1, sb.toString().replaceAll(",$", ""));
							ps.setString(2, userId);
							int output = ps.executeUpdate();
							if (output > 0) {
								System.out.println("successfully updated column in userprofile table");
								if ("error".equalsIgnoreCase(error))
									System.out
											.println(" exception in form before commiting for updateresidentprofile function");
		
								else {
									conn.commit();
									flag = "success";
								}
							}
							// sb=null;
						  }
						}
						// JSONObject POI=getArray.getJSONObject("");
						// JSONObject objects = getArray.getJSONObject(i);
					} catch (Exception e) {
				POI = "";
				e.printStackTrace();
				flag = "failure";
			} // POI="";}

			// System.out.println(POI);
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// *******************************************************************//
	/* Created By Swarnadip Ghosh */
	/**
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */



	/**
	 * Created by Swarnadip Ghosh
	 * 
	 * @param userName
	 * @return
	 */
	public ArrayList<Set_Get_Data_PostLogIn> GetStudentDataPostLoginAsUser(String userName) throws Exception {
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(databaseQ.selectStudentAppData());){
				ps.setString(1, userName);
					/*"SELECT dbtr.app_id, scm.SchemeName, scm.SchemeId, dbtr.scheme_type,dbtr.app_serviceCategorty, "
							+ "DATE_FORMAT(dbtr.app_submit_datetime, '%d/%m/%y %H:%i') app_submit_date,"
							+ "dbte.app_stage_code app_status,dbtr.pref_flag "
							+ "FROM  dbt_user_application_status_dashboard_dtl dbtr "
							+ "INNER JOIN dbt_scheme_master scm ON scm.SchemeId = dbtr.app_scheme_id "
							+ "INNER JOIN esc_application_tracker_dtl dbte ON dbte.app_id = dbtr.app_id "
							+ "WHERE dbtr.user_id='" + userName + "'"
							+ "ORDER BY dbtr.app_submit_datetime DESC;")*/
				try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
		
						dto.setAppid(rs.getString("app_id"));
						dto.setScheme(rs.getString("SchemeName"));
						dto.setSchemeId(rs.getInt("SchemeId"));
						dto.setSchemeType(rs.getString("scheme_type"));
						dto.setCategory(rs.getString("app_serviceCategorty"));
						dto.setDateOfApp(rs.getString("app_submit_date"));
						dto.setStatus(rs.getString("app_status"));
						dto.setPref_flag(rs.getString("pref_flag"));
		
						listData.add(dto);
					}
				}
			}
		} catch (SQLException sec) {
			sec.printStackTrace();
		} 
		return listData;
	}

	public ArrayList<Set_Get_Data_OnAppID> getDataOnclickAppID(String appID) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_OnAppID> listData = new ArrayList<Set_Get_Data_OnAppID>();
		
			try(Connection conn = PGAdmin_DB_Connection.getConnection();
				PreparedStatement ps = conn.prepareStatement(databaseQ.dataOnclickAppID());){
				ps.setString(1, appID);
				try(ResultSet rs = ps.executeQuery();){
				while (rs.next()) {
					Set_Get_Data_OnAppID dto = new Set_Get_Data_OnAppID();
	
					dto.setIsDomicileofMaharashtra(rs.getString("IsDomicileofMaharashtra"));
					dto.setFirstName(rs.getString("FirstName"));
					dto.setMiddleName(rs.getString("MiddleName"));
					dto.setLastName(rs.getString("LastName"));
					dto.setADHAARNo(rs.getString("ADHAARNo"));
					dto.setMobileNo(rs.getString("MobileNo"));
					dto.setEmailID(rs.getString("EmailID"));
					dto.setDateofBirth(rs.getString("DateofBirth"));
					dto.setGender(rs.getString("Gender"));
					dto.setPANNo(rs.getString("PANNo"));
					dto.setCasteCertificateNo(rs.getString("CasteCertificateNo"));
					dto.setIsPhysicallyHandicapped(rs.getString("IsPhysicallyHandicapped"));
					dto.setDisabilityPercentage(rs.getString("DisabilityPercentage"));
					dto.setHostelAccommodation(rs.getString("HostelAccommodation"));
					dto.setSalaried(rs.getString("Salaried"));
					dto.setOccupation(rs.getString("Occupation"));
					dto.setIncome(rs.getString("Income"));
					// dto.setPhoto(rs.getString("Photo"));
					dto.setTenthPassingBoard(rs.getString("10thPassingBoard"));
					dto.setTenthPassingYear(rs.getString("10thPassingYear"));
					dto.setTenthcertificateNumber(rs.getString("10thcertificateNumber"));
					dto.setTenthMarksObtained(rs.getString("10thMarksObtained"));
					dto.setElethPassingBoard(rs.getString("11thPassingBoard"));
					dto.setElethPassingYear(rs.getString("11thPassingYear"));
					dto.setElethcertificateNumber(rs.getString("11thcertificateNumber "));
					dto.setElethMarksObtained(rs.getString("11thMarksObtained"));
					dto.setTwethPassingBoard(rs.getString("12thPassingBoard"));
					dto.setTwethPassingYear(rs.getString("12thPassingYear"));
					dto.setTwethcertificateNumber(rs.getString("12thcertificateNumber "));
					dto.setTwethMarksObtained(rs.getString("12thMarksObtained"));
					dto.setCasteCategory(rs.getString("CasteCategory"));
					dto.setCaste(rs.getString("Caste"));
					dto.setSubCaste(rs.getString("SubCaste"));
					dto.setBankName(rs.getString("BankName"));
					dto.setBankBranchName(rs.getString("BankBranchName"));
					dto.setAccountType(rs.getString("AccountType"));
					dto.setBankAccountNo(rs.getString("BankAccountNo"));
					dto.setBankDistrict(rs.getString("BankDistrict"));
					dto.setBankTaluka(rs.getString("BankTaluka"));
					dto.setBankBranchIFSCCode(rs.getString("BankBranchIFSCCode"));
					dto.setDistrict(rs.getString("District"));
					dto.setTaluka(rs.getString("Taluka"));
					dto.setVillageCityTown(rs.getString("VillageCityTown"));
					dto.setPinCode(rs.getString("PinCode"));
					dto.setCurrentCourseDistrict(rs.getString("CurrentCourseDistrict"));
					dto.setCurrentCourseTaluka(rs.getString("CurrentCourseTaluka"));
					dto.setCurrentCourseCollegeName(rs.getString("CurrentCourseCollegeName"));
					dto.setCurrentCourseCourseName(rs.getString("CurrentCourseCourseName"));
					dto.setCurrentCourseUniversityName(rs.getString("CurrentCourseUniversityName"));
					dto.setCurrentCourseYear(rs.getString("CurrentCourseYear"));
					dto.setCurrentCourseFeesPaid(rs.getString("CurrentCourseFeesPaid"));
					dto.setCurrentCourseGrantType(rs.getString("CurrentCourseGrantType"));
					dto.setCurrentCourseCAP(rs.getString("CurrentCourseCAP"));
					dto.setCurrentCourseCollegeAdmissionNo(rs.getString("CurrentCourseCollegeAdmissionNo"));
					dto.setCurrentCourseAdmissionDate(rs.getString("CurrentCourseAdmissionDate"));
					dto.setCourseAppliedFor(rs.getString("CourseAppliedFor"));
					dto.setCourseType(rs.getString("CourseType"));
					dto.setAppliedForYear(rs.getString("AppliedForYear"));
					dto.setJoiningYear(rs.getString("JoiningYear"));
					dto.setFatherFirstName(rs.getString("FatherFirstName"));
					dto.setFatherMiddleName(rs.getString("FatherMiddleName"));
					dto.setFatherLastName(rs.getString("FatherLastName"));
					dto.setFatherDateofBirth(rs.getString("FatherDateofBirth"));
					dto.setFatherOccupation(rs.getString("FatherOccupation"));
					dto.setFatherMaritalStatus(rs.getString("FatherMaritalStatus "));
					dto.setFatherPANNo(rs.getString("FatherPANNo"));
					dto.setFatherIncome(rs.getString("FatherIncome"));
					dto.setFatherIncomeCertificateNo(rs.getString("FatherIncomeCertificateNo"));
					dto.setFatherCasteCertificateNo(rs.getString("FatherCasteCertificateNo"));
					dto.setMotherFirstName(rs.getString("MotherFirstName"));
					dto.setMotherMiddleName(rs.getString("MotherMiddleName"));
					dto.setMotherLastName(rs.getString("MotherLastName"));
					dto.setMotherDateofBirth(rs.getString("MotherDateofBirth"));
					dto.setMotherOccupation(rs.getString("MotherOccupation"));
					dto.setMotherMaritalStatus(rs.getString("MotherMaritalStatus "));
					dto.setMotherPANNo(rs.getString("MotherPANNo"));
					dto.setMotherIncome(rs.getString("MotherIncome"));
					dto.setMotherIncomeCertificateNo(rs.getString("MotherIncomeCertificateNo"));
					dto.setMotherCasteCertificateNo(rs.getString("MotherCasteCertificateNo"));
					// dto.setApplicantIncomeCertificateImage(rs.getString("ApplicantIncomeCertificateImage"));
					dto.setApplicantIncomeCertificarteYear(rs.getString("ApplicantIncomeCertificarteYear"));
					// dto.setApplicantcasteCertificateImage(rs.getString("ApplicantcasteCertificateImage"));
					dto.setApplicantCasteCertificateYear(rs.getString("ApplicantCasteCertificateYear"));
					// dto.setApplicantHSCImage(rs.getString("ApplicantHSCImage"));
					// dto.setApplicantHSCBoard(rs.getString("ApplicantHSCBoard"));
					// dto.setApplicantHSCBoardYear(rs.getString("ApplicantHSCBoardYear"));
					// dto.setApplicantHSCStandard(rs.getString("ApplicantHSCStandard"));
					// dto.setApplicantHSCRollNo(rs.getString("ApplicantHSCRollNo"));
					// dto.setApplicantHSCName(rs.getString("ApplicantHSCName"));
					// dto.setApplicantSSCImage(rs.getString("ApplicantSSCImage"));
					// dto.setApplicantSSCBoard(rs.getString("ApplicantSSCBoard"));
					// dto.setApplicantSSCBoardYear(rs.getString("ApplicantSSCBoardYear"));
					// dto.setApplicantSSCStandard(rs.getString("ApplicantSSCStandard"));
					// dto.setApplicantSSCRollNo(rs.getString("ApplicantSSCRollNo"));
					// dto.setApplicantSSCName(rs.getString("ApplicantSSCName"));
					// dto.setApplicantDomicileImage(rs.getString("ApplicantDomicileImage"));
					// dto.setApplicantDomicileYear(rs.getString("ApplicantDomicileYear"));
					// dto.setApplicantDomicileState(rs.getString("ApplicantDomicileState"));
					// dto.setApplicantDomicileCertificateNo(rs.getString("ApplicantDomicileCertificateNo"));
	
					listData.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return listData;
	}

	public ArrayList<Set_Get_Data_OnAppID> getDataOnclickAppIDPrinciple(String appID) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_OnAppID> listData = new ArrayList<Set_Get_Data_OnAppID>();
		
			try(Connection conn = PGAdmin_DB_Connection.getConnection();
			PreparedStatement ps = conn.prepareStatement(databaseQ.dataOnclickAppIDPrinciple());){
			ps.setString(1, appID);
			
				try(ResultSet rs = ps.executeQuery();){

				while (rs.next()) {
					Set_Get_Data_OnAppID dto = new Set_Get_Data_OnAppID();
	
					dto.setClerk_Remarks(rs.getString("remarks"));
	
					dto.setIsDomicileofMaharashtra(rs.getString("IsDomicileofMaharashtra"));
					dto.setFirstName(rs.getString("FirstName"));
					dto.setMiddleName(rs.getString("MiddleName"));
					dto.setLastName(rs.getString("LastName"));
					dto.setADHAARNo(rs.getString("ADHAARNo"));
					dto.setMobileNo(rs.getString("MobileNo"));
					dto.setEmailID(rs.getString("EmailID"));
					dto.setDateofBirth(rs.getString("DateofBirth"));
					dto.setGender(rs.getString("Gender"));
					dto.setPANNo(rs.getString("PANNo"));
					dto.setCasteCertificateNo(rs.getString("CasteCertificateNo"));
					dto.setIsPhysicallyHandicapped(rs.getString("IsPhysicallyHandicapped"));
					dto.setDisabilityPercentage(rs.getString("DisabilityPercentage"));
					dto.setHostelAccommodation(rs.getString("HostelAccommodation"));
					dto.setSalaried(rs.getString("Salaried"));
					dto.setOccupation(rs.getString("Occupation"));
					dto.setIncome(rs.getString("Income"));
					// dto.setPhoto(rs.getString("Photo"));
					dto.setTenthPassingBoard(rs.getString("10thPassingBoard"));
					dto.setTenthPassingYear(rs.getString("10thPassingYear"));
					dto.setTenthcertificateNumber(rs.getString("10thcertificateNumber"));
					dto.setTenthMarksObtained(rs.getString("10thMarksObtained"));
					dto.setElethPassingBoard(rs.getString("11thPassingBoard"));
					dto.setElethPassingYear(rs.getString("11thPassingYear"));
					dto.setElethcertificateNumber(rs.getString("11thcertificateNumber "));
					dto.setElethMarksObtained(rs.getString("11thMarksObtained"));
					dto.setTwethPassingBoard(rs.getString("12thPassingBoard"));
					dto.setTwethPassingYear(rs.getString("12thPassingYear"));
					dto.setTwethcertificateNumber(rs.getString("12thcertificateNumber "));
					dto.setTwethMarksObtained(rs.getString("12thMarksObtained"));
					dto.setCasteCategory(rs.getString("CasteCategory"));
					dto.setCaste(rs.getString("Caste"));
					dto.setSubCaste(rs.getString("SubCaste"));
					dto.setBankName(rs.getString("BankName"));
					dto.setBankBranchName(rs.getString("BankBranchName"));
					dto.setAccountType(rs.getString("AccountType"));
					dto.setBankAccountNo(rs.getString("BankAccountNo"));
					dto.setBankDistrict(rs.getString("BankDistrict"));
					dto.setBankTaluka(rs.getString("BankTaluka"));
					dto.setBankBranchIFSCCode(rs.getString("BankBranchIFSCCode"));
					dto.setDistrict(rs.getString("District"));
					dto.setTaluka(rs.getString("Taluka"));
					dto.setVillageCityTown(rs.getString("VillageCityTown"));
					dto.setPinCode(rs.getString("PinCode"));
					dto.setCurrentCourseDistrict(rs.getString("CurrentCourseDistrict"));
					dto.setCurrentCourseTaluka(rs.getString("CurrentCourseTaluka"));
					dto.setCurrentCourseCollegeName(rs.getString("CurrentCourseCollegeName"));
					dto.setCurrentCourseCourseName(rs.getString("CurrentCourseCourseName"));
					dto.setCurrentCourseUniversityName(rs.getString("CurrentCourseUniversityName"));
					dto.setCurrentCourseYear(rs.getString("CurrentCourseYear"));
					dto.setCurrentCourseFeesPaid(rs.getString("CurrentCourseFeesPaid"));
					dto.setCurrentCourseGrantType(rs.getString("CurrentCourseGrantType"));
					dto.setCurrentCourseCAP(rs.getString("CurrentCourseCAP"));
					dto.setCurrentCourseCollegeAdmissionNo(rs.getString("CurrentCourseCollegeAdmissionNo"));
					dto.setCurrentCourseAdmissionDate(rs.getString("CurrentCourseAdmissionDate"));
					dto.setCourseAppliedFor(rs.getString("CourseAppliedFor"));
					dto.setCourseType(rs.getString("CourseType"));
					dto.setAppliedForYear(rs.getString("AppliedForYear"));
					dto.setJoiningYear(rs.getString("JoiningYear"));
					dto.setFatherFirstName(rs.getString("FatherFirstName"));
					dto.setFatherMiddleName(rs.getString("FatherMiddleName"));
					dto.setFatherLastName(rs.getString("FatherLastName"));
					dto.setFatherDateofBirth(rs.getString("FatherDateofBirth"));
					dto.setFatherOccupation(rs.getString("FatherOccupation"));
					dto.setFatherMaritalStatus(rs.getString("FatherMaritalStatus "));
					dto.setFatherPANNo(rs.getString("FatherPANNo"));
					dto.setFatherIncome(rs.getString("FatherIncome"));
					dto.setFatherIncomeCertificateNo(rs.getString("FatherIncomeCertificateNo"));
					dto.setFatherCasteCertificateNo(rs.getString("FatherCasteCertificateNo"));
					dto.setMotherFirstName(rs.getString("MotherFirstName"));
					dto.setMotherMiddleName(rs.getString("MotherMiddleName"));
					dto.setMotherLastName(rs.getString("MotherLastName"));
					dto.setMotherDateofBirth(rs.getString("MotherDateofBirth"));
					dto.setMotherOccupation(rs.getString("MotherOccupation"));
					dto.setMotherMaritalStatus(rs.getString("MotherMaritalStatus "));
					dto.setMotherPANNo(rs.getString("MotherPANNo"));
					dto.setMotherIncome(rs.getString("MotherIncome"));
					dto.setMotherIncomeCertificateNo(rs.getString("MotherIncomeCertificateNo"));
					dto.setMotherCasteCertificateNo(rs.getString("MotherCasteCertificateNo"));
					// dto.setApplicantIncomeCertificateImage(rs.getString("ApplicantIncomeCertificateImage"));
					dto.setApplicantIncomeCertificarteYear(rs.getString("ApplicantIncomeCertificarteYear"));
					// dto.setApplicantcasteCertificateImage(rs.getString("ApplicantcasteCertificateImage"));
					dto.setApplicantCasteCertificateYear(rs.getString("ApplicantCasteCertificateYear"));
					// dto.setApplicantHSCImage(rs.getString("ApplicantHSCImage"));
					// dto.setApplicantHSCBoard(rs.getString("ApplicantHSCBoard"));
					// dto.setApplicantHSCBoardYear(rs.getString("ApplicantHSCBoardYear"));
					// dto.setApplicantHSCStandard(rs.getString("ApplicantHSCStandard"));
					// dto.setApplicantHSCRollNo(rs.getString("ApplicantHSCRollNo"));
					// dto.setApplicantHSCName(rs.getString("ApplicantHSCName"));
					// dto.setApplicantSSCImage(rs.getString("ApplicantSSCImage"));
					// dto.setApplicantSSCBoard(rs.getString("ApplicantSSCBoard"));
					// dto.setApplicantSSCBoardYear(rs.getString("ApplicantSSCBoardYear"));
					// dto.setApplicantSSCStandard(rs.getString("ApplicantSSCStandard"));
					// dto.setApplicantSSCRollNo(rs.getString("ApplicantSSCRollNo"));
					// dto.setApplicantSSCName(rs.getString("ApplicantSSCName"));
					// dto.setApplicantDomicileImage(rs.getString("ApplicantDomicileImage"));
					// dto.setApplicantDomicileYear(rs.getString("ApplicantDomicileYear"));
					// dto.setApplicantDomicileState(rs.getString("ApplicantDomicileState"));
					// dto.setApplicantDomicileCertificateNo(rs.getString("ApplicantDomicileCertificateNo"));
	
					listData.add(dto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listData;
	}

	public ArrayList<ApplySchemeEntity> getDataOnclickAppIDStudent(String appID, String schemetype, int schemeid,
			String userIndex) {

		ArrayList<ApplySchemeEntity> listData = new ArrayList<ApplySchemeEntity>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			// String set_field = databaseQ.getEscApplicationData();
			String set_field = databaseQ.getEscApplicationData();
			try (Connection conn = ds.getConnection();) {

				try (CallableStatement callable = conn.prepareCall(set_field);) {
					callable.setString(1, appID);
					callable.setString(2, schemetype);
					callable.setInt(3, schemeid);
					callable.setString(4, userIndex);

					ApplySchemeEntity dto = new ApplySchemeEntity();

					try (ResultSet rs = callable.executeQuery();) {
						while (rs.next()) {
							if (schemetype.equals("PostMatric")) {
								if (schemeid == 16 || schemeid == 17 || schemeid == 18 || schemeid == 19
										|| schemeid == 20 || schemeid == 21) {

									System.out.println(
											"Enter : -------- Tribal Development Department > PostMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));
									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));
									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setFatherIncome(rs.getInt("fatherIncome"));
									dto.setMotherIncome(rs.getInt("motherIncome"));
									dto.setCourseType(rs.getString("courseType"));
									dto.setJoiningYear(rs.getInt("joiningYear"));
									dto.setCurrentCourseState(rs.getString("current_courseState"));
									dto.setCurrentCourseDistrict(rs.getString("current_courseDistrict"));
									dto.setCurrentCourseTaluka(rs.getString("current_courseTaluka"));
									dto.setCurrentCourseCollegeName(rs.getString("current_courseCollegeName"));
									dto.setCurrentCourseCollegeType(rs.getString("current_courseCollegeType"));
									dto.setCurrentCourseCourseName(rs.getString("current_course_CourseName"));
									dto.setCurrentCourseUniversityName(rs.getString("current_courseUniversityName"));
									dto.setCurrentCourseYear(rs.getInt("current_courseYear"));
									dto.setCurrentCourseGrantType(rs.getString("current_courseGrantType"));
									dto.setCurrentCourseCap(rs.getString("current_courseCAPNo"));
									dto.setCurrentCourseCollegeAdmissionNo(rs.getString("current_courseCollegeAdmissionNo"));
									dto.setCurrentCourseAdmissionDate(rs.getString("current_courseCollegeAdmissionDate"));
									dto.setCurrentCourseCollegeRegisterNo(rs.getString("collegeRegsitrationNo"));
									dto.setApplicant10thPassingBoard(rs.getString("10thPassingBoard"));
									dto.setApplicantSSCDivision(rs.getString("10thPassingDivision"));
									dto.setApplicant10thPassingYear(rs.getInt("10thPassingYear"));
									dto.setApplicant10thPassingMonth(rs.getString("10thPassingMonth"));
									dto.setApplicant10thRollNo(rs.getString("10thRollNo"));
									dto.setApplicant10thMarksObtained(rs.getString("10thMarksObtained"));
									dto.setApplicant10thcertificateNumber(rs.getString("10thcertificateNumber"));
									dto.setApplicantSSCBoard1(rs.getString("10thPassingBoardOther"));
									dto.setApplicant11thPassingBoard(rs.getString("11thPassingBoard"));
									dto.setApplicant11thDivision(rs.getString("11thPassingDivision"));
									dto.setApplicant11thPassingYear(rs.getInt("11thPassingYear"));
									dto.setApplicant11thPassingMonth(rs.getString("11thPassingMonth"));
									dto.setApplicant11thRollNo(rs.getString("11thRollNo"));
									dto.setApplicant11thMarksObtained(rs.getString("11thMarksObtained"));
									dto.setApplicant11thcertificateNumber(rs.getString("11thcertificateNumber"));
									dto.setApplicant11thBoard1(rs.getString("11thPassingBoardOther"));
									dto.setApplicant12thPassingBoard(rs.getString("12thPassingBoard"));
									dto.setApplicantHSCDivision(rs.getString("12thPassingDivision"));
									dto.setApplicant12thPassingYear(rs.getInt("12thPassingYear"));
									dto.setApplicant12thPassingMonth(rs.getString("12thPassingMonth"));
									dto.setApplicant12thRollNo(rs.getString("12thRollNo"));
									dto.setApplicant12thMarksObtained(rs.getString("12thMarksObtained"));
									dto.setApplicant12thcertificateNumber(rs.getString("12thcertificateNumber"));
									dto.setApplicantHSCBoard1(rs.getString("12thPassingBoardOther"));
									dto.setDiplomaAcademicYearPassed(rs.getInt("first_diplomaAcademicYearPassed"));
									dto.setDiplomaCollegeName(rs.getString("first_diplomaCollegeName"));
									dto.setDiplomaCourseName(rs.getString("first_diplomaCourseName"));
									dto.setDiplomaUniversityName(rs.getString("first_diplomaUniversityName"));
									dto.setDiplomaYear(rs.getString("first_diplomaYear"));
									dto.setDiplomaResult(rs.getString("first_diplomaResult"));
									dto.setDiplomaMarksObtained(rs.getString("first_diplomaMarksObtained"));
									dto.setSdiplomaAcademicYearPassed(rs.getInt("second_diplomaAcademicYearPassed"));
									dto.setSdiplomaCollegeName(rs.getString("second_diplomaCollegeName"));
									dto.setSdiplomaCourseName(rs.getString("second_diplomaCourseName"));
									dto.setSdiplomaUniversityName(rs.getString("second_diplomaUniversityName"));
									dto.setSdiplomaYear(rs.getString("second_diplomaYear"));
									dto.setSdiplomaResult(rs.getString("second_diplomaResult"));
									dto.setSdiplomaMarksObtained(rs.getString("second_diplomaMarksObtained"));
									dto.setTdiplomaAcademicYearPassed(rs.getInt("third_diplomaAcademicYearPassed"));
									dto.setTdiplomaCollegeName(rs.getString("third_diplomaCollegeName"));
									dto.setTdiplomaCourseName(rs.getString("third_diplomaCourseName"));
									dto.setTdiplomaUniversityName(rs.getString("third_diplomaUniversityName"));
									dto.setTdiplomaYear(rs.getString("third_diplomaYear"));
									dto.setTdiplomaResult(rs.getString("third_diplomaResult"));
									dto.setTdiplomaMarksObtained(rs.getString("third_diplomaMarksObtained"));
									dto.setfYearAcademicYearPassed(rs.getInt("first_collegeAcademicYearPassed"));
									dto.setfYearCollegeName(rs.getString("first_collegeName"));
									dto.setfYearCourseName(rs.getString("first_collegeCourseName"));
									dto.setfYearUniversityName(rs.getString("first_collegeUniversityName"));
									dto.setfYearYear(rs.getString("first_collegeYear"));
									dto.setfYearResult(rs.getString("first_collegeResult"));
									dto.setfYearMarksObtained(rs.getString("first_collegeMarksObtained"));
									dto.setsYearAcademicYearPassed(rs.getInt("second_collegeAcademicYearPassed"));
									dto.setsYearCollegeName(rs.getString("second_collegeName"));
									dto.setsYearCourseName(rs.getString("second_collegeCourseName"));
									dto.setsYearUniversityName(rs.getString("second_collegeUniversityName"));
									dto.setsYearYear(rs.getString("second_collegeYear"));
									dto.setsYearResult(rs.getString("second_collegeResult"));
									dto.setsYearMarksObtained(rs.getString("second_collegeMarksObtained"));
									dto.settYearAcademicYearPassed(rs.getInt("third_collegeAcademicYearPassed"));
									dto.settYearCollegeName(rs.getString("third_collegeName"));
									dto.settYearCourseName(rs.getString("third_collegeCourseName"));
									dto.settYearUniversityName(rs.getString("third_collegeUniversityName"));
									dto.settYearYear(rs.getString("third_collegeYear"));
									dto.settYearResult(rs.getString("third_collegeResult"));
									dto.settYearMarksObtained(rs.getString("third_collegeMarksObtained"));
									dto.setgYearAcademicYearPassed(rs.getInt("graduation_collegeAcademicYearPassed"));
									dto.setgYearCollegeName(rs.getString("graduation_collegeName"));
									dto.setgYearCourseName(rs.getString("graduation_collegeCourseName"));
									dto.setgYearUniversityName(rs.getString("graduation_collegeUniversityName"));
									dto.setgYearYear(rs.getString("graduation_collegeYear"));
									dto.setgYearResult(rs.getString("graduation_collegeResult"));
									dto.setgYearMarksObtained(rs.getString("graduation_collegeMarksObtained"));
									dto.setPgYearAcademicYearPassed(rs.getInt("post_graduation_collegeAcademicYearPassed"));
									dto.setPgYearCollegeName(rs.getString("post_graduation_collegeName"));
									dto.setPgYearCourseName(rs.getString("post_graduation_collegeCourseName"));
									dto.setPgYearUniversityName(rs.getString("post_graduation_collegeUniversityName"));
									dto.setPgYearYear(rs.getString("post_graduation_collegeYear"));
									dto.setPgYearResult(rs.getString("post_graduation_collegeResult"));
									dto.setPgYearMarksObtained(rs.getString("post_graduation_collegeMarksObtained"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setIsdiplomaPassed(rs.getString("isDplomaPassed"));
									dto.setTdiplomaState(rs.getString("third_diplomaState"));
									dto.setTdiplomaDistrict(rs.getString("third_diplomaDistrict"));
									dto.setTdiplomaTaluka(rs.getString("third_diplomaTaluka"));
									dto.setApplicantMaritalStatus(rs.getString("maritalStatus"));

									dto.setCapApplicationID(rs.getString("applicationID"));

									dto.setSpouseName(rs.getString("spouseName"));
									dto.setSpouseRelation(rs.getString("spouseRelation"));
									dto.setSpouseAge(rs.getString("spouseAge"));
									dto.setSpouseProfession(rs.getString("spouseOccupation"));
									dto.setSpouseAddress(rs.getString("spouseAddress"));
									dto.setSpouseState(rs.getString("spouseState"));
									dto.setSpouseDistrict(rs.getString("spouseDistrict"));
									dto.setSpouseTaluka(rs.getString("spouseTaluka"));
									dto.setSpouseCity(rs.getString("spouseCity"));
									dto.setSpousePinCode(rs.getString("spousePinCode"));
									dto.setApplicantSpouseSalaried(rs.getString("applicantSpouseSalaried"));

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(
											rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));

									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));

									dto.setApplicant10thTotalMarksObtained(rs.getInt("applicant10thTotalMarksObtained"));
									dto.setApplicant10thTotalMarks(rs.getInt("applicant10thTotalMarks"));
									dto.setApplicant11thTotalMarksObtained(rs.getInt("applicant11thTotalMarksObtained"));
									dto.setApplicant11thTotalMarks(rs.getInt("applicant11thTotalMarks"));
									dto.setApplicant12thTotalMarksObtained(rs.getInt("applicant12thTotalMarksObtained"));
									dto.setApplicant12thTotalMarks(rs.getInt("applicant12thTotalMarks"));
									dto.setApplicant11thGap(rs.getString("applicant11thGap"));
									dto.setApplicant12thStream(rs.getString("applicant12thStream"));
									dto.setApplicant12thMathsMarksObtained(rs.getInt("applicant12thMathsMarksObtained"));
									dto.setApplicant12thPhysicsMarksObtained(rs.getInt("applicant12thPhysicsMarksObtained"));
									dto.setApplicant12thGap(rs.getString("applicant12thGap"));
									dto.setApplicanttdiplomaGap(rs.getString("applicanttdiplomaGap"));
									dto.setApplicantdiplomaGap(rs.getString("applicantdiplomaGap"));
									dto.setSdiplomaGap(rs.getString("sdiplomaGap"));
									dto.setApplicant1styearGap(rs.getString("applicant1styearGap"));
									dto.setApplicant2ndyearGap(rs.getString("applicant2ndyearGap"));
									dto.setApplicant3rdyearGap(rs.getString("applicant3rdyearGap"));
									dto.setApplicantGyearGap(rs.getString("applicantGyearGap"));

									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));

									dto.setApplicant10thResult(rs.getString("10thResult"));
									dto.setApplicant11thResult(rs.getString("11thResult"));
									dto.setApplicant12thResult(rs.getString("12thResult"));

									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantPGyearGap(rs.getString("applicantPGyearGap"));
									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(
											rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));

									dto.setSscCertificateName(rs.getString("namessccertificate"));
									
									dto.setCurrentCourseExamFees(rs.getFloat("currentCourseExamFees"));
									dto.setCurrentCourseTutionFees(rs.getFloat("currentCourseTutionFees"));
									dto.setCurrentCourseOtherFees(rs.getFloat("currentCourseOtherFees"));
									
								} else if (schemeid == 22 || schemeid == 23 || schemeid == 24 || schemeid == 25
										|| schemeid == 26 || schemeid == 27 || schemeid == 28 || schemeid == 29
										|| schemeid == 30 || schemeid == 31 || schemeid == 32 || schemeid == 33) {
									System.out.println(
											"Enter : -------- Higher and Technical Education > PostMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));
									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));
									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setFatherIncome(rs.getInt("fatherIncome"));
									dto.setMotherIncome(rs.getInt("motherIncome"));
									dto.setCourseType(rs.getString("courseType"));
									dto.setJoiningYear(rs.getInt("joiningYear"));
									dto.setCurrentCourseState(rs.getString("current_courseState"));
									dto.setCurrentCourseDistrict(rs.getString("current_courseDistrict"));
									dto.setCurrentCourseTaluka(rs.getString("current_courseTaluka"));
									dto.setCurrentCourseCollegeName(rs.getString("current_courseCollegeName"));
									dto.setCurrentCourseCollegeType(rs.getString("current_courseCollegeType"));
									dto.setCurrentCourseCourseName(rs.getString("current_course_CourseName"));
									dto.setCurrentCourseUniversityName(rs.getString("current_courseUniversityName"));
									dto.setCurrentCourseYear(rs.getInt("current_courseYear"));
									dto.setCurrentCourseGrantType(rs.getString("current_courseGrantType"));
									dto.setCurrentCourseCap(rs.getString("current_courseCAPNo"));
									dto.setCurrentCourseCollegeAdmissionNo(rs.getString("current_courseCollegeAdmissionNo"));
									dto.setCurrentCourseAdmissionDate(rs.getString("current_courseCollegeAdmissionDate"));
									dto.setCurrentCourseCollegeRegisterNo(rs.getString("collegeRegsitrationNo"));
									dto.setApplicant10thPassingBoard(rs.getString("10thPassingBoard"));
									dto.setApplicantSSCDivision(rs.getString("10thPassingDivision"));
									dto.setApplicant10thPassingYear(rs.getInt("10thPassingYear"));
									dto.setApplicant10thPassingMonth(rs.getString("10thPassingMonth"));
									dto.setApplicant10thRollNo(rs.getString("10thRollNo"));
									dto.setApplicant10thMarksObtained(rs.getString("10thMarksObtained"));
									dto.setApplicant10thcertificateNumber(rs.getString("10thcertificateNumber"));
									dto.setApplicantSSCBoard1(rs.getString("10thPassingBoardOther"));
									dto.setApplicant11thPassingBoard(rs.getString("11thPassingBoard"));
									dto.setApplicant11thDivision(rs.getString("11thPassingDivision"));
									dto.setApplicant11thPassingYear(rs.getInt("11thPassingYear"));
									dto.setApplicant11thPassingMonth(rs.getString("11thPassingMonth"));
									dto.setApplicant11thRollNo(rs.getString("11thRollNo"));
									dto.setApplicant11thMarksObtained(rs.getString("11thMarksObtained"));
									dto.setApplicant11thcertificateNumber(rs.getString("11thcertificateNumber"));
									dto.setApplicant11thBoard1(rs.getString("11thPassingBoardOther"));
									dto.setApplicant12thPassingBoard(rs.getString("12thPassingBoard"));
									dto.setApplicantHSCDivision(rs.getString("12thPassingDivision"));
									dto.setApplicant12thPassingYear(rs.getInt("12thPassingYear"));
									dto.setApplicant12thPassingMonth(rs.getString("12thPassingMonth"));
									dto.setApplicant12thRollNo(rs.getString("12thRollNo"));
									dto.setApplicant12thMarksObtained(rs.getString("12thMarksObtained"));
									dto.setApplicant12thcertificateNumber(rs.getString("12thcertificateNumber"));
									dto.setApplicantHSCBoard1(rs.getString("12thPassingBoardOther"));
									dto.setDiplomaAcademicYearPassed(rs.getInt("first_diplomaAcademicYearPassed"));
									dto.setDiplomaCollegeName(rs.getString("first_diplomaCollegeName"));
									dto.setDiplomaCourseName(rs.getString("first_diplomaCourseName"));
									dto.setDiplomaUniversityName(rs.getString("first_diplomaUniversityName"));
									dto.setDiplomaYear(rs.getString("first_diplomaYear"));
									dto.setDiplomaResult(rs.getString("first_diplomaResult"));
									dto.setDiplomaMarksObtained(rs.getString("first_diplomaMarksObtained"));
									dto.setSdiplomaAcademicYearPassed(rs.getInt("second_diplomaAcademicYearPassed"));
									dto.setSdiplomaCollegeName(rs.getString("second_diplomaCollegeName"));
									dto.setSdiplomaCourseName(rs.getString("second_diplomaCourseName"));
									dto.setSdiplomaUniversityName(rs.getString("second_diplomaUniversityName"));
									dto.setSdiplomaYear(rs.getString("second_diplomaYear"));
									dto.setSdiplomaResult(rs.getString("second_diplomaResult"));
									dto.setSdiplomaMarksObtained(rs.getString("second_diplomaMarksObtained"));
									dto.setTdiplomaAcademicYearPassed(rs.getInt("third_diplomaAcademicYearPassed"));
									dto.setTdiplomaCollegeName(rs.getString("third_diplomaCollegeName"));
									dto.setTdiplomaCourseName(rs.getString("third_diplomaCourseName"));
									dto.setTdiplomaUniversityName(rs.getString("third_diplomaUniversityName"));
									dto.setTdiplomaYear(rs.getString("third_diplomaYear"));
									dto.setTdiplomaResult(rs.getString("third_diplomaResult"));
									dto.setTdiplomaMarksObtained(rs.getString("third_diplomaMarksObtained"));
									dto.setfYearAcademicYearPassed(rs.getInt("first_collegeAcademicYearPassed"));
									dto.setfYearCollegeName(rs.getString("first_collegeName"));
									dto.setfYearCourseName(rs.getString("first_collegeCourseName"));
									dto.setfYearUniversityName(rs.getString("first_collegeUniversityName"));
									dto.setfYearYear(rs.getString("first_collegeYear"));
									dto.setfYearResult(rs.getString("first_collegeResult"));
									dto.setfYearMarksObtained(rs.getString("first_collegeMarksObtained"));
									dto.setsYearAcademicYearPassed(rs.getInt("second_collegeAcademicYearPassed"));
									dto.setsYearCollegeName(rs.getString("second_collegeName"));
									dto.setsYearCourseName(rs.getString("second_collegeCourseName"));
									dto.setsYearUniversityName(rs.getString("second_collegeUniversityName"));
									dto.setsYearYear(rs.getString("second_collegeYear"));
									dto.setsYearResult(rs.getString("second_collegeResult"));
									dto.setsYearMarksObtained(rs.getString("second_collegeMarksObtained"));
									dto.settYearAcademicYearPassed(rs.getInt("third_collegeAcademicYearPassed"));
									dto.settYearCollegeName(rs.getString("third_collegeName"));
									dto.settYearCourseName(rs.getString("third_collegeCourseName"));
									dto.settYearUniversityName(rs.getString("third_collegeUniversityName"));
									dto.settYearYear(rs.getString("third_collegeYear"));
									dto.settYearResult(rs.getString("third_collegeResult"));
									dto.settYearMarksObtained(rs.getString("third_collegeMarksObtained"));
									dto.setgYearAcademicYearPassed(rs.getInt("graduation_collegeAcademicYearPassed"));
									dto.setgYearCollegeName(rs.getString("graduation_collegeName"));
									dto.setgYearCourseName(rs.getString("graduation_collegeCourseName"));
									dto.setgYearUniversityName(rs.getString("graduation_collegeUniversityName"));
									dto.setgYearYear(rs.getString("graduation_collegeYear"));
									dto.setgYearResult(rs.getString("graduation_collegeResult"));
									dto.setgYearMarksObtained(rs.getString("graduation_collegeMarksObtained"));
									dto.setPgYearAcademicYearPassed(rs.getInt("post_graduation_collegeAcademicYearPassed"));
									dto.setPgYearCollegeName(rs.getString("post_graduation_collegeName"));
									dto.setPgYearCourseName(rs.getString("post_graduation_collegeCourseName"));
									dto.setPgYearUniversityName(rs.getString("post_graduation_collegeUniversityName"));
									dto.setPgYearYear(rs.getString("post_graduation_collegeYear"));
									dto.setPgYearResult(rs.getString("post_graduation_collegeResult"));
									dto.setPgYearMarksObtained(rs.getString("post_graduation_collegeMarksObtained"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setIsdiplomaPassed(rs.getString("isDplomaPassed"));
									dto.setTdiplomaState(rs.getString("third_diplomaState"));
									dto.setTdiplomaDistrict(rs.getString("third_diplomaDistrict"));
									dto.setTdiplomaTaluka(rs.getString("third_diplomaTaluka"));
									dto.setApplicantMotherTongue(rs.getString("motherTongue"));
									dto.setApplicant10thSchoolName(rs.getString("SSCSchoolName"));
									dto.setApplicant10thSchoolDistrict(rs.getString("SSCSchooDistrict"));
									dto.setApplicant10thSchoolTaluka(rs.getString("SSCSchooTaluka"));
									dto.setApplicantType(rs.getString("applicantType"));
									dto.setApplicantSiblings(rs.getInt("applicantSiblings"));
									dto.setApplicantFatherExFreedomFighter(rs.getString("applicantFatherExFreedomFighter"));
									dto.setFatherStruggleName(rs.getString("fatherStruggleName"));
									dto.setFatherFreedomFighterType(rs.getString("fatherFreedomFighterType"));
									dto.setApplicantFatherExServiceMen(rs.getString("applicantFatherExServiceMen"));
									dto.setApplicantFatherPosted(rs.getString("applicantFatherPosted"));
									dto.setApplicantFatherServiceType(rs.getString("applicantFatherServiceType"));
									dto.setApplicantMotherExFreedomFighter(rs.getString("applicantMotherExFreedomFighter"));
									dto.setMotherStruggleName(rs.getString("motherStruggleName"));
									dto.setMotherFreedomFighterType(rs.getString("motherFreedomFighterType"));
									dto.setApplicantMotherExServiceMen(rs.getString("applicantMotherExServiceMen"));
									dto.setApplicantMotherPosted(rs.getString("applicantMotherPosted"));
									dto.setApplicantMotherServiceType(rs.getString("applicantMotherServiceType"));
									dto.setApplicantGuardianExFreedomFighter(rs.getString("applicantGuardianExFreedomFighter"));
									dto.setGuardianStruggleName(rs.getString("GuardianStruggleName"));
									dto.setGuardianFreedomFighterType(rs.getString("GuardianFreedomFighterType"));
									dto.setApplicantGuardianExServiceMen(rs.getString("applicantGuardianExServiceMen"));
									dto.setApplicantGuardianPosted(rs.getString("applicantGuardianPosted"));
									dto.setApplicantGuardianServiceType(rs.getString("applicantGuardianServiceType"));

									dto.setCapApplicationID(rs.getString("applicationID"));

									dto.setApplicantMaritalStatus(rs.getString("maritalStatus"));
									dto.setSpouseName(rs.getString("spouseName"));
									dto.setSpouseRelation(rs.getString("spouseRelation"));
									dto.setSpouseAge(rs.getString("spouseAge"));
									dto.setSpouseProfession(rs.getString("spouseOccupation"));
									dto.setSpouseAddress(rs.getString("spouseAddress"));
									dto.setSpouseState(rs.getString("spouseState"));
									dto.setSpouseDistrict(rs.getString("spouseDistrict"));
									dto.setSpouseTaluka(rs.getString("spouseTaluka"));
									dto.setSpouseCity(rs.getString("spouseCity"));
									dto.setSpousePinCode(rs.getString("spousePinCode"));
									dto.setApplicantSpouseSalaried(rs.getString("applicantSpouseSalaried"));

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));

									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));

									dto.setApplicant10thTotalMarksObtained(
											rs.getInt("applicant10thTotalMarksObtained"));
									dto.setApplicant10thTotalMarks(rs.getInt("applicant10thTotalMarks"));
									dto.setApplicant11thTotalMarksObtained(rs.getInt("applicant11thTotalMarksObtained"));
									dto.setApplicant11thTotalMarks(rs.getInt("applicant11thTotalMarks"));
									dto.setApplicant12thTotalMarksObtained(rs.getInt("applicant12thTotalMarksObtained"));
									dto.setApplicant12thTotalMarks(rs.getInt("applicant12thTotalMarks"));
									dto.setApplicant11thGap(rs.getString("applicant11thGap"));
									dto.setApplicant12thStream(rs.getString("applicant12thStream"));
									dto.setApplicant12thMathsMarksObtained(rs.getInt("applicant12thMathsMarksObtained"));
									dto.setApplicant12thPhysicsMarksObtained(rs.getInt("applicant12thPhysicsMarksObtained"));
									dto.setApplicant12thGap(rs.getString("applicant12thGap"));
									dto.setApplicanttdiplomaGap(rs.getString("applicanttdiplomaGap"));
									dto.setApplicantdiplomaGap(rs.getString("applicantdiplomaGap"));
									dto.setSdiplomaGap(rs.getString("sdiplomaGap"));
									dto.setApplicant1styearGap(rs.getString("applicant1styearGap"));
									dto.setApplicant2ndyearGap(rs.getString("applicant2ndyearGap"));
									dto.setApplicant3rdyearGap(rs.getString("applicant3rdyearGap"));
									dto.setApplicantGyearGap(rs.getString("applicantGyearGap"));
									dto.setApplicantReligion(rs.getString("religion"));
									dto.setApplicantChildNo(rs.getString("applicantChildNo"));

									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));

									dto.setApplicant10thResult(rs.getString("10thResult"));
									dto.setApplicant11thResult(rs.getString("11thResult"));
									dto.setApplicant12thResult(rs.getString("12thResult"));

									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantPGyearGap(rs.getString("applicantPGyearGap"));

									dto.setApplicant12thScienceMarksObtained(rs.getInt("applicant12thScienceMarksObtained"));
									dto.setApplicant12thMathsMarksObtained(rs.getInt("applicant12thMathsMarksObtained"));
									dto.setApplicant12thPhysicsMarksObtained(rs.getInt("applicant12thPhysicsMarksObtained"));

									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(
											rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));
									
									dto.setSscCertificateName(rs.getString("namessccertificate"));
									
									dto.setCurrentCourseExamFees(rs.getFloat("currentCourseExamFees"));
									dto.setCurrentCourseTutionFees(rs.getFloat("currentCourseTutionFees"));
									dto.setCurrentCourseOtherFees(rs.getFloat("currentCourseOtherFees"));
									
								} else if (schemeid == 38 || schemeid == 39 || schemeid == 40) {
									System.out.println("Enter : -------- School Education > PostMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));
									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));
									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setFatherIncome(rs.getInt("fatherIncome"));
									dto.setMotherIncome(rs.getInt("motherIncome"));
									dto.setCourseType(rs.getString("courseType"));
									dto.setJoiningYear(rs.getInt("joiningYear"));
									dto.setCurrentCourseState(rs.getString("current_courseState"));
									dto.setCurrentCourseDistrict(rs.getString("current_courseDistrict"));
									dto.setCurrentCourseTaluka(rs.getString("current_courseTaluka"));
									dto.setCurrentCourseCollegeName(rs.getString("current_courseCollegeName"));
									dto.setCurrentCourseCollegeType(rs.getString("current_courseCollegeType"));
									dto.setCurrentCourseCourseName(rs.getString("current_course_CourseName"));
									dto.setCurrentCourseUniversityName(rs.getString("current_courseUniversityName"));
									dto.setCurrentCourseYear(rs.getInt("current_courseYear"));
									dto.setCurrentCourseGrantType(rs.getString("current_courseGrantType"));
									dto.setCurrentCourseCap(rs.getString("current_courseCAPNo"));
									dto.setCurrentCourseCollegeAdmissionNo(rs.getString("current_courseCollegeAdmissionNo"));
									dto.setCurrentCourseAdmissionDate(rs.getString("current_courseCollegeAdmissionDate"));
									dto.setCurrentCourseCollegeRegisterNo(rs.getString("collegeRegsitrationNo"));
									dto.setApplicant10thPassingBoard(rs.getString("10thPassingBoard"));
									dto.setApplicantSSCDivision(rs.getString("10thPassingDivision"));
									dto.setApplicant10thPassingYear(rs.getInt("10thPassingYear"));
									dto.setApplicant10thPassingMonth(rs.getString("10thPassingMonth"));
									dto.setApplicant10thRollNo(rs.getString("10thRollNo"));
									dto.setApplicant10thMarksObtained(rs.getString("10thMarksObtained"));
									dto.setApplicant10thcertificateNumber(rs.getString("10thcertificateNumber"));
									dto.setApplicantSSCBoard1(rs.getString("10thPassingBoardOther"));
									dto.setApplicant11thPassingBoard(rs.getString("11thPassingBoard"));
									dto.setApplicant11thDivision(rs.getString("11thPassingDivision"));
									dto.setApplicant11thPassingYear(rs.getInt("11thPassingYear"));
									dto.setApplicant11thPassingMonth(rs.getString("11thPassingMonth"));
									dto.setApplicant11thRollNo(rs.getString("11thRollNo"));
									dto.setApplicant11thMarksObtained(rs.getString("11thMarksObtained"));
									dto.setApplicant11thcertificateNumber(rs.getString("11thcertificateNumber"));
									dto.setApplicant11thBoard1(rs.getString("11thPassingBoardOther"));
									dto.setApplicant12thPassingBoard(rs.getString("12thPassingBoard"));
									dto.setApplicantHSCDivision(rs.getString("12thPassingDivision"));
									dto.setApplicant12thPassingYear(rs.getInt("12thPassingYear"));
									dto.setApplicant12thPassingMonth(rs.getString("12thPassingMonth"));
									dto.setApplicant12thRollNo(rs.getString("12thRollNo"));
									dto.setApplicant12thMarksObtained(rs.getString("12thMarksObtained"));
									dto.setApplicant12thcertificateNumber(rs.getString("12thcertificateNumber"));
									dto.setApplicantHSCBoard1(rs.getString("12thPassingBoardOther"));
									dto.setDiplomaAcademicYearPassed(rs.getInt("first_diplomaAcademicYearPassed"));
									dto.setDiplomaCollegeName(rs.getString("first_diplomaCollegeName"));
									dto.setDiplomaCourseName(rs.getString("first_diplomaCourseName"));
									dto.setDiplomaUniversityName(rs.getString("first_diplomaUniversityName"));
									dto.setDiplomaYear(rs.getString("first_diplomaYear"));
									dto.setDiplomaResult(rs.getString("first_diplomaResult"));
									dto.setDiplomaMarksObtained(rs.getString("first_diplomaMarksObtained"));
									dto.setSdiplomaAcademicYearPassed(rs.getInt("second_diplomaAcademicYearPassed"));
									dto.setSdiplomaCollegeName(rs.getString("second_diplomaCollegeName"));
									dto.setSdiplomaCourseName(rs.getString("second_diplomaCourseName"));
									dto.setSdiplomaUniversityName(rs.getString("second_diplomaUniversityName"));
									dto.setSdiplomaYear(rs.getString("second_diplomaYear"));
									dto.setSdiplomaResult(rs.getString("second_diplomaResult"));
									dto.setSdiplomaMarksObtained(rs.getString("second_diplomaMarksObtained"));
									dto.setTdiplomaAcademicYearPassed(rs.getInt("third_diplomaAcademicYearPassed"));
									dto.setTdiplomaCollegeName(rs.getString("third_diplomaCollegeName"));
									dto.setTdiplomaCourseName(rs.getString("third_diplomaCourseName"));
									dto.setTdiplomaUniversityName(rs.getString("third_diplomaUniversityName"));
									dto.setTdiplomaYear(rs.getString("third_diplomaYear"));
									dto.setTdiplomaResult(rs.getString("third_diplomaResult"));
									dto.setTdiplomaMarksObtained(rs.getString("third_diplomaMarksObtained"));
									dto.setfYearAcademicYearPassed(rs.getInt("first_collegeAcademicYearPassed"));
									dto.setfYearCollegeName(rs.getString("first_collegeName"));
									dto.setfYearCourseName(rs.getString("first_collegeCourseName"));
									dto.setfYearUniversityName(rs.getString("first_collegeUniversityName"));
									dto.setfYearYear(rs.getString("first_collegeYear"));
									dto.setfYearResult(rs.getString("first_collegeResult"));
									dto.setfYearMarksObtained(rs.getString("first_collegeMarksObtained"));
									dto.setsYearAcademicYearPassed(rs.getInt("second_collegeAcademicYearPassed"));
									dto.setsYearCollegeName(rs.getString("second_collegeName"));
									dto.setsYearCourseName(rs.getString("second_collegeCourseName"));
									dto.setsYearUniversityName(rs.getString("second_collegeUniversityName"));
									dto.setsYearYear(rs.getString("second_collegeYear"));
									dto.setsYearResult(rs.getString("second_collegeResult"));
									dto.setsYearMarksObtained(rs.getString("second_collegeMarksObtained"));
									dto.settYearAcademicYearPassed(rs.getInt("third_collegeAcademicYearPassed"));
									dto.settYearCollegeName(rs.getString("third_collegeName"));
									dto.settYearCourseName(rs.getString("third_collegeCourseName"));
									dto.settYearUniversityName(rs.getString("third_collegeUniversityName"));
									dto.settYearYear(rs.getString("third_collegeYear"));
									dto.settYearResult(rs.getString("third_collegeResult"));
									dto.settYearMarksObtained(rs.getString("third_collegeMarksObtained"));
									dto.setgYearAcademicYearPassed(rs.getInt("graduation_collegeAcademicYearPassed"));
									dto.setgYearCollegeName(rs.getString("graduation_collegeName"));
									dto.setgYearCourseName(rs.getString("graduation_collegeCourseName"));
									dto.setgYearUniversityName(rs.getString("graduation_collegeUniversityName"));
									dto.setgYearYear(rs.getString("graduation_collegeYear"));
									dto.setgYearResult(rs.getString("graduation_collegeResult"));
									dto.setgYearMarksObtained(rs.getString("graduation_collegeMarksObtained"));
									dto.setPgYearAcademicYearPassed(rs.getInt("post_graduation_collegeAcademicYearPassed"));
									dto.setPgYearCollegeName(rs.getString("post_graduation_collegeName"));
									dto.setPgYearCourseName(rs.getString("post_graduation_collegeCourseName"));
									dto.setPgYearUniversityName(rs.getString("post_graduation_collegeUniversityName"));
									dto.setPgYearYear(rs.getString("post_graduation_collegeYear"));
									dto.setPgYearResult(rs.getString("post_graduation_collegeResult"));
									dto.setPgYearMarksObtained(rs.getString("post_graduation_collegeMarksObtained"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setIsdiplomaPassed(rs.getString("isDplomaPassed"));
									dto.setTdiplomaState(rs.getString("third_diplomaState"));
									dto.setTdiplomaDistrict(rs.getString("third_diplomaDistrict"));
									dto.setTdiplomaTaluka(rs.getString("third_diplomaTaluka"));
									dto.setApplicantExamSeatNo(rs.getString("applicantExamSeatNo"));
									dto.setApplicantSanskritMarks(rs.getString("applicantSanskritMarks"));
									dto.setApplicantSet(rs.getString("applicantSet"));
									dto.setCapApplicationID(rs.getString("applicationID"));
									dto.setApplicantMaritalStatus(rs.getString("maritalStatus"));
									dto.setSpouseName(rs.getString("spouseName"));
									dto.setSpouseRelation(rs.getString("spouseRelation"));
									dto.setSpouseAge(rs.getString("spouseAge"));
									dto.setSpouseProfession(rs.getString("spouseOccupation"));
									dto.setSpouseAddress(rs.getString("spouseAddress"));
									dto.setSpouseState(rs.getString("spouseState"));
									dto.setSpouseDistrict(rs.getString("spouseDistrict"));
									dto.setSpouseTaluka(rs.getString("spouseTaluka"));
									dto.setSpouseCity(rs.getString("spouseCity"));
									dto.setSpousePinCode(rs.getString("spousePinCode"));
									dto.setApplicantSpouseSalaried(rs.getString("applicantSpouseSalaried"));

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));

									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));

									dto.setApplicant10thTotalMarksObtained(rs.getInt("applicant10thTotalMarksObtained"));
									dto.setApplicant10thTotalMarks(rs.getInt("applicant10thTotalMarks"));
									dto.setApplicant11thTotalMarksObtained(rs.getInt("applicant11thTotalMarksObtained"));
									dto.setApplicant11thTotalMarks(rs.getInt("applicant11thTotalMarks"));
									dto.setApplicant12thTotalMarksObtained(rs.getInt("applicant12thTotalMarksObtained"));
									dto.setApplicant12thTotalMarks(rs.getInt("applicant12thTotalMarks"));
									dto.setApplicant11thGap(rs.getString("applicant11thGap"));
									dto.setApplicant12thStream(rs.getString("applicant12thStream"));
									dto.setApplicant12thMathsMarksObtained(rs.getInt("applicant12thMathsMarksObtained"));
									dto.setApplicant12thPhysicsMarksObtained(rs.getInt("applicant12thPhysicsMarksObtained"));
									dto.setApplicant12thGap(rs.getString("applicant12thGap"));
									dto.setApplicanttdiplomaGap(rs.getString("applicanttdiplomaGap"));
									dto.setApplicantdiplomaGap(rs.getString("applicantdiplomaGap"));
									dto.setSdiplomaGap(rs.getString("sdiplomaGap"));
									dto.setApplicant1styearGap(rs.getString("applicant1styearGap"));
									dto.setApplicant2ndyearGap(rs.getString("applicant2ndyearGap"));
									dto.setApplicant3rdyearGap(rs.getString("applicant3rdyearGap"));
									dto.setApplicantGyearGap(rs.getString("applicantGyearGap"));

									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));

									dto.setApplicant10thResult(rs.getString("10thResult"));
									dto.setApplicant11thResult(rs.getString("11thResult"));
									dto.setApplicant12thResult(rs.getString("12thResult"));

									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantPGyearGap(rs.getString("applicantPGyearGap"));
									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));
									
									dto.setSscCertificateName(rs.getString("namessccertificate"));
									
									dto.setCurrentCourseExamFees(rs.getFloat("currentCourseExamFees"));
									dto.setCurrentCourseTutionFees(rs.getFloat("currentCourseTutionFees"));
									dto.setCurrentCourseOtherFees(rs.getFloat("currentCourseOtherFees"));
									
								} else {
									System.out.println(
											"Enter : -------- Social Justice & Special Assistance > PostMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));

									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));

									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setFatherIncome(rs.getInt("fatherIncome"));
									dto.setMotherIncome(rs.getInt("motherIncome"));
									dto.setCourseType(rs.getString("courseType"));
									dto.setJoiningYear(rs.getInt("joiningYear"));
									dto.setCurrentCourseState(rs.getString("current_courseState"));
									dto.setCurrentCourseDistrict(rs.getString("current_courseDistrict"));
									dto.setCurrentCourseTaluka(rs.getString("current_courseTaluka"));
									dto.setCurrentCourseCollegeName(rs.getString("current_courseCollegeName"));
									dto.setCurrentCourseCollegeType(rs.getString("current_courseCollegeType"));
									dto.setCurrentCourseCourseName(rs.getString("current_course_CourseName"));
									dto.setCurrentCourseUniversityName(rs.getString("current_courseUniversityName"));
									dto.setCurrentCourseYear(rs.getInt("current_courseYear"));
									dto.setCurrentCourseGrantType(rs.getString("current_courseGrantType"));
									dto.setCurrentCourseCap(rs.getString("current_courseCAPNo"));
									dto.setCurrentCourseCollegeAdmissionNo(rs.getString("current_courseCollegeAdmissionNo"));
									dto.setCurrentCourseAdmissionDate(rs.getString("current_courseCollegeAdmissionDate"));
									dto.setCurrentCourseCollegeRegisterNo(rs.getString("collegeRegsitrationNo"));
									dto.setApplicant10thPassingBoard(rs.getString("10thPassingBoard"));
									dto.setApplicantSSCDivision(rs.getString("10thPassingDivision"));
									dto.setApplicant10thPassingYear(rs.getInt("10thPassingYear"));
									dto.setApplicant10thPassingMonth(rs.getString("10thPassingMonth"));
									dto.setApplicant10thRollNo(rs.getString("10thRollNo"));
									dto.setApplicant10thMarksObtained(rs.getString("10thMarksObtained"));
									dto.setApplicant10thcertificateNumber(rs.getString("10thcertificateNumber"));
									dto.setApplicantSSCBoard1(rs.getString("10thPassingBoardOther"));
									dto.setApplicant11thPassingBoard(rs.getString("11thPassingBoard"));
									dto.setApplicant11thDivision(rs.getString("11thPassingDivision"));
									dto.setApplicant11thPassingYear(rs.getInt("11thPassingYear"));
									dto.setApplicant11thPassingMonth(rs.getString("11thPassingMonth"));
									dto.setApplicant11thRollNo(rs.getString("11thRollNo"));
									dto.setApplicant11thMarksObtained(rs.getString("11thMarksObtained"));
									dto.setApplicant11thcertificateNumber(rs.getString("11thcertificateNumber"));
									dto.setApplicant11thBoard1(rs.getString("11thPassingBoardOther"));
									dto.setApplicant12thPassingBoard(rs.getString("12thPassingBoard"));
									dto.setApplicantHSCDivision(rs.getString("12thPassingDivision"));
									dto.setApplicant12thPassingYear(rs.getInt("12thPassingYear"));
									dto.setApplicant12thPassingMonth(rs.getString("12thPassingMonth"));
									dto.setApplicant12thRollNo(rs.getString("12thRollNo"));
									dto.setApplicant12thMarksObtained(rs.getString("12thMarksObtained"));
									dto.setApplicant12thcertificateNumber(rs.getString("12thcertificateNumber"));
									dto.setApplicantHSCBoard1(rs.getString("12thPassingBoardOther"));
									dto.setDiplomaAcademicYearPassed(rs.getInt("first_diplomaAcademicYearPassed"));
									dto.setDiplomaCollegeName(rs.getString("first_diplomaCollegeName"));
									dto.setDiplomaCourseName(rs.getString("first_diplomaCourseName"));
									dto.setDiplomaUniversityName(rs.getString("first_diplomaUniversityName"));
									dto.setDiplomaYear(rs.getString("first_diplomaYear"));
									dto.setDiplomaResult(rs.getString("first_diplomaResult"));
									dto.setDiplomaMarksObtained(rs.getString("first_diplomaMarksObtained"));
									dto.setSdiplomaAcademicYearPassed(rs.getInt("second_diplomaAcademicYearPassed"));
									dto.setSdiplomaCollegeName(rs.getString("second_diplomaCollegeName"));
									dto.setSdiplomaCourseName(rs.getString("second_diplomaCourseName"));
									dto.setSdiplomaUniversityName(rs.getString("second_diplomaUniversityName"));
									dto.setSdiplomaYear(rs.getString("second_diplomaYear"));
									dto.setSdiplomaResult(rs.getString("second_diplomaResult"));
									dto.setSdiplomaMarksObtained(rs.getString("second_diplomaMarksObtained"));
									dto.setTdiplomaAcademicYearPassed(rs.getInt("third_diplomaAcademicYearPassed"));
									dto.setTdiplomaCollegeName(rs.getString("third_diplomaCollegeName"));
									dto.setTdiplomaCourseName(rs.getString("third_diplomaCourseName"));
									dto.setTdiplomaUniversityName(rs.getString("third_diplomaUniversityName"));
									dto.setTdiplomaYear(rs.getString("third_diplomaYear"));
									dto.setTdiplomaResult(rs.getString("third_diplomaResult"));
									dto.setTdiplomaMarksObtained(rs.getString("third_diplomaMarksObtained"));
									dto.setfYearAcademicYearPassed(rs.getInt("first_collegeAcademicYearPassed"));
									dto.setfYearCollegeName(rs.getString("first_collegeName"));
									dto.setfYearCourseName(rs.getString("first_collegeCourseName"));
									dto.setfYearUniversityName(rs.getString("first_collegeUniversityName"));
									dto.setfYearYear(rs.getString("first_collegeYear"));
									dto.setfYearResult(rs.getString("first_collegeResult"));
									dto.setfYearMarksObtained(rs.getString("first_collegeMarksObtained"));
									dto.setsYearAcademicYearPassed(rs.getInt("second_collegeAcademicYearPassed"));
									dto.setsYearCollegeName(rs.getString("second_collegeName"));
									dto.setsYearCourseName(rs.getString("second_collegeCourseName"));
									dto.setsYearUniversityName(rs.getString("second_collegeUniversityName"));
									dto.setsYearYear(rs.getString("second_collegeYear"));
									dto.setsYearResult(rs.getString("second_collegeResult"));
									dto.setsYearMarksObtained(rs.getString("second_collegeMarksObtained"));
									dto.settYearAcademicYearPassed(rs.getInt("third_collegeAcademicYearPassed"));
									dto.settYearCollegeName(rs.getString("third_collegeName"));
									dto.settYearCourseName(rs.getString("third_collegeCourseName"));
									dto.settYearUniversityName(rs.getString("third_collegeUniversityName"));
									dto.settYearYear(rs.getString("third_collegeYear"));
									dto.settYearResult(rs.getString("third_collegeResult"));
									dto.settYearMarksObtained(rs.getString("third_collegeMarksObtained"));
									dto.setgYearAcademicYearPassed(rs.getInt("graduation_collegeAcademicYearPassed"));
									dto.setgYearCollegeName(rs.getString("graduation_collegeName"));
									dto.setgYearCourseName(rs.getString("graduation_collegeCourseName"));
									dto.setgYearUniversityName(rs.getString("graduation_collegeUniversityName"));
									dto.setgYearYear(rs.getString("graduation_collegeYear"));
									dto.setgYearResult(rs.getString("graduation_collegeResult"));
									dto.setgYearMarksObtained(rs.getString("graduation_collegeMarksObtained"));
									dto.setPgYearAcademicYearPassed(rs.getInt("post_graduation_collegeAcademicYearPassed"));
									dto.setPgYearCollegeName(rs.getString("post_graduation_collegeName"));
									dto.setPgYearCourseName(rs.getString("post_graduation_collegeCourseName"));
									dto.setPgYearUniversityName(rs.getString("post_graduation_collegeUniversityName"));
									dto.setPgYearYear(rs.getString("post_graduation_collegeYear"));
									dto.setPgYearResult(rs.getString("post_graduation_collegeResult"));
									dto.setPgYearMarksObtained(rs.getString("post_graduation_collegeMarksObtained"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setIsdiplomaPassed(rs.getString("isDplomaPassed"));
									dto.setTdiplomaState(rs.getString("third_diplomaState"));
									dto.setTdiplomaDistrict(rs.getString("third_diplomaDistrict"));
									dto.setTdiplomaTaluka(rs.getString("third_diplomaTaluka"));

									dto.setCapApplicationID(rs.getString("applicationID"));

									dto.setApplicantMaritalStatus(rs.getString("maritalStatus"));
									dto.setSpouseName(rs.getString("spouseName"));
									dto.setSpouseRelation(rs.getString("spouseRelation"));
									dto.setSpouseAge(rs.getString("spouseAge"));
									dto.setSpouseProfession(rs.getString("spouseOccupation"));
									dto.setSpouseAddress(rs.getString("spouseAddress"));
									dto.setSpouseState(rs.getString("spouseState"));
									dto.setSpouseDistrict(rs.getString("spouseDistrict"));
									dto.setSpouseTaluka(rs.getString("spouseTaluka"));
									dto.setSpouseCity(rs.getString("spouseCity"));
									dto.setSpousePinCode(rs.getString("spousePinCode"));
									dto.setApplicantSpouseSalaried(rs.getString("applicantSpouseSalaried"));

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));

									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));

									dto.setApplicant10thTotalMarksObtained(rs.getInt("applicant10thTotalMarksObtained"));
									dto.setApplicant10thTotalMarks(rs.getInt("applicant10thTotalMarks"));
									dto.setApplicant11thTotalMarksObtained(rs.getInt("applicant11thTotalMarksObtained"));
									dto.setApplicant11thTotalMarks(rs.getInt("applicant11thTotalMarks"));
									dto.setApplicant12thTotalMarksObtained(rs.getInt("applicant12thTotalMarksObtained"));
									dto.setApplicant12thTotalMarks(rs.getInt("applicant12thTotalMarks"));
									dto.setApplicant11thGap(rs.getString("applicant11thGap"));
									dto.setApplicant12thStream(rs.getString("applicant12thStream"));
									dto.setApplicant12thMathsMarksObtained(rs.getInt("applicant12thMathsMarksObtained"));
									dto.setApplicant12thPhysicsMarksObtained(rs.getInt("applicant12thPhysicsMarksObtained"));
									dto.setApplicant12thGap(rs.getString("applicant12thGap"));
									dto.setApplicanttdiplomaGap(rs.getString("applicanttdiplomaGap"));
									dto.setApplicantdiplomaGap(rs.getString("applicantdiplomaGap"));
									dto.setSdiplomaGap(rs.getString("sdiplomaGap"));
									dto.setApplicant1styearGap(rs.getString("applicant1styearGap"));
									dto.setApplicant2ndyearGap(rs.getString("applicant2ndyearGap"));
									dto.setApplicant3rdyearGap(rs.getString("applicant3rdyearGap"));
									dto.setApplicantGyearGap(rs.getString("applicantGyearGap"));
									dto.setApplicantReligion(rs.getString("religion"));
									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));
									dto.setApplicant10thResult(rs.getString("10thResult"));
									dto.setApplicant11thResult(rs.getString("11thResult"));
									dto.setApplicant12thResult(rs.getString("12thResult"));

									dto.setApplicantChildNo(rs.getString("applicantChildNo"));
									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantPGyearGap(rs.getString("applicantPGyearGap"));
									dto.setApplicantJNUStudent(rs.getString("JNURecommended"));
									dto.setApplicantDHERecommended(rs.getString("DHERecommended"));
									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));
									
									dto.setSscCertificateName(rs.getString("namessccertificate"));
									
									dto.setCurrentCourseExamFees(rs.getFloat("currentCourseExamFees"));
									dto.setCurrentCourseTutionFees(rs.getFloat("currentCourseTutionFees"));
									dto.setCurrentCourseOtherFees(rs.getFloat("currentCourseOtherFees"));
								}
							} else if (schemetype.equals("PreMatric")) {
								if (schemeid == 15 || schemeid == 21) {
									System.out.println(
											"Enter : -------- Tribal Development Department > PreMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));
									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));
									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setApplicantSARALNO(rs.getString("saralNo"));
									dto.setApplicantGRNNO(rs.getString("grnNo"));
									dto.setApplicantSchoolName(rs.getString("schoolName"));
									dto.setApplicantAcademicYear(rs.getInt("academicYear"));
									dto.setApplicantSchoolAdmissionDate(rs.getString("admissionDate"));
									dto.setApplicantSchoolStandard(rs.getString("standard"));
									// dto.setLastYearResult(rs.getString("lastYearResult"));
									dto.setApplicantlastYearResult(rs.getString("lastYearResult"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setApplicantUDISECode(rs.getString("udiseCode"));
									dto.setDoYouHaveGRNNo(rs.getString("haveGRNNo"));
									/*dto.setLastSchoolName(rs.getString("last_schoolName"));
									dto.setLastAcademicYear(rs.getInt("last_academicYear"));
									dto.setLastSchoolAdmissionDate(rs.getString("last_admissionDate"));
									dto.setLastSchoolStandard(rs.getString("last_Standard"));
									dto.setLastSchoolGrade(rs.getString("last_grade"));
									// dto.setLastSchoolMarks(String.valueOf(Math.round(rs.getFloat("lastYearMarksObtained"))));
									dto.setApplicantSchoolMarks(String.valueOf(Math.round(rs.getFloat("lastYearMarksObtained"))));*/

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));
									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));
									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));
									// dto.setApplicantSchoolMarks(rs.getString("marksObtained"));
									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));

									dto.setApplicantSchoolDistrict(rs.getString("schooldistrict"));
									dto.setApplicantSchoolTaluka(rs.getString("schooltaluka"));
								} else if (schemeid == 34 || schemeid == 40) {
									System.out.println("Enter : -------- School Education > PreMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));
									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));
									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setApplicantSARALNO(rs.getString("saralNo"));
									dto.setApplicantGRNNO(rs.getString("grnNo"));
									dto.setApplicantSchoolName(rs.getString("schoolName"));
									dto.setApplicantAcademicYear(rs.getInt("academicYear"));
									dto.setApplicantSchoolAdmissionDate(rs.getString("admissionDate"));
									dto.setApplicantSchoolStandard(rs.getString("standard"));
									dto.setApplicantlastYearResult(rs.getString("lastYearResult"));
									dto.setApplicantSchoolGrade(rs.getString("grade"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setApplicantUDISECode(rs.getString("udiseCode"));
									dto.setDoYouHaveGRNNo(rs.getString("haveGRNNo"));
									dto.setApplicantExamSeatNo(rs.getString("applicantExamSeatNo"));
									dto.setApplicantSanskritMarks(rs.getString("applicantSanskritMarks"));
									dto.setApplicantSet(rs.getString("applicantSet"));
									dto.setParikshaExam(rs.getString("parikshaExam"));

									dto.setApplicantSchoolMarks(rs.getString("marksObtained"));

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(	rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(	rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));
									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));

									dto.setApplicantIsBPL(rs.getString("applicantIsBPL"));
									dto.setApplicantBPlNo(rs.getString("applicantBPlNo"));

									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));
									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));
									dto.setApplicantHaveBPLCertificate(rs.getString("is_bpl_certificate"));
									dto.setApplicantHaveBPLBarCode(rs.getString("havebplbarcode"));
									dto.setApplicantBPLBarCode(rs.getString("bplbarcodenumber"));
									dto.setApplicantBPLBarcodeName(rs.getString("bplbarcodename"));
									dto.setApplicantBPLAuthority(rs.getString("bplauthority"));
									dto.setApplicantBPLDate(rs.getString("bplissuedate"));
									dto.setApplicantSchoolDistrict(rs.getString("schooldistrict"));
									dto.setApplicantSchoolTaluka(rs.getString("schooltaluka"));
								} else {
									System.out.println(
											"Enter : -------- Social Justice & Special Assistance(else) > PreMatric------------");

									dto.setApplicantRenewable(rs.getString("isapplicantionRenewable"));
									dto.setOldApplicationID(rs.getString("oldApplicationID"));
									dto.setApplicantDomicile(rs.getString("isDomicile"));
									dto.setApplicantDomicileCertificateNo(rs.getString("domicileCertificateNo"));
									dto.setApplicantDomicileCertificateYear(rs.getInt("domicileCertificateYear"));
									dto.setApplicantFullName(rs.getString("applicant_name"));
									dto.setApplicantAadhaarUID(rs.getString("aadhaarNo"));
									dto.setApplicantMobileNo(rs.getString("mobileNo"));
									dto.setApplicantEmailID(rs.getString("email"));
									dto.setApplicantDOB(rs.getString("dob"));
									dto.setApplicantGender(rs.getString("gender"));
									dto.setBankSeededAadhaar(rs.getString("isBankAAdhaarSeeded"));
									dto.setFamilyIncome(rs.getString("familyIncome"));
									dto.setApplicantAddress(rs.getString("address"));
									dto.setApplicantState(rs.getString("state"));
									dto.setApplicantDistrict(rs.getString("district"));
									dto.setApplicantTaluka(rs.getString("taluka"));
									dto.setApplicantCity(rs.getString("city"));
									dto.setApplicantPinCode(rs.getInt("pinCode"));
									dto.setApplicantAddress_correspondence(rs.getString("correspondence_address"));
									dto.setApplicantState_correspondence(rs.getString("correspondence_state"));
									dto.setApplicantDistrict_correspondence(rs.getString("correspondence_district"));
									dto.setApplicantTaluka_correspondence(rs.getString("correspondence_taluka"));
									dto.setApplicantCity_correspondence(rs.getString("correspondence_city"));
									dto.setApplicantPinCode_correspondence(rs.getInt("correspondence_pinCode"));
									dto.setApplicantCasteCategory(rs.getString("casteCategory"));
									dto.setApplicantCaste(rs.getString("caste"));
									dto.setApplicantSubCaste(rs.getString("subCaste"));
									dto.setApplicantIsSalaried(rs.getString("isSalaried"));
									dto.setApplicantJobType(rs.getString("jobType"));
									dto.setApplicantIncome(rs.getString("income"));
									dto.setApplicantPanNo(rs.getString("panNo"));
									dto.setApplicantIsPhysicallyHandicapped(rs.getString("isDisabled"));
									dto.setApplicantDisabilityType(rs.getString("disabilityType"));
									dto.setApplicantDisabilityPercentage(rs.getString("disabilityPerCent"));
									dto.setFatherName(rs.getString("father_name"));
									dto.setFatherDOB(rs.getString("fatherDOB"));
									dto.setFatherOccupation(rs.getString("fatherOccupation"));
									dto.setFatherPanNo(rs.getString("fatherPANNo"));
									dto.setFatherIncomeCertificateNo(rs.getString("fatherIncomeCertificateNo"));
									dto.setMotherName(rs.getString("mother_name"));
									dto.setMotherDOB(rs.getString("motherDOB"));
									dto.setMotherOccupation(rs.getString("motherOccupation"));
									dto.setMotherPanNo(rs.getString("motherPANNo"));
									dto.setMotherIncomeCertificateNo(rs.getString("motherIncomeCertificateNo"));
									dto.setApplicantCasteCertificateNo(rs.getString("casteCertificateNo"));
									dto.setApplicantCasteCertificateDate(rs.getString("casteCertificateDate"));
									dto.setApplicantCasteCertificateYear(rs.getInt("casteCertificateYear"));
									dto.setApplicantCasteDistrict(rs.getString("casteCertificateDistrict"));
									dto.setApplicantCasteTaluka(rs.getString("casteCertificateTaluka"));
									dto.setApplicantCasteCertificateSubdivision(rs.getString("casteCertificateSubdivision"));
									dto.setApplicantCasteCertificateAuthority(rs.getString("casteCertificateAuthority"));
									dto.setApplicantCasteValidityNo(rs.getString("casteValidityNo"));
									dto.setApplicantCasteValidityCertificateDate(rs.getString("casteValidityDate"));
									dto.setApplicantCasteValidityCertificateYear(rs.getInt("casteValiditYear"));
									dto.setCasteValidityDistrict(rs.getString("casteValidityDistrict"));
									dto.setApplicantCasteValidityTaluka(rs.getString("casteValidityTaluka"));
									dto.setApplicantDayScholarDetails(rs.getString("allowanceType")); // DayScholar
									dto.setApplicantHostelDistrict(rs.getString("hostelDistrict"));
									dto.setApplicantHostelTaluka(rs.getString("hostelTaluka"));
									dto.setHostelType(rs.getString("hostelType"));
									dto.setApplicantHostelName(rs.getString("hostelName"));
									dto.setHostelAided(rs.getString("isHostelAided"));
									dto.setApplicantHostelAddress(rs.getString("hostelAddress"));
									dto.setApplicantSARALNO(rs.getString("saralNo"));
									dto.setApplicantGRNNO(rs.getString("grnNo"));
									dto.setApplicantSchoolName(rs.getString("schoolName"));
									dto.setApplicantAcademicYear(rs.getInt("academicYear"));
									dto.setApplicantSchoolAdmissionDate(rs.getString("admissionDate"));
									dto.setApplicantSchoolStandard(rs.getString("standard"));
									dto.setApplicantlastYearResult(rs.getString("lastYearResult"));
									dto.setApplicantSchoolGrade(rs.getString("grade"));
									dto.setAddressIsPermanent(rs.getString("addressIsPermanent"));
									dto.setApplicantFatherAlive(rs.getString("applicantFatherAlive"));
									dto.setApplicantMotherAlive(rs.getString("applicantMotherAlive"));
									dto.setApplicantFatherSalaried(rs.getString("applicantFatherSalaried"));
									dto.setApplicantMotherSalaried(rs.getString("applicantMotherSalaried"));
									dto.setApplicantUDISECode(rs.getString("udiseCode"));
									dto.setDoYouHaveGRNNo(rs.getString("haveGRNNo"));

									dto.setApplicantSchoolMarks(rs.getString("marksObtained"));
									/*
									 * ------Added at 16-06-2017
									 * -----------------------------------------
									 * --------------------------------
									 */

									dto.setApplicantHaveDomicileBarCode(rs.getString("applicantHaveDomicileBarCode"));
									dto.setApplicantDomicileCertificateBarCode(
											rs.getString("applicantDomicileCertificateBarCode"));
									dto.setApplicantDomicileBarcodeName(rs.getString("applicantDomicileBarcodeName"));
									dto.setApplicantDomicileCertificateDate_barcode(
											rs.getString("applicantDomicileCertificateDate_barcode"));
									dto.setApplicantDomicileCertificateAuthority_barcode(
											rs.getString("applicantDomicileCertificateAuthority_barcode"));
									dto.setApplicantAge(rs.getInt("applicantAge"));
									dto.setApplicantHaveIncomeBarCode(rs.getString("applicantHaveIncomeBarCode"));
									dto.setApplicantIncomeCertificateBarCode(
											rs.getString("applicantIncomeCertificateBarCode"));
									dto.setApplicantIncomeBarcodeName(rs.getString("applicantIncomeBarcodeName"));
									// dto.setApplicantIncomeCertificateDate_barcode(rs.getString("applicantIncomeCertificateDate_barcode"));
									// dto.setApplicantIncomeCertificateAuthority_barcode(rs.getString("applicantIncomeCertificateAuthority_barcode"));
									dto.setApplicantIncomeCertificateNo(rs.getString("applicantIncomeCertificateNo"));
									dto.setApplicantIncomeCertificateDate(
											rs.getString("applicantIncomeCertificateDate"));
									dto.setReaderOpted(rs.getString("readerOpted"));
									dto.setApplicantHaveDisabilityBarCode(
											rs.getString("applicantHaveDisabilityBarCode"));
									dto.setApplicantDisabilityCertificateBarCode(
											rs.getString("applicantDisabilityCertificateBarCode"));
									dto.setApplicantDisabilityBarcodeName(
											rs.getString("applicantDisabilityBarcodeName"));
									// dto.setApplicantDisabilityCertificateDate_barcode(rs.getString("applicantDisabilityCertificateDate_barcode"));
									// dto.setApplicantDisabilityCertificateAuthority_barcode(rs.getString("applicantDisabilityCertificateAuthority_barcode"));
									dto.setApplicantDisabilityCertificateNo(
											rs.getString("applicantDisabilityCertificateNo"));
									dto.setApplicantDisabilityCertificateAuthority(
											rs.getString("applicantDisabilityCertificateAuthority"));
									dto.setApplicantDisabilityCertificateDate(
											rs.getString("applicantDisabilityCertificateDate"));
									dto.setApplicantIncomeCertificateAuthority(
											rs.getString("applicantIncomeCertificateAuthority"));
									dto.setApplicantDisableType(rs.getString("applicantDisableType"));

									dto.setApplicantHaveCasteBarCode(rs.getString("isHaveBarCode"));
									dto.setApplicantCasteCertificateBarCode(rs.getString("casteCertificateBarCode"));
									dto.setApplicantBarcodeName(rs.getString("applicantName_barcode"));
									// dto.setApplicantCasteCertificateDate_barcode(rs.getString("casteCertificateDate_barcode"));
									// dto.setApplicantCasteCertificateAuthority_barcode(rs.getString("casteCertificateAuthority_barcode"));

									dto.setApplicantIsBPL(rs.getString("applicantIsBPL"));
									dto.setApplicantBPlNo(rs.getString("applicantBPlNo"));

									dto.setGaurdianName(rs.getString("gaurdianName"));
									dto.setGaurdianAddress(rs.getString("gaurdianAddress"));
									dto.setApplicantGuardinSalaried(rs.getString("applicantGuardinSalaried"));
									dto.setGuardianOccupation(rs.getString("guardianOccupation"));

									dto.setApplicantDisabilityYear(rs.getInt("applicantDisabilityYear"));
									dto.setApplicantHaveIncomeCertificate(rs.getString("is_income_certificate"));
									dto.setApplicantHaveDisabilityCertificate(
											rs.getString("is_disability_certificate"));
									dto.setApplicantHaveCertificateDomicile(rs.getString("is_domicile_certificate"));
									dto.setApplicantHaveCasteCertificate(rs.getString("is_caste_certificate"));

									dto.setApplicantHaveBPLCertificate(rs.getString("is_bpl_certificate"));
									dto.setApplicantHaveBPLBarCode(rs.getString("havebplbarcode"));
									dto.setApplicantBPLBarCode(rs.getString("bplbarcodenumber"));
									dto.setApplicantBPLBarcodeName(rs.getString("bplbarcodename"));
									dto.setApplicantBPLAuthority(rs.getString("bplauthority"));
									dto.setApplicantBPLDate(rs.getString("bplissuedate"));
									dto.setApplicantSchoolDistrict(rs.getString("schooldistrict"));
									dto.setApplicantSchoolTaluka(rs.getString("schooltaluka"));
								}

							}

						}
					}

					callable.getMoreResults();
					try (ResultSet rs = callable.getResultSet();) {
						ArrayList<DocumentEntity> documentList = new ArrayList<DocumentEntity>();
						while (rs.next()) {
							DocumentEntity docObj = new DocumentEntity();
							docObj.setType(rs.getString("document_type"));
							docObj.setPath(rs.getString("document_path"));
							/* --images-- */
							System.out.println(rs.getString("document_type"));
							if (rs.getString("document_type").equalsIgnoreCase("domicileCertificate")) {
								dto.setDomicileCertificateImagePath(rs.getString("document_path"));
								dto.setDomicileRadio(rs.getString("rip_flag"));
							} else if (rs.getString("document_type").equalsIgnoreCase("applicantCasteCertificate")) {
								dto.setCasteCertificateImagePath(rs.getString("document_path"));
								dto.setRtsflagcastecertificate(rs.getString("rts_flag"));
							} else if (rs.getString("document_type").equalsIgnoreCase("FatherIncomeCertificate")) {
								dto.setFatherIncomeCertificateImagePath(rs.getString("document_path"));
								dto.setFatherRadio(rs.getString("rip_flag"));
							} else if (rs.getString("document_type").equalsIgnoreCase("MotherIncomeCertificate")) {
								dto.setMotherIncomeCertificateImagePath(rs.getString("document_path"));
								dto.setMotherRadio(rs.getString("rip_flag"));
							} else if (rs.getString("document_type").equalsIgnoreCase("SSCCertificate")) {
								System.out.println("Doc :: " + rs.getString("document_type"));
								dto.setSsccertificateImagePath(rs.getString("document_path"));
								dto.setSscCerRadio(rs.getString("rip_flag"));
							} else if (rs.getString("document_type").equalsIgnoreCase("HSCCertificate")) {
								dto.setHsccertificateImagePath(rs.getString("document_path"));
								dto.setHscCerRadio(rs.getString("rip_flag"));
							} else if (rs.getString("document_type").equalsIgnoreCase("CasteValidityCertificate")) {
								dto.setCasteValiityCertificateImagePath(rs.getString("document_path"));
								dto.setCasteValRadio(rs.getString("rip_flag"));
							}
							/*-- 18-June-2017 --*/

							else if (rs.getString("document_type").equalsIgnoreCase("familyIncomeCertificate")) {
								dto.setFamilyIncomeCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("ReaderCertificate")) {
								dto.setReaderCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("DisabilityCertificate")) {
								dto.setDisabilityCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("rationCard")) {
								dto.setRationCardImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("bplCard")) {
								dto.setBplCardImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("FatherOccupationCertificate")) {
								dto.setFatherOccupationCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("fatherDeathCertificate")) {
								dto.setFatherDeathCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("FatherExFreedomFighterCertificate")) {
								dto.setFatherExFreedomFighterCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("FatherExServiceMenCertificate")) {
								dto.setFatherExServiceMenCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("MotherOccupationCertificate")) {
								dto.setMotherOccupationCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("motherDeathCertificate")) {
								dto.setMotherDeathCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("MotherExFreedomFighterCertificate")) {
								dto.setMotherExFreedomFighterCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("MotherExServiceMenCertificate")) {
								dto.setMotherExServiceMenCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("guardianCertificate")) {
								dto.setGuardianCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("GurdianOccupationCertificate")) {
								dto.setGurdianOccupationCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("DHELetter")) {
								dto.setDheletterImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("JNUCertificate")) {
								dto.setJnucertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("admissionReceipt")) {
								dto.setAdmissionReceiptImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("guideLetter")) {
								dto.setGuideClgImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("bonafideCertificateClg")) {
								dto.setBonafideCertificateClgImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("SSCTCCertificate")) {
								dto.setSscTclCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("HSCTCCertificate")) {
								dto.setHscTclCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("HSCGapCertificate")) {
								dto.setHscgapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("tdiplomaGapCertificate")) {
								dto.setTdiplomaGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("FDGapCertificate")) {
								dto.setFdgapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("FirstGapCertificate")) {
								dto.setfGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("LastExamCerForFirstYear")) {
								dto.setLastExamCerForFirstYearImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("SecondGapCertificate")) {
								dto.setsGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("LastExamCerForSecondYear")) {
								dto.setLastExamCerForSecondYearImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("ThirdGapCertificate")) {
								dto.settGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("LastExamCerForThirdYear")) {
								dto.setLastExamCerForThirdYearImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("GrGapCertificate")) {
								dto.setGrGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("LastExamCerForGraduationYear")) {
								dto.setLastExamCerForGraduationYearImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("PGGapCertificate")) {
								dto.setPgGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("LastExamCerForPGraduationYear")) {
								dto.setLastExamCerForPGraduationYearImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("lastExamMarksheetCertificate")) {
								dto.setMarksheetCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("HostelCertificate")) {
								dto.setHostelCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("WardenNOCCertificate")) {
								dto.setWardenNOCCertificateImagePath(rs.getString("document_path"));
							}

							/*
							 * else
							 * if(rs.getString("document_type").equalsIgnoreCase
							 * ("11thCertificate")) {
							 * dto.setEleventhCertificateImagePath(rs.getString(
							 * "document_path")); } else
							 * if(rs.getString("document_type").equalsIgnoreCase
							 * ("11thGapCertificate")) {
							 * dto.setEleventhGapCertificateImagePath(rs.
							 * getString("document_path")); }
							 */
							else if (rs.getString("document_type").equalsIgnoreCase("Certificate_11th")) {
								dto.setEleventhCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("Gap_Certificate_11th")) {
								dto.setEleventhGapCertificateImagePath(rs.getString("document_path"));
							} else if (rs.getString("document_type").equalsIgnoreCase("tdiplomaCertificate")) {
								dto.setTdiplomaCertificateImagePath(rs.getString("document_path"));
							}

							documentList.add(docObj);
						}
						dto.setDocumentList(documentList);
					}

					callable.getMoreResults();
					try (ResultSet rs = callable.getResultSet();) {
						while (rs.next()) {
							dto.setIncome_is_rts_seeded_data(rs.getString("income_is_rts_seeded_data"));
							dto.setIncome_verification_status(rs.getString("income_verification_status"));
							dto.setDomicile_is_rts_seeded_data(rs.getString("domicile_is_rts_seeded_data"));
							dto.setDomicile_verification_status(rs.getString("domicile_verification_status"));
							dto.setDisaility_is_rts_seeded_data(rs.getString("disaility_is_rts_seeded_data"));
							dto.setDisaility_verification_status(rs.getString("disaility_verification_status"));
							dto.setCaste_is_rts_seeded_data(rs.getString("caste_is_rts_seeded_data"));
							dto.setCaste_verification_status(rs.getString("caste_verification_status"));
							dto.setSsc_verification_status(rs.getString("ssc_verification_status"));
							dto.setHsc_verification_status(rs.getString("hsc_verification_status"));
							dto.setCval_verification_status(rs.getString("cval_verification_status"));

						}
					}
					
					/**
					 * @author swarnadip.ghosh
					 * added for get attendance data from dbt_attendance_dtl
					 */
					callable.getMoreResults();
					try (ResultSet rs = callable.getResultSet();) {
						while (rs.next()) {
							dto.setAttendance(rs.getString("attendance_percent"));
						}
					}
					
					listData.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listData;
	}

	
	//change on 7th July for Attendance schemes.
	public int setAttendanceDetails(String appId, String user_index, String attendance_percent){
		ResultSet rs = null;
		int count = 0;
		String sql = ""; 
		String attendanceQuery = "";
		int status = -1;
		
		try{
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());			
			sql = databaseQ.get_check_AttendanceDetails();
			
			try(Connection conn = ds.getConnection();) {
				conn.setAutoCommit(false);
				try (PreparedStatement ps = conn.prepareStatement(sql);){
					ps.setString(1, appId);
					rs = ps.executeQuery();
					
					while(rs.next()){
						count = rs.getInt(1);
					}
					
					if(count == 0){
						//insert
						attendanceQuery = databaseQ.getInsertAttendanceDetails();
						try (PreparedStatement ps1 = conn.prepareStatement(attendanceQuery);){
							ps1.setString(1, appId);
							ps1.setString(2, user_index);
							ps1.setString(3, attendance_percent);
							status = ps1.executeUpdate();
							
							if(status > 0) {
								conn.commit();
								status = 1;
							}
						}
					}else{
						//update
						attendanceQuery = databaseQ.getUpdateAttendanceDetails();
						try (PreparedStatement ps1 = conn.prepareStatement(attendanceQuery);){
							ps1.setString(1, attendance_percent);
							ps1.setString(2, appId);
							ps1.setString(3, user_index);
							status = ps1.executeUpdate();
							
							if(status > 0) {
								conn.commit();
								status = 1;
							}
						}
					}
					
				}
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return status;
	}
	
	//change on 9th July
	public int updateClerkStatus(String appId, String regNo, String authLvl, String schemeid, String username,
			String action, String comment, int verify_status, String verify_Remarks, String attendance_percent, String user_index) {
		
		int updateStatus = -1;
		try {
			/*
			 * DateFormat dateFormat = new SimpleDateFormat(
			 * "yyyy-mm-dd HH:mm:ss"); Date date = new Date();
			 * System.out.println(dateFormat.format(date));
			 */

			//change on 11th July
			if("35".equalsIgnoreCase(schemeid) || "22".equalsIgnoreCase(schemeid) || "24".equalsIgnoreCase(schemeid)){
				updateStatus = setAttendanceDetails(appId, user_index, attendance_percent);
				if(updateStatus == -1){
					System.out.println("Attendance Not Updated Successfully !!");
					return updateStatus;
				}
			}
			
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			String sql = databaseQ.getUpdateApplicationStatus();
			try(Connection conn = ds.getConnection();) {

				/*
				 * ps = conn.prepareStatement(
				 * "update esc_application_approver_dashboard_dtl set app_status = 'COMPLETED' "
				 * + "where app_id = '"+appId+"' and ibps_registration_no = '"
				 * +regNo+"' and authority_level = '"+authLvl+"';");
				 */
				int approverUpdateStatus = -1;
				conn.setAutoCommit(false);
				try (PreparedStatement ps = conn.prepareStatement(sql);){
					ps.setString(1, action);
					ps.setString(2, comment);
					ps.setInt(3, verify_status);
					ps.setString(4, regNo);
					ps.setString(5, appId);
					ps.setString(6, schemeid);
					ps.setString(7, authLvl);
					approverUpdateStatus = ps.executeUpdate();
					
					//Add remarks if verify status = 1
					if(verify_status == 1) {
						if(approverUpdateStatus > 0) {
							
							//Check whether remarks row for application already exist or not. 
							//If exit then update else insert new row.
							int remarksUpdateStatus = -1;
							String checkRemarks_Query = databaseQ.get_CheckApp_VerifyRemarks();
							try (PreparedStatement ps1 = conn.prepareStatement(checkRemarks_Query);){
								ps1.setString(1, appId);
								ResultSet rs = ps1.executeQuery();
	
								//Convert Verify Remarks to Json Object and get all remarks
								JSONObject remarksObj = new JSONObject(verify_Remarks);
								String insertRemarks_Query = "";

								
								int count = 0;
								while(rs.next()){
									count = rs.getInt(1);
								}
								
								if (count == 0) {
									insertRemarks_Query = databaseQ.get_InsertCheckApp_VerifyRemarks();
								}
								else {
									insertRemarks_Query = databaseQ.get_UpdateApp_VerifyRemarks();
								}
								
								try (PreparedStatement ps2 = conn.prepareStatement(insertRemarks_Query);){
									ps2.setString(1, remarksObj.getString("PersonalDetails_VerifyRemarks"));
									ps2.setString(2, remarksObj.getString("IncomeDetails_VerifyRemarks"));
									ps2.setString(3, remarksObj.getString("PersonalEligibilityDetails_VerifyRemarks"));
									ps2.setString(4, remarksObj.getString("SpouseDetails_VerifyRemarks"));
									ps2.setString(5, remarksObj.getString("CasteDetails_VerifyRemarks"));
									ps2.setString(6, remarksObj.getString("ParentsDetails_VerifyRemarks"));
									ps2.setString(7, remarksObj.getString("CourseDetails_VerifyRemarks"));
									ps2.setString(8, remarksObj.getString("CasteValidityDetails_VerifyRemarks"));
									ps2.setString(9, remarksObj.getString("EducationDetails_VerifyRemarks"));
									ps2.setString(10, remarksObj.getString("SchoolDetails_VerifyRemarks"));
									ps2.setString(11, remarksObj.getString("HostelDetails_VerifyRemarks"));
									ps2.setString(12, appId);

									remarksUpdateStatus = ps2.executeUpdate();
								}
								
								if(remarksUpdateStatus > 0) {
									conn.commit();
									updateStatus = 1;
								}
							}
						}
					}
					else if(verify_status == 0) {

						if(approverUpdateStatus > 0) {
							
							//Check whether remarks row for application already exist or not. 
							//If exit then update
							int remarksUpdateStatus = -1;
							String checkRemarks_Query = databaseQ.get_CheckApp_VerifyRemarks();
							try (PreparedStatement ps1 = conn.prepareStatement(checkRemarks_Query);){
								ps1.setString(1, appId);
								ResultSet rs = ps1.executeQuery();
	
								//Convert Verify Remarks to Json Object and get all remarks
								JSONObject remarksObj = new JSONObject(verify_Remarks);
								String insertRemarks_Query = "";
								
								int count = 0;
								while(rs.next()){
									count = rs.getInt(1);
								}
								
								if (count > 0) {
									insertRemarks_Query = databaseQ.get_UpdateApp_VerifyRemarks();

									try (PreparedStatement ps2 = conn.prepareStatement(insertRemarks_Query);){
										ps2.setString(1, null);
										ps2.setString(2, null);
										ps2.setString(3, null);
										ps2.setString(4, null);
										ps2.setString(5, null);
										ps2.setString(6, null);
										ps2.setString(7, null);
										ps2.setString(8, null);
										ps2.setString(9, null);
										ps2.setString(10, null);
										ps2.setString(11, null);
										ps2.setString(12, appId);

										remarksUpdateStatus = ps2.executeUpdate();
									}
									
									if(remarksUpdateStatus > 0) {
										conn.commit();
										updateStatus = 1;
									}
								}
								else {
									conn.commit();
									updateStatus = 1;
								}	
							}
						}
					}
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return updateStatus;
	}

	/**
	 * 
	 * @param userName
	 * @param userPass
	 * @return
	 */
	// using mysql
	public String checkUserIDinDB(String userid) {
		// TODO Auto-generated method stub
		String sql = null;
		String isUserExists = null;
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			sql = databaseQ.getCheckUserId();
			try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);){
				ps.setString(1, userid);

				System.out.println(ps.toString());

				try(ResultSet rs = ps.executeQuery();){

			if (rs.next()) {
				isUserExists = "exists";
			}
			// else
			// isUserExists = "success";
				}
			}

		} catch (Exception sec) {
			sec.printStackTrace();
		}

		return isUserExists;

	}

	public String checkUserIDinDBPostgree(String userid) {
		// TODO Auto-generated method stub
		String sql = null;
		String isUserExists = null;
		sql = "select username from dbt_user_reg_table where username = ?;";
			try(Connection conn = PGAdmin_DB_Connection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);){
				ps.setString(1, userid);

				try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
						isUserExists = "exists";
					}
				}
			}
		catch (SQLException sec) {
			sec.printStackTrace();
		} 
		return isUserExists;

	}


	/**
	 * This method is used to check user input Aadhaar No. in database.
	 * Returns "exists" if there is a match
	 * 
	 * @param aadhaarid
	 * @return isUserExists
	 */
	public String checkAadhaarinDB(String aadhaarid) {
		String sql = null;
		String isUserExists = null;
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			sql = databaseQ.getCheckAadhaar();
			try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
				ps.setString(1, aadhaarid);
				try (ResultSet rs = ps.executeQuery();) {

					if (rs.next()) {
						isUserExists = "exists";
					}
				}
			}
		} catch (Exception sec) {
			sec.printStackTrace();
		}

		return isUserExists;
	}

	public HashMap checkEidinDB(AadhaarRegistrationEntity eidEntity, HashMap resultMap) {
		// TODO Auto-generated method stub
		String sql = null;

		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			sql = databaseQ.getCheckEID();
				try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql);){
						ps.setString(1, eidEntity.getEid_pce_no());
		
						try(ResultSet rs = ps.executeQuery();){
		
							if (rs.next()) {
				
								resultMap.put("resultCode", -1);
								resultMap.put("resultDetails", "Record already exist with same user name");
							} else {
								resultMap.put("resultCode", 0);
								resultMap.put("resultDetails", "No Record Found");
							}
						}
				}

		} catch (Exception sec) {
			sec.printStackTrace();
		} 
		return resultMap;

	}

	

	public ArrayList<Set_Get_Data_PostLogIn> getCategoryDetails() throws NamingException {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {
			DataSourceConnect dsc = new DataSourceConnect();
			try(Connection conn = dsc.getDataSourceConnection();

			PreparedStatement ps = conn.prepareStatement(databaseQ.getCategoryDetails());){


				try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
						
						if("Both".equalsIgnoreCase(rs.getString("schemetype"))){
								
							//For PreMatric

							dto.setScheme(rs.getString("SchemeName"));
							dto.setDepartment(rs.getString("Department"));
							dto.setSchemeId(rs.getInt("schemeid"));
							dto.setSchemeType(rs.getString("schemetype"));
							dto.setSchemeSubType("PreMatric");
							dto.setStatus(rs.getString("SchemeStatus"));
							listData.add(dto);
							
							//For PostMatric
							dto = new Set_Get_Data_PostLogIn();
							dto.setScheme(rs.getString("SchemeName"));
							dto.setDepartment(rs.getString("Department"));
							dto.setSchemeId(rs.getInt("schemeid"));
							dto.setSchemeType(rs.getString("schemetype"));
							dto.setSchemeSubType("PostMatric");
							dto.setStatus(rs.getString("SchemeStatus"));
							listData.add(dto);
							
						}
						else{
							dto.setScheme(rs.getString("SchemeName"));
							dto.setDepartment(rs.getString("Department"));
							dto.setSchemeId(rs.getInt("schemeid"));
							dto.setSchemeType(rs.getString("schemetype"));
							dto.setSchemeSubType(rs.getString("schemetype"));
							dto.setStatus(rs.getString("SchemeStatus"));


							listData.add(dto);
						}
						
					}
				}
			}
		} catch (SQLException sec) {
			sec.printStackTrace();
		}

		return listData;
	}

	public ArrayList<Set_Get_Data_PostLogIn> getDeptDetails(String dept) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();

				PreparedStatement ps = conn.prepareStatement(databaseQ.getDepartmentDetails());){
				ps.setString(1, dept);
				
				try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
						if("Both".equalsIgnoreCase(rs.getString("schemetype"))){
							
							//For PreMatric

							dto.setScheme(rs.getString("SchemeName"));
							dto.setDepartment(rs.getString("Department"));
							dto.setSchemeId(rs.getInt("schemeid"));
							dto.setSchemeType(rs.getString("schemetype"));
							dto.setSchemeSubType("PreMatric");
							dto.setStatus(rs.getString("SchemeStatus"));
							listData.add(dto);
							
							//For PostMatric
							dto = new Set_Get_Data_PostLogIn();
							dto.setScheme(rs.getString("SchemeName"));
							dto.setDepartment(rs.getString("Department"));
							dto.setSchemeId(rs.getInt("schemeid"));
							dto.setSchemeType(rs.getString("schemetype"));
							dto.setSchemeSubType("PostMatric");
							dto.setStatus(rs.getString("SchemeStatus"));
							listData.add(dto);
							
						}
						else{
							dto.setScheme(rs.getString("SchemeName"));
							dto.setDepartment(rs.getString("Department"));
							dto.setSchemeId(rs.getInt("schemeid"));
							dto.setSchemeType(rs.getString("schemetype"));
							dto.setSchemeSubType(rs.getString("schemetype"));
							dto.setStatus(rs.getString("SchemeStatus"));


							listData.add(dto);
						}
					}
				}
			}
		} catch (Exception sec) {
			sec.printStackTrace();
		} 
		return listData;
	}

	/*****************************************************************************************************************/


	public ArrayList<Set_Get_Data_PendingCases> getPendingCasesData(String userName) throws Exception {
		ArrayList<Set_Get_Data_PendingCases> listData = new ArrayList<Set_Get_Data_PendingCases>();
		try {

			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();){
				ps = conn.prepareStatement(databaseQ.getUserPendingCases());
				ps.setString(1, userName);
				try(ResultSet rs = ps.executeQuery();){

	
					while (rs.next()) {
						Set_Get_Data_PendingCases dto = new Set_Get_Data_PendingCases();
		
						dto.setAppID(rs.getString("app_id"));
						dto.setAppStatus(rs.getString("app_status"));
						dto.setSchemeName(rs.getString("SchemeName"));
						dto.setSchemeId(rs.getString("SchemeId"));
						dto.setSchemeType(rs.getString("scheme_type"));
						dto.setAppServiceCategory(rs.getString("app_serviceCategorty"));
						dto.setAppSubmitDate(rs.getString("app_submit_datetime"));
						dto.setIbpsRegNo(rs.getString("ibps_registration_no"));
		
						listData.add(dto);
					}
				}
			}
		} catch (SQLException sec) {
			sec.printStackTrace();
		}

		return listData;
	}

	public ArrayList<String> getInstituteListFromMaster(String type, String action) {
		// TODO Auto-generated method stub
		ArrayList<String> listData = new ArrayList<String>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(databaseQ.instituteListFromMaster());){
				ps.setString(1, type);
				ps.setString(2, action);
				try(ResultSet rs = ps.executeQuery();){
	
						while (rs.next()) {
							Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
							listData.add(rs.getString("value"));
						}
				}
			}
		} catch (Exception sec) {
			sec.printStackTrace();
		} 

		return listData;
	}

	/**
	 * Created by Swarnadip Ghosh
	 * 
	 * @param userName
	 * @return
	 */
	public ArrayList<Set_Get_Data_PostLogIn> appTracking(String userName, String appid) throws Exception {
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(databaseQ.getApplicationStatus());){
				ps.setString(1, userName);
				ps.setString(2, appid);

				try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
		
						dto.setAppid(rs.getString("app_id"));
						dto.setScheme(rs.getString("SchemeName"));
						dto.setSchemeId(rs.getInt("SchemeId"));
						dto.setSchemeType(rs.getString("scheme_type"));
						dto.setCategory(rs.getString("app_serviceCategorty"));
						dto.setDateOfApp(rs.getString("app_submit_date"));
						dto.setStatus(rs.getString("app_status"));
		
						listData.add(dto);
					}
				}
			}
		} catch (SQLException sec) {
			sec.printStackTrace();
		} 

		return listData;
	}

	public ArrayList<DocumentEntity> getUserDocuments(String userName) throws IOException {

		ArrayList<DocumentEntity> documentlist = new ArrayList<DocumentEntity>();
		try {
			
			String location = "";
			ImageEncoderUtility encode=new ImageEncoderUtility();
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(databaseQ.getUserRepositoryDetails());){
					ps.setString(1, userName);
					try(ResultSet rs = ps.executeQuery();){

					while (rs.next()) {
						System.out.println("Path>>"+rs.getString("doc_path"));
						DocumentEntity docEnt = new DocumentEntity();
						docEnt.setDocId(rs.getInt("doc_id"));
						docEnt.setPath(rs.getString("doc_path"));
						/** Read Document Content **/
						String filePath = rs.getString("doc_path");

						location = filePath;
						/*if(filePath.contains(".pdf")){*/
							String retByteStr = encode.encodeFileToBase64Binary(filePath);
							location=retByteStr;							
						/*}else{
							BufferedImage img = ImageIO.read(new File(location));							
							String retByteStr = encode.encodeToString(img, "jpg");
							location=retByteStr;
						}*/
						
						
						docEnt.setDocEncStr(location);
						
						docEnt.setNumber(rs.getString("doc_num"));
						docEnt.setRegistrationDate(rs.getString("reg_date"));
						docEnt.setRegistrationAuthority(rs.getString("reg_authority"));
						docEnt.setDocumentId(rs.getInt("document_id"));
						docEnt.setType(rs.getString("docType"));
						docEnt.setDocName(rs.getString("docName"));
						documentlist.add(docEnt);
					}
					}
			}
		} catch (Exception sec) {
			sec.printStackTrace();
		}

		return documentlist;
	}
	/* Changed by swarnadip : 25-Jul-2017 */
	public ArrayList<Set_Get_Data_PostLogIn> getWorkFlowName(String schemeid, String stagecode) {
		// TODO Auto-generated method stub
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());

			Integer stageCodeFront = Integer.valueOf(stagecode);

			try (Connection conn = ds.getConnection();

					PreparedStatement ps = conn.prepareStatement(databaseQ.getApplicationWorkflow());) {
				// ps.setString(1, schemeid);

				try (ResultSet rs = ps.executeQuery();) {

					while (rs.next()) {
						Integer stageCodeDB = Integer.valueOf(rs.getString("stage_code"));

						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();
						System.out.println("stagecode : "+stagecode);
						/* When stage code = 4,5, need to skip stage 3  */
						if (stagecode.equals("4") || stagecode.equals("5")) {
							if (rs.getString("stage_code").equals("3")) {
								continue;
							}
							dto.setSchemeId(rs.getInt("scheme_id"));
							dto.setStage_code(rs.getString("stage_code"));
							dto.setStage_name(rs.getString("stage_name"));
						}
						/* When stage code = 5, need to skip stage 3,5  */
						else if (stagecode.equals("6") || stagecode.equals("7")) {
							if (rs.getString("stage_code").equals("3") || rs.getString("stage_code").equals("5")) {
								continue;
							}
							dto.setSchemeId(rs.getInt("scheme_id"));
							dto.setStage_code(rs.getString("stage_code"));
							dto.setStage_name(rs.getString("stage_name"));
						}else {
							dto.setSchemeId(rs.getInt("scheme_id"));
							dto.setStage_code(rs.getString("stage_code"));
							dto.setStage_name(rs.getString("stage_name"));
						}
						/* Show stage code till as coming from front end  */
						if (stageCodeDB <= stageCodeFront)
							listData.add(dto);
					}
				}
			}
		} catch (Exception sec) {
			sec.printStackTrace();
		}
		return listData;
	}
	
	
	public ArrayList<Set_Get_Data_PendingCases> getResidentDraftData(String userName) throws Exception {
		ArrayList<Set_Get_Data_PendingCases> listData = new ArrayList<Set_Get_Data_PendingCases>();
		try {

			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());

			try(Connection conn = ds.getConnection();){

				try(PreparedStatement ps = conn.prepareStatement(databaseQ.getUserDraftsCases());){
				ps.setString(1, userName);
	
						try(ResultSet rs = ps.executeQuery();){
			
							while (rs.next()) {
								Set_Get_Data_PendingCases dto = new Set_Get_Data_PendingCases();
				
								dto.setAppID(rs.getString("app_id"));
								dto.setSchemeName(rs.getString("scheme_name"));
								dto.setSchemeId(rs.getString("scheme_id"));
								dto.setSchemeType(rs.getString("scheme_type"));
								dto.setAppServiceCategory(rs.getString("app_serviceCategory"));
								dto.setAppSubmitDate(rs.getString("app_submit_datetime"));
				
								listData.add(dto);
							}
						}
				}
			}
		} catch (SQLException sec) {
			sec.printStackTrace();
		} 

		return listData;
	}
	
	public String setCasteDataINDoc(String appid, String doctype, String docPath, String userID, String docFlag,String rtsFlag) 
	{
		// TODO Auto-generated method stub
		String data = null;
		try {

			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try(Connection conn = ds.getConnection();
					PreparedStatement ps = conn.prepareStatement(databaseQ.getSetRTSDocDetails());){
					ps.setString(1, appid);
					ps.setString(2, doctype);
					ps.setString(3, docPath);
					ps.setString(4, userID);
					ps.setString(5, rtsFlag);
		
					int count = ps.executeUpdate();
		
					if (count > 0)
						data = "Success";
					else
						data = "Faliure";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return data;
	}
	
	/**
	 * This method returns all the matched results for given email id.
	 * @param userEmail
	 * @return JsonObject
	 */
	public JsonObject checkUserEmailInDB(String userEmail) {
		
		String sql = null;
		JsonObject resultObj = new JsonObject();
		try {
			Context = new javax.naming.InitialContext();
	        ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	    	sql = databaseQ.getCheckUserEmailId();
	        try(Connection conn = ds.getConnection();
	        	PreparedStatement ps = conn.prepareStatement(sql);){
				ps.setString(1, userEmail);
				try(ResultSet rs = ps.executeQuery();){
					if(rs.next()) {
						resultObj.addProperty("Status", "Y");
						JsonArray matchUserList = new JsonArray();
						while (rs.next()) {
							JsonObject matchedUserObj = new JsonObject();
							matchedUserObj.addProperty("UserId", rs.getString("user_id"));
							matchedUserObj.addProperty("MobileNo", rs.getString("mobile_no"));
							matchedUserObj.addProperty("EmailId",  rs.getString("email_id"));
							matchedUserObj.addProperty("DOB",  rs.getString("dob"));
							matchUserList.add(matchedUserObj);
						}
						resultObj.add("MatchedUserList", matchUserList);
					}
					else
						resultObj.addProperty("Status", "N");
				}
	        }
		
		} catch(Exception sec) {
			sec.printStackTrace();
		}
		
		return resultObj;
	}

	/**
	 * This method is used to generate OTP of given length
	 * @param len
	 * @return
	 */
	public String OTP(int len) {
        
        // Using numeric values
        String numbers = "0123456789";
 
        // Using random method
        Random rndm_method = new Random();
 
        char[] otpArr = new char[len];
        String otp = "";
 
        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
        	otpArr[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        		//System.out.println("New Digit>>"+otpArr[i]);
        	otp +=  otpArr[i];
        }
        return otp;
    }
	
	/**
	 * This method is used to send the OTP to to user registerd emailId and mobile no.
	 * @param userId
	 * @param mobileNo
	 * @param emailId
	 * @param otpId
	 * @return status
	 */
	public String sendVerificationOTP(String userId, String mobileNo, String emailId, int otpId) {
		
		String status = "N";
		
		/**Generate OTP**/
		String generatedOTP = OTP(6);
		
		/**Save OTP to user table along with timestamp for expiry check**/
		String sql = null;

		try {
			Context = new javax.naming.InitialContext();
	        ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	        sql = databaseQ.getSetVerificationOTPDetails();
	        try(Connection conn = ds.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, userId);
			ps.setInt(2, otpId);
			ps.setString(3, generatedOTP);
			ps.setInt(4, otpId);
			int insertCount = ps.executeUpdate();
				
			if(insertCount > 0) {
				status = "Y";
			
				/**Send SMS & Email to User for OTP**/
				boolean smsStatus = false;
				boolean emailStatus = false;
				if(!"".equalsIgnoreCase(mobileNo)) {
					SendMessageUtility smu = new SendMessageUtility();
	   			 	String smsMessage = generatedOTP + " is the Onetime Password (OTP) for your transaction over DBT Portal."
	   			 			+ " This is usabale once & valid for 30 min from the request. PLS DO NOT SHARE WITH ANYONE.";
	   			 	smsStatus = smu.sendMessage(mobileNo, smsMessage, "1", "1", "ForgotPassword", "", "");
				}
				
				if(!"".equalsIgnoreCase(emailId)) {
					SendEmailUtility emu = new SendEmailUtility();
					String emailSubject = "Transaction Alert";
	   			 	String emailMessage = generatedOTP + " is the Onetime Password (OTP) for your transaction over DBT Portal."
	   			 			+ " This is usabale once & valid for 30 min from the request. PLS DO NOT SHARE WITH ANYONE.";
	   			 	emailStatus = emu.sendMessage("1", InitConfigProp.getProperty("dbtDefaultEmail"), emailId, emailSubject,emailMessage);
				}
				
				System.out.println("Forgot Password SMS Status >>"+smsStatus +", Email Status >>"+emailStatus);
			}	
			else
				status = "N";
	        }
	
		} catch(Exception sec) {
			sec.printStackTrace();
		}
		
		return status;	
	}

	/**
	 * This method returns all the matched results for given input.
	 * @param userInput
	 * @return JsonObject
	 */
	public HashMap<String, Object> checkUserDetails(String jsonInput, HashMap<String, Object> resultMap) {
		
		String sql = null;
		JsonObject resultObj = new JsonObject();
		DateFormatConverter dateConverter = new DateFormatConverter();
		
		try {
			//Parse the json input
			JSONObject jObj = new JSONObject(jsonInput);
			String userInput = (String)jObj.getString("UserInput");
			String userDOB = (String)jObj.getString("UserDOB");
			String userId = (String)jObj.getString("UserId");
			int type = (int)jObj.getInt("Type");
			
			// Validation Check for blank or null values 
			switch (type) {
				case 10:	//In case on email is passed
					if("".equalsIgnoreCase(userInput) || userInput == null) {
						resultMap.put("resultCode", "-3");
						return resultMap;
					}
					sql = databaseQ.getCheckUserEmailId();
					break;
				case 11:	//In case on email & DOB is passed
					if("".equalsIgnoreCase(userInput) || userInput == null || "".equalsIgnoreCase(userDOB)
					|| userDOB == null) {
						resultMap.put("resultCode", "-3");
						return resultMap;
					}
					sql = databaseQ.getCheckUserEmailId_DOB();
					break;
				case 12: //In case on email, DOB & UserId is passed
					if("".equalsIgnoreCase(userInput) || userInput == null || "".equalsIgnoreCase(userDOB)
					|| userDOB == null || "".equalsIgnoreCase(userId) || userId == null) {
						resultMap.put("resultCode", "-3");
						return resultMap;
					}
					sql = databaseQ.getCheckUserEmailId_DOB_UserId();
					break;
				case 20: //In case on mobile is passed
					if("".equalsIgnoreCase(userInput) || userInput == null) {
						resultMap.put("resultCode", "-3");
						return resultMap;
					}
					sql = databaseQ.getCheckUserMobile(); 
					break;
				case 21: //In case on mobile & DOB is passed
					if("".equalsIgnoreCase(userInput) || userInput == null || "".equalsIgnoreCase(userDOB)
					|| userDOB == null) {
						resultMap.put("resultCode", "-3");
						return resultMap;
					}
					sql = databaseQ.getCheckUserMobile_DOB();
					break;
				case 22: //In case on mobile, DOB & UserId is passed
					if("".equalsIgnoreCase(userInput) || userInput == null || "".equalsIgnoreCase(userDOB)
					|| userDOB == null || "".equalsIgnoreCase(userId) || userId == null) {
						resultMap.put("resultCode", "-3");
						return resultMap;
					}
					sql = databaseQ.getCheckUserMobile_DOB_UserId();
					break;
				default:
					resultMap.put("resultCode", "-2");
					return resultMap;	
			}
			
			
			Context = new javax.naming.InitialContext();
	        ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
	        try(Connection conn = ds.getConnection();

				PreparedStatement ps = conn.prepareStatement(sql);){
	        	
	        	//Set prepared statement according to user input type
	        	if(type == 10) {
	        		ps.setString(1, userInput);
	        	}
	        	else if(type == 11) {
	        		ps.setString(1, userInput);
	        		userDOB = dateConverter.dateFormatConverter(userDOB, "dd/mm/yyyy", "yyyy-mm-dd");
	        		ps.setString(2, userDOB);
	        	}
	        	else if(type == 12) {
	        		ps.setString(1, userInput);
	        		userDOB = dateConverter.dateFormatConverter(userDOB, "dd/mm/yyyy", "yyyy-mm-dd");
	        		ps.setString(2, userDOB);
	        		ps.setString(3, userId);
	        	}
				else if(type == 20) {
					ps.setString(1, userInput);
				}
				else if(type == 21) {
					ps.setString(1, userInput);
					userDOB = dateConverter.dateFormatConverter(userDOB, "dd/mm/yyyy", "yyyy-mm-dd");
	        		ps.setString(2, userDOB);
				}
				else if(type == 22) {
					ps.setString(1, userInput);
					userDOB = dateConverter.dateFormatConverter(userDOB, "dd/mm/yyyy", "yyyy-mm-dd");
	        		ps.setString(2, userDOB);
	        		ps.setString(3, userId);
				}
	        	
				try(ResultSet rs = ps.executeQuery();){
					
					if(rs.isBeforeFirst()) {
						resultMap.put("resultCode", "0");
						resultMap.put("Status", "Y");
						JsonArray matchUserList = new JsonArray();
						
						//Parse all matched rows
						while (rs.next()) {
							JsonObject matchedUserObj = new JsonObject();
							matchedUserObj.addProperty("UserId", rs.getString("user_id"));
							matchedUserObj.addProperty("MobileNo", rs.getString("mobile_no"));
							matchedUserObj.addProperty("EmailId",  rs.getString("email_id"));
							matchedUserObj.addProperty("DOB",  rs.getString("dob"));
							matchUserList.add(matchedUserObj);
						}
						resultMap.put("MatchedUserList", matchUserList);
					}
					else {
						resultMap.put("resultCode", "0");
						resultMap.put("Status", "N");
					}
				}
	        }
	
		} catch(Exception sec) {
			sec.printStackTrace();
			resultMap.put("resultCode", "-1");
		}
		
		return resultMap;
	}
	
	public JsonObject verifyOTP(String userId, String otpType, String otpVal) {
		
		String sql = null;
		JsonObject resultObj = new JsonObject();
		try {
			Context = new javax.naming.InitialContext();
	        ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			sql = databaseQ.getVerifyOTP();

	      try( Connection conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, userId);
			ps.setString(2, otpType);
			try(ResultSet rs = ps.executeQuery();){
				
			if(rs.next()) {
				
				if(rs.getString("otp_value").equalsIgnoreCase(otpVal)) {
					resultObj.addProperty("VerifyStatus", "Y");
					sql = databaseQ.getOTPExpiry();

					try(PreparedStatement ps_sql = conn.prepareStatement(sql);){
						ps_sql.setString(1, rs.getString("otp_expiry_time"));
						try(ResultSet rs_sql = ps_sql.executeQuery();){
							if(rs_sql.next()) {
								resultObj.addProperty("ExpiryStatus", "N");
							}
							else {
								resultObj.addProperty("ExpiryStatus", "Y");
							}
						}
					}
				}
				else {
					resultObj.addProperty("VerifyStatus", "N");
				}
			}
			else
				resultObj.addProperty("VerifyStatus", "N");
			}
	      }
		} catch(Exception sec) {
			sec.printStackTrace();
		}
		
		
		return resultObj;
	}
	
	/**
	 * This method returns the all the user applied schemes.
	 * 
	 * @author Swarnadip Ghosh
	 * @since 2017-06-06
	 * @param userName
	 * @param resultMap 
	 * @return
	 */
	public HashMap<String, Object> getUserAppliedSchemeDetails(String userName,
			HashMap<String, Object> resultMap) throws Exception {
		
		ArrayList<Set_Get_Data_PostLogIn> listData = new ArrayList<Set_Get_Data_PostLogIn>();
		try {
			//Get Database Object
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try (Connection conn = ds.getConnection();
				//Get Prepared Statement with Query
				PreparedStatement ps = conn.prepareStatement(databaseQ.getUserAppliedSchemeDetails());) {
				ps.setString(1, userName);
				try (ResultSet rs = ps.executeQuery();) {

					while (rs.next()) {
						Set_Get_Data_PostLogIn dto = new Set_Get_Data_PostLogIn();

						dto.setAppid(rs.getString("app_id"));
						dto.setDateOfApp(rs.getString("app_submit_date"));
						dto.setUser_id(rs.getString("user_id"));
						dto.setPref_flag(rs.getString("pref_flag"));
						dto.setScheme(rs.getString("SchemeName"));
						dto.setSchemeId(Integer.parseInt(rs.getString("app_scheme_id")));
						listData.add(dto);
					}
					
					//Set Return Details
					resultMap.put("resultCode", 0);
					resultMap.put("AppliedSchemes", listData);
				}
			}
		} catch (SQLException sec) {
			sec.printStackTrace();
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", sec.getMessage());
		} catch (Exception ex) {
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", ex.getMessage());
		}

		return resultMap;
	}
	
	public ArrayList<String> updateUserAppliedSchemePreference(String app_id, String preference) throws NamingException {
		// TODO Auto-generated method stub
		String status = null;
		ArrayList<String> listData = new ArrayList<String>();
		try {
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			conn = ds.getConnection();

			ps = conn.prepareStatement(databaseQ.userAppliedSchemePreference());
			ps.setString(1, preference);
			ps.setString(2, app_id);
			int rs = ps.executeUpdate();
			if(rs > 0)
			{
				status = "success";
			}
			else
			{
				status = "faliure";
			}
			
			listData.add(status);

		} catch (SQLException sec) {
			sec.printStackTrace();
		} finally /* Mandatory Code */
		{
			try {
				conn.close();
				ps.close();
			} catch (SQLException sef) {
				sef.printStackTrace();
			}
		}

		return listData;
	
	}

	/**
	 * This method is used to get the all grouping option available to user if not done already.
	 * 
	 * @author Varun Saddi
	 * @since 2017-07-18
	 * @param resultMap
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> getSchemeGroupingOptions(HashMap<String, Object> resultMap) {
		
		try {
			//Define Color Options
			ArrayList<String> colorList = 
					new ArrayList<String>(Arrays.asList("blue", "green", "orange", "violet", "red"));
			
			/*Parse the user applied schemes and check if possibility of grouping is there or not*/
			//Iterate through Hashmap for applied scheme
			ArrayList<Set_Get_Data_PostLogIn> schemeList = new ArrayList<Set_Get_Data_PostLogIn>();
			JSONObject jsonObj = new JSONObject();
			schemeList = (ArrayList<Set_Get_Data_PostLogIn>) resultMap.get("AppliedSchemes");
			for (Set_Get_Data_PostLogIn scheme : schemeList) {
				/*Get the Scheme Id of all schemes that can be grouped with this scheme Id from Scheme Group Master */
				//Get Database Object
				Context = new javax.naming.InitialContext();
				ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
				try (Connection conn = ds.getConnection();
					//Get Prepared Statement with Query
					PreparedStatement ps = conn.prepareStatement(databaseQ.get_GroupingSchemesId_For_SchemeId());) {
					ps.setInt(1, scheme.getSchemeId());
					try (ResultSet rs = ps.executeQuery();) {
						String grouping_SchemesId = "NA";
						int groupingCount = 0;
						if(rs.isBeforeFirst()){
							while (rs.next()) {
								for (int i = 0; i < schemeList.size(); i++) {
									//If there is scheme with which there is grouping option available and the preference is not same for both the schemes
									if((schemeList.get(i).getSchemeId() == Integer.parseInt(rs.getString("grouped_scheme_id"))
											&& !schemeList.get(i).getPref_flag().equalsIgnoreCase(scheme.getPref_flag()))) {
										jsonObj.put(String.valueOf(scheme.getSchemeId()), schemeList.get(i).getSchemeId());
										
										//Set Grouping Schemes Id with comma separated
										if(groupingCount == 0)
											grouping_SchemesId += schemeList.get(i).getScheme();
										else
											grouping_SchemesId += "," + schemeList.get(i).getScheme();
										
										groupingCount++;
										
										if(scheme.getGroupingColor() == null) {
											//Set a color for scheme
											scheme.setGroupingColor(colorList.get(0));
											
											//Set the color for grouped scheme as well
											schemeList.get(i).setGroupingColor(colorList.get(0));
												//System.out.println(scheme.getAppid()+"==" + schemeList.get(i).getAppid() +">>"+colorList.get(0));
											//remove the color which is already set for others
											colorList.remove(0);	
										}
									}
								};
							}
							
							scheme.setGrouping_SchemesId(grouping_SchemesId);
						}
						else {
							scheme.setGrouping_SchemesId("NA");
						}
					}	
				}
			}
			
			//Set Return Details
			resultMap.put("resultCode", 0);
			resultMap.put("SchemesGroupingOptons", jsonObj.toString());
			
		} catch (SQLException sec) {
			sec.printStackTrace();
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", sec.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", ex.getMessage());
		}

		return resultMap;
	}

}
