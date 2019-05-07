/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CheckInputUtils;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 更新员工信息
 * @author 梦
 *
 */
public class UpdateEmpsInfoUI implements BaseUI {


	@Override
	public void run() {
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao=new EmployeeDaoJDBCImpl();
		
		showUpdateEmpsMenu();
		try {
			do {
				if (WelcomeUI.DataStore.equals("1")) {
					if (empDao.updateEmp(updateEmps()))
						System.out.println("\nRecord modifyed.");
				} else {
					if (empJDBCDao.updateEmp(updateEmps())){
						System.out.println("\nRecord modifyed.");
					}
				}
				
			} while (updateOtherEmp());

		} catch (HRMISException e) {
			System.out.println(e.getMessage());
			CommonUtils.pause();
		}

	}
	
	/**
	 * 显示修改员工信息菜单
	 */
	public void showUpdateEmpsMenu() {
		System.out.println("\n兴邦资讯–Employee Records:");
		System.out.println("======================================\n");
		System.out.println("Employee Record modification:");
		System.out.println("You can modify the following details of the employee by payroll number:");
		System.out.println("Phone Number");
		System.out.println("Last Name");
		System.out.println("First Name");
		System.out.println("Middle Init");
		System.out.println("Dept #");
		System.out.println("Job Title");
		System.out.println("Date Hired");
		System.out.println();
	}
	
	
	/**
	 * 显示员工可修改信息菜单
	 */
	public void showUpdateInfoMenu(){
		System.out.println("\nEmployee Information to Update:");
		System.out.println("==========================================\n");
		System.out.println("1 - Phone Number");
		System.out.println("2 - Last Name");
		System.out.println("3 - First Name");
		System.out.println("4 - Middle Init");
		System.out.println("5 - Dept #");
		System.out.println("6 - Job Title");
		System.out.println("7 - Date Hired\n");
		System.out.print("Your Selection:");	
	}
	
	/**
	 * 更新员工
	 * @return
	 */
	public Employee updateEmps(){
		
		Employee emp = new Employee();
		String payRollNo = "";

		do {
			System.out.print("Enter employee 3 digit payroll number:");
			payRollNo = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkDelPayRollNo(payRollNo));
		
		do {
			emp = updateEmpInfo(payRollNo);
		} while (updateOtherInfo());

		return emp;
	}
	
	/**
	 * 更新员工其他信息
	 * @param payRollNo
	 * @return
	 */
	public Employee updateEmpInfo(String payRollNo){
		Employee emp = new Employee();
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao=new EmployeeDaoJDBCImpl();
		
		String entry = null;
		String telephoneCode = "";
		String lastName = "";
		String firstName = "";
		String initial = "";
		String deptNo = "";
		String jobTitle = "";
		String hiringDate = "";
		
	try {
			if (WelcomeUI.DataStore.equals("1"))
				emp = empDao.loadEmpByNO(payRollNo);// 从文本文件中获取数据
			else
				emp = empJDBCDao.loadEmpByNO(payRollNo);// 从数据库中获取数据

			System.out.println(emp.longString());

			showUpdateInfoMenu();

			entry = CommonUtils.getEntry();
			CommonUtils.checkBlankEntry(entry);

			if (entry.equals("1")) {//更新与员工电话号码
				do {
					System.out.print("\nEnter Phone Number (02-12345678):");
					telephoneCode = CommonUtils.getEntry();
				} while (!CheckInputUtils.checkPhone(telephoneCode));
				emp.setTelephoneCode(telephoneCode);
				UpdateData(emp);
				
			} else if (entry.equals("2")) {//更新员工lastName
				do {
					System.out.print("\nEnter Last Name:");
					lastName = CommonUtils.getEntry();
				} while (!CheckInputUtils.checkLastName(lastName));
				emp.setLastName(lastName);
				UpdateData(emp);

			} else if (entry.equals("3")) {//更新员工firstName
				do {
					System.out.print("\nEnter First Name:");
					firstName = CommonUtils.getEntry();
				} while (!CheckInputUtils.checkFirstName(firstName));
				emp.setFirstName(firstName);
				UpdateData(emp);
				
			} else if (entry.equals("4")) {//更新员工initial
				do {
					System.out.print("\nEnter Middle Init:");
					initial = CommonUtils.getEntry();
				} while (!CheckInputUtils.checkInitial(initial));
				emp.setInitial(initial);
				UpdateData(emp);
				
			} else if (entry.equals("5")) {//更新员工deptNo
				do {
					System.out.print("\nEnter Dept #:");
					deptNo = CommonUtils.getEntry();
				} while (!CheckInputUtils.checkDeptNo(deptNo));
				emp.setDeptNo(Integer.parseInt(deptNo));
				UpdateData(emp);

			} else if (entry.equals("6")) {//更行员工jobTitle
				do {
					System.out.print("\nEnter Job Title:");
					jobTitle = CommonUtils.getEntry();
				} while (!CheckInputUtils.checkJobTitle(jobTitle));
				emp.setJobTitle(jobTitle);
				UpdateData(emp);
				
			} else if (entry.equals("7")) {//更新员工雇佣日期
				do {
					System.out.print("\nEnter Date Hired (dd-mm-yyyy):");
					hiringDate = CommonUtils.getEntry();	

				} while (!CheckInputUtils.checkHiringDate(hiringDate));
				emp.setHiringDate(hiringDate);
				UpdateData(emp);
				
			} else {
				CommonUtils.pause("Invalid code!Press Enter to continue…");
			}
		}catch(NullPointerException e){
			throw new HRMISException("\nEmployee record for "+payRollNo +" not found!");
	
		} catch (BlankEntryException e) {
			CommonUtils.pause("No selection entered.Press Enter to continue…");
		}
	//通过更新验证返回修改后的员工
	return emp;
	}

	/**
	 * 是否要修改该员工的其他信息
	 * @return
	 */
	public boolean updateOtherInfo(){
		System.out.print("\nUpdate another infomation of the employee? (y)es or (n)o:");
		String entry=CommonUtils.getEntry();
    	if(entry.equalsIgnoreCase("y"))
    		return true;
    	return false;
	}
	
	/**
	 * 是否要修改其他员工信息
	 * @return
	 */
	public boolean updateOtherEmp(){
		System.out.print("\nUpdate another employee? (y)es or (n)o:");
		String entry=CommonUtils.getEntry();
    	if(entry.equalsIgnoreCase("y"))
    		return true;
    	return false;
	}
	
	/**
	 * 更新信息
	 * @param emp
	 */
	public void UpdateData(Employee emp){
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();

		if (WelcomeUI.DataStore.equals("1")) {
			empDao.updateEmp(emp);

		} else {
			empJDBCDao.updateEmp(emp);

		}
	}
}
