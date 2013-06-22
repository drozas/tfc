package eu.ist.astra.am.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

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
public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel;
	private JTextField usernameTextField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel loginLabel;
	private JButton loginButton;
	

	private ApplicationManagerController controller;
	
	
	public LoginWindow(String title) {
		//drozas: Get singleton instance of the controller
		this.controller = ApplicationManagerController.getInstance();
		
		initComponents();

		this.setTitle(title);
	}

	private void initComponents() {
		setTitle("Application Manager - Help menu");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getUsernameLabel(), new Constraints(new Leading(17, 10, 10), new Leading(55, 10, 10)));
		add(getPasswordLabel(), new Constraints(new Leading(17, 12, 12), new Leading(96, 10, 10)));
		add(getUsernameTextField(), new Constraints(new Leading(94, 134, 12, 12), new Leading(55, 12, 12)));
		add(getPasswordField(), new Constraints(new Leading(94, 134, 12, 12), new Leading(96, 10, 10)));
		add(getLoginButton(), new Constraints(new Leading(97, 12, 12), new Leading(127, 12, 12)));
		add(getLoginLabel(), new Constraints(new Leading(17, 12, 12), new Leading(19, 12, 12)));
		setSize(240, 164);
	}

	private JButton getLoginButton() {
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setText("Login");
			
			loginButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					loginButtonMouseMouseClicked(event);
				}
			});
		}
		return loginButton;
	}

	private JLabel getLoginLabel() {
		if (loginLabel == null) {
			loginLabel = new JLabel();
			loginLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

			//drozas: Assign here to the controller
			this.controller.setLoginLabel(loginLabel);
		}
		return loginLabel;
	}

	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setEchoChar('•');
			this.controller.setPasswordField(passwordField);
			passwordField.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					passwordFieldKeyKeyPressed(event);
				}
			});
		}
		return passwordField;
	}

	private JLabel getPasswordLabel() {
		if (passwordLabel == null) {
			passwordLabel = new JLabel();
			passwordLabel.setText("Password:");
		}
		return passwordLabel;
	}

	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			this.controller.setLoginTextField(usernameTextField);
			usernameTextField.addKeyListener(new KeyAdapter() {
	
				public void keyPressed(KeyEvent event) {
					usernameTextFieldKeyKeyPressed(event);
				}
			});
		}
		return usernameTextField;
	}

	private JLabel getUsernameLabel() {
		if (usernameLabel == null) {
			usernameLabel = new JLabel();
			usernameLabel.setText("Username:");
		}
		return usernameLabel;
	}
	
	/**
	 * 
	 * It calls the controller to validate the user when the login
	 * button is pressed.
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 * 
	 */
	private void loginButtonMouseMouseClicked(MouseEvent event) {
		this.controller.login();
	}

	/**
	 * 
	 * It calls the controller to validate the user when ENTER key
	 * is pressed while typing in the password field
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 * 
	 */
	private void passwordFieldKeyKeyPressed(KeyEvent event) 
	{
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.login();
	}

	/**
	 * 
	 * It calls the controller to validate the user when ENTER key
	 * is pressed while typing in the username field
	 * 
	 * @param event
	 * 
	 * @author David Rozas
	 * 
	 */
	private void usernameTextFieldKeyKeyPressed(KeyEvent event) {
		int key = event.getKeyCode();
	     if (key == KeyEvent.VK_ENTER)
	    	 this.controller.login();
	}

}
