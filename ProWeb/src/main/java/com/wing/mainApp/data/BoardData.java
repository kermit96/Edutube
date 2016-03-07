package com.wing.mainApp.data;

import java.util.Date;

import com.wing.mainApp.util.StringUtil;

public class BoardData {
	public int		no;
	public int 		qno;
	public String 	id;
	public String 	title;
	public String 	body;
	public int		hit;
	public Date	wdate;
	public int		top;
	public int		mid;
	public int		bottom;
	public String	admin;
	public String	nick;
	
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getBottom() {
		return bottom;
	}
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	public String getDatetime() {
		return StringUtil.getDateTimeStr(wdate);
	}
	public String getDate() {
		return StringUtil.getDateStr(wdate);
	}
	public String getBrbody() {
		return StringUtil.setBr(body);
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
}
