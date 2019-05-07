/**
 * 
 */
package edu.fjnu.hrmis.domain;

import edu.fjnu.hrmis.utils.CommonUtils;



/**
 * 员工
 * @author 梦
 *
 */
public class Employee extends ValueObject implements Comparable<Employee>{
	
	/** 员工号 */
	private String payRollNo;
	/** 办公电话 */
	private String telephoneCode;
	/** 员工的姓氏 */
	private String lastName;
	/** 员工的名字 */
	private String firstName;
	/** 员工的字 */
	private String initial;
	/** 部门号 */
	private Integer deptNo;
	/** 职称 */
	private String jobTitle;
	/** 雇用日期 */
	private String hiringDate;
	
	public String getPayRollNo() {
		return payRollNo;
	}
	public void setPayRollNo(String payRollNo) {
		this.payRollNo = payRollNo;
	}
	public String getTelephoneCode() {
		return telephoneCode;
	}
	public void setTelephoneCode(String telephoneCode) {
		this.telephoneCode = telephoneCode;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
	public Integer getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getHiringDate() {
		return hiringDate;
	}
	public void setHiringDate(String hiringDate) {
		this.hiringDate = hiringDate;
	}
	
	
	
	public String toString() {
		return this.payRollNo + ":" + this.telephoneCode + ":"
				+ CommonUtils.fomatString(this.lastName) + ":"
				+ CommonUtils.fomatString(this.firstName) + ":"
				+ this.initial.toUpperCase() + ":" + this.deptNo + ":"
				+ CommonUtils.fomatString(this.jobTitle) + ":"
				+ this.hiringDate;
	}
	
	
	/**
	 * 长格式输出
	 * @return
	 */
	public String longString(){
		return this.toString();
	}
	
	/**
	 * 长格式(格式化)输出
	 * @return
	 */
	public String longFormattedString(){
		return String.format("%-10s%-10s%-5s%-5s%-15s%-5s%-20s%-20s", CommonUtils.fomatString(this.lastName),CommonUtils.fomatString(this.firstName),this.initial.toUpperCase(),this.payRollNo,this.telephoneCode,this.deptNo,CommonUtils.fomatString(this.jobTitle),this.hiringDate);
	}
	
	
	/**
	 * 短格式输出
	 * @return
	 */
	public String shortString(){
		return CommonUtils.fomatString(this.lastName)+","+CommonUtils.fomatString(this.firstName)+","+this.telephoneCode;
	}
	
	
	/**
	 * 短格式(格式化)输出
	 * @return
	 */
	public String shortFormattedString(){
		return String.format("%-10s%-10s%-10s", CommonUtils.fomatString(this.lastName),CommonUtils.fomatString(this.firstName),this.telephoneCode);
	}
	
	
	/**
	 * 把员工数据转换成员工对象
	 * @param dataStr
	 * @return
	 */
	public static Employee getEmpFromDataStr(String dataStr){
		
		//111:02-98781999:Williams:Nick:T:35:Computer Officer:14-10-2000
		Employee emp = new Employee();
		
		String[] section = dataStr.split("\\:");
		emp.setPayRollNo(section[0]);
		emp.setTelephoneCode(section[1]);
		emp.setLastName(section[2]);
		emp.setFirstName(section[3]);
		emp.setInitial(section[4]);
		emp.setDeptNo(Integer.parseInt(section[5]));
		emp.setJobTitle(section[6]);
		emp.setHiringDate(section[7]);
		
		return emp;
	}
	
	/**
	 * 重写compareTo方法实现TreeSet排序
	 */
	@Override
	public int compareTo(Employee otherEmployee) {
		if(!otherEmployee.lastName.equals(this.lastName))
			return this.lastName .compareTo(otherEmployee.lastName);//按照员工的lastName排名
		return 0;
	}

	/**
	 * 重写equals方法，员工号相等则认为这两个员工对象一样
	 */
	@Override
	public boolean equals(Object obj){
		//特例处理
		if(obj==null) return false;
		if(obj==this) return true;
		if(!(obj instanceof Employee)) 
			return false;
		
		//员工号相等则认为这两个员工对象一样
		Employee otherEmp=(Employee)obj;
		return this.payRollNo.equals(otherEmp.payRollNo);
	}
	

}
