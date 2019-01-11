package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Class that loads and saves a catalog of courses. Includes functionality to
 * add and delete courses from the catalog.
 * 
 * @author dtbrown5
 * @author alcherni
 * @author hachaud3
 */
public class CourseCatalog {
	/** List of Courses in the catalog */
	private SortedList<Course> catalog;

	/**
	 * Default constructor for CourseCatalog
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}

	/**
	 * Creates a new catalog
	 */
	public void newCourseCatalog() {
		this.catalog = new SortedList<Course>();
	}

	/**
	 * Reads a list of courses from a file
	 * 
	 * @param fileName
	 *            The name of the file to read from
	 * @throws IllegalArgumentException
	 *             if the filename given is invalid
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Adds a course to the course catalog
	 * 
	 * @param name
	 *            The name of the course
	 * @param title
	 *            The title of the course
	 * @param section
	 *            The course section
	 * @param credits
	 *            The number of credits the course is worth
	 * @param instructorId
	 *            The instructor ID of the course
	 * @param enrollmentCap
	 *            the maximum number of students who can enroll in the course
	 * @param meetingDays
	 *            The days the course meets
	 * @param startTime
	 *            The course's start time
	 * @param endTime
	 *            The course's end time
	 * @return True if the course was added, false if it wasn't
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course newCourse = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays,
				startTime, endTime);

		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (newCourse.compareTo(c) == 0)
				return false;
		}

		return catalog.add(newCourse);
	}

	/**
	 * Removes a course from the catalog
	 * 
	 * @param name
	 *            The name of the course
	 * @param section
	 *            The course section
	 * @return True if the course was removed, false otherwise
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course courseToDelete = new Course(name, "Course to remove", section, 3, "dontmatter", 10, "MTWHF");
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (courseToDelete.compareTo(c) == 0) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a course from the catalog with the specified name and section
	 * 
	 * @param name
	 *            The name of the course to find
	 * @param section
	 *            The section of the course to find
	 * @return The course with the same name and section as specified, null if the
	 *         course was not found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section))
				return c;
		}
		return null;
	}

	/**
	 * Returns a two dimensional String array with a row for each course in the
	 * course catalog and a column for the names, sections, and titles of the
	 * courses.
	 * 
	 * @return a two dimensional String array with a row for each course in the
	 *         course catalog and a column for the names, sections, and titles of
	 *         the courses.
	 */
	public String[][] getCourseCatalog() {
		int columnNumber = 5; // A column for name, section, title, and meeting information
		int rowNumber = catalog.size();

		String[][] courseCatalog = new String[rowNumber][columnNumber];
		for (int i = 0; i < catalog.size(); i++) {
			courseCatalog[i][0] = catalog.get(i).getName();
			courseCatalog[i][1] = catalog.get(i).getSection();
			courseCatalog[i][2] = catalog.get(i).getTitle();
			courseCatalog[i][3] = catalog.get(i).getMeetingString();
			courseCatalog[i][4] = catalog.get(i).getCourseRoll().getOpenSeats() + "";
		}

		return courseCatalog;
	}

	/**
	 * Saves the course catalog to the filename passed in
	 * 
	 * @param fileName
	 *            The name of the file to save it to.
	 * @throws IllegalArgumentException
	 *             If the filename is invalid
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
	}

}
