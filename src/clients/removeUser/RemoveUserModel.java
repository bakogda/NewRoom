package clients.removeUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.dBV;

public class RemoveUserModel {
	public RemoveUserModel(){
		System.out.println("RemoveUser Model:");
	}
	
	//remove a user from the database
	public static void removeUser(String username) throws SQLException, ClassNotFoundException{
		
		String removeUserSQL = "delete from " + database.JDBConnect.tbl_user + " where USERNAME='" + username + "'";
		
		if(database.queryDB.checkUser(username)){
			//username exists
			Connection conn = DriverManager.getConnection(dBV.JDBC_URL);
			conn.createStatement().execute(removeUserSQL);
			System.out.println("user " + username + " removed!");
		}
		else {
			//username doesn't exist and cant be removed
			System.out.println("username does not exist");
		}
	}
}
