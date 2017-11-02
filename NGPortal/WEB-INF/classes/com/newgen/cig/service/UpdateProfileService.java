/**
 * Provides the classes used to implement business/functional logic 
 * for all the different types of request from portal.
 */
package com.newgen.cig.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonElement;
import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.utility.AppConstants;
import com.newgen.utility.DateFormatConverter;

/**
 * <h1>ForgotPassword</h1>
 * <p>
 * This file covers all the business logic and validations implemented for Updating User Profile.
 * It returns the hashmap with result code and result details 
 * @author Varun Saddi
 * @version 1.0
 * @since   2017-07-16
 */
public class UpdateProfileService {

	InitialContext Context;
	DataSource ds;
	DatabaseQuery databaseQ = new DatabaseQuery();
	
	/**
	 * This method is used to update Non Aadhar user profile to Aadhar or existing Aadhar profile.
	 *  
	 * @param aadharRegistrationEntity_data
	 * @param userName
	 * @param resultMap
	 * @return HashMap
	 */
	public HashMap<String, Object> updateProfileUsingAadhar(AadhaarRegistrationEntity aadharRegistrationEntity_data,
			String userName, HashMap<String, Object> resultMap) {
		
		DateFormatConverter dateConverter = new DateFormatConverter();
		
		try {
			//Validation Check
			boolean resultVal = checkValidation(aadharRegistrationEntity_data);
			if (!resultVal) {
				resultMap.put("resultCode", -3);
				resultMap.put("resultDetails", "Invalid Input");
				return resultMap;
			}
			
			//Get Database COnnection Object
			Context = new javax.naming.InitialContext();
			ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
			try (Connection conn = ds.getConnection();) {
				conn.setAutoCommit(false);
				
				//Change DOB to Mysql Format
				String formatedDOB = dateConverter.dateFormatConverter(
						aadharRegistrationEntity_data.getDob().replaceAll("-", "/"), "dd/mm/yyyy", "yyyy-mm-dd");
				
				//Check Duplicate Entry for another registration
				String duplicate_RegistrationQuery = databaseQ.get_DuplicateCheck_For_UpdateProfile();
				try (PreparedStatement ps0 = conn.prepareStatement(duplicate_RegistrationQuery);) {
					ps0.setString(1, aadharRegistrationEntity_data.getName().trim().toLowerCase());
					ps0.setString(2, formatedDOB);
					ps0.setString(3, aadharRegistrationEntity_data.getMobile());
					ps0.setString(4, userName);
						System.out.println(ps0.toString() + "  :: validation check in aadhar registration service");
					try (ResultSet rs1 = ps0.executeQuery();) {
						if (rs1.next()) {
							resultMap.put("resultCode", -4);
							resultMap.put("resultDetails", "User already registered with similar details."
									+ " If this is not your account then please report this to helpdesk. ");
								System.out.println("User already registered with similar details. If this is not your account then please report this to helpdesk. Aadhaar No : " +aadharRegistrationEntity_data.getAuidno());
							return resultMap;
						}
					}
				}
				
				//Get Update Query
				String update_ProfileQuery = databaseQ.get_UpdateUserProfile_To_Aadhaar();
				
				//Set NPCI Details
				String npciStatus = "N";
				if ("Active".equalsIgnoreCase(aadharRegistrationEntity_data.getNpciStatus())) {
					npciStatus = "A";
				} else if ("Inactive".equalsIgnoreCase(aadharRegistrationEntity_data.getNpciStatus())) {
					npciStatus = "I";
				} else {
					npciStatus = "N";
				}
				
				//Update User Details in database and clear Non Aadhaar values
				int x = 1;
				try(PreparedStatement ps = conn.prepareStatement(update_ProfileQuery);){
					ps.setString(x++, aadharRegistrationEntity_data.getAuidno());
					ps.setString(x++, aadharRegistrationEntity_data.getName());
					ps.setString(x++, aadharRegistrationEntity_data.getCareoff());
					ps.setString(x++, formatedDOB);
					ps.setInt(x++, aadharRegistrationEntity_data.getAge());
					ps.setString(x++, aadharRegistrationEntity_data.getMobile());
					ps.setString(x++, aadharRegistrationEntity_data.getEmail());
					ps.setString(x++, aadharRegistrationEntity_data.getGender());
					ps.setString(x++, aadharRegistrationEntity_data.getHouse());
					ps.setString(x++, aadharRegistrationEntity_data.getStreet());
					ps.setString(x++, aadharRegistrationEntity_data.getVillagetowncity());
					ps.setString(x++, aadharRegistrationEntity_data.getPostoffice());
					ps.setString(x++, aadharRegistrationEntity_data.getLandmark());
					ps.setString(x++, aadharRegistrationEntity_data.getSubdistrict());
					ps.setString(x++, aadharRegistrationEntity_data.getDistrict());
					ps.setString(x++, aadharRegistrationEntity_data.getState());
					ps.setInt(x++, aadharRegistrationEntity_data.getPincode());
					ps.setString(x++, null); //Same Correspondence Address
					ps.setString(x++, null); //Correspondence House
					ps.setString(x++, null); //Correspondence Street
					ps.setString(x++, null); //Correspondence Village
					ps.setString(x++, null); //Correspondence Locality
					ps.setString(x++, null); //Correspondence Post Office
					ps.setString(x++, null); //Correspondence Landmark
					ps.setString(x++, null); //Correspondence Sub District
					ps.setString(x++, null); //Correspondence District
					ps.setString(x++, null); //Correspondence State
					ps.setString(x++, null); //Correspondence Pincode
					ps.setString(x++, null); //Father
					ps.setString(x++, null); // Is Father Alive
					ps.setString(x++, null); //Mother
					ps.setString(x++, null); // Is Mother Alive
					ps.setString(x++, null); //Guardian
					ps.setString(x++, aadharRegistrationEntity_data.getPhoto());
					ps.setString(x++, "aadhaar");
					ps.setString(x++, npciStatus);
					ps.setString(x++, userName);
					int updateCount = ps.executeUpdate();
					if (updateCount > 0) {
						
						//Update Bank Details. Only Branch Name to be updated and clear Non Aadhaar values
						int y = 1;
						String updateBank = databaseQ.getUpdateUserBankDetails();
						try(PreparedStatement ps1 = conn.prepareStatement(updateBank);){
							ps1.setString(y++, null);
							ps1.setString(y++, aadharRegistrationEntity_data.getBankName());
							ps1.setString(y++, null);
							ps1.setString(y++, null);
							ps1.setString(y++, userName);
							int updateBankCount = ps1.executeUpdate();
							if (updateBankCount > 0) {
								//Commit the transaction
								conn.commit();
								resultMap.put("resultCode", 0);
								resultMap.put("resultDetails", "Success");
								return resultMap;
							} else {
								resultMap.put("resultCode", -6);
								resultMap.put("resultDetails", "Failure");
								return resultMap;
							}
						}

					} else {
						resultMap.put("resultCode", -6);
						resultMap.put("resultDetails", "Failure");
						return resultMap;
					}
				}
			}
			
		}catch (NamingException | SQLException ex) {
			resultMap.put("resultCode", -5);
			resultMap.put("resultDetails", ex.getMessage());
			System.out.println("RegistrationService DB Exception: " + ex.toString());
			return resultMap;
		} catch (Exception ex) {
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", ex.getMessage());
			System.out.println("RegistrationService DB Exception: " + ex.toString());
			return resultMap;
		}
	}
	
	/**
	 * This method is used to check all input validations for updating user profile.
	 * It return true if everything checks up else false.
	 * 
	 * @param aadharRegistrationEntity_data
	 * @return boolean
	 */
	private boolean checkValidation(AadhaarRegistrationEntity aadharRegistrationEntity_data) {
		boolean result = true;
		
		if (aadharRegistrationEntity_data == null) {
			return false;
		}
		
		return result;
	}

}
