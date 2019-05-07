package edu.fjnu.hrmis.ui.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import edu.fjnu.hrmis.dao.EmployeeDao;
import edu.fjnu.hrmis.dao.EmployeeDaoJDBCImpl;
import edu.fjnu.hrmis.dao.EmployeeDaoTxtImpl;
import edu.fjnu.hrmis.domain.Employee;
import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.Font;

import javax.swing.JTextArea;

public class ShowEmpsInfoFormUI extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowEmpsInfoFormUI frame = new ShowEmpsInfoFormUI();
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
	public ShowEmpsInfoFormUI() {
		setBounds(0, 0, 550, 400);
		setSize(550, 400);
		//setLocation(0, 0);
		getContentPane().setLayout(null);
		
		//设置背景图片
    	String path="img/41.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel = new JLabel(background);
		lblNewLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		getContentPane().add(lblNewLabel);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		
		JLabel label = new JLabel("\u5458\u5DE5\u4FE1\u606F");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(160, 31, 97, 24);
		getContentPane().add(label);
		
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		textArea.setBounds(61, 84, 4, 24);
		textArea.setBounds(20, 56, 501, 394);
		textArea.setSize(500, 300);
		getContentPane().add(textArea);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.CYAN);
		menuBar.setBackground(Color.CYAN);
		menuBar.setBounds(0, 0, 652, 21);
		getContentPane().add(menuBar);
		
		JButton button = new JButton("\u663E\u793A\u5458\u5DE5\u6240\u6709\u4FE1\u606F");
		button.setForeground(Color.BLUE);
		menuBar.add(button);
		
		JButton button_1 = new JButton("\u683C\u5F0F\u663E\u793A\u6240\u6709\u4FE1\u606F");
		button_1.setForeground(Color.BLUE);
		button_1.addMouseListener(new MouseAdapter() {
			/**
			 * 格式显示员工所有信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				
				EmployeeDao empDao = null;
				if (CommonUtils.DataStore==1)
					empDao=new EmployeeDaoTxtImpl();
				else
					empDao=new EmployeeDaoJDBCImpl();
				
				String infoList="";//员工信息集

				TreeSet<Employee> empSet = new TreeSet<Employee>();
				List<Employee> empList = new ArrayList<Employee>();

			    empList = empDao.loadEmps();
				empSet.addAll(empList);

				for (Employee emp : empSet) {
					infoList+=emp.longFormattedString();
					infoList+="\n";
				}
				
				textArea.setText(infoList);
			}
		});
		menuBar.add(button_1);
		
		JButton button_2 = new JButton("\u663E\u793A\u5458\u5DE5\u90E8\u5206\u4FE1\u606F");
		button_2.setForeground(Color.BLUE);
		button_2.addMouseListener(new MouseAdapter() {
			/**
			 * 显示员工部分信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeDao empDao = null;
				if (CommonUtils.DataStore == 1)
					empDao = new EmployeeDaoTxtImpl();
				else
					empDao = new EmployeeDaoJDBCImpl();

				List<Employee> empList = new ArrayList<Employee>();
				String infoList = "";

				empList = empDao.loadEmps();

				for (Employee emp : empList) {
					infoList += emp.shortString();
					infoList += "\n";
				}

				textArea.setText(infoList);
			}
		});
		menuBar.add(button_2);
		
		JButton button_3 = new JButton("\u683C\u5F0F\u663E\u793A\u90E8\u5206\u4FE1\u606F");
		button_3.setForeground(Color.BLUE);
		button_3.addMouseListener(new MouseAdapter() {
			/**
			 * 格式显示员工部分信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeDao empDao = null;
				if (CommonUtils.DataStore == 1)
					empDao = new EmployeeDaoTxtImpl();
				else
					empDao = new EmployeeDaoJDBCImpl();

				TreeSet<Employee> empSet = new TreeSet<Employee>();
				List<Employee> empList = new ArrayList<Employee>();

				String infoList = "";

				empList = empDao.loadEmps();

				empSet.addAll(empList);

				for (Employee emp : empSet) {
					infoList += emp.shortFormattedString();
					infoList += "\n";
				}
				textArea.setText(infoList);
			}
		});
		menuBar.add(button_3);
		
		JButton btnNewButton = new JButton("\u5173\u95ED\u7A97\u53E3");
		btnNewButton.addMouseListener(new MouseAdapter() {
			/**
			 * 关闭窗口
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(315, 34, 93, 23);
		getContentPane().add(btnNewButton);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		button.addMouseListener(new MouseAdapter() {
			/**
			 * 显示员工所有信息
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				EmployeeDao empDao = null;
				if (CommonUtils.DataStore == 1)
					empDao = new EmployeeDaoTxtImpl();
				else
					empDao = new EmployeeDaoJDBCImpl();

				List<Employee> empList = new ArrayList<Employee>();
				String infoList = "";

				empList = empDao.loadEmps();

				for (Employee emp : empList) {
					infoList += emp.longString();
					infoList += "\n";
				}

				textArea.setText(infoList);
			}
		});
	}
}
