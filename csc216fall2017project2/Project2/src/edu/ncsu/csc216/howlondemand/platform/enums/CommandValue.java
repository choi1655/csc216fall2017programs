package edu.ncsu.csc216.howlondemand.platform.enums;

/**
 * Enums for FSM functions.
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public enum CommandValue {
	/** Initial State, Quit to Selection*/
	SELECT_STATION, 
	/** Buffering*/
	BUFFERING, 
	/** Without Buffering*/
	NOT_BUFFERING, 
	/** Stop*/
	STOP, 
	/** Play*/
	PLAY, 
	/** Finish the Track*/
	FINISH_TRACK, 
	/** Finish the Station*/
	FINISH_STATION, 
	/** Return to Selection*/
	RETURN, 
	/** Skip to Forward track*/
	SKIP_FORWARD, 
	/** Skip to Backward track*/
	SKIP_BACKWARD 
	
}
