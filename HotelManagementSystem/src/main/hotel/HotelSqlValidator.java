package hotel;

import java.sql.SQLException;

/**
 * This class validates a Hotel object to make sure
 * that none of the database constraints have been violated.
 */
public class HotelSqlValidator {

	/**
	 * Validates a Hotel object to make sure that none of the
	 * database constraints have been violated.
	 * 
	 * @param obj the Hotel object that is being validated
	 * @throws SQLException
	 *  
	 */
    public void validate(Hotel obj) throws SQLException {
        String errors = "";
        if(obj.getHotelID() < 0) {
            errors += "HotelID must be greater than zero. ";
        }
        if(obj.getAddress() == null) {
            errors += "Address must not be null. ";
        }
        if(obj.getName() == null) {
            errors += "Name must not be null. ";
        }
        if(obj.getPhone() == null) {
            errors += "Phone must not be null. ";
        } else {
            if(!obj.getPhone().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$")) {
                errors += "Phone number format must be nnn-nnn-nnnn. ";
            }
        }
        
        if(errors.length() > 0) {
            throw new SQLException(errors);
        }
    }
}
