/**
 * 
 */
package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.TrackChunkXML;

/**
 * JUnit test cases for AudioTrack.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 10312017
 */
public class AudioTrackTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.AudioTrack#getNextChunk()}.
	 */
	@Test
	public void testGetNextChunk() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		AudioTrack t = new AudioTrack(id, title, artist);
		try {
			TrackChunk x = new TrackChunk();
			t.addChunk(x);
			assertEquals(x, t.getNextChunk());
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.AudioTrack#hasNextChunk()}.
	 */
	@Test
	public void testHasNextChunk() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		AudioTrack t = new AudioTrack(id, title, artist);
		
		try {
			TrackChunk x = new TrackChunk();
			t.addChunk(x);
			assertTrue(t.hasNextChunk());
			t.setChunkIndex(1);
			assertFalse(t.hasNextChunk());
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.AudioTrack#toString()}.
	 */
	@Test
	public void testToString() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		AudioTrack t = new AudioTrack(id, title, artist);
		assertEquals("AudioTrack [id=1, title=FGH, artist=ABCD]", t.toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.AudioTrack#AudioTrack(int, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAudioTrackIntStringString() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		AudioTrack t = new AudioTrack(id, title, artist);
		assertEquals("ABCD", t.getArtist());
		assertEquals("FGH", t.getTitle());
		assertEquals(1, t.getId());
		assertEquals(0, t.getChunkIndex());
		t.setChunkIndex(0);
		assertEquals(0, t.getChunkIndex());

			
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.AudioTrack#AudioTrack(edu.ncsu.csc216.audioxml.xml.AudioTrackXML)}.
	 */
	@Test
	public void testAudioTrackAudioTrackXML() {
		TrackChunkXML x = new TrackChunkXML();
		x.getChunk().add(0, "e");
		assertEquals(1, x.getChunk().size());
		AudioTrackXML a = new AudioTrackXML();
		a.setArtist("ABCD");
		a.setId(1);
		a.setTitle("FGH");
		a.setTrackChunks(x);
		try {
			AudioTrack t2 = new AudioTrack(a);
			assertEquals("ABCD", t2.getArtist());
			assertEquals("FGH", t2.getTitle());
			assertEquals(1, t2.getId());
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.AudioTrack#getTrackChunkSize()}.
	 */
	@Test
	public void testGetTrackChunkSize() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		AudioTrack t = new AudioTrack(id, title, artist);
		
		try {
			TrackChunk x = new TrackChunk();
			t.addChunk(x);
			assertEquals(1, t.getTrackChunkSize());

		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
	}

}
