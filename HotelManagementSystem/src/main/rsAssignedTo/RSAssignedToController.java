package rsAssignedTo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is the controller used for the RSAssignedTo table.
 */
public class RSAssignedToController {
    private RSAssignedToData data;
    
    /**
     * The constructor for this class.
     */
    public RSAssignedToController () {
    	data = new RSAssignedToSql();
    }
    
    /**
     * Adds a tuple with the following attributes into the RSAssignedTo table.
     * 
     * @param staffID the staff id.
     * @param roomID the room id.
     * @param customerID the customer id.
     * @return true if the tuple is added without any problems, false otherwise.
     * @throws SQLException
     */
    public boolean add(Connection conn, RSAssignedTo rs) throws SQLException {
        return data.add(conn, rs);
    }
    
    public boolean update(Connection conn, RSAssignedTo rs) throws SQLException {
        return data.update(conn, rs);
    }
    
    /**
     * Delete a specific tuple from the RSAssignedTo table.
     * 
     * @param rsAssignedTo the object of the RSAssignedTo table.
     * @return true the tuple is deleted successfully, false otherwise.
     * @throws SQLException
     */
    public boolean delete(Connection conn, RSAssignedTo rs) throws SQLException {
        return data.delete(conn, rs);
    }
    
    /**
     * Get a specific tuple from the RSAssignedTo table.
     * 
     * @param roomID the room id in the tuple returned.
     * @return the tuple where the room id is used to check.
     * @throws SQLException
     */
    public RSAssignedTo getSpecificRSAssignedTo(int roomID) throws SQLException {
        return data.getSpecificRSAssignedTo(roomID);
    }
    
    /**
     * Return a list of all tuples from the RSAssignedTo table in the system.
     * 
     * @return A list of all tuples from the RSAssignedTo table.
     * @throws SQLException
     */
    public List<RSAssignedTo> getAll() throws SQLException {
        return data.getAll();
    }
}
