package entity;
/**
 * �û���¼��
 * @author wj
 *
 */
public class UserLog {
	private String userid;//�û���
	private String password;//����
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserLog(String userid, String password, double balance,int id) {
		super();
		this.userid = userid;
		this.password = password;
	}
}
