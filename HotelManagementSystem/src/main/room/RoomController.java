package room;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is the controller used for the Room table.
 */
public class RoomController {

	private RoomData data;
	
	/**
	 * The constructor for room controller.
	 */
	public RoomController() {
		data = new RoomSql();
	}

	/**
	 * Adds a Room with the following attributes. roomID is generated on
     * insertion into the database, and may NOT be set by the user.
     * 
	 * @param conn
	 * @param r
	 * @return
	 * @throws SQLException
	 */
	public boolean add(Connection conn, Room r)
			throws SQLException {
		return data.add(conn, r);
	}

	/**
	 * Update a specific room with the following information. room is the room
     * to be updated, while roomNumber, roomType, rate, and maxOccupancy are the
     * fields available to be updated. Any field that does not need to change
     * may be left null for String type field, 0 for int field, or 0.00 for real
     * number field. roomID may NOT be changed once a room has been inserted
     * into the database. roomID is generated on insertion, and is not
     * changeable by the user.
	 * @param conn
	 * @param r
	 * @return
	 * @throws SQLException
	 */
	public boolean update(Connection conn, Room r)
			throws SQLException {
		return data.update(conn, r);
	}

	/**
     * Delete a specific tuple from the Room table.
     * 
	 * @param conn
	 * @param r
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(Connection conn, Room r) throws SQLException {
		return data.delete(conn, r);
	}

	/**
     * Get a specific tuple from the Room table using the key (hotel ID, room
     * number, and room ID).
     * 
	 * @param roomID
	 * @return
	 * @throws SQLException
	 */
	public Room getSpecificRoom(int roomID) throws SQLException {
		return data.getSpecificRoom( roomID );
	}

	/**
	 * Return a list of all tuples from the Room table in the system.
	 * 
	 * @return A list of all tuples from the Room table.
	 * @throws SQLException
	 */
	public List<Room> getAll() throws SQLException {
		return data.getAll();
	}
	
	/**
	 * Return a list of rooms with the given room type from the database
	 * @param roomType to search for from the database
	 * @return list of rooms that matches the room type
	 * @throws SQLException thrown when the database throws an exception
	 */
	public List<Room> getRoomType(String roomType) throws SQLException {
		return data.getRoomType(roomType);
	}
}
