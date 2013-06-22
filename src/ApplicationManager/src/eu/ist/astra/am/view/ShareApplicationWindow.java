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
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import eu.ist.astra.am.controller.ApplicationManagerController;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ShareApplicationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel shareAppLabel;
	private JLabel shareAppDescLabel;
	private JTextField shareAppIdTextBox;
	private JTextArea shareAppDescTextArea;
	private JScrollPane jScrollPane0;
	private JScrollPane shareAppCommunitiesScrollPanel;
	private JTable shareAppCommunitiesTable;
	private JScrollPane shareAppRulesPanel;
	private JTable shareAppRulesTable;
	private JLabel shareAppCommunitiesLabel;
	private JLabel shareAppRulesLabel;
	private JButton shareAppCancelButton;
	private JButton shareAppOkButton;

	private ApplicationManagerController controller;
	private JLabel shareAppRuleDescLabel;
	private JTextArea shareAppRuleDescTextArea;
	private JScrollPane shareAppRuleDescScrollPanel;
	
	public ShareApplicationWindow() {
		//drozas: Get singleton instance of the controller
		this.controller = ApplicationManagerController.getInstance();
		
		initComponents();

	}

	private void initComponents() {
		setTitle("Sharing application");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new GroupLayout());
		add(getShareAppRulesPanel(), new Constraints(new Leading(10, 311, 10, 10), new Leading(226, 87, 12, 12)));
		add(getShareAppCommunitiesScrollPanel(), new Constraints(new Leading(13, 308, 12, 12), new Leading(112, 87, 12, 12)));
		add(getJScrollPane0(), new Constraints(new Leading(96, 225, 10, 10), new Leading(41, 44, 10, 10)));
		add(getShareAppIdTextBox(), new Constraints(new Leading(96, 224, 12, 12), new Leading(10, 12, 12)));
		add(getShareAppCommunitiesLabel(), new Constraints(new Leading(12, 12, 12), new Leading(88, 14, 10, 10)));
		add(getShareAppRulesLabel(), new Constraints(new Leading(12, 12, 12), new Leading(205, 12, 12)));
		add(getShareAppDescLabel(), new Constraints(new Leading(13, 12, 12), new Leading(37, 10, 10)));
		add(getShareAppLabel(), new Constraints(new Leading(16, 10, 10), new Leading(12, 12, 12)));
		add(getShareAppRuleDescLabel(), new Constraints(new Leading(10, 12, 12), new Leading(319, 12, 12)));
		add(getShareAppCancelButton(), new Constraints(new Leading(167, 12, 12), new Leading(427, 10, 10)));
		add(getShareAppOkButton(), new Constraints(new Leading(247, 73, 12, 12), new Leading(427, 12, 12)));
		add(getShareAppRuleDescScrollPanel(), new Constraints(new Bilateral(11, 12, 22), new Leading(342, 75, 10, 10)));
		addWindowListener(new WindowAdapter() {
	
			public void windowClosing(WindowEvent event) {
				windowWindowClosing(event);
			}
		});
		setSize(329, 457);
	}

	private JScrollPane getShareAppRuleDescScrollPanel() {
		if (shareAppRuleDescScrollPanel == null) {
			shareAppRuleDescScrollPanel = new JScrollPane();
			shareAppRuleDescScrollPanel.setViewportView(getShareAppRuleDescTextArea());
		}
		return shareAppRuleDescScrollPanel;
	}

	private JTextArea getShareAppRuleDescTextArea() {
		if (shareAppRuleDescTextArea == null) {
			shareAppRuleDescTextArea = new JTextArea();
			
			shareAppRuleDescTextArea.setEditable(false);
			shareAppRuleDescTextArea.setLineWrap(true);
			shareAppRuleDescTextArea.setWrapStyleWord(true);
			
			//drozas: Assign here to the controller
			this.controller.setShareWindowRuleDesc(shareAppRuleDescTextArea);
		}
		return shareAppRuleDescTextArea;
	}

	private JLabel getShareAppRuleDescLabel() {
		if (shareAppRuleDescLabel == null) {
			shareAppRuleDescLabel = new JLabel();
			//drozas: Assign here to the controller
			this.controller.setShareWindowRuleDescLabel(shareAppRuleDescLabel);
		}
		return shareAppRuleDescLabel;
	}

	private JButton getShareAppOkButton() {
		if (shareAppOkButton == null) {
			shareAppOkButton = new JButton();
			shareAppOkButton.setText("Ok");
			shareAppOkButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					shareAppOkButtonMouseMouseClicked(event);
				}
			});
		}
		return shareAppOkButton;
	}

	private JButton getShareAppCancelButton() {
		if (shareAppCancelButton == null) {
			shareAppCancelButton = new JButton();
			shareAppCancelButton.setText("Cancel");
			shareAppCancelButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					shareAppCancelButtonMouseMouseClicked(event);
				}
			});
		}
		return shareAppCancelButton;
	}

	private JLabel getShareAppRulesLabel() {
		if (shareAppRulesLabel == null) {
			shareAppRulesLabel = new JLabel();
			shareAppRulesLabel.setText("Rules:");
		}
		return shareAppRulesLabel;
	}

	private JLabel getShareAppCommunitiesLabel() {
		if (shareAppCommunitiesLabel == null) {
			shareAppCommunitiesLabel = new JLabel();
			shareAppCommunitiesLabel.setText("Communities:");
		}
		return shareAppCommunitiesLabel;
	}

	private JTable getShareAppRulesTable() {
		if (shareAppRulesTable == null) {
			shareAppRulesTable = new JTable();
			shareAppRulesTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] {};
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			shareAppRulesTable.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					shareAppRulesTableMouseMouseClicked(event);
				}
			});
			
			//drozas: Assign here to the controller
			this.controller.setShareWindowRulesTable(shareAppRulesTable);
			
		}
		return shareAppRulesTable;
	}

	private JScrollPane getShareAppRulesPanel() {
		if (shareAppRulesPanel == null) {
			shareAppRulesPanel = new JScrollPane();
			shareAppRulesPanel.setViewportView(getShareAppRulesTable());
		}
		return shareAppRulesPanel;
	}

	private JTable getShareAppCommunitiesTable() {
		if (shareAppCommunitiesTable == null) {
			shareAppCommunitiesTable = new JTable();
			
			//drozas: Assign here to the controller
			this.controller.setShareWindowCommunitiesTable(shareAppCommunitiesTable);
		}
		return shareAppCommunitiesTable;
	}

	private JScrollPane getShareAppCommunitiesScrollPanel() {
		if (shareAppCommunitiesScrollPanel == null) {
			shareAppCommunitiesScrollPanel = new JScrollPane();
			shareAppCommunitiesScrollPanel.setViewportView(getShareAppCommunitiesTable());
		}
		return shareAppCommunitiesScrollPanel;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getShareAppDescTextArea());
		}
		return jScrollPane0;
	}

	private JTextArea getShareAppDescTextArea() {
		if (shareAppDescTextArea == null) {
			shareAppDescTextArea = new JTextArea();
			shareAppDescTextArea.setEditable(true);
			shareAppDescTextArea.setLineWrap(true);
			shareAppDescTextArea.setWrapStyleWord(true);
			
			//drozas: Assign here to the controller
			this.controller.setShareWindowAppDesc(shareAppDescTextArea);
		}
		return shareAppDescTextArea;
	}

	private JTextField getShareAppIdTextBox() {
		if (shareAppIdTextBox == null) {
			shareAppIdTextBox = new JTextField();
			//drozas: Assign here to the controller
			this.controller.setShareWindowAppId(shareAppIdTextBox);
		}
		return shareAppIdTextBox;
	}

	private JLabel getShareAppDescLabel() {
		if (shareAppDescLabel == null) {
			shareAppDescLabel = new JLabel();
			shareAppDescLabel.setText("Description:");
		}
		return shareAppDescLabel;
	}

	private JLabel getShareAppLabel() {
		if (shareAppLabel == null) {
			shareAppLabel = new JLabel();
			shareAppLabel.setText("Identifier:");
		}
		return shareAppLabel;
	}

	/**
	 * 
	 * Call the controller to cancel the sharing process.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void shareAppCancelButtonMouseMouseClicked(MouseEvent event) {
		this.controller.endSharing();
	}

	/**
	 * 
	 * Call the controller to cancel the sharing process.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void windowWindowClosing(WindowEvent event) {
		this.controller.endSharing();
	}

	/**
	 * 
	 * Call the controller to export the application information.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void shareAppOkButtonMouseMouseClicked(MouseEvent event) {
		this.controller.exportApplication();
	}

	/**
	 * 
	 * Call the controller to visualize the description of the rule.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 */
	private void shareAppRulesTableMouseMouseClicked(MouseEvent event) {
		this.controller.visualizeLocalRule();
	}

}
