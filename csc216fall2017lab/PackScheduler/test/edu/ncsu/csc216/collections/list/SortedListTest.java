package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;
 /**
  * Tests Sorted Lists in PackScheduler
  * @author Anna chernikov
  * @author Hamzah Chaudry
  * @author Dominic Brown
  *
  */
public class SortedListTest {

	
	/**
	 * Tests making sorted Lists
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertFalse(list.contains("apple"));
		
		int stringsToAdd = 11;
		for (int i = 1; i <= stringsToAdd; i++) {
			list.add("test " + i);
		}
		
		assertEquals(list.size(), stringsToAdd);
		
	}


	/**
	 * tests adding to a sorted list
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//Testing adding to the front, middle and back of the list
		list.add("aardvark");
		assertEquals(2, list.size());
		assertEquals("aardvark", list.get(0));
		
		list.add("apple");
		assertEquals(3, list.size());
		assertEquals("apple", list.get(1));
		assertEquals("aardvark", list.get(0));
		
		list.add("zebra");
		assertEquals(4, list.size());
		assertEquals("zebra", list.get(3));
		
		//Testing adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		//Testing adding a duplicate element
		try {
			list.add("banana");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
	}
	

	/**
	 * Tests getting an object from a sorted list
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//Testing getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Testing getting an element at an index < 0
		list.add("banana");
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		
		//Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
	}
	

	/**
	 * tests removing an object from a sorted list
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Testing removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		
		//Testing removing an element at an index < 0
		list.add("banana");
		list.add("apple");
		list.add("aardvark");
		list.add("zebra");
		
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Testing removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		
		//Testing removing a middle element
		list.remove(1);
		assertEquals(3, list.size());
		
		//Testing removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		
		//Testing removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		
		//Testing removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	

	/**
	 * tests the indexOf() method of a sorted list
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Testing indexOf on an empty list
		int nonExsistentIndex = list.indexOf("banana");
		assertEquals(-1, nonExsistentIndex);
		
		//Testing various calls to indexOf for elements in the list
		//and not in the list
		list.add("banana");
		list.add("a real human being");
		
		int bananaIndex = list.indexOf("banana");
		int personIndex = list.indexOf("a real human being");
		nonExsistentIndex = list.indexOf("my love life");
		
		assertEquals(0, personIndex);
		assertEquals(1, bananaIndex);
		assertEquals(-1, nonExsistentIndex);
		
		//Testing checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			//do nothing
		}
		
	}
	

	/**
	 * tests clearing a SortedList
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();


		list.add("banana");
		list.add("a real human being");		

		list.clear();
		assertEquals(0, list.size());

	}

	/**
	 * tests the IsEmpty() method of a sorted list
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		

		assertTrue(list.isEmpty());

		list.add("banana");		

		assertFalse(list.isEmpty());
	}

	/**
	 * tests the contains() method of a sorted list
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		

		assertTrue(list.isEmpty());

		list.add("banana");
		list.add("a real human being");			

		assertTrue(list.contains("banana"));
		assertFalse(list.contains("Kumquat"));
		assertTrue(list.contains("a real human being"));
		assertFalse(list.contains("Fig Newtons"));
	}
	

	/**
	 * tests the Equals() method of a sorted list
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		

		list1.add("kumquat");
		list2.add("kumquat");
		list3.add("Fig Newton");	

		assertTrue(list1.equals(list2));
		assertFalse(list2.equals(list3));
		assertFalse(list1.equals(list3));
	}

	/**
	 * tests the HashCode() method of a sortedList
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		list1.add("kumquat");
		list2.add("kumquat");
		list3.add("Fig Newton");	

       assertEquals(list1.hashCode(), list2.hashCode());
       assertNotEquals(list1.hashCode(), list3.hashCode());
       assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}
 