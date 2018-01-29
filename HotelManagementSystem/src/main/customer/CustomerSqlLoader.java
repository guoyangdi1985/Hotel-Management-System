package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entityUtil.SQLLoader;

/**
 * This class is responsible for loading the customer data
 * into the database.
 */
public class CustomerSqlLoader implements SQLLoader<Customer>{

	/**
	 * Loads a list of customers from a result set.
	 * 
	 * @param rs the source for the customer list to be built
	 * @return the list of customers from the result set
	 * @throws SQLException
	 */
    @Override
    public List<Customer> loadList(ResultSet rs) throws SQLException {
        ArrayList<Customer> list = new ArrayList<Customer>();
        while (rs.next()) {
            list.add(loadSingle(rs));
        }
        return list;
    }

    /**
	 * Map rows from result set to Customer objects to be added to list
	 * 
	 * @param rs the source for the customer list to be built
	 * @return a Customer object to be added to the list
	 * @throws SQLException
	 */
    @Override
    public Customer loadSingle(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        
        customer.setCustomerID(rs.getInt("customerID"));
        customer.setAddress(rs.getString("address"));
        customer.setGender((rs.getString("gender")).charAt(0));
        customer.setName(rs.getString("name"));
        customer.setSsn(rs.getString("ssn"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }

    /**
	 * Generates a prepared SQL statement, either an insertion of a customer tuple or an
	 * update of a customer tuple
	 * 
	 * @param conn represents the connection with database
	 * @param pstring the prepared statement built and returned
	 * @param object the Customer object of interest
	 * @param newInstance tells us whether to do insertion of new tuple or an update of tuple
	 * @return A prepared SQL statement for either an insertion or update of customer tuple
	 * @throws SQLException
	 */
    @Override
    public PreparedStatement loadParameters(Connection conn, PreparedStatement pstring, Customer object,
            boolean newInstance) throws SQLException {
        String stmt = "";
        
        if(newInstance) {
            stmt = "INSERT INTO customer(customerID, name, gender, address, ssn, email) VALUES (customer_seq.nextval,?,?,?,?,?)";
        } else {
            stmt = "UPDATE customer SET customerID=" + object.getCustomerID() + ", "
                    + "name=?, "
            		+ "gender=?, "
                    + "address=?, "
                    + "ssn=?, "
                    + "email=? "
                    + "WHERE customerID=" + object.getCustomerID();
        }
        pstring = conn.prepareStatement(stmt);
        pstring.setString(1, object.getName());
        pstring.setString(2, object.getGender().toString());
        pstring.setString(3, object.getAddress());
        pstring.setString(4, object.getSsn());
        pstring.setString(5, object.getEmail());
        return pstring;
    }

}
