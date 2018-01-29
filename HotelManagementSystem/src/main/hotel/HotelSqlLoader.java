package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * This class is responsible for loading the hotel data
 * into the database.
 */
public class HotelSqlLoader implements SQLLoader<Hotel>{

	/**
	 * Loads a list of hotels from a result set.
	 * 
	 * @param rs the source for the hotel list to be built
	 * @return the list of hotels from the result set
	 * @throws SQLException
	 */
    @Override
    public List<Hotel> loadList(ResultSet rs) throws SQLException {
        ArrayList<Hotel> list = new ArrayList<Hotel>();
        while (rs.next()) {
            list.add(loadSingle(rs));
        }
        return list;
    }

    /**
	 * Map rows from result set to Hotel objects to be added to list
	 * 
	 * @param rs the source for the hotel list to be built
	 * @return a Hotel object to be added to the list
	 * @throws SQLException
	 */
    @Override
    public Hotel loadSingle(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();
        
        hotel.setHotelID(rs.getInt("hotelID"));
        hotel.setAddress(rs.getString("address"));
        hotel.setName(rs.getString("name"));
        hotel.setPhone(rs.getString("phone"));
        
        return hotel;
    }

    /**
	 * Generates a prepared SQL statement, either an insertion of a hotel tuple or an
	 * update of a hotel tuple
	 * 
	 * @param conn represents the connection with database
	 * @param pstring the prepared statement built and returned
	 * @param object the Hotel object of interest
	 * @param newInstance tells us whether to do insertion of new tuple or an update of tuple
	 * @return A prepared SQL statement for either an insertion or update of hotel tuple
	 * @throws SQLException
	 */
    @Override
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, Hotel object,
            boolean newInstance) throws SQLException {
        String stmt = "";
        
        if(newInstance) {
            stmt = "INSERT INTO hotel VALUES (hotel_seq.nextval,?,?,?)";
//        	stmt = "INSERT INTO hotel(hotelID, name, address, phone) VALUES (hotel_seq.nextval, '"
//        			+ object.getName() + "', '" 
//        			+ object.getAddress() + "', '"
//        			+ object.getPhone() + "');";
        } else {
            stmt = "UPDATE hotel SET hotelID=" + object.getHotelID() + ", "
                    + "name=?, "
                    + "address=?, "
                    + "phone=? "
                    + "WHERE hotelID=" + object.getHotelID();
        }
        pstring = conn.prepareStatement(stmt);
        pstring.setString(1, object.getName());
        pstring.setString(2, object.getAddress());
        pstring.setString(3, object.getPhone());
        
        return pstring;
    }


}
