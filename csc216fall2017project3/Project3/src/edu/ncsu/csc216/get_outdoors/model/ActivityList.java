/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * An ActivityList has a SortedArrayList of Activity.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class ActivityList extends Observable implements Observer, Tabular {
	/** The name of the ActivityList */
	private String name;
	/** The number of Activities */
	private int numActivities;
	/** The list of sorted activities */
	private SortedArrayList<Activity> activities;

	/**
	 * Constructs the edu.ncsu.csc216.get_outdoors.util.SortedArrayList and sets
	 * numActivities to 0.
	 */
	public ActivityList() {
		name = "Activities";
		activities = new SortedArrayList<Activity>();
		numActivities = 0;
	}

	/**
	 * Returns the name field.
	 * 
	 * @return name of the activity list
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns true if the Activity is added to the list and false otherwise.
	 * The method constructs a Activity using the provided parameters and
	 * creates a unique id for the activityID using the numActivities as
	 * described above. The Activity is added to activities so that the list is
	 * always sorted by Activity name. Observers of ActivityList are notified of
	 * the change if the Activity is added to activities. You will not be able
	 * to use Collections.sort() for this method.
	 * 
	 * @param name
	 *            to set
	 * @param description
	 *            to set
	 * @param needSnow
	 *            to set
	 * @param snowBoundary
	 *            to set
	 * @return true if activity has been added
	 */
	public boolean addActivity(String name, String description, boolean needSnow, int snowBoundary) {

		String actId = "act-" + numActivities;

		Activity act = new Activity(actId, name, description, needSnow, snowBoundary);

		if (activities.add(act)) {
			numActivities++;
			act.addObserver(this);
			setChanged();
			notifyObservers(this);
			return true;
		}

		return false;
	}

	/**
	 * Returns the Activity at the given index in the activities list. If the
	 * index is less than 0 or the index is greater or equal to size() an 
	 * IndexOutOfBoundsException is thrown.
	 * 
	 * @param idx
	 *            of the activity
	 * @return Activity at given index
	 */
	public Activity getActivityAt(int idx) {
		if (idx < 0 || idx >= activities.size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds for Activties.");
		}

		return activities.get(idx);
	}

	/**
	 * Returns the number of Activity objects in the activities list.
	 * 
	 * @return int size of the Activity list
	 */
	public int size() {
		return numActivities;
	}

	/**
	 * Returns true if the activities list is empty and false otherwise.
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return numActivities == 0;
	}

	/**
	 * Returns an Object[][] array. Each row, i, contains an Activity. [i][0] is
	 * the Activity activityID, [i][1] is the Activity name, [i][2] is the
	 * Activity description, [i][3] is the Activity needSnow, and [i][4] is the
	 * Activity snowBoundary.
	 * 
	 * @return Object[][] contains activity
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] actArray = new Object[numActivities][5];
		for (int i = 0; i < activities.size(); i++) {
			Activity act = activities.get(i);
			actArray[i][0] = act.getActivityID();
			actArray[i][1] = act.getName();
			actArray[i][2] = act.getDescription();
			actArray[i][3] = act.snowNeeded();
			actArray[i][4] = act.getSnowBoundary();
		}
		return actArray;
	}

	/**
	 * Returns the index of the first occurrence of a Activity that has an exact
	 * match to the provided id or -1 if there are no Activity objects with an
	 * exact match on the given id.
	 * 
	 * @param id
	 *            to look up
	 * @return int index of the requested Activity with given id
	 */
	public int indexOfID(String id) {
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getActivityID().equals(id)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * If a Activity in the Activity List changes, the update() method is
	 * automatically called. Activity List should propagate the notification of
	 * the change to its Observers IF the Observable o is contained in the
	 * activities list. The arg parameter is passed to notifyObservers().
	 * 
	 * @param ob
	 *            of Observable pattern
	 * @param obj
	 *            element to update
	 */
	public void update(Observable ob, Object obj) {
		if (ob instanceof Activity && this.activities.contains((Activity) ob)) {
			setChanged();
			notifyObservers(obj);
		}

	}

}
