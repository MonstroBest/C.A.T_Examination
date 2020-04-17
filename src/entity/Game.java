package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Game {
	private String team1;//����1
	private String team2;//����2
	private Timestamp date;//������ʼʱ��
	private BigDecimal sellingPrice;//��Ʊ�۸�
	private int surplus;//ʣ��Ʊ��
	private String introduction;//��������
	private int gameId;//�������
	
	public String getAll(){
		return "ʱ��:"+date+" \t\t "+team1+" vs "+team2+" \t\t �۸�:"+sellingPrice+"  \t ��ʣ"+surplus+"��Ʊ";
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
	 * ��д��equals���������ڱȽ�����Game������Ƿ����
	 * ���ܣ���ѯ������bug�õ�
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
