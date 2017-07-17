package clients.addUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.dBV;

public class AddUserModel {
	public AddUserModel(){
		System.out.println("AddUser model: ");
	}
	
	//add a new user to the database
	public static void addUser(String usn, String pwd, String fn, String ln, String typ) throws ClassNotFoundException, SQLException{
		String combine = usn + pwd;
		String hashed = database.hash.sha1(combine);
		String addUserSQL = "insert into " + database.JDBConnect.tbl_user + " values ((SELECT max(USER_ID)+1 FROM USERDET), '" + usn + "', '" + hashed + "', '" + fn + "', '" + ln + "', '" + typ + "')";
		System.out.println(addUserSQL);
		if(!database.queryDB.checkUser(usn)){
			//username does not exist in  the database
			//insert user data into user table
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

			connection.createStatement().execute(addUserSQL);
			System.out.println("User " + usn + " added!");
		}
		else {
			System.out.println("username exists. please choose a different one!");
		}
	}
}
