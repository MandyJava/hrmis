/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.util.List;

import edu.fjnu.hrmis.domain.Employee;

/**
 * 员工接口
 * @author 梦
 *
 */
public interface EmployeeDao {


	/**
	 * 加载所有的员工信息
	 * @return
	 */
	List<Employee> loadEmps();
	
	/**
	 * 增加员工
	 * @param emp
	 */
	void addEmp(Employee emp);
	
	/**
	 * 删除员工
	 * @param emp
	 * @return
	 */
	boolean removeEmp(Employee emp);
	
	/**
	 * 更新员工信息
	 * @param emp
	 * @return
	 */
	boolean updateEmp(Employee emp);
	
	/**
	 * 通过员工号获得员工对象
	 * @param payRollNo
	 * @return
	 */
	Employee loadEmpByNO(String payRollNo);
}
