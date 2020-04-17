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
 * ���ܣ���¼����
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
		 * ��ʾ��ǩ
		 */
		Label labName = new Label("�˺ţ�");
		Label labPassword = new Label("���룺");
		Label labLoginTips = new Label("�˺Ż������������");
		Label labLogonTips = new Label("�û����Ѵ��ڣ�");
		labLoginTips.setOpacity(0);//����͸��
		labLoginTips.setTextFill(Color.RED);
		labLogonTips.setOpacity(0);
		labLogonTips.setTextFill(Color.ORANGERED);
		labName.setFont(Font.font(20));//��������
		labPassword.setFont(Font.font(20));
		
		/**
		 * �˺����������������
		 */
		TextField tfName = new TextField();
		tfName.setTooltip(new Tooltip("�������û���"));//��ʾ
		PasswordField pfPassword = new PasswordField();
		pfPassword.setTooltip(new Tooltip("����������"));
		
		/**
		 * ע�ᡢ��¼�������ť
		 */
		Button btAdminLogin = new Button("����Ա��¼");
		Button btLogin = new Button("��¼");
		Button btClearOfName = new Button("���");
		Button btClearOfPassword = new Button("���");
		Button btLogon = new Button("ע��");
		
		/**
		 * ���񲼾�,���ÿؼ�λ��
		 */
		GridPane gr = new GridPane();
		gr.setStyle("-fx-background-color:#FFF0F595");//������ɫ
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
		gr.setAlignment(Pos.CENTER);//�ӽڵ���ж���
		GridPane.setMargin(btLogin, new Insets(0, 0, 0, 200));//�����ӽڵ�λ��
		GridPane.setMargin(btAdminLogin, new Insets(0, 0, 0, 74));
		gr.setHgap(10);//�ӽڵ��ˮƽ���
		gr.setVgap(18);//�ӽڵ�䴹ֱ���
		
		/**
		 * stage��scene
		 */
		Scene logScene = new Scene(gr);
		Stage logStage = new Stage();
		logStage.setScene(logScene);
		logStage.setTitle("LPL��Ʊϵͳ��¼����");
		logStage.getIcons().add(new Image("./view/LPL.png"));//���ô���ͼ��
		logStage.setWidth(500);
		logStage.setHeight(300);
		logStage.setResizable(false);//���ò��ɵ��ڴ��ڴ�С
		
		/**
		 * 	���ܣ���������ʵ�ֵ�¼����
		 */
		btLogin.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				String name = tfName.getText();
				String password = pfPassword.getText();
				try {
					
					if(userController.userLogin(name, password)){
						System.out.println("��½�ɹ�");
						logStage.close();
						PriStage priStage = new PriStage(UserLogin.userInfo);
					}else{
						labLogonTips.setOpacity(0);
						labLoginTips.setOpacity(1);
						labName.setTextFill(Color.RED);
						labPassword.setTextFill(Color.RED);
						System.out.println("��½ʧ��");
						logStage.setTitle("�˺Ż��������");
						
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
//					System.out.println("��½�ɹ�");
//					//MyWindow window = new MyWindow();
//					logStage.close();
//					PriStage priStage = new PriStage(UserLogin.user);
//				}else{
//					labLoginTips.setOpacity(1);
//					labName.setTextFill(Color.RED);
//					labPassword.setTextFill(Color.RED);
//					System.out.println("��½ʧ��");
//					logStage.setTitle("�˺Ż��������");
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
						System.out.println("ע��ɹ������¼");
					}else{
						labLoginTips.setOpacity(0);
						labLogonTips.setOpacity(1);
						labName.setTextFill(Color.GREEN);
						labPassword.setTextFill(Color.GREEN);
						System.out.println("�˺��ѱ�ע�ᣬ�뻻���˺�");
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
