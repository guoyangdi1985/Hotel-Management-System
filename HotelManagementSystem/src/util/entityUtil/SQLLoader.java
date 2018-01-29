package entityUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This enforces a uniform style for all SQLLoader classes
 * 
 * @author Andrew Poe
 * @param <T> - Generic java object
 */
public interface SQLLoader<T> {

    /**
     * Takes a ResultSet containing a table of data and creates a list of objects from it
     * @param rs - The ResultSet of table data
     * @return - A list of objects built from the data
     * @throws SQLException
     */
    public List<T> loadList(ResultSet rs) throws SQLException;
    
    /**
     * Builds a single object from a ResultSet
     * @param rs - The ResultSet of table data
     * @return - A single object built from the data
     * @throws SQLException
     */
    public T loadSingle(ResultSet rs) throws SQLException;
    
    /**
     * Writes either an add or update PreparedStatement using the provided information
     * @param conn - The database connection
     * @param pstring - The PreparedStatement to be modified
     * @param object - The object to load information from
     * @param newInstance - When true, creates an Add statement. When false, creates an update statement
     * @return - The PreparedStatement for the desired SQL command
     * @throws SQLException
     */
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, 
            T object, boolean newInstance) throws SQLException;
}
