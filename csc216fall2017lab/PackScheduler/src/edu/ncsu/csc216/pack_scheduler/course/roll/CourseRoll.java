package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Creates a courseroll object to hold the students that the course.
 * 
 * @author Kamai A. M. Guillory
 *
 */
public class CourseRoll {

	/** Minimum number of people that can enroll in a course */
	private static final int MIN_ENROLLMENT = 10;
	/** Maximum number of people that can enroll in a course */
	private static final int MAX_ENROLLMENT = 250;
	/** List of students enrolled in a course */
	private LinkedAbstractList<Student> roll;
	/** how many students can enroll in that particular course */
	private int enrollmentCap;
	/** The waiting list of students for this course */
	private LinkedQueue<Student> waitlist;
	/** The course that this course roll refers to */
	private Course course;

	/**
	 * Constructs a CourseRoll
	 * 
	 * @param enrollmentCap
	 *            the number of students that can be on the CourseRoll
	 * @param c
	 *            the actual course of this course roll
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if (c == null) {
			throw new IllegalArgumentException();
		}

		roll = new LinkedAbstractList<Student>(enrollmentCap);
		setEnrollmentCap(enrollmentCap);
		waitlist = new LinkedQueue<Student>(10);
		course = c;
	}

	/**
	 * returns the number of open seats in the CourseRoll
	 * 
	 * @return openSeats how many open seats there are
	 */
	public int getOpenSeats() {
		int openSeats = enrollmentCap - roll.size();
		return openSeats;
	}

	/**
	 * enrolls a student in the course
	 * 
	 * @param s
	 *            Student to be enrolled
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException("Casper can't enroll");
		}

		if (!canEnroll(s)) {

			throw new IllegalArgumentException("Person already exists");
		}
		if (enrollmentCap == roll.size()) {
			try {
				waitlist.enqueue(s);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}

		if (getOpenSeats() > 0) {
			roll.add(roll.size(), s);

		}

	}

	/**
	 * can enroll returns a boolean value identifying if a student can be
	 * enrolled in a course
	 * 
	 * @param s
	 *            Student who may be enrolled
	 * @return boolean if there is room for the student
	 */
	public boolean canEnroll(Student s) {

		for (int i = 0; i < roll.size(); i++) {
			if (roll.get(i).equals(s)) {
				return false;
			}
		}

		if (waitlist.contains(s)) {
			return false;
		}

		return true;

	}

	/**
	 * returns the maximum number of people who can enroll in that course
	 * 
	 * @return enrollmentCap the number of people enrolled
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * sets the EnrollmentCap
	 * 
	 * @param enrollmentCap
	 *            number of people who can enroll in a course
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("It's too small or too big, you need Jussssst Right.");
		}
		if (roll.size() > enrollmentCap) {
			throw new IllegalArgumentException("You need to have the write amount of students");
		}
		this.enrollmentCap = enrollmentCap;
		roll.setCapacity(enrollmentCap);

	}

	/**
	 * removes a student from a course roll
	 * 
	 * @param s
	 *            Student to be removed
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		LinkedQueue<Student> temp = waitlist;
		boolean studentExists = false; // true if student exists in waitlist
		Student s1;
		for (int i = 0; i < waitlist.size(); i++) {
			s1 = waitlist.dequeue();
			if (s1 != s) {
				temp.enqueue(s1);
				studentExists = false;
			} else {
				studentExists = true;
			}
		}
		waitlist = temp;

		boolean studentExistsRoll = false;
		if (!studentExists) {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i) == s) {
					roll.remove(s);
					studentExistsRoll = true;
					break;
				} else {
					studentExistsRoll = false;
				}
			}
		}
		if (studentExistsRoll && waitlist.size() > 0 && roll.size() < MAX_ENROLLMENT) {
			Student student = waitlist.dequeue();
			roll.add(roll.size(), student);
			student.getSchedule().addCourseToSchedule(course);

		}
	}

	/**
	 * The number of student's on the course's waiting list.
	 * 
	 * @return the number of student's on the waiting list
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}

	/**
	 * Returns the list of enrolled Students.
	 * 
	 * @return list of scheduled Students
	 */
	public String[][] getEnrolledStudents() {
		String[][] scheduleArray = new String[roll.size()][3];
		for (int i = 0; i < roll.size(); i++) {
			scheduleArray[i] = ((Student) roll.get(i)).getShortDisplayArray();
		}
		return scheduleArray;
	}

}
