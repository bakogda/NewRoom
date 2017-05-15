package clients.mainPanel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import admin.View;

class ShowEventWindow extends JFrame {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		ShowEventWindow frameTabel = new ShowEventWindow();
	}

	String[] times = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
	String [] rooms = {"Room 1","Room 2","IT Room"};

	
	JButton OK = new JButton("OK!");
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

	ShowEventWindow() throws SQLException, ClassNotFoundException{
		super("Show Event");
		setSize(420,450);
		setLocation(750,250);
		panel.setLayout (null);

		JLabel label = new JLabel("Date:");
		String label1Text = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
		JLabel label1 = new JLabel(label1Text);
		String titleLabel = database.queryDB.getQuery("SELECT TITLE FROM EVENT WHERE DATE='" + label1Text + "'" );
		JLabel eventTitle = new JLabel(titleLabel);
		//String roomText = database.queryDB.getQuery("SELECT ROOM FROM EVENT WHERE DATE='" + label1Text + "'");
		//JLabel roomLabel = new JLabel(roomText);
		
		
		
		//JTextField text = new JTextField(20);
		final JFrame f = new JFrame();

		eventDesc.setLineWrap(true);
		eventDesc.setWrapStyleWord(true);
		eventNotes.setLineWrap(true);
		eventNotes.setWrapStyleWord(true);
		eventDesc.setBounds(50,255,300,100);
		eventNotes.setBounds(50, 400, 300, 100);
		eventTitle.setBounds(50,45,300,20);
		eventDesc.setBounds(50,255,300,100);
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String username = usn;
				String title = eventTitle.getText();
				String date = label1.getText();
				String room = (String)roomList.getSelectedItem();
				String st = (String)timeList1.getSelectedItem();
				String et = (String)timeList2.getSelectedItem();
				String desc = eventDesc.getText();	
				String notes = eventNotes.getText();

				try {
					database.queryDB.addEvent(username, title, date, room, st, et, desc, notes);
					database.queryDB.query("SELECT * FROM event");
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

		OK.setBounds(10, 375, 75, 30);
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



		panel.add(OK);
		panel.add(eventTitle);
	//	panel.add(roomLabel);
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
	//	setDefaultCloseOperation(JButton.ABORT);
		setVisible(true);

	}

}
