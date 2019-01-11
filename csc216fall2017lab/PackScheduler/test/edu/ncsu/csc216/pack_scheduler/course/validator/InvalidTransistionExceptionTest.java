package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;
	/**
	 * Tester for the InvalidTransitionException class.
	 * @author kaguillo
	 * @author alcherni
	 * @author jballie
	 */
public class InvalidTransistionExceptionTest {
	
	/**
	 * Test method for the default InvalidTransistionException constructor.
	 */
	@Test
	public void testInvalidTransistionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ce.getMessage());
	}

	/**
	 * Test method for InvalidTransitionException constructor with a string parameter.
	 */
	@Test
	public void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

}
