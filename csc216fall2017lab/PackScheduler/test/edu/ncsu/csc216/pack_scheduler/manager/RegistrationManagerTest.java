package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A JUnit test RegistrationManagerTest, tests the RegistrationManager methods
 * for expected output
 * 
 * @author Jonathan Balliet
 * @author Kamai Guillory
 * @author Anna Chernikov
 */
public class RegistrationManagerTest {
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
	/** The main registartionManager object. */
	private RegistrationManager manager;

	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 15;

	/**
	 * Sets up the RegistrationeManager and clears the data.
	 * 
	 * @throws Exception
	 *             throws an exception if encounters an error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();
	}

	/**
	 * Valid test to make sure course is properly added to course catalog.
	 */
	@Test
	public void testGetCourseCatalog() {
		RegistrationManager testManager = RegistrationManager.getInstance();
		CourseCatalog testCatalog = testManager.getCourseCatalog();
		testCatalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 200, MEETING_DAYS, START_TIME,
				END_TIME);
		assertEquals(testManager.getCourseCatalog().getCourseCatalog().length, 1);

		testCatalog.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 200, MEETING_DAYS, START_TIME,
				END_TIME);
		assertEquals(testManager.getCourseCatalog().getCourseCatalog().length, 1);

	}

	/**
	 * Tests that Registration Manager can create a new instance create a
	 * StudentDirectory and call methods to add students to it.
	 */
	@Test
	public void testGetStudentDirectory() {
		RegistrationManager testManager = RegistrationManager.getInstance();
		StudentDirectory testDirectory = testManager.getStudentDirectory();

		testDirectory.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertEquals(testManager.getStudentDirectory().getStudentDirectory().length, 1);

		testDirectory.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		assertEquals(testManager.getStudentDirectory().getStudentDirectory().length, 1);
	}
	
	/**
	 * Tests that Registration Manager can create a new instance create a 
	 * FacultyDirectory and call methods to add faculty to it.
	 */
	@Test
	public void testGetFacultyDirectory() {
		RegistrationManager testManager = RegistrationManager.getInstance();
		FacultyDirectory testDirectory = testManager.getFacultyDirectory();
		testDirectory.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 1);
		assertEquals(testManager.getFacultyDirectory().getFacultyDirectory().length, 1);
		testDirectory.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, 1);
		assertEquals(testManager.getFacultyDirectory().getFacultyDirectory().length, 1);
	}

	/**
	 * Tests that the user can login based on matching id and password
	 */
	@Test
	public void testLogin() {
		RegistrationManager testManager = RegistrationManager.getInstance();
		StudentDirectory testDirectory = testManager.getStudentDirectory();
		FacultyDirectory facDirectory = testManager.getFacultyDirectory();
		testDirectory.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		testDirectory.addStudent(FIRST_NAME, LAST_NAME, "3455", EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		facDirectory.addFaculty("Marley", "Stevens", "msteve", "msteve@ncsu.edu", "pw", "pw", 2);
		// testing a student
		assertTrue(testManager.login(ID, PASSWORD));
		testManager.logout();
		assertTrue(testManager.login("3455", PASSWORD));
		testManager.logout();

		assertFalse(testManager.login(ID, "2940ajio"));
		
		
		//testing a faculty
		assertTrue(testManager.login("msteve", "pw"));
		testManager.logout();

		// testing an invalid
		try {
			testManager.login("343433", "2940ajio");
		} catch (IllegalArgumentException ex) {
			assertEquals(ex.getMessage(), "User doesn't exist.");
		}

		// testing a registrar
		// make property file
		Properties prop = new Properties();

		InputStream input;
		try {
			input = new FileInputStream("registrar.properties");
			prop.load(input);
		} catch (Exception e) {
			fail();
		}

		assertTrue(testManager.login(prop.getProperty("id"), prop.getProperty("pw")));

	}

	/**
	 * Tests the logout method.
	 */
	@Test
	public void testLogout() {

		// make property file
		Properties prop = new Properties();

		InputStream input;
		try {
			input = new FileInputStream("registrar.properties");
			prop.load(input);
		} catch (Exception e) {
			fail();

		}

		// make registration manager
		RegistrationManager testManager = RegistrationManager.getInstance();

		// logout
		testManager.logout();
		// assert currentUser equals null after logout
		assertNull(testManager.getCurrentUser());
	}

	/**
	 * Tests that the current user is the student that logged in.
	 */
	@Test
	public void testGetCurrentUser() {

		RegistrationManager testManager = RegistrationManager.getInstance();
		StudentDirectory testDirectory = testManager.getStudentDirectory();
		testManager.logout();
		testDirectory.addStudent(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		testManager.login(ID, PASSWORD);

		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_CREDITS);

		assertEquals(testManager.getCurrentUser().getId(), s.getId());

		testManager.logout();

	}

	/**
	 * Tests RegistrationManager.enrollStudentInCourse()
	 */
	@Test
	public void testEnrollStudentInCourse() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //test if not logged in
	    try {
	        manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login("registrar", "Regi5tr@r");
	    try {
	        manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.", "registrar", manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(3, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(1, scheduleFrostArray.length);
	    assertEquals("CSC226", scheduleFrostArray[0][0]);
	    assertEquals("001", scheduleFrostArray[0][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleFrostArray[0][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleFrostArray[0][3]);
	    assertEquals("9", scheduleFrostArray[0][4]);
	            
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    //Check Student Schedule
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(10, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(3, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC226", scheduleHicksArray[1][0]);
	    assertEquals("001", scheduleHicksArray[1][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
	    assertEquals("8", scheduleHicksArray[1][4]);
	    assertEquals("CSC116", scheduleHicksArray[2][0]);
	    assertEquals("003", scheduleHicksArray[2][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
	    assertEquals("9", scheduleHicksArray[2][4]);
	    
	    manager.logout();
	}

	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //test if not logged in
	    try {
	        manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login("registrar", "Regi5tr@r");
	    try {
	        manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
	        fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.", "registrar", manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    assertFalse("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: False - student not enrolled in the course", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(0, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(0, scheduleFrostArray.length);
	    
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(10, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(3, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC226", scheduleHicksArray[1][0]);
	    assertEquals("001", scheduleHicksArray[1][1]);
	    assertEquals("Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
	    assertEquals("MWF 9:35AM-10:25AM", scheduleHicksArray[1][3]);
	    assertEquals("9", scheduleHicksArray[1][4]);
	    assertEquals("CSC116", scheduleHicksArray[2][0]);
	    assertEquals("003", scheduleHicksArray[2][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[2][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[2][3]);
	    assertEquals("9", scheduleHicksArray[2][4]);
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(7, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(2, scheduleHicksArray.length);
	    assertEquals("CSC216", scheduleHicksArray[0][0]);
	    assertEquals("001", scheduleHicksArray[0][1]);
	    assertEquals("Programming Concepts - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    assertEquals("CSC116", scheduleHicksArray[1][0]);
	    assertEquals("003", scheduleHicksArray[1][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[1][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
	    assertEquals("9", scheduleHicksArray[1][4]);
	    
	    assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(3, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(1, scheduleHicksArray.length);
	    assertEquals("CSC116", scheduleHicksArray[0][0]);
	    assertEquals("003", scheduleHicksArray[0][1]);
	    assertEquals("Intro to Programming - Java", scheduleHicksArray[0][2]);
	    assertEquals("TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
	    assertEquals("9", scheduleHicksArray[0][4]);
	    
	    assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True", manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    
	    //Check schedule
	    ahicks = directory.getStudentById("ahicks");
	    scheduleHicks = ahicks.getSchedule();
	    assertEquals(0, scheduleHicks.getScheduleCredits());
	    scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(0, scheduleHicksArray.length);
	    
	    manager.logout();
	}

	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
	    StudentDirectory directory = manager.getStudentDirectory();
	    directory.loadStudentsFromFile("test-files/student_records.txt");
	    
	    CourseCatalog catalog = manager.getCourseCatalog();
	    catalog.loadCoursesFromFile("test-files/course_records.txt");
	    
	    manager.logout(); //In case not handled elsewhere
	    
	    //Test if not logged in
	    try {
	        manager.resetSchedule();
	        fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.", manager.getCurrentUser());
	    }
	    
	    //test if registrar is logged in
	    manager.login("registrar", "Regi5tr@r");
	    try {
	        manager.resetSchedule();
	        fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
	    } catch (IllegalArgumentException e) {
	        assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.", "registrar", manager.getCurrentUser().getId());
	    }
	    manager.logout();
	    
	    manager.login("efrost", "pw");
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: False - Student max credits are 3 and course has 4.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: efrost\nCourse: CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: efrost\nCourse: CSC226-001, CSC230-001\nResults: False - cannot exceed max student credits.", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    manager.resetSchedule();
	    //Check Student Schedule
	    Student efrost = directory.getStudentById("efrost");
	    Schedule scheduleFrost = efrost.getSchedule();
	    assertEquals(0, scheduleFrost.getScheduleCredits());
	    String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
	    assertEquals(0, scheduleFrostArray.length);
	    
	    manager.logout();
	    
	    manager.login("ahicks", "pw");
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
	    assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
	    assertFalse("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits", manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));
	    
	    manager.resetSchedule();
	    //Check Student schedule
	    Student ahicks = directory.getStudentById("ahicks");
	    Schedule scheduleHicks = ahicks.getSchedule();
	    assertEquals(0, scheduleHicks.getScheduleCredits());
	    String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
	    assertEquals(0, scheduleHicksArray.length);
	    
	    manager.logout();
	}
	
	/**
	 * Tests addFacultyToCourse() method in RegistrationManager class.
	 */
	@Test
	public void testAddFacultyToCourse() {
		FacultyDirectory f = manager.getFacultyDirectory();
		f.loadFacultyFromFile("test-files/faculty_records_extended.txt");
		Faculty fa = f.getFacultyById("jdyoung2");
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course course = catalog.getCourseFromCatalog("CSC116", "001");
		course.setInstructorId(null);
		
		manager.login("registrar", "Regi5tr@r");
		manager.resetFacultySchedule(fa);
		assertTrue(manager.addFacultyToCourse(course, fa));
		manager.logout();
	
		manager.login("sesmith5", "pw");
		try {
			manager.addFacultyToCourse(course, fa);
			fail();
		}
		catch(IllegalArgumentException e){
			assertEquals(e.getMessage(), null);
		}
	
	
		manager.logout();
		
	}
	
	/**
	 * Tests removeFacultyFromCourse() method in RegistrationManager class.
	 */
	@Test
	public void testRemoveFacultyFromCourse() {
		FacultyDirectory f = manager.getFacultyDirectory();
		f.loadFacultyFromFile("test-files/faculty_records_extended.txt");
		Faculty fa = f.getFacultyById("sesmith5");
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		Course course = catalog.getCourseFromCatalog("CSC216", "001");
		System.out.println(course);
		//System.out.println(fa);
		manager.login("registrar", "Regi5tr@r");
		assertTrue(manager.removeFacultyFromCourse(course, fa));
		manager.logout();
		/*
		manager.login("bbrewer", "pw");
		try {
			manager.removeFacultyFromCourse(course, fa);
			//fail();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		manager.logout();
		*/
	}
	
	/**
	 * Tests resetFacultySchedule() method in RegistrationManager class.
	 */
	@Test
	public void testResetFacultySchedule() {
		FacultyDirectory f = manager.getFacultyDirectory();
		f.loadFacultyFromFile("test-files/faculty_records.txt");
		Faculty fa = f.getFacultyById("bbrewer");
		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");
		manager.login("registrar", "Regi5tr@r");
		manager.resetFacultySchedule(fa);
		assertEquals(0, fa.getSchedule().getNumScheduledCourses());
		manager.logout();
	}
}