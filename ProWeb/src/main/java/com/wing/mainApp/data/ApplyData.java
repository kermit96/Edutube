package com.wing.mainApp.data;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ApplyData {
	private int no;
	private String code;
	private String id;
	private String body;
	private String path;
	private String oriname;
	private String savename;
	private long len;
	private Date sdate;
	private Date cdate;
	private String nal;
	private MultipartFile afile;
	
	
	
	public MultipartFile getAfile() {
		return afile;
	}
	public void setAfile(MultipartFile afile) {
		this.afile = afile;
	}
	public String getNal() {
		return nal;
	}
	public void setNal(String nal) {
		this.nal = nal;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if(code.equals("langkor")){
			this.code="한국어";
		}
		else if(code.equals("langjap")){
			this.code="일본어";
		}
		else if(code.equals("langchn")){
			this.code="중국어";
		}
		else if(code.equals("langeng")){
			this.code="영어";
		}
		else if(code.equals("progjava")){
			this.code="자바";
		}
		else if(code.equals("progclang")){
			this.code="C언어";
		}
		else if(code.equals("progphp")){
			this.code="PHP";
		}
		else if(code.equals("cookkor")){
			this.code="한식";
		}
		else if(code.equals("cookchn")){
			this.code="중식";
		}
		else if(code.equals("cookjap")){
			this.code="일식";
		}
		else if(code.equals("cookwest")){
			this.code="양식";
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
}
