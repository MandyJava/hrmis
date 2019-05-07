/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;



import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.utils.CheckInputUtils;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 新增员工记录
 * @author 梦
 *
 */
public class AddEmpsInfoUI implements BaseUI {

	
	@Override
	public void run() {
		do {
			showAddEmpsMenu();

			EmployeeDao empDao = new EmployeeDaoTxtImpl();
			EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();

			if (WelcomeUI.DataStore.equals("1"))
				empDao.addEmp(inputEmps());// 增加新的员工记录到文件
			else
				empJDBCDao.addEmp(inputEmps());// 增加新的员工记录到数据库

		} while (addAnotherEmp());
		

	}
	
	/**
	 * 显示新增员工菜单
	 */
	public void showAddEmpsMenu() {
		System.out.println("\n我的资讯–Employee Records:");
		System.out.println("======================================\n");
		System.out.println("Employee Record Additions:\n");
		System.out.println("Enter the following details of the new employee:\n");
		System.out.println("Employee 3 digit payroll number");
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
	 * 新增的员工
	 * @return
	 */
	public Employee inputEmps(){

		Employee emp = new Employee();
		String payRollNo = "";
		String telephoneCode = "";
		String lastName = "";
		String firstName = "";
		String initial = "";
		String deptNo = "";
		String jobTitle = "";
		String hiringDate = "";
        
		//输入员工号并验证
		do {
			System.out.print("Enter employee 3 digit payroll number:");
			payRollNo = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkPayRollNo(payRollNo));
		emp.setPayRollNo(payRollNo);
        System.out.println();
        
        //输入员工电话号码并验证
		do {
			System.out.print("Enter Phone Number (02-12345678):");
			telephoneCode = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkPhone(telephoneCode));
		emp.setTelephoneCode(telephoneCode);
		System.out.println();

		//输入员工lastName并验证
		do {
			System.out.print("Enter Last Name:");
			lastName = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkLastName(lastName));
		emp.setLastName(lastName);
		System.out.println();

		//输入员工firstName并验证
		do {
			System.out.print("Enter First Name:");
			firstName = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkFirstName(firstName));
		emp.setFirstName(firstName);
		System.out.println();

		//输入员工initial并验证
		do {
			System.out.print("Enter Middle Init:");
			initial = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkInitial(initial));
		emp.setInitial(initial);
		System.out.println();

		//输入员工deptNo并验证
		do {
			System.out.print("Enter Dept #:");
			deptNo = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkDeptNo(deptNo));
		emp.setDeptNo(Integer.parseInt(deptNo));
		System.out.println();

		//输入员工jobTitle并验证
		do {
			System.out.print("Enter Job Title:");
			jobTitle = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkJobTitle(jobTitle));
		emp.setJobTitle(jobTitle);
		System.out.println();
		
		//输入员工雇佣日期并验证
		do {
			System.out.print("Enter Date Hired (dd-mm-yyyy):");
			hiringDate = CommonUtils.getEntry();

		} while (!CheckInputUtils.checkHiringDate(hiringDate));
		emp.setHiringDate(hiringDate);
		System.out.println();
		
		//验证通过返回该员工
		return emp;
	}
	
    
	/**
	 * 是否要增加其他员工记录
	 * @return
	 */
	public boolean addAnotherEmp(){
		System.out.println("\nRecord Saved ");
		System.out.print("\nAdd another employee record? (y)es or (n)o:");
		String entry = CommonUtils.getEntry();
		if (entry.equalsIgnoreCase("y"))
			return true;
		return false;
	}
	
}
