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
 * ɾ������
 * @author ��
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
	 * ��ʾɾ��Ա���˵�
	 */
    public void showDelEmpsMenu(){
    	System.out.println("�˰���Ѷ�CEmployee Records:");
		System.out.println("======================================");
		System.out.println("Employee Record Deletion:");
		System.out.println();
		
	}
    
    /**
     * ɾ����Ա��
     * @return
     */
    public Employee DeleteEmps(){
    	
    	Employee emp=new Employee();
    	EmployeeDao empDao=new EmployeeDaoTxtImpl();
    	EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();
    	String payRollNo="";
    	
    	//����Ա���Ų���֤
		do {
			System.out.print("Enter employee's 3 digit payroll number to enable record deletion:");
			payRollNo = CommonUtils.getEntry();
		} while (!CheckInputUtils.checkDelPayRollNo(payRollNo));
    	
		try{

			if (WelcomeUI.DataStore.equals("1"))
				emp = empDao.loadEmpByNO(payRollNo);// ���ı��ļ��л�ȡ����
			else
				emp = empJDBCDao.loadEmpByNO(payRollNo);// �����ݿ��л�ȡ����
			
			System.out.println(emp.longString());

			if (confirmDelete()){
				//ȷ��ɾ�����ظ�Ա��
				return emp;	
			}
			
		}catch(NullPointerException e){
			throw new HRMISException("\nEmployee record for "+payRollNo +" not found!");
		}
        return null;
    }
    
    /**
     * ȷ���Ƿ�ɾ��
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
     * �Ƿ�Ҫɾ������Ա��
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
