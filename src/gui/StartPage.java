package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartPage extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label response = new Label("Select user to play against");
		ListView<String> lvUsers;
		
		Text title = new Text("Rock, Papper & Scissors");
		
		FlowPane root = new FlowPane(10,10);
		root.setAlignment(Pos.CENTER);
		
		ObservableList<String> Users =
				FXCollections.observableArrayList("André", "Daniel", "Gustav", "John");
		
		lvUsers = new ListView<String>(Users);
		lvUsers.setPrefSize(300, 150);
		
		MultipleSelectionModel<String> lvSelModel =
              lvUsers.getSelectionModel();
		lvSelModel.selectedItemProperty().addListener((new ChangeListener<String>() {
			public void changed (ObservableValue<? extends String> changed,
					String oldVal, String newVal) {
				response.setText("You will play against: " + newVal);
			}
		}));
		
		root.getChildren().add(title);
		root.getChildren().add(lvUsers);
		root.getChildren().add(response);
		
		Scene scene = new Scene(root, 350, 300);
		
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
