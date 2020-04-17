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
		
		Menu menuPersonalCenter = new Menu("个人中心");
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menuPersonalCenter);
		MenuItem itemRecharge = new MenuItem("充值");
		menuPersonalCenter.getItems().addAll(itemRecharge);
		
		ListView<Game> listView = new ListView<Game>(obslist);
		listView.setPlaceholder(new Label("没有数据"));
		listView.setPrefWidth(800);
		listView.setPrefHeight(500);
		listView.setEditable(false);//可编辑
		listView.setFixedCellSize(50);//设置单元格大小
		
		TextField tf = new TextField();
		tf.setStyle("-fx-text-fill:gray");
		tf.setPromptText("在这里输入赛事关键字进行查询噢~点击赛事进行赛事预览及赛事预定！");
		tf.setPrefWidth(800);
		tf.setPrefHeight(30);
		
		Date d = new Date();	//格式化	
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Game temp = new Game(" ", " ", null, BigDecimal.valueOf(0), 0, " ",0);
		Game temp1 = new Game("主队", "客队", Timestamp.valueOf(sim.format(d)), BigDecimal.valueOf(0), 0, " ",0);
		
		for(int i =0;i < listTeam.size();i++){
			//Team t = listTeam.get(i);
			if(listTeam.size()==0){
				listView.getItems().add(temp);
			}else if(i==0){
				listView.getItems().add(temp1);
			}
			listView.getItems().add(listTeam.get(i));
		}

		listView.getSelectionModel().select(0);//默认选择第一个
		System.out.println(listView.getSelectionModel().getSelectedIndex());//返回当前索引/对象
		
		Group group = new Group();
		group.getChildren().add(labTest);
		
		AnchorPane an = new AnchorPane();
		AnchorPane.setTopAnchor(listView, 230.0);
		AnchorPane.setLeftAnchor(listView, 250.0);
		AnchorPane.setTopAnchor(tf, 200.0);
		AnchorPane.setLeftAnchor(tf, 250.0);
		an.getChildren().addAll(group,listView,tf,menuBar);
		
		
		/**
		 * 类型转换
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
		listView.setCellFactory(call);//可编辑
		
		/**
		 * 文本监听，过滤
		 */
		tf.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				FilteredList<Game> fl = obslist.filtered(new Predicate<Game>() {
					
					@Override
					public boolean test(Game t) {
						if(t.getAll().contains(newValue) || t.getAll().contains("主队")){
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
		 * 点击跳转到预订窗口
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
		priStage.setTitle("LPL购票系统");
		priStage.getIcons().add(new Image("./view/LPL.png"));//设置窗口图标
		priStage.setWidth(1280);
		priStage.setHeight(960);
		priStage.show();
		
	}
}
