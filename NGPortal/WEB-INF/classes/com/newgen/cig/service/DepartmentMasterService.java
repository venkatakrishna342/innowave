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

import com.newgen.cig.entity.DepartmentMasterEntity;
import com.newgen.utility.AppConstants;

public class DepartmentMasterService {

    InitialContext Context;
    DataSource ds;
    String token = null;
    Claims claims;

    public DepartmentMasterService() {
    }

    public HashMap getDepartmentIndex(DepartmentMasterEntity departmentmasterEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (httpServletRequest.getHeader("token") != null) {
            token = AppConstants.validateJWT(httpServletRequest.getHeader("token"));
            if (token.contains("Invalid Token")) {
                resultMap.put("resultCode", -3);
                resultMap.put("resultDetails", "Invalid Token");
                return resultMap;
            }
        }
        if (departmentmasterEntity_i == null) {
            resultMap.put("resultCode", -2);
            resultMap.put("resultDetails", "Invalid input");
            return resultMap;
        }
        String selectDepartmentIndex = "select departmentid,departmentname,status,description,modifiedBy,"
                + "DATE_FORMAT(modifiedOn, '%Y-%m-%d %T') as modifiedOn from dbt_department_master where status = 0";
        int departmentid = departmentmasterEntity_i.getDepartmentid();
        if (departmentid > 0) {
            selectDepartmentIndex = selectDepartmentIndex + " and departmentid = ?";
        }
        ArrayList<DepartmentMasterEntity> masterEntityList = new ArrayList<>();
        try {
            Context = new javax.naming.InitialContext();
            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
            try (
                    Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(selectDepartmentIndex);) {
                int argIndex = 1;
                if (departmentid > 0) {
                    ps.setInt(argIndex++, departmentid);
                }
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        DepartmentMasterEntity masterEntity = new DepartmentMasterEntity();
                        masterEntity.setDepartmentid(rs.getInt(1));
                        masterEntity.setDepartmentname(rs.getString(2));
                        masterEntity.setStatus(rs.getInt(3));
                        String str = (rs.getInt(3) == 0 ? "Approved" : "Deleted");
                        masterEntity.setStrstatus(str);
                        masterEntity.setDescription(rs.getString(4));
                        masterEntity.setModifiedBy(rs.getInt(5));
                        masterEntity.setStrmodifiedOn(rs.getString(6));
                        masterEntityList.add(masterEntity);
                    }
                    resultMap.put("resultCode", 0);
                    httpServletResponse.setHeader("token", token);
                    resultMap.put("resultDetails", masterEntityList);
                }
            }
        } catch (NamingException | SQLException ex) {
            resultMap.put("resultCode", -1);
            resultMap.put("resultDetails", "ContentDepartmentService DB Exception: " + ex.toString());
            return resultMap;
        }
        return resultMap;
    }

//    public HashMap insertDepartment(DepartmentMasterEntity masterEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
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
//        if (masterEntity_i == null) {
//            resultMap.put("resultCode", -2);
//            resultMap.put("resultDetails", "Invalid input");
//            return resultMap;
//        }
//        String departmentname = masterEntity_i.getDepartmentname();
//        if (departmentname == null) {
//           resultMap.put("resultCode", -3);
//            resultMap.put("resultDetails", "Department Name not found");
//            return resultMap;
//        }
//        try {
//            Context = new javax.naming.InitialContext();
//            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//            try (Connection conn = ds.getConnection();) {
//                claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
//                int modifiedBy = (int) claims.get("userid");
//                String masterEntity = "";
//                String selectdepartmentname = "select departmentname from dbt_department_master where departmentname = ?";
//                try (PreparedStatement ps = conn.prepareStatement(selectdepartmentname);) {
//                    ps.setString(1, departmentname);
//                    ResultSet rs = ps.executeQuery();
//                    while (rs.next()) {
//                        masterEntity = rs.getString(1);
//                    }
//                }
//                if (!masterEntity.equals(departmentname)) {
//                    String insertDept = "insert into dbt_department_master(departmentid,"
//                            + "departmentname,"
//                            + "status,"
//                            + "description,"
//                            + "modifiedBy,"
//                            + "modifiedOn)"
//                            + "VALUES(0,"
//                            + "?,"
//                            + "0,"
//                            + "?,"
//                            + "?,"
//                            + "CURRENT_TIMESTAMP())";
//                    try (PreparedStatement ps_insertDept = conn.prepareStatement(insertDept);) {
//                        ps_insertDept.setString(1, masterEntity_i.getDepartmentname());
//                        ps_insertDept.setString(2, masterEntity_i.getDescription());
//                        ps_insertDept.setInt(3, modifiedBy);
//                        int insertResultSet = ps_insertDept.executeUpdate();
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
//                    resultMap.put("resultDetails", "Record already exist with same department name");
//                    return resultMap;
//                }
//            }
//
//        } catch (NamingException | SQLException ex) {
//            resultMap.put("resultCode", -1);
//            resultMap.put("resultDetails", "ContentDepartmentService DB Exception: " + ex.toString());
//            return resultMap;
//        }
//        return resultMap;
//    }
//
//    public HashMap updateDepartment(DepartmentMasterEntity departmentmasterEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
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
//        if (departmentmasterEntity_i == null) {
//            resultMap.put("resultCode", -2);
//            resultMap.put("resultDetails", "Invalid input");
//            return resultMap;
//        }
//        int departmentid = departmentmasterEntity_i.getDepartmentid();
//        if (departmentid <= 0) {
//            resultMap.put("resultCode", -3);
//            resultMap.put("resultDetails", "Department Id not found");
//            return resultMap;
//        }
//        String departmentname = departmentmasterEntity_i.getDepartmentname();
//        String description = departmentmasterEntity_i.getDescription();
//        String departmentUpdate = "Update dbt_department_master SET ";
//        String appendString;
//        int checkflag = 0;
//        if (departmentname != null) {
//            appendString = (checkflag > 0) ? ",departmentname  = '" + departmentname + "'" : " departmentname = '" + departmentname + "'";
//            departmentUpdate = departmentUpdate + appendString;
//            checkflag = 1;
//        }
//        if (description != null) {
//            appendString = (checkflag > 0) ? ", description = '" + description + "'" : " description = '" + description + "'";
//            departmentUpdate = departmentUpdate + appendString;
//            checkflag = 1;
//        }
//        claims = Jwts.parser().setSigningKey(AppConstants.JWT_SECRET).parseClaimsJws(token).getBody();
//        int modifiedBy = (int) claims.get("userid");
//        departmentUpdate = departmentUpdate + ",modifiedBy = " + modifiedBy + ",modifiedOn = current_timestamp() where departmentid = " + departmentid;
//        try {
//            Context = new javax.naming.InitialContext();
//            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//            try (Connection conn = ds.getConnection();
//                    PreparedStatement ps_updateDepartment = conn.prepareStatement(departmentUpdate);) {
//                int updatedRows = ps_updateDepartment.executeUpdate(departmentUpdate);
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
//            resultMap.put("resultDetails", "DepartmentMasterService DB Exception: " + ex.toString());
//            return resultMap;
//        }
//        return resultMap;
//    }
//
//    public HashMap deleteDepartment(DepartmentMasterEntity departmentmasterEntity_i, HashMap resultMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
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
//        if (departmentmasterEntity_i == null) {
//            resultMap.put("resultCode", -2);
//            resultMap.put("resultDetails", "Invalid input");
//        }
//        int departmentid = departmentmasterEntity_i.getDepartmentid();
//        if (departmentid <= 0) {
//            resultMap.put("resultCode", -3);
//            resultMap.put("resultDetails", "Department Id not found");
//            return resultMap;
//        }
//        try {
//            Context = new javax.naming.InitialContext();
//            ds = (javax.sql.DataSource) Context.lookup(AppConstants.getContextLookup());
//            int departmentId = departmentmasterEntity_i.getDepartmentid();
//            String deleteDepartment = "delete from dbt_department_master where departmentid = ?";
//            try (Connection conn = ds.getConnection();
//                    PreparedStatement ps = conn.prepareStatement(deleteDepartment);) {
//                ps.setInt(1, departmentId);
//                int delete = ps.executeUpdate();
//                if (delete > 0) {
//                    resultMap.put("resultCode", 0);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record successfully deleted");
//                } else {
//                    resultMap.put("resultCode", -3);
//                    httpServletResponse.setHeader("token", token);
//                    resultMap.put("resultDetails", "Record not exists");
//                }
//            }
//        } catch (NamingException | SQLException ex) {
//            //logger.error("Content Scheme Service DB Exception: " + ex.toString());
//            resultMap.put("resultCode", -1);
//            resultMap.put("resultDetails", "ContentDepartmentService DB Exception: " + ex.toString());
//            return resultMap;
//        }
//        return resultMap;
//    }
}
