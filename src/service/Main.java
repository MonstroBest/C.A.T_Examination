package service;

import javafx.application.Application;
import javafx.stage.Stage;
import view.LogStage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LogStage logStage = new LogStage();
	}
}
