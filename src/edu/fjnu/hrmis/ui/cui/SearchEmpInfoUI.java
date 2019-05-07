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
import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 查询界面
 * @author 梦
 *
 */
public class SearchEmpInfoUI implements BaseUI {

	@Override
	public void run() {
		
		String keyEntry = "";// 关键字

		//验证输入的关键字
		do {
			System.out.print("Enter keyword:");
			keyEntry = CommonUtils.getEntry();
		} while (!checkKeyWord(keyEntry));

		List<Employee> empList = new ArrayList<Employee>();
		List<Employee> searchEmpList = new ArrayList<Employee>();

		if ((WelcomeUI.DataStore.equals("1"))) {
			EmployeeDao empDao = new EmployeeDaoTxtImpl();
			empList = empDao.loadEmps();
		} else {
			EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();
			empList = empJDBCDao.loadEmps();
		}

		//员工搜索集合
		for (Employee emp : empList) {
			if (CommonUtils.isFound(emp.toString(), keyEntry)) {
				searchEmpList.add(emp);
			}
		}

		if (searchEmpList.size() == 0) {
			System.out.println("Keyword –" + keyEntry + "-not found");
		} else {
			for (Employee emp : searchEmpList) {
				System.out.println("\n" + emp.longString());
			}
		}

	}
	
	/**
	 * 判断用户是否输入回车
	 * @param keyEntry
	 * @return
	 */
	public boolean checkKeyWord(String keyEntry){
		try{
			CommonUtils.checkBlankEntry(keyEntry);
		}catch(BlankEntryException e){
			System.out .println("\nNo keyword entered–try again…\n");
			return false;
		}
		return true;
	}

}
