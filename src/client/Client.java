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
	 * @param machine
	 *            the machine of the socket
	 * @param port
	 *            the port of the socket
	 */
	public Client(String clientName, String machine, int port) {

		try {

			// Create socket and get input and output streams
			socket = new Socket(machine, port);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			PrintWriter out = new PrintWriter(os, true);

			// Start input stream thread
			InputThread inputThread = new InputThread(in);
			inputThread.start();

			// Create new client message for the server
			command = "nc " + clientName;

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
		command = "c " + clientName;
	}

	/**
	 * Quits the application
	 */
	public void quit() {
		command = "q";
	}
}
