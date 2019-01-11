/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Custom LinkedList that extends AbstractSequentialList class.
 * 
 * @author kavitpatel
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @param <E>
 *            element to pass
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** The pointer to the front listNode */
	private ListNode front;
	/** The pointer to the back listNode */
	private ListNode back;
	/** The size */
	private int size;

	/**
	 * No argument constructor that initializes the fields.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);

		front.next = back;
		back.prev = front;
		size = 0;
	}

	/**
	 * Inner class to create nodes.
	 * 
	 * @author mchoi@ncsu.edu
	 * @author jballie@ncsu.edu
	 * @author krpatel7@ncsu.edu
	 */
	private class ListNode {

		/** The object that is the stored in this node */
		private E data;
		/** the node this node points to */
		private ListNode next;

		private ListNode prev;

		/**
		 * Constructs a ListNode object with only the data as a parameter. This
		 * constructor is only used when a new node is to be added at the start
		 * of the linkedList.
		 * 
		 * @param data
		 *            Object to be stored in the list node
		 */
		public ListNode(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;

		}

		/**
		 * Constructs a ListNode object with the given Object data and next
		 * ListNode parameters.
		 * 
		 * @param data
		 *            Object to be stored in the list node
		 * @param next
		 *            the next listNode in the linked list
		 */
		public ListNode(E data, ListNode prev, ListNode next) {

			this.data = data;
			this.next = next;
			this.prev = prev;

		}

	}

	/**
	 * Inner class that interates through the linkedlist.
	 * 
	 * @author mchoi@ncsu.edu
	 * @author jballie@ncsu.edu
	 * @author krpatel7@ncsu.edu
	 */
	private class LinkedListIterator implements ListIterator<E> {

		/** The pointer to the previous listNode */
		ListNode previous = new ListNode(null);
		/** The pointer to the next listNode */
		ListNode next = new ListNode(null);
		/** The index of the previous listNode */
		int previousIndex;
		/** The index of the next listNode */
		int nextIndex;
		/** The lastRetrived listNode */
		ListNode lastRetrieved;

		/**
		 * Constructor that takes in index of node and sets previous and next's
		 * location. Throws IndexOutOfBoundsException if the index is not in
		 * range.
		 * 
		 * @param idx
		 *            index of the node
		 */
		public LinkedListIterator(int idx) {
			if (idx < 0 || idx > size) {
				throw new IndexOutOfBoundsException("Index not in range");
			}

			if (idx == 0) {
				previous = front;
				next = front.next;

			} else if (idx == size) {

				previous = back.prev;
				next = back;

			}

			else {

				ListNode current = front;
				for (int i = 0; i < idx; i++) {

					current = current.next;
				}

				previous = current;
				next = current.next;
			}
			previousIndex = idx - 1;
			nextIndex = idx;

			lastRetrieved = null;

		}

		/**
		 * Method that adds element that is being passed in as a parameter.
		 * 
		 * @param object
		 *            element to add
		 */
		@Override
		public void add(E object) {
			if (object == null) {
				throw new NullPointerException("Can't add null.");
			}
			if (size() == 0) {
				ListNode temp = new ListNode(object, back.prev, front.next);
				front.next = temp;
				back.prev = temp;

			} else {
				ListNode temp = new ListNode(object, next.prev, previous.next);
				previous.next = temp;
				next.prev = temp;

			}
			size++;
			lastRetrieved = null;

		}

		/**
		 * Method that returns true if list has next node.
		 * 
		 * @return true if next node exists
		 */
		@Override
		public boolean hasNext() {
			if (next.data == null) {
				return false;
			}
			return true;
		}

		/**
		 * Method that returns true if list had previous node.
		 * 
		 * @return true if list had previous node
		 */
		@Override
		public boolean hasPrevious() {
			if (previous.data == null) {
				return false;
			}
			return true;
		}

		/**
		 * Method that returns the next element.
		 * 
		 * @return E next element
		 */
		@Override
		public E next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			nextIndex++;
			previousIndex++;
			E data = next.data;
			lastRetrieved = next;
			next = next.next;
			previous = previous.next;
			return data;
		}

		/**
		 * Method that returns the index of next
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Method that returns the element that exists before the current.
		 * 
		 * @return E previous node
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			previousIndex--;
			nextIndex--;
			E data = previous.data;
			lastRetrieved = previous;
			previous = previous.prev;
			next = next.prev;
			return data;
		}

		/**
		 * Method that returns the index of previous node.
		 * 
		 * @return previousIndex index of previous
		 */
		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return previousIndex;
		}

		/**
		 * Method that removes the element.
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}

			if (lastRetrieved == previous) {
				previous = previous.prev;
				previous.next = lastRetrieved.next;
				next.prev = lastRetrieved.prev;

			} else if (lastRetrieved == next) {
				next = next.next;
				next.prev = lastRetrieved.prev;
				previous.next = lastRetrieved.next;

			}

			lastRetrieved.data = null;
			lastRetrieved = null;
			size--;

		}

		/**
		 * Method that replaces element that is being passed in.
		 * 
		 * @param element
		 *            element to replace with
		 */
		@Override
		public void set(E element) {
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}

			if (element == null) {
				throw new NullPointerException();
			}

			lastRetrieved.data = element;

		}

	}

	/**
	 * Iterates through the list.
	 * 
	 * @param idx
	 *            index of element
	 * @return iterator returns LinkedListIterator
	 */
	public ListIterator<E> listIterator(int idx) {
		LinkedListIterator iterator = new LinkedListIterator(idx);
		return iterator;
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Overrides the add method. Checks for duplicates and throws an
	 * IllegalArgumentException if duplicate is found.
	 */
	@Override
	public void add(int idx, E obj) {
		ListNode temp = front;
		while (temp.next != null) {
			if (temp.data != null && temp.data.equals(obj)) {
				throw new IllegalArgumentException("this already exists in the list");
			}
			temp = temp.next;

		}

		super.add(idx, obj);
	}

	/**
	 * Overrides the set method. Checks for duplicates and throws an exception
	 * if duplicate is found.
	 */
	@Override
	public E set(int idx, E obj) {
		ListNode temp = front;
		while (temp.next != null) {
			if (temp.data != null && temp.data.equals(obj)) {
				throw new IllegalArgumentException("this already exists in the list");
			}
			temp = temp.next;

		}

		return super.set(idx, obj);
	}

}
