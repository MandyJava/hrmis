/**
 * 
 */
package edu.fjnu.hrmis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.fjnu.hrmis.dao.UserDao;
import edu.fjnu.hrmis.dao.UserDaoJDBCImpl;
import edu.fjnu.hrmis.dao.UserDaoTxtImpl;
import edu.fjnu.hrmis.domain.User;
import edu.fjnu.hrmis.exception.BlankEntryException;
import edu.fjnu.hrmis.exception.HRMISException;

/**
 * 
 * 系统通用工具
 * @author 梦
 *
 */
public class CommonUtils {
	
	/** 数据存储方式 */
	public static int DataStore=0;
	/** 当前用户帐号*/
	public static String userNo=null;
	/**删除操作是否通过验证*/
	public static int delepass=0;
	
	/**
	 * 获得当前系统用户
	 * @return
	 */
	public static User getUser(){
		UserDao userDao=null;
		if(DataStore==0){
			userDao=new UserDaoTxtImpl();
		}
		else{
			userDao=new UserDaoJDBCImpl();
		}
		
		return userDao.getUserByNo(userNo);
	}
	
	/**
	 * 从控制台读取数据
	 * 
	 * @return
	 */
	public static String getEntry() {

		String entry = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			entry = reader.readLine();

		} catch (IOException e) {
			e.printStackTrace();
			throw new HRMISException("从控制台读取输入的数据失败!");
		}

		return entry;

	}

 
	/**
	 * 带消息的暂停
	 * 
	 * @param prompt
	 */
	public static void pause(String prompt) {
		if (prompt != null)
			System.out.print(prompt);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 不带消息的暂停
	 */
	public static void pause(){
		System.out .println();
		pause("Press Enter to continue...");
	}
	
	/**
	 * 检查是否输入回车键
	 * 
	 * @param entry
	 */
	public static void checkBlankEntry(String entry) {
		if (entry == null || entry.length() == 0)
			throw new BlankEntryException();
	}
	
	/**
	 * 检查员工数据文件是否存在
	 */
	 public static void checkEmpsResource(){
			
		 File file = new File("f:/records.txt");
		 if(!file.exists()){
			 throw new HRMISException("Employee information datafile not found! Now exit!");
		 }
		 
	 }
	 
	/**
	 * 检查用户数据文件是否存在
	 */
	public static void checkUserResource() {

		File file = new File("f:/users.txt");
		if (!file.exists()) {
			throw new HRMISException("User information datafile not found! Now exit!");
		}

	}
	 
    /**
     * 搜索员工记录关键字
     * @param strLine
     * @param keyWord
     * @return
     */
	public static boolean isFound(String strLine,String keyWord){
		if(strLine.toLowerCase().contains(keyWord.toLowerCase()))
			return true;
		return false;
	}

	/**
	 * 格式化字符串（字符串中首个字母大写）
	 * @param entry
	 * @return
	 */
	public static String fomatString(String entry) {

		String[] section = entry.trim().split("\\ ");
		String fomatEntry = "";

		for (int i = 0; i < section.length; i++) {
			section[i] = section[i].trim();
			section[i] = section[i].replaceFirst(section[i].substring(0, 1),
					section[i].substring(0, 1).toUpperCase());
			fomatEntry += section[i] + " ";
		}
		fomatEntry = fomatEntry.substring(0, fomatEntry.length() - 1);
		return fomatEntry;
	}
	
	/**
	 * 日期（dd-mm-yyyy）格式转化成（yyyy-mm-dd）格式
	 * @param entry
	 * @return
	 */
	public static String DateFormatChange(String entry){
		
		Pattern p = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4}");
		Matcher m = p.matcher(entry);
		if(!m.matches())
			throw new HRMISException();
		
		String[] section = entry.split("\\-");
		return section[2]+"-"+section[1]+"-"+section[0];
	}
	
	/** 
     * 生成MD5简单方法 
     * @param str 
     * @return 
     */  
    public static StringBuilder getMD5(String str){  
        byte [] buf=str.getBytes();  
        MessageDigest md5;  
        StringBuilder sb=new StringBuilder();  
        try {  
            md5 = MessageDigest.getInstance("MD5");  
            md5.update(buf);  
            byte [] tmp=md5.digest();  
            for (byte b:tmp) {  
                sb.append(Integer.toHexString(b&0xff));  
            }  
        } catch (NoSuchAlgorithmException e) {   
            e.printStackTrace();  
        }  
        return sb;  
    }
    
    /**
     * 生成用户操作日志
     * @param record
     */
    public static void userLog(String record){
    	//BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream("f:\\userlog.txt",true));
			writer.print("用户:"+getUser().getUserName()+" ");
			writer.print(record);
			writer.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			writer.flush();
			writer.close();
		}
    }
}
