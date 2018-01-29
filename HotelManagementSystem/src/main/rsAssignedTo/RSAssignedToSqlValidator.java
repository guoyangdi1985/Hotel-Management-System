package rsAssignedTo;

import java.sql.SQLException;

/**
 * This is the validator class used to check validations of 
 * room id, staff id, and customer id.
 */
public class RSAssignedToSqlValidator {

	/**
	 * This method checks whether the room id, the staff id, 
	 * and the customer id are greater than 0.
	 * 
	 * @param obj the object of a tuple in the RSAssignedTo table.
	 * @throws SQLException
	 */
    public void validate(RSAssignedTo obj) throws SQLException {
        String errors = "";
        if( obj.getRoomID() <= 0 ) {
            errors += "Room ID must be greater than zero. ";
        }
        if( obj.getStaffID() <= 0 ) {
            errors += "Staff ID must be greater than zero. ";
        }
        if( obj.getCustomerID() <= 0 ) {
            errors += "Customer ID must be greater than zero. ";
        }
        
        if(errors.length() > 0) {
            throw new SQLException(errors);
        }
    }
}
