/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Test class for TrailList.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class TrailListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#TrailList(edu.ncsu.csc216.get_outdoors.model.Park)}.
	 */
	@Test
	public void testTrailList() {
		Park park = null;
		TrailList tl = null;
		try {
			tl = new TrailList(park);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
			assertNull(tl);
		}

		try {
			park = new Park(null, "ParkName", "ParkDescription");
			tl = new TrailList(park);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
			assertNull(tl);
		}

		try {
			park = new Park("", "ParkName", "ParkDescription");
			tl = new TrailList(park);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
			assertNull(tl);
		}

		try {
			park = new Park(" ", "ParkName", "ParkDescription");
			tl = new TrailList(park);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
			assertNull(tl);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#getParkName()}.
	 */
	@Test
	public void testGetParkName() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		TrailList tl = new TrailList(park);
		assertEquals("ParkName", tl.getParkName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#addTrail(java.lang.String, edu.ncsu.csc216.get_outdoors.util.SortedArrayList, boolean, double, double, edu.ncsu.csc216.get_outdoors.enums.Difficulty)}.
	 */
	@Test
	public void testAddTrail() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		assertTrue(tl.addTrail("TrailName", act, false, 0, 2.1, Difficulty.MODERATE));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#getTrailAt(int)}.
	 */
	@Test
	public void testGetTrailAt() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 1.23, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 3.53, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 3.14, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 7.30, 20, Difficulty.CHALLENGING);
		assertEquals(4, tl.size());
		assertEquals("TrailName3", tl.getTrailAt(2).getTrailName());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#removeTrail(int)}.
	 */
	@Test
	public void testRemoveTrailInt() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 1.23, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 3.53, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 3.14, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 7.30, 20, Difficulty.CHALLENGING);

		assertEquals(tl.removeTrail(0), new Trail("park-3-trail0", "TrailName1", act, false, 1.23, 5, Difficulty.EASY));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#removeTrail(java.lang.String)}.
	 */
	@Test
	public void testRemoveTrailString() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 1.23, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 3.53, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 3.14, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 7.30, 20, Difficulty.CHALLENGING);

		assertEquals(tl.removeTrail("park-3-0"),
				new Trail("park-3-0", "TrailName1", act, false, 1.23, 5, Difficulty.EASY));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#size()}.
	 */
	@Test
	public void testSize() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 1.23, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 3.53, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 3.14, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 7.30, 20, Difficulty.CHALLENGING);
		assertEquals(4, tl.size());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		assertTrue(tl.isEmpty());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 0, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 0, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 0, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 0, 20, Difficulty.CHALLENGING);

		TrailList t2 = new TrailList(park);
		t2.addTrail("TrailName1", act, false, 0, 5, Difficulty.EASY);
		t2.addTrail("TrailName2", act, true, 0, 10, Difficulty.MODERATE);
		t2.addTrail("TrailName3", act, true, 0, 15, Difficulty.EXTREME);
		t2.addTrail("TrailName4", act, false, 0, 20, Difficulty.CHALLENGING);

		assertEquals(t2, tl);
		Park park2 = new Park("park-3", "ParkName", "ParkDescription");
		TrailList t3 = new TrailList(park2);
		TrailList t4 = null;
		String str = new String("Park");

		assertNotEquals(t3, t2);
		assertNotEquals(t2, t4);
		assertNotEquals(t2, str);

		t2 = tl;
		assertEquals(t2, tl);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		Park park2 = new Park("park-3", "ParkName", "ParkDescription");
		TrailList tl = new TrailList(park);
		TrailList t2 = new TrailList(park);
		TrailList t3 = new TrailList(park2);

		assertEquals(tl.hashCode(), t2.hashCode());
		assertNotEquals(tl.hashCode(), t3.hashCode());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#get2DArray()}.
	 */
	@Test
	public void testGet2DArray() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 2);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 2);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 1.23, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 3.53, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 3.14, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 7.30, 20, Difficulty.CHALLENGING);

		Object[][] array = tl.get2DArray();

		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i][0], tl.getTrailAt(i).getTrailID());
			assertEquals(array[i][1], tl.getTrailAt(i).getTrailName());
			assertEquals(array[i][2], tl.getTrailAt(i).closedForMaintenance());
			assertEquals(array[i][3], tl.getTrailAt(i).getSnow());
			assertEquals(array[i][4], tl.getTrailAt(i).getDistance());
			assertEquals(array[i][5], tl.getTrailAt(i).getDifficulty());
			assertEquals(array[i][6], tl.getTrailAt(i).getActivities());

		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#get2DArray(edu.ncsu.csc216.get_outdoors.model.Activity)}.
	 */
	@Test
	public void testGet2DArrayActivity() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 10);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 10);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 0, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 0, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 0, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 0, 20, Difficulty.CHALLENGING);

		TrailList t2 = new TrailList(park);
		t2.addTrail("TrailName1", act, false, 0, 5, Difficulty.EASY);
		t2.addTrail("TrailName4", act, false, 0, 20, Difficulty.CHALLENGING);
		Object[][] array = t2.get2DArray(activity0);
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i][0], t2.getTrailAt(i).getTrailID());
			assertEquals(array[i][1], t2.getTrailAt(i).getTrailName());
			assertEquals(array[i][2], t2.getTrailAt(i).closedForMaintenance());
			assertEquals(array[i][3], t2.getTrailAt(i).getSnow());
			assertEquals(array[i][4], t2.getTrailAt(i).getDistance());
			assertEquals(array[i][5], t2.getTrailAt(i).getDifficulty());
			assertEquals(array[i][6], t2.getTrailAt(i).getActivities());

		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#get2DArrayNoMaintenance()}.
	 */
	@Test
	public void testGet2DArrayNoMaintenance() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 10);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 10);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 0, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 0, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 0, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 0, 20, Difficulty.CHALLENGING);

		TrailList t2 = new TrailList(park);
		t2.addTrail("TrailName1", act, false, 0, 10, Difficulty.MODERATE);
		t2.addTrail("TrailName4", act, false, 0, 15, Difficulty.EXTREME);
		Object[][] array = t2.get2DArrayNoMaintenance();
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i][0], t2.getTrailAt(i).getTrailID());
			assertEquals(array[i][1], t2.getTrailAt(i).getTrailName());
			assertEquals(array[i][2], t2.getTrailAt(i).closedForMaintenance());
			assertEquals(array[i][3], t2.getTrailAt(i).getSnow());
			assertEquals(array[i][4], t2.getTrailAt(i).getDistance());
			assertEquals(array[i][5], t2.getTrailAt(i).getDifficulty());
			assertEquals(array[i][6], t2.getTrailAt(i).getActivities());

		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.TrailList#indexOfID(java.lang.String)}.
	 */
	@Test
	public void testIndexOfID() {
		Park park = new Park("park-3", "ParkName", "ParkDescription");
		SortedArrayList<Activity> act = new SortedArrayList<Activity>();
		Activity activity0 = new Activity("activity-01", "ActivityName1", "ActivityDescription1", false, 10);
		Activity activity1 = new Activity("activity-02", "ActivityName2", "ActivityDescription2", true, 2);
		Activity activity2 = new Activity("activity-03", "ActivityName3", "ActivityDescription3", false, 10);
		act.add(activity0);
		act.add(activity1);
		act.add(activity2);
		TrailList tl = new TrailList(park);
		tl.addTrail("TrailName1", act, false, 0, 5, Difficulty.EASY);
		tl.addTrail("TrailName2", act, true, 0, 10, Difficulty.MODERATE);
		tl.addTrail("TrailName3", act, true, 0, 15, Difficulty.EXTREME);
		tl.addTrail("TrailName4", act, false, 0, 20, Difficulty.CHALLENGING);

		assertEquals(tl.indexOfID("park-3-0"), 0);
		assertEquals(tl.indexOfID("park-3-1"), 1);
		assertEquals(tl.indexOfID("park-3-2"), 2);
		assertEquals(tl.indexOfID("park-3-3"), 3);
		assertEquals(tl.indexOfID("park-3-4"), -1);

	}

}
