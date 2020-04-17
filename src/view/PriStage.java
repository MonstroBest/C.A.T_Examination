package view;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import dao.CrudUtils;
import entity.Game;
import entity.UserInfo;
import entity.UserLog;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class PriStage extends Application{
ObservableList<Game> obslist = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
	
	public PriStage(UserInfo userInfo){
		List<Game> listTeam = CrudUtils.queryMultiOfGame("select * from game");
		
		Stage priStage = new Stage();
		Label labTest = new Label("1");
//		labTest.setTextFill(Color.BLUE);
		
		Menu menuPersonalCenter = new Menu("��������");
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menuPersonalCenter);
		MenuItem itemRecharge = new MenuItem("��ֵ");
		menuPersonalCenter.getItems().addAll(itemRecharge);
		
		ListView<Game> listView = new ListView<Game>(obslist);
		listView.setPlaceholder(new Label("û������"));
		listView.setPrefWidth(800);
		listView.setPrefHeight(500);
		listView.setEditable(false);//�ɱ༭
		listView.setFixedCellSize(50);//���õ�Ԫ���С
		
		TextField tf = new TextField();
		tf.setStyle("-fx-text-fill:gray");
		tf.setPromptText("�������������¹ؼ��ֽ��в�ѯ��~������½�������Ԥ��������Ԥ����");
		tf.setPrefWidth(800);
		tf.setPrefHeight(30);
		
		Date d = new Date();	//��ʽ��	
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Game temp = new Game(" ", " ", null, BigDecimal.valueOf(0), 0, " ",0);
		Game temp1 = new Game("����", "�Ͷ�", Timestamp.valueOf(sim.format(d)), BigDecimal.valueOf(0), 0, " ",0);
		
		for(int i =0;i < listTeam.size();i++){
			//Team t = listTeam.get(i);
			if(listTeam.size()==0){
				listView.getItems().add(temp);
			}else if(i==0){
				listView.getItems().add(temp1);
			}
			listView.getItems().add(listTeam.get(i));
		}

		listView.getSelectionModel().select(0);//Ĭ��ѡ���һ��
		System.out.println(listView.getSelectionModel().getSelectedIndex());//���ص�ǰ����/����
		
		Group group = new Group();
		group.getChildren().add(labTest);
		
		AnchorPane an = new AnchorPane();
		AnchorPane.setTopAnchor(listView, 230.0);
		AnchorPane.setLeftAnchor(listView, 250.0);
		AnchorPane.setTopAnchor(tf, 200.0);
		AnchorPane.setLeftAnchor(tf, 250.0);
		an.getChildren().addAll(group,listView,tf,menuBar);
		
		
		/**
		 * ����ת��
		 */
		Callback<ListView<Game>,ListCell<Game>> call = TextFieldListCell.forListView(new StringConverter<Game>(){

			@Override
			public String toString(Game object) {
						
				return object.getAll();
			}

			@Override
			public Game fromString(String string) {
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				return new Game("1", "1", ts, BigDecimal.ZERO, 1, "1",1);
			}
					
		});
		listView.setCellFactory(call);//�ɱ༭
		
		/**
		 * �ı�����������
		 */
		tf.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				FilteredList<Game> fl = obslist.filtered(new Predicate<Game>() {
					
					@Override
					public boolean test(Game t) {
						if(t.getAll().contains(newValue) || t.getAll().contains("����")){
							return true;
						}else{
							return false;
						}
						
					}
				});
				
				listView.setItems(fl);
			}
			
		});
		
		/**
		 * �����ת��Ԥ������
		 */
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {

			@Override
			public void changed(ObservableValue<? extends Game> observable, Game oldValue, Game newValue) {
				//System.out.println("gameid"+newValue.getGameId());
				//System.out.println("gameid"+newValue.getAll());
				if(newValue!=null && !newValue.equals(oldValue) && !newValue.equals(temp1)){
					//tf.setPromptText("");
					//tf.requestFocus();
					
					BookStage book = new BookStage(newValue,priStage,userInfo);
					Stage bookStage = new Stage();
					try {
						book.start(bookStage);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		itemRecharge.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				RechargeStage recharge = new RechargeStage(priStage,userInfo);
				Stage rechargeStage = new Stage();
				try {
					recharge.start(rechargeStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		Scene scene = new Scene(an);
		//ListenStageChangedUtils.listenStageChanged(priStage);
		priStage.setScene(scene);
		priStage.setTitle("LPL��Ʊϵͳ");
		priStage.getIcons().add(new Image("./view/LPL.png"));//���ô���ͼ��
		priStage.setWidth(1280);
		priStage.setHeight(960);
		priStage.show();
		
	}
}
