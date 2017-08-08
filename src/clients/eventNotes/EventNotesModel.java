package clients.eventNotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import database.dBV;

public class EventNotesModel {

	public EventNotesModel()
	{
		System.out.println("Event Notes Model: ");
	}
	
	public static String checkEventID(String eiName, String eName) throws SQLException, ClassNotFoundException
	{
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();
			String SQL_statement = ("SELECT E_ID FROM EVENT WHERE TITLE ='"+ eiName +"' OR TITLE ='"+ eName +"'");
			ResultSet resultSet = statement.executeQuery(SQL_statement);
			System.out.println(SQL_statement);

			while(resultSet.next())
			{
				String eventID = resultSet.getString("E_ID");
				return eventID;
			}
			if (resultSet != null) resultSet.close();
			if (statement != null) statement.close();
			if (connection != null) connection.close();
			return null;
	}
	
	public static String saveNotes(String userid, String eventID, String notesEnc) throws SQLException,ClassNotFoundException
	{
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		String SQL_statement = ("DO $do$ BEGIN IF EXISTS (SELECT * FROM NOTES WHERE USER_ID = '"+ userid +"' AND EVENT_ID ='"+ eventID +"') THEN UPDATE NOTES SET NOTES = '"+ notesEnc +"' WHERE USER_ID = '"+ userid +"' AND EVENT_ID ='"+ eventID +"'; ELSE INSERT INTO NOTES VALUES((SELECT max(NOTES_ID)+1 FROM NOTES), '"+ eventID +"', '"+ userid + "','1','"+ notesEnc +"'); END IF; END $do$");
		System.out.println(SQL_statement);
		Statement statement = connection.createStatement();
		statement.executeUpdate(SQL_statement);
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}
	
	public static String getNotes(String eventID, String userid) throws SQLException,ClassNotFoundException
	{
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
		Statement statement = connection.createStatement();
		
		String s= "SELECT NOTES FROM " + database.JDBConnect.tbl_notes + " WHERE EVENT_ID='" + eventID + "' AND NOTES.USER_ID='"+ userid +"'";
		ResultSet resultSet = statement.executeQuery(s);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();
		
		if (resultSet.next())
		{
			String notesToDecrypt = resultSet.getString("NOTES") ;
			return notesToDecrypt;
		}
	
		
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}
	
	public static String getEvent(String userid) throws SQLException
	{
		String SQL_statement = "SELECT TITLE,E_ID FROM EVENT WHERE USER_ID ='"+ userid +"'";
		
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
		//create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		while(resultSet.next())
		{
			EventNotesView.eventNames.addItem(resultSet.getString(1));
			
		}
			
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}
	
	public static String getEvents(String usn) throws SQLException
	{
		String SQL_statement = "SELECT TITLE,INVITE_ID FROM INVITE,EVENT WHERE EVENT_ID = E_ID AND USERNAME_INVITED='"+ usn +"'";
		
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		
		//create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		while(resultSet.next())
		{
			EventNotesView.ieventNames.addItem(resultSet.getString(1));
		}
		
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}

}
