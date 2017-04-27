package clients.editUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import database.dBV;

public class EditUserModel {
	
	public EditUserModel(){
		System.out.println("EditUser model: ");
	}
	
	//add a new user to the database
	public static void editUser(String usn, String fn, String ln, String typ) throws ClassNotFoundException, SQLException{
		
		String updateUserSQL = "update " + database.JDBConnect.tbl_user + " set FIRSTNAME='" + fn + "', LASTNAME='" + ln + "', USERTYPE='" + typ + "' where USERNAME='" + usn + "'";
		
		//it would be nice to compare the current details with the new ones to see if any change needs to take place at all
		
		if(database.queryDB.checkUser(usn)){
			//user name does exist in the database
			//insert user data into user table
			Connection connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
					"password");		
			System.out.println("here");
			connection.createStatement().execute(updateUserSQL);
			System.out.println("here2");
			connection.close();
			System.out.println("should have closed");
			System.out.println(usn + "'s details have been changed!");
		}
		else {
			System.out.println("Entered username is incorrect. please choose a different one!");
		}
	}
	
	
}
