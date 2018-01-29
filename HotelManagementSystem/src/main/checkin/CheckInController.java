package checkin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class can add a checkin, update a checkin, delete a checkin,
 * get a specific checkin entry, get a list of a certain customer's checkins from the database,
 * and get a list of all checkins in the system.
 */
public class CheckInController {
	private CheckInData data; // uses DataBean class
	
	/**
	 * Default empty constructor.
	 */
	public CheckInController() {
		data = new CheckInSql();
	}

	/**
	 * Adds a checkin tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param c the checkin tuple to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
	public boolean add(Connection conn, CheckIn c) throws SQLException {
		return data.add(conn, c);
	}

	/**
	 * Update a specific check-in tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param c the checkin tuple to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
	public boolean update(Connection conn, CheckIn c)
			throws SQLException {
		return data.update(conn, c);
	}

	/**
	 * Delete a specific check-in tuple from the database
	 * 
	 * @param conn represents the connection with database
	 * @param c the checkin tuple to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
	public boolean delete(Connection conn, CheckIn c) throws SQLException {
		return data.delete(conn, c);
	}

	/**
	 * Get a specific check-in tuple from the database
	 * 
	 * @param roomID, this is the primary key for the CheckIn relation
	 * @return The check-in tuple from the database with the matching roomID
	 * @throws SQLException
	 */
	public CheckIn getSpecificCheckIn(int roomID) throws SQLException {
		return data.getSpecificCheckIn(roomID);
	}
	
	/**
	 * Get a certain customer's check-in list from the database
	 * 
	 * @param customerID identifies the customer of interest
	 * @return the particular customer's checkins as a list
	 * @throws SQLException
	 */
    public List<CheckIn> getCustomerCheckIns(int customerID) throws SQLException {
    	return data.getCustomerCheckIns(customerID);
    }

	/**
	 * Get a list of all checkins in the system
	 * 
	 * @return A list of all check-ins in the system
	 * @throws SQLException
	 */
	public List<CheckIn> getAll() throws SQLException {
		return data.getAll();
	}
}
