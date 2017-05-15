package clients.editEvent;

import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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
import javax.swing.JTextField;

import admin.View;
import clients.mainPanel.MainCalendar;

public class EditEventView extends JFrame
{	
	public static void main(String[] args)
	{
		EditEventView frameTable = new EditEventView();
	}
	String[] times = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
	String [] rooms = {"Room 1","Room 2","IT Room"};
	JComboBox<String> eventNames = new JComboBox<String>();
	JButton createEvent = new JButton("Create Event");
	JButton cancel = new JButton("Cancel");
	JPanel panel = new JPanel();
	JTextField eventTitle = new JTextField(20);
	JTextArea eventDesc = new JTextArea();
	JTextArea eventNotes = new JTextArea();
	JLabel eventTitleLabel = new JLabel("Event Title:");
	JLabel eventDescLabel = new JLabel("Description:");
	JLabel eventNotesLabel = new JLabel("Event Notes:");
	JLabel roomListLabel = new JLabel("Room:");
	JLabel timeListLabel1 = new JLabel("From:");
	JComboBox roomList = new JComboBox(rooms);
	JComboBox timeList1 = new JComboBox(times);
	JComboBox timeList2 = new JComboBox(times);
	JLabel timeListLabel2 = new JLabel("Until:");
	String usn = View.getLogin();

	
	//BUTTONS 
	private Button getDet = new Button("Get Details");
	private Button update = new Button("Update");

	
	public EditEventView()
	{
		super("Create New Event");
		setSize(420,570);
		setLocation(750,250);
		panel.setLayout (null);

		JLabel label = new JLabel("Date:");
		String label1Text = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
		JLabel label1 = new JLabel(label1Text);
		//JTextField text = new JTextField(20);
		final JFrame f = new JFrame();

		eventDesc.setLineWrap(true);
		eventDesc.setWrapStyleWord(true);
		eventNotes.setLineWrap(true);
		eventNotes.setWrapStyleWord(true);
		eventNames.setBounds(50,45,300,20);
		eventDesc.setBounds(50,255,300,100);
		eventNotes.setBounds(50, 400, 300, 100);
		try{
			String SQL_statement = "SELECT TITLE, DATE, ROOM, STARTTIME, ENDTIME, DESCR,NOTES FROM EVENT WHERE USERNAME='"+ usn +"'";
			
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
		String eName = (String)eventNames.getSelectedItem();
		try{
			String SQL_statement = "SELECT DATE FROM EVENT WHERE USERNAME='"+ usn +"'AND TITLE='"+ eName +"'";
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
					"password");
			
			//create a new statement
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_statement);
			while(resultSet.next())
			{
				label1Text=(resultSet.getString(1));
			}
			
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR");
		}finally
		{
			
		}
		
		update.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String eName = (String)eventNames.getSelectedItem();
				String eNotes = (String)eventNotes.getText();
				
				try
				{
					Class.forName("org.postgresql.Driver");
					Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					if(connection != null){
						String SQL_statement = ("UPDATE EVENT SET NOTES = '"+ eNotes +"'WHERE USERNAME='"+ usn +"'AND TITLE='"+ eName +"'");
						Statement statement = connection.createStatement();
						statement.executeQuery(SQL_statement);
						
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
		cancel.setBounds(10, 510, 75, 30);
		eventTitleLabel.setBounds(50,20,300,20);
		eventDescLabel.setBounds(50,185,300,100);
		eventNotesLabel.setBounds(50, 335, 300, 100);
		label.setBounds(50,70,300,20);
		//text.setBounds(50,95,300,20);
		//text.setText(MainCalendar.dom);
		label1.setBounds(50,95,300,20);
		roomList.setBounds(50,150,200,20);
		roomListLabel.setBounds(50,125,300,20);
		timeList1.setBounds(110,188,100,20);
		timeListLabel1.setBounds(50,185,75,20);
		timeList2.setBounds(273,188,100,20);
		timeListLabel2.setBounds(225,185,75,20);

		panel.add(eventNames);
		panel.add(createEvent);
		panel.add(cancel);
		panel.add(eventTitle);
		panel.add(eventDesc);
		panel.add(eventTitleLabel);
		panel.add(eventDescLabel);
		panel.add(eventNotes);
		panel.add(eventNotesLabel);
		panel.add(label);
		//panel.add(text);
		panel.add(roomList);
		panel.add(roomListLabel);
		panel.add(timeList1);
		panel.add(timeListLabel1);
		panel.add(timeList2);
		panel.add(timeListLabel2);
		panel.add(label1);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

}
