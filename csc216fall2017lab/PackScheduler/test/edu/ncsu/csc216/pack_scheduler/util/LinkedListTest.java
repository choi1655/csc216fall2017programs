package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

/**
 * JUnit test for LinkedList class functionality.
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 * @author krpatel7@ncsu.edu
 */
public class LinkedListTest {

	/**
	 * Test method for Size().
	 */
	@Test
	public void testSize() {
		LinkedList<String> list = new LinkedList<String>();

		assertEquals(list.size(), 0);

	}

	/**
	 * Test method for Add().
	 */
	@Test
	public void testAdd() {
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.add(0, 5);
		
		list.add(1, 4);
	
		list.add(2, 3);
		list.add(3, 8);
		list.add(4, 15);
		list.add(5, 14);
		list.add(6, 13);
		list.add(7, 18);
		list.add(8, 52);
		list.add(9, 42);
		list.add(10, 32);
		list.add(11, 82);


		
		assertEquals(list.get(0), new Integer(5));
		assertEquals(list.get(1), new Integer(4));
		assertEquals(list.get(2), new Integer(3));

		LinkedList<String> list2 = new LinkedList<String>();
		list2.add(0, "Anna");
		list2.add(1, "Kamai");
		list2.add(1, "Jon");

		assertEquals(list2.get(0), "Anna");
		assertEquals(list2.get(1), "Jon");
		assertEquals(list2.get(2), "Kamai");

		LinkedList<String> list3 = new LinkedList<String>();
		list3.add(0, "apple");
		list3.add(0, "orange");
		list3.add(1, "banana");
		list3.add(3, "kiwi");

		assertEquals(list3.get(0), "orange");
		assertEquals(list3.get(1), "banana");
		assertEquals(list3.get(2), "apple");
		assertEquals(list3.get(3), "kiwi");

		LinkedList<String> list4 = new LinkedList<String>();
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
			assertEquals(e.getMessage(), "this already exists in the list");
		}

		try {
			list.add(null);
		} catch (NullPointerException e) {
			assertEquals(e.getMessage(), "Can't add null.");
		}

	}

	/**
	 * Test method for Get().
	 */
	@Test
	public void testGetInt() {
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.add(0, 5);

		try {
			list.get(45);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Index not in range");
		}

	}

	/**
	 * Test method for Remove().
	 */
	@Test
	public void testRemove() {
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);

		list.remove(1);
		//System.out.println(list.get(0));
		//System.out.println(list.get(1));
		assertEquals(list.get(0), new Integer(5));
		assertEquals(list.get(1), new Integer(3));

	}

	/**
	 * Test method for Set().
	 */
	@Test
	public void testSet() {
		LinkedList<Integer> list = new LinkedList<Integer>();

		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);

		list.set(0, 10);

		assertEquals(list.get(0), new Integer(10));
		assertEquals(list.get(1), new Integer(4));
		assertEquals(list.get(2), new Integer(3));

	}
	
	/**
	 * Tests testIterator. 
	 */
	@Test
	public void testIterator() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);
		
		assertEquals(list.size(), 3);
	
		
		for (Integer i : list){
			System.out.println(i);
		}
		
		
		ListIterator<Integer> iterator = list.listIterator(1);

		
		assertTrue(iterator.hasPrevious());
		
		assertTrue(iterator.hasPrevious());
		
		Integer i = iterator.previous();
		
		assertEquals(i, new Integer(5));
		
		
	}

}



