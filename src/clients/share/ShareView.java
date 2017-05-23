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
import javax.swing.JTextArea;

public class ShareView extends JFrame{

	/**
	 * Bako Gdaniec
	 */
	
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private JLabel eventNamesLabel = new JLabel("Your Events:");
	private JLabel user1 = new JLabel("Enter User Name:");
	private JLabel user2 = new JLabel("Enter User Name:");
	private JLabel user3 = new JLabel("Enter User Name:");
	private JLabel user4 = new JLabel("Enter User Name:");
	private JLabel user5 = new JLabel("Enter User Name:");
	private JLabel user6 = new JLabel("Enter User Name:");
	private JLabel delete = new JLabel("Remove User: ");

	JComboBox<String> eventNames = new JComboBox<String>();
	JPanel panel = new JPanel();
	
	
	private static JTextField sm_enterName = new JTextField(10);//enter the staff you want to search for!
	private static JTextField sm_enterName2 = new JTextField(10);
	private static JTextField sm_enterName3 = new JTextField(10);
	private static JTextField sm_enterName4 = new JTextField(10);
	private static JTextField sm_enterName5 = new JTextField(10);
	private static JTextField sm_enterName6 = new JTextField(10);

	private JButton searchButton = new JButton("Search");
	private JButton share = new JButton("Invite");
	private JButton checkusr1 = new JButton("Check User");
	private JButton checkusr2 = new JButton("Check User");
	private JButton checkusr3 = new JButton("Check User");
	private JButton checkusr4 = new JButton("Check User");
	private JButton checkusr5 = new JButton("Check User");
	private JButton checkusr6 = new JButton("Check User");
	private JButton cancel = new JButton("Cancel");
	private JButton remove = new JButton("Remove");
	String usn = View.getLogin();
	String eventID = null;
	String userid = null;
	

	
	public ShareView(){
		super("Invite User(s)");
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(500, 350);
		panel.setLayout(null);
		//SET BOUNDS & ADD TO PANEL
				panel.setLayout(null);
				eventNamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
				eventNamesLabel.setBounds(132,65,113,59);
				panel.add(eventNamesLabel);
				eventNames.setBounds(124,7,166,27);
				panel.add(eventNames);
				searchButton.setBounds(302, 6, 85, 29);
				panel.add(searchButton);
				sm_enterName2.setBounds(142, 74, 130, 26);
				panel.add(sm_enterName2);
				sm_enterName3.setBounds(142, 102, 130, 26);
				panel.add(sm_enterName3);
				sm_enterName4.setBounds(142, 130, 130, 26);
				panel.add(sm_enterName4);
				sm_enterName5.setBounds(142, 158, 130, 26);
				panel.add(sm_enterName5);
				sm_enterName6.setBounds(142, 186, 130, 26);
				panel.add(sm_enterName6);
				sm_enterName.setBounds(142, 46, 130, 26);
				user1.setBounds(29, 51, 108, 16);

				
				panel.add(user1);
				user2.setBounds(29, 135, 108, 16);
				panel.add(user2);
				user3.setBounds(29, 191, 108, 16);
				panel.add(user3);
				user4.setBounds(29, 79, 108, 16);
				panel.add(user4);
				user5.setBounds(29, 107, 108, 16);
				panel.add(user5);
				user6.setBounds(29, 163, 108, 16);
				panel.add(user6);
				eventNamesLabel.setBounds(34, 11, 78, 16);
				panel.add(eventNamesLabel);
				checkusr1.setBounds(272, 46, 115, 29);
				
				panel.add(checkusr1);
				checkusr2.setBounds(272, 74, 115, 29);
				panel.add(checkusr2);
				checkusr3.setBounds(272, 102, 115, 29);
				panel.add(checkusr3);
				checkusr4.setBounds(272, 130, 115, 29);
				panel.add(checkusr4);
				checkusr5.setBounds(272, 158, 115, 29);
				panel.add(checkusr5);
				checkusr6.setBounds(272, 186, 115, 29);
				panel.add(checkusr6);
				
				panel.add(sm_enterName);
				share.setBounds(258, 254, 79, 29);
				panel.add(share);
				cancel.setBounds(338, 254, 86, 29);
				panel.add(cancel);
				
				
				getContentPane().add(panel);
				
				JLabel lblRemoveUser = new JLabel("Remove User:");
				lblRemoveUser.setBounds(29, 214, 135, 27);
				panel.add(lblRemoveUser);
				
				JTextArea removeUser = new JTextArea();
				removeUser.setBounds(142, 219, 130, 21);
				panel.add(removeUser);
				
				JButton btnRemove = new JButton("Remove ");
				btnRemove.setBounds(272, 219, 117, 29);
				panel.add(btnRemove);
				
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
			
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
					"password");
			
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
	
		share.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String eName = (String)eventNames.getSelectedItem();
			String name1 = sm_enterName.getText();
			String name2 = sm_enterName2.getText();
			String name3 = sm_enterName3.getText();
			String name4 = sm_enterName4.getText();
			String name5 = sm_enterName5.getText();
			String name6 = sm_enterName6.getText();
			
			try
			{
				String SQL_statement = "SELECT E_ID, USER_ID, TITLE FROM EVENT WHERE USER_ID='" +userid+"' AND TITLE='"+ eName +"'";
				
				Connection connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
						"password");
				
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
			ArrayList<String> list = new ArrayList<String>();
			if(name1 != null && !name1.isEmpty())
			{
				list.add(name1);
			}
			if(name2 !=  null && !name2.isEmpty())
			{
				list.add(name2);
			}
			if(name3 !=  null && !name3.isEmpty())
			{
				list.add(name3);
			}
			if(name4 !=  null && !name4.isEmpty())
			{
				list.add(name4);
			}
			if(name5 !=  null && !name5.isEmpty())
			{
				list.add(name5);
			}
			if(name6 != null && !name6.isEmpty())
			{
				list.add(name6);
			}
			System.out.print(list);
		/*	
			if(name1 != null && !name1.isEmpty())
			{
				if(name1 != null && name1.equals(name2) || !name1.equals(name3) || !name1.equals(name4) || !name1.equals(name5) || name1.equals(name6))
				{
					System.out.println("1");
				}
			}
			if(name2 != null && !name2.isEmpty())
			{
				if(name2.equals(name1) || !name2.equals(name3) || !name2.equals(name4) || !name2.equals(name5) || name2.equals(name6))
				{
					System.out.println("2");

				}
			}
			if(name3 != null && !name2.isEmpty())
			{
				if(name3.equals(name1) || !name3.equals(name2) || !name3.equals(name4) || !name3.equals(name5) || name3.equals(name6))
				{
					System.out.println("3");

				}
			}
			if(name4 != null && !name4.isEmpty())
				{
				 if(name4.equals(name1) || !name4.equals(name2) || !name4.equals(name3) || !name4.equals(name5) || name4.equals(name6))
				 {
						System.out.println("4");

				 }
				}
			if(name5 != null && !name5.isEmpty())
			{
				if(name5.equals(name1) || !name5.equals(name2) || !name5.equals(name3) || !name5.equals(name4) || name5.equals(name6))
				{
					System.out.println("5");

				}
			}
			if(name6 != null && !name6.isEmpty())
			{
			 if(name6.equals(name1) || !name6.equals(name2) || !name6.equals(name3) || !name6.equals(name4) || name6.equals(name5))
			 {
					System.out.println("6");

			 }
			}else
				{
*/
					try{
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection(
								"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
								"password");
				
					for(String name: list)
					{		
					String SQL_statement = ("INSERT INTO INVITE VALUES((SELECT max(INVITE_ID)+1 FROM INVITE),'"+ eventID +"','"+ userid +"','"+ name +"')");
					System.out.println(SQL_statement);
					Statement statement = connection.createStatement();
					statement.executeUpdate(SQL_statement);
					}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
					setVisible(false);
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
					
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");
					
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
					
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");
					
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
					Class.forName("org.postgresql.Driver");
					Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					
			
						String SQL_statement = ("DELETE FROM INVITE WHERE EVENT_ID ='"+ eventID +"' AND USERNAME_INVITED='"+ userName +"' ");
						System.out.println(SQL_statement);
						Statement statement = connection.createStatement();
						statement.executeUpdate(SQL_statement);
						
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
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
							Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");		
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
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
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
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
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
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
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
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
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
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
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
	
	private void toggleOff() {
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
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		
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
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
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
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
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
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
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
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
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
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
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


	
