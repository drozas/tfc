package eu.ist.astra.am.controller;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.osgi.framework.BundleContext;


import eu.ist.astra.am.model.ApplicationManagerModel;
import eu.ist.astra.am.view.AddTagWindow;
import eu.ist.astra.am.view.ApplicationTreeNode;
import eu.ist.astra.am.view.HelpWindow;
import eu.ist.astra.am.view.LoginWindow;
import eu.ist.astra.am.view.MainWindow;
import eu.ist.astra.am.view.RetrieveApplicationWindow;
import eu.ist.astra.am.view.ShareApplicationWindow;
import eu.ist.astra.am.view.ApplicationTableModel;
import eu.ist.astra.am.view.TagListItem;

import java.awt.Color;
import java.io.IOException;

/**
 * 
 * It implements the controller part of the application following the 
 * MVC architectural pattern
 * (Adapted to SWING based on the article: http://www.developer.com/design/article.php/3678856)
 * 
 * @author David Rozas
 *
 */
public class ApplicationManagerController {
	
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	private static ApplicationManagerController instance = null;
	private ApplicationManagerModel model = null;
	
	/* ===== Singleton details ===== */
	
	/**
	 * 	Internal Constructor
	 *  
	 *  @author David Rozas
	 * 
	 */
	protected ApplicationManagerController() {
		installLnF();
		this.model = ApplicationManagerModel.getInstance();
	}
	
	/**
	 * 
	 * It returns an instance of the controller (which will be unique)
	 * 
	 * @return	ApplicationManagerController instance
	 * 
	 * @author David Rozas
	 */
	public static ApplicationManagerController getInstance() {
		if (instance==null) {
			instance = new ApplicationManagerController();
		}
		return instance;
	}

	/**
	 * 
	 * It installs the standard Look and Feel
	 * 
	 */
	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}
	
	
	/* ===== controller instance ===== */
	
	//GUI widgets we want to call methods on
	private MainWindow mainWindow = null;
	private LoginWindow loginWindow = null;
	private ShareApplicationWindow shareWindow = null;
	private RetrieveApplicationWindow retrieveWindow = null;
	private HelpWindow helpWindow = null;
	private AddTagWindow addTagWindow = null;
	
	
	/////// Login window components //////////////
	private JLabel loginLabel = null;
	private JTextField loginTextField = null;
	private JPasswordField passwordField = null;
	
	////// My applications components ////////////////
	private JTree myApplicationsTree = null;
	private JTextField myApplicationsApplicationTitle = null;
	private JTextArea myApplicationsApplicationDescription = null;
	private JButton myApplicationsShareButton = null;
	private JList myApplicationsAppMyTagsList = null;
	private JButton myApplicationsAddTagButton = null;
	private JButton myApplicationsDeleteTagButton = null;
	private JLabel myApplicationsLoadingLabel = null;
	private JScrollPane myApplicationsScrollPanel = null;

	////// Repository components ////////////////
	private JTree repositoryTree = null;
	private JTextField repositoryApplicationTitle = null;
	private JTextArea repositoryApplicationDescription = null;
	private JButton repositoryGetButton = null;
	private JList repositoryTagList = null;
	private JLabel repositoryLoadingLabel = null;
	private JScrollPane repositoryScrollPanel = null;
	
	////// Search by criteria components ////////////////
	private JComboBox searchComboBox = null;
	private JTextField searchTextField = null;
	private JList searchList = null;
	private JTextField searchAppTitle = null;
	private JTextArea searchAppDescription = null;
	private JButton searchGetButton = null;
	
	////// Search by similarity components ////////////////
	private JTree similarityAppsTree = null;
	private JList similarityResultsList = null;
	private JTextField similarityAppTitle = null;
	private JTextArea similarityAppDescription = null;
	private JButton similarityGetButton = null;
	private JLabel similarityLoadingLabel = null;
	private JScrollPane similarityScrollPanel = null;
	

	/////// Share window components //////////////
	private JTable shareAppCommunitiesTable = null;
	private JTextArea shareAppDescription = null;
	private JTextField shareAppId = null;
	private JTable shareAppRulesTable = null;
	private JTextArea shareRuleDescription = null;
	private JLabel shareRuleDescriptionLabel = null;
	
	/////// Retrieve window components //////////////
	private JTextArea retrieveAppDescription = null;
	private JTextField retrieveAppId = null;
	private JTextField retrieveAppOwner = null;
	private JTable retrieveAppRulesTable = null;
	private JTextArea retrieveRuleDescription = null;
	private JLabel retrieveRuleDescLabel = null;
	
	/////// Add tag window components //////////////
	private JTextField addTagAppId = null;
	private JTable addTagCommunitiesTable = null;
	private JTextField addTagName = null;
	private JRadioButton addTagPublicButton = null;
	private JRadioButton addTagCommunitiesButton = null;
	private JRadioButton addTagPrivateButton = null;

	/* ===== controller instance fields methods ===== */

	
	/////// Login window references //////////////
	
	/**
	 * Sets the login label (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setLoginLabel(JLabel label) {
		this.loginLabel = label;
	}
	
	/**
	 * Sets the login text field (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setLoginTextField(JTextField textField) {
		this.loginTextField = textField;
	}
	
	/**
	 * Sets the login password field (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
	

	/////// My applications references //////////////
	/**
	 * Sets the tags list in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyAppMyTagsList(JList list) {
		this.myApplicationsAppMyTagsList = list;
	}
	
	/**
	 * Sets the delete tag button in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyAppDeleteTagButton(JButton button) {
		this.myApplicationsDeleteTagButton = button;
	}
	
	
	
	/**
	 * Sets the tree in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsTree(JTree tree) {
		this.myApplicationsTree = tree;
	}
	
	
	/**
	 * Sets application title in the repository tab(to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsApplicationTitle(JTextField title) {
		this.myApplicationsApplicationTitle = title;
	}
	
	/**
	 * Sets the application description in "My applications" tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsApplicationDescription(JTextArea desc) {
		this.myApplicationsApplicationDescription = desc;
	}
	
	/**
	 * Sets the share application button in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsButton(JButton button) {
		this.myApplicationsShareButton = button;
	}
	
	/**
	 * Sets the share application button in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsAddTagButton(JButton button) {
		this.myApplicationsAddTagButton = button;
	}
	
	/**
	 * Sets the loading label in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsLoadingLabel(JLabel label) {
		this.myApplicationsLoadingLabel = label;
	}
	
	/**
	 * Sets the scroll panel in my applications tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setMyApplicationsScrollPanel(JScrollPane panel) {
		this.myApplicationsScrollPanel = panel;
	}


	/////// Repository references //////////////
	
	/**
	 * Sets the tree in the repository tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryTree(JTree tree) {
		this.repositoryTree = tree;
	}
	
	/**
	 * Sets application title in the repository tab(to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryApplicationTitle(JTextField title) {
		this.repositoryApplicationTitle = title;
	}
	
	/**
	 * Sets the application description in the repository tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryApplicationDescription(JTextArea desc) {
		this.repositoryApplicationDescription = desc;
	}

	/**
	 * Sets the get application button in the repository tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryGetButton(JButton button) {
		this.repositoryGetButton = button;
	}
	
	/**
	 * Sets the tags list in the repository tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryTagsList(JList list) {
		this.repositoryTagList = list;
	}
	
	
	/**
	 * Sets the loading label in the repository tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryLoadingLabel(JLabel label) {
		this.repositoryLoadingLabel = label;
	}
	
	/**
	 * Sets the scroll panel in the repository tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRepositoryScrollPanel(JScrollPane panel) {
		this.repositoryScrollPanel = panel;
	}
	
	
	/////// Search by criteria references //////////////	
	
	/**
	 * Sets the combo box in the search tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSearchComboBox(JComboBox combo){
		this.searchComboBox = combo;
	}
	
	/**
	 * Sets the text field in the search tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSearchTextField(JTextField textField){
		this.searchTextField = textField;
	}
	

	
	/**
	 * Sets the list in the search tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSearchList (JList list){
		this.searchList = list;
	}
	
	/**
	 * Sets the application text field in the search tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSearchApplicationTextField(JTextField textField){
		this.searchAppTitle = textField;
	}
	
	/**
	 * Sets the application description text area in the search tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSearchAppDescTextArea(JTextArea desc) {
		this.searchAppDescription = desc;
	}
	
	/**
	 * Sets the get application button in the search tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSearchGetButton(JButton button){
		this.searchGetButton = button;
	}
	

	/////// Search by similarity references //////////////
	
	
	/**
	 * Sets the tree in the similarity tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityApplicationsTree(JTree tree) {
		this.similarityAppsTree = tree;
	}
	

	
	/**
	 * Sets the results list for similarity (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityResultsList(JList list) {
		this.similarityResultsList = list;
	}
	
	/**
	 * Sets the application text field in the similarity tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityAppTitle(JTextField textField){
		this.similarityAppTitle = textField;
	}
	
	/**
	 * Sets the application description text area in the similarity tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityAppDescription(JTextArea desc) {
		this.similarityAppDescription = desc;
	}
	
	/**
	 * Sets the get application button in the similiarity tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityGetButton(JButton button) {
		this.similarityGetButton = button;
	}
	
	/**
	 * Sets the loading label in the similarity tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityLoadingLabel(JLabel label) {
		this.similarityLoadingLabel = label;
	}
	
	/**
	 * Sets the scroll panel in the similarity tab (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setSimilarityScrollPanel(JScrollPane panel) {
		this.similarityScrollPanel = panel;
	}
	
	
	/////// Share window references //////////////
	
	/**
	 * Sets the communities table in the sharing window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setShareWindowCommunitiesTable(JTable table){
		this.shareAppCommunitiesTable = table;
	}
	
	
	/**
	 * Sets the text area in the sharing window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setShareWindowAppDesc(JTextArea textArea){
		this.shareAppDescription = textArea;
	}
	
	/**
	 * Sets the application id textfiel in the sharing window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setShareWindowAppId(JTextField textField){
		this.shareAppId = textField;
	}
	
	/**
	 * Sets the rules table in the sharing window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setShareWindowRulesTable(JTable table){
		this.shareAppRulesTable = table;
	}
	
	/**
	 * Sets the text area for rule description in the retrieving window (to be called by model)
	 *
	 * @author David Rozas
	 */
	public void setShareWindowRuleDesc(JTextArea textArea){
		this.shareRuleDescription = textArea;
	}
	
	/**
	 * Sets the label for rule description in the sharing window (to be called by model)
	 *
	 * @author David Rozas
	 */
	public void setShareWindowRuleDescLabel(JLabel label){
		this.shareRuleDescriptionLabel = label;
	}
	
	
	/////// Retrieve window references //////////////	
	
	/**
	 * Sets the text area in the retrieving window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRetrieveWindowAppDesc(JTextArea textArea){
		this.retrieveAppDescription = textArea;
	}
	
	/**
	 * Sets the app identifier text field in the retrieving window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRetrieveWindowAppId(JTextField textField){
		this.retrieveAppId = textField;
	}
	
	/**
	 * Sets the owner text field in the retrieving window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRetrieveWindowAppOwner(JTextField textField){
		this.retrieveAppOwner = textField;
	}
	
	
	/**
	 * Sets the text area for rule description in the retrieving window (to be called by model)
	 *
	 * @author David Rozas
	 */
	public void setRetrieveWindowRuleDesc(JTextArea textArea){
		this.retrieveRuleDescription = textArea;
	}
	
	/**
	 * Sets the label for rule description in the retrieving window (to be called by model)
	 *
	 * @author David Rozas
	 */
	public void setRetrieveRuleDescLabel(JLabel label){
		this.retrieveRuleDescLabel = label;
	}
	
	/////// Add tag window references //////////////
	
	/**
	 * Sets the application identifier text field in the add tag window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setAddTagAppIde(JTextField textField){
		this.addTagAppId = textField;
	}
	
	
	/**
	 * Sets the rules table in the retrieving window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setRetrieveWindowRulesTable(JTable table){
		this.retrieveAppRulesTable = table;
	}
	
	
	/**
	 * Sets the communities table in the add tag window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setAddTagCommunitiesTable(JTable table){
		this.addTagCommunitiesTable = table;
	}
	
	/**
	 * Sets the private radio button in the add tag window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setAddTagPrivateButton(JRadioButton button){
		this.addTagPrivateButton = button;
	}
	
	/**
	 * Sets the communities radio button in the add tag window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setAddTagCommunitiesButton(JRadioButton button){
		this.addTagCommunitiesButton = button;
	}
	
	/**
	 * Sets the public radio button in the add tag window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setAddTagPublicButton(JRadioButton button){
		this.addTagPublicButton = button;
	}
	
	
	/**
	 * Sets the communities radio button in the add tag window (to be called by model)
	 * 
	 * @author David Rozas
	 */
	public void setAddTagName(JTextField textArea){
		this.addTagName = textArea;
	}
	
	
	/**
	 * Sets the bundle context in the model (which is only accessed
	 * from the model)
	 * 
	 * @param bc Bundle context
	 * 
	 * @author David Rozas
	 */
	public void setBundleContext(BundleContext bc) {
		//this.bc = bc;
		this.model.setBundleContext(bc);
	}
	
	
	/**
	 * 
	 * It implements all the necessary operations to be done when the
	 * application is started
	 * 
	 * @author David Rozas
	 * 
	 */
	public void start(){
		this.loginWindow = new LoginWindow("Login");
		this.loginLabel.setText(this.model.getApplicationManagerTitle());
		
		//These are the setters which the plugin creates automatically in the main method
		this.loginWindow.pack();
		this.loginWindow.setLocationRelativeTo(null);
		this.loginWindow.setVisible(true);
		
	}
	
	/**
	 * 
	 * It implements all the necessary operations to be done when the
	 * application is stopped
	 * 
	 * @author David Rozas
	 * 
	 */
	public void stop(){
		if (this.loginWindow != null)
			this.loginWindow.dispose();
		
		if (this.mainWindow != null)
			this.mainWindow.dispose();
		
		if (this.shareWindow != null)
			this.shareWindow.dispose();
		
		if (this.retrieveWindow != null)
			this.retrieveWindow.dispose();
		
		if (this.helpWindow != null)
			this.helpWindow.dispose();
		
		if (this.addTagWindow != null)
			this.addTagWindow.dispose();
		
	}
	
	/**
	 *
	 * Implements the login process, and creates the main window if the process is correct
	 * 
	 * @author David Rozas
	 * 
	 */
	public void login() {
		String username = this.loginTextField.getText();
		String password = new String(this.passwordField.getPassword());
		String uid = null;

		if (username.equals("") || password.equals(""))
		{
			JOptionPane.showMessageDialog(this.loginWindow, "Username and password cannnot be empty!", 
					"Login error", JOptionPane.ERROR_MESSAGE);

		}else{
			try {
				
				uid = this.model.validate(username, password);
				if (uid!=null)
				{
					this.loginWindow.dispose();
					this.model.setUserId(uid);
					
					this.mainWindow = new MainWindow(this.model.getApplicationManagerTitle() + " (" + uid + ")");
					
					//These are the setters which the plugin creates automatically in the main method
					this.mainWindow.pack();
					this.mainWindow.setLocationRelativeTo(null);
					this.mainWindow.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(this.loginWindow, "The username or the password are not valid", 
							"Login error", JOptionPane.ERROR_MESSAGE);					
				}
			} catch (RemoteException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.loginWindow, "It was not possible to connect with the Back-End", 
						"Login error", JOptionPane.ERROR_MESSAGE);	

			}
		}

	}
	
	/**
	 *
	 * Implements the logout process
	 * 
	 * @author David Rozas
	 * 
	 */
	public void logout() {
		String msg = "Are you sure you want to logout?";
		String title = "Logout";
		int reply = JOptionPane.showConfirmDialog(this.mainWindow, msg, title, JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION)
		{
			this.mainWindow.dispose();
			if (this.helpWindow != null)
				this.helpWindow.dispose();
			this.start();
		}

	}
	

	
	///////////////////// My applications ///////////////////////////////////
	
	/**
	 * 
	 * It loads all the applications which belongs to this user in my applications tree.
	 * It creates a thread to load the tree hierarchy.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadMyApplications() {

			
		//Disable the add and delete tag buttons when the frame is loaded
		this.myApplicationsAddTagButton.setEnabled(false);
		this.myApplicationsDeleteTagButton.setEnabled(false);
		
		this.myApplicationsApplicationTitle.setText("");
		this.myApplicationsApplicationDescription.setText("");
		this.myApplicationsAppMyTagsList.setModel(new DefaultListModel());
		this.myApplicationsShareButton.setEnabled(false);
		
		//We set the view port loading component
		this.myApplicationsScrollPanel.setViewportView(this.myApplicationsLoadingLabel);
		//And afterwards we create a thread which loads the repository applications
		//and sets the new viewport
		MyApplicationsThread worker = new MyApplicationsThread();
		worker.execute();
			
	}
	
	/**
	 * 
	 * It loads all the information of certain application selected in MyApplications
	 * and shows it in the relevant fields.
	 * An error message is shown if there are any connectivity problems with
	 * the back-end.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadMyApplicationsApplicationInfo() {
		
		if (this.myApplicationsTree.getLastSelectedPathComponent()!=null)
		{
			//This node is actually an ApplicationTreeNode, but here we access the parent class
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.myApplicationsTree.getLastSelectedPathComponent();

			if (isEligibleNode(node))
			{
				try 
				{
					this.myApplicationsApplicationTitle.setText(this.model.getMyApplicationsApplicationTitle(node.toString()));
					this.myApplicationsApplicationDescription.setText(this.model.getMyApplicationsApplicationDescription(node.toString()));
					
					//Add public and private tags
					this.loadTags(node.toString());

					
					//Activate the button if is not already shared
					if (!this.model.isAlreadyShared(node.toString()))
						this.myApplicationsShareButton.setEnabled(true);
					else
						this.myApplicationsShareButton.setEnabled(false);
					
					//Activates the add tag button
					this.myApplicationsAddTagButton.setEnabled(true);
					
					//And disable the delete tag button
					this.myApplicationsDeleteTagButton.setEnabled(false);

					
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
							"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
					e.printStackTrace();
				}
			}else{
				this.myApplicationsApplicationTitle.setText("");
				this.myApplicationsApplicationDescription.setText("");
				((DefaultListModel) this.myApplicationsAppMyTagsList.getModel()).clear();
				this.myApplicationsAddTagButton.setEnabled(false);
				this.myApplicationsShareButton.setEnabled(false);
			}
		}
		
	}
	
	/**
	 * 
	 * It performs all the checking related to share an application in the Repository
	 * tree, and calls the function which is in charge of sharing and adapting 
	 * the application.
	 * 
	 * In this case this operation in only performed by one of the widgets, but in this 
	 * way the shareApplication method is completely general.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void shareApplicationFromTree()
	{

		if (this.myApplicationsTree.getLastSelectedPathComponent()!=null)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.myApplicationsTree.getLastSelectedPathComponent();
			//We will have to check also if it is root's child (ex.: an empty category)
			if (isEligibleNode(node))
			{
				this.shareApplication(node.toString());
			}else{
				JOptionPane.showMessageDialog(this.mainWindow, "You have to select an application in the tree to share it", 
						"Sharing an application", JOptionPane.INFORMATION_MESSAGE);	
			}
		}

	}
	
	/////////////////// Repository ////////////////////////////////////////////
	
	/**
	 * 
	 * It loads all the repository applications in the repository tree.
	 * An error message is shown if there are any connectivity problems with
	 * the back-end.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadRepositoryApplications() {

		//Clear title and description
		this.repositoryApplicationTitle.setText("");
		this.repositoryApplicationDescription.setText("");
		
		
		//Clean the tags list
		((DefaultListModel) this.repositoryTagList.getModel()).clear();
		this.repositoryGetButton.setEnabled(false);
		
		//We set the view port loading component
		this.repositoryScrollPanel.setViewportView(this.repositoryLoadingLabel);
		
		//And afterwards we create thread which loads the repository applications
		//and sets the new viewport
		RepositoryApplicationsThread worker = new RepositoryApplicationsThread();
		worker.execute();
	
	}
	
	
	/**
	 * 
	 * It loads all the information of certain repository application, and shows them 
	 * in the relevant fields
	 * An error message is shown if there are any connectivity problems with
	 * the back-end.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadRepositoryApplicationInfo() {
		
		if (this.repositoryTree.getLastSelectedPathComponent()!=null)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.repositoryTree.getLastSelectedPathComponent();

			if (isEligibleNode(node))
			{
				try 
				{
					this.repositoryApplicationTitle.setText(this.model.getRepositoryApplicationTitle(node.toString()));
					this.repositoryApplicationDescription.setText(this.model.getRepositoryApplicationDescription(node.toString()));
					
					((DefaultListModel) this.repositoryTagList.getModel()).clear();
					
					//Get and render public tags
					String[] othersTags = this.model.getOthersTags(node.toString());
					if(othersTags!=null)
						for(int i=0; i<othersTags.length; i++)
							((DefaultListModel) this.repositoryTagList.getModel()).addElement(othersTags[i]);
					
					//And enable the button to allow retrieving it
					this.repositoryGetButton.setEnabled(true);
					
					
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
							"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
					e.printStackTrace();
				}
			}else{
				this.repositoryApplicationTitle.setText("");
				this.repositoryApplicationDescription.setText("");
				this.repositoryGetButton.setEnabled(false);
				((DefaultListModel) this.repositoryTagList.getModel()).clear();
				
			}
		}
		
	}
	

	
	/**
	 * 
	 * It performs all the checking related to get an application from the Repository
	 * tree, and calls the function which is in charge of retrieving and adapting 
	 * the application
	 * 
	 * @author David Rozas
	 * 
	 */
	public void retrieveApplicationFromTree()
	{

		if (this.repositoryTree.getLastSelectedPathComponent()!=null)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.repositoryTree.getLastSelectedPathComponent();

			if (isEligibleNode(node))
			{
				retrieveApplication(node.toString());
			}else{
				JOptionPane.showMessageDialog(this.mainWindow, "You have to select an application in the tree to retrieve it", 
						"Repository", JOptionPane.INFORMATION_MESSAGE);	
			}
		}

	}
	
	
	/////////////////// Search by criteria //////////////////////////////
	
	/**
	 * 
	 * Searches for an application and displays the results
	 * 
	 * @author David Rozas
	 * 
	 */
	public void search(){
		
		this.searchAppDescription.setText("");
		this.searchAppTitle.setText("");
		
		if (this.searchTextField.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this.mainWindow, "Please, enter some input in the field Keywords", 
					"Search", JOptionPane.WARNING_MESSAGE);
		}else{
			try
			{
				String[] results = this.model.search(this.searchTextField.getText(), this.searchComboBox.getSelectedItem().toString());
				if (results!=null && results.length>0)
				{
					DefaultListModel listModel = new DefaultListModel();
					//Display results
					for (int i=0; i<results.length; i++)
						listModel.addElement(results[i]);
					
					this.searchList.setModel(listModel);
	
				}else{
					JOptionPane.showMessageDialog(this.mainWindow, "No applications found. Please, perform a new query.", 
							"Search", JOptionPane.WARNING_MESSAGE);
				}
			}catch (RemoteException e){
				JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
						"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * It performs all the checking related to get an application from the searching
	 * process, and calls the function which is in charge of retrieving and adapting 
	 * the application
	 * 
	 * @author David Rozas
	 * 
	 */
	public void retrieveApplicationFromSearch()
	{
		if (this.searchList.getSelectedValue()!=null)
			this.retrieveApplication((String)this.searchList.getSelectedValue());
	}
	
	
	
	/**
	 * Loads the application information for a result of searching
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadSearchApplicationInfo() {
		
		if (this.searchList.getSelectedIndex()>=0)
		{
			String appId = (String)this.searchList.getSelectedValue();
			
			try
			{
				this.searchAppTitle.setText(this.model.getRepositoryApplicationTitle(appId));
				this.searchAppDescription.setText(this.model.getRepositoryApplicationDescription(appId));
				this.searchGetButton.setEnabled(true);
				
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
						"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
				e.printStackTrace();
			}
		}else{
			this.searchGetButton.setEnabled(false);
		}
		
	}
	

	
	
	////////////////////// Search by similarity ////////////////////////////////
	
	/**
	 * 
	 * It loads all the applications which belongs to this user in similar applications tree.
	 * It creates a thread to load the tree hierarchy.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadSimilarApplications() {
	
		this.similarityAppDescription.setText("");
		this.similarityAppTitle.setText("");
		this.similarityGetButton.setEnabled(false);
		this.similarityResultsList.setModel(new DefaultListModel());
		
		//We set the view port loading component
		this.similarityScrollPanel.setViewportView(this.similarityLoadingLabel);
		
		//And afterwards we create thread which loads the repository applications
		//and sets the new viewport
		SimilarApplicationsThread worker = new SimilarApplicationsThread();
		worker.execute();		
	}
	
	
	/**
	 * 
	 * Searches for a simmilar application and displays the results
	 * 
	 * @author David Rozas
	 * 
	 */
	public void searchBySimilarity(){
		

		if (this.similarityAppsTree.getLastSelectedPathComponent()!=null)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.similarityAppsTree.getLastSelectedPathComponent();

			if (isEligibleNode(node))
			{
				//Reinitialize the components which show the results
				this.similarityResultsList.setModel(new DefaultListModel());
				this.similarityAppTitle.setText("");
				this.similarityAppDescription.setText("");
				try
					{
					String[] results = this.model.searchBySimilarity(node.toString());
					if (results!=null && results.length>0)
					{
						DefaultListModel listModel = new DefaultListModel();
						//Display results
						for (int i=0; i<results.length; i++)
							listModel.addElement(results[i]);
						
						this.similarityResultsList.setModel(listModel);
	
					}else{
						//drozas: remove the message now (with the new way of browsing it can be annoying)
						//JOptionPane.showMessageDialog(this.mainWindow, "No applications found.", 
						//		"Search", JOptionPane.WARNING_MESSAGE);
					}
				}catch (RemoteException e){
					JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
							"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
					e.printStackTrace();
				}
			
			}else{
				//If it is not an eligible node, clean the results
				this.similarityResultsList.setModel(new DefaultListModel());
			}
		}
		
	}
	
	/**
	 * 
	 * It loads all the information of certain similar application
	 * and shows it in the relevant fields
	 * An error message is shown if there are any connectivity problems with
	 * the back-end.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void loadSimilarApplicationInfo() {
		
		if (this.similarityResultsList.getSelectedIndex()>=0)
		{
			String appId = (String)this.similarityResultsList.getSelectedValue();
			
			try
			{
				this.similarityAppTitle.setText(this.model.getRepositoryApplicationTitle(appId));
				this.similarityAppDescription.setText(this.model.getRepositoryApplicationDescription(appId));
				this.similarityGetButton.setEnabled(true);
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
						"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
				e.printStackTrace();
			}
		}else{
			this.similarityGetButton.setEnabled(false);
		}
		
	}
	
	/**
	 * 
	 * It performs all the checking related to get an application from a similarity 
	 * searching process, and calls the function which is in charge of retrieving and adapting 
	 * the application
	 * 
	 * @author David Rozas
	 * 
	 */
	public void retrieveApplicationFromSimilaritySearch()
	{
		if (this.similarityResultsList.getSelectedValue()!=null)
			this.retrieveApplication((String)this.similarityResultsList.getSelectedValue());
	}
	

	
	
	/////////////////// Share applications //////////////////////
	
	/**
	 * 
	 * It allows the user to share an application.
	 * 
	 * For the moment the user has to select communities and rules which he wants to share,
	 * and a dialog explaining that the OntologyManager services for helping him is shown.
	 * 
	 * 
	 * @param appId	Application to be retrieved
	 * 
	 * @author David Rozas
	 * 
	 */
	private void shareApplication(String appId){
		if (this.model.analyzeApplication(appId)==null)
		{
			String msg = "Ontology Manager services for helping in the application adaptation are not available.\n" +
					"Would you like to share the application " + appId + " and adapt it manually?";
			String title = "Application Adaptation";
			int reply = JOptionPane.showConfirmDialog(this.mainWindow, msg, title, JOptionPane.YES_NO_OPTION);
		    if (reply == JOptionPane.YES_OPTION)
		    {
				
		    	try
			    {
			    	//Create window for sharing application and disable the main one
					this.mainWindow.setEnabled(false);
			    	this.shareWindow = new ShareApplicationWindow();
					this.shareWindow.pack();
					this.shareWindow.setLocationRelativeTo(null);
					this.shareWindow.setVisible(true);
					
					//Set window components
					this.shareAppId.setToolTipText("This is the application identifier which will be stored in the repository");
					this.shareAppId.setText(appId);
					this.shareAppId.setEditable(false);

					this.shareAppDescription.setToolTipText("This is the application identifier which will be stored in the repository.\n (click to edit it)");
					this.shareAppDescription.setText(this.model.getMyApplicationsApplicationDescription(appId));
					this.shareAppDescription.setEditable(true);
					
					this.shareAppCommunitiesTable.setToolTipText("Select the communities in which you want to share this application");
					String[] columnCommunityNames = {"Community","Share in this community"};
					this.shareAppCommunitiesTable.setModel(new ApplicationTableModel(columnCommunityNames, this.model.getCommunitiesList(), this.getCommunitiesData()));
	
					this.shareAppRulesTable.setToolTipText("Select the rules you want to share. \n Click on the identifier to see its description");
					String[] columnRulesNames = {"Rule","Share it?"};
					this.shareAppRulesTable.setModel(new ApplicationTableModel(columnRulesNames, this.model.getRules(appId), this.getRulesData(this.model.getRules(appId))));
					
					String appName = this.model.getMyApplicationsApplicationTitle(this.shareAppId.getText());
				  	  
					this.shareRuleDescriptionLabel.setText("I say " + appName + " when...");
					
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(this.shareWindow, "It was not possible to connect with the Back-End", 
							"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
					e.printStackTrace();
					this.endSharing();
				}

		      
		    }

			
		}else{
			//TODO: To implement once the OntologyManager Interface has been implemented
		}
		
	}
	
	/**
	 * 
	 * Returns a array of objects with the needed information about the communities to be represented
	 * as a table by the JTable
	 * 
	 * @author David Rozas
	 * 
	 */
	public Object[][] getCommunitiesData() throws RemoteException{
		
		String[] communities = this.model.getCommunitiesList();
		Object[][] communitiesData = new Object[communities.length][2];
		for (int i=0; i<communities.length; i++)
		{
			communitiesData[i][0] = this.model.getCommunityName(communities[i]);
			communitiesData[i][1] = new Boolean(true);
		}

	    return communitiesData;
	}
	
	
	/**
	 * 
	 * Performs all the necesary operations once the
	 * sharing process is done/cancelled.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void endSharing(){
		this.shareWindow.dispose();
		this.mainWindow.setEnabled(true);
		//Refresh my applications
		this.loadMyApplications();
	}
	
	/**
	 * 
	 * Performs all the necesary operations to store the application in the
	 * repository.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void exportApplication()
	{
		if (anyCommunitySelected(this.shareAppCommunitiesTable))
		{
			try
			{
				//Export the application and its information
				this.model.createSharedApplication(this.shareAppId.getText(), this.shareAppDescription.getText());
				
				//Export in the selected communities
				for (int i=0; i<this.shareAppCommunitiesTable.getRowCount(); i++)
				{
					if ((Boolean)this.shareAppCommunitiesTable.getValueAt(i, 1))
						this.model.exportInCommunity(this.shareAppId.getText(), ((ApplicationTableModel)this.shareAppCommunitiesTable.getModel()).getIdentifierAt(i));
				}
				
				//Export selected rules
				for (int i=0; i<this.shareAppRulesTable.getRowCount(); i++)
				{
					if ((Boolean)this.shareAppRulesTable.getValueAt(i, 1))
						this.model.exportRule(this.shareAppId.getText(), ((ApplicationTableModel)this.shareAppRulesTable.getModel()).getIdentifierAt(i));
				}
				
				//If everything was ok, proceed to end sharing
				this.endSharing();
				
			}catch(RemoteException e){
				JOptionPane.showMessageDialog(this.shareWindow, "It was not possible to connect with the Back-End", 
						"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
				e.printStackTrace();
				this.endSharing();
			}
			
			
		}else{
			JOptionPane.showMessageDialog(this.shareWindow, "You have to select at least one community", 
					"Adapting application to share", JOptionPane.WARNING_MESSAGE);	
		}
		
		
	}
	
	
	//////////////// Retrieve applications ///////////////////////////////
	
	/**
	 * 
	 * It allows the user to retrieve an application.
	 * 
	 * For the moment the user has to select the rules which he wants to retrieve,
	 * and a dialog explaining that the OntologyManager services for helping him is shown.
	 * 
	 * @param appId	Application to be retrieved
	 */
	private void retrieveApplication(String appId){
		if (this.model.analyzeApplication(appId)==null)
		{
			String msg = "Ontology Manager services for helping in the application adaptation are not available.\n" +
					"Would you like to get the application " + appId + " and adapt it manually?";
			String title = "Application Adaptation";
			int reply = JOptionPane.showConfirmDialog(this.mainWindow, msg, title, JOptionPane.YES_NO_OPTION);
		    if (reply == JOptionPane.YES_OPTION)
		    {
		    	//Create window for retrieving application and disable the main one
		    	try
		    	{
					this.mainWindow.setEnabled(false);
			    	
			    	this.retrieveWindow = new RetrieveApplicationWindow();
					this.retrieveWindow.pack();
					this.retrieveWindow.setLocationRelativeTo(null);
					this.retrieveWindow.setVisible(true);
					
					//Set window components
		
					this.retrieveAppId.setToolTipText("This is the application identifier which will be stored in \"My applications\" ");
					this.retrieveAppId.setText(this.model.changeOwner(appId));
					this.retrieveAppId.setEditable(false);
					
					this.retrieveAppOwner.setToolTipText("This is the user who shared the application");
					this.retrieveAppOwner.setText(this.model.getRepositoryApplicationOwner(appId));
					this.retrieveAppOwner.setEditable(false);
					
					this.retrieveRuleDescLabel.setText("I say " + this.model.getRepositoryApplicationTitle(appId) + " when ...");
	
					this.retrieveAppDescription.setToolTipText("This is the application description which will be stored in \"My applications\" .\n (click to edit it)");
					this.retrieveAppDescription.setText(this.model.getRepositoryApplicationDescription(appId));
					this.retrieveAppDescription.setEditable(true);
			
					this.retrieveAppRulesTable.setToolTipText("Select the rules you want to retrieve.\n Click on the identifier to visualize its description.");
					String[] columnRulesNames = {"Rule","Get it?"};
					//In identifiers we will have the old rule identifiers, and in the first column the new ones
					this.retrieveAppRulesTable.setModel(new ApplicationTableModel(columnRulesNames, this.model.getRulesFromRepository(appId), this.getRulesData(this.model.getRulesFromRepository(appId))));
					
					
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End", 
							"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
					e.printStackTrace();
					//If there was any problem with the connection, reactivate the main window and dispose this one
					this.mainWindow.setEnabled(true);
					this.retrieveWindow.dispose();
				}
		      
		    }

			
		}else{
			//TODO: To implement once the OntologyManager Interface has been discussed
		}
		
	}
	
	/**
	 * 
	 * Performs all the necesary operations once the
	 * retrieving process is done/cancelled.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void endRetrieving(){
		this.retrieveWindow.dispose();
		this.mainWindow.setEnabled(true);
	}
	
	/**
	 * 
	 * Performs all the necesary operations to import the application from the
	 * repository.
	 * 
	 * @author David Rozas
	 * 
	 */
	public void importApplication()
	{
		String appId  = this.retrieveAppId.getText();
		//To create an application in the AAM first we need to call createApplication with its name and owner
		String appName = this.model.getApplicationPart(appId);
		
		//Returns false if the application was already created
		if (this.model.createApplicationInAAM(appName))
		{
			//Set the rest of the data for the application (a set of putData calls)
			this.model.saveApplicationDataInAAM(appId, this.retrieveAppDescription.getText());
			
			//And finally import all the selected rules (changing also into the XML file the owner)
			for (int i=0; i<this.retrieveAppRulesTable.getRowCount(); i++)
			{
				if ((Boolean)this.retrieveAppRulesTable.getValueAt(i, 1))
					//this.model.importRule(appId, this.retrieveAppOwner.getText(),((AdaptedApplicationTableModel)this.retrieveAppRulesTable.getModel()).getIdentifierAt(i), (String)this.retrieveAppRulesTable.getValueAt(i, 0));
					this.model.importRule(appId, this.retrieveAppOwner.getText(),((ApplicationTableModel)this.retrieveAppRulesTable.getModel()).getIdentifierAt(i));

			}
		}else{
			JOptionPane.showMessageDialog(this.retrieveWindow, "You have already retrieved this application (" +appId + ")", 
					"Adapting application to retrieve", JOptionPane.WARNING_MESSAGE);
			
		}
		
		this.endRetrieving();
	}
	
	
	/////////////////////// Rules visualization //////////////////
	
	/**
	 * 
	 * It shows the description of the rule in the application adaptation 
	 * window.
	 * 
	 */
	public void visualizeRemoteRule(){

		try
		{
		  int row = this.retrieveAppRulesTable.getSelectedRow();
		  String ruleId = (String)this.retrieveAppRulesTable.getValueAt(row, 0);
	  	  String ruleDescription = this.model.getRemoteRuleDescription(this.retrieveAppId.getText(), this.retrieveAppOwner.getText(), ruleId);
	
	  	  if (ruleDescription!=null)
	  	  {
		  	  this.retrieveRuleDescription.setText(ruleDescription);
		  	  //To set the scroll up after adding text
		  	  this.retrieveRuleDescription.setCaretPosition(0);
	  	  }
		}catch(RemoteException e)
		{
		  	  this.retrieveRuleDescription.setText("Information not available");
		  	  //To set the scroll up after adding text
		  	  this.retrieveRuleDescription.setCaretPosition(0);
			System.err.println("Error while trying to connect to the Backend to get the rule description.");
		}
  	  

	}  
	
	/**
	 * 
	 * It shows the description of the rule in the application sharing 
	 * window.
	 * 
	 */
	public void visualizeLocalRule(){
	  int row = this.shareAppRulesTable.getSelectedRow();

	  String ruleId = (String)this.shareAppRulesTable.getValueAt(row, 0);
	  String ruleXml = (String)this.model.getMyApplicationsRuleXml(ruleId);
  	  String ruleDescription = this.model.getMyApplicationsRuleDescription(ruleXml);
	  this.shareRuleDescription.setText(ruleDescription);
  	  //To set the scroll up after adding text
  	  this.shareRuleDescription.setCaretPosition(0);

	}  

	
	
	/////////////////////// Help Menu ///////////////////////////
	
	public void showHelpMenu(){
		//Prepare help window and disable the main one
		this.helpWindow = new HelpWindow();
		this.helpWindow.pack();
		this.helpWindow.setLocationRelativeTo(null);
		this.helpWindow.setVisible(true);
		
		this.mainWindow.setEnabled(false);
	}
	
	/**
	 * Returns the remote help content, null if there was any problem.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getHelpContent(){
		try {
			return this.model.getHelpContent();
		} catch (IOException e) {
			// If it was not possible to retrieve the content (ex. network unreachable) display warning
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * Performs all the necesary operations once the
	 * help window is closed
	 * 
	 * @author David Rozas
	 * 
	 */
	public void endHelp(){
		this.helpWindow.dispose();
		this.mainWindow.setEnabled(true);

	}
	
	/////////////// Tags management /////////////////////////
	
	/**
	 * 
	 * Performs all the necessary operations to display the add tag window
	 * 
	 */
	public void showTagWindow(){
		if (this.myApplicationsTree.getLastSelectedPathComponent()!=null)
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)this.myApplicationsTree.getLastSelectedPathComponent();

			if (isEligibleNode(node))
			{
				try
					{
					String appId = node.toString();
					
					this.mainWindow.setEnabled(false);
			    	
			    	this.addTagWindow = new AddTagWindow();
					this.addTagWindow.pack();
					this.addTagWindow.setLocationRelativeTo(null);
					this.addTagWindow.setVisible(true);
					
					//Set window components
					this.addTagAppId.setText(appId);
					this.addTagAppId.setEditable(false);
					
					//Set communities table
					this.addTagCommunitiesTable.setToolTipText("Select the communities in which you want to publish this tag");
					String[] columnCommunityNames = {"Community","Share in it?"};
					this.addTagCommunitiesTable.setModel(new ApplicationTableModel(columnCommunityNames, this.model.getCommunitiesList(), this.getCommunitiesData()));
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(this.addTagWindow, "It was not possible to connect with the Back-End", 
							"Problems with the connection", JOptionPane.ERROR_MESSAGE);	
					e.printStackTrace();
					this.endAddTag();
				}
				
				
			}
		}
	}
	
	/**
	 * 
	 * Performs all the operations to save a tag using the information given by
	 * the user in the addTagWindow.
	 * 
	 */
	public void saveTag(){

		if(!this.addTagName.getText().equals(""))
		{
			try {
				
				boolean valid = true;
				
				if(this.addTagPublicButton.isSelected())
				{
					valid = this.model.savePublicTag(this.addTagName.getText(), this.addTagAppId.getText());
				}else if(this.addTagCommunitiesButton.isSelected()){

					//Save tag in all the selected communities
					if (anyCommunitySelected(this.addTagCommunitiesTable))
					{
						//Export in the selected communities

						int i = 0;
						while(i<this.addTagCommunitiesTable.getRowCount() && valid)
						{
							if ((Boolean)this.addTagCommunitiesTable.getValueAt(i, 1))
							{
								valid = this.model.saveCommunityTag(this.addTagName.getText(), this.addTagAppId.getText(), ((ApplicationTableModel)this.addTagCommunitiesTable.getModel()).getIdentifierAt(i));
							}
							i++;
						}
					}else{
						valid = false;	
					}
				}else if(this.addTagPrivateButton.isSelected()){
					valid = this.model.savePrivateTag(this.addTagName.getText(), this.addTagAppId.getText());
				}
				
				//If it is not valid or duplicated show error and display the same window
				if (!valid){
					JOptionPane.showMessageDialog(this.addTagWindow, "Error while validating the data. Please check: " +
							"\n-The tag is valid. \n-You have not already introduce it. \n-If you are storing a community tag at least choose one community.", 
							"Adding a tag", JOptionPane.ERROR_MESSAGE);
				}else{
					//If not, refresh the list of tags and perform closing operations.
					this.loadTags(this.addTagAppId.getText());
					this.endAddTag();
				}
					
			} catch (RemoteException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.addTagWindow, "It was not possible to connect with the Back-End." +
						"The tag was not stored", "Problems with the connection", JOptionPane.ERROR_MESSAGE);	
				this.endAddTag();
			}
		}else{
			JOptionPane.showMessageDialog(this.addTagWindow, "You have to type a tag before saving it.", 
					"Adding a tag", JOptionPane.WARNING_MESSAGE);
			
		}
		
	}
	
	/**
	 * 
	 * Performs all the necessary operations to delete a tag taking into
	 * account its scope.
	 * 
	 */
	public void deleteTag()
	{
		if (this.myApplicationsAppMyTagsList.getSelectedValue()!=null && this.myApplicationsTree.getSelectionPath()!=null)
		{
			String appId = ((DefaultMutableTreeNode)this.myApplicationsTree.getLastSelectedPathComponent()).toString();
			String tagName = ((TagListItem)this.myApplicationsAppMyTagsList.getSelectedValue()).getValue();
			String tagType = ((TagListItem)this.myApplicationsAppMyTagsList.getSelectedValue()).getType();
			

			
			try 
			{
				boolean valid = false;
				
				if(tagType.equalsIgnoreCase("public")){
					valid = this.model.deletePublicTag(tagName, tagType, appId);
				}else if(tagType.equalsIgnoreCase("private")){
					valid = this.model.deletePrivateTag(tagName, tagType, appId);
				}else if(tagType.equalsIgnoreCase("communities")){
					valid = this.model.deleteCommunityTag(tagName, tagType, appId, ((TagListItem)this.myApplicationsAppMyTagsList.getSelectedValue()).getCommunityId());
				}
				
				if (valid)
				{
					//If everything was correct, reload the tags
					this.loadTags(appId);
	
				}else{
					JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to delete the tag", 
							"Deleting a tag", JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (RemoteException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the back-end", 
						"Deleting a tag", JOptionPane.ERROR_MESSAGE);
			}

			
		}
	}
	
	/**
	 * 
	 * Performs all the necesary operations once the
	 * add tag window is closed
	 * 
	 * @author David Rozas
	 * 
	 */
	public void endAddTag(){
		this.addTagWindow.dispose();
		this.mainWindow.setEnabled(true);

	}
	
	
	////////////// Common auxiliar functions /////////////////////////////
	
	/**
	 * It loads all the public and private tags in the list of tags of MyApplications
	 * 
	 */
	private void loadTags(String appId) throws RemoteException{
		
		((DefaultListModel) this.myApplicationsAppMyTagsList.getModel()).clear();
		
		//Get and render public tags
		String[] publicTags = this.model.getPublicTags(appId);
		if(publicTags!=null)
			for(int i=0; i<publicTags.length; i++)
			{
				TagListItem tagItem = new TagListItem("public", publicTags[i]);
				((DefaultListModel) this.myApplicationsAppMyTagsList.getModel()).addElement(tagItem);
			}
		
		//Get and render community tags
		String[] communities = this.model.getCommunitiesList();
		for(int i=0; i<communities.length; i++)
		{
			String[] communitiesTags = this.model.getTagsByCommunity(appId, communities[i]);
			if(communitiesTags!=null)
				for(int j=0; j<communitiesTags.length; j++)
				{
					TagListItem tagItem = new TagListItem("communities", communitiesTags[j], communities[i], this.model.getCommunityName(communities[i]));
					((DefaultListModel) this.myApplicationsAppMyTagsList.getModel()).addElement(tagItem);
				}
		}
		
		//Get and render private tags
		String[] privateTags = this.model.getPrivateTags(appId);
		if(privateTags!=null)
			for(int i=0; i<privateTags.length; i++)
			{
				TagListItem tagItem = new TagListItem("private", privateTags[i]);
				((DefaultListModel) this.myApplicationsAppMyTagsList.getModel()).addElement(tagItem);
			}
	}
	
	/**
	 * 
	 * It returns the application type, given the node.
	 * It asks the repository of the Awareness Application Manager depending
	 * on the tree's root
	 * 
	 * @param appId	Application Identifier in Astra standard format
	 * @return	Application type if there were no problems, null otherwise
	 * 
	 * @author David Rozas
	 * 
	 */
	public String getApplicationType(DefaultMutableTreeNode node){
		try {
			if(node.getRoot().toString().equalsIgnoreCase(this.model.getRepository_root_name()))
				return this.model.getRepositoryApplicationType(node.toString());
			else
				return this.model.getMyApplicationsApplicationType(node.toString());
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this.mainWindow, "It was not possible to connect with the Back-End while " +
					"trying to retrieve the application type", "Problems with the connection", JOptionPane.ERROR_MESSAGE);	
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * Returns a array of objects with the needed information about the rules to be represented
	 * as a table by the JTable
	 * 
	 * For the moment only the indentifier is displayed (there is no name or description)
	 * 
	 * @author David Rozas
	 * 
	 */
	public Object[][] getRulesData(String[] rules){
		
		//String[] rules = this.model.getRules(appId);
		if (rules!=null)
		{
			Object[][] rulesData = new Object[rules.length][2];
			for (int i=0; i<rules.length; i++)
			{
				//Here we will store the new name of the rule (or the same if it belongs to the
				//same user, as in "share application")
				rulesData[i][0] = this.model.changeOwner(rules[i]);
				rulesData[i][1] = new Boolean(true);
			}
	
		    return rulesData;
		}else{
			return null;
		}
	}
	

	
	/**
	 * 
	 * It returns true if the node is eligible to retrieve information of it.
	 * 
	 * @param node	Node to be analyzed
	 * 
	 * @return	True if the node is eligible, false otherwise
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean isEligibleNode(DefaultMutableTreeNode node){
		
		/*Eligible if:
		- Is leaf and
		- Is not repository/my apps (this covers the case when we have no apps) and
		- Is not repository/my apps child (this cover second level hierarchy when no apps)
		- Is not "by community" child (this covers third level - only necessary in communities)
		*/
		return node!=null && node.isLeaf() && 
		!node.toString().contentEquals(this.model.getRepository_root_name()) &&
		!node.toString().contentEquals(this.model.getMyApplications_root_name()) &&
		!node.getParent().toString().contentEquals(this.model.getRepository_root_name()) &&
		!node.getParent().toString().contentEquals(this.model.getMyApplications_root_name())&&
		!node.getParent().toString().contentEquals(this.model.getCommunitiesRepositoryRoot())&&
		!node.getParent().toString().contentEquals(this.model.getCommunitiesMyApplicationsRoot());
		
	
	}
	
	
	/**
	 * 
	 * It analyzes the communities selected by the user and returns true if at
	 * least one was selected in the given table.
	 * 
	 * @param table	Table to be checked
	 * 
	 * @return	True if any community was selected, false otherwise.
	 * 
	 * @author David Rozas
	 * 
	 */
	private boolean anyCommunitySelected(JTable table)
	{
		boolean any = false;
		int i = 0;
		
		while(i<table.getRowCount() && !any){
			if ((Boolean)table.getValueAt(i, 1))
				any = true;
			i++;
		}
		
		return any;
	}

/////////////// SwingWorker threads //////////////////////
	
	
/**
 * This class extends SwinWorker to allow the creation of threads
 * which loads the Repository applications
 * 	
 */
class RepositoryApplicationsThread extends SwingWorker<DefaultTreeModel, Void>{
		
	/**
	 * 
	 * Process the information of the repository returning a hierarchy tree model
	 * 
	 * @return Hierarchy for the tree
	 * 
	 * @author David Rozas
	 */
	@Override
    public DefaultTreeModel doInBackground() throws RemoteException
    {
		String[] apps = model.getRepositoryApplications();
		String[] myCommunities = model.getMyCommunities();
		
		ApplicationTreeNode rootNode = new ApplicationTreeNode(model.getRepository_root_name());

		
		if (apps.length>0)
		{
			//Process the "all" hierarchy
			ApplicationTreeNode allNode = new ApplicationTreeNode("All");
			
			for(int i=0; i<apps.length; i++)
				allNode.add(new ApplicationTreeNode(apps[i]));
			
			
			//Process the "communities" hierarchy
			ApplicationTreeNode byCommunitiesNode = new ApplicationTreeNode(model.getCommunitiesRepositoryRoot());
			
			for(int i=0; i<myCommunities.length; i++)
			{
				//Create and add community node (by name)
				ApplicationTreeNode com_node = new ApplicationTreeNode(model.getCommunityName(myCommunities[i]));
				byCommunitiesNode.add(com_node);
				
				//For every application visible for this user, check if it has been belong
				//in the current community and add it if so.
				for(int j=0; j<apps.length; j++)
					if(model.isSharedInCommunity(apps[j], myCommunities[i]))
						com_node.add(new ApplicationTreeNode(apps[j]));
						
			}
			
			//Add al the browing categories to the root
			rootNode.add(allNode);
			rootNode.add(byCommunitiesNode);
			
			
			repositoryTree.setToolTipText("Repository's applications (click to select one)");
		}else{
			repositoryTree.setToolTipText("You cannot access to any application in the repository. " +
					"\nHave you joined any community?");
		}
		
//    		//Just for testing
//    		try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		return new DefaultTreeModel(rootNode);
    		
    	
    }

    @Override
    protected void done(){
        DefaultTreeModel treeModel;
		try {
			treeModel = get();
			
	        //Now we perform the UI operations here
			repositoryTree.setModel(treeModel);
			repositoryScrollPanel.setViewportView(repositoryTree);
			repositoryScrollPanel.getViewport().setBackground(Color.WHITE);

		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("Loading repository applications thread has been interrupted");
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.err.println("There were problems during the execution of loading repository applications thread");

		}
    }
}


/**
 * This class extends SwinWorker to allow the creation of threads
 * which loads the local applications in my applications tab
 * 	
 */
class MyApplicationsThread extends SwingWorker<DefaultTreeModel, Void>{
		
	/**
	 * 
	 * Process the information of the repository returning a hierarchy tree model
	 * 
	 * @return Hierarchy for the tree
	 * 
	 * @author David Rozas
	 */
	@Override
    public DefaultTreeModel doInBackground() throws RemoteException
    {
		String[] apps = model.getMyApplications();
		String[] myCommunities = model.getMyCommunities();
		
		//This nodes will be used as DefaultMutableTreeNode (parent class) in the rest of the methods
		ApplicationTreeNode rootNode = new ApplicationTreeNode(model.getMyApplications_root_name());
		
		if(apps.length>0)
		{
			ApplicationTreeNode allNode = new ApplicationTreeNode("All");
			
			for(int i=0; i<apps.length; i++)
				allNode.add(new ApplicationTreeNode(apps[i]));
			
			//Process the "shared in" hierarchy
			ApplicationTreeNode byCommunitiesNode = new ApplicationTreeNode(model.getCommunitiesMyApplicationsRoot());
			
			for(int i=0; i<myCommunities.length; i++)
			{
				//Create and add community node (by name)
				ApplicationTreeNode com_node = new ApplicationTreeNode(model.getCommunityName(myCommunities[i]));
				byCommunitiesNode.add(com_node);
				
				//For every application visible for this user, check if it has been belong
				//in the current community and add it if so.
				for(int j=0; j<apps.length; j++)
					if(model.isAlreadyShared(apps[j]) && model.isSharedInCommunity(apps[j], myCommunities[i]))
						com_node.add(new ApplicationTreeNode(apps[j]));
						
			}
			
			//Add al the browing categories to the root
			rootNode.add(allNode);
			rootNode.add(byCommunitiesNode);
			
			myApplicationsTree.setToolTipText("My applications (click to select one)");
		}else{
			myApplicationsTree.setToolTipText("You have not created any applications. \nWhy do not you create one?");
		}
			
		
		
//		//Just for testing
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return new DefaultTreeModel(rootNode);
    		
    	
    }

    @Override
    protected void done(){
        DefaultTreeModel treeModel;
		try {
			treeModel = get();
			
	        //Now we perform the UI operations here
			myApplicationsTree.setModel(treeModel);
			myApplicationsScrollPanel.setViewportView(myApplicationsTree);
			myApplicationsScrollPanel.getViewport().setBackground(Color.WHITE);

		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("Loading my applications thread has been interrupted");
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.err.println("There were problems during the execution of loading my applications thread");

		}
    }
}


/**
 * This class extends SwinWorker to allow the creation of threads
 * which loads the local applications in the similar applications tree
 * 	
 */
class SimilarApplicationsThread extends SwingWorker<DefaultTreeModel, Void>{
		
	/**
	 * 
	 * Process the information of the repository returning a hierarchy tree model
	 * 
	 * @return Hierarchy for the tree
	 * 
	 * @author David Rozas
	 */
	@Override
    public DefaultTreeModel doInBackground() throws RemoteException
    {
		String[] apps = model.getMyApplications();
		String[] myCommunities = model.getMyCommunities();
		
		//This nodes will be used as DefaultMutableTreeNode (parent class) in the rest of the methods
		ApplicationTreeNode rootNode = new ApplicationTreeNode(model.getMyApplications_root_name());
		
		if(apps.length>0)
		{
			ApplicationTreeNode allNode = new ApplicationTreeNode("All");
			
			for(int i=0; i<apps.length; i++)
				allNode.add(new ApplicationTreeNode(apps[i]));
			
			//Process the "shared in" hierarchy
			ApplicationTreeNode byCommunitiesNode = new ApplicationTreeNode(model.getCommunitiesMyApplicationsRoot());
			
			for(int i=0; i<myCommunities.length; i++)
			{
				//Create and add community node (by name)
				ApplicationTreeNode com_node = new ApplicationTreeNode(model.getCommunityName(myCommunities[i]));
				byCommunitiesNode.add(com_node);
				
				//For every application visible for this user, check if it has been belong
				//in the current community and add it if so.
				for(int j=0; j<apps.length; j++)
					if(model.isAlreadyShared(apps[j]) && model.isSharedInCommunity(apps[j], myCommunities[i]))
						com_node.add(new ApplicationTreeNode(apps[j]));
						
			}
			
			//Add al the browing categories to the root
			rootNode.add(allNode);
			rootNode.add(byCommunitiesNode);
			
			similarityAppsTree.setToolTipText("My applications (click to select one)");
		}else{
			similarityAppsTree.setToolTipText("You have not created any applications. \nWhy do not you create one?");
		}
			
		
		
		//Just for testing
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return new DefaultTreeModel(rootNode);
    		
    	
    }

    @Override
    protected void done(){
        DefaultTreeModel treeModel;
		try {
			treeModel = get();
			
	        //Now we perform the UI operations here
			similarityAppsTree.setModel(treeModel);
			similarityScrollPanel.setViewportView(similarityAppsTree);
			similarityScrollPanel.getViewport().setBackground(Color.WHITE);

		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("Loading similar applications thread has been interrupted");
		} catch (ExecutionException e) {
			e.printStackTrace();
			System.err.println("There were problems during the execution of loading similar applications thread");

		}
    }
}


}
