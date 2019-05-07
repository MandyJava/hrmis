/**
 * 
 */
package edu.fjnu.hrmis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.ui.cui.WelcomeUI;


/**
 * 输入验证工具
 * @author 梦
 *
 */
public class CheckInputUtils {
	
	/**
	 * 验证输入的员工号是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkPayRollNo(String entry){
		int i = 0;
		EmployeeDao empDao = new EmployeeDaoTxtImpl();
		EmployeeDao empJDBCDao = new EmployeeDaoJDBCImpl();

		List<Employee> empList = new ArrayList<Employee>();

		if (WelcomeUI.DataStore.equals("1"))
			empList = empDao.loadEmps();// 从文本文件中获得数据
		else
			empList = empJDBCDao.loadEmps();// 从数据库中获得数据
		
		try{
			CommonUtils.checkBlankEntry(entry);
			Integer.parseInt(entry);
			if(entry.length()!=3)
				throw new HRMISException("Payroll number must be three digits");
			for (Employee emp : empList) {
				if(emp.getPayRollNo().equals(entry)){
					i=1;
				    break;
				}
			}
			if(i==1)
				throw new HRMISException("Payroll number can't repeat");
			
		}catch(BlankEntryException e){
			System.out.println("No payroll number entered –try again");	
			return false;
		}catch(NumberFormatException e){
			System.out.println("Payroll number can contain only numerical characters");	
			return false;
		}catch(HRMISException e){
			System.out .println(e.getMessage());
			return false;
		}
		return true;	
	}

	/**
	 * 验证输入的员工电话号码是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkPhone(String entry){
		Pattern p = Pattern.compile("0[2-8]{1}-[1-9]{1}[0-9]{7}");
		Matcher m = p.matcher(entry);
		try {
			CommonUtils.checkBlankEntry(entry);
			if (!m.matches())
				throw new HRMISException();
		} catch (BlankEntryException e) {
			System.out.println("No phone number entered–try again ");
			return false;
		}catch (HRMISException e) {
			System.out.println("Invalid phone number–try again");
			return false;
		}
		return true;
		
	}
	
	/**
	 * 验证输入的员工姓是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkLastName(String entry) {
		Pattern p = Pattern.compile("^[ A-Za-z]*$");
		Matcher m = p.matcher(entry);
		try {
			CommonUtils.checkBlankEntry(entry);
			if (!m.matches())
				throw new HRMISException();
		} catch (BlankEntryException e) {
			System.out.println("No last name entered–try again ");
			return false;
		}catch (HRMISException e) {
			System.out.println("Last name can contain only alphabetical characters and spaces");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证输入的员工的名是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkFirstName(String entry){
		Pattern p = Pattern.compile("^[ A-Za-z]*$");
		Matcher m = p.matcher(entry);
		try {
			CommonUtils.checkBlankEntry(entry);
			if (!m.matches())
				throw new HRMISException();
		}catch (BlankEntryException e) {
			System.out.println("No Firstname entered–try again ");
			return false;
		}catch (HRMISException e) {
			System.out.println("Firstname can contain only alphabetical characters and spaces");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证输入的员工的字是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkInitial(String entry){
		Pattern p = Pattern.compile("^[A-Za-z]*$");
		Matcher m = p.matcher(entry);
		try {
			CommonUtils.checkBlankEntry(entry);
			if(entry.length()!=1)
				throw new HRMISException("Middle Init must be one digits");
			if (!m.matches())
				throw new HRMISException("Middle Init can contain only alphabetical characters");
		}catch (BlankEntryException e) {
			System.out.println("No Middle Init entered–try again ");
			return false;
		}catch (HRMISException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
		
	}
	
	/**
	 * 验证输入的员工的部门号码是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkDeptNo(String entry){
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(entry);
		try {
			CommonUtils.checkBlankEntry(entry);
			if (!m.matches())
				throw new HRMISException();
		}catch (BlankEntryException e) {
			System.out.println("No Dept# entered–try again");
			return false;
		}catch (HRMISException e) {
			System.out.println("Dept # can contain only digits with no spaces");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证输入的员工的职称是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkJobTitle(String entry){
		Pattern p = Pattern.compile("^[ A-Za-z]*$");
		Matcher m = p.matcher(entry);
		try {
			CommonUtils.checkBlankEntry(entry);
			if (!m.matches())
				throw new HRMISException();
		}catch (BlankEntryException e) {
			System.out.println("No Job title entered–try again ");
			return false;
		}catch (HRMISException e) {
			System.out.println("Job titlecan contain only alphabetical characters and spaces");
			return false;
		}
		return true;
	}
	
	/**
	 * 验证输入的员工的雇佣日期是否有效
	 * @param entry
	 * @return
	 */
	public static boolean checkHiringDate(String entry){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			CommonUtils.checkBlankEntry(DateFormatChange(entry));
			Date d = sdf.parse(DateFormatChange(entry));//将格式化字符串转为日期
			String s = sdf.format(d);//将日期再转为字符串
			if (DateFormatChange(entry).compareTo(s) != 0)
				throw new HRMISException();
		} catch (BlankEntryException e) {
			System.out.println("No date hired entered–try again");
			return false;
		} catch (ParseException e) {
			System.out.println("Invalid Date Hired");
			return false;
		} catch (HRMISException e) {
			System.out.println("Invalid Date Hired");
			return false;
		}
    	return true;
	}
	
	/**
	 * 日期（dd-mm-yyyy）格式转化成（yyyy-mm-dd）格式
	 * @param entry
	 * @return
	 */
	public static String DateFormatChange(String entry){
		
		Pattern p = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4}");
		Matcher m = p.matcher(entry);
		if(!m.matches())
			throw new HRMISException();
		
		String[] section = entry.split("\\-");
		return section[2]+"-"+section[1]+"-"+section[0];
	}
	

	/**
	 * 判断删除时所输入的员工号是否合法
	 * @param entry
	 * @return
	 */
	public static boolean checkDelPayRollNo(String entry){
		
		try{
			CommonUtils.checkBlankEntry(entry);
			Integer.parseInt(entry);
			if(entry.length()!=3)
				throw new HRMISException("Payroll number can contain only numerical characters");
			
		}catch(BlankEntryException e){
			System.out.println("No payroll number entered –try again");	
			return false;
		}catch(NumberFormatException e){
			System.out.println("Payroll number can contain only numerical characters");	
			return false;
		}catch(HRMISException e){
			System.out .println(e.getMessage());
			return false;
		}
		return true;	
	}


}
