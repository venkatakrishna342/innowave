/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarStatusServiceLocator.java
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

public class AadhaarStatusServiceLocator extends org.apache.axis.client.Service implements org.npci.aadhaar.AadhaarStatusService {

    public AadhaarStatusServiceLocator() {
    }


    public AadhaarStatusServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AadhaarStatusServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AadhaarStatusPort
    private java.lang.String AadhaarStatusPort_address = "https://192.168.179.231/CMAadhaar/AadhaarStatusService";

    public java.lang.String getAadhaarStatusPortAddress() {
        return AadhaarStatusPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AadhaarStatusPortWSDDServiceName = "AadhaarStatusPort";

    public java.lang.String getAadhaarStatusPortWSDDServiceName() {
        return AadhaarStatusPortWSDDServiceName;
    }

    public void setAadhaarStatusPortWSDDServiceName(java.lang.String name) {
        AadhaarStatusPortWSDDServiceName = name;
    }

    public org.npci.aadhaar.AadhaarStatus getAadhaarStatusPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AadhaarStatusPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAadhaarStatusPort(endpoint);
    }

    public org.npci.aadhaar.AadhaarStatus getAadhaarStatusPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.npci.aadhaar.AadhaarStatusPortBindingStub _stub = new org.npci.aadhaar.AadhaarStatusPortBindingStub(portAddress, this);
            _stub.setPortName(getAadhaarStatusPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAadhaarStatusPortEndpointAddress(java.lang.String address) {
        AadhaarStatusPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.npci.aadhaar.AadhaarStatus.class.isAssignableFrom(serviceEndpointInterface)) {
                org.npci.aadhaar.AadhaarStatusPortBindingStub _stub = new org.npci.aadhaar.AadhaarStatusPortBindingStub(new java.net.URL(AadhaarStatusPort_address), this);
                _stub.setPortName(getAadhaarStatusPortWSDDServiceName());
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
        if ("AadhaarStatusPort".equals(inputPortName)) {
            return getAadhaarStatusPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://aadhaar.npci.org/", "AadhaarStatusService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://aadhaar.npci.org/", "AadhaarStatusPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AadhaarStatusPort".equals(portName)) {
            setAadhaarStatusPortEndpointAddress(address);
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
