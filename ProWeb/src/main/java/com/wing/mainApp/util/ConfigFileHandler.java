package com.wing.mainApp.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;

/** 
 * ----------------------------------------------------------------------------
 *  1. Module Name	   : 
 *  2. Sub-Module Name : 
 *  3. Program ID      : ConfigExtFileHandler
 *  4. Program Desc    : �������α׷��� �������� ȯ�溯������ ������ iedu.conf �� �ٷ��.
 *  5. Table (S)       : 
 *     Table (C,I,U,D) : 
 *  6. Usage           : 	����1
 *						 ConfigFileHandler oConf = new ConfigFileHandler();
 *						 String stOne = oConf.getValue("homepage.root.path");
 *						 	����2 : conf ������ ������ conf����
 *						 String sPath = "xast"+File.separator+"iedu+File.separator+"iedu.conf";
 *						 ConfigFileHandler oConf = new ConfigFileHandler(sPath);
 *						 System.out.println(oConf.getValue("server.port"));
 *  7. Comment         : ������1: ConfigFileHandler()
 *  					  ������1: ConfigFileHandler(String)
 */
public class ConfigFileHandler {
	
	private Properties m_oProps = new Properties();
	private FileLoadingInfo fileLoadingInfo;
	

	private  static Hashtable fileMap = new Hashtable();

	private static  FileLoadingInfo InitialFileInfo = null;
	
	private static Properties m_defaultprops = new Properties();
	/**
	 * Singlenton pattern
	 * 
	 * @return
	 */
	public static ConfigFileHandler getConfigFileHandler() {
		return getConfigFileHandler("iedu.conf");
	}

	/**
	 * Singlenton pattern
	 * 
	 * @return
	 */
	public static synchronized ConfigFileHandler getConfigFileHandler(
			String filename) {
		Object o = fileMap.get(filename);
		if (o != null) {
            
			ConfigFileHandler handler = (ConfigFileHandler) o;
			FileLoadingInfo loadingFinfo = handler.fileLoadingInfo;
			if (loadingFinfo.isUpdated()) {
				handler.loadFile();
			}
			return handler;
		} else {

			
			ConfigFileHandler handler = new ConfigFileHandler(filename);
			fileMap.put(filename, handler);
			return handler;
		}
	}

	/**
	 * ezaid.conf <br>
	 * 
	 * @param
	 * @return
	 * @deprecated
	 */
	private  ConfigFileHandler() {
		init("iedu.conf");
	}

	
	public void Save()
	{		
		 		  File file = this.fileLoadingInfo.file;
		 		 
		 			try(FileOutputStream fio=   new FileOutputStream(file)) {		 				
		 				m_oProps.store(fio, null);
		 				fileLoadingInfo.setLastLoaded(System.currentTimeMillis());
		 			} catch(IOException e) {
		 				e.printStackTrace();
		 			}		  		 		  
	}
	
	/**
	 * ezaid.conf�̿��� conf���丮 �ؿ� conf����<br>
	 * 
	 * @param
	 * @return
	 * @deprecated
	 */
	private ConfigFileHandler(String sFilePath) {
		init(sFilePath);
	}

	/**
	 * �ش� Key�� ���� Value�� ���Ѵ�.<br>
	 * 
	 * @param p_sKey
	 *            conf file key
	 * @return String �ش� key������ value�� �����Ѵ�.
	 */
	public String getValue(String p_sKey) {
		String ret =  m_oProps.getProperty(p_sKey);
		
		if (ret == null)
			ret = "";		
		return ret;

	}

	
	
	public void setValue(String p_sKey,String value)
	{
		 m_oProps.setProperty(p_sKey, value);	 
	}
	 
	public void setValue(String p_sKey,int value)
	{
		 m_oProps.setProperty(p_sKey, Integer.toString(value));	 
	}
	 
	
	private static void LoadInitailDir()
	{

		if (InitialFileInfo == null || !InitialFileInfo.getFile().isFile()) {
			// ������ ����� �ε����� �ʾҴ�.
			
			System.out.println(ConfigFileHandler.class.getName()
					+ ".loadFile() : cannot open config file==>"+InitialFileInfo.getFile().getName());
			return ;
		}

		
		File configFile = InitialFileInfo.getFile();

	
		
		FileInputStream oFileInput = null;
		try {
			oFileInput = new FileInputStream(configFile);
			
			m_defaultprops.load(oFileInput);
			
			return ;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("Don't exists Property files");
			return ;
		} catch (IOException ie) {
			ie.printStackTrace();
			return ;
		} finally {
			if (oFileInput != null) try {oFileInput.close();} catch (Exception ignore) {}
			
			InitialFileInfo.setLastLoaded(System.currentTimeMillis());
			
		}

		
	}
	
	public static synchronized String getInitDirectory() 
	{

		String sPath="";

		sPath = ConfigFileHandler.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		// WEB-INF ��ġ

		int nFindInx = sPath.indexOf("WEB-INF");

		if (nFindInx > 0)
			sPath = sPath.substring(0, nFindInx + 7);

  
		String sConfPath="";
		sConfPath = sPath + File.separator + "conf";		
		
		File file = new File(sConfPath);
		if (file.exists() == false ) {
			
			file.mkdir();
		}
		
		return sConfPath;
	
		
	/*
		
		String sConfPath="";
		sConfPath = sPath + File.separator + "conf" + File.separator+"init.property";
		
		
		System.out.println("confpath="+sConfPath);
		if (InitialFileInfo ==null ) {
			
			
			
    	   InitialFileInfo =  new  FileLoadingInfo(new File(sConfPath));
    	 
    	   LoadInitailDir();
		} else {
			
			if (InitialFileInfo.isUpdated()) {
				LoadInitailDir();
			}			
		}
		
		return m_defaultprops.getProperty("DIR");
		*/
	}
	
	public  static  synchronized boolean SaveInitDirectory(String Dir)
	{
		String sPath="";

		sPath = ConfigFileHandler.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		// WEB-INF ��ġ

		int nFindInx = sPath.indexOf("WEB-INF");

		if (nFindInx > 0)
			sPath = sPath.substring(0, nFindInx + 7);

		String sConfPath="";
		sConfPath = sPath + File.separator + "conf" + File.separator+"init.property";
		
		m_defaultprops.setProperty("DIR", Dir);	 
		
		 try {
			  File file = new File(Dir);
			  boolean ret =true;
			  if (!file.exists())
			    ret =  file.mkdir();
			  if (ret == false)
			  {
				  return ret;
				  
			  }
			 
		 } catch(Exception ex) {
			 
			 return false;
		 }
		
		 
			try(FileOutputStream fio=   new FileOutputStream(sConfPath)) {		 				
				m_defaultprops.store(fio, null);
				InitialFileInfo.setLastLoaded(System.currentTimeMillis());

 			} catch(IOException e) {
 				e.printStackTrace();
 			}		
	
			
			fileMap.clear();
			
			return true;
	}
	
	
	
	
	/**
	 * iedu.conf �� �޸𸮿� �ε��Ѵ�.<br>
	 * 
	 * @param
	 * @return
	 */
	private void init(String sFilePath) {
		String sConfPath = null;
		try {
			// TODO : jar ������ ���� : ������ �����ؾ� �Ѵ�.
			// System.getProperty("user.dir") ����Ǵ� ���丮

			/*
			 * sPath =
			 * ConfigFileHandler.class.getProtectionDomain().getCodeSource()
			 * .getLocation().getPath(); WEB-INF�ؿ� Ŭ������ �ִ� ���
			 * Ŭ�������/kr/co/ezaid/conf classpath�� ������ ��� => Ŭ��������� �����´�.
			 */

			String sPath = null;
			
			/*
			sPath = ConfigFileHandler.class.getProtectionDomain()
					.getCodeSource().getLocation().getPath();
			// WEB-INF ��ġ
			int nFindInx = sPath.indexOf("WEB-INF");
			if (nFindInx > 0)
				sPath = sPath.substring(0, nFindInx + 7);
           */
			
			sPath = getInitDirectory();
			

	//		System.out.println("file ����==>"+sPath);
			
			sConfPath = sPath +  File.separator
					+ sFilePath;
			 							

			fileLoadingInfo = new FileLoadingInfo(new File(sConfPath));

			loadFile();

		} catch (Exception e) {
			System.out.println("Exception occured(initSql):" + e);
		}
	}

	/**
	 * 
	 * @return if success, return true. else return false
	 */
	// written by YuKwangmin
	protected synchronized boolean loadFile() {
		
		if (fileLoadingInfo == null || !fileLoadingInfo.getFile().isFile()) {
			// ������ ����� �ε����� �ʾҴ�.
			
			System.out.println(this.getClass().getName()
					+ ".loadFile() : cannot open config file==>"+fileLoadingInfo.getFile().getPath());
			return false;
		}

		
		m_oProps.clear();
		
		File configFile = fileLoadingInfo.getFile();
		System.out.println(this.getClass().getName() + ".loadFile() : "
				+ configFile.getName() + " loaded.");

		FileInputStream oFileInput = null;
		try {
			oFileInput = new FileInputStream(configFile);

			m_oProps.load(oFileInput);
			return true;
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
			System.out.println("Don't exists Property files");
			return false;
		} catch (IOException ie) {
			ie.printStackTrace();
			return false;
		} finally {
			if (oFileInput != null) try {oFileInput.close();} catch (Exception ignore) {}
			fileLoadingInfo.setLastLoaded(System.currentTimeMillis());
		}
	}

	/**
	 * @author YuKwangmin
	 * 
	 * �������Ͽ� ����� Property���� �̸��� ��ȯ�Ѵ�.
	 * 
	 * @return Property names
	 * @see getValue()
	 */
	public Enumeration propertyNames() {
		if (m_oProps == null)
			return null;
		return m_oProps.propertyNames();
	}

	/*
	 * // for test public static void main(String[] args) { ConfigFileHandler
	 * oConf = new ConfigFileHandler();
	 * System.out.println(oConf.getValue("homepage.root.path"));
	 * 
	 * String sPath = "xast"+File.separator+"alarm"+File.separator+"alarm.conf";
	 * ConfigFileHandler oConf = new ConfigFileHandler(sPath);
	 * System.out.println(oConf.getValue("server.port"));
	 * System.out.println(oConf.getValue("server.maxhandlers")); }
	 */

	private static class FileLoadingInfo {

		private long lastLoaded;

		private File file;

		public FileLoadingInfo(File file) {
			this.file = file;
		}

		public File getFile() {
			return file;
		}

		public long getLastModified() {
			return file.lastModified();
		}

		public long getLastLoaded() {
			return lastLoaded;
		}

		public void setLastLoaded(long time) {
			lastLoaded = time;
		}

		public boolean isUpdated() {
			return (getLastModified() > lastLoaded) ? true : false;
		}
	}
}