package com.wing.mainApp.util.sql;

public class DbInfo {
	
	
	private  String  Dbid; // db 구별자 
     public String dbname; // db 명  
     private String  Drivename; //  drive 
     private String  Url ;  // url
     private int      defaultport;  // defaultport
    

	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getDrivename() {
		return Drivename;
	}
	public void setDrivename(String drivename) {
		Drivename = drivename;
	}
	
	public String getUrl() {
		return Url;
	}
	
	public String getUrl(String host,int port,String dbname) {
		
		String ret = Url;
	    
		ret = ret.replace("{HOSTNAME}",host);
		
		if (port == 0 ) 
			port = defaultport;
		
		ret = ret.replace("{PORT}",Integer.toString(port));
		ret = ret.replace("{DBNAME}",dbname);
		
		return ret;
	}
	
	
	public void setUrl(String url) {
		Url = url;
	}
	public int getDefaultport() {
		return defaultport;
	}
	public void setDefaultport(int defaultport) {
		this.defaultport = defaultport;
	}

	public String getDbid() {
		return Dbid;
	}

	public void setDbid(String dbid) {
		Dbid = dbid;
	}

     
}
