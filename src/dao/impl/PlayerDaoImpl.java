package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dao.DatabaseConnectionPool;
import dao.PlayerDao;
import entity.PlayerInfo;
import entity.UserLog;
import entity.PlayerInfo;

public class PlayerDaoImpl implements PlayerDao {
	
	static DatabaseConnectionPool db = new DatabaseConnectionPoolImpl();
	
	@Override
	public boolean login(UserLog userLog) {
		String sql = "select count(*) from userlog where username='"+userLog.getUserName()+"' and password='"+userLog.getUserPassword()+"'";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1.获取连接
			connection = db.getConnection();
			//2.执行查询
			statement = connection.prepareStatement(sql);
			
			set = statement.executeQuery();
			if(set.next()) {
				
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
			db.close(connection, set, statement);
		}
	}
	/**
	 * 验证邮箱是否存在
	 * @param userInfo
	 * @return
	 */
	public boolean emailVerify(String email) {
		String email0 = email;
		
		String sql1 = "select count(*) from player_info where player_email='"+email0+"'";

		Connection connection = null;
		PreparedStatement statement1 = null;
		ResultSet set = null;
		
		try {
			//1.获取连接
			connection = db.getConnection();
			//2.执行查询
			statement1 = connection.prepareStatement(sql1);
			
			set = statement1.executeQuery();
			if(set.next()) {
				
			}
			
			int count = set.getInt(1);//
			System.out.println(count);
			if(count==0){
				return true;
			}else{
				return false;
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			db.close(connection, set, statement1);
		}

	}
	
	/**
	 * 验证选手id是否存在
	 * @param userInfo
	 * @return
	 */
	public boolean idVerify(PlayerInfo playerInfo) {
		String name = playerInfo.getName();
		
		String sql1 = "select count(*) from player_info where player_id='"+name+"'";

		Connection connection = null;
		PreparedStatement statement1 = null;
		ResultSet set = null;
		
		try {
			//1.获取连接
			connection = db.getConnection();
			//2.执行查询
			statement1 = connection.prepareStatement(sql1);
			
			set = statement1.executeQuery();
			if(set.next()) {
				
			}
			
			int count = set.getInt(1);//
			if(count==0){//如果无注册记录
				return true;
			}else{
				return false;
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			db.close(connection, set, statement1);
		}

	}
	
	/**
	 * 录入注册信息
	 */
	@Override
	public boolean register(PlayerInfo playerInfo,UserLog userLog) {
		String name = playerInfo.getName();
		System.out.println(name);
		String email = playerInfo.getEmail();
		String intro = playerInfo.getIntro();
		System.out.println(intro);
		String password = playerInfo.getPassword();
		String phone = playerInfo.getPhone();
		System.out.println(phone);
		String lastTeam = playerInfo.getLastTeam();
		String joinTime = playerInfo.getJoinTime();
		String headPath = playerInfo.getHeadPath();
		String age = playerInfo.getAge();
		
		String sql2 = "insert into player_info set player_name='"+name+"', player_email='"+email+"', player_password='"+password+"', player_intro='"+intro+"', player_phone='"+phone+"', player_lastteam='"+lastTeam+"', player_jointime='"+joinTime+"', player_age='"+age+"',player_headpath='"+headPath+"'";
		
		Connection connection = null;
		PreparedStatement statement2 = null;
		ResultSet set = null;
		
		try {
			//1.获取连接
			connection = db.getConnection();
			//2.执行查询
			connection.setAutoCommit(false);
			statement2 = connection.prepareStatement(sql2);
			int update = statement2.executeUpdate();
			System.out.println(update);
			if(update>0){
				connection.commit();
				return true;
			}else{
				connection.rollback();
				return false;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			db.close(connection, set, statement2);
		}
	}

//	@Override
//	public List<User> getUserAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String name, String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<UserLog> getUserAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean registerUserLog(UserLog userLog) {
		String name = userLog.getUserName();
		System.out.println(name);
		String password = userLog.getUserPassword();
		
		String sql2 = "insert into userlog set username='"+name+"', password='"+password+"', status='0'";
		
		Connection connection = null;
		PreparedStatement statement2 = null;
		ResultSet set = null;
		
		try {
			//1.获取连接
			connection = db.getConnection();
			//2.执行查询
			connection.setAutoCommit(false);
			statement2 = connection.prepareStatement(sql2);
			int update = statement2.executeUpdate();
			System.out.println(update);
			if(update>0){
				connection.commit();
				return true;
			}else{
				connection.rollback();
				return false;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			db.close(connection, set, statement2);
		}
	}

}
