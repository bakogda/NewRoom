package clients.eventNotes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import admin.View;
import clients.currentShares.CurrentSharesView;
import resources.Print;

public class EventNotesView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JComboBox<String> eventNames = new JComboBox<String>();
	static JComboBox<String> ieventNames = new JComboBox<String>();
	JPanel panel = new JPanel();
	static JTextArea notes = new JTextArea();

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

	public EventNotesView() {
		setSize(700, 550);
		setLocation(750, 250);
		notes.setText("");
		
		ieventNames.insertItemAt(null, 0);
		String eName = (String) eventNames.getSelectedItem();
		String eiName = (String) ieventNames.getSelectedItem();

		panel.setLayout(null);
		eventNamesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		eventNamesLabel.setBounds(66, 65, 123, 59);
		panel.add(eventNamesLabel);
		eventNames.setBounds(220, 82, 100, 27);
		panel.add(eventNames);
		ieventNames.setBounds(220, 49, 100, 27);
		panel.add(ieventNames);
		ieventNamesLabel.setBounds(66, 48, 153, 27);
		panel.add(ieventNamesLabel);
		save.setBounds(399, 434, 113, 29);
		panel.add(save);
		print.setBounds(198, 434, 200, 29);
		notesLabel.setBounds(14, 122, 95, 16);
		panel.add(notesLabel);
		cancel.setBounds(517, 434, 86, 29);
		panel.add(cancel);
		getContentPane().add(panel);
		getNotes.setBounds(350, 66, 153, 29);
		panel.add(print);
		panel.add(getNotes);
		notes.setSize(500, 500);
		notes.setEditable(true);
		notes.setLineWrap(true);
		notes.setWrapStyleWord(true);
		notes.setBounds(121, 122, 482, 288);
		panel.add(notes);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);

		try {
			userid = database.queryDB.getId(usn);
			EventNotesModel.getEvent(userid);
			EventNotesModel.getEvents(usn);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});

		print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Print.printComponent(notes);
			}
		});

	}

	public void aclSave(ActionListener c) {
		System.out.println("Save button action Listener");
		save.addActionListener(c);
	}

	public void aclGet(ActionListener d) {
		System.out.println("Get / Create button Action Listener");
		getNotes.addActionListener(d);
	}

	void toggleOff() {
		this.setVisible(false);
	}
	 public static void main(String[] args)
	    {
	        EventNotesView frame = new EventNotesView();
	        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
	        frame.pack();
	        frame.setVisible(true);
	    }
}
