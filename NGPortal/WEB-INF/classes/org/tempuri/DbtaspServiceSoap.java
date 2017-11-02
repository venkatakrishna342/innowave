
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DbtaspServiceSoap.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 22, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/

package org.tempuri;

public interface DbtaspServiceSoap extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public int VCAvailable(java.lang.String applicantFullName, java.lang.String VC, java.lang.String caste, java.lang.String fathername) throws java.rmi.RemoteException;
}
