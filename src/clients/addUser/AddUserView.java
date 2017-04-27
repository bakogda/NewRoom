package clients.addUser;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddUserView extends JFrame{

	/**
	 * Meow
	 */
	private static final long serialVersionUID = 1L;//stops eclipse from complaining
	//create all objects which will be on the panel
	//LABELS
	String[] userT = {"Admin","User"};
	private JLabel firstnameLabel = new JLabel("Firstname: ");
	private JLabel lastnameLabel = new JLabel("Lastname: ");
	private JLabel dobLabel = new JLabel("Date of Birth: ");
	private JLabel userTypeLabel = new JLabel("User type: ");
	private JComboBox userType1 = new JComboBox(userT);
	private JLabel emailLabel = new JLabel("Email:	");
	private JLabel usernameLabel = new JLabel("Username: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	private JLabel retypePasswordLabel = new JLabel("Retype Password: ");
	
	//TEXT FIELDS
	//there could be a button to automatically generate the username from the first, 
	//lastname and a random number
	private JButton genUsernameButton = new JButton("Generate Username");
	private JTextField firstname = new JTextField(10);
	private JTextField lastname = new JTextField(10);
	private JTextField dob = new JTextField(10);
	private JTextField email = new JTextField(10);
	private JTextField userType = new JTextField(10);
	private JTextField username = new JTextField(10);
	private JPasswordField password = new JPasswordField(10);
	private JPasswordField retypePassword = new JPasswordField(10);
	
	//BUTTONS
	private Button create = new Button("Create");
	private JButton cancel = new JButton("Cancel");
	
	
	public AddUserView(){
		JPanel auView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500, 400);
		
		/*
		 * laying it out nicely
		 */
		GridBagConstraints c = new GridBagConstraints();//would like to have an anchor
		c.insets = new Insets(5,5,5,5);//padding
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(firstnameLabel, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(firstname, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(lastnameLabel, c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(lastname, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(dobLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(dob, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(emailLabel, c);		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(email, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(userTypeLabel, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(userType1, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(usernameLabel, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(username, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(passwordLabel, c);
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(password, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(retypePasswordLabel, c);
		
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(retypePassword, c);
		
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(create, c);
		
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		auView.add(cancel, c);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
		this.add(auView);
	}
	
	//action listener for the create button
	public void aclCreate(ActionListener controller){
		System.out.println("Create button action listener: ");
		create.addActionListener(controller);
	}
	
	//get the firstname
	public String getFirstname(){
		return firstname.getText();
	}
	
	//get the lastname
	public String getLastname(){
		return lastname.getText();
	}
		
	//get the username
	public String getUsername(){
		return username.getText();
	}
	
	//get the password
	public String getPassword(){
		String passText = new String(password.getPassword());
		return passText;
	}
	
	//get the second password
	public String get2Password(){
		String passText2 = new String(retypePassword.getPassword());
		return passText2;
	}
	
	//get the user type
	public String getUserType(){
		return (String) userType1.getSelectedItem();
	}
	
	public void toggleOff(){
		this.setVisible(false);
	}
	
	public void toggleOn(){
		this.setVisible(true);
	}
}
