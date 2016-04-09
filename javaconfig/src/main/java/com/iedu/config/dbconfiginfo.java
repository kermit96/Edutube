package com.iedu.config;

public class dbconfiginfo {
  private String dbtype;
  private String host;
  private int port;
  private String userid;
  private String password;
  private String dbname;
  
  public String getDbname() {
	return dbname;
}
public void setDbname(String dbname) {
	this.dbname = dbname;
}


public String getDbtype() {
	return dbtype;
}
public void setDbtype(String dbtype) {
	this.dbtype = dbtype;
}
public String getHost() {
	return host;
}
public void setHost(String host) {
	this.host = host;
}
public int getPort() {
	return port;
}
public void setPort(int port) {
	this.port = port;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
  
}
