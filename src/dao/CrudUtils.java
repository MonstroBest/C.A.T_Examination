package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Game;
import entity.UserLog;

public class CrudUtils {
	public static int testUpdate(String sql,Object...params) {//返回受影响的行数
		/**
		 * 功能：增删改
		 * 针对于任何表的任何增删改语句
		 * @param sql sql语句
		 * @param 不定参数
		 * @return 受影响的行数
		 */
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			//1.获取连接
			connection = JdbcUtils.getConnection();
			//2.执行sql语句
			//2.1开启事务
			connection.setAutoCommit(false);//取消事务自动开启
			statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			
			int update = statement.executeUpdate();//返回受影响的行数
			//2.2结束事务
			if(update>0){
				connection.commit();
			}else{
				connection.rollback();
			}
			
			return update;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			JdbcUtils.close(null, statement, connection);
		}
		
	}
	
	/**
	 * 功能：查询单条记录是否存在
	 * 用于：账号比对、记录记录等
	 * 注意：sql语句用select count(*)
	 * @return boolean，是否有结果
	 */
	public static boolean querySingleOfLog(String sql,Object...params){
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1.获取连接
			connection = JdbcUtils.getConnection();
			//2.执行查询
			statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			
			if(set.next()){
				
			}
			int count = set.getInt(1);//
			if(count==0){
				return false;
			}else{
				return true;
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.close(set, statement, connection);
		}
	}
	
	/**
	 * 功能：获得单个字段值,有可能为空
	 */
	public static Object querySingle(String sql,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		Object find = null;//要查询的字段值
		
		try {
			//1.获取连接
			connection = JdbcUtils.getConnection();
			//2.执行查询
			statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			
			if(set.next()){
				find = set.getObject(1);
			}
			return find;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.close(set, statement, connection);
		}
	}
	
	/**
	 * 查询userlog表
	 * @param sql
	 * @param params
	 * @return UserLog类的对象列表
	 */
	public static List<UserLog> queryMultiOfUserLog(String sql,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			//1.获取连接
			connection = JdbcUtils.getConnection();
			//2.执行查询
			statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			List<UserLog> list = new ArrayList<>();
			//包装结果集中的内容，UserLog类对象，装进List里
			while(set.next()){
				String username = set.getString("userid");
				String password = set.getString("password");
				double balance = set.getDouble("balance");
				int id = set.getInt("id");
				final UserLog admin = new UserLog(username,password,balance,id);
				list.add(admin);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.close(set, statement, connection);
		}
	}
	
	/**
	 * 功能：查询game表的比赛
	 * @param sql1
	 * @param params
	 * @return Game类的对象集
	 */
	public static List<Game> queryMultiOfGame(String sql1,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			//1.获取连接
			connection = JdbcUtils.getConnection();
			//2.执行查询
			statement = connection.prepareStatement(sql1);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			List<Game> list = new ArrayList<>();
			while(set.next()){
				int surplus = set.getInt("surplus");
				String introduction = set.getString("introduction");
				String team1 = set.getString("team1");
				String team2 = set.getString("team2");
				BigDecimal sellingPrice = set.getBigDecimal("selling_price");
				Timestamp date = set.getTimestamp("date");
				int gameId = set.getInt("game_id");
				Game team = new Game(team1,team2,date,sellingPrice,surplus,introduction,gameId);
				list.add(team);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtils.close(set, statement, connection);
		}
	}
}
