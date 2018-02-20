package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GameView extends Application {

	private List<String> list = new ArrayList<>();
	private String p1Choice;
	private String p2Choice;
	ObservableList<String> data;

	/**
	 * Creates a window environment for the game and chat function. User inputs
	 * strings in a TextField object and display them in a ListView object by
	 * pressing a Button object. Furthermore, three Button objects is accessible
	 * to play the game. The result of the game is displayed in the ListView
	 * object.
	 *
	 * @param primaryStage
	 *            the Stage object representing the FlowPane used
	 */
	public void start(Stage primaryStage) {
		primaryStage.setTitle("GameView");

		// Creating Pane
		FlowPane root = new FlowPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(15, 15, 15, 15));

		// Defining the window for the chat
		ListView<String> view = new ListView<String>();
		view.setPrefSize(370, 300);
		view.setEditable(false);

		// Defining the Chat text field
		TextField chatField = new TextField();
		chatField.setPromptText("Message");
		chatField.getText();
		chatField.setPrefWidth(250);
		chatField.setTranslateX(30);
		chatField.setTranslateY(450);

		// Defining the send button
		Button send = new Button("Send");
		send.setTranslateX(30);
		send.setTranslateY(450);

		// Defining the rock button
		Button rockBtn = new Button("Rock");
		rockBtn.setTranslateX(30);
		rockBtn.setTranslateY(350);

		// Defining the paper button
		Button paperBtn = new Button("Paper");
		paperBtn.setTranslateX(30);
		paperBtn.setTranslateY(350);

		// Defining the scissors button
		Button scissorsBtn = new Button("Scissors");
		scissorsBtn.setTranslateX(30);
		scissorsBtn.setTranslateY(350);

		// Setting an action for the send button
		send.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// Retrieves string from TextField
				String message = chatField.getText();

				// If empty message --> do not display
				if (message.equals("")) {
					return;
				}

				// Adds string to ArrayList object
				list.add("You: " + message);

				// Display the string in the TableView
				data = FXCollections.observableList(list);
				view.setItems(data);

				// Make game buttons accessible
				rockBtn.setDisable(false);
				paperBtn.setDisable(false);
				scissorsBtn.setDisable(false);

				// Clear the TextField after string is sent and displayed in
				// TableView
				chatField.clear();

			}
		});

		// Setting an action for the rock button
		rockBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				// String to display when button is pressed
				String message = "***...waiting for opponent...***";

				// Adds string to ArrayList object
				list.add(message);

				// Display the string in the TableView
				data = FXCollections.observableList(list);
				view.setItems(data);

				// Make game buttons unaccessible until opponent makes a move
				rockBtn.setDisable(true);
				paperBtn.setDisable(true);
				scissorsBtn.setDisable(true);

				// Player1s choice is set
				p1Choice = "rock";
				System.out.println(p1Choice);

			}

		});

		// Setting an action for the paper button
		paperBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				// String to display when button is pressed
				String message = "***...waiting for opponent...***";

				// Adds string to ArrayList object
				list.add(message);

				// Display the string in the TableView
				data = FXCollections.observableList(list);
				view.setItems(data);

				// Make game buttons unaccessible until opponent makes a move
				rockBtn.setDisable(true);
				paperBtn.setDisable(true);
				scissorsBtn.setDisable(true);

				// Player1s choice is set
				p1Choice = "paper";
				System.out.println(p1Choice);

			}

		});

		// Setting an action for the scissors button
		scissorsBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				// String to display when button is pressed
				String message = "***...waiting for opponent...***";

				// Adds string to ArrayList object
				list.add(message);

				// Display the string in the TableView
				data = FXCollections.observableList(list);
				view.setItems(data);

				// Make game buttons unaccessible until opponent makes a move
				rockBtn.setDisable(true);
				paperBtn.setDisable(true);
				scissorsBtn.setDisable(true);

				// Player1s choice is set
				p1Choice = "scissors";
				System.out.println(p1Choice);

			}

		});

		// Add all components to the FlowPane and make it visible
		root.getChildren().addAll(send, chatField, rockBtn, paperBtn, scissorsBtn, view);
		primaryStage.setScene(new Scene(root, 400, 500));
		primaryStage.show();

		// Make window size fixed
		primaryStage.setResizable(false);

	}

	public static void main(String[] args) {
		launch(args);
	}

}