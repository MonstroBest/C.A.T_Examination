package com.lwj.event.controller;

import com.lwj.event.bean.Admin;
import com.lwj.event.utils.CrudUtils;

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
	 */
	public boolean login(String name,String password){
		//��¼�ȶԲ���
		int count = CrudUtils.querySingle("select count(*) from admin where password=? && username=?", password,name);
		
		if(count==0){
			return false;
		}else{
			return true;
		}
		//���ؽ��
	}
	
	public boolean logon(String name,String password){
		//��¼�ȶԲ���
		int count = CrudUtils.querySingle("select count(*) from admin where username=?", name);
		
		if(count==0){
			CrudUtils.testUpdate("insert into admin set username=?,password=?,balance=DEFAULT", name,password);
			return true;
		}else{
			return false;
		}
		//���ؽ��
	}
	
}
