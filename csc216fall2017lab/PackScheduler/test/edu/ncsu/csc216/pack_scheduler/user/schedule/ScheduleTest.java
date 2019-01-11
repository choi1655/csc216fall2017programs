package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Tests that the schedule class functions properly
 * 
 * @author Kamai A. M. Guillory
 * @author Jonathan Balliet
 * @author Anna Chernikov
 */
public class ScheduleTest {
	/** Schedule used for testing */
	Schedule testSchedule = new Schedule();

	/**
	 * Tests that the title is set properly
	 */
	@Test
	public void testTitle() {
		testSchedule.setTitle("Fall Semester");
		assertEquals("Fall Semester", testSchedule.getTitle());
	}

	/**
	 * Tests courses can be added to the schedule.
	 */
	@Test
	public void testAdd() {
		testSchedule.resetSchedule();
		Course c1 = new Course("CSC230", "Computer Programing", "001", 3, "jsam123", 10, "A");
		Course c2 = new Course("CSC280", "Hard Computer Programing", "001", 4, "jsam123", 10, "A");
		Course c3 = new Course("CSC300", "Hardest Computer Programing", "001", 4, "jsam123", 10, "A");

		testSchedule.addCourseToSchedule(c1);
		testSchedule.addCourseToSchedule(c2);
		testSchedule.addCourseToSchedule(c3);
		assertEquals(c1.getName(), testSchedule.getScheduledCourses()[0][0]);
		assertEquals(c2.getName(), testSchedule.getScheduledCourses()[1][0]);
		assertEquals(c3.getName(), testSchedule.getScheduledCourses()[2][0]);
	}

	/**
	 * Tests courses can be removed from the schedule.
	 */
	@Test
	public void testRemove() {
		testSchedule.resetSchedule();
		ArrayList<Course> courses = new ArrayList<>();
		Course c1 = new Course("CSC230", "Computer Programing", "001", 3, "jsam123", 10, "A");
		Course c2 = new Course("CSC280", "Hard Computer Programing", "001", 4, "jsam123", 10, "A");
		Course c3 = new Course("CSC300", "Hardest Computer Programing", "001", 4, "jsam123", 10, "A");
		courses.add(c1);
		courses.add(c2);
		courses.add(c3);

		testSchedule.addCourseToSchedule(c1);
		testSchedule.addCourseToSchedule(c2);

		assertTrue(testSchedule.removeCourseFromSchedule(c1));
		assertFalse(testSchedule.removeCourseFromSchedule(c3));
	}
	

}
