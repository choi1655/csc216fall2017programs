package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tester for the CourseNameValidator class.
 * 
 * @author kaguillo
 * @author alcherni
 * @author jballie
 */
public class CourseNameValidatorTest {
	/**
	 * Tests isValid().
	 * 
	 * @throws InvalidTransitionException
	 *             an exception is thrown when an invalid transition is attempted
	 */
	@Test
	public void testIsValid() throws InvalidTransitionException {
		CourseNameValidator cnv = new CourseNameValidator();
		// Tests for one letter
		assertTrue(cnv.isValid("E115"));
		// Tests for two letters
		assertTrue(cnv.isValid("MA305"));
		// Tests for three letters
		assertTrue(cnv.isValid("CSC216"));
		// Tests for four letters
		assertTrue(cnv.isValid("HESF110"));
		// Tests for suffix
		assertTrue(cnv.isValid("E115h"));
		// Tests that empty string returns false
		assertFalse(cnv.isValid(""));

		// Tests that a Course name can only contain letters and digits.
		try {
			cnv.isValid("!!!E115");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}

		// Tests that a Course name must start with a letter.
		try {
			cnv.isValid("545E");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		// Tests that a Course name cannot start with more than 4 letters.
		try {
			cnv.isValid("ABCDE123");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		// Tests that a Course name must have 3 digits
		try {
			cnv.isValid("ABC1234");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		try {
			cnv.isValid("ABC1e");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		try {
			cnv.isValid("ABC12ee");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		// Tests that a Course name can only have a 1 letter suffix
		try {
			cnv.isValid("e115hh");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		// test that a course name has no digits after suffix
		try {
			cnv.isValid("e115h2");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}

	}
}
