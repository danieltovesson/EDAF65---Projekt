package gui;

import java.util.ArrayList;
import java.util.List;

import client.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI {

	private Stage stage;
	private TextField serverTextField;
	private Stage gameStage;
	private TextField userTextField;
	private Client client;
	private String selectedUser;
	private List<String> list = new ArrayList<>();
	private ListView<String> view;
	private ObservableList<String> data;
	private Button rockBtn;
	private Button paperBtn;
	private Button scissorsBtn;

	/**
	 * 
	 * Creates the GUI object.
	 * 
	 * @param primaryStage
	 */
	public GUI(Stage primaryStage) {

		// Creates a grid pane for the first view
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		// Creates sceneTitle and adds font
		Text serverTitle = new Text("Enter server:");
		serverTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(serverTitle, 0, 0, 2, 1);

		// Creates a label
		Label server = new Label("Server: ");
		grid.add(server, 0, 1);

		// Creates a text field
		serverTextField = new TextField();
		grid.add(serverTextField, 1, 1);

		// Creates sceneTitle and adds font
		Text sceneTitle = new Text("Enter username:");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sceneTitle, 0, 2, 2, 1);

		// Creates a label
		Label userName = new Label("Username: ");
		grid.add(userName, 0, 3);

		// Creates a text field
		userTextField = new TextField();
		grid.add(userTextField, 1, 3);

		// Create "Create User: " button
		Button create = new Button("Create User");
		grid.add(create, 1, 5);

		// Save reference to GUI
		GUI gui = this;

		// Opens a new view after having created a user
		create.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Creates a string from userTextField input
				if (!userTextField.getText().equals("") && !serverTextField.getText().equals("")) {

					// Creates ObserveableList
					ObservableList<String> users = FXCollections.observableArrayList();

					// Create client
					client = new Client(userTextField.getText(), users, gui, serverTextField.getText());

					// Try to start client
					if (client.start()) {

						// Create FlowPane for StartPage
						FlowPane flow = new FlowPane(50, 50);
						flow.setAlignment(Pos.CENTER);
						flow.setHgap(100);
						flow.setVgap(20);
						flow.setPadding(new Insets(15, 15, 15, 15));

						// Create response Label
						Label response = new Label("Select user to play against");

						// Create user label
						Label user = new Label("Active as: " + userTextField.getText());
						user.setAlignment(Pos.CENTER_LEFT);

						// Create Play button
						Button play = new Button("Play");

						// Create title for view
						Text title = new Text("Rock, Papper & Scissors");

						// Creates a list view to show observable list
						ListView<String> lvUsers = new ListView<String>(users);
						lvUsers.setPrefSize(150, 150);

						// When user is clicked upon, opponents name is showing
						MultipleSelectionModel<String> lvSelModel = lvUsers.getSelectionModel();
						lvSelModel.selectedItemProperty().addListener((new ChangeListener<String>() {
							public void changed(ObservableValue<? extends String> changed, String oldVal,
									String newVal) {
								selectedUser = newVal;
								response.setText("You will play against: " + newVal);
							}
						}));

						// Adds elements to FlowPane
						flow.getChildren().add(title);
						flow.getChildren().add(user);
						flow.getChildren().add(lvUsers);
						flow.getChildren().add(response);
						flow.getChildren().add(play);

						// Show list stage
						stage = new Stage();
						stage.setResizable(false);
						stage.setTitle("Game");
						Scene scene = new Scene(flow, 300, 400);
						stage.setScene(scene);
						stage.show();
						primaryStage.hide();

						// Opens a new view when play button is pushed
						play.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {

								// Try to connect users
								client.connectTo(selectedUser);

							}

						});

						// Check if user closes window
						stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

							@Override
							public void handle(WindowEvent event) {
								Platform.runLater(new Runnable() {

									@Override
									public void run() {
										// Remove user and close socket
										client.quit();
									}
								});
							}
						});

					} else {
						// Alert if client could not connect to server
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Fel");
						alert.setHeaderText("Kunde inte skapa användaren");
						alert.showAndWait();
					}

				} else {
					// Alert if no user name is typed
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fel");
					alert.setHeaderText("Du måste ange ett användarnamn");
					alert.showAndWait();
				}
			}
		});

		// Show primary stage
		primaryStage.setResizable(false);
		primaryStage.setTitle("User");
		Scene scene = new Scene(grid, 300, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Show game view
	 */
	public void showGameView() {

		// Creating flow pane
		FlowPane root = new FlowPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(15, 15, 15, 15));

		// Defining the window for the chat
		view = new ListView<String>();
		view.setPrefSize(370, 300);
		view.setEditable(false);

		// Creating label to show user name
		Label userName = new Label("Active as: " + userTextField.getText());
		userName.setAlignment(Pos.CENTER);

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
		rockBtn = new Button("Rock");
		rockBtn.setTranslateX(30);
		rockBtn.setTranslateY(350);

		// Defining the paper button
		paperBtn = new Button("Paper");
		paperBtn.setTranslateX(30);
		paperBtn.setTranslateY(350);

		// Defining the scissors button
		scissorsBtn = new Button("Scissors");
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

				// Send message
				client.sendMessage(message);

				// Clear the TextField after string is sent and displayed in
				// TableView
				chatField.clear();

			}
		});

		// Setting an action for the rock button
		rockBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				setPlayerChoice("rock");
			}
		});

		// Setting an action for the paper button
		paperBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				setPlayerChoice("paper");
			}
		});

		// Setting an action for the scissors button
		scissorsBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				setPlayerChoice("scissors");
			}
		});

		// Add all components to the FlowPane and make it visible
		gameStage = new Stage();
		stage.setTitle("GameView");
		root.getChildren().addAll(send, chatField, rockBtn, paperBtn, scissorsBtn, userName, view);
		Scene gameScene = new Scene(root, 390, 500);
		gameStage.setScene(gameScene);
		gameStage.show();

		// Check if user closes window
		gameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// Disconnect users
						client.disconnectFromOpponent();
					}
				});
			}
		});
	}

	/**
	 * Closes game view
	 */
	public void closeGameView() {
		gameStage.hide();
	}

	/**
	 * Sets the players choice
	 * 
	 * @param choice
	 *            the choice
	 */
	private void setPlayerChoice(String choice) {

		// Set client choice
		client.setChoice(choice);

	}

	/**
	 * Adds a message to the chat window
	 * 
	 * @param message
	 *            the message to add
	 */
	public void addMessage(String message) {

		// Adds string to ArrayList object
		list.add(message);

		// Display the string in the TableView
		data = FXCollections.observableList(list);
		view.setItems(data);

	}

	/**
	 * Sets the choice of the opponents
	 * 
	 * @param user
	 *            the user who made the choice
	 * @param choice
	 *            the choice
	 */
	public void setChoice(String user, String choice) {

		// Checks if the client or the opponent made the choice
		if (client.getName().equals(user)) {

			// Adds string to ArrayList object
			list.add("You choose " + choice);

			// Make game buttons unaccessible
			rockBtn.setDisable(true);
			paperBtn.setDisable(true);
			scissorsBtn.setDisable(true);

		} else {
			list.add("Your opponent has chosen!");
		}

		// Display the string in the TableView
		data = FXCollections.observableList(list);
		view.setItems(data);
	}

	/**
	 * Sets the result of the opponents
	 * 
	 * @param user
	 *            the user who got the result
	 * @param result
	 *            the result
	 */
	public void setResult(String user, String result) {

		if (result.equals("draw")) {

			// Print it's a draw message
			list.add("It's a draw!");
		} else {

			// Print winner
			if (user.equals(client.getName())) {
				user = "You";
			} else {
				user = "Your opponent";
			}
			list.add(user + " " + result);
		}

		// Make game buttons accessible
		rockBtn.setDisable(false);
		paperBtn.setDisable(false);
		scissorsBtn.setDisable(false);

		// Display the string in the TableView
		data = FXCollections.observableList(list);
		view.setItems(data);
	}
}
