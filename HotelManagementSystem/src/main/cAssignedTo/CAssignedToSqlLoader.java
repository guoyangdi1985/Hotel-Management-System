package cAssignedTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * This class is used to load data into the CAssignedTo table.
 */
public class CAssignedToSqlLoader implements SQLLoader<CAssignedTo>{

    @Override
    public List<CAssignedTo> loadList(ResultSet rs) throws SQLException {
        ArrayList<CAssignedTo> list = new ArrayList<CAssignedTo>();
        while (rs.next()) {
            list.add(loadSingle(rs));
        }
        return list;
    }

    @Override
    public CAssignedTo loadSingle(ResultSet rs) throws SQLException {
        CAssignedTo cAssignedTo = new CAssignedTo();
        
        cAssignedTo.setStaffID(rs.getInt("staffID"));
        cAssignedTo.setRoomID(rs.getInt("roomID"));
        cAssignedTo.setCustomerID(rs.getInt("customerID"));
        cAssignedTo.setHotelID(rs.getInt("hotelID"));
        
        return cAssignedTo;
    }

    @Override
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, CAssignedTo object,
            boolean newInstance) throws SQLException {
        String stmt = "";
        
        if(newInstance) {
            stmt = "INSERT INTO CAssignedTo VALUES (?,?,?,?)";
            pstring = conn.prepareStatement(stmt);
            pstring.setInt( 1, object.getStaffID() );
            pstring.setInt( 2, object.getRoomID() );
            pstring.setInt( 3, object.getCustomerID() );   
            pstring.setInt( 4, object.getHotelID());
        } else {
            stmt = "UPDATE CAssignedTo SET staffID=?, "
                    + "roomID=" + object.getRoomID() + ", "
                    + "customerID=?, "
                    + "hotelID=? "
                    + "WHERE roomID=" + object.getRoomID();
            pstring = conn.prepareStatement(stmt);
            pstring.setInt(1, object.getStaffID());
            pstring.setInt(2, object.getCustomerID());
            pstring.setInt(3, object.getHotelID());
        }
        return pstring;
    }

}
