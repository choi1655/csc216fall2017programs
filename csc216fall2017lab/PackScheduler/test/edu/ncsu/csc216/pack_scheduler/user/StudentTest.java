package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test all methods of Student class
 * 
 * @author hachaud3
 * @author dtbrown5
 * @author alcherni
 *
 */
public class StudentTest {

	/**
	 * Test the constructor of Student
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		String firstName = "FirstName";
		String lastName = "LastName";
		String id = "flname";
		String email = "flname@email.com";
		String password = "letmein";
		int maxCredits = 12;
	
		Student s = new Student(firstName, lastName, id, email, password, maxCredits);
		
		assertEquals(s.getFirstName(), firstName);
		assertEquals(s.getLastName(), lastName);
		assertEquals(s.getId(), id);
		assertEquals(s.getEmail(), email);
		assertEquals(s.getPassword(), password);
		assertEquals(s.getMaxCredits(), maxCredits);
		
		String nullId = null;
		String emptyId = "";
		

		User s3 = null;
		User s2 = null;
		try {
			s2 = new Student(firstName, lastName, nullId, email, password, maxCredits);
			fail();
		} catch (Exception e) {
            assertEquals(s2, null);
		}
		try {
			s3 = new Student(firstName, lastName, emptyId, email, password, maxCredits);
			fail();
		} catch (Exception e) {
            assertEquals(s3, null);
		}
		
		
	}
	
	/**
	 * Test the second constructor of Student
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		String firstName = "FirstName";
		String lastName = "LastName";
		String id = "flname";
		String email = "flname@email.com";
		String password = "letmein";
	
		Student s = new Student(firstName, lastName, id, email, password);
		
		assertEquals(s.getFirstName(), firstName);
		assertEquals(s.getLastName(), lastName);
		assertEquals(s.getId(), id);
		assertEquals(s.getEmail(), email);
		assertEquals(s.getPassword(), password);
		assertEquals(s.getMaxCredits(), Student.MAX_CREDITS);
	}

	/**
	 * Test the hashCode method of Student
	 */
	@Test
	public void testHashCode() {
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu",
				"ilovejava", 18);
		User student2 = setUpStudent();
		User student3 = setUpStudent();
		
		assertNotEquals(student1.hashCode(), student2.hashCode());
		assertEquals(student2.hashCode(), student3.hashCode());
		assertNotEquals(student1.hashCode(), student3.hashCode());
	}
	
	/**
	 * Test the setFirstName method of Student
	 */
	@Test
	public void testSetFirstName() {
		User s = setUpStudent();
		
		String newName = "Steve";
		s.setFirstName(newName);
		assertEquals(s.getFirstName(), newName);
		
		String invalidName = "";
		try {
			s.setFirstName(invalidName);
			fail();
		} catch (Exception e) {
			assertEquals(s.getFirstName(), newName);
		}
		
		String invalidName2 = null;
		try {
			s.setFirstName(invalidName2);
			fail();
		} catch (Exception e) {
			assertEquals(s.getFirstName(), newName);
		}
		
	}

	/**
	 * Test the setLastName method of Student
	 */
	@Test
	public void testSetLastName() {
		User s = setUpStudent();
		
		String newName = "Steve";
		s.setLastName(newName);
		assertEquals(s.getLastName(), newName);
		
		String invalidName = "";
		try {
			s.setLastName(invalidName);
			fail();
		} catch (Exception e) {
			assertEquals(s.getLastName(), newName);
		}
		
		String invalidName2 = null;
		try {
			s.setLastName(invalidName2);
			fail();
		} catch (Exception e) {
			assertEquals(s.getLastName(), newName);
		}
	}

	/**
	 * Test the setEmail method of Student
	 */
	@Test
	public void testSetEmail() {
		User s = setUpStudent();
		
		String newEmail = "steve@email.com";
		s.setEmail(newEmail);
		assertEquals(s.getEmail(), newEmail);
		
		String invalidEmail = "";
		try {
			s.setEmail(invalidEmail);
			fail();
		} catch (Exception e) {
			assertEquals(s.getEmail(), newEmail);
		}
		
		String invalidEmail1 = null;
		try {
			s.setEmail(invalidEmail1);
			fail();
		} catch (Exception e) {
			assertEquals(s.getEmail(), newEmail);
		}
		
		String invalidEmail2 = "steveatemail.com";
		try {
			s.setEmail(invalidEmail2);
			fail();
		} catch (Exception e) {
			assertEquals(s.getEmail(), newEmail);
		}
		
		String invalidEmail3 = "steve@emaildotcom";
		try {
			s.setEmail(invalidEmail3);
			fail();
		} catch (Exception e) {
			assertEquals(s.getEmail(), newEmail);
		}
		
		String invalidEmail4 = "steve.@emailcom";
		try {
			s.setEmail(invalidEmail4);
			fail();
		} catch (Exception e) {
			assertEquals(s.getEmail(), newEmail);
		}
	}

	/**
	 * Test the setPassword method of Student
	 */
	@Test
	public void testSetPassword() {
		User s = setUpStudent();
		
		String nullPassword = null;
		String emptyPassword = "";
		String goodPassword = "steve242";
		
        s.setPassword(goodPassword);
        assertEquals(s.getPassword(), goodPassword);
        
		try {
			s.setPassword(nullPassword);
			fail();
		} catch (Exception e) {
			assertEquals(s.getPassword(), goodPassword);
		}
		
		try {
			s.setPassword(emptyPassword);
			fail();
		} catch (Exception e) {
			assertEquals(s.getPassword(), goodPassword);
		}
		

	}

	/**
	 * Test the setMaxCredits method of Student
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = setUpStudent();
		
		int highCredits = 21;
		int lowCredits = 2;
		int goodCredits = 15;
		
		s.setMaxCredits(goodCredits);
		assertEquals(s.getMaxCredits(), goodCredits);
		
		try {
			s.setMaxCredits(highCredits);
			fail();
		} catch (Exception e) {
			assertEquals(s.getMaxCredits(), goodCredits);
		}
		
		try {
			s.setMaxCredits(lowCredits);
			fail();
		} catch (Exception e) {
			assertEquals(s.getMaxCredits(), goodCredits);
		}
	}

	/**
	 * Test the equals method of Student
	 */
	@Test
	public void testEqualsObject() {
		User student1 = new Student("Timmy", "Stevens", "tsteven5", "tsteven5@ncsu.edu",
				"ilovejava", 18);
		User student2 = setUpStudent();
		User student3 = null;
		
		assertFalse(student1.equals(student2));
		assertFalse(student1.equals(student3));
		
		student3 = setUpStudent();
		assertTrue(student2.equals(student3));
		assertFalse(student3.equals(student1));
		
		student2 = student1;
		
		assertTrue(student1.equals(student2));
		
	}

	/**
	 * Test the toString method of Student
	 */
	@Test
	public void testToString() {
		Student student = setUpStudent();
		String string = student.getFirstName() + "," + student.getLastName() + "," + student.getId()
			+ "," + student.getEmail() + "," + student.getPassword() + "," + student.getMaxCredits();
		
		assertEquals(string, student.toString());
	}
	
	

	/**
	 * Tests the CompareTo method of Student
	 */
	@Test
	public void testCompareTo() {
		Student s1 = setUpStudent();
		Student s2 = new Student("Anna", "Chernikov", "alcherni", "alcherni@ncsu.edu", "milkk", 7);
		Student s3 = new Student("Bobo", "Chernikov", "blcherni", "blcherni@ncsu.edu", "milkkk", 8);
		Student s4 = new Student("Bobo", "Chernikov", "zlcherni", "blcherni@ncsu.edu", "milkkk", 8);
		Student s5 = new Student("Bobo", "Chernikov", "zlcherni", "glcherni@ncsu.edu", "milkshake", 10);	
		
		assertTrue(s2.compareTo(s1) < 0 );
		assertTrue(s1.compareTo(s2) > 0);
		assertTrue(s3.compareTo(s2) > 0);
		assertTrue(s3.compareTo(s4) < 0);		
		assertTrue(s4.compareTo(s5) ==  0);	
		
		
	}
	
	/**
	 * Helper method to set up student before test
	 * @return a fully initialized Student
	 */
	public Student setUpStudent() {
		String firstName = "FirstName";
		String lastName = "LastName";
		String id = "flname";
		String email = "flname@email.com";
		String password = "letmein";
		int maxCredits = 12;
		
		return new Student(firstName, lastName, id, email, password, maxCredits);
	}


}
