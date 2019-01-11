package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tester for the LinkedStack Class.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class LinkedStackTest {

	/**
	 * Tester for the push method.
	 */
	@Test
	public void testPush() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		stack.push(5);
		stack.push(10);
		stack.push(15);
		stack.push(20);

		assertEquals(4, stack.size());
		assertEquals(new Integer(20), stack.pop());
		assertEquals(3, stack.size());
		assertEquals(new Integer(15), stack.pop());
		assertEquals(2, stack.size());
		assertEquals(new Integer(10), stack.pop());
		assertEquals(1, stack.size());
		assertEquals(new Integer(5), stack.pop());
		assertEquals(0, stack.size());
	}

	/**
	 * Tester for the pop method.
	 */
	@Test
	public void testPop() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		stack.push(5);
		stack.push(10);
		stack.push(15);
		stack.push(20);

		assertEquals(4, stack.size());
		assertEquals(new Integer(20), stack.pop());
		assertEquals(3, stack.size());
		assertEquals(new Integer(15), stack.pop());
		assertEquals(2, stack.size());
		assertEquals(new Integer(10), stack.pop());
		assertEquals(1, stack.size());
		assertEquals(new Integer(5), stack.pop());
		assertEquals(0, stack.size());

		try {
			stack.pop();
		} catch (EmptyStackException e) {
			assertEquals(e.getMessage(), null);
		}

	}

	/**
	 * Tester for the isEmpty method.
	 */
	@Test
	public void testIsEmpty() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(1);
		assertTrue(stack.isEmpty());
		stack.push(10);
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Tester for the size method.
	 */
	@Test
	public void testSize() {
		LinkedStack<Integer> stack = new LinkedStack<Integer>(10);
		assertEquals(0, stack.size());

		stack.push(5);
		stack.push(10);
		stack.push(15);
		stack.push(20);

		assertEquals(4, stack.size());
		assertEquals(new Integer(20), stack.pop());
		assertEquals(3, stack.size());
		assertEquals(new Integer(15), stack.pop());
		assertEquals(2, stack.size());
		assertEquals(new Integer(10), stack.pop());
		assertEquals(1, stack.size());
		assertEquals(new Integer(5), stack.pop());
		assertEquals(0, stack.size());
	}

	/**
	 * Tester for the setCapacity method.
	 */
	@Test
	public void testSetCapacity() {
		LinkedStack<Integer> stack;
		try {
			stack = new LinkedStack<Integer>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		stack = new LinkedStack<Integer>(10);
		stack.push(5);
		stack.push(10);
		stack.push(15);
		stack.push(20);

		try {
			stack.setCapacity(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

	}

}
