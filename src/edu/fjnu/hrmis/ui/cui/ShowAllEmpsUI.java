/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import java.util.ArrayList;
import java.util.List;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;

/**
 * ��ʾԱ��������Ϣ
 * @author ��
 *
 */
public class ShowAllEmpsUI implements BaseUI {

	@Override
	public void run() {
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();
		List<Employee> empList = new ArrayList<Employee>();

		if (WelcomeUI.DataStore.equals("1"))
			empList = empDao.loadEmps();// ���ı��ļ��л������
		else
			empList = empJDBCDao.loadEmps();// �����ݿ��л������

		for (Employee emp : empList) {
			System.out.println(emp.longString());
		}

	}

}
