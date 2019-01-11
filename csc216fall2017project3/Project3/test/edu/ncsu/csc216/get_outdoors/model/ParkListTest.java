
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for ParkList.
 * 
 * @author jballie@ncsu.edu
 * @author mchoi@ncsu.edu
 *
 */
public class ParkListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#ParkList()}.
	 */
	@Test
	public void testParkList() {
		ParkList pl = new ParkList();
		assertEquals("Parks", pl.getName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#getName()}.
	 */
	@Test
	public void testGetName() {
		ParkList pl = new ParkList();
		assertEquals("Parks", pl.getName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#addPark(java.lang.String, java.lang.String, double)}.
	 */
	@Test
	public void testAddPark() {
		ParkList pl = new ParkList();
		assertTrue(pl.addPark("Test", "Test Description", 1.2));
		assertEquals(1, pl.size());

		try {
			pl.addPark("Test", "Test Description", 1.2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#getParkAt(int)}.
	 */
	@Test
	public void testGetParkAt() {
		ParkList pl = new ParkList();
		pl.addPark("Test", "Test Description", 1.2);
		pl.addPark("Test2", "Test Description2", 3.1);
		pl.addPark("Test3", "Test Description3", -1.2);
		assertEquals("Test2", pl.getParkAt(1).getName());
		assertEquals("park-1", pl.getParkAt(1).getParkID());
		assertEquals("Test Description2", pl.getParkAt(1).getDescription());

		try {
			pl.getParkAt(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}

		try {
			pl.getParkAt(-2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(null, e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#size()}.
	 */
	@Test
	public void testSize() {
		ParkList pl = new ParkList();
		pl.addPark("Test", "Test Description", 1.2);
		pl.addPark("Test2", "Test Description2", 3.1);
		pl.addPark("Test3", "Test Description3", -1.2);
		assertEquals(3, pl.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		ParkList pl = new ParkList();
		assertTrue(pl.isEmpty());
		pl.addPark("Test", "Test Description", 1.2);
		pl.addPark("Test2", "Test Description2", 3.1);
		pl.addPark("Test3", "Test Description3", -1.2);
		assertFalse(pl.isEmpty());
	}

	// /**
	// * Test method for
	// * {@link
	// edu.ncsu.csc216.get_outdoors.model.ParkList#update(java.util.Observable,
	// java.lang.Object)}.
	// */
	// @Test
	// public void testUpdate() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#indexOfID(java.lang.String)}.
	 */
	@Test
	public void testIndexOfID() {
		ParkList pl = new ParkList();
		pl.addPark("Test", "Test Description", 1.2);
		pl.addPark("Test2", "Test Description2", 3.1);
		pl.addPark("Test3", "Test Description3", -1.2);
		assertEquals(2, pl.indexOfID("park-2"));
		assertEquals(-1, pl.indexOfID("testingforinvalidID"));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ParkList#get2DArray()}.
	 */
	@Test
	public void testGet2DArray() {
		ParkList pl = new ParkList();
		pl.addPark("Test", "Test Description", 1.2);
		pl.addPark("Test2", "Test Description2", 3.1);
		pl.addPark("Test3", "Test Description3", -1.2);

		Object[][] array = pl.get2DArray();
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i][0], pl.getParkAt(i).getParkID());
			assertEquals(array[i][1], pl.getParkAt(i).getName());
			assertEquals(array[i][2], pl.getParkAt(i).getDescription());
			assertEquals(array[i][3], pl.getParkAt(i).getSnowChange());

		}

	}

}
