package edu.fjnu.hrmis.ui;

import edu.fjnu.hrmis.ui.cui.UIFactory;
import edu.fjnu.hrmis.ui.gui.WelcomeFormUI;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * �û�����ѡ��
 * @author ��
 *
 */
public class SelectUI {
	
	public String uiselect;

	/**
	 * ��ʾ��ӭ����
	 */
	public void showMenu(){
		System.out.println("\n�˰���Ѷ - Employee Information - Welcome");
		System.out.println("==========================================================");
		System.out.println("\tWelcome to use the XB-HRMIS V1.1 system��\n");
	}
	
	/**
	 * ui����ѡ��˵�
	 */
	public void selectMenu(){
		System.out.println("\tPlease select the UI view:");
		System.out.println("\t1.CUI     \t2.GUI\n");
		System.out.print("Your Selection:");
	}
	
	/**
	 * �û����Խ���CUI������GUI�����ѡ��
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
