
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarStatusService.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 23, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package org.npci.aadhaar;

public interface AadhaarStatusService extends javax.xml.rpc.Service {
    public java.lang.String getAadhaarStatusPortAddress();

    public org.npci.aadhaar.AadhaarStatus getAadhaarStatusPort() throws javax.xml.rpc.ServiceException;

    public org.npci.aadhaar.AadhaarStatus getAadhaarStatusPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
