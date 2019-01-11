/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * A LinkedList based implementation of an Queue. Implements the Queue
 * Interface. Contains the methods enqueue, dequeue, isEmpty, size and
 * setCapacity.
 * 
 * @param <E>
 *            the type of object to be stored in the LinkedQueue.
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public class LinkedQueue<E> implements Queue<E> {

	/** A LinkedAbstractList of the objects stored in this queue. */
	private LinkedAbstractList<E> list;
	/** The capacity of this queue. */
	private int capacity;

	/**
	 * Constructs a LinkedQueue based on the given capacity value.
	 * 
	 * @param cap
	 *            the capacity of the LinkedQueue.
	 */
	public LinkedQueue(int cap) {
		list = new LinkedAbstractList<E>(cap);
		setCapacity(cap);
	}

	/**
	 * Adds the element to the back of the queue. Throws an exception if the size of
	 * the queue is greater than or equal to the capacity.
	 * 
	 * @param element
	 *            the object to be added to the queue
	 */
	@Override
	public void enqueue(E element) {
		if (this.size() >= capacity) {
			throw new IllegalArgumentException();
		}
		list.add(element);
	}

	/**
	 * Removes the first element at the front of the queue. Throws an exception if
	 * the size of the queue is equal to zero.
	 * 
	 * @return object removed from the queue.
	 */
	@Override
	public E dequeue() {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		return list.remove(0);
	}

	/**
	 * Returns a boolean value of whether the queue is empty or not.
	 * 
	 * @return boolean value of whether queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the size of the queue.
	 * 
	 * @return the size of the queue.
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the queue. Throws an exception if the capacity is less
	 * than zero or greater than the size of the queue.
	 * 
	 */
	@Override
	public void setCapacity(int cap) {
		if (cap < 0 || cap < this.size()) {
			throw new IllegalArgumentException();
		}

		capacity = cap;
	}

	/**
	 * Checks to see if the object is in the queue.
	 * 
	 * @param object
	 *            to be searched for in the queue.
	 * @return boolean value of whether object is found or not
	 */
	public boolean contains(E object) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(object)) {
				return true;
			}
		}

		return false;

	}

}
