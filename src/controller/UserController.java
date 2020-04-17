package controller;

import dao.CrudUtils;
import entity.Game;
import entity.UserInfo;
import service.UserBook;
import service.UserLogin;
import service.UserLogon;
import service.UserRecharge;

/**
 * 功能：处理用户模块的控制请求
 * @author lwj
 *
 */
public class UserController {
	/**
	 * 功能：注册和登录，进行账户比对
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception 
	 */
	public boolean userLogin(String userid,String password) throws Exception{
		return UserLogin.userLogin(userid, password);
	}
	
	public boolean userLogon(String userid,String password) throws Exception{
		return UserLogon.userLogon(userid,password);
	}
	
	public boolean adminLogin(String name,String password){
		//登录比对操作
		boolean count = CrudUtils.querySingleOfLog("select count(*) from adminlog where password=? && adminid=?", password,name);
		
		if(count){
			return false;
		}else{
			return true;
		}
		//返回结果
	}
	/**
	 * 功能：预订
	 * @param id
	 * @param gameId
	 * @param t
	 * @return 是否预定成功
	 */
	public boolean userBook(UserInfo userinfo,Game t){
		return UserBook.userBook(userinfo, t);
	}
	
	public boolean userRecharge(String money,UserInfo userinfo){
		return UserRecharge.userRecharge(money, userinfo);
	}
}
