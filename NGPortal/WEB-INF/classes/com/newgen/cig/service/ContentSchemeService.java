/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : ContentSchemeService.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 27, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.cig.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import org.glassfish.jersey.client.ClientConfig;

import com.newgen.cig.entity.SchemeEntity;
import com.newgen.utility.AppConstants;

public class ContentSchemeService {

    InitialContext Context;
    DataSource ds;
    String token = null;
    Claims claims;

    public ContentSchemeService() {
    }

    public HashMap getSchemeIndex(SchemeEntity schemeEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (httpServletRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
                resultMap.put("resultCode", -3);
                resultMap.put("resultDetails", "Invalid Token");
                return resultMap;
            }
        }
        if (schemeEntity_i == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid Input");
            return resultMap;
        }
     /*   String selectSchemeIndex = "select schemeid,schemename,code,s.description,department,d.departmentname,category,c.categoryname,s.status,"
                + "s.modifiedBy,DATE_FORMAT(s.modifiedOn, '%Y-%m-%d %T') as modifiedOn  from dbt_scheme_master s "
                + "join dbt_department_master d  on s.department = d.departmentid where s.status = 0";*/
        
        
        String selectSchemeIndex = "select schemeid,schemename,d.departmentname from dbt_scheme_master s join dbt_department_master d  on s.departmentid = d.departmentid where d.status = 0";
               // + "join categorymaster c on s.category = c.categoryid  where s.status = 0";
        
        
        System.out.println(selectSchemeIndex);
        String appendCondition = " and ";
        int schemeId = schemeEntity_i.getSchemeId();
        int department = schemeEntity_i.getDepartment();
        int category = schemeEntity_i.getCategory();
        if (schemeId > 0) {
            selectSchemeIndex = selectSchemeIndex + appendCondition + " schemeid= ?";
        }
        if (department > 0) {
            selectSchemeIndex = selectSchemeIndex + appendCondition + " d.departmentid= ?";
        }
//        if (category > 0) {
//            selectSchemeIndex = selectSchemeIndex + appendCondition + " category= ?";
//        }
        System.out.println(selectSchemeIndex);
        
        ArrayList<SchemeEntity> schemeEntityList = new ArrayList<>();
        try {
            Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try (
                    Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(selectSchemeIndex);) {
                int argIndex = 1;
                if (schemeId > 0) {
                    ps.setInt(argIndex++, schemeId);
                }
                if (department > 0) {
                    ps.setInt(argIndex++, department);
                }
//                if (category > 0) {
//                    ps.setInt(argIndex++, category);
//                }
                System.out.println(ps.toString());
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        SchemeEntity schemeEntity = new SchemeEntity();
                        schemeEntity.setSchemeId(rs.getInt(1));
                        schemeEntity.setSchemename(rs.getString(2));
//                        schemeEntity.setCode(rs.getString(3));
//                        schemeEntity.setDescription(rs.getString(4));
//                        schemeEntity.setDepartment(rs.getInt(5));
                        schemeEntity.setDepartmentname(rs.getString(3));
//                        schemeEntity.setCategory(rs.getInt(7));
//                        schemeEntity.setCategoryname(rs.getString(8));
//                        schemeEntity.setStatus(rs.getInt(9));
                        //String str = (rs.getInt(9) == 0 ? "Approved" : "Deleted");
//                        schemeEntity.setStrstatus(str);
//                        schemeEntity.setModifiedBy(rs.getInt(10));
//                        schemeEntity.setModifiedOn(rs.getString(11));
                        schemeEntityList.add(schemeEntity);
                    }
                    resultMap.put("resultCode", 0);
                    httpServletResponse.setHeader("token", token);
                    resultMap.put("resultDetails", schemeEntityList);
                }
            }
        } catch (NamingException | SQLException ex) {
            //logger.error("Content Scheme Service DB Exception: " + ex.toString());
            resultMap.put("resultCode", -1);
            resultMap.put("resultDetails", "ContentSchemeService DB Exception: " + ex.toString());
            return resultMap;
        }
        return resultMap;
    }

//    public HashMap insertScheme(SchemeEntity schemeEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        if (httpServletRequest.getHeader("token") != null) {
//            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
//            if (token.contains("Invalid Token")) {
//                resultMap.put("resultCode", -4);
//                resultMap.put("resultDetails", "Invalid Token");
//                return resultMap;
//            }
//        } else {
//            resultMap.put("resultCode", -4);
//            resultMap.put("resultDetails", "Unauthorized credentials");
//            return resultMap;
//        }
//        if (schemeEntity_i == null) {
//            resultMap.put("resultCode", -2);
//            resultMap.put("resultDetails", "Invalid Input");
//            return resultMap;
//        }
//        String schemename = schemeEntity_i.getSchemename();
//        if (schemename == null) {
//            resultMap.put("resultCode", -3);
//            resultMap.put("resultDetails", "Scheme Name not found");
//            return resultMap;
//        }
//        try {
//            Context = new javax.naming.InitialContext();
//            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//            try (Connection conn = ds.getConnection();) {
//                String schemeEntity = "";
//                claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
//                int modifiedBy = (int) claims.get("userid");
//                String selectschemename = "select schemename from dbt_scheme_master where schemename = ?";
//                try (PreparedStatement ps = conn.prepareStatement(selectschemename);) {
//                    ps.setString(1, schemename);
//                    ResultSet rs = ps.executeQuery();
//                    while (rs.next()) {
//                        schemeEntity = rs.getString(1);
//                    }
//                }
//                if (!schemeEntity.equals(schemename)) {
//                    String insertScheme = "insert into dbt_scheme_master(schemeid,schemename,code,description,department,category,status,modifiedBy,modifiedOn) VALUES(0,?,?,?,?,?,?,?,current_timestamp())";
//                    try (PreparedStatement ps_insertScheme = conn.prepareStatement(insertScheme);) {
//                        ps_insertScheme.setString(1, schemeEntity_i.getSchemename());
//                        ps_insertScheme.setString(2, schemeEntity_i.getCode());
//                        ps_insertScheme.setString(3, schemeEntity_i.getDescription());
//                        ps_insertScheme.setInt(4, schemeEntity_i.getDepartment());
//                        ps_insertScheme.setInt(5, schemeEntity_i.getCategory());
//                        ps_insertScheme.setInt(6, 0);
//                        ps_insertScheme.setInt(7, modifiedBy);
//                        int insertResultSet = ps_insertScheme.executeUpdate();
//                        if (insertResultSet > 0) {
//                            resultMap.put("resultCode", 0);
//                            httpServletResponse.setHeader("token", token);
//                            resultMap.put("resultDetails", "Record inserted successfully");
//                        } else {
//                            resultMap.put("resultCode", -3);
//                            httpServletResponse.setHeader("token", token);
//                            resultMap.put("resultDetails", "No record inserted");
//                        }
//                    }
//                } else {
//                    resultMap.put("resultCode", -3);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record already exist with same scheme name please select another seheme name");
//                    return resultMap;
//                }
//            }
//        } catch (NamingException | SQLException ex) {
//            //logger.error("Content Scheme Service DB Exception: " + ex.toString());
//            resultMap.put("resultCode", -1);
//            resultMap.put("resultDetails", "DB Error in inserting scheme details: " + ex.toString());
//            return resultMap;
//        }
//        return resultMap;
//    }
//
//    public HashMap updateScheme(SchemeEntity schemeEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        if (httpServletRequest.getHeader("token") != null) {
//            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
//            if (token.contains("Invalid Token")) {
//                resultMap.put("resultCode", -4);
//                resultMap.put("resultDetails", "Invalid Token");
//                return resultMap;
//            }
//        } else {
//            resultMap.put("resultCode", -4);
//            resultMap.put("resultDetails", "Unauthorized credentials");
//            return resultMap;
//        }
//        if (schemeEntity_i == null) {
//            resultMap.put("resultCode", -2);
//            resultMap.put("resultDetails", "Invalid Input");
//            return resultMap;
//        }
//        int schemeid = schemeEntity_i.getSchemeId();
//        if (schemeid <= 0) {
//            resultMap.put("resultCode", -3);
//            resultMap.put("resultDetails", "Scheme Id not found");
//            return resultMap;
//        }
//        String schemename = schemeEntity_i.getSchemename();
//        String code = schemeEntity_i.getCode();
//        String description = schemeEntity_i.getDescription();
//        int department = schemeEntity_i.getDepartment();
//        int category = schemeEntity_i.getCategory();
//        String schemeUpdate = "Update schememaster SET ";
//        String appendString;
//        int checkflag = 0;
//        if (schemename != null) {
//            appendString = (checkflag > 0) ? ", schemename = '" + schemename + "'" : " schemename = '" + schemename + "'";
//            schemeUpdate = schemeUpdate + appendString;
//            checkflag = 1;
//        }
//        if (code != null) {
//            appendString = (checkflag > 0) ? ", code = '" + code + "'" : " code = '" + code + "'";
//            schemeUpdate = schemeUpdate + appendString;
//            checkflag = 1;
//        }
//
//        if (description != null) {
//            appendString = (checkflag > 0) ? ", description = '" + description + "'" : " description = '" + description + "'";
//            schemeUpdate = schemeUpdate + appendString;
//            checkflag = 1;
//        }
//        if (department > 0) {
//            appendString = (checkflag > 0) ? ", department = " + department : " department = " + department;
//            schemeUpdate = schemeUpdate + appendString;
//            checkflag = 1;
//        }
//        if (category > 0) {
//            appendString = (checkflag > 0) ? ", category = " + category : " category = " + category;
//            schemeUpdate = schemeUpdate + appendString;
//            checkflag = 1;
//        }
//        claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
//        int modifiedBy = (int) claims.get("userid");
//        schemeUpdate = schemeUpdate + ",modifiedBy = " + modifiedBy + ",modifiedOn = current_timestamp() where schemeid = " + schemeid;
//        try {
//            Context = new javax.naming.InitialContext();
//            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//            try (Connection conn = ds.getConnection();
//                    PreparedStatement ps_updateScheme = conn.prepareStatement(schemeUpdate);) {
//                int updatedRows = ps_updateScheme.executeUpdate();
//                if (updatedRows > 0) {
//                    resultMap.put("resultCode", 0);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record updated successfully");
//                } else {
//                    resultMap.put("resultCode", -3);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record not exists");
//                }
//            }
//        } catch (NamingException | SQLException ex) {
//            resultMap.put("resultCode", -1);
//            resultMap.put("resultDetails", "DB Error in updating field: " + ex.toString());
//            return resultMap;
//        }
//        return resultMap;
//    }
//
//    public HashMap deleteScheme(SchemeEntity schemeEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        if (httpServletRequest.getHeader("token") != null) {
//            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
//            if (token.contains("Invalid Token")) {
//                resultMap.put("resultCode", -4);
//                resultMap.put("resultDetails", "Invalid Token");
//                return resultMap;
//            }
//        } else {
//            resultMap.put("resultCode", -4);
//            resultMap.put("resultDetails", "Unauthorized credentials");
//            return resultMap;
//        }
//        if (schemeEntity_i == null) {
//            resultMap.put("resultCode", -2);
//            resultMap.put("resultDetails", "Invalid Input");
//            return resultMap;
//        }
//        int schemeId = schemeEntity_i.getSchemeId();
//        if (schemeId <= 0) {
//            resultMap.put("resultCode", -3);
//            resultMap.put("resultDetails", "Scheme Id not found");
//            return resultMap;
//        }
//        try {
//            Context = new javax.naming.InitialContext();
//            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//            String deleteScheme = "delete from dbt_scheme_master where schemeid = ?";
//            try (Connection conn = ds.getConnection();
//                    PreparedStatement ps_deleteScheme = conn.prepareStatement(deleteScheme);) {
//                ps_deleteScheme.setInt(1, schemeId);
//                int deleteResultSet = ps_deleteScheme.executeUpdate();
//                if (deleteResultSet > 0) {
//                    resultMap.put("resultCode", 0);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record deleted successfully");
//                } else {
//                    resultMap.put("resultCode", -3);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record not exists");
//                }
//            }
//
//        } catch (NamingException | SQLException ex) {
//            //logger.error("Content Scheme Service DB Exception: " + ex.toString());
//            resultMap.put("resultCode", -1);
//            resultMap.put("resultDetails", "DB Error in deleting scheme details: " + ex.toString());
//            return resultMap;
//        }
//        return resultMap;
//    }

}
