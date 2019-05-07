/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * ���˵�����
 * @author ��
 * 
 */
public class MainMenu implements BaseUI {

	@Override
	public void run() {

		String entry = null;
		boolean isContinued = true;

		while (isContinued) {

			showMenu();

			try {
				entry = CommonUtils.getEntry().trim();
				CommonUtils.checkBlankEntry(entry);

				//�˵�����ѡ��
				if (entry.length() == 1) {
					switch (entry.toUpperCase().charAt(0)) {
					case '1': {//��ʾ����Ա��
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_ALL_EMPS_UI).run();
						CommonUtils.pause();
						break;
					}
					case '2': {//��ʽ��ʾ����Ա��
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_EMPS_INFO_UI).run();
						CommonUtils.pause();
						break;
					}
					case '3': {//��ʾ����Ա��
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_ALL_EMPS_PHONE_UI).run();
						CommonUtils.pause();
						break;
					}
					case '4': {//��ʽ��ʾ����Ա��
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_EMPS_PHONE_INFO_UI).run();
						CommonUtils.pause();
						break;
					}
					case '5': {//��ѯԱ����Ϣ
						UIFactory.getInstance()
								.getUI(UIFactory.SEARCH_EMPS_INFO_UI).run();
						CommonUtils.pause();
						break;
					}
					case '6': {//����Ա����¼
						UIFactory.getInstance()
								.getUI(UIFactory.ADD_EMPS_INFO_UI).run();
						break;
					}
					case '7': {//ɾ��Ա����¼
						UIFactory.getInstance()
								.getUI(UIFactory.DELETE_EMPS_INFO_UI).run();
						break;
					}
					case '8': {//�޸�Ա����Ϣ
						UIFactory.getInstance()
								.getUI(UIFactory.UPDATE_EMPS_INFO_UI).run();
						break;
					}
					case 'Q': {//�˳�
						isContinued = false;
						break;
					}
					default:
						CommonUtils
								.pause("Invalid code!Press Enter to continue��");
					}
				} else
					CommonUtils.pause("Invalid code!Press Enter to continue��");

			} catch (BlankEntryException e) {
				CommonUtils
						.pause("No selection entered.Press Enter to continue��");
			}

		}

		System.out.println("\n\nThank you for using XB-HRMIS V1.1, GoodBye!");

	}

	/**
	 * ��ʾϵͳ�˵�
	 */
	private void showMenu() {

		System.out.println("\n�˰���Ѷ - Employee Information - Main Menu");
		System.out
				.println("==========================================================\n");
		System.out.println("1 - Print All Current Records");
		System.out.println("2 �C Print All Current Records (formatted)");
		System.out.println("3 �C Print Names and Phone Numbers");
		System.out.println("4 �C Print Names and Phone Numbers (formatted)");
		System.out.println("5 - Search for specific Record(s)");
		System.out.println("6 - Add New Records");
		System.out.println("7 �C Delete Records");
		System.out.println("8 �C Update Records\n");
		System.out.println("Q - Quit\n");
		System.out.print("Your Selection:");
	}

}
