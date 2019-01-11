package edu.ncsu.csc216.howlondemand.model;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;

/**
 * A TrackChuck should contain exactly 8 characters that are used in hexadecimal 
 * notation (0123456789ABCDEF - assume only capital letters are valid characters). 
 * If the TrackChuck constructor or setChunk methods receive a null parameter or an 
 * invalid String, they will throw a MalformedTrackException. 
 * The isValid method returns a boolean to indicate whether a String is valid (true) 
 * or not (false).
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public class TrackChunk {
	private String chunk;
	
	/**
	 * No argument constructor.
	 * Initializes chunk to 8 zeros.
	 * @throws MalformedTrackException thrown if chunk is invalid
	 */
	public TrackChunk() throws MalformedTrackException {
		chunk = "00000000";
	}
	
	/**
	 * Constructor taking in one parameter for chunk.
	 * Will throw a MalformedTrackException if parameter is null or an invalid string.
	 * @param chunk chunk to take
	 * @throws MalformedTrackException thrown if track is malformed
	 */
	public TrackChunk(String chunk) throws MalformedTrackException {
		if (!validChunk(chunk) || chunk == null) {
			throw new MalformedTrackException();
		}
		setChunk(chunk);
	}
	
	/**
	 * Getter for chunk field.
	 * @return the chunk
	 */
	public String getChunk() {
		return chunk;
	}
	
	/**
	 * Setter for chunk field.
	 * @param newChunk to set
	 * @throws MalformedTrackException thrown if track is malformed
	 */
	public void setChunk(String newChunk) throws MalformedTrackException {
		if (!validChunk(newChunk) || newChunk == null) {
			throw new MalformedTrackException();
		}
		chunk = newChunk;
	}
	
	/**
	 * Returns true if chunk taken in is valid.
	 * @param chunk to determine if valid
	 * @return true if valid
	 */
	public boolean validChunk(String chunk) {
		if (chunk == null) {
			return false;
		}
		
		if(chunk.matches("[0-9A-F]+") && chunk.length() == 8) {
			return true;
		}
		return false;
	}

	/**
	 * Method that returns the chunk in formatted String.
	 * @return String chunk in format
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrackChunk [chunk=" + chunk + "]";
	}
}
