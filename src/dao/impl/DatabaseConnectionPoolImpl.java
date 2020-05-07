package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import dao.DatabaseConnectionPool;

public class DatabaseConnectionPoolImpl implements DatabaseConnectionPool {
	//最小、最大连接数
		private static final int minCount = 1;
		private static final int maxCount = 10;
		//连接池
		private static final LinkedList<Connection> pools = new LinkedList<Connection>();
		//创建连接方法类的对象
		GetConnectionImpl get = new GetConnectionImpl();
		
		/**
		 * 初始化连接池
		 */
		@Override
		public void init() {
			Connection con = null;
			try {
				//逐个创建添加连接进连接池
				for(int i=0;i<minCount;i++){
					con = get.getConnection();
					pools.add(con);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 从数据库连接池中拿走一个连接
		 * synchronized关键字
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
		 * 把连接归还给数据库连接池
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
