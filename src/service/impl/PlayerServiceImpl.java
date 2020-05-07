package service.impl;

import dao.impl.PlayerDaoImpl;
import entity.PlayerInfo;
import entity.UserLog;
import service.PlayerService;

public class PlayerServiceImpl implements PlayerService {

	@Override
	public boolean PlayerRegister(PlayerInfo playerInfo,UserLog userLog) {
		PlayerDaoImpl playerDao = new PlayerDaoImpl();
		boolean flag1 = playerDao.registerUserLog(userLog);
		boolean flag2 = playerDao.register(playerInfo,userLog);
		if(flag1&&flag2) {
			return true;
		}else {
			return false;
		}
		
	}

}
