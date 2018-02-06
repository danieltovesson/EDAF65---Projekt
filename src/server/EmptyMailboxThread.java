package server;

import java.io.PrintWriter;
import java.util.List;

import classes.User;

public class EmptyMailboxThread extends Thread {

	// Variables
	private Mailbox mailbox;
	private List<User> users;

	/**
	 * Creates a EmptyMailboxThread object
	 * 
	 * @param mailbox
	 *            the mailbox
	 * @param users
	 *            the users
	 */
	public EmptyMailboxThread(Mailbox mailbox, List<User> users) {
		this.mailbox = mailbox;
		this.users = users;
	}

	/**
	 * Runs the thread
	 */
	public void run() {

		// Prints the mailbox message
		while (true) {
			String message = mailbox.getMessage();
			if (message != null) {
				for (User user : users) {
					PrintWriter out = user.getPrintWriter();
					out.println(message);
					out.flush();
				}
			}
		}
	}
}
