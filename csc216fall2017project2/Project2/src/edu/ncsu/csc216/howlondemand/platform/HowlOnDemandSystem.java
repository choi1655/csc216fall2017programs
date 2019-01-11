package edu.ncsu.csc216.howlondemand.platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;
import edu.ncsu.csc216.audioxml.xml.StationIOException;
import edu.ncsu.csc216.audioxml.xml.StationXML;
import edu.ncsu.csc216.audioxml.xml.StationsReader;
import edu.ncsu.csc216.howlondemand.model.AudioTrack;
import edu.ncsu.csc216.howlondemand.model.Station;
import edu.ncsu.csc216.howlondemand.model.TrackChunk;

/**
 * HowlOnDemandSystem provide information to the GUI and maintain collection of Stations.
 * 
 * @author cnlee2@ncsu.edu
 * @author mchoi@ncsu.edu
 */
public class HowlOnDemandSystem {
	/** BUFFER_CAPACITY */
	public static final int BUFFER_CAPACITY = 100;
	/** STATION_CAPACITY */
	public static final int STATION_CAPACITY = 9;
	/** Current Station */
	private Station currentStation;
	/** Collection of Stations */
	private ArrayList<Station> stations;
	/** Collection of TrackChunk */
	private ArrayList<TrackChunk> chunks;
	/** initial state */
	private HowlOnDemandSystemState state;
	/** Play With Buffering state */
	private final HowlOnDemandSystemState playWithBufferingState = new PlayWithBufferingState();
	/** Play Without Buffering state */
	private final HowlOnDemandSystemState playWithoutBufferingState = new PlayWithoutBufferingState();
	/** Quit State */
	private final HowlOnDemandSystemState quitState = new QuitState();
	/** Finished State */
	private final HowlOnDemandSystemState finishedState = new FinishedState();
	/** Stop With Buffering state */
	private final HowlOnDemandSystemState stopWithBufferingState = new StopWithBufferingState();
	/** Stop Without Buffering state */
	private final HowlOnDemandSystemState stopWithoutBufferingState = new StopWithoutBufferingState();
	/** Selection State */
	private final HowlOnDemandSystemState selectionState = new SelectionState();
	/** Selection State name */
	public static final String SELECTION_NAME = "Selection";
	/** PlayWithBuffering State name */
	public static final String PLAYWITHBUFFERING_NAME = "Playing with Buffering";
	/** PlayWithoutBuffering State name */
	public static final String PLAYWITHOUTBUFFERING_NAME = "Playing without Buffering";
	/** StopWithBuffering State name */
	public static final String STOPWITHBUFFERING_NAME = "Stopped with Buffering";
	/** StopWithoutBuffering State name */
	public static final String STOPWITHOUTBUFFERING_NAME = "Stopped without Buffering";
	/** Quit State name */
	public static final String QUIT_NAME = "Quit";
	/** Finished State name */
	public static final String FINISHED_NAME = "Finished";
	/** Singleton */
	private static HowlOnDemandSystem singleton; 
	
	/**
	 * Private constructor to set initial state to selection and 
	 * initialize collection of stations and chunks using ArrayList
	 */
	private HowlOnDemandSystem() {
		state = selectionState;
		stations = new ArrayList<Station>(STATION_CAPACITY);
		chunks = new ArrayList<TrackChunk>(BUFFER_CAPACITY);
	}
	
	/**
	 * Static method for singleton design to get instance
	 * @return singleton instance.
	 */
	public static HowlOnDemandSystem getInstance() {
		if (singleton == null) {
			singleton = new HowlOnDemandSystem();
		}
		
		return singleton;
	}
	
	/**
	 * Method that loads stations from a file.
	 * Throws StationIOException, MalformedTrackException.
	 * @param filename file to load
	 * @throws StationIOException thrown
	 * @throws MalformedTrackException thrown
	 */
	public void loadStationsFromFile(String filename) throws StationIOException, MalformedTrackException {
		try {
			StationsReader st = new StationsReader(filename);
			List<StationXML> stationXML = st.getStations();
			for (int i = 0; i < st.getStations().size(); i++) {
				StationXML sx = stationXML.get(i);
				stations.add(new Station(sx));
			}
		} catch (MalformedTrackException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Method that returns the current Station.
	 * @return currentStation current station
	 */
	public Station getCurrentStation() {
		return currentStation;
	}
	
	/**
	 * Method that returns current audio track.
	 * If currentStation is null, throw nullpointerException.
	 * @return AudioTrack null for now
	 */
	public AudioTrack getCurrentAudioTrack() {
		if (currentStation == null) {
			throw new NullPointerException();
		}
		return currentStation.getCurrentAudioTrack();
	}
	
	/**
	 * Method that resets the system.
	 * Set state to selection and null current station.
	 * Clear the both chunks and stations collection.
	 */
	public void reset() {
		state = selectionState;
		if(currentStation != null) {
			currentStation.reset();
			currentStation = null;
		}
		chunks.clear();
		stations.clear();
	}

	/**
	 * Method that returns formatted string value.
	 * @return String value in format
	 */
	public String toString() {
		return "HowlOnDemandSystem [state=" + state.getStateName() + ", chunks.size()=" + getChunkSize() + "]";
	}
	
	/**
	 * Method that returns the size of chunk.
	 * @return int size of the chunk array list
	 */
	public int getChunkSize() {
		return chunks.size();
	}
	
	/**
	 * Method that returns consumed track chunk.
	 * @return TrackChunk returns removed TrackChunk
	 */
	public TrackChunk consumeTrackChunk() {
		TrackChunk f;
		if (chunks.size() >= 1) {
			f = chunks.remove(0);
		} else {
			throw new IllegalArgumentException();
		}
		return f;
	}
	
	/**
	 * Method that returns true if system has next track chunk.
	 * @return true if system has next track chunk
	 */
	public boolean hasNextTrackChunk() {
		if (chunks.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that adds track chunk for system to buffer.
	 * throw IAE if buffer has no room to add.
	 * @param chunkToAdd chunk to add at the end of the arraylist
	 */
	public void addTrackChunkToBuffer(TrackChunk chunkToAdd) {
		if (!bufferHasRoom()) {
			throw new IllegalArgumentException();
		}
		chunks.add(chunkToAdd);
	}
	
	/**
	 * Method that returns true if buffer has room.
	 * @return true if buffer has room
	 */
	public boolean bufferHasRoom() {
		if (chunks.size() < BUFFER_CAPACITY) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method that returns current state of HowlOnDemandSystem.
	 * @return state current state
	 */
	public HowlOnDemandSystemState getState() {
		return state;
	}
	
	/**
	 * Method that executes the given command.
	 * @param c command to execute
	 */
	public void executeCommand(Command c) {
		state.updateState(c);
	}
	
	/**
	 * Inner class StationState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class SelectionState implements HowlOnDemandSystemState {
		
		
		/**
		 * Method that updates state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case PLAY : 
				addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
				state = playWithBufferingState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that gets name of state.
		 * @return SELECTION_NAME name of state
		 */
		@Override
		public String getStateName() {
			return SELECTION_NAME;
		}
		
	}
	
	/**
	 * Inner class PlayWithBufferingState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class PlayWithBufferingState implements HowlOnDemandSystemState {
		
		/**
		 * Method that updates state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case BUFFERING : 
				if (getCurrentAudioTrack().hasNextChunk()) {
					addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
					if (!bufferHasRoom() || !getCurrentAudioTrack().hasNextChunk()) {
						state = playWithoutBufferingState;
					}
					if (chunks.size() > 0) {
						consumeTrackChunk();
					} else if (chunks.size() == 0 && !getCurrentAudioTrack().hasNextChunk()) {
						state = finishedState;
					}
				}
				break;
			case NOT_BUFFERING : 
				if (chunks.size() > 0) {
					consumeTrackChunk();
				} else if (chunks.size() == 0 && !getCurrentAudioTrack().hasNextChunk()) {
					state = finishedState;
				}
				state = playWithoutBufferingState;
				break;
			case STOP : 
				state = stopWithBufferingState;
				break;
			case RETURN : 
				state = quitState;
				break;
			case SKIP_FORWARD : 
				if (currentStation.getShuffle()) {
					Random random = new Random();
					int randomIdx = 0;
					while (randomIdx == currentStation.getIndex()) { 
						randomIdx = random.nextInt(getCurrentStation().getPlaylist().size()); 
					}
					currentStation.setIndex(randomIdx);
					getCurrentAudioTrack().setChunkIndex(0);
				} else if (currentStation.hasNextTrack()) {
					int currentIndex = currentStation.getIndex() + 1;
					currentStation.setIndex(currentIndex);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				} else if (!currentStation.hasNextTrack() && currentStation.getRepeat()) {
					currentStation.setIndex(0);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				} else {
					state = finishedState;
				}
				break;
			case SKIP_BACKWARD : 
				if (currentStation.getIndex() >= 1) {
					int index = currentStation.getIndex() - 1;
					currentStation.setIndex(index);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				}
				if (getCurrentStation().getPlaylist().get(0) == getCurrentAudioTrack()) {
					getCurrentAudioTrack().setChunkIndex(0);
				}
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that returns the name of state.
		 * @return PLAYWITHBUFFERING_NAME name of state
		 */
		@Override
		public String getStateName() {
			return PLAYWITHBUFFERING_NAME;
		}
	
	}
	
	/**
	 * Inner class PlayWithoutBufferingState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class PlayWithoutBufferingState implements HowlOnDemandSystemState {

		/**
		 * Method that updates the state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case BUFFERING :
				if (chunks.size() < BUFFER_CAPACITY && chunks.size() > 0) {
					consumeTrackChunk();
				}
				if (getCurrentAudioTrack().hasNextChunk()) {
					addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
					state = playWithBufferingState;
				}
				break;
			case NOT_BUFFERING : 
				if (chunks.size() > 0) {
					consumeTrackChunk();
				} else if (chunks.size() == 0 && !getCurrentAudioTrack().hasNextChunk()) {
					state = finishedState;
				} else {
					state = playWithBufferingState;
				}
				break;
			case STOP : 
				state = stopWithoutBufferingState;
				break;
			case RETURN :
				state = quitState;
				break;
			case SKIP_FORWARD : 
				if (currentStation.getShuffle()) {
					Random random = new Random();
					int randomIdx = 0;
					while (randomIdx == currentStation.getIndex()) { 
						randomIdx = random.nextInt(getCurrentStation().getPlaylist().size()); 
					}
					currentStation.setIndex(randomIdx);
					getCurrentAudioTrack().setChunkIndex(0);
				} else if (currentStation.hasNextTrack()) {
					int currentIndex = currentStation.getIndex() + 1;
					currentStation.setIndex(currentIndex);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				} else if (!currentStation.hasNextTrack() && currentStation.getRepeat()) {
					currentStation.setIndex(0);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				} else {
					state = finishedState;
				}
				break;
			case SKIP_BACKWARD : 
				if (currentStation.getIndex() >= 1) {
					int index = currentStation.getIndex() - 1;
					currentStation.setIndex(index);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				}
				if (getCurrentStation().getPlaylist().get(0) == getCurrentAudioTrack()) {
					getCurrentAudioTrack().setChunkIndex(0);
				}
				break;
			default : 
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that returns the name of state.
		 * @return PLAYWITHOUTBUFFERING_NAME name of state
		 */
		@Override
		public String getStateName() {
			return PLAYWITHOUTBUFFERING_NAME;
		}
		
	}
	
	/**
	 * Inner class StopWithBufferingState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class StopWithBufferingState implements HowlOnDemandSystemState {
		

		/**
		 * Method that updates state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case BUFFERING :
				if (getCurrentAudioTrack().hasNextChunk() || chunks.size() <= BUFFER_CAPACITY) {
					addTrackChunkToBuffer(getCurrentAudioTrack().getNextChunk());
					if (chunks.size() == BUFFER_CAPACITY || !(getCurrentAudioTrack().hasNextChunk())) {
						state = stopWithoutBufferingState;
					}
				}
				else 
					state = playWithoutBufferingState;
				break;
			case NOT_BUFFERING :
				state = stopWithoutBufferingState;
				break;
			case PLAY : 
				state = playWithBufferingState;
				break;
			case RETURN :
				state = quitState;
				break;
			case SKIP_FORWARD : 
				if (currentStation.hasNextTrack()) {
					int idx = currentStation.getIndex() + 1;
					currentStation.setIndex(idx);
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						getCurrentAudioTrack().setChunkIndex(0);
						chunks.clear();
					}
					if (currentStation.getShuffle()) {
						Random random = new Random();
						int randomIdx = 0;
						while (randomIdx == currentStation.getIndex()) { 
							randomIdx = random.nextInt(getCurrentStation().getPlaylist().size()); 
						}
						currentStation.setIndex(randomIdx);
						getCurrentAudioTrack().setChunkIndex(0);
					}
				} else {
					state = finishedState;
				}
				break;
				case SKIP_BACKWARD :
					if (currentStation.getIndex() >= 1) {
						int idx = currentStation.getIndex() - 1;
						getCurrentStation().setIndex(idx);
						getCurrentAudioTrack().setChunkIndex(0);
						chunks.clear();
					}
					if (currentStation.getPlaylist().get(0) == getCurrentAudioTrack()) {
						getCurrentAudioTrack().setChunkIndex(0);
					}
				break;
			default :
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that returns the name of state.
		 * @return STOPWITHBUFFERING_NAME name of state
		 */
		@Override
		public String getStateName() {
			return STOPWITHBUFFERING_NAME;
		}
	}
	
	/**
	 * Inner class StopWithoutBufferingState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class StopWithoutBufferingState implements HowlOnDemandSystemState {
		

		/**
		 * Method that updates the state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case BUFFERING : 
				state = stopWithBufferingState;
				break;
			case NOT_BUFFERING :
				state = stopWithoutBufferingState;
				break;
			case PLAY :
				if (chunks.size() > 0) {
					consumeTrackChunk();
					state = playWithoutBufferingState;
				} else if (chunks.size() == 0 && !getCurrentAudioTrack().hasNextChunk()) {
					state = finishedState;
				}
				break;
			case RETURN :
				state = quitState;
				break;
			case SKIP_FORWARD :
				if (currentStation.hasNextTrack()) {
					int idx = currentStation.getIndex() + 1;
					currentStation.setIndex(idx);
					if (currentStation.getRepeat()) {
						currentStation.setIndex(0);
						getCurrentAudioTrack().setChunkIndex(0);
						chunks.clear();
					}
					if (currentStation.getShuffle()) {
						Random random = new Random();
						int randomIdx = 0;
						while (randomIdx == currentStation.getIndex()) { 
							randomIdx = random.nextInt(getCurrentStation().getPlaylist().size()); 
						}
						currentStation.setIndex(randomIdx);
						getCurrentAudioTrack().setChunkIndex(0);
					}
				} else {
					state = finishedState;
				}
				break;
			case SKIP_BACKWARD :
				if (currentStation.getIndex() >= 1) {
					int idx = currentStation.getIndex() - 1;
					getCurrentStation().setIndex(idx);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
				}
				if (currentStation.getPlaylist().get(0) == getCurrentAudioTrack()) {
					getCurrentAudioTrack().setChunkIndex(0);
				}
				break;
			default :
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that return name of state.
		 * @return STOPWITHOUTBUFFERING_NAME name of state
		 */
		@Override
		public String getStateName() {
			return STOPWITHOUTBUFFERING_NAME;
		}
	}
	
	/**
	 * Inner class QuitState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class QuitState implements HowlOnDemandSystemState {

		
		/**
		 * Method that updates state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case SELECT_STATION : 
				reset();
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that returns the name of state.
		 * @return QUIT_NAME name of state
		 */
		@Override
		public String getStateName() {
			return QUIT_NAME;
		}
		
	}
	
	/**
	 * Inner class FinishedState.
	 * Part of FSM.
	 * @author mchoi@ncsu.edu
	 * @author cnlee2@ncsu.edu
	 */
	private class FinishedState implements HowlOnDemandSystemState {


		/**
		 * Method that updates state.
		 * @param c command to receive
		 */
		@Override
		public void updateState(Command c) {
			switch (c.getCommand()) {
			case FINISH_TRACK : 
				if (currentStation.getShuffle()) {
					Random random = new Random();
					int idx = 0;
					while (idx == currentStation.getIndex()) {
						idx = random.nextInt(getCurrentStation().getPlaylist().size());
					}
					currentStation.setIndex(idx);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
					state = playWithBufferingState;
				} else if (currentStation.hasNextTrack()) {
					int idx = currentStation.getIndex() + 1;
					currentStation.setIndex(idx);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
					state = playWithBufferingState;
				} else if (!currentStation.hasNextTrack() && currentStation.getRepeat()) {
					currentStation.setIndex(0);
					getCurrentAudioTrack().setChunkIndex(0);
					chunks.clear();
					state = playWithBufferingState;
				}
				break;
			case FINISH_STATION : 
				state = finishedState;
				break;
			case RETURN :
				reset();
				state = quitState;
				break;
			default:
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Method that returns the name of state.
		 * @return FINISHED_NAME name of state
		 */
		@Override
		public String getStateName() {
			return FINISHED_NAME;
		}
		
	}

	/**
	 * Returns arraylist of stations.
	 * @return stations returns stations collection
	 */
	public ArrayList<Station> getStations() {
		return stations;
	}

	/**
	 * The method that loads stations.
	 * Set given station to currentStation.
	 * Throw IllegalArgumentException if station is null.
	 * @param station station to load
	 */
	public void loadStation(Station station) {
		if (station == null) {
			throw new IllegalArgumentException();
		}
		currentStation = station;
		currentStation.setIndex(0);
	}
}
