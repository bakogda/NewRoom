package clients.resetPassword;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import database.dBV;

public class ResetPasswordModel {

	public ResetPasswordModel(){
		System.out.println("Reset Password model:");
	}
	
	public static void resetPassword(String un, String pw) throws SQLException, ClassNotFoundException {
		String combine = un + pw;
		String hashed = database.hash.sha1(combine);
		
		if(database.queryDB.checkUser(un)){
			//username exists so the password can be reset
			//check to see if new password if the same as old one
			if(database.queryDB.checkUser(un) == true && database.queryDB.checkPass(hashed) == true){
				//entered username and password are the same as what is in the db currently
				System.out.println("you have entered " + un + "'s current password");			
			}
			else {
				//username is correct password is different
				//update user details
				String resetPasswordSQL = "update " + database.createDB.tbl_user + " set PASSWORD='" + hashed + "' where USERNAME='" + un + "'";
				
				Connection conn = DriverManager.getConnection(dBV.JDBC_URL);
				conn.createStatement().execute(resetPasswordSQL);
				System.out.println(un + "'s password is reset!");
			}
		}
		else {
			System.out.println("username incorrect");
		}
	}
}
