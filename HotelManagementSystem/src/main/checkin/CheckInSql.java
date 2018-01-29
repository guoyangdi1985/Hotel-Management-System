package checkin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cAssignedTo.CAssignedTo;
import cAssignedTo.CAssignedToController;
import dbUtil.DBConnection;
import room.Room;
import room.RoomController;
import rsAssignedTo.RSAssignedTo;
import rsAssignedTo.RSAssignedToController;
import staff.Staff;
import staff.StaffController;

/**
 * This class involves connecting to the database and provides 
 * various utility methods pertaining to checkin queries and updates
 * with the connection.
 */
public class CheckInSql implements CheckInData{

    private CheckInSqlLoader sqlLoader;
    private Connection conn = null;
    private CheckInSqlValidator sqlValidator;
    private StaffController staffController;
    private RSAssignedToController rsController;
    private RoomController roomController;
    private CAssignedToController cController;
    
    /**
	 * Default empty constructor.
	 */
    public CheckInSql() {
        sqlLoader = new CheckInSqlLoader();
        sqlValidator = new CheckInSqlValidator();
        rsController = new RSAssignedToController();
        staffController = new StaffController();
        roomController = new RoomController();
        cController = new CAssignedToController();
    }
    
    /**
	 * Get a list of all checkins in the system
	 * 
	 * @return A list of all check-ins in the system
	 * @throws SQLException
	 */
    @Override
    public List<CheckIn> getAll() throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM checkin");
            rs = pstring.executeQuery();
            List<CheckIn> list = sqlLoader.loadList(rs);
            return list;
        } finally {
            DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
	 * Adds a checkin tuple.
	 * 
	 * @param connection represents the connection with database
	 * @param addObj the checkin tuple to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    @Override
    public boolean add(Connection connection, CheckIn addObj) throws SQLException { 
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        try {
            //When we add a check in, we might need to also add an entry to the two assignedTo
            //tables if the checkin has room service or catering selected.
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            sqlValidator.validate(addObj);
            
            //Now that the checkin is validated, determine whether to create assignedTo objects
            if(addObj.getHasCatering().equals("T")) {
                assignCateringStaff(conn, pstring, addObj);
            }
            if(addObj.getHasRoomService().equals("T")) {
                assignRoomServiceStaff(conn, pstring, addObj);
            }
            
            int results;
            pstring = sqlLoader.loadParameters(conn, pstring, addObj, true);
            results = pstring.executeUpdate();
            conn.commit(); //Commit the changes
            retVal = (results > 0);
            return retVal;
        } catch (SQLException e) {
            //If an SQLException is thrown during the transaction, roll the
            //transaction back to the original state and pass the exception back.
            conn.rollback();
            throw e;
        }finally {
            DBConnection.getInstance().closeConnection(conn, pstring);
        }
    } 

	/**
	 * Update a specific check-in tuple.
	 * 
	 * @param connection represents the connection with database
	 * @param updateObj the checkin tuple to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
    @Override
    public boolean update(Connection connection, CheckIn updateObj) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        try {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            sqlValidator.validate(updateObj);
            
            //Now that the checkin is validated, determine whether to create assignedTo objects
            //If the checkin already has staff assigned when it's supposed to, don't do anything.
            //If staff are supposed to be assigned, but aren't, then assign them.
            //If staff are not supposed to be assigned, attempt to delete any outstanding assignments.
            if(updateObj.getHasRoomService().equals("T")) {
                RSAssignedTo rs = rsController.getSpecificRSAssignedTo(updateObj.getRoomID());
                if(rs == null) {
                    assignRoomServiceStaff(conn, pstring, updateObj);
                }
            } else {
                deleteAssignedRoomService(conn, pstring, updateObj);
            }
            if(updateObj.getHasCatering().equals("T")) {
                CAssignedTo c = cController.getSpecificCAssignedTo(updateObj.getRoomID());
                if(c == null) {
                    assignCateringStaff(conn, pstring, updateObj);
                }
            } else {
                deleteAssignedCatering(conn, pstring, updateObj);
            }
            
            int results;
            pstring = sqlLoader.loadParameters(conn, pstring, updateObj, false);
            results = pstring.executeUpdate();
            conn.commit();
            retVal = (results > 0);
            return retVal;
        } catch (SQLException e) {
            //If an SQLException is thrown during the transaction, roll the
            //transaction back to the original state and pass the exception back.
            conn.rollback();
            throw e;
        } finally {
            DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
	 * Delete a specific check-in tuple from the database
	 * 
	 * @param connection represents the connection with database
	 * @param deleteObj the checkin tuple to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
    @Override
    public boolean delete(Connection connection, CheckIn deleteObj) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        int results;
        try {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            
            //Try to delete any outstanding staff assignments
            deleteAssignedCatering(conn, pstring, deleteObj);
            deleteAssignedRoomService(conn, pstring, deleteObj);
            
            pstring = conn.prepareStatement("DELETE FROM checkin WHERE roomID=?");
            pstring.setInt(1, deleteObj.getRoomID());
            results = pstring.executeUpdate();
            conn.commit();
            retVal = (results > 0);
            return retVal;
        } catch (SQLException e) {
            //If an SQLException is thrown during the transaction, roll the
            //transaction back to the original state and pass the exception back.
            conn.rollback();
            throw e;
        } finally {
            DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
	 * Get a specific check-in tuple from the database
	 * 
	 * @param roomID, this is the primary key for the CheckIn relation
	 * @return The check-in tuple from the database with the matching roomID
	 * @throws SQLException
	 */
    @Override
    public CheckIn getSpecificCheckIn(int roomID) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        CheckIn checkin = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM checkin WHERE roomID=?");
            pstring.setInt(1, roomID);
            rs = pstring.executeQuery();
            List<CheckIn> list = sqlLoader.loadList(rs);
            if(list.size() > 0) {
                checkin = list.get(0);
            }
            return checkin;
        } finally {
            DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }
    
    /**
	 * Get a certain customer's check-in list from the database
	 * 
	 * @param customerID identifies the customer of interest
	 * @return the particular customer's checkins as a list
	 * @throws SQLException
	 */
    public List<CheckIn> getCustomerCheckIns(int customerID) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM checkin WHERE customerID=?");
            pstring.setInt(1, customerID);
            rs = pstring.executeQuery();
            List<CheckIn> list = sqlLoader.loadList(rs);
            return list;
        } finally {
            DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    /**
     * If a checkin requires a room service staff to be assigned, this method automatically 
     * finds the room service staff with the fewest assigned rooms at the hotel where the
     * room being checked into is located, and then assigned that staff member to the room.
     * @param connection represents connection to the database
     * @param pstring represents SQL statement
     * @param addObj need to see if this checkin requires room service staff to be assigned
     * @throws SQLException
     */
    private void assignRoomServiceStaff(Connection connection, PreparedStatement pstring, CheckIn addObj) throws SQLException {
        Room r = roomController.getSpecificRoom(addObj.getRoomID());
        int hotelID = r.getHotelID();
        List<Staff> initialList = staffController.getAllByStaffType("Room Service Staff");
        List<Staff> specificHotelStaffList = new ArrayList<Staff>();
        //Go through the list, only keeping the staff from the desired hotel
        for(Staff s : initialList) {
            if(s.getHotelID() == hotelID) {
                specificHotelStaffList.add(s);
            }
        }
        
        //Find only the Assigned entries originating from the desired hotel.
        List<RSAssignedTo> initialAssignedList = rsController.getAll();
        List<RSAssignedTo> specificHotelAssignedList = new ArrayList<RSAssignedTo>();
        for(RSAssignedTo rs : initialAssignedList) {
            if(rs.getHotelID() == hotelID) {
                specificHotelAssignedList.add(rs);
            }
        }
        
        //Find out which staff member has the fewest assigned jobs, and assign the job to him/her
        int minCount = -1;
        int rsID = -1;
        for(Staff s : specificHotelStaffList) {
            int count = 0;
            for(RSAssignedTo rs : specificHotelAssignedList) {
                if(rs.getStaffID() == s.getStaffID()) {
                    count++;
                }
            }
            if(minCount == -1) {
                minCount = count;
                rsID = s.getStaffID();
            } else if(count < minCount){
                minCount = count;
                rsID = s.getStaffID();
            }
        }
        if(rsID == -1) {
            throw new SQLException("No available Room Service Staff to assign to room");
        } else {
            RSAssignedTo rs = new RSAssignedTo(rsID, addObj.getRoomID(), addObj.getCustomerID(), hotelID);
            rsController.add(connection, rs);
        }
    }
    
    /**
     * If a checkin requires a catering staff to be assigned, this method automatically 
     * finds the catering staff with the fewest assigned rooms at the hotel where the
     * room being checked into is located, and then assigned that staff member to the room.
     * @param connection represents connection to the database
     * @param pstring represents SQL statement
     * @param addObj need to see if this checkin requires catering staff to be assigned
     * @throws SQLException
     */
    private void assignCateringStaff(Connection connection, PreparedStatement pstring, CheckIn addObj) throws SQLException {
        Room r = roomController.getSpecificRoom(addObj.getRoomID());
        int hotelID = r.getHotelID();
        List<Staff> initialList = staffController.getAllByStaffType("Catering Staff");
        List<Staff> specificHotelStaffList = new ArrayList<Staff>();
        //Go through the list, only keeping the staff from the desired hotel
        for(Staff s : initialList) {
            if(s.getHotelID() == hotelID) {
                specificHotelStaffList.add(s);
            }
        }
        
        //Find only the Assigned entries originating from the desired hotel.
        List<CAssignedTo> initialAssignedList = cController.getAll();
        List<CAssignedTo> specificHotelAssignedList = new ArrayList<CAssignedTo>();
        for(CAssignedTo c : initialAssignedList) {
            if(c.getHotelID() == hotelID) {
                specificHotelAssignedList.add(c);
            }
        }
        
        //Find out which staff member has the fewest assigned jobs, and assign the job to him/her
        int minCount = -1;
        int cID = -1;
        for(Staff s : specificHotelStaffList) {
            int count = 0;
            for(CAssignedTo c : specificHotelAssignedList) {
                if(c.getStaffID() == s.getStaffID()) {
                    count++;
                }
            }
            if(minCount == -1) {
                minCount = count;
                cID = s.getStaffID();
            } else if(count < minCount){
                minCount = count;
                cID = s.getStaffID();
            }
        }
        if(cID == -1) {
            throw new SQLException("No available Catering Staff to assign to room");
        } else {
            CAssignedTo c = new CAssignedTo(cID, addObj.getRoomID(), addObj.getCustomerID(), hotelID);
            cController.add(connection, c);
        }
    }
    
    
    /**
     * Delete the entry from the service assignment table with the roomID equal to the check-in's roomID
     * @param connection represents the connection to the database
     * @param pstring represents SQL statement
     * @param obj has the roomID that is used to delete entry from service assignment table
     * @throws SQLException
     */
    private void deleteAssignedCatering(Connection connection, PreparedStatement pstring, CheckIn obj) throws SQLException {
        String stmt = "DELETE FROM CAssignedTo WHERE roomID=?";
        pstring = connection.prepareStatement(stmt);
        pstring.setInt(1, obj.getRoomID());
        pstring.executeUpdate();
    }

    /**
     * Delete the entry from the service assignment table with the roomID equal to the check-in's roomID
     * @param connection represents the connection to the database
     * @param pstring represents SQL statement
     * @param obj has the roomID that is used to delete entry from service assignment table
     * @throws SQLException
     */
   private void deleteAssignedRoomService(Connection connection, PreparedStatement pstring, CheckIn obj) throws SQLException {
       String stmt = "DELETE FROM RSAssignedTo WHERE roomID=?";
       pstring = connection.prepareStatement(stmt);
       pstring.setInt(1, obj.getRoomID());
       pstring.executeUpdate();
   }

}
