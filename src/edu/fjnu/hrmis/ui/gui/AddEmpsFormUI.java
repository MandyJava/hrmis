package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Font;
import java.awt.Color;

public class AddEmpsFormUI extends JInternalFrame {
	private JTextField payroll;
	private JTextField phone;
	private JTextField lastname;
	private JTextField firstname;
	private JTextField dept;
	private JTextField jobtitle;
	private JTextField date;
	private JTextField init;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmpsFormUI frame = new AddEmpsFormUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	public AddEmpsFormUI() {
		getContentPane().setForeground(Color.RED);
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 10));
		setBounds(0, 0, 550, 400);
		setSize(550, 400);
		getContentPane().setLayout(null);
		
		
		//设置背景图片
    	String path="img/41.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel_3 = new JLabel(background);
		lblNewLabel_3.setBounds(0, 0, this.getWidth(), this.getHeight());
		getContentPane().add(lblNewLabel_3);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel_3,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		
		JLabel label = new JLabel("\u65B0\u589E\u5458\u5DE5\u8BB0\u5F55");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(219, 10, 147, 32);
		getContentPane().add(label);
		
		JLabel lblPayrollNumber = new JLabel("payroll number\uFF1A");
		lblPayrollNumber.setBounds(32, 69, 105, 15);
		getContentPane().add(lblPayrollNumber);
		
		payroll = new JTextField();
		payroll.setBounds(127, 66, 97, 21);
		getContentPane().add(payroll);
		payroll.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number\uFF1A");
		lblPhoneNumber.setBounds(22, 129, 115, 15);
		getContentPane().add(lblPhoneNumber);
		
		phone = new JTextField();
		phone.setBounds(127, 126, 97, 21);
		getContentPane().add(phone);
		phone.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name\uFF1A");
		lblLastName.setBounds(48, 194, 79, 15);
		getContentPane().add(lblLastName);
		
		lastname = new JTextField();
		lastname.setBounds(127, 191, 97, 21);
		getContentPane().add(lastname);
		lastname.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name\uFF1A");
		lblFirstName.setBounds(48, 255, 79, 15);
		getContentPane().add(lblFirstName);
		
		firstname = new JTextField();
		firstname.setBounds(127, 252, 97, 21);
		getContentPane().add(firstname);
		firstname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Dept #\uFF1A");
		lblNewLabel.setBounds(346, 129, 54, 15);
		getContentPane().add(lblNewLabel);
		
		dept = new JTextField();
		dept.setBounds(396, 126, 97, 21);
		getContentPane().add(dept);
		dept.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Job Title\uFF1A");
		lblNewLabel_1.setBounds(334, 194, 66, 15);
		getContentPane().add(lblNewLabel_1);
		
		jobtitle = new JTextField();
		jobtitle.setBounds(396, 191, 97, 21);
		getContentPane().add(jobtitle);
		jobtitle.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date Hired\uFF1A");
		lblNewLabel_2.setBounds(328, 255, 72, 15);
		getContentPane().add(lblNewLabel_2);
		
		date = new JTextField();
		date.setBounds(396, 252, 97, 21);
		getContentPane().add(date);
		date.setColumns(10);
		
		final JLabel payrol_notice = new JLabel("");
		payrol_notice.setForeground(Color.RED);
		payrol_notice.setFont(new Font("宋体", Font.BOLD, 10));
		payrol_notice.setBounds(10, 94, 255, 15);
		getContentPane().add(payrol_notice);
		
		final JLabel phone_notice = new JLabel("");
		phone_notice.setForeground(Color.RED);
		phone_notice.setFont(new Font("宋体", Font.BOLD, 10));
		phone_notice.setBounds(10, 154, 235, 15);
		getContentPane().add(phone_notice);
		
		final JLabel last_notice = new JLabel("");
		last_notice.setForeground(Color.RED);
		last_notice.setFont(new Font("宋体", Font.BOLD, 10));
		last_notice.setBounds(10, 219, 302, 15);
		getContentPane().add(last_notice);
		
		final JLabel first_notice = new JLabel("");
		first_notice.setForeground(Color.RED);
		first_notice.setFont(new Font("宋体", Font.BOLD, 10));
		first_notice.setBounds(10, 280, 302, 15);
		getContentPane().add(first_notice);
		
		JLabel lblNewLabel_4 = new JLabel("Middle Init\uFF1A");
		lblNewLabel_4.setBounds(321, 69, 79, 15);
		getContentPane().add(lblNewLabel_4);
		
		init = new JTextField();
		init.setBounds(396, 66, 97, 21);
		getContentPane().add(init);
		init.setColumns(10);
		
		final JLabel init_notice = new JLabel("");
		init_notice.setForeground(Color.RED);
		init_notice.setFont(new Font("宋体", Font.BOLD, 10));
		init_notice.setBounds(299, 91, 235, 15);
		getContentPane().add(init_notice);
		
		final JLabel dept_notice = new JLabel("");
		dept_notice.setForeground(Color.RED);
		dept_notice.setFont(new Font("宋体", Font.BOLD, 10));
		dept_notice.setBounds(321, 154, 213, 15);
		getContentPane().add(dept_notice);
		
		final JLabel job_notice = new JLabel("");
		job_notice.setForeground(Color.RED);
		job_notice.setFont(new Font("宋体", Font.BOLD, 10));
		job_notice.setBounds(299, 219, 235, 15);
		getContentPane().add(job_notice);
		
		final JLabel date_notice = new JLabel("\r\n");
		date_notice.setForeground(Color.RED);
		date_notice.setFont(new Font("宋体", Font.BOLD, 10));
		date_notice.setBounds(346, 280, 188, 15);
		getContentPane().add(date_notice);
		
		JButton button = new JButton("\u65B0\u589E\u5458\u5DE5");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = 0;
				String record=null;//用户操作记录
				EmployeeDao empDao = null;
				//数据存储选择
				if (CommonUtils.DataStore==1)
					empDao=new EmployeeDaoTxtImpl();
				else
					empDao=new EmployeeDaoJDBCImpl();

				List<Employee> empList = empDao.loadEmps();

				String entry=payroll.getText().toString();
				
				//验证员工号
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
					payrol_notice.setText(null);
				}catch(BlankEntryException e1){	
					payrol_notice.setText("No payroll number entered –try again");
				}catch(NumberFormatException e1){	
					payrol_notice.setText("Contain only numerical characters");
				}catch(HRMISException e1){
				    payrol_notice.setText(e1.getMessage());
				}
				
				//验证员工电话号码
				String entryPhone=phone.getText().toString();
				
				Pattern p = Pattern.compile("0[2-8]{1}-[1-9]{1}[0-9]{7}");
				Matcher m = p.matcher(entryPhone);
				try {
					CommonUtils.checkBlankEntry(entryPhone);
					if (!m.matches())
						throw new HRMISException();
					phone_notice.setText(null);
				} catch (BlankEntryException e1) {
		             phone_notice.setText("No phone number entered–try again");
				}catch (HRMISException e1) {
				    phone_notice.setText("Invalid phone number–try again");
				}
				
				
				//验证员工的姓氏
				String entryLast=lastname.getText().toString();
				
				Pattern p1 = Pattern.compile("^[ A-Za-z]*$");
				Matcher m1 = p1.matcher(entryLast);
				try {
					CommonUtils.checkBlankEntry(entryLast);
					if (!m1.matches())
						throw new HRMISException();
					last_notice.setText(null);
				} catch (BlankEntryException e1) {
					last_notice.setText("No last name entered–try again");
				}catch (HRMISException e1) {
					last_notice.setText("Contain only alphabetical characters and spaces");
				}
				
				
				//验证员工的名字
				String entryFirst=firstname.getText().toString();
				Pattern p2 = Pattern.compile("^[ A-Za-z]*$");
				Matcher m2 = p2.matcher(entryFirst);
				try {
					CommonUtils.checkBlankEntry(entryFirst);
					if (!m2.matches())
						throw new HRMISException();
					first_notice.setText(null);
				}catch (BlankEntryException e1) {
					first_notice.setText("No First name entered–try again");
				}catch (HRMISException e1) {
					first_notice.setText("Contain only alphabetical characters and spaces");
				}
				
				
				//验证员工的字
				String entryInit=init.getText().toString();
				Pattern p3 = Pattern.compile("^[A-Za-z]*$");
				Matcher m3 = p3.matcher(entryInit);
				try {
					CommonUtils.checkBlankEntry(entryInit);
					if(entryInit.length()!=1)
						throw new HRMISException("Middle Init must be one digits");
					if (!m3.matches())
						throw new HRMISException("Contain only alphabetical characters");
					init_notice.setText(null);
				}catch (BlankEntryException e1) {
					init_notice.setText("No Middle Init entered–try again");
			
				}catch (HRMISException e1) {
			        init_notice.setText(e1.getMessage());
				}
				
				
				//验证员工部门号
				String entryDept=dept.getText().toString();
				Pattern p4 = Pattern.compile("^[0-9]*$");
				Matcher m4 = p4.matcher(entryDept);
				try {
					CommonUtils.checkBlankEntry(entryDept);
					if (!m4.matches())
						throw new HRMISException();
					dept_notice.setText(null);
				}catch (BlankEntryException e1) {
					dept_notice.setText("No Dept# entered–try again");
				}catch (HRMISException e1) {
					dept_notice.setText("Contain only digits with no spaces");
				}
				
				//验证员工职称
				String entryJob=jobtitle.getText().toString();
				Pattern p5 = Pattern.compile("^[ A-Za-z]*$");
				Matcher m5 = p5.matcher(entryJob);
				try {
					CommonUtils.checkBlankEntry(entryJob);
					if (!m5.matches())
						throw new HRMISException();
					job_notice.setText(null);
				}catch (BlankEntryException e1) {
					job_notice.setText("No Job title entered–try again");
				    
				}catch (HRMISException e1) {
					job_notice.setText("Only alphabetical characters and spaces");
				}
				
				//验证员工雇佣日期
				String entryDate=date.getText().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					CommonUtils.checkBlankEntry(CommonUtils.DateFormatChange(entryDate));
					Date d = sdf.parse(CommonUtils.DateFormatChange(entryDate));//将格式化字符串转为日期
					String s = sdf.format(d);//将日期再转为字符串
					if (CommonUtils.DateFormatChange(entryDate).compareTo(s) != 0)
						throw new HRMISException();
					date_notice.setText(null);
				} catch (BlankEntryException e1) {
					date_notice.setText("No date hired entered–try again");
				} catch (ParseException e1) {
					date_notice.setText("Invalid Date Hired");
				} catch (HRMISException e1) {
					date_notice.setText("Invalid Date Hired");
				}
		    	
             //验证通过
				if (payrol_notice.getText() == null
						&& phone_notice.getText() == null
						&& last_notice.getText() == null
						&& first_notice.getText() == null
						&& init_notice.getText() == null
						&& dept_notice.getText() == null
						&& job_notice.getText() == null
						&& date_notice.getText() == null) {
					
					Employee employee = new Employee();
					employee.setPayRollNo(entry);
					employee.setTelephoneCode(entryPhone);
					employee.setLastName(entryLast);
					employee.setFirstName(entryFirst);
					employee.setInitial(entryInit);
					employee.setDeptNo(Integer.parseInt(entryDept));
					employee.setJobTitle(entryJob);
					employee.setHiringDate(entryDate);

					empDao.addEmp(employee);
					JOptionPane.showMessageDialog(null, "增加成功^_^", "兴邦资讯",
							JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
					
					//用户操作记录存盘
					record="新增员工:["+employee.longString()+"]";
					CommonUtils.userLog(record);
					
				}

			}
		});
		button.setBounds(236, 319, 93, 23);
		getContentPane().add(button);
		
	}
	
}
