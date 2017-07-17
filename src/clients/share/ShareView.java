package clients.share;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import admin.View;
import database.dBV;

import javax.swing.JTextArea;

public class ShareView extends JFrame{

	/**
	 * Bako Gdaniec
	 */
	
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the shView
	private JLabel eventNamesLabel = new JLabel("Your Events:");
	private JLabel user1 = new JLabel("Enter User Name:");
	private JLabel user2 = new JLabel("Enter User Name:");
	private JLabel user3 = new JLabel("Enter User Name:");
	private JLabel user4 = new JLabel("Enter User Name:");
	private JLabel user5 = new JLabel("Enter User Name:");
	private JLabel user6 = new JLabel("Enter User Name:");
	private JLabel delete = new JLabel("Remove User: ");

	JComboBox<String> eventNames = new JComboBox<String>();
	JPanel shView = new JPanel();
	
	
	private static JTextField sm_enterName = new JTextField(10);//enter the staff you want to search for!
	private static JTextField sm_enterName2 = new JTextField(10);
	private static JTextField sm_enterName3 = new JTextField(10);
	private static JTextField sm_enterName4 = new JTextField(10);
	private static JTextField sm_enterName5 = new JTextField(10);
	private static JTextField sm_enterName6 = new JTextField(10);

	private JButton searchButton = new JButton("Search");
	private JButton shareButton = new JButton("Invite");
	private JButton checkusr1 = new JButton("Check User");
	private JButton checkusr2 = new JButton("Check User");
	private JButton checkusr3 = new JButton("Check User");
	private JButton checkusr4 = new JButton("Check User");
	private JButton checkusr5 = new JButton("Check User");
	private JButton checkusr6 = new JButton("Check User");
	private JButton cancel = new JButton("Cancel");
	private JButton remove = new JButton("Remove");
	String usn = View.getLogin();
	String userid = null;
	String eventID = null;

	

	
	public ShareView(){
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(500, 350);
		shView.setLayout(null);
		//SET BOUNDS & ADD TO shView
				shView.setLayout(null);
				eventNamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
				eventNamesLabel.setBounds(132,65,113,59);
				shView.add(eventNamesLabel);
				eventNames.setBounds(124,7,166,27);
				shView.add(eventNames);
				searchButton.setBounds(302, 6, 85, 29);
				shView.add(searchButton);
				sm_enterName2.setBounds(142, 74, 130, 26);
				shView.add(sm_enterName2);
				sm_enterName3.setBounds(142, 102, 130, 26);
				shView.add(sm_enterName3);
				sm_enterName4.setBounds(142, 130, 130, 26);
				shView.add(sm_enterName4);
				sm_enterName5.setBounds(142, 158, 130, 26);
				shView.add(sm_enterName5);
				sm_enterName6.setBounds(142, 186, 130, 26);
				shView.add(sm_enterName6);
				sm_enterName.setBounds(142, 46, 130, 26);
				user1.setBounds(29, 51, 108, 16);

				
				shView.add(user1);
				user2.setBounds(29, 135, 108, 16);
				shView.add(user2);
				user3.setBounds(29, 191, 108, 16);
				shView.add(user3);
				user4.setBounds(29, 79, 108, 16);
				shView.add(user4);
				user5.setBounds(29, 107, 108, 16);
				shView.add(user5);
				user6.setBounds(29, 163, 108, 16);
				shView.add(user6);
				eventNamesLabel.setBounds(34, 11, 78, 16);
				shView.add(eventNamesLabel);
				checkusr1.setBounds(272, 46, 115, 29);
				
				shView.add(checkusr1);
				checkusr2.setBounds(272, 74, 115, 29);
				shView.add(checkusr2);
				checkusr3.setBounds(272, 102, 115, 29);
				shView.add(checkusr3);
				checkusr4.setBounds(272, 130, 115, 29);
				shView.add(checkusr4);
				checkusr5.setBounds(272, 158, 115, 29);
				shView.add(checkusr5);
				checkusr6.setBounds(272, 186, 115, 29);
				shView.add(checkusr6);
				
				shView.add(sm_enterName);
				shareButton.setBounds(258, 254, 79, 29);
				shView.add(shareButton);
				cancel.setBounds(338, 254, 86, 29);
				shView.add(cancel);
				
				
				getContentPane().add(shView);
				
				JLabel lblRemoveUser = new JLabel("Remove User:");
				lblRemoveUser.setBounds(29, 214, 135, 27);
				shView.add(lblRemoveUser);
				
				JTextArea removeUser = new JTextArea();
				removeUser.setBounds(142, 219, 130, 21);
				shView.add(removeUser);
				
				JButton btnRemove = new JButton("Remove ");
				btnRemove.setBounds(272, 219, 117, 29);
				shView.add(btnRemove);
				
				setVisible(true);
				
		try {
			userid = database.queryDB.getId(usn);
			System.out.println(userid);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try{
			String SQL_statement = "SELECT E_ID, USER_ID, TITLE FROM EVENT WHERE USER_ID='" +userid+"'AND DATE >= now()";
			
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
			
			//create a new statement
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_statement);
			while(resultSet.next())
			{
				eventNames.addItem(resultSet.getString("TITLE"));
			}
			
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR");
		}finally
		{
		
		}
		//CANCEL BUTTON
		
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
	
		
			
		
		//SEARCH BUTTON 
		
		
		searchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String eName = (String)eventNames.getSelectedItem();
				sm_enterName.setText(null);
				sm_enterName2.setText(null);
				sm_enterName3.setText(null);
				sm_enterName4.setText(null);
				sm_enterName5.setText(null);
				sm_enterName6.setText(null);
				
				try{
					String SQL_statement = "SELECT E_ID, USER_ID, TITLE FROM EVENT WHERE USER_ID='" +userid+"' AND TITLE='"+ eName +"'";
					
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

					
					//create a new statement
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(SQL_statement);
					while(resultSet.next())
					{
						eventID = resultSet.getString("E_ID");
					}
					
					if (statement != null) statement.close();
					if (connection != null) connection.close();
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "ERROR");
				}finally
				{
				
					
				}
				try {
					sm_enterName.setText(getFirstInvitation(userid, eventID, eName));
					String data=sm_enterName.getText().trim();
					if(!data.equals(""))
					{
						sm_enterName.setEditable(false);
					}else
					{
						sm_enterName.setEditable(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName2.setText(getSecondInvitation(userid, eventID, eName));
					String data=sm_enterName2.getText().trim();
					if(!data.equals(""))
					{
						sm_enterName2.setEditable(false);
					}else
					{
						sm_enterName2.setEditable(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName3.setText(getThirdInvitation(userid, eventID, eName));
					String data=sm_enterName3.getText().trim();
					if(!data.equals(""))
					{
						sm_enterName3.setEditable(false);
					
					}else
					{
						sm_enterName3.setEditable(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName4.setText(getFourthInvitation(userid, eventID, eName));
					String data=sm_enterName4.getText().trim();
					if(!data.equals(""))
					{
						sm_enterName4.setEditable(false);
					
					}else
					{
						sm_enterName4.setEditable(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName5.setText(getFithInvitation(userid, eventID, eName));
					String data=sm_enterName5.getText().trim();
					if(!data.equals(""))
					{
						sm_enterName5.setEditable(false);
						
					}else
					{
						sm_enterName5.setEditable(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName6.setText(getSixthInvitation(userid, eventID, eName));
					String data=sm_enterName6.getText().trim();
					if(!data.equals(""))
					{
						sm_enterName6.setEditable(false);
					}else
					{
						sm_enterName6.setEditable(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// REMOVE BUTTON
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String eName = (String)eventNames.getSelectedItem();
				String userName = removeUser.getText();
				
				try
				{
					String SQL_statement = "SELECT E_ID FROM EVENT WHERE USER_ID='" +userid+"' AND TITLE='"+ eName +"'";
					
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
					
					//create a new statement
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(SQL_statement);
					while(resultSet.next())
					{
						eventID = resultSet.getString("E_ID");
					}
					
					if (statement != null) statement.close();
					if (connection != null) connection.close();
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "ERROR");
				}finally
				{
				
				}

						try{
							Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
			
						String SQL_statement = ("DELETE FROM INVITE WHERE EVENT_ID ='"+ eventID +"' AND USERNAME_INVITED='"+ userName +"' ");
						System.out.println(SQL_statement);
						Statement statement = connection.createStatement();
						statement.executeUpdate(SQL_statement);
						
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
						toggleOff();
			}
				});
		
		// CHECK USERS BUTTONS
		
			checkusr1.addActionListener(new ActionListener()
					{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					String name1 = sm_enterName.getText();
					try
					{
						if(database.queryDB.checkUser(name1)){
							//user name does exist in the database
							//insert user data into user table
							Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
	
							connection.close();
							JOptionPane.showMessageDialog(null, new Object[] {
								    "'"+ name1 +"' exists in the database"			    
								    });
			
						}
						else {
							JOptionPane.showMessageDialog(null, new Object[] {
									"'"+ name1 +"' Doesn't exist in the database"				    
								    });
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
					{
					
					}
					}
					});
			
			checkusr2.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String name2 = sm_enterName2.getText();
			try
			{
				if(database.queryDB.checkUser(name2)){
					//user name does exist in the database
					//insert user data into user table
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);		
					connection.close();
					JOptionPane.showMessageDialog(null, new Object[] {
						    "'"+ name2 +"' exists in the database"			    
						    });
			
				}
				else {
					JOptionPane.showMessageDialog(null, new Object[] {
							"'"+ name2 +"' Doesn't exist in the database"				    
						    });
				
			}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}finally
			{
			
			}
			}
			});
			
			checkusr3.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String name3 = sm_enterName3.getText();
			try
			{
				if(database.queryDB.checkUser(name3)){
					//user name does exist in the database
					//insert user data into user table
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);		
					connection.close();
					JOptionPane.showMessageDialog(null, new Object[] {
						    "'"+ name3 +"' exists in the database"			    
						    });
			
				}
				else {
					JOptionPane.showMessageDialog(null, new Object[] {
							"'"+ name3 +"' Doesn't exist in the database"				    
						    });
				
			}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}finally
			{
			
			}
			}
			});
			
			checkusr4.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String name4 = sm_enterName4.getText();
			try
			{
				if(database.queryDB.checkUser(name4)){
					//user name does exist in the database
					//insert user data into user table
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);		
					connection.close();
					JOptionPane.showMessageDialog(null, new Object[] {
						    "'"+ name4 +"' exists in the database"			    
						    });
			
				}
				else {
					JOptionPane.showMessageDialog(null, new Object[] {
							"'"+ name4 +"' Doesn't exist in the database"				    
						    });
				
			}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}finally
			{
			
			}
			}
			});
			
			checkusr5.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String name5 = sm_enterName5.getText();
			try
			{
				if(database.queryDB.checkUser(name5)){
					//user name does exist in the database
					//insert user data into user table
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);		
					connection.close();
					JOptionPane.showMessageDialog(null, new Object[] {
						    "'"+ name5 +"' exists in the database"			    
						    });
			
				}
				else {
					JOptionPane.showMessageDialog(null, new Object[] {
							"'"+ name5 +"' Doesn't exist in the database"				    
						    });
				
			}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}finally
			{
			
			}
			}
			});
			
			checkusr6.addActionListener(new ActionListener()
			{
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String name6 = sm_enterName6.getText();
			try
			{
				if(database.queryDB.checkUser(name6)){
					//user name does exist in the database
					//insert user data into user table
					Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
					connection.close();
					JOptionPane.showMessageDialog(null, new Object[] {
						    "'"+ name6 +"' exists in the database"			    
						    });
			
				}
				else {
					JOptionPane.showMessageDialog(null, new Object[] {
							"'"+ name6 +"' Doesn't exist in the database"				    
						    });
				
			}
			} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}finally
			{
			
			}
			}
			});
		
	}
	
	public void aclShare(ActionListener c){
		System.out.println("Change button action listener: ");
		shareButton.addActionListener(c);
	}
	
	public void aclSearch(ActionListener d)
	{
		System.out.println("Search button action listener: ");
		searchButton.addActionListener(d);
	}
	
	public void aclRemove(ActionListener f)
	{
		System.out.println("Remove button action listener: ");
		remove.addActionListener(f);
	}
	
	void toggleOff() {
		this.setVisible(false);
}
		
	public String getFirstName()
	{
		return sm_enterName.getText();
	}
	
	public String getSecondName()
	{
		return sm_enterName2.getText();
	}
	
	public String getThirdName()
	{
		return sm_enterName3.getText();
	}
	
	public String getFourthName()
	{
		return sm_enterName4.getText();
	}
	
	public String getFithName()
	{
		return sm_enterName5.getText();
	}
	
	public String getSixthName()
	{
		return sm_enterName6.getText();
	}
	
	
	
	private String getFirstInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);	
		
		String s= "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid + "'AND EVENT_ID= '"+ eventID+"' and TITLE = '"+ eName +"' LIMIT 1" ;
		System.out.println(s);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(s);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
		
	}
	
	private String getSecondInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);	
		Statement statement = connection.createStatement();
		
		String s= "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid + "'AND EVENT_ID='"+ eventID +"' and TITLE = '"+ eName +"'OFFSET 1 LIMIT 1";
		System.out.println(s);
		ResultSet resultSet = statement.executeQuery(s);
	
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
		
	}
	
	private String getThirdInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);	
		Statement statement = connection.createStatement();
		
		String s= "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid + "'AND EVENT_ID='"+ eventID +"' and TITLE = '"+ eName +"'OFFSET 2 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);
	
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
		
	}
	
	private String getFourthInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);		
		Statement statement = connection.createStatement();
		
		String s= "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid + "'AND EVENT_ID='"+ eventID +"' and TITLE = '"+ eName +"'OFFSET 3 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);
	
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
		
	}
	
	private String getFithInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
		Statement statement = connection.createStatement();
		
		String s= "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid + "'AND EVENT_ID='"+ eventID +"' and TITLE ='"+ eName +"'OFFSET 4 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);
	
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
		
	}
	
	private String getSixthInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
		Statement statement = connection.createStatement();
		
		String s= "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid + "'AND EVENT_ID='"+ eventID +"' and TITLE = '"+ eName +"'OFFSET 5 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);
	
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;	

	}
	}


	
