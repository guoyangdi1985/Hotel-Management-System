package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbUtil.DBConnection;

/**
 * This class involves connecting to the database and provides 
 * various utility methods pertaining to hotel queries and updates
 * with the connection.
 */
public class HotelSql implements HotelData{

    private HotelSqlLoader sqlLoader;
    private Connection conn = null;
    private HotelSqlValidator sqlValidator;
    
    /**
	 * Default empty constructor.
	 */
    public HotelSql() {
        sqlLoader = new HotelSqlLoader();
        sqlValidator = new HotelSqlValidator();
    }
    
    /**
	 * Get a list of all hotels in the system
	 * 
	 * @return A list of all hotels in the system
	 * @throws SQLException
	 */
    @Override
    public List<Hotel> getAll() throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM hotel");
            rs = pstring.executeQuery();
            List<Hotel> list = sqlLoader.loadList(rs);
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
	 * Adds a hotel tuple.
	 * 
	 * @param connection represents the connection with database
	 * @param addObj the hotel tuple to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    @Override
    public boolean add(Connection connection, Hotel addObj) throws SQLException { 
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
	 * Update a specific hotel tuple.
	 * 
	 * @param connection represents the connection with database
	 * @param updateObj the hotel tuple to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
    @Override
    public boolean update(Connection connection, Hotel updateObj) throws SQLException {
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
	 * Delete a specific hotel tuple from the database
	 * 
	 * @param connection represents the connection with database
	 * @param deleteObj the hotel tuple to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    @Override
    public boolean delete(Connection connection, Hotel deleteObj) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        int results;
        try {
            pstring = conn.prepareStatement("DELETE FROM hotel WHERE hotelID=?");
            pstring.setInt(1, deleteObj.getHotelID());
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
	 * Get a specific hotel tuple from the database
	 * 
	 * @param hotelID, this is the primary key for the Hotel relation
	 * @return The hotel tuple from the database with the matching hotelID
	 * @throws SQLException
	 */
    @Override
    public Hotel getSpecificHotel(int hotelID) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        Hotel hotel = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM hotel WHERE hotelID=?");
            pstring.setInt(1, hotelID);
            rs = pstring.executeQuery();
            List<Hotel> list = sqlLoader.loadList(rs);
            if(list.size() > 0) {
                hotel = list.get(0);
            }
            return hotel;
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

}
