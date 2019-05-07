package edu.fjnu.hrmis.ui.gui;


import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class WelcomeFormUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFormUI frame = new WelcomeFormUI();
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
	public  WelcomeFormUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setSize(500, 300); 
		setResizable(false);   
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//设置背景图片
		String path="img/6.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel = new JLabel(background);
		lblNewLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		contentPane.add(lblNewLabel);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		
		JLabel label = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u5174\u90A6\u8D44\u8BAF\u4EBA\u529B\u8D44\u6E90\u7BA1\u7406\u7CFB\u7EDF");
		label.setForeground(Color.CYAN);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(125, 28, 359, 29);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8BF7\u9009\u62E9\u6570\u636E\u5B58\u50A8\u65B9\u5F0F");
		label_1.setForeground(Color.CYAN);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(286, 189, 171, 15);
		contentPane.add(label_1);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("\u6587\u672C\u6587\u4EF6\u5B58\u50A8");
		rdbtnNewRadioButton.setForeground(Color.CYAN);
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.setBounds(246, 210, 121, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("MySQL\u6570\u636E\u5E93");
		rdbtnNewRadioButton_1.setForeground(Color.CYAN);
		rdbtnNewRadioButton_1.setOpaque(false);
		rdbtnNewRadioButton_1.setBounds(382, 210, 106, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		bg.add(rdbtnNewRadioButton_1);
		
		JButton button = new JButton("\u8FDB\u5165\u7CFB\u7EDF");
		button.setBackground(Color.ORANGE);
		button.setForeground(Color.CYAN);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//数据存储方式的选择
				if(rdbtnNewRadioButton.isSelected()){
					CommonUtils.DataStore=1;
				}
				else{
					CommonUtils.DataStore=2;
				}
				//跳转登录界面
				LoaderFormUI loader=new LoaderFormUI();
				loader.setVisible(true);
				dispose();//关闭当前页面
			}
		});
		button.setBounds(321, 239, 93, 23);
		contentPane.add(button);
	}
}
