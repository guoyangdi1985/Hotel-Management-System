package room;

import java.sql.SQLException;

/**
 * This is the validator class used to check validations of room id, staff id,
 * and customer id.
 */
public class RoomSqlValidator {

	/**
	 * This method checks whether the room id, the staff id, and the customer id
	 * are greater than 0.
	 * 
	 * @param obj
	 *            the object of a tuple in the CAssignedTo table.
	 * @throws SQLException
	 */
	public void validate(Room obj) throws SQLException {
		String errors = "";
		if (obj.getHotelID() <= 0) {
			errors += "Hotel ID must be greater than zero. ";
		}
		if (obj.getRoomNumber() <= 0) {
			errors += "Room number must be greater than zero. ";
		}
		if (obj.getRoomType() == null) {
			errors += "Room type must not be null. ";
		} else if ((!obj.getRoomType().equals("Economy")) && (!obj.getRoomType().equals("Deluxe"))
				&& (!obj.getRoomType().equals("Executive Suite"))
				&& (!obj.getRoomType().equals("Presidential Suite"))) {
			errors += "Room Type is invalid, must be: Economy, Deluxe, Executive Suite, or Presidential Suite. ";
		}
		if (obj.getRate() <= 0.0) {
			errors += "Rate must be greater than zero. ";
		}
		if (obj.getMaxOccupancy() < 1 || obj.getMaxOccupancy() > 4) {
			errors += "Maximum occupancy must be between 1 and 4. ";
		}
		if (obj.getRoomID() < 0) {
			errors += "Room ID must be greater than zero. ";
		}
		if (errors.length() > 0) {
			throw new SQLException(errors);
		}
	}
}
