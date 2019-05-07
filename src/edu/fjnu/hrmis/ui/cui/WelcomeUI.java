/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * ��ӭ����
 * @author ��
 *
 */
public class WelcomeUI implements BaseUI {
    
	/** ���ݴ洢��ʽ */
	public static String DataStore=null;
	
	@Override
	public void run() {
		showMenu();
		do{
			selectMenu();
			DataStore = CommonUtils.getEntry();
			if (DataStore.equals("1"))
				UIFactory.getInstance().getUI(UIFactory.LOADER_UI).run();//��ת��¼����
			else if (DataStore.equals("2"))
				UIFactory.getInstance().getUI(UIFactory.LOADER_UI).run();//��ת��¼����
			else
				System.out.println("select error!Please select again\n");
		} while ((!DataStore.equals("1")) && (!DataStore.equals("2")));
		
	}

	/**
	 * ��ʾ��ӭ����
	 */
	public void showMenu(){
		System.out.println("\n�˰���Ѷ - Employee Information - Welcome");
		System.out.println("==========================================================");
		System.out.println("\tWelcome to use the XB-HRMIS V1.1 system��\n");
	}
	
	/**
	 * ���ݴ洢ѡ��˵�
	 */
	public void selectMenu(){
		System.out.println("\tPlease select the data storage method:");
		System.out.println("\t1.text file     \t2.database\n");
		System.out.print("Your Selection:");
	}
}
