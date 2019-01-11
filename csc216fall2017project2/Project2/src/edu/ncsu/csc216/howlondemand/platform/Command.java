package edu.ncsu.csc216.howlondemand.platform;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * Used in enum CommandValue.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public class Command {
	private CommandValue c;
	
	/**
	 * Constructor that takes in CommandValue object as a parameter and set the value.
	 * @param value command value
	 */
	public Command(CommandValue value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		c = value;
	}
	
	/**
	 * Method that gets command.
	 * @return c commandValue 
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * Method that returns a formatted String value of Command.
	 * @return String representation of commandValue
	 */
	public String toString() {
		return c.toString();
	}
}
