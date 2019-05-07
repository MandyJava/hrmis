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
 * ϵͳͨ�ù���
 * @author ��
 *
 */
public class CommonUtils {
	
	/** ���ݴ洢��ʽ */
	public static int DataStore=0;
	/** ��ǰ�û��ʺ�*/
	public static String userNo=null;
	/**ɾ�������Ƿ�ͨ����֤*/
	public static int delepass=0;
	
	/**
	 * ��õ�ǰϵͳ�û�
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
	 * �ӿ���̨��ȡ����
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
			throw new HRMISException("�ӿ���̨��ȡ���������ʧ��!");
		}

		return entry;

	}

 
	/**
	 * ����Ϣ����ͣ
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
	 * ������Ϣ����ͣ
	 */
	public static void pause(){
		System.out .println();
		pause("Press Enter to continue...");
	}
	
	/**
	 * ����Ƿ�����س���
	 * 
	 * @param entry
	 */
	public static void checkBlankEntry(String entry) {
		if (entry == null || entry.length() == 0)
			throw new BlankEntryException();
	}
	
	/**
	 * ���Ա�������ļ��Ƿ����
	 */
	 public static void checkEmpsResource(){
			
		 File file = new File("f:/records.txt");
		 if(!file.exists()){
			 throw new HRMISException("Employee information datafile not found! Now exit!");
		 }
		 
	 }
	 
	/**
	 * ����û������ļ��Ƿ����
	 */
	public static void checkUserResource() {

		File file = new File("f:/users.txt");
		if (!file.exists()) {
			throw new HRMISException("User information datafile not found! Now exit!");
		}

	}
	 
    /**
     * ����Ա����¼�ؼ���
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
	 * ��ʽ���ַ������ַ������׸���ĸ��д��
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
	 * ���ڣ�dd-mm-yyyy����ʽת���ɣ�yyyy-mm-dd����ʽ
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
     * ����MD5�򵥷��� 
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
     * �����û�������־
     * @param record
     */
    public static void userLog(String record){
    	//BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new FileOutputStream("f:\\userlog.txt",true));
			writer.print("�û�:"+getUser().getUserName()+" ");
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
