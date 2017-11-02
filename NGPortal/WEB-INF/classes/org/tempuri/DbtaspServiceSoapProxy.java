/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DbtaspServiceSoapProxy.java
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

public class DbtaspServiceSoapProxy implements org.tempuri.DbtaspServiceSoap {
  private String _endpoint = null;
  private org.tempuri.DbtaspServiceSoap dbtaspServiceSoap = null;
  
  public DbtaspServiceSoapProxy() {
    _initDbtaspServiceSoapProxy();
  }
  
  public DbtaspServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initDbtaspServiceSoapProxy();
  }
  
  private void _initDbtaspServiceSoapProxy() {
    try {
      dbtaspServiceSoap = (new org.tempuri.DbtaspServiceLocator()).getdbtaspServiceSoap();
      if (dbtaspServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dbtaspServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dbtaspServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dbtaspServiceSoap != null)
      ((javax.xml.rpc.Stub)dbtaspServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.DbtaspServiceSoap getDbtaspServiceSoap() {
    if (dbtaspServiceSoap == null)
      _initDbtaspServiceSoapProxy();
    return dbtaspServiceSoap;
  }
  
  public java.lang.String helloWorld() throws java.rmi.RemoteException{
    if (dbtaspServiceSoap == null)
      _initDbtaspServiceSoapProxy();
    return dbtaspServiceSoap.helloWorld();
  }
  
  public int VCAvailable(java.lang.String applicantFullName, java.lang.String VC, java.lang.String caste, java.lang.String fathername) throws java.rmi.RemoteException{
    if (dbtaspServiceSoap == null)
      _initDbtaspServiceSoapProxy();
    return dbtaspServiceSoap.VCAvailable(applicantFullName, VC, caste, fathername);
  }
  
  
}