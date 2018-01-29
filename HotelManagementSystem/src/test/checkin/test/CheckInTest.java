package checkin.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cAssignedTo.CAssignedTo;
import cAssignedTo.CAssignedToController;
import dbUtil.RunSqlScript;
import rsAssignedTo.RSAssignedTo;
import rsAssignedTo.RSAssignedToController;
import staff.Staff;
import staff.StaffController;
import checkin.CheckIn;
import checkin.CheckInController;

/**
 * Test the CheckIn class
 */
public class CheckInTest {
	private CheckInController controller;
	private StaffController staffController;
	private RSAssignedToController rsController;
	private CAssignedToController cController;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		controller = new CheckInController();
		staffController = new StaffController();
		rsController = new RSAssignedToController();
		cController = new CAssignedToController();
		RunSqlScript.runScript();
	}
	
	@Test
	public void testController() throws SQLException {
		
		List<CheckIn> list = null;
		List<RSAssignedTo> rsList = null;
		List<CAssignedTo> cList = null;
		try {
			list = controller.getAll();
			rsList = rsController.getAll();
			cList = cController.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		assertEquals(2, list.size());
		assertEquals(0, rsList.size());
		assertEquals(2, cList.size());
		
		//Next, add a checkin entry to the list.
		try {
			Staff s = new Staff(0, "333-33-3333", "jimbo", "M", "999-999-9999", "123 street place, nowhere", "Room Service Staff", 1);
			staffController.add(null, s);
			CheckIn c = new CheckIn(1, 3, Timestamp.valueOf("2016-11-21 12:00:00"), Timestamp.valueOf("2016-11-14 12:00:00"), 3, "T", "T", 0f, 0f, 0f, "check");
			controller.add(null, c);
			list = null;
            rsList = null;
            cList = null;
			list = controller.getAll();
			rsList = rsController.getAll();
            cList = cController.getAll();
			
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		// Verify that the checkin entry was added correctly, and find the roomID assigned to it.
		assertEquals(3, list.size());
		assertEquals(1, rsList.size());
		assertEquals(3, cList.size());
		boolean foundValue = false;
		int roomID = -1;
		for(CheckIn c : list) {
			if(c.getRoomID() == 3) {
				foundValue = true;
				roomID = c.getRoomID();
				break;
			}
		}
		assertTrue(foundValue);
		foundValue = false;
		RSAssignedTo rs = rsController.getSpecificRSAssignedTo(roomID);
		CAssignedTo ca = cController.getSpecificCAssignedTo(roomID);
		if(rs == null) {
		    Assert.fail();
		}
		if(ca == null) {
		    Assert.fail();
		}
		
		// Update the checkin that was added with a new payment method
		// Verify that the checkin was updated correctly
		CheckIn c = null;
		try {
		    c = controller.getSpecificCheckIn(roomID);
		    c.setPaymentMethod("Credit Card (3333-3333-3333-3333)");
		    c.setHasCatering("F");
		    c.setHasRoomService("F");
			controller.update(null, c);
			list = null;
            rsList = null;
            cList = null;
			list = controller.getAll();
            rsList = rsController.getAll();
            cList = cController.getAll();
			c = controller.getSpecificCheckIn(roomID);
			assertEquals("Credit Card (3333-3333-3333-3333)", c.getPaymentMethod());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(3, list.size());
        assertEquals(0, rsList.size());
        assertEquals(2, cList.size());
        try {
            c = controller.getSpecificCheckIn(roomID);
            c.setHasCatering("T");
            c.setHasRoomService("T");
            controller.update(null, c);
            list = null;
            rsList = null;
            cList = null;
            list = controller.getAll();
            rsList = rsController.getAll();
            cList = cController.getAll();
        } catch(SQLException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(3, list.size());
        assertEquals(1, rsList.size());
        assertEquals(3, cList.size());
		
		// Delete the checkin entry from the database and ensure that
		// it was deleted correctly.
		try {
			controller.delete(null, c);
			list = null;
			rsList = null;
			cList = null;
			list = controller.getAll();
            rsList = rsController.getAll();
            cList = cController.getAll();
			assertEquals(2, list.size());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(2, list.size());
        assertEquals(0, rsList.size());
        assertEquals(2, cList.size());
		
	}
}

