package client;

import java.io.BufferedReader;
import java.io.IOException;

import javafx.collections.ObservableList;

public class InputThread extends Thread {

	// Variables
	private BufferedReader in;
	private ObservableList<String> users;

	/**
	 * Creates a InputThread object
	 * 
	 * @param in
	 *            the input stream
	 */
	public InputThread(BufferedReader in, ObservableList<String> users) {
		this.in = in;
		this.users = users;
	}

	public void run() {

		// Show server output
		try {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {

				// Add user to users list
				users.add(inputLine);
				System.out.println(inputLine);
			}
		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}
}
