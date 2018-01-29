package staff;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * StaffController manages the actions of the database
 */
public class StaffController {
    private StaffData data;
 
    /**
	 * The constructor for staff controller.
	 */
	public StaffController() {
		data = new StaffSql();
	}

	/**
	 * Adds a staff tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param s the staff tuple to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    public boolean add(Connection conn, Staff s) throws SQLException {
        return data.add(conn, s);
    }

    /**
	 * Update a specific staff tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param s the staff tuple to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
    public boolean update(Connection conn, Staff s) throws SQLException {
        return data.update(conn, s);
    }

    /**
	 * Delete a specific staff tuple from the database
	 * 
	 * @param conn represents the connection with database
	 * @param s the staff tuple to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    public boolean delete(Connection conn, Staff s) throws SQLException {
        return data.delete(conn, s);
    }

    /**
     * Get a specific staff from the database
     * @param staffID
     * @return The staff from the database with the matching staffID
     * @throws SQLException
     */
    public Staff getSpecificStaff(int staffID) throws SQLException {
        return data.getSpecificStaff(staffID);
    }

    /**
     * Get a list of all staff in the system
     * @return A list of all staff in the system
     * @throws SQLException
     */
    public List<Staff> getAll() throws SQLException {
        return data.getAll();
    }
    
    /**
     * Get the list of staff with a specific staff type
     * @param staffType the type of staff we are interested in
     * @return The list of staff with the specific staff type
     * @throws SQLException
     */
    public List<Staff> getAllByStaffType(String staffType) throws SQLException {
        return data.getAllByStaffType(staffType);
    }
}
