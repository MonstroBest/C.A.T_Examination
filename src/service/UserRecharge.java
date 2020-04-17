package service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.CrudUtils;
import entity.UserInfo;
import javafx.scene.control.Alert;

public class UserRecharge {
	public static boolean userRecharge(String money,UserInfo userinfo){
		
		//^\d{1,4}+$
		String regex = "^\\d{1,4}+$";//������ʽ
		String input = money;
		Pattern p = Pattern.compile(regex);//�ж����
		Matcher m = p.matcher(input);//���ж����
		boolean flag = m.matches();//�ж�
		if(flag){
			BigDecimal mon = new BigDecimal(money);
			int update = CrudUtils.testUpdate("update userinfo set balance=balance+? where userid=?", mon,userinfo.getUserId());
			if(update!=0){
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("봽�ɹ�");
				alert.show();
				return true;
			}
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("봽�ʧ��#");
			alert.show();
			return false;
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("봽�ʧ��#");
			alert.show();
			return false;
		}
		
	}
}
