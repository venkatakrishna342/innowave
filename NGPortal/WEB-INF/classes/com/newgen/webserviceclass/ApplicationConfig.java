/********************************************************************
*  NEWGEN SOFTWARE TECHNOLOGIES LIMITED
* Group               : CIG
* Product / Project   : DBT
* Module              : Webservice Layer Module
* File Name           : ApplicationConfig.java
* Author              : Swarnadip Ghosh
* Date written
* (DD/MM/YYYY)        : May 7, 2017
* Description         : Messages
*
* CHANGE HISTORY
  ************************************************************************************************************
* Date                           Change By            Change Description (Bug No. (If Any))
* 
 ************************************************************************************************************/


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.newgen.webserviceclass;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * @author Swarnadip.Ghosh
 */
@javax.ws.rs.ApplicationPath("portal")
public class ApplicationConfig extends Application 
{	
	@Override
	public Set<Class<?>> getClasses() 
	{
		Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
		addRestResourceClasses(resources);
		return resources;
	}

	/**
	 * Do not modify addRestResourceClasses() method.
	 * It is automatically populated with
	 * all resources defined in the project.
	 * If required, comment out calling this method in getClasses().
	 */
	private void addRestResourceClasses(Set<Class<?>> resources) 
	{
		resources.add(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
        resources.add(com.newgen.webserviceclass.JacksonJsonProvider.class);
		resources.add(com.newgen.webserviceclass.Portal_WS.class);
	}
}
