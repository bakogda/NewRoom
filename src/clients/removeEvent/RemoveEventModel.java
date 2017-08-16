package clients.removeEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import clients.eventNotes.EventNotesView;
import database.dBV;

public class RemoveEventModel {

	public RemoveEventModel() {
		System.out.println("RemoveUser Model:");
	}

	public static void getRecords() throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("SELECT * FROM " + database.JDBConnect.tbl_event + "");
		System.out.println(resultSet);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		
		int cCount = rsmd.getColumnCount();

		for (int i = 1; i <= cCount; i++){
			RemoveEventView.detailsArea.append(rsmd.getColumnName(i) + " | ");
		}
		while (resultSet.next()) {
			RemoveEventView.detailsArea.append(RemoveEventView.newline);
			for (int x = 1; x <= cCount; x++)
				RemoveEventView.detailsArea.append(resultSet.getString(x) + " | ");
		}

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
	
	public static String getEvent() throws SQLException {
		RemoveEventView.listEvents.removeAllItems();
		String SQL_statement = "SELECT TITLE FROM EVENT";
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		// create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		while (resultSet.next()) {
		RemoveEventView.listEvents.addItem(resultSet.getString(1));
		}

		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;
	}

	public static void removeEvent(String event) throws SQLException {
		String SQL_statement = "DELETE FROM EVENT WHERE TITLE ='" + event + "'";
		System.out.println(SQL_statement);

		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();
		statement.executeUpdate(SQL_statement);
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
}