package com.lwj.event.bean;
/**
 * ���ݿ��
 * @author lwj
 *
 */
public class Admin {
	private int id;//�ڼ����˻�
	private String username;//�û���
	private String password;//����
	private double balance;
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public double getBalance() {
		return balance;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Admin(int id, String username, String password, double balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
}
