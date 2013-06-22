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


import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import eu.ist.astra.persistency.IPersistencyManager;
import eu.ist.astra.persistency.PersistencyException;
import eu.ist.astra.tmn.*;



/**
 * Implementation class of the TagManagerNode service interface
 * 
 * Handles private tags
 *  
 * A MySql database is used to store tags.
 * 
 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
 * 
 * @version 0.3
 * 
 * Adapted to use it in Astra
 */
public class TagManagerNode implements ITagManagerNode {

	private IPersistencyManager pm;
	private BundleContext bc;
	
	public TagManagerNode(BundleContext bc) throws PersistencyException 
	{
		this.bc = bc;
		getPMref();

		setupDB();
			
	
	}	

	/**
	 * Takes a reference to the PM in the attribute
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void getPMref() {

		ServiceReference sr = bc.getServiceReference(IPersistencyManager.class.getName());
		if (sr == null) {
			pm = null;
			return;
		}
		pm = (IPersistencyManager)bc.getService(sr);
	}
	
	
	/**
	 * Creates a new private tag 
	 *
	 * Adapted for Astra.
	 *
	 * @param name Tag name
	 * @param appId Application identifier
	 * @param userId User identifier
	 * 
	 * @return True if the operation was successful, false otherwise.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 */
	public boolean addTag(String name, String appId, String userId)
	{		

		//Tag validation and parameters checking
		if(!validateTag(name))
			return false;
		if(appId == null || appId.equals(""))
			return false;
		if(userId == null || userId.equals(""))
			return false;
		
		try 
		{
			
			String query = 	"SELECT ID FROM TagNode WHERE NAME='" + name + 
							"' AND APPLICATION_ID='" + appId +"' AND UID='" + userId + "'";

			//drozas&alfredo: If it has already been stored, return false
			String[][] res = pm.executeQuery(query, false);
			
			if(res.length>0) 
			{
				return false;
			}
			
			query = "INSERT INTO TagNode (NAME,APPLICATION_ID,UID) VALUES " +
					"('" + name  + "' , '" + appId + "', '" + userId +"')";
			
			pm.executeQuery(query, true);
						
			
		}catch (PersistencyException e)
		{
			System.out.println( "It was not possible to add the tag " + name +  " for the application " + appId + 
								" by the user " + userId +  " in the database");

			return false;
		}
		
		return true;
	}

	
	/**
	 * Finds and returns all the private tags for the given application for this user.
	 * 
	 * 
	 * @param appId Application identifier
	 * @param userId	User Identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * 
	 * @return List of private tags resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 * 
	 */
	public String[] getTags(String appId, String userId, int limit)
	{		
		
		try 
		{
			
			String query;
			
			query = "SELECT NAME FROM TagNode " +
					"WHERE APPLICATION_ID= '" + appId  +"' AND UID='" + userId + "'";
			
			
			if(limit > 0)
				query += " LIMIT 0," + limit;
	
			
			String res[][] = pm.executeQuery(query, false);

			String[] tags = new String[res.length];
			
			for (int i = 0; i< res.length; i++)
				tags[i] = res[i][0];
			 
			return tags;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while retrieving the public tags for the application " + appId +
					"for the user " + userId +" : ");
			e.printStackTrace();
			return null;

		}
		
	}	
	
	
	/**
	 * 
	 * Deletes a private tag
	 * 
	 * @param name	Tag name
	 * @param appId	Application identifier in standard ASTRA format
	 * @param userId	User identifier
	 * 
	 * @return	True if the process was successful, false otherwise.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deleteTag(String name, String appId, String userId)
	{		
		
		try 
		{
			
			String query = "DELETE FROM TagNode " +
					"WHERE APPLICATION_ID= '" + appId +
					"' AND UID= '" + userId  +"' AND name= '" + name + "'";
	
			pm.executeQuery(query, true);
			
			return true;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while deleting the public tag " + name + " for the application " + appId + " : ");
			e.printStackTrace();
			return false;

		}
		
	}	
	
	

	/**
	 * 
	 * Checks if a tag is valid
	 * The criteria are
	 * - not null
	 * - does not contain spaces
	 * - contains no more than 30 characters
	 * 
	 * @param name Tag name
	 * @return True if tag is valid, false otherwise
	 * 
	 * @author Christian Laverton
	 */
	private boolean validateTag(String name) {
		if(name == null || name.equals(""))
			return false;
		
		if(name.indexOf(' ') != -1)
			return false;
		
		if(name.length() > 30)
			return false;
		
		return true;
	}
	
	
	/**
	 * 
	 * Performs the setup of the database model.
	 * 
	 * IMPORTANT: 	There is not a table for representing Application in the DB model of the client yet. 
	 * 				Therefore, we have not established APPLICATION_ID as a foreign key of that table (in the back-end
	 * 				is SharedApplication).
	 * 				If a table for representing Applications in the client DB is created, here APPLICATION_ID SHOULD REFERENCE
	 * 				THIS TABLE AS A FOREIGN KEY IN ORDER TO KEEP INTEGRITY.
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void setupDB() throws PersistencyException
	{
		String query =	"CREATE TABLE IF NOT EXISTS TagNode (ID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, " +
						"UID VARCHAR(255) NOT NULL, " +
						"APPLICATION_ID VARCHAR(255) NOT NULL, " +
						"NAME VARCHAR(255) NOT NULL, " +
						"PRIMARY KEY(ID))";
						
						
/*						"FOREIGN KEY(APPLICATION_ID, UID) " +
						"REFERENCES SharedApplication(APPLICATION_ID, UID) " +
						"ON DELETE CASCADE " +
						"ON UPDATE CASCADE)";*/
		
		try
		{
			pm.executeQuery(query, true);

		}catch(PersistencyException e)
		{
			System.out.println("There was an exception with the Persistency Manager while the tables needed for the repository were created");
			System.out.println(e.getMessage());
			throw e;
		}
				
	
	}
	

}
