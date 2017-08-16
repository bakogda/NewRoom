package clients.events;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import admin.View;
import clients.currentShares.CurrentSharesView;
import database.dBV;

public class EventsView extends JFrame
{
	String usn = View.getLogin();
	String userid = null;


	public EventsView() throws ClassNotFoundException, SQLException
	{
		super("My Events");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(1000, 600);
		 ArrayList<String> columnNames = new ArrayList<String>();
		 ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		
		try {
			 userid = database.queryDB.getId(usn);
		}catch (ClassNotFoundException | SQLException e2)
		{
			e2.printStackTrace();
		}
		
		try{
			String SQL_statement = "SELECT TITLE, DATE, ROOM, TO_CHAR(STARTTIME, 'HH24:MI') AS START_TIME, TO_CHAR(ENDTIME, 'HH24:MI') AS END_TIME, DESCR AS DESCRIPTION FROM EVENT WHERE USER_ID = '"
					+ userid + "' AND DATE >= now()";
			System.out.println(SQL_statement);
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

			// create a new statement
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL_statement);
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int cCount = rsmd.getColumnCount();
			for (int i = 1; i <= cCount; i++) {
				columnNames.add(rsmd.getColumnName(i));
			}

			while (resultSet.next()) {
				ArrayList<Object> row = new ArrayList<Object>(cCount);

				for (int i = 1; i <= cCount; i++) {
					row.add(resultSet.getObject(i));
				}
				data.add(row);
				
			}
		}finally
		{
		}
		
		 Vector<String> columnNamesVector = new Vector<String>();
		 Vector<Vector> dataVector = new Vector<Vector>();
			
		
			for (int i = 0; i < data.size(); i++) {
				ArrayList subArray = data.get(i);
				Vector subVector = new Vector();
				for (int j = 0; j < subArray.size(); j++) {
					subVector.add(subArray.get(j));
				}
				dataVector.add(subVector);
			}
		 for (int i = 0; i < columnNames.size(); i++)
				columnNamesVector.add(columnNames.get(i));
		
				 
			 JTable table = new JTable(dataVector, columnNamesVector) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public Class<?> getColumnClass1(int column) {
					return getValueAt(0, column).getClass();
				}

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

				@Override
				public Class getColumnClass(int column) {
					for (int row = 0; row < getRowCount(); row++) {
						Object o = getValueAt(row, column);

						if (o != null) {
							return o.getClass();
						}
					}

					return Object.class;
				}
			};			
		
		

		// Create table with database data
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);


				}


	
	
	
	public static void main(String[] args) {
		CurrentSharesView frame = new CurrentSharesView();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	void toggleOff() {
		this.setVisible(false);
	}

}

	
