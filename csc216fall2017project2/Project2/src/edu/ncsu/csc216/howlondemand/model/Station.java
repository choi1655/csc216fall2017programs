package edu.ncsu.csc216.howlondemand.model;

import java.util.ArrayList;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationXML;

/**
 * A Station should contain the id, title, repeat, shuffle, 
 * color, a collection of AudioTracks and an index to point at the current AudioTrack. 
 * Similar to AudioTrack, the Station class should contain two constructors.
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public class Station {
	private int id;
	private String title;
	private boolean repeat;
	private boolean shuffle;
	private int color;
	private int index;
	private ArrayList<AudioTrack> playlist;
	
	/**
	 * Assigns values from the parameters and initialize an empty collection of AudioTracks.
	 * Sets index to 0.
	 * @param id of the AudioTrack
	 * @param title of the AudioTrack
	 * @param color of the AudioTrack
	 */
	public Station(int id, String title, int color)  {
		playlist = new ArrayList<AudioTrack>();
		setId(id);
		setTitle(title);
		setColor(color);
		setIndex(0);
		repeat = false;
		shuffle = false;
	}
	
	/**
	 * Pulls values from the StationXML parameter, including any AudioTracks 
	 * supplied from StationXML's getAudioTracks() method. The getId, getTitle, 
	 * getRepeat, getShuffle, getPlaylist, getColor, getIndex, and getCurrentAudioTrack 
	 * methods should operate as traditional getter methods.
	 * @param stationXML XML file of station
	 * @throws MalformedTrackException thrown if track is malformed
	 */
	public Station(StationXML stationXML) throws MalformedTrackException {
		this(stationXML.getId(), stationXML.getTitle(), stationXML.getColor());
		repeat = stationXML.isRepeat();
		shuffle = stationXML.isShuffle();
		for (int i = 0; i < stationXML.getAudioTracks().getAudioTrackXML().size(); i++) {
			playlist.add(new AudioTrack(stationXML.getAudioTracks().getAudioTrackXML().get(i)));
		}
	}

	/**
	 * Getter for id field.
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for id field.
	 * Throws IAE if id is negative.
	 * @param id the id to set
	 */
	public void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("ID cannot be negative.");
		}
		this.id = id;
	}

	/**
	 * Getter for title field.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title field.
	 * Throws IAE if title is null.
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Getter for repeat field.
	 * @return the repeat
	 */
	public boolean getRepeat() {
		return repeat;
	}
	
	/**
	 * Alternates between true and false value, depending on their previous values.
	 */
	public void toggleRepeat() {
		if (repeat) {
			repeat = false;
		} else if (!repeat) {
			repeat = true;
		}
	}
	
	/**
	 * Getter for shuffle field.
	 * @return the shuffle
	 */
	public boolean getShuffle() {
		return shuffle;
	}
	
	/**
	 * Alternates between true and false, depending on their previous values.
	 */
	public void toggleShuffle() {
		if (shuffle) {
			shuffle = false;
		} else if (!shuffle) {
			shuffle = true;
		}
	}
	
	/**
	 * Returns the playlist.
	 * @return the playlist 
	 */
	public ArrayList<AudioTrack> getPlaylist() {
		return playlist;
	}
	
	/**
	 * Adds the audio track to the station.
	 * @param audioTrack to add to the station
	 */
	public void addAudioTrack(AudioTrack audioTrack) {
		playlist.add(audioTrack);
	}
	
	/**
	 * Returns true if the station has a next available track.
	 * Returns true if the index is less than the size of the playlist.
	 * @return true if next track exists
	 */
	public boolean hasNextTrack() {
		boolean isTrue = false;
		if (getIndex() + 1 < playlist.size()) {
			isTrue = true;
		}
		return isTrue;
	}
	
	/**
	 * Getter for color field.
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Sets the color only if color is between 0 and 5 inclusive.
	 * Throws IAE if color is out of bound.
	 * @param color the color to set
	 */
	public void setColor(int color) {
		if (color >= 0 && color <= 5) {
			this.color = color;
		} else {
			throw new IllegalArgumentException("Color should be between 0 and 5 inclusive.");
		}
	}

	/**
	 * Getter for index field.
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Setter for index field.
	 * Throws IAE if index is negative.
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Index cannot be negative.");
		}
		this.index = index;
	}
	
	/**
	 * Returns the current audio track.
	 * @return AudioTrack the current audio track
	 */
	public AudioTrack getCurrentAudioTrack() {
		return playlist.get(getIndex());
	}
	
	/**
	 * Resets the station.
	 * Makes the index return to 0 and repeat and shuffle to false.
	 */
	public void reset() {
		setIndex(0);
		repeat = false;
		shuffle = false;
	}
	
	/**
	 * Returns the title of the station.
	 * @return String title
	 */
	public String toString() {
		return "Station \"" + getTitle() + "\"";
	}
}
