/**
 * 
 */
package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * JUnit test cases for Command.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11012017
 */
public class CommandTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.Command#Command(edu.ncsu.csc216.howlondemand.platform.enums.CommandValue)}.
	 */
	@Test
	public void testCommand() {
		Command command = new Command(CommandValue.BUFFERING);
		assertEquals(CommandValue.BUFFERING, command.getCommand());
		try {
			command = new Command(null);
			fail();
		} catch (IllegalArgumentException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.Command#toString()}.
	 */
	@Test
	public void testToString() {
		Command command = new Command(CommandValue.PLAY);
		assertEquals("PLAY", command.toString());
	}

}
