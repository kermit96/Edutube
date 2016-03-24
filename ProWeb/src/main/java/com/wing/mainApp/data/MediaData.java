package com.wing.mainApp.data;

public class MediaData {
	public int mediaNO;
	public String mediaURL;
	public String realURL;
	
	// 원래글 번호
	public int no;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMediaNO() {
		return mediaNO;
	}
	public void setMediaNO(int mediaNO) {
		this.mediaNO = mediaNO;
	}
	public String getMediaURL() {
		return mediaURL;
	}
	public void setMediaURL(String mediaURL) {
		this.mediaURL = mediaURL;
	}	
	
	public String getRealURL() {		
		return mediaURL.substring(17);
	}
	
}
