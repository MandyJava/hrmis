package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

public class SearchEmpFormUI extends JInternalFrame {
	private JTextField keyword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchEmpFormUI frame = new SearchEmpFormUI();
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
	public SearchEmpFormUI() {
		setBounds(100, 100, 450, 300);
		setBounds(0, 0, 550, 400);
		setSize(550, 400);
		getContentPane().setLayout(null);
		
		//设置背景图片
    	String path="img/p3.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel = new JLabel(background);
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		getContentPane().add(lblNewLabel);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		
		JLabel label = new JLabel("\u67E5\u8BE2\u5458\u5DE5\u4FE1\u606F");
		label.setForeground(Color.CYAN);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(345, 158, 167, 32);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5173\u952E\u5B57\uFF1A");
		label_1.setForeground(Color.CYAN);
		label_1.setBounds(277, 216, 54, 15);
		getContentPane().add(label_1);
		
		keyword = new JTextField();
		keyword.setBackground(Color.LIGHT_GRAY);
		keyword.setForeground(Color.CYAN);
		keyword.setBounds(329, 213, 66, 21);
		getContentPane().add(keyword);
		keyword.setColumns(10);
		
		final JTextArea textArea = new JTextArea();
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.CYAN);
		textArea.setBounds(136, 246, 398, 115);
		getContentPane().add(textArea);
		textArea.setAutoscrolls(true);
		

		
		JButton button = new JButton("\u67E5\u8BE2");
		button.setForeground(Color.CYAN);
		button.setBackground(Color.LIGHT_GRAY);
		button.addMouseListener(new MouseAdapter() {
			/**
			 * 查询员工信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				String keyEntry = keyword.getText().toString();
				String foundList = "";//搜索记录集
				List<Employee> empList = new ArrayList<Employee>();
				List<Employee> searchEmpList = new ArrayList<Employee>();

				EmployeeDao empDao = null;
				if (CommonUtils.DataStore == 1)
					empDao = new EmployeeDaoTxtImpl();
				else
					empDao = new EmployeeDaoJDBCImpl();

				empList = empDao.loadEmps();

				for (Employee emp : empList) {
					if (CommonUtils.isFound(emp.toString(), keyEntry)) {
						searchEmpList.add(emp);
					}
				}

				if (searchEmpList.size() == 0) {
					textArea.setText("Keyword –" + keyEntry + "-not found");
				} else {
					for (Employee emp : searchEmpList) {
						foundList += emp.longString();
						foundList += "\n";
					}
					textArea.setText(foundList);
				}
			}
		});
		button.setBounds(431, 213, 93, 23);
		getContentPane().add(button);
		
		JButton btnNewButton = new JButton("\u5173\u95ED\u7A97\u53E3");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addMouseListener(new MouseAdapter() {
			/**
			 * 关闭窗口
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
		});
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBounds(33, 325, 93, 23);
		getContentPane().add(btnNewButton);
			
		
	}
}
