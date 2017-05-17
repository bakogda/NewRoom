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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import admin.View;
import clients.mainPanel.MainCalendar;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;


public class EventNotesView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> eventNames = new JComboBox<String>();
	JComboBox<String> ieventNames = new JComboBox<String>();
	JPanel panel = new JPanel();
	JTextArea notes = new JTextArea();
	
	JLabel notesLabel = new JLabel("Meeting Notes:");
	JLabel ieventNamesLabel = new JLabel("Invited Meeting:");
	JLabel eventNamesLabel = new JLabel("Your Events:");
	
	JButton cancel = new JButton("Cancel");
	JButton save = new JButton("Save Notes");
	JButton getNotes = new JButton("Get/Create Notes");
	
	final static String newline = "\n";
	String usn = View.getLogin();
	
	public EventNotesView()
	{
		setSize(700,550);
		setLocation(750,250);
		notes.setText("");
		eventNames.insertItemAt(null, 0);
		ieventNames.insertItemAt(null, 0);
		
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
		try{
			String SQL_statement = "SELECT TITLE FROM EVENT WHERE INV_ONE='"+ usn +"'OR INV_TWO='"+ usn +"'OR INV_THREE='"+ usn +"'OR INV_FOUR='"+ usn +"'OR INV_FIVE='"+ usn +"'OR INV_SIX='"+ usn +"'";
			
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
		panel.setLayout(null);
		eventNamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		eventNamesLabel.setBounds(66,65,123,59);
		panel.add(eventNamesLabel);
		
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
				String eNotes = (String)notes.getText();
				
				try
				{
					String SQL_statement = "SELECT * FROM EVENT WHERE INV_ONE='"+ usn +"'OR INV_TWO='"+ usn +"'OR INV_THREE='"+ usn +"'OR INV_FOUR='"+ usn +"'OR INV_FIVE='"+ usn +"'OR INV_SIX='"+ usn +"'";
					
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
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
				}
				try{
					if(eName != null);
					{
					Class.forName("org.postgresql.Driver");
					Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					if(connection != null){
						String SQL_statement = ("UPDATE EVENT SET NOTES = '"+ eNotes +"'WHERE USERNAME='"+ usn +"'AND TITLE='"+ eName +"'");
						Statement statement = connection.createStatement();
						statement.executeQuery(SQL_statement);
					}else
						Class.forName("org.postgresql.Driver");
					Connection connection1 = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					if(connection1 != null){
						String SQL_statement = ("UPDATE EVENT SET NOTES = '"+ eNotes +"'WHERE USERNAME='"+ usn +"'AND TITLE='"+ eiName +"'");
						Statement statement = connection1.createStatement();
						statement.executeQuery(SQL_statement);
					}
						
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
		eventNames.setBounds(220,82,100,27);
		panel.add(eventNames);
		ieventNames.setBounds(220,49,100,27);
		panel.add(ieventNames);
		ieventNamesLabel.setBounds(66,48,153,27);
		panel.add(ieventNamesLabel);
		
		save.setBounds(390,434,113,29);
		panel.add(save);
		notesLabel.setBounds(14,122,95,16);
		panel.add(notesLabel);
		cancel.setBounds(517,434,86,29);
		panel.add(cancel);


		getContentPane().add(panel);
		
		getNotes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					notes.setText("");
					String eName = (String)eventNames.getSelectedItem();
					Connection connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
							"password");		
					Statement statement = connection.createStatement();
					
					String s= "SELECT NOTES FROM " + database.JDBConnect.tbl_event + " WHERE TITLE='" + eName + "'AND USERNAME='"+ usn +"'";
					ResultSet resultSet = statement.executeQuery(s);
					ResultSetMetaData rsmd = resultSet.getMetaData();
					int colCount = rsmd.getColumnCount();
					System.out.println(rsmd);
					System.out.println(colCount);
					
					if (resultSet.next())
					{
						for(int x = 1; x <= colCount; x++) notes.append(resultSet.getString(x) +" ");
					}
				
					
					if (statement != null) statement.close();
					if (connection != null) connection.close();
		
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
					
				}
			}
		});
		getNotes.setBounds(350,66,153,29);
		panel.add(getNotes);
		notes.setSize(500,500);
		notes.setEditable(true);
		notes.setLineWrap(true);
		notes.setWrapStyleWord(true);
		notes.setBounds(121,122,482,288);
		panel.add(notes);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	
	private void toggleOff() {
		this.setVisible(false);
	}
	}
