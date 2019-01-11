package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The Student class is a POJO that represents an individual student record
 * 
 * @author Dominic Brown
 * @author Hamzah Chaudhry
 * @author Anna Chernikov
 */

public class Student extends User implements Comparable<Student> {

	/** Max credits a student can take */
	public static final int MAX_CREDITS = 18;
	/** minimum credits a student can take */
	public static final int MIN_CREDITS = 3;
	/** Student's max credits */
	private int maxCredits;
	/** Students's schedule */
	private Schedule mySchedule;

	/**
	 * Constructs a Student object using all of it's parameter fields.
	 * 
	 * @param firstName
	 *            Student first name
	 * @param lastName
	 *            Student last name
	 * @param id
	 *            Student unity id
	 * @param email
	 *            Student email address
	 * @param password
	 *            Student hashed password
	 * @param maxCredits
	 *            Student's max credits
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		mySchedule = new Schedule();
	}

	/**
	 * Constructs a Student object without a parameter field for maxCredits.
	 * 
	 * @param firstName
	 *            Student first name
	 * @param lastName
	 *            Student last name
	 * @param id
	 *            Student unity id
	 * @param email
	 *            Student email address
	 * @param password
	 *            Student hashed password
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
		mySchedule = new Schedule();
	}

	/**
	 * Returns the maximum number of credits a student has
	 * 
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets student's max credits
	 * 
	 * @param maxCredits
	 *            the maxCredits to set
	 * @throws IllegalArgumentException
	 *             for credits less than 3 or greater than 18
	 */
	public void setMaxCredits(int maxCredits) throws IllegalArgumentException {

		if (maxCredits < MIN_CREDITS || maxCredits > MAX_CREDITS)
			throw new IllegalArgumentException("Invalid max credits");

		this.maxCredits = maxCredits;
	}

	/**
	 * Convert Student object to a String using all of its field values
	 * 
	 * @see java.lang.Object#toString()
	 * @return String representation of Student object
	 */
	@Override
	public String toString() {
		return super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + ","
				+ super.getPassword() + "," + maxCredits;
	}

	/**
	 * Generates a hashCode for the Student using all of its fields.
	 * 
	 * @return int value of the hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Overrides the equals method. All Student objects that have the same field
	 * values are considered equal.
	 * 
	 * @return boolean value of whether the two Students are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Compares two Students objects by last name, first name, and if.
	 * 
	 * @return 0 if the Students are the same, a negative number if the Student
	 *         comes before, and 1 if it comes after the passed in Student
	 */
	@Override
	public int compareTo(Student student) {
		if (student.getLastName().equals(this.getLastName())) {
			if (student.getFirstName().equals(this.getFirstName())) {
				if (student.getId().equals(this.getId())) {
					return 0;
				} else {
					return this.getId().compareTo(student.getId());
				}
			} else {
				return this.getFirstName().compareTo(student.getFirstName());
			}
		} else {
			return this.getLastName().compareTo(student.getLastName());
		}

	}

	/**
	 * Gets the schedule of a particular schedule
	 * 
	 * @return the schedule of the Student
	 */
	public Schedule getSchedule() {
		return mySchedule;
	}

	/**
	 * determines if a course can be added
	 * 
	 * @param c
	 *            Course that may be added
	 * @return boolean if a course can be added
	 */
	public boolean canAdd(Course c) {
		if (c == null) {
			return false;
		}
		if (this.getSchedule().getScheduleCredits() + c.getCredits() > this.getMaxCredits()) {
			return false;
		}
		if (!this.getSchedule().canAdd(c)) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Return array with first name, last name, and Student Id info.
	 * @return shortArray, an array. 
	 */
	public String[] getShortDisplayArray() {
		String[] shortArray = { getFirstName(), getLastName(), getId() };
		return shortArray;
	}
}