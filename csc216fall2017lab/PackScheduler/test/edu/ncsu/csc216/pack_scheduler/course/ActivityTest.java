package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test class to test the Activity. At the time of writing, it only tests checkConflict()
 * @author Dominic Brown
 */
public class ActivityTest {

	/**
	 * Test for checkConflict()
	 */
	@Test
	public void testCheckConflict() {
	    Activity a1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "MW", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", 10, "TH", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	    
	    a1.setMeetingDays("TH");
	    a1.setActivityTime(1445, 1530);
	    try {
	        a1.checkConflict(a2);
	        fail(); //ConflictException should have been thrown, but was not.
	    } catch (ConflictException e) {
	        //Check that the internal state didn't change during method call.
	        assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    }
	   
    	//Checking the activities in the opposite order is still invalid
	    try {
	    	a2.checkConflict(a1);
	    	fail();
	    } catch (ConflictException e) {
	    	assertEquals("TH 2:45PM-3:30PM", a1.getMeetingString());
	        assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		}
	    
    	//Activities at non-overlapping times on the same day
	    a1.setActivityTime(1530, 1630);
	    try {
	    	a1.checkConflict(a2);
	    	assertEquals("TH 3:30PM-4:30PM", a1.getMeetingString());
	    	assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
	    } catch (ConflictException e) {
	    	fail();
	    }
	    
	    //Checking the activities in the opposite order still is valid
	    try {
	    	a2.checkConflict(a1);
	    	assertEquals("TH 3:30PM-4:30PM", a1.getMeetingString());
	    	assertEquals("TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
	    	fail();
	    }
	    
	    //If one of the Events is arranged, there should be no conflicts
	    Activity a4 = new Course("STR101", "Da Street - Introduction to Da Street", "001", 3, "drdre1", 10, "A");
	    try {
	    	a4.checkConflict(a1);
	    	a1.checkConflict(a4);
	    	assertEquals("Arranged", a4.getMeetingString());
	    	assertEquals("TH 3:30PM-4:30PM", a1.getMeetingString());
	    } catch (ConflictException e) {
	    	fail();
	    }
	    
	    //Checking when an activity's run time is completely covered by the other activity's time
	    a1 = new Course("TST111", "Test Course", "101", 2, "testperson", 10, "MW", 1330, 1445);
	    a2 = new Course("TDT112", "Test Course2", "102", 2, "testperson2", 10, "W", 1400, 1430);
	    try {
	    	a1.checkConflict(a2);
			fail();
		} catch (ConflictException e) {
			assertEquals("MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("W 2:00PM-2:30PM", a2.getMeetingString());
		}
	    
	}

}
