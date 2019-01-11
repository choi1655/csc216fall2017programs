package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Tester for the ArrayStack Class.
 * 
 * @author jballie
 * @author mchoi
 *
 */
public class ArrayStackTest {

	/**
	 * Tester for the method push().
	 */
	@Test
	public void testPush() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
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
	 * Tester for the method pop().
	 */
	@Test
	public void testPop() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
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
	 * Tester for the method isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(1);
		assertTrue(stack.isEmpty());
		stack.push(10);
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());

	}

	/**
	 * Tester for the method size().
	 */
	@Test
	public void testSize() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(10);
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
	 * Tester for the method setCapacity().
	 */
	@Test
	public void testSetCapacity() {
		ArrayStack<Integer> stack;
		try {
			stack = new ArrayStack<Integer>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		stack = new ArrayStack<Integer>(10);
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
