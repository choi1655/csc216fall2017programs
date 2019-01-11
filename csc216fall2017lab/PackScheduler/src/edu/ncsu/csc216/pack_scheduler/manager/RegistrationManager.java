package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The RegistrationManager class creates a single instance of this object,
 * creates a registrar, and gives login accessibility to users.
 * 
 * @author Jonathan Balliet
 * @author Kamai Guillory
 * @author Anna Chernikov
 */
public class RegistrationManager {
	/** An instance of a registration manager */
	private static RegistrationManager instance;
	/** A new course catalog to be modified by the program */
	private CourseCatalog courseCatalog;
	/** A new student directory to be modified by the program */
	private StudentDirectory studentDirectory;
	/** A new faculty directory to be modified by the program */
	private FacultyDirectory facultyDirectory;
	/** The registrar user to be assigned */
	private User registrar;
	/** Current user logged in */
	private User currentUser;
	/** Tracks if a user is currently logged in or not */
	private boolean loggedIn = false;

	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Properties file where registrar will be created */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Assigns courseCatalog a new CourseCatalog() and studentDirectory a new
	 * StudentDirectory, and calls createRegistrar.
	 */
	private RegistrationManager() {
		createRegistrar();
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		currentUser = null;
		loggedIn = false;
	}

	/**
	 * Creates a new registrar from the properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * hashPW hashes the password for the users of the system.
	 * 
	 * @param pw
	 *            password to be hashed for security reasons.
	 * @return will return the hashed password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Singleton method for a RegistrationManager. Assigns the instance variable a
	 * new Registration Manager object if null, otherwise returns the current
	 * instance.
	 * 
	 * @return instance this instance of the Registration Manager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Returns the course catalog
	 * 
	 * @return courseCatalog - the courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Returns the student directory
	 * 
	 * @return studentDirectory - the student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Returns the faculty directory.
	 * 
	 * @return facultyDirectory the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}

	/**
	 * Login checks the student/registrar for a match in id and password and logs
	 * the correct user in.
	 * 
	 * @param id
	 *            Id used to find the user in the system.
	 * @param password
	 *            password that the user enters
	 * @return true if the user can login, otherwise returns false.
	 */
	public boolean login(String id, String password) {

		// makes sure a user is not already logged in
		if (loggedIn) {
			return false;
		}
		Faculty f = facultyDirectory.getFacultyById(id);
		Student s = studentDirectory.getStudentById(id);
		if (s == null && !registrar.getId().equals(id) && f == null) {
			throw new IllegalArgumentException("User doesn't exist.");
		} else if (s != null) {

			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					loggedIn = true;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {

				throw new IllegalArgumentException("User doesn't exist.");
			}

		} else if (f != null) {

			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (f.getPassword().equals(localHashPW)) {
					currentUser = f;
					loggedIn = true;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {

				throw new IllegalArgumentException("User doesn't exist.");
			}

		} else {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					loggedIn = true;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {

				throw new IllegalArgumentException("User doesn't exist.");
			}

		}

		return false;
	}

	/**
	 * Logs out the current user.
	 */
	public void logout() {
		currentUser = null;
		loggedIn = false;
	}

	/**
	 * Returns the current user
	 * 
	 * @return currentUser the current user of the GUI
	 */
	public User getCurrentUser() {
		if (!loggedIn) {
			currentUser = null;
		}
		return currentUser;
	}

	/**
	 * Clears data by assigning the variables courseCatalog and studentDirectory to
	 * new objects.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}

	/**
	 * Constructs a registrar based on the fields from the User class. Inner private
	 * class.
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password in the
		 * registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c
	 *            Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c
	 *            Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			(c.getCourseRoll()).drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 */
	public void resetSchedule() {
		if (currentUser == null || !(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}

	/**
	 * Adds passed in faculty to the passed in course.
	 * 
	 * @param c
	 *            course to add faculty
	 * @param f
	 *            faculty to be added
	 * @return true if added correctly
	 */
	public boolean addFacultyToCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			// if (c.getInstructorId() != null){
			if (f.getSchedule().addCourseToSchedule(c)) {
				return true;
			}
			// }
		} else if (currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		return false;
	}

	/**
	 * Removes faculty from the course.
	 * 
	 * @param c
	 *            course that faculty is to be removed from
	 * @param f
	 *            faculty to be removed
	 * @return true if removed correctly
	 */
	public boolean removeFacultyFromCourse(Course c, Faculty f) {
		if (currentUser != null && currentUser == registrar) {
			// if(c.getInstructorId().equals(f.getId())){
			if (f.getSchedule().removeCourseFromSchedule(c)) {
				return true;
			}
			// }
		} else if (currentUser != registrar) {
			throw new IllegalArgumentException();
		}
		return false;
	}

	/**
	 * Resets the faculty schedule of the passed in faculty.
	 * 
	 * @param f
	 *            faculty with to-be reseted schedule
	 */
	public void resetFacultySchedule(Faculty f) {
		if (currentUser != null && currentUser == registrar && f.getSchedule() != null) {
			f.getSchedule().resetSchedule();
		} else if (currentUser != registrar) {
			throw new IllegalArgumentException();
		}
	}
}
