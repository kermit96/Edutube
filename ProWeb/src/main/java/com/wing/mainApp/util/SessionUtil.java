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
	
	
}
