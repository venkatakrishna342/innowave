/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : GetIP.java
* Author              : Ankit Katoch
* Date written
* (DD/MM/YYYY)        : Apr 27, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
*
 ************************************************************************************************************/
package com.newgen.dbt.integration.adhar;



import java.io.BufferedReader;
import java.io.InputStreamReader;

/*import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class GetPublicIPAddress {

	private String getPublicIpAddress() {
	    String res = null;
	    try {
	        String localhost = InetAddress.getLocalHost().getHostAddress();
	        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
	        while (e.hasMoreElements()) {
	            NetworkInterface ni = (NetworkInterface) e.nextElement();
	            if(ni.isLoopback())
	                continue;
	            if(ni.isPointToPoint())
	                continue;
	            Enumeration<InetAddress> addresses = ni.getInetAddresses();
	            while(addresses.hasMoreElements()) {
	                InetAddress address = (InetAddress) addresses.nextElement();
	                if(address instanceof Inet4Address) {
	                    String ip = address.getHostAddress();
	                    if(!ip.equals(localhost))
	                        System.out.println((res = ip));
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return res;
	}
	
	
	public static void main(String[] args) throws IOException {

		GetPublicIPAddress g = new GetPublicIPAddress();
		String ip = g.getPublicIpAddress();
		System.out.println("ip "+ip);
		
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);

		
		
		
		
	}

}
*/
import java.net.URL;
public class GetIP {
    private static String PublicIpAddress = null;



	public String getPublicIP() {
    
    	try{
   		 URL whatismyip = new URL("http://checkip.amazonaws.com");
       	 BufferedReader in = new BufferedReader(new InputStreamReader(
       	                 whatismyip.openStream()));

       	 String PublicIpAddress = in.readLine(); //you get the IP as a String
       	 System.out.println(PublicIpAddress);
       	 this.PublicIpAddress = PublicIpAddress;
   	 }catch(Exception e){
   	System.out.println(e);	 
   	 }
		return this.PublicIpAddress;
    	
    }
        
        
        
         public static void main(String args[]) {
    	/*try{
    	
    	
    	java.net.URL URL = new java.net.URL("http://www.whatismyip.org/");
 
		java.net.HttpURLConnection Conn = (HttpURLConnection)URL.openConnection();
		 
		java.io.InputStream InStream = Conn.getInputStream();
		 
		java.io.InputStreamReader Isr = new java.io.InputStreamReader(InStream);
		 
		java.io.BufferedReader Br = new java.io.BufferedReader(Isr);
		 
		System.out.print("Your IP address is " + Br.readLine());
		JOptionPane.showMessageDialog(null, "IP is: " + Br.readLine() );
		
    	}catch(Exception e){e.printStackTrace();}
		
    	}*/
        	 
        	 GetIP g = new GetIP();	 
        	 String publicIP =  g.getPublicIP();
        	
         }
}


















