/**
 * Provides the classes used to implement business/functional logic 
 * for all the different types of request from portal.
 */
package com.newgen.cig.service;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.newgen.daoImpl.DB_GetUserData;
import com.newgen.dbt.commons.DatabaseQuery;

import JSON.JSONObject;

/**
 * <h1>ForgotPassword</h1>
 * <p>
 * This file covers all the business logic and validations implemented for Forgot Password functionality.
 * 
 * @author Varun Saddi
 * @version 1.0
 * @since   2017-07-10
 */
public class ForgotPassword {

	/**
	 * This method is used to decide the query to executed for checking verification based of on input type.
	 * @param jsonInput
	 * @param resultMap
	 * @return HashMap
	 */
	public HashMap<String, Object> verifyUserDetails(String jsonInput, HashMap<String, Object> resultMap) {
		
		DB_GetUserData userInfo = new DB_GetUserData();
		
		try {
			//Get the list of all matched registered id's for given input.
			HashMap<String, Object> userDetails = userInfo.checkUserDetails(jsonInput, resultMap);
				//System.out.println("In checkUser_UserDetails" + userDetails.get("Status").getAsString());
			if(!userDetails.get("resultCode").toString().equalsIgnoreCase("0")) {
				return userDetails;
			}
			else if(userDetails.get("Status").toString().equalsIgnoreCase("Y")) {
				//Check if there exist more than one match outputs
				JsonArray matchUserList = (JsonArray) userDetails.get("MatchedUserList");
				if(matchUserList.size() == 1) {
					//If there is only match then send the OTP to registered email id and mobile no.
					String optStatus = userInfo.sendVerificationOTP(
							matchUserList.get(0).getAsJsonObject().get("UserId").getAsString(),
							matchUserList.get(0).getAsJsonObject().get("MobileNo").getAsString(),
							matchUserList.get(0).getAsJsonObject().get("EmailId").getAsString(), 1);
					if(optStatus.equalsIgnoreCase("Y")) {
						resultMap.put("resultCode", "0");
						resultMap.put("userName", matchUserList.get(0).getAsJsonObject().get("UserId").getAsString());
						/*resultMap.put("userMobile", matchUserList.get(0).getAsJsonObject().get("MobileNo").getAsString());
						resultMap.put("userEmail", matchUserList.get(0).getAsJsonObject().get("EmailId").getAsString());*/
					}
					else
						resultMap.put("resultCode", "-4");
				}
				else {
					//If there are more than one match then return the result for asking DOB
					resultMap.put("resultCode", "1");
					resultMap.put("MatchedUserList", (JsonArray) userDetails.get("MatchedUserList"));
				}
			}
			else if(userDetails.get("Status").toString().equalsIgnoreCase("N")) {
				resultMap.put("resultCode", "-5");
			}
		} catch (Exception e) {
			
			System.out.println("Exception in verifyUserDetails>>" + e.getMessage()); //Console 
			resultMap.put("resultCode", "-1");
		}
		
		return resultMap;	
	}

}
