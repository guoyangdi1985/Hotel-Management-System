package rsAssignedTo.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import checkin.CheckIn;
import checkin.CheckInController;
import dbUtil.RunSqlScript;
import rsAssignedTo.RSAssignedTo;
import rsAssignedTo.RSAssignedToController;
import staff.Staff;
import staff.StaffController;

/**
 * Test the RSAssignedTo class
 */
public class RSAssignedToTest {
	private RSAssignedToController controller;
	private StaffController staffController;
	private CheckInController checkInController;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		RunSqlScript.runScript();
		controller = new RSAssignedToController();
		staffController = new StaffController();
		checkInController = new CheckInController();
	}
	
	@Test
	public void testController() throws SQLException {
		List<RSAssignedTo> list = null;
		try {
			list = controller.getAll();
			assertEquals(0, list.size());
			Staff s = new Staff(0, "333-33-3333", "jimbo", "M", "999-999-9999", "123 street place, nowhere", "Room Service Staff", 1);
            staffController.add(null, s);
            CheckIn c = new CheckIn(1, 3, Timestamp.valueOf("2016-11-21 12:00:00"), Timestamp.valueOf("2016-11-14 12:00:00"), 3, "T", "T", 0f, 0f, 0f, "check");
            checkInController.add(null, c);
            RSAssignedTo rs = controller.getSpecificRSAssignedTo(3);
            assertTrue(rs != null);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
        
	}
}
