package com.wing.mainApp.data;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class DownLoadData {
	public		String				title;
	public		String				body;
	public		String				pw;
	public		MultipartFile[]		upfile;
	public		String				id;
	public		int					no;
	public		String				path;
	public		String				oriname;
	public		String				savename;
	public		long				len;
	public		int					hit;
	public		Date				wdate;
	public		Date				cdate;
	public		int					oriNo;
	public		String				kind;
	public		int					nowPage;
	
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public MultipartFile[] getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile[] upfile) {
		this.upfile = upfile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
}
