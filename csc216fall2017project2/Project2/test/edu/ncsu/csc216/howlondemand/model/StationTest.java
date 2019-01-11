/**
 * 
 */
package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.AudioTrackList;
import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationXML;
import edu.ncsu.csc216.audioxml.xml.TrackChunkXML;

/**
 * JUnit test cases for Station.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 10312017
 */
public class StationTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#Station(int, int, int)}.
	 */
	@Test
	public void testStationIntIntInt() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getPlaylist().size());
		assertEquals(3, station.getId());
		assertEquals("Title", station.getTitle());
		assertEquals(4, station.getColor());
		assertFalse(station.getShuffle());
		assertFalse(station.getRepeat());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#Station(edu.ncsu.csc216.audioxml.xml.StationXML)}.
	 * @throws MalformedTrackException 
	 */
	@Test
	public void testStationStationXML() throws MalformedTrackException {
		StationXML s = new StationXML();
		AudioTrackList a = new AudioTrackList();
		AudioTrackXML b = new AudioTrackXML();
		TrackChunkXML x = new TrackChunkXML();
		x.getChunk().add(0, "00000000");
		b.setTrackChunks(x);
		b.setArtist("A");
		b.setId(1);
		b.setTitle("AB");
		a.getAudioTrackXML().add(0, b);
		s.setColor(1);
		s.setId(1);
		s.setRepeat(false);
		s.setShuffle(false);
		s.setTitle("title");
		s.setAudioTracks(a);
		Station station = new Station(s);
		assertEquals(station.getId(), s.getId());
		assertEquals(station.getTitle(), s.getTitle());
		assertEquals(station.getColor(), s.getColor());
		assertEquals(station.getShuffle(), s.isShuffle());
		assertEquals(station.getRepeat(), s.isRepeat());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getId()}.
	 */
	@Test
	public void testGetId() {
		Station station = new Station(3, "Title", 4);
		assertEquals(3, station.getId()); 
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#setId(int)}.
	 */
	@Test
	public void testSetId() {
		Station station = new Station(3, "Title", 4);
		station.setId(5);
		assertEquals(5, station.getId()); 
		station.setId(0);
		assertEquals(0, station.getId()); 
		station.setId(100);
		assertEquals(100, station.getId()); 
		try {
			station.setId(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be negative.", e.getMessage());
		}
		try {
			station.setId(-10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("ID cannot be negative.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		Station station = new Station(3, "Title", 4);
		assertEquals("Title", station.getTitle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		Station station = new Station(3, "Title", 4);
		station.setTitle("Test");
		assertEquals("Test", station.getTitle());
		station.setTitle("John");
		assertEquals("John", station.getTitle());
		station.setTitle("Three Degrees");
		assertEquals("Three Degrees", station.getTitle());
		try {
			station.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Title cannot be null.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getRepeat()}.
	 */
	@Test
	public void testGetRepeat() {
		Station station = new Station(3, "Title", 4);
		assertFalse(station.getRepeat());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#toggleRepeat()}.
	 */
	@Test
	public void testToggleRepeat() {
		Station station = new Station(3, "Title", 4);
		assertFalse(station.getRepeat());
		station.toggleRepeat();
		assertTrue(station.getRepeat());
		station.toggleRepeat();
		assertFalse(station.getRepeat());
		station.toggleRepeat();
		station.toggleRepeat();
		assertFalse(station.getRepeat());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getShuffle()}.
	 */
	@Test
	public void testGetShuffle() {
		Station station = new Station(3, "Title", 4);
		assertFalse(station.getShuffle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#toggleShuffle()}.
	 */
	@Test
	public void testToggleShuffle() {
		Station station = new Station(3, "Title", 4);
		assertFalse(station.getShuffle());
		station.toggleShuffle();
		assertTrue(station.getShuffle());
		station.toggleShuffle();
		assertFalse(station.getShuffle());
		station.toggleShuffle();
		station.toggleShuffle();
		assertFalse(station.getShuffle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getPlaylist()}.
	 */
	@Test
	public void testGetPlaylist() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getPlaylist().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#addAudioTrack(edu.ncsu.csc216.howlondemand.model.AudioTrack)}.
	 */
	@Test
	public void testAddAudioTrack() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getPlaylist().size());
		AudioTrack a = new AudioTrack(4, "TitleA", "ArtistA");
		AudioTrack b = new AudioTrack(1, "TitleB", "ArtistB");
		AudioTrack c = new AudioTrack(6, "TitleC", "ArtistC");
		station.addAudioTrack(a);
		assertEquals(1, station.getPlaylist().size());
		station.addAudioTrack(b);
		assertEquals(2, station.getPlaylist().size());
		station.addAudioTrack(c);
		assertEquals(3, station.getPlaylist().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#hasNextTrack()}.
	 */
	@Test
	public void testHasNextTrack() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getPlaylist().size());
		AudioTrack a = new AudioTrack(4, "TitleA", "ArtistA");
		AudioTrack b = new AudioTrack(1, "TitleB", "ArtistB");
		AudioTrack c = new AudioTrack(6, "TitleC", "ArtistC");
		station.addAudioTrack(a);
		station.addAudioTrack(b);
		station.addAudioTrack(c);
		station.setIndex(2);
		assertFalse(station.hasNextTrack());
		station.setIndex(0);
		assertTrue(station.hasNextTrack());
		station.setIndex(3);
		assertFalse(station.hasNextTrack());
		station.setIndex(5);
		assertFalse(station.hasNextTrack());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getColor()}.
	 */
	@Test
	public void testGetColor() {
		Station station = new Station(3, "Title", 4);
		assertEquals(4, station.getColor());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#setColor(int)}.
	 */
	@Test
	public void testSetColor() {
		Station station = new Station(3, "Title", 4);
		assertEquals(4, station.getColor());
		station.setColor(3);
		assertEquals(3, station.getColor());
		station.setColor(5);
		assertEquals(5, station.getColor());
		station.setColor(0);
		assertEquals(0, station.getColor());
		try {
			station.setColor(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Color should be between 0 and 5 inclusive.", e.getMessage());
		}
		try {
			station.setColor(6); 
			fail(); 
		} catch (IllegalArgumentException e) {
			assertEquals("Color should be between 0 and 5 inclusive.", e.getMessage()); 
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getIndex()}.
	 */
	@Test
	public void testGetIndex() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getIndex());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#setIndex(int)}.
	 */
	@Test
	public void testSetIndex() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getIndex());
		station.setIndex(2);
		assertEquals(2, station.getIndex());
		try {
			station.setIndex(-1); 
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Index cannot be negative.", e.getMessage());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#getCurrentAudioTrack()}.
	 */
	@Test
	public void testGetCurrentAudioTrack() {
		Station station = new Station(3, "Title", 4);
		AudioTrack a = new AudioTrack(4, "TitleA", "ArtistA");
		AudioTrack b = new AudioTrack(1, "TitleB", "ArtistB");
		AudioTrack c = new AudioTrack(6, "TitleC", "ArtistC");
		station.addAudioTrack(a);
		station.addAudioTrack(b);
		station.addAudioTrack(c);
		assertEquals(a, station.getCurrentAudioTrack());
		station.setIndex(1);
		assertEquals(b, station.getCurrentAudioTrack());
		station.setIndex(2);
		assertEquals(c, station.getCurrentAudioTrack());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#reset()}.
	 */
	@Test
	public void testReset() {
		Station station = new Station(3, "Title", 4);
		assertEquals(0, station.getIndex());
		assertFalse(station.getRepeat()); 
		assertFalse(station.getShuffle());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Station#toString()}.
	 */
	@Test
	public void testToString() {
		Station station = new Station(3, "Metal", 4);
		assertEquals("Station \"Metal\"", station.toString());
	}

}
