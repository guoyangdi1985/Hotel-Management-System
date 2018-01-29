package room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbUtil.DBConnection;

public class RoomSql implements RoomData {

	private RoomSqlLoader sqlLoader;
	private Connection conn = null;
	private RoomSqlValidator sqlValidator;

	/**
	 * Constructor for the RoomSql class.
	 */
	public RoomSql() {
		sqlLoader = new RoomSqlLoader();
		sqlValidator = new RoomSqlValidator();
	}
	
	/**
     * Get a list of all rooms in the system
     * 
     * @return A list of all rooms in the system
     * @throws SQLException
     */
	@Override
	public List<Room> getAll() throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstring = null;
		ResultSet rs = null;
		try {
			pstring = conn.prepareStatement("SELECT * FROM Room");
			rs = pstring.executeQuery();
			List<Room> list = sqlLoader.loadList(rs);
			return list;
		} finally {
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}
	
	
	@Override
	public List<Room> getRoomType(String roomType) throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstring = null;
		ResultSet rs = null;
		try {
			pstring = conn.prepareStatement("SELECT * FROM Room WHERE roomType=?");
			pstring.setString(1, roomType);
			rs = pstring.executeQuery();
			List<Room> list = sqlLoader.loadList(rs);
			return list;
		} finally {
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}

	@Override
	public boolean add(Connection connection, Room addObj) throws SQLException {
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

	@Override
	public boolean update(Connection connection, Room updateObj) throws SQLException {
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

	@Override
	public boolean delete(Connection connection, Room deleteObj) throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean retVal = false;
		PreparedStatement pstring = null;
		int results;
		try {
			pstring = conn.prepareStatement("DELETE FROM Room WHERE roomID=?");
			pstring.setInt( 1, deleteObj.getRoomID() );
			results = pstring.executeUpdate();
			retVal = (results > 0);
			return retVal;
		} finally {
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}

	@Override
	public Room getSpecificRoom( int roomID ) throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstring = null;
		ResultSet rs = null;
		Room room = null;
		try {
			pstring = conn.prepareStatement("SELECT * FROM Room WHERE roomID=?");
			pstring.setInt( 1, roomID );
			rs = pstring.executeQuery();
			List<Room> list = sqlLoader.loadList(rs);
			if (list.size() > 0) {
				room = list.get(0);
			}
			return room;
		} finally {
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}

}
