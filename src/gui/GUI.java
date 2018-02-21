package gui;

import client.Client;
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

public class GUI {

	private TextField userTextField;

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
		Text scenetitle = new Text("Enter username:");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		// Creates a label
		Label userName = new Label("Username: ");
		grid.add(userName, 0, 1);

		// Creates a text field
		userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		// Create "Create User: " button
		Button create = new Button("Create User");
		grid.add(create, 1, 4);

		// Opens a new view after having created a user
		create.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Creates a string from userTextField input
				if (!userTextField.getText().equals("")) {

					// Creates ObserveableList
					ObservableList<String> users = FXCollections.observableArrayList();

					// Create client
					Client client = new Client(userTextField.getText(), users);

					// Try to start client
					if (client.start()) {

						// Create FlowPane for StartPage
						FlowPane flow = new FlowPane(50, 50);
						flow.setAlignment(Pos.CENTER);

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
								response.setText("You will play against: " + newVal);
							}
						}));

						// Adds elements to FlowPane
						flow.getChildren().add(title);
						flow.getChildren().add(user);
						flow.getChildren().add(lvUsers);
						flow.getChildren().add(response);
						flow.getChildren().add(play);

						// Show stage
						Stage stage = new Stage();
						stage.setResizable(false);
						stage.setTitle("Game");
						Scene scene = new Scene(flow, 300, 400);
						stage.setScene(scene);
						stage.show();
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Fel");
						alert.setHeaderText("Kunde inte skapa användaren");
						alert.showAndWait();
					}

				} else {
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
}
