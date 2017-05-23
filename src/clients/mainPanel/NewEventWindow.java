package clients.mainPanel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import admin.View;

class NewEventWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		NewEventWindow frameTabel = new NewEventWindow();
		
	}

	String[] times = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
	String [] rooms = {"Room 1","Room 2","IT Room"};

	JButton createEvent = new JButton("Create Event");
	JButton cancel = new JButton("Cancel");
	JPanel panel = new JPanel();
	JTextField eventTitle = new JTextField(20);
	JTextArea eventDesc = new JTextArea();
	//JTextArea eventNotes = new JTextArea();
	JLabel eventTitleLabel = new JLabel("Event Title:");
	JLabel eventDescLabel = new JLabel("Description:");
	//JLabel eventNotesLabel = new JLabel("Event Notes:");
	JLabel roomListLabel = new JLabel("Room:");
	JLabel timeListLabel1 = new JLabel("From:");
	JComboBox roomList = new JComboBox(rooms);
	JComboBox timeList1 = new JComboBox(times);
	JComboBox timeList2 = new JComboBox(times);
	JLabel timeListLabel2 = new JLabel("Until:");
	String usn = View.getLogin();

	NewEventWindow() {
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
	//	eventNotes.setLineWrap(true);
	//	eventNotes.setWrapStyleWord(true);
		eventTitle.setBounds(50,45,300,20);
		eventDesc.setBounds(50,255,300,100);
	//	eventNotes.setBounds(50, 400, 300, 100);
		

		createEvent.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {	
				String title = eventTitle.getText();
				String date = label1.getText();
				String room = (String)roomList.getSelectedItem();
				String st = (String)timeList1.getSelectedItem();
				String et = (String)timeList2.getSelectedItem();
				//String notes = eventNotes.getText();
				String desc = eventDesc.getText();	
				String userid = null;
				try {
					userid = database.queryDB.getId(usn);
					System.out.println(userid);
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
						
				try {
					Class.forName("org.postgresql.Driver");
					Connection connection = DriverManager.getConnection(
									"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
									"password");
					
					Statement statement1 = connection.createStatement();
					ResultSet resultSet1 =statement1.executeQuery("SELECT * FROM event WHERE DATE='"+ date +"'AND STARTTIME BETWEEN '"+ st +"'AND '"+ et +"'");
	
					int result = st.compareTo(et);
					
					if(resultSet1.next())
					{
						JOptionPane.showMessageDialog(null, new Object[] {
							    "Event already exists at this time in the '"+ room +"' Please Choose different Room or Time"			    
							    });
						if(result < 0)
						{
							JOptionPane.showMessageDialog(null, new Object[] {
								    "The End time can not be earlier or same as Start Time"		    
								    });					
						}
						}else
					{
					database.queryDB.addEvent(userid, title, date, room, st, et, desc);
					
					}
				} catch (SQLException e1) {
					System.out.println("Error!");
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				MainCalendar.refreshCalendar(MainCalendar.realMonth, MainCalendar.realYear);
				setVisible(false);
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
		createEvent.setBounds(278,510,117,30);
		cancel.setBounds(10, 510, 75, 30);
		eventTitleLabel.setBounds(50,20,300,20);
		eventDescLabel.setBounds(50,185,300,100);
		//eventNotesLabel.setBounds(50, 335, 300, 100);
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


		panel.add(createEvent);
		panel.add(cancel);
		panel.add(eventTitle);
		panel.add(eventDesc);
		panel.add(eventTitleLabel);
		panel.add(eventDescLabel);
		//panel.add(eventNotes);
		//panel.add(eventNotesLabel);
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
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
	private void toggleOff() {
		this.setVisible(false);
	}

}
