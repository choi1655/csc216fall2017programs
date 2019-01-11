package edu.ncsu.csc216.howlondemand.platform;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * Interface for HowlOnDemandSystem.java's subclasses.
 * FSM.
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public interface HowlOnDemandSystemState {
	/**
	 * Update the {@link HowlOnDemandSystem} based on the given {@link Command}.
	 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
	 * is not a valid action for the given state.  
	 * @param c {@link Command} describing the action that will update the {@link HowlOnDemandSystem}'s
	 * state.
	 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
	 * for the given state.
	 */
	public void updateState(Command c);
	
	/**
	 * Returns the state name.
	 * @return String name of the state
	 */
	public String getStateName();
}