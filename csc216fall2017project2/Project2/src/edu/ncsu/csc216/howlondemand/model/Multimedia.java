package edu.ncsu.csc216.howlondemand.model;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;


/**
 * Represents the id and title, which is required for the AudioTrack class.
 * You should implement and test the Multimedia class before implementing 
 * the AudioTrack class since AudioTrack inherits Multimedia.
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public abstract class Multimedia {
	/**ID of Track*/ 
	protected int id;
	/**Title of track */
	protected String title;
	
	/**
	 * Constructor that takes in two parameters.
	 * @param id of the multimedia
	 * @param title of the multimedia
	 */
	public Multimedia(int id, String title) {
		if (id < 0) {
			throw new IllegalArgumentException("ID cannot be negative");
		}
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.id = id;
		this.title = title;
	}
	
	/**
	 * Getter for the ID field.
	 * @return id of the multimedia
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter for the ID field.
	 * Throws MalformedTrackException if newId is negative.
	 * @param newId id to set
	 */
	public void setId(int newId) {
		if (newId < 0) {
			throw new IllegalArgumentException();
		}
		id = newId;
	}
	
	/**
	 * Getter for title field.
	 * @return title of the multimedia
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter for title field.
	 * Throws MalformedTrackException if newTitle is null.
	 * @param newTitle title to set
	 */
	public void setTitle(String newTitle) {
		if (newTitle == null) {
			throw new IllegalArgumentException();
		}
		title = newTitle;
	}
	
	/**
	 * Retrieves the next chunk of the track.
	 * @return TrackChunk returns next TrackChunk
	 */
	public abstract TrackChunk getNextChunk();
	
	/** 
	 * Returns true if the track has next chunk.
	 * @return true if track has next chunk
	 */
	public abstract boolean hasNextChunk();
	
	/**
	 * Adds the TrackChunk received by the parameter.
	 * @param t chunk to add
	 * @throws MalformedTrackException thrown if track is malformed
	 */
	public abstract void addChunk(TrackChunk t) throws MalformedTrackException;
	
	/**
	 * Method that returns formatted String value.
	 * @return formatted String value.
	 */
	public abstract String toString();
}

