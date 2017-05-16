package database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnect {
	public static final String tbl_user = "userdet";
	public static final String tbl_cal = "calendar";
	public static final String tbl_share = "share";
	public static final String tbl_event = "event";
	public static final String tbl_invite = "invite";
	
	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		userTable();
		calendarTable();
		shareTable();
		eventTable();
		inviteTable();
		database.queryDB.main(args);
	}


		public static void userTable() throws ClassNotFoundException, SQLException{
			try {
			//default admin username and password
			String admin = "admin";
			String pwd = "password";
			//default normal username and password
			String user = "user";
			String userPwd = "password";
			//encrypt passwords
			String adminPass = hash.sha1(admin + pwd);
			String userPass = hash.sha1(user + userPwd);
System.out.println("-------- PostgreSQL "
			+ "JDBC Connection Testing ------------");


Class.forName("org.postgresql.Driver");
Connection connection = DriverManager.getConnection(
		"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
		"password");
connection.createStatement().execute("CREATE TABLE IF NOT EXISTS " + tbl_user +" ( USERNAME varchar(32), PASSWORD varchar(50), FIRSTNAME varchar(50), LASTNAME varchar(50), USERTYPE varchar(20), PRIMARY KEY (USERNAME))");
//insert dummy account info
connection.createStatement().execute("insert into " + tbl_user +" values " + "('" + admin + "','"+ adminPass + "','John','Watt','admin')");
connection.createStatement().execute("insert into " + tbl_user +" values " + "('" + user + "','"+ userPass +"','David','Blake','normal')");
System.out.println(tbl_user + " table populated");
}
catch (Exception e) {
System.out.println("Table: " + tbl_user + " exists!");
}

}
public static void calendarTable() throws ClassNotFoundException, SQLException {
	//attempt to create and populate the table
	try {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
						"password");
		connection.createStatement().execute("create table " + tbl_cal +"(C_ID SERIAL, COWNER varchar(32), PRIMARY KEY(C_ID))");
		System.out.println(tbl_cal + " table created");
		
		//add some calendars for existing users
		connection.createStatement().execute(
				"insert into " + tbl_cal +" (COWNER) values " + 
				"('admin'),"+
				"('user')"); 
		System.out.println(tbl_cal + " table populated");
	}
	catch (Exception e) {
		System.out.println("Table: " + tbl_cal + " exists!");
	}
}

//////////////////////////////////////////////////////////////////////////////////

public static void shareTable() throws ClassNotFoundException, SQLException{
	//start creating the database 
	try {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
						"password");
		connection.createStatement().execute("create table " + tbl_share +" (S_ID SERIAL, COWNERREF varchar(32), CREC varchar(32), PRIMARY KEY (S_ID))");
		System.out.println(tbl_share + " table created");
		
		//allow the admin to see the user's calendar
		connection.createStatement().execute(
				"insert into " + tbl_share +" (COWNERREF, CREC) values " + 
				"('user','admin')"); 
		System.out.println(tbl_share + " table populated");
		System.out.println("All ok...");
	}
	catch (Exception e) {
		System.out.println("Table: " + tbl_share + " exists!");
	}
}

//////////////////////////////////////////////////////////////////////////////////

public static void eventTable() throws ClassNotFoundException, SQLException{
	//start creating the database 
	try {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
						"password");
		connection.createStatement().execute("create table " + tbl_event + "(E_ID SERIAL, USERNAME varchar(32), TITLE varchar(50), DATE date,ROOM varchar(50), STARTTIME TIME, ENDTIME TIME, DESCR varchar(200), NOTES varchar(8000), INV_ONE varchar(32), INV_ONE_CONFIRMATION varchar(32), INV_TWO varchar(32), INV_TWO_CONFRIMATION varchar(32), INV_THREE varchar(32), INV_THREE_CONFIRMATION varchar(32), INV_FOUR varchar(32), INV_FOUR_CONFIRMATION varchar(32), INV_FIVE varchar(32), INV_FIVE_CONFIRMATION varchar(32), INV_SIX varchar(32), INV_SIX_CONFIRMATION varchar(32), CREF INTEGER, PRIMARY KEY (E_ID))"); 

		
		System.out.println(tbl_event + " table created");
		
		System.out.println("All ok...");
	}
	catch (Exception e) {
		System.out.println("Table: " + tbl_event + " exists!");
	}	
}

//////////////////////////////////////////////////////////////////////////////////

public static void inviteTable() throws ClassNotFoundException, SQLException{
	try {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(
						"jdbc:postgresql://127.0.0.1:5432/booking", "postgres",
						"password");
		connection.createStatement().execute("create table " + tbl_invite +" (I_ID SERIAL, SERNER varchar(32), RECIEVER varchar(32), E_IDREF INTEGER, MESSAGE varchar(200), PRIMARY KEY (I_ID))");
		System.out.println(tbl_invite + " table created");
		
		System.out.println("All ok...");
	}
	catch (Exception e) {
		System.out.println("Table: " + tbl_invite + " exists!");
	}
}
}

