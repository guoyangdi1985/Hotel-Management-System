package checkin;

import java.sql.Timestamp;
import java.sql.SQLException;

/**
 * This class validates a CheckIn object to make sure
 * that none of the database constraints have been violated.
 */
public class CheckInSqlValidator {

	/**
	 * Validates a CheckIn object to make sure that none of the
	 * database constraints have been violated.
	 * 
	 * @param obj the CheckIn object that is being validated
	 * @throws SQLException
	 *  
	 */
    public void validate(CheckIn obj) throws SQLException {
        String errors = "";
        if(obj.getRoomID() <= 0) {
            errors += "RoomID must be greater than zero. ";
        }
        if (obj.getCustomerID() <= 0) {
        	errors += "CustomerID must be greater than zero. ";
        }
        Timestamp checkOutDT = obj.getCheckOutDateTime();
        Timestamp checkInDT = obj.getCheckInDateTime();
        
        if (checkOutDT == null) {
        	errors += "The checkout datetime must not be null. ";
        }
        
        if (checkInDT == null) {
        	errors += "The checkin datetime must not be null. ";
        }
        if (checkOutDT != null && checkInDT != null && checkOutDT.compareTo(checkInDT) <= 0) {
        	errors += "The checkout datetime must come after the checkin datetime. ";
        }
        if (obj.getNumberInParty() < 1 || obj.getNumberInParty() > 4) {
        	errors += "The number in the party must be between 1 and 4, inclusive. ";
        }
        
        if (obj.getRestaurantBill() < 0) {
        	errors += "The restaurant bill must be nonnegative. ";
        }
        if (obj.getLaundryBill() < 0) {
        	errors += "The laundry bill must be nonnegative. ";
        }
        if (obj.getPhoneBill() < 0) {
        	errors += "The phone bill must be nonnegative. ";
        }
        
        String paymentMethod = obj.getPaymentMethod();
        if (paymentMethod == null) {
        	errors += "The payment method must not be null. ";
        }
        boolean credit = paymentMethod.matches("^Credit Card [(][0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}[)]$");
        if( !(credit || paymentMethod.equalsIgnoreCase("cash") || paymentMethod.equalsIgnoreCase("check"))) {
            errors += "The payment method must be credit, cash, or check. ";
            errors += "If the payment method is credit, the card number be of the format nnnn-nnnn-nnnn-nnnn";
        }
        
        if(errors.length() > 0) {
            throw new SQLException(errors);
        }
        
    }
}

