package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class PasswordFormUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordFormUI frame = new PasswordFormUI();
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
	public PasswordFormUI() {
		setBackground(Color.MAGENTA);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(300, 100); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u8BF7\u51FA\u793A\u7BA1\u7406\u5458\u5BC6\u7801\uFF1A");
		label.setForeground(Color.MAGENTA);
		label.setBounds(10, 13, 123, 15);
		contentPane.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 10, 80, 21);
		contentPane.add(passwordField);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 10));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(20, 38, 254, 15);
		contentPane.add(lblNewLabel);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.setForeground(Color.MAGENTA);
		button.addMouseListener(new MouseAdapter() {
			/**
			 * 管理员用户删除是密码验证
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!CommonUtils.getMD5(passwordField.getText().toString()).toString().equals(CommonUtils.getUser().getUserPwd()))
				{
					lblNewLabel.setText("管理员密码错误，无法进行此操作！");
					CommonUtils.delepass=0;
				}
				else{
					lblNewLabel.setForeground(Color.MAGENTA);
					lblNewLabel.setText("点击主菜单的‘删除员工记录’按钮可进入操作");
					CommonUtils.delepass=1;	
				}
				
			}
		});
		button.setBounds(221, 9, 63, 23);
		contentPane.add(button);
		
	}
}
