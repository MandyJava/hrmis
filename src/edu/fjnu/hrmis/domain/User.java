/**
 * 
 */
package edu.fjnu.hrmis.domain;

/**
 * 用户
 * @author 梦
 *
 */
public class User extends ValueObject {

	/**用户帐号*/
	private String userNo;
	/**用户名称*/
	private String userName;
	/**用户密码*/
	private String userPwd;
	/**用户权限*/
	private int priority;
	

	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	/**
	 * 把用户数据转换成用户对象
	 * @param dataStr
	 * @return
	 */
	public static User getUserFromDataStr(String dataStr){
		
		User user=new User();
		
		String[] section = dataStr.split("\\,");
		
		user.setUserNo(section[0]);
		user.setUserPwd(section[1]);
		user.setUserName(section[2]);
		user.setPriority(Integer.parseInt(section[3]));
		return user;
	}
}
