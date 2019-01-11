package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Exception to be thrown when two activities have conflicting time commitments
 * 
 * @author Dominic Brown
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default ConflictException constructor
	 */
	public ConflictException() {
		super("Schedule conflict.");
	}

	/**
	 * ConflictException constructor that allows a custom error message
	 * 
	 * @param errorMessage
	 *            Custom error message
	 */
	public ConflictException(String errorMessage) {
		super(errorMessage);
	}

}
