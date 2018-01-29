package rsAssignedTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbUtil.DBConnection;

public class RSAssignedToSql implements RSAssignedToData{

    private RSAssignedToSqlLoader sqlLoader;
    private Connection conn = null;
    private RSAssignedToSqlValidator sqlValidator;
    
    public RSAssignedToSql() {
        sqlLoader = new RSAssignedToSqlLoader();
        sqlValidator = new RSAssignedToSqlValidator();
    }
    
    @Override
    public List<RSAssignedTo> getAll() throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM RSAssignedTo");
            rs = pstring.executeQuery();
            List<RSAssignedTo> list = sqlLoader.loadList(rs);
            return list;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    @Override
    public boolean add(Connection connection, RSAssignedTo addObj) throws SQLException { 
        try {
            if(connection == null) {
                conn = DBConnection.getInstance().getDBConnection();
            } else {
                conn = connection;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        try {
            sqlValidator.validate(addObj);
            int results;
            pstring = sqlLoader.loadParameters(conn, pstring, addObj, true);
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
            if(connection == null) {
                DBConnection.getInstance().closeConnection(conn, pstring);
            }
        }
    }

    @Override
    public boolean update(Connection connection, RSAssignedTo updateObj) throws SQLException {
        try {
            if(connection == null) {
                conn = DBConnection.getInstance().getDBConnection();
            } else {
                conn = connection;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        try {
            sqlValidator.validate(updateObj);
            int results;
            pstring = sqlLoader.loadParameters(conn, pstring, updateObj, false);
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
            if(connection == null) {
                DBConnection.getInstance().closeConnection(conn, pstring);
            }
        }
    }

    @Override
    public boolean delete(Connection connection, RSAssignedTo deleteObj) throws SQLException {
        try {
            if(connection == null) {
                conn = DBConnection.getInstance().getDBConnection();
            } else {
                conn = connection;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean retVal = false;
        PreparedStatement pstring = null;
        int results;
        try {
            pstring = conn.prepareStatement("DELETE FROM RSAssignedTo WHERE roomID=?");
            pstring.setInt(1, deleteObj.getRoomID());
            results = pstring.executeUpdate();
            retVal = (results > 0);
            return retVal;
        } finally {
            if(connection == null) {
                DBConnection.getInstance().closeConnection(conn, pstring);
            }
        }
    }

    @Override
    public RSAssignedTo getSpecificRSAssignedTo(int roomID) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        RSAssignedTo rsAssignedTo = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM RSAssignedTo WHERE roomID=?");
            pstring.setInt(1, roomID);
            rs = pstring.executeQuery();
            List<RSAssignedTo> list = sqlLoader.loadList(rs);
            if(list.size() > 0) {
            	rsAssignedTo = list.get(0);
            }
            return rsAssignedTo;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

}
