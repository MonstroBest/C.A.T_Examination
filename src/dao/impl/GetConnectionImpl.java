package dao.impl;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import dao.GetConnection;

public class GetConnectionImpl implements GetConnection {

	//全局变量
		private static String user;
		private static String password;
		private static String url;
		private static String driver;
		private Connection con = null;
				
		/**
		 * 获取连接并返回
		 */
		@Override
		public Connection getConnection() {
				
			
//					user=root
//					password=buaichixueli19
//					url=jdbc:mysql://localhost:3306/store?rewriteBatchedStatements=true
//					driver=com.mysql.jdbc.Driver
			
				try {
//					Properties info = new Properties();
//					info.load(new FileInputStream("Java Resources/src/dao/jdbc.properties"));
							
//					user = info.getProperty("user");
//					password = info.getProperty("password");
//					url = info.getProperty("url");
//					driver = info.getProperty("driver");
					
					user = "root";
					password = "buaichixueli19";
					url = "jdbc:mysql://localhost:3306/store?rewriteBatchedStatements=true&useSSL=false";
					driver = "com.mysql.jdbc.Driver";
							
					Class.forName(driver);
					this.con = DriverManager.getConnection(url,user,password);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
				return con;
			}

}
