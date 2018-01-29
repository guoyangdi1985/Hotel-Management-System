package cAssignedTo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbUtil.DBConnection;

public class CAssignedToSql implements CAssignedToData{

    private CAssignedToSqlLoader sqlLoader;
    private Connection conn = null;
    private CAssignedToSqlValidator sqlValidator;
    
    public CAssignedToSql() {
        sqlLoader = new CAssignedToSqlLoader();
        sqlValidator = new CAssignedToSqlValidator();
    }
    
    @Override
    public List<CAssignedTo> getAll() throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM CAssignedTo");
            rs = pstring.executeQuery();
            List<CAssignedTo> list = sqlLoader.loadList(rs);
            return list;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

    @Override
    public boolean add(Connection connection, CAssignedTo addObj) throws SQLException { 
        try {
            conn = DBConnection.getInstance().getDBConnection();
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
    public boolean update(Connection connection, CAssignedTo updateObj) throws SQLException {
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
    public boolean delete(Connection connection, CAssignedTo deleteObj) throws SQLException {
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
            pstring = conn.prepareStatement("DELETE FROM CAssignedTo WHERE roomID=?");
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
    public CAssignedTo getSpecificCAssignedTo(int roomID) throws SQLException {
        try {
            conn = DBConnection.getInstance().getDBConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PreparedStatement pstring = null;
        ResultSet rs = null;
        CAssignedTo cAssignedTo = null;
        try {
            pstring = conn.prepareStatement("SELECT * FROM CAssignedTo WHERE roomID=?");
            pstring.setInt(1, roomID);
            rs = pstring.executeQuery();
            List<CAssignedTo> list = sqlLoader.loadList(rs);
            if(list.size() > 0) {
            	cAssignedTo = list.get(0);
            }
            return cAssignedTo;
        } finally {
        	DBConnection.getInstance().closeConnection(conn, pstring);
        }
    }

}
