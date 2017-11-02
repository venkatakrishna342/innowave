/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : Check_Login_Credential.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Jan 20, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/

package com.newgen.activityclass;

import com.google.gson.JsonObject;
import com.newgen.daoImpl.DB_GetUserData;

public class Check_Login_Credential {
	
	public String checkUserIDinDB(String userid) 
	{
		// TODO Auto-generated method stub
		DB_GetUserData dao = new DB_GetUserData();
		String isUserExists = dao.checkUserIDinDB(userid);
		
		return isUserExists;
	}
}
