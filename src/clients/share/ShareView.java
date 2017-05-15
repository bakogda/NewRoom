package clients.share;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import admin.View;

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

	JComboBox<String> eventNames = new JComboBox<String>();
	JPanel panel = new JPanel();
	
	
	private JTextField sm_enterName = new JTextField(10);//enter the staff you want to search for!
	private JTextField sm_enterName2 = new JTextField(10);
	private JTextField sm_enterName3 = new JTextField(10);
	private JTextField sm_enterName4 = new JTextField(10);
	private JTextField sm_enterName5 = new JTextField(10);
	private JTextField sm_enterName6 = new JTextField(10);

	private JButton searchButton = new JButton("Search");
	private JButton share = new JButton("Invite");
	private JButton checkusr1 = new JButton("Check User");
	private JButton checkusr2 = new JButton("Check User");
	private JButton checkusr3 = new JButton("Check User");
	private JButton checkusr4 = new JButton("Check User");
	private JButton checkusr5 = new JButton("Check User");
	private JButton checkusr6 = new JButton("Check User");
	private JButton cancel = new JButton("Cancel");
	String usn = View.getLogin();

	
	public ShareView(){
		super("Invite User(s)");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(500, 350);
		panel.setLayout(null);
		
		try{
			String SQL_statement = "SELECT TITLE FROM EVENT WHERE USERNAME='"+ usn +"'";
			
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
					"password");
			
			//create a new statement
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_statement);
			while(resultSet.next())
			{
				eventNames.addItem(resultSet.getString(1));
			}
			
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR");
		}finally
		{
		
		}
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
		checkusr1.addActionListener(new ActionListener()
				{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name1 = (String)sm_enterName.getText();
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
		String name2 = (String)sm_enterName2.getText();
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
		String name3 = (String)sm_enterName3.getText();
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
		String name4 = (String)sm_enterName4.getText();
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
		String name5 = (String)sm_enterName5.getText();
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
		String name6 = (String)sm_enterName6.getText();
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
		
		
		share.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
			String eName = (String)eventNames.getSelectedItem();
			String name1 = (String)sm_enterName.getText();
			String name2 = (String)sm_enterName2.getText();
			String name3 = (String)sm_enterName3.getText();
			String name4 = (String)sm_enterName4.getText();
			String name5 = (String)sm_enterName5.getText();
			String name6 = (String)sm_enterName6.getText();
			
			try{
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection(
								"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
								"password");
				if(connection != null){					
					String SQL_statement = ("UPDATE EVENT SET INV_ONE = '"+ name1 +"', INV_TWO= '"+ name2 +"', INV_THREE= '"+ name3 +"', INV_FOUR= '"+ name4 +"', INV_FIVE='"+ name5 +"', INV_SIX= '"+ name6 +"'WHERE USERNAME='"+ usn +"'AND TITLE='"+ eName +"'");
					Statement statement = connection.createStatement();
					statement.executeUpdate(SQL_statement);
					System.out.println(SQL_statement);
				}
			} catch (SQLException e1) {
				System.out.println("Error!");
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		}
	});
		
		searchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				sm_enterName.setText("");
				sm_enterName2.setText("");
				sm_enterName3.setText("");
				sm_enterName4.setText("");
				sm_enterName5.setText("");
				sm_enterName6.setText("");
				try {
					sm_enterName.setText(getFirstInvitation(usn));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName2.setText(getSecondInvitation(usn));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName3.setText(getThirdInvitation(usn));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName4.setText(getFourthInvitation(usn));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName5.setText(getFithInvitation(usn));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sm_enterName6.setText(getSixthInvitation(usn));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	
		});
		
		
		
		//SET BOUNDS & ADD TO PANEL
		panel.setLayout(null);
		eventNamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		eventNamesLabel.setBounds(132,65,113,59);
		panel.add(eventNamesLabel);
		eventNames.setBounds(124,7,52,27);
		panel.add(eventNames);
		searchButton.setBounds(205, 6, 85, 29);
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
		setVisible(true);
		
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
	
	
	private String getFirstInvitation(String usn) throws SQLException {
		String eName = (String)eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		Statement statement = connection.createStatement();
		
		String s= "SELECT INV_ONE FROM " + database.JDBConnect.tbl_event + " WHERE USERNAME = '" + usn + "'AND TITLE= '"+ eName +"'";
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
	
	private String getSecondInvitation(String usn) throws SQLException {
		String eName = (String)eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		Statement statement = connection.createStatement();
		
		String s= "SELECT INV_TWO FROM " + database.JDBConnect.tbl_event + " WHERE USERNAME = '" + usn + "'AND TITLE= '"+ eName +"'";
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
	
	private String getThirdInvitation(String usn) throws SQLException {
		String eName = (String)eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		Statement statement = connection.createStatement();
		
		String s= "SELECT INV_THREE FROM " + database.JDBConnect.tbl_event + " WHERE USERNAME = '" + usn + "'AND TITLE= '"+ eName +"'";
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
	
	private String getFourthInvitation(String usn) throws SQLException {
		String eName = (String)eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		Statement statement = connection.createStatement();
		
		String s= "SELECT INV_FOUR FROM " + database.JDBConnect.tbl_event + " WHERE USERNAME = '" + usn + "'AND TITLE= '"+ eName +"'";
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
	
	private String getFithInvitation(String usn) throws SQLException {
		String eName = (String)eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		Statement statement = connection.createStatement();
		
		String s= "SELECT INV_FIVE FROM " + database.JDBConnect.tbl_event + " WHERE USERNAME = '" + usn + "'AND TITLE= '"+ eName +"'";
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
	
	private String getSixthInvitation(String usn) throws SQLException {
		String eName = (String)eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");		
		Statement statement = connection.createStatement();
		
		String s= "SELECT INV_SIX FROM " + database.JDBConnect.tbl_event + " WHERE USERNAME = '" + usn + "'AND TITLE= '"+ eName +"'";
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
	private void toggleOff() {
		this.setVisible(false);
	}

	
}
