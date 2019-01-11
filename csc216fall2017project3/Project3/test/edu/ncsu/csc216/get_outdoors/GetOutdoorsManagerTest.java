/**
 * 
 */
package edu.ncsu.csc216.get_outdoors;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.model.Activity;
import edu.ncsu.csc216.get_outdoors.model.ActivityList;
import edu.ncsu.csc216.get_outdoors.model.Park;
import edu.ncsu.csc216.get_outdoors.model.TrailList;

/**
 * Test class for GetOutDoorsManager.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class GetOutdoorsManagerTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#isChanged()}.
	 */
	@Test
	public void testIsChanged() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		assertFalse(manager.isChanged());

		manager.setChanged(true);
		assertTrue(manager.isChanged());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#setFilename(java.lang.String)}.
	 */
	@Test
	public void testSetFilename() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		manager.setFilename("NCSU.md");

		assertEquals(manager.getFilename(), "NCSU.md");

		try {
			manager.setFilename(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			manager.setFilename("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			manager.setFilename("       ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		manager.setFilename(" NCSU.md  ");
		assertEquals(manager.getFilename(), "NCSU.md");

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#getNumTrailLists()}.
	 */
	@Test
	public void testGetNumTrailLists() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		assertEquals(manager.getNumTrailLists(), 0);

		Park park1 = new Park("park-1", "ParkName1", "ParkDescription");
		Park park2 = new Park("park-2", "ParkName2", "ParkDescription");
		Park park3 = new Park("park-3", "ParkName3", "ParkDescription");
		Park park4 = new Park("park-4", "ParkName4", "ParkDescription");

		manager.addTrailList(park1);
		assertEquals(manager.getNumTrailLists(), 1);

		manager.addTrailList(park2);
		assertEquals(manager.getNumTrailLists(), 2);

		manager.addTrailList(park3);
		assertEquals(manager.getNumTrailLists(), 3);

		manager.addTrailList(park4);
		assertEquals(manager.getNumTrailLists(), 4);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#getTrailList(int)}.
	 */
	@Test
	public void testGetTrailList() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		assertEquals(manager.getNumTrailLists(), 0);

		Park park1 = new Park("park-1", "ParkName1", "ParkDescription");
		Park park2 = new Park("park-2", "ParkName2", "ParkDescription");
		Park park3 = new Park("park-3", "ParkName3", "ParkDescription");
		Park park4 = new Park("park-4", "ParkName4", "ParkDescription");

		manager.addTrailList(park1);
		assertEquals(manager.getNumTrailLists(), 1);

		manager.addTrailList(park2);
		assertEquals(manager.getNumTrailLists(), 2);

		assertEquals(manager.getTrailList(0), new TrailList(park1));
		assertEquals(manager.getTrailList(1), new TrailList(park2));
		manager.addTrailList(park3);
		manager.addTrailList(park4);
		assertEquals(manager.getTrailList(2), new TrailList(park3));
		assertEquals(manager.getTrailList(3), new TrailList(park4));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#getTrailLists()}.
	 */
	@Test
	public void testGetTrailLists() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		assertEquals(manager.getNumTrailLists(), 0);

		Park park1 = new Park("park-1", "ParkName1", "ParkDescription");
		Park park2 = new Park("park-2", "ParkName2", "ParkDescription");
		Park park3 = new Park("park-3", "ParkName3", "ParkDescription");
		Park park4 = new Park("park-4", "ParkName4", "ParkDescription");

		manager.addTrailList(park1);
		assertEquals(manager.getNumTrailLists(), 1);

		manager.addTrailList(park2);
		assertEquals(manager.getNumTrailLists(), 2);

		manager.addTrailList(park3);
		assertEquals(manager.getNumTrailLists(), 3);

		manager.addTrailList(park4);
		assertEquals(manager.getNumTrailLists(), 4);

		TrailList[] trails = manager.getTrailLists();

		for (int i = 0; i < manager.getNumTrailLists(); i++) {
			assertEquals(trails[i], manager.getTrailList(i));
		}

		try {
			manager.getTrailList(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			manager.getTrailList(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#getActivities()}.
	 */
	@Test
	public void testGetActivities() {

		GetOutdoorsManager manager = new GetOutdoorsManager();
		assertEquals(manager.getActivities().size(), 0);
		ActivityList list = manager.getActivities();
		list.addActivity("Running", "Running on a trail", false, 3);
		assertEquals(list.size(), 1);
		assertEquals(list.getActivityAt(0), new Activity("act0", "Running", "Running on a trail", false, 3));

		list.addActivity("Biking", "Biking on a trail", false, 2);
		assertEquals(list.size(), 2);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#addTrailList(edu.ncsu.csc216.get_outdoors.model.Park)}.
	 */
	@Test
	public void testAddTrailList() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		assertEquals(manager.getNumTrailLists(), 0);

		Park park1 = new Park("park-1", "ParkName1", "ParkDescription");
		Park park2 = new Park("park-2", "ParkName2", "ParkDescription");
		Park park3 = new Park("park-3", "ParkName3", "ParkDescription");
		Park park4 = new Park("park-4", "ParkName4", "ParkDescription");

		manager.addTrailList(park1);
		assertEquals(manager.getNumTrailLists(), 1);

		manager.addTrailList(park2);
		assertEquals(manager.getNumTrailLists(), 2);

		manager.addTrailList(park3);
		assertEquals(manager.getNumTrailLists(), 3);

		manager.addTrailList(park4);
		assertEquals(manager.getNumTrailLists(), 4);

		TrailList[] trails = manager.getTrailLists();

		for (int i = 0; i < manager.getNumTrailLists(); i++) {
			assertEquals(trails[i], manager.getTrailList(i));
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#getParks()}.
	 */
	/*
	 * @Test public void testGetParks() { fail("Not yet implemented"); }
	 */
	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#update(java.util.Observable, java.lang.Object)}.
	 */
	/*
	 * @Test public void testUpdate() { fail("Not yet implemented"); }
	 */

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#saveDataFile(java.lang.String)}.
	 */
	@Test
	public void testSaveDataFile() {
		GetOutdoorsManager manager = new GetOutdoorsManager();
		manager.openDataFile("test-files/NCSU.md");
		assertEquals(manager.getActivities().size(), 3);
		assertEquals(manager.getParks().size(), 2);

		assertEquals(manager.getNumTrailLists(), 2);
		Park park1 = new Park("park-1", "ParkName1", "ParkDescription");
		manager.addTrailList(park1);

		assertEquals(manager.getNumTrailLists(), 3);

		assertTrue(manager.saveDataFile("test-files/NCSU.md"));
		GetOutdoorsManager manager2 = new GetOutdoorsManager();
		manager2.openDataFile("test-files/NCSU.md");

		assertEquals(manager2.getNumTrailLists(), 2);

		assertFalse(manager2.saveDataFile(null));
		assertFalse(manager2.saveDataFile(""));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.GetOutdoorsManager#openDataFile(java.lang.String)}.
	 */
	@Test
	public void testOpenDataFile() {

		GetOutdoorsManager manager = new GetOutdoorsManager();

		manager.openDataFile("test-files/NCSU.md");

		assertEquals(manager.getActivities().size(), 3);
		assertEquals(manager.getParks().size(), 2);

		assertEquals(manager.getNumTrailLists(), 2);

		//GetOutdoorsGUI gui = new GetOutdoorsGUI(manager);
		//gui.getGetOutdoorsManager().openDataFile("test-files/NCSU.md");

	}

}
