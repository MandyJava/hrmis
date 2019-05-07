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
 * �û��ı�����ʵ��
 * @author ��
 *
 */
public class UserDaoTxtImpl implements UserDao {
	private static final String USER_DATA_FILE ="f:/users.txt"; //�û���¼�ĵ�
	
	/**
	 * ͨ���û��ʺŻ���û�����
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
	 * ���������û�
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
			throw new HRMISException("�����ļ�û�б��ҵ�!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		return useList;
	}

}
