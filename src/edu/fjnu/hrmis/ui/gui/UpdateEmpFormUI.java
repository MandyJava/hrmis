package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateEmpFormUI extends JInternalFrame {
	private JTextField textpayroll;
	private JTextField phone;
	private JTextField lastname;
	private JTextField firstname;
	private JTextField init;
	private JTextField dept;
	private JTextField job;
	private JTextField date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmpFormUI frame = new UpdateEmpFormUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateEmpFormUI() {
		setBounds(100, 100, 450, 300);
		setBounds(0, 0, 550, 400);
		setSize(550, 400);
		getContentPane().setLayout(null);
		
		//设置背景图片
    	String path="img/41.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel_5 = new JLabel(background);
		lblNewLabel_5.setBounds(0, 0, this.getWidth(), this.getHeight());
		getContentPane().add(lblNewLabel_5);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel_5,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		
		JLabel label = new JLabel("payroll number\uFF1A");
		label.setBounds(127, 55, 111, 15);
		getContentPane().add(label);
		
		textpayroll = new JTextField();
		textpayroll.setColumns(10);
		textpayroll.setBounds(219, 52, 66, 21);
		getContentPane().add(textpayroll);
		
		JLabel label_1 = new JLabel("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		label_1.setBounds(219, 10, 133, 31);
		getContentPane().add(label_1);
		
		final JLabel payroll_notice = new JLabel("");
		payroll_notice.setForeground(Color.RED);
		payroll_notice.setFont(new Font("宋体", Font.BOLD, 10));
		payroll_notice.setBounds(150, 80, 374, 15);
		getContentPane().add(payroll_notice);
		
		JLabel label_3 = new JLabel("Phone Number\uFF1A");
		label_3.setBounds(18, 113, 97, 15);
		getContentPane().add(label_3);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(127, 110, 97, 21);
		getContentPane().add(phone);
		
		final JLabel phone_notice = new JLabel("");
		phone_notice.setForeground(Color.RED);
		phone_notice.setFont(new Font("宋体", Font.BOLD, 10));
		phone_notice.setBounds(10, 138, 235, 15);
		getContentPane().add(phone_notice);
		
		JLabel label_5 = new JLabel("Last Name\uFF1A");
		label_5.setBounds(36, 178, 79, 15);
		getContentPane().add(label_5);
		
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(127, 175, 97, 21);
		getContentPane().add(lastname);
		
		final JLabel last_notice = new JLabel("");
		last_notice.setForeground(Color.RED);
		last_notice.setFont(new Font("宋体", Font.BOLD, 10));
		last_notice.setBounds(10, 203, 302, 15);
		getContentPane().add(last_notice);
		
		JLabel label_7 = new JLabel("First Name\uFF1A");
		label_7.setBounds(31, 239, 84, 15);
		getContentPane().add(label_7);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(127, 236, 97, 21);
		getContentPane().add(firstname);
		
		final JLabel first_notice = new JLabel("");
		first_notice.setForeground(Color.RED);
		first_notice.setFont(new Font("宋体", Font.BOLD, 10));
		first_notice.setBounds(10, 264, 302, 15);
		getContentPane().add(first_notice);
		
		JLabel label_9 = new JLabel("Middle Init\uFF1A");
		label_9.setBounds(34, 294, 84, 15);
		getContentPane().add(label_9);
		
		init = new JTextField();
		init.setColumns(10);
		init.setBounds(128, 291, 97, 21);
		getContentPane().add(init);
		
		final JLabel init_notice = new JLabel("");
		init_notice.setForeground(Color.RED);
		init_notice.setFont(new Font("宋体", Font.BOLD, 10));
		init_notice.setBounds(31, 316, 235, 15);
		getContentPane().add(init_notice);
		
		JLabel label_11 = new JLabel("Dept #\uFF1A");
		label_11.setBounds(336, 108, 54, 15);
		getContentPane().add(label_11);
		
		dept = new JTextField();
		dept.setColumns(10);
		dept.setBounds(386, 105, 97, 21);
		getContentPane().add(dept);
		
		final JLabel dept_notice = new JLabel("");
		dept_notice.setForeground(Color.RED);
		dept_notice.setFont(new Font("宋体", Font.BOLD, 10));
		dept_notice.setBounds(311, 133, 213, 15);
		getContentPane().add(dept_notice);
		
		JLabel label_13 = new JLabel("Job Title\uFF1A");
		label_13.setBounds(324, 173, 66, 15);
		getContentPane().add(label_13);
		
		job = new JTextField();
		job.setColumns(10);
		job.setBounds(386, 170, 97, 21);
		getContentPane().add(job);
		
		final JLabel job_notice = new JLabel("");
		job_notice.setForeground(Color.RED);
		job_notice.setFont(new Font("宋体", Font.BOLD, 10));
		job_notice.setBounds(289, 198, 235, 15);
		getContentPane().add(job_notice);
		
		JLabel label_15 = new JLabel("Date Hired\uFF1A");
		label_15.setBounds(318, 234, 72, 15);
		getContentPane().add(label_15);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(386, 231, 97, 21);
		getContentPane().add(date);
		
		final JLabel date_notice = new JLabel("\r\n");
		date_notice.setForeground(Color.RED);
		date_notice.setFont(new Font("宋体", Font.BOLD, 10));
		date_notice.setBounds(336, 259, 188, 15);
		getContentPane().add(date_notice);
		
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addMouseListener(new MouseAdapter() {
			/**
			 * 通过员工号定位员工信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				Employee emp=new Employee();
				EmployeeDao empDao = null;
				
				if (CommonUtils.DataStore==1)
					empDao=new EmployeeDaoTxtImpl();
				else
					empDao=new EmployeeDaoJDBCImpl();
				
				String entry=textpayroll.getText().toString();
				
				//验证输入的员工号
				try{
					CommonUtils.checkBlankEntry(entry);
					Integer.parseInt(entry);
					if(entry.length()!=3)
						throw new HRMISException("Payroll number can contain only numerical characters");
					
					payroll_notice.setText("");
					
					//根据员工号回填员工信息
					emp=empDao.loadEmpByNO(entry);
					phone.setText(emp.getTelephoneCode());
					lastname.setText(emp.getLastName());
					firstname.setText(emp.getFirstName());
					init.setText(emp.getInitial());
					dept.setText(""+emp.getDeptNo());
					job.setText(emp.getJobTitle());
					date.setText(emp.getHiringDate());
					
				}catch(BlankEntryException e1){
					//System.out.println("No payroll number entered –try again");	
					payroll_notice.setText("No payroll number entered –try again");
					phone.setText("");
					lastname.setText("");
					firstname.setText("");
					init.setText("");
					dept.setText("");
					job.setText("");
					date.setText("");
				}catch(NumberFormatException e1){
					//System.out.println("Payroll number can contain only numerical characters");	
					payroll_notice.setText("Payroll number can contain only numerical characters");
					phone.setText("");
					lastname.setText("");
					firstname.setText("");
					init.setText("");
					dept.setText("");
					job.setText("");
					date.setText("");
				}catch(NullPointerException e1){
					payroll_notice.setText("Employee record for "+entry +" not found!");
					phone.setText("");
					lastname.setText("");
					firstname.setText("");
					init.setText("");
					dept.setText("");
					job.setText("");
					date.setText("");
					
				}catch(HRMISException e1){
					//System.out .println(e1.getMessage());
					payroll_notice.setText(e1.getMessage());
					phone.setText("");
					lastname.setText("");
					firstname.setText("");
					init.setText("");
					dept.setText("");
					job.setText("");
					date.setText("");
				}
	
			}
		});
		button.setBounds(331, 51, 74, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("\u66F4\u65B0");
		button_1.addMouseListener(new MouseAdapter() {
			/**
			 * 修改员工信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
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
				String entryJob=job.getText().toString();
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
				if (
						phone_notice.getText() == null
						&& last_notice.getText() == null
						&& first_notice.getText() == null
						&& init_notice.getText() == null
						&& dept_notice.getText() == null
						&& job_notice.getText() == null
						&& date_notice.getText() == null) {
					
					Employee employee=new Employee();
					EmployeeDao empDao = null;
					
					if (CommonUtils.DataStore==1)
						empDao=new EmployeeDaoTxtImpl();
					else
						empDao=new EmployeeDaoJDBCImpl();
					
					//修改员工相关信息
					employee.setPayRollNo(textpayroll.getText().toString());
					employee.setTelephoneCode(entryPhone);
					employee.setLastName(entryLast);
					employee.setFirstName(entryFirst);
					employee.setInitial(entryInit);
					employee.setDeptNo(Integer.parseInt(entryDept));
					employee.setJobTitle(entryJob);
					employee.setHiringDate(entryDate);

					empDao.updateEmp(employee);
					JOptionPane.showMessageDialog(null, "更新成功^_^", "兴邦资讯",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
					
					//用户操作记录存盘
					String record="更新员工:["+employee.longString()+"]";
					CommonUtils.userLog(record);
				}
			}
		});
		button_1.setBounds(239, 338, 133, 23);
		getContentPane().add(button_1);
	}
}
