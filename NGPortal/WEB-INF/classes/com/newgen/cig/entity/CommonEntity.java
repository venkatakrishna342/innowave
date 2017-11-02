/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : CommonEntity.java
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

public class CommonEntity {
	
		private String newpassword;
		private String oldpassword;
		private String userId;
		private String instituteId;
		private String districtId;
		private String roleId;

		
		
	    public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getNewpassword() {
			return newpassword;
		}
		public void setNewpassword(String newpassword) {
			this.newpassword = newpassword;
		}
		public String getOldpassword() {
			return oldpassword;
		}
		public void setOldpassword(String oldpassword) {
			this.oldpassword = oldpassword;
		}
		public String getInstituteId() {
			return instituteId;
		}
		public void setInstituteId(String instituteId) {
			this.instituteId = instituteId;
		}
		public String getDistrictId() {
			return districtId;
		}
		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}
		public String getRoleId() {
			return roleId;
		}
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}


















		

}
