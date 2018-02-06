package client;

import java.io.PrintWriter;

public class OutputThread extends Thread {

	// Variables
	private String command;
	private PrintWriter out;

	/**
	 * Creates a OutputThread object
	 * 
	 * @param command
	 *            the command
	 * @param out
	 *            the output stream
	 */
	public OutputThread(String command, PrintWriter out) {
		this.command = command;
		this.out = out;
	}

	public void run() {

		// Print user command to server
		while (true) {
			if (command != null) {
				out.println(command);
				out.flush();
				command = null;
			}
		}
	}
}
