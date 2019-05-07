/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.hrmis.domain.User;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 用户文本操作实现
 * @author 梦
 *
 */
public class UserDaoTxtImpl implements UserDao {
	private static final String USER_DATA_FILE ="f:/users.txt"; //用户记录文档
	
	/**
	 * 通过用户帐号获得用户对象
	 */
	@Override
	public User getUserByNo(String userNo) {
		for (User user : loadUsers()) {
			if(userNo.equals(user.getUserNo()))
				return user;
		}
		return null;
	}

	/**
	 * 加载所以用户
	 */
	@Override
	public List<User> loadUsers() {
		CommonUtils.checkUserResource();
		List<User> useList = new ArrayList<User>();
		String useData = null;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(USER_DATA_FILE)));

			while ((useData = reader.readLine()) != null) {

				User user = User.getUserFromDataStr(useData);
				useList.add(user);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new HRMISException("数据文件没有被找到!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		return useList;
	}

}
