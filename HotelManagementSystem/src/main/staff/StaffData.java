package staff;

import java.sql.SQLException;
import java.util.List;

import entityUtil.DataBean;

/**
 * Interface for the StaffData, used by StaffController class
 */
public interface StaffData extends DataBean<Staff>{
    
    /**
     * Get a specific staff member
     * @param staffId - The id for the staff to be retrieved
     * @return A specific Staff from the database
     * @throws SQLException
     */
    public Staff getSpecificStaff(int staffID) throws SQLException;

    /**
     * Return list of staff of a specific staffType
     * @param staffType to consider
     * @return list of staff of that type
     * @throws SQLException
     */
	public List<Staff> getAllByStaffType(String staffType) throws SQLException;
}