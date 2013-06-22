package eu.ist.astra.rm;


public interface IRepositoryManager 
{
	public final String SOAP_SERVICE_NAME = "RepositoryManager";
	
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
	public boolean createSharedApplication(String appId, String userId, String appType, String appDescription);
	
	
	/**
	 * 
	 * Delete a SharedApplication in the DB and in local memory
	 * 
	 * @param appId	Application Identifier in the standard format
	 * 
	 * @return	True if the application was deleted properly, false otherwise (exception in the DB, delete an application
	 * 			which does not exist, etc.)
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	public boolean deleteSharedApplication(String appId);
	
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
	public boolean shareInCommunity(String userId, String appId, String communityId);
	
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
	public boolean isSharedInCommunity(String appId, String communityId);
	
	
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
	public boolean createSharedRule(String userId, String appId, String ruleId, String xml_data);
	
	
	

	/**
	 * Returns the list of applications in the repository
	 * 
	 * @param userID User identifier in the standard format
	 * 
	 * @return List of applications in the repository
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	public String[] listSharedApplications(String userID);
	
	
	/**
	 * Returns the list of rules in the repository which belongs to appID
	 * 
	 * @param appID Application identifier in the standard format
	 * 
	 * @return List of shared rules identifiers for this application
	 * 
	 * @author David Rozas & Alfredo Perez
	 * 
	 */
	public String[] listSharedRules(String appID);
	
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
	public String getXmlData(String appID, String ruleID);
	
	/**
	 * Returns the name of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application name
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationName(String appID);
	
	/**
	 * Returns the owner of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application owner
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationOwner(String appID);
	
	/**
	 * Returns the description of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application description
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationDescription(String appID);
	
	/**
	 * Returns the sharing date of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application sharing date
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationDate(String appID);
	
	/**
	 * Returns the type of the application, retrieving it from the object in memory
	 * 
	 * @param appID	Application Identifier
	 * 
	 * @return application description
	 * 
	 * @author David Rozas & Alfredo Perez
	 */	
	public String getSharedApplicationType(String appID);
	
	
	/**
	 * 
	 * Function that checks if an application has been already shared
	 * 
	 * @param appId	Application id in the standard format
	 * 
	 * @return true if already exists, false otherwise
	 * 
	 * @author David Rozas & Alfredo Perez
	 */
	public boolean isAlreadyShared(String appId);
	
	
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
	public String[] search(String userId, String q, String criterion);
	
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
	public String[] search(String userId, String q, String[] criteria);
	
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
	public String[] searchBySimilarity(String userId, String appId, String appDescription);

	
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
	public String getXmlRule(String appID, String ruleID, String newUserID);
	
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
	public String getRuleDescription(String appID, String ruleID);
	
}
