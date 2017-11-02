
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarStatus.java
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

package org.npci.aadhaar;

public interface AadhaarStatus extends java.rmi.Remote {
    public org.npci.aadhaar.AadhaarStatusResponse getAadhaarStatus(org.npci.aadhaar.AadhaarStatusRequest arg0) throws java.rmi.RemoteException, org.npci.aadhaar.Exception, org.npci.aadhaar.UnknownHostException;
}
