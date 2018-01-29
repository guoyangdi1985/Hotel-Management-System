package checkin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * This class is responsible for loading the checkin data
 * into the database.
 */
public class CheckInSqlLoader implements SQLLoader<CheckIn>{

	/**
	 * Loads a list of checkins from a result set.
	 * 
	 * @param rs the source for the checkin list to be built
	 * @return the list of checkins from the result set
	 * @throws SQLException
	 */
    @Override
    public List<CheckIn> loadList(ResultSet rs) throws SQLException {
        ArrayList<CheckIn> list = new ArrayList<CheckIn>();
        while (rs.next()) {
            list.add(loadSingle(rs));
        }
        return list;
    }

    /**
	 * Map rows from result set to CheckIn objects to be added to list
	 * 
	 * @param rs the source for the checkin list to be built
	 * @return a CheckIn object to be added to the list
	 * @throws SQLException
	 */
    @Override
    public CheckIn loadSingle(ResultSet rs) throws SQLException {
        CheckIn checkin = new CheckIn();
        
		checkin.setCustomerID(rs.getInt("customerID"));
		checkin.setRoomID(rs.getInt("roomID"));
		checkin.setCheckOutDateTime(rs.getTimestamp("checkOutDateTime"));
		checkin.setCheckInDateTime(rs.getTimestamp("checkInDateTime"));
		checkin.setNumberInParty(rs.getInt("numberInParty"));
		checkin.setHasCatering(rs.getString("hasCatering"));
		checkin.setHasRoomService(rs.getString("hasRoomService"));
		checkin.setRestaurantBill(rs.getFloat("restaurantBill"));
		checkin.setLaundryBill(rs.getFloat("laundryBill"));
		checkin.setPhoneBill(rs.getFloat("phoneBill"));
		checkin.setPaymentMethod(rs.getString("paymentMethod"));
        
        return checkin;
    }

    /**
	 * Generates a prepared SQL statement, either an insertion of a checkin tuple or an
	 * update of a checkin tuple
	 * 
	 * @param conn represents the connection with database
	 * @param pstring the prepared statement built and returned
	 * @param object the CheckIn object of interest
	 * @param newInstance tells us whether to do insertion of new tuple or an update of tuple
	 * @return A prepared SQL statement for either an insertion or update of checkin tuple
	 * @throws SQLException
	 */
    @Override
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, CheckIn object,
            boolean newInstance) throws SQLException {
        String stmt = "";
        
        if(newInstance) {
            stmt = "INSERT INTO checkin(customerID, roomID, checkOutDateTime, checkInDateTime, " +
            		"numberInParty, hasCatering, hasRoomService, restaurantBill, laundryBill, " +
            		"phoneBill, paymentMethod) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
        } else {
            stmt = "UPDATE checkin SET customerID=?, "
                    + "roomID=?, "
                    + "checkOutDateTime=?, "
                    + "checkInDateTime=?, "
                    + "numberInParty=?, "
                    + "hasCatering=?, "
                    + "hasRoomService=?, "
                    + "restaurantBill=?, "
                    + "laundryBill=?, "
                    + "phoneBill=?, "
                    + "paymentMethod=? "
                    + "WHERE roomID=" + object.getRoomID();
        }
        pstring = conn.prepareStatement(stmt);
        
        pstring.setInt(1, object.getCustomerID());
        pstring.setInt(2, object.getRoomID());
        pstring.setTimestamp(3, object.getCheckOutDateTime());
        pstring.setTimestamp(4, object.getCheckInDateTime());
        pstring.setInt(5, object.getNumberInParty());
        pstring.setString(6, object.getHasCatering());
        pstring.setString(7, object.getHasRoomService());
        pstring.setFloat(8,  object.getRestaurantBill());
        pstring.setFloat(9,  object.getLaundryBill());
        pstring.setFloat(10, object.getPhoneBill());
        pstring.setString(11, object.getPaymentMethod());
        
        return pstring;
    }

}
