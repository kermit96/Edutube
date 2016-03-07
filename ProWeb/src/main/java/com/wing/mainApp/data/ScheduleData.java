package com.wing.mainApp.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScheduleData {

	public int no;
	public String id;
	public String name;
	public Date sdate;
	public Date edate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public String getSdate1() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		return form.format(sdate);
	}
	public String getEdate1() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		if(edate == null || edate.equals(sdate)) {
			return "";
		}
		else {
			return form.format(edate);
		}
	}
}
