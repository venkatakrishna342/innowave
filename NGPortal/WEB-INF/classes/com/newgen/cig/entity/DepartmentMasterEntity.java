/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DepartmentMasterEntity.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : March 10, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.cig.entity;

import java.sql.Date;

public class DepartmentMasterEntity {
    private int departmentid;
    private String departmentname;
    private int status;
    private String description; 
    
    private int modifiedBy;
    private Date modifiedOn;
    private String strstatus;
    private String strmodifiedOn;
    
    public String getStrmodifiedOn() {
        return strmodifiedOn;
    }

    public void setStrmodifiedOn(String strmodifiedOn) {
        this.strmodifiedOn = strmodifiedOn;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getStrstatus() {
        return strstatus;
    }

    public void setStrstatus(String strstatus) {
        this.strstatus = strstatus;
    }
}
