package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests the LinkedAbstractList class 
 * @author alcherni
 * @author kaguillo
 * @author jballie
 *
 */
public class LinkedAbstractListTest {

	/**
	 * tests the size() method of LinkedAbstractList
	 */
	@Test
	public void testSize() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(0);

		assertEquals(list.size(), 0);
	}

	/**
	 * tests the getInt() method of of LinkedAbstractList
	 */
	@Test
	public void testGetInt() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(1);

		list.add(0, 5);

		try {
			list.get(45);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), null);
		}
	}
	
	/**
	 * Test method for Add().
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(8);

		list.add(0, 5);
		list.add(0, 4);
		list.add(0, 3);
		list.add(0, 8);
		list.add(0, 15);
		list.add(0, 14);
		list.add(0, 13);
		list.add(0, 18);
		
		try {
			list.add(0, 52);
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "No more items can be added to the list. At full capacity");
		}
		


		assertEquals(list.get(0), new Integer(18));
		assertEquals(list.get(1), new Integer(13));
		assertEquals(list.get(2), new Integer(14));

		LinkedAbstractList<String> list2 = new LinkedAbstractList<String>(5);
		list2.add(0, "Anna");
		list2.add(1, "Kamai");
		list2.add(1, "Jon");

		assertEquals(list2.get(0), "Anna");
		assertEquals(list2.get(1), "Jon");
		assertEquals(list2.get(2), "Kamai");

		
		try {
			list2.add(60, "test");
			fail();
		}
		catch(IndexOutOfBoundsException e)
		{
			assertEquals(e.getMessage(), "Linked list not big enough");
		}
		
		
		try {
			list2.add(3, null);
			fail();
		}
		catch(NullPointerException e)
		{
			assertEquals(e.getMessage(), "Can't add null!");
		}




	}
	/**
	 * Test method for Remove().
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(3);

		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);

		list.remove(1);

		assertEquals(list.get(0), new Integer(5));
		assertEquals(list.get(1), new Integer(3));
		
		list.remove(1);

	}

	/**
	 * Test method for Set().
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(3);

		list.add(0, 5);
		list.add(1, 4);
		list.add(2, 3);

		list.set(0, 10);

		assertEquals(list.get(0), new Integer(10));
		assertEquals(list.get(1), new Integer(4));
		assertEquals(list.get(2), new Integer(3));

	}

}




