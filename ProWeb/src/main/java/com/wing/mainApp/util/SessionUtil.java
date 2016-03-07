package com.wing.mainApp.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	/*
	 * 세션이 있는지 여부를 판단해 주는 함수
	 */
	public static boolean isSession(HttpSession session){
		if(StringUtil.isNull((String)session.getAttribute("ID"))){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isAdmin(HttpSession session){
		if(session.getAttribute("NAL").equals("A")){
			return true;
		}
		else{
			return false;
		}		
	}
	
	public static boolean isLecturer(HttpSession session){
		if(session.getAttribute("NAL").equals("L")){
			return true;
		}
		else{
			return false;
		}		
	}
	
	
}
