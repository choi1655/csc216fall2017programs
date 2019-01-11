/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tester for the ArrayQueue Class.
 * 
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 */
public class ArrayQueueTest {

	/**
	 * Test method for enqueue().
	 */
	@Test
	public void testEnqueue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		queue.enqueue(5);
		queue.enqueue(10);
		queue.enqueue(15);
		queue.enqueue(20);

		assertEquals(4, queue.size());
		assertEquals(new Integer(5), queue.dequeue());
		assertEquals(3, queue.size());
		assertEquals(new Integer(10), queue.dequeue());
		assertEquals(2, queue.size());
		assertEquals(new Integer(15), queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(new Integer(20), queue.dequeue());
		assertEquals(0, queue.size());
	}

	/**
	 * Test method for dequeue().
	 */
	@Test
	public void testDequeue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		queue.enqueue(5);
		queue.enqueue(10);
		queue.enqueue(15);
		queue.enqueue(20);

		assertEquals(4, queue.size());
		assertEquals(new Integer(5), queue.dequeue());
		assertEquals(3, queue.size());
		assertEquals(new Integer(10), queue.dequeue());
		assertEquals(2, queue.size());
		assertEquals(new Integer(15), queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(new Integer(20), queue.dequeue());
		assertEquals(0, queue.size());

		try {
			queue.dequeue();
		} catch (NoSuchElementException e) {
			assertEquals(e.getMessage(), null);
		}
	}

	/**
	 * Test method for isEmpty().
	 */
	@Test
	public void testIsEmpty() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(1);
		assertTrue(queue.isEmpty());
		queue.enqueue(10);
		assertFalse(queue.isEmpty());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}

	/**
	 * Test method for size().
	 */
	@Test
	public void testSize() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);
		queue.enqueue(5);
		queue.enqueue(10);
		queue.enqueue(15);
		queue.enqueue(20);

		assertEquals(4, queue.size());
		assertEquals(new Integer(5), queue.dequeue());
		assertEquals(3, queue.size());
		assertEquals(new Integer(10), queue.dequeue());
		assertEquals(2, queue.size());
		assertEquals(new Integer(15), queue.dequeue());
		assertEquals(1, queue.size());
		assertEquals(new Integer(20), queue.dequeue());
		assertEquals(0, queue.size());
	}

	/**
	 * Test method for setCapacity().
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<Integer> queue;
		try {
			queue = new ArrayQueue<Integer>(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}

		queue = new ArrayQueue<Integer>(10);
		queue.enqueue(5);
		queue.enqueue(10);
		queue.enqueue(15);
		queue.enqueue(20);

		try {
			queue.setCapacity(2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), null);
		}
	}

}
