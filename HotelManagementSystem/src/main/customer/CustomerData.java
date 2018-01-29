package customer;

import java.sql.SQLException;

import entityUtil.DataBean;

/**
 * This interface is used by the CustomerController class for the various operations.
 */
public interface CustomerData extends DataBean<Customer>{
    
    /**
     * @param customerID - The id for the customer to be retrieved
     * @return A specific Customer from the database
     */
    public Customer getSpecificCustomer(int customerID) throws SQLException;
}
