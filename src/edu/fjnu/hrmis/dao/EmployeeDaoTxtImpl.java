/**
 * 
 */
package edu.fjnu.hrmis.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * Ա���ı�����ʵ��
 * @author ��
 *
 */
public class EmployeeDaoTxtImpl implements EmployeeDao {

	private static final String EMP_DATA_FILE ="f:/records.txt"; //Ա����¼�ĵ�
	private  static List<Employee> empList;
	
	/**
	 * �������е�Ա����Ϣ
	 */
	@Override
	public List<Employee> loadEmps() { 
		CommonUtils.checkEmpsResource();
		empList=new ArrayList<Employee>();
		String empData=null;
	
	  try {
		BufferedReader reader  = new BufferedReader(new InputStreamReader(new FileInputStream(EMP_DATA_FILE)));
		
		while((empData=reader.readLine())!=null){

			Employee emp = Employee.getEmpFromDataStr(empData);
			empList.add(emp);
			
			//�½����ݿ�ʱ���ļ����ݱ��浽���ݿ�ֻ����һ��
			//EmployeeDao empJDBCDao=new EmployeeDaoJDBCImpl();
			//empJDBCDao.addEmp(emp);
		}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new HRMISException("�����ļ�û�б��ҵ�!");
	} catch (IOException e) {
		e.printStackTrace();
	}
		return empList;
	}

	/**
	 * ����Ա��
	 */
	@Override
	public void addEmp(Employee emp) {
		empList=loadEmps();
		empList.add(emp);
		writeFile();

	}

	
	 /**
     * ɾ��Ա��
     */
	@Override
	public boolean removeEmp(Employee emp) {
	   empList=loadEmps();
       if(empList.remove(emp)){
       writeFile();
       return true;
       }
		return false;
	}

	
	@Override
	public Employee loadEmpByNO(String payRollNo) {
		 empList=loadEmps();
		for (Employee emp : empList) {
			if(payRollNo.equals(emp.getPayRollNo()))
				return emp;
		}
		return null;
	}

	 /**
     * ����Ա����Ϣ
     */
	@Override
	public boolean updateEmp(Employee emp) {
		empList=loadEmps();
		for (Employee e : empList) {
			if(e.equals(emp)){
				//e=emp;
				empList.remove(e);
				empList.add(emp);
				writeFile();
				return true;
			}
		}

		return false;
	}
	
	/**
	 * ���������Ա����¼д���ļ�
	 */
	public void writeFile(){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream(EMP_DATA_FILE));
			for (Employee emp : empList) {
				writer.println(emp.longString());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new HRMISException("�����ļ�û�б��ҵ�!");
		} finally {
			writer.flush();
			writer.close();
		}
	}

}
