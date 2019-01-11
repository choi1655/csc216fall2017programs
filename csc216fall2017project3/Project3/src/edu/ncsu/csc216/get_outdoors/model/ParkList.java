/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.model;

import java.util.Observable;
import java.util.Observer;

import edu.ncsu.csc216.get_outdoors.util.SortedLinkedList;

/**
 * An ParkList has a SortedLinkedList of Park.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class ParkList extends Observable implements Observer, Tabular {

	private String name;
	private int numParks;
	private SortedLinkedList<Park> parks;

	/**
	 * Constructs the edu.ncsu.csc216.get_outdoors.util.SortedLinkedList and
	 * sets numParks to 0.
	 */
	public ParkList() {
		name = "Parks";
		parks = new SortedLinkedList<Park>();
		numParks = 0;
	}

	/**
	 * Returns the ParkList name.
	 * 
	 * @return name of the parklist
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns true if the Park is added to the list and false otherwise. The
	 * method constructs a Park using the provided parameters and creates a
	 * unique parkID for the Park using the numParks as described above. The
	 * Park is added to the parks list so that the list is always sorted by Park
	 * name. Observers of ParkList are notified of the change if the Park is
	 * added to the parks list. You will not be able to use Collections.sort()
	 * for this method.
	 * 
	 * @param name
	 *            of the park to add
	 * @param description
	 *            of the park to add
	 * @param snowChange
	 *            of the park to add
	 * @return true if added correctly
	 */
	public boolean addPark(String name, String description, double snowChange) {
		String parkId = "park-" + numParks;
		Park park = new Park(parkId, name, description, snowChange);

		// Checks for duplicate park and throws exception if found
		for (int i = 0; i < parks.size(); i++) {
			if (park.compareTo(parks.get(i)) == 0) {
				throw new IllegalArgumentException();
			}

		}

		boolean isTrue = false;

		if (parks.add(park)) {
			numParks++;
			park.addObserver(this);
			setChanged();
			notifyObservers(this);
			isTrue = true;
		}
		return isTrue;
	}

	/**
	 * Returns the Park at the given index in the list. If the index is less than 0 or the
	 * index is greater or equal to size() an IndexOutOfBoundsException is thrown.
	 * 
	 * @param idx
	 *            index of park to retrieve
	 * @return Park at given index
	 */
	public Park getParkAt(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return parks.get(idx);
	}

	/**
	 * Returns the number of Parks in the parks list.
	 * 
	 * @return int size of the park list
	 */
	public int size() {
		return parks.size();
	}

	/**
	 * Returns true if the list is empty and false otherwise.
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		return parks.size() == 0;
	}

	/**
	 * If a Park in the ParkList changes, the update() method is automatically
	 * called. ParkList should propagate the notification of the change to its
	 * Observers IF the Observable o is contained in the parks list of Parks.
	 * The arg parameter is passed to notifyObservers().
	 * 
	 * @param ob
	 *            Observable pattern
	 * @param obj
	 *            element to update
	 */
	public void update(Observable ob, Object obj) {
		if (ob instanceof Park && parks.contains((Park) ob)) {
			setChanged();
			notifyObservers(obj);

		}
	}

	/**
	 * Returns the index of the first occurrence of a Park that has an exact
	 * match to the provided parkID or -1 if there are no Parks with an exact
	 * match on the given parkID.
	 * 
	 * @param parkID
	 *            to look up in the list
	 * @return returnVal index of the park with given id or -1 if parkID doesn't
	 *         exist
	 */
	public int indexOfID(String parkID) {
		int returnVal = -1;
		for (int i = 0; i < parks.size(); i++) {
			if (parks.get(i).getParkID().equals(parkID)) {
				returnVal = i;
				break;
			}
		}
		return returnVal;
	}

	/**
	 * Returns an Object[][] array. Each row, i, contains a Park. [i][0] is the
	 * Park parkID, [i][1] is the Park name, [i][2] is the Park description, and
	 * [i][3] is the Park snowChange.
	 * 
	 * @return Object[][] with Park
	 */
	@Override
	public Object[][] get2DArray() {
		Object[][] obj = new Object[parks.size()][4];
		for (int i = 0; i < parks.size(); i++) {
			obj[i][0] = parks.get(i).getParkID();
			obj[i][1] = parks.get(i).getName();
			obj[i][2] = parks.get(i).getDescription();
			obj[i][3] = parks.get(i).getSnowChange();
		}
		return obj;
	}
}
