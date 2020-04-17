package view;

import controller.UserController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.UserLogin;

/**
 * 功能：登录窗口
 * @author lwj
 *
 */
public class LogStage extends Application{
	static UserController userController = new UserController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	public LogStage(){
		/**
		 * 提示标签
		 */
		Label labName = new Label("账号：");
		Label labPassword = new Label("密码：");
		Label labLoginTips = new Label("账号或密码输入错误！");
		Label labLogonTips = new Label("用户名已存在！");
		labLoginTips.setOpacity(0);//设置透明
		labLoginTips.setTextFill(Color.RED);
		labLogonTips.setOpacity(0);
		labLogonTips.setTextFill(Color.ORANGERED);
		labName.setFont(Font.font(20));//设置字体
		labPassword.setFont(Font.font(20));
		
		/**
		 * 账号输入框和密码输入框
		 */
		TextField tfName = new TextField();
		tfName.setTooltip(new Tooltip("请输入用户名"));//提示
		PasswordField pfPassword = new PasswordField();
		pfPassword.setTooltip(new Tooltip("请输入密码"));
		
		/**
		 * 注册、登录、清除按钮
		 */
		Button btAdminLogin = new Button("管理员登录");
		Button btLogin = new Button("登录");
		Button btClearOfName = new Button("清除");
		Button btClearOfPassword = new Button("清除");
		Button btLogon = new Button("注册");
		
		/**
		 * 网格布局,设置控件位置
		 */
		GridPane gr = new GridPane();
		gr.setStyle("-fx-background-color:#FFF0F595");//背景颜色
		gr.add(labName, 0, 0);
		gr.add(tfName, 1, 0);
		gr.add(labPassword, 0, 1);
		gr.add(pfPassword, 1, 1);
		gr.add(labLoginTips, 1, 3);
		gr.add(labLogonTips, 1, 3);
		gr.add(btLogon, 1, 2);
		gr.add(btClearOfPassword, 2, 1);
		gr.add(btClearOfName, 2, 0);
		gr.add(btLogin, 1, 2);
		gr.add(btAdminLogin, 1, 2);
		gr.setAlignment(Pos.CENTER);//子节点居中对齐
		GridPane.setMargin(btLogin, new Insets(0, 0, 0, 200));//调整子节点位置
		GridPane.setMargin(btAdminLogin, new Insets(0, 0, 0, 74));
		gr.setHgap(10);//子节点间水平间距
		gr.setVgap(18);//子节点间垂直间距
		
		/**
		 * stage、scene
		 */
		Scene logScene = new Scene(gr);
		Stage logStage = new Stage();
		logStage.setScene(logScene);
		logStage.setTitle("LPL售票系统登录窗口");
		logStage.getIcons().add(new Image("./view/LPL.png"));//设置窗口图标
		logStage.setWidth(500);
		logStage.setHeight(300);
		logStage.setResizable(false);//设置不可调节窗口大小
		
		/**
		 * 	功能：监听器，实现登录功能
		 */
		btLogin.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				String name = tfName.getText();
				String password = pfPassword.getText();
				try {
					
					if(userController.userLogin(name, password)){
						System.out.println("登陆成功");
						logStage.close();
						PriStage priStage = new PriStage(UserLogin.userInfo);
					}else{
						labLogonTips.setOpacity(0);
						labLoginTips.setOpacity(1);
						labName.setTextFill(Color.RED);
						labPassword.setTextFill(Color.RED);
						System.out.println("登陆失败");
						logStage.setTitle("账号或密码错误");
						
						FadeTransition fade = new FadeTransition();
						fade.setDuration(Duration.seconds(0.5));
						fade.setNode(gr);
						fade.setFromValue(0);
						fade.setToValue(1);
						fade.play();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
//		btAdminLogin.setOnAction(new EventHandler<ActionEvent>(){
//			
//			@Override
//			public void handle(ActionEvent event) {
//				String name = tfName.getText();
//				String password = pfPassword.getText();
//				if(userController.adminLogin(name, password)){
//					System.out.println("登陆成功");
//					//MyWindow window = new MyWindow();
//					logStage.close();
//					PriStage priStage = new PriStage(UserLogin.user);
//				}else{
//					labLoginTips.setOpacity(1);
//					labName.setTextFill(Color.RED);
//					labPassword.setTextFill(Color.RED);
//					System.out.println("登陆失败");
//					logStage.setTitle("账号或密码错误");
//				
//					FadeTransition fade = new FadeTransition();
//					fade.setDuration(Duration.seconds(0.5));
//					fade.setNode(gr);
//					fade.setFromValue(0);
//					fade.setToValue(1);
//					fade.play();
//				}
//			}
//			
//		});
		
		btLogon.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				
				try {
					if(userController.userLogon(tfName.getText(),pfPassword.getText())){
						System.out.println("注册成功，请登录");
					}else{
						labLoginTips.setOpacity(0);
						labLogonTips.setOpacity(1);
						labName.setTextFill(Color.GREEN);
						labPassword.setTextFill(Color.GREEN);
						System.out.println("账号已被注册，请换个账号");
//				
						FadeTransition fade = new FadeTransition();
						fade.setDuration(Duration.seconds(0.5));
						fade.setNode(gr);
						fade.setFromValue(0);
						fade.setToValue(1);
						fade.play();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		btClearOfName.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
						tfName.setText("");
			}
			
		});
		
		btClearOfPassword.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
						pfPassword.setText("");
			}
			
		});
		
		
		
		
		
		logStage.show();
	}
}
