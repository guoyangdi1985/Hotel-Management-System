package hotel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class can add a hotel, update a hotel, delete a hotel,
 * get a specific hotel, and get a list of all hotels in the system.
 */
public class HotelController {
    private HotelData data;
    
	/**
	 * Default empty constructor.
	 */
    public HotelController() {
    	data = new HotelSql();
    }
    
    /**
	 * Adds a hotel tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param h the hotel tuple to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    public boolean add(Connection conn, Hotel h) throws SQLException {
        return data.add(conn, h);
    }
    
    /**
	 * Update a specific hotel tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param h the hotel tuple to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
    public boolean update(Connection conn, Hotel h) throws SQLException {
        return data.update(conn, h);
    }
    
    /**
	 * Delete a specific hotel tuple from the database
	 * 
	 * @param conn represents the connection with database
	 * @param h the hotel tuple to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    public boolean delete(Connection conn, Hotel h) throws SQLException {
        return data.delete(conn, h);
    }
    
    /**
	 * Get a specific hotel tuple from the database
	 * 
	 * @param hotelID, this is the primary key for the Hotel relation
	 * @return The hotel tuple from the database with the matching hotelID
	 * @throws SQLException
	 */
    public Hotel getSpecificHotel(int hotelID) throws SQLException {
        return data.getSpecificHotel(hotelID);
    }
    
    /**
     * @return A list of all hotels in the system
     * @throws SQLException
     */
    public List<Hotel> getAll() throws SQLException {
        return data.getAll();
    }
}
