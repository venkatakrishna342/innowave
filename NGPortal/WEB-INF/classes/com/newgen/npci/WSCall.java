/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : WSCall.java
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
package com.newgen.npci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;


public class WSCall 
{
    
    public String invoke(String SOAPUrl, String soapRequest, String SOAPActionURL) throws Exception 
    {
    	ignoreCertificates();
        return sendSOAPRequest(SOAPUrl, soapRequest, SOAPActionURL);
    }

    private String sendSOAPRequest(String SOAPUrl, String soapRequest, String SOAPActionURL) throws Exception 
    {
        StringBuffer response = new StringBuffer(5000);
        try 
        {
        	String SOAPAction = SOAPActionURL;
            URL url = new URL(SOAPUrl);
            URLConnection urlConnection = url.openConnection();
            byte[] b = soapRequest.getBytes("UTF-8");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(b.length));
            urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            urlConnection.setRequestProperty("SOAPAction", SOAPAction);
            if (urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlConnection).setRequestMethod("POST");
            } else {
                throw new RuntimeException("this connection is NOT an HttpUrlConnection connection");
            }
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(25000);
            OutputStream out = urlConnection.getOutputStream();
            out.write(b);
            out.flush();

            System.out.println("input streeam --- "+urlConnection.getOutputStream());
            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) 
            {
                response.append(inputLine);
            }
            in.close();
        } 
        catch (ConnectException e) 
        {
        	System.out.println("Connect Exception: "+e);
        	e.printStackTrace();
        	response.append("ConnectError");
        }
        catch (SocketTimeoutException e) 
        {
        	System.out.println("Connect Exception: "+e);
        	e.printStackTrace();
        	response.append("SocketError");
        }
        catch(Exception e){
        	System.out.println("Exception: "+e);
        	response.append("ConnectError");
        }
        return response.toString();
    }
    
    
    public void ignoreCertificates() throws Exception 
    {
        TrustManager tm = new TrustManager();
        TrustManager[] trustAllCerts = {tm};
        HostnameVerifier AllowAllHostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(AllowAllHostnameVerifier);
    }
    
    static class TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
