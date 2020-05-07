package utils;

import entity.PlayerInfo;
import entity.UserLog;

public interface PlayerInfoUtils {
	public void isWant(String fieldName,String fieldValue,PlayerInfo player,UserLog user);
	
	public boolean isName(String fieldName,String fieldValue,PlayerInfo player,UserLog user);
	public boolean isPassword(String fieldName,String fieldValue,PlayerInfo player,UserLog user);
	public boolean isAge(String fieldName,String fieldValue,PlayerInfo player);
	public boolean isLastTeam(String fieldName,String fieldValue,PlayerInfo player);
	public boolean isJoinTime(String fieldName,String fieldValue,PlayerInfo player);
	public boolean isEmail(String fieldName,String fieldValue,PlayerInfo player);
	public boolean isPhone(String fieldName,String fieldValue,PlayerInfo player);
	public boolean isIntro(String fieldName,String fieldValue,PlayerInfo player);
	public void isHeadPath(String path,PlayerInfo player);
}
