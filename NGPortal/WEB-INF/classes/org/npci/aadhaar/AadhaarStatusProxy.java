/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarStatusProxy.java
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

public class AadhaarStatusProxy implements org.npci.aadhaar.AadhaarStatus {
  private String _endpoint = null;
  private org.npci.aadhaar.AadhaarStatus aadhaarStatus = null;
  
  public AadhaarStatusProxy() {
    _initAadhaarStatusProxy();
  }
  
  public AadhaarStatusProxy(String endpoint) {
    _endpoint = endpoint;
    _initAadhaarStatusProxy();
  }
  
  private void _initAadhaarStatusProxy() {
    try {
      aadhaarStatus = (new org.npci.aadhaar.AadhaarStatusServiceLocator()).getAadhaarStatusPort();
      if (aadhaarStatus != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)aadhaarStatus)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)aadhaarStatus)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (aadhaarStatus != null)
      ((javax.xml.rpc.Stub)aadhaarStatus)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.npci.aadhaar.AadhaarStatus getAadhaarStatus() {
    if (aadhaarStatus == null)
      _initAadhaarStatusProxy();
    return aadhaarStatus;
  }
  
  public org.npci.aadhaar.AadhaarStatusResponse getAadhaarStatus(org.npci.aadhaar.AadhaarStatusRequest arg0) throws java.rmi.RemoteException, org.npci.aadhaar.Exception, org.npci.aadhaar.UnknownHostException{
    if (aadhaarStatus == null)
      _initAadhaarStatusProxy();
    return aadhaarStatus.getAadhaarStatus(arg0);
  }
  
  
}