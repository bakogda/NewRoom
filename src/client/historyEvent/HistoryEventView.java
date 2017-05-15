package client.historyEvent;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import admin.View;

public class HistoryEventView extends JFrame{
	/**
	 * Bako Gdaniec 
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private static JTextArea events = new JTextArea(15,35);
	private JScrollPane scroll;
	private Button cancel = new Button("Cancel");
	final static String newline = "\n";
	String usn = View.getLogin();
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	String date2 = format1.format(cal.getTime());


	public HistoryEventView() throws SQLException, ClassNotFoundException{
		JPanel view = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(550, 550);
		events.setSize(300,500);
		events.setEditable(false);
		try
		{
			histEvent(date2, usn);
		}catch (SQLException e1)
		{
			e1.printStackTrace();
		}
			
		/**
		String title = database.queryDB.getQuery("SELECT TITLE FROM EVENT WHERE DATE<'" + date2 +"'AND USERNAME='" + usn +"'");
		String st = database.queryDB.getQuery("SELECT STARTTIME FROM EVENT WHERE DATE<'" + date2 +"'AND USERNAME='" + usn +"'");
		String et = database.queryDB.getQuery("SELECT ENDTIME FROM EVENT WHERE DATE<'" + date2 +"'AND USERNAME='" + usn +"'");
		String room = database.queryDB.getQuery("SELECT ROOM FROM EVENT WHERE DATE<'" + date2 +"'AND USERNAME='" + usn +"'");
		String desc = database.queryDB.getQuery("SELECT DESCR FROM EVENT WHERE DATE<'" + date2 +"'AND USERNAME='" + usn +"'");
		String date = database.queryDB.getQuery("SELECT DATE FROM EVENT WHERE DATE<'" + date2 + "'AND USERNAME='" + usn + "'");	
		
		
		if(database.queryDB.histEvent(date2, usn) == false) {
			events.setText("Date: " + date
					+"\n" + 
					"Title: " + title+ "\n" + 
					"Start time: " + st + "\n" +
					"End time: " + et + "\n" +
					"Room: " + room + "\n" + 
					"Description: " + desc + "\n"					
					);

		}
		else { 
			events.setText("There are NO previous events to view");
		}
		
		**/
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

	public static void histEvent(String date2, String usn) throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
						"password");
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM event WHERE DATE<'" + date2 + "'AND USERNAME='" + usn +"'");
		ResultSetMetaData rsmd = resultSet.getMetaData(); 

		//loop through number of columns
		int colCount = rsmd.getColumnCount();
		System.out.println(colCount);
		
		for(int i = 1; i<= colCount; i++) events.append( rsmd.getColumnName(i)+" | ");
		while(resultSet.next()) {
			events.append(newline);
			for(int x = 1; x <= colCount; x++) events.append(resultSet.getString(x) + " | ");
		}
		
		if (statement != null) statement.close();
		if (connection != null) connection.close();
	}
	
	public void toggleOff(){
		events.setText(null);
		this.setVisible(false);
	}
}