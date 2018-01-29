package entityUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DataBean<T> {
    
    /**
     * @return A list of all the given objects in the database
     * @throws SQLException
     */
    public List<T> getAll() throws SQLException;
    
    /**
     * Add a specific object to the database
     * @param addObj - The object to be added
     * @return A boolean value to demonstrate success or failure
     * @throws SQLException
     */
    public boolean add(Connection connection, T addObj) throws SQLException;
    
    /**
     * Update a specific object in the database
     * Note: Unique identifier variables may not be changed.
     * Delete the object and re-add it to change them
     * @param updateObj - The object to be updated
     * @return A boolean value to demonstrate success or failure
     * @throws SQLException
     */
    public boolean update(Connection connection, T updateObj) throws SQLException;
    
    /**
     * Delete a specific object from the database
     * @param deleteObj - The object to be deleted
     * @return A boolean value to demonstrate success or failure
     * @throws SQLException
     */
    public boolean delete(Connection connection, T deleteObj) throws SQLException;
}
