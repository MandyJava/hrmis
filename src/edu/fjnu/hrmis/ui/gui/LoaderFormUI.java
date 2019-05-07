package edu.fjnu.hrmis.ui.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import edu.fjnu.hrmis.dao.UserDao;
import edu.fjnu.hrmis.dao.UserDaoJDBCImpl;
import edu.fjnu.hrmis.dao.UserDaoTxtImpl;
import edu.fjnu.hrmis.domain.User;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CommonUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoaderFormUI extends JFrame {

	private JPanel contentPane;
	private JTextField loginNO;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
									
					LoaderFormUI frame = new LoaderFormUI();
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
	public LoaderFormUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		JLabel label = new JLabel("\u6B22\u8FCE\u767B\u5F55");
		label.setForeground(Color.CYAN);
		label.setFont(new Font("宋体", Font.BOLD, 20));
		label.setBounds(342, 10, 102, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u5E10\u53F7\uFF1A");
		label_1.setForeground(Color.CYAN);
		label_1.setBounds(313, 47, 65, 15);
		contentPane.add(label_1);
		
		loginNO = new JTextField();
		loginNO.setBounds(378, 44, 81, 21);
		contentPane.add(loginNO);
		loginNO.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		label_2.setForeground(Color.CYAN);
		label_2.setBounds(313, 75, 65, 15);
		contentPane.add(label_2);
		
		final JLabel notice = new JLabel("");
		notice.setForeground(Color.CYAN);
		notice.setBounds(313, 199, 164, 15);
		contentPane.add(notice);
		
		password = new JPasswordField();
		password.setBounds(378, 75, 81, 21);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				User user=new User();
				UserDao userDao=null;
				if (CommonUtils.DataStore==1)
				    userDao=new UserDaoTxtImpl();
				else
					userDao=new UserDaoJDBCImpl();
				
				try{
					user=userDao.getUserByNo(loginNO.getText().toString());
					if(!CommonUtils.getMD5(password.getText().toString()).toString().equals(user.getUserPwd()))
						throw new HRMISException("    帐号密码有误!");	
					
					//获得系统当前用户
                    CommonUtils.userNo=loginNO.getText().toString();
                    //登录成功跳转主菜单界面
				    MainFormUI main=new MainFormUI();
				    main.setVisible(true);
                    dispose();
				}catch(NullPointerException e1){
				    notice.setText("此帐号用户不存在，请检查!");		
				}
				catch(HRMISException e1){
					notice.setText(e1.getMessage());
				}
			}
		});
		btnNewButton.setBounds(352, 239, 93, 23);
		contentPane.add(btnNewButton);

	}
}
