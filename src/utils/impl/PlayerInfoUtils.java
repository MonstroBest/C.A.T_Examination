package utils.impl;

import entity.PlayerInfo;
import entity.UserLog;

public class PlayerInfoUtils implements utils.PlayerInfoUtils {

	@Override
	public void isWant(String fieldName,String fieldValue,PlayerInfo player,UserLog user) {
		isName(fieldName,fieldValue,player,user);
		isPassword(fieldName,fieldValue,player,user);
		isAge(fieldName,fieldValue,player);
		isLastTeam(fieldName,fieldValue,player);
		isJoinTime(fieldName,fieldValue,player);
		isEmail(fieldName,fieldValue,player);
		isPhone(fieldName,fieldValue,player);
		isIntro(fieldName,fieldValue,player);
		
	}

	@Override
	public boolean isName(String fieldName,String fieldValue,PlayerInfo player,UserLog user) {
		String name = "username";
		if(name.equals(fieldName)) {
			player.setName(fieldValue);
			user.setUserName(fieldValue);
			user.setStatus(0);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isPassword(String fieldName,String fieldValue,PlayerInfo player,UserLog user) {
		String password = "password";
		if(password.equals(fieldName)) {
			player.setPassword(fieldValue);
			user.setUserPassword(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isAge(String fieldName,String fieldValue,PlayerInfo player) {
		String age = "age";
		if(age.equals(fieldName)) {
			player.setAge(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isLastTeam(String fieldName,String fieldValue,PlayerInfo player) {
		String lastTeam = "lastTeam";
		if(lastTeam.equals(fieldName)) {
			player.setLastTeam(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isJoinTime(String fieldName,String fieldValue,PlayerInfo player) {
		String joinTime = "joinTime";
		if(joinTime.equals(fieldName)) {
			player.setJoinTime(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isEmail(String fieldName,String fieldValue,PlayerInfo player) {
		String email = "email";
		if(email.equals(fieldName)) {
			player.setEmail(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isPhone(String fieldName,String fieldValue,PlayerInfo player) {
		String phone = "phone";
		if(phone.equals(fieldName)) {
			player.setPhone(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean isIntro(String fieldName,String fieldValue,PlayerInfo player) {
		String intro = "intro";
		if(intro.equals(fieldName)) {
			player.setIntro(fieldValue);
			return true;
		}else {
			return false;
		}
	}

	public void isHeadPath(String path,PlayerInfo player) {
		player.setHeadPath(path);
	}
	
}
