package entity;

public class UserLog {
	private String userName;
	private String userPassword;
	private int status;
	
	
	
	public UserLog(String userName, String userPassword, int status) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.status = status;
	}
	public UserLog(int status) {
		this.status = status;
	}
	public UserLog() {
		
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
