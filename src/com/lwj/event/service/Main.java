package com.lwj.event.service;

import com.lwj.event.view.AdminStage;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	/**
	 * 功能：调用view层的AdminStage类，实现登录窗口
	 */
	public void start(Stage primaryStage) throws Exception {
		Stage stage = new Stage();
		AdminStage adminStage = new AdminStage();
		adminStage.start(stage);
	}
	
	
}
