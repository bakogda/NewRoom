package clients.removeEvent;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import clients.mainPanel.MainCalendar;
import database.dBV;
import javax.swing.JComboBox;

public class RemoveEventView extends JFrame {

	private static final long serialVersionUID = 1L;
	private Button get = new Button("Get Events");
	private Button remove = new Button("Remove");
	private Button cancel = new Button("Cancel");
	static JTextArea detailsArea = new JTextArea(15, 35);
	String label1Text = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
	String date = new String(label1Text);
	final static String newline = "\n";
	final JFrame f = new JFrame();
	private final JLabel lblChooseEventTo = new JLabel("Choose Event to delete : ");
	final static JComboBox<Object> listEvents = new JComboBox<Object>();


	public RemoveEventView() {
		JPanel removeEvent = new JPanel();
		this.setSize(800, 600);
		removeEvent.add(remove);
		removeEvent.add(detailsArea);
		removeEvent.add(cancel);
		
		detailsArea.setEditable(false);
		try {
			detailsArea.setText(null);
			RemoveEventModel.getRecords();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			toggleOff();
			}
		});
		removeEvent.add(get);
		removeEvent.add(lblChooseEventTo);
		removeEvent.add(listEvents);
		getContentPane().add(removeEvent);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setVisible(true);
		
			try {
				RemoveEventModel.getEvent();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		finally
		{
			
		}
			
	}

	public void toggleOff() {
		this.setVisible(false);
	}

	public void toggleOn() {
		this.setVisible(true);
	}

	public void aclRemove(ActionListener c) {
		System.out.println("Remove button action listener: ");
		remove.addActionListener(c);
	}
	
}
