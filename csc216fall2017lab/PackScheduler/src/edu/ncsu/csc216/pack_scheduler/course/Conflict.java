package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface to include a function to check if there is a time conflict between
 * scheduled activities
 * 
 * @author dtbrown5
 */
public interface Conflict {

	/**
	 * Method to check if there is a time conflict between scheduled events
	 * 
	 * @param possibleConflictingActivity
	 *            The event to check against
	 * @throws ConflictException
	 *             If the events have conflicting time commitments
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
