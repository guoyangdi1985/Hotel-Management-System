package cAssignedTo;

import java.sql.SQLException;

import entityUtil.DataBean;

/**
 * This interface is used as a data bean for the CAssignedTo table.
 */
public interface CAssignedToData extends DataBean<CAssignedTo> {
    
    /**
     * Return the specific tuple from the CAssignedTo table using a 
     * specific room id.
     * 
     * @param roomID - The id for a specific CAssignedTo tuple to be retrieved.
     * @return The CAssignedTo tuple from the database.
     */
    public CAssignedTo getSpecificCAssignedTo( int roomID ) throws SQLException;
}
