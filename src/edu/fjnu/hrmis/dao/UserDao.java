/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.util.List;

import edu.fjnu.hrmis.domain.User;

/**
 * 用户接口
 * @author 梦
 *
 */
public interface UserDao {
	
	/**
	 * 加载所有的用户信息
	 * @return
	 */
	List<User> loadUsers();
	
	/**
	 * 通过用户帐号获得用户对象
	 * @param userNo
	 * @return
	 */
	User getUserByNo(String userNo);

}
