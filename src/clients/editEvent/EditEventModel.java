package clients.editEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.dBV;

public class EditEventModel 
{
	public EditEventModel()
	{
		System.out.println("Edit Event Model: ");
	}
	
	public static String getDetails(String userid) throws SQLException
	{
		String eName = (String)EditEventView.eventNames.getSelectedItem();
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		String SQL_statement = "SELECT E_ID, DATE,ROOM, TO_CHAR(STARTTIME, 'HH24:MI') AS STARTTIME, TO_CHAR(ENDTIME, 'HH24:MI') AS ENDTIME, DESCR FROM EVENT WHERE USER_ID='"+ userid +"'AND TITLE='"+ eName +"'";
		System.out.println(SQL_statement);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		
		while(resultSet.next())
		{
			String roomEvent = resultSet.getString("ROOM");
			String timeOne = resultSet.getString("STARTTIME");
			String timeTwo = resultSet.getString("ENDTIME");
			String descr   = resultSet.getString("DESCR");
			String dateEvent = resultSet.getString("DATE");
			String eID = resultSet.getString("E_ID");
			EditEventView.roomList.setSelectedItem(roomEvent);
			EditEventView.timeList1.setSelectedItem(timeOne);
			EditEventView.timeList2.setSelectedItem(timeTwo);
			EditEventView.eventDesc.setText(descr);
			EditEventView.dateField.setText(dateEvent);
			return eID;
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}
	
	public static String getEventNames(String userid) throws SQLException
	{
		String SQL_statement = "SELECT E_ID, TITLE, DATE, ROOM, TO_CHAR(STARTTIME, 'HH:MI'), TO_CHAR(ENDTIME, 'HH:MI'), DESCR FROM EVENT WHERE USER_ID='"+ userid +"' AND DATE >= NOW()";
		System.out.println(SQL_statement);
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		
		//create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		while(resultSet.next())
		{
			EditEventView.eventNames.addItem(resultSet.getString("TITLE"));
		}
		
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}
	
	public static String saveChanges(String dateEvent, String timeFr, String timeTo, String room, String descr, String userid, String eName) throws SQLException
	{
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		String SQL_statement = "UPDATE EVENT SET DATE='"+ dateEvent +"', STARTTIME ='"+ timeFr +"', ENDTIME='"+ timeTo +"', ROOM='"+ room +"', DESCR='"+ descr +"' WHERE USER_ID='"+ userid +"'AND TITLE='"+ eName +"'";
		System.out.println(SQL_statement);
		Statement statement = connection.createStatement();
		 statement.executeUpdate(SQL_statement);

		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
	}
}
