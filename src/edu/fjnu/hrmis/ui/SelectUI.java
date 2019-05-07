package edu.fjnu.hrmis.ui;

import edu.fjnu.hrmis.ui.cui.UIFactory;
import edu.fjnu.hrmis.ui.gui.WelcomeFormUI;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 用户界面选择
 * @author 梦
 *
 */
public class SelectUI {
	
	public String uiselect;

	/**
	 * 显示欢迎界面
	 */
	public void showMenu(){
		System.out.println("\n兴邦资讯 - Employee Information - Welcome");
		System.out.println("==========================================================");
		System.out.println("\tWelcome to use the XB-HRMIS V1.1 system！\n");
	}
	
	/**
	 * ui界面选择菜单
	 */
	public void selectMenu(){
		System.out.println("\tPlease select the UI view:");
		System.out.println("\t1.CUI     \t2.GUI\n");
		System.out.print("Your Selection:");
	}
	
	/**
	 * 用户可以进行CUI界面与GUI界面的选择
	 */
	public void runUI(){
		showMenu();
		do{
			selectMenu();
			uiselect = CommonUtils.getEntry();
			if (("1").equals(uiselect)){
				UIFactory.getInstance().getUI(UIFactory.WELCOME_UI).run();
			}else if (("2").equals(uiselect)){
				WelcomeFormUI welcome=new WelcomeFormUI();
			    welcome.setVisible(true);
			}else{
				System.out.println("select error!Please select again\n");
			}
		} while ((!("1").equals(uiselect)) && (!("2").equals(uiselect)));
	}
}
