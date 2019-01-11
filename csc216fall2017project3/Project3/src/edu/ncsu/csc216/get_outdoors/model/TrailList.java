/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * Represents a TrailList that contains a SortedArrayList of Trails. Implements
 * the Observer and Tabular interfaces.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class TrailList extends Observable implements Observer, Tabular {

	/** The id of this TrailList */
	private String trailListID;
	/** The number of trails in this TrailList */
	private int numTrails;
	/** The SortedArrayList of Trail */
	private SortedArrayList<Trail> trails;
	/** The park these trails are contained in */
	private Park park;

	/**
	 * Constructs the SortedArrayList, sets numTrails to 0, and sets the field
	 * with the parameter value. Throws an IllegalArgumentException if park
	 * parameter is null or if the trailListID is null, empty string, or a
	 * string with whitespace only Observers of TrailList are notified of the
	 * change.
	 * 
	 * @param park
	 *            to pass in
	 */
	public TrailList(Park park) {
		trails = new SortedArrayList<Trail>();
		numTrails = 0;
		if (park == null) {
			throw new IllegalArgumentException();
		}
		this.park = park;
		if (park.getParkID() == null || park.getParkID().trim().equals("")) {
			throw new IllegalArgumentException();
		}

		trailListID = park.getParkID().trim();
		park.addObserver(this);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Returns the name of the park.
	 * 
	 * @return String name of the park
	 */
	public String getParkName() {
		return park.getName();
	}

	/**
	 * Returns true if the Trail is added to the list and false otherwise. The
	 * method constructs a Trail using the provided parameters and creates a
	 * unique id for the Trail using the TrailListID and numTrails. The Trail is
	 * added to the trails list so that the list is always sorted by trailName.
	 * Observers of TrailListare notified of the change if the Trail is added to
	 * the trails list.
	 * 
	 * @param trailName
	 *            of the Trail to add
	 * @param activities
	 *            of the Trail to add
	 * @param trailMaintenance
	 *            of the Trail to add
	 * @param snow
	 *            of the Trail to add
	 * @param distance
	 *            of the Trail to add
	 * @param difficulty
	 *            of the Trail to add
	 * @return true if Trail has been added correctly
	 */
	public boolean addTrail(String trailName, SortedArrayList<Activity> activities, boolean trailMaintenance,
			double snow, double distance, Difficulty difficulty) {
		boolean isAdded = false;
		String trailID = trailListID + "-" + numTrails; // park-#-#
		Trail trail = new Trail(trailID, trailName, activities, trailMaintenance, snow, distance, difficulty);
		if (trails.add(trail)) {
			isAdded = true;
			numTrails++;
			trail.addObserver(this);
			setChanged();
			notifyObservers(this);
		}
		return isAdded;
	}

	/**
	 * Returns the Trail at the given index in the list. If the index is less
	 * than 0 or the index is greater or equal to size(), an
	 * IndexOutOfBoundsException is thrown.
	 * 
	 * @param idx
	 *            of Trail to retrieve
	 * @return Trail at given index
	 */
	public Trail getTrailAt(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return trails.get(idx);
	}

	/**
	 * Returns the Trail removed from the trails list at the given index, and
	 * Observers of TrailList are notified of the change. The TrailList is
	 * removed as an Observer of the removed Trail. If the index is less than 0
	 * or the index is greater or equal to size(), an IndexOutOfBoundsException
	 * is thrown.
	 * 
	 * @param idx
	 *            of the Trail to remove
	 * @return Trail that was removed
	 */
	public Trail removeTrail(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}

		Trail t = trails.remove(idx);

		t.deleteObserver(this);
		this.setChanged();
		notifyObservers(this);

		return t;
	}

	/**
	 * Removes the first occurrence of the Trail with the given TrailID from the
	 * list. Returns the Trail. If there is no such Trail,
	 * IndexOutOfBoundsException is thrown. If there is such a Trail, returns
	 * the trail and Observers of TrailList are notified of the change.
	 * 
	 * @param trailID
	 *            of the Trail to remove
	 * @return Trail that was removed
	 */
	public Trail removeTrail(String trailID) {
		boolean idExists = false;
		Trail t;
		int idx = 0;
		for (int i = 0; i < trails.size(); i++) {
			if (trails.get(i).getTrailID().equals(trailID)) {
				idExists = true;
				idx = i;
				break;
			}
		}
		if (!idExists) {
			throw new IndexOutOfBoundsException();
		}
		t = trails.remove(idx);
		t.deleteObserver(this);
		this.setChanged();
		notifyObservers(this);
		return t;
	}

	/**
	 * Returns the number of Trails in the trails list.
	 * 
	 * @return int size of the trailist
	 */
	public int size() {
		return trails.size();
	}

	/**
	 * Returns true if the trails list is empty and false otherwise.
	 * 
	 * @return true if the traillist is empty
	 */
	public boolean isEmpty() {
		return trails.size() == 0;
	}

	/**
	 * Returns an Object[][] array. Each row, i, contains a Trail and each
	 * column contains that trail's fields.
	 * 
	 * @return Object[][] containing the list of trails with each of its field
	 *         values.
	 */
	public Object[][] get2DArray() {
		Object[][] obj = new Object[trails.size()][7];
		for (int i = 0; i < trails.size(); i++) {
			obj[i][0] = trails.get(i).getTrailID();
			obj[i][1] = trails.get(i).getTrailName();
			obj[i][2] = trails.get(i).closedForMaintenance();
			obj[i][3] = trails.get(i).getSnow();
			obj[i][4] = trails.get(i).getDistance();
			obj[i][5] = trails.get(i).getDifficulty();
			obj[i][6] = trails.get(i).getActivities();
		}
		return obj;
	}

	/**
	 * HashCode method for TrailList.
	 * 
	 * @return int difference
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((park == null) ? 0 : park.hashCode());
		return result;
	}

	/**
	 * Two TrailList objects are considered the same if they have the same park.
	 * 
	 * @param obj
	 *            to compare
	 * @return true if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrailList other = (TrailList) obj;
		if (!park.equals(other.park))
			return false;
		return true;
	}

	/**
	 * Returns an Object[][] array. Each row, i, contains a Trail that is
	 * currently open for activity.
	 * 
	 * @param activity
	 *            to get Array from
	 * @return Object[][] with Trails
	 */
	public Object[][] get2DArray(Activity activity) {
		SortedArrayList<Trail> temp = new SortedArrayList<Trail>();
		for (int i = 0; i < trails.size(); i++) {
			if (trails.get(i).trailOpen(activity)) {
				temp.add(trails.get(i));
			}
		}

		Object[][] obj = new Object[temp.size()][7];
		for (int i = 0; i < temp.size(); i++) {
			obj[i][0] = temp.get(i).getTrailID();
			obj[i][1] = temp.get(i).getTrailName();
			obj[i][2] = temp.get(i).closedForMaintenance();
			obj[i][3] = temp.get(i).getSnow();
			obj[i][4] = temp.get(i).getDistance();
			obj[i][5] = temp.get(i).getDifficulty();
			obj[i][6] = temp.get(i).getActivities();

		}
		return obj;

	}

	/**
	 * Returns an Object[][] array. Each row, i, contains a Trail that is
	 * currently not closed due to maintenance.
	 * 
	 * @return Object[][] with Trails
	 */
	public Object[][] get2DArrayNoMaintenance() {
		SortedArrayList<Trail> temp = new SortedArrayList<Trail>();
		for (int i = 0; i < trails.size(); i++) {
			if (!trails.get(i).closedForMaintenance()) {
				temp.add(trails.get(i));
			}
		}

		Object[][] obj = new Object[temp.size()][7];
		for (int i = 0; i < temp.size(); i++) {
			obj[i][0] = temp.get(i).getTrailID();
			obj[i][1] = temp.get(i).getTrailName();
			obj[i][2] = temp.get(i).closedForMaintenance();
			obj[i][3] = temp.get(i).getSnow();
			obj[i][4] = temp.get(i).getDistance();
			obj[i][5] = temp.get(i).getDifficulty();
			obj[i][6] = temp.get(i).getActivities();
		}

		return obj;
	}

	/**
	 * Returns the index of the first occurrence of a Trail that has an exact
	 * match to the provided id or -1 if there are no Trails with an exact match
	 * on the given id.
	 * 
	 * @param id
	 *            of the trail to search
	 * @return int index of Trail with given id
	 */
	public int indexOfID(String id) {
		for (int i = 0; i < trails.size(); i++) {
			if (trails.get(i).getTrailID().equals(id)) {
				return i;

			}
		}
		return -1;
	}

	/**
	 * If a Trail in the TrailList changes, the update() method is automatically
	 * called. TrailList should propagate the notification of the change to its
	 * Observers IF the Observable o is contained in the list of Trails. The arg
	 * parameter is passed to notifyObservers(). If Park associated with the
	 * TrailList changes (snowChange value updated), the update() method is
	 * automatically called. TrailList should add the snow change to each trail
	 * and propagate the notification of the change to its Observers. The arg
	 * parameter is passed to notifyObservers().
	 * 
	 * @param ob
	 *            Observable pattern
	 * @param obj
	 *            element to update
	 */
	public void update(Observable ob, Object obj) {

		if (ob instanceof Trail && this.trails.contains((Trail) ob)) {
			setChanged();
			notifyObservers(obj);
		}
		// if the associated park with the trailList changes then...
		if (ob instanceof Park && this.getParkName().equals(((Park) ob).getName())) {
			System.out.println("Park found!!");
			for (int i = 0; i < trails.size(); i++) {
				trails.get(i).addSnow(park.getSnowChange());
				this.setChanged();
				notifyObservers(obj);

			}

		}

	}

}
