package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Game {
	private String team1;//队伍1
	private String team2;//队伍2
	private Timestamp date;//比赛开始时间
	private BigDecimal sellingPrice;//售票价格
	private int surplus;//剩余票数
	private String introduction;//比赛介绍
	private int gameId;//比赛编号
	
	public String getAll(){
		return "时间:"+date+" \t\t "+team1+" vs "+team2+" \t\t 价格:"+sellingPrice+"  \t 还剩"+surplus+"张票";
		//return team1+"?";
	}
	
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public int getSurplus() {
		return surplus;
	}
	public void setSurplus(int surplus) {
		this.surplus = surplus;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Game(String team1, String team2, Timestamp date, BigDecimal sellingPrice, int surplus, String introduction,int gameId) {
		super();
		this.team1 = team1;
		this.team2 = team2;
		this.date = date;
		this.sellingPrice = sellingPrice;
		this.surplus = surplus;
		this.introduction = introduction;
		this.gameId = gameId;
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	/**
	 * 重写的equals方法，用于比较两个Game类对象是否相等
	 * 功能：查询窗口修bug用的
	 */
	@Override
	public boolean equals(Object obj) {
		Game g = (Game) obj;
		if(this!=null && g!=null && this.getDate()!=g.getDate()){
			return false;
		}else{
			return true;
		}
	}
}
