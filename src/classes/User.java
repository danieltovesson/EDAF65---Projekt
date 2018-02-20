package classes;

import java.io.PrintWriter;

public class User {

	// Variables
	private String name;
	private PrintWriter out;
	private boolean connected;
	private String connection;

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
		connected = false;
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
	 * Connects the user to another one
	 * 
	 * @param name
	 *            the other user
	 */
	public void connect(String name) {
		connected = true;
		connection = name;
	}

	/**
	 * Disconnects the user from the other one
	 */
	public void disconnect() {
		connected = false;
		connection = null;
	}

	/**
	 * Checks if the user is connected
	 * 
	 * @return true if the user is connected, otherwise false
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Gets the name of the connected user
	 * 
	 * @return the name of the connected user
	 */
	public String getConnection() {
		return connection;
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
