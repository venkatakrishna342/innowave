/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Portal_WS.java
* Author              : Ankit Katoch/Ankit Bhasin/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 15, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/

package com.newgen.webserviceclass;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.tempuri.DbtaspServiceSoapProxy;
import org.w3c.dom.Document;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.newgen.activityclass.Check_Login_Credential;
import com.newgen.activityclass.DocumentRepositoryDetails;
import com.newgen.activityclass.PostLogin_StudentDetails;
import com.newgen.activityclass.PostMatricDetails;
import com.newgen.activityclass.PreMatricFormDetails;
import com.newgen.boardIntegration.BoardIntegartion;
import com.newgen.cig.entity.AadhaarRegistrationEntity;
import com.newgen.cig.entity.ApplySchemeEntity;
import com.newgen.cig.entity.ClientEntity;
import com.newgen.cig.entity.CommonEntity;
import com.newgen.cig.entity.DepartmentMasterEntity;
import com.newgen.cig.entity.DocumentEntity;
import com.newgen.cig.entity.NotificationEntity;
import com.newgen.cig.entity.SchemeEntity;
import com.newgen.cig.ibpsConnect.ConfProperty;
import com.newgen.cig.ibpsConnect.IBPS_Supported_Operations;
import com.newgen.cig.service.ApplySchemeService;
import com.newgen.cig.service.ChangePasswordService;
import com.newgen.cig.service.ContentSchemeService;
import com.newgen.cig.service.DepartmentMasterService;
import com.newgen.cig.service.DisplayDocTypeService;
import com.newgen.cig.service.Escholarship_Application_Operations;
import com.newgen.cig.service.ForgotPassword;
import com.newgen.cig.service.GetResidentDataCountService;
import com.newgen.cig.service.InstituteUserActionCount;
import com.newgen.cig.service.NonAadhaarRegistrationService;
import com.newgen.cig.service.NotificationService;
import com.newgen.cig.service.RegistrationServiceAadhaar;
import com.newgen.cig.service.UpdateProfileService;
import com.newgen.cig.service.UserLoginLogoutService;
import com.newgen.cig.service.UserLoginService;
import com.newgen.daoImpl.DB_GetPostMatricData;
import com.newgen.daoImpl.DB_GetUserData;
import com.newgen.dbt.integration.adhar.AdharIntegration;
import com.newgen.dbt.integration.adhar.ClientSendOTP;
import com.newgen.dto.Set_Get_Data_ApplicationForm;
import com.newgen.dto.Set_Get_Data_EditProfile;
import com.newgen.dto.Set_Get_Data_OnAppID;
import com.newgen.dto.Set_Get_Data_PendingCases;
import com.newgen.dto.Set_Get_Data_PortalToIBPS;
import com.newgen.dto.Set_Get_Data_PostLogIn;
import com.newgen.utility.AppConstants;
import com.newgen.utility.InitConfigProp;

import JSON.JSONArray;
import JSON.JSONException;
import JSON.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Path("/dbtPortal")
public class Portal_WS {	

	JSONObject jsonObj = null;
	String token=null;	
	Claims claims = null;
	@Context
	private ServletContext application;
	
	@POST
	@Path("/scheme/index")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap getSchemes(SchemeEntity schemeEntity_i, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {
		ContentSchemeService contentschemeservice = new ContentSchemeService();
		HashMap resultMap = new HashMap();
		return contentschemeservice.getSchemeIndex(schemeEntity_i, resultMap, httpServletRequest, httpServletResponse);
	}
	
	@POST
	@Path("/dept/index")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap getDeptIndex(DepartmentMasterEntity masterEntity_i, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {

		DepartmentMasterService departmentMasterService = new DepartmentMasterService();
		HashMap resultMap = new HashMap();
		return departmentMasterService.getDepartmentIndex(masterEntity_i, resultMap, httpServletRequest,
				httpServletResponse);
	}
	 
	@POST
	@Path("/changePassword")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap changePass(CommonEntity commonEntity) {
		ChangePasswordService changePass = new ChangePasswordService();
		HashMap resultMap = new HashMap();
		// System.out.println(changePass.changePassword(commonEntity,
		// resultMap));
		return changePass.changePassword(commonEntity, resultMap);

	}
	
	@POST
	@Path("/getInstCountService")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap getInstCount(CommonEntity commonEntity) {
		InstituteUserActionCount dataCount = new InstituteUserActionCount();
		HashMap resultMap = new HashMap();
		return dataCount.getInstituteDataCountService(commonEntity, resultMap);
	}

	@POST
	 @Path("/getCountService")
	 @Consumes("application/json")
	 @Produces("application/json")
	 public HashMap getCount( CommonEntity commonEntity) {
		 GetResidentDataCountService dataCount= new GetResidentDataCountService();
		 HashMap resultMap = new HashMap();
		 return dataCount.getResidentDataCountServiceFromWeb(commonEntity, resultMap);
	 }	
	
	@SuppressWarnings("finally")
	@POST
	@Path("/updateResidentProfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public HashMap userUpdateProfile(FormDataMultiPart formParams, @FormDataParam("formData") String fullDetails) {
		HashMap response = new HashMap();
		String listData = "";
		// AadhaarRegistrationEntity aadhaar
		try {

			JSONObject jsonData = new JSONObject(fullDetails);

			DB_GetUserData plsd = new DB_GetUserData();
			listData = plsd.updateResidentProfile(jsonData, formParams);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("success".equalsIgnoreCase(listData))
			response.put("resultCode", "0");
		else
			response.put("resultCode", "-1");
		return response;

	}
				
			
	@SuppressWarnings("finally")
	@POST
	@Path("/nonAadhaarResidentProfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public HashMap nonAadhaarResidentProfile(FormDataMultiPart formParams,
			@FormDataParam("formData") String fullDetails) {
		HashMap response = new HashMap();
		String listData = "";
		try {
			JSONObject jsonData = new JSONObject(fullDetails);
			NonAadhaarRegistrationService register = new NonAadhaarRegistrationService();
			response = register.createResidentProfile(jsonData, formParams, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
		
	@SuppressWarnings("finally")
	@POST
	@Path("/userRegistrationUsingAadhar")
	@Consumes("application/json")
	@Produces("application/json")
	public String userRegistrationUsingAadhaar(AadhaarRegistrationEntity aadharRegistrationEntity_data) {
		HashMap hashMap = null;
		Gson gson = new Gson();
		RegistrationServiceAadhaar registrationService = new RegistrationServiceAadhaar();
		HashMap resultMap = new HashMap();
		String response = gson.toJson(registrationService.userRegistraion(aadharRegistrationEntity_data, resultMap));
		return response;

	}

	/* View Document Changed by Ankit */
	@GET
	@Path("/getImageView")
	@Produces("application/json")
	public Response getImageForView(@QueryParam("appID") String appID) {
		String retJsonStr = null;
		try {
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getDocumentDetails(appID);
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	@GET
	@Path("/getImageViewForDocs")
	@Produces("application/json")
	public Response getImageViewForDocs(@QueryParam("filePath") String filePath) {
		String retJsonStr = null;
		try {
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getImageViewForDocs(filePath);
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	/* Getting data from master for auto population of editprofle */
	@POST
	@Path("/getProfileAutoData")
	@Consumes("application/json")
	@Produces("application/json")
	public Response editRegAutoPopulate(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {

		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
				System.out.println("UserName : " + userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {
				retJsonStr = "{\"resultCode\":\"-4\"}";
				;
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			DB_GetUserData plsd = new DB_GetUserData();
			retJsonStr = plsd.getProfileDataAutoPopulate(userName);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
			httpResponse.setHeader("token", token);
			e.printStackTrace();
		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
	
	@POST
	@Path("/showDocsType")
	@Consumes("application/json")
	@Produces("application/json")
	public String showDocsType(String json) {
		
		String jsonReturn = null;
		ArrayList<Set_Get_Data_EditProfile> listData = null;
		try {
			String user_id = "";
			JSONObject jObj = new JSONObject(json);

			String docType = (String) jObj.getString("docsType");
			try {
				user_id = (String) jObj.getString("user_id");
			} catch (Exception e) {
				user_id = "";
			}
			;

			DisplayDocTypeService disp = new DisplayDocTypeService();
			listData = disp.getDocTypeData(docType, user_id);
			Gson gson = new Gson();
			// System.out.println(gson.toJson(listData));
			jsonReturn = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
		}

		return jsonReturn;
	}
    
    
	@POST
	@Path("/callDocType")
	@Consumes("application/json")
	@Produces("application/json")
	public String callDocType(String json) {
		String jsonReturn = null;
		ArrayList<Set_Get_Data_EditProfile> listData = null;
		try {
			String user_id = "";
			JSONObject jObj = new JSONObject(json);

			// String docType = (String)jObj.getString("docsType");
			try {
				user_id = (String) jObj.getString("user_id");
			} catch (Exception e) {
				user_id = "";
			}
			;

			DisplayDocTypeService disp = new DisplayDocTypeService();
			jsonReturn = disp.callDocType(user_id);
			// Gson gson = new Gson();
			// System.out.println(jsonReturn+" final return");
			// jsonReturn = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
		}

		return jsonReturn;
	}	
    
	/** Changed by Ankit Bhasin for ApplicationForm **/
	@POST
	@Path("/saveOrSubmitApplicationForm")
	@Consumes("application/json")
	public HashMap<String, Object> applySchemes(String data, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		JacksonJsonProvider jackPro = new JacksonJsonProvider();
		ApplySchemeEntity applySchemeEntity_data = null;
		try {
			//Deserialize Json input with Jackson
			applySchemeEntity_data = jackPro.getContext(ApplySchemeEntity.class).readValue(data, ApplySchemeEntity.class);
			ApplySchemeService applySchemeService = new ApplySchemeService();
			return applySchemeService.applyScheme(applySchemeEntity_data, resultMap, httpRequest, httpResponse);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception Error");
			httpResponse.setHeader("token", token);
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", e.getMessage());
			return resultMap;
		}

		httpResponse.setHeader("token", token);
		return resultMap;
	}
	
	
	/* Getting data from master for auto population of tab1 */
	@GET
	@Path("/getPostMatircDataFromMaster7")
	@Produces("application/json")
	public Response getPostMatircAutoPopulate(@QueryParam("userName") String userName,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) {
		String retJsonStr = null;
		Response response = null;
		try {

			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsAutoPopulate(userName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Auto Populate : " + retJsonStr);

		} catch (Exception e) {
			System.out.println("Exception Error");
			httpResponse.setHeader("token", token);

		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		System.out.println("response : " + response);
		return response;
	}
    
	@GET
	@Path("/getCasteCategoryMaster")
	@Produces("application/json")
	public Response getCasteCategoryDetails(@Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		String retJsonStr = null;
		Response response = null;
		try {

			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getCasteCategory();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			// System.out.println("Caste Category : " + retJsonStr);

		} catch (Exception e) {
			System.out.println("Exception Error");
			retJsonStr = "{\"resultCode\":\"-1\"}";
			httpResponse.setHeader("token", token);

		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
	
	@GET
	@Path("/getCaste")
	@Produces("application/json")
	public Response getCasteDetails(@QueryParam("casteCategory") String casteCategory,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) {
		String retJsonStr = null;
		Response response = null;
		try {
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getCaste(casteCategory);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			// System.out.println("Caste : " + retJsonStr);

		} catch (Exception e) {
			System.out.println("Exception Error");
			retJsonStr = "{\"resultCode\":\"-1\"}";
			httpResponse.setHeader("token", token);

		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
	
	/* Getting data from master for courses of last year */
	@GET
	@Path("/getLastYearCourse")
	@Produces("application/json")
	public Response getLastYearCourse(@QueryParam("collegeName") String collegeName,
			@QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsLastYearCourses(collegeName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Courses :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
	//changed on 22nd July 2017
	/* change end by ankit bhasin */
	@GET
	@Path("/getPostMatircDataFromMaster1")
	@Produces("application/json")
	public Response getPostMatircTaluka(@QueryParam("district") String districtName,@QueryParam("state") String stateName,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) {
		String retJsonStr = null;
		Response response = null;
		try {
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsTaluka(districtName,stateName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("TalukaNew:: " + retJsonStr);
			
		} catch (Exception e) {
			System.out.println("Exception Error");
			httpResponse.setHeader("token", token);

		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);

		return response;
		// return retJsonStr;
	}	
	
	@POST
	@Path("/uploadDoc")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String getUploadDocument(@FormDataParam("file") InputStream uploadedInputStream,@FormDataParam("file") FormDataContentDisposition fileDetail) {
		saveToDisk(uploadedInputStream, fileDetail);
		return "Upload Successfully";
	}
	
	@POST
	@Path("/uploadDoc1")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String getUploadDocuments(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @QueryParam("userID") String userID,
			@QueryParam("fileType") String fileType, @QueryParam("schemeID") String schemeID,
			@QueryParam("appid") String appid, @QueryParam("schemeType") String schemeType,
			@QueryParam("userIndex") String userIndex, @QueryParam("fullName") String fullName) {

		String fileName = fileDetail.getFileName();
//		System.out.println("FileName:: " + fileName);
//		System.out.println("Bhasin:: " + userID);
//		System.out.println("Type :: " + fileType);
//		System.out.println("appid :: " + appid);

		String uploadStatus = "false";
		String documentPath = "";

		File docDirectory = null;
		try {
			docDirectory = new File(InitConfigProp.getProperty("documentpath") + File.separatorChar + "Applications"
					+ File.separatorChar + userID + File.separatorChar + "Documents");
		} catch (Exception e) {
			System.out.println("Placing document directory not found !!");
			e.printStackTrace();
		}
		String fileLocation = "";
		fileLocation = docDirectory + File.separator + fileName;
		System.out.println("Location :: " + fileLocation);

		try {
			documentPath = saveOnDisk(uploadedInputStream, fileDetail, userID, docDirectory, fileLocation);
			uploadStatus = "true";
		} catch (Exception ex) {
			uploadStatus = "false";
			fileLocation = "";
			ex.printStackTrace();
		}

		if (uploadStatus.equalsIgnoreCase("true")) {
			PostMatricDetails plsd = new PostMatricDetails();
			uploadStatus = plsd.addDocumentsDetails(schemeID, userID, documentPath, fileType, appid, schemeType,
					userIndex, fullName, "N");
		}

		return uploadStatus;
	}
	
	/* @author Ankit Katoch */
	/*
	 * 7 march
	 */
	/*
	 * url = "http://'hostname'/NGPortalWS/portal/dbtPortal/userLogoutService"
	 */
	@POST
	@Path("/logoutService")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap userLogoutSerivce(AadhaarRegistrationEntity registrationEntity_i) {

		// System.out.println(json);
		Gson gson = new Gson();
		System.out.println(gson.toJson(registrationEntity_i));
		UserLoginLogoutService logoutService = new UserLoginLogoutService();
		HashMap resultMap = new HashMap();
		return logoutService.logoutService(registrationEntity_i, resultMap);
	}
	
	/* @author Ankit Katoch */
	/*
	 * 3 march
	 */
	/* url = "http://'hostname'/NGPortalWS/portal/dbtPortal/userLogInService" */
	@POST
	@Path("/userLogInService")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap userLoginSerivce(String inputData, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		JacksonJsonProvider jackPro = new JacksonJsonProvider();
		AadhaarRegistrationEntity registrationEntity_i = null;
		try {
			registrationEntity_i = jackPro.getContext(AadhaarRegistrationEntity.class).readValue(inputData, AadhaarRegistrationEntity.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String ip = request.getRemoteAddr();
		registrationEntity_i.setIpAdderess(ip);
		UserLoginService loginService = new UserLoginService();
		HashMap resultMap = new HashMap();
		return loginService.userLoginService(registrationEntity_i, resultMap, response);
	}
	
	/* Created by Swarnadip Ghosh */
	/* 03 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/studentDataPostLoginAsUser" */
	@POST
	@Path("/studentDataPostLoginAsUser")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getStudentDataPostLoginAsUser(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {

		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
			System.out.println("UserName : " + userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getPostLoginStudentDetailsAsUser(userName);
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);

		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
			retJsonStr = "{\"resultCode\":\"-1\"}";

			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			httpResponse.setHeader("token", token);
			return response;

		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
	
	/*Getting data from master for District*/
	@GET
	@Path("/getPostMatircDataFromMaster")
	@Produces("application/json")
	public Response getPostMatircDataFromMaster(@Context HttpServletResponse httpResponse,
			@Context HttpServletRequest httpRequest) {
		String retJsonStr = null;
		Response response = null;
		try {
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetails();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			// System.out.println("Ankit:: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
    
    /*Getting data from master for courses*/
	/*@GET
	@Path("/getPostMatircDataFromMaster5")
	@Produces("application/json")
	public Response getPostMatircColleges(@QueryParam("collegeName") String collegeName,
			@QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsCourses(collegeName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Courses :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
		// return retJsonStr;
	}*/
    
	@POST
	@Path("/getPostMatircDataFromMaster5")
	@Produces("application/json")
	public Response getPostMatircColleges(String collegeDetails) {
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(collegeDetails);
			String collegeName = (String) jObj.getString("collegeName");
			System.out.println("collegeName : " + collegeName);
			
			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID : " + schemeID);
			
			String state = (String) jObj.getString("state");
			System.out.println("state : " + state);
			
			String district = (String) jObj.getString("district");
			System.out.println("district : " + district);
			
			String taluka = (String) jObj.getString("taluka");
			System.out.println("taluka : " + taluka);
			
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsCourses(collegeName, schemeID, state, district, taluka);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Courses :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
		// return retJsonStr;
	}
	
	/**
	 * @param schemeID
	 * @return
	 */
	@GET
	@Path("/getReligion")
	@Produces("application/json")
	public Response getReligionFromMaster(@QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getReligionFromMasterDB(schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Courses :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
	/**
	 * This service was used to get beneficiary list but not used as of now.
	 * @param httpRequest
	 * @param httpResponse
	 * @return
	 */
	/*@GET
	@Path("/getBeneficiaryType")
	@Produces("application/json")
	public Response getBeneficiaryDetails(@Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		String retJsonStr = null;
		Response response = null;
		try {

			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getBeneficiary();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Beneficiary : " + retJsonStr);

		} catch (Exception e) {
			System.out.println("Exception Error");
			retJsonStr = "{\"resultCode\":\"-1\"}";
			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}*/
    
    /*Getting data from master for Boards*/
    @GET
	@Path("/getBoards")
	@Produces("application/json")
	public Response getPostMatircBoardDetails() {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsBoard();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Boards :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}
		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
    
    /*Getting data from master for grant Type*/
	/*@GET
	@Path("/getCourseYear")
	@Produces("application/json")
	public Response getPostMatircCourseYear(@QueryParam("courseName") String courseName,
			@QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsCourseYear(courseName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Grants :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}*/
    
    @POST
	@Path("/getCourseYear")
	@Produces("application/json")
	public Response getPostMatircCourseYear(String courseDetails) {
		String retJsonStr = null;
		try {
			
			JSONObject jObj = new JSONObject(courseDetails);
			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID : " + schemeID);
			
			String collegeName = (String) jObj.getString("collegeName");
			System.out.println("collegeName : " + collegeName);
			
			String state = (String) jObj.getString("state");
			System.out.println("state : " + state);
			
			String district = (String) jObj.getString("district");
			System.out.println("district : " + district);
			
			String taluka = (String) jObj.getString("taluka");
			System.out.println("taluka : " + taluka);
			
			String university = (String) jObj.getString("university");
			System.out.println("university : " + university);
			
			String grantType = (String) jObj.getString("grantType");
			System.out.println("grantType : " + grantType);
			
			String courseName = (String) jObj.getString("courseName");
			System.out.println("courseName : " + courseName);
			
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsCourseYear(schemeID, collegeName, state, district, taluka, university, grantType, courseName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("courseYear :: " + retJsonStr);
			
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
    
    /*Getting data from master for grant Type*/
	/*@GET
	@Path("/getGrantType")
	@Produces("application/json")
	public Response getPostMatircGrantType(@QueryParam("courseName") String courseName,
			@QueryParam("collegeName") String collegeName, @QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsGrantType(courseName, collegeName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Grants :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
		// return retJsonStr;
	}*/
    
    @POST
	@Path("/getGrantType")
	@Produces("application/json")
	public Response getPostMatircGrantType(String courseDetails) {
		String retJsonStr = null;
		try {
			
			JSONObject jObj = new JSONObject(courseDetails);
			String collegeName = (String) jObj.getString("collegeName");
			System.out.println("collegeName : " + collegeName);
			
			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID : " + schemeID);
			
			String state = (String) jObj.getString("state");
			String district = (String) jObj.getString("district");
			String taluka = (String) jObj.getString("taluka");
			String university = (String) jObj.getString("university");
			String courseName = (String) jObj.getString("courseName");
			
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsGrantType(collegeName, schemeID, state, district, taluka, university, courseName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Grants :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
    
	/**
	 * This service was used to get sub division list but not used now. 
	 * @return
	 */
	/*@GET
	@Path("/getSubDivisions")
	@Produces("application/json")
	public Response getSubDivision() {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getSubDivision();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Grants :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}*/
    
    /*Getting data from master for universities*/
    /*@GET
	@Path("/getUniversities")
	@Produces("application/json")
	public Response getPostMatircUniversity(@QueryParam("collegeName") String collegeName,
			@QueryParam("schemeID") String schemeID) {
		
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsUniversities(collegeName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Universities :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
		// return retJsonStr;
	}*/
    
    @POST
	@Path("/getUniversities")
	@Produces("application/json")
	public Response getPostMatircUniversity(String collegeDetails) {
		
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(collegeDetails);
			String collegeName = (String) jObj.getString("collegeName");
			System.out.println("collegeName : " + collegeName);
			
			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID : " + schemeID);
			
			String courseName = (String) jObj.getString("courseName");
			System.out.println("courseName : " + courseName);
			
			String state = (String) jObj.getString("state");
			System.out.println("state : " + state);
			
			String district = (String) jObj.getString("district");
			System.out.println("district : " + district);
			
			String taluka = (String) jObj.getString("taluka");
			System.out.println("taluka : " + taluka);
			
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsUniversities(collegeName, schemeID, courseName, state, district, taluka);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Universities :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
		// return retJsonStr;
	}
    
    /*Getting data from master for standard*/
    @GET
	@Path("/getStandarad")
	@Produces("application/json")
	public Response getSTD(@QueryParam("schemeID") String schemeID,
			@QueryParam("udiseCode") String udiseCode) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPreMatricDetailStandard(schemeID,udiseCode);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
    
    /*Getting data from master for colleges*/
	@GET
	@Path("/getPostMatircDataFromMaster6")
	@Produces("application/json")
	public Response getColleges(@QueryParam("state") String state, @QueryParam("district") String district,
			@QueryParam("taluka") String taluka, @QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailColleges(state, district, taluka, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);

			retJsonStr = retJsonStr.replaceAll("\\u0026", "&");

			// System.out.println("Colleges11:: " + retJsonStr);

		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
    /*get course Types*/
	/*@GET
	@Path("/getCourseType")
	@Produces("application/json")
	public Response getCourseTypes(@QueryParam("courseName") String courseName,
			@QueryParam("collegeName") String collegeName, @QueryParam("schemeID") String schemeID) {
		System.out.println("collegeName222 :: " + collegeName);
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailCourseTypes(courseName, collegeName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("CourseTypes:: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}*/
	
	@POST
	@Path("/getCourseType")
	@Produces("application/json")
	public Response getCourseTypes(String courseDetails) {
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(courseDetails);

			String courseName = (String) jObj.getString("courseName");
			System.out.println("courseName : " + courseName);
			
			String collegeName = (String) jObj.getString("collegeName");
			System.out.println("collegeName : " + collegeName);
			
			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID : " + schemeID);
			
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailCourseTypes(courseName, collegeName, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("CourseTypes:: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	
	/* Created by Swarnadip Ghosh */
	/* 04 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/studentDataPostLoginAsUser" */
	@POST
	@Path("/collegeLoginAsUser")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getclerkLoginAsUser(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		System.out.println(json);
		String retJsonStr = null;
		Response response = null;
		try {
			// JSONObject jObj = new JSONObject(json);
			// String userName = (String)jObj.getString("userName");
			// System.out.println("UserName : "+userName);

			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(httpRequest.getHeader("token"))
					.getBody();
			String userid = claims.get("userid", String.class);
			String userrole = claims.get("userrole", String.class);
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getPostLoginStudentDetailsAsUser1(userid, userrole);
			Gson gson = new Gson();
			// System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception Error"); // Console
			httpResponse.setHeader("token", token);
		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);

		return response;
	}
	
	/*
	 * url =
	 * "http://localhost:8080/NGPortalWS/portal/dbtPortal/studentDataPostLoginAsUser"
	 */
	@POST
	@Path("/getCommonApplicaitonData")

	@Consumes("application/json")
	@Produces("application/json")
	public Response getCommonApplicaitonData(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		
		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);



			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {
				
				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getCommonApplicaitonData(jObj,httpRequest);

			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error in Approved Apps"); // Console
			httpResponse.setHeader("token", token);
		}
		
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}	
	/* Created by Swarnadip Ghosh */
	/* 05 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/getDataOnclickAppID" */
	@POST
	@Path("/getDataOnclickAppID")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getDataOnclickAppID(String json) {
		
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String appID = (String) jObj.getString("id");
			System.out.println("UserName : " + appID);

			ArrayList<Set_Get_Data_OnAppID> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getDataOnclickAppID(appID);
			Gson gson = new Gson();
			// System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	@POST
	@Path("/getAppTrackingApplicaitonData")

	@Consumes("application/json")
	@Produces("application/json")
	public Response getAppTrackingApplicaitonData(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		
		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);
//			String userName = (String) jObj.getString("userName");
//			System.out.println("UserName : " + userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {
				
				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getTrackingApplicaiton(jObj,httpRequest);

			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error in Approved Apps"); // Console
			httpResponse.setHeader("token", token);
		}
		
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}	
	
	/* Created by Swarnadip Ghosh */
	/* 07 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/getDataOnclickAppIDStudent" */
	@POST
	@Path("/getDataOnclickAppIDStudent")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getDataOnclickAppIDStudent(String json) {
		System.out.println(json);
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String appID = (String) jObj.getString("appid");
			String schemetype = (String) jObj.getString("schemetype");
			int schemeid = (int) jObj.getInt("schemeid");
			String userIndex = (String) jObj.getString("userIndex");

			System.out.println("appID : " + appID);
			System.out.println("schemetype : " + schemetype);
			System.out.println("schemeid : " + schemeid);
			System.out.println("userIndex : " + userIndex);

			ArrayList<ApplySchemeEntity> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getDataOnclickAppIDStudent(appID, schemetype, schemeid, userIndex);
			/*
			 * Gson gson = new Gson();
			 * System.out.println(gson.toJson(listData)); retJsonStr =
			 * gson.toJson(listData);
			 */

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			ObjectWriter writer = objectMapper.writer();
			System.out.println(writer.writeValueAsString(listData));
			retJsonStr = writer.writeValueAsString(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	/* Created by Swarnadip Ghosh */
	/* 08 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/updateClerkStatus" */
	@POST
	@Path("/updateClerkStatus")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateClerkStatus(String json) {
		System.out.println(json);
		String retJsonStr = null;
		int updateStatus = -1;

		String attendance_percent = "";
		String user_index = "";

		try {
			JSONObject jObj = new JSONObject(json);

			String appId = (String) jObj.getString("appId");
			System.out.println("appId : " + appId);

			String regNo = (String) jObj.getString("regNo");
			System.out.println("regNo : " + regNo);

			String authLvl = (String) jObj.getString("authLvl");
			System.out.println("authLvl : " + authLvl);

			/* addition 17/5/2017 ---- Start */
			String schemeid = (String) jObj.getString("schemeid");
			System.out.println("schemeid : " + schemeid);

			String userName = (String) jObj.getString("userName");
			System.out.println("userName : " + userName);

			String action = (String) jObj.getString("action");
			System.out.println("action : " + action);

			String comment = (String) jObj.getString("comment");
			System.out.println("comment : " + comment);
			/* addition 17/5/2017 ---- End */

			int verify_status = jObj.getInt("verify_status");
			System.out.println("verify_status : " + verify_status);

			String verify_Remarks = (String) jObj.getString("verify_Remarks");
			System.out.println("verify_Remarks : " + verify_Remarks);

			// change on 11th July
			if ("35".equalsIgnoreCase(schemeid) || "22".equalsIgnoreCase(schemeid) || "24".equalsIgnoreCase(schemeid)) {
				// change on 7th July
				attendance_percent = (String) jObj.getString("attendance_percent");
				System.out.println("attendance_percent : " + attendance_percent);
				user_index = (String) jObj.getString("userIndex");
				System.out.println("user_index : " + user_index);
			}

			// Parse action object for action id
			JsonParser parser = new JsonParser();
			JsonObject actionObj = new JsonObject();
			actionObj = parser.parse(action).getAsJsonObject();
			action = actionObj.get("action_id").getAsString();

			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			updateStatus = plsd.updateClerkStatus(appId, regNo, authLvl, schemeid, userName, action, comment,
					verify_status, verify_Remarks, attendance_percent, user_index);
			if (updateStatus == 1)
				retJsonStr = "Success";
			else
				retJsonStr = "Failure";
			Gson gson = new Gson();
			System.out.println(gson.toJson(retJsonStr));
			retJsonStr = gson.toJson(retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	/* Created By Swarnadip Ghosh */
	/* 09 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/checkUserIDinDB" */
	@POST
	@Path("/checkUserIDinDB")
	@Consumes("application/json")
	@Produces("application/json")
	public Response checkUserIDinDB(String json) {
		
		String isUserExists = null;
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(json);

			String userid = (String) jObj.getString("userid");
			System.out.println("userid : " + userid);

			Check_Login_Credential clc = new Check_Login_Credential();
			isUserExists = clc.checkUserIDinDB(userid);

			Gson gson = new Gson();
			System.out.println(gson.toJson(isUserExists));
			retJsonStr = gson.toJson(isUserExists);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;

	}
	
	/* Created on 03 march */
	/**
	 * This service is used to check user input Aadhaar No. in database
	 * @param json
	 * @return
	 */
	@POST
	@Path("/checkAadhaarinDB")
	@Consumes("application/json")
	@Produces("application/json")
	public Response checkAadhaarinDB(String json) {
		String isUserExists = null;
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(json);

			String aadhaarid = (String) jObj.getString("aadhaarID");
				System.out.println("userid : " + aadhaarid);
			DB_GetUserData dao = new DB_GetUserData();
			isUserExists = dao.checkAadhaarinDB(aadhaarid);
			Gson gson = new Gson();
				System.out.println(gson.toJson(isUserExists));
			retJsonStr = gson.toJson(isUserExists);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}

	/**
	 * This service is used to generate an OTP for user input Aadhaar No. on mobile
	 * @param json
	 * @return
	 */
	@POST
	@Path("/sendOTP")
	@Consumes("application/json")
	@Produces("application/json")
	public String sendOTP(String json) {
		String jsonReturn = null;
		String listData = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String uidNumber = (String) jObj.getString("uidNumber");
				System.out.println("aadhaar id : " + uidNumber);
			org.json.simple.JSONObject obj = ClientSendOTP.sendOTP(uidNumber);
				System.out.println("the json obj " + obj);
			listData = obj.toJSONString();
				System.out.println("listData " + listData.toString());

			Gson gson = new Gson();
			jsonReturn = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
		}

		return jsonReturn;
	}

	/**
	 * This service is used to verify user input OTP from Aadhaar and get back the user details
	 * 
	 * @param json
	 * @return
	 */
	@POST
	@Path("/validateOTP")
	@Consumes("application/json")
	@Produces("application/json")
	public String validateOTP(String json) {
//		String jsonReturn = null;
		String listData = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String uidNumber = (String) jObj.getString("uidNumber");
			String otp = (String) jObj.getString("otp");
				System.out.println("uidNumber : " + uidNumber + "  otp :" + otp);

			org.json.simple.JSONObject obj = AdharIntegration.generateAdharXml(uidNumber, otp);
			listData = obj.toJSONString();
			/*Gson gson = new Gson();
			jsonReturn = gson.toJson(listData);*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listData;
	}	
	
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/checkEmailDB" */
	/*@POST
	@Path("/checkEmailinDB")
	@Produces("application/json")
	public Response checkEmailinDB(String json)
	{
		System.out.println(json);
		String isUserExists = null;
		String retJsonStr = null ;
		JsonObject resultObj = new JsonObject();
		try 
		{
			JSONObject jObj = new JSONObject(json);
			
			String email = (String)jObj.getString("email");		
			System.out.println("userid : "+email);
			
			Check_Login_Credential clc = new Check_Login_Credential();
			JsonObject userDetails = clc.checkEmailInDB(email);
			if(userDetails.get("Status") == null) {
				resultObj.addProperty("resultCode", "-2");//status null
			}
			else if(userDetails.get("Status").getAsString().equalsIgnoreCase("Y")) {
				resultObj.addProperty("resultCode", "-1");//already exist
			}
			else if(userDetails.get("Status").getAsString().equalsIgnoreCase("N")) {
				resultObj.addProperty("resultCode", "0");//not exist
			}
			else{
				resultObj.addProperty("resultCode", "-3");//some exception
			}
			
			Gson gson = new Gson();
			System.out.println(gson.toJson(isUserExists));
			retJsonStr = gson.toJson(isUserExists);	
		}
		catch (Exception e)
		{
			System.out.println("Exception Error"); //Console 
			e.printStackTrace();
		}
		
		Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*").build(); 
		return response;
		
	}*/
	
	/* Created on 17may */
	/* 05 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/checkEidinDB" */
	@POST
	@Path("/checkEidinDB")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap checkEidinDB(AadhaarRegistrationEntity eidEntity) {
		DB_GetUserData getEid = new DB_GetUserData();
		HashMap resultMap = new HashMap();
		return getEid.checkEidinDB(eidEntity, resultMap);
	}
	
	/* Created by Swarnadip Ghosh */
	/* 10 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/studentDataPostLoginAsUser" */
	@POST
	@Path("/getCategoryDetails")
	@Produces("application/json")
	public Response getCategoryDetails(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		// System.out.println(json);
		String retJsonStr = null;
		Response response = null;

		try {
			// JSONObject jObj = new JSONObject(json);
			// String userName = (String)jObj.getString("userName");
			// System.out.println("UserName : "+userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";

				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getCategoryDetails();
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
			retJsonStr = "{\"resultCode\":\"-1\"}";
			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			return response;

		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);

		return response;
	}
	
	/* Created by Swarnadip Ghosh */
	/* 11 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/studentDataPostLoginAsUser" */
	@POST
	@Path("/getDeptDetails")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getDeptDetails(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		System.out.println(json);
		String retJsonStr = null;
		Response response = null;

		try {
			JSONObject jObj = new JSONObject(json);
			String dept = (String) jObj.getString("dept");

			System.out.println("dept : " + dept);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getDeptDetails(dept);
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
			retJsonStr = "{\"resultCode\":\"-1\"}";
			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			httpResponse.setHeader("token", token);
			return response;

		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);

		return response;
	}
	
	//change on 23rd
	@GET
	@Path("/getSpecificStatesAsPerSchemeID")
	@Produces("application/json")
	public Response getSpecificStatesAsPerSchemeID(@QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getSpecificStatesFromDB(schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
	
	//changed on 22nd July 2017
	/*Getting data from master for taluka*/
	@GET
	@Path("/getTaluka")
	@Produces("application/json")
	public Response getTaluka(@QueryParam("district") String districtName,@QueryParam("state") String stateName) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsTaluka(districtName,stateName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;

		// return retJsonStr;
	}
	
	/*Getting data from master for District*/
	@GET
	@Path("/getDistrict")
	@Produces("application/json")
	public Response getDistrict(@QueryParam("state") String state) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getDistrict(state);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			//System.out.println("Ankit:: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;

		// return retJsonStr;
	}
    
    /*Getting data from master for States*/
	@GET
	@Path("/getStates")
	@Produces("application/json")
	public Response getState() {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getStatesFromDB();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
    
    /*Getting data from master for Villages*/
	@GET
	@Path("/getVillages")
	@Produces("application/json")
	public Response getVillage(@QueryParam("district") String district, @QueryParam("taluka") String taluka) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getVillagesDB(district, taluka);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;

		// return retJsonStr;
	}
    
	/* Getting data from master for colleges */
	@GET
	@Path("/getDiplomaCollege")
	@Produces("application/json")
	public Response getDiplomaColleges(@QueryParam("state") String state, @QueryParam("district") String district,
			@QueryParam("taluka") String taluka, @QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailCollegesDiploma(state, district, taluka, schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);

			retJsonStr = retJsonStr.replaceAll("\\u0026", "&");
			System.out.println("Colleges11:: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
	/*getschoolName Types*/
	@GET
	@Path("/getschoolName")
	@Produces("application/json")
	public Response getschoolName(@QueryParam("schemeID") String schemeID, @QueryParam("district") String district,
			@QueryParam("taluka") String taluka) {
		String retJsonStr = null;
		try {
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailSchoolName(schemeID, district, taluka);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			
			System.out.println("schoolName:: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
    
    /*Getting data from master for Occupation*/
	@GET
	@Path("/getProfession")
	@Produces("application/json")
	public Response getPostMatircOccupationDetails(@QueryParam("schemeID") String schemeID) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsOccupation(schemeID);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Profession :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}
		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
    
	@GET
	@Path("/getDisabilityTypes")
	@Produces("application/json")
	public Response getDisabilityTypes() {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getDisabilityType();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Caste Category : " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}
		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	@POST
	@Path("/studentDataPendingCases")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getPendingCasesDataFromDB(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		System.out.println(json);
		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
			System.out.println("UserName : " + userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<Set_Get_Data_PendingCases> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getPendingCasesDataFromDB(userName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Pending Case :: " + retJsonStr);

		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
			retJsonStr = "{\"resultCode\":\"-1\"}";
			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			return response;
		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
	
	/**
	 * This service is to get Remarks History of application url =
	 * "http://localhost:8080/NGPortalWS/portal/dbtPortal/getRemarkHistoryClient"
	 * 
	 * @author Swarnadip Ghosh, Varun Saddi
	 * @since 2017-07-18
	 * @param json
	 * @return JSONObject
	 */
	@POST
	@Path("/getRemarkHistoryClient")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getRemarkHistoryClient(String json) throws org.codehaus.jettison.json.JSONException {
		
		String ibpsServerIp = "";
		String ibpsServerport = "";
		String protocol = "";
		try {
			ConfProperty property = new ConfProperty();
			ibpsServerIp = ConfProperty.getProperty("ServerIP");
			ibpsServerport = ConfProperty.getProperty("IbpsServerPort");
			protocol = ConfProperty.getProperty("Protocol");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		final String BASE_URI = protocol + "://" + ibpsServerIp + ":" + ibpsServerport
				+ "/SJ_Pre_Matric_RemarksHistoryWS/webresources/genericPreMatric/post_History";
		String output, jsonOutput = null;
		
		try {
			JSONObject jObj = new JSONObject(json);
			String ibpsRegNo = jObj.getString("ibpsRegNo");
			String userRoleId = jObj.getString("userrole");				
			jsonObj = new JSONObject();	
			jsonObj.put("regNo", ibpsRegNo);
			jsonObj.put("userAction", userRoleId);
		
            URL url = new URL(BASE_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);

            String input = jsonObj.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                  throw new RuntimeException("Failed : HTTP error code : "
                         + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                         (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                  jsonOutput = output;
            }
            
            System.out.println("Remarks Output>> : "+jsonOutput);
            conn.disconnect();

		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Response fResponse = Response.status(200).entity(jsonOutput).header("Access-Control-Allow-Origin", "*").build();
		return fResponse;
	}
	
	/* Created by Swarnadip Ghosh */
	/* 14 ----------------------------------------------------------------------------------------------------- */
	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/appTracking" */
	@POST
	@Path("/appTracking")
	@Consumes("application/json")
	@Produces("application/json")
	public Response appTracking(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		System.out.println(json);
		String retJsonStr = null;
		Response response = null;

		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
			String appid = (String) jObj.getString("appid");
			System.out.println("UserName : " + userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";

				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.appTracking(userName, appid);
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error in app tracking"); // Console
			retJsonStr = "{\"resultCode\":\"-4\"}";
			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			return response;
		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;

	}
	
	/*Getting data from master for collegeTypes*/
	@GET
	@Path("/getCollegeTypes")
	@Produces("application/json")
	public Response getCollegeTypes(@QueryParam("collegeName") String collegeName,
			@QueryParam("schemeID") String schemeID, @QueryParam("state") String state,
			@QueryParam("district") String district, @QueryParam("taluka") String taluka) {
		System.out.println("collegeName" + collegeName);
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPostMatricDetailsCollegeTypes(collegeName, schemeID, state, district, taluka);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("CollegeTypes :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
   
    /*Getting data from master for SSC District*/
	@GET
	@Path("/getDistrictForSSC")
	@Produces("application/json")
	public Response getSSCDistrict() {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getSSC_District();
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;

		// return retJsonStr;
	}
    
	@POST
	@Path("/documentRepository")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUserDocumentRepositoryList(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {

		String retJsonStr = null;
		Response response = null;

		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
			System.out.println("UserName : " + userName);

			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			ArrayList<DocumentEntity> documentList = null;
			DocumentRepositoryDetails docRepDt = new DocumentRepositoryDetails();
			documentList = docRepDt.getDocumentRepositoryDocuments(userName);
			Gson gson = new Gson();
			System.out.println(gson.toJson(documentList));
			retJsonStr = gson.toJson(documentList);

		} catch (Exception e) {

			System.out.println("Exception Error"); // Console
			retJsonStr = "{\"resultCode\":\"-1\"}";
		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
    
	//static String outputs;
	@GET
	@Path("/getSARALGRNData")
	@Produces("application/json")
	public String getSARALGRNData(@QueryParam("grn") String grn, @QueryParam("saral") String saral,
			@QueryParam("udise") String udise) {
		
		System.out.println("GRN:: " + grn);
		String output = null;
		StringBuilder sb = new StringBuilder();

		String districtName = "";
		String talukaName = "";
		JSONObject obj1 = new JSONObject();

		try {
			if(grn.equalsIgnoreCase("0000")){
				grn = "";
			}
			
			if(saral.equalsIgnoreCase("0000000000000000000")){
				saral = "";
			}
			
			PostMatricDetails plsd = new PostMatricDetails();
			JsonObject inputObj = new JsonObject();
			inputObj.addProperty("schcd", udise);
			inputObj.addProperty("student_id", saral);
			inputObj.addProperty("grn", grn);

			/*
			 * if(!"".equalsIgnoreCase(saral) && !"null".equalsIgnoreCase(saral)
			 * && saral != null){ inputObj.addProperty("student_id", saral);
			 * }else if(!"".equalsIgnoreCase(grn) &&
			 * !"null".equalsIgnoreCase(grn) && grn != null){
			 * inputObj.addProperty("student_id", grn); }else{ return
			 * "{\"Status\":\"Incorrect Input\"}"; }
			 */

			// DefaultHttpClient httpClient = new DefaultHttpClient();
			// String urlReq =
			// "https://student.maharashtra.gov.in/stud_db/Students/get_stud_info?grn="+grn+"&saralNo="+saral+"&udiseCode="+udise+"";
			/*
			 * HttpGet getRequest = new HttpGet(urlReq);
			 * getRequest.addHeader("accept", "application/json");
			 * 
			 * HttpResponse response = httpClient.execute(getRequest);
			 * 
			 * if (response.getStatusLine().getStatusCode() != 200) { throw new
			 * RuntimeException("Failed : HTTP error code : " +
			 * response.getStatusLine().getStatusCode()); }
			 * 
			 * BufferedReader br = new BufferedReader( new
			 * InputStreamReader((response.getEntity().getContent())));
			 * 
			 * String output; System.out.println("Output from Server .... \n");
			 * while ((output = br.readLine()) != null) {
			 * System.out.println(output); outputs = output ; }
			 * httpClient.getConnectionManager().shutdown();
			 */

			URL url = new URL("http://student.maharashtra.gov.in/stud_db_api/student_info/1.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(inputObj.toString().getBytes());
			os.flush();

			System.out.println(conn.getResponseCode() + " " + HttpURLConnection.HTTP_CREATED);
			if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			System.out.println("Output from Server .... \n");

			while ((output = br.readLine()) != null) {
				System.out.println(output);
				sb.append(output + "\n");
			}
			br.close();

			conn.disconnect();

			obj1 = new JSONObject(sb.toString());
			if (!obj1.getString("Student_Info").equalsIgnoreCase("No record found")) {

				JSONObject obj2;
				JSONObject obj3;
				obj2 = new JSONObject(obj1.getString("Student_Info"));

				try {
					System.out.println("Dist : " + obj2.getString("dist_nm"));
					System.out.println("Taluka : " + obj2.getString("taluka_nm"));

					districtName = obj2.getString("dist_nm");
					talukaName = obj2.getString("taluka_nm");

				} catch (JSONException jesxp) {
					jesxp.printStackTrace();
					districtName = "";
					talukaName = "";
				}

				output = "";

				if (districtName.isEmpty() || talukaName.isEmpty()) {
					output = plsd.getSchoolDistTaluka(udise);
					if (!"".equalsIgnoreCase(output) && !"null".equalsIgnoreCase(output) && output != null) {
						obj3 = new JSONObject(output);
						districtName = obj3.getString("District_Name");
						talukaName = obj3.getString("Taluka_Name");

						System.out.println(districtName + " : " + talukaName);
					}
				}

				obj2.put("dist_nm", districtName);
				obj2.put("taluka_nm", talukaName);

				obj1.remove("Student_Info");
				obj1.put("Student_Info", obj2);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "{\"Status\":\"-1\"}";
		} catch (IOException e) {
			e.printStackTrace();
			return "{\"Status\":\"-2\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"Status\":\"-2\"}";
		}

		System.out.println("" + obj1.toString());
		// return sb.toString();
		return obj1.toString();
	}
	
	 /*
     * This service will return notification for
     * logged in user.
     */
	@POST
	@Path("/getNotification")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getNotification(String json, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {

		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
			System.out.println("UserName : " + userName);

			ArrayList<NotificationEntity> listData = null;
			NotificationService notificationService = new NotificationService();
			listData = notificationService.getNotification(userName, httpServletRequest, httpServletResponse);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);

		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;

	}
    
    /*
     * This service will insert notification for
     * users.
     */
	@POST
	@Path("/setNotification")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap setNotification(NotificationEntity notificationEntity,
			@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {

		NotificationService notificationService = new NotificationService();
		HashMap resultMap = new HashMap();
		return notificationService.setNotification(notificationEntity, resultMap, httpServletRequest,
				httpServletResponse);
	}
    
    /*
     * This service will delete notification for
     * users.
     */
    @POST
	@Path("/deleteNotification")
    @Consumes("application/json")
	@Produces("application/json")
	public HashMap deleteNotification(String notificationSlNo, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {
    	
    	
		NotificationService notificationService = new NotificationService();
		HashMap resultMap = new HashMap();
		return notificationService.deleteNotification(notificationSlNo, resultMap, httpServletRequest, httpServletResponse);
	}
    
	@POST
	@Path("/getWorkFlowName")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getWorkFlowName(String json) {
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(json);
			
			String schemeid = (String) jObj.getString("schemeId");
			String stagecode = (String) jObj.getString("stagecode");
			
			ArrayList<Set_Get_Data_PostLogIn> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getWorkFlowName(schemeid, stagecode);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}
	
	@POST
	@Path("/getResidentDraftData")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getResidentDraftData(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		System.out.println(json);
		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);
			String userName = (String) jObj.getString("userName");
			System.out.println("UserName : " + userName);
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}
			ArrayList<Set_Get_Data_PendingCases> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.getResidentDraftData(userName);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Pending Case :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
			retJsonStr = "{\"resultCode\":\"-4\"}";
			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			return response;
		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
	
	@GET
	@Path("/getBarcodeData")
	@Produces("application/json")
	public String getBarcodeData(@QueryParam("barcode") String barcode, @QueryParam("dept") String dept,
			@QueryParam("serviceId") String serviceId) {
		JSONObject obj = new JSONObject();
		String output = "";
		try {
			// String dept = "Revenue";
			// String serviceId = "1284";
			// String barcode = "1751899991253600002062";

			String url = "http://testaaplesarkarapi.mahaonlinegov.in/api/SearchBarCode?deptName=" + dept + "&serviceId="
					+ serviceId + "&barCode=" + barcode + "";
			// ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(); // Client.create(config);
			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			} else {
				output = (String) response.readEntity(String.class);
				System.out.println("Output from Server .... \n" + output);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return output.toString();
	}
	
	/**
	 * This service is not in use as of now.
	 * @param json
	 * @return
	 */
	/*@POST
	@Path("/setCasteDataINDoc")
	@Produces("application/json")
	public Response setCasteDataINDoc(String json) {
		
		String retJsonStr = null;
		String data = null;
		try {
			JSONObject jObj = new JSONObject(json);

			String appid = (String) jObj.getString("appid");
			System.out.println("appId : " + appid);

			String doctype = (String) jObj.getString("doctype");
			System.out.println("doctype : " + doctype);

			String docPath = (String) jObj.getString("docPath");
			System.out.println("docPath : " + docPath);

			String userID = (String) jObj.getString("userID");
			System.out.println("userID : " + userID);

			String docFlag = (String) jObj.getString("docFlag");
			System.out.println("docFlag : " + docFlag);

			String rtsFlag = (String) jObj.getString("rtsFlag");
			System.out.println("rtsFlag : " + rtsFlag);

			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			data = plsd.setCasteDataINDoc(appid, doctype, docPath, userID, docFlag, rtsFlag);
			Gson gson = new Gson();
			System.out.println(gson.toJson(data));
			retJsonStr = gson.toJson(data);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		return response;
	}*/
	
	/**
	 * This method is to verify and generate OTP for Forgot Password functionality
	 * @param json
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return JSON
	 */
	@POST
	@Path("/forgotUserIdPassword")
	@Consumes("application/json")
	@Produces("application/json")
	public Response forgotUserIdPassword(String jsonInput, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {
		
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	ForgotPassword fgtpwd = new ForgotPassword();
    	resultMap = fgtpwd.verifyUserDetails(jsonInput, resultMap);
    	Gson gson = new Gson();
    	Response response = Response.status(200).entity(gson.toJson(resultMap).toString()).header("Access-Control-Allow-Origin", "*").build(); 
		return response;
	}
    
    @POST
	@Path("/verifyOTP")
    @Consumes("application/json")
	@Produces("application/json")
	public Response verifyOTP(String json, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {
		
    	JsonObject resultObj = new JsonObject();
    	
    	/*if (httpServletRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
            	resultString = "{\"resultCode\":\"-1\"}";
            }
            else {*/
            	try {
        			
        			JSONObject jObj = new JSONObject(json);
        			String userId = (String)jObj.getString("UserId");
        			String type = (String)jObj.getString("OTPType");
        			String userInput = (String)jObj.getString("UserInput");
        			Check_Login_Credential clc = new Check_Login_Credential();
        			
        			if("".equalsIgnoreCase(type) || type == null || "".equalsIgnoreCase(userInput) || userInput == null ||
        					"".equalsIgnoreCase(userId) || userId == null)
        				resultObj.addProperty("resultCode", "-2");
        			else {
        				
        				DB_GetUserData dao = new DB_GetUserData();
        				JsonObject otpStatus = dao.verifyOTP(userId, type, userInput);
        				if(otpStatus.get("VerifyStatus") == null) {
    						resultObj.addProperty("resultCode", "-3");
    					}
    					else if(otpStatus.get("VerifyStatus").getAsString().equalsIgnoreCase("N")) {
    						resultObj.addProperty("resultCode", "0");
    						resultObj.addProperty("VerifyStatus", "N");
    					}
    					else if(otpStatus.get("VerifyStatus").getAsString().equalsIgnoreCase("Y")) {
    						resultObj.addProperty("resultCode", "0");
    						resultObj.addProperty("VerifyStatus", "Y");
    						resultObj.addProperty("ExpiryStatus", otpStatus.get("ExpiryStatus").getAsString());
    					}
        			}
        			
        		}
        		catch (Exception e) {
        			
        			System.out.println("Exception Error"); //Console 
        			resultObj.addProperty("resultCode", "-1");
        		}
            /*}
        }*/

		Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*").build(); 
		return response;
	}
    
	@POST
	@Path("/setNewPassword")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap setNewPassword(CommonEntity commonEntity) {
		ChangePasswordService changePass = new ChangePasswordService();
		HashMap resultMap = new HashMap();

		return changePass.setNewPassword(commonEntity, resultMap);
	}
    
	/**
	 * This service is used to get the list of all the schemes applied by current logged in user.
	 * url: "http://localhost:8080/NGPortalWS/portal/dbtPortal/getUserAppliedSchemeDetails"
	 * 
	 * @author Swarnadip Ghosh, Varun Saddi
	 * @since 2017-06-06
	 * @param json
	 * @param httpRequest
	 * @param httpResponse
	 * @return JSONObject
	 */
	@POST
	@Path("/getUserAppliedSchemeDetails")
	@Produces("application/json")
	public HashMap<String, Object> getUserAppliedSchemeDetails(@Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {

		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//Check Token Validity
			if (httpRequest.getHeader("token") != null) {
	            token = AppConstants.validateJWT(httpRequest.getHeader("token"));
	            if (token.contains("Invalid Token")) {
	            	resultMap.put("resultCode", -2);
        			return resultMap;
	            }
	        } else {
	        	resultMap.put("resultCode", -2);
    			return resultMap;
	        }	
			
			//Get UserId from Token
			Claims claims;
			String userId = null;
			
		    claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
		    userId = claims.get("userid", String.class);
				System.out.println("userid : "+userId);
			
			//Get the list of all applied schemes
			DB_GetUserData dao = new DB_GetUserData();
			resultMap = dao.getUserAppliedSchemeDetails(userId, resultMap);
			if(resultMap.get("resultCode").toString().equalsIgnoreCase("0")) {
				//Get the list of all grouped options
				resultMap = dao.getSchemeGroupingOptions(resultMap);
			}

		} catch (Exception e) {
			System.out.println("Exception in /getUserAppliedSchemeDetails service"); // Console
			e.printStackTrace();
			httpResponse.setHeader("token", token);
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", e.getMessage());
			return resultMap;
		}
		
		httpResponse.setHeader("token", token);
		return resultMap;
	}
	
	/* Getting data from Preference Drop Down */
	@GET
	@Path("/getPreferenceDropDownValue")
	@Produces("application/json")
	public Response getPreferenceDropDownValue(@QueryParam("user_Name") String user_Name) {
		String retJsonStr = null;
		try {
			ArrayList<String> listData = null;
			PostMatricDetails plsd = new PostMatricDetails();
			listData = plsd.getPreferenceDropDownValue(user_Name);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
    /**
     * Barti Integration
     * @throws JSONException 
     */
	@GET
	@Path("/getBartiData")
	@Produces("application/json")
	public String getBartiStatus(@QueryParam("name") String name, @QueryParam("vcNumber") String vcNumber,
			@QueryParam("caste") String caste, @QueryParam("fatherName") String fatherName) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		try {
			DbtaspServiceSoapProxy ob = new DbtaspServiceSoapProxy();
			/**
			 * name, VC , Caste, Father's name
			 */
			int status = ob.VCAvailable(name, vcNumber, caste, fatherName);
			System.out.println("status  is " + status);

			jsonObj.put("status", status);
			System.out.println(jsonObj.toString());

			/**
			 * output 0 = Data not found in barti database output 1 = Data found
			 * output 2 = invalid input
			 */

		} catch (Exception e) {

			jsonObj.put("status", "Error While Connecting");
			return jsonObj.toString();
			// e.printStackTrace();

		}
		return jsonObj.toString();

	}

	@GET
	@Path("/getBoardIntegrationData")
	@Produces("application/json")
	public String getBoardIntegrationData(@QueryParam("doctype") String doctype, @QueryParam("name") String name,
			@QueryParam("year") String year, @QueryParam("rollno") String rollno,
			@QueryParam("session") String exsession, @QueryParam("totalmarks") String totalmarks,
			@QueryParam("userID") String userID, @QueryParam("fileType") String fileType,
			@QueryParam("schemeID") String schemeID, @QueryParam("appid") String appid,
			@QueryParam("schemeType") String schemeType, @QueryParam("userIndex") String userIndex)
			throws JSONException {
		InputStream is = null;
		JSONObject jsonObj = new JSONObject();
		JSONObject valuesObject = new JSONObject();
		JSONArray list = new JSONArray();
		String fileLocation = "";

		try {
			/*
			 * String doctype = "SSCER"; String name = "Kanifnath Yewale";
			 * String year = "2008"; String rollno = "C144823"; String exsession
			 * = "MAR"; String totalmarks = "447";
			 */

			System.out.println(
					doctype + " : " + name + " : " + year + " : " + rollno + " : " + exsession + " : " + totalmarks);
			jsonObj = BoardIntegartion.sendReuest(doctype, name, year, rollno, exsession, totalmarks);

			// change on 6th July
			if (jsonObj == null) {
				return "{\"flag\" : \"-1\"}";
			}

			System.out.println("output : " + jsonObj.toString());
			String str = jsonObj.getString("DocContent");
			System.out.println(str);

			File docDirectory = null;
			try {
				docDirectory = new File(InitConfigProp.getProperty("documentpath") + File.separatorChar + "Applications"
						+ File.separatorChar + userID + File.separatorChar + "Documents");
			} catch (Exception e) {
				System.out.println("Placing document directory not found !!");
				e.printStackTrace();
			}

			if (doctype.equalsIgnoreCase("SSC")) {
				fileLocation = docDirectory + File.separator + "SSC_Certificate.pdf";
				fileType = "SSCCertificate";

			} else if (doctype.equalsIgnoreCase("HSC")) {
				fileLocation = docDirectory + File.separator + "HSC_Certificate.pdf";
				fileType = "HSCCertificate";
			}

			System.out.println("Location :: " + fileLocation);
			byte[] data = Base64.decodeBase64(str);
			is = new ByteArrayInputStream(data);

			String documentPath = saveOnDisk(is, null, userID, docDirectory, fileLocation);
			PostMatricDetails plsd = new PostMatricDetails();
			String uploadStatus = plsd.addDocumentsDetails(schemeID, userID, documentPath, fileType, appid, schemeType,
					userIndex, name, "Y");

			if ("false".equalsIgnoreCase(uploadStatus)) {
				return "{\"flag\" : \"-2\"}";
			}

			// change on 6th July
			valuesObject.put("location", fileLocation);
			list.put(valuesObject);
			jsonObj.accumulate("values", list);

			return jsonObj.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"-1\"}";
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					return "{\"flag\" : \"-2\"}";
				}
			}
		}
		/*
		 * valuesObject.put("location",fileLocation); list.put(valuesObject);
		 * jsonObj.accumulate("values", list);
		 * 
		 * return jsonObj.toString();
		 */
	}
    
	@POST
	@Path("/getDocumentRepositoryMasterDocTypeList")
	@Produces("application/json")
	public Response getDocumentRepositoryMasterDocTypeList(@Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {

		String retJsonStr = null;
		Response response = null;

		try {
			if (httpRequest.getHeader("token") != null) {
				System.out.println("Inside TOkem");
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {
				System.out.println("Inside Else TOkem");
				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			ArrayList<DocumentEntity> documentList = null;
			DocumentRepositoryDetails docRepDt = new DocumentRepositoryDetails();
			documentList = docRepDt.get_DocumentRepository_MasterDocTypeList();
			Gson gson = new Gson();
			// System.out.println(gson.toJson(documentList));
			retJsonStr = gson.toJson(documentList);
		} catch (Exception e) {

			System.out.println("Exception Error"); // Console
			retJsonStr = "{\"resultCode\":\"-1\"}";
		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
    
	@POST
	@Path("/addNewDocumentToUserRepository")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public Response addNewDocumentToUserRepository(FormDataMultiPart formParams,
			@FormDataParam("formData") String docDetails, @FormDataParam("userId") String userId,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) {
		String retJsonStr = null;
		Response response = null;
		System.out.println("****" + formParams.toString());
		try {
			JSONObject jsonData = new JSONObject(docDetails);

			if (httpRequest.getHeader("token") != null) {
				System.out.println("Inside TOkem");
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {
				System.out.println("Inside Else TOkem");
				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			DocumentRepositoryDetails docRepDt = new DocumentRepositoryDetails();
			JsonObject result = docRepDt.addDocumentTo_UserDocumentRepository(userId, jsonData, formParams);
			Gson gson = new Gson();
			// System.out.println(gson.toJson(documentList));
			retJsonStr = gson.toJson(result);
		} catch (Exception e) {

			System.out.println("Exception Error"); // Console
			retJsonStr = "{\"resultCode\":\"-1\"}";
		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}

	@POST
	@Path("/updateApplicationPreferenceOnApproval")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update_ApplicationPreference_On_Approval(String jsonString,
			@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {

		// Initializing return object
		JsonObject resultObj = new JsonObject();

		// Parse the input params for rejected appid
		JsonObject jObj = new JsonObject();
		jObj = jObj.getAsJsonObject("AppRejectInfo");
		String appId = jObj.get("AppId").getAsString();
		int appPrefOrder = -1;
		try {
			appPrefOrder = Integer.parseInt(jObj.get("PreferenceOrder").getAsString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		}
		String appStatus = jObj.get("Status").getAsString();

		if (appId == null || appStatus == null || "".equalsIgnoreCase(appId) || "".equalsIgnoreCase(appStatus)
				|| appPrefOrder < 1) {
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		} else {
			if (appStatus.equalsIgnoreCase("Reject")) {
				Escholarship_Application_Operations escAppOp = new Escholarship_Application_Operations();
				resultObj = escAppOp.upadate_Pref_AppId(appId, appPrefOrder, appStatus);
			} else {
				resultObj.addProperty("ResulCode", -2);
				resultObj.addProperty("Message", "Not a reject case.");
			}
		}

		Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*")
				.build();
		return response;
	}
    
	@POST
	@Path("/updateApplicationPreferenceOnRejection")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update_ApplicationPreference_On_Rejection(String jsonString,
			@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {

		// Initializing return object
		JsonObject resultObj = new JsonObject();

		// Parse the input params for rejected appid
		JsonObject jObj = new JsonObject();
		jObj = jObj.getAsJsonObject("AppRejectInfo");
		String appId = jObj.get("AppId").getAsString();
		int appPrefOrder = -1;
		try {
			appPrefOrder = Integer.parseInt(jObj.get("PreferenceOrder").getAsString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		}
		String appStatus = jObj.get("Status").getAsString();

		if (appId == null || appStatus == null || "".equalsIgnoreCase(appId) || "".equalsIgnoreCase(appStatus)
				|| appPrefOrder < 1) {
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		} else {
			if (appStatus.equalsIgnoreCase("Reject")) {
				Escholarship_Application_Operations escAppOp = new Escholarship_Application_Operations();
				resultObj = escAppOp.upadate_Pref_AppId(appId, appPrefOrder, appStatus);
			} else {
				resultObj.addProperty("ResulCode", -2);
				resultObj.addProperty("Message", "Not a reject case.");
			}
		}

		Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*")
				.build();
		return response;
	}
	
/*	@POST
	@Path("/updateApprovedSchemePreference")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateApprovedSchemePreference(String jsonString,
			@Context HttpServletRequest httpServletRequest, @Context HttpServletResponse httpServletResponse) {

		String status[]={ "Approved","Rejected" };
		// Initializing return object
		JsonObject resultObj = new JsonObject();
		
		// Parse the input params for rejected appid
		JSONObject jObj = new JSONObject(jsonString);
		String appId = (String) jObj.getString("appid");

		if (appId == null || "".equalsIgnoreCase(appId)){
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		} else {			
				Escholarship_Application_Operations escAppOp = new Escholarship_Application_Operations();
				resultObj = escAppOp.updateApprovedSchemePreference(status[0]);
			}
		}

		Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*")
				.build();
		return response;
	}*/
    
    /* Created by Swarnadip Ghosh */
   	/* 21 ----------------------------------------------------------------------------------------------------- */
   	/* url = "http://localhost:8080/NGPortalWS/portal/dbtPortal/updateUserAppliedSchemePreference" */
	@POST
	@Path("/updateUserAppliedSchemePreference")
	@Produces("application/json")
	public Response updateUserAppliedSchemePreference(String json, @Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		System.out.println(json);
		String retJsonStr = null;
		Response response = null;
		try {
			JSONObject jObj = new JSONObject(json);

			String app_id = (String) jObj.getString("app_id");
			System.out.println("app_id : " + app_id);

			String preference = (String) jObj.getString("preference");
			System.out.println("preference : " + preference);

			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					retJsonStr = "{\"resultCode\":\"-4\"}";
					response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*")
							.build();
					return response;
				}
			} else {

				retJsonStr = "{\"resultCode\":\"-4\"}";
				response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
				return response;
			}

			ArrayList<String> listData = null;
			PostLogin_StudentDetails plsd = new PostLogin_StudentDetails();
			listData = plsd.updateUserAppliedSchemePreference(app_id, preference);
			Gson gson = new Gson();
			System.out.println(gson.toJson(listData));
			retJsonStr = gson.toJson(listData);

		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
			e.printStackTrace();
			retJsonStr = "{\"resultCode\":\"-1\"}";

			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
			httpResponse.setHeader("token", token);
			return response;

		}

		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		return response;
	}
   	
  	/**
  	 * This servic is used to get verification remarks on application.
  	 * 
  	 * @author Varun Saddi
  	 * @since 2017-07-18
  	 * @param json
  	 * @return
  	 * @throws org.codehaus.jettison.json.JSONException
  	 */
  	@POST
	@Path("/getVerifyRemarkHistoryClient")
	@Consumes("application/json")
	@Produces("application/json")
	public Response get_VerifyRemarkHistory_Client(String json) throws org.codehaus.jettison.json.JSONException {
		
    	String ibpsServerIp = "";
		String ibpsServerport = "";
		String protocol = "";
		
		try {
			ConfProperty property= new ConfProperty();
			ibpsServerIp = ConfProperty.getProperty("ServerIP");
			ibpsServerport = ConfProperty.getProperty("IbpsServerPort");
			protocol = ConfProperty.getProperty("Protocol");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		final String BASE_URI = protocol + "://" + ibpsServerIp + ":" + ibpsServerport + 
				"/SJ_Pre_Matric_RemarksHistoryWS/webresources/genericPreMatric/post_VerificationHistory";
		String output, jsonOutput = null;
		
		try {
			JSONObject jObj = new JSONObject(json);
			String ibpsRegNo = jObj.getString("ibpsRegNo");
			String schemeId = jObj.getString("schemeId");
			String userRoleId = jObj.getString("userRoleId");				
			jsonObj = new JSONObject();	
			jsonObj.put("regNo", ibpsRegNo);
			jsonObj.put("schemeId", schemeId);
			jsonObj.put("roleId", userRoleId);
		
            URL url = new URL(BASE_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);

            String input = jsonObj.toString();
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                  throw new RuntimeException("Failed : HTTP error code : "
                         + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                         (conn.getInputStream())));

            while ((output = br.readLine()) != null) {
                  jsonOutput = output;
            }
            
            System.out.println("Verification Remarks Output>> : "+jsonOutput);
            conn.disconnect();

		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		Response fResponse = Response.status(200).entity(jsonOutput).header("Access-Control-Allow-Origin", "*").build(); 
		return fResponse;
	}
  
   	@POST
	@Path("/get_InstituteProcessedApplications")
    @Consumes("application/json")
	@Produces("application/json")
	public Response get_InstituteProcessedApplications(String jsonInput, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {
    	
    	//Initializing return object
    	JsonParser parser = new JsonParser();
    	JsonObject inputObj = new JsonObject();
    	JsonObject resultObj = new JsonObject();
    		System.out.println("jsonInput>>"+jsonInput); 
    	inputObj = parser.parse(jsonInput).getAsJsonObject();
    	//Parse the input params for status
		String appStatus = inputObj.get("AppStatus").getAsString();
		
		if(appStatus == null || "".equalsIgnoreCase(appStatus)) {
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		}
		else {
			IBPS_Supported_Operations ibspSuppOp = new IBPS_Supported_Operations();
			resultObj = ibspSuppOp.get_InstituteProcessedApplications(appStatus);
		}
    	
    	Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*").build(); 
		return response;
    }
    
    @POST
	@Path("/update_ProcessedApplicationStatus")
    @Consumes("application/json")
	@Produces("application/json")
	public Response update_ProcessedApplicationStatus(String jsonInput, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {
    	
    	//Initializing return object
    	JsonParser parser = new JsonParser();
    	JsonObject inputObj = new JsonObject();
    	JsonObject resultObj = new JsonObject();
    	
    	//Parse the input params for status
    		System.out.println("jsonInput>>"+jsonInput);
    	inputObj = parser.parse(jsonInput).getAsJsonObject();
		String appStatus = inputObj.get("AppStatus").getAsString();
		String authorityId = inputObj.get("AuthorityId").getAsString();
		String ibps_registrationNo = inputObj.get("RegistrationNo").getAsString();
		
		if(appStatus == null || "".equalsIgnoreCase(appStatus) || authorityId == null || "".equalsIgnoreCase(authorityId)
				|| ibps_registrationNo == null || "".equalsIgnoreCase(ibps_registrationNo)) {
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Input Params are blank.");
		}
		else {
			IBPS_Supported_Operations ibspSuppOp = new IBPS_Supported_Operations();
			resultObj = ibspSuppOp.update_ProcessedApplicationStatus(appStatus, authorityId, ibps_registrationNo);
		}
    	
    	Response response = Response.status(200).entity(resultObj.toString()).header("Access-Control-Allow-Origin", "*").build(); 
		return response;
    }
	
    
    /* Added By Swarnadip Ghosh at 14-Jul-2017 */
    /* Checking Scheme is already exists in DB */
	@GET
	@Path("/checkSchemeExistance")
	@Produces("application/json")
	public Response checkSchemeExistance(@QueryParam("schemeType") String schemeType,
			@QueryParam("userFullName") String userFullName,
			@QueryParam("schemeId") String schemeId,
			@QueryParam("userIndex") String userIndex,
			@Context HttpServletRequest httpRequest,@Context HttpServletResponse httpResponse) 
	{
		String retJsonStr = null;
		Response response = null;
		
		//Claims claims = new DefaultClaims();
		/*Claims claims;
		String userName = null;
		
	    claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
	    userName = claims.get("username", String.class);
		System.out.println("userName : "+userName);*/
		
		try 
		{
			
			if (httpRequest.getHeader("token") != null) {
	            token = AppConstants.validateJWT(httpRequest.getHeader("token"));
	            if (token.contains("Invalid Token")) {
	            	retJsonStr="{\"resultCode\":\"-4\"}";
        			response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build(); 
        			return response;
	            }
	        } else {
		        		
		        	retJsonStr="{\"resultCode\":\"-4\"}";
		        	response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build(); 
        			return response;
	        }
			
			ApplySchemeService ass = new ApplySchemeService();
			JsonObject result = ass.checkSchemeExistance(schemeType, userFullName, schemeId, userIndex);
			Gson gson = new Gson();

			retJsonStr = gson.toJson(result);
		
		}catch (Exception e) {
			System.out.println("Exception Error");
			httpResponse.setHeader("token",token);

		}
		response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();
		httpResponse.setHeader("token", token);
		System.out.println("response : " + response);
		return response;
	}
	
	/**
	 * This method is used to update Aadhaar or NonAadhaar profile to Aadhaar Profile
	 * 
	 * @param aadharRegistrationEntity_data
	 * @param httpRequest
	 * @param httpResponse
	 * @return
	 */
	@POST
	@Path("/updateProfileUsingAadhar")
	@Consumes("application/json")
	@Produces("application/json")
	public HashMap<String, Object> updateProfileUsingAadhar(AadhaarRegistrationEntity aadharRegistrationEntity_data,
			@Context HttpServletRequest httpRequest, @Context HttpServletResponse httpResponse) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			// Check Token Validity
			if (httpRequest.getHeader("token") != null) {
				token = AppConstants.validateJWT(httpRequest.getHeader("token"));
				if (token.contains("Invalid Token")) {
					resultMap.put("resultCode", -2);
					return resultMap;
				}
			} else {
				resultMap.put("resultCode", -2);
				return resultMap;
			}

			// Get UserId from Token
			Claims claims;
			String userid = null;

			claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
			userid = claims.get("userid", String.class);
			System.out.println("userid : " + userid);

			UpdateProfileService updateProfileService = new UpdateProfileService();
			// Call Update Method for Profile
			resultMap = updateProfileService.updateProfileUsingAadhar(aadharRegistrationEntity_data, userid,
					resultMap);

		} catch (Exception e) {
			System.out.println("Exception Error");
			httpResponse.setHeader("token", token);
			resultMap.put("resultCode", -1);
			resultMap.put("resultDetails", e.getMessage());
			return resultMap;
		}

		httpResponse.setHeader("token", token);
		return resultMap;
	}
	
	/**
	 * This web service is using to pull the application details from PORTAL, need for IBPS process . 
	 * @author : Swarnadip Ghosh
	 * @since : 2017-07-18
	 */
    @POST
	@Path("/getPortalApplicationDetailsForIBPS")
	@Consumes("application/json")
	@Produces("application/json")
	public String getPortalApplicationDetailsForIBPS(String json) {
		String jsonReturn = null;
		ArrayList<Set_Get_Data_PortalToIBPS> listData = null;
		try {
			String limit = "";
			JSONObject jObj = new JSONObject(json);

			try {
				limit = (String) jObj.getString("limit");
			} catch (Exception e) {
				limit = "";
			}
			
			System.out.println("Limit :" + limit);
			IBPS_Supported_Operations portalToIBPS = new IBPS_Supported_Operations();
			listData = portalToIBPS.getPortalApplicationDetailsForIBPS(limit);

			Gson gson = new Gson();
			jsonReturn = gson.toJson(listData);

			System.err.println(jsonReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonReturn;
	}
    
	/**
	 * This web service is using to esc_application_tracker flag . 
	 * @author : Swarnadip Ghosh
	 * @since : 2017-07-18
	 */
    @POST
	@Path("/updatTrackerTableFlag")
	@Consumes("application/json")
	@Produces("application/json")
	public String updatTrackerTableFlag(String json) {
		String jsonReturn = null;
		JsonObject resultObj = new JsonObject();
		try {
			String app_id = "";
			JSONObject jObj = new JSONObject(json);

			try {
				app_id = (String) jObj.getString("app_id");
				System.out.println("app_id : "+app_id);
			} catch (Exception e) {
				app_id = "";
			}
			IBPS_Supported_Operations updateTracker = new IBPS_Supported_Operations();
			JsonObject result = updateTracker.updatTrackerTableFlag(app_id);
			Gson gson = new Gson();
			jsonReturn = gson.toJson(result);
			System.out.println(jsonReturn);
		} catch (Exception e) {
			e.printStackTrace();
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Some exception occured while updating application flag.");
		}

		return jsonReturn;
	}	
    
	/**
	 * This web service is using to pull updated application details from PORTAL, need for IBPS process . 
	 * @author : Swarnadip Ghosh
	 * @since : 2017-07-19
	 */
	@POST
	@Path("/getPortalApplicationDetailsForIBPS_Update")
	@Consumes("application/json")
	@Produces("application/json")
	public String getPortalApplicationDetailsForIBPS_Update(String json) {
		String jsonReturn = null;
		JsonObject resultObj = new JsonObject();
		ArrayList<Set_Get_Data_PortalToIBPS> listData = null;
		try {
			String limit = "";
			JSONObject jObj = new JSONObject(json);

			try {
				limit = (String) jObj.getString("limit");
			} catch (Exception e) {
				limit = "";
			}

			System.out.println("Limit :" + limit);
			IBPS_Supported_Operations portalToIBPS = new IBPS_Supported_Operations();
			listData = portalToIBPS.getPortalApplicationDetailsForIBPS_Update(limit);

			Gson gson = new Gson();
			jsonReturn = gson.toJson(listData);

			System.err.println(jsonReturn);
		} catch (Exception e) {
			e.printStackTrace();
			resultObj.addProperty("ResulCode", -1);
			resultObj.addProperty("Message", "Some exception occured while pulling updated flag data.");
		}

		return jsonReturn;
	}    
    
    private void saveToDisk(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
		String fileName = fileDetail.getFileName();
		System.out.println("FileName :: " + fileName);

		File docDirectory = new File(System.getProperty("user.dir") + File.separatorChar + "dbt" + File.separatorChar
				+ "newRegistration" + File.separatorChar + "uid.as");
		String fileLocation = docDirectory + File.separator + fileName;
		if (!docDirectory.exists()) {
			boolean result = false;
			try {
				docDirectory.mkdirs();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
		try {
			OutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    private String saveOnDisk(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, String userID,
			File docDirectory, String fileLocation) {
		/*
		 * String fileName = fileDetail.getFileName();
		 * System.out.println("FileName :: " + fileName);
		 */

		// File docDirectory = new File(System.getProperty("user.dir") +
		// File.separatorChar + "dbt" + File.separatorChar + "Applications" +
		// File.separatorChar + userID + File.separatorChar + "Documents");
		// File docDirectory = new File(".." + File.separatorChar + ".." +
		// File.separatorChar + ".." + File.separatorChar + "dbt" +
		// File.separatorChar + "Applications" + File.separatorChar + userID +
		// File.separatorChar + "Documents");
		// String fileLocation = docDirectory + File.separator + fileName;
		System.out.println("fileLocation :: " + fileLocation);
		if (!docDirectory.exists()) {
			boolean result = false;
			try {
				docDirectory.mkdirs();
				result = true;
			} catch (SecurityException se) {
				System.out.println("Exception occurred !!");
				System.out.println("Failed to create directory !!");
				se.printStackTrace();
			}
			if (result) {
				System.out.println("Documents Directory created sussessfully !!");
			}
		}
		try {
			OutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileLocation;
	}
    /**
     * This function is used to update application status and stage code .
     * @author Swarnadip Ghosh
     * @since 24-Jul-2017
     * @param jsonString
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @POST
	@Path("/updateApprovedSchemesStatusStageCode")
	@Consumes("application/json")
	@Produces("application/json")
    public String updateApprovedSchemes_Status_StageCode(String jsonString, @Context HttpServletRequest httpServletRequest,
			@Context HttpServletResponse httpServletResponse) {

		//String status[] = { "Approve", "Reject" };
    	//String stageflag[] = { "institute", "department" };
		// Initializing return object
		JsonObject resultObj = new JsonObject();
		String appId;
		String decesion;
		String stageflag;
		try {
			JSONObject jObj = new JSONObject(jsonString);
			appId = (String) jObj.getString("appid");
			decesion = (String) jObj.getString("decesion");
			stageflag = (String) jObj.getString("stageflag");
			
			if (appId == null || "".equalsIgnoreCase(appId)) {
				resultObj.addProperty("ResulCode", -1);
				resultObj.addProperty("Message", "Input Params are blank.");
			} else {
				Escholarship_Application_Operations escAppOp = new Escholarship_Application_Operations();
				resultObj = escAppOp.updateApprovedSchemePreference(appId, decesion, stageflag);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			resultObj.addProperty("ResulCode", -3);
			resultObj.addProperty("Message", "Exception Occured while processing .");
			e.printStackTrace();
		}
		System.out.println("resultObj : " + resultObj.toString());
		return resultObj.toString();
	}
    
  //Changed on 25th July 2017
    @POST
	@Path("/getCollegesForEducationDetails")
	@Consumes("application/json")
	@Produces("application/json")
	public String getCollegesFromDB(String details) {
		
    	String jsonReturn = null;
		JsonObject resultObj = new JsonObject();
		ArrayList<String> listData = null;
		try {
			JSONObject jObj = new JSONObject(details);

			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID" + schemeID);
			String state = (String) jObj.getString("state");
			System.out.println("state" + state);
			String district = (String) jObj.getString("district");
			System.out.println("district" + district);
			String taluka = (String) jObj.getString("taluka");
			System.out.println("taluka" + taluka);
			String courseType = (String) jObj.getString("courseType");
			System.out.println("courseType" + courseType);
			
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData = dao.getCollegeForCourseTypes(schemeID,state,district,taluka,courseType);

			Gson gson = new Gson();
			jsonReturn = gson.toJson(listData);

			System.err.println(jsonReturn);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonReturn;
	}   
    
    //change on 25th July 2015
    @POST
	@Path("/getCoursesForLastEducationDetails")
	@Produces("application/json")
	public Response getCoursesLastEduDetails(String collegeDetails) {
		String retJsonStr = null;
		try {
			JSONObject jObj = new JSONObject(collegeDetails);
			String collegeName = (String) jObj.getString("collegeName");
			System.out.println("collegeName : " + collegeName);
			
			String schemeID = (String) jObj.getString("schemeID");
			System.out.println("schemeID : " + schemeID);
			
			String state = (String) jObj.getString("state");
			System.out.println("state : " + state);
			
			String district = (String) jObj.getString("district");
			System.out.println("district : " + district);
			
			String taluka = (String) jObj.getString("taluka");
			System.out.println("taluka : " + taluka);
			
			String courseType = (String) jObj.getString("courseType");
			System.out.println("courseType : " + courseType);
			
			ArrayList<Set_Get_Data_ApplicationForm> listData = null;
			DB_GetPostMatricData dao = new DB_GetPostMatricData();
			listData = dao.getCoursesForLastYearDetails(collegeName, schemeID, state, district, taluka, courseType);
			Gson gson = new Gson();
			retJsonStr = gson.toJson(listData);
			System.out.println("Courses :: " + retJsonStr);
		} catch (Exception e) {
			System.out.println("Exception Error");
		}

		Response response = Response.status(200).entity(retJsonStr).header("Access-Control-Allow-Origin", "*").build();

		return response;
	}
    
}
