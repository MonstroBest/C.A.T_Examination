package com.lwj.event.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lwj.event.bean.Admin;

public class CrudUtils {
	public static int testUpdate(String sql,Object...params) {//������Ӱ�������
		/**
		 * ���ܣ���ɾ��
		 * ������κα���κ���ɾ�����
		 * @return ��Ӱ�������
		 */
		
		try {
			//1.��ȡ����
			Connection connection = JDBCUtils.getConnection();
			//2.ִ��sql���
			PreparedStatement statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			
			int update = statement.executeUpdate();//������Ӱ�������
			
			return update;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * ���ܣ��˺űȶ�
	 */
	public static int querySingle(String sql1,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1.��ȡ����
			connection = JDBCUtils.getConnection();
			//2.ִ�в�ѯ
			statement = connection.prepareStatement(sql1);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			
			if(set.next()){
				//����
//				int id = set.getInt("id");
//				String username = set.getString("username");
//				String password = set.getString("password");
//				double balance = set.getDouble("balance");
//				Admin admin = new Admin(id,username,password,balance);
//				
			}
			int count = set.getInt(1);//
			return count;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(set, statement, connection);
		}
	}
	/**
	 * ��ѯָ���������¼
	 * @param sql1
	 * @param params
	 * @return
	 */
	public static List<Admin> queryMulti(String sql1,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			//1.��ȡ����
			connection = JDBCUtils.getConnection();
			//2.ִ�в�ѯ
			statement = connection.prepareStatement(sql1);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			List<Admin> list = new ArrayList<>();
			while(set.next()){
				int id = set.getInt("id");
				String username = set.getString("username");
				String password = set.getString("password");
				double balance = set.getDouble("balance");
				Admin admin = new Admin(id,username,password,balance);
				list.add(admin);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(set, statement, connection);
		}
	}
}
