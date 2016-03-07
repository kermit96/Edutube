package com.wing.mainApp.data;

import 	org.springframework.web.multipart.MultipartFile;

public class GoodsInfoData {
	private	int				no;
	private	String			scode;
	private	String			name;
	private	String			maker;
	private	int				price;
	private	String			origin;
	private	MultipartFile	gimg;
	private	MultipartFile	iimg;
	//	
	private	String			gimg2;
	private	String			iimg2;
	private	String			id;
	private	int				count;
	private	int				total;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getGimg2() {
		return gimg2;
	}
	public void setGimg2(String gimg2) {
		this.gimg2 = gimg2;
	}
	public String getIimg2() {
		return iimg2;
	}
	public void setIimg2(String iimg2) {
		this.iimg2 = iimg2;
	}
	public String getScode() {
		return scode;
	}
	public void setScode(String scode) {
		this.scode = scode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public MultipartFile getGimg() {
		return gimg;
	}
	public void setGimg(MultipartFile gimg) {
		this.gimg = gimg;
	}
	public MultipartFile getIimg() {
		return iimg;
	}
	public void setIimg(MultipartFile iimg) {
		this.iimg = iimg;
	}
}








