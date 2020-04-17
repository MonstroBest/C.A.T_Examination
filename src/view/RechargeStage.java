package view;

import entity.UserInfo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.UserRecharge;

public class RechargeStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	public RechargeStage(Stage ownerStage,UserInfo userinfo) {
		Stage rechargeStage = new Stage();
//		ListenStageChangedUtils.listenStageChanged(rechargeStage);
		
		TextField tf = new TextField();
		tf.setStyle("-fx-text-fill:red");
		tf.setPromptText("请输入充值金额：");
		tf.setPrefWidth(100);
		tf.setPrefHeight(50);
		Button bt = new Button("确认充值!");
		bt.setPrefWidth(150);
		bt.setPrefHeight(50);
		
		AnchorPane an = new AnchorPane();
		AnchorPane.setTopAnchor(tf, 200.0);
		AnchorPane.setLeftAnchor(tf, 250.0);
		AnchorPane.setTopAnchor(bt, 200.0);
		AnchorPane.setLeftAnchor(bt, 350.0);
		an.getChildren().addAll(tf,bt);
		
		
		
		
		
		bt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(UserRecharge.userRecharge(tf.getText(), userinfo)){
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText("充值成功");
					alert.initOwner(ownerStage);
					alert.show();
				}else{
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("充值失败，请检查输入金额数值是否正确噢");
					alert.initOwner(ownerStage);
					alert.show();
				}
			}
		});
		
		
		
		
		
		
		
		Scene scene = new Scene(an);
//		ModalityUtils.initModality(ownerStage, rechargeStage);
		rechargeStage.setScene(scene);
		rechargeStage.setWidth(1000);
		rechargeStage.setHeight(700);
		rechargeStage.show();
	
	}
}
