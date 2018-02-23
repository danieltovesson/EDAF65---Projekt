package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import classes.EchoProtocol;
import classes.User;

public class ClientServerThread extends Thread {

	// Variables
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private Mailbox mailbox;
	private List<User> users;
	private User user;

	/**
	 * Creates a ClientServerThread object
	 * 
	 * @param clientSocket
	 *            the client socket
	 * @param in
	 *            the input from the client
	 * @param out
	 *            the output from the server
	 * @param mailbox
	 *            the mailbox
	 * @param users
	 *            the users
	 */
	public ClientServerThread(Socket clientSocket, BufferedReader in, PrintWriter out, Mailbox mailbox,
			List<User> users) {
		this.clientSocket = clientSocket;
		this.in = in;
		this.out = out;
		this.mailbox = mailbox;
		this.users = users;
	}

	/**
	 * Runs the thread
	 */
	public void run() {

		try {

			// Read lines
			String inputLine, outputLine;
			EchoProtocol ep = new EchoProtocol();
			while ((inputLine = in.readLine()) != null) {

				// Process user input
				outputLine = ep.processInput(inputLine);

				if (inputLine.startsWith("newClient ")) {

					// Adds new client to the user list
					addUser(outputLine);

					// Sends a message saying broadcast name to everyone except
					// self
					mailbox.setMessage(user.getName(), "all", user.getName());

				} else if (inputLine.startsWith("connectTo ")) {

					if (!user.isConnected()) {

						if (!user.getName().equals(outputLine)) {

							// Connect users
							boolean found = false;
							for (User u : users) {
								if (u.getName().equals(outputLine)) {

									// Connect users if neither is connected
									if (!u.isConnected()) {

										// Connect users
										user.connect(outputLine);
										u.connect(user.getName());

										// Say users got connected
										out.println("connected");
										u.getPrintWriter().println("connected");

									} else {
										out.println("Server: Requested user already connected");
										out.flush();
									}
									found = true;
									break;
								}
							}

							// Error message if user was not found
							if (!found) {
								out.println("Server: Couldn't find user to connect to (" + outputLine + ")");
								out.flush();
							}
						} else {
							out.println("Server: You can't connect to yourself");
							out.flush();
						}
					} else {
						out.println("Server: User already connected");
						out.flush();
					}

				} else if (inputLine.startsWith("disconnectFrom ")) {

					if (user.isConnected()) {

						if (!user.getName().equals(outputLine)) {

							// Disconnect users
							boolean found = false;
							for (User u : users) {
								if (u.getName().equals(outputLine)) {

									// Disconnect users if neither is
									// disconnected
									if (u.isConnected()) {

										// Disconnect users
										user.disconnect();
										u.disconnect();

										// Say users got disconnected
										out.println("disconnected");
										u.getPrintWriter().println("disconnected");

									} else {
										out.println("Server: Requested user not connected");
										out.flush();
									}
									found = true;
									break;
								}
							}

							// Error message if user was not found
							if (!found) {
								out.println("Server: Couldn't find user to disconnect from (" + outputLine + ")");
								out.flush();
							}
						} else {
							out.println("Server: You can't disconnect from yourself");
							out.flush();
						}
					} else {
						out.println("Server: User not connected");
						out.flush();
					}

				} else if (inputLine.startsWith("msg ")) {

					// Prints user message
					if (user.isConnected()) {
						String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
						String message = timestamp + " " + user.getName() + ": " + outputLine;
						mailbox.setMessage(user.getName(), user.getConnection(), message);
					}

				} else if (inputLine.equals("quit")) {

					// Remove user and close socket if user quits
					removeUser();
					clientSocket.close();
					break;

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds the user to list of active users
	 * 
	 * @param name
	 *            the name of the user
	 */
	private synchronized void addUser(String name) {
		user = new User(name, out);
		users.add(user);
	}

	/**
	 * Removes the user
	 */
	private synchronized void removeUser() {
		users.remove(user);
	}
}
