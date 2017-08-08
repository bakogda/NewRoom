package clients.share;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.dBV;

public class ShareModel 
{
	public ShareModel()
	{
		System.out.println("Share Model: ");
	}
	
	
	public static String checkEventId(String ename, String userid) throws SQLException, ClassNotFoundException
	{
		
		String checkEventSQL = "SELECT E_ID, USER_ID, TITLE FROM EVENT WHERE USER_ID='" +userid+"' AND TITLE='"+ ename +"'";
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		System.out.println(checkEventSQL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(checkEventSQL);
		while(resultSet.next())
		{
			String eventID = resultSet.getString("E_ID");
			return eventID;
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;
		
	}
	public static void inviteUser(String eventID, String userid, String name ) throws SQLException, ClassNotFoundException
	{
		
		String inviteSQL = ("INSERT INTO INVITE VALUES((SELECT max(INVITE_ID)+1 FROM INVITE),'"+ eventID +"','"+ userid +"','"+ name +"')");
		System.out.println(inviteSQL);
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		connection.createStatement().execute(inviteSQL);
		

	}
}
