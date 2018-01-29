package room.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbUtil.RunSqlScript;
import room.Room;
import room.RoomController;

/**
 * Test the Room class
 */
public class RoomTest {
	private RoomController controller;
	Room r;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
		RunSqlScript.runScript();
		controller = new RoomController();
	}
	
	@Test
	public void testController() throws SQLException {
		List<Room> list = null;
		try {
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(3, list.size());
		
		//Next, add a room to the list.
		try {
		    r = new Room(1, 110, "Economy", 119, 2, 0);
			controller.add(null, r);
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		//Verify that the room was added correctly, and find the hotelID, roomNumber, and roomID assigned to it.
		assertEquals(4, list.size());
		boolean foundValue = false;
		int roomID = -1;
		for( Room h : list ) {
			if( h.getRoomNumber() == 110 && h.getRoomType().equals("Economy") ) {
				foundValue = true;
				roomID = h.getRoomID();
				break;
			}
		}
		assertTrue(foundValue);		
		
		//Update the Room that was added with a new address
		//Verify that the Room was updated correctly
		Room h = null;
		try {
		    r = controller.getSpecificRoom(roomID);
		    r.setRoomNumber(210);
		    r.setRoomType("Executive Suite");
		    r.setRate(90);
		    r.setMaxOccupancy(3);
			controller.update(null, r);
			h = controller.getSpecificRoom( roomID );
			assertEquals( 210, h.getRoomNumber() );
			assertEquals( "Executive Suite", h.getRoomType() );
			assertTrue( h.getRate() == 90 );
			assertEquals( 3, h.getMaxOccupancy() );
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		//Delete the Room from the database and ensure that
		//it was deleted correctly.
		try {
			controller.delete(null, h);
			list = null;
			list = controller.getAll();
			assertEquals(3, list.size());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
}
