package com.iedu.config;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.iedu.util.DbInfo;
import com.iedu.util.SeedUtil;
import com.iedu.util.ase256;

public class Globalconfig {
    
    private int    smtpport;
    private String smtphost;
    private String smtpuserid;
    private String smtppassword;
    
    private String  smtpemail;
    
    private String  smtpsender;
    
    private int    encryptedmethod; // 0 : 없음 1: tls 2: ssl
    
    private final  int MAX_DB_NUM = 9;  // max db 설정 
    
    
    
    private dbconfiginfo[] dbsaveinfoarray; // dbsetting array


	public dbconfiginfo[] getDbsaveinfoarray() {
		return dbsaveinfoarray;
	}

	public void setDbsaveinfoarray(dbconfiginfo[] dbsaveinfoarray) {
		this.dbsaveinfoarray = dbsaveinfoarray;
		
		for(int i=0;i<dbsaveinfoarray.length;i++) {
		
			handler.setValue("dbport"+i,dbsaveinfoarray[i].getDbport());
			handler.setValue("dbname"+i,dbsaveinfoarray[i].getDbname());
			handler.setValue("dbuserid"+i,dbsaveinfoarray[i].getDbuserid());
			handler.setValue("dbpassword"+i,
					SeedUtil.encrypt(dbsaveinfoarray[i].getDbpassword()));
					
			handler.setValue("dbtype"+i,dbsaveinfoarray[i].getDbtype());
			
			handler.setValue("dbhost"+i,dbsaveinfoarray[i].getDbhost());
			handler.setValue("dbdesc"+i,dbsaveinfoarray[i].getDbdesc());
			
		}				
	}
	
	public void setDbsaveinfoarray(ArrayList<dbconfiginfo>  list) {
		dbconfiginfo[]  dbsaveinfoarray ;
		dbsaveinfoarray  = new dbconfiginfo[list.size()];
		for(int i=0;i<list.size();i++) {			
			dbsaveinfoarray[i] = list.get(i);			
		}
		
		
		setDbsaveinfoarray(dbsaveinfoarray);
	}

	public String getSmtpemail() {
		return smtpemail;
	}

	public void setSmtpemail(String smtpemail) {
		this.smtpemail = smtpemail;
		handler.setValue("smtpemail",smtpemail );
		
	}

	public String getSmtpsender() {
		return smtpsender;
	}

	public void setSmtpsender(String smtpsender) {
		this.smtpsender = smtpsender;
		handler.setValue("smtpsender",smtpsender );
	}

	
    
    private String savedir;
    
    /** 
	 * @return the savedir
	 */
	public String getSavedir() {
		return savedir;
	}

	/**
	 * @param savedir the savedir to set
	 */
	public void setSavedir(String savedir) {
		this.savedir = savedir;
		
        File dir = new File(savedir);
        if (dir.exists() == false) 
		  dir.mkdirs();
		
		
		handler.setValue("savedir",savedir );
	}

	ConfigFileHandler handler;



	public int getSmtpport() {
		return smtpport;
	}

	public void setSmtpport(int smtpport) {
		this.smtpport = smtpport;
		handler.setValue("smtpport",smtpport );
	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
		handler.setValue("smtphost",smtphost );
		}

	public String getSmtpuserid() {
		return smtpuserid;
	}

	public void setSmtpuserid(String smtpuserid) {
		this.smtpuserid = smtpuserid;
		handler.setValue("smtpuserid",smtpuserid );
	}

	public String getSmtppassword() {
		return smtppassword;
	}

	public void setSmtppassword(String smtppassword) {
		this.smtppassword = smtppassword;
		handler.setValue("smtppassword",smtppassword );
	}

	public int getEncryptedmethod() {
		return encryptedmethod;
	}

	public void setEncryptedmethod(int encryptedmethod) {
		this.encryptedmethod = encryptedmethod;
		handler.setValue("encryptedmethod",encryptedmethod );
	}

	public ConfigFileHandler getHandler() {
		return handler;
	}

	public void setHandler(ConfigFileHandler handler) {
		this.handler = handler;
	}

	public void Save()
	{
		handler.Save();
		
	}
	
	public dbconfiginfo getDbconfig(int index) 
	{		
		if (index<0 || index >= MAX_DB_NUM)
			return null;		
		return  dbsaveinfoarray[index];
	}
	
	public Globalconfig(String filename)
	{
		
		dbsaveinfoarray = new dbconfiginfo[MAX_DB_NUM];
		
	    handler = ConfigFileHandler.getConfigFileHandler(filename);
	    
	    
	    
	    
	    for(int i=0;i<MAX_DB_NUM;i++) {
	    	
	    	dbconfiginfo info = new dbconfiginfo();
    	   String host = handler.getValue("dbhost"+i);
    	   
    	   if (host == null)
    		   host="";
    	   
    	   System.out.println(host);
    	   info.setDbhost(host);
    	   
    	   
		   int dbport = 0;
		    
		   
		   try {
		     dbport = 	Integer.parseInt( handler.getValue("dbport"+i));

		   } catch (Exception ex ) {}
		   
		   
		    info.setDbport(dbport);
		      
		    
		    String dbname =  handler.getValue("dbname"+i);
		    String  dbuserid =  handler.getValue("dbuserid"+i);
				
		    
		   if (dbuserid == null)
			  dbuserid = "";
		

		   String dbdesc = handler.getValue("dbdesc"+i);
		   
		   
		   info.setDbdesc(dbdesc);
		   info.setDbuserid(dbuserid);
		   info.setDbname(dbname);
		   
		   String  password;
		    try {
			// password =   ase256.AES_Decode( handler.getValue("password"));
			 password =   SeedUtil.decrypt(handler.getValue("dbpassword"+i));			 
			
		    	} catch (Exception	ex) {
		    		ex.printStackTrace();
		    		password = handler.getValue("dbpassword"+1);
		    	}
		
		    info.setDbpassword(password);
		    
		 String dbtype;
		 
		 dbtype = handler.getValue("dbtype"+i);
		 
		 info.setDbtype(dbtype);
		 dbsaveinfoarray[i] = info;
		 
	    }

		 try {
			   this.smtpport = Integer.parseInt( handler.getValue("smtpport"));		
			 } catch (Exception ex) {
				 smtpport =0;
		 }
			 
		
 
 	    this.smtphost = handler.getValue("smtphost");		
       	
 	    this.smtpuserid = handler.getValue("smtpuserid");
 	   
 	    this.smtppassword = handler.getValue("smtppassword");

 	   
 	    this.smtpemail = handler.getValue("smtpemail");
 	  
 	    this.smtpsender= handler.getValue("smtpsender");
 	   
		 
 		 try {
			   this.encryptedmethod = Integer.parseInt( handler.getValue("encryptedmethod"));		
			 } catch (Exception ex) {
				 encryptedmethod =0;
		 }
 		 
		 savedir = handler.getValue("savedir");
		 if (savedir == null)
			 savedir = "";
		 
	}
	
    public Globalconfig()
    {
      this("iedu.conf");  	
    }
    
}
