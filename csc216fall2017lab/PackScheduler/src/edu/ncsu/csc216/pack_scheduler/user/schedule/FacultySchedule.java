package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.LinkedListRecursive;

/**
 * A Faculty's schedule
 * 
 * @author Sarah Heckman
 */
public class FacultySchedule {

	/** Schedule of courses with no cap */
	private LinkedListRecursive<Object> schedule;
	/** Instructor id for updating courses */
	private String instructorId;

	/**
	 * Creates an empty schedule.
	 * 
	 * @param instructorId
	 *            faculty's id for updating Course
	 */
	public FacultySchedule(String instructorId) {
		schedule = new LinkedListRecursive<Object>();
		this.instructorId = instructorId;
	}

	/**
	 * Adds a course to the schedule.
	 * 
	 * @param course
	 *            Course to add to schedule
	 * @return true if added
	 */
	public boolean addCourseToSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (((Activity) schedule.get(i)).isDuplicate(course)) {
				throw new IllegalArgumentException("Already assigned " + course.getName());
			}
			try {
				((Activity) schedule.get(i)).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be assigned due to a conflict.");
			}
		}
		if (course.getInstructorId() != null) {
			throw new IllegalArgumentException("The course already has an instructor.");
		}
		if (schedule.add(course)) {
			course.setInstructorId(instructorId);
			return true;
		}
		return false;
	}

	/**
	 * Removes a course from the schedule.
	 * 
	 * @param object
	 *            Course to remove from the schedule
	 * @return true if added
	 */
	public boolean removeCourseFromSchedule(Object object) {
		if (schedule.remove(object)) {
			((Course) object).setInstructorId(null);
			return true;
		}
		return false;
	}

	/**
	 * Resets the schedule to an empty schedule
	 */
	public void resetSchedule() {
		int startingSize = schedule.size();
		for (int i = 0; i < startingSize; i++) {
			removeCourseFromSchedule(schedule.get(0)); // also removes from
														// Course
		}
	}

	/**
	 * Returns the list of scheduled Courses.
	 * 
	 * @return list of scheduled Courses
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleArray = new String[schedule.size()][4];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i] = ((Course) schedule.get(i)).getShortDisplayArray();
		}
		return scheduleArray;
	}

	/**
	 * Returns the number of courses the faculty is scheduled to teach.
	 * 
	 * @return num courses
	 */
	public int getNumScheduledCourses() {
		return schedule.size();
	}

	/**
	 * Returns a course from the schedule with the specified name and section
	 * 
	 * @param name
	 *            The name of the course to find
	 * @param section
	 *            The section of the course to find
	 * @return The course with the same name and section as specified, null if
	 *         the course was not found
	 */
	public Course getCourseFromSchedule(String name, String section) {
		for (int i = 0; i < schedule.size(); i++) {
			Course c = (Course) schedule.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section))
				return c;
		}
		return null;
	}

}