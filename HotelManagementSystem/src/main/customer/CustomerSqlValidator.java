package customer;

import java.sql.SQLException;

/**
 * This class validates a Customer object to make sure
 * that none of the database constraints have been violated.
 */
public class CustomerSqlValidator {

	/**
	 * Validates a Customer object to make sure that none of the
	 * database constraints have been violated.
	 * 
	 * @param obj the Customer object that is being validated
	 * @throws SQLException
	 *  
	 */
    public void validate(Customer obj) throws SQLException {
        String errors = "";
        if(obj.getCustomerID() < 0) {
            errors += "CustomerID must be greater than zero. ";
        }
        if(obj.getGender() == null) {
            errors += "Gender must not be null. ";
        }
        if(obj.getAddress() == null) {
            errors += "Address must not be null. ";
        }
        if(obj.getName() == null) {
            errors += "Name must not be null. ";
        }
        if(obj.getSsn() == null) {
            errors += "SSN must not be null. ";
        }
        if(!obj.getSsn().matches("^[0-9]{3}-[0-9]{2}-[0-9]{4}$")) {
            errors += "SSN format must be nnn-nn-nnnn. ";
        }
        if(obj.getEmail() == null) {
            errors += "Email must not be null. ";
        }
        if(obj.getGender() != 'M' && obj.getGender() != 'F') {
            errors += "Gender type is invalid. ";
        }
        
        if(errors.length() > 0) {
            throw new SQLException(errors);
        }
    }
}
