package room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * This class is used to load data into the Room table.
 */
public class RoomSqlLoader implements SQLLoader<Room> {

	@Override
	public List<Room> loadList(ResultSet rs) throws SQLException {
		ArrayList<Room> list = new ArrayList<Room>();
		while (rs.next()) {
			list.add(loadSingle(rs));
		}
		return list;
	}

	@Override
	public Room loadSingle(ResultSet rs) throws SQLException {
		Room room = new Room();
		room.setHotelID(rs.getInt("hotelID"));
		room.setRoomNumber(rs.getInt("roomNumber"));
		room.setRoomType(rs.getString("roomType"));
		room.setRate(rs.getDouble("rate"));
		room.setMaxOccupancy(rs.getInt("maxOccupancy"));
		room.setRoomID(rs.getInt("roomID"));
		return room;
	}

	@Override
	public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, Room object,
			boolean newInstance) throws SQLException {
		String stmt = "";

		if (newInstance) {
			stmt = "INSERT INTO room VALUES (?,?,?,?,?,room_seq.nextval)";
		} else {
			stmt = "UPDATE room SET hotelID=?, "
			       + "roomNumber=?, " 
				   + "roomType=?, "
			       + "rate=?, "
				   + "maxOccupancy=?, "
			       + "roomID=" + object.getRoomID()
			       + " WHERE roomID=" + object.getRoomID();
		}
		pstring = conn.prepareStatement(stmt);
		pstring.setInt( 1, object.getHotelID() );
		pstring.setInt( 2, object.getRoomNumber() );
		pstring.setString( 3, object.getRoomType() );
		pstring.setDouble( 4, object.getRate() );
		pstring.setInt( 5, object.getMaxOccupancy() );
		return pstring;
	}

}
