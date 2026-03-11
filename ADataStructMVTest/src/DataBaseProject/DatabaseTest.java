package DataBaseProject;

import java.sql.*;

public class DatabaseTest {
	
	/**				Database Reading and printing on Eclipse IDE
	 * 	import java.sql library for the majority of the work
	 * 	Use the class for jdbc "com.mysql.cj.jdbc.Driver"
	 *  
	 * 	Use the URL "jdbc:mysql:// and connect it to the database "localhost:3306/dbtest" with the host name
	 * 		-Include the host name ("root") and password
	 * 
	 * address: localhost
	 * port: 3306
	 * 
	 * 	NOTE: If an exception error is thrown, go to the website to download the Connector/J (recent) file 
	 * 		as platform-independent; add it as a JAR file in the main library of this project
	 * 
	 * 
	 * 	The rest is similar to reading data from a file  
	 */
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		
		//JDBC is the API for connecting Java to a database (MySQL)
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtest", "root", "DerNachtZug*87");
		System.out.println("Connection Created");
		
		String command = "select * from employees";
		Statement stm = connect.createStatement();
		ResultSet result = stm.executeQuery(command);
		
		while(result.next()) {
			String col1 = result.getString("fullname");
			int col2 = result.getInt("ID");
			
			System.out.println(col1 + " " + col2);
		}
	}

}
