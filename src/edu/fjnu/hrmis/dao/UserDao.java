/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.util.List;

import edu.fjnu.hrmis.domain.User;

/**
 * �û��ӿ�
 * @author ��
 *
 */
public interface UserDao {
	
	/**
	 * �������е��û���Ϣ
	 * @return
	 */
	List<User> loadUsers();
	
	/**
	 * ͨ���û��ʺŻ���û�����
	 * @param userNo
	 * @return
	 */
	User getUserByNo(String userNo);

}
