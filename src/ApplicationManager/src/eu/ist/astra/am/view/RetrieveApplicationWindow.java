package eu.ist.astra.am.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import eu.ist.astra.am.controller.ApplicationManagerController;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RetrieveApplicationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel retrieveAppIdLabel;
	private JLabel retrieveAppDescLabel;
	private JTextField retrieveAppIdTextBox;
	private JTextArea retrieveAppDescTextArea;
	private JScrollPane jScrollPane0;
	private JScrollPane retrieveAppRulesPanel;
	private JTable retrieveAppRulesTable;
	private JLabel retrieveAppRulesLabel;
	private JButton retrieveAppCancelButton;
	private JButton retrieveAppOkButton;

	private ApplicationManagerController controller;
	private JLabel retrieveAppOwnerLabel;
	private JTextField retrieveAppOwnerTextField;
	private JLabel retrieveRuleDescriptionLabel;
	private JTextArea retrieveRuleDescriptionTextArea;
	private JScrollPane retrieveRuleDescriptionScrollPanel;
	
	public RetrieveApplicationWindow() {
		//drozas: Get singleton instance of the controller
		this.controller = ApplicationManagerController.getInstance();
		
		initComponents();

	}

	private void initComponents() {
		setTitle("Application adaptation");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new GroupLayout());
		add(getRetrieveAppIdTextBox(), new Constraints(new Leading(96, 224, 12, 12), new Leading(10, 12, 12)));
		add(getRetrieveAppIdLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getRetrieveAppRulesLabel(), new Constraints(new Leading(13, 12, 12), new Leading(130, 12, 12)));
		add(getRetrieveAppRulesPanel(), new Constraints(new Leading(10, 311, 12, 12), new Leading(151, 87, 12, 12)));
		add(getRetrieveAppOwnerLabel(), new Constraints(new Leading(12, 12, 12), new Leading(43, 12, 12)));
		add(getRetrieveAppOwnerTextField(), new Constraints(new Leading(97, 222, 12, 12), new Leading(41, 12, 12)));
		add(getRetrieveAppDescLabel(), new Constraints(new Leading(12, 12, 12), new Leading(76, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(98, 220, 12, 12), new Leading(79, 44, 12, 12)));
		add(getRetrieveAppOkButton(), new Constraints(new Trailing(12, 73, 250, 250), new Leading(350, 12, 12)));
		add(getRetrieveAppCancelButton(), new Constraints(new Trailing(91, 12, 12), new Leading(350, 12, 12)));
		add(getRetrieveRuleDescriptionLabel(), new Constraints(new Leading(12, 12, 12), new Leading(244, 12, 12)));
		add(getRetrieveRuleDescriptionScrollPanel(), new Constraints(new Leading(11, 308, 12, 12), new Leading(264, 80, 12, 12)));
		addWindowListener(new WindowAdapter() {
	
			public void windowClosing(WindowEvent event) {
				windowWindowClosing(event);
			}
		});
		setSize(329, 382);
	}

	private JScrollPane getRetrieveRuleDescriptionScrollPanel() {
		if (retrieveRuleDescriptionScrollPanel == null) {
			retrieveRuleDescriptionScrollPanel = new JScrollPane();
			retrieveRuleDescriptionScrollPanel.setViewportView(getRetrieveRuleDescriptionTextArea());
		}
		return retrieveRuleDescriptionScrollPanel;
	}

	private JTextArea getRetrieveRuleDescriptionTextArea() {
		if (retrieveRuleDescriptionTextArea == null) {
			retrieveRuleDescriptionTextArea = new JTextArea();
			
			retrieveRuleDescriptionTextArea.setEditable(false);
			retrieveRuleDescriptionTextArea.setLineWrap(true);
			retrieveRuleDescriptionTextArea.setWrapStyleWord(true);
			
			//drozas: Assign here to the controller
			this.controller.setRetrieveWindowRuleDesc(retrieveRuleDescriptionTextArea);
		}
		return retrieveRuleDescriptionTextArea;
	}

	private JLabel getRetrieveRuleDescriptionLabel() {
		if (retrieveRuleDescriptionLabel == null) {
			retrieveRuleDescriptionLabel = new JLabel();
			
			//drozas: Assign here to the controller
			this.controller.setRetrieveRuleDescLabel(retrieveRuleDescriptionLabel);
		}
		return retrieveRuleDescriptionLabel;
	}

	private JTextField getRetrieveAppOwnerTextField() {
		if (retrieveAppOwnerTextField == null) {
			retrieveAppOwnerTextField = new JTextField();
			
			//drozas: Assign here to the controller
			this.controller.setRetrieveWindowAppOwner(retrieveAppOwnerTextField);
		}
		return retrieveAppOwnerTextField;
	}

	private JLabel getRetrieveAppOwnerLabel() {
		if (retrieveAppOwnerLabel == null) {
			retrieveAppOwnerLabel = new JLabel();
			retrieveAppOwnerLabel.setText("Shared by:");
		}
		return retrieveAppOwnerLabel;
	}

	private JButton getRetrieveAppOkButton() {
		if (retrieveAppOkButton == null) {
			retrieveAppOkButton = new JButton();
			retrieveAppOkButton.setText("Ok");
			retrieveAppOkButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					retrieveAppOkButtonMouseMouseClicked(event);
				}
			});
		}
		return retrieveAppOkButton;
	}

	private JButton getRetrieveAppCancelButton() {
		if (retrieveAppCancelButton == null) {
			retrieveAppCancelButton = new JButton();
			retrieveAppCancelButton.setText("Cancel");
			retrieveAppCancelButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					retrieveAppCancelButtonMouseMouseClicked(event);
				}
			});
		}
		return retrieveAppCancelButton;
	}

	private JLabel getRetrieveAppRulesLabel() {
		if (retrieveAppRulesLabel == null) {
			retrieveAppRulesLabel = new JLabel();
			retrieveAppRulesLabel.setText("Rules:");
		}
		return retrieveAppRulesLabel;
	}

	private JTable getRetrieveAppRulesTable() {
		if (retrieveAppRulesTable == null) {
			retrieveAppRulesTable = new JTable();
			//drozas: Assign here to the controller
			this.controller.setRetrieveWindowRulesTable(retrieveAppRulesTable);
			
			retrieveAppRulesTable.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent event) {
					retrieveAppRulesPanelMouseMouseClicked(event);
				}
			});
		}
		return retrieveAppRulesTable;
	}

	private JScrollPane getRetrieveAppRulesPanel() {
		if (retrieveAppRulesPanel == null) {
			retrieveAppRulesPanel = new JScrollPane();
			retrieveAppRulesPanel.setViewportView(getRetrieveAppRulesTable());

		}
		return retrieveAppRulesPanel;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getRetrieveAppDescTextArea());
		}
		return jScrollPane0;
	}

	private JTextArea getRetrieveAppDescTextArea() {
		if (retrieveAppDescTextArea == null) {
			retrieveAppDescTextArea = new JTextArea();
			retrieveAppDescTextArea.setEditable(true);
			retrieveAppDescTextArea.setLineWrap(true);
			retrieveAppDescTextArea.setWrapStyleWord(true);
			
			//drozas: Assign here to the controller
			this.controller.setRetrieveWindowAppDesc(retrieveAppDescTextArea);
		}
		return retrieveAppDescTextArea;
	}

	private JTextField getRetrieveAppIdTextBox() {
		if (retrieveAppIdTextBox == null) {
			retrieveAppIdTextBox = new JTextField();
			//drozas: Assign here to the controller
			this.controller.setRetrieveWindowAppId(retrieveAppIdTextBox);
		}
		return retrieveAppIdTextBox;
	}

	private JLabel getRetrieveAppDescLabel() {
		if (retrieveAppDescLabel == null) {
			retrieveAppDescLabel = new JLabel();
			retrieveAppDescLabel.setText("Description:");
		}
		return retrieveAppDescLabel;
	}

	private JLabel getRetrieveAppIdLabel() {
		if (retrieveAppIdLabel == null) {
			retrieveAppIdLabel = new JLabel();
			retrieveAppIdLabel.setText("Identifier:");
		}
		return retrieveAppIdLabel;
	}

	/**
	 * 
	 * Call the controller to cancel the retrieving.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void retrieveAppCancelButtonMouseMouseClicked(MouseEvent event) {
		this.controller.endRetrieving();
	}

	/**
	 * 
	 * Call the controller to cancel the retrieving.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void windowWindowClosing(WindowEvent event) {
		this.controller.endRetrieving();
	}

	/**
	 * 
	 * Call the controller to import the application
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void retrieveAppOkButtonMouseMouseClicked(MouseEvent event) {
		this.controller.importApplication();
	}

	/**
	 * 
	 * Call the controller to display the rule description
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void retrieveAppRulesPanelMouseMouseClicked(MouseEvent event) {
		this.controller.visualizeRemoteRule();
	
	}


}
