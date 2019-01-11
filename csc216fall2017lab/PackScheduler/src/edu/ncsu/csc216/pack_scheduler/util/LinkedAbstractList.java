package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * An implementation of a generic LinkedAbstractList. Stores objects in
 * connected nodes that have pointers to the next node in the list. Has
 * functions to add, remove, set, and get objects stored in this linked list.
 * 
 * @author alcherni
 * @author kaguillo
 * @author jballie
 *
 * @param <E>
 *            The generic object type of the list
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** ListNode for pointing to the first element of the linkedList. */
	private ListNode front;
	/** ListNode for pointing to the last element of the linkedList. */
	private ListNode back;
	/** The size of the LinkedAbstractList */
	private int size;
	/** how much the linkedAbstractList can fit */
	private int capacity;

	/**
	 * Private inner class of LinkedAbstractList. Represents a node object that
	 * stores an object and contains a pointer to the next node in the linked list.
	 * 
	 * @author alcherni
	 * @author kaguillo
	 * @author jballie
	 *
	 */
	private class ListNode {
		/** The object that is the stored in this node */
		private E data;
		/** the node this node points to */
		private ListNode next;

		/**
		 * Constructs a ListNode object with only the data as a parameter. This
		 * constructor is only used when a new node is to be added at the start of the
		 * linkedList.
		 * 
		 * @param data
		 *            Object to be stored in the list node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = front;

		}

		/**
		 * Constructs a ListNode object with the given Object data and next ListNode
		 * parameters.
		 * 
		 * @param data
		 *            Object to be stored in the list node
		 * @param next
		 *            the next listNode in the linked list
		 */
		public ListNode(E data, ListNode next) {

			this.data = data;
			this.next = next;

		}
	}

	/**
	 * Constructs a linkedAbstractList object given the passed in capacity
	 * parameter. If the capacity value is less than zero - throws an
	 * IllegalArgumentException().
	 * 
	 * @param cap
	 *            the capacity of the linked Abstract List
	 */
	public LinkedAbstractList(int cap) {
		front = null;
		back = front;
		size = 0;
		if (cap >= 0) {
			this.capacity = cap;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the Object at the index in the LinkedAbstractList. Throws an
	 * exception if the given index is less than zero or greater than or equal to
	 * the size of the list.
	 * 
	 * @param idx
	 *            the index to retrieve data from in the LinkedAbstractList
	 */
	@Override
	public E get(int idx) {

		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		int n = 0;
		while (n < idx) {

			current = current.next;
			n++;
		}
		return current.data;
	}

	/**
	 * Returns the size of the linkedAbstractList
	 * 
	 * @return size the size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds an object to the list. Throws exceptions if the the list is at its
	 * capacity, the index is less than zero or greater than the size, the value is
	 * null, or the value is duplicate of a value already present in the list.
	 * 
	 * @param idx
	 *            the index where the object must be added
	 * @param value
	 *            type E the object to be added at that index
	 */
	public void add(int idx, E value) {
		if (size == capacity) {
			throw new IllegalArgumentException("No more items can be added to the list. At full capacity");
		}

		if ((idx < 0 || idx > size())) {
			throw new IndexOutOfBoundsException("Linked list not big enough");
		}

		if (value == null) {
			throw new NullPointerException("Can't add null!");
		}
		// checks if value already exists in list
		ListNode temp = front;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals(value)) {
				throw new IllegalArgumentException("this already exists in the list");
			}
			temp = temp.next;

		}
		if (idx == 0) {
			front = new ListNode(value);
			if (size == 0) {
				back = front;
			}
		} else if (idx == size) {
			back.next = new ListNode(value);
			back = back.next;

		} else {

			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(value, current.next);
		}
		size++;

	}

	/**
	 * Sets the passed object at the given index of the linked List. Returns the
	 * object that was previously at that index. Throws an exception of the given
	 * index is greater than or equal to the size or less than zero or the passed in
	 * value is null.
	 * 
	 * @param idx
	 *            the index of the ArrayList that must be set
	 * @param value
	 *            of type E the object to set at that location
	 * @return obj the object that was set
	 */
	public E set(int idx, E value) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (value == null) {
			throw new NullPointerException();
		}

		// checks if value already exists in list
		ListNode current = front;
		for (int i = 0; i < idx; i++) {
			if (current.data.equals(value)) {
				throw new IllegalArgumentException("this already exists in the list");
			}
			current = current.next;

		}

		// actually sets
		current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		E obj = current.data;
		current.data = value;
		return obj;
	}

	/**
	 * Removes the object at the given index. Returns the object that is removed
	 * from the list.Throws an exception if the given index is greater than or equal
	 * to the size or less than zero.
	 * 
	 * @param idx
	 *            of the node to be removed
	 * @return obj the Object that was removed
	 */
	public E remove(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		E obj;
		if (idx == 0) {
			obj = front.data;
			front = front.next;

		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			obj = current.next.data;
			current.next = current.next.next;
			if (idx == size - 1) {
				back = current;
			}

		}
		size--;
		return obj;

	}

	/**
	 * Sets the capacity of the LinkedList. Throws an exception if the given
	 * capacity is less than zero or greater than the size of the linked list.
	 * 
	 * @param capacity
	 *            capacity of the linked list.
	 * 
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}

		this.capacity = capacity;

	}

}
