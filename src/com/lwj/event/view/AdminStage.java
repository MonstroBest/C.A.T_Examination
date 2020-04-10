package com.lwj.event.view;

import com.lwj.event.controller.UserController;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
/**
 * 
 * @author lwj
 * ���ܣ�ʵ�ֵ�¼����
 */
public class AdminStage extends Application {
	
	static UserController userController = new UserController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Label labName = new Label("�˺ţ�");
		Label labPassword = new Label("���룺");
		Label labTips = new Label("�˺Ż������������");
		labTips.setOpacity(0);
		labTips.setTextFill(Color.RED);
		labName.setFont(Font.font(20));
		labPassword.setFont(Font.font(20));
		
		TextField tfName = new TextField();
		tfName.setTooltip(new Tooltip("�������û���"));
		PasswordField pfPassword = new PasswordField();
		pfPassword.setTooltip(new Tooltip("����������"));
		
		Button btLogin = new Button("��¼");
		Button btClearOfName = new Button("���");
		Button btClearOfPassword = new Button("���");
		Button btLogon = new Button("ע��");
		
		GridPane gr = new GridPane();
		gr.setStyle("-fx-background-color:#FFF0F595");//#FFF5EE
		
		gr.add(labName, 0, 0);
		gr.add(tfName, 1, 0);
		gr.add(labPassword, 0, 1);
		gr.add(pfPassword, 1, 1);
		gr.add(labTips, 1, 3);
		gr.add(btLogon, 1, 2);
		gr.add(btClearOfPassword, 2, 1);
		gr.add(btClearOfName, 2, 0);
		gr.add(btLogin, 1, 2);
		
		gr.setAlignment(Pos.CENTER);
		GridPane.setMargin(btLogin, new Insets(0, 0, 0, 200));
		gr.setHgap(10);//ˮƽ���
		gr.setVgap(18);//��ֱ���
		
		Scene adminScene = new Scene(gr);
		Stage adminStage = new Stage();
		adminStage.setScene(adminScene);
		adminStage.setTitle("LPL��Ʊϵͳ��¼����");
		adminStage.getIcons().add(new Image("./com/lwj/event/view/LPL.png"));//���ô���ͼ��
		adminStage.setWidth(500);
		adminStage.setHeight(300);
		adminStage.setResizable(false);
		
		adminStage.show();
		
		/**
		 * 	���ܣ���������ʵ�ֵ�¼����
		 */
		btLogin.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				String name = tfName.getText();
				String password = pfPassword.getText();
				if(userController.login(name, password)){
					System.out.println("��½�ɹ�");
					//MyWindow window = new MyWindow();
					adminStage.close();
					PriStage priStage = new PriStage();
				}else{
					labTips.setOpacity(1);
					labName.setTextFill(Color.RED);
					labPassword.setTextFill(Color.RED);
					System.out.println("��½ʧ��");
					primaryStage.setTitle("�˺Ż��������");
				
					FadeTransition fade = new FadeTransition();
					fade.setDuration(Duration.seconds(0.5));
					fade.setNode(gr);
					fade.setFromValue(0);
					fade.setToValue(1);
					fade.play();
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
		
		btLogon.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				String name = tfName.getText();
				String password = pfPassword.getText();
				if(userController.logon(name, password)){
					System.out.println("ע��ɹ������¼");
				}else{
					labTips.setOpacity(1);
					labName.setTextFill(Color.GREEN);
					labPassword.setTextFill(Color.GREEN);
					System.out.println("�˺��ѱ�ע�ᣬ�뻻���˺�");
//					primaryStage.setTitle("�˺��ѱ�ע�ᣬ�뻻���˺�");
//				
					FadeTransition fade = new FadeTransition();
					fade.setDuration(Duration.seconds(0.5));
					fade.setNode(gr);
					fade.setFromValue(0);
					fade.setToValue(1);
					fade.play();
				}
			}
			
		});
		
	}

}

class PriStage extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	public PriStage(){
		Stage priStage = new Stage();
		Label labTest = new Label("��½�ɹ�");
		labTest.setTextFill(Color.BLUE);
		Group group = new Group();
		group.getChildren().add(labTest);
		Scene scene = new Scene(group);
		priStage.setScene(scene);
		priStage.setTitle("LPL��Ʊϵͳ");
		priStage.setWidth(1280);
		priStage.setHeight(960);
		priStage.show();
	}
	
}



