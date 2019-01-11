package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * The unit test to test all of the
 * methods inside of the CourseCatalog class
 * 
 * @author hachaud3
 * @author dtbrown5
 * @author alcherni
 *
 */
public class CourseCatalogTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Programming Concepts - Java";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 4;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Course enrollment cap */
	private static final int ENROLL = 250;

	/**
	 * Test the CourseCatalog constructor
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		assertFalse(catalog.removeCourseFromCatalog("CSC216", "002"));
		assertEquals(0, catalog.getCourseCatalog().length);
	}

	/**
	 * Unit test for loading courses from file
	 */
	@Test
	public void testLoadCoursesFromFile() {
		// construct new catalog
		CourseCatalog catalog = new CourseCatalog();
		
		// test nonexistent file
		try {
			catalog.loadCoursesFromFile("test-files/hello.txt");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, catalog.getCourseCatalog().length);
		}
		
		// test invalid file
		catalog.loadCoursesFromFile("test-files/invalid_course_records.txt");
		assertEquals(0, catalog.getCourseCatalog().length);
		
		// load courses from file
		catalog.loadCoursesFromFile(validTestFile);
		assertEquals(8, catalog.getCourseCatalog().length);
	}

	/**
	 * Test adding courses to the catalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		// construct new catalog
		CourseCatalog catalog = new CourseCatalog();
		
		// test valid course
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		String[][] courseCatalog = catalog.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_DAYS + " 1:30PM-2:45PM", courseCatalog[0][3]);
		
		// test same course name and section
		boolean result = catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		assertFalse(result);
		courseCatalog = catalog.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_DAYS + " 1:30PM-2:45PM", courseCatalog[0][3]);
		
		// test invalid course
		try {
			catalog.addCourseToCatalog("LONG NAME", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			courseCatalog = catalog.getCourseCatalog();
			assertEquals(1, courseCatalog.length);
		}
	}

	/**
	 * Test method to remove a specific course from the catalog
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		
		//Removing a course from an empty catalog
		boolean removedCourse = catalog.removeCourseFromCatalog(NAME, SECTION);
		assertFalse(removedCourse);
		assertEquals(0, catalog.getCourseCatalog().length);
		
		//Removing a course that's in the catalog
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(1, catalog.getCourseCatalog().length);
		removedCourse = catalog.removeCourseFromCatalog(NAME, SECTION);
		assertTrue(removedCourse);
		assertEquals(0, catalog.getCourseCatalog().length);
	}

	/**
	 * Test method to get specific course from catalog
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		
		// Searching for a course that doesn't exist
		Course nullCourse = catalog.getCourseFromCatalog(NAME, SECTION);
		assertNull(nullCourse);
		
		// Searching for a course that exists
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		Course sameCourse = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		Course searchedCourse = catalog.getCourseFromCatalog(NAME, SECTION);
		assertEquals(sameCourse, searchedCourse);
	}

	/**
	 * Test method to get the course catalog of
	 * all of the courses currently in it
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		
		String[][] emptyCatalog = catalog.getCourseCatalog();
		assertEquals(0, emptyCatalog.length);
		
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		String[][] actualCatalog = catalog.getCourseCatalog();
		
		assertEquals(NAME, actualCatalog[0][0]);
		assertEquals(SECTION, actualCatalog[0][1]);
		assertEquals(TITLE, actualCatalog[0][2]);
		assertEquals(MEETING_DAYS + " 1:30PM-2:45PM", actualCatalog[0][3]);
		assertEquals(1, actualCatalog.length);
		assertEquals(5, actualCatalog[0].length);
	}

	/**
	 * Test method to save the course catalog to
	 * a text file
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog catalog = new CourseCatalog();
		
		// Saving to a directory that it can't access
		try {
			catalog.saveCourseCatalog("/home/sesmith5/actual_student_records.txt");	
		} catch (IllegalArgumentException e) {
	        assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e.getMessage());
		}
		
		// Saving to a valid file
		catalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLL, MEETING_DAYS, START_TIME, END_TIME);
		catalog.saveCourseCatalog("test-files/test_writingCourseRecords_actual");
		checkFiles("test-files/test_writingCourseRecords_expected", "test-files/test_writingCourseRecords_actual");
		
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
