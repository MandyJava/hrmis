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
 * ��ѯ����
 * @author ��
 *
 */
public class SearchEmpInfoUI implements BaseUI {

	@Override
	public void run() {
		
		String keyEntry = "";// �ؼ���

		//��֤����Ĺؼ���
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

		//Ա����������
		for (Employee emp : empList) {
			if (CommonUtils.isFound(emp.toString(), keyEntry)) {
				searchEmpList.add(emp);
			}
		}

		if (searchEmpList.size() == 0) {
			System.out.println("Keyword �C" + keyEntry + "-not found");
		} else {
			for (Employee emp : searchEmpList) {
				System.out.println("\n" + emp.longString());
			}
		}

	}
	
	/**
	 * �ж��û��Ƿ�����س�
	 * @param keyEntry
	 * @return
	 */
	public boolean checkKeyWord(String keyEntry){
		try{
			CommonUtils.checkBlankEntry(keyEntry);
		}catch(BlankEntryException e){
			System.out .println("\nNo keyword entered�Ctry again��\n");
			return false;
		}
		return true;
	}

}
