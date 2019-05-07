/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 欢迎界面
 * @author 梦
 *
 */
public class WelcomeUI implements BaseUI {
    
	/** 数据存储方式 */
	public static String DataStore=null;
	
	@Override
	public void run() {
		showMenu();
		do{
			selectMenu();
			DataStore = CommonUtils.getEntry();
			if (DataStore.equals("1"))
				UIFactory.getInstance().getUI(UIFactory.LOADER_UI).run();//跳转登录界面
			else if (DataStore.equals("2"))
				UIFactory.getInstance().getUI(UIFactory.LOADER_UI).run();//跳转登录界面
			else
				System.out.println("select error!Please select again\n");
		} while ((!DataStore.equals("1")) && (!DataStore.equals("2")));
		
	}

	/**
	 * 显示欢迎界面
	 */
	public void showMenu(){
		System.out.println("\n兴邦资讯 - Employee Information - Welcome");
		System.out.println("==========================================================");
		System.out.println("\tWelcome to use the XB-HRMIS V1.1 system！\n");
	}
	
	/**
	 * 数据存储选择菜单
	 */
	public void selectMenu(){
		System.out.println("\tPlease select the data storage method:");
		System.out.println("\t1.text file     \t2.database\n");
		System.out.print("Your Selection:");
	}
}
