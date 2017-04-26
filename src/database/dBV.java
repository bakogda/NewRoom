package database;
/*
 * dBV == Database Variables
 * These variable occur too many times so it seems logical to move them
 * to one location and all occurrences refer to it.
 */
public class dBV {
	public static final String dbName = "theDB";
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:" + dbName + ";create=true";
	
	//there would need to be checks throughout the sql code to check whether this is true and if so start printing out info?
	public static final Boolean debugDB = false;
}
