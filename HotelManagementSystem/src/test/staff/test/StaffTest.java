package staff.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbUtil.RunSqlScript;
import staff.Staff;
import staff.StaffController;

/**
 * Test the Staff class
 */
public class StaffTest {
	private StaffController controller;
	Staff s;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		RunSqlScript.runScript();
		controller = new StaffController();
	}
	
	@Test
	public void testController() throws SQLException {
		//First check that there are 8 staff in the list
		List<Staff> list = null;
		try {
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(3, list.size());

		//Next, add a staff to the list.
		try {
		    s = new Staff(0, "876-54-6337", "testName", "M", "333-333-3333", "testAddress", "Front Desk Representative", 1);
			controller.add(null, s);
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		//Verify that the staff was added correctly, and find the staffID assigned to it.
		assertEquals(4, list.size());
		boolean foundValue = false;
		int staffID = -1;
		for(Staff h : list) {
			if(h.getPhone().equals("333-333-3333")){
				foundValue = true;
				staffID = h.getStaffID();
				break;
			}
		}
		assertTrue(foundValue);		
		
		//Update the staff that was added with a new address
		//Verify that the staff was updated correctly
		Staff s = null;
		try {
		    s = controller.getSpecificStaff(staffID);
		    s.setSsn("876-54-6337");
		    s.setAddress("secondAddress");
			controller.update(null, s);
			s = controller.getSpecificStaff(staffID);
			assertEquals("secondAddress", s.getAddress());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		//Delete the staff from the database and ensure that
		//it was deleted correctly.
		try {
			controller.delete(null, s);
			list = null;
			list = controller.getAll();
			assertEquals(3, list.size());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		//select all staff of a certain type
		// front desk staff
		try {
			list = controller.getAllByStaffType("Front Desk Representative");
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(1, list.size());
		
		// manager staff
		try {
			list = controller.getAllByStaffType("Manager");
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(1, list.size());
		
		// catering staff
		try {
			list = controller.getAllByStaffType("Catering Staff");
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(1, list.size());
	}
}
