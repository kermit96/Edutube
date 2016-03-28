package com.wing.mainApp.data;

import java.util.Date;

import com.wing.mainApp.util.StringUtil;

public class NoticeData {
	private int notice_no;
	private String mem_id;
	private String notice_title="";
	private String notice_body;
	private Date notice_date;
	private Date notice_cDate;
	private int notice_isDeleted;
	public int		nowPage;
	
	
	public String	nick;
	
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_body() {
		return notice_body;
	}
	public void setNotice_body(String notice_body) {
		this.notice_body = notice_body;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public Date getNotice_cDate() {
		return notice_cDate;
	}
	public void setNotice_cDate(Date notice_cDate) {
		this.notice_cDate = notice_cDate;
	}
	public int getNotice_isDeleted() {
		return notice_isDeleted;
	}
	public void setNotice_isDeleted(int notice_isDeleted) {
		this.notice_isDeleted = notice_isDeleted;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getDatetime() {
		return StringUtil.getDateTimeStr(notice_date);
	}
	public String getDate() {
		return StringUtil.getDateStr(notice_date);
	}
	public String getBrbody() {
		return StringUtil.setBr(notice_body);
	}
}
