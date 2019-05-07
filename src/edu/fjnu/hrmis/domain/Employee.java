/**
 * 
 */
package edu.fjnu.hrmis.domain;

import edu.fjnu.hrmis.utils.CommonUtils;



/**
 * Ա��
 * @author ��
 *
 */
public class Employee extends ValueObject implements Comparable<Employee>{
	
	/** Ա���� */
	private String payRollNo;
	/** �칫�绰 */
	private String telephoneCode;
	/** Ա�������� */
	private String lastName;
	/** Ա�������� */
	private String firstName;
	/** Ա������ */
	private String initial;
	/** ���ź� */
	private Integer deptNo;
	/** ְ�� */
	private String jobTitle;
	/** �������� */
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
	 * ����ʽ���
	 * @return
	 */
	public String longString(){
		return this.toString();
	}
	
	/**
	 * ����ʽ(��ʽ��)���
	 * @return
	 */
	public String longFormattedString(){
		return String.format("%-10s%-10s%-5s%-5s%-15s%-5s%-20s%-20s", CommonUtils.fomatString(this.lastName),CommonUtils.fomatString(this.firstName),this.initial.toUpperCase(),this.payRollNo,this.telephoneCode,this.deptNo,CommonUtils.fomatString(this.jobTitle),this.hiringDate);
	}
	
	
	/**
	 * �̸�ʽ���
	 * @return
	 */
	public String shortString(){
		return CommonUtils.fomatString(this.lastName)+","+CommonUtils.fomatString(this.firstName)+","+this.telephoneCode;
	}
	
	
	/**
	 * �̸�ʽ(��ʽ��)���
	 * @return
	 */
	public String shortFormattedString(){
		return String.format("%-10s%-10s%-10s", CommonUtils.fomatString(this.lastName),CommonUtils.fomatString(this.firstName),this.telephoneCode);
	}
	
	
	/**
	 * ��Ա������ת����Ա������
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
	 * ��дcompareTo����ʵ��TreeSet����
	 */
	@Override
	public int compareTo(Employee otherEmployee) {
		if(!otherEmployee.lastName.equals(this.lastName))
			return this.lastName .compareTo(otherEmployee.lastName);//����Ա����lastName����
		return 0;
	}

	/**
	 * ��дequals������Ա�����������Ϊ������Ա������һ��
	 */
	@Override
	public boolean equals(Object obj){
		//��������
		if(obj==null) return false;
		if(obj==this) return true;
		if(!(obj instanceof Employee)) 
			return false;
		
		//Ա�����������Ϊ������Ա������һ��
		Employee otherEmp=(Employee)obj;
		return this.payRollNo.equals(otherEmp.payRollNo);
	}
	

}
