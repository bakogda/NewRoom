package clients.viewUsers;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import database.dBV;

public class ViewUsersModel {
	public ViewUsersModel(){
		System.out.println("adding viewusers model");
	}
	
//	public static void viewUsers() throws SQLException{
//		String SQL_statement = "select * from " + database.createDB.tbl_user;
//		
//		Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
//		
//		//create a new statement
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery(SQL_statement);
//		ResultSetMetaData rsmd = resultSet.getMetaData();
//		int cCount = rsmd.getColumnCount();
////		System.out.println(cCount);
//
//		//print all user information to the serial monitor
//		//this needs to be changed to print to a textarea.
//		for(int i = 1; i<= cCount; i++) System.out.format("%20s", rsmd.getColumnName(i)+" | ");
//		while(resultSet.next()) {
//			System.out.println("");
//			for(int x = 1; x <= cCount; x++) System.out.format("%20s", resultSet.getString(x) + " | ");
//		}
//		
//		if (statement != null) statement.close();
//		if (connection != null) connection.close();
//	}
}
