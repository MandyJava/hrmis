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
 * 显示员工所有信息
 * @author 梦
 *
 */
public class ShowAllEmpsUI implements BaseUI {

	@Override
	public void run() {
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();
		List<Employee> empList = new ArrayList<Employee>();

		if (WelcomeUI.DataStore.equals("1"))
			empList = empDao.loadEmps();// 从文本文件中获得数据
		else
			empList = empJDBCDao.loadEmps();// 从数据库中获得数据

		for (Employee emp : empList) {
			System.out.println(emp.longString());
		}

	}

}
