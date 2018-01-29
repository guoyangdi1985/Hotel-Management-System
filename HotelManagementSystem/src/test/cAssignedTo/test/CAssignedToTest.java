package cAssignedTo.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cAssignedTo.CAssignedTo;
import cAssignedTo.CAssignedToController;
import dbUtil.RunSqlScript;

/**
 * Test the CAssignedTo class
 */
public class CAssignedToTest {
	private CAssignedToController controller;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		RunSqlScript.runScript();
		controller = new CAssignedToController();
	}
	
	@Test
	public void testController() throws SQLException {
		List<CAssignedTo> list = null;
		try {
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(2, list.size());
		//CAssignedTo c = controller.getSpecificCAssignedTo(list.get(0).getRoomID());
		
	}
}
