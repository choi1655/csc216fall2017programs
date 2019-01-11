/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tester for the ArrayList Class.
 * 
 * @author Jonathan Balliet
 * @author Kamai Guillory
 * @author Anna Chernikov
 *
 */
public class ArrayListTest {

	/**
	 * Test method for Size().
	 */
	@Test
	public void testSize() {
		ArrayList<String> list = new ArrayList<String>();

		assertEquals(list.size(), 0);

	}

	/**
	 * Test method for Add().
	 */
	@Test
	public void testAdd() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(0, 5);
		list.add(0, 4);
		list.add(0, 3);
		list.add(0, 8);
		list.add(0, 15);
		list.add(0, 14);
		list.add(0, 13);
		list.add(0, 18);
		list.add(0, 52);
		list.add(0, 42);
		list.add(0, 32);
		list.add(0, 82);

		assertEquals(list.get(0), new Integer(82));
		assertEquals(list.get(1), new Integer(32));
		assertEquals(list.get(2), new Integer(42));

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add(0, "Anna");
		list2.add(1, "Kamai");
		list2.add(1, "Jon");

		assertEquals(list2.get(0), "Anna");
		assertEquals(list2.get(1), "Jon");
		assertEquals(list2.get(2), "Kamai");

		ArrayList<String> list3 = new ArrayList<String>();
		list3.add(0, "apple");
		list3.add(0, "orange");
		list3.add(1, "banana");
		list3.add(3, "kiwi");

		assertEquals(list3.get(0), "orange");
		assertEquals(list3.get(1), "banana");
		assertEquals(list3.get(2), "apple");
		assertEquals(list3.get(3), "kiwi");

		ArrayList<String> list4 = new ArrayList<String>();
		list4.add(0, "orange");
		list4.add(1, "banana");
		list4.add(2, "apple");
		list4.add(3, "kiwi");

		assertEquals(list4.get(0), "orange");
		assertEquals(list4.get(1), "banana");
		assertEquals(list4.get(2), "apple");
		assertEquals(list4.get(3), "kiwi");

		try {
			list.add(0, 5);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		try {
			list.add(null);
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for Get().
	 */
	@Test
	public void testGetInt() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(0, 5);

		try {
			list.get(45);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Test method for Remove().
	 */
	@Test
	public void testRemove() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);

		list.remove(1);

		assertEquals(list.get(0), new Integer(5));
		assertEquals(list.get(1), new Integer(3));

	}

	/**
	 * Test method for Set().
	 */
	@Test
	public void testSet() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);

		list.set(0, 10);

		assertEquals(list.get(0), new Integer(10));
		assertEquals(list.get(1), new Integer(4));
		assertEquals(list.get(2), new Integer(3));

	}

}
