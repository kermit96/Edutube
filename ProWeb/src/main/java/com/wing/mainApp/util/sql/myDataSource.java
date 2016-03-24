package com.wing.mainApp.util.sql;

import com.wing.mainApp.util.config.Globalconfig;

// Globalconfig 에서 값을 읽어와서 db 설정
public class myDataSource extends  org.apache.commons.dbcp.BasicDataSource  {
   public myDataSource()
   {
	   super();
	   
	   Globalconfig config = new Globalconfig();  

	   String classname = "";
	   String url = "";
	   
	   String userid ="";
	   String password ="";
	   try {
		    userid = config.getUserid();			   
	   } catch(Exception ex) { 			   			   
	   }
	   		   
	   try {
		   password = config.getPassword();
		   
	   } catch(Exception ex) { 
		   			   
	   }
	   
	   int port = config.getPort();
	   
	   String dbtype = config.getDbtype();
	   
	   DbInfo  dbinfo =  new DbInfoMap().GetDbInfo(dbtype);
	   
	   if (dbinfo != null) {
		   classname = dbinfo.getDrivename();
		   url = dbinfo.getUrl(config.getHost(),port , config.getDbname());
	   }
	   
        this.setDriverClassName(classname);
        this.setUsername(userid);
        this.setUrl(url);
        this.setPassword(password);
        
      
   }
	 
}
