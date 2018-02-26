package client;

import java.io.BufferedReader;
import java.io.IOException;

import classes.EchoProtocol;
import gui.GUI;
import javafx.application.Platform;
import javafx.collections.ObservableList;

public class InputThread extends Thread {

	// Variables
	private BufferedReader in;
	private ObservableList<String> users;
	private GUI gui;

	/**
	 * Creates a InputThread object
	 * 
	 * @param in
	 *            the input stream
	 * @param users
	 *            the users of the game
	 * @param gui
	 *            the game GUI
	 */
	public InputThread(BufferedReader in, ObservableList<String> users, GUI gui) {
		this.in = in;
		this.users = users;
		this.gui = gui;
	}

	public void run() {

		// Show server output
		try {
			String inputLine, outputLine;
			EchoProtocol ep = new EchoProtocol();
			while ((inputLine = in.readLine()) != null) {

				// Process user input
				outputLine = ep.processInput(inputLine);

				if (inputLine.startsWith("list ")) {
					// Add user to users list
					if (!users.contains(outputLine)) {
						users.add(outputLine);
					}
				} else if (inputLine.equals("connected")) {
					// Show game view for connected users
					Platform.runLater(() -> {
						gui.showGameView();
					});
				} else if (inputLine.equals("disconnected")) {
					// Close game view for connected users
					Platform.runLater(() -> {
						gui.closeGameView();
					});
				} else if (inputLine.startsWith("msg ")) {
					// Show message for connected users
					gui.addMessage(outputLine);
				} else if (inputLine.startsWith("choice ")) {
					// Set choice for connected users
					String[] cmds = outputLine.split(" ");
					gui.setChoice(cmds[0], cmds[1]);
				} else if (inputLine.startsWith("result ")) {
					// Set result for connected users
					String[] cmds = outputLine.split(" ");
					gui.setResult(cmds[0], cmds[1]);
				} else {
					System.out.println(outputLine);
				}
			}
		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}
}
