package com.lwj.event.controller;

import com.lwj.event.bean.Admin;
import com.lwj.event.utils.CrudUtils;

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
	 */
	public boolean login(String name,String password){
		//登录比对操作
		int count = CrudUtils.querySingle("select count(*) from admin where password=? && username=?", password,name);
		
		if(count==0){
			return false;
		}else{
			return true;
		}
		//返回结果
	}
	
	public boolean logon(String name,String password){
		//登录比对操作
		int count = CrudUtils.querySingle("select count(*) from admin where username=?", name);
		
		if(count==0){
			CrudUtils.testUpdate("insert into admin set username=?,password=?,balance=DEFAULT", name,password);
			return true;
		}else{
			return false;
		}
		//返回结果
	}
	
}
