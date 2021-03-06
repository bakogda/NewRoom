package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnect {
	public static final String tbl_user = "userdet";
	public static final String tbl_notes = "notes";
	public static final String tbl_event = "event";
	public static final String tbl_invite = "invite";

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		userTable();
		eventTable();
		inviteTable();
		notesTable();
		database.queryDB.main(args);
	}

	public static void userTable() throws ClassNotFoundException, SQLException {
		try {
			// default admin username and password
			String admin = "admin";
			String pwd = "password";
			// default normal username and password
			String user = "user";
			String userPwd = "password";
			// encrypt passwords
			String adminPass = hash.sha1(admin + pwd);
			String userPass = hash.sha1(user + userPwd);
			System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");
			
			
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

			connection.createStatement().execute("CREATE TABLE IF NOT EXISTS " + tbl_user
					+ " (USER_ID SERIAL PRIMARY KEY, USERNAME varchar(32), PASSWORD varchar(50), FIRSTNAME varchar(50), LASTNAME varchar(50), USERTYPE varchar(20), EMAIL varchar(30))");
			// insert dummy account info
			connection.createStatement().execute("insert into " + tbl_user + " values " + "('1','" + admin + "','"
					+ adminPass + "','Bako','Gdaniec','admin','bakogda@gmail.com')");
			connection.createStatement().execute("insert into " + tbl_user + " values " + "('2','" + user + "','"
					+ userPass + "','Mike','Cross','normal','user@gmail.com')");
			System.out.println(tbl_user + " table populated");
		} catch (Exception e) {
			System.out.println("Table: " + tbl_user + " exists!");
		}

	}

	//////////////////////////////////////////////////////////////////////////////////

	public static void eventTable() throws ClassNotFoundException, SQLException {
		// start creating the database
		try {
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);
			connection.createStatement().execute("create table " + tbl_event
					+ "(EVENT_ID SERIAL PRIMARY KEY, USER_ID INTEGER REFERENCES userdet ON DELETE CASCADE, TITLE varchar(50), DATE date,ROOM varchar(50), STARTTIME TIME, ENDTIME TIME, DESCR varchar(200))");
		    connection.createStatement().execute("insert into " + tbl_event + "(USER_ID, TITLE, DATE, ROOM, STARTTIME, ENDTIME, DESCR) values ('2','TEST','2017-08-17','Room 1','09:00','09:00','')");
		   
			System.out.println(tbl_event + " table created");

			System.out.println("All ok...");
		} catch (Exception e) {
			System.out.println("Table: " + tbl_event + " exists!");
		}
	}

	//////////////////////////////////////////////////////////////////////////////////

	public static void inviteTable() throws ClassNotFoundException, SQLException {
		try {
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

			connection.createStatement().execute("create table " + tbl_invite
					+ " (INVITE_ID SERIAL PRIMARY KEY, EVENT_ID INTEGER REFERENCES event ON DELETE CASCADE, USER_ID INTEGER REFERENCES userdet ON UPDATE CASCADE ON DELETE CASCADE, USERNAME_INVITED varchar(50), ATTENDING char(3), REASON varchar(300))");
			connection.createStatement().execute("INSERT INTO INVITE VALUES('1','1','2','admin')");
			System.out.println("All ok...");
		} catch (Exception e) {
			System.out.println("Table: " + tbl_invite + " exists!");
		}
	}

	///////////////////////////////////////////////////////////////////////////////////

	private static void notesTable() {
		try {
			Connection connection = DriverManager.getConnection(dBV.JDBC_URL);

			connection.createStatement().execute("create table " + tbl_notes
					+ " (NOTES_ID SERIAL PRIMARY KEY, EVENT_ID INTEGER REFERENCES event ON DELETE CASCADE, USER_ID INTEGER REFERENCES event ON DELETE CASCADE, INVITE_ID INTEGER REFERENCES invite ON DELETE CASCADE, NOTES varchar(8000))");
			System.out.println(tbl_notes + " table created");
			connection.createStatement().execute("INSERT INTO NOTES VALUES('1', '1','1','1','XywoRs3rlhC0/du497GN+g==')");
			System.out.println("All ok...");
		} catch (Exception e) {
			System.out.println("Table: " + tbl_notes + " exists!");
		}
	}
}
