package staff;

import java.sql.SQLException;

/**
 * The class that validates the Staff member for SQL
 */
public class StaffSqlValidator {

	/**
	 * Validate the staff object
	 * @param obj to validate (staff)
	 * @throws SQLException throws an exception if the database does
	 */
    public void validate(Staff obj) throws SQLException {
        String errors = "";
        
        if(obj.getStaffID() < 0) {
            errors += "StaffID must be greater than zero. ";
        }
        
        if (obj.getHotelID() < 0) {
        	errors += "HotelID must be greater than zero. ";
        }
        
        if(obj.getName() == null) {
            errors += "Name must not be null. ";
        }
        if(obj.getSsn() == null) {
            errors += "SSN must not be null. ";
        }
        if(obj.getGender() == null) {
            errors += "Gender must not be null. ";
        }
        if(!obj.getGender().equals("M") && !obj.getGender().equals("F")) {
            errors += "Gender type is invalid, must be M or F. ";
        }
        
        if(obj.getPhone() == null) {
            errors += "Phone must not be null. ";
        } else {
            if(!obj.getPhone().matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$")) {
                errors += "Phone number format must be nnn-nnn-nnnn. ";
            }
        }
        
        
        if(obj.getAddress() == null) {
            errors += "Address must not be null. ";
        }
        
        if(obj.getStaffType() == null) {
            errors += "Email must not be null. ";
        } else if ((!obj.getStaffType().equalsIgnoreCase("Manager")) && (!obj.getStaffType().equalsIgnoreCase("Front Desk Representative")) && 
        		(!obj.getStaffType().equalsIgnoreCase("Catering Staff")) && (!obj.getStaffType().equalsIgnoreCase("Room Service Staff"))) {
        	errors += "StaffType is invalid, must be: Manager, Front Desk Representative, Catering Staff, or Room Service Staff. ";
        }
        
        
        if(errors.length() > 0) {
            throw new SQLException(errors);
        }
    }
}
