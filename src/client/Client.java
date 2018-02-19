package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	// Variables
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private String command;

	/**
	 * Creates a Client object
	 * 
	 * @param name
	 *            the name of the client
	 */
	public Client(String name) {

		try {

			// Create socket and get input and output streams
			socket = new Socket("localhost", 9163);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			PrintWriter out = new PrintWriter(os, true);

			// Start input stream thread
			InputThread inputThread = new InputThread(in);
			inputThread.start();

			// Create new client message for the server
			command = "newClient " + name;

			// Start output stream thread
			OutputThread outputThread = new OutputThread(command, out);
			outputThread.start();

		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}

	/**
	 * Connects to another client
	 * 
	 * @param clientName
	 *            the name of the client
	 */
	public void connectTo(String clientName) {
		command = "connectTo " + clientName;
	}

	/**
	 * Disconnects from another client
	 * 
	 * @param clientName
	 *            the name of the client
	 */
	public void disconnectFrom(String clientName) {
		command = "disconnectFrom " + clientName;
	}

	/**
	 * Sends a message to a connected client
	 * 
	 * @param message
	 *            the message
	 */
	public void sendMessage(String message) {
		command = "msg " + message;
	}

	/**
	 * Quits the application
	 */
	public void quit() {
		command = "quit";
	}
}
