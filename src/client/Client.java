package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Stack;

import gui.GUI;
import javafx.collections.ObservableList;

public class Client {

	// Variables
	private String name;
	private ObservableList<String> users;
	private GUI gui;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private Stack<String> commands;

	/**
	 * Creates a Client object
	 * 
	 * @param name
	 *            the name of the client
	 * @param users
	 *            the users of the game
	 * @param gui
	 *            the game GUI
	 */
	public Client(String name, ObservableList<String> users, GUI gui) {
		this.name = name;
		this.users = users;
		this.gui = gui;
		commands = new Stack<String>();
	}

	/**
	 * Starts the client
	 * 
	 * @return true if the client were successfully started, otherwise false
	 */
	public boolean start() {

		try {

			// Create socket and get input and output streams
			socket = new Socket("localhost", 9163);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			PrintWriter out = new PrintWriter(os, true);

			// Start input stream thread
			InputThread inputThread = new InputThread(in, users, gui);
			inputThread.start();

			// Create new client message for the server
			commands.push("newClient " + name);

			// Start output stream thread
			OutputThread outputThread = new OutputThread(commands, out);
			outputThread.start();

			return true;

		} catch (IOException e) {
			System.out.println("Connection error");
			return false;
		}
	}

	/**
	 * Gets the name of the client
	 * 
	 * @return the name of the client
	 */
	public String getName() {
		return name;
	}

	/**
	 * Connects to another client
	 * 
	 * @param clientName
	 *            the name of the client
	 */
	public void connectTo(String clientName) {
		commands.push("connectTo " + clientName);
	}

	/**
	 * Disconnects from another client
	 * 
	 * @param clientName
	 *            the name of the client
	 */
	public void disconnectFrom(String clientName) {
		commands.push("disconnectFrom " + clientName);
	}

	/**
	 * Sends a message to a connected client
	 * 
	 * @param message
	 *            the message
	 */
	public void sendMessage(String message) {
		commands.push("msg " + message);
	}

	/**
	 * Sends the choice to a connected client
	 * 
	 * @param choice
	 *            the choice
	 */
	public void setChoice(String choice) {
		commands.push("choice " + choice);
	}

	/**
	 * Quits the application
	 */
	public void quit() {
		commands.push("quit " + name);
	}
}
