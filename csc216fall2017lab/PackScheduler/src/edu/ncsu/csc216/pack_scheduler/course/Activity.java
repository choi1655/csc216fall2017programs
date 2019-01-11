package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Abstract class that represents a scheduled activity. An Activity Object has a
 * title, the meeting days that it occurs, and the start/end time.
 * 
 * @author Dominic Brown (dtbrown5)
 */
public abstract class Activity implements Conflict {

	/** Activity's title. */
	private String title;
	/** Activity meeting days. */
	private String meetingDays;
	/** Activity's start time. */
	private int startTime;
	/** Activity's end time. */
	private int endTime;

	/**
	 * Constructs an Activity object with all fields
	 * 
	 * @param title
	 *            The title of the activity
	 * @param meetingDays
	 *            The days the activity meets
	 * @param startTime
	 *            The time the activity starts
	 * @param endTime
	 *            The time the activity ends
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Abstract function to return a String array with a few details about the
	 * activity
	 * 
	 * @return String array with details about the activity
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Abstract function to return a String array with most/all details about the
	 * activity
	 * 
	 * @return String array with details about the activity
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Functionality to check whether an activity is a duplicate of this Activity
	 * 
	 * @param activity
	 *            The activity to compare
	 * @return true if they are duplicate, false if they are not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Returns the Activity's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title. If the title is null or an empty string, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @throws IllegalArgumentException
	 *             if the name is null or empty
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if (title == null || title.equals(""))
			throw new IllegalArgumentException("Invalid course title");
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Activity's meeting days.
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns a String in the format of "Meeting days startTime-endTime" OR just
	 * "Arranged" if the meeting days are arranged
	 * 
	 * @return a String in the format of "Meeting days startTime-endTime" OR just
	 *         "Arranged" if the meeting days are arranged
	 */
	public String getMeetingString() {
		if (this.getMeetingDays().equals("A"))
			return "Arranged";

		String hoursPlace = militaryToStandardTime(this.getStartTime());
		String minutesPlace = militaryToStandardTime(this.getEndTime());
		return this.getMeetingDays() + " " + hoursPlace + "-" + minutesPlace;
	}

	/**
	 * Helper method that takes a valid military time and converts it to standard
	 * time format, with the AM or PM suffix
	 * 
	 * @param time
	 *            In military time format
	 * @return the time in standard format ("1:30AM" for example)
	 */
	private String militaryToStandardTime(int time) {
		int hours = parseHoursFromMilitaryTime(time);
		int minutes = parseMinutesFromMilitaryTime(time);

		String period;
		String hoursPlace;
		String minutesPlace;

		if (hours >= 12) {
			period = "PM";
			// Converting 1300-2300 to 1-11pm
			if (hours >= 13)
				hoursPlace = Integer.toString(hours - 12);
			else
				hoursPlace = Integer.toString(hours);
		} else {
			hoursPlace = Integer.toString(hours);
			period = "AM";
		}

		if (minutes < 10)
			minutesPlace = "0" + Integer.toString(minutes);
		else
			minutesPlace = Integer.toString(minutes);

		return hoursPlace + ":" + minutesPlace + period;
	}

	/**
	 * Sets the Activity's start and end time. If the times aren't valid military
	 * times, the end time is earlier than the start time, or the meeting days are
	 * arranged and the start/end times aren't zero, an IllegalArgumentException is
	 * thrown
	 * 
	 * @throws IllegalArgumentException
	 *             if the times aren't valid military times, the end time is earlier
	 *             than the start time, or the meeting days are arranged and the
	 *             start/end times aren't zero.
	 * @param startTime
	 *            The Activity's start time
	 * @param endTime
	 *            The Activity's end time
	 */
	public void setActivityTime(int startTime, int endTime) {
		String errorMessage = "Invalid meeting times";
		if (!isValidMilitaryTime(startTime) || !isValidMilitaryTime(endTime))
			throw new IllegalArgumentException(errorMessage);
		if (endTime < startTime)
			throw new IllegalArgumentException(errorMessage);
		// if (this.getMeetingDays().equals("A") && startTime != 0 && endTime != 0)
		// throw new IllegalArgumentException(errorMessage);

		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the Activity's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Helper method to check if the given military time is valid. The numbers in
	 * the hour place cannot be greater than 23 or less than zero, and the time
	 * cannot be anything other than
	 * 
	 * @param time
	 *            The time to check
	 * @return True if the time is valid, false otherwise.
	 */
	private boolean isValidMilitaryTime(int time) {
		String timeString = Integer.toString(time);
		if (timeString.length() >= 5)
			return false;

		int hours = parseHoursFromMilitaryTime(time);
		if (hours > 23)
			return false;

		int minutes = parseMinutesFromMilitaryTime(time);
		if (minutes > 59 || minutes < 0)
			return false;

		return true;
	}

	/**
	 * Takes the time in military format and returns the hour's place
	 * 
	 * @param time
	 *            The time in military time
	 * @return The hours place of the time
	 */
	private int parseHoursFromMilitaryTime(int time) {
		String timeString = Integer.toString(time);
		int hours;

		if (timeString.length() >= 3)
			hours = Integer.parseInt(timeString.substring(0, timeString.length() - 2));
		else
			hours = 0;

		return hours;
	}

	/**
	 * Takes the time in military format and returns the minutes place
	 * 
	 * @param time
	 *            The time in military time
	 * @return The minutes place of the time
	 */
	private int parseMinutesFromMilitaryTime(int time) {
		String timeString = Integer.toString(time);
		int minutes;

		if (timeString.length() >= 3)
			minutes = Integer.parseInt(timeString.substring(timeString.length() - 2));
		else
			minutes = Integer.parseInt(timeString);

		return minutes;
	}

	/**
	 * Generates a hashCode for the Activity using all of its fields.
	 * 
	 * @return int value of the hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method. All Activity objects that have the same field
	 * values are considered equal.
	 * 
	 * @return boolean value of whether the two Activities are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks to see if two Activities occur during overlapping times. Times that
	 * start one after another (10:00-10:30 and 10:30-11:00 for example) count as
	 * overlapping
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		// Checks if the events share a day. If they don't, it doesn't bother checking
		// if the times
		// overlap
		boolean hasSameDay = false;
		String thisMeetingDays = this.getMeetingDays();
		for (int i = 0; i < thisMeetingDays.length(); i++) {
			if (possibleConflictingActivity.getMeetingDays().indexOf(thisMeetingDays.charAt(i)) != -1) {
				hasSameDay = true;
				break;
			}
		}

		// It shouldn't check the times if either event is arranged
		if (this.getMeetingDays().equals("A") || possibleConflictingActivity.getMeetingDays().equals("A"))
			hasSameDay = false;

		if (hasSameDay) {
			int thisStartTime = this.getStartTime();
			int thisEndTime = this.getEndTime();
			int possibleStartTime = possibleConflictingActivity.getStartTime();
			int possibleEndTime = possibleConflictingActivity.getEndTime();

			// Check if this object's start time is occurs during the possible activity's
			// runtime
			if (thisStartTime <= possibleEndTime && thisStartTime >= possibleStartTime)
				throw new ConflictException();

			// Check if this object's start time is occurs during the possible activity's
			// runtime
			if (thisEndTime <= possibleEndTime && thisEndTime >= possibleStartTime)
				throw new ConflictException();

			// Check if the possible activity's start time occurs during this object's
			// runtime
			if (possibleStartTime <= thisEndTime && possibleStartTime >= thisStartTime)
				throw new ConflictException();

			// Check if the possible activity's start time occurs during this object's
			// runtime
			if (possibleEndTime <= thisEndTime && possibleEndTime >= thisStartTime)
				throw new ConflictException();

		}

	}

}