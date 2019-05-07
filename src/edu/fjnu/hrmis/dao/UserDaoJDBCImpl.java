/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.fjnu.hrmis.domain.User;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.DBUtils;

/**
 * �û����ݿ����ʵ��
 * @author ��
 *
 */
public class UserDaoJDBCImpl implements UserDao {
	
	private static final String SQL_GETUSER_BYNO="select * from tbl_users where user_no=?";

	/**
	 * ͨ���û��ʺŻ���û�����
	 */
	@Override
	public User getUserByNo(String userNo) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User user = null;
		
		try {
			pstmt = conn.prepareStatement(SQL_GETUSER_BYNO);
			pstmt.setString(1, userNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				user = new User();
				
				user.setUserNo(userNo);
				user.setUserName(rset.getString("user_name"));
				user.setUserPwd(rset.getString("user_pwd"));
				user.setPriority(rset.getInt("user_pro"));
								
			}
			else
			   throw new HRMISException("�ʺ�"+userNo+"�û�������!");			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * ���������û�
	 */
	@Override
	public List<User> loadUsers() {
		return null;
	}

}
