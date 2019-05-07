package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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
import java.awt.Font;
import java.awt.Color;

public class DeleteFormUI extends JInternalFrame {
	private JTextField payroll;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteFormUI frame = new DeleteFormUI();
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
	public DeleteFormUI() {
		setBounds(100, 100, 450, 300);
		setBounds(0, 0, 550, 400);
		setSize(550, 400);
		getContentPane().setLayout(null);
		
		//设置背景图片
    	String path="img/11.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel = new JLabel(background);
		lblNewLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		getContentPane().add(lblNewLabel);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		

		
		JLabel label = new JLabel("\u5220\u9664\u5458\u5DE5\u8BB0\u5F55");
		label.setForeground(Color.MAGENTA);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(323, 10, 162, 37);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5458\u5DE5\u53F7\uFF1A");
		label_1.setForeground(Color.MAGENTA);
		label_1.setBounds(278, 61, 54, 15);
		getContentPane().add(label_1);
		
		payroll = new JTextField();
		payroll.setBackground(Color.BLUE);
		payroll.setForeground(Color.MAGENTA);
		payroll.setBounds(331, 58, 66, 21);
		getContentPane().add(payroll);
		payroll.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		textField_1.setForeground(Color.MAGENTA);
		textField_1.setBackground(Color.BLUE);
		textField_1.setBounds(109, 86, 425, 21);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		final JButton button_1 = new JButton("\u5220\u9664");
		button_1.setForeground(Color.MAGENTA);
		button_1.setBackground(Color.BLUE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String record=null;//用户操作记录
				
				int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "兴邦资讯",
						JOptionPane.YES_NO_OPTION);
				
				if (n == JOptionPane.YES_OPTION) {
					Employee emp = new Employee();
					EmployeeDao empDao = null;
					if (CommonUtils.DataStore == 1)
						empDao = new EmployeeDaoTxtImpl();
					else
						empDao = new EmployeeDaoJDBCImpl();

					emp = empDao.loadEmpByNO(payroll.getText().toString());
					if (empDao.removeEmp(emp)) {
						JOptionPane.showMessageDialog(null, "删除成功！", "兴邦资讯",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						
						//用户操作记录存盘
						record="删除员工:["+emp.longString()+"]";
						CommonUtils.userLog(record);
					}

				} else if (n == JOptionPane.NO_OPTION) {
					//不做任何操作
				}
			}
		});
		button_1.setBounds(368, 141, 93, 23);
		getContentPane().add(button_1);
		button_1.setVisible(false);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.setBackground(Color.BLUE);
		button.setForeground(Color.MAGENTA);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//验证删除时输入的员工号是否合法
				Employee emp = new Employee();
				EmployeeDao empDao = null;
				if (CommonUtils.DataStore == 1)
					empDao = new EmployeeDaoTxtImpl();
				else
					empDao = new EmployeeDaoJDBCImpl();

				String payRollNo = payroll.getText().toString();
				
				try{
					Integer.parseInt(payRollNo);
					if(payRollNo.length()!=3)
						throw new HRMISException("Payroll number can contain only numerical characters");
					emp = empDao.loadEmpByNO(payRollNo);
					textField_1.setText(emp.longString());
					button_1.setVisible(true);
				}catch(BlankEntryException e1){
					textField_1.setText("No payroll number entered –try again");
					button_1.setVisible(false);
	
				}catch(NumberFormatException e1){	
					textField_1.setText("Payroll number can contain only numerical characters");
					button_1.setVisible(false);
				}catch (NullPointerException e1) {
					textField_1.setText("Employee record for " + payRollNo
							+ " not found!");
					button_1.setVisible(false);
				}catch(HRMISException e1){
					textField_1.setText(e1.getMessage());
					button_1.setVisible(false);

				}
   
			}
		});
		button.setBounds(419, 57, 93, 23);
		getContentPane().add(button);
		
		
	}
}
