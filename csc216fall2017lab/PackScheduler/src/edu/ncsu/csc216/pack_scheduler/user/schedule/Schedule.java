package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Represents a Schedule that a student can add courses to, remove courses from,
 * and retrieve all of their scheduled courses information.
 * 
 * @author alcherni
 * @author jballie
 * @author kaguillo
 */
public class Schedule {
	/** An ArrayList containing the Student's courses */
	private ArrayList<Course> courses;
	/** The title of the Schedule */
	private String title;

	/**
	 * Constructor for a Schedule. Initializes the ArrayList of Course objects. Sets
	 * the default title of the schedule to 'My Schedule'.
	 */
	public Schedule() {
		courses = new ArrayList<Course>();
		title = "My Schedule";

	}

	/**
	 * Sets the title for the scheulde based on the given String title. Throws an
	 * exception if the title passed in is null
	 * 
	 * @param title
	 *            title of the schedule
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;

	}

	/**
	 * Returns the title of the Schedule
	 * 
	 * @return title the Shedule's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Adds a Course to the Schedule based on the passed in course. Throws an
	 * exception of the Student is already enrolled in the course or there is a
	 * conflict with another course in their schedule.
	 * 
	 * @param c
	 *            the Course to be added.
	 * @return boolean value of whether the Course was successfully added to the
	 *         Schedule or not.
	 */
	public boolean addCourseToSchedule(Course c) {

		for (int i = 0; i < courses.size(); i++) {
			if (c.isDuplicate(courses.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + c.getName());
			}

			try {
				c.checkConflict(courses.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}

		try {

			courses.add(c);

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Removes the Course from the Schedule based on the passed in Course value. If
	 * the Course is successfully removed, returns true. Otherwise, returns false.
	 * 
	 * @param c
	 *            Course to be removed
	 * @return boolean value of whether the Course was successfully added to the
	 *         Schedule or not.
	 */
	public boolean removeCourseFromSchedule(Course c) {
		if (c == null || courses.isEmpty()) {
			return false;
		}
		for (int i = 0; i < courses.size(); i++) {
			if (c.equals(courses.get(i))) {
				courses.remove(i);
				return true;
			}
		}

		return false;

	}

	/**
	 * Resets the Schedule of courses by assigning it to a new ArrayList.
	 */
	public void resetSchedule() {
		courses = new ArrayList<Course>();
	}

	/**
	 * Gets a two-dimensional String Array of the scheduled Courses. This array
	 * contains the name, section, title, and meeting information in each column of
	 * the array, with the row being the specific Course.
	 * 
	 * @return schedule two-dimensional array of the scheduled course information
	 */
	public String[][] getScheduledCourses() {
		int columnNumber = 5; // A column for name, section, title, and meeting
								// information
		int rowNumber = courses.size();

		String[][] schedule = new String[rowNumber][columnNumber];
		for (int i = 0; i < courses.size(); i++) {
			schedule[i][0] = courses.get(i).getName();
			schedule[i][1] = courses.get(i).getSection();
			schedule[i][2] = courses.get(i).getTitle();
			schedule[i][3] = courses.get(i).getMeetingString();
			schedule[i][4] = courses.get(i).getCourseRoll().getOpenSeats() + "";
		}

		return schedule;

	}

	/**
	 * returns the total credits in a schedule
	 * 
	 * @return totalCredits total credits in a schedule
	 */
	public int getScheduleCredits() {
		int totalCredits = 0;
		for (int i = 0; i < courses.size(); i++) {
			totalCredits += courses.get(i).getCredits();
		}

		return totalCredits;
	}

	/**
	 * determines if a course can be added to a schedule without conflict and
	 * duplicate
	 * 
	 * @param c
	 *            the Course to be added
	 * @return boolean if a course can be added
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}

		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).isDuplicate(c)) {
				return false;
			}
			try {
				c.checkConflict(courses.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}

}
