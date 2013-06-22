package eu.ist.astra.rm.impl;

import eu.ist.astra.cm.ICommunityManager;
import eu.ist.astra.em.events.AstraEvent;
import eu.ist.astra.em.IEventsListener;
import eu.ist.astra.em.IEventsServer;
import eu.ist.astra.em.events.NewTagAstraEvent;
import eu.ist.astra.em.events.RemoveTagAstraEvent;
import eu.ist.astra.em.events.TagAstraEvent;
import eu.ist.astra.persistency.*;
import eu.ist.astra.rm.IRepositoryManager;
import eu.ist.astra.rm.searchEngine.SearchEngine;
import eu.ist.astra.rm.searchEngine.SearchEngineException;
import eu.ist.astra.rm.xml.XMLFunctionalities;
import eu.ist.astra.tmbe.ITagManagerBackEnd;


import java.sql.Timestamp;
import java.util.*;


import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;



/**
 * Implements the ApplicationRepositoryManager functionality:
 * 
 * - Offer services to share applications
 * - Offer services to get shared applications
 * - Store them in his own DB model (using PM) 
 * - Offer services to search applications by criteria (new)
 * - Offer services to search applications by similarity (new)
 * 
 * - This bundle keeps a copy in memory which is synchronized with the DB
 *
 *	@author David Rozas & Alfredo Perez
 */
public class RepositoryManagerImpl implements IRepositoryManager , IEventsListener{

	IPersistencyManager pm;
	ICommunityManager cm;
	ITagManagerBackEnd tmbe;
	private IEventsServer es;
	
	Hashtable<String,SharedApplication> apps;
	BundleContext bc;
	
	SearchEngine searchEngine; 

	public RepositoryManagerImpl(BundleContext bc) throws PersistencyException {
		this.bc = bc;
		apps = new Hashtable<String, SharedApplication>();
		
		//drozas&alfredo: Get references to other bundles, setup the db if necessary and load all the applications & rules
		getPMref();
		getESref();
		getCMref();
		getTMBEref();
		
		setupDB();
		
		try {
			this.searchEngine = new SearchEngine();
		} catch (SearchEngineException e) {
			e.printStackTrace();
		} 

		loadApplications();
		
		
		this.es.addListener(this, TagAstraEvent.class, "PUBLIC");
		
		
	
	}
	
	/**
	 * Takes a reference to the EventsManager in the attribute.
	 * 
	 * @author David Rozas & Alfredo Perez
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
	 * Takes a reference to the PM in the attribute,
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
	 * Takes a reference to the CM
	 * 
	 * Needed for retrieving list of communities joined for this user
	 * 
	 * @author David Rozas 
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
	 * Takes a reference to the TagManagerBackEnd
	 * 
	 * Needed for indexing a list of tags associated to an application
	 * 
	 * @author David Rozas 
	 */
	private void getTMBEref() {

		ServiceReference sr = bc.getServiceReference(ITagManagerBackEnd.class.getName());
		if (sr == null) {
			tmbe = null;
			return;
		}
		tmbe = (ITagManagerBackEnd)bc.getService(sr);
	}
	
	/**
	 * Returns the name of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application name
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationName(String appID) 
	{
		SharedApplication  app =(SharedApplication)apps.get(appID);
		if ( app== null) return null; // Application doesn't exist!
		return app.getName();
	}
	
	/**
	 * Returns the owner of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application owner
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationOwner(String appID) 
	{
		SharedApplication  app =(SharedApplication)apps.get(appID);
		if ( app== null) return null; // Application doesn't exist!
		return app.getUid();
	}

	/**
	 * Returns the description of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application description
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationDescription(String appID) 
	{
		SharedApplication app = (SharedApplication)apps.get(appID);
		if ( app== null) return null; // Application doesn't exist!
		return app.getDescription();
	}
	
	/**
	 * Returns the type of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application description
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationType(String appID) 
	{
		SharedApplication app = (SharedApplication)apps.get(appID);
		if ( app== null) return null; // Application doesn't exist!
		return app.getType();
	}
	
	/**
	 * Returns the sharing date of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application sharing date
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationDate(String appID) 
	{
		SharedApplication  app =(SharedApplication)apps.get(appID);
		if ( app== null) return null; // Application doesn't exist!
		return app.getSharingDate();
	}
	
	/**
	 * 
	 * Function that checks if an application has been already shared
	 * 
	 * @param appId	Application id in the standard format
	 * 
	 * @return true if already exists, false otherwise
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	public boolean isAlreadyShared(String appId)
	{
		return apps.containsKey(appId);
	}
	
	/**
	 * 
	 * Function that checks if an application has been shared in the
	 * given community
	 * 
	 * @param appId	Application id in the standard format
	 * @param communityId	Community identifier in the standard ASTRA format
	 * 
	 * @return true if it has been shared in that community, false otherwise
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean isSharedInCommunity(String appId, String communityId)
	{
		return this.apps.get(appId).getCommunitiesList().contains(communityId);
	}
	
	
	
	/**
	 * Create a new shared application in the back-end. 
	 * @param appId Application identifier in the standard format
	 * @param userId Identifier of the user who uploads the application to the repository in the standard format
	 * @param appType Type of application
	 * @param appDescription Application Description
	 * 
	 * The date is created directly in the DB, and refers to the date when the application was shared
	 * 
	 * 
	 * @return True if everything is ok, false if there was a failure (db, ...)
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	public boolean createSharedApplication(String appId, String userId, String appType, String appDescription)
	{
		//Since there are not important operations with dates, we have decided to store it as an String in database and object for simplicity
		Timestamp stamp = new Timestamp(new Date().getTime());
		String date = stamp.toString();
		
		String query = "INSERT INTO SharedApplication VALUES ('"+appId+"','"+userId+"','"+ appType + "', '" + date + "' , '" +appDescription+"')";
		
		try{
			
			//We store before in the database, and later in local memory, in order to avoid desynchronization if there were problems with the database
			pm.executeQuery(query,true);
			
			//add it into local memory
			SharedApplication app = new SharedApplication(appId,appType,userId,appDescription,date);
			apps.put(appId,app);
			
			//Adding the application in the search engine index.
			//For the moment only the public tags are used for the indexation (for privacy reasons)
			this.searchEngine.addSharedApplicationToIndex(app, this.tmbe.getTags(appId, -1));
			
			return true;
		}
		catch (PersistencyException e){
			System.out.println( "It was not possible to store the application " + appId + " in the database");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 
	 * Delete a SharedApplication in the DB and in local memory
	 * 
	 * @param appId	Application Identifier in the standard format
	 * 
	 * @return	True if the application was deleted properly, false otherwise (exception in the DB, delete an application
	 * 			which does not exist, etc.)
	 * 
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	public boolean deleteSharedApplication(String appId)
	{
		if(this.apps.containsKey(appId))
		{
			//Delete first in the DB and after in local memory to avoid inconsistency if DB fails
			String query = "DELETE FROM SharedApplication WHERE APPLICATION_ID = '" + appId + "'";
			
			try{
				
				//We store before in the database, and later in local memory, in order to avoid desynchronization if there were problems with the database
				pm.executeQuery(query,true);
				
				//delete from local memory
				this.apps.remove(appId);
				
				//delete from the search engine index 
				this.searchEngine.deleteSharedApplicationFromIndex(appId);
				
				return true;
			}
			catch (PersistencyException e){
				System.out.println( "It was not possible to delete the application " + appId + " in the database");
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
		
		
	}

	/**
	 * 
	 * Store relationship between a shared application and a community in 
	 * the DB and in local memory
	 * 
	 * 
	 * @param userId	User identifier in the standard format
	 * @param appId		Application identifier in the standard format	
	 * @param communityId	Community identifier in the standard format
	 * 
	 * @return	True if the operation was successful, false otherwise 
	 * 
	 * @author David Rozas
	 */
	public boolean shareInCommunity(String userId, String appId, String communityId)
	{
		String query = "INSERT INTO SharedIn VALUES ('"+userId+"','"+appId+"','" +communityId+"')";
		
		try{
			
			//We store before in the database, and later in local memory, in order to avoid desynchronization if there were problems with the database
			pm.executeQuery(query,true);
			
			//add it into local memory
			this.apps.get(appId).addCommunity(communityId);
			
			return true;
		}
		catch (PersistencyException e){
			System.out.println( "It was not possible to store the application " + appId + " in the database");
			return false;
		}
		
	}
	
	/**
	 * Create a new shared rule in the back-end. 
	 * 
	 * @param userId Identifier of the user who uploads the application to the repository in the standard format
	 * @param appId Application identifier in the standard format
	 * @param ruleId Rule identifier in the standard format
	 * @param xml_data Description of the rule in XML format
	 * 
	 * 
	 * @return True if everything is ok, false if there was a failure (db, ...)
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	public boolean createSharedRule(String userId, String appId, String ruleId, String xml_data)
	{

		
		String query = "INSERT INTO SharedRule VALUES ('"+ruleId+"','"+userId+"','"+ appId + "', '" + xml_data + "')";
		
		try{
			
			//We store before in the database, and later in local memory, in order to avoid desynchronization if there were problems with the database
			pm.executeQuery(query,true);
			
			//We add it into local memory recovering the application which this rule belongs to
			this.apps.get(appId).addSharedRule(new SharedRule(ruleId,xml_data));
			
			return true;
		}
		catch (PersistencyException e){
			System.out.println( "It was not possible to store the rule " + ruleId + " in the database");
			return false;
		}

	}


	/**
	 * Returns the list of applications in the repository, filtering the ones this user
	 * is not part of any of the communities. 
	 * 
	 * @param userID User identifier in the standard format
	 * 
	 * @return List of applications in the repository
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	public String[] listSharedApplications(String userID) {
		
		Vector<String> myApps = new Vector<String>();
		String[] communities = cm.isMemberOf(userID);
		
		for (Enumeration<SharedApplication> e = apps.elements();e.hasMoreElements();) 
		{
			SharedApplication aa = (SharedApplication)e.nextElement();
			//alfredo: if he is the owner then we add it also because we may want to tag it!
			
			if( aa.canBeRetrieved(communities))
				myApps.add(aa.getAppID());	
		}
		String[] ret = new String[myApps.size()];
		
		for (int i=0;i<ret.length;i++)
			ret[i] = (String)myApps.elementAt(i);
		
		return ret;
	}
	
	/**
	 * Returns the list of rules in the repository which belongs to appID
	 * 
	 * @param appID Application identifier in the standard format
	 * 
	 * @return List of shared rules for this application
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	public String[] listSharedRules(String appID) 
	{
		
		Vector<String> myRules = new Vector<String>();
		
		Enumeration<String> e =apps.get(appID).getSharedRulesList();
		
		
		while(e.hasMoreElements())
		{
			String ruleId = e.nextElement();
			myRules.addElement(ruleId);
		}
		
		
		String[] ret = new String[myRules.size()];
		
		for (int i=0;i<ret.length;i++)
			ret[i] = (String)myRules.elementAt(i);
		
		return ret;
		
	}
	
	/**
	 * 
	 * Returns the XML file which describes the rule.
	 * 
	 * @param appID		Application identifier
	 * @param ruleID	Rule identifier
	 * 
	 * @return	XML file describing the rule
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	public String getXmlData(String appID, String ruleID)
	{
		return apps.get(appID).getSharedRule(ruleID).getXmlData();
	}
	
	
	/**
	 * 
	 * Returns the XML file which describes the rule. The difference with getXMLData
	 * is that this method provides already the necessary changes in the ownership
	 * internally (which is maintained to provide compatibility)
	 * 
	 * @param appID		Application identifier
	 * @param ruleID	Rule identifier
	 * @param newUserID	New user identifier.
	 * 
	 * @return	XML file describing the rule
	 * 
	 * @author David Rozas 
	 * 
	 */
	public String getXmlRule(String appID, String ruleID, String newUserID){
		String oldXmlData = this.getXmlData(appID, ruleID);
		return XMLFunctionalities.getXmlRule(oldXmlData, newUserID);
	}
	
	/**
	 * 
	 * Returns a description of the rule in a "readable" using the data from
	 * the original XML file
	 * 
	 * @param appID		Application identifier
	 * @param ruleID	Rule identifier
	 * 
	 * @return	XML file describing the rule
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getRuleDescription(String appID, String ruleID)
	{
		return apps.get(appID).getSharedRule(ruleID).getDescription();
	}
	
	
	
	
	/**
	 * 
	 * It performs a search according to one criterion 
	 * @param userId	Identifier of the user who is performing the search
	 * @param q	String with the query
	 * @param criterion	String with the criterion to filter by : (currently: description, tags and type)
	 * 
	 * @return Array with all the application identifiers (in standard ASTRA format) sorted by score, 
	 * 			null if there were not results
	 * 
	 * @author David Rozas
	 */
	public String[] search(String userId, String q, String criterion){
		//This method wraps the call in the search engine
		try
		{
			return this.searchEngine.search(q, criterion, userId, false);
		} catch (SearchEngineException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * It performs a search according to a set of criteria 
	 * @param userId	Identifier of the user who is performing the search
	 * @param q	String with the query
	 * @param criteria	Array with the criteria to filter by : (currently: description, tags and type)
	 * 
	 * @return Array with all the application identifiers (in standard ASTRA format) sorted by score, 
	 * 			null if there were not results
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] search(String userId, String q, String[] criteria){
		//This method wraps the call in the search engine
		try 
		{
			return this.searchEngine.search(q, criteria, userId, false);
		} catch (SearchEngineException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * It performs a search of applications with similar information to the one
	 * which is sent.
	 * For the moment it is based on:
	 * 	- Description of the compared application (sent by the node) + 
	 *  - List of community tags visible for that user (recovered by the repository in the Backend)
	 * 	- List of public tags of the compared application (recovered by the repository in the backend)
	 * 
	 * @param userId User identifier in the standard astra format
	 * @param appId Application identifier in the standard astra format
	 * @param appDescription Application description
	 * 
	 * 
	 * @return Array with all the application identifiers (in standard ASTRA format) sorted by score, 
	 * 			null if there were not results
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] searchBySimilarity(String userId, String appId, String appDescription){
		//This method wraps the call in the search engine
		try {
			return this.searchEngine.searchBySimilarity(userId, appDescription, this.tmbe.getTagsByApplication(appId, userId, -1),true);
		} catch (SearchEngineException e) {
			e.printStackTrace();
			return null;
		}
	}
	

		

	/**
	 * Load all the shared applications in local memory 
	 * 
	 * 	- Query to get all the applications
	 *		
	 *	- Create SharedApplication objects with this info for each of them
	 *		
	 *	- Store them in local memory (hash table apps)
	 * 
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	private void loadApplications() throws PersistencyException
	{
		System.out.println("RM.loadApplications() - Loading applications from DB");
		String query = "SELECT * FROM SharedApplication";
		try
		{
			
			String res[][] = pm.executeQuery(query, false);
			for(int appI=0; appI<res.length; appI++)
			{
				String appId = res[appI][0];
				String uid = res[appI][1];
				String type = res[appI][2];
				String date = res[appI][3];
				String desc = res[appI][4];
				
				SharedApplication app = new SharedApplication(appId,type,uid,desc,date);
				
				apps.put(appId,app);
				
				//Load the communities which this application belongs to
				loadSharedInCommunities(appId);
				
				//Load all the rules which belongs to this application
				loadRules(appId);
				
				//Adding the application in the search engine index
				this.searchEngine.addSharedApplicationToIndex(app, this.tmbe.getTags(appId, -1));
				
	
			}

		}catch(PersistencyException e)
		{
			System.out.println("There were some problems with the PM while loading applications");
			System.out.println(e.getMessage());
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	/**
	 * 
	 * Load the relationships between applications and communities from the DB, and store 
	 * them into local memory
	 * 
	 * @param appID	Application Identifier
	 * @throws PersistencyException
	 * 
	 * @author David Rozas
	 * 
	 */
	private void loadSharedInCommunities(String appID) throws PersistencyException
	{
		System.out.println("RM.loadRules() - Loading relationships between application and communities " + appID);
		
		//Query to recover rules which belong to this application 
		String query = "SELECT CID FROM SharedIn WHERE APPLICATION_ID = '" + appID + "'";
		
		//Add them to the local memory
		try
		{
			
			String res[][] = pm.executeQuery(query, false);
			for(int comI=0; comI<res.length; comI++)
				this.apps.get(appID).addCommunity(res[comI][0]);

		}catch(PersistencyException e)
		{
			System.out.println("There were some problems with the PM while loading the relationships between applications and communities");
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 
	 * Load the rules from DB and store them in local memory
	 * 
	 * @param appID	Application identifier in the standard format
	 * @throws PersistencyException
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	private void loadRules(String appID) throws PersistencyException
	{
		System.out.println("RM.loadRules() - Loading Rules from DB for application " + appID);
		
		//Query to recover rules which belong to this application 
		String query = "SELECT * FROM SharedRule WHERE APPLICATION_ID = '" + appID + "'";
		
		//Add them to the hash table
		try
		{
			
			String res[][] = pm.executeQuery(query, false);
			for(int ruleI=0; ruleI<res.length; ruleI++)
				this.apps.get(appID).addSharedRule(new SharedRule(res[ruleI][0], res[ruleI][3]));


		}catch(PersistencyException e)
		{
			System.out.println("There were some problems with the PM while loading applications");
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 
	 * Performs the setup of the database model for the repository 
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	public void setupDB() throws PersistencyException
	{
		String query_app = "CREATE TABLE IF NOT EXISTS SharedApplication(" +
						"APPLICATION_ID VARCHAR(255) NOT NULL, " +
						"UID VARCHAR(255) NOT NULL, " +
						"APPLICATION_TYPE VARCHAR(255) NOT NULL, " +
						"SHARING_DATE VARCHAR(255) NOT NULL, " +
						"DESCRIPTION TEXT NOT NULL, " +
						"PRIMARY KEY(APPLICATION_ID, UID), " +
						"FOREIGN KEY(UID) " +
						"REFERENCES User(UID) " +
						"ON DELETE CASCADE " +
						"ON UPDATE CASCADE)";
						
		String query_rule="CREATE TABLE IF NOT EXISTS SharedRule (" +
						"RULE_ID VARCHAR(255) NOT NULL," +
						"UID VARCHAR(255) NOT NULL," +
						"APPLICATION_ID VARCHAR(255) NOT NULL," +
						"XML_DATA TEXT NOT NULL," +
						"PRIMARY KEY(RULE_ID, UID, APPLICATION_ID)," +
						"FOREIGN KEY(APPLICATION_ID, UID)" +
						"REFERENCES SharedApplication(APPLICATION_ID, UID)" +
						" ON DELETE CASCADE" +
						" ON UPDATE CASCADE)";
		
		String query_communities="CREATE TABLE IF NOT EXISTS SharedIn (" +
					"UID VARCHAR(255) NOT NULL," +
					"APPLICATION_ID VARCHAR(255) NOT NULL," +
					"CID VARCHAR(255) NOT NULL," +
					"PRIMARY KEY(UID, APPLICATION_ID, CID)," +
					"FOREIGN KEY(APPLICATION_ID, UID)" +
					"REFERENCES SharedApplication(APPLICATION_ID, UID)" +
					" ON DELETE CASCADE" +
					" ON UPDATE CASCADE," +
					"FOREIGN KEY(CID)" +
					"REFERENCES Community(CID)" +
					" ON DELETE CASCADE" +
					" ON UPDATE CASCADE)";
		
		try
		{
			pm.executeQuery(query_app, true);
			pm.executeQuery(query_rule, true);
			pm.executeQuery(query_communities, true);
		}catch(PersistencyException e)
		{
			System.out.println("There was an exception with the Persistency Manager while the tables needed for the repository were created");
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Override
	public void update(Object sender, AstraEvent event, String channel) {
		if(event instanceof NewTagAstraEvent)
		{
			
			try
			{
				this.searchEngine.addTagToDocument(((NewTagAstraEvent)event).getAppId(), ((NewTagAstraEvent)event).getTag(), true);
			}catch (SearchEngineException e) {
				System.err.println("It was not possible to update the index of the search engine while adding a tag.");
				System.err.println(e.getMessage());
			}
		}else if(event instanceof RemoveTagAstraEvent){
			try
			{
				this.searchEngine.removeTagFromDocument(((RemoveTagAstraEvent)event).getAppId(), ((RemoveTagAstraEvent)event).getTag(), true);
			}catch (SearchEngineException e) {
				System.err.println("It was not possible to update the index of the search engine while removing a tag.");
				System.err.println(e.getMessage());
			}
		}
		
	}
	
}