package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import dao.DatabaseConnectionPool;

public class DatabaseConnectionPoolImpl implements DatabaseConnectionPool {
	//��С�����������
		private static final int minCount = 1;
		private static final int maxCount = 10;
		//���ӳ�
		private static final LinkedList<Connection> pools = new LinkedList<Connection>();
		//�������ӷ�����Ķ���
		GetConnectionImpl get = new GetConnectionImpl();
		
		/**
		 * ��ʼ�����ӳ�
		 */
		@Override
		public void init() {
			Connection con = null;
			try {
				//�������������ӽ����ӳ�
				for(int i=0;i<minCount;i++){
					con = get.getConnection();
					pools.add(con);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * �����ݿ����ӳ�������һ������
		 * synchronized�ؼ���
		 */
		@Override
		public synchronized Connection getConnection() {
			Connection con = null;
			if(pools.size()==0){
				con = get.getConnection();
			}else{
				con = pools.remove(0);
			}
			return con;
		}
		
		/**
		 * �����ӹ黹�����ݿ����ӳ�
		 */
		@Override
		public synchronized void close(Connection connection,ResultSet set,Statement statement) {
			if(pools.size()<maxCount){
				pools.add(connection);
			}
			if(set!=null){
				try {
					set.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
