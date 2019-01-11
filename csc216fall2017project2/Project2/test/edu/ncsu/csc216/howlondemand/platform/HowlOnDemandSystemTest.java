/**
 * 
 */
package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;
import edu.ncsu.csc216.howlondemand.model.AudioTrack;
import edu.ncsu.csc216.howlondemand.model.Station;
import edu.ncsu.csc216.howlondemand.model.TrackChunk;
import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * JUnit test cases for HowlOnDemandSystem.java
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 */
public class HowlOnDemandSystemTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#loadStationsFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadStationsFromFile() {
		try {
			HowlOnDemandSystem.getInstance().loadStationsFromFile("test-files/StationList.xml");
		} catch (StationIOException e) {
			e.printStackTrace();
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
		assertEquals(9, HowlOnDemandSystem.getInstance().getStations().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getCurrentStation()}.
	 */
	@Test
	public void testGetCurrentStation() {
		Station s = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s);
		assertEquals(s, HowlOnDemandSystem.getInstance().getCurrentStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getCurrentAudioTrack()}.
	 */
	@Test
	public void testGetCurrentAudioTrack() {
		Station s = new Station(1, "ABC", 0);
		AudioTrack a = new AudioTrack(1, "ABC", "DEF");
		s.addAudioTrack(a);
		HowlOnDemandSystem.getInstance().loadStation(s);
		assertEquals(s.getCurrentAudioTrack(), HowlOnDemandSystem.getInstance().getCurrentAudioTrack());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#reset()}.
	 */
	@Test
	public void testReset() {
		Station s = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s);
		HowlOnDemandSystem.getInstance().reset();
		assertEquals(null, HowlOnDemandSystem.getInstance().getCurrentStation());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#toString()}.
	 */
	@Test
	public void testToString() {
		assertEquals("HowlOnDemandSystem [state=Selection, chunks.size()=0]", HowlOnDemandSystem.getInstance().toString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getChunkSize()}.
	 */
	@Test
	public void testGetChunkSize() {
		Station s = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s);
		try {
			TrackChunk c = new TrackChunk();
			HowlOnDemandSystem.getInstance().addTrackChunkToBuffer(c);
			assertEquals(1, HowlOnDemandSystem.getInstance().getChunkSize());
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#consumeTrackChunk()}.
	 */
	@Test
	public void testConsumeTrackChunk() {
		Station s2 = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s2);
		HowlOnDemandSystem.getInstance().consumeTrackChunk();
		HowlOnDemandSystem.getInstance().consumeTrackChunk();
		HowlOnDemandSystem.getInstance().consumeTrackChunk();
		HowlOnDemandSystem.getInstance().consumeTrackChunk();
		assertEquals(96, HowlOnDemandSystem.getInstance().getChunkSize());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#hasNextTrackChunk()}.
	 */
	@Test
	public void testHasNextTrackChunk() {
		Station s2 = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s2);
		try {
			TrackChunk c = new TrackChunk();
			HowlOnDemandSystem.getInstance().addTrackChunkToBuffer(c);
			assertTrue(HowlOnDemandSystem.getInstance().hasNextTrackChunk());
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#bufferHasRoom()}.
	 */
	@Test
	public void testBufferHasRoom() {
		Station s = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s);
		try {
			TrackChunk c = new TrackChunk();
			HowlOnDemandSystem.getInstance().addTrackChunkToBuffer(c);
			assertTrue(HowlOnDemandSystem.getInstance().bufferHasRoom());
			for (int i = 0; i < 98; i++) {
				HowlOnDemandSystem.getInstance().addTrackChunkToBuffer(c);
			}
			assertFalse(HowlOnDemandSystem.getInstance().bufferHasRoom());
		} catch (MalformedTrackException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#getState()}.
	 */
	@Test
	public void testGetState() {
		Station s = new Station(1, "ABC", 0);
		HowlOnDemandSystem.getInstance().loadStation(s);
		assertEquals("Selection", HowlOnDemandSystem.getInstance().getState().getStateName());

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.howlondemand.platform.HowlOnDemandSystem#executeCommand(edu.ncsu.csc216.howlondemand.platform.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		Command c = new Command(CommandValue.PLAY);
		Station s = new Station(1, "ABC", 0);
		AudioTrack a = new AudioTrack(1, "ABC", "DEF");
		TrackChunk e;
		TrackChunk e2;
		try {
			e = new TrackChunk();
			e2 = new TrackChunk();
			a.addChunk(e);
			a.addChunk(e2);
		} catch (MalformedTrackException e1) {
			e1.printStackTrace();
		}
		
		//testing inner classes
		s.addAudioTrack(a);
		HowlOnDemandSystem.getInstance().loadStation(s);
		HowlOnDemandSystem.getInstance().executeCommand(c);
		assertEquals(HowlOnDemandSystem.PLAYWITHBUFFERING_NAME, HowlOnDemandSystem.getInstance().getState().getStateName());
		HowlOnDemandSystem.getInstance().getStations().add(s);
		HowlOnDemandSystem.getInstance().getStations().get(0);
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.STOP));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		s.addAudioTrack(a);
		s.toggleRepeat();
		s.toggleShuffle();
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_BACKWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.PLAY));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_BACKWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.PLAY));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_BACKWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_BACKWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		s.toggleShuffle();
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		try {
			HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.FINISH_STATION));
		} catch(UnsupportedOperationException e23) {
			e23.printStackTrace();
		}
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		s.setIndex(3);
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.STOP));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		s.toggleShuffle();
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.FINISH_TRACK));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.STOP));
		s.setIndex(3);
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		s.toggleShuffle();
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.FINISH_STATION));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.FINISH_TRACK));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.NOT_BUFFERING));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.STOP));
		s.setIndex(3);
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SKIP_FORWARD));
		try {
			HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SELECT_STATION));
		} catch(UnsupportedOperationException e23) {
			e23.printStackTrace();
		}
		assertEquals(HowlOnDemandSystem.FINISHED_NAME, HowlOnDemandSystem.getInstance().getState().getStateName());
		HowlOnDemandSystem.getInstance().getCurrentStation().addAudioTrack(a);
		s.setIndex(0);
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.FINISH_TRACK));
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.RETURN));
		try {
			HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.BUFFERING));
		} catch(UnsupportedOperationException e23) {
			e23.printStackTrace();
		}
		HowlOnDemandSystem.getInstance().executeCommand(new Command(CommandValue.SELECT_STATION));
		
	}
}
