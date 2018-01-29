package cAssignedTo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is the controller used for the CAssignedTo table.
 */
public class CAssignedToController {
	private CAssignedToData data;

	/**
	 * The constructor for this class.
	 */
	public CAssignedToController() {
		data = new CAssignedToSql();
	}

	/**
     * Adds a tuple with the following attributes into the CAssignedTo table.
	 * @param conn is the connection to the database
	 * @param c is the catering assigned
	 * @return true if result is true, else false
	 * @throws SQLException throws SQL exception if the database does
	 */
	public boolean add(Connection conn, CAssignedTo c) throws SQLException {
		return data.add(conn, c);
	}

	/**
	 * Update a tuple with the following attributes
	 * @param conn is the connection to the database
	 * @param c is the catering assigned
	 * @return true if result is true, else false
	 * @throws SQLException throws SQL exception if the database does
	 */
	public boolean update(Connection conn, CAssignedTo c) throws SQLException {
		return data.update(conn, c);
	}

	/**
	 * Delete a specific tuple from the CAssignedTo table.
	 * @param conn is the connection to the database
	 * @param cAssignedTo the object of the CAssignedTo table.
	 * @return true the tuple is deleted successfully, false otherwise.
	 * @throws SQLException throws SQL exception if the database does
	 */
	public boolean delete(Connection conn, CAssignedTo c) throws SQLException {
		return data.delete(conn, c);
	}

	/**
	 * Get a specific tuple from the CAssignedTo table.
	 * 
	 * @param roomID the room id in the tuple returned.
	 * @return the tuple where the room id is used to check.
	 * @throws SQLException throws SQL exception if the database does
	 */
	public CAssignedTo getSpecificCAssignedTo(int roomID) throws SQLException {
		return data.getSpecificCAssignedTo(roomID);
	}

	/**
	 * Return a list of all tuples from the CAssignedTo table in the system.
	 * @return A list of all tuples from the CAssignedTo table.
	 * @throws SQLException throws SQL exception if the database does
	 */
	public List<CAssignedTo> getAll() throws SQLException {
		return data.getAll();
	}
}
