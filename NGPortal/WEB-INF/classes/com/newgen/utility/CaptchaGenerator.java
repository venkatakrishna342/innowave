/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module 
* File Name           : CaptchaGenerator.java
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
package com.newgen.utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

/*import com.sun.jersey.core.util.Base64;*/

	

public class CaptchaGenerator {
	public static final String FILE_TYPE = "jpeg";
	
	
	
	public byte[] createCaptcha()
	{
		String captchaStr="";
		captchaStr=Utility.generateCaptchaTextMethod2(6);
		 byte[] imageInByte=null;
		
	File img=null;
	    try {
	
	    	int width=100;     	int height=40;
	    	img=new File("d:\\captcha.jpeg");
	    	Color bg = new Color(0,255,255);
	    	Color fg = new Color(0,100,0);
	    	
	    	Font font = new Font("Arial", Font.BOLD, 20);
	    	BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
	    	Graphics g = cpimg.createGraphics();
	
	    	g.setFont(font);
	        g.setColor(bg);
	        g.fillRect(0, 0, width, height);
	        g.setColor(fg);
	    	g.drawString(captchaStr,10,25);   
	       ImageIO.write(cpimg, FILE_TYPE, img);
	   
	       System.out.println("image writing successful");
	       
	       
	       ByteArrayOutputStream baos=new ByteArrayOutputStream();
	       cpimg=ImageIO.read(img);
	       ImageIO.write(cpimg, "jpg", baos);
	       baos.flush();
	    
	        imageInByte = baos.toByteArray();
	        baos.close();
//	        imageInByte =Base64.encode(imageInByte);
	       System.out.println("reading compleated");
	       System.out.println(imageInByte);

	     
	    } catch (Exception e) {
	            e.printStackTrace();
	            
	    }
	    return imageInByte;
	}
	
	
	public String getPath() throws UnsupportedEncodingException {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("/WEB-INF/classes/");
		System.out.println(fullPath);
		System.out.println(pathArr[0]);
		fullPath = pathArr[0];
		String reponsePath = "";
		// to read a file from webcontent
		reponsePath = new File(fullPath).getPath() + File.separatorChar + "captcha\\captcha.jpeg";
		System.out.println(reponsePath+" ................... ................. ..........");
		return reponsePath;
		}
	
	
	
}

	
	


