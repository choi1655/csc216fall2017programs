package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * tests the CourseRoll class
 * 
 * @author alcherni
 * @author jballie
 * @author kaguillo
 */
public class CourseRollTest {

	/**
	 * Tests getOpenSeats.
	 */
	@Test
	public void testGetOpenSeats() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "A");

		CourseRoll roll = c.getCourseRoll();
		assertEquals(11, roll.getOpenSeats());
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);
		roll.enroll((Student) student1);
		assertEquals(10, roll.getOpenSeats());

	}

	/**
	 * Tests getEnroll.
	 */
	@Test
	public void testEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "A");
		CourseRoll cr = c.getCourseRoll();
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);
		cr.enroll((Student) student1);

		try {
			cr.enroll(null);
		} catch (IllegalArgumentException e) {
			assertEquals("Casper can't enroll", e.getMessage());
		}
	}

	/**
	 * Tests canEnroll.
	 */
	@Test
	public void testCanEnroll() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "A");
		CourseRoll cr = c.getCourseRoll();
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);
		User student2 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);

		cr.enroll((Student) student1);
		assertFalse(cr.canEnroll((Student) student1));
		assertFalse(cr.canEnroll((Student) student2));
	}

	/**
	 * Tests getEnrollmentCap.
	 */
	@Test
	public void testGetEnrollmentCap() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "A");
		CourseRoll cr = c.getCourseRoll();
		assertEquals(11, cr.getEnrollmentCap());
	}

	/**
	 * Tests dropping a student from a given course.
	 */
	@Test
	public void testDrop() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 11, "A");
		CourseRoll cr = c.getCourseRoll();
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);
		User student3 = new Student("Himmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student4 = new Student("Kimmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student5 = new Student("Zimmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student6 = new Student("Ximmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);

		cr.enroll((Student) student1);
		cr.enroll((Student) student3);
		cr.enroll((Student) student4);
		cr.enroll((Student) student5);
		cr.enroll((Student) student6);
		assertEquals(cr.getOpenSeats(), 6);
		cr.drop((Student) student1);

		assertEquals(cr.getOpenSeats(), 7);

		cr.drop((Student) student3);
		cr.drop((Student) student4);
		assertEquals(cr.getOpenSeats(), 9);

		try {
			cr.drop(null);
		} catch (IllegalArgumentException e) {
			assertEquals(null, e.getMessage());
		}

	}

	/**
	 * Tests getNumberOnWaitlist().
	 */
	@Test
	public void testGetNumberOnWaitlist() {
		Course c = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "A");
		CourseRoll cr = c.getCourseRoll();
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);
		User student3 = new Student("Himmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student4 = new Student("Kimmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student5 = new Student("Zimmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student6 = new Student("Ximmy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);

		User student7 = new Student("Billy", "Stevens", "tsteven5", "tsteven5@ncsu.edu", "ilovejava", 18);
		User student8 = new Student("John", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student9 = new Student("Tommy", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student10 = new Student("Claire", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student2 = new Student("Bob", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);
		User student11 = new Student("Bobby", "Stevens", "hsteven8", "hsteven5@ncsu.edu", "ilovejava", 18);

		cr.enroll((Student) student1);
		cr.enroll((Student) student3);
		cr.enroll((Student) student4);
		cr.enroll((Student) student5);
		cr.enroll((Student) student6);
		cr.enroll((Student) student7);
		cr.enroll((Student) student8);
		cr.enroll((Student) student9);
		cr.enroll((Student) student10);
		cr.enroll((Student) student2);
		cr.enroll((Student) student11);

		assertEquals(cr.getNumberOnWaitlist(), 1);
		cr.drop((Student) student1);
		assertEquals(cr.getNumberOnWaitlist(), 0);

		cr.drop((Student) student3);
		cr.drop((Student) student4);
	}

}
