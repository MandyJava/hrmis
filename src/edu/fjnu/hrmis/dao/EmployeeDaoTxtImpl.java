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
 * 员工文本操作实现
 * @author 梦
 *
 */
public class EmployeeDaoTxtImpl implements EmployeeDao {

	private static final String EMP_DATA_FILE ="f:/records.txt"; //员工记录文档
	private  static List<Employee> empList;
	
	/**
	 * 加载所有的员工信息
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
			
			//新建数据库时将文件数据保存到数据库只操作一次
			//EmployeeDao empJDBCDao=new EmployeeDaoJDBCImpl();
			//empJDBCDao.addEmp(emp);
		}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		throw new HRMISException("数据文件没有被找到!");
	} catch (IOException e) {
		e.printStackTrace();
	}
		return empList;
	}

	/**
	 * 增加员工
	 */
	@Override
	public void addEmp(Employee emp) {
		empList=loadEmps();
		empList.add(emp);
		writeFile();

	}

	
	 /**
     * 删除员工
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
     * 更新员工信息
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
	 * 将操作后的员工记录写入文件
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
			throw new HRMISException("数据文件没有被找到!");
		} finally {
			writer.flush();
			writer.close();
		}
	}

}
