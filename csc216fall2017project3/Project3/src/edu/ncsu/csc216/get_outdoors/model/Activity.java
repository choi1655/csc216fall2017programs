package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

/**
 * A representation of an Activity in the GetOutdoorsManager application.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class Activity extends Observable implements Comparable<Activity> {
	/** The activityId of the Activity */
	private String activityId;
	/** The name of the Activity */
	private String name;
	/** The description of the Activity */
	private String description;
	/** Whether this Activity needs snow */
	private boolean needSnow;
	/** The snow boundary for this activity */
	private int snowBoundary;

	/**
	 * The behaviors defined for setDescription(), setNeedSnow(), and
	 * setSnowBoundary() apply when constructing an Activity with the given
	 * parameters. That means the constructor should throw an
	 * IllegalArgumentException if activityID, name, or description are null,
	 * the empty string, or string with whitespace only. Leading and trailing
	 * whitespace should be trimmed from activityID, name, and description prior
	 * to assigning to fields. An IllegalArgumentException should also be thrown
	 * if snowBoundary is negative. Observers of Activity are notified of the
	 * change.
	 * 
	 * @param id
	 *            to set
	 * @param name
	 *            to set
	 * @param desc
	 *            to set
	 * @param snow
	 *            to set
	 * @param boundary
	 *            to set
	 */
	public Activity(String id, String name, String desc, boolean snow, int boundary) {
		setId(id);
		setName(name);
		setDescription(desc);
		setNeedSnow(snow);
		setSnowBoundary(boundary);
		setChanged();
		notifyObservers(this);

	}

	/**
	 * Private helper method to set the id of the Activity. Throws an exception
	 * if the id is null, empty string, or consists only of whitespace
	 * characters.
	 * 
	 * @param id
	 *            activity's id .
	 */
	private void setId(String id) {
		if (id == null || id.equals("") || !id.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		activityId = id.trim();

	}

	/**
	 * Private helper method to set the name of the Activity. Throws an
	 * exception if the name is null, empty string, or consists only of
	 * whitespace characters.
	 * 
	 * @param name
	 *            name of the activity.
	 */
	private void setName(String name) {
		if (name == null || name.equals("") || !name.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		this.name = name.trim();

	}

	/**
	 * If the description parameter is null, the empty string, or string with
	 * whitespace only, an IllegalArgumentException is thrown. Otherwise, the
	 * field is set with leading and trailing whitespace removed, and Observers
	 * of Activity are notified of the change.
	 * 
	 * @param desc
	 *            to set
	 */
	public void setDescription(String desc) {
		if (desc == null || desc.equals("") || !desc.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		description = desc.trim();
		setChanged();
		notifyObservers(this);

	}

	/**
	 * The needSnow field is set and Observers of Activity are notified of the
	 * change.
	 * 
	 * @param snow
	 *            to set
	 */
	public void setNeedSnow(boolean snow) {
		needSnow = snow;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * If the snowBoundary parameter is negative, an IllegalArgumentException is
	 * thrown. Otherwise, the snowBoundary field is set, and Observers of
	 * Activity are notified of the change.
	 * 
	 * @param boundary
	 *            to set
	 */
	public void setSnowBoundary(int boundary) {
		if (boundary < 0) {
			throw new IllegalArgumentException();
		}
		snowBoundary = boundary;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Returns the activityId field.
	 * 
	 * @return activityId id of this activity
	 */
	public String getActivityID() {
		return activityId;
		
	}

	/**
	 * Returns the name of the activity.
	 * 
	 * @return name of the activity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of the activity.
	 * 
	 * @return description of the activity
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the snowNeeded boolean field of the activity.
	 * 
	 * @return needSnow of the activity
	 */
	public boolean snowNeeded() {
		return needSnow;
	}

	/**
	 * Returns snowBoundary field of the activity.
	 * 
	 * @return snowBoundary of the activity
	 */
	public int getSnowBoundary() {
		return snowBoundary;
	}

	/**
	 * The string representation of an Activity is the name, description,
	 * needSnow, and snowBoundary fields separated by tabs.
	 * (name\tdescription\tneedSnow\tsnowBoundary)
	 * 
	 * @return String representation of the activity
	 */
	public String toString() {
		return  name + "\t" + description + "\t" + needSnow + "\t" + snowBoundary;
	}

	/**
	 * HashCode method of Activity.
	 * 
	 * @return the hashCode of the Activity.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Two Activity objects are considered the same if their names are an exact
	 * match. The match is case-sensitive.
	 * 
	 * @param obj
	 *            to compare if equal
	 * @return true if equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Two Activity objects are compared on their names. Delegate to the
	 * String’s compareTo method.
	 * 
	 * @param a
	 *            activity to compare
	 * @return int difference
	 */
	@Override
	public int compareTo(Activity a) {
		return name.compareTo(a.getName());

	}

}
