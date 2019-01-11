package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * POJO that represents a school Course. The course has a course name, a title,
 * a section, number of credits it's worth, an instructor, meeting days, and
 * meeting times.
 * 
 * @author dtbrown5
 */
public class Course extends Activity implements Comparable<Course> {

	CourseNameValidator cnv = new CourseNameValidator();

	CourseRoll roll;

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credits. */
	private int credits;
	/** Course's instructor. */
	private String instructorId;

	/** Minimum length of course name */
	private static final int MIN_NAME_LENGTH = 4;
	/** Maximum length of course name */
	private static final int MAX_NAME_LENGTH = 6;
	/** Maximum number of credits a Course can have */
	private static final int MAX_CREDITS = 5;
	/** Minimum number of credits a Course can have */
	private static final int MIN_CREDITS = 1;
	/** Length the section should have */
	private static final int SECTION_LENGTH = 3;
	/** Character for arranged meeting days */
	private static final char ARRANGED_MEETING_DAY_CHARACTER = 'A';
	/** Array that holds all valid characters for meeting days */
	private static final char[] VALID_MEETING_DAY_CHARACTERS = { 'M', 'T', 'W', 'H', 'F',
			ARRANGED_MEETING_DAY_CHARACTER };

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name
	 *            The name of the Course
	 * @param title
	 *            The title of the Course
	 * @param section
	 *            The section number of the Course
	 * @param credits
	 *            The Course's number of credits
	 * @param instructorId
	 *            The Course's instructor's ID
	 * @param enrollmentCap
	 *            the maximum amount of people who can enroll in a course
	 * @param meetingDays
	 *            The days the Course meets
	 * @param startTime
	 *            The time the Course starts
	 * @param endTime
	 *            The time the Course ends
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Constructs a Course object with all fields except for start and end
	 * times, which it sets to zero.
	 * 
	 * @param name
	 *            The name of the Course
	 * @param title
	 *            The title of the Course
	 * @param section
	 *            The section number of the Course
	 * @param credits
	 *            The Course's number of credits
	 * @param instructorId
	 *            The Course's instructor's ID
	 * @param enrollmentCap
	 *            the maximum number of people who can enroll in a course
	 * @param meetingDays
	 *            The days the Course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null or length is less than 4 or greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name");
		}
		try {
			if (cnv.isValid(name)) {
				this.name = name;
			}
		} catch (InvalidTransitionException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section number. If the number is null or not the valid
	 * section length (3), an IllegalArgumentException is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the number is null or not the valid section length (3), an
	 *             IllegalArgumentException is thrown.
	 * @param section
	 *            the section to set
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH)
			throw new IllegalArgumentException("Invalid section number");
		this.section = section;
	}

	/**
	 * Returns the Course's number of credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. If the credits aren't a number, less than the
	 * minimum number of credits, or greater than the maximum number, an
	 * IllegalArguementException is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the credits aren't a number, less than the minimum number
	 *             of credits, or greater than the maximum number, an
	 *             IllegalArguementException is thrown.
	 * @param credits
	 *            the credits to set
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS)
			throw new IllegalArgumentException("Invalid credits");
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructor's ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor. If the instructor id is null or an empty
	 * string, an IllegalArguementException is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the instructor id is null or an empty string.
	 * @param instructorId
	 *            the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId != null && instructorId.equals(""))
			throw new IllegalArgumentException("Invalid instructor unity id");
		this.instructorId = instructorId;
	}

	/**
	 * Sets the Course's meeting days. If the meeting day letters are null;
	 * empty; contains the arranged meeting day character, but it isn't the only
	 * character; or contains a character other than the valid meeting day
	 * characters, an IllegalArguementException is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the meeting day letters are null; empty; contains the
	 *             arranged meeting day character, but it isn't the only
	 *             character; or contains a character other than the valid
	 *             meeting day characters.
	 * @param meetingDays
	 *            the meetingDays to set
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.equals(""))
			throw new IllegalArgumentException("Invalid meeting days");

		if (meetingDays.indexOf(ARRANGED_MEETING_DAY_CHARACTER) != -1 && meetingDays.length() != 1)
			throw new IllegalArgumentException("Invalid meeting days");

		String validCharacters = new String(VALID_MEETING_DAY_CHARACTERS);
		for (char letter : meetingDays.toCharArray()) {
			if (validCharacters.indexOf(letter) == -1)
				throw new IllegalArgumentException("Invalid meeting days");
		}

		super.setMeetingDays(meetingDays);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return getName() + "," + getTitle() + "," + getSection() + "," + getCredits() + "," + getInstructorId()
					+ "," + roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return getName() + "," + getTitle() + "," + getSection() + "," + getCredits() + "," + getInstructorId() + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Return array with name, section, title, and meeting info
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = { getName(), getSection(), getTitle(), getMeetingString(),
				Integer.toString(roll.getOpenSeats()) };
		return shortArray;
	}

	/**
	 * Return array with name, section, title, credits, instructor id, and
	 * meeting info
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = { getName(), getSection(), getTitle(), Integer.toString(getCredits()), getInstructorId(),
				getMeetingString(), "" };
		return longArray;
	}

	/**
	 * Checks if the given Activity is a Course object, and if it is, returns
	 * true if the Course names are the same. Returns false if the names are
	 * different or the activity is not a Course
	 * 
	 * @param activity
	 *            The activity object to check
	 * @return True if the activity is a Course with the same name, false
	 *         otherwise.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course course = (Course) activity;
			if (this.getName().equals(course.getName()))
				return true;
		}

		return false;
	}

	/**
	 * Compares two Course objects by name and section.
	 * 
	 * @return 0 if the courses are the same, a negative num
	 */
	@Override
	public int compareTo(Course o) {
		if (this.getName().equals(o.getName())) {
			if (this.getSection().equals(o.getSection())) {
				return 0;
			}

			return this.getSection().compareTo(o.getSection());
		}

		return this.getName().compareTo(o.getName());
	}

	/**
	 * returns the courseRoll
	 * 
	 * @return roll CourseRoll corresponding to that course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

}
