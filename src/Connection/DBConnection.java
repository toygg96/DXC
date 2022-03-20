package Connection;

import java.sql.*;

/* Model classes which requires a connection to perform any CRUD operation at the MySQL Database
 can  get the connection by calling getConnection() */
public class DBConnection {
	public static Connection getConnection() throws Exception {
		try {
			// Step1: Load JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Step 2: Define Connection URL
			String connURL ="jdbc:mysql://localhost/ase_assignment?user=root&password=root&serverTimezone=UTC"; 
			// Step 3: Establish connection to URL
			Connection conn =   DriverManager.getConnection(connURL); 
			// Step 4: Create Statement object
			return conn;
		} finally {
			
		}
	}
}