/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DB_GetPostMatricData.java
* Author              : Ankit Katoch/Ankit Bhasin/Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : Jan 18, 2017
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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.newgen.cig.service.ApplySchemeService;
import com.newgen.dbt.commons.DatabaseQuery;
import com.newgen.dto.Set_Get_Data_ApplicationForm;
import com.newgen.utility.AppConstants;
import com.newgen.utility.ImageEncoderUtility;

public class DB_GetPostMatricData {
	
	Connection conn = null ;
	PreparedStatement ps = null;
	ResultSet rs = null ;
	InitialContext Context;
    DataSource ds;
	DatabaseQuery databaseQ = new DatabaseQuery();
    
	public String getSchoolDistTaluka(String udiseCode){	
		String jsonStr = "";
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String query = databaseQ.getSchoolDistrictTaluka();
            try(Connection conn = ds.getConnection();){
            	System.out.println("udiseCode: " + udiseCode);
            	try(PreparedStatement ps = conn.prepareStatement(query);){
                    ps.setString(1,udiseCode);
                   
            		
            		try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							String temp2 = rs.getString(2);
							
							jsonStr = "{\"District_Name\":\"" + temp1 +  "\", \"Taluka_Name\":\"" + temp2 +  "\"}";
							System.out.println(jsonStr);
						}
            		}
        		}
            }
		}
		catch(Exception e)
		{
			 e.printStackTrace();
		}
		return jsonStr;
	}
	
	public ArrayList<String> getCourseYearFromDB(String schemeID, String collegeName, String  state, String  district, String taluka, 
    		String  university, String  grantType, String courseName) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String query = databaseQ.getDuration();
            try(Connection conn = ds.getConnection();){
            	
            	try(PreparedStatement ps = conn.prepareStatement(query);){
                    ps.setString(1,collegeName);
                    ps.setString(2,courseName);
                    ps.setString(3,university);
                    ps.setString(4,grantType);
                    ps.setString(5,state);
                    ps.setString(6,district);
                    ps.setString(7,taluka);
                    ps.setString(8,schemeID);
                    
            		try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							int temp2 = Integer.parseInt(temp1);
							temp2 = temp2/12;
							if(temp2 <= 1){
								temp2 = 1;
							}else if(temp2 <= 2 && temp2 > 1){
								temp2 = 2;
							}else if(temp2 <= 3 && temp2 > 2){
								temp2 = 3;
							}else if(temp2 <= 4 && temp2 > 3){
								temp2 = 4;
							}else if(temp2 <= 5 && temp2 > 4){
								temp2 = 5;
							}
							for(int i=1;i<=temp2;i++){
								temp1 = Integer.toString(i);
								listData.add(temp1);
							}
						}
            		}
        		}
            }
		}
		catch(Exception e)
		{
			 e.printStackTrace();
		}

		return listData;
	}
    
	//change on 7th July 2017
    //change on 24th July 2017
    public ArrayList<String> getSpecificStates(String schemeID) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String ps_getStates=null;
            if("22".equalsIgnoreCase(schemeID) || "23".equalsIgnoreCase(schemeID) || "24".equalsIgnoreCase(schemeID)){
        		ps_getStates= databaseQ.getStateForTechnical();
			
        	}else if("10".equalsIgnoreCase(schemeID) || "11".equalsIgnoreCase(schemeID) || "20".equalsIgnoreCase(schemeID)){
        		
        		ps_getStates= databaseQ.getStateForAll();
        	}else if("36".equalsIgnoreCase(schemeID)){
        		
        		ps_getStates= databaseQ.getStateForDelhi();
        	}else{
        		
        		ps_getStates= databaseQ.getStateForMaharashtra();
        	}
            	
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps=conn.prepareStatement(ps_getStates)){
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
					}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
    
    public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDataFromDBSchoolName(String schemeID, String district, String taluka) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = new ArrayList<Set_Get_Data_ApplicationForm>();
		
		String query = "";
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            
            try(Connection conn = ds.getConnection();){
            	
            	if("12".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getSainikSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else if("34".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getPurvauchaSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else if("6".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getNineTenthSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else if("35".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getAttendanceAllowanceSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else if("8".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getPreMeritSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else if("21".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getPreMatricHandicapSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else if("40".equalsIgnoreCase(schemeID)){
            		query = databaseQ.getPreMatricSanskritSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}else{
            		query = databaseQ.getOtherSchools();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,district);
            			ps.setString(2,taluka);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							String temp2 = rs.getString(2);
    							
    							dto.setSchoolName(temp1);
    							dto.setUdiseCode(temp2);
    							
    							listData.add(dto);
    						}
                		}
                	}
            	}
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return listData;
	}
    
    public ArrayList<String> getPostMatricDataFromDBCollegeDiploma(String state, String district, String taluka, String schemeID) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		
		
		System.out.println("state11 :: " + state);
		System.out.println("district11 :: " + district);
		System.out.println("taluka11 :: " + taluka);
		
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String query = databaseQ.getDiplomaCollege();
            
            try(Connection conn = ds.getConnection();){
            
				try(PreparedStatement ps = conn.prepareStatement(query);){
					ps.setString(1,state);
	            	ps.setString(2,district);
	            	ps.setString(3,taluka);
	            	ps.setString(4,schemeID);
	            	
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString("college_name");				
							listData.add(temp1);
						}
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return listData;
	}
    
    
	public ArrayList<String> getSSCDistrictFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String sqlQuery = databaseQ.getSSCDistrict();
            
            try(Connection conn = ds.getConnection();
            	PreparedStatement ps = conn.prepareStatement(sqlQuery);){
            
            	try(ResultSet rs = ps.executeQuery();){
            		while(rs.next())
            		{
						String temp1 = rs.getString(1);
						if(temp1 == null || "null".equalsIgnoreCase(temp1)){
							temp1 = "";
						}
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();;
		}

		return listData;
	}
	
	
	public ArrayList<String> getReligionFromDB(String schemeID) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String sqlQuery = "";
            if(schemeID.equalsIgnoreCase("14")){
        		sqlQuery = databaseQ.getCommunityForMinority();
        	}else{
        		sqlQuery = databaseQ.getCommunityForOther();
        	}
            
            try(Connection conn = ds.getConnection();
            	PreparedStatement ps = conn.prepareStatement(sqlQuery);){
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						if(temp1 == null || "null".equalsIgnoreCase(temp1)){
							temp1 = "";
						}
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return listData;
	}
	
	//change on 23rd june
	//changed on 23rd July 2017
	public ArrayList<String> getSTDFromDB(String schemeID, String udiseCode) throws Exception
	{
		System.out.println("schemeID :: " + schemeID);
		ArrayList<String> listData = new ArrayList<String>();
		
		String query = "";
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            /*if(lowestClass == null || lowestClass.isEmpty() || "null".equalsIgnoreCase(lowestClass)){
            	lowestClass = "1";
            }
            
            if(highestClass == null || highestClass.isEmpty() || "null".equalsIgnoreCase(highestClass) || "11".equalsIgnoreCase(highestClass) || "12".equalsIgnoreCase(highestClass)){           	
            	highestClass = "10";
            }*/
            
            
            try(Connection conn = ds.getConnection();){           
            if(schemeID.equalsIgnoreCase("34")){
            	/*query = databaseQ.getStandard();
            	try(PreparedStatement ps = conn.prepareStatement(query);){
            		try(ResultSet rs = ps.executeQuery();){
	        			while(rs.next())
	        			{
	        				String temp1 = rs.getString(1);
	        				listData.add(temp1);
	        			}
            		}
            	}*/
            	
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"6");
                	callableStatement.setString(3,"10");

                	
                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }else if(schemeID.equalsIgnoreCase("12")){
            	/*query = databaseQ.getStandardFortweleth();
            	try(PreparedStatement ps = conn.prepareStatement(query);){      		
            		try(ResultSet rs = ps.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}*/
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"1");
                	callableStatement.setString(3,"12");

                	
                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }else if(schemeID.equalsIgnoreCase("6")){ //change done by ankit on 23rd
            	/*query = databaseQ.getStandardForSixth();
            	try(PreparedStatement ps = conn.prepareStatement(query);){      		
            		try(ResultSet rs = ps.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}*/
            	
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"9");
                	callableStatement.setString(3,"10");

                	
                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }else if(schemeID.equalsIgnoreCase("35")){
            	
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"1");
                	callableStatement.setString(3,"4");

                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }else if(schemeID.equalsIgnoreCase("8")){
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"5");
                	callableStatement.setString(3,"10");

                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}	
            }else if(schemeID.equalsIgnoreCase("21")){
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"8");
                	callableStatement.setString(3,"10");

                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }else if(schemeID.equalsIgnoreCase("40")){
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"9");
                	callableStatement.setString(3,"10");

                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }else{
            	/*query = databaseQ.getStandardForAll();
            	try(PreparedStatement ps = conn.prepareStatement(query);){
            		ps.setString(1,lowestClass);
            		ps.setString(2,highestClass);
            		
            		try(ResultSet rs = ps.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}*/ 
            	query = databaseQ.getStandardForAll();
            	try(CallableStatement callableStatement = conn.prepareCall(query);){
            		callableStatement.setString(1,udiseCode);
                	callableStatement.setString(2,"1");
                	callableStatement.setString(3,"10");

                	
                	try(ResultSet rs = callableStatement.executeQuery();){
            			while(rs.next())
            			{
            				String temp1 = rs.getString(1);
            				listData.add(temp1);
            			}
            		}
            	}
            }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return listData;
	}
	
	
	public ArrayList<String> getBeneficiaryFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();){
            	
            	String query = databaseQ.getBeneficiary();
				try(PreparedStatement ps = conn.prepareStatement(query);){
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	public ArrayList<String> getVillagesFromDB(String district, String taluka) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String query = databaseQ.getVillage();
            try(Connection conn = ds.getConnection();
            		/*PreparedStatement ps = conn.prepareStatement("select village_name from dbt_village_m where district_id = (select district_id from dbt_district_m where "
        					+ "district_name = '" + district + "') and taluka_id = (select taluka_id from dbt_taluka_m where "
        					+ "taluka_name = '" + taluka + "')");*/
            	PreparedStatement ps = conn.prepareStatement(query);){
            	ps.setString(1,taluka);
            	ps.setString(2,district);
            	
            	try(ResultSet rs = ps.executeQuery();){
            		while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	
	
	public ArrayList<String> getBoardsFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String query = databaseQ.getBoard();
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps = conn.prepareStatement(query);){
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
	
	//change on 24th July 2017
	public ArrayList<Set_Get_Data_ApplicationForm> getUniversityFromDB(String collegeName, String schemeID, String courseName, String state, String district,
			String taluka) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = new ArrayList<Set_Get_Data_ApplicationForm>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String ps_getUni=null;
            
            /*if(schemeID.equalsIgnoreCase("30")){
            	 ps_getUni= databaseQ.getUniversityForSpecific();
            }else{
				 ps_getUni= databaseQ.getUniversityForAll();
			}*/
            
            ps_getUni= databaseQ.getUniversityForAll();

            try(Connection conn = ds.getConnection();
            		PreparedStatement ps=conn.prepareStatement(ps_getUni)){
            		/*if(!schemeID.equalsIgnoreCase("30")){
            			ps.setString(1,collegeName);
            		}*/
            		ps.setString(1,collegeName);
            		ps.setString(2,courseName);
            		ps.setString(3,state);
            		ps.setString(4,district);
            		ps.setString(5,taluka);
            		ps.setString(6,schemeID);
            		
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
						dto.setCcUniversityName(rs.getString(1));
						//dto.setCcType(rs.getString(2));
						
						listData.add(dto);
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	
	
	public ArrayList<String> getStates() throws Exception
	{
		
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String ps_getStates=null;
            ps_getStates=databaseQ.getStates();
            	
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps=conn.prepareStatement(ps_getStates)){
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
					}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
			return listData;
	}
	
	/*public ArrayList<String> getSubDivisionFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps = conn.prepareStatement("select division_name from dbt_division_m where isactive = 1");){
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
					}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}*/
	
	public ArrayList<String> getDistrictFromDB(String state) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String query = databaseQ.getDistrict();
            try(Connection conn = ds.getConnection();
            		/*PreparedStatement ps = conn.prepareStatement("select district_name from dbt_district_m where state_id = "
                    		+ "(select state_id from dbt_state_m where state_name = '" + state + "' and isactive = 1) and isactive = 1");*/
            		PreparedStatement ps = conn.prepareStatement(query);
            		){
            	ps.setString(1,state);
	           try(ResultSet rs = ps.executeQuery();)
	           {
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
	           }
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	
	/*public ArrayList<String> getState() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps = conn.prepareStatement("select state_name from dbt_state_m;");){
			
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}*/
	
	
	public ArrayList<String> getCourseTypeFromDB(String courseName, String collegeName, String schemeID) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			System.out.println("courseName1:: " + courseName);
			System.out.println("collegeName1:: " + collegeName);
			System.out.println("schemeID1:: " + schemeID);
			int temp1 = 0;
			String query = "";
			
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            query = databaseQ.getProfessionalCourse();
            try(Connection conn = ds.getConnection();
            		
                    PreparedStatement ps1 = conn.prepareStatement(query);){
            		ps1.setString(1,collegeName);
            		ps1.setString(2,schemeID);
            		ps1.setString(3,courseName);
            		
					try(ResultSet rs = ps1.executeQuery();){
						while(rs.next())
						{
							temp1 = rs.getInt(1);
						}
					}
            }
            
            if(temp1 == 1){
            	listData.add("Professional Course");
            }else{
            	 query = databaseQ.getOtherCourseType();
            	 try(Connection conn = ds.getConnection();
                 		
                         PreparedStatement ps = conn.prepareStatement(query);){
            		 	 ps.setString(1,collegeName);
            		 	 ps.setString(2,schemeID);
            		 	 ps.setString(3,courseName);
            		 
     					try(ResultSet rs = ps.executeQuery();){
     						while(rs.next())
     						{
     							String temp2 = rs.getString(1);
     							listData.add(temp2);
     						}
     					}
                 }
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	
	public ArrayList<Set_Get_Data_ApplicationForm> getGrantTypeFromDB(String collegeName, String schemeID, String state,
			String district, String taluka, String university, String courseName) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = new ArrayList<Set_Get_Data_ApplicationForm>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String query = databaseQ.getGrantType();
            
           try(Connection  conn = ds.getConnection();
        	   PreparedStatement ps = conn.prepareStatement(query);){
        	   ps.setString(1,collegeName);
        	   ps.setString(2,courseName);
        	   ps.setString(3,university);
        	   ps.setString(4,state);
        	   ps.setString(5,district);
        	   ps.setString(6,taluka);
        	   ps.setString(7,schemeID);
        	   
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						//String temp1 = rs.getString(1);
						Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
						dto.setCcType(rs.getString(1));
						
						listData.add(dto);
					}
				}
           }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	
	public ArrayList<Set_Get_Data_ApplicationForm> getCourseFromDB(String collegeName, String schemeID, String state, String district, String taluka) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = new ArrayList<Set_Get_Data_ApplicationForm>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String ps_getCourse=null;
            
            /*if(!schemeID.equalsIgnoreCase("26") && !schemeID.equalsIgnoreCase("27") && !schemeID.equalsIgnoreCase("31") && !schemeID.equalsIgnoreCase("29") && 
            		!schemeID.equalsIgnoreCase("38") && !schemeID.equalsIgnoreCase("39")){*/
            if(schemeID.equalsIgnoreCase("1") || schemeID.equalsIgnoreCase("2") || schemeID.equalsIgnoreCase("4") || schemeID.equalsIgnoreCase("14") || 
                		schemeID.equalsIgnoreCase("16") || schemeID.equalsIgnoreCase("17") || schemeID.equalsIgnoreCase("22") || schemeID.equalsIgnoreCase("24") ||
                		schemeID.equalsIgnoreCase("25") || schemeID.equalsIgnoreCase("28")){	
            	/*ps_getCourse = "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
						+ "on a.course_id = b.course_id inner join dbt_college_m c "
						+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
						+ "on a.department_id = d.departmentid where "
						+ "c.college_name = '" + collegeName + "' "
						+ "and b.isactive = '1' and b.is_disable = '0' and "
						+ "d.schemeId = '" + schemeID + "'";*/
            	ps_getCourse = databaseQ.getCourseCondition1() ;
				
			}else if(schemeID.equalsIgnoreCase("26") || schemeID.equalsIgnoreCase("27") || schemeID.equalsIgnoreCase("10") || 
					schemeID.equalsIgnoreCase("18") || schemeID.equalsIgnoreCase("19") || schemeID.equalsIgnoreCase("23") || schemeID.equalsIgnoreCase("32") 
					|| schemeID.equalsIgnoreCase("33") || schemeID.equalsIgnoreCase("37")){
				/*ps_getCourse = "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
						+ "on a.course_id = b.course_id inner join dbt_college_m c "
						+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
						+ "on a.department_id = d.departmentid where "
						+ "c.college_name = '" + collegeName + "' "
						+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = '" + schemeID + "' and (a.course_name not like '%12th %' && a.course_name not like '%12th%' && "
						+ "course_name not like '%11th %' && course_name not like '%11th%' && course_name not like '%XII (12 th)%' && course_name not like '%XI (11 th)%')";
				*/
				ps_getCourse = databaseQ.getCourseCondition2();
						
			}else if(schemeID.equalsIgnoreCase("31")){
				/*ps_getCourse = "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
						+ "on a.course_id = b.course_id inner join dbt_college_m c "
						+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
						+ "on a.department_id = d.departmentid where "
						+ "c.college_name = '" + collegeName + "' "
						+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = '" + schemeID + "' and "
						+ "(a.course_name like '%P.H.D%' || a.course_name like '%Ph.D%' || a.course_name like '%PHD%' || a.course_name like '%PH.D%')";
				*/
				ps_getCourse = databaseQ.getCourseCondition3();
			}else if(schemeID.equalsIgnoreCase("30")){
				/*ps_getCourse = "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
						+ "on a.course_id = b.course_id inner join dbt_college_m c "
						+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
						+ "on a.department_id = d.departmentid where "
						+ "c.college_name = '" + collegeName + "' "
						+ "and b.isactive = '1' and b.is_disable = '0' and d.schemeId = '" + schemeID + "' and "
						+ "(a.course_name like '%Post Graduate%' && (a.course_name like '%Law%' || a.course_name like '%Commerce%' || "
						+ "a.course_name like '%Arts%' || a.course_name like '%Science%'))";*/
				ps_getCourse = databaseQ.getCourseCondition4();
			}else if(schemeID.equalsIgnoreCase("38") || schemeID.equalsIgnoreCase("39")){
				/*ps_getCourse = "select distinct(a.course_name) from dbt_course_m a inner join dbt_college_course_m b "
						+ "on a.course_id = b.course_id inner join dbt_college_m c "
						+ "on b.college_id = c.college_id inner join dbt_scheme_master d "
						+ "on a.department_id = d.departmentid where "
						+ "c.college_name = '" + collegeName + "' "
						+ "and b.isactive = '1' and b.is_disable = '0' and "
						+ "d.schemeId = '" + schemeID + "' and b.is_granted = 3";*/
				ps_getCourse = databaseQ.getCourseCondition5();
			}else if(schemeID.equalsIgnoreCase("3") || schemeID.equalsIgnoreCase("21") || 
					schemeID.equalsIgnoreCase("40")){
				
				ps_getCourse = databaseQ.getCourseEleventhAndTewelve();
			}else if(schemeID.equalsIgnoreCase("29")){
				
				ps_getCourse = databaseQ.getCourseLawCommerce();
			}
            
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps=conn.prepareStatement(ps_getCourse);){
            		ps.setString(1, collegeName);
            		ps.setString(2, state);
            		ps.setString(3, district);
            		ps.setString(4, taluka);
            		ps.setString(5, schemeID);
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
						
						dto.setCurrentCourseName(rs.getString(1));
						dto.setCurrentCourseType(rs.getString(2));
						dto.setIsProfessional(rs.getString(3));
						
						listData.add(dto);
					}
        		}
            		
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
	
	public ArrayList<String> getCasteCategoryFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String casteCategoryFromDB = databaseQ.casteCategoryFromDB();
            try(Connection conn = ds.getConnection();
            	PreparedStatement ps = conn.prepareStatement(casteCategoryFromDB);){
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	public ArrayList<String> getCasteFromDB(String casteCategory) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String ps_getCaste =  null;
			/*if(casteCategory.equalsIgnoreCase("all")){
				ps_getCaste =  "select caste_name from dbt_caste_m";
			}else{*/
				/*ps_getCaste =  "select caste_name from dbt_caste_m where caste_category_id = "
						+ "(select caste_id from dbt_caste_category_m where caste = '" + casteCategory + "')";*/
				ps_getCaste =  databaseQ.casteCategory();
			/*}*/
			try(Connection conn = ds.getConnection();
					PreparedStatement ps=conn.prepareStatement(ps_getCaste);){
					ps.setString(1, casteCategory);
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
					}
			}
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	/*public ArrayList<String> getSubCasteFromDB(String caste) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();
            
			PreparedStatement ps = conn.prepareStatement("select subcaste_name from dbt_subcaste_m where caste_id = "
					+ "(select caste_id from dbt_caste_m where caste_name = '" + caste + "')");){
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}*/
	
	public ArrayList<String> getDisabilityFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String disabilityQuery = databaseQ.getDisabilityType();
            try(Connection conn = ds.getConnection();           		
			PreparedStatement ps = conn.prepareStatement(disabilityQuery);){
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	
	public ArrayList<String> getPostMatricDataFromDB() throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String query = databaseQ.getDistrictForMaharshtra();
            try(Connection conn = ds.getConnection();
            
			PreparedStatement ps = conn.prepareStatement(query);){
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
	
	
	public ArrayList<String> getLastCourseFromDB(String collegeName, String schemeID) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		String query = "";
		
		try
		{
				Context = new javax.naming.InitialContext();
				ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
				
				query = databaseQ.getLastCourse();
				try(Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
				ps.setString(1,collegeName);
	            ps.setString(2,schemeID);
			
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
					}
				}
		}
		catch(Exception e)
		{
			throw e;
		}
				return listData;
	}
	
	
	public ArrayList<String> getPostMatricDataFromDBTaluka(String districtName, String stateName) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            String query = databaseQ.getTaluka();
            try(Connection  conn = ds.getConnection();
            
			/*PreparedStatement ps = conn.prepareStatement("SELECT taluka_name FROM dbt_taluka_m where district_id in "
					+ "(select district_id from dbt_district_m where district_name = '" + districtName + "' "
					+ "and isactive = 1) and isactive = 1");*/
             PreparedStatement ps = conn.prepareStatement(query);){
            	ps.setString(1,stateName);
            	ps.setString(2,districtName);
            	
            	//System.out.println("TalukaQuery :: " + query);
            	try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp1 = rs.getString(1);
						listData.add(temp1);
					}
            	}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
	
	public ArrayList<String> getPostMatricDataFromDBCollege(String state, String district, String taluka, String schemeID) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{

			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String ps_getPostMatric = null;
            
            /*if(state.equalsIgnoreCase("all") && district.equalsIgnoreCase("all") && taluka.equalsIgnoreCase("all")){
				ps = conn.prepareStatement("select college_name from dbt_college_m");
				//ps = conn.prepareStatement("select college_name from dbt_college_m where state_id = '27' and district_id = '6' and taluka_id = '143'");
				
			}*//*else if(schemeID.equalsIgnoreCase("30") && !(state.equalsIgnoreCase("all") && district.equalsIgnoreCase("all") && taluka.equalsIgnoreCase("all"))){
				
				
			}else if(schemeID.equalsIgnoreCase("31") && !(state.equalsIgnoreCase("all") && district.equalsIgnoreCase("all") && taluka.equalsIgnoreCase("all"))){
				
			}*/
            
            if(schemeID.equalsIgnoreCase("38") || schemeID.equalsIgnoreCase("39")){
            	ps_getPostMatric = databaseQ.getAidedColleges();	
				
			}else if(schemeID.equalsIgnoreCase("1") || schemeID.equalsIgnoreCase("2") || schemeID.equalsIgnoreCase("4") || schemeID.equalsIgnoreCase("14")
					|| schemeID.equalsIgnoreCase("16") || schemeID.equalsIgnoreCase("17") || schemeID.equalsIgnoreCase("25")
					|| schemeID.equalsIgnoreCase("28")){
				
				ps_getPostMatric = databaseQ.getColleges();
			}else if(schemeID.equalsIgnoreCase("3") || schemeID.equalsIgnoreCase("21") || schemeID.equalsIgnoreCase("40")){
				
				ps_getPostMatric = databaseQ.getEleventhTwelvethColleges();
			}else if(schemeID.equalsIgnoreCase("10") || schemeID.equalsIgnoreCase("18") || schemeID.equalsIgnoreCase("19") ||
					schemeID.equalsIgnoreCase("23") || schemeID.equalsIgnoreCase("26") || schemeID.equalsIgnoreCase("27") || 
					schemeID.equalsIgnoreCase("30") || schemeID.equalsIgnoreCase("31") || schemeID.equalsIgnoreCase("32") ||
					schemeID.equalsIgnoreCase("33") || schemeID.equalsIgnoreCase("37") || schemeID.equalsIgnoreCase("22") || 
					schemeID.equalsIgnoreCase("24")){
				
				ps_getPostMatric = databaseQ.getCollegesAboveEleventhAndTwelveth();
			}else if(schemeID.equalsIgnoreCase("29")){
				
				ps_getPostMatric = databaseQ.getCollegesForLawCommerce();
			}
            
          try(Connection  conn = ds.getConnection();
        		  PreparedStatement ps = conn.prepareStatement(ps_getPostMatric)){
        	  	  ps.setString(1,state);
        	  	  ps.setString(2,district);
        	  	  ps.setString(3,taluka);
        	  	  ps.setString(4,schemeID);
        	  	  
        	  		try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							listData.add(temp1);
						}
        	  		}
          		}
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
	
	
	public ArrayList<Set_Get_Data_ApplicationForm> getPostMatricDataFromDBAutoPopulate(String userName) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = new ArrayList<Set_Get_Data_ApplicationForm>();
		
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String query = 	databaseQ.getAutoPopulateDataSP();
            System.out.println("Swarn :: " + query);
            
            try(Connection conn = ds.getConnection();){
            
	            try(CallableStatement callable = conn.prepareCall(query);){
	            	
	            	callable.setString(1, userName);
	            	
	            	Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();

					try(ResultSet rs = callable.executeQuery();){

						while(rs.next())
						{	
							dto.setAadhaarNo(rs.getString("aadhaar_no"));
							dto.setName(rs.getString("full_name"));
							dto.setMobileNo(rs.getString("mobile_no"));
							dto.setEmailID(rs.getString("email_id"));
							dto.setDateofBirth(rs.getString("dob"));
							dto.setGender(rs.getString("gender"));
							dto.setApplicantHouse(rs.getString("house"));
							dto.setApplicantStreet(rs.getString("street"));
							dto.setApplicantPostOffice(rs.getString("post_office"));
							dto.setApplicantLandmark(rs.getString("landmark"));
							dto.setApplicantState(rs.getString("state"));
							dto.setApplicantDistrict(rs.getString("district"));
							dto.setApplicantPinCode(rs.getString("pincode"));
							dto.setApplicantTaluka(rs.getString("sub_district"));
							dto.setApplicantCity(rs.getString("village_town"));
							dto.setApplicantAge(rs.getInt("age"));
							dto.setNpciStatus(rs.getString("npci_status"));
							
							dto.setSameAddress(rs.getString("sameAddress"));
							dto.setC_house(rs.getString("upro.c_house"));
							dto.setC_street(rs.getString("c_street"));
							dto.setC_post_office(rs.getString("c_post_office"));
							dto.setC_landmark(rs.getString("c_landmark"));
							dto.setC_state(rs.getString("c_state"));
							dto.setC_district(rs.getString("c_district"));
							dto.setC_pincode(rs.getString("c_pincode"));
							dto.setC_village_town(rs.getString("c_village_town"));
							dto.setC_sub_district(rs.getString("c_sub_district"));
							dto.setIsFatherAlive(rs.getString("isFatherAlive"));
							dto.setIsMotherAlive(rs.getString("isMotherAlive"));
							dto.setFather(rs.getString("father"));
							dto.setMother(rs.getString("mother"));
							dto.setGuardian(rs.getString("guardian"));
							
							dto.setIncome_is_rts_seeded_data(rs.getString("income_is_rts_seeded_data"));
							dto.setIncome_cert_barcode(rs.getString("income_cert_barcode"));
							dto.setIncome_name_on_cert(rs.getString("income_name_on_cert"));
							dto.setIncome_cert_date(rs.getString("income_cert_date"));
							dto.setIncome_cert_number(rs.getString("income_cert_number"));
							dto.setIncome_issuing_authority(rs.getString("income_issuing_authority"));
							dto.setIncome_family_income(rs.getString("income_family_income"));
							dto.setIncome_verification_status(rs.getString("income_verification_status"));
	
							dto.setDomicile_is_rts_seeded_data(rs.getString("domicile_is_rts_seeded_data"));
							dto.setDomicile_cert_barcode(rs.getString("domicile_cert_barcode"));
							dto.setDomicile_name_on_cert(rs.getString("domicile_name_on_cert"));
							dto.setDomicile_cert_date(rs.getString("domicile_cert_date"));
							dto.setDomicile_cert_number(rs.getString("domicile_cert_number"));
							dto.setDomicile_issuing_authority(rs.getString("domicile_issuing_authority"));
							dto.setDomicile_is_domicile(rs.getString("domicile_is_domicile"));
							dto.setDomicile_verification_status(rs.getString("domicile_verification_status"));
	
							dto.setDisaility_is_rts_seeded_data(rs.getString("disaility_is_rts_seeded_data"));
							dto.setDisaility_cert_barcode(rs.getString("disaility_cert_barcode"));
							dto.setDisaility_name_on_cert(rs.getString("disaility_name_on_cert"));
							dto.setDisaility_cert_date(rs.getString("disaility_cert_date"));
							dto.setDisaility_cert_number(rs.getString("disaility_cert_number"));
							dto.setDisaility_issuing_authority(rs.getString("disaility_issuing_authority"));
							dto.setDisaility_is_disabled(rs.getString("disaility_is_disabled"));
							dto.setDisaility_type(rs.getString("disaility_type"));
							dto.setDisaility_category(rs.getString("disaility_category"));
							dto.setDisaility_percent(String.valueOf(Math.round(rs.getFloat("disaility_percent"))));
							dto.setDisaility_validity_year(rs.getInt("disaility_validity_year"));
							dto.setDisaility_reader_opted(rs.getString("disaility_reader_opted"));
							dto.setDisaility_verification_status(rs.getString("disaility_verification_status"));
	
							dto.setCaste_is_rts_seeded_data(rs.getString("caste_is_rts_seeded_data"));
							dto.setCaste_cert_barcode(rs.getString("caste_cert_barcode"));
							dto.setCaste_name_on_cert(rs.getString("caste_name_on_cert"));
							dto.setCaste_cert_date(rs.getString("caste_cert_date"));
							dto.setCaste_cert_number(rs.getString("caste_cert_number"));
							dto.setCaste_issuing_authority(rs.getString("caste_issuing_authority"));
							dto.setCaste_category(rs.getString("caste_category"));
							dto.setCaste_caste(rs.getString("caste_caste"));
							dto.setCaste_verification_status(rs.getString("caste_verification_status"));
	
							dto.setSsc_total_marks(rs.getString("ssc_total_marks"));
							dto.setSsc_final_result(rs.getString("ssc_final_result"));
							dto.setSsc_integration_flag(rs.getString("ssc_integration_flag"));
							dto.setSsc_board(rs.getString("ssc_board"));
							dto.setSsc_other_board(rs.getString("ssc_other_board"));
							dto.setSsc_pass_year(rs.getInt("ssc_pass_year"));
							dto.setSsc_pass_month(rs.getString("ssc_pass_month"));
							dto.setSsc_seat_number(rs.getString("ssc_seat_number"));
							dto.setSsc_marks_obtained(rs.getString("ssc_marks_obtained"));
							dto.setSsc_marks_percent(rs.getString("ssc_marks_percent"));
							dto.setSsc_verification_status(rs.getString("ssc_verification_status"));
							dto.setSsc_namessccertificate(rs.getString("ssc_namessccertificate"));
	
							dto.setHsc_total_marks(rs.getString("hsc_total_marks"));
							dto.setHsc_final_result(rs.getString("hsc_final_result"));
							dto.setHsc_integration_flag(rs.getString("hsc_integration_flag"));
							dto.setHsc_board(rs.getString("hsc_board"));
							dto.setHsc_other_board(rs.getString("hsc_other_board"));
							dto.setHsc_pass_year(rs.getInt("hsc_pass_year"));
							dto.setHsc_pass_month(rs.getString("hsc_pass_month"));
							dto.setHsc_seat_number(rs.getString("hsc_seat_number"));
							dto.setHsc_marks_obtained(rs.getString("hsc_marks_obtained"));
							dto.setHsc_marks_percent(rs.getString("hsc_marks_percent"));
							dto.setHsc_verification_status(rs.getString("hsc_verification_status"));
	
							dto.setCval_castevalidity_number(rs.getString("cval_castevalidity_number"));
							dto.setCval_barti_flag(rs.getString("cval_barti_flag"));
							dto.setCval_issuing_date(rs.getString("cval_issuing_date"));
							dto.setCval_verification_status(rs.getString("cval_verification_status"));
							
							//change on 11th July 2017
							dto.setDomicile_is_domicile_certificate(rs.getString("domicile_is_domicile_certificate"));
							dto.setIncome_is_income_certificate(rs.getString("income_is_income_certificate"));
							dto.setDisaility_is_disability_certificate(rs.getString("disaility_is_disability_certificate"));
							dto.setCaste_is_caste_certificate(rs.getString("caste_is_caste_certificate"));
							
							dto.setBpl_is_rts_seeded_data(rs.getString("bpl_is_rts_seeded_data"));
							dto.setBpl_cert_barcode(rs.getString("bpl_cert_barcode"));
							dto.setBpl_name_on_cert(rs.getString("bpl_name_on_cert"));
							dto.setBpl_cert_date(rs.getString("bpl_cert_date"));
							dto.setBpl_cert_number(rs.getString("bpl_cert_number"));
							dto.setBpl_issuing_authority(rs.getString("bpl_issuing_authority"));
							dto.setBpl_verification_status(rs.getString("bpl_verification_status"));
							dto.setBpl_is_bpl(rs.getString("bpl_is_bpl"));
							dto.setBpl_is_bpl_certificate(rs.getString("bpl_is_bpl_certificate"));							
							
						}
						
					}
					
					callable.getMoreResults();
					try(ResultSet rs = callable.getResultSet();){
					while(rs.next())
					{
						/* --images-- */
						System.out.println(rs.getString("document_type"));
						if(rs.getString("document_type").equalsIgnoreCase("domicileCertificate"))
						{	
							dto.setDomicileCertificateImagePath(rs.getString("document_path"));
						}	
						else if(rs.getString("document_type").equalsIgnoreCase("applicantCasteCertificate"))
						{
							dto.setCasteCertificateImagePath(rs.getString("document_path"));
						}
						else if(rs.getString("document_type").equalsIgnoreCase("familyIncomeCertificate"))
						{
							dto.setFamilyIncomeCertificateImagePath(rs.getString("document_path"));
						}
						else if(rs.getString("document_type").equalsIgnoreCase("DisabilityCertificate"))
						{
							dto.setDisabilityCertificateImagePath(rs.getString("document_path"));
						}
						else if(rs.getString("document_type").equalsIgnoreCase("bplCard"))
						{
							dto.setBplCardImagePath(rs.getString("document_path"));
						}
					}
				}
				listData.add(dto);
	          }
           }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listData;
	}
	
	//changed on 6th July 2017
	public String setDocumentsDetails(String schemeID, String userID, String documentPath, String documentType,
			String appid,String schemeType, String userIndex, String fullName, String rts_flag) {
		
		ApplySchemeService appSS = new ApplySchemeService();
		
		String status = "false";
		String rip_Flag = "L";
		
		try
		{
			if(documentPath.contains("../../..")){
				documentPath = documentPath.replaceAll("../../..","/usr");
			}else if(documentPath.contains("\\")){
				documentPath = documentPath.replaceAll("\\\\","/");
			}
			
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
           try(Connection conn = ds.getConnection();){
            
			if(appid.equals("") || appid == null)
			{
				/*generate app id*/
				appid = appSS.getNewApplicationId(schemeType,fullName,schemeID,userIndex);
			}else{
				//do nothing
			}
			System.out.println("APPID in Doc :: " + appid);
			
			String set_documentDetails = databaseQ.getSetDocumentDetails();
			try(CallableStatement callableStatement = conn.prepareCall(set_documentDetails);){
				callableStatement.setString(1,appid);
            	callableStatement.setString(2,documentType);
            	callableStatement.setString(3,documentPath);
            	callableStatement.setString(4,userID);
            	callableStatement.setString(5,rts_flag);
            	callableStatement.setString(6,rip_Flag);
            	
            	callableStatement.executeUpdate();
            	status = callableStatement.getString(7);
            	System.out.println("Status in Setting Document Details:: " + status);
            	
			}
			/*ps1 = conn.prepareStatement("select count(1) from dbt_document_dtl where app_id = '"+appid+"' and document_type='"+documentType+"' and user_id = '"+userID+"';");
			ResultSet rs = ps1.executeQuery();
			
			int count = 0;
			while(rs.next()){
				count = rs.getInt(1);
			}
			if(count == 0){
				ps = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
						+ "('" + appid + "', '" + documentType + "', '" + documentPath + "','" + userID + "','N','N','L');");
				ps.executeUpdate();
				status = "true";
				ps.close();
				ps1.close();
			}else{
				ps2 = conn.prepareStatement("update dbt_document_dtl set document_path = '"+documentPath+"',document_flag = 'U' where "
						+ "app_id = '"+appid+"' and document_type = '"+documentType+"' and user_id = '"+userID+"';");
				ps2.executeUpdate();
				status = "true";
				ps2.close();
			}*/
				
				
			/*ps1 = conn.prepareStatement("delete from dbt_document_dtl where app_id = '"+schemeID+"' and document_type='"+documentType+"' and user_id = '"+userID+"';");
			ps1.executeUpdate();*/
			/*ps = conn.prepareStatement("insert into dbt_document_dtl(app_id,document_type,document_path,user_id,document_flag,rts_flag,rip_flag) values"
					+ "('" + schemeID + "', '" + documentType + "', '" + documentPath + "','" + userID + "','N','N','L');");
			ps.executeUpdate();
			status = "true";
			ps.close();
			ps1.close();
			
			//change by ankit 18th june
			ps2 = conn.prepareStatement("update dbt_document_dtl set document_path = '"+documentPath+"',document_flag = 'U' where app_id = '"+appid+"' and document_type = '"+documentType+"' and user_id = '"+userID+"';");
			ps2.executeUpdate();
			status = "true";
			ps2.close();*/
			
          }
        }
		catch(Exception e)
		{
			System.out.println("Error occurred in inserting documentdetails :: " + e);
			e.printStackTrace();
			status = "false";
		}

		return status;
	}
	
	public ArrayList<String> getPostMatricDataFromDBOccupation(String schemeID) throws Exception{
		ArrayList<String> occupationList = new ArrayList<String>();
		try
		{
			
			System.out.println("schemeIDOccupation :: " + schemeID);
			
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String ps_postMatricData = null;
            
            if(schemeID.equalsIgnoreCase("9")){
            	//ps_postMatricData = "select profession_name from dbt_profession_m where isunclean = '1'";
            	ps_postMatricData = databaseQ.getOccupationForUnclean();
            	
			}else if(schemeID.equalsIgnoreCase("6")){
				/*ps_postMatricData = "select profession_name from dbt_profession_m "
						+ "where (profession_name = 'Service' || profession_name = 'Businessman' || profession_name = 'ServiceBusiness')";*/
				ps_postMatricData = databaseQ.getOccupationFor9and10();
				
			}else{
				//ps_postMatricData = "select profession_name from dbt_profession_m";
				ps_postMatricData = databaseQ.getOccupation();
			}
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps= conn.prepareStatement(ps_postMatricData) ){
            		
            		try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							occupationList.add(temp1);
						}
            		}
            }
            
		}
		catch(Exception e)
		{
			throw e;
		}

		return occupationList;
	}
	
	public ArrayList<Set_Get_Data_ApplicationForm> getImageViewFromDB(String appID) throws Exception{
		ArrayList<Set_Get_Data_ApplicationForm> imageList = new ArrayList<Set_Get_Data_ApplicationForm>();
		String location = "";
		
		try
		{
			Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
			Context = new javax.naming.InitialContext();
			String query = databaseQ.getDocumentPath();
			
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();
            		PreparedStatement ps = conn.prepareStatement(query);){
			
					ps.setString(1,appID);
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							location = rs.getString("document_path");
							BufferedImage img = ImageIO.read(new File(location));
							ImageEncoderUtility encode=new ImageEncoderUtility();
							String retByteStr = encode.encodeToString(img, "jpg");
							location=retByteStr;
						}
						dto.setImage_location(location);
						imageList.add(dto);
					}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return imageList;
	}
	
	public ArrayList<Set_Get_Data_ApplicationForm> getImageViewForDocs(String filePath) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> imageList = new ArrayList<Set_Get_Data_ApplicationForm>();
		String location = "";
		System.out.println("filePath : "+filePath);
		// usrdbtApplicationsankit.katochDocuments1910_U.S._Telephone_Herald_stock_certificate.JPEG
		// filePath = "D:\\usr\\dbt\\Applications\\ankit.katoch\\Documents\\1910_U.S._Telephone_Herald_stock_certificate.JPEG";
		ImageEncoderUtility encode=new ImageEncoderUtility();
		
		try
		{
			Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
			location = filePath;
			if(filePath.contains(".pdf")){
				String retByteStr = encode.encodeFileToBase64Binary(filePath);
				location=retByteStr;
				
			}else{
				BufferedImage img = ImageIO.read(new File(location));
				
				String retByteStr = encode.encodeToString(img, "jpg");
				location=retByteStr;
			}
			
			dto.setImage_location(location);
			imageList.add(dto);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{

		}
		return imageList;
	}
	
	public ArrayList<String> getCollegeTypeFromDB(String collegeName,String schemeID, String state, String district, String taluka) throws Exception
	{
		ArrayList<String> listData = new ArrayList<String>();
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            String query = databaseQ.getCollegeType(); 
            
            try(Connection conn = ds.getConnection();
            
			PreparedStatement ps = conn.prepareStatement(query);){
            ps.setString(1,state);
            ps.setString(2,district);	
            ps.setString(3,taluka);
            ps.setString(4,schemeID);
            ps.setString(5,collegeName);
            
					try(ResultSet rs = ps.executeQuery();){
						while(rs.next())
						{
							String temp1 = rs.getString(1);
							if(temp1 == null || "null".equalsIgnoreCase(temp1)){
								temp1 = "";
							}
							listData.add(temp1);
						}
					}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	}
	
	public ArrayList<String> getPreferenceDropDownValue(String user_Name) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> listData = new ArrayList<String>();	
		String temp = null;
		try
		{
			String getPreference = databaseQ.getPreference(); 
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();

			
			PreparedStatement ps = conn.prepareStatement(getPreference);){
			ps.setString(1, user_Name);
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						int count = Integer.parseInt(rs.getString("countFromDB"));
		
						for(int i=1;i<=count;i++){
							temp = Integer.toString(i);
							listData.add(temp);
						}
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}

		return listData;
	
	}
	
	
	//change on 25th July 2017
	public ArrayList<String> getCollegeForCourseTypes(String schemeID, String state, String district,
			String taluka,String courseType) throws Exception {
		
		ArrayList<String> listData = new ArrayList<String>();	
		try
		{
			String query = databaseQ.getCollegesForEdu(); 
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try(Connection conn = ds.getConnection();

			
			PreparedStatement ps = conn.prepareStatement(query);){
			ps.setString(1, courseType);
			ps.setString(2, state);
			ps.setString(3, district);
			ps.setString(4, taluka);
			ps.setString(5, schemeID);
			
				try(ResultSet rs = ps.executeQuery();){
					while(rs.next())
					{
						String temp = rs.getString(1);
						listData.add(temp);
					}
				}
            }
		}
		catch(Exception e)
		{
			throw e;
		}
		return listData;
	}
		
	public ArrayList<Set_Get_Data_ApplicationForm> getCoursesForLastYearDetails(String collegeName, String schemeID, String state, 
			String district, String taluka, String courseType) throws Exception
	{
		ArrayList<Set_Get_Data_ApplicationForm> listData = new ArrayList<Set_Get_Data_ApplicationForm>();
		
		String query = "";
		try
		{
			Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            
            
            try(Connection conn = ds.getConnection();){
            		query = databaseQ.getCoursesForEdu();
            		try(PreparedStatement ps = conn.prepareStatement(query);){
            			ps.setString(1,collegeName);
            			ps.setString(2,courseType);
            			ps.setString(3,state);
            			ps.setString(4,district);
            			ps.setString(5,taluka);
            			ps.setString(6,schemeID);
            			
                		try(ResultSet rs = ps.executeQuery();){
    						while(rs.next())
    						{
    							Set_Get_Data_ApplicationForm dto = new Set_Get_Data_ApplicationForm();
    							String temp1 = rs.getString(1);
    							dto.setCurrentCourseName(temp1);
    							listData.add(dto);
    						}
                		}
                	}
            }
		}catch(Exception ex){
			System.out.println("Error in getting course details !!");
			ex.printStackTrace();
		}
		return listData;
	}
	
}
