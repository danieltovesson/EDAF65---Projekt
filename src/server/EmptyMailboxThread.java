package server;

import java.io.PrintWriter;
import java.util.ArrayList;
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

		while (true) {

			// Prints the mailbox message if there is any
			String[] message = mailbox.getMessage();
			if (message != null) {

				// Saves the users to print to in a array
				ArrayList<String> connectedUsers = new ArrayList<String>();
				connectedUsers.add(message[0]);
				connectedUsers.add(message[1]);

				for (User user : users) {
					if (connectedUsers.contains(user.getName())) {

						// Prints the message
						PrintWriter out = user.getPrintWriter();
						out.println(message[2]);
						out.flush();
					}
				}
			}
		}
	}
}
