package clients.mainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditEventWindow extends JFrame {

	public static void main(String[] args) {
		EditEventWindow frameTabel = new EditEventWindow();
	}

	String[] times = { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00" };
	String[] rooms = { "Room 1", "Room 2", "IT Room" };
	JButton createEvent = new JButton("Save");
	JButton deleteEvent = new JButton("Delete");
	JButton cancel = new JButton("Cancel");
	JPanel panel = new JPanel();
	JTextField eventTitle = new JTextField(20);
	JTextArea eventDesc = new JTextArea();
	JLabel eventTitleLabel = new JLabel("Event Title:");
	JLabel eventDescLabel = new JLabel("Event Description:");
	JLabel roomListLabel = new JLabel("Select Room:");
	JLabel timeListLabel1 = new JLabel("From:");
	JComboBox roomList = new JComboBox(rooms);
	JComboBox timeList1 = new JComboBox(times);
	JComboBox timeList2 = new JComboBox(times);
	JLabel timeListLabel2 = new JLabel("Until:");

	EditEventWindow() {
		super("Edit Event");
		setSize(420, 450);
		setLocation(750, 250);
		panel.setLayout(null);

		JLabel label = new JLabel("Select Date:");
		JTextField text = new JTextField(20);
		JButton b = new JButton("Date");
		final JFrame f = new JFrame();
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				text.setText(new DatePicker(f).setPickedDate());
			}
		});

		eventDesc.setLineWrap(true);
		eventDesc.setWrapStyleWord(true);
		eventTitle.setBounds(50, 45, 300, 20);
		eventDesc.setBounds(50, 255, 300, 100);
		createEvent.setBounds(286, 375, 109, 30);
		cancel.setBounds(195, 375, 81, 30);
		deleteEvent.setBounds(7, 375, 81, 30);
		eventTitleLabel.setBounds(50, 20, 300, 20);
		eventDescLabel.setBounds(50, 185, 300, 100);
		label.setBounds(50, 70, 300, 20);
		text.setBounds(50, 95, 200, 20);
		b.setBounds(260, 95, 90, 20);
		roomList.setBounds(50, 150, 200, 20);
		roomListLabel.setBounds(50, 125, 300, 20);
		timeList1.setBounds(110, 188, 75, 20);
		timeListLabel1.setBounds(50, 185, 75, 20);
		timeList2.setBounds(273, 188, 75, 20);
		timeListLabel2.setBounds(225, 185, 75, 20);

		panel.add(createEvent);
		panel.add(cancel);
		panel.add(eventTitle);
		panel.add(eventDesc);
		panel.add(eventTitleLabel);
		panel.add(eventDescLabel);
		panel.add(label);
		panel.add(text);
		panel.add(b);
		panel.add(roomList);
		panel.add(roomListLabel);
		panel.add(timeList1);
		panel.add(timeListLabel1);
		panel.add(timeList2);
		panel.add(timeListLabel2);
		panel.add(deleteEvent);

		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

}
