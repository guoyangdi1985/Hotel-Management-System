package staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbUtil.DBConnection;

/**
 * This class handles the SQL for the Staff member
 */
public class StaffSql implements StaffData{

    private StaffSqlLoader sqlLoader;
    private Connection conn = null;
    private StaffSqlValidator sqlValidator;

    /**
     * Initialize the StaffSQL via empty constructor
     */
    public StaffSql() {
        sqlLoader = new StaffSqlLoader();
        sqlValidator = new StaffSqlValidator();
    }

    /**
	 * Get a list of all staff in the system
	 * 
	 * @return A list of all staff in the system
	 * @throws SQLException
	 */
    @Override
    public List<Staff> getAll() throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM staff");
            rs = pstring.executeQuery();
            List<Staff> list = sqlLoader.loadList(rs);
            return list;          
        } finally {
        	try {
        		if(rs != null) {
        			rs.close();
        		} 
        	} catch(SQLException e) {
        		System.out.println("Error closing ResultSet");
        	} finally {
        		DBConnection.getInstance().closeConnection(conn, pstring);
        	} 
        }
    }

    /**
	 * Adds a staff to the DB.
	 * 
	 * @param connection represents the connection with database
	 * @param addObj the staff to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    @Override
    public boolean add(Connection connection, Staff addObj) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        try {
            sqlValidator.validate(addObj);
            int results;
            pstring = sqlLoader.loadParameters(conn, pstring, addObj, true);
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
	 * Update a specific staff tuple.
	 * 
	 * @param connection represents the connection with database
	 * @param updateObj the staff to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
    @Override
    public boolean update(Connection connection, Staff updateObj) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        try {
            sqlValidator.validate(updateObj);
            int results;
            pstring = sqlLoader.loadParameters(conn, pstring, updateObj, false);
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
	 * Delete a specific staff from the database
	 * 
	 * @param connection represents the connection with database
	 * @param deleteObj the staff to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    @Override
    public boolean delete(Connection connection, Staff deleteObj) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        int results;
        try {
            pstring = conn.prepareStatement("DELETE FROM staff WHERE staffID=?");
            pstring.setInt(1, deleteObj.getStaffID());
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }
    
    /**
	 * Get a list of staff members having a certain staff type
	 * 
	 * @param staffType the staff type of interest
	 * @return A list of staff matching that staff type
	 * @throws SQLException
	 */
    public List<Staff> getAllByStaffType(String staffType) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM staff WHERE staffType=?");
            pstring.setString(1, staffType);
            rs = pstring.executeQuery();
            List<Staff> list = sqlLoader.loadList(rs);
            return list;          
        } finally {
        	try {
        		if(rs != null) {
        			rs.close();
        		} 
        	} catch(SQLException e) {
        		System.out.println("Error closing ResultSet");
        	} finally {
        		DBConnection.getInstance().closeConnection(conn, pstring);
        	} 
        }
    }
    
    /**
	 * Get a specific staff member
	 * 
	 * @param staffID the unique ID for the staff member
	 * @return The particular staff member who has that ID
	 * @throws SQLException
	 */
    @Override
    public Staff getSpecificStaff(int staffID) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        Staff staff = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM staff WHERE staffID=?");
            pstring.setInt(1, staffID);
            rs = pstring.executeQuery();
            List<Staff> list = sqlLoader.loadList(rs);
            if(list.size() > 0) {
            	staff = list.get(0);
            }
            return staff;
        } finally {
        	try {
        		if (rs != null) {
        			rs.close();
        		}
        	} catch(SQLException e) {
        		System.out.println("Error closing ResultSet");
        	} finally {
        		DBConnection.getInstance().closeConnection(conn, pstring);
        	}
        }
    }

}
