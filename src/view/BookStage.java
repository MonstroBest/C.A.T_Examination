package view;

import controller.UserController;
import dao.CrudUtils;
import entity.Game;
import entity.UserInfo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BookStage extends Application{
static UserController userController = new UserController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

	}
	
	public BookStage(Game bookGame,Stage ownerStage,UserInfo userinfo){
		Stage bookStage = new Stage();
		
		Button btBook = new Button("预订赛事");//按钮：预定赛事
		btBook.setPrefWidth(100.0);
		btBook.setPrefHeight(50.0);
		Button btCancelBook = new Button("取消订单");
		btCancelBook.setPrefWidth(100.0);
		btCancelBook.setPrefHeight(50.0);
		//查询订单，如果有订单记录，不能下单
		if(CrudUtils.querySingleOfLog("select count(*) from book where userid=? and game_id=?", userinfo.getUserId(),bookGame.getGameId())){
			btBook.setDisable(true);//按钮不可用
		}
		//查询订单，如果没有订单记录，不能取消订单
		if(!CrudUtils.querySingleOfLog("select count(*) from book where userid=? and game_id=?", userinfo.getUserId(),bookGame.getGameId())){
			btCancelBook.setDisable(true);
		}
		
		TextArea ta = new TextArea();
		ta.setEditable(false);
		if(bookGame.getIntroduction()!=null){
			ta.setText("对阵双方:\n\t"+bookGame.getTeam1()+" vs "+bookGame.getTeam2()+"\n时间:\n\t"+bookGame.getDate()+"\n票价:\n\t"+bookGame.getSellingPrice()+"\n剩余门票:\n\t"+bookGame.getSurplus()+"张"+"\n介绍:\n\t"+bookGame.getIntroduction());
		}else{
			ta.setText("对阵双方:\n\t"+bookGame.getTeam1()+" vs "+bookGame.getTeam2()+"\n时间:\n\t"+bookGame.getDate()+"\n票价:\n\t"+bookGame.getSellingPrice()+"\n剩余门票:\n\t"+bookGame.getSurplus()+"张");
		}
		
		AnchorPane an = new AnchorPane();
		AnchorPane.setTopAnchor(ta, 100.0);
		AnchorPane.setLeftAnchor(ta, 100.0);
		AnchorPane.setLeftAnchor(btCancelBook, 100.0);
		an.getChildren().addAll(btBook,ta,btCancelBook);
		
		btBook.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					userController.userBook(userinfo,  bookGame);
			}
		});
		btCancelBook.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CrudUtils.testUpdate("delete from book where userid=? and game_id=?", userinfo.getUserId(),bookGame.getGameId());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("取消订单成功~");
				alert.show();
			}
			
		});
		
//		ListenStageChangedUtils.listenStageChanged(bookStage);
		Scene scene = new Scene(an);
//		ModalityUtils.initModality(ownerStage, bookStage);
		bookStage.setScene(scene);
		bookStage.setWidth(1000);
		bookStage.setHeight(700);
		bookStage.show();
	}
}
