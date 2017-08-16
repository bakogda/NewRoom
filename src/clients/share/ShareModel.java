package clients.share;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import database.dBV;

public class ShareModel {
	public ShareModel() {
		System.out.println("Share Model: ");
	}

	public static String checkEventId(String ename, String userid) throws SQLException, ClassNotFoundException {

		String checkEventSQL = "SELECT EVENT_ID, USER_ID, TITLE FROM EVENT WHERE USER_ID='" + userid + "' AND TITLE='"
				+ ename + "'";
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		System.out.println(checkEventSQL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(checkEventSQL);
		while (resultSet.next()) {
			String eventID = resultSet.getString("EVENT_ID");
			return eventID;
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}

	public static void inviteUser(String eventID, String userid, String name)
			throws SQLException, ClassNotFoundException {

		String inviteSQL = ("INSERT INTO INVITE VALUES((SELECT max(INVITE_ID)+1 FROM INVITE),'" + eventID + "','"
				+ userid + "','" + name + "')");
		System.out.println(inviteSQL);
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		connection.createStatement().execute(inviteSQL);
		if (connection != null)
			connection.close();
	}

	public static void removeUser(String eventID, String userName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		String SQL_statement = ("DELETE FROM INVITE WHERE EVENT_ID ='" + eventID + "' AND USERNAME_INVITED='" + userName
				+ "' ");
		System.out.println(SQL_statement);
		Statement statement = connection.createStatement();
		statement.executeUpdate(SQL_statement);
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
	}
	
	String getFirstInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		String s = "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid
				+ "'AND EVENT.EVENT_ID= '" + eventID + "' and TITLE = '" + eName + "' LIMIT 1";
		System.out.println(s);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(s);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (resultSet.next()) {
			for (int x = 1; x <= colCount; x++) {
				if (resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}

	String getSecondInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();

		String s = "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid
				+ "'AND EVENT.EVENT_ID='" + eventID + "' and TITLE = '" + eName + "'OFFSET 1 LIMIT 1";
		System.out.println(s);
		ResultSet resultSet = statement.executeQuery(s);

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (resultSet.next()) {
			for (int x = 1; x <= colCount; x++) {
				if (resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}

	String getThirdInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();

		String s = "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid
				+ "'AND EVENT.EVENT_ID='" + eventID + "' and TITLE = '" + eName + "'OFFSET 2 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (resultSet.next()) {
			for (int x = 1; x <= colCount; x++) {
				if (resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}

	String getFourthInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();

		String s = "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid
				+ "'AND EVENT.EVENT_ID='" + eventID + "' and TITLE = '" + eName + "'OFFSET 3 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (resultSet.next()) {
			for (int x = 1; x <= colCount; x++) {
				if (resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}

	String getFithInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		Statement statement = connection.createStatement();

		String s = "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid
				+ "'AND EVENT.EVENT_ID='" + eventID + "' and TITLE ='" + eName + "'OFFSET 4 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (resultSet.next()) {
			for (int x = 1; x <= colCount; x++) {
				if (resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}

	String getSixthInvitation(String userid, String eventID, String eName) throws SQLException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		Statement statement = connection.createStatement();

		String s = "SELECT USERNAME_INVITED,TITLE FROM INVITE,EVENT WHERE event.user_id = '" + userid
				+ "'AND EVENT.EVENT_ID='" + eventID + "' and TITLE = '" + eName + "'OFFSET 5 LIMIT 1";
		System.out.println(s);

		ResultSet resultSet = statement.executeQuery(s);

		ResultSetMetaData rsmd = resultSet.getMetaData();
		int colCount = rsmd.getColumnCount();

		while (resultSet.next()) {
			for (int x = 1; x <= colCount; x++) {
				if (resultSet.getString(x) != null) {
					return resultSet.getString(x);
				}
			}
		}
		if (statement != null)
			statement.close();
		if (connection != null)
			connection.close();
		return null;

	}
}
