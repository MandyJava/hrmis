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
 * 格式显示员工部分信息
 * @author 梦
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
			empList = empDao.loadEmps();// 从文本文件中获得数据
		else
			empList = empJDBCDao.loadEmps();// 从数据库中获得数据

		empSet.addAll(empList);

		for (Employee emp : empSet) {
			System.out.println(emp.shortFormattedString());
		}
	}

}
