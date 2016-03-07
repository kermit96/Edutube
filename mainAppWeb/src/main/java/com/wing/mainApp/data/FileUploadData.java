package com.wing.mainApp.data;

import 	org.springframework.web.multipart.MultipartFile;

public class FileUploadData {
	public	String			writer;
	public	String			title;
	public	String			body;
	//	�뙆�씪�씠 �꽆�뼱�삤�뒗 遺�遺꾩� MultipartFile	�씠�씪�뒗 �겢�옒�뒪濡� 諛쏅뒗�떎.
	//	�씠 �븞�뿉�뒗 �뙆�씪�쓽 �씠由꾧낵 �떎�젣 �궡�슜�씠 �떎 湲곗뼲�릺�뼱 吏꾨떎.
	public	MultipartFile	upfile;
	//	媛숈� name�씠 �뿬�윭媛� 議댁옱�븯�뒗 寃쎌슦�뿉�뒗 諛곗뿴濡� 諛쏆쑝硫� �맂�떎.
	public MultipartFile[]	files;
	public	String			oriname;
	public	String			savename;
	public	String			path;
	public	long			len;
	public	int				no;
	public	int				dhit;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getDhit() {
		return dhit;
	}
	public void setDhit(int dhit) {
		this.dhit = dhit;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public MultipartFile getUpfile() {
		return upfile;
	}
	public void setUpfile(MultipartFile upfile) {
		//	�씠 �븿�닔媛� �떎�뻾�릺�뒗 �닚媛�
		//	�꽌踰꾩쓽 �엫�떆 硫붾え由ъ뿉 �뙆�씪�씠 ���옣�맂�떎.
		this.upfile = upfile;
	}
}



