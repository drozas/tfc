package eu.ist.astra.rm.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;



/**
 * Represents a Shared Application. For the moment we have only considered to store:
 * 
 *	- appID		Application identifier in the standard format
 *	- type		Application type
 *	- uid		User who shares the application
 *	- desc		Application description gave for the user who shares it
 *	- date		Date when the application was shared
 *	- rules		Rules which belongs to this application
 *	- communities Community identifiers where this application is being shared
 * 	
 *
 *	@author David Rozas & Alfredo Perez
 */
public class SharedApplication {
	
	private String appID;  // appID = (remote)userID+":"+type
	private String type; //type of application
	private String uid; //  User who shared the app
	private String desc; // Application description
	private String date; // Date when it was shared
	private Hashtable<String,SharedRule> rules; //rules which belong to this application
	private List<String> communities;

	/**
	 * 
	 * Constructs a new SharedApplication object including date (used in load)
	 * 
	 * @param appID	Application identifier
	 * @param type	Application type
	 * @param uid	User who is sharing the application
	 * @param desc	Application description
	 * @param date	Date when the application was shared
	 */
	public SharedApplication(String appID, String type, String uid, String desc, String date) 
	{
	
		System.out.println("new "+this.getClass().getName()+"("+appID+","+uid+"," + desc+","+date+")");
		
		this.appID = appID;
		this.type = type;
		this.uid=uid;
		this.desc=desc;
		this.date=date;
		
		//Rules list initialization
		this.rules = new Hashtable<String, SharedRule>();
		
		//Communities initialization
		this.communities = new ArrayList<String>();

	}
	
	/**
	 * Returns the application id
	 * 
	 * @return application ID
	 */
	public String getAppID() 
	{
		return this.appID;
	}
	
	/**
	 * Returns the type of application
	 * 
	 * @return application type
	 */
	public String getType() 
	{
		return this.type;
	}
	
	/**
	 * Returns the userId who shared the application
	 * 
	 * @return userId
	 */
	public String getUid() 
	{
		return this.uid;
	}
	
	/**
	 * Returns the description of this application
	 * 
	 * @return description
	 */
	public String getDescription() 
	{
		return this.desc;
	}
	
	/**
	 * Returns the date when the application was shared
	 * 
	 * @return date
	 */
	public String getSharingDate() 
	{
		return this.date.toString();
	}
	
	/**
	 * Returns the name of the application
	 * 
	 * @return application name
	 */
	public String getName() 
	{
		return this.appID.substring(this.appID.indexOf(":")+1);
	}
	
	/**
	 * Returns an string with the information of the application
	 * 
	 * @return String with all attributes
	 * 
	 */
	public String toString()
	{
		return "AppID: " + this.appID + " Type: " + this.type +  
				" UID: " + this.uid + " Description: " + this.desc +
				" Sharing date : " + this.date;
	}

	/**
	 * 
	 * Add the rule to the hash table which stores the rules
	 * 
	 * @param rule	Rule to add
	 */
	public void addSharedRule(SharedRule rule)
	{
		this.rules.put(rule.getId(), rule);
	}
	

	/**
	 * 
	 * Returns the SharedRule object identified by this rule identifier
	 * 
	 * @param	ruleId	Rule Identifier
	 * 
	 * @return	SharedRule object	
	 */
	public SharedRule getSharedRule(String ruleId)
	{
		return this.rules.get(ruleId);
	}
	
	/**
	 * 
	 * Returns true if the rule already exists, false otherwise
	 * 
	 * 
	 * @param ruleId	Rule identifier
	 * 
	 * @return	True if the rule exists, false otherwise
	 */
	public boolean existsSharedRule(String ruleId)
	{
		return this.rules.containsKey(ruleId);
	}
	
	/**
	 * 
	 * Returns the list of shared rules identifiers for this application
	 * 
	 * @return	List of shared rules
	 */
	public Enumeration<String> getSharedRulesList()
	{
		return this.rules.keys();
	}
	
	/**
	 * 
	 * Add the community in local memory
	 * 
	 * @param communityId	Community to add
	 */
	public void addCommunity(String communityId)
	{
		this.communities.add(communityId);
	}
	

	/**
	 * 
	 * Returns true if the application is shared in the given community
	 * 
	 * 
	 * @param communityId	Community identifier
	 * 
	 * @return	True if the application is being shared in that community, false otherwise
	 */
	public boolean isSharedInCommunity(String communityId)
	{
		return this.communities.contains(communityId);
	}
	
	/**
	 * 
	 * Returns the list of communities where this application is being shared
	 * 
	 * @return	List of communities
	 */
	public List<String> getCommunitiesList()
	{
		return this.communities;
	}
	
	/**
	 * 
	 * Check that the application can be retrieved by this user
	 * 
	 * @param communities	Communities this user is member of
	 * 
	 * @return	True if the user is member of at least one, false otherwise
	 * 
	 */
	public boolean canBeRetrieved(String[]communities)
	{
		boolean hasAny = false;
		int i = 0;
		while(i<communities.length && !hasAny)
		{
			hasAny = this.getCommunitiesList().contains(communities[i]);
			i++;
		}
		
		return hasAny;
	}
}
