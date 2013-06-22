package eu.ist.astra.am.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import eu.ist.astra.am.controller.ApplicationManagerController;

//VS4E -- DO NOT REMOVE THIS LINE!
public class HelpWindow extends JFrame {

	private ApplicationManagerController controller;
	
	private static final long serialVersionUID = 1L;
	private JPanel helpWindowPanel;
	private JEditorPane helpWindowEditor;
	private JScrollPane jScrollPane0;



	private JButton helpWindowCloseButton;

	public HelpWindow() {
		//Get singleton instance of the controller
		this.controller = ApplicationManagerController.getInstance();
		initComponents();
	}

	private void initComponents() {
		setTitle("Application Manager - Help");
		setResizable(false);
		setLayout(new GroupLayout());
		add(getHelpWindowPanel(), new Constraints(new Leading(2, 576, 10, 10), new Leading(2, 323, 10, 10)));
		addWindowListener(new WindowAdapter() {
	
			public void windowClosing(WindowEvent event) {
				windowWindowClosing(event);
			}
		});
		setSize(580, 329);
	}

	private JButton getHelpWindowCloseButton() {
		if (helpWindowCloseButton == null) {
			helpWindowCloseButton = new JButton();
			helpWindowCloseButton.setText("Close");
			helpWindowCloseButton.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					helpWindowCloseButtonMouseMouseClicked(event);
				}
			});
		}
		return helpWindowCloseButton;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getHelpWindowEditor());
		}
		return jScrollPane0;
	}

	private JEditorPane getHelpWindowEditor() {
		if (helpWindowEditor == null) {
			helpWindowEditor = new JEditorPane();
			

	        String htmlString = this.controller.getHelpContent();
	        if (htmlString!=null)
	        {
		        // add an html editor kit
		        HTMLEditorKit kit = new HTMLEditorKit();
		        helpWindowEditor.setEditorKit(kit);
		        
		        // add some styles to the html
		        StyleSheet styleSheet = kit.getStyleSheet();
		        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
		        styleSheet.addRule("h1 {color: blue;}");
		        styleSheet.addRule("h2 {color: #ff0000;}");
		        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

		        // create a document, set it on the jeditorpanel
		        Document doc = kit.createDefaultDocument();
		        helpWindowEditor.setDocument(doc);
		        
		        //set the remote html contents
		        helpWindowEditor.setText(htmlString);
	        }else{
	        	//Or explain the error if it was not possible
	        	helpWindowEditor.setText("It was not possible to access to the remote content");
	        	
	        }
	        //Necessary to scroll from the beginning
	        helpWindowEditor.setCaretPosition(0);
	        
	        helpWindowEditor.setEditable(false);
	        

		}
		return helpWindowEditor;
	}

	private JPanel getHelpWindowPanel() {
		if (helpWindowPanel == null) {
			helpWindowPanel = new JPanel();
			helpWindowPanel.setLayout(new GroupLayout());
			helpWindowPanel.add(getHelpWindowCloseButton(), new Constraints(new Leading(502, 10, 10), new Leading(294, 24, 10, 10)));
			helpWindowPanel.add(getJScrollPane0(), new Constraints(new Leading(3, 570, 10, 10), new Leading(2, 288, 10, 10)));
		}
		return helpWindowPanel;
	}

	private void windowWindowClosing(WindowEvent event) {
		this.controller.endHelp();
	}

	private void helpWindowCloseButtonMouseMouseClicked(MouseEvent event) {
		this.controller.endHelp();
	}

}
