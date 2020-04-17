package controller;

import dao.CrudUtils;
import entity.Game;
import entity.UserInfo;
import service.UserBook;
import service.UserLogin;
import service.UserLogon;
import service.UserRecharge;

/**
 * ���ܣ������û�ģ��Ŀ�������
 * @author lwj
 *
 */
public class UserController {
	/**
	 * ���ܣ�ע��͵�¼�������˻��ȶ�
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
		//��¼�ȶԲ���
		boolean count = CrudUtils.querySingleOfLog("select count(*) from adminlog where password=? && adminid=?", password,name);
		
		if(count){
			return false;
		}else{
			return true;
		}
		//���ؽ��
	}
	/**
	 * ���ܣ�Ԥ��
	 * @param id
	 * @param gameId
	 * @param t
	 * @return �Ƿ�Ԥ���ɹ�
	 */
	public boolean userBook(UserInfo userinfo,Game t){
		return UserBook.userBook(userinfo, t);
	}
	
	public boolean userRecharge(String money,UserInfo userinfo){
		return UserRecharge.userRecharge(money, userinfo);
	}
}
