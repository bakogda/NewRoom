package clients.viewUsers;

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

import database.dBV;

public class ViewUsersView extends JFrame{

	/**
	 * Meow
	 */
	private static final long serialVersionUID = 1L;
	//create all objects on which will be on the panel
	private JLabel usersLabel = new JLabel("Current Users in the database: ");
	private static JTextArea users = new JTextArea(15,35);
	private JScrollPane scroll;
	private Button cancel = new Button("Cancel");
	final static String newline = "\n";
	
	
	public ViewUsersView(){
		JPanel resetView = new JPanel(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setSize(450, 400);
		users.setEditable(false);
		try {
			viewUsers();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		GridBagConstraints c = new GridBagConstraints();
		scroll = new JScrollPane(users, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		c.insets = new Insets(5,5,5,5);//padding
	
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		resetView.add(usersLabel, c);
		
		c.gridx = 0;
		c.gridy = 1;
		resetView.add(scroll, c);
		
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		resetView.add(cancel, c);
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				toggleOff();
			}
		});
		
		
		this.add(resetView);
	}
	
	public static void viewUsers() throws SQLException{
		String SQL_statement = "select username, firstname , lastname from " + database.JDBConnect.tbl_user;
		
		Connection connection = DriverManager.getConnection(
				"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");
		
		//create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int cCount = rsmd.getColumnCount();
//		System.out.println(cCount);

		//print all user information to the serial monitor
		//this needs to be changed to print to a textarea.
		for(int i = 1; i<= cCount; i++) users.append( rsmd.getColumnName(i)+" | ");
		while(resultSet.next()) {
			users.append(newline);
			for(int x = 1; x <= cCount; x++) users.append(resultSet.getString(x) + " | ");
		}
		
		if (statement != null) statement.close();
		if (connection != null) connection.close();
	}
	
	
	public void toggleOff(){
		users.setText(null);
		this.setVisible(false);
	}
	
	public void toggleOn(){
		this.setVisible(true);
	}
	
}

