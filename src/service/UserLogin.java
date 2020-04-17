package service;

import java.math.BigDecimal;
import java.sql.Timestamp;

import dao.CrudUtils;
import entity.UserInfo;
import entity.UserLog;
import utils.RsaUtils;

public class UserLogin {
	public static UserInfo userInfo = null;
	
	public static boolean userLogin(String userid,String password) throws Exception{
		
		String publicKey = (String) CrudUtils.querySingle("select public_Key from rsa where userid=?", userid);
		String privateKey = (String) CrudUtils.querySingle("select private_key from rsa where userid=?", userid);
		System.out.println(privateKey.length());
		
		//登录比对操作
		String messageEn;
		Double balance;
		int id;
		
		System.out.println("password="+password);
		if(CrudUtils.querySingleOfLog("select count(*) from userlog where userid = ?", userid)){//如果用户名存在
			messageEn = (String) CrudUtils.querySingle("select password from userlog where userid=?", userid);//取出密文
		}else{//如果用户名不存在
			return false;
		}
		
		System.out.println("公钥="+publicKey);
		System.out.println("私钥="+privateKey);
		
		String messageDe = RsaUtils.decrypt(messageEn, privateKey);//用对应的私钥加密用户输入的明文
		System.out.println("messageDe="+messageDe);
		System.out.println(messageDe.equals(password));
		if(!messageDe.equals(password)){//如果用户输入的明文和数据库解密后的明文不相等
			return false;
		}else{
			BigDecimal bal = (BigDecimal)CrudUtils.querySingle("select balance from userinfo where userid =?", userid);
//			balance = bal.doubleValue();
			String name = (String) CrudUtils.querySingle("select name from userinfo where userid =?", userid);
			String phoneNumber = (String) CrudUtils.querySingle("select phonenumber from userinfo where userid =?", userid);
			
			BigDecimal consumption = (BigDecimal) CrudUtils.querySingle("select consumption from userinfo where userid =?", userid);
			Timestamp suspendedTime = (Timestamp) CrudUtils.querySingle("select suspended_time from userinfo where userid =?", userid);
			if(userid!=null){
//				userInfo.setUserId(userid);
//				userInfo.setBalance(bal);
//				userInfo.setName(name);
//				userInfo.setConsumption(consumption);
//				userInfo.setPhoneNumber(phoneNumber);
//				userInfo.setSuspendedTime(suspendedTime);
				userInfo = new UserInfo(userid);
				return true;
			}else{
				return false;
			}
			
			
			
		}
		//返回结果
	}
}
