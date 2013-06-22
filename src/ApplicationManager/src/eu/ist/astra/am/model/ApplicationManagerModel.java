package eu.ist.astra.am.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import eu.ist.astra.aam.IAwarenessApplicationManager;
import eu.ist.astra.awarenessmanager.IAwarenessManager;
import eu.ist.astra.ontologymanager.api.IOntologyManager;
import eu.ist.astra.rfm.IRemoteFrameworkManager;
import eu.ist.astra.rfm.proxies.ICommunityManager;
import eu.ist.astra.rfm.proxies.IRepositoryManager;
import eu.ist.astra.rfm.proxies.ITagManagerBackEnd;
import eu.ist.astra.rfm.proxies.IUserManager;
import eu.ist.astra.tmn.ITagManagerNode;

/**
 * 
 * It implements the model part of the application following the 
 * MVC architectural pattern
 * (Adapted to SWING based on the article: http://www.developer.com/design/article.php/3678856)
 * 
 * Remote exceptions are thrown to be threaten in the controller where
 * the necessary measures are taken.
 * 
 * @author David Rozas
 *
 */
public class ApplicationManagerModel {
	
	private static ApplicationManagerModel instance = null;
	private BundleContext bc = null;
	private String applicationTitle = null;
	private String uid = null;
	
	private final String repository_root_name = "Repository";
	private final String myApps_root_name = "My applications";
	private final String communities_repository_root = "By communities";
	private final String communities_my_apps_root = "Shared in";
	
	private final String helpManualUrl = "http://astra.perseum.com/help/am-help.html";

	//Remote and local bundle references.
	IRemoteFrameworkManager rfm;
	IAwarenessApplicationManager aam;
	IAwarenessManager am;
	ICommunityManager cm;
	IUserManager um;
	IRepositoryManager rm;
	IOntologyManager om;
	ITagManagerBackEnd tmbe;
	ITagManagerNode tmn;
	
	/**
	 * 	Nothing needed, but this prevents a public no-arg
	 *  constructor from being created automatically.
	 *  
	 *  @author David Rozas
	 * 
	 */
	protected ApplicationManagerModel() {
	}

	/**
	 * 
	 * Returns the single instance of the model.
	 * 
	 * @return	reference to ApplicationManagerModel
	 * 
	 * @author David Rozas
	 * 
	 */
	public static ApplicationManagerModel getInstance() {
		if (instance==null) {
			instance = new ApplicationManagerModel();
		}
		return instance;
	}
	
	/**
	 * 
	 * Set the bundle context, to be used by the model
	 * 
	 * @param bc Application Manager bundle context
	 * 
	 * @author David Rozas
	 */
	public void setBundleContext(BundleContext bc) {
		this.bc = bc;
		// Get the bundle references.
		this.getReferences();
		this.setApplicationTitle();
	}
	
	/**
	 * 
	 * Sets the application title with the bundle context information:
	 * Bundle-Vendor + Bundle-Name
	 * 
	 * @author David Rozas
	 * 
	 */
	private void setApplicationTitle()
	{
		this.applicationTitle = this.bc.getBundle().getHeaders().get("Bundle-Vendor").toString() + " - " + 
								this.bc.getBundle().getHeaders().get("Bundle-Name").toString();
		
	}
	
	/**
	 * 
	 * Sets the current user id and store it in the model, once 
	 * he/she has been validated by the UserManager
	 * 
	 * @param uid	User id in standard Astra format
	 * 
	 * @author David Rozas
	 * 
	 */
	public void setUserId(String uid)
	{
		this.uid = uid;
	}
	

	
	/**
	 * 
	 * Returns the ApplicationManager title
	 * (the one of the bundle itself, do not confuse with an "astra application")
	 * 
	 * @return Application Manager title
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getApplicationManagerTitle(){
		return this.applicationTitle;
	}
	
	/**
	 * 
	 * Returns the root name of the repository (constant in the model)
	 * 
	 * @return	root name of the repository
	 * 
	 * @author David Rozas
	 */
	public String getRepository_root_name() {
		return this.repository_root_name;
	}
	
	/**
	 * 
	 * Returns the root name of my applications (constant in the model)
	 * 
	 * @return	root name of my applications
	 * 
	 * @author David Rozas
	 */
	public String getMyApplications_root_name() {
		return this.myApps_root_name;
	}
	
	/**
	 * 
	 * Returns the root name of the communities in the repository (constant in the model)
	 * 
	 * @return	root name of my the communities in the repository
	 * 
	 * @author David Rozas
	 */
	public String getCommunitiesRepositoryRoot() {
		return this.communities_repository_root;
	}
	
	/**
	 * 
	 * Returns the root name of the communities in my apps (constant in the model)
	 * 
	 * @return	root name of my communities in my apps
	 * 
	 * @author David Rozas
	 */
	public String getCommunitiesMyApplicationsRoot() {
		return this.communities_my_apps_root;
	}
	
	///// Internal operations /////
	
	///////////// Bundles references /////////////////////////////
	/**
	 * 
	 * It retrieves all the references (local an remote) to the rest of
	 * bundles
	 * 
	 * @author David Rozas
	 * 
	 */
	private void getReferences(){
		getRemoteFrameworkReference();
		getAwarenessAppReference();
		getOntologyManagerReference();
		getTagManagerNodeReference();
		getAwarenessManagerReference();
		


		cm = (ICommunityManager)rfm.getRemoteService("CommunityManager");
		um = (IUserManager)rfm.getRemoteService("UserManager");
		rm = (IRepositoryManager)rfm.getRemoteService("RepositoryManager");
		tmbe = (ITagManagerBackEnd)rfm.getRemoteService("TagManagerBackEnd");


	}

	/**
	 * 
	 * Retrieve a reference to the Remote Framework.
	 * 
	 * @author David Rozas
	 * 
	 */
	private void getRemoteFrameworkReference(){
		ServiceReference sr = bc.getServiceReference(IRemoteFrameworkManager.class.getName());
		if (sr == null) {
			rfm = null;
			return;
		}
		rfm = (IRemoteFrameworkManager)bc.getService(sr);
	}

	/**
	 * 
	 * Retrieve a reference to the Awareness Application Manager.
	 * 
	 * @author David Rozas
	 * 
	 */
	private void getAwarenessAppReference(){
		ServiceReference sr = bc.getServiceReference(IAwarenessApplicationManager.class.getName());
		if (sr == null) {
			aam = null;
			return;
		}
		aam = (IAwarenessApplicationManager)bc.getService(sr);
	}
	
	/**
	 * 
	 * Retrieve a reference to the Awareness Manager.
	 * 
	 * @author David Rozas
	 * 
	 */
	private void getAwarenessManagerReference(){
		ServiceReference sr = bc.getServiceReference(IAwarenessManager.class.getName());
		if (sr == null) {
			am = null;
			return;
		}
		am = (IAwarenessManager)bc.getService(sr);
	}
	
	/**
	 * 
	 * Retrieve a reference to the OntologyManager.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	private void getOntologyManagerReference(){
		ServiceReference sr = bc.getServiceReference(IOntologyManager.class.getName());
		if (sr == null) {
			this.om = null;
			return;
		}
		this.om = (IOntologyManager)bc.getService(sr);
	}
	
	/**
	 * 
	 * Retrieve a reference to the TagManagerNode.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	private void getTagManagerNodeReference(){
		ServiceReference sr = bc.getServiceReference(ITagManagerNode.class.getName());
		if (sr == null) {
			this.tmn = null;
			return;
		}
		this.tmn = (ITagManagerNode)bc.getService(sr);
	}
	


	//// Here we have the "business logic". In our case, is mostly "stubs" of the calls/////
	
	
	//////////////// Repository operations //////////////////////
	
	/**
	 * 
	 * Retrieves all the applications from the repository by
	 * calling the bundle remotely.
	 * 
	 * @return	List of applications identifiers.
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getRepositoryApplications() throws RemoteException
	{
		return this.rm.listSharedApplications(this.uid);
	}
	
	
	/**
	 * Returns the application title of an application given its application
	 * identifier, calling the bundle remotely.
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Title of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getRepositoryApplicationTitle(String appId) throws RemoteException
	{
		return this.rm.getSharedApplicationName(appId);
	}
	
	/**
	 * 
	 * Returns the application description of an application given its application
	 * identifier, calling the bundle remotely.
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Description of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getRepositoryApplicationDescription(String appId) throws RemoteException
	{
		return this.rm.getSharedApplicationDescription(appId);
	}
	
	/**
	 * 
	 * Returns the application owner of an application given its application
	 * identifier, calling the bundle remotely.
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Owner of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getRepositoryApplicationOwner(String appId) throws RemoteException
	{
		return this.rm.getSharedApplicationOwner(appId);
	}
	
	/**
	 * 
	 * Returns the application type of an application given its application
	 * identifier, calling the RepositoryManager bundle remotely.
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Type (focus or nimbus) of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getRepositoryApplicationType(String appId) throws RemoteException
	{
		return this.rm.getSharedApplicationType(appId);
	}
	
	/**
	 * 
	 * Returns true if the application is already shared in the RM,
	 * false otherwise
	 * 
	 * @param appId	Application identifier in the standard Astra format
	 * @return True if the app is already shared, false otherwise
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean isAlreadyShared(String appId) throws RemoteException
	{
		return this.rm.isAlreadyShared(appId);
	}
	
	/**
	 * 
	 * Checks if the application is shared in the given community
	 * 
	 * @return	True if the application has been shared into that community,
	 * 			false otherwise.
	 * 
	 * @throws RemoteException
	 */
	public boolean isSharedInCommunity(String appId, String communityId) throws RemoteException
	{
			return this.rm.isSharedInCommunity(appId, communityId);
	}
	
	/**
	 * 
	 * Returns a set of applications identifiers based on the query performed
	 * by the user.
	 * 
	 * 
	 * @param query	Query 
	 * @param criterion	Criterion to filter by
	 * @return	Application identifiers in Astra standard format, null if there
	 * 			were not results
	 * 
	 * @author David Rozas
	 */
	public String[] search(String query, String criterion) throws RemoteException{
		
		String[] results = null;	
			
		if (criterion.equalsIgnoreCase("any"))
		{
			String[] fields = {"description", "tags", "type"};
			return this.rm.search(this.uid, query, fields);
		}else if (criterion.equalsIgnoreCase("by description")){
			return this.rm.search(this.uid, query, "description");
		}else if (criterion.equalsIgnoreCase("by type")){
			return this.rm.search(this.uid, query, "type");
		}else if (criterion.equalsIgnoreCase("by tags")){
			return this.rm.search(this.uid, query, "tags");
		}

		return results;
	}
	

	
	
	/**
	 * 
	 * Returns a set of applications identifiers based on the similarity to this application
	 * 
	 * @param Application identifier 
	 * @return	Application identifiers in Astra standard format, null if there
	 * 			were not results
	 * 
	 * @author David Rozas
	 */
	public String[] searchBySimilarity(String appId) throws RemoteException{
		return this.rm.searchBySimilarity(this.uid, appId, (String)this.aam.getData(appId, "desc"));
	}
	
	/**
	 * 
	 * Performs all the necessary operations to share the application.
	 * 
	 * @param appId	Application identifier
	 * @param appDesc	Application description
	 * 
	 * @author David Rozas
	 * 
	 */
	public void createSharedApplication(String appId, String appDesc) throws RemoteException
	{
		this.rm.createSharedApplication(appId, this.uid, this.getMyApplicationsApplicationType(appId), appDesc);
	}
	
	/**
	 * 
	 * It stores the given application in the given community into the Repository.
	 * 
	 * @param appId	Application identifier in the standard Astra format
	 * @param communityId	Community identifier
	 * 
	 * @author David Rozas
	 * 
	 */
	public void exportInCommunity(String appId, String communityId) throws RemoteException
	{
			this.rm.shareInCommunity(this.uid, appId, communityId);
	}
	
	/**
	 * 
	 * It stores the given rule in the RepositoryManager.
	 * 
	 * @param appId	Application identifier in the standard Astra format.
	 * @param ruleId	Rule identifier.
	 * 
	 * @author David Rozas
	 */
	public void exportRule(String appId, String ruleId) throws RemoteException
	{
			this.rm.createSharedRule(this.uid, appId, ruleId, this.aam.getRuleXML(ruleId));
	}
	
	/**
	 * Return a list of all the shared rules for an application stored in 
	 * the repository
	 * 
	 * @return String array with all the rules
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getRulesFromRepository(String appId) throws RemoteException
	{
			return this.rm.listSharedRules(appId);
	}
	
	/**
	 * 
	 * Returns the description of the rule
	 * 
	 * @param appId	Application identifier
	 * @param owner	Application owner
	 * @param rule	Rule identifier
	 * 
	 * @return	Rule description
	 * 
	 * @author David Rozas
	 */
	public String getRemoteRuleDescription(String appId, String owner, String ruleId) throws RemoteException
	{
		//Recompose the old identifier
		String oldAppId = owner + ":" + this.getApplicationPart(appId);
		String oldRuleId = owner + ":" + this.getApplicationPart(ruleId);
		return this.rm.getRuleDescription(oldAppId, oldRuleId);

	}
	
	/**
	 * 
	 * Import the rule from the repository.
	 * 
	 * @param appId	Application identifier
	 * @param owner	New owner
	 * @param oldRuleId	Old rule identifier
	 * 
	 * @author David Rozas
	 */
	public void importRule(String appId, String owner, String oldRuleId)
	{
		String oldAppId = owner + ":" + this.getApplicationPart(appId);

		try 
		{
			String xmlRule = this.rm.getXmlRule(oldAppId, oldRuleId, this.uid);
			String newRuleId = this.am.setRule(xmlRule);
			if (!newRuleId.equals(""))
				this.aam.addRuleToApplication(appId, newRuleId);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	

	///////////////// UserManager operations ////////////////////////
	
	/**
	 * 
	 * Validates the user by calling the User Manager remotely.
	 * 
	 * @param username	
	 * @param password
	 * @return	User ID in standard astra format if there were no problems, null otherwise
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String validate(String username, String password) throws RemoteException{
		return this.um.authenticateUser(username, password);
	}
	

	///////////////// OntologyManager operations ////////////////////////
	
	/**
	 * 
	 * TODO: While the interface to connect with the OntologyManager is discussed,
	 * this method will return null and the user will be informed to adapt
	 * the application manually
	 * 
	 * @param appId		Application ID in standard astra format
	 * 
	 * @author David Rozas
	 */
	public String[] analyzeApplication(String appId)
	{
	
		//this.om.getRecommendedDevicesSubstitution(this.uid, String appId,String device)
		return null;
	}
	

	///////////////// AwarenessApplicationManager operations ////////////////////////
	
	/**
	 * 
	 * Retrieves all the applications from the Awareness Application
	 * Manager which belong to this user calling the bundle remotely.
	 * 
	 * @return	List of applications identifiers.
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getMyApplications()
	{
		return this.aam.listApplications(this.uid);
	}
	
	/**
	 * Returns the application title of an application given its application
	 * identifier, calling the Awareness Application Manager
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Title of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getMyApplicationsApplicationTitle(String appId)
	{
		return this.aam.getApplicationName(appId);
	}
	
	/**
	 * Returns the application description of an application given its application
	 * identifier, calling the Awareness Application Manager
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Description of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getMyApplicationsApplicationDescription(String appId)
	{
		return (String)this.aam.getData(appId, "desc");
	}
	
	/**
	 * 
	 * Returns the application type of an application given its application
	 * identifier, calling the Awareness Application Manager
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	Type (focus or nimbus) of this application
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 * 
	 */
	public String getMyApplicationsApplicationType(String appId)
	{
		return this.aam.getApplicationType(appId);
	}
	
	
	/**
	 * 
	 * Returns the rule xml file
	 * 
	 * @param ruleId	Rule identifier in the standard astra format
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getMyApplicationsRuleXml(String ruleID)
	{
		return this.aam.getRuleXML(ruleID);
	}
	
	/**
	 * Return a list of all the rules for a given application, null if there was any problem.
	 * 
	 * @return String array with all the rules, null if there was any problem.
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getRules(String appId)
	{
			return this.aam.getApplicationRules(appId);
	}
	
	/**
	 * 
	 * It creates the application in the AwarenessApplicationManager
	 * 
	 * @param appName	Application name
	 * @return	True if the process was successful, false otherwise.
	 * 
	 * @author David Rozas
	 */
	public boolean createApplicationInAAM(String appName)
	{
		return this.aam.createApplication(this.uid, appName);
	}
	
	/**
	 * 
	 * Stores all the information about the application in the AwarenessApplicationManager
	 * 
	 * @param appId	Application identifier in the standard Astra format
	 * @param appDescription	Application description
	 * 
	 * @author David Rozas
	 */
	public void saveApplicationDataInAAM(String appId, String appDescription)
	{
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		
		this.aam.putData(appId, "desc", appDescription);
		this.aam.putData(appId, "owner", this.uid);			
		this.aam.putData(appId, "cdate", dateFormat.format(date));
		this.aam.putData(appId, "publishable", "1");
		this.aam.putData(appId, "state", "on");
	}

	

	
	///////////////// AwarenessManager operations ////////////////////////
	
	/**
	 * 
	 * Returns the description of the rule
	 * 
	 * @param ruleXml	rule in xml format
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getMyApplicationsRuleDescription(String ruleXml)
	{
		return this.am.getRuleDescription(ruleXml);
	}
	
	
	///////////////// TagManagerBackEnd operations ////////////////////////
	
	/**
	 * Returns the public tags of an application given its application
	 * identifier, calling the TagManagerBackEnd
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	List of public tags
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getPublicTags(String appId) throws RemoteException
	{
		return this.tmbe.getTags(appId, -1);
	}
	
	/**
	 * Returns the community tags of an application given its application
	 * identifier, calling the TagManagerBackEnd
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @param communityId	Community identifier
	 * 
	 * 
	 * @return	List of tags
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getTagsByCommunity(String appId, String communityId) throws RemoteException
	{
		return this.tmbe.getTagsByCommunity(appId, -1, communityId);
	}
	
	
	/**
	 * Returns all the tags (public or from communities where this user is part of) from
	 * a given application identifier.
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	List of tags
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getOthersTags(String appId) throws RemoteException
	{
		return this.tmbe.getTagsByApplication(appId, this.uid, -1);
	}
	
	
	/**
	 * 
	 * It communicates with the remote bundle TagManagerBackEnd to store the public tag typed
	 * by the user
	 * 
	 * @param tag	Tag to be stored
	 * @param appId	Application identifier whose tag is going to be associated
	 * 
	 * @return	True if the operation was performed sucessfully, false otherwise (not valid 
	 * tag or duplicated tag)
	 * 
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean savePublicTag(String tag, String appId) throws RemoteException{
		return this.tmbe.addTag(tag, appId, this.uid);
	}
	
	/**
	 * 
	 * It communicates with the remote bundle TagManagerBackEnd to store the tag typed
	 * by the user in certain community
	 * 
	 * @param tag	Tag to be stored
	 * @param appId	Application identifier whose tag is going to be associated
	 * @param communityId	Community identifier whose tag is going to be associated to
	 * 
	 * @return	True if the operation was performed sucessfully, false otherwise (not valid 
	 * tag or duplicated tag)
	 * 
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean saveCommunityTag(String tag, String appId, String communityId) throws RemoteException{
		return this.tmbe.addTagByCommunity(tag, appId, this.uid, communityId);
	}
	
	/**
	 * 
	 * Deletes a public tag
	 * 
	 * @param tag	Name of the tag
	 * @param type	Type of tag
	 * @param appId	Application identifier in standard ASTRA format
	 * 
	 * @return	True if the process was sucessful, false otherwise.
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deletePublicTag(String tag, String type, String appId) throws RemoteException
	{
		return this.tmbe.deleteTag(tag, appId, this.uid);
	}
	
	/**
	 * 
	 * Deletes a public tag
	 * 
	 * @param tag	Name of the tag
	 * @param type	Type of tag
	 * @param appId	Application identifier in standard ASTRA format
	 * 
	 * @return	True if the process was sucessful, false otherwise.
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deleteCommunityTag(String tag, String type, String appId, String communityId) throws RemoteException
	{
		return this.tmbe.deleteTag(tag, appId, this.uid, communityId);
	}
	

	
	///////////////// TagManagerNode operations ////////////////////////
	
	/**
	 * Returns the private tags of an application given its application
	 * identifier, calling the TagManagerNode
	 * 
	 * @param appId	Application identifier in Astra standard format
	 * @return	List of private tags
	 * @throws RemoteException
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getPrivateTags(String appId) throws RemoteException
	{
		return this.tmn.getTags(appId, this.uid, -1);
	}
	
	/**
	 * 
	 * It communicates with the local bundle TagManagerNode to store the private tag typed
	 * by the user
	 * 
	 * @param tag	Tag to be stored
	 * @param appId	Application identifier whose tag is going to be associated
	 * 
	 * @return	True if the operation was performed sucessfully, false otherwise (not valid 
	 * tag or duplicated tag)
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean savePrivateTag(String tag, String appId){
		return this.tmn.addTag(tag, appId, this.uid);
	}
	
	/**
	 * 
	 * Deletes a private tag
	 * 
	 * @param tag	Name of the tag
	 * @param type	Type of tag
	 * @param appId	Application identifier in standard ASTRA format
	 * 
	 * @return	True if the process was sucessful, false otherwise.
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deletePrivateTag(String tag, String type, String appId) throws RemoteException
	{
		return this.tmn.deleteTag(tag, appId, this.uid);
	}
	
	///////////////// CommunityManager operations ////////////////////////
	
	/**
	 * Return a list of all the communities, null if there were any problems.
	 * 
	 * @return String array with all the communities, null if there was any problem.
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getCommunitiesList() throws RemoteException
	{
			return this.cm.getCommunityIdentifiers();
	}
	
	/**
	 * 
	 * Returns the name of the community
	 * 
	 * @param communityId	Community identifier
	 * 
	 * @return	Community name if everything was ok, null otherwise
	 */
	public String getCommunityName(String communityId) throws RemoteException
	{
			return this.cm.getCommunityName(communityId);
	}
	
	
	
	/**
	 * 
	 * Returns the list of communities this user is member of
	 * 
	 * @return	List of communities
	 * 
	 * @throws RemoteException
	 */
	public String[] getMyCommunities() throws RemoteException
	{
			return this.cm.isMemberOf(this.uid);
	}
	
	

	

	/////////////// Remote Help ///////////////////////////////////
	/**
	 * 
	 * Reads the HTML manual contents from the server.
	 * 
	 * @return	String with the help information in HTML format
	 * 
	 * @throws IOException If there were any connection problems
	 * 
	 * @author David Rozas
	 */
	public String getHelpContent() throws IOException{
		


		URL manualUrl;
		try {
			manualUrl = new URL(this.helpManualUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(manualUrl.openStream()));
			
			String inputLine;
			String res = "";

			while ((inputLine = in.readLine()) != null)
				res +=inputLine;

			in.close();
			
			return res;
		} catch (MalformedURLException e) {
			// Since we are setting the url this should never happen, but just in case
			e.printStackTrace();
			return null;
		}
	}
	

	//////////// Common Auxiliar functionalities ///////////////
	
	/**
	 * 
	 * Returns an identifier in the standard astra format, with the
	 * uid of the user which is running the application.
	 * This can be used for applications, rules, etc.
	 * 
	 * @param oldId	Old identifier
	 * 
	 * @return	New identifier
	 * 
	 * @author David Rozas
	 */
	public String changeOwner(String oldId)
	{
		String[] parts = oldId.split(":");
		return this.uid + ":" + parts[(parts.length-1)];
	}
	
	/**
	 * 
	 * Returns the application name
	 * 
	 * @param appId	Application identifier
	 * @return	Application name
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getApplicationPart(String appId)
	{
		String[] parts = appId.split(":");
		return parts[(parts.length-1)];
	}
	

	
}
