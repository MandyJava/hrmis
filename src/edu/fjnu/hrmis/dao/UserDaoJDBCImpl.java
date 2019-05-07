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
 * 用户数据库操作实现
 * @author 梦
 *
 */
public class UserDaoJDBCImpl implements UserDao {
	
	private static final String SQL_GETUSER_BYNO="select * from tbl_users where user_no=?";

	/**
	 * 通过用户帐号获得用户对象
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
			   throw new HRMISException("帐号"+userNo+"用户不存在!");			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * 加载所以用户
	 */
	@Override
	public List<User> loadUsers() {
		return null;
	}

}
