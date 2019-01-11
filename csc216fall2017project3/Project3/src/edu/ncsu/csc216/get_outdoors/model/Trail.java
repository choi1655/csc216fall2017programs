/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * A representation of a Trail in the GetOutdoorsManager application.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class Trail extends Observable implements Comparable<Trail> {
	/** The id of the Trail */
	private String trailID;
	/** the name of the Trail */
	private String trailName;
	/** Whether the trail is closed for maintenance */
	private boolean closedForMaintenance;
	/** The amount of snow in the trail */
	private double snow;
	/** The distance of the trail */
	private double distance;
	/** The activities for this trail */
	private SortedArrayList<Activity> activities;
	/** The difficulty of this trail */
	private Difficulty difficulty;

	/**
	 * The behaviors defined for setter methods apply when constructing an Trail
	 * with the given parameters. The constructor should throw an
	 * IllegalArgumentException if trailID or trailName are null, the empty
	 * string, or string with whitespace only. Leading and trailing whitespace
	 * should be trimmed from trailID or trailName prior to assigning to fields.
	 * An IllegalArgumentException should also be thrown if distance is
	 * negative. An IllegalArgumentException should be thrown if activities or
	 * difficulty is null. Observers of Trail are notified of the change.
	 * 
	 * @param trailID
	 *            to set
	 * @param trailName
	 *            to set
	 * @param activities
	 *            to set
	 * @param trailMaintenance
	 *            to set
	 * @param snow
	 *            to set
	 * @param distance
	 *            to set
	 * @param difficulty
	 *            to set
	 */
	public Trail(String trailID, String trailName, SortedArrayList<Activity> activities, boolean trailMaintenance,
			double snow, double distance, Difficulty difficulty) {
		if (difficulty == null) {
			throw new IllegalArgumentException();
		}
		setId(trailID);
		setName(trailName);
		setActivities(activities);
		setTrailMaintenance(trailMaintenance);
		setSnow(snow);
		setDistance(distance);
		this.difficulty = difficulty;
		setChanged();
		this.notifyObservers(this);

	}

	/**
	 * Private helper method to set the id of the Trail. Throws an exception if
	 * the id is null, empty string, or consists only of whitespace characters.
	 * 
	 * @param id
	 *            trail's id .
	 */
	private void setId(String id) {
		if (id == null || id.equals("") || !id.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		trailID = id.trim();
	}

	/**
	 * Private helper method to set the name of the Trail. Throws an exception
	 * if the name is null, empty string, or consists only of whitespace
	 * characters.
	 * 
	 * @param name
	 *            name of the trail.
	 */
	private void setName(String name) {
		if (name == null || name.equals("") || !name.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		trailName = name.trim();

	}

	/**
	 * An IllegalArgumentException should be thrown if activities is null. The
	 * activities field is set, and Observers of `Park` Trail are notified of
	 * the change.
	 * 
	 * @param activities
	 *            to set
	 */
	public void setActivities(SortedArrayList<Activity> activities) {
		if (activities == null) {
			throw new IllegalArgumentException();
		}
		this.activities = activities;
		setChanged();
		this.notifyObservers(this);
	}

	/**
	 * The trailMaintenance field is set, and Observers of `Park` Trail are
	 * notified of the change.
	 * 
	 * @param trailMaintenance
	 *            to set
	 */
	public void setTrailMaintenance(boolean trailMaintenance) {
		this.closedForMaintenance = trailMaintenance;
		setChanged();
		this.notifyObservers(this);

	}

	/**
	 * The snow field is set; if snow parameter is negative, the field is set to
	 * zero. Observers of `Park` Trail are notified of the change.
	 * 
	 * @param snow
	 *            to set
	 */
	public void setSnow(double snow) {
		if (snow < 0) {
			this.snow = 0;
		} else {
			this.snow = snow;
		}
		setChanged();
		this.notifyObservers();
	}

	/**
	 * The snowfall is added to snow field; if this results in a negative value,
	 * the snow field is set to zero. The updated value of snow is returned.
	 * Observers of `Park` Trail are notified of the change.
	 * 
	 * @param snowfall
	 *            to add to snow field
	 * @return double updated value of snow
	 */
	public double addSnow(double snowfall) {
		double newSnowAmt = snow + snowfall;
		if (newSnowAmt < 0) {
			this.snow = 0;
		} else {
			this.snow = newSnowAmt;
		}
		setChanged();
		this.notifyObservers(this);
		return this.snow;

	}

	/**
	 * An IllegalArgumentException should be thrown if distance is negative. The
	 * distance field is set, and Observers of `Park` Trail are notified of the
	 * change.
	 * 
	 * @param distance
	 *            to set
	 */
	public void setDistance(double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException("Distance can't be negative");
		}
		this.distance = distance;
		setChanged();
		this.notifyObservers(this);

	}

	/**
	 * Returns the ID of the trail.
	 * 
	 * @return trailID of the trail
	 */
	public String getTrailID() {
		return trailID;
	}

	/**
	 * Returns the name of the trail.
	 * 
	 * @return trailName of the trail
	 */
	public String getTrailName() {
		return trailName;
	}

	/**
	 * Returns the activities field.
	 * 
	 * @return activities of the trail
	 */
	public SortedArrayList<Activity> getActivities() {
		return activities;
	}

	/**
	 * Returns true if the trail is closed for maintenance.
	 * 
	 * @return true if closed for maintenance
	 */
	public boolean closedForMaintenance() {
		return closedForMaintenance;
	}

	/**
	 * Returns the snow field.
	 * 
	 * @return snow of the trail
	 */
	public double getSnow() {
		return snow;
	}

	/**
	 * Returns the trail's distance.
	 * 
	 * @return distance of the trail
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the difficulty of the trail.
	 * 
	 * @return difficulty of the trail
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Returns whether based on maintenance and snow level the trail is open for
	 * the given activity. [Recall from Part 1: If snow is required for the
	 * activity, the snow boundary is the minimum number of inches of snow
	 * needed in order to do the activity. If snow is not required for the
	 * activity, the snow boundary is the maximum number of inches of snow
	 * before people are no longer able to do the activity.] Returns false, if
	 * activity is not allowed on the trail.
	 * 
	 * @param activity
	 *            to see if the trail is open for
	 * @return true if trail is open for this activity
	 */
	public boolean trailOpen(Activity activity) {
		if (!allow(activity)) {
			return false;
		}
		if (closedForMaintenance()) {
			return false;
		}
		if (activity.snowNeeded()) {
			if (snow < activity.getSnowBoundary()) {
				return false;
			}
		} else {
			if (snow > activity.getSnowBoundary()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns whether activity is allowed on this trail. This method does NOT
	 * say that based on maintenance and snow level the trail is currently open.
	 * 
	 * @param activity
	 *            to see if it's allowed on this trail
	 * @return true if this activity is allowed on this trail
	 */
	public boolean allow(Activity activity) {
		return activities.contains(activity);
	}

	/**
	 * HashCode method for this class.
	 * 
	 * @return int difference
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trailName == null) ? 0 : trailName.hashCode());
		return result;
	}

	/**
	 * Two Trail objects are considered the same if their trailNames are an
	 * exact match. The match is case-sensitive.
	 * 
	 * @param obj
	 *            to compare
	 * @return true if objects are equal to each other
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trail other = (Trail) obj;
		if (!trailName.equals(other.trailName))
			return false;
		return true;
	}

	/**
	 * The string representation of an Activity is the trailName,
	 * closedForMaintenance, snow, distance, difficult and activities fields
	 * separated by tabs.
	 * (trailName\tclosedForMaintenance\tsnow\tdistance\tdifficulty\tactivities
	 * where activities is a string of activities separated by tabs)
	 * 
	 * @return String representation of an Activity
	 */
	@Override
	public String toString() {
		String actString = "";
		for (int i = 0; i < activities.size(); i++) {
			if (i == activities.size() - 1) {
				actString += activities.get(i).getName();
			} else {
				actString += activities.get(i).getName() + "\t";
			}
		}

		return trailName + "\t" + closedForMaintenance + "\t" + snow + "\t" + distance + "\t" + difficulty + "\t"
				+ actString;
	}

	/**
	 * Two Trail objects are compared on their trailNames. Delegate to the
	 * String’s compareTo method.
	 * 
	 * @param trail
	 *            to compare
	 * @return int the difference
	 */
	@Override
	public int compareTo(Trail trail) {
		return this.trailName.compareTo(trail.getTrailName());
	}
}
