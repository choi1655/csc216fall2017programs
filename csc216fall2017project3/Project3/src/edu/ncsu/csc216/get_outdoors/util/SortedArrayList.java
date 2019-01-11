package edu.ncsu.csc216.get_outdoors.util;

import java.util.Arrays;

/**
 * An array based implementation of a generic SortedList that keeps its stored
 * objects in a specific sorted order. Implements the SortedList interface.
 * 
 * @author jballie
 * @author mchoi
 *
 * @param <E>
 *            The object the SortedArrayList stores
 */
public class SortedArrayList<E extends Comparable<E>> implements SortedList<E> {
	/** The Initial capacity of the SortedArrayList */
	private static int initSize = 10;
	/** The array of the generic objects the list stores */
	private E[] list;
	/** The number of elements in the list */
	private int size;
	/** The capacity of the list */
	private int capacity;

	/**
	 * Default constructor for a SortedArrayList. Initializes the capacity, list
	 * of objects, and the size.
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayList() {
		capacity = initSize;
		list = (E[]) new Comparable[initSize];
		size = 0;
	}

	/**
	 * Constructor for a SortedArrayList that sets the capacity based on the
	 * passed in value. Initializes the capacity, list of objects, and the size.
	 * 
	 * @param cap
	 *            the capacity of this list
	 */
	@SuppressWarnings("unchecked")
	public SortedArrayList(int cap) {
		if (cap < 0)
			throw new IllegalArgumentException();
		capacity = cap;
		list = (E[]) new Comparable[cap];
		this.size = 0;
	}

	/**
	 * The size of the SortedArrayList.
	 * 
	 * @return size - the size of the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Determines whether the list is empty.
	 * 
	 * @return boolean value of whether list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Determines whether the list contains the passed in object.
	 * 
	 * @return boolean value of whether the object exists in this list.
	 */
	@Override
	public boolean contains(E obj) {
		for (int i = 0; i < size; i++) {
			if (list[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the passed in object to the list. Places the object in its correct
	 * sorted when added to the list. Calls the growArray method if the size is
	 * equal to the capacity. Returns whether the object was added or not.
	 * 
	 * @return boolean value of whether the object was added.
	 */
	@Override
	public boolean add(E obj) {

		if (obj == null) {
			throw new NullPointerException();
		}
		if (this.contains(obj)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			list[size] = obj;
			size++;
			return true;
		}

		if (size >= capacity) {
			growArray();
		}

		int index = -1;
		for (int i = 0; i < size; i++) {
			if (list[i].compareTo(obj) >= 0) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			index = size;
		}

		for (int i = size - 1; i >= index; i--) {
			list[i + 1] = list[i];
		}

		list[index] = obj;
		size++;
		return true;
	}

	/**
	 * Helper method to double the capacity of the array when the size of the
	 * array equals its capacity.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {

		E[] temp = (E[]) new Comparable[(capacity * 2)];

		for (int i = 0; i < size; i++) {
			temp[i] = list[i];
		}

		list = temp;

	}

	/**
	 * Returns the object stored in the list at the passed in index value.
	 * Throws an exception if the given index is less than zero or greater than
	 * or equal to the size of the list.
	 * 
	 * @return object stored at the index.
	 */
	@Override
	public E get(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return list[index];
	}

	/**
	 * Removes the object stored at the given index value and returns it. Throws
	 * an exception if the given index is less than zero or greater than or
	 * equal to the size of the list.
	 * 
	 * @return the object that is removed from the list.
	 */
	@Override
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
	 * Returns the index of the given object. Returns -1 if the object is not
	 * found in the list.
	 * 
	 * @return index of the object in the list.
	 */
	@Override
	public int indexOf(E obj) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if (list[i].compareTo(obj) == 0) {
				index = i;
				return index;
			}
		}
		return index;
	}

	/**
	 * Determines the hashCode value for a SortedArrayList.
	 * 
	 * @return the list's hashCode value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(list);
		result = prime * result + size;
		return result;
	}

	/**
	 * Determines whether the two SortedArrayList objects are equal or not.
	 * 
	 * @return boolean value of whether the lists are equal.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortedArrayList<E> other = (SortedArrayList<E>) obj;
		if (!Arrays.equals(list, other.list))
			return false;
		return true;
	}

	/**
	 * Displays the contents of the SortedArrayList.
	 * 
	 * @return String value of list's contents.
	 */
	public String toString() {
		String listStr = "[";
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				listStr += list[i].toString();
			} else {
				listStr += list[i].toString() + ", ";
			}
		}
		listStr += "]";
		return listStr;

	}

}
