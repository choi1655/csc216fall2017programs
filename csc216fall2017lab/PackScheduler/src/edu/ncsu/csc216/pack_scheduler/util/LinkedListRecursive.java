package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Custom LinkedList with recursive method.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @author krpatel7@ncsu.edu
 * @param <E>
 *            element to use
 */
public class LinkedListRecursive<E> {
	/** The pointer to the front ListNode */
	private ListNode front;
	/** The size */
	private int size;

	/**
	 * No argument that initializes front to null and size to 0.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * Method that returns true if the list is empty.
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty() {

		return size == 0;

	}

	/**
	 * Method that returns the size of the list.
	 * 
	 * @return size of the list
	 */
	public int size() {
		return size;

	}

	/**
	 * Method that adds the passed in element.
	 * 
	 * @param obj
	 *            element to add
	 * @return true if added
	 */
	public boolean add(E obj) {
		if (this.contains(obj)) {
			throw new IllegalArgumentException();
		}
		if (front == null) {
			front = new ListNode(obj, null);
			size++;
			return true;
		} else {
			return front.add(obj);
		}

	}

	/**
	 * Method that adds element to the given index.
	 * 
	 * @param idx
	 *            to add at
	 * @param obj
	 *            to add
	 */
	public void add(int idx, E obj) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		if (obj == null) {
			throw new NullPointerException();
		}
		if (this.contains(obj)) {
			throw new IllegalArgumentException();
		}
		if (isEmpty()) {
			front = new ListNode(obj, null);
			size++;
		} else if (idx == 0) {
			front = new ListNode(obj, front);
			size++;
		} else {
			front.add(obj, idx - 1);
		}

	}

	/**
	 * Method that retrieves the element at given index.
	 * 
	 * @param idx
	 *            index to retrieve from
	 * @return E element at that index
	 */
	public E get(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		if (front == null) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		if (idx == 0) {
			return front.data;
		} else {
			return front.get(idx - 1);
		}
	}

	/**
	 * Method that removes passed in element from the list.
	 * 
	 * @param obj
	 *            to remove
	 * @return true if removed
	 */
	public boolean remove(E obj) {
		if (obj == null) {
			return false;
		}
		if (front == null) {
			return false;
		}

		if (front.data.equals(obj)) {
			front = front.next;
			size--;
			return true;
		} else {

			return front.remove(obj);

		}

	}

	/**
	 * Removes element at given index.
	 * 
	 * @param idx
	 *            to remove element at
	 * @return E element that was removed
	 */
	public E remove(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		if (front == null) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		if (idx == 0) {
			E obj = front.data;
			front = front.next;
			size--;
			return obj;
		} else {
			return front.remove(idx - 1);
		}

	}

	/**
	 * Replaces element at given index with given element
	 * 
	 * @param idx
	 *            to replace from
	 * @param obj
	 *            to replace with
	 * @return E element that was replaced
	 */
	public E set(int idx, E obj) {
		if (obj == null) {
			throw new NullPointerException();
		}
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		if (front == null) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		if (this.contains(obj)) {
			throw new IllegalArgumentException();
		}
		if (idx == 0) {
			E retObj = front.data;
			front.data = obj;
			return retObj;
		} else {
			return front.set(idx - 1, obj);
		}

	}

	/**
	 * Returns true if list contains element.
	 * 
	 * @param obj
	 *            element to check
	 * @return true if exists
	 */
	public boolean contains(E obj) {
		if (front == null) {
			return false;
		}
		if (isEmpty()) {
			return false;
		} else {
			return front.contains(obj);
		}

	}

	/**
	 * Private inner class of LinkedAbstractList. Represents a node object that
	 * stores an object and contains a pointer to the next node in the linked list.
	 * 
	 * @author krpatel7@ncsu.edu
	 * @author mchoi@ncsu.edu
	 * @author jballie@ncsu.edu
	 *
	 */
	private class ListNode {
		/** The object that is the stored in this node */
		private E data;
		/** the node this node points to */
		private ListNode next;

		/**
		 * Returns true if element exists.
		 * 
		 * @param obj
		 *            to check
		 * @return true if exists
		 */
		public boolean contains(E obj) {
			if (data.equals(obj)) {
				return true;
			} else if (next == null) {
				return false;

			} else {
				return next.contains(obj);
			}

		}

		/**
		 * Method that replaces element at given index with element passed in.
		 * 
		 * @param idx
		 *            to replace from
		 * @param obj
		 *            to replace with
		 * @return E element that was replaced
		 */
		public E set(int idx, E obj) {
			if (idx == 0) {
				E retObj = next.data;
				next.data = obj;
				return retObj;
			} else {
				if (next == null) {
					throw new IllegalArgumentException();
				}
				return next.set(idx - 1, obj);
			}
		}

		/**
		 * Returns true if element passed was removed.
		 * 
		 * @param obj
		 *            element to remove
		 * @return true if removed
		 */
		public boolean remove(E obj) {
			if (next == null) {
				return false;
			} else if (next.data.equals(obj)) {
				next = next.next;
				size--;
				return true;
			} else {
				return next.remove(obj);
			}

		}

		/**
		 * Removes element at given index.
		 * 
		 * @param idx
		 *            element to remove from
		 * @return E removed element
		 */
		public E remove(int idx) {
			if (next == null) {
				throw new IndexOutOfBoundsException();
			}

			if (idx == 0) {
				E obj = next.data;
				next = next.next;
				size--;
				return obj;
			} else {
				return next.remove(idx - 1);
			}

		}

		/**
		 * Retrieves element at given index.
		 * 
		 * @param idx
		 *            to retrieve from
		 * @return E element that was retrieved
		 */
		public E get(int idx) {
			if (next == null) {
				throw new IndexOutOfBoundsException();
			}
			if (idx == 0) {
				return next.data;
			} else {
				return next.get(idx - 1);
			}
		}

		/**
		 * Method that adds element at given index.
		 * 
		 * @param obj
		 *            element to add
		 * @param idx
		 *            location to add to
		 */
		public void add(E obj, int idx) {
			if (next == null) {
				next = new ListNode(obj, null);
				size++;
			} else if (idx == 0) {
				next = new ListNode(obj, next);
				size++;
			} else {

				next.add(obj, idx - 1);
			}

		}

		/**
		 * Adds given element to the list.
		 * 
		 * @param obj
		 *            element to add
		 * @return true if added
		 */
		public boolean add(E obj) {
			if (next == null) {
				next = new ListNode(obj, null);
				size++;
				return true;
			} else {
				return next.add(obj);

			}
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

}
