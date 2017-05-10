package clients.mainPanel;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ViewEvents extends JFrame{
	/**
	 * Bako Gdaniec 
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private static JTextArea events = new JTextArea(15,35);
	private JScrollPane scroll;
	private Button cancel = new Button("Cancel");
	final static String newline = "\n";

	public ViewEvents() throws SQLException, ClassNotFoundException{
		JPanel view = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(450, 400);
		events.setEditable(false);
		
		String date = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
		String date2 = MainCalendar.theDate;
		String title = database.queryDB.getQuery("SELECT TITLE FROM EVENT WHERE DATE='" + date2 +"'");
		String st = database.queryDB.getQuery("SELECT STARTTIME FROM EVENT WHERE DATE='" + date2 +"'");
		String et = database.queryDB.getQuery("SELECT ENDTIME FROM EVENT WHERE DATE='" + date2 +"'");
		String room = database.queryDB.getQuery("SELECT ROOM FROM EVENT WHERE DATE='" + date2 +"'");
		String desc = database.queryDB.getQuery("SELECT DESCR FROM EVENT WHERE DATE='" + date2 +"'");
			
		
		
		if(database.queryDB.isEvent(date2) == true) {
			events.setText("Date: " + date2
					+"\n" + 
					"Title: " + title+ "\n" + 
					"Start time: " + st + "\n" +
					"End time: " + et + "\n" +
					"Room: " + room + "\n" + 
					"Description: " + desc + "\n"					
					
					);

		}
		else { 
			events.setText("There are NO events on this day");
		}
		
		GridBagConstraints c = new GridBagConstraints();
		scroll = new JScrollPane(events, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.insets = new Insets(5,5,5,5);//padding
		c.gridx = 0;
		c.gridy = 0;
		view.add(scroll, c);
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		view.add(cancel, c);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		this.add(view);
	}
	public static void viewEvents(){
		//does something
	}

	public void toggleOff(){
		events.setText(null);
		this.setVisible(false);
	}
}