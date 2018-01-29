package room;

import java.sql.SQLException;
import java.util.List;

import entityUtil.DataBean;

/**
 * This interface is used as a data bean for the Room table.
 */
public interface RoomData extends DataBean<Room> {

	/**
	 * Return the specific tuple from the Room table using the key (hotel ID,
	 * room number, and room ID).
	 * 
	 * @param hotelID
	 * @param roomNumber
	 * @param roomID
	 * @return
	 * @throws SQLException
	 */
	public Room getSpecificRoom(int roomID) throws SQLException;

	/**
	 * Return a list of rooms that matches a certain room type
	 * @param roomType string to search for
	 * @return list of rooms matching a room type
	 * @throws SQLException thrown if the SQL database throws an exception
	 */
	public List<Room> getRoomType(String roomType) throws SQLException;
}
