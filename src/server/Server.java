package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import classes.User;

public class Server {

	// Variables
	private ServerSocket serverSocket;
	private List<User> users;

	/**
	 * Creates a Server object
	 */
	public Server() {
		users = new ArrayList<User>();
	}

	/**
	 * Starts the server
	 */
	public void start() {

		try {

			// Start server connection
			serverSocket = new ServerSocket(9163);

			// Create mailbox
			Mailbox mailbox = new Mailbox();

			// Create empty mailbox thread and start it
			EmptyMailboxThread emptyMailboxThread = new EmptyMailboxThread(mailbox, users);
			emptyMailboxThread.start();

			while (true) {

				// Get client socket
				Socket clientSocket = serverSocket.accept();

				// Get socket streams
				InputStream is = clientSocket.getInputStream();
				OutputStream os = clientSocket.getOutputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(is));
				PrintWriter out = new PrintWriter(os, true);

				// Get client address and print it
				InetAddress inetAddress = clientSocket.getInetAddress();
				System.out.println("Connected to client: " + inetAddress.toString());

				// Start thread for new client connection
				Thread clientServerThread = new ClientServerThread(clientSocket, in, out, mailbox, users);
				clientServerThread.start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Stops the server
	 */
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
