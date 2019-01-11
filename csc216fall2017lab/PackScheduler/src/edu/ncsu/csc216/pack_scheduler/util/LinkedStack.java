package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A LinkedList based implementation of an Stack. Implements the Stack
 * Interface. Contains the methods push, pop, isEmpty, size and setCapacity.
 * 
 * @param <E>
 *            the type of object to be stored in the LinkedStack.
 * @author mchoi
 * @author jballie
 *
 */
public class LinkedStack<E> implements Stack<E> {
	/** A LinkedAbstractList of the objects stored in this stack. */
	private LinkedAbstractList<E> list;
	/** The capacity of this stack. */
	private int capacity;

	/**
	 * Constructs an LinkedStack based on the given capacity value.
	 * 
	 * @param cap
	 *            the capacity of the LinkedStack.
	 */
	public LinkedStack(int cap) {
		list = new LinkedAbstractList<E>(cap);
		setCapacity(cap);
	}

	/**
	 * Adds the element to the top of the stack. Throws an exception if the size of
	 * the stack is greater than or equal to the capacity.
	 * 
	 * @param element
	 *            the object to be added to the stack
	 */
	@Override
	public void push(E element) {
		if (this.size() >= capacity) {
			throw new IllegalArgumentException();
		}
		list.add(0, element);

	}

	/**
	 * Removes the element at the top of the stack. Throws an exception if the size
	 * of the stack is equal to zero.
	 * 
	 * @return object removed from the stack.
	 */
	@Override
	public E pop() {
		if (this.size() == 0) {
			throw new EmptyStackException();
		}
		return list.remove(0);
	}

	/**
	 * Returns a boolean value of whether the stack is empty or not.
	 * 
	 * @return boolean value of whether stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the size of the stack.
	 * 
	 * @return the size of the stack.
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the stack. Throws an exception if the capacity is less
	 * than zero or greater than the size of the stack.
	 * 
	 */
	@Override
	public void setCapacity(int cap) {
		if (cap < 0 || cap < this.size()) {
			throw new IllegalArgumentException();
		}

		capacity = cap;

	}

}
