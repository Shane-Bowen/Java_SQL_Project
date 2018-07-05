package Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

	private static final String URL = "jdbc:mysql://localhost:3306/My_Shop_Database?autoReconnect=true&useSSL=false&relaxAutoCommit=true";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";

	private static Connection connection; // manages connection

	public static Connection My_Connection()
	{
		try 
		{
			connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
			System.exit( 1 );
		} // end catch
		
		return connection;
	} 

	public void close()
	{
		try 
		{
			connection.close();
		} // end try
		catch ( SQLException sqlException )
		{
			sqlException.printStackTrace();
		} // end catch
	} // end method close 
}