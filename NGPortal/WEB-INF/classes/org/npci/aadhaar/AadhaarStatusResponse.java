
/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : AadhaarStatusResponse.java
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

public class AadhaarStatusResponse  implements java.io.Serializable {
    private java.lang.String aadhaarNumber;

    private java.lang.String bankName;

    private java.lang.String error;

    private java.lang.String lastUpdatedDate;

    private java.lang.String mandateCustDate;

    private java.lang.String mandateFlag;

    private java.lang.String mobileNumber;

    private java.lang.String ODDate;

    private java.lang.String ODFlag;

    private java.lang.String processedDateTimeStamp;

    private java.lang.String requestNumber;

    private java.lang.String requestReceivedDateTime;

    private java.lang.String requestedDateTimeStamp;

    private java.lang.String status;

    public AadhaarStatusResponse() {
    }

    public AadhaarStatusResponse(
           java.lang.String aadhaarNumber,
           java.lang.String bankName,
           java.lang.String error,
           java.lang.String lastUpdatedDate,
           java.lang.String mandateCustDate,
           java.lang.String mandateFlag,
           java.lang.String mobileNumber,
           java.lang.String ODDate,
           java.lang.String ODFlag,
           java.lang.String processedDateTimeStamp,
           java.lang.String requestNumber,
           java.lang.String requestReceivedDateTime,
           java.lang.String requestedDateTimeStamp,
           java.lang.String status) {
           this.aadhaarNumber = aadhaarNumber;
           this.bankName = bankName;
           this.error = error;
           this.lastUpdatedDate = lastUpdatedDate;
           this.mandateCustDate = mandateCustDate;
           this.mandateFlag = mandateFlag;
           this.mobileNumber = mobileNumber;
           this.ODDate = ODDate;
           this.ODFlag = ODFlag;
           this.processedDateTimeStamp = processedDateTimeStamp;
           this.requestNumber = requestNumber;
           this.requestReceivedDateTime = requestReceivedDateTime;
           this.requestedDateTimeStamp = requestedDateTimeStamp;
           this.status = status;
    }


    /**
     * Gets the aadhaarNumber value for this AadhaarStatusResponse.
     * 
     * @return aadhaarNumber
     */
    public java.lang.String getAadhaarNumber() {
        return aadhaarNumber;
    }


    /**
     * Sets the aadhaarNumber value for this AadhaarStatusResponse.
     * 
     * @param aadhaarNumber
     */
    public void setAadhaarNumber(java.lang.String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }


    /**
     * Gets the bankName value for this AadhaarStatusResponse.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this AadhaarStatusResponse.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the error value for this AadhaarStatusResponse.
     * 
     * @return error
     */
    public java.lang.String getError() {
        return error;
    }


    /**
     * Sets the error value for this AadhaarStatusResponse.
     * 
     * @param error
     */
    public void setError(java.lang.String error) {
        this.error = error;
    }


    /**
     * Gets the lastUpdatedDate value for this AadhaarStatusResponse.
     * 
     * @return lastUpdatedDate
     */
    public java.lang.String getLastUpdatedDate() {
        return lastUpdatedDate;
    }


    /**
     * Sets the lastUpdatedDate value for this AadhaarStatusResponse.
     * 
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(java.lang.String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


    /**
     * Gets the mandateCustDate value for this AadhaarStatusResponse.
     * 
     * @return mandateCustDate
     */
    public java.lang.String getMandateCustDate() {
        return mandateCustDate;
    }


    /**
     * Sets the mandateCustDate value for this AadhaarStatusResponse.
     * 
     * @param mandateCustDate
     */
    public void setMandateCustDate(java.lang.String mandateCustDate) {
        this.mandateCustDate = mandateCustDate;
    }


    /**
     * Gets the mandateFlag value for this AadhaarStatusResponse.
     * 
     * @return mandateFlag
     */
    public java.lang.String getMandateFlag() {
        return mandateFlag;
    }


    /**
     * Sets the mandateFlag value for this AadhaarStatusResponse.
     * 
     * @param mandateFlag
     */
    public void setMandateFlag(java.lang.String mandateFlag) {
        this.mandateFlag = mandateFlag;
    }


    /**
     * Gets the mobileNumber value for this AadhaarStatusResponse.
     * 
     * @return mobileNumber
     */
    public java.lang.String getMobileNumber() {
        return mobileNumber;
    }


    /**
     * Sets the mobileNumber value for this AadhaarStatusResponse.
     * 
     * @param mobileNumber
     */
    public void setMobileNumber(java.lang.String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    /**
     * Gets the ODDate value for this AadhaarStatusResponse.
     * 
     * @return ODDate
     */
    public java.lang.String getODDate() {
        return ODDate;
    }


    /**
     * Sets the ODDate value for this AadhaarStatusResponse.
     * 
     * @param ODDate
     */
    public void setODDate(java.lang.String ODDate) {
        this.ODDate = ODDate;
    }


    /**
     * Gets the ODFlag value for this AadhaarStatusResponse.
     * 
     * @return ODFlag
     */
    public java.lang.String getODFlag() {
        return ODFlag;
    }


    /**
     * Sets the ODFlag value for this AadhaarStatusResponse.
     * 
     * @param ODFlag
     */
    public void setODFlag(java.lang.String ODFlag) {
        this.ODFlag = ODFlag;
    }


    /**
     * Gets the processedDateTimeStamp value for this AadhaarStatusResponse.
     * 
     * @return processedDateTimeStamp
     */
    public java.lang.String getProcessedDateTimeStamp() {
        return processedDateTimeStamp;
    }


    /**
     * Sets the processedDateTimeStamp value for this AadhaarStatusResponse.
     * 
     * @param processedDateTimeStamp
     */
    public void setProcessedDateTimeStamp(java.lang.String processedDateTimeStamp) {
        this.processedDateTimeStamp = processedDateTimeStamp;
    }


    /**
     * Gets the requestNumber value for this AadhaarStatusResponse.
     * 
     * @return requestNumber
     */
    public java.lang.String getRequestNumber() {
        return requestNumber;
    }


    /**
     * Sets the requestNumber value for this AadhaarStatusResponse.
     * 
     * @param requestNumber
     */
    public void setRequestNumber(java.lang.String requestNumber) {
        this.requestNumber = requestNumber;
    }


    /**
     * Gets the requestReceivedDateTime value for this AadhaarStatusResponse.
     * 
     * @return requestReceivedDateTime
     */
    public java.lang.String getRequestReceivedDateTime() {
        return requestReceivedDateTime;
    }


    /**
     * Sets the requestReceivedDateTime value for this AadhaarStatusResponse.
     * 
     * @param requestReceivedDateTime
     */
    public void setRequestReceivedDateTime(java.lang.String requestReceivedDateTime) {
        this.requestReceivedDateTime = requestReceivedDateTime;
    }


    /**
     * Gets the requestedDateTimeStamp value for this AadhaarStatusResponse.
     * 
     * @return requestedDateTimeStamp
     */
    public java.lang.String getRequestedDateTimeStamp() {
        return requestedDateTimeStamp;
    }


    /**
     * Sets the requestedDateTimeStamp value for this AadhaarStatusResponse.
     * 
     * @param requestedDateTimeStamp
     */
    public void setRequestedDateTimeStamp(java.lang.String requestedDateTimeStamp) {
        this.requestedDateTimeStamp = requestedDateTimeStamp;
    }


    /**
     * Gets the status value for this AadhaarStatusResponse.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this AadhaarStatusResponse.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AadhaarStatusResponse)) return false;
        AadhaarStatusResponse other = (AadhaarStatusResponse) obj;
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
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.lastUpdatedDate==null && other.getLastUpdatedDate()==null) || 
             (this.lastUpdatedDate!=null &&
              this.lastUpdatedDate.equals(other.getLastUpdatedDate()))) &&
            ((this.mandateCustDate==null && other.getMandateCustDate()==null) || 
             (this.mandateCustDate!=null &&
              this.mandateCustDate.equals(other.getMandateCustDate()))) &&
            ((this.mandateFlag==null && other.getMandateFlag()==null) || 
             (this.mandateFlag!=null &&
              this.mandateFlag.equals(other.getMandateFlag()))) &&
            ((this.mobileNumber==null && other.getMobileNumber()==null) || 
             (this.mobileNumber!=null &&
              this.mobileNumber.equals(other.getMobileNumber()))) &&
            ((this.ODDate==null && other.getODDate()==null) || 
             (this.ODDate!=null &&
              this.ODDate.equals(other.getODDate()))) &&
            ((this.ODFlag==null && other.getODFlag()==null) || 
             (this.ODFlag!=null &&
              this.ODFlag.equals(other.getODFlag()))) &&
            ((this.processedDateTimeStamp==null && other.getProcessedDateTimeStamp()==null) || 
             (this.processedDateTimeStamp!=null &&
              this.processedDateTimeStamp.equals(other.getProcessedDateTimeStamp()))) &&
            ((this.requestNumber==null && other.getRequestNumber()==null) || 
             (this.requestNumber!=null &&
              this.requestNumber.equals(other.getRequestNumber()))) &&
            ((this.requestReceivedDateTime==null && other.getRequestReceivedDateTime()==null) || 
             (this.requestReceivedDateTime!=null &&
              this.requestReceivedDateTime.equals(other.getRequestReceivedDateTime()))) &&
            ((this.requestedDateTimeStamp==null && other.getRequestedDateTimeStamp()==null) || 
             (this.requestedDateTimeStamp!=null &&
              this.requestedDateTimeStamp.equals(other.getRequestedDateTimeStamp()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getLastUpdatedDate() != null) {
            _hashCode += getLastUpdatedDate().hashCode();
        }
        if (getMandateCustDate() != null) {
            _hashCode += getMandateCustDate().hashCode();
        }
        if (getMandateFlag() != null) {
            _hashCode += getMandateFlag().hashCode();
        }
        if (getMobileNumber() != null) {
            _hashCode += getMobileNumber().hashCode();
        }
        if (getODDate() != null) {
            _hashCode += getODDate().hashCode();
        }
        if (getODFlag() != null) {
            _hashCode += getODFlag().hashCode();
        }
        if (getProcessedDateTimeStamp() != null) {
            _hashCode += getProcessedDateTimeStamp().hashCode();
        }
        if (getRequestNumber() != null) {
            _hashCode += getRequestNumber().hashCode();
        }
        if (getRequestReceivedDateTime() != null) {
            _hashCode += getRequestReceivedDateTime().hashCode();
        }
        if (getRequestedDateTimeStamp() != null) {
            _hashCode += getRequestedDateTimeStamp().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AadhaarStatusResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://aadhaar.npci.org/", "aadhaarStatusResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aadhaarNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "aadhaarNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "bankName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdatedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "lastUpdatedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandateCustDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandateCustDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandateFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mandateFlag"));
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
        elemField.setFieldName("ODDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ODDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ODFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ODFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("processedDateTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "processedDateTimeStamp"));
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
        elemField.setFieldName("requestReceivedDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestReceivedDateTime"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
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
