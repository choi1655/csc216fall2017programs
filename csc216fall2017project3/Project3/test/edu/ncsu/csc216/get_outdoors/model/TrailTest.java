/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Test class for Trail.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class TrailTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#Trail(java.lang.String, java.lang.String, edu.ncsu.csc216.get_outdoors.util.SortedArrayList, boolean, double, double, edu.ncsu.csc216.get_outdoors.enums.Difficulty)}.
	 */
	@Test
	public void testTrail() {

		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Running", "Running on a nature trail", false, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		assertEquals(trail.getTrailID(), "trail0");
		assertEquals(trail.getTrailName(), "HawkEye Trail");
		assertEquals(trail.getActivities(), activities);
		assertEquals(trail.closedForMaintenance(), false);
		assertEquals(trail.getSnow(), 0.0, 0.5);
		assertEquals(trail.getDistance(), 150, 0.5);
		assertEquals(trail.getDifficulty(), Difficulty.EASY);

		// invalid id
		try {
			trail = new Trail(null, "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			trail = new Trail("", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			trail = new Trail("    ", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// Invalid name
		try {
			trail = new Trail("trail0", null, activities, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			trail = new Trail("trail0", "", activities, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			trail = new Trail("trail0", "    ", activities, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// invalid activities
		try {
			trail = new Trail("trail0", "HawkEye Trail", null, false, 0, 150, Difficulty.EASY);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#setActivities(edu.ncsu.csc216.get_outdoors.util.SortedArrayList)}.
	 */
	@Test
	public void testSetActivities() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Running", "Running on a nature trail", false, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		assertEquals(trail.getActivities(), activities);

		// invalid activities
		try {
			trail.setActivities(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#setTrailMaintenance(boolean)}.
	 */
	@Test
	public void testSetTrailMaintenance() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Running", "Running on a nature trail", false, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		assertEquals(trail.closedForMaintenance(), false);

		trail.setTrailMaintenance(true);
		assertEquals(trail.closedForMaintenance(), true);

		trail.setTrailMaintenance(false);
		assertEquals(trail.closedForMaintenance(), false);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#setSnow(double)}.
	 */
	@Test
	public void testSetSnow() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Running", "Running on a nature trail", false, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);

		trail.setSnow(-5);
		assertEquals(trail.getSnow(), 0, 0);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#addSnow(double)}.
	 */
	@Test
	public void testAddSnow() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Running", "Running on a nature trail", false, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);

		assertEquals(0, trail.addSnow(0), 0);

		assertEquals(trail.addSnow(-5), 0, 0);

		assertEquals(trail.addSnow(50), 50, 4);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#setDistance(double)}.
	 */
	@Test
	public void testSetDistance() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Running", "Running on a nature trail", false, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		assertEquals(trail.getDistance(), 150, 0);

		try {
			trail.setDistance(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Distance can't be negative");
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#trailOpen(edu.ncsu.csc216.get_outdoors.model.Activity)}.
	 */
	@Test
	public void testTrailOpen() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Skiing", "Skiing on a nature trail", true, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		assertTrue(trail.trailOpen(trail.getActivities().get(0)));

		assertFalse(trail.trailOpen(trail.getActivities().get(1)));

		trail.addSnow(5.1);
		assertFalse(trail.trailOpen(trail.getActivities().get(0)));
		assertTrue(trail.trailOpen(trail.getActivities().get(1)));

		trail.addSnow(-0.2);
		assertTrue(trail.trailOpen(trail.getActivities().get(0)));
		assertFalse(trail.trailOpen(trail.getActivities().get(1)));

		trail.setTrailMaintenance(true);
		assertFalse(trail.trailOpen(trail.getActivities().get(0)));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#allow(edu.ncsu.csc216.get_outdoors.model.Activity)}.
	 */
	@Test
	public void testAllow() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Skiing", "Skiing on a nature trail", true, 5));

		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);

		assertTrue(trail.allow(new Activity("act0", "Biking", "Biking on a nature trail", false, 5)));
		assertFalse(trail.allow(new Activity("act3", "Running", "Biking on a nature trail", false, 5)));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Skiing", "Skiing on a nature trail", true, 5));
		Trail trail1 = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		Trail trail2 = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		Trail trail3 = new Trail("trail0", "Aurora Falls", activities, false, 0, 150, Difficulty.EASY);
		Trail trail4 = null;
		String str = new String("Aurora Falls");

		assertEquals(trail1, trail2);
		assertNotEquals(trail1, trail3);

		assertNotEquals(trail1, trail4);

		trail1 = trail2;
		assertEquals(trail1, trail2);

		assertNotEquals(trail3, str);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#toString()}.
	 */
	@Test
	public void testToString() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Skiing", "Skiing on a nature trail", true, 5));
		Trail trail = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);

		String actString = "";
		for (int i = 0; i < trail.getActivities().size(); i++) {
			if (i == trail.getActivities().size() - 1) {
				actString += trail.getActivities().get(i).getName();
			} else {
				actString += trail.getActivities().get(i).getName() + "\t";
			}
		}

		String trailStr = trail.getTrailName() + "\t" + trail.closedForMaintenance() + "\t" + trail.getSnow() + "\t"
				+ trail.getDistance() + "\t" + trail.getDifficulty() + "\t" + actString;

		assertEquals(trailStr, trail.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#compareTo(edu.ncsu.csc216.get_outdoors.model.Trail)}.
	 */
	@Test
	public void testCompareTo() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Skiing", "Skiing on a nature trail", true, 5));
		Trail trail1 = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		Trail trail2 = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		Trail trail3 = new Trail("trail0", "Aurora Falls", activities, false, 0, 150, Difficulty.EASY);

		assertTrue(trail1.compareTo(trail2) == 0);
		assertTrue(trail3.compareTo(trail1) < 0);
		assertTrue(trail2.compareTo(trail3) > 0);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Trail#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		SortedArrayList<Activity> activities = new SortedArrayList<Activity>();
		activities.add(new Activity("act0", "Biking", "Biking on a nature trail", false, 5));
		activities.add(new Activity("act1", "Skiing", "Skiing on a nature trail", true, 5));
		Trail trail1 = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		Trail trail2 = new Trail("trail0", "HawkEye Trail", activities, false, 0, 150, Difficulty.EASY);
		Trail trail3 = new Trail("trail0", "Aurora Falls", activities, false, 0, 150, Difficulty.EASY);

		assertEquals(trail1.hashCode(), trail2.hashCode());
		assertNotEquals(trail1.hashCode(), trail3.hashCode());
	}
}
