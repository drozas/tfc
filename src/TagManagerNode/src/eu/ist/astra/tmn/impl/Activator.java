/*
 * Copyright 2007 UbiCollab.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.ist.astra.tmn.impl;

import java.util.Properties;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import eu.ist.astra.persistency.PersistencyException;
import eu.ist.astra.tmn.ITagManagerNode;

/**
 * The bundle class taking care of starting and stopping the bundle.
 * 
 * @author   Christian Laverton, modified by David Rozas & Alfredo Perez
 * @version 0.3
 */
public class Activator implements BundleActivator {
	private BundleContext context = null;
	private ITagManagerNode tagManager;

	/**
	 * Method called when the bundle is started.
	 * Registers the service.
	 */
	public void start(BundleContext bc) throws Exception {
	
		try {
			context = bc;
			Properties properties = new Properties();
			properties.put("Language", "English");
			properties.put("SOAP.service.name", GlobalConstants.SOAP_SERVICE_NAME);
			tagManager = new TagManagerNode(context);
			context.registerService(ITagManagerNode.class.getName(), tagManager, properties);
			
				
		
		}catch(PersistencyException e){
		
			System.out.println("It was not possible to start " + GlobalConstants.SOAP_SERVICE_NAME + ": ");
			e.printStackTrace();
			throw e;
		}
	  
	}

	/**
	 * Method called when bundle is stopped.
	 */
	public void stop(BundleContext context) throws Exception {
		try {		
			context = null;
		} catch (Exception e) {
			throw new BundleException("Failed to stop TagManagerNode bundle", e);
		}
  	}
}