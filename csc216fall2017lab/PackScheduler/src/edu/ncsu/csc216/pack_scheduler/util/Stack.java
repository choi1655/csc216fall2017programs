package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface that is to be implemented by an object representing a Stack.
 * Contains the Stack methods push, pop, isEmpty, size, and setCapacity.
 * 
 * @param <E>
 *            the type of object to be stored in the stack.
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 *
 */
public interface Stack<E> {
	/**
	 * Pushes the element to the top of the stack.
	 * 
	 * @param element
	 *            element to be pushed to the stack.
	 */
	void push(E element);

	/**
	 * Pops the element from the top of the stack and returns it.
	 * 
	 * @return the element to removed from the top of the stack
	 */
	E pop();

	/**
	 * Returns boolean value of whether the stack is empty or not.
	 * 
	 * @return boolean value of whether stack is empty.
	 */
	boolean isEmpty();

	/**
	 * Returns the size of the stack.
	 * 
	 * @return size of stack.
	 */
	int size();

	/**
	 * Sets the capacity of the stack based on the given value.
	 * 
	 * @param cap
	 *            capacity of the stack.
	 */
	void setCapacity(int cap);

}
