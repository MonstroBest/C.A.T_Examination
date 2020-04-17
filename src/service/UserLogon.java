package service;

import dao.CrudUtils;
import utils.RsaUtils;

public class UserLogon {
	public static boolean userLogon(String userid,String password) throws Exception{
		boolean count = true;//默认被注册
		//登录比对操作
		if(userid!=null){
			count = CrudUtils.querySingleOfLog("select count(*) from userlog where userid=?", userid);//有无被注册
			System.out.println(1);
		}
		if(!count){//如果没有注册
			if(userid!=null&&password!=null){
				System.out.println(2);
				//if(CrudUtils.querySingle("select id from userlog where userid=?", userid)!=null){
					System.out.println(3);
					int id = 3;
					RsaUtils.genKeyPair(id);
					String messageEn = RsaUtils.encrypt(password, RsaUtils.keyMap.get(2*id-2));
					CrudUtils.testUpdate("insert into userinfo set userid=?", userid);
					CrudUtils.testUpdate("insert into userlog set userid=?,password=?",userid,messageEn);
					CrudUtils.testUpdate("insert into rsa(userid,public_key,private_key) values(?,?,?)", userid,RsaUtils.keyMap.get(2*id-2),RsaUtils.keyMap.get(2*id-1));
					return true;
				//}
			}
			System.out.println(4);
			return false;
		}else{
			System.out.println(5);
			return false;
		}
		//返回结果
	}
}
