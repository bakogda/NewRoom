package clients.editUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import database.dBV;

public class EditUserModel {

	public EditUserModel() {
		System.out.println("EditUser model: ");
	}

	// add a new user to the database
	public static void editUser(String usn, String fn, String ln, String typ)
			throws ClassNotFoundException, SQLException {

		String updateUserSQL = "update " + database.JDBConnect.tbl_user + " set FIRSTNAME='" + fn + "', LASTNAME='" + ln
				+ "', USERTYPE='" + typ + "' where USERNAME='" + usn + "'";

		// it would be nice to compare the current details with the new ones to
		// see if any change needs to take place at all

		if (database.queryDB.checkUser(usn)) {
			// user name does exist in the database
			// insert user data into user table
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

			System.out.println("here");
			connection.createStatement().execute(updateUserSQL);
			System.out.println("here2");
			connection.close();
			System.out.println("should have closed");
			System.out.println(usn + "'s details have been changed!");
		} else {
			System.out.println("Entered username is incorrect. please choose a different one!");
		}
	}

	public static String getFN(String usn) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");
		Statement statement = connection.createStatement();

		String s = "SELECT FIRSTNAME FROM " + database.JDBConnect.tbl_user + " WHERE USERNAME = '" + usn + "'";
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

	public static String getLN(String usn) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");
		Statement statement = connection.createStatement();

		String s = "SELECT LASTNAME FROM " + database.JDBConnect.tbl_user + " WHERE USERNAME = '" + usn + "'";
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

	public static String getUT(String usn) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
				"password");
		Statement statement = connection.createStatement();

		String s = "SELECT USERTYPE FROM " + database.JDBConnect.tbl_user + " WHERE USERNAME = '" + usn + "'";
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
