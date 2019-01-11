package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * Class that defines Faculty which is a type of User.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @author krpatel7@ncsu.edu
 */
public class Faculty extends User {

	/** Maximum Courses faculty can teach. */
	private int maxCourses;
	/** Minimum Courses faculty can teach. */
	public static final int MIN_COURSES = 1;
	/** Maximum Courses faculty can teach. */
	public static final int MAX_COURSES = 3;

	/** Schedule of the faculty. */
	public FacultySchedule schedule;

	/**
	 * Constructs Faculty object.
	 * 
	 * @param firstName
	 *            Faculty's first name
	 * @param lastName
	 *            Faculty's last name
	 * @param id
	 *            Faculty's id
	 * @param email
	 *            Faculty's email
	 * @param hashPw
	 *            Faculty's pw
	 * @param maxCourse
	 *            Faculty's maximum course
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPw, int maxCourse) {
		super(firstName, lastName, id, email, hashPw);
		setMaxCourses(maxCourse);
		schedule = new FacultySchedule(id);
	}

	/**
	 * Sets max courses for faculty.
	 * 
	 * @param courses
	 *            to set
	 */
	public void setMaxCourses(int courses) {
		if (courses < 1 || courses > 3) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = courses;
	}

	/**
	 * Gets the max courses for faculty.
	 * 
	 * @return faculty's max courses
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Returns string form of faculty object.
	 * 
	 * @return String getters in format
	 */
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ maxCourses;
	}

	/**
	 * Gets hashcode identifier for unique faculty.
	 * 
	 * @return int returns hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Checks if two faculty objects are the same.
	 * 
	 * @param obj
	 *            to check
	 * @return true if same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	/**
	 * Gets faculty's schedule.
	 * 
	 * @return FacultySchedule of faculty member.
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Returns true if faculty is overloaded.
	 * 
	 * @return maxCourses if equal or greater than MAX_COURSES.
	 */
	public boolean isOverloaded() {
		return schedule.getNumScheduledCourses() > maxCourses;

	}

}
