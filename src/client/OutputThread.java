package client;

import java.io.PrintWriter;
import java.util.Stack;

public class OutputThread extends Thread {

	// Variables
	private Stack<String> commands;
	private PrintWriter out;

	/**
	 * Creates a OutputThread object
	 * 
	 * @param commands
	 *            the commands
	 * @param out
	 *            the output stream
	 */
	public OutputThread(Stack<String> commands, PrintWriter out) {
		this.commands = commands;
		this.out = out;
	}

	public void run() {

		// Print user command to server
		while (true) {
			if (!commands.isEmpty()) {
				out.println(commands.pop());
				out.flush();
			}
		}
	}
}
