package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.DatabaseException;

public class ConnectionFactory {
	
	private static Connection connection = null;

	public static Connection getConnection(){
		
		if(connection == null) {
		
			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection("jdbc:mysql://localhost/ifc_store", "root", "bancodedados");
			
				return connection;
				
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage());
			} 
		
		} else {
			return connection;
		}
		
	}
	
	public static void closeConnection() {
		try {
			
			connection.close();
		
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DatabaseException(e.getMessage());
      		}
		}
	}

}
