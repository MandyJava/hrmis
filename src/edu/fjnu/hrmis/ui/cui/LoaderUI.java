/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.dao.UserDao;
import edu.fjnu.hrmis.dao.UserDaoJDBCImpl;
import edu.fjnu.hrmis.dao.UserDaoTxtImpl;
import edu.fjnu.hrmis.domain.User;
import edu.fjnu.hrmis.exception.HRMISException;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * ��¼����
 * @author ��
 *
 */
public class LoaderUI implements BaseUI {

	@Override
	public void run() {
		
		if (("1").equals(WelcomeUI.DataStore)) {

			do {
				showLoadMenu();

			} while (!TxtLogin());
		} else {
			do {
				showLoadMenu();

			} while (!JDBCLogin());
		}
        System.out.println("\t��ϲ����¼�ɹ�^-^");
		UIFactory.getInstance().getUI(UIFactory.MAIN_MENU_UI).run();//��¼�ɹ���ת�����˵�ҳ��
	}

	/**
	 * ��¼����
	 */
	public void showLoadMenu(){
		System.out.println("\n�˰���Ѷ - Employee Information - Load Menu");
		System.out.println("==========================================================\n");
	}
	
	
	
	/**
	 * users�ļ���֤��¼
	 * @return
	 */
	public boolean TxtLogin(){
		String logNo=null;
		String password=null;
		User user=new User();
		UserDao userDao=new UserDaoTxtImpl();
		
		System.out.print("\t�û��˺ţ�");
		logNo=CommonUtils.getEntry();
		System.out.print("\t�û����룺");
		password=CommonUtils.getEntry();
		
		try{
			user=userDao.getUserByNo(logNo);
			if(!CommonUtils.getMD5(password).toString().equals(user.getUserPwd()))
				throw new HRMISException("�ʺ���������!");	
		}catch(NullPointerException e){
			System.out .println("\t���ʺ��û������ڣ�����!");
			return false;
		}
		catch(HRMISException e){
			System.out .println("\t"+e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * ���ݿ���֤��¼
	 * @return
	 */
	public boolean JDBCLogin(){
		String logNo=null;
		String password=null;
		User user=new User();
		UserDao userDao=new UserDaoJDBCImpl();
		
		System.out.print("\t�û��˺ţ�");
		logNo=CommonUtils.getEntry();
		System.out.print("\t�û����룺");
		password=CommonUtils.getEntry();
		
		try{
			user=userDao.getUserByNo(logNo);
			if(!CommonUtils.getMD5(password).toString().equals(user.getUserPwd()))
				throw new HRMISException("�ʺ���������!");	
		}catch(HRMISException e){
			System.out .println("\t"+e.getMessage());
			return false;
		}
		
		return true;
	}
	
}
