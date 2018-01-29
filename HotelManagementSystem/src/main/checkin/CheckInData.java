package checkin;

import java.sql.SQLException;
import java.util.List;

import entityUtil.DataBean;

/**
 * This interface is used by the CheckInController class for the various operations.
 */

public interface CheckInData extends DataBean<CheckIn>{
    
    /**
     * @param roomID - The room id for the check-in tuple to be retrieved
     * @return A specific check-in tuple from the database
     * @throws SQLException
     */
    public CheckIn getSpecificCheckIn(int roomID) throws SQLException;
    
    /**
     * @param customerID - The customer id for the check-in tuples to be retrieved
     * @return A list of checkins for that customer of interest
     */
    public List<CheckIn> getCustomerCheckIns(int customerID) throws SQLException;
}

