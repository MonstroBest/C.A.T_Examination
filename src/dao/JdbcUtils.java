package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/**
 * JDBC���������ݿ�
 * @author wj
 *
 */
public class JdbcUtils {
	
	static String user;
	static String password;
	static String url;
	static String driver;
	
	/**
	 * ���ļ����ȡ��Ϣ
	 */
	static{
		try {
			
			Properties info = new Properties();
			info.load(new FileInputStream("src/dao/jdbc.properties"));
			
			user = info.getProperty("user");
			password = info.getProperty("password");
			url = info.getProperty("url");
			driver = info.getProperty("driver");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ���ܣ���ȡ���õ����Ӷ���
	 */
	public static Connection getConnection() {
		//2.��ȡ����
		try {
			Connection connection = DriverManager.getConnection(url,user,password);
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//return DriverManager.getConnection(url,user,password);
	}
	
	/**
	 * ���ܣ��رգ��ͷ���Դ
	 * @param set
	 * @param statement
	 * @param connection
	 * @throws Exception
	 */
	public static void close(ResultSet set,Statement statement,Connection connection) {
		try {
			if(set!=null){
				set.close();
			}
			if(statement!=null){
				statement.close();
			}
			if(connection!=null){
				connection.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void close(Statement statement,ResultSet set){
		try {
			if(statement!=null){
				statement.close();
			}
			if(set!=null){
				set.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(ResultSet set, PreparedStatement pStatement, Connection connection) {
		try {
			if(set!=null){
				set.close();
			}
			if(pStatement!=null){
				pStatement.close();
			}
			if(connection!=null){
				connection.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
