package rsAssignedTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * This class is used to load data into the RSAssignedTo table.
 */
public class RSAssignedToSqlLoader implements SQLLoader<RSAssignedTo>{

    @Override
    public List<RSAssignedTo> loadList(ResultSet rs) throws SQLException {
        ArrayList<RSAssignedTo> list = new ArrayList<RSAssignedTo>();
        while (rs.next()) {
            list.add(loadSingle(rs));
        }
        return list;
    }

    @Override
    public RSAssignedTo loadSingle(ResultSet rs) throws SQLException {
        RSAssignedTo rsAssignedTo = new RSAssignedTo();
        
        rsAssignedTo.setStaffID(rs.getInt("staffID"));
        rsAssignedTo.setRoomID(rs.getInt("roomID"));
        rsAssignedTo.setCustomerID(rs.getInt("customerID"));
        rsAssignedTo.setHotelID(rs.getInt("hotelID"));
        
        return rsAssignedTo;
    }

    @Override
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, RSAssignedTo object,
            boolean newInstance) throws SQLException {
        String stmt = "";
        
        if(newInstance) {
            stmt = "INSERT INTO RSAssignedTo VALUES (?,?,?,?)";
            pstring = conn.prepareStatement(stmt);
            pstring.setInt( 1, object.getStaffID() );
            pstring.setInt( 2, object.getRoomID() );
            pstring.setInt( 3, object.getCustomerID() );   
            pstring.setInt( 4, object.getHotelID());
        } else {
            stmt = "UPDATE RSAssignedTo SET staffID=?, "
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
