/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DbtaspService.java
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

public interface DbtaspService extends javax.xml.rpc.Service {
    public java.lang.String getdbtaspServiceSoapAddress();

    public org.tempuri.DbtaspServiceSoap getdbtaspServiceSoap() throws javax.xml.rpc.ServiceException;

    public org.tempuri.DbtaspServiceSoap getdbtaspServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
