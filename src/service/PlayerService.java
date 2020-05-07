package service;

import entity.PlayerInfo;
import entity.UserLog;

public interface PlayerService {
	public boolean PlayerRegister(PlayerInfo playerInfo,UserLog userLog);
}
