package com.wing.mainApp.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	/*
	 * ������ �ִ��� ���θ� �Ǵ��� �ִ� �Լ�
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
