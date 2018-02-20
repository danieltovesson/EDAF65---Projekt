package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import server.Server;

public class RockPaperScissors extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		new GUI(primaryStage);
	}
}
