package com.iedu.sql;

import com.iedu.config.Globalconfig;
import com.iedu.config.dbconfiginfo;
import com.iedu.util.DbInfo;
import com.iedu.util.DbInfoMap;


// Globalconfig 에서 값을 읽어와서 db 설정
public class myDataSource extends  org.apache.commons.dbcp.BasicDataSource  {
	
	public myDataSource()
	{		
		this(0);
	}
	
	
   public myDataSource(int index)
   {
	   super();
	   
	   Globalconfig config = new Globalconfig();  

	   String classname = "";
	   String url = "";
	   
	   String userid ="";
	   String password ="";
	  	   
	   dbconfiginfo info =  config.getDbconfig(index) ;
	   
	  if (info == null)
		  return;
	   
	  userid =  info.getDbuserid();
	  password = info.getDbpassword();
	  int port  = info.getDbport();
	  String dbtype = info.getDbtype();
	   
	  String dbname = info.getDbname();
	  String dbhost = info.getDbhost();
	   DbInfo  dbinfo =  new DbInfoMap().GetDbInfo(dbtype);
	   
	   if (dbinfo != null) {
		   classname = dbinfo.getDrivename();
		   url = dbinfo.getUrl(dbhost,port , dbname);
	   }
	   
        this.setDriverClassName(classname);
        this.setUsername(userid);
        this.setUrl(url);
        this.setPassword(password);
        
      
   }
	 
}
