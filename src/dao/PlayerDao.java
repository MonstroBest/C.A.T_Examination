package dao;

import java.util.List;

import entity.PlayerInfo;
import entity.UserLog;


public interface PlayerDao {
	public boolean login(UserLog userLog);
	public boolean register(PlayerInfo playerInfo,UserLog userLog);
	public boolean registerUserLog(UserLog userLog);
	public List<UserLog> getUserAll();
	public boolean delete(String id);
	public boolean update(String name,String id);
	public boolean emailVerify(String email);
	public boolean idVerify(PlayerInfo userInfo);
}
