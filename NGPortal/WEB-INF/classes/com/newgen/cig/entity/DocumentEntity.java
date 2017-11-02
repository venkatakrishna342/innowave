/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DocumentEntity.java
* Author              : Varun Saddi
* Date written
* (DD/MM/YYYY)        : April 10, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any)) 
*
 ************************************************************************************************************/
package com.newgen.cig.entity;

/**
 * @author Varun Saddi
 *
 */
public class DocumentEntity {
	
	private int docId;
	private int documentId;
	private String type;
	private String path;
	private String docEncStr;
	private String number;
	private String registrationDate;
	private String registrationAuthority;
	private String docName;
	
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	/**
	 * @return the docId
	 */
	public int getDocId() {
		return docId;
	}
	/**
	 * @param docId the docId to set
	 */
	public void setDocId(int docId) {
		this.docId = docId;
	}
	/**
	 * @return the documentId
	 */
	public int getDocumentId() {
		return documentId;
	}
	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the docEncStr
	 */
	public String getDocEncStr() {
		return docEncStr;
	}
	/**
	 * @param docEncStr the docEncStr to set
	 */
	public void setDocEncStr(String docEncStr) {
		this.docEncStr = docEncStr;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}
	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	/**
	 * @return the registrationAuthority
	 */
	public String getRegistrationAuthority() {
		return registrationAuthority;
	}
	/**
	 * @param registrationAuthority the registrationAuthority to set
	 */
	public void setRegistrationAuthority(String registrationAuthority) {
		this.registrationAuthority = registrationAuthority;
	}
	

}
