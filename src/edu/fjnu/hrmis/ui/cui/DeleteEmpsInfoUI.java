/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CheckInputUtils;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 删除界面
 * @author 梦
 *
 */
public class DeleteEmpsInfoUI implements BaseUI {

	@Override
	public void run() {
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();

		do {
			showDelEmpsMenu();

			try {
				if ((WelcomeUI.DataStore.equals("1"))) {
					if (empDao.removeEmp(DeleteEmps())) {
						System.out.println("\nRecord deleted.");
					}
				} else {
					if (empJDBCDao.removeEmp(DeleteEmps())) {
						System.out.println("\nRecord deleted.");
					}
				}
			} catch(NullPointerException e){
				
			}catch (HRMISException e) {
				System.out.println(e.getMessage());
				CommonUtils.pause();
				break;
			}

		} while (deleteAnotherEmp());
		
	}
	
	
	/**
	 * 显示删除员工菜单
	 */
    public void showDelEmpsMenu(){
    	System.out.println("兴邦资讯–Employee Records:");
		System.out.println("======================================");
		System.out.println("Employee Record Deletion:");
		System.out.println();
		
	}
    
    /**
     * 删除的员工
     * @return
     */
    public Employee DeleteEmps(){
    	
    	Employee emp=new Employee();
    	EmployeeDao empDao=new EmployeeDaoTxtImpl();
    	EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();
    	String payRollNo="";
    	
    	//输入员工号并验证
		do {
			System.out.print("Enter employee's 3 digit payroll number to enable record deletion:");
			payRollNo = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkDelPayRollNo(payRollNo));
    	
		try{

			if (WelcomeUI.DataStore.equals("1"))
				emp = empDao.loadEmpByNO(payRollNo);// 从文本文件中获取数据
			else
				emp = empJDBCDao.loadEmpByNO(payRollNo);// 从数据库中获取数据
			
			System.out.println(emp.longString());

			if (confirmDelete()){
				//确认删除返回该员工
				return emp;	
			}
			
		}catch(NullPointerException e){
			throw new HRMISException("\nEmployee record for "+payRollNo +" not found!");
		}
        return null;
    }
    
    /**
     * 确认是否删除
     * @return
     */
    public boolean confirmDelete(){
		System.out.print("\nConfirm record deletion,(y)es or (n)o:");
		String entry = CommonUtils.getEntry();
		if (entry.equalsIgnoreCase("y"))
			return true;
		return false;
    }

    /**
     * 是否要删除其他员工
     * @return
     */
    public boolean deleteAnotherEmp(){
		System.out.print("\nDelete another? (y)es or (n)o:");
		String entry = CommonUtils.getEntry();
		if (entry.equalsIgnoreCase("y"))
			return true;
		return false;
    }
}
