/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for ActivityList.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class ActivityListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#getName()}.
	 */
	@Test
	public void testGetName() {
		ActivityList acts = new ActivityList();
		assertEquals(acts.getName(), "Activities");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#addActivity(java.lang.String, java.lang.String, boolean, int)}.
	 */
	@Test
	public void testAddActivity() {
		ActivityList acts = new ActivityList();
		assertEquals(acts.size(), 0);

		assertTrue(acts.addActivity("Biking", "Biking on a nature trail", false, 5));
		assertEquals(acts.size(), 1);

		Activity act0 = new Activity("act0", "Biking", "Biking on a nature trail", false, 5);
		assertEquals(acts.getActivityAt(0), act0);

		Activity act1 = new Activity("act1", "Running", "Running on a nature trail", false, 5);
		assertTrue(acts.addActivity("Running", "Running on a nature trail", false, 5));
		assertEquals(acts.getActivityAt(1), act1);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#getActivityAt(int)}.
	 */
	@Test
	public void testGetActivityAt() {
		ActivityList acts = new ActivityList();
		assertTrue(acts.addActivity("Biking", "Biking on a nature trail", false, 5));

		try {
			acts.getActivityAt(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Index is out of bounds for Activties.");
		}

		try {
			acts.getActivityAt(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Index is out of bounds for Activties.");
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#size()}.
	 */
	@Test
	public void testSize() {
		ActivityList acts = new ActivityList();
		assertEquals(acts.size(), 0);

		assertTrue(acts.addActivity("Biking", "Biking on a nature trail", false, 5));
		assertEquals(acts.size(), 1);

		assertTrue(acts.addActivity("Running", "Running on a nature trail", false, 5));
		assertEquals(acts.size(), 2);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		ActivityList acts = new ActivityList();
		assertTrue(acts.isEmpty());

		assertTrue(acts.addActivity("Biking", "Biking on a nature trail", false, 5));
		assertFalse(acts.isEmpty());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#get2DArray()}.
	 */
	@Test
	public void testGet2DArray() {
		ActivityList acts = new ActivityList();

		assertTrue(acts.addActivity("Biking", "Biking on a nature trail", false, 5));

		assertTrue(acts.addActivity("Running", "Running on a nature trail", false, 5));

		Object[][] array = acts.get2DArray();

		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i][0], acts.getActivityAt(i).getActivityID());
			assertEquals(array[i][1], acts.getActivityAt(i).getName());
			assertEquals(array[i][2], acts.getActivityAt(i).getDescription());
			assertEquals(array[i][3], acts.getActivityAt(i).snowNeeded());
			assertEquals(array[i][4], acts.getActivityAt(i).getSnowBoundary());
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#indexOfID(java.lang.String)}.
	 */
	@Test
	public void testIndexOfID() {
		ActivityList acts = new ActivityList();

		assertTrue(acts.addActivity("Biking", "Biking on a nature trail", false, 5));

		assertTrue(acts.addActivity("Running", "Running on a nature trail", false, 5));

		assertEquals(acts.indexOfID(acts.getActivityAt(0).getActivityID()), 0);
		assertEquals(acts.indexOfID(acts.getActivityAt(1).getActivityID()), 1);
		assertEquals(acts.indexOfID("act2"), -1);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.ActivityList#update(java.util.Observable, java.lang.Object)}.
	 * 
	 * @Test public void testUpdate() { // Implement later
	 * 
	 *       }
	 */

}
