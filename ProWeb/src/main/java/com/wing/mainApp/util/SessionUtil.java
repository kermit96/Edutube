package com.wing.mainApp.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	/*
	 * 세션이 있는지 여부를 판단해주는 함수
	 */
	public static boolean isSession(HttpSession session) {
		if (StringUtil.isNull((String) session.getAttribute("ID"))) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * 관리자 인지 여부를 판단해주는 함수
	 */
	public static boolean isAdmin(HttpSession session) {
		if (session.getAttribute("NAL").equals("A")) {
			return true;
		} 
		else {
			return false;
		}
		/*
		 * N : 그냥 회원 	A : 관리자 	L : 강사
		 */
	}
}
