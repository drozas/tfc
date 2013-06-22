package eu.ist.astra.am.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import eu.ist.astra.am.controller.ApplicationManagerController;

//VS4E -- DO NOT REMOVE THIS LINE!
/**
 * 
 * Some parts of this class are generated automatically using 
 * Visual Swing 4 Eclipse. So, for the moment no javadoc is going to 
 * be added to those elements in order not to interfere with the automatic 
 * code generator.
 * It will be once the GUI is in its final version.
 * 
 * @author David Rozas
 * 
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane mainTabbedPanel;
	private JPanel myApplicationsPanel;
	private JPanel repositoryPanel;
	private JPanel advancedSearch;
	private JTree myApplicationsTree;
	private JScrollPane myApplicationsScrollPanel;
	private JTree repositoryTree;
	private JScrollPane repositoryScrollPanel;
	private JLabel searchLabel;
	private JTextField searchTextField;
	private JComboBox searchComboBox;
	private JList searchList;
	private JScrollPane searchScrollPanel;
	private JButton searchButton;
	private JMenuItem helpMenuItem;
	private JMenuItem exitMenuItem;
	private JMenu jMainMenu;
	private JMenuBar mainMenuBar;
	private JLabel myAppsAppTitleLabel;
	private JTextField myAppsAppTitleTextField;
	private JLabel myAppsAppDescriptionLabel;
	private JTextArea myAppsAppDescriptionTextArea;
	private JScrollPane myAppsAppDescriptionScroll;
	private JLabel myAppsAppMyTagsLabel;
	private JList myAppsAppMyTagsList;
	private JScrollPane myAppsAppMyTagsScroll;
	private JButton myAppsShareButton;
	private JButton myAppsAddTagButton;
	private ButtonGroup buttonGroup1;
	private JLabel repositoryTitleLabel;
	private JTextField repositoryTitleTextField;
	private JTextArea repositoryDescriptionTextArea;
	private JScrollPane repositoryDescriptionScroll;
	private JLabel repositoryDescriptionLabel;
	private JButton repositoryGetButton;
	private JLabel searchAppTitleLabel;
	private JTextField searchAppTitleTextField;
	private JLabel searchAppDescriptionLabel;
	private JTextArea searchAppDescTextArea;
	private JScrollPane searchAppDescScroll;
	private JButton searchGetButton;
	private JButton myAppsDeleteTagButton;
	private JLabel searchTypeLabel;
	private JLabel searchResultLabel;
	
	//Customized application icons
	private ImageIcon nimbusIcon  = createImageIcon("icons/cloud.png");
	private ImageIcon focusIcon = createImageIcon("icons/glasses.png");
	
	//Customized tags scope icons
	private ImageIcon privateIcon = createImageIcon("icons/private.png");
	private ImageIcon publicIcon = createImageIcon("icons/public.png");
	private ImageIcon communitiesIcon = createImageIcon("icons/communities2.png");
	
	//Loading animation
	private ImageIcon loadingAnimation = createImageIcon("img/astra_logo_animated.gif");

	
	
	private ApplicationManagerController controller;
	private JPanel searchBySimilarityPanel;
	private JList similarityResultsList;
	private JScrollPane jScrollPane1;
	private JLabel similarityDescriptionLabel;
	private JLabel similaritySimilarAppsLabel;
	private JTree similarityMyAppsTree;
	private JScrollPane jScrollPane0;
	private JLabel similarityMyAppsLabel;
	private JButton similarityGetButton;
	private JLabel similarityTitleLabel;
	private JTextField similarityAppTitle;
	private JTextArea similarityAppDescription;
	private JScrollPane jScrollPane2;
	private JLabel repositoryTagsLabel;
	private JList repositoryTagsList;
	private JScrollPane repositoryTagsScrollPanel;
	
	private JLabel repositoryLoadingLabel;
	private JLabel myApplicationsLoadingLabel;
	private JLabel similarityLoadingLabel;
	
	public MainWindow(String title) {
		//Get singleton instance of the controller
		this.controller = ApplicationManagerController.getInstance();
		
		initComponents();
		this.setTitle(title);
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getMainTabbedPanel(), new Constraints(new Leading(2, 10, 10), new Trailing(12, 300, 10, 10)));
		addWindowListener(new WindowAdapter() {
	
			public void windowClosing(WindowEvent event) {
				windowWindowClosing(event);
			}
		});
		setJMenuBar(getMainMenuBar());
		initButtonGroup1();
		setSize(608, 343);
	}

	private JLabel getMyApplicationsLoadingLabel() {
		if (myApplicationsLoadingLabel == null) {
			myApplicationsLoadingLabel = new JLabel();
			myApplicationsLoadingLabel.setIcon(this.loadingAnimation);
	        
			//Set a reference in the controller
			this.controller.setMyApplicationsLoadingLabel(myApplicationsLoadingLabel);
		}
		return myApplicationsLoadingLabel;
	}
	
	private JLabel getRepositoryLoadingLabel() {
		if (repositoryLoadingLabel == null) {
			repositoryLoadingLabel = new JLabel();
	        repositoryLoadingLabel.setIcon(this.loadingAnimation);
	        
			//Set a reference in the controller
			this.controller.setRepositoryLoadingLabel(repositoryLoadingLabel);
		}
		return repositoryLoadingLabel;
	}
	
	private JLabel getSimilarityLoadingLabel() {
		if (similarityLoadingLabel == null) {
			similarityLoadingLabel = new JLabel();
			similarityLoadingLabel.setIcon(this.loadingAnimation);
	        
			//Set a reference in the controller
			this.controller.setSimilarityLoadingLabel(similarityLoadingLabel);
		}
		return myApplicationsLoadingLabel;
	}
	
	private JScrollPane getRepositoryTagsScrollPanel() {
		if (repositoryTagsScrollPanel == null) {
			repositoryTagsScrollPanel = new JScrollPane();
			repositoryTagsScrollPanel.setViewportView(getRepositoryTagsList());
		}
		return repositoryTagsScrollPanel;
	}

	private JList getRepositoryTagsList() {
		if (repositoryTagsList == null) {
			repositoryTagsList = new JList();
			DefaultListModel listModel = new DefaultListModel();
			repositoryTagsList.setModel(listModel);
			repositoryTagsList.setEnabled(false);
			
			//Set a reference in the controller
			this.controller.setRepositoryTagsList(repositoryTagsList);
		}
		return repositoryTagsList;
	}

	private JLabel getRepositoryTagsLabel() {
		if (repositoryTagsLabel == null) {
			repositoryTagsLabel = new JLabel();
			repositoryTagsLabel.setText("Tags");
		}
		return repositoryTagsLabel;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getSimilarityAppDescription());
		}
		return jScrollPane2;
	}

	private JTextArea getSimilarityAppDescription() {
		if (similarityAppDescription == null) {
			similarityAppDescription = new JTextArea();
			similarityAppDescription.setText("");
			similarityAppDescription.setEditable(false);
			similarityAppDescription.setLineWrap(true);
			similarityAppDescription.setWrapStyleWord(true);
			
			//Set a reference in the controller
			this.controller.setSimilarityAppDescription(similarityAppDescription);
		}
		return similarityAppDescription;
	}

	private JTextField getSimilarityAppTitle() {
		if (similarityAppTitle == null) {
			similarityAppTitle = new JTextField();
			similarityAppTitle.setText("");
			similarityAppTitle.setEditable(false);
			
			//Set a reference in the controller
			this.controller.setSimilarityAppTitle(similarityAppTitle);
		}
		return similarityAppTitle;
	}

	private JLabel getSimilarityTitleLabel() {
		if (similarityTitleLabel == null) {
			similarityTitleLabel = new JLabel();
			similarityTitleLabel.setText("Title");
		}
		return similarityTitleLabel;
	}

	private JButton getSimilarityGetButton() {
		if (similarityGetButton == null) {
			similarityGetButton = new JButton();
			similarityGetButton.setText("Get");
			similarityGetButton.setEnabled(false);
			
			//Set reference in the controller
			this.controller.setSimilarityGetButton(similarityGetButton);
			
			similarityGetButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					similarityGetButtonMouseMouseClicked(event);
				}
			});
		}
		return similarityGetButton;
	}

	private JLabel getSimilarityMyAppsLabel() {
		if (similarityMyAppsLabel == null) {
			similarityMyAppsLabel = new JLabel();
			similarityMyAppsLabel.setText("My applications");
		}
		return similarityMyAppsLabel;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getSimilarityMyAppsTree());
			
			
			//Set the loading label as the viewport afterwards
			jScrollPane0.setViewportView(getSimilarityMyAppsTree());
			jScrollPane0.setViewportView(getSimilarityLoadingLabel());
			jScrollPane0.getViewport().setBackground(Color.WHITE);
			
			//drozas: set the reference in the controller
			this.controller.setSimilarityScrollPanel(jScrollPane0);
		}
		return jScrollPane0;
	}

	private JTree getSimilarityMyAppsTree() {
		if (similarityMyAppsTree == null) {
			similarityMyAppsTree = new JTree();
			
			//Disabling multiselection
			similarityMyAppsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			//drozas: set the reference in the controller
			this.controller.setSimilarityApplicationsTree(similarityMyAppsTree);
			
	        /* drozas: If the icons were loaded successfully, call the customized renderer.
	         * If there were any problem, the standard ones will be shown, and a message is 
	         * displayed in the standard error output.
	         */
			if (this.nimbusIcon!=null && this.focusIcon!=null)
			{
				similarityMyAppsTree.setCellRenderer(new TreeRenderer(this.nimbusIcon, this.focusIcon, this.controller));

			}else{
				System.err.println("Displaying standard icons");
			}
			similarityMyAppsTree.addTreeSelectionListener(new TreeSelectionListener() {
	
				public void valueChanged(TreeSelectionEvent event) {
					similarityMyAppsTreeTreeSelectionValueChanged(event);
				}
			});
		}
		return similarityMyAppsTree;
	}

	private JLabel getSimilaritySimilarAppsLabel() {
		if (similaritySimilarAppsLabel == null) {
			similaritySimilarAppsLabel = new JLabel();
			similaritySimilarAppsLabel.setText("Similar applications");
		}
		return similaritySimilarAppsLabel;
	}

	private JLabel getSimilarityDescriptionLabel() {
		if (similarityDescriptionLabel == null) {
			similarityDescriptionLabel = new JLabel();
			similarityDescriptionLabel.setText("Description");
		}
		return similarityDescriptionLabel;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getSimilarityResultsList());
		}
		return jScrollPane1;
	}

	private JList getSimilarityResultsList() {
		if (similarityResultsList == null) {
			similarityResultsList = new JList();
			similarityResultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel listModel = new DefaultListModel();
			similarityResultsList.setModel(listModel);
			
			//Reference to the controller
			this.controller.setSimilarityResultsList(similarityResultsList);
			
			similarityResultsList.addListSelectionListener(new ListSelectionListener() {
	
				public void valueChanged(ListSelectionEvent event) {
					similarityResultsListListSelectionValueChanged(event);
				}
			});
		}
		return similarityResultsList;
	}

	private JPanel getSearchBySimilarityPanel() {
		if (searchBySimilarityPanel == null) {
			searchBySimilarityPanel = new JPanel();
			searchBySimilarityPanel.setLayout(new GroupLayout());
			searchBySimilarityPanel.add(getSimilarityGetButton(), new Constraints(new Leading(515, 12, 12), new Leading(244, 12, 12)));
			searchBySimilarityPanel.add(getSimilarityAppTitle(), new Constraints(new Leading(386, 184, 10, 10), new Leading(65, 12, 12)));
			searchBySimilarityPanel.add(getJScrollPane2(), new Constraints(new Leading(384, 188, 12, 12), new Leading(124, 102, 12, 12)));
			searchBySimilarityPanel.add(getSimilarityDescriptionLabel(), new Constraints(new Leading(386, 12, 12), new Leading(102, 12, 12)));
			searchBySimilarityPanel.add(getSimilarityTitleLabel(), new Constraints(new Leading(386, 12, 12), new Leading(41, 12, 12)));
			searchBySimilarityPanel.add(getJScrollPane1(), new Constraints(new Leading(202, 153, 10, 10), new Leading(40, 228, 12, 12)));
			searchBySimilarityPanel.add(getSimilaritySimilarAppsLabel(), new Constraints(new Leading(217, 12, 12), new Leading(19, 12, 12)));
			searchBySimilarityPanel.add(getSimilarityMyAppsLabel(), new Constraints(new Leading(35, 10, 10), new Leading(19, 12, 12)));
			searchBySimilarityPanel.add(getJScrollPane0(), new Constraints(new Leading(11, 162, 10, 10), new Leading(39, 230, 12, 12)));
			searchBySimilarityPanel.addComponentListener(new ComponentAdapter() {
	
				public void componentShown(ComponentEvent event) {
					searchBySimilarityPanelComponentComponentShown(event);
				}
			});
		}
		return searchBySimilarityPanel;
	}

	private JLabel getSearchResultLabel() {
		if (searchResultLabel == null) {
			searchResultLabel = new JLabel();
			searchResultLabel.setText("Results");
		}
		return searchResultLabel;
	}

	private JLabel getSearchTypeLabel() {
		if (searchTypeLabel == null) {
			searchTypeLabel = new JLabel();
			searchTypeLabel.setText("Search type");
		}
		return searchTypeLabel;
	}


	private JButton getMyAppsDeleteTagButton() {
		if (myAppsDeleteTagButton == null) {
			myAppsDeleteTagButton = new JButton();
			myAppsDeleteTagButton.setText("Delete");
			myAppsDeleteTagButton.setEnabled(false);
			myAppsDeleteTagButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					myAppsDeleteTagButtonMouseMouseClicked(event);
				}
			});
			
			//Set a reference in the controller
			this.controller.setMyAppDeleteTagButton(myAppsDeleteTagButton);
		}
		return myAppsDeleteTagButton;
	}

	private JButton getSearchGetButton() {
		if (searchGetButton == null) {
			searchGetButton = new JButton();
			searchGetButton.setText("Get");
			searchGetButton.setEnabled(false);
			searchGetButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					searchGetButtonMouseMouseClicked(event);
				}
			});
			
			//drozas: set the reference in the controller
			this.controller.setSearchGetButton(searchGetButton);
			
		}
		return searchGetButton;
	}

	private JScrollPane getSearchAppDescScroll() {
		if (searchAppDescScroll == null) {
			searchAppDescScroll = new JScrollPane();
			searchAppDescScroll.setViewportView(getSearchAppDescTextArea());
		}
		return searchAppDescScroll;
	}

	private JTextArea getSearchAppDescTextArea() {
		if (searchAppDescTextArea == null) {
			searchAppDescTextArea = new JTextArea();
			searchAppDescTextArea.setEditable(false);
			searchAppDescTextArea.setLineWrap(true);
			searchAppDescTextArea.setWrapStyleWord(true);
			
			//drozas: set the reference in the controller
			this.controller.setSearchAppDescTextArea(searchAppDescTextArea);
			
		}
		return searchAppDescTextArea;
	}

	private JLabel getSearchAppDescriptionLabel() {
		if (searchAppDescriptionLabel == null) {
			searchAppDescriptionLabel = new JLabel();
			searchAppDescriptionLabel.setText("Description");
		}
		return searchAppDescriptionLabel;
	}

	private JTextField getSearchAppTitleTextField() {
		if (searchAppTitleTextField == null) {
			searchAppTitleTextField = new JTextField();
			searchAppTitleTextField.setEditable(false);
			
			//drozas: set the reference in the controller
			this.controller.setSearchApplicationTextField(searchAppTitleTextField);
			
		}
		return searchAppTitleTextField;
	}

	private JLabel getSearchAppTitleLabel() {
		if (searchAppTitleLabel == null) {
			searchAppTitleLabel = new JLabel();
			searchAppTitleLabel.setText("Title");
		}
		return searchAppTitleLabel;
	}

	private JButton getRepositoryGetButton() {
		if (repositoryGetButton == null) {
			repositoryGetButton = new JButton();
			repositoryGetButton.setText("Get");
			
			//drozas: set the reference in the controller
			this.controller.setRepositoryGetButton(repositoryGetButton);
			
			repositoryGetButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					repositoryGetButtonMouseMouseClicked(event);
				}
			});
		}
		return repositoryGetButton;
	}

	private JLabel getRepositoryDescriptionLabel() {
		if (repositoryDescriptionLabel == null) {
			repositoryDescriptionLabel = new JLabel();
			repositoryDescriptionLabel.setText("Description");
		}
		return repositoryDescriptionLabel;
	}

	private JScrollPane getRepositoryDescriptionScroll() {
		if (repositoryDescriptionScroll == null) {
			repositoryDescriptionScroll = new JScrollPane();
			repositoryDescriptionScroll.setViewportView(getRepositoryDescriptionTextArea());
		}
		return repositoryDescriptionScroll;
	}

	private JTextArea getRepositoryDescriptionTextArea() {
		if (repositoryDescriptionTextArea == null) {
			repositoryDescriptionTextArea = new JTextArea();
			repositoryDescriptionTextArea.setEditable(false);
			repositoryDescriptionTextArea.setLineWrap(true);
			repositoryDescriptionTextArea.setWrapStyleWord(true);
			
			
			//drozas: set the reference in the controller
			this.controller.setRepositoryApplicationDescription(repositoryDescriptionTextArea);
		}
		return repositoryDescriptionTextArea;
	}

	private JTextField getRepositoryTitleTextField() {
		if (repositoryTitleTextField == null) {
			repositoryTitleTextField = new JTextField();
			repositoryTitleTextField.setEditable(false);

			//drozas: set the reference in the controller
			this.controller.setRepositoryApplicationTitle(repositoryTitleTextField);
			
		}
		return repositoryTitleTextField;
	}

	private JLabel getRepositoryTitleLabel() {
		if (repositoryTitleLabel == null) {
			repositoryTitleLabel = new JLabel();
			repositoryTitleLabel.setText("Title");
		}
		return repositoryTitleLabel;
	}

	private void initButtonGroup1() {
		buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(getMyAppsShareButton());
	}

	private JButton getMyAppsAddTagButton() {
		if (myAppsAddTagButton == null) {
			myAppsAddTagButton = new JButton();
			myAppsAddTagButton.setText("Add");
			
			//drozas: set the reference in the controller
			this.controller.setMyApplicationsAddTagButton(myAppsAddTagButton);
			
			myAppsAddTagButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					myAppsAddTagButtonMouseMouseClicked(event);
				}
			});
		}
		return myAppsAddTagButton;
	}

	private JButton getMyAppsShareButton() {
		if (myAppsShareButton == null) {
			myAppsShareButton = new JButton();
			myAppsShareButton.setText("Share");
			myAppsShareButton.setEnabled(false);
			
			//drozas: set the reference in the controller
			this.controller.setMyApplicationsButton(myAppsShareButton);
			
			myAppsShareButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					myAppsShareButtonMouseMouseClicked(event);
				}
			});
		}
		return myAppsShareButton;
	}

	private JScrollPane getMyAppsAppMyTagsScroll() {
		if (myAppsAppMyTagsScroll == null) {
			myAppsAppMyTagsScroll = new JScrollPane();
			myAppsAppMyTagsScroll.setViewportView(getMyAppsAppMyTagsList());
		}
		return myAppsAppMyTagsScroll;
	}

	private JList getMyAppsAppMyTagsList() {
		if (myAppsAppMyTagsList == null) {
			myAppsAppMyTagsList = new JList();
			myAppsAppMyTagsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel listModel = new DefaultListModel();
			myAppsAppMyTagsList.setModel(listModel);
			
			
			if (this.privateIcon!=null && this.publicIcon!=null && this.communitiesIcon!=null)
			{
				myAppsAppMyTagsList.setCellRenderer(new TagListCellRenderer(this.publicIcon, this.communitiesIcon, this.privateIcon));

			}else{
				System.err.println("Displaying standard icons");
			}
			
			//drozas: set the reference in the controller
			this.controller.setMyAppMyTagsList(myAppsAppMyTagsList);
			
			
			myAppsAppMyTagsList.addListSelectionListener(new ListSelectionListener() {
	
				public void valueChanged(ListSelectionEvent event) {
					myAppsAppMyTagsListListSelectionValueChanged(event);
				}
			});
		}
		return myAppsAppMyTagsList;
	}

	private JLabel getMyAppsAppMyTagsLabel() {
		if (myAppsAppMyTagsLabel == null) {
			myAppsAppMyTagsLabel = new JLabel();
			myAppsAppMyTagsLabel.setText("My tags");
		}
		return myAppsAppMyTagsLabel;
	}

	private JScrollPane getMyAppsAppDescriptionScroll() {
		if (myAppsAppDescriptionScroll == null) {
			myAppsAppDescriptionScroll = new JScrollPane();
			myAppsAppDescriptionScroll.setViewportView(getMyAppsAppDescriptionTextArea());
		}
		return myAppsAppDescriptionScroll;
	}

	private JTextArea getMyAppsAppDescriptionTextArea() {
		if (myAppsAppDescriptionTextArea == null) {
			myAppsAppDescriptionTextArea = new JTextArea();
			myAppsAppDescriptionTextArea.setEditable(false);
			
			myAppsAppDescriptionTextArea.setLineWrap(true);
			myAppsAppDescriptionTextArea.setWrapStyleWord(true);
			/////////////////////////
			
			//drozas: set the reference in the controller
			this.controller.setMyApplicationsApplicationDescription(myAppsAppDescriptionTextArea);
		}
		return myAppsAppDescriptionTextArea;
	}

	private JLabel getMyAppsAppDescriptionLabel() {
		if (myAppsAppDescriptionLabel == null) {
			myAppsAppDescriptionLabel = new JLabel();
			myAppsAppDescriptionLabel.setText("Description");
		}
		return myAppsAppDescriptionLabel;
	}

	private JTextField getMyAppsAppTitleTextField() {
		if (myAppsAppTitleTextField == null) {
			myAppsAppTitleTextField = new JTextField();
			myAppsAppTitleTextField.setEditable(false);
			
			//drozas: set the reference in the controller
			this.controller.setMyApplicationsApplicationTitle(myAppsAppTitleTextField);
		}
		return myAppsAppTitleTextField;
	}

	private JLabel getMyAppsAppTitleLabel() {
		if (myAppsAppTitleLabel == null) {
			myAppsAppTitleLabel = new JLabel();
			myAppsAppTitleLabel.setText("Title");
		}
		return myAppsAppTitleLabel;
	}

	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getJMainMenu());
		}
		return mainMenuBar;
	}

	private JMenu getJMainMenu() {
		if (jMainMenu == null) {
			jMainMenu = new JMenu();
			jMainMenu.setText("Menu");
			jMainMenu.setOpaque(false);
			jMainMenu.add(getHelpMenuItem());
			jMainMenu.add(getExitMenuItem());
		}
		return jMainMenu;
	}

	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addMouseListener(new MouseAdapter() {
	
				public void mousePressed(MouseEvent event) {
					exitMenuItemMouseMousePressed(event);
				}
			});
		}
		return exitMenuItem;
	}

	private JMenuItem getHelpMenuItem() {
		if (helpMenuItem == null) {
			helpMenuItem = new JMenuItem();
			helpMenuItem.setText("Online help");
			helpMenuItem.addMouseListener(new MouseAdapter() {
	
				public void mousePressed(MouseEvent event) {
					helpMenuItemMouseMousePressed(event);
				}
			});
		}
		return helpMenuItem;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setText("Go!");
			searchButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					searchButtonMouseMouseClicked(event);
				}
			});
			
		}
		return searchButton;
	}

	private JScrollPane getSearchScrollPanel() {
		if (searchScrollPanel == null) {
			searchScrollPanel = new JScrollPane();
			searchScrollPanel.setViewportView(getSearchList());
		}
		return searchScrollPanel;
	}

	private JList getSearchList() {
		if (searchList == null) {
			searchList = new JList();
			searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			DefaultListModel listModel = new DefaultListModel();
			searchList.setModel(listModel);
			searchList.addListSelectionListener(new ListSelectionListener() {
	
				public void valueChanged(ListSelectionEvent event) {
					searchListListSelectionValueChanged(event);
				}
			});
			
			//drozas: set the reference in the controller
			this.controller.setSearchList(searchList);	
		}
		return searchList;
	}

	private JComboBox getSearchComboBox() {
		if (searchComboBox == null) {
			searchComboBox = new JComboBox();
			searchComboBox.setModel(new DefaultComboBoxModel(new Object[] { "any", "by description", "by tags", "by type" }));
			searchComboBox.setDoubleBuffered(false);
			searchComboBox.setBorder(null);
			
			//drozas: set the reference in the controller
			this.controller.setSearchComboBox(searchComboBox);			
			
		}
		return searchComboBox;
	}

	private JTextField getSearchTextField() {
		if (searchTextField == null) {
			searchTextField = new JTextField();
			searchTextField.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					searchTextFieldKeyKeyPressed(event);
				}
			});
			
			//drozas: set the reference in the controller
			this.controller.setSearchTextField(searchTextField);		
		}
		return searchTextField;
	}

	private JLabel getSearchLabel() {
		if (searchLabel == null) {
			searchLabel = new JLabel();
			searchLabel.setText("Keywords");
		}
		return searchLabel;
	}

	private JScrollPane getRepositoryScrollPanel() {
		if (repositoryScrollPanel == null) {
			repositoryScrollPanel = new JScrollPane();
			
			//set both components
			repositoryScrollPanel.setViewportView(getRepositoryTree());
			repositoryScrollPanel.setViewportView(getRepositoryLoadingLabel());
			repositoryScrollPanel.getViewport().setBackground(Color.WHITE);
	
			//drozas: set the reference in the controller
			this.controller.setRepositoryScrollPanel(repositoryScrollPanel);
		}
		return repositoryScrollPanel;
	}

	private JTree getRepositoryTree() {
		if (repositoryTree == null) {
			repositoryTree = new JTree();
			DefaultTreeModel treeModel = null;
			repositoryTree.setModel(treeModel);
			
			//Disabling multiselection
			repositoryTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			
			//drozas: set the reference in the controller
			this.controller.setRepositoryTree(repositoryTree);
			
	        /* drozas: If the icons were loaded successfully, call the customized renderer.
	         * If there were any problem, the standard ones will be shown, and a message is 
	         * displayed in the standard error output.
	         */
			if (this.nimbusIcon!=null && this.focusIcon!=null)
			{
				repositoryTree.setCellRenderer(new TreeRenderer(this.nimbusIcon, this.focusIcon, this.controller));

			}else{
				System.err.println("Displaying standard icons");
			}
			
			repositoryTree.addTreeSelectionListener(new TreeSelectionListener() {

				public void valueChanged(TreeSelectionEvent event) {
					repositoryTreeTreeSelectionValueChanged(event);
				}
			});
		}
		return repositoryTree;
	}

	private JScrollPane getMyApplicationsScrollPanel() {
		if (myApplicationsScrollPanel == null) {
			myApplicationsScrollPanel = new JScrollPane();
			
			//Set the loading label as the viewport afterwards
			myApplicationsScrollPanel.setViewportView(getMyApplicationsTree());
			myApplicationsScrollPanel.setViewportView(getMyApplicationsLoadingLabel());
			myApplicationsScrollPanel.getViewport().setBackground(Color.WHITE);
			
			//drozas: set the reference in the controller
			this.controller.setMyApplicationsScrollPanel(myApplicationsScrollPanel);
			
		}
		return myApplicationsScrollPanel;
	}

	private JTree getMyApplicationsTree() {
		if (myApplicationsTree == null) {
			myApplicationsTree = new JTree();
			
			DefaultTreeModel treeModel = null;
			myApplicationsTree.setModel(treeModel);
			
			//Disabling multiselection
			myApplicationsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			//drozas: set the reference in the controller
			this.controller.setMyApplicationsTree(myApplicationsTree);
			
	        /* drozas: If the icons were loaded successfully, call the customized renderer.
	         * If there were any problem, the standard ones will be shown, and a message is 
	         * displayed in the standard error output.
	         */
			if (this.nimbusIcon!=null && this.focusIcon!=null)
			{
				myApplicationsTree.setCellRenderer(new TreeRenderer(this.nimbusIcon, this.focusIcon, this.controller));

			}else{
				System.err.println("Displaying standard icons");
			}
			
			
			
			myApplicationsTree.addTreeSelectionListener(new TreeSelectionListener() {
	
				public void valueChanged(TreeSelectionEvent event) {
					myApplicationsTreeTreeSelectionValueChanged(event);
				}
			});
		}
		return myApplicationsTree;
	}

	private JPanel getAdvancedSearch() {
		if (advancedSearch == null) {
			advancedSearch = new JPanel();
			advancedSearch.setLayout(new GroupLayout());
			advancedSearch.add(getSearchAppTitleLabel(), new Constraints(new Leading(305, 10, 10), new Leading(27, 12, 12)));
			advancedSearch.add(getSearchAppDescriptionLabel(), new Constraints(new Leading(305, 12, 12), new Leading(79, 12, 12)));
			advancedSearch.add(getSearchAppDescScroll(), new Constraints(new Leading(304, 258, 12, 12), new Leading(105, 104, 12, 12)));
			advancedSearch.add(getSearchGetButton(), new Constraints(new Leading(507, 12, 12), new Trailing(12, 139, 221)));
			advancedSearch.add(getSearchAppTitleTextField(), new Constraints(new Leading(303, 259, 12, 12), new Leading(48, 12, 12)));
			advancedSearch.add(getSearchScrollPanel(), new Constraints(new Leading(18, 207, 10, 10), new Leading(132, 135, 10, 10)));
			advancedSearch.add(getSearchLabel(), new Constraints(new Leading(20, 10, 10), new Leading(54, 12, 12)));
			advancedSearch.add(getSearchButton(), new Constraints(new Leading(165, 56, 12, 12), new Leading(81, 23, 12, 12)));
			advancedSearch.add(getSearchTextField(), new Constraints(new Leading(85, 136, 12, 12), new Leading(48, 23, 12, 12)));
			advancedSearch.add(getSearchResultLabel(), new Constraints(new Leading(20, 12, 12), new Leading(110, 12, 12)));
			advancedSearch.add(getSearchComboBox(), new Constraints(new Leading(99, 122, 10, 10), new Leading(12, 12, 12)));
			advancedSearch.add(getSearchTypeLabel(), new Constraints(new Leading(20, 73, 12, 12), new Leading(16, 12, 12)));
		}
		return advancedSearch;
	}

	private JPanel getRepositoryPanel() {
		if (repositoryPanel == null) {
			repositoryPanel = new JPanel();
			repositoryPanel.setLayout(new GroupLayout());
			//add it in the panel
			//repositoryPanel.add(getRepositoryLoadingLabel(), new Constraints(new Leading(0, 150, 0, 0), new Bilateral(0, 0, 0)));
			repositoryPanel.add(getRepositoryScrollPanel(), new Constraints(new Leading(5, 150, 10, 10), new Bilateral(12, 12, 22)));
			repositoryPanel.add(getRepositoryTitleLabel(), new Constraints(new Leading(181, 10, 10), new Leading(12, 136, 132)));
			repositoryPanel.add(getRepositoryDescriptionLabel(), new Constraints(new Leading(181, 12, 12), new Leading(65, 10, 10)));
			repositoryPanel.add(getRepositoryGetButton(), new Constraints(new Trailing(12, 39, 167), new Leading(241, 10, 10)));
			repositoryPanel.add(getRepositoryTitleTextField(), new Constraints(new Leading(181, 354, 12, 12), new Leading(36, 10, 10)));
			repositoryPanel.add(getRepositoryTagsLabel(), new Constraints(new Leading(181, 12, 12), new Leading(146, 12, 12)));
			repositoryPanel.add(getRepositoryDescriptionScroll(), new Constraints(new Leading(181, 353, 10, 10), new Leading(89, 50, 10, 10)));
			repositoryPanel.add(getRepositoryTagsScrollPanel(), new Constraints(new Leading(183, 134, 10, 10), new Bilateral(167, 12, 22)));
			repositoryPanel.addComponentListener(new ComponentAdapter() {
	
				public void componentShown(ComponentEvent event) {
					repositoryPanelComponentComponentShown(event);
				}
			});
		}
		return repositoryPanel;
	}

	private JPanel getMyApplicationsPanel() {
		if (myApplicationsPanel == null) {
			myApplicationsPanel = new JPanel();
			myApplicationsPanel.setLayout(new GroupLayout());
			myApplicationsPanel.add(getMyApplicationsScrollPanel(), new Constraints(new Leading(5, 150, 10, 10), new Bilateral(12, 12, 22)));
			myApplicationsPanel.add(getMyAppsAppTitleLabel(), new Constraints(new Leading(180, 10, 10), new Leading(12, 12, 12)));
			myApplicationsPanel.add(getMyAppsAppTitleTextField(), new Constraints(new Leading(180, 347, 10, 10), new Leading(33, 12, 12)));
			myApplicationsPanel.add(getMyAppsAppDescriptionLabel(), new Constraints(new Leading(180, 12, 12), new Leading(64, 12, 12)));
			myApplicationsPanel.add(getMyAppsAppDescriptionScroll(), new Constraints(new Leading(180, 344, 10, 10), new Leading(85, 50, 10, 10)));
			myApplicationsPanel.add(getMyAppsAppMyTagsLabel(), new Constraints(new Leading(180, 12, 12), new Leading(141, 12, 12)));
			myApplicationsPanel.add(getMyAppsAddTagButton(), new Constraints(new Leading(182, 88, 12, 12), new Leading(239, 12, 12)));
			myApplicationsPanel.add(getMyAppsDeleteTagButton(), new Constraints(new Leading(276, 88, 12, 12), new Leading(239, 12, 12)));
			myApplicationsPanel.add(getMyAppsAppMyTagsScroll(), new Constraints(new Leading(182, 182, 12, 12), new Leading(160, 73, 10, 10)));
			myApplicationsPanel.add(getMyAppsShareButton(), new Constraints(new Leading(510, 71, 10, 10), new Leading(239, 12, 12)));
			myApplicationsPanel.addComponentListener(new ComponentAdapter() {
	
				public void componentShown(ComponentEvent event) {
					myApplicationsPanelComponentComponentShown(event);
				}
			});
		}
		return myApplicationsPanel;
	}

	private JTabbedPane getMainTabbedPanel() {
		if (mainTabbedPanel == null) {
			mainTabbedPanel = new JTabbedPane();
			mainTabbedPanel.addTab("My applications", null, getMyApplicationsPanel(), "Browse, tag, share, etc. already installed applications");
			mainTabbedPanel.addTab("Repository", null, getRepositoryPanel(), "Browse the repository");
			mainTabbedPanel.addTab("Search", null, getAdvancedSearch(), "Search by different criteria (description, tags, type, etc.)");
			mainTabbedPanel.addTab("Search by similarity", null, getSearchBySimilarityPanel(), "Search for similar applications");
		}
		return mainTabbedPanel;
	}

	/**
	 * 
	 * It calls the controller to add a tag when the add tag button is pressed.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 * 
	 */
	private void myAppsAddTagButtonMouseMouseClicked(MouseEvent event) {
		this.controller.showTagWindow();
	}

	/**
	 * 
	 * It calls the controller to load all the applications in the repository tree
	 *  when the repository panel is shown.
	 * 
	 * @param event
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	private void repositoryPanelComponentComponentShown(ComponentEvent event) {
		this.controller.loadRepositoryApplications();
	}
	
	/**
	 * 
	 * It calls the controller to load all the applications in my applications tree
	 *  when that panel is shown.
	 * 
	 * @param event
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	private void myApplicationsPanelComponentComponentShown(ComponentEvent event) {
		this.controller.loadMyApplications();
	}

	/**
	 * 
	 * It calls the controller to load all the information related to
	 * certain application when the user is browsing through the tree
	 * 
	 * @param event
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	private void repositoryTreeTreeSelectionValueChanged(TreeSelectionEvent event) {
		this.controller.loadRepositoryApplicationInfo();
	}

	/**
	 * 
	 * It calls the controller to retrieve (and adapt) an application selected
	 * by the user.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void repositoryGetButtonMouseMouseClicked(MouseEvent event) {
		this.controller.retrieveApplicationFromTree();
	}
	
	/**
	 * 
	 * Call the controller to perform search by criteria
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void searchButtonMouseMouseClicked(MouseEvent event) {
		this.controller.search();
	}

	/**
	 * 
	 * Call the controller to load the information about an application
	 * in the search by criteria tab
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void searchListListSelectionValueChanged(ListSelectionEvent event) {
		this.controller.loadSearchApplicationInfo();
	}

	/**
	 * 
	 * Call the controller to perform search by criteria
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void searchTextFieldKeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.search();
	}

	/**
	 * 
	 * Call the controller to retrieve an application selected in 
	 * search by criteria tab
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void searchGetButtonMouseMouseClicked(MouseEvent event) {
		if (searchGetButton.isEnabled())
			this.controller.retrieveApplicationFromSearch();
	}

	/**
	 * 
	 * Call the controller to display information about an application
	 * selected in "My applications"
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void myApplicationsTreeTreeSelectionValueChanged(TreeSelectionEvent event) {
		this.controller.loadMyApplicationsApplicationInfo();
	}

	/**
	 * 
	 * Call the controller to load all the similar applications
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void searchBySimilarityPanelComponentComponentShown(ComponentEvent event) {
		this.controller.loadSimilarApplications();
		
	}

	/**
	 * 
	 * Call the controller to perform a search by similarity
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void similarityMyAppsTreeTreeSelectionValueChanged(TreeSelectionEvent event) {
		this.controller.searchBySimilarity();
	}

	/**
	 * 
	 * Call the controller to load information about a similar application.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void similarityResultsListListSelectionValueChanged(ListSelectionEvent event) {
		this.controller.loadSimilarApplicationInfo();
	}

	/**
	 * 
	 * Call the controller to retrieve an application from similarity search
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void similarityGetButtonMouseMouseClicked(MouseEvent event) {
		if (similarityGetButton.isEnabled())
			this.controller.retrieveApplicationFromSimilaritySearch();
	}

	/**
	 * 
	 * Call the controller to share an application.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void myAppsShareButtonMouseMouseClicked(MouseEvent event) {
		if (this.myAppsShareButton.isEnabled())
			this.controller.shareApplicationFromTree();
	}

	/**
	 * 
	 * Call the controller to logout
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void exitMenuItemMouseMousePressed(MouseEvent event) {
		this.controller.logout();
	}

	/**
	 * 
	 * Call the controller to logout
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void windowWindowClosing(WindowEvent event) {
		this.controller.logout();
	}

	/**
	 * 
	 * Call the controller to display remote help.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void helpMenuItemMouseMousePressed(MouseEvent event) {
		this.controller.showHelpMenu();
	}

	/**
	 * 
	 * Enables tag button if any tag selected
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void myAppsAppMyTagsListListSelectionValueChanged(ListSelectionEvent event) {
		//Enable button if a tag is selected
		if (this.myAppsAppMyTagsList.getSelectedIndex()>=0)
			this.myAppsDeleteTagButton.setEnabled(true);
	}

	/**
	 * 
	 * Call the controller to delete a tag.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void myAppsDeleteTagButtonMouseMouseClicked(MouseEvent event) {
		if (this.myAppsDeleteTagButton.isEnabled()==true)
			this.controller.deleteTag();
	}

	/**
	 * 
	 * It reds a file in array of bytes format.
	 * 
	 * @param inputStream 
	 * 
	 * @return a file in array of bytes format.
	 * 
	 * @author Alfredo Perez
	 * 
	 */
	public static byte[] getImageAsArray(InputStream inputStream)
	{
		
	        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                  
  		  
  		  int abyte;
  		  while(true)
		  {
			try
			{
				abyte=inputStream.read();
				

				if (abyte== -1)
					break;
					
				baos.write(abyte);
			}
			catch(Exception e1)
			{
				break;
			}
		   }//while

		   return baos.toByteArray();
		


	}

	/**
	 * 
	 * It returns an ImageIcon given its path.
	 * 
	 * @param str	ImageIcon path
	 * @return	ImageIcon if there were no problems, null otherwise.
	 * 
	 * @author Alfredo Perez
	 * 
	 */
	public ImageIcon createImageIcon(String str){
		ClassLoader cl = this.getClass().getClassLoader(); 
		InputStream is=cl.getResourceAsStream(str);
		if(is==null){
			try{
				 is=new FileInputStream((new File(str)));
				}catch (Exception e){
					return null;
				}
		}
		byte imageBytes[] = getImageAsArray(is);

		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(imageBytes));
			 
	}


}
