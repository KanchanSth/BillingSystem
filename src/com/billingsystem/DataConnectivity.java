
import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnectivity {

	public static final String DATABASE_NAME="product"; //const name in capital
	public static final String DRIVER_NAME="com.mysql.jdbc.Driver";
	public static final String URL= "jdbc:mysql://localhost/";
	public static final String USER_NAME= "root";
	public static final String PASSWORD= "";
	
	public static Connection getConnection() throws Exception
	{
		Class.forName(DRIVER_NAME); // TO INITIALIZE DRIVER
		return DriverManager.getConnection(URL+DATABASE_NAME,USER_NAME,PASSWORD);
	}
	
	
}
