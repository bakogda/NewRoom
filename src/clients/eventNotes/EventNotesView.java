package clients.eventNotes;


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
import javax.swing.JTextArea;

import admin.View;
import database.DesEncrypter;
import resources.Print;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class EventNotesView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> eventNames = new JComboBox<String>();
	JComboBox<String> ieventNames = new JComboBox<String>();
	JPanel panel = new JPanel();
	JTextArea notes = new JTextArea();
	
	String notesToDecrypt = new String();
	
	JLabel notesLabel = new JLabel("Meeting Notes:");
	JLabel ieventNamesLabel = new JLabel("Invited Meeting:");
	JLabel eventNamesLabel = new JLabel("Your Events:");
	
	JButton cancel = new JButton("Cancel");
	JButton save = new JButton("Save Notes");
	JButton getNotes = new JButton("Get/Create Notes");
	JButton print = new JButton("Print Notes");
	
	final static String newline = "\n";
	final String secretKey = "AES";
	String usn = View.getLogin();
	String userid = null;
	
	public EventNotesView()
	{
		setSize(700,550);
		setLocation(750,250);
		notes.setText("");
		eventNames.insertItemAt(null, 0);
		ieventNames.insertItemAt(null, 0);
		String eName = (String)eventNames.getSelectedItem();
		String eiName = (String)ieventNames.getSelectedItem();
		
		
		try {
			userid = database.queryDB.getId(usn);
			System.out.println(userid);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try{
			String SQL_statement = "SELECT TITLE,E_ID FROM EVENT WHERE USER_ID ='"+ userid +"'";
			
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
		try{
			String SQL_statement = "SELECT TITLE,INVITE_ID FROM INVITE,EVENT WHERE EVENT_ID = E_ID AND USERNAME_INVITED='"+ usn +"'";
			
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
					"password");
			
			//create a new statement
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_statement);
			while(resultSet.next())
			{
				ieventNames.addItem(resultSet.getString(1));
			}
			
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR");
		}finally
		{
		
		}
		
		getNotes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String eName = (String)eventNames.getSelectedItem();
				String eiName = (String)ieventNames.getSelectedItem();
				String eventID = null;
			    String decryptedString = DesEncrypter.decrypt(notesToDecrypt, "AES") ;

				
				try{
					Class.forName("org.postgresql.Driver");
					Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					Statement statement = connection.createStatement();
						String SQL_statement = ("SELECT E_ID FROM EVENT WHERE TITLE ='"+ eiName +"' OR TITLE ='"+ eName +"'");
						ResultSet resultSet = statement.executeQuery(SQL_statement);
						System.out.println(SQL_statement);

						while(resultSet.next())
						{
							eventID = resultSet.getString("E_ID");
						}
						if (resultSet != null) resultSet.close();
						if (statement != null) statement.close();
						if (connection != null) connection.close();
				} catch (SQLException e1) {
					System.out.println("Error!");
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
					notes.append(decryptedString);
				}
				
				System.out.println(eventID);
				if(eName != null && eiName == null)
				{
				try {
					
					notes.setText("");
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
					Statement statement = connection.createStatement();
					
					String s= "SELECT NOTES FROM " + database.JDBConnect.tbl_notes + " WHERE EVENT_ID='" + eventID + "' AND NOTES.USER_ID='"+ userid +"'";
					ResultSet resultSet = statement.executeQuery(s);
					ResultSetMetaData rsmd = resultSet.getMetaData();
					int colCount = rsmd.getColumnCount();
					
					if (resultSet.next())
					{
						 notesToDecrypt = resultSet.getString("NOTES") ;
						 notes.append(decryptedString);
					}
				
					
					if (statement != null) statement.close();
					if (connection != null) connection.close();
		
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
					
				}
				}else if(eName == null && eiName != null)
				{
					try {
						
						notes.setText("");
						Connection connection = DriverManager.getConnection(
								"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
								"password");		
						Statement statement = connection.createStatement();
						
						String s= "SELECT NOTES FROM " + database.JDBConnect.tbl_notes + " WHERE EVENT_ID ='" + eventID + "'AND NOTES.USER_ID='"+ userid +"'";
						ResultSet resultSet = statement.executeQuery(s);
						ResultSetMetaData rsmd = resultSet.getMetaData();
						int colCount = rsmd.getColumnCount();
						
						if (resultSet.next())
						{
							 notesToDecrypt = resultSet.getString("NOTES");
							 notes.setText(decryptedString);
						}
					    

						
						if (statement != null) statement.close();
						if (connection != null) connection.close();
			
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally
					{
						
					}
					}else
					{
						notes.setText("");					
						}
			}
			});
		
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
		save.addActionListener(new ActionListener(){
			
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String eName = (String)eventNames.getSelectedItem();
				String eiName = (String)ieventNames.getSelectedItem();
				String eNotes = notes.getText();
				String eventID = null;
				String notesEnc = DesEncrypter.encrypt(eNotes, "AES");
				
				try{
					Class.forName("org.postgresql.Driver");
					Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					Statement statement = connection.createStatement();
						String SQL_statement = ("SELECT E_ID FROM EVENT WHERE TITLE ='"+ eiName +"' OR TITLE ='"+ eName +"'");
						ResultSet resultSet = statement.executeQuery(SQL_statement);
						System.out.println(SQL_statement);

						while(resultSet.next())
						{
							eventID = resultSet.getString("E_ID");
						}
						if (resultSet != null) resultSet.close();
						if (statement != null) statement.close();
						if (connection != null) connection.close();
				} catch (SQLException e1) {
					System.out.println("Error!");
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
					
				}
				
				System.out.println(eventID);
				
				if(eName == null && eiName == null)
					{
						JOptionPane.showMessageDialog(null, "Please Choose only one event!");
					}else if(eName != null && eiName != null)
					{
						JOptionPane.showMessageDialog(null, "Please Choose only one event!");
					}else if (eName != null && eiName == null)
					{
						try{
							Class.forName("org.postgresql.Driver");
							Connection connection = DriverManager.getConnection(
											"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
											"password");
								String SQL_statement = ("DO $do$ BEGIN IF EXISTS (SELECT * FROM NOTES WHERE USER_ID = '"+ userid +"' AND EVENT_ID ='"+ eventID +"') THEN UPDATE NOTES SET NOTES = '"+ notesEnc +"' WHERE USER_ID = '"+ userid +"' AND EVENT_ID ='"+ eventID +"'; ELSE INSERT INTO NOTES VALUES((SELECT max(NOTES_ID)+1 FROM NOTES), '"+ eventID +"', '"+ userid + "','1','"+ notesEnc +"'); END IF; END $do$");
								System.out.println(SQL_statement);
								Statement statement = connection.createStatement();
								statement.executeUpdate(SQL_statement);
								if (statement != null) statement.close();
								if (connection != null) connection.close();
								toggleOff();
						} catch (SQLException e1) {
							System.out.println("Error!");
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally
						{
							
						}
					}else if(eName == null && eiName != null)
					{
						try{
							Class.forName("org.postgresql.Driver");
							Connection connection = DriverManager.getConnection(
											"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
											"password");
								String SQL_statement = ("DO $do$ BEGIN IF EXISTS (SELECT * FROM NOTES WHERE USER_ID = '"+ userid +"' AND EVENT_ID ='"+ eventID +"') THEN UPDATE NOTES SET NOTES = '"+ notesEnc +"' WHERE USER_ID = '"+ userid +"' AND EVENT_ID ='"+ eventID +"'; ELSE INSERT INTO NOTES VALUES((SELECT max(NOTES_ID)+1 FROM NOTES), '"+ eventID +"', '"+ userid + "','1','"+ notesEnc +"'); END IF; END $do$");
								System.out.println(SQL_statement);
								Statement statement = connection.createStatement();
								 statement.executeUpdate(SQL_statement);
								if (statement != null) statement.close();
								if (connection != null) connection.close();
								toggleOff();
						} catch (SQLException e1) {
							System.out.println("Error!");
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}finally
						{
							
						}
					}
			}
			});
		
		print.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Print.printComponent(notes);
			}
		});
		
		panel.setLayout(null);
		eventNamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		eventNamesLabel.setBounds(66,65,123,59);
		panel.add(eventNamesLabel);
		eventNames.setBounds(220,82,100,27);
		panel.add(eventNames);
		ieventNames.setBounds(220,49,100,27);
		panel.add(ieventNames);
		ieventNamesLabel.setBounds(66,48,153,27);
		panel.add(ieventNamesLabel);	
		save.setBounds(399,434,113,29);
		panel.add(save);
		print.setBounds(198, 434, 200, 29);
		notesLabel.setBounds(14,122,95,16);
		panel.add(notesLabel);
		cancel.setBounds(517,434,86,29);
		panel.add(cancel);
		getContentPane().add(panel);
		getNotes.setBounds(350,66,153,29);
		panel.add(print);
		panel.add(getNotes);
		notes.setSize(500,500);
		notes.setEditable(true);
		notes.setLineWrap(true);
		notes.setWrapStyleWord(true);
		notes.setBounds(121,122,482,288);
		panel.add(notes);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	private void toggleOff() {
		this.setVisible(false);
	}
	}
