/**
 * 
 */
package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;

/**
 * JUnit test cases for TrackChunk.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 10312017
 */
public class TrackChunkTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.TrackChunk#TrackChunk()}.
	 * @throws MalformedTrackException 
	 */
	@Test
	public void testTrackChunk() throws MalformedTrackException {
		TrackChunk t = new TrackChunk();
		assertEquals("00000000", t.getChunk());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.TrackChunk#TrackChunk(java.lang.String)}.
	 */
	@Test
	public void testTrackChunkString() throws MalformedTrackException {
		TrackChunk t = new TrackChunk("19763811");
		assertEquals("19763811", t.getChunk());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.TrackChunk#getChunk()}.
	 */
	@Test
	public void testGetChunk() throws MalformedTrackException {
		TrackChunk t = new TrackChunk("19763811");
		assertEquals("19763811", t.getChunk());
		try {
			t = new TrackChunk(null);
			fail();
		} catch(MalformedTrackException e) {
			e.getStackTrace();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.TrackChunk#setChunk(java.lang.String)}.
	 */
	@Test
	public void testSetChunk() throws MalformedTrackException {
		TrackChunk t = new TrackChunk();
		assertEquals("00000000", t.getChunk());
		t.setChunk("81736389");
		assertEquals("81736389", t.getChunk());
		try {
			t.setChunk(null);
			fail();
		} catch (MalformedTrackException e) {
			e.getStackTrace();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.TrackChunk#validChunk(java.lang.String)}.
	 */
	@Test
	public void testValidChunk() throws MalformedTrackException {
		TrackChunk t = new TrackChunk("1783ADFF");
		assertEquals("1783ADFF", t.getChunk());
		assertTrue(t.validChunk("1783ADFF"));
		assertFalse(t.validChunk(null));
		assertFalse(t.validChunk("1983178"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.TrackChunk#toString()}.
	 */
	@Test
	public void testToString() throws MalformedTrackException {
		TrackChunk t = new TrackChunk("1378ADFF");
		assertEquals("TrackChunk [chunk=1378ADFF]", t.toString());
	}

}
