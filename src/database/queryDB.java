package database;


import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;


public class queryDB {
	public static final String SQL_statement = "SELECT * FROM " + database.JDBConnect.tbl_user;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		//this code is used by the login table
		//connect to the database
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);


		//create a new statement
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int cCount = rsmd.getColumnCount();
		System.out.println(cCount);

		//print all usernames and encrypted passwords to the serial monitor
		for(int i = 1; i<= cCount; i++) System.out.format("%28s", rsmd.getColumnName(i)+" | ");
		while(resultSet.next()) {
			System.out.println("");
			for(int x = 1; x <= cCount; x++) System.out.format("%25s", resultSet.getString(x) + " | ");
		}

		if (statement != null) statement.close();
		if (connection != null) connection.close();
	}


	//checks if the username exists in the database, returns true if it does
	public static boolean checkUser(String user) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		//loop through number of columns
		int colCount = rsmd.getColumnCount();

		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x).equals(user)) {
					return true;
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();

		return false;
	}

	public static boolean checkPass(String pass) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(SQL_statement);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		//loop through the number of columns
		int colCount = rsmd.getColumnCount();
		System.out.println(colCount);
		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x).equals(pass)) {
					System.out.println(resultSet);
					return true;
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return false;
	}

	//a useful template
	//redundant code at the moment....
	public static void query(String theQuery) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(theQuery);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		//loop through the number of columns
		int colCount = rsmd.getColumnCount();
		for(int i = 1; i<= colCount; i++) System.out.format("%15s", rsmd.getColumnName(i)+" | ");
		while(resultSet.next()) {
			System.out.println("");
			for(int x = 1; x <= colCount; x++) System.out.format("%15s", resultSet.getString(x) + " | ");
		}

		if (statement != null) statement.close();
		if (connection != null) connection.close();

	}

	public static void addEvent(String userid, String title, String date, String room, String st, String et, String desc) throws ClassNotFoundException, SQLException{
		String addEventSQL = "insert into " + database.JDBConnect.tbl_event + "(USER_ID, TITLE, DATE, ROOM, STARTTIME, ENDTIME, DESCR) values ('" + userid + "','" + title + "','" + date + "','" + room + "','" + st +"','" + et + "','" + desc + "')";

		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

		connection.createStatement().execute(addEventSQL);
		System.out.println("Event with title " + title + " added!");
	}
	
	public static String getId(String usn) throws ClassNotFoundException, SQLException
	{
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT USER_ID FROM userdet WHERE USERNAME='"+ usn +"'");
		while(resultSet.next())
		{
			String userid = resultSet.getString(1);
			return userid;
		}
		return null;

	}



	public static boolean isEvent(String date) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM event WHERE DATE='" + date + "'");
		ResultSetMetaData rsmd = resultSet.getMetaData(); 

		//loop through number of columns
		int colCount = rsmd.getColumnCount();
		while(resultSet.next()) {
			for(int x = 1; x <= colCount; x++) 
			{
				if(resultSet.getString(x).equals(date)) {
					return true;
				}
			}
		}
		if (statement != null) statement.close();
		if (connection != null) connection.close();

		return false;
	}
	
	public static boolean histEvent(String date, String username) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM event WHERE DATE<'" + date + "'AND USERNAME='" + username +"'");
		ResultSetMetaData rsmd = resultSet.getMetaData(); 

		//loop through number of columns
		int colCount = rsmd.getColumnCount();
		System.out.println(colCount);
		System.out.println("Event with title " + username + " added!");
		if (statement != null) statement.close();
		if (connection != null) connection.close();

		return false;
	}

	public static String getQuery(String theQuery) throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(theQuery);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		//loop through the number of columns
		int colCount = rsmd.getColumnCount();
		for(int i = 1; i<= colCount; i++)
			while(resultSet.next()) {
				for(int x = 1; x <= colCount; x++) 
				{
					String p = resultSet.getString(x);
					return p;
				}
			}

		if (statement != null) statement.close();
		if (connection != null) connection.close();
		return null;

	}

}

