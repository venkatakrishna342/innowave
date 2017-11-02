
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarStatusRequest.java
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

public class AadhaarStatusRequest  implements java.io.Serializable {
    private java.lang.String aadhaarNumber;

    private java.lang.String mobileNumber;

    private java.lang.String requestNumber;

    private java.lang.String requestedDateTimeStamp;

    public AadhaarStatusRequest() {
    }

    public AadhaarStatusRequest(
           java.lang.String aadhaarNumber,
           java.lang.String mobileNumber,
           java.lang.String requestNumber,
           java.lang.String requestedDateTimeStamp) {
           this.aadhaarNumber = aadhaarNumber;
           this.mobileNumber = mobileNumber;
           this.requestNumber = requestNumber;
           this.requestedDateTimeStamp = requestedDateTimeStamp;
    }


    /**
     * Gets the aadhaarNumber value for this AadhaarStatusRequest.
     * 
     * @return aadhaarNumber
     */
    public java.lang.String getAadhaarNumber() {
        return aadhaarNumber;
    }


    /**
     * Sets the aadhaarNumber value for this AadhaarStatusRequest.
     * 
     * @param aadhaarNumber
     */
    public void setAadhaarNumber(java.lang.String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }


    /**
     * Gets the mobileNumber value for this AadhaarStatusRequest.
     * 
     * @return mobileNumber
     */
    public java.lang.String getMobileNumber() {
        return mobileNumber;
    }


    /**
     * Sets the mobileNumber value for this AadhaarStatusRequest.
     * 
     * @param mobileNumber
     */
    public void setMobileNumber(java.lang.String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    /**
     * Gets the requestNumber value for this AadhaarStatusRequest.
     * 
     * @return requestNumber
     */
    public java.lang.String getRequestNumber() {
        return requestNumber;
    }


    /**
     * Sets the requestNumber value for this AadhaarStatusRequest.
     * 
     * @param requestNumber
     */
    public void setRequestNumber(java.lang.String requestNumber) {
        this.requestNumber = requestNumber;
    }


    /**
     * Gets the requestedDateTimeStamp value for this AadhaarStatusRequest.
     * 
     * @return requestedDateTimeStamp
     */
    public java.lang.String getRequestedDateTimeStamp() {
        return requestedDateTimeStamp;
    }


    /**
     * Sets the requestedDateTimeStamp value for this AadhaarStatusRequest.
     * 
     * @param requestedDateTimeStamp
     */
    public void setRequestedDateTimeStamp(java.lang.String requestedDateTimeStamp) {
        this.requestedDateTimeStamp = requestedDateTimeStamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AadhaarStatusRequest)) return false;
        AadhaarStatusRequest other = (AadhaarStatusRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aadhaarNumber==null && other.getAadhaarNumber()==null) || 
             (this.aadhaarNumber!=null &&
              this.aadhaarNumber.equals(other.getAadhaarNumber()))) &&
            ((this.mobileNumber==null && other.getMobileNumber()==null) || 
             (this.mobileNumber!=null &&
              this.mobileNumber.equals(other.getMobileNumber()))) &&
            ((this.requestNumber==null && other.getRequestNumber()==null) || 
             (this.requestNumber!=null &&
              this.requestNumber.equals(other.getRequestNumber()))) &&
            ((this.requestedDateTimeStamp==null && other.getRequestedDateTimeStamp()==null) || 
             (this.requestedDateTimeStamp!=null &&
              this.requestedDateTimeStamp.equals(other.getRequestedDateTimeStamp())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAadhaarNumber() != null) {
            _hashCode += getAadhaarNumber().hashCode();
        }
        if (getMobileNumber() != null) {
            _hashCode += getMobileNumber().hashCode();
        }
        if (getRequestNumber() != null) {
            _hashCode += getRequestNumber().hashCode();
        }
        if (getRequestedDateTimeStamp() != null) {
            _hashCode += getRequestedDateTimeStamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AadhaarStatusRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://aadhaar.npci.org/", "aadhaarStatusRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aadhaarNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aadhaarNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mobileNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedDateTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestedDateTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
