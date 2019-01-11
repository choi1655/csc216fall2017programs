package edu.ncsu.csc216.get_outdoors;

//import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
//import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import edu.ncsu.csc216.get_outdoors.enums.Difficulty;
import edu.ncsu.csc216.get_outdoors.model.*;
import edu.ncsu.csc216.get_outdoors.util.SortedArrayList;

/**
 * The main class for the GetOutdoors tools. Holds references to the top-level
 * data structures and acts as the controller between the model and the GUI
 * presentation view.
 * 
 * @author Jessica Young Schmidt
 * @author mchoi@ncsu.edu
 * @author jballie@ncsu.edu
 */
public class GetOutdoorsManager extends Observable implements Observer {
	/** Increment for increasing the capacity of the array of TrailList */
	private static final int RESIZE = 3;

	// States for file processing
	/** Start State */
	private final State startState = new StartState();
	/** State when processing activities */
	private final State activityState = new ActivityState();
	/** State when processing parks */
	public final State parkState = new ParkState();
	/** State when processing trails */
	public final State trailState = new TrailState();
	/** Heading for activities in files */
	private static final String ACTIVITY_HEADER = "Activities";
	/** Heading for parks in files */
	private static final String PARK_HEADER = "Parks";
	/** Heading for trails in files */
	private static final String TRAIL_HEADER = "Trails";
	/** Delimiter in files */
	public static final String DELIMITER = "\t";

	/** State for reading from file */
	private State state;

	/** A collection of TrailList */
	private TrailList[] trailLists;
	/** Number of TrailList */
	private int numLists;
	/** A collection of Activities */
	private ActivityList activities;
	/** A collection of Parks */
	private ParkList parks;
	/** Filename for saving the information */
	private String filename;
	/** True if manager has changed since last save */
	private boolean changed;
	/** Capacity of the trailList */
	private int capacity;

	/**
	 * Constructs the GetOutdoorsManager by doing the following: Constructs the
	 * trailLists array with at least three elements. Constructs a new
	 * ActivityList. The GetOutdoorsManager instance should be an Observer of
	 * the ActivityList. Constructs a new ParkList. The GetOutdoorsManager
	 * instance should be an Observer of the ParkList. changed should be set to
	 * false.
	 */
	public GetOutdoorsManager() {
		trailLists = new TrailList[RESIZE];
		capacity = RESIZE;

		activities = new ActivityList();
		activities.addObserver(this);

		parks = new ParkList();
		parks.addObserver(this);

		changed = false;
	}

	/**
	 * Returns the value stored in changed.
	 * 
	 * @return true if the value is changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets the changed field to the given value.
	 * 
	 * @param changed
	 *            to set to true or false
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;

	}

	/**
	 * Returns the filename.
	 * 
	 * @return filename of the file
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * If the filename parameter is null, the empty string, or string with
	 * whitespace only, an IllegalArgumentException is thrown. Leading and
	 * trailing whitespace should be trimmed from filename prior to assigning to
	 * field. Otherwise, the filename field is set.
	 * 
	 * @param fileName
	 *            to set
	 */
	public void setFilename(String fileName) {
		if (fileName == null || fileName.equals("") || !fileName.matches(".*\\w.*")) {
			throw new IllegalArgumentException();
		}
		filename = fileName.trim();
	}

	/**
	 * Returns the number of TrailLists.
	 * 
	 * @return numLists number of trailLists
	 */
	public int getNumTrailLists() {
		return numLists;
	}

	/**
	 * Returns the TrailList at the given index. If the index is less than 0 or
	 * the index is greater or equal to numLists, an IndexOutOfBoundsException
	 * is thrown.
	 * 
	 * @param idx
	 *            of the TrailList
	 * @return TrailList at given index
	 */
	public TrailList getTrailList(int idx) {
		if (idx < 0 || idx >= numLists) {
			throw new IndexOutOfBoundsException();
		}

		return trailLists[idx];
	}

	/**
	 * Returns trailLists.
	 * 
	 * @return TrailList[] the trailLists
	 */
	public TrailList[] getTrailLists() {
		return trailLists;
	}

	/**
	 * Returns activities.
	 * 
	 * @return activities the activity
	 */
	public ActivityList getActivities() {
		return activities;
	}

	/**
	 * Returns the index of the added TrailList. If necessary, the TrailLists
	 * array should be resized to accommodate new TrailList. Every TrailList
	 * added should have the GetOutdoorsManager instance added as an Observer.
	 * 
	 * @param park
	 *            Park
	 * @return int index of the added TrailList
	 */
	public int addTrailList(Park park) {
		TrailList trail = new TrailList(park);
		if (numLists == capacity) {
			growTrailLists();
		}
		trailLists[numLists] = trail;
		numLists++;
		trail.addObserver(this);

		return numLists;
	}

	/**
	 * Private helper method to increase the capacity of the trailLists array if
	 * the capacity is reached.
	 */
	private void growTrailLists() {
		TrailList[] temp = new TrailList[(capacity * RESIZE)];

		for (int i = 0; i < numLists; i++) {
			temp[i] = trailLists[i];
		}

		trailLists = temp;
		capacity *= RESIZE;
	}

	/**
	 * Returns parks.
	 * 
	 * @return parks the parks to return
	 */
	public ParkList getParks() {
		return parks;
	}

	/**
	 * If a ActivityList, ParkList, or TrailList observed by GetOutdoorsManager
	 * changes, the update() method is automatically called. When o is equals to
	 * parks, the method should iterate through parks to check that each park in
	 * parks has a TrailList in trailLists. If a park doesn't have an associated
	 * TrailList, a TrailList should be added. GetOutdoorsManager should
	 * propagate the notification of the change to its Observers in all cases
	 * and changed should be updated to true.
	 * 
	 * @param o
	 *            Observable pattern
	 * @param obj
	 *            element
	 */
	@Override
	public void update(Observable o, Object obj) {
		if (o instanceof ParkList || o instanceof ActivityList || o instanceof TrailList) {

			if (o instanceof ParkList && o.equals(parks)) {
				boolean trailFound;
				for (int i = 0; i < parks.size(); i++) {
					trailFound = false;
					for (int j = 0; j < numLists; j++) {
						if (parks.getParkAt(i).getName().equals(trailLists[j].getParkName())) {
							trailFound = true;
							break;
						}
					}
					if (!trailFound) {
						this.addTrailList(parks.getParkAt(i));
					}
				}

			}
			setChanged();

			notifyObservers(o);
			changed = true;
		}
	}

	/**
	 * Saves the GetOutdoorsManager to file.
	 * 
	 * @param filename
	 *            filename to save GetOutdoorsManager information to.
	 * @return true is saved successfully
	 */
	public boolean saveDataFile(String filename) {
		if (filename == null || filename.trim().equals("")) {
			System.err.println("Invalid filename: " + filename);
			return false;
		} else {
			try {
				PrintStream out = new PrintStream(new File(filename.trim()));
				out.println("# Activities\n");
				for (int i = 0; i < activities.size(); i++) {
					out.println("*\t" + clean(activities.getActivityAt(i).toString()));
				}
				out.println("\n# Parks\n");
				for (int i = 0; i < parks.size(); i++) {
					out.println("*\t" + clean(parks.getParkAt(i).toString()));
				}
				out.println("\n# Trails\n");
				for (int i = 0; i < numLists; i++) {
					TrailList trails = trailLists[i];
					for (int j = 0; j < trails.size(); j++) {
						out.println(
								"*\t" + clean(trails.getParkName()) + "\t" + clean(trails.getTrailAt(j).toString()));
					}
				}
				changed = false;
				out.close();
				return true;
			} catch (IOException e) {
				System.err.println("An error occurred while saving file " + filename);
				e.printStackTrace(System.err);
				return false;
			}
		}
	}

	/**
	 * Opens a data file with the given name and creates the data structures
	 * from the file.
	 * 
	 * @param fname
	 *            filename to create GetOutdoorsManager information from.
	 * @return true is opened successfully
	 */
	public boolean openDataFile(String fname) {
		if (changed) {
			saveDataFile(this.filename);
		}
		try {
			state = startState;
			Scanner fileIn = new Scanner(new File(fname));
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				processLine(line);
			}

			setChanged(true);
			notifyObservers(this);
			changed = false;
			fileIn.close();
			return true;
		} catch (IOException e) {
			System.err.println("An error occurred while reading file " + fname);
			e.printStackTrace(System.err);
			return false;
		}
	}

	/**
	 * Cleans text input to remove line break character
	 * 
	 * @param text
	 *            - the text to clean
	 * @return the cleaned text
	 */
	private String clean(String text) {
		return text.replace("\n", " ").trim();
	}

	/**
	 * Processes a single line from the input text file
	 * 
	 * @param line
	 *            the line being processed
	 */
	private void processLine(String line) {
		try (Scanner lineScan = new Scanner(line)) {
			if (lineScan.hasNext()) {
				String token = lineScan.next();
				if (token.equals("#")) {
					state.onHeader(lineScan.nextLine().trim());
				} else {
					state.onContent(line);
				}
			}
		}

	}

	/**
	 * Represents a State in the FSM for processing the input file
	 * 
	 * @author Jason King
	 * 
	 *         Initial FSM for config file
	 * 
	 * @author Jessica Young Schmidt
	 * 
	 *         FSM for GetOutdoors
	 *
	 */
	private abstract class State {

		/**
		 * Transition for when a header (line begins with a #) is encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		public abstract void onHeader(String line);

		/**
		 * Transition for when a non-header (line does not begin with a #) is
		 * encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		public abstract void onContent(String line);

	}

	/**
	 * Start State for the FSM for processing the input file
	 * 
	 * @author Jason King
	 * 
	 *         Initial FSM for config file
	 * 
	 * @author Jessica Young Schmidt
	 * 
	 *         FSM for GetOutdoors
	 *
	 */
	private class StartState extends State {

		/**
		 * Transition for when a header (line begins with a #) is encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onHeader(String line) {
			if (line.equalsIgnoreCase(ACTIVITY_HEADER)) {
				state = activityState;
			} else {
				throw new IllegalArgumentException("File must start with the ACTIVITY information.");
			}

		}

		/**
		 * Transition for when a non-header (line does not begin with a #) is
		 * encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onContent(String line) {
			throw new IllegalArgumentException("File must start with the ACTIVITY information.");
		}

	}

	/**
	 * Activity State for the FSM for processing the input file
	 * 
	 * @author Jason King
	 * 
	 *         Initial FSM for config file
	 * 
	 * @author Jessica Young Schmidt
	 * 
	 *         FSM for GetOutdoors
	 *
	 */
	private class ActivityState extends State {

		/**
		 * Transition for when a header (line begins with a #) is encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onHeader(String line) {
			if (line.equalsIgnoreCase(PARK_HEADER)) {
				state = parkState;
			} else {
				throw new IllegalArgumentException("File must define PARK after the ACTIVITY information.");
			}
		}

		/**
		 * Transition for when a non-header (line does not begin with a #) is
		 * encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onContent(String line) {
			if (!line.trim().equals("")) { // Not a blank line
				try (Scanner lineScan = new Scanner(line)) {
					lineScan.useDelimiter(DELIMITER);
					lineScan.next(); // * at start
					String activityName = lineScan.next();
					String activityDesc = lineScan.next();
					boolean needSnow = lineScan.nextBoolean();
					int snowBoundary = lineScan.nextInt();
					activities.addActivity(activityName, activityDesc, needSnow, snowBoundary);
				} catch (Exception e) {
					throw new IllegalArgumentException("Error loading activity information: " + e.getMessage());
				}
			}
		}

	}

	/**
	 * Park State for the FSM for processing the input file
	 * 
	 * @author Jason King
	 * 
	 *         Initial FSM for config file
	 * 
	 * @author Jessica Young Schmidt
	 * 
	 *         FSM for GetOutdoors
	 *
	 */
	private class ParkState extends State {

		/**
		 * Transition for when a header (line begins with a #) is encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onHeader(String line) {
			if (line.equalsIgnoreCase(TRAIL_HEADER)) {
				state = trailState;
			} else {
				throw new IllegalArgumentException("File must define TRAIL after the PARK information.");
			}
		}

		/**
		 * Transition for when a non-header (line does not begin with a #) is
		 * encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onContent(String line) {
			if (!line.trim().equals("")) { // Not a blank line
				try (Scanner lineScan = new Scanner(line)) {
					lineScan.useDelimiter(DELIMITER);
					lineScan.next(); // * at start
					String parkName = lineScan.next();
					String parkDesc = lineScan.next();
					double snow = lineScan.nextDouble();
					parks.addPark(parkName, parkDesc, snow);
				} catch (Exception e) {
					throw new IllegalArgumentException("Error loading park information: " + e.getMessage());
				}
			}
		}

	}

	/**
	 * Trail State for the FSM for processing the input file
	 * 
	 * @author Jason King
	 * 
	 *         Initial FSM for config file
	 * 
	 * @author Jessica Young Schmidt
	 * 
	 *         FSM for GetOutdoors
	 *
	 */
	private class TrailState extends State {

		/**
		 * Transition for when a header (line begins with a #) is encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onHeader(String line) {
			throw new IllegalArgumentException("File must not define any other headers after TRAIL.");
		}

		/**
		 * Transition for when a non-header (line does not begin with a #) is
		 * encountered
		 * 
		 * @param line
		 *            the line of text to process
		 */
		@Override
		public void onContent(String line) {
			if (!line.trim().equals("")) { // Not a blank line
				try (Scanner lineScan = new Scanner(line)) {
					lineScan.useDelimiter(DELIMITER);
					lineScan.next(); // * at start
					String parkName = lineScan.next();
					for (int i = 0; i < numLists; i++) {
						if (trailLists[i].getParkName().equals(parkName)) {
							String trailName = lineScan.next();
							boolean trailMaintenance = lineScan.nextBoolean();
							double snow = lineScan.nextDouble();
							double distance = lineScan.nextDouble();
							Difficulty difficulty = Difficulty.valueOf(lineScan.next());

							SortedArrayList<Activity> activitiesList = new SortedArrayList<Activity>();
							if (lineScan.hasNextLine()) {
								String actLine = lineScan.nextLine();
								Scanner actScan = new Scanner(actLine);
								actScan.useDelimiter(DELIMITER);
								while (actScan.hasNext()) {
									String activityName = actScan.next();
									for (int j = 0; j < activities.size(); j++) {
										Activity activity = activities.getActivityAt(j);
										if (activity.getName().equals(activityName)) {
											activitiesList.add(activity);
										}
									}
								}
								actScan.close();
							}
							trailLists[i].addTrail(trailName, activitiesList, trailMaintenance, snow, distance,
									difficulty);
						}
					}
				} catch (Exception e) {
					throw new IllegalArgumentException("Error loading trail information: " + e.getMessage());
				}
			}
		}

	}

}