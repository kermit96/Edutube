package com.wing.mainApp.data;

public class MessageData {
	private int no;
	private String toid;
	private String fromid;
	private String body;
	private String bodys;
	private String com;
	private String coms;
	
	
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getToid() {
		return toid;
	}
	public void setToid(String toid) {
		this.toid = toid;
	}
	public String getFromid() {
		return fromid;
	}
	public void setFromid(String fromid) {
		this.fromid = fromid;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBodys(){
		if(this.body.length() >7){
		return this.body.substring(0, 7) + "...";
		}
		else{
				return this.body;
		}
	}
	public String getComs(){
		if(this.com.equals("N")){
			return "안읽음";
		}
		else{
			return "읽음";
		}
	}
}
