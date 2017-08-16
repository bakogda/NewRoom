package database;

/*
 * dBV == Database Variables
 * These variable occur too many times so it seems logical to move them
 * to one location and all occurrences refer to it.
 */
public class dBV {
	public static final String dbName = "booking";
	public static final String DRIVER = "org.postgresql.Driver";
	public static final String JDBC_URL = "jdbc:postgresql://localhost/booking?user=postgres&password=password";

	// there would need to be checks throughout the sql code to check whether
	// this is true and if so start printing out info?
	public static final Boolean debugDB = false;
}
