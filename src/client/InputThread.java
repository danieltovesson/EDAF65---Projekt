package client;

import java.io.BufferedReader;
import java.io.IOException;

public class InputThread extends Thread {

	// Variables
	private BufferedReader in;

	/**
	 * Creates a InputThread object
	 * 
	 * @param in
	 *            the input stream
	 */
	public InputThread(BufferedReader in) {
		this.in = in;
	}

	public void run() {

		// Show server output
		try {
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}
}
