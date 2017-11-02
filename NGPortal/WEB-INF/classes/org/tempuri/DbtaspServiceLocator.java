/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : DbtaspServiceLocator.java
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

public class DbtaspServiceLocator extends org.apache.axis.client.Service implements org.tempuri.DbtaspService {

    public DbtaspServiceLocator() {
    }


    public DbtaspServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DbtaspServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for dbtaspServiceSoap
    private java.lang.String dbtaspServiceSoap_address = "http://mis.barti.in/dbtservice/dbtaspservice.asmx";

    public java.lang.String getdbtaspServiceSoapAddress() {
        return dbtaspServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String dbtaspServiceSoapWSDDServiceName = "dbtaspServiceSoap";

    public java.lang.String getdbtaspServiceSoapWSDDServiceName() {
        return dbtaspServiceSoapWSDDServiceName;
    }

    public void setdbtaspServiceSoapWSDDServiceName(java.lang.String name) {
        dbtaspServiceSoapWSDDServiceName = name;
    }

    public org.tempuri.DbtaspServiceSoap getdbtaspServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(dbtaspServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getdbtaspServiceSoap(endpoint);
    }

    public org.tempuri.DbtaspServiceSoap getdbtaspServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.DbtaspServiceSoapStub _stub = new org.tempuri.DbtaspServiceSoapStub(portAddress, this);
            _stub.setPortName(getdbtaspServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setdbtaspServiceSoapEndpointAddress(java.lang.String address) {
        dbtaspServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.DbtaspServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.DbtaspServiceSoapStub _stub = new org.tempuri.DbtaspServiceSoapStub(new java.net.URL(dbtaspServiceSoap_address), this);
                _stub.setPortName(getdbtaspServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("dbtaspServiceSoap".equals(inputPortName)) {
            return getdbtaspServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "dbtaspService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "dbtaspServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("dbtaspServiceSoap".equals(portName)) {
            setdbtaspServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
