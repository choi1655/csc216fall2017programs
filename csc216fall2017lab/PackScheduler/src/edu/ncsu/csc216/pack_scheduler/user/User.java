package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The User Class is a parent class to Student and holds methods and fields for
 * student and registrar objects.
 * 
 * @author Jonathan Balliet
 * @author Kamai Guillory
 * @author Anna Chernikov
 */
public abstract class User {

	/** Student first name */
	private String firstName;
	/** Student last name */
	private String lastName;
	/** Student unity id */
	private String id;
	/** Student email address */
	private String email;
	/** Student's hashed password */
	private String password;

	/**
	 * Constructs a student or registrar with a first name, last name, id, email and
	 * password.
	 * 
	 * @param firstName
	 *            student or registrar first name
	 * @param lastName
	 *            student or registrar last name
	 * @param id
	 *            student or registrar id
	 * @param email
	 *            student or registrar email
	 * @param password
	 *            student or registrar password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Gets the user's first name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the user's first name
	 * 
	 * @param firstName
	 *            the firstName to set
	 * @throws IllegalArgumentException
	 *             for empty or null first name
	 */
	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName == null || firstName.equals(""))
			throw new IllegalArgumentException("Invalid first name");

		this.firstName = firstName;
	}

	/**
	 * Gets the user's last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the user's last name
	 * 
	 * @param lastName
	 *            the lastName to set
	 * @throws IllegalArgumentException
	 *             for empty or null last name
	 */
	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName == null || lastName.equals(""))
			throw new IllegalArgumentException("Invalid last name");

		this.lastName = lastName;
	}

	/**
	 * Gets the user's unity id
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the user's unity id
	 * 
	 * @param id
	 *            the id to set
	 * @throws IllegalArgumentException
	 *             for empty or null id
	 */
	protected void setId(String id) throws IllegalArgumentException {
		if (id == null || id.equals(""))
			throw new IllegalArgumentException("Invalid id");

		this.id = id;
	}

	/**
	 * Returns user's email address
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets user's email
	 * 
	 * @param email
	 *            the email to set
	 * @throws IllegalArgumentException
	 *             for empty or null email that does not meet the requirements
	 */
	public void setEmail(String email) throws IllegalArgumentException {
		if (email == null)
			throw new IllegalArgumentException("Invalid email");

		int atIndex = email.indexOf('@');
		int periodIndex = email.lastIndexOf('.');

		if (email.equals("") || atIndex < 0 || periodIndex < 0 || periodIndex < atIndex)
			throw new IllegalArgumentException("Invalid email");

		this.email = email;
	}

	/**
	 * Returns user's password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password
	 * 
	 * @param password
	 *            the password to set
	 * @throws IllegalArgumentException
	 *             for empty or null password
	 */
	public void setPassword(String password) throws IllegalArgumentException {
		if (password == null || password.equals(""))
			throw new IllegalArgumentException("Invalid password");

		this.password = password;
	}

	/**
	 * Generates a hashCode for the User using all of its fields.
	 * 
	 * @return int value of the hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Overrides the equals method. All User objects that have the same field values
	 * are considered equal.
	 * 
	 * @return boolean value of whether the two Users are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}