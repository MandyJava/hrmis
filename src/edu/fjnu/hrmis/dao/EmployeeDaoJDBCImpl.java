/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.utils.DBUtils;

/**
 * 员工数据库操作实现
 * @author 梦
 *
 */
public class EmployeeDaoJDBCImpl implements EmployeeDao {

	private static final String SQL_ADD = "insert into tbl_employee values(?,?,?,?,?,?,?,?)";
	private static final String SQL_REMOVE = "delete from tbl_employee where emp_payRollNo=?";
	private static final String SQL_LOADALL = "select * from tbl_employee order by emp_payRollNo";
	private static final String SQL_LOADBYNO = "select * from tbl_employee where emp_payRollNo=?";
	private static final String SQL_UPDATE = "update tbl_employee set emp_telephoneCode=?,emp_lastName=?,emp_firstName=?,emp_initial=?,emp_deptNo =?,emp_jobTitle=?,emp_hiringDate=? where emp_payRollNo=?";

	/**
	 * 加载所有的员工信息
	 */
	@Override
	public List<Employee> loadEmps() {
	
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Employee> empsList = null;

		try {

			pstmt = conn.prepareStatement(SQL_LOADALL);
			rset = pstmt.executeQuery();

			empsList = new ArrayList<Employee>();

			while (rset.next()) {

				Employee emp=new Employee();
				emp.setPayRollNo(rset.getString("emp_payRollNo"));
				emp.setTelephoneCode(rset.getString("emp_telephoneCode"));
				emp.setLastName(rset.getString("emp_lastName"));
				emp.setFirstName(rset.getString("emp_firstName"));
				emp.setInitial(rset.getString("emp_initial"));
				emp.setDeptNo(rset.getInt("emp_deptNo"));
				emp.setJobTitle(rset.getString("emp_jobTitle"));
				emp.setHiringDate(rset.getString("emp_hiringDate"));

				empsList.add(emp);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.releaseRes(conn, pstmt, rset);
		}

		return empsList;
	}


	/**
	 * 增加员工
	 */
	@Override
	public void addEmp(Employee emp) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;

		try {
			
			pstmt = conn.prepareStatement(SQL_ADD);
			pstmt.setString(1, emp.getPayRollNo());
			pstmt.setString(2, emp.getTelephoneCode());
			pstmt.setString(3, emp.getLastName());
			pstmt.setString(4, emp.getFirstName());
			pstmt.setString(5, emp.getInitial());
			pstmt.setInt(6, emp.getDeptNo());
			pstmt.setString(7,emp.getJobTitle());
			pstmt.setString(8,emp.getHiringDate());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.releaseRes(conn, pstmt, null);
		}
	}

    /**
     * 删除员工
     */
	@Override
	public boolean removeEmp(Employee emp) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			pstmt = conn.prepareStatement(SQL_REMOVE);
			pstmt.setString(1, emp.getPayRollNo());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.releaseRes(conn, pstmt, null);
		}

		return cnt == 1;
	}

    /**
     * 更新员工信息
     */
	@Override
	public boolean updateEmp(Employee emp) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {

			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, emp.getTelephoneCode());
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3, emp.getFirstName());
			pstmt.setString(4, emp.getInitial());
			pstmt.setInt(5, emp.getDeptNo());
			pstmt.setString(6,emp.getJobTitle());
			pstmt.setString(7,emp.getHiringDate());
			pstmt.setString(8, emp.getPayRollNo());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.releaseRes(conn, pstmt, null);
		}
		return cnt==1;
	}

    /**
     * 通过员工号获得员工对象
     */
	@Override
	public Employee loadEmpByNO(String payRollNo) {
		Connection conn = DBUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Employee emp=null;
        try {
			
			pstmt = conn.prepareStatement(SQL_LOADBYNO);
			pstmt.setString(1, payRollNo);
			rset = pstmt.executeQuery();
			
			
			if(rset.next()){
				
				emp=new Employee();
				emp.setPayRollNo(rset.getString("emp_payRollNo"));
				emp.setTelephoneCode(rset.getString("emp_telephoneCode"));
				emp.setLastName(rset.getString("emp_lastName"));
				emp.setFirstName(rset.getString("emp_firstName"));
				emp.setInitial(rset.getString("emp_initial"));
				emp.setDeptNo(rset.getInt("emp_deptNo"));
				emp.setJobTitle(rset.getString("emp_jobTitle"));
				emp.setHiringDate(rset.getString("emp_hiringDate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
            DBUtils.releaseRes(conn, pstmt, rset);	
		}
		
		return emp;
	}

}
