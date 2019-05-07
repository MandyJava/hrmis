/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;

/**
 * ��ʽ��ʾԱ��������Ϣ
 * @author ��
 *
 */
public class ShowEmpPhoneInfoUI implements BaseUI {

	@Override
	public void run() {
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();

		TreeSet<Employee> empSet = new TreeSet<Employee>();
		List<Employee> empList = new ArrayList<Employee>();

		if (WelcomeUI.DataStore.equals("1"))
			empList = empDao.loadEmps();// ���ı��ļ��л������
		else
			empList = empJDBCDao.loadEmps();// �����ݿ��л������

		empSet.addAll(empList);

		for (Employee emp : empSet) {
			System.out.println(emp.shortFormattedString());
		}
	}

}
