
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface that is to be implemented by an object representing a Queue.
 * Contains the abstract Queue methods enqueue, dequeue, isEmpty, size, and
 * setCapacity.
 * 
 * @param <E>
 *            the type of object to be stored in the queue.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public interface Queue<E> {
	/**
	 * Adds the element to the back of the queue.
	 * 
	 * @param element
	 *            to be added to the back of queue.
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the element at the front of the queue.
	 * 
	 * @return element at front of the queue.
	 */
	E dequeue();

	/**
	 * Returns boolean value of whether the queue is empty or not.
	 * 
	 * @return boolean value of whether queue is empty.
	 */
	boolean isEmpty();

	/**
	 * Returns the size of the queue.
	 * 
	 * @return size of queue.
	 */
	int size();

	/**
	 * Sets the capacity of the queue based on the given value.
	 * 
	 * @param cap
	 *            capacity of the queue.
	 */
	void setCapacity(int cap);
}
