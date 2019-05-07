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
 * 登录界面
 * @author 梦
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
        System.out.println("\t恭喜！登录成功^-^");
		UIFactory.getInstance().getUI(UIFactory.MAIN_MENU_UI).run();//登录成功跳转到主菜单页面
	}

	/**
	 * 登录界面
	 */
	public void showLoadMenu(){
		System.out.println("\n兴邦资讯 - Employee Information - Load Menu");
		System.out.println("==========================================================\n");
	}
	
	
	
	/**
	 * users文件验证登录
	 * @return
	 */
	public boolean TxtLogin(){
		String logNo=null;
		String password=null;
		User user=new User();
		UserDao userDao=new UserDaoTxtImpl();
		
		System.out.print("\t用户账号：");
		logNo=CommonUtils.getEntry();
		System.out.print("\t用户密码：");
		password=CommonUtils.getEntry();
		
		try{
			user=userDao.getUserByNo(logNo);
			if(!CommonUtils.getMD5(password).toString().equals(user.getUserPwd()))
				throw new HRMISException("帐号密码有误!");	
		}catch(NullPointerException e){
			System.out .println("\t此帐号用户不存在，请检查!");
			return false;
		}
		catch(HRMISException e){
			System.out .println("\t"+e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * 数据库验证登录
	 * @return
	 */
	public boolean JDBCLogin(){
		String logNo=null;
		String password=null;
		User user=new User();
		UserDao userDao=new UserDaoJDBCImpl();
		
		System.out.print("\t用户账号：");
		logNo=CommonUtils.getEntry();
		System.out.print("\t用户密码：");
		password=CommonUtils.getEntry();
		
		try{
			user=userDao.getUserByNo(logNo);
			if(!CommonUtils.getMD5(password).toString().equals(user.getUserPwd()))
				throw new HRMISException("帐号密码有误!");	
		}catch(HRMISException e){
			System.out .println("\t"+e.getMessage());
			return false;
		}
		
		return true;
	}
	
}
