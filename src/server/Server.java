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
	private List<User> users;

	public static void main(String[] args) {
		Server s = new Server();
		s.run();
	}

	/**
	 * Runs the program
	 */
	private void run() {

		// Initialize users list
		users = new ArrayList<User>();

		try {

			// Start server connection
			ServerSocket serverSocket = new ServerSocket(9163);

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
				Thread serverThread = new ClientThread(serverSocket, clientSocket, in, out, mailbox, users);
				serverThread.start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
