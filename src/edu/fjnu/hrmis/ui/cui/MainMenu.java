/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.utils.CommonUtils;

/**
 * 主菜单界面
 * @author 梦
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

				//菜单操作选择
				if (entry.length() == 1) {
					switch (entry.toUpperCase().charAt(0)) {
					case '1': {//显示所有员工
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_ALL_EMPS_UI).run();
						CommonUtils.pause();
						break;
					}
					case '2': {//格式显示所有员工
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_EMPS_INFO_UI).run();
						CommonUtils.pause();
						break;
					}
					case '3': {//显示部分员工
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_ALL_EMPS_PHONE_UI).run();
						CommonUtils.pause();
						break;
					}
					case '4': {//格式显示部分员工
						UIFactory.getInstance()
								.getUI(UIFactory.LOAD_EMPS_PHONE_INFO_UI).run();
						CommonUtils.pause();
						break;
					}
					case '5': {//查询员工信息
						UIFactory.getInstance()
								.getUI(UIFactory.SEARCH_EMPS_INFO_UI).run();
						CommonUtils.pause();
						break;
					}
					case '6': {//新增员工记录
						UIFactory.getInstance()
								.getUI(UIFactory.ADD_EMPS_INFO_UI).run();
						break;
					}
					case '7': {//删除员工记录
						UIFactory.getInstance()
								.getUI(UIFactory.DELETE_EMPS_INFO_UI).run();
						break;
					}
					case '8': {//修改员工信息
						UIFactory.getInstance()
								.getUI(UIFactory.UPDATE_EMPS_INFO_UI).run();
						break;
					}
					case 'Q': {//退出
						isContinued = false;
						break;
					}
					default:
						CommonUtils
								.pause("Invalid code!Press Enter to continue…");
					}
				} else
					CommonUtils.pause("Invalid code!Press Enter to continue…");

			} catch (BlankEntryException e) {
				CommonUtils
						.pause("No selection entered.Press Enter to continue…");
			}

		}

		System.out.println("\n\nThank you for using XB-HRMIS V1.1, GoodBye!");

	}

	/**
	 * 显示系统菜单
	 */
	private void showMenu() {

		System.out.println("\n兴邦资讯 - Employee Information - Main Menu");
		System.out
				.println("==========================================================\n");
		System.out.println("1 - Print All Current Records");
		System.out.println("2 – Print All Current Records (formatted)");
		System.out.println("3 – Print Names and Phone Numbers");
		System.out.println("4 – Print Names and Phone Numbers (formatted)");
		System.out.println("5 - Search for specific Record(s)");
		System.out.println("6 - Add New Records");
		System.out.println("7 – Delete Records");
		System.out.println("8 – Update Records\n");
		System.out.println("Q - Quit\n");
		System.out.print("Your Selection:");
	}

}
