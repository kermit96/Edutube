package com.wing.mainApp.data;

import java.util.Date;

public class Member {
       
	private String mem_id; 
	private String mem_nick;
	private String mem_pass ;
	private String mem_name ;
	private String mem_email ; /* 회원 이메일 */
	private String mem_tel ; /* 회원 전화번호 */
	private String mem_addrCode; /* 회원 주소코드 */
	private String mem_addr ; /* 회원 주소 */
	private String mem_detailaddr ; /* 회원 상세주소소 */
	private String  mem_Nal ; /* 회원 구분 */
	private String   mem_isDeleted; /* 회원 삭제 여부 */
	private Date   mem_cDate;		
	private String  mem_block ;/* 회원 블럭상태 */
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addrCode() {
		return mem_addrCode;
	}
	public void setMem_addrCode(String mem_addrCode) {
		this.mem_addrCode = mem_addrCode;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getMem_detailaddr() {
		return mem_detailaddr;
	}
	public void setMem_detailaddr(String mem_detailaddr) {
		this.mem_detailaddr = mem_detailaddr;
	}
	public String getMem_Nal() {
		return mem_Nal;
	}
	public void setMem_Nal(String mem_Nal) {
		this.mem_Nal = mem_Nal;
	}
	public String getMem_isDeleted() {
		return mem_isDeleted;
	}
	public void setMem_isDeleted(String mem_isDeleted) {
		this.mem_isDeleted = mem_isDeleted;
	}
	public Date getMem_cDate() {
		return mem_cDate;
	}
	public void setMem_cDate(Date mem_cDate) {
		this.mem_cDate = mem_cDate;
	}
	public String getMem_block() {
		return mem_block;
	}
	public void setMem_block(String mem_block) {
		this.mem_block = mem_block;
	}
	
	
	
}
