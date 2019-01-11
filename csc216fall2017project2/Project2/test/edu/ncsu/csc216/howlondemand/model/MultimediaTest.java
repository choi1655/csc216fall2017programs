/**
 * 
 */
package edu.ncsu.csc216.howlondemand.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test for Multimedia.java
 * @author cnlee2@ncsu.edu
 * @author mchoi@ncsu.edu
 * @version 11022017
 */
public class MultimediaTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Multimedia#setId(int)}.
	 */
	@Test
	public void testSetId() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		Multimedia m = new AudioTrack(id, title, artist);
		m.setId(2);
		assertEquals(2, m.getId());
		try {
			m.setId(-1);
			fail();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.model.Multimedia#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		int id = 1;
		String title = "FGH";
		String artist = "ABCD";
		Multimedia m = new AudioTrack(id, title, artist);
		m.setTitle("ABC");
		assertEquals("ABC", m.getTitle());
		try {
			String a = null;
			m.setTitle(a);
			fail();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

}
