package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Provides a single access point for a database connection
 * @author Andrew Poe
 */
public class DBConnection {
    private static final String jdbcURL = "jdbc:oracle:thin:@//not.working.anymore.com:9988/not.working.anymore.com"; // Using SERVICE_NAME
    // Put your oracle ID and password here
    private static final String user = "fakeuser";
    private static final String password = "password";
    private static Connection connection = null;
    private static DBConnection dbConnection = null;
    
    private DBConnection(){}
    
    /**
     * Get instance of database connection
     */
    public static DBConnection getInstance() {
        if(dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
    
    /**
     * @return A database connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection(jdbcURL, user, password);
        return connection;
    }
    
    /**
     * Close database connection
     * @param conn the connection
     * @param pstring the statement to close connection
     */
    public void closeConnection(Connection conn, PreparedStatement pstring) {
    	try {
    		if(pstring != null) {
    			pstring.close();
    		}
    	} catch (SQLException e) {
    		System.out.println("Error closing statement");
    	}
    	try {
    		if(conn != null) {
    			conn.close();
    		}
    	} catch (SQLException e) {
    		System.out.println("Error closing connection");
    	}
    }
}
