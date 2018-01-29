package hotel.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dbUtil.RunSqlScript;
import hotel.Hotel;
import hotel.HotelController;

/**
 * Test the Hotel class
 */
public class HotelTest {
	private HotelController controller;
	Hotel h;
	
	@Before
	public void setup() throws SQLException, ClassNotFoundException {
	    RunSqlScript.runScript();
		controller = new HotelController();
	}
	
	@Test
	public void testController() throws SQLException {
		//First check that there are 4 hotels in the list
		List<Hotel> list = null;
		try {
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		assertEquals(1, list.size());
		
		//Next, add a hotel to the list.
		try {
		    h = new Hotel(0, "testHotel", "testAddress", "333-333-3333");
			controller.add(null, h);
			list = controller.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		//Verify that the hotel was added correctly, and find the hotelID assigned to it.
		assertEquals(2, list.size());
		boolean foundValue = false;
		int hotelID = -1;
		for(Hotel h : list) {
			if(h.getPhone().equals("333-333-3333")){
				foundValue = true;
				hotelID = h.getHotelID();
				break;
			}
		}
		assertTrue(foundValue);		
		
		//Update the hotel that was added with a new address
		//Verify that the hotel was updated correctly
		Hotel h = null;
		try {
		    h = controller.getSpecificHotel(hotelID);
		    h.setAddress("secondAddress");
			controller.update(null, h);
			h = controller.getSpecificHotel(hotelID);
			assertEquals("secondAddress", h.getAddress());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		//Delete the Hotel from the database and ensure that
		//it was deleted correclty.
		try {
			controller.delete(null, h);
			list = null;
			list = controller.getAll();
			assertEquals(1, list.size());
		} catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
	}	
}
