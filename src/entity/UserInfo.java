package entity;
/**
 * �û���Ϣ��
 * @author wj
 *
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserInfo {
	private String userId;//�û��˻�
	private String name;//�û��ǳ�
	private BigDecimal balance;//�˻����
	private BigDecimal consumption;//���ѽ��
	private Timestamp suspendedTime;//�û��������
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
