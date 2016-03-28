package com.wing.mainApp.data;

import java.util.Date;

import com.wing.mainApp.util.StringUtil;

public class MainData {
	private Date notice_date;
	private String notice_title;
	private Date class_date;
	private String class_title;
	private String sub_code;
	private String sub_codek;
	private String qa_title;
	private Date qa_date;
	private String qa_dates;
	private String down_title;
	private Date down_date;
	private String down_dates;
	
	public String getDown_dates(){
		return StringUtil.getDateStr(down_date);
	}
	public String getDown_title() {
		return down_title;
	}
	public void setDown_title(String down_title) {
		this.down_title = down_title;
	}
	public Date getDown_date() {
		return down_date;
	}
	public void setDown_date(Date down_date) {
		this.down_date = down_date;
	}
	public String getQa_dates(){
		return StringUtil.getDateStr(qa_date);
	}
	public String getQa_title() {
		return qa_title;
	}
	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}
	public Date getQa_date() {
		return qa_date;
	}
	public void setQa_date(Date qa_date) {
		this.qa_date = qa_date;
	}
	public String getClass_code() {
		return sub_code;
	}
	public String getClass_codek(){
		if(sub_code.equals("langkor")){
			return "한국어";
		}
		else if(sub_code.equals("langjap")){
			return "일본어";
		}
		else if(sub_code.equals("langchn")){
			return "중국어";
		}
		else if(sub_code.equals("langeng")){
			return "영어";
		}
		else if(sub_code.equals("progjava")){
			return "자바";
		}
		else if(sub_code.equals("progclang")){
			return "C언어";
		}
		else if(sub_code.equals("progphp")){
			return "PHP";
		}
		else if(sub_code.equals("cookkor")){
			return "한식";
		}
		else if(sub_code.equals("cookchn")){
			return "중식";
		}
		else if(sub_code.equals("cookjap")){
			return "일식";
		}
		else {
			return "양식";
		}
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_dates(){
		return StringUtil.getDateStr(notice_date);
	}
	public Date getClass_date() {
		return class_date;
	}
	public void setClass_date(Date class_date) {
		this.class_date = class_date;
	}
	public String getClass_title() {
		return class_title;
	}
	public void setClass_title(String class_title) {
		this.class_title = class_title;
	}
	public String getClass_dates(){
		return StringUtil.getDateStr(class_date);
	}
}
