package admin;

import java.sql.SQLException;

public class Main{

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		//create the db automatically 
		database.createDB.main(args);
		//start the login GUI 
		RunMVC mainRunMVC = new RunMVC();
	}
}
