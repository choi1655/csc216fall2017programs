/**
 * 
 */
package edu.ncsu.csc216.get_outdoors.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for SortedArrayList.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class SortedArrayListTest {

	/**
	 * Test method for SortedArrayList constructor with no parameter.
	 */
	@Test
	public void testSortedArrayList() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();
		list.add(1);
		assertEquals(list.size(), 1);
		list.add(2);
		assertEquals(list.size(), 2);
		list.add(3);
		assertEquals(list.size(), 3);
		list.add(4);
		assertEquals(list.size(), 4);
		list.add(5);
		assertEquals(list.size(), 5);
		list.add(6);
		assertEquals(list.size(), 6);
		list.add(7);
		assertEquals(list.size(), 7);
		list.add(8);
		assertEquals(list.size(), 8);
		list.add(9);
		assertEquals(list.size(), 9);
		list.add(10);
		assertEquals(list.size(), 10);
		list.add(11);
		assertEquals(list.size(), 11);

	}

	/**
	 * Test method for SortedArrayList constructor with no parameter.
	 */
	@Test
	public void testSortedArrayListInt() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>(5);
		list.add(1);
		assertEquals(list.size(), 1);
		list.add(2);
		assertEquals(list.size(), 2);
		list.add(3);
		assertEquals(list.size(), 3);
		list.add(4);
		assertEquals(list.size(), 4);
		list.add(5);
		assertEquals(list.size(), 5);
		list.add(6);
		assertEquals(list.size(), 6);
	}

	/**
	 * Test method for size.
	 */
	@Test
	public void testSize() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();
		assertEquals(list.size(), 0);

		list.add(new Integer(1));
		assertEquals(list.size(), 1);

		list.add(5);
		assertEquals(list.size(), 2);

		list.add(3);
		assertEquals(list.size(), 3);

	}

	/**
	 * Test method for isEmpty.
	 */
	@Test
	public void testIsEmpty() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();
		assertTrue(list.isEmpty());

		list.add(new Integer(1));
		assertFalse(list.isEmpty());

		list.remove(0);
		assertTrue(list.isEmpty());

	}

	/**
	 * Test method for contains.
	 */
	@Test
	public void testContains() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();

		list.add(1);
		assertTrue(list.contains(1));

		list.add(5);
		assertTrue(list.contains(5));
		assertFalse(list.contains(10));

		list.add(3);
		assertTrue(list.contains(3));

		list.remove(1);
		assertFalse(list.contains(3));
	}

	/**
	 * Test method for add.
	 */
	@Test
	public void testAdd() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();
		assertTrue(list.add(1));

		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), null);
		}

		assertTrue(list.add(5));

		assertTrue(list.add(3));

		try {
			list.add(3);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}
	}

	/**
	 * Test method for get.
	 */
	@Test
	public void testGet() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();
		list.add(1);
		assertEquals(list.get(0), new Integer(1));

		list.add(5);
		assertEquals(list.get(1), new Integer(5));

		try {
			list.get(2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}

		list.add(3);
		assertEquals(list.get(1), new Integer(3));

		list.add(10);
		assertEquals(list.get(3), new Integer(10));

		list.add(2);
		assertEquals(list.get(1), new Integer(2));

		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for remove.
	 */
	@Test
	public void testRemove() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();
		list.add(1);
		assertEquals(list.get(0), new Integer(1));

		list.add(5);
		assertEquals(list.get(1), new Integer(5));

		list.add(3);
		assertEquals(list.get(1), new Integer(3));

		assertEquals(list.remove(0), new Integer(1));
		assertEquals(list.get(0), new Integer(3));

		list.add(10);
		assertEquals(list.get(2), new Integer(10));

		assertEquals(list.remove(2), new Integer(10));
		assertEquals(list.get(1), new Integer(5));

		try {
			list.remove(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}
	}

	/**
	 * Test method for indexOf.
	 */
	@Test
	public void testIndexOf() {
		SortedArrayList<Integer> list = new SortedArrayList<Integer>();

		list.add(2);
		assertEquals(list.indexOf(2), 0);

		list.add(5);
		assertEquals(list.indexOf(5), 1);

		list.add(3);
		assertEquals(list.indexOf(3), 1);
		assertEquals(list.indexOf(5), 2);

		list.add(1);
		assertEquals(list.indexOf(1), 0);

		assertEquals(list.indexOf(20), -1);
	}

	/**
	 * Test method for equals.
	 */
	@Test
	public void testEqualsObject() {
		SortedArrayList<Integer> list1 = new SortedArrayList<Integer>();
		SortedArrayList<Integer> list2 = new SortedArrayList<Integer>();
		SortedArrayList<Integer> list3 = null;
		SortedLinkedList<Integer> list4 = new SortedLinkedList<Integer>();

		list1.add(1);
		list2.add(1);

		assertEquals(list1, list2);

		list1.add(2);
		assertNotEquals(list1, list2);

		list2.add(3);
		assertNotEquals(list1, list2);

		list1 = list2;
		assertEquals(list1, list2);

		assertNotEquals(list2, list3);

		list4.add(1);
		list4.add(2);
		assertNotEquals(list4, list2);

	}

	/**
	 * Test method for hashCode.
	 */
	@Test
	public void testHashCode() {
		SortedArrayList<Integer> list1 = new SortedArrayList<Integer>();
		SortedArrayList<Integer> list2 = new SortedArrayList<Integer>();

		list1.add(1);
		list2.add(1);

		assertEquals(list1.hashCode(), list2.hashCode());

		list1.add(2);
		assertNotEquals(list1.hashCode(), list2.hashCode());

	}

	/**
	 * Tests toString.
	 */
	@Test
	public void testToString() {
		SortedArrayList<Integer> list1 = new SortedArrayList<Integer>();

		list1.add(1);
		list1.add(2);
		list1.add(3);

		String listStr = "[1, 2, 3]";
		assertEquals(list1.toString(), listStr);
	}

}
