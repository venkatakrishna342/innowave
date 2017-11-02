/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DateFormatConverter.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 26, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatConverter {
	
	public String dateFormatConverter(String dateValue,String currentFormat,String requiredFormat)
	{
		String dateReturned="";
		Date date=new Date();
		DateFormat parser = new SimpleDateFormat(currentFormat); 
		try 
		{
			if(("").equalsIgnoreCase(dateValue) || ("null").equalsIgnoreCase(dateValue) || dateValue == null){
				return dateValue;
			}
			if(dateValue.contains("T")){
				int len = dateValue.indexOf("T");
				dateValue = dateValue.substring(0,len);
				System.out.println(dateValue);
				
				return dateValue;
			}
			if(dateValue.contains("-")){
				
				return dateValue.toString();
			}			
			date = (Date) parser.parse(dateValue);
			DateFormat formatter = new SimpleDateFormat(requiredFormat); 
			dateReturned=formatter.format(date).toString();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateReturned.toString();
	}
	
	public String dateTimeFormatConverter(String dateValue,String currentFormat,String requiredFormat)
	{
		String dateReturned="";
		Date date=new Date();
		DateFormat parser = new SimpleDateFormat(currentFormat); 
		try 
		{
			if(("").equalsIgnoreCase(dateValue) || ("null").equalsIgnoreCase(dateValue) || dateValue == null){
				return dateValue;
			}
			if(dateValue.contains("T")){
				int len = dateValue.indexOf("T");
				dateValue = dateValue.substring(0,len);
				System.out.println(dateValue);
				
				return dateValue;
			}
			/*if(dateValue.contains("/")){
				dateValue = dateValue.replaceAll("/","-");
			}*/			
			date = (Date) parser.parse(dateValue);
			DateFormat formatter = new SimpleDateFormat(requiredFormat); 
			dateReturned=formatter.format(date).toString();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateReturned.toString();
	}

}
