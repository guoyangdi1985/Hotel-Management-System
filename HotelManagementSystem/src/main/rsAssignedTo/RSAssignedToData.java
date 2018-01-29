package rsAssignedTo;

import java.sql.SQLException;

import entityUtil.DataBean;

/**
 * This interface is used as a data bean for the RSAssignedTo table.
 */
public interface RSAssignedToData extends DataBean<RSAssignedTo> {
    
    /**
     * Return the specific tuple from the RSAssignedTo table using a 
     * specific room id.
     * 
     * @param roomID - The id for a specific RSAssignedTo tuple to be retrieved.
     * @return The RSAssignedTo tuple from the database.
     */
    public RSAssignedTo getSpecificRSAssignedTo( int roomID ) throws SQLException;
}
