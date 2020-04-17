package service;

import java.math.BigDecimal;

import dao.CrudUtils;
import entity.Game;
import entity.UserInfo;
import javafx.scene.control.Alert;

public class UserBook {
	/**
	 * ���ܣ�Ԥ������
	 * @param id
	 * @param gameId
	 * @param t
	 * @return
	 */
	public static boolean userBook(UserInfo userinfo,Game t){
		//Ԥ������
		BigDecimal balance = BigDecimal.ZERO;
		if(CrudUtils.querySingle("select balance from userinfo where userid=?",userinfo.getUserId())!=null){
			balance = (BigDecimal) CrudUtils.querySingle("select balance from userinfo where userid=?",userinfo.getUserId());
		}
		BigDecimal sellingPrice = BigDecimal.ZERO;
		if(CrudUtils.querySingle("select selling_price from game where game_id=?", t.getGameId())!=null){
			sellingPrice = (BigDecimal) CrudUtils.querySingle("select selling_price from game where game_id=?", t.getGameId());
		}
		Object con = CrudUtils.querySingle("select consumption from userinfo where userid=?", userinfo.getUserId());
		if(con!=null){
			int consumption = Integer.parseInt(String.valueOf(con));
		}
		int surplus = (int) CrudUtils.querySingle("select surplus from game where game_id=?", t.getGameId());
		
		System.out.println("balance:"+balance);
		System.out.println("sellingPrice:"+sellingPrice);
		System.out.println("getAll:"+t.getAll());
		
//		BigDecimal vip = BigDecimal.ZERO;
//		if(consumption >= 1000){
//			vip = BigDecimal.valueOf((double)consumption % 1000);
//		}
		
		//BigDecimal payment = sellingPrice.multiply((BigDecimal.ONE.subtract(vip.divide(BigDecimal.TEN).divide(BigDecimal.TEN).multiply(BigDecimal.ONE.add(BigDecimal.ONE)))));
		BigDecimal payment = sellingPrice;
		System.out.println("payment:"+payment);
		
		if(balance.compareTo(sellingPrice)==1 && surplus>0){//1:���ڣ�-1��С��
			CrudUtils.testUpdate("UPDATE userinfo SET balance=balance-? WHERE userid=?",payment,userinfo.getUserId());
			CrudUtils.testUpdate("UPDATE userinfo SET consumption=consumption+? WHERE userid=?",payment,userinfo.getUserId());
			CrudUtils.testUpdate("update game set surplus=surplus-1 where game_id=?",t.getGameId());
			CrudUtils.testUpdate("insert into book set userid=?,game_id=?,payment=?",userinfo.getUserId(),t.getGameId(),payment);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Ԥ���ɹ�");
			alert.show();
			return true;
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Ԥ��ʧ�ܣ������������Ʊ������");
			alert.show();
			System.out.println("���㣬��봽�#");
			return false;
		}
	}
}
