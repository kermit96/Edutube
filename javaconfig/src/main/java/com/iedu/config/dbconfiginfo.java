package com.iedu.config;

public class dbconfiginfo {
  private String dbtype;
  private String dbhost;
  private int dbport;
  private String dbuserid;
  private String dbpassword;
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
public String getDbhost() {
	return dbhost;
}
public void setDbhost(String host) {
	this.dbhost = host;
}
public int getDbport() {
	return dbport;
}
public void setDbport(int port) {
	this.dbport = port;
}
public String getDbuserid() {
	return dbuserid;
}
public void setDbuserid(String userid) {
	this.dbuserid = userid;
}
public String getDbpassword() {
	return dbpassword;
}
public void setDbpassword(String password) {
	this.dbpassword = password;
}
  
}
