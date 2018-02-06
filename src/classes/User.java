package classes;

import java.io.PrintWriter;

public class User {

	// Variables
	private String name;
	private PrintWriter out;

	/**
	 * Creates a User object
	 * 
	 * @param name
	 *            the name of the user
	 * @param out
	 *            the print writer of the user
	 */
	public User(String name, PrintWriter out) {
		this.name = name;
		this.out = out;
	}

	/**
	 * Gets the name of the user
	 * 
	 * @return the name of the user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the print writer of the user
	 * 
	 * @return the print writer of the user
	 */
	public PrintWriter getPrintWriter() {
		return out;
	}
}
