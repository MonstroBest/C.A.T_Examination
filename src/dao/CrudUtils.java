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
	public static int testUpdate(String sql,Object...params) {//������Ӱ�������
		/**
		 * ���ܣ���ɾ��
		 * ������κα���κ���ɾ�����
		 * @param sql sql���
		 * @param ��������
		 * @return ��Ӱ�������
		 */
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			//1.��ȡ����
			connection = JdbcUtils.getConnection();
			//2.ִ��sql���
			//2.1��������
			connection.setAutoCommit(false);//ȡ�������Զ�����
			statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			
			int update = statement.executeUpdate();//������Ӱ�������
			//2.2��������
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
	 * ���ܣ���ѯ������¼�Ƿ����
	 * ���ڣ��˺űȶԡ���¼��¼��
	 * ע�⣺sql�����select count(*)
	 * @return boolean���Ƿ��н��
	 */
	public static boolean querySingleOfLog(String sql,Object...params){
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1.��ȡ����
			connection = JdbcUtils.getConnection();
			//2.ִ�в�ѯ
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
	 * ���ܣ���õ����ֶ�ֵ,�п���Ϊ��
	 */
	public static Object querySingle(String sql,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		Object find = null;//Ҫ��ѯ���ֶ�ֵ
		
		try {
			//1.��ȡ����
			connection = JdbcUtils.getConnection();
			//2.ִ�в�ѯ
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
	 * ��ѯuserlog��
	 * @param sql
	 * @param params
	 * @return UserLog��Ķ����б�
	 */
	public static List<UserLog> queryMultiOfUserLog(String sql,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			//1.��ȡ����
			connection = JdbcUtils.getConnection();
			//2.ִ�в�ѯ
			statement = connection.prepareStatement(sql);
			for(int i = 0;i < params.length;i++){
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			List<UserLog> list = new ArrayList<>();
			//��װ������е����ݣ�UserLog�����װ��List��
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
	 * ���ܣ���ѯgame��ı���
	 * @param sql1
	 * @param params
	 * @return Game��Ķ���
	 */
	public static List<Game> queryMultiOfGame(String sql1,Object...params){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			//1.��ȡ����
			connection = JdbcUtils.getConnection();
			//2.ִ�в�ѯ
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
