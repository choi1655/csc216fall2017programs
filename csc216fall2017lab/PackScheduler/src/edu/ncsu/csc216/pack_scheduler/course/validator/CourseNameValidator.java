package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Finite State Machine using State objects for checking whether a Course's Name
 * is valid or not.
 * 
 * @author Jonathan Balliet
 * @author Kamai Guillory
 * @author Anna Chernikov
 *
 */
public class CourseNameValidator {
	/** Initial state before the input is examined */
	private final State initial = new StateInitial();
	/** State for when one letter has been found */
	private final State stateL = new StateL();
	/** State for when two sequential letters have been found */
	private final State stateLL = new StateLL();
	/** State for when three sequential letters have been found */
	private final State stateLLL = new StateLLL();
	/** State for when fours sequential letters have been found */
	private final State stateLLLL = new StateLLLL();
	/** State for when one digit has been found */
	private final State stateD = new StateD();
	/** State for when two sequential digits have been found */
	private final State stateDD = new StateDD();
	/** State for when three sequential digits have been found */
	private final State stateDDD = new StateDDD();
	/** State for when one suffix has been found */
	private final State stateSuffix = new StateSuffix();
	/** Tracker for which state we are currently in */
	private State state;
	/** Tracker for whether the course name is valid or not */
	boolean isValid;

	/**
	 * Returns true if the course name parameter is valid. Returns false if it is
	 * not valid. Throws an exception if an invalid character is found during the
	 * iteration process of validating the course name.
	 * 
	 * @param courseName
	 *            name of the course to be validated
	 * @return boolean value of whether the course name is valid or invalid
	 * @throws InvalidTransitionException
	 *             throws exception when an invalid transition is found during the
	 *             validation process
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		// Initialize isValid to false
		isValid = false;
		// Initializes the state to the intialState
		state = initial;
		// Initializes the current index to zero
		int idx = 0;

		// Iterates through the passed in course name string
		while (idx < courseName.length()) {
			// Gets the char at current index
			char c = courseName.charAt(idx);
			// Calls on the corresponding method for the state, depending on
			// which type of character is at the current index
			if (Character.isLetter(c)) {
				state.onLetter();
			} else if (Character.isDigit(c)) {
				state.onDigit();
			} else {
				state.onOther();
			}
			// Increments index value
			idx++;
		}
		// Returns whether the course name is valid
		return isValid;
	}

	/**
	 * Abstract private class State represents a state in the Course Name Validator
	 * Finite State Machine.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public abstract class State {
		/**
		 * Abstract method. Used when a letter is encountered during transition of the
		 * FSM for this State.
		 * 
		 * @throws InvalidTransitionException
		 *             throws exception if a letter is an invalid transition for this
		 *             State
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Abstract method. Used when a digit is encountered during transition of the
		 * FSM for this State.
		 * 
		 * @throws InvalidTransitionException
		 *             throws exception if a digit is an invalid transition for this
		 *             State
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Abstract method. Used when a character other than a letter or digit is
		 * encountered during transition of the FSM for this State
		 * 
		 * @throws InvalidTransitionException
		 *             throws exception if a character other than a letter or digit is
		 *             encountered during an transition for this State
		 * 
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * Represents the initial State for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateInitial extends State {
		/**
		 * Changes the state to STATEL when a letter is encountered.
		 */
		@Override
		public void onLetter() {
			state = stateL;

		}

		/**
		 * Throws an invalidTransitionException when a digit is encountered.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");

		}

	}

	/**
	 * Represents the the state of one letter for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateL extends State {
		/**
		 * Changes the state to STATELL when a letter is encountered.
		 */
		@Override
		public void onLetter() {
			state = stateLL;

		}

		/**
		 * Changes the state to STATED when a digit is encountered.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			state = stateD;

		}

	}

	/**
	 * Represents the state of two letters for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateLL extends State {

		/**
		 * Changes the state to STATELLL when a letter is encountered.
		 */
		@Override
		public void onLetter() {
			state = stateLLL;

		}

		/**
		 * Changes the state to STATED when a digit is encountered.
		 */
		@Override
		public void onDigit() {
			state = stateD;

		}

	}

	/**
	 * Represents the state of three letters for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateLLL extends State {

		/**
		 * Changes the state to STATELLLL when a letter is encountered.
		 */
		@Override
		public void onLetter() {
			state = stateLLLL;

		}

		/**
		 * Changes the state to STATED when a digit is encountered.
		 */
		@Override
		public void onDigit() {
			state = stateD;

		}

	}

	/**
	 * Represents the state of fours letters for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateLLLL extends State {

		/**
		 * Throws an exception when a letter is encountered.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");

		}

		/**
		 * Changes state to STATED when a digit is encountered.
		 */
		@Override
		public void onDigit() {
			state = stateD;

		}

	}

	/**
	 * Represents the state of one digit for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateD extends State {

		/**
		 * Throws an exception when a letter is encountered.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must have 3 digits.");

		}

		/**
		 * Changes state to STATEDD when a digit is encountered.
		 */
		@Override
		public void onDigit() {
			state = stateDD;

		}

	}

	/**
	 * Represents the state of two digits for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateDD extends State {

		/**
		 * Throws an exception when a letter is encountered.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must have 3 digits.");

		}

		/**
		 * Changes state to STATEDDD when a digit is encountered.
		 */
		@Override
		public void onDigit() {
			state = stateDDD;
			isValid = true;

		}

	}

	/**
	 * Represents the state of three digits for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateDDD extends State {
		/**
		 * Changes state to STATEDDD when a digit is encountered.
		 */
		@Override
		public void onLetter() {
			state = stateSuffix;
			isValid = true;

		}

		/**
		 * Throws an exception when a digit is encountered.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have 3 digits.");

		}

	}

	/**
	 * Represents the state of a suffix for the Course Name Validator.
	 * 
	 * @author Jonathan Balliet
	 * @author Kamai Guillory
	 * @author Anna Chernikov
	 */
	public class StateSuffix extends State {

		/**
		 * Throws an exception when a letter is encountered.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");

		}

		/**
		 * Throws an exception when a digit is encountered.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}

	}

}
