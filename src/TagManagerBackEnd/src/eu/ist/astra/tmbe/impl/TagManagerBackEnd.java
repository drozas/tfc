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

package eu.ist.astra.tmbe.impl;




import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import eu.ist.astra.persistency.IPersistencyManager;
import eu.ist.astra.persistency.PersistencyException;
import eu.ist.astra.tmbe.*;

import eu.ist.astra.cm.*;
import eu.ist.astra.em.IEventsServer;
import eu.ist.astra.em.events.NewTagAstraEvent;
import eu.ist.astra.em.events.RemoveTagAstraEvent;

/**
 * Implementation class of the Tag Manager Server service interface
 * 
 * Handles public and community tags.
 *  
 * A MySql database is used to store tags.
 * 
 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
 * 
 * @version 0.3
 * 
 * Adapted to use it in Astra
 */
public class TagManagerBackEnd implements ITagManagerBackEnd {

	private IPersistencyManager pm;
	private BundleContext bc;
	private ICommunityManager cm;
	private IEventsServer es;

	public TagManagerBackEnd(BundleContext bc) throws PersistencyException 
	{
		this.bc = bc;
		getPMref();
		getESref();
		getCMref();
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
	 * Takes a reference to the Events Servers in the attribute
	 * 
	 * @author Alfredo Perez
	 */
	private void getESref() {

		ServiceReference sr = bc.getServiceReference(IEventsServer.class.getName());
		if (sr == null) {
			es = null;
			return;
		}
		es = (IEventsServer)bc.getService(sr);
	}
	
	/**
	 * Takes a reference to the CM in the attribute
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void getCMref() {

		ServiceReference sr = bc.getServiceReference(ICommunityManager.class.getName());
		if (sr == null) {
			cm = null;
			return;
		}
		cm = (ICommunityManager)bc.getService(sr);
	}
	
	
	/**
	 * Creates a new public tag. 
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
			
			String query = 	"SELECT ID FROM Tag WHERE NAME='" + name + 
							"' AND APPLICATION_ID='" + appId +"' AND UID='" + userId + "'";

			//drozas&alfredo: If it has already been stored, return false
			String[][] res = pm.executeQuery(query, false);
			
			if(res.length>0) 
			{
				return false;
			}
			
			query = "INSERT INTO Tag (NAME,APPLICATION_ID,UID) VALUES " +
					"('" + name  + "' , '" + appId + "', '" + userId +"')";
			
			pm.executeQuery(query, true);
						
			
		}catch (PersistencyException e)
		{
			System.out.println( "It was not possible to add the tag " + name +  " for the application " + appId + 
								" by the user " + userId +  " in the database");

			return false;
		}
		
		/*
		 * We notify of a new tag. 
		 */
		this.notifyNewTag(userId, appId, name);
		return true;
	}

	/**
	 * Creates a new community tag.
	 *
	 * Adapted for Astra.
	 *
	 * @param name Tag name
	 * @param appId Application identifier
	 * @param userId User identifier
	 * @param communityId	Community identifier
	 * 
	 * @return True if the operation was successful, false otherwise.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 */
	public boolean addTagByCommunity(String name, String appId, String userId, String communityId)
	{		

		//Tag validation and parameters checking
		if(!validateTag(name))
			return false;
		if(appId == null || appId.equals(""))
			return false;
		if(userId == null || userId.equals(""))
			return false;
		if(communityId == null || communityId.equals(""))
			return false;
		
		try 
		{
			
			String query = 	"SELECT ID FROM Tag WHERE NAME='" + name + 
							"' AND APPLICATION_ID='" + appId +"' AND UID='" + userId + "'" +
							" AND CID='" + communityId + "'";

			//drozas&alfredo: If it has already been stored, return false
			String[][] res = pm.executeQuery(query, false);
			
			if(res.length>0) 
			{
				return false;
			}
			
			query = "INSERT INTO Tag (NAME,APPLICATION_ID,UID,CID) VALUES " +
					"('" + name  + "' , '" + appId + "', '" + userId + "', '" + communityId + "')";
			
			pm.executeQuery(query, true);
						
			
		}catch (PersistencyException e)
		{
			System.out.println( "It was not possible to add the tag " + name +  " for the application " + appId + 
					" by the user " + userId + " in the community " + communityId + " in the database");

			return false;
		}
		
		return true;
	}
	
	/**
	 * Finds and returns all tags for the given application. 
	 * The tags returned are unique (if many users chose the same name, it will only appears once)
	 * and it is sorted by popularity (number of occurrences).
	 * 
	 * 
	 * @param appId Application identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * 
	 * @return List of public tags resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 * 
	 */
	public String[] getTags(String appId, int limit)
	{		
		
		try 
		{
			
			String query;
			
			query = "SELECT NAME, COUNT(*) as CNT FROM Tag " +
					"WHERE APPLICATION_ID= '" + appId  +"' AND CID='public' " +
					"GROUP BY NAME ORDER BY CNT DESC" ;
			
			
			if(limit > 0)
				query += " LIMIT 0," + limit;
	
			
			String res[][] = pm.executeQuery(query, false);

			String[] tags = new String[res.length];
			
			for (int i = 0; i< res.length; i++)
				tags[i] = res[i][0];
			 
			return tags;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while retrieving the public tags for the application " + appId + " : ");
			e.printStackTrace();
			return null;

		}
		
	}	
	
	/**
	 * Finds and returns all tags for the given application and community. 
	 * The tags returned are unique (if many users chose the same name, it will only appears once)
	 * and it is sorted by popularity (number of occurrences).
	 * 
	 * 
	 * @param appId Application identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * @param communityId	Community Identifier
	 * 
	 * @return List of tags which belong to that community resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 * 
	 */
	public String[] getTagsByCommunity(String appId, int limit, String communityId)
	{		
		
		try 
		{
			
			String query;
			
			query = "SELECT NAME, COUNT(*) as CNT FROM Tag " +
					"WHERE APPLICATION_ID= '" + appId  +"' AND CID='" + communityId +"' " +
					"GROUP BY NAME ORDER BY CNT DESC" ;
			
			
			if(limit > 0)
				query += " LIMIT 0," + limit;
	
			
			String res[][] = pm.executeQuery(query, false);

			String[] tags = new String[res.length];
			
			for (int i = 0; i< res.length; i++)
				tags[i] = res[i][0];
			 
			return tags;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while retrieving the tags for the community " + communityId +
								" for the application " + appId + " : ");
			e.printStackTrace();
			return null;

		}
		
	}
	
	/**
	 * Finds and returns all tags for the given application which are public or are from a community
	 * where the user is part of. 
	 * The tags returned are unique (if many users chose the same name, it will only appears once)
	 * and it is sorted by popularity (number of occurrences).
	 * 
	 * 
	 * @param appId Application identifier
	 * @param userId	User identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * 
	 * @return List of tags which belong to that community resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getTagsByApplication(String appId, String userId, int limit)
	{		
		
		try 
		{
			
			String query;
			String[] communities = this.cm.isMemberOf(userId);
			
			query = "SELECT NAME, COUNT(*) as CNT FROM Tag " +
					"WHERE APPLICATION_ID= '" + appId  +"' AND (CID='public'";
			
			for (int i=0; i<communities.length; i++)
				query+= " OR CID='" + communities[i] + "'";
			
			query+=") GROUP BY NAME ORDER BY CNT DESC" ;
			//(CID='" + communityId +"' " +
			
			if(limit > 0)
				query += " LIMIT 0," + limit;
	
			
			String res[][] = pm.executeQuery(query, false);

			String[] tags = new String[res.length];
			
			for (int i = 0; i< res.length; i++)
				tags[i] = res[i][0];
			 
			return tags;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while retrieving the tags by application");
			e.printStackTrace();
			return null;
		}
	
	}
	
	
	/**
	 * 
	 * Deletes a public tag
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
			
			String query = "DELETE FROM Tag " +
					"WHERE APPLICATION_ID= '" + appId  +"' AND CID='public' " +
					"AND UID= '" + userId  +"' AND name= '" + name + "'";
	
			pm.executeQuery(query, true);
			
			//Notify tag removal
			this.notifyDeleteTag(userId, appId, name);
			
			return true;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while deleting the public tag " + name + " for the application " + appId + " : ");
			e.printStackTrace();
			return false;

		}
		
	}
	
	/**
	 * 
	 * Deletes a tag for a given community
	 * 
	 * @param name	Tag name
	 * @param appId	Application identifier in standard ASTRA format
	 * @param userId	User identifier
	 * @param communityId	Community Identifier
	 * 
	 * @return	True if the process was successful, false otherwise.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deleteTag(String name, String appId, String userId, String communityId)
	{		
		
		try 
		{
			
			String query = "DELETE FROM Tag " +
					"WHERE APPLICATION_ID= '" + appId  +"' AND CID='" + communityId + "' " +
					"AND UID= '" + userId  +"' AND name= '" + name + "'";
	
			pm.executeQuery(query, true);
			
			return true;
			
		}catch(PersistencyException e){		
			
			System.out.println( "There was a problem while deleting the community tag " + name 
								+ " for the application " + appId + " in the community " + communityId);
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
	 * Performs the setup of the database model 
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void setupDB() throws PersistencyException
	{
		String query =	"CREATE TABLE IF NOT EXISTS Tag (ID INTEGER UNSIGNED NOT NULL AUTO_INCREMENT, " +
						"UID VARCHAR(255) NOT NULL, " +
						"APPLICATION_ID VARCHAR(255) NOT NULL, " +
						"CID VARCHAR(255) NOT NULL DEFAULT 'public'," +
						"NAME VARCHAR(255) NOT NULL, " +
						"PRIMARY KEY(ID) ) " ;
						//"FOREIGN KEY(APPLICATION_ID, UID) " +
						//"REFERENCES SharedApplication(APPLICATION_ID, UID) " +
						//"ON DELETE CASCADE " +
						//"ON UPDATE CASCADE," +
						//"FOREIGN KEY(CID) " +
						//"REFERENCES Community(CID) " +
						//"ON DELETE CASCADE " +
						//"ON UPDATE CASCADE)"
						
		
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

	
	@Deprecated
	/**
	 * Add a listener that will be notified whenever a tag is created
	 * or modified.
	 * @param String url Url of the service that implements the interface IEventsNotifier in the form http://server/
	 * @param String service Service that implements the interface IEventsNotifier i.e. EventsManager
	 */
	public void addTagListener(String url, String service) {
		
	/*	IEventsManager em = (IEventsManager) rfm.getRemoteService(service, url);
		this.tagListenerList.add(em);
		*/
	}
	
	/**
	 * Notifies a new public tag was created to the Events Manager
	 * 
	 * @param uid	User who is adding the tag.
	 * @param appId	Application whose tag is associated to.
	 * @param tag	Tag name
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void notifyNewTag(String uid, String appId, String tag) {	
		es.notify(this, new NewTagAstraEvent(uid, appId, tag), "PUBLIC");
	}
	
	/**
	 * Notifies a new public tag was deleted to the Events Manager
	 * 
	 * @param uid	User who is adding the tag.
	 * @param appId	Application whose tag is associated to.
	 * @param tag	Tag name
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void notifyDeleteTag(String uid, String appId, String tag) {		
		es.notify(this, new RemoveTagAstraEvent(uid, appId, tag), "PUBLIC");
	}
	
}
