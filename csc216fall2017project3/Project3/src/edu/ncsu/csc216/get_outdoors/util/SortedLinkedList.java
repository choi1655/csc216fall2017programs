package edu.ncsu.csc216.get_outdoors.util;

/**
 * A linked list based implementation of a generic SortedList that keeps its
 * stored objects in a specific sorted order. Implements the SortedList
 * interface. Stores a list of node objects that point to one another and
 * contains the private Node inner class.
 * 
 * @author jballie
 * @author mchoi
 *
 * @param <E>
 *            The object type the SortedLinkedList stores
 */
public class SortedLinkedList<E extends Comparable<E>> implements SortedList<E> {
	/** The node that points to the first node of this list. */
	private Node<E> front;
	/** The size of this list. */
	private int size;

	/**
	 * Default constructor for SortedLinkedList. Initializes the front node to
	 * null and the size to 0.
	 */
	public SortedLinkedList() {
		front = null;
		size = 0;
	}

	/**
	 * The size of the SortedLinkedList.
	 * 
	 * @return size - the size of the list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines whether the list is empty.
	 * 
	 * @return boolean value of whether list is empty.
	 */
	@Override
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
		Node<E> current = front;
		for (int i = 0; i < size; i++) {
			if (current.value.equals(obj)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	/**
	 * Adds the passed in object to the list. Places the object in its correct
	 * sorted when added to the list. Returns whether the object was added or
	 * not.
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
			front = new Node<E>(obj, null);
			size++;
			return true;

		} else if (front.value.compareTo(obj) >= 0) {
			front = new Node<E>(obj, front);
			size++;
			return true;
		} else {
			boolean isAdded = false;
			Node<E> current = front;
			current = front;
			while (current.next != null) {
				if (current.next.value.compareTo(obj) >= 0) {
					current.next = new Node<E>(obj, current.next);
					isAdded = true;
					break;
				}
				current = current.next;
			}

			if (isAdded) {
				size++;
				return true;
			} else {
				current.next = new Node<E>(obj, null);
				size++;
				return true;
			}

		}
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
		Node<E> current = front;

		for (int i = 0; i < index; i++) {

			current = current.next;
		}

		return current.value;

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
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E obj;
		if (index == 0) {
			obj = front.value;
			front = front.next;

		} else {
			Node<E> current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			obj = (E) current.next.value;
			current.next = current.next.next;
		}
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
		Node<E> current = front;

		for (int i = 0; i < size; i++) {
			if (current.value.equals(obj)) {
				return i;
			}
			current = current.next;
		}

		return -1;
	}

	/**
	 * Determines the hashCode value for a SortedLinkedList.
	 * 
	 * @return the list's hashCode value.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((front == null) ? 0 : front.hashCode());
		return result;
	}

	/**
	 * Determines whether the two SortedLinkedList objects are equal or not.
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
		SortedLinkedList<E> other = (SortedLinkedList<E>) obj;
		if (front == null) {
			if (other.front != null)
				return false;
		} else if (!front.equals(other.front))
			return false;
		return true;
	}

	/**
	 * Displays the contents of the SortedLinkedList.
	 * 
	 * @return String value of list's contents.
	 */
	public String toString() {
		String listStr = "[";
		Node<E> current = front;
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				listStr += current.value.toString();
			} else {
				listStr += current.value.toString() + ", ";
				current = current.next;
			}
		}
		listStr += "]";
		return listStr;

	}

	/**
	 * Private inner class of SortedLinkedList. Represents a node object that
	 * stores an object and contains a pointer to the next node in the linked
	 * list.
	 * 
	 * @author jballie
	 * @author mchoi
	 *
	 * @param <E>
	 *            The object type the Node stores
	 */
	@SuppressWarnings("hiding")
	private class Node<E> {
		/** The object that is the stored in this node */
		private E value;
		/** the node this node points to */
		private Node<E> next;

		/**
		 * Constructor for a Node with a data and node parameter. Initializes
		 * the value this node stores and the node it points to.
		 * 
		 * @param data
		 *            object the node stores
		 * @param node
		 *            node this node points to
		 */
		public Node(E data, Node<E> node) {
			value = data;
			next = node;
		}

		/**
		 * Determines the hashCode value for a Node.
		 * 
		 * @return the Nodes's hashCode value.
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((next == null) ? 0 : next.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		/**
		 * Determines whether the two Node objects are equal or not.
		 * 
		 * @return boolean value of whether the Nodes are equal.
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
			Node<E> other = (Node<E>) obj;
			if (next == null) {
				if (other.next != null)
					return false;
			} else if (!next.equals(other.next))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}

	}

}
