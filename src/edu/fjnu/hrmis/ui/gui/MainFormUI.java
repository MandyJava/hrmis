package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;



import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.Font;
import java.awt.Color;

public class MainFormUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFormUI frame = new MainFormUI();
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
	public MainFormUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 687, 465);
		setSize(700, 500); 
		setResizable(false);  
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		//���ñ���ͼƬ
		String path="img/6.jpg";
		ImageIcon background=new ImageIcon(path);
		
		JLabel lblNewLabel_2 = new JLabel(background);
		lblNewLabel_2.setBounds(0, 0, this.getWidth(), this.getHeight());
		contentPane.add(lblNewLabel_2);
		JPanel imgpanel=(JPanel)this.getContentPane();
		imgpanel.setOpaque(false);
		this.getLayeredPane().add(lblNewLabel_2,new Integer(Integer.MIN_VALUE));
		setVisible(true);
		
		
		
		JLabel lblNewLabel = new JLabel("\u64CD\u4F5C\u9009\u9879");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel.setBounds(27, 66, 90, 23);
		contentPane.add(lblNewLabel);
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.CYAN);
		desktopPane.setBounds(134, 49, 550, 400);
		desktopPane.setSize(550, 400);
		contentPane.add(desktopPane);
		
		String path1="img/3.jpg";
		ImageIcon background1=new ImageIcon(path1);
		final JLabel lblNewLabel_4 = new JLabel(background1);
		lblNewLabel_4.setBounds(0, 0, 550, 400);
		desktopPane.add(lblNewLabel_4);
		

		JButton button = new JButton("\u663E\u793A\u5458\u5DE5\u4FE1\u606F");
		button.setForeground(Color.MAGENTA);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			/**
			 * ��ʾ����
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				ShowEmpsInfoFormUI showInfo=new ShowEmpsInfoFormUI();
				desktopPane.add(showInfo);
				showInfo.setVisible(true);
				lblNewLabel_4.setVisible(false);
			}
		});
		button.setBounds(10, 111, 114, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u67E5\u8BE2\u5458\u5DE5\u4FE1\u606F");
		button_1.setForeground(Color.MAGENTA);
		button_1.addMouseListener(new MouseAdapter() {
			/**
			 * ��ѯ����
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchEmpFormUI searchform=new SearchEmpFormUI();
				desktopPane.add(searchform);
				searchform.setVisible(true);
				lblNewLabel_4.setVisible(false);
			}
		});
		button_1.setBounds(10, 161, 114, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u65B0\u589E\u5458\u5DE5\u8BB0\u5F55");
		button_2.setForeground(Color.MAGENTA);
		button_2.addMouseListener(new MouseAdapter() {
			/**
			 * ��������
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (CommonUtils.getUser().getPriority()!=1) {//�ж��Ƿ��ǹ���Ա�û�
					JOptionPane.showMessageDialog(null, "�ǹ���Ա�޷����д˲�����", "�˰���Ѷ",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					//����Ա�û���ת��������
					AddEmpsFormUI addform = new AddEmpsFormUI();
					addform.setVisible(true);
					desktopPane.add(addform);
					lblNewLabel_4.setVisible(false);
				}
			}
		});
		button_2.setBounds(10, 211, 114, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u5220\u9664\u5458\u5DE5\u8BB0\u5F55");
		button_3.setForeground(Color.MAGENTA);
		button_3.addMouseListener(new MouseAdapter() {
			/**
			 * ɾ������
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (CommonUtils.getUser().getPriority()!=1) {//�ж��Ƿ��ǹ���Ա�û�
					JOptionPane.showMessageDialog(null, "�ǹ���Ա�޷����д˲�����", "�˰���Ѷ",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					//ɾ������Ҫ����Ա�û���ʾ����Ա����
					PasswordFormUI passform=new PasswordFormUI();//��ʾ���������
					passform.setVisible(true);
					
					//����Ա��ʾ������ȷ
					if (CommonUtils.delepass == 1) {
						passform.dispose();
						lblNewLabel_4.setVisible(false);
						DeleteFormUI delete = new DeleteFormUI();
						delete.setVisible(true);
						desktopPane.add(delete);
						CommonUtils.delepass = 0;
					}
				}
			}
		});
		button_3.setBounds(10, 260, 114, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\u4FEE\u6539\u5458\u5DE5\u4FE1\u606F");
		button_4.setForeground(Color.MAGENTA);
		button_4.addMouseListener(new MouseAdapter() {
			/**
			 * �޸Ľ���
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if (CommonUtils.getUser().getPriority()!=1) {//�ж��Ƿ��ǹ���Ա�û�
					JOptionPane.showMessageDialog(null, "�ǹ���Ա�޷����д˲�����", "�˰���Ѷ",
							JOptionPane.ERROR_MESSAGE);
				}
				else{
					//����Ա�û���ת�޸Ľ���
					UpdateEmpFormUI update = new UpdateEmpFormUI();
					update.setVisible(true);
					desktopPane.add(update);
					lblNewLabel_4.setVisible(false);
				}
			}
		});
		button_4.setBounds(10, 310, 114, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\u9000\u51FA\u7CFB\u7EDF");
		button_5.setForeground(Color.MAGENTA);
		button_5.addMouseListener(new MouseAdapter() {
			/**
			 * �˳�����
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int n = JOptionPane.showConfirmDialog(null, "�Ƿ��˳���ϵͳ?", "�˰���Ѷ",
						JOptionPane.YES_NO_OPTION);
				
				if (n == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "��лʹ�ñ�ϵͳ", "�˰���Ѷ",
							JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);//�˳�ϵͳ

				} else if (n == JOptionPane.NO_OPTION) {
					// �����κβ���
				}
			}
		});
		button_5.setBounds(10, 410, 114, 23);
		contentPane.add(button_5);
		
		JLabel lblNewLabel_1 = new JLabel("\u5174\u90A6\u8D44\u8BAF\u4EBA\u529B\u8D44\u6E90\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_1.setBounds(291, 10, 316, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton button_6 = new JButton("\u7528\u6237\u767B\u51FA");
		button_6.addMouseListener(new MouseAdapter() {
			/**
			 * �û��ǳ�����
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "�𾴵�"+CommonUtils.getUser().getUserName()+"�û�����ȷ���ǳ���", "�˰���Ѷ",
						JOptionPane.YES_NO_OPTION);
				
				if (n == JOptionPane.YES_OPTION) {
					//��ת��¼����
					LoaderFormUI loader=new LoaderFormUI();
					loader.setVisible(true);
					dispose();//�رյ�ǰҳ��

				} else if (n == JOptionPane.NO_OPTION) {
					// �����κβ���
				}
			}
		});
		button_6.setForeground(Color.MAGENTA);
		button_6.setBounds(10, 360, 114, 23);
		contentPane.add(button_6);
		
		
		

	}
}
