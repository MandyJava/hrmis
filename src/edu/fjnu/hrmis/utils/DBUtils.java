/**
 * 
 */
package edu.fjnu.hrmis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库工具类
 * @author 梦
 *
 */
public class DBUtils {
	
	    //数据位置
		private static final String CONN_URL="jdbc:mysql://localhost:3306/hrmis";
		private static final String USERNAME="mandy";
		private static final String PWD="123456";
		
		public static Connection getConnection(){
			
			Connection conn = null;
			
			//驱动入口点类
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(CONN_URL, USERNAME, PWD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //在类路径范围去查找这个驱动，确认该驱动是否存在
	        catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
			
		}
		
		
		/**
		 * 释放资源
		 * @param conn
		 * @param pstmt
		 * @param rset
		 */
		public static void releaseRes(Connection conn,PreparedStatement pstmt, ResultSet rset){
			
			try {
				if(rset!=null) rset.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


}
