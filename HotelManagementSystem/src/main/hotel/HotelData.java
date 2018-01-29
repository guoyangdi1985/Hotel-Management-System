package hotel;

import java.sql.SQLException;

/**
 * This interface is used by the HotelController class for the various operations.
 */

import entityUtil.DataBean;

/**
 * This interface is used by the HotelController class for the various operations.
 */
public interface HotelData extends DataBean<Hotel>{
    
    /**
     * @param hotelID - The id for the hotel to be retrieved
     * @return A specific Hotel from the database
     * @throws SQLException
     */
    public Hotel getSpecificHotel(int hotelID) throws SQLException;
}
