/**
 * 
 */
package edu.fjnu.hrmis.domain;

/**
 * �û�
 * @author ��
 *
 */
public class User extends ValueObject {

	/**�û��ʺ�*/
	private String userNo;
	/**�û�����*/
	private String userName;
	/**�û�����*/
	private String userPwd;
	/**�û�Ȩ��*/
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
	 * ���û�����ת�����û�����
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
