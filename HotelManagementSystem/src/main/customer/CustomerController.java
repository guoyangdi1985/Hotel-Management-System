package customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Customer Controller manages the actions of the database
 */
public class CustomerController {
	private CustomerData data;

	/**
	 * The constructor for customer controller.
	 */
	public CustomerController() {
		data = new CustomerSql();
	}

	/**
	 * Adds a customer tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param c the customer tuple to add
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
	public boolean add(Connection conn, Customer c) throws SQLException {
		return data.add(conn, c);
	}

	/**
	 * Update a specific customer tuple.
	 * 
	 * @param conn represents the connection with database
	 * @param c the customer tuple to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
	public boolean update(Connection conn, Customer c) throws SQLException {
		return data.update(conn, c);
	}

	/**
	 * Delete a specific customer tuple from the database
	 * 
	 * @param conn represents the connection with database
	 * @param c the customer tuple to delete
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 */
	public boolean delete(Connection conn, Customer c) throws SQLException {
		return data.delete(conn, c);
	}

	/**
	 * Get a specific customer from the database
	 * 
	 * @param customerID, this is the primary key for the Customer relation
	 * @return The customer from the database with the matching customerID
	 * @throws SQLException
	 */
	public Customer getSpecificCustomer(int customerID) throws SQLException {
		return data.getSpecificCustomer(customerID);
	}

	/**
	 * Get a list of all customers in the system
	 * @return A list of all customers in the system
	 * @throws SQLException throws SQL exception if the database throws one
	 */
	public List<Customer> getAll() throws SQLException {
		return data.getAll();
	}
 
	/**
	 * Update a specific customer's info.
	 * 
	 * @param conn represents the connection with database
	 * @param c the customer to update
	 * @return A boolean marking success or failure
	 * @throws SQLException
	 *  
	 */
	public boolean updateCustomer(Connection conn, Customer c) throws SQLException {
        return data.update(conn, c);
	}
}
