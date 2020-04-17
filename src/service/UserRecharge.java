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
		String regex = "^\\d{1,4}+$";//正则表达式
		String input = money;
		Pattern p = Pattern.compile(regex);//判断语句
		Matcher m = p.matcher(input);//待判断语句
		boolean flag = m.matches();//判断
		if(flag){
			BigDecimal mon = new BigDecimal(money);
			int update = CrudUtils.testUpdate("update userinfo set balance=balance+? where userid=?", mon,userinfo.getUserId());
			if(update!=0){
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("氪金成功");
				alert.show();
				return true;
			}
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("氪金失败#");
			alert.show();
			return false;
		}else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("氪金失败#");
			alert.show();
			return false;
		}
		
	}
}
