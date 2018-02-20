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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class GameView extends Application {

	private List<String> list = new ArrayList<>();
	private String p1Choice;
	private String p2Choice;
	ObservableList<String> data;

	public void start(Stage primaryStage) {
		primaryStage.setTitle("GameView");

		//Createing Pane
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

		// Defining the Submit button
		Button send = new Button("Send");
		send.setTranslateX(30);
		send.setTranslateY(450);

		// Defining the Rock button
		Button rockBtn = new Button("Rock");
		rockBtn.setTranslateX(30);
		rockBtn.setTranslateY(350);

		// Defining the Paper button
		Button paperBtn = new Button("Paper");
		// paper.setAlignment(Pos.BOTTOM_CENTER);
		paperBtn.setTranslateX(30);
		paperBtn.setTranslateY(350);

		// Defining the Scissors button
		Button scissorsBtn = new Button("Scissors");
		scissorsBtn.setTranslateX(30);
		scissorsBtn.setTranslateY(350);

		// Setting an action for the Submit button
		send.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String message = chatField.getText();

				// If empty message --> do not display
				if (message.equals("")) {
					return;
				}

				list.add("You: " + message);
				data = FXCollections.observableList(list);

				view.setItems(data);

				rockBtn.setDisable(false);
				paperBtn.setDisable(false);
				scissorsBtn.setDisable(false);

				chatField.clear();

			}
		});

		// Setting an action for the rock button
		rockBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String message = "***...waiting for opponent...***";

				list.add(message);
				data = FXCollections.observableList(list);

				view.setItems(data);

				// Unable to make another move until the opponent has
				rockBtn.setDisable(true);
				paperBtn.setDisable(true);
				scissorsBtn.setDisable(true);

				p1Choice = "rock";
				System.out.println(p1Choice);

			}

		});

		// Setting an action for the paper button
		paperBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String message = "***...waiting for opponent...***";

				list.add(message);
				data = FXCollections.observableList(list);

				view.setItems(data);

				// Unable to make another move until the opponent has
				rockBtn.setDisable(true);
				paperBtn.setDisable(true);
				scissorsBtn.setDisable(true);

				p1Choice = "paper";
				System.out.println(p1Choice);

			}

		});

		// Setting an action for the scissors button
		scissorsBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				String message = "***...waiting for opponent...***";

				list.add(message);
				data = FXCollections.observableList(list);

				view.setItems(data);

				// Unable to make another move until the opponent has
				rockBtn.setDisable(true);
				paperBtn.setDisable(true);
				scissorsBtn.setDisable(true);

				p1Choice = "scissors";
				System.out.println(p1Choice);


			}

		});

		root.getChildren().addAll(send, chatField, rockBtn, paperBtn, scissorsBtn, view);
		primaryStage.setScene(new Scene(root, 400, 500));
		primaryStage.show();
		primaryStage.setResizable(false);

	}

	public static void main(String[] args) {
		launch(args);
	}

}