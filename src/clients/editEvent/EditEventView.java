package clients.editEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javax.swing.WindowConstants;

import admin.View;
import database.dBV;

public class EditEventView extends JFrame
{	
	public static void main(String[] args)
	{
		EditEventView frameTable = new EditEventView();
	}
	static String[] times = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
	static String [] rooms = {"Room 1","Room 2","IT Room"};
	static JComboBox<String> eventNames = new JComboBox<String>();
	JButton createEvent = new JButton("Create Event");
	JButton cancel = new JButton("Cancel");
	JPanel panel = new JPanel();
	JTextField eventTitle = new JTextField(20);
	static JTextArea eventDesc = new JTextArea();
	JTextArea eventNotes = new JTextArea();
	JLabel eventTitleLabel = new JLabel("Event Title:");
	JLabel eventDescLabel = new JLabel("Description:");
	JLabel roomListLabel = new JLabel("Room:");
	JLabel timeListLabel1 = new JLabel("From:");
	static JComboBox roomList = new JComboBox(rooms);
	static JComboBox timeList1 = new JComboBox(times);
	static JComboBox timeList2 = new JComboBox(times);
	String roomEvent = new String();
	JLabel timeListLabel2 = new JLabel("Until:");
	String usn = View.getLogin();
	String userid = null;
	String eventID = null;

	
	//BUTTONS 
	private JButton getDet = new JButton("Get Details");
	private JButton saveChanges = new JButton("Save Changes");
	private final JButton btnRemoveEvent = new JButton("Remove Event");
	static JTextField dateField;

	
	public EditEventView()
	{
		super("Create New Event");
		setSize(420,570);
		setLocation(750,250);
		panel.setLayout (null);

		JLabel label = new JLabel("Date:");
		//JTextField text = new JTextField(20);
		final JFrame f = new JFrame();

		eventDesc.setLineWrap(true);
		eventDesc.setWrapStyleWord(true);
		eventNames.setBounds(50,45,300,20);
		eventDesc.setBounds(50,255,300,100);
		cancel.setBounds(0, 390, 75, 30);
		eventTitleLabel.setBounds(50,20,300,20);
		eventDescLabel.setBounds(50,185,300,100);
		label.setBounds(50,70,300,20);
		//text.setBounds(50,95,300,20);
		//text.setText(MainCalendar.dom);
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
		panel.add(label);
		//panel.add(text);
		panel.add(roomList);
		panel.add(roomListLabel);
		panel.add(timeList1);
		panel.add(timeListLabel1);
		panel.add(timeList2);
		panel.add(timeListLabel2);

		getContentPane().add(panel);
		btnRemoveEvent.setBounds(72, 391, 117, 29);
		
		panel.add(btnRemoveEvent);
		
		getDet.setBounds(186, 391, 117, 29);
		panel.add(getDet);
		
		dateField = new JTextField();
		dateField.setBounds(50, 91, 160, 26);
		panel.add(dateField);
		dateField.setColumns(10);
		
		saveChanges.setBounds(297, 391, 117, 29);
		panel.add(saveChanges);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		try {
			userid = database.queryDB.getId(usn);
			EditEventModel.getEventNames(userid);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	public void aclSave(ActionListener c)
	{
		System.out.println("Save button action Listener");
		saveChanges.addActionListener(c);
	}
	
	public void aclGet(ActionListener d)
	{
		System.out.println("Get Details button Action Listener");
		getDet.addActionListener(d);
	}
			void toggleOff() {
				this.setVisible(false);
				
			}

	}
