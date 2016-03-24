package com.wing.mainApp.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyData {
	
	public int reno;
	public int orino;
	public String reid;	
	public String renick;
	public String rebody;
	public Date redate;
	public String realdate;
	public String realbody;
	
	public int getReno() {
		return reno;
	}
	public void setReno(int reno) {
		this.reno = reno;
	}
	public String getRebody() {
		return rebody;
	}
	public void setRebody(String rebody) {
		this.rebody = rebody;
	}
	public String getRenick() {
		return renick;
	}
	public void setRenick(String renick) {
		this.renick = renick;
	}
	
	public Date getRedate() {
		return redate;
	}
	public void setRedate(Date redate) {
		this.redate = redate;
	}
		
	public String getReid() {
		return reid;
	}
	public void setReid(String reid) {
		this.reid = reid;
	}	
	public int getOrino() {
		return orino;
	}
	public void setOrino(int orino) {
		this.orino = orino;
	}
	/**날짜 변경*/
	public String getRealdate() {
		SimpleDateFormat form = new SimpleDateFormat("yy-MM-dd HH:mm");
		return form.format(redate);
	}
	/**BR태그 넣기*/
	public String getRealbody(){
		return rebody.replaceAll("\r\n", "<br>");
	}
	
}
