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

public class RemoveEventView extends JFrame{


	private static final long serialVersionUID = 1L;
	
	//create all objects on which will be on the panel
	private JButton b = new JButton("Select Date");
	private Button d = new Button("Get Events");
	private Button remove = new Button("Remove");
	private Button cancel = new Button("Cancel");
	private static JTextArea detailsArea = new JTextArea(15,35);
	String label1Text = MainCalendar.yod + "-" + MainCalendar.moy + "-" + MainCalendar.dom;
	JLabel label1 = new JLabel(label1Text);
	String date = new String(label1Text);
	final static String newline = "\n";
	final JFrame f = new JFrame();
	
	public RemoveEventView(){
		JPanel removeEvent = new JPanel();
		detailsArea.setEditable(false);
		
		this.setSize(450, 300);
		label1.setBounds(50,95,200,20);
		b.setBounds(50,70,300,20);

	
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String means = new clients.mainPanel.DatePicker(f).setPickedDate();
				try {
					getRecords(means);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		d.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try{
				detailsArea.setText(null);
				detailsArea.setText(getRecords(label1Text));
			}catch (SQLException e1)
			{
				
			}
		}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
	
		removeEvent.add(b);
		removeEvent.add(label1);
		removeEvent.add(cancel);
		removeEvent.add(d);
		removeEvent.add(detailsArea);
		
		getContentPane().add(removeEvent);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setVisible(true);

	}
	public void toggleOff(){
		this.setVisible(false);
	}
	
	public void toggleOn(){
		this.setVisible(true);
	}
	

	public void aclRemove(ActionListener controller) {
		System.out.println("Remove button action listener: ");
		remove.addActionListener(controller);
	}
	
	
	public static String getRecords(String means) throws SQLException
	{
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
	
		Statement statement = connection.createStatement();
		
		String s= "SELECT * FROM " + database.JDBConnect.tbl_event + " WHERE date = '" + means + "'";
		System.out.println(s);
		ResultSet resultSet = statement.executeQuery(s);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int cCount = rsmd.getColumnCount();
		System.out.println(cCount);

		//print all user information to the serial monitor
		//this needs to be changed to print to a textarea.
		for(int i = 1; i<= cCount; i++) detailsArea.append( rsmd.getColumnName(i)+" | ");
		while(resultSet.next()) {
			detailsArea.append(newline);
			for(int x = 1; x <= cCount; x++) detailsArea.append(resultSet.getString(x) + " | ");
		}
		
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return s;
	}
	}

