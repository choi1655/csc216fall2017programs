package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * InvalidTransistionException constructs a custom exception that is used for
 * invalid transitions.
 * 
 * @author alcherni
 * @author jballie
 * @author kaguillo
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for a InvalidTransitonException with no parameter. Calls it's
	 * parent class with a default message.
	 */
	public InvalidTransitionException() {
		super("Invalid FSM Transition.");

	}

	/**
	 * Constructor for a InvalidTransitonException with a string parameter. Calls
	 * its parent class with the passed in message.
	 * 
	 * @param message
	 *            String message to be sent to parent
	 * 
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
}
