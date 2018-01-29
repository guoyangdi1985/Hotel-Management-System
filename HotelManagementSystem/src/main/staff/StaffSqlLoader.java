package staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * The Staff SQL loader class for the database
 */
public class StaffSqlLoader implements SQLLoader<Staff>{

	/**
	 * Loads a list of staff from a result set.
	 * 
	 * @param rs the source for the staff list to be built
	 * @return the list of staff from the result set
	 * @throws SQLException
	 */
    @Override
    public List<Staff> loadList(ResultSet rs) throws SQLException {
        ArrayList<Staff> list = new ArrayList<Staff>();
        while (rs.next()) {
            list.add(loadSingle(rs));
        }
        return list;
    }

    /**
	 * Map rows from result set to Staff objects to be added to list
	 * 
	 * @param rs the source for the staff list to be built
	 * @return a Staff object to be added to the list
	 * @throws SQLException
	 */
    @Override
    public Staff loadSingle(ResultSet rs) throws SQLException {
        Staff staff = new Staff();
        staff.setStaffID(rs.getInt("staffID"));
        staff.setSsn(rs.getString("ssn"));
        staff.setName(rs.getString("name"));
        staff.setGender((rs.getString("gender"))/*.charAt(0)*/);
        staff.setPhone(rs.getString("phone"));
        staff.setAddress(rs.getString("address"));
        staff.setStaffType(rs.getString("staffType"));
        staff.setHotelID(rs.getInt("hotelID"));
        return staff;
    }

    /**
	 * Generates a prepared SQL statement, either an insertion of a staff tuple or an
	 * update of a staff tuple
	 * 
	 * @param conn represents the connection with database
	 * @param pstring the prepared statement built and returned
	 * @param object the Staff object of interest
	 * @param newInstance tells us whether to do insertion of new tuple or an update of tuple
	 * @return A prepared SQL statement for either an insertion or update of staff tuple
	 * @throws SQLException
	 */
    @Override
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, Staff object,
            boolean newInstance) throws SQLException {
        String stmt = "";
        
        if(newInstance) {
            stmt = "INSERT INTO staff VALUES (staff_seq.nextval,?,?,?,?,?,?,?)";
        } else {
            stmt = "UPDATE staff SET staffID="+ object.getStaffID() + ", "
                    + "ssn=?, "
            		+ "name=?, "
            		+ "gender=?, "
                    + "phone=?, "
                    + "address=?, "
                    + "staffType=?, "
                    + "hotelID=? "
                    + "WHERE staffID=" + object.getStaffID();
        }
        pstring = conn.prepareStatement(stmt);
        pstring.setString(1, object.getSsn());
        pstring.setString(2, object.getName());
        pstring.setString(3, object.getGender());
        pstring.setString(4, object.getPhone());
        pstring.setString(5, object.getAddress());
        pstring.setString(6, object.getStaffType());
        pstring.setInt(7, object.getHotelID());
        return pstring;
    }
}