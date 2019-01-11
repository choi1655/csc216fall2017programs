package edu.ncsu.csc216.pack_scheduler.util;

/**
 * An implementation of a generic ArrayList. Stores objects in an array of
 * objects. Has functions to add, remove, set, and get objects stored in this
 * array list.
 * 
 * @author Jonathan Balliet
 * @author Kamai Guillory
 * @author Anna Chernikov
 * @param <E>
 *            The type of object to be stored in this ArrayList
 */
public class ArrayList<E> extends java.util.AbstractList<E> {
	/** Initialize the size of the list array */
	private static final int INIT_SIZE = 10;
	/** Stores each object in an array of objects */
	private Object[] list;
	/** The size of the arrayList (how many items are in this list) */
	private int size;
	/** The capacity of the arrayList. */
	private int capacity;

	/**
	 * Constructor for an ArrayList. Initializes the size of the array of objects,
	 * the capacity, and the size of this array.
	 */
	public ArrayList() {
		list = new Object[INIT_SIZE];
		capacity = INIT_SIZE;
		size = 0;
	}

	/**
	 * Adds object at the designated index, based on the passed in parameters.
	 * Throws an exception if a null object is added, the given index is out of
	 * bounds, or the object already exists in the array list.
	 * 
	 * @param index
	 *            index in the list array
	 * @param obj
	 *            object to be stored at that index
	 */
	@Override
	public void add(int index, E obj) {

		// If the object is null, throws a NullPointerException
		if (obj == null) {

			throw new NullPointerException();
		}
		// If the index is less 0 or greater than the size of the array, throws
		// an IndexOutOfBoundsException
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		// If the size is equal to or greater then the capacity, calls the
		// growArray helper method to increase the capacity
		if (size >= capacity) {
			growArray();
		}

		// Checks that the object does not already exist in this list
		for (int i = 0; i < size; i++) {
			if (list[i] != null && list[i].equals(obj)) {

				throw new IllegalArgumentException();
			}
		}

		// Shifts everything to the right of the index to be added
		for (int i = size - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}
		// Sets the object at the wanted index
		list[index] = obj;
		// Increases the array size
		size++;
	}

	/**
	 * Helper method to double the capacity of the array when the size of the array
	 * equal its capacity.
	 */
	private void growArray() {

		Object[] temp = new Object[(capacity * 2)];

		for (int i = 0; i < size; i++) {
			temp[i] = list[i];
		}

		list = temp;

	}

	/**
	 * Returns the object in the list based on the given index value. Throws an
	 * exception if the the given index is out of bounds.
	 * 
	 * @param index
	 *            index of the object in the array list to be returned
	 * @return E the object to be returned
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {

		// If the index is less 0 or greater than the size of the array, throws
		// an IndexOutOfBoundsException
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return (E) list[index];

	}

	/**
	 * Returns the size of the array list.
	 * 
	 * @return size the size of the array list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes the object in the array list at the given index. Returns the object
	 * that is removed. Throws an exception if the given index is out of bounds.
	 * 
	 * @param index
	 *            index location of the object to be removed
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		E obj = (E) list[index];

		for (int i = index; i < size - 1; i++) {

			list[i] = list[i + 1];

		}
		list[size - 1] = null;
		size--;

		return obj;

	}

	/**
	 * Sets the passed in object for the given index value in the array list.
	 * Returns the original object that was stored there. Throws an exception if a
	 * null object is added, the given index is out of bounds, or the object already
	 * exists in the array list.
	 * 
	 * @param index
	 *            index in the array list to set the new object
	 * @param obj
	 *            the object to be stored in the array list
	 * @return E the original object stored at that index
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E obj) {
		if (index == 0 && size == 0) {
			throw new IndexOutOfBoundsException();
		}

		if (obj == null) {

			throw new NullPointerException();
		}

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < size; i++) {
			if (list[i] != null && list[i].equals(obj)) {
				throw new IllegalArgumentException();
			}
		}

		E original = (E) list[index];
		list[index] = obj;
		return original;

	}

}
