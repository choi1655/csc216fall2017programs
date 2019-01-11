package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

/**
 * A representation of a Park in the GetOutdoorsManager application.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class Park extends Observable implements Comparable<Park> {

	private String parkId;
	private String name;
	private String description;
	private double snowChange;

	/**
	 * The constructor should throw an IllegalArgumentException if parkID, name,
	 * or description are null, the empty string, or string with whitespace
	 * only. Leading and trailing whitespace should be trimmed from parkID,
	 * name, and description prior to assigning to fields. snowChange should be
	 * set to 0. Observers of Park are notified of the change.
	 * 
	 * @param parkID
	 *            to set
	 * @param name
	 *            to set
	 * @param desc
	 *            to set
	 */
	public Park(String parkID, String name, String desc) {
		if (parkID == null || name == null || desc == null || parkID.equals("") || name.equals("") || desc.equals("")
				|| !parkID.matches(".*\\w.*") || !name.matches(".*\\w.*") || !desc.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		/*
		 * if (parkID.charAt(0) == ' ') { parkID = parkID.substring(1); } if
		 * (name.charAt(0) == ' ') { name = name.substring(1); } if
		 * (desc.charAt(0) == ' ') { desc = desc.substring(1); } if
		 * (parkID.charAt(parkID.length() - 1) == ' ') { parkID =
		 * parkID.substring(0, parkID.length() - 1); } if
		 * (name.charAt(name.length() - 1) == ' ') { name = name.substring(0,
		 * name.length() - 1); } if (desc.charAt(desc.length() - 1) == ' ') {
		 * desc = desc.substring(0, desc.length() - 1); }
		 */
		parkId = parkID.trim();
		this.name = name.trim();
		description = desc.trim();
		snowChange = 0.0;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * The constructor should throw an IllegalArgumentException if parkID, name,
	 * or description are null, the empty string, or string with whitespace
	 * only. Leading and trailing whitespace should be trimmed from parkID,
	 * name, and description prior to assigning to fields. Observers of Park are
	 * notified of the change.
	 * 
	 * @param parkID
	 *            to set
	 * @param name
	 *            to set
	 * @param desc
	 *            to set
	 * @param snowChange
	 *            to set
	 */
	public Park(String parkID, String name, String desc, double snowChange) {
		if (parkID == null || name == null || desc == null || parkID.equals("") || name.equals("") || desc.equals("")
				|| !parkID.matches(".*\\w.*") || !name.matches(".*\\w.*") || !desc.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		/*
		 * if (parkID.charAt(0) == ' ') { parkID = parkID.substring(1); } if
		 * (name.charAt(0) == ' ') { name = name.substring(1); } if
		 * (desc.charAt(0) == ' ') { desc = desc.substring(1); } if
		 * (parkID.charAt(parkID.length() - 1) == ' ') { parkID =
		 * parkID.substring(0, parkID.length() - 1); } if
		 * (name.charAt(name.length() - 1) == ' ') { name = name.substring(0,
		 * name.length() - 1); } if (desc.charAt(desc.length() - 1) == ' ') {
		 * desc = desc.substring(0, desc.length() - 1); }
		 */
		parkId = parkID.trim();
		this.name = name.trim();
		description = desc.trim();
		setSnowChange(snowChange);
	}

	/**
	 * Returns name of the park.
	 * 
	 * @return name of the park
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns park's id.
	 * 
	 * @return parkId of the park
	 */
	public String getParkID() {
		return parkId;
	}

	/**
	 * Returns the description of park.
	 * 
	 * @return description of the park
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns snow change of the park.
	 * 
	 * @return snowChange of the park
	 */
	public double getSnowChange() {
		return snowChange;
	}

	/**
	 * The snowChange field is set, and Observers of Park are notified of the
	 * change.
	 * 
	 * @param snowChange
	 *            to set
	 */
	public void setSnowChange(double snowChange) {
		this.snowChange = snowChange;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Two Park objects are compared on their names. Delegate to the String's
	 * compareTo method.
	 * 
	 * @param anotherPark
	 *            to compare with
	 * @return int difference
	 * @see java.lang.String#compareTo(java.lang.String)
	 */
	public int compareTo(Park anotherPark) {
		return name.compareTo(anotherPark.name);
	}

	/**
	 * The string representation of a Park is the name, description, and
	 * snowChange fields separated by tabs. (name\tdescription\tsnowChange)
	 * 
	 * @return String representation of a park
	 */
	public String toString() {
		return name + "\t" + description + "\t" + snowChange;
	}

}
