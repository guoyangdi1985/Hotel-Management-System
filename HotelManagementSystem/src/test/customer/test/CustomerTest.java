package customer.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import customer.CustomerController;
import dbUtil.RunSqlScript;

/**
 * Test the Customer class
 */
public class CustomerTest {
	private CustomerController controller;
	Customer c;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		RunSqlScript.runScript();
		controller = new CustomerController();
	}
	
	@Test
	public void testController() throws SQLException {
		//First check that there are 4 customer in the list
		List<Customer> list = null;
		try {
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(2, list.size());
		
		//Next, add a customer to the list.
		try {
		    c = new Customer(0, "testName", 'M', "testAddress", "testEmail",  "759-90-8888");
			controller.add(null, c);
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		//Verify that the customer was added correctly, and find the customerID assigned to it.
		assertEquals(3, list.size());
		boolean foundValue = false;
		int customerID = -1;
		for(Customer h : list) {
			if(h.getSsn().equals("759-90-8888")){
				foundValue = true;
				customerID = h.getCustomerID();
				break;
			}
		}
		assertTrue(foundValue);		
		
		//Update the customer that was added with a new address
		//Verify that the customer was updated correctly
		Customer c = null;
		try {
		    c = controller.getSpecificCustomer(customerID);
		    c.setAddress("secondAddress");
			controller.update(null, c);
			c = controller.getSpecificCustomer(customerID);
			assertEquals("secondAddress", c.getAddress());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		//Delete the Customer from the database and ensure that
		//it was deleted correctly.
		try {
			controller.delete(null, c);
			list = null;
			list = controller.getAll();
			assertEquals(2, list.size());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
	}	
}
