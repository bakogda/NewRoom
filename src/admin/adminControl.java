package admin;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.Action;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import database.createDB;
import database.queryDB;

public class adminControl extends JFrame
{
	private static final long serialVersionUID = 1L;
//	private JPanel contentPane;
//	private JTextField textField;
//	private JPasswordField passwordField;
//	private JTextField textField_1;
//	private JTextField textField_2;
//	private JPasswordField passwordField_1;
	
	private JTextArea userLabel 	= new JTextArea(4,20);
	private JButton addUserButton   = new JButton("Add User");
	private JButton editUserButton  = new JButton("Edit User");
	private JButton viewUsersButton = new JButton("View Users");
	private JButton removeUserButton = new JButton("Remove User");
	
	private JLabel resetPassLabel 	= new JLabel("Reset user's password");
	private JButton resetPassButton = new JButton("Reset Password");
	private JLabel viewLogsLabel    = new JLabel("View the logs that monutor user access to the applicaion.");
	private JButton viewLogsButton  = new JButton("View Logs");
	
	private JButton btnLogout		= new JButton("Logout");
	
	/**
	 * Launch the application.This is essentially the client
	 */
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try{
					adminControl frame = new adminControl();
							frame.setVisible(true);
				}catch (Exception e)
				{
					e.printStackTrace();
					
				}
			}
		});
	}
	
	/**
	 * Create the frame.This is essentially the view
	 * @throws SQLException 
	 */
	public adminControl() throws SQLException
	{
		setTitle("Admin Control Panel");
		JPanel adminView = new JPanel(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,300);
		
		userLabel.append("Manage the users who use the application. Add/Edit/View/Remove Users");
		userLabel.setEditable(false);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);//padding
		
		c.gridx = 0; c.gridy = 0;
		c.gridheight = 4;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(userLabel, c);
		
		c.gridx = 1; c.gridy = 0;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(addUserButton, c);
		
		c.gridx = 1; c.gridy = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(editUserButton, c);
		
		c.gridx = 1; c.gridy = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(viewUsersButton, c);
		
		c.gridx = 1; c.gridy = 3;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(removeUserButton, c);
		
		c.gridx = 0; c.gridy = 4;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(resetPassLabel, c);
		
		c.gridx = 1; c.gridy = 4;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(resetPassButton, c);
		
		c.gridx = 0; c.gridy = 5;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(viewLogsLabel, c);
		
		c.gridx = 1; c.gridy = 5;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(viewLogsButton, c);
		
		c.gridx = 1; c.gridy = 6;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		adminView.add(btnLogout,c);
		
		this.add(adminView);

//		btnLogout.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LogoutView logoutView = new LogoutView();
//				logoutView.setTitle("Logout?");
//				logoutView.setVisible(true);
//			}
//		});
//		 
//		JButton btnNewButton = new JButton("Add!");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					if(queryDB.checkUser(textField.getText()) == false) {
//						//compare password fields 
//						if(!Arrays.equals(passwordField.getPassword(),passwordField_1.getPassword())) {
//							textField_1.setText("Passwords do not match");
//						}
//						else {
//
//							try {
//
//								//convert from char array to string to compare
//								char pass[] = passwordField.getPassword();
//								String pwd = new String( pass);
//								//add pw to db
//								Model.addUser(textField.getText(), pwd);
//								textField_1.setText("Username " + textField.getText() + " added to the database!");
//							}
//							catch (SQLException e2) {
//								textField_1.setText("Username " + textField.getText() + " already exists in the database!");
//
//							}
//						}
//					}
//					else if(queryDB.checkUser(textField.getText()) == true) {
//						textField_1.setText("Username is in use!");
//					}
//
//				}
//				catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				catch (ClassNotFoundException e1) {
//
//					e1.printStackTrace();
//				}
//			}
//		});
//		
//		btnViewUsers.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//					queryDB.main(null);
//				} catch (ClassNotFoundException e1) {
//					e1.printStackTrace();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//
//			}
//		});

	}
	
	public void toggleOn(){
		this.setVisible(true);
	}
	
	public void toggleOff(){
		this.setVisible(false);
	}
}

