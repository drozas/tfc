package eu.ist.astra.am.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import eu.ist.astra.am.controller.ApplicationManagerController;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AddTagWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel addTagAppIdLabel;
	private JTextField addTagAppIdTextBox;
	private JScrollPane addTagCommunitiesPanel;
	private JTable addTagsCommunitiesTable;
	private JLabel addTagCommunitiesLabel;
	private JButton addTagCancelButton;
	private JButton addTagOkButton;
	private JLabel addTagNameLabel;
	private JTextField addTagNameTextField;
	private JRadioButton publicTagRadioButton;
	private JRadioButton communitiesTagRadioButton;
	private JRadioButton privateTagRadioButton;
	private ButtonGroup buttonsGroup;

	

	private ApplicationManagerController controller;
	private JLabel addTagScopeLabel;
	public AddTagWindow() {
		//drozas: Get singleton instance of the controller
		this.controller = ApplicationManagerController.getInstance();
		
	    //Group the radio buttons.
	    this.buttonsGroup = new ButtonGroup();

		initComponents();
		
		//Associate the buttons
		this.buttonsGroup.add(publicTagRadioButton);
		this.buttonsGroup.add(privateTagRadioButton);
		this.buttonsGroup.add(communitiesTagRadioButton);
		
	}

	private void initComponents() {
		setTitle("Add tag");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new GroupLayout());
		add(getAddTagAppIdLabel(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getAddTagNameLabel(), new Constraints(new Leading(12, 12, 12), new Leading(39, 12, 12)));
		add(getAddTagAppIdTextBox(), new Constraints(new Leading(96, 224, 12, 12), new Leading(10, 12, 12)));
		add(getAddTagNameTextField(), new Constraints(new Leading(96, 223, 12, 12), new Leading(37, 12, 12)));
		add(getAddTagCommunitiesLabel(), new Constraints(new Leading(12, 12, 12), new Leading(99, 12, 12)));
		add(getAddTagCommunitiesPanel(), new Constraints(new Leading(12, 311, 12, 12), new Leading(120, 87, 12, 12)));
		add(getAddTagCancelButton(), new Constraints(new Leading(167, 10, 10), new Leading(213, 12, 12)));
		add(getAddTagOkButton(), new Constraints(new Leading(246, 73, 12, 12), new Leading(213, 12, 12)));
		add(getAddTagScopeLabel(), new Constraints(new Leading(12, 12, 12), new Leading(68, 12, 12)));
		add(getPublicTagRadioButton(), new Constraints(new Leading(73, 8, 8), new Leading(64, 8, 8)));
		add(getPrivateTagRadioButton(), new Constraints(new Leading(250, 8, 8), new Leading(64, 8, 8)));
		add(getCommunitiesTagRadioButton(), new Constraints(new Leading(139, 8, 8), new Leading(64, 8, 8)));
		addWindowListener(new WindowAdapter() {
	
			public void windowClosing(WindowEvent event) {
				windowWindowClosing(event);
			}
		});
		initButtonsGroup();
		setSize(329, 250);
	}

	private JLabel getAddTagScopeLabel() {
		if (addTagScopeLabel == null) {
			addTagScopeLabel = new JLabel();
			addTagScopeLabel.setText("Scope:");
		}
		return addTagScopeLabel;
	}

	private void initButtonsGroup() {
		buttonsGroup = new ButtonGroup();
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
		buttonsGroup.add(getPublicTagRadioButton());
		buttonsGroup.add(getPrivateTagRadioButton());
		buttonsGroup.add(getCommunitiesTagRadioButton());
	}

	private JRadioButton getPrivateTagRadioButton() {
		if (privateTagRadioButton == null) {
			privateTagRadioButton = new JRadioButton();
			privateTagRadioButton.setText("Private");
			privateTagRadioButton.setToolTipText("Private tags can only be seen by you");
			privateTagRadioButton.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					privateTagRadioButtonKeyKeyPressed(event);
				}
			});
			privateTagRadioButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					privateTagRadioButtonMouseMouseClicked(event);
				}
			});
			
			//drozas: Assign here to the controller
			this.controller.setAddTagPrivateButton(privateTagRadioButton);
		}
		return privateTagRadioButton;
	}

	private JRadioButton getCommunitiesTagRadioButton() {
		if (communitiesTagRadioButton == null) {
			communitiesTagRadioButton = new JRadioButton();
			communitiesTagRadioButton.setText("Communities");
			communitiesTagRadioButton.setToolTipText("With this option you can select in which communities you want your tag to be seen");
			communitiesTagRadioButton.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					communitiesTagRadioButtonKeyKeyPressed(event);
				}
			});
			communitiesTagRadioButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					communitiesTagRadioButtonMouseMouseClicked(event);
				}
			});
			
			//drozas: Assign here to the controller
			this.controller.setAddTagCommunitiesButton(communitiesTagRadioButton);
		}
		return communitiesTagRadioButton;
	}

	private JRadioButton getPublicTagRadioButton() {
		if (publicTagRadioButton == null) {
			publicTagRadioButton = new JRadioButton();
			publicTagRadioButton.setSelected(true);
			publicTagRadioButton.setText("Public");
			publicTagRadioButton.setToolTipText("Public tags can be seen by all the communities");
			publicTagRadioButton.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					publicTagRadioButtonKeyKeyPressed(event);
				}
			});
			publicTagRadioButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					publicTagRadioButtonMouseMouseClicked(event);
				}
			});
			
			//drozas: Assign here to the controller
			this.controller.setAddTagPublicButton(publicTagRadioButton);
		}
		return publicTagRadioButton;
	}

	private JTextField getAddTagNameTextField() {
		if (addTagNameTextField == null) {
			addTagNameTextField = new JTextField();
			addTagNameTextField.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					addTagNameTextFieldKeyKeyPressed(event);
				}
			});
			
			//drozas: Assign here to the controller
			this.controller.setAddTagName(addTagNameTextField);
		}
		return addTagNameTextField;
	}

	private JLabel getAddTagNameLabel() {
		if (addTagNameLabel == null) {
			addTagNameLabel = new JLabel();
			addTagNameLabel.setText("Tag:");
		}
		return addTagNameLabel;
	}

	private JButton getAddTagOkButton() {
		if (addTagOkButton == null) {
			addTagOkButton = new JButton();
			addTagOkButton.setText("Ok");
			addTagOkButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					addTagOkButtonMouseMouseClicked(event);
				}
			});
		}
		return addTagOkButton;
	}

	private JButton getAddTagCancelButton() {
		if (addTagCancelButton == null) {
			addTagCancelButton = new JButton();
			addTagCancelButton.setText("Cancel");
			addTagCancelButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					cancelButtonMouseMouseClicked(event);
				}
			});
		}
		return addTagCancelButton;
	}

	private JLabel getAddTagCommunitiesLabel() {
		if (addTagCommunitiesLabel == null) {
			addTagCommunitiesLabel = new JLabel();
			addTagCommunitiesLabel.setText("Communities:");
		}
		return addTagCommunitiesLabel;
	}

	private JTable getAddTagsCommunitiesTable() {
		if (addTagsCommunitiesTable == null) {
			addTagsCommunitiesTable = new JTable();
			addTagsCommunitiesTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] {};
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			addTagsCommunitiesTable.setVisible(false);
			addTagsCommunitiesTable.setEnabled(false);
			
			//drozas: Assign here to the controller
			this.controller.setAddTagCommunitiesTable(addTagsCommunitiesTable);
		}
		return addTagsCommunitiesTable;
	}

	private JScrollPane getAddTagCommunitiesPanel() {
		if (addTagCommunitiesPanel == null) {
			addTagCommunitiesPanel = new JScrollPane();
			addTagCommunitiesPanel.setViewportView(getAddTagsCommunitiesTable());

		}
		return addTagCommunitiesPanel;
	}

	private JTextField getAddTagAppIdTextBox() {
		if (addTagAppIdTextBox == null) {
			addTagAppIdTextBox = new JTextField();
			addTagAppIdTextBox.setEditable(false);
			
			//drozas: Assign here to the controller
			this.controller.setAddTagAppIde(addTagAppIdTextBox);
			
		}
		return addTagAppIdTextBox;
	}

	private JLabel getAddTagAppIdLabel() {
		if (addTagAppIdLabel == null) {
			addTagAppIdLabel = new JLabel();
			addTagAppIdLabel.setText("Application:");
		}
		return addTagAppIdLabel;
	}

	private void cancelButtonMouseMouseClicked(MouseEvent event) {
		this.controller.endAddTag();
	}

	private void windowWindowClosing(WindowEvent event) {
		this.controller.endAddTag();
	}


	private void publicTagRadioButtonMouseMouseClicked(MouseEvent event) {
		this.addTagsCommunitiesTable.setVisible(false);
		this.addTagsCommunitiesTable.setEnabled(false);
	}

	private void communitiesTagRadioButtonMouseMouseClicked(MouseEvent event) {
		this.addTagsCommunitiesTable.setVisible(true);
		this.addTagsCommunitiesTable.setEnabled(true);
	}

	private void privateTagRadioButtonMouseMouseClicked(MouseEvent event) {
		this.addTagsCommunitiesTable.setVisible(false);
		this.addTagsCommunitiesTable.setEnabled(false);
	}

	private void addTagOkButtonMouseMouseClicked(MouseEvent event) {
		this.controller.saveTag();
	}

	private void addTagNameTextFieldKeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.saveTag();
	}

	private void publicTagRadioButtonKeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.saveTag();
	}

	private void communitiesTagRadioButtonKeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.saveTag();
	}

	private void privateTagRadioButtonKeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.saveTag();
	}

}
