/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for Activity.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class ActivityTest {


	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#Activity(java.lang.String, java.lang.String, java.lang.String, boolean, int)}.
	 */
	@Test
	public void testActivity() {
		Activity act;
		// Tests adding valid Activity
		act = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		assertEquals(act.getActivityID(), "Act0");
		assertEquals(act.getName(), "Biking");
		assertEquals(act.getDescription(), "Biking!!!");
		assertEquals(act.getSnowBoundary(), 5);
		assertEquals(act.hasChanged(), false);

		// Tests invalid id's
		// Tests null id
		try {
			act = new Activity(null, "Biking", "Biking!!!", false, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// Tests empty id
		try {
			act = new Activity("", "Biking", "Biking!!!", false, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// Tests id made of whitespace
		try {
			act = new Activity("      ", "Biking", "Biking!!!", false, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// Tests invalid name
		// Tests null name
		try {
			act = new Activity("Act0", null, "Biking!!!", false, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// Tests empty name
		try {
			act = new Activity("Act0", "", "Biking!!!", false, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		// Tests name made of whitespace
		try {
			act = new Activity("Act0", "       ", "Biking!!!", false, 5);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		Activity act = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		assertEquals(act.getDescription(), "Biking!!!");

		act.setDescription("Biking on the nature trail!");
		assertEquals(act.getDescription(), "Biking on the nature trail!");

		try {
			act.setDescription(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			act.setDescription("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			act.setDescription("      ");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#setNeedSnow(boolean)}.
	 */
	@Test
	public void testSetNeedSnow() {
		Activity act = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		assertEquals(act.snowNeeded(), false);

		act.setNeedSnow(true);
		assertEquals(act.snowNeeded(), true);

		act.setNeedSnow(false);
		assertEquals(act.snowNeeded(), false);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#setSnowBoundary(int)}.
	 */
	@Test
	public void testSetSnowBoundary() {
		Activity act = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		assertEquals(act.getSnowBoundary(), 5);

		act.setSnowBoundary(13);
		assertEquals(act.getSnowBoundary(), 13);

		act.setSnowBoundary(0);
		assertEquals(act.getSnowBoundary(), 0);

		try {

			act.setSnowBoundary(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#toString()}.
	 */
	@Test
	public void testToString() {
		Activity act = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		
		String actString = "Biking\tBiking!!!\tfalse\t5";
		
		assertEquals(actString, act.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Activity act1 = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		Activity act2 = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		Activity act3 = new Activity("Act0", "Running", "Biking!!!", false, 5);
		Activity act4 = null;
		String str = new String("Running");
	
		assertEquals(act1, act2);
		assertNotEquals(act1, act3);
		act1 = act3;
		assertEquals(act1, act3);
		assertNotEquals(act1, act4);
		assertNotEquals(act3, str);
		
		
	}
	
	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		Activity act1 = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		Activity act2 = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		Activity act3 = new Activity("Act0", "Running", "Biking!!!", false, 5);
		
		assertEquals(act1.hashCode(), act2.hashCode());
		assertNotEquals(act2.hashCode(), act3.hashCode());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.get_outdoors.model.Activity#compareTo(edu.ncsu.csc216.get_outdoors.model.Activity)}.
	 */
	@Test
	public void testCompareTo() {
		Activity act1 = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		Activity act2 = new Activity("Act0", "Biking", "Biking!!!", false, 5);
		Activity act3 = new Activity("Act0", "Running", "Biking!!!", false, 5);
		
		assertTrue(act1.compareTo(act2) == 0);
		assertTrue(act1.compareTo(act3) < 0);
		assertTrue(act3.compareTo(act2) > 0 );
	}

}
