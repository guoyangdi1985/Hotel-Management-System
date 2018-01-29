package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbUtil.DBConnection;

/**
 * This class involves connecting to the database and provides 
 * various utility methods pertaining to customer queries and updates
 * with the connection.
 */
public class CustomerSql implements CustomerData {

	private CustomerSqlLoader sqlLoader;
	private Connection conn = null;
	private CustomerSqlValidator sqlValidator;

	/**
	 * Default empty constructor.
	 */
	public CustomerSql() {
		sqlLoader = new CustomerSqlLoader();
		sqlValidator = new CustomerSqlValidator();
	}

	/**
	 * Get a list of all customers in the system
	 * 
	 * @return A list of all customers in the system
	 * @throws SQLException
	 */
	@Override
	public List<Customer> getAll() throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstring = null;
		ResultSet rs = null;
		try {
			pstring = conn.prepareStatement("SELECT * FROM customer");
			rs = pstring.executeQuery();
			List<Customer> list = sqlLoader.loadList(rs);
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("Error closing ResultSet");
			} finally {
				DBConnection.getInstance().closeConnection(conn, pstring);
			}
		}
	}

	/**
	 * Adds a customer to the DB.
	 * 
	 * @param connection represents the connection with database
	 * @param addObj the customer to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
	@Override
	public boolean add(Connection connection, Customer addObj) throws SQLException {
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
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}

	/**
	 * Update a specific customer tuple.
	 * 
	 * @param connection represents the connection with database
	 * @param updateObj the customer to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
	@Override
	public boolean update(Connection connection, Customer updateObj) throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
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
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}

	/**
	 * Delete a specific customer from the database
	 * 
	 * @param connection represents the connection with database
	 * @param deleteObj the customer to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
	@Override
	public boolean delete(Connection connection, Customer deleteObj) throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean retVal = false;
		PreparedStatement pstring = null;
		int results;
		try {
			pstring = conn.prepareStatement("DELETE FROM customer WHERE customerID=?");
			pstring.setInt(1, deleteObj.getCustomerID());
			results = pstring.executeUpdate();
			retVal = (results > 0);
			return retVal;
		} finally {
			DBConnection.getInstance().closeConnection(conn, pstring);
		}
	}

	/**
	 * Get a specific customer from the database
	 * 
	 * @param customerID, this is the primary key for the Customer relation
	 * @return The customer tuple from the database with the matching customerID
	 * @throws SQLException
	 */
	@Override
	public Customer getSpecificCustomer(int customerID) throws SQLException {
		try {
			conn = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		PreparedStatement pstring = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			pstring = conn.prepareStatement("SELECT * FROM customer WHERE customerID=?");
			pstring.setInt(1, customerID);
			rs = pstring.executeQuery();
			List<Customer> list = sqlLoader.loadList(rs);
			if (list.size() > 0) {
				customer = list.get(0);
			}
			return customer;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("Error closing ResultSet");
			} finally {
				DBConnection.getInstance().closeConnection(conn, pstring);
			}
		}
	}

}
