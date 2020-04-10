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
	 * ���ܣ�����view���AdminStage�࣬ʵ�ֵ�¼����
	 */
	public void start(Stage primaryStage) throws Exception {
		Stage stage = new Stage();
		AdminStage adminStage = new AdminStage();
		adminStage.start(stage);
	}
	
	
}
