package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import classes.EchoProtocol;
import classes.User;

public class ClientThread extends Thread {

	// Variables
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private Mailbox mailbox;
	private List<User> users;
	private User user;

	/**
	 * Creates a ClientThread object
	 * 
	 * @param serverSocket
	 *            the server socket
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
	public ClientThread(ServerSocket serverSocket, Socket clientSocket, BufferedReader in, PrintWriter out,
			Mailbox mailbox, List<User> users) {
		this.serverSocket = serverSocket;
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

				// Adds new client to the user list
				if (inputLine.startsWith("nc ")) {
					addUser(inputLine.substring(2));
				}

				// Checks if the user quits
				if (inputLine.equals("q")) {
					removeUser();
					clientSocket.close();
					break;
				} else if (inputLine.equals("q server")) {
					removeAllUsers();
					serverSocket.close();
					break;
				}

				// Process user input
				outputLine = ep.processInput(inputLine);
				if (outputLine.startsWith("sys ")) {
					// Prints system message
					System.out.println(outputLine.substring(4));
				} else {
					// Prints user message
					String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
					mailbox.setMessage(timestamp + " " + user.getName() + ": " + outputLine);
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

	/**
	 * Removes all users
	 */
	private synchronized void removeAllUsers() {
		users.clear();
	}
}
