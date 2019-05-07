/**
 * 
 */
package edu.fjnu.hrmis.ui.cui;

import edu.fjnu.hrmis.exception.HRMISException;

/**
 * 界面工厂
 * @author 梦
 *
 */
public class UIFactory {
	
    private static UIFactory FACTORY = new UIFactory();	
    
    public static String MAIN_MENU_UI="hrmis.MainMenu";
    public static String LOAD_ALL_EMPS_UI = "hrmis.showAllEmpsUI";
    public static String LOAD_EMPS_INFO_UI = "hrmis.ShowEmpInfoUI";
    public static String LOAD_ALL_EMPS_PHONE_UI = "hrmis.showAllEmpsPhoneUI";
    public static String LOAD_EMPS_PHONE_INFO_UI = "hrmis.ShowEmpPhoneInfoUI";
    public static String SEARCH_EMPS_INFO_UI="hrmis.SearchEmpInfoUI";
    public static String ADD_EMPS_INFO_UI="hrmis.AddEmpsInfoUI";
    public static String DELETE_EMPS_INFO_UI="hrmis.DeleteEmpsInfoUI";
    public static String UPDATE_EMPS_INFO_UI="hrmis.UpdateEmpsInfoUI";
    public static String LOADER_UI="hrmis.LoaderUI";
    public static String WELCOME_UI="hrmis.WelcomeUI";
    
    /**
     * 获得实例对象
     * @return
     */
    public static UIFactory getInstance(){
    	return FACTORY;
    }
	private UIFactory(){
		
	}

	/**
	 * 获得界面
	 * @param uiType
	 * @return
	 */
	public BaseUI getUI(String uiType){
		BaseUI ui=null;
		
		if(uiType.equals(LOAD_ALL_EMPS_UI)){
			ui = new ShowAllEmpsUI();
		}
		else if(uiType.equals(LOAD_EMPS_INFO_UI)){
			ui=new ShowEmpInfoUI();
		}
		else if(uiType.equals(LOAD_ALL_EMPS_PHONE_UI)){
			ui=new ShowAllEmpsPhoneUI();
		}
		else if(uiType.equals(LOAD_EMPS_PHONE_INFO_UI)){
			ui=new ShowEmpPhoneInfoUI();
		}
		else if(uiType.equals(SEARCH_EMPS_INFO_UI)){
			ui=new SearchEmpInfoUI();	
		}
		else if(uiType.equals(ADD_EMPS_INFO_UI)){
			ui=new AddEmpsInfoUI();
		}
		else if(uiType.equals(DELETE_EMPS_INFO_UI)){
			ui=new DeleteEmpsInfoUI();
		}
		else if(uiType.equals(UPDATE_EMPS_INFO_UI)){
			ui=new UpdateEmpsInfoUI();
		}
		else if(uiType.equals(LOADER_UI)){
			ui=new LoaderUI();
		}
		else if(uiType.equals(MAIN_MENU_UI)){
			ui=new MainMenu();
		}
		else if(uiType.equals(WELCOME_UI)){
			ui=new WelcomeUI();
		}
		if(ui==null)
			 throw new HRMISException("ui模块:"+uiType+",加载失败，请检查!");
		
		return ui;
	}
}
