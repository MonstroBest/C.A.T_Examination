package entity;
/**
 * 用户信息类
 * @author wj
 *
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserInfo {
	private String userId;//用户账户
	private String name;//用户昵称
	private BigDecimal balance;//账户余额
	private BigDecimal consumption;//消费金额
	private Timestamp suspendedTime;//用户被封禁至
	private String phoneNumber;
	
	
	
//	public String getAll{
//		return UserId+name;
//	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getConsumption() {
		return consumption;
	}
	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}
	public Timestamp getSuspendedTime() {
		return suspendedTime;
	}
	public void setSuspendedTime(Timestamp suspendedTime) {
		this.suspendedTime = suspendedTime;
	}
	
	public UserInfo(String userId) {
		super();
		this.userId = userId;
	}
	
	
}
