package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Tests for Faculty class.
 * 
 * @author kavitpatel
 */
public class FacultyTest {

	Faculty faculty1;
	Faculty faculty2;
	Faculty faculty3;
	Faculty faculty4;

	/**
	 * Setup before each test case.
	 *
	 * @throws Exception
	 *             throws exception if an error is occurred while setting up the
	 *             tests.
	 */
	@Before
	public void setUp() throws Exception {
		// first last id email hash
		faculty1 = new Faculty("Joe", "Bixon", "bjoe", "bjoe@ncsu.edu", "pw", 2);
		faculty2 = new Faculty("Bill", "Shoe", "sbil", "sbil@ncsu.edu", "pw", 2);
		faculty3 = new Faculty("Nancy", "Ellison", "enan", "enan@ncsu.edu", "pw", 2);
		faculty4 = new Faculty("Joe", "Bixon", "bjoe", "bjoe@ncsu.edu", "pw", 2);

		faculty1.setMaxCourses(1);
		faculty2.setMaxCourses(1);
		faculty3.setMaxCourses(1);
	}

	/**
	 * Tests Faculty hashCode().
	 */
	@Test
	public void testHashCode() {
		Faculty faculty11 = faculty1;
		assertEquals(faculty11.hashCode(), faculty1.hashCode());
	}

	/**
	 * Tests Faculty.
	 */
	@Test
	public void testFaculty() {
		assertEquals("Joe", faculty1.getFirstName());
		assertEquals("Bixon", faculty1.getLastName());
		assertEquals("bjoe", faculty1.getId());
		assertEquals("bjoe@ncsu.edu", faculty1.getEmail());
		assertEquals("pw", faculty1.getPassword());
	}

	/**
	 * Tests Faculty setMaxCourses(int).
	 */
	@Test
	public void testSetMaxCourses() {
		faculty1.setMaxCourses(2);
		assertEquals(2, faculty1.getMaxCourses());
		faculty1.setMaxCourses(1);
		faculty1.setMaxCourses(3);
		assertEquals(3, faculty1.getMaxCourses());

		try {
			faculty1.setMaxCourses(-99);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, faculty1.getMaxCourses());
		}
		try {
			faculty1.setMaxCourses(99);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, faculty1.getMaxCourses());
		}
	}

	/**
	 * Tests Faculty equals.
	 */
	@Test
	public void testEqualsFaculty() {
		Faculty faculty22 = faculty2;
		assertTrue(faculty3.equals(faculty3));
		assertTrue(faculty2.equals(faculty22));
		assertTrue(faculty22.equals(faculty2));
		assertFalse(faculty1.equals(faculty2));
		assertFalse(faculty2.equals(faculty1));
		assertFalse(faculty1.equals(faculty4));

	}

	/**
	 * Tests Faculty toString().
	 */
	@Test
	public void testToString() {
		assertEquals("Joe,Bixon,bjoe,bjoe@ncsu.edu,pw,1", faculty1.toString());
	}

}