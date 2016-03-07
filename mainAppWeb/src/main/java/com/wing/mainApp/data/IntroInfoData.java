package com.wing.mainApp.data;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class IntroInfoData {
	private int    intro_no; /* �Ұ� ��ȣ */
	private String mem_id;       /* ȸ�� ���̴� */
	private String intro_title;  /* �Ұ� ���� */
	private String intro_body;   /* �Ұ� ���� */
	private Date   intro_date;     /* �Ұ� �ۼ��� */
	private String intro_path;     /* ���� ��� * /
	private String intro_oriName;  /*���� �̸� */ 
	private int    intro_length;     /*���� ���� */
	private Date   intro_cDate;    /* ������ ���� ��¥ */
	private int    intro_isDeleted; /* �Ұ� ���� ���� */
	
	//���� ���� ���� �����
	public	MultipartFile	gimg;
	public	MultipartFile	upfile;
	public  MultipartFile[]	files;
	private	String			gimg1;
	private	String			gimg2;
			
	public MultipartFile getGimg() {
		return gimg;
	}
	public void setGimg(MultipartFile gimg) {
		this.gimg = gimg;
	}
	public String getGimg2() {
		return gimg2;
	}
	public void setGimg2(String gimg2) {
		this.gimg2 = gimg2;
	}
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getGimg1() {
		return gimg1;
	}
	public void setGimg1(String gimg1) {
		this.gimg1 = gimg1;
	}
	
		
	public int getIntro_no() {
		return intro_no;
	}
	public void setIntro_no(int intro_no) {
		this.intro_no = intro_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getIntro_title() {
		return intro_title;
	}
	public void setIntro_title(String intro_title) {
		this.intro_title = intro_title;
	}
	public String getIntro_body() {
		return intro_body;
	}
	public void setIntro_body(String intro_body) {
		this.intro_body = intro_body;
	}
	public Date getIntro_date() {
		return intro_date;
	}
	public void setIntro_date(Date intro_date) {
		this.intro_date = intro_date;
	}
	public String getIntro_path() {
		return intro_path;
	}
	public void setIntro_path(String intro_path) {
		this.intro_path = intro_path;
	}
	public int getIntro_length() {
		return intro_length;
	}
	public void setIntro_length(int intro_length) {
		this.intro_length = intro_length;
	}
	public Date getIntro_cDate() {
		return intro_cDate;
	}
	public void setIntro_cDate(Date intro_cDate) {
		this.intro_cDate = intro_cDate;
	}
	public int getIntro_isDeleted() {
		return intro_isDeleted;
	}
	public void setIntro_isDeleted(int intro_isDeleted) {
		this.intro_isDeleted = intro_isDeleted;
	}
	
}

