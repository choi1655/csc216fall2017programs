package edu.ncsu.csc216.howlondemand.model;

import java.util.ArrayList;

import edu.ncsu.csc216.audioxml.xml.AudioTrackXML;
import edu.ncsu.csc216.audioxml.xml.MalformedTrackException;

/**
 * represents the audio track artist, current chuckIndex, and chunks collection of 
 * TrackChunks, which is required every time HowlOnDemandSystem requests the next track 
 * chunk to load into the buffer. You should implement and test the AudioTrack class 
 * before implementing the Station class since Station has collection AudioTracks.
 * @author mchoi@ncsu.edu
 * @author cnlee2@ncsu.edu
 * @version 11032017
 */
public class AudioTrack extends Multimedia {
	private int chunkIndex;
	private String artist;
	private ArrayList<TrackChunk> chunks;
	
	/**
	 * Assigns values from the parameters and initialize an empty collection of TrackChunks.
	 * @param id chunk index of this audio track
	 * @param title title of the audio track
	 * @param artist artist of the audio track
	 */
	public AudioTrack(int id, String title, String artist) {
		super(id, title);
		chunks = new ArrayList<TrackChunk>();
		this.setArtist(artist);
		this.setTitle(title);
		this.setChunkIndex(0); 
	}
	
	/**
	 * Pull values from the AudioTrackXML parameter, including any 
	 * TrackChunks supplied from AudioTrackXML's getTrackChunks method.
	 * @param at AudioTrackXML to take in
	 * @throws MalformedTrackException thrown if track is malformed
	 */
	public AudioTrack(AudioTrackXML at) throws MalformedTrackException {
		this(at.getId(), at.getTitle(), at.getArtist());
		for (int i = 0; i < at.getTrackChunks().getChunk().size(); i++) {
			TrackChunk t = new TrackChunk(at.getTrackChunks().getChunk().get(i));
			chunks.add(t);
		}
	}
	
	/**
	 * Getter for artist field.
	 * @return artist returns artist
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Setter for the artist field.
	 * @param newArtist artist to set
	 */
	public void setArtist(String newArtist) {
		if (newArtist == null) {
			throw new IllegalArgumentException("Artist cannot be null");
		}
		artist = newArtist;
	}

	/**
	 * Setter for chunkIndex field.
	 * @param chunkIndex chunk index to set
	 */
	public void setChunkIndex(int chunkIndex) {
		if (chunkIndex < 0 || chunkIndex > chunks.size()) {
			throw new IllegalArgumentException("Chunk index cannot be negative or larger than size of TrackChunk collection");
		}
		this.chunkIndex = chunkIndex;
	}
	
	/**
	 * Getter for chunk index field.
	 * @return chunkIndex index of the chunk
	 */
	public int getChunkIndex() {
		return chunkIndex;
	}
	
	/**
	 * Returns the size of the track chunk.
	 * @return int size of the track chunk
	 */
	public int getTrackChunkSize() {
		return chunks.size();
	}
	
	/**
	 * It checks to see if the current index is less than the size of the TrackChunk collection,
	 * if not, it should throw an IAE. If the index is smaller, then the method should 
	 * return the TrackChunk at that index, and then increment the index.
	 * @return TrackChunk returns next TrackChunk
	 */
	@Override
	public TrackChunk getNextChunk() {
		if (!(chunkIndex < chunks.size())) {
			throw new IllegalArgumentException();
		}
		int tempIndex = chunkIndex;
		chunkIndex++;
		return chunks.get(tempIndex);
	}

	/**
	 * Returns true If the AudioTrack's index is less than the size of the TrackChunk
	 * collection.
	 * @return true if track has next chunk
	 */
	@Override
	public boolean hasNextChunk() {
		if (chunkIndex < chunks.size()) {
			return true;
		} 
		return false;
	}

	/**
	 * Adds the TrackChunk received by the parameter.
	 * @param t chunk to add
	 */
	@Override
	public void addChunk(TrackChunk t) throws MalformedTrackException  {
		if (t == null) {
			throw new MalformedTrackException();
		}
		chunks.add(t);
	}

	/**
	 * Method that returns the artist's name.
	 * @return formatted String value
	 */
	@Override
	public String toString() {
		return "AudioTrack [id=" + getId() + ", title=" + getTitle() + ", artist=" + getArtist() + "]" ;
	}
}
