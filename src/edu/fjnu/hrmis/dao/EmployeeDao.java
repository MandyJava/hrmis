/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.util.List;

import edu.fjnu.hrmis.domain.Employee;

/**
 * Ա���ӿ�
 * @author ��
 *
 */
public interface EmployeeDao {


	/**
	 * �������е�Ա����Ϣ
	 * @return
	 */
	List<Employee> loadEmps();
	
	/**
	 * ����Ա��
	 * @param emp
	 */
	void addEmp(Employee emp);
	
	/**
	 * ɾ��Ա��
	 * @param emp
	 * @return
	 */
	boolean removeEmp(Employee emp);
	
	/**
	 * ����Ա����Ϣ
	 * @param emp
	 * @return
	 */
	boolean updateEmp(Employee emp);
	
	/**
	 * ͨ��Ա���Ż��Ա������
	 * @param payRollNo
	 * @return
	 */
	Employee loadEmpByNO(String payRollNo);
}
