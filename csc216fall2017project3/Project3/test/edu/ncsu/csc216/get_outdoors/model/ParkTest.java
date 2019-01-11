/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Park.
 * 
 * @author jballie@ncsu.edu
 * @author mchoi@ncsu.edu
 *
 */
public class ParkTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#Park(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testParkStringStringString() {
		Park park = new Park("park-2", "Test", "Test Description");
		assertEquals("park-2", park.getParkID());
		assertEquals("Test", park.getName());
		assertEquals("Test Description", park.getDescription());
		assertEquals(0, park.getSnowChange(), 0.1);
		
		try {
			park = new Park("     ", "Test", "Test Description");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			park = new Park("park-2", "     ", "Test Description");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		park = new Park(" park-2", "Test", "Test Description");
		assertEquals("park-2", park.getParkID());
		park = new Park("park-2", "Test", "Test Description ");
		assertEquals("Test Description", park.getDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#Park(java.lang.String, java.lang.String, java.lang.String, double)}.
	 */
	@Test
	public void testParkStringStringStringDouble() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		assertEquals("park-2", park.getParkID());
		assertEquals("Test", park.getName());
		assertEquals("Test Description", park.getDescription());
		assertEquals(1.2, park.getSnowChange(), 0.1);
		
		try {
			park = new Park("", "Test", "Test Description", 1.2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		try {
			park = new Park("park-2", " ", "Test Description", 1.2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}
		park = new Park(" park-2", "Test", "Test Description", 1.2);
		assertEquals("park-2", park.getParkID());
		park = new Park("park-2", "Test", "Test Description ", 1.2);
		assertEquals("Test Description", park.getDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#getName()}.
	 */
	@Test
	public void testGetName() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		assertEquals("Test", park.getName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#getParkID()}.
	 */
	@Test
	public void testGetParkID() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		assertEquals("park-2", park.getParkID());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		assertEquals("Test Description", park.getDescription());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#getSnowChange()}.
	 */
	@Test
	public void testGetSnowChange() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		assertEquals(1.2, park.getSnowChange(), 0.1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#setSnowChange(double)}.
	 */
	@Test
	public void testSetSnowChange() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		park.setSnowChange(3.1);
		assertEquals(3.1, park.getSnowChange(), 0.1);
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.get_outdoors.model.Park#toString()}.
	 */
	@Test
	public void testToString() {
		Park park = new Park("park-2", "Test", "Test Description", 1.2);
		assertEquals("Test\tTest Description\t1.2", park.toString());
	}

}
