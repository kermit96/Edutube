package com.wing.mainApp.util;

import javax.servlet.http.HttpSession;

/*
 * 	�꽭�뀡�뿉 愿��젴�맂 �쑀�떥由ы떚 �겢�옒�뒪
 */
public class SessionUtil {
	/*
	 * 	�꽭�뀡�씠 �엳�뒗吏� �뿬遺�瑜� �뙋�떒�빐二쇰뒗 �븿�닔
	 */
	public static boolean isSession(HttpSession session) {
		if(StringUtil.isNull((String)session.getAttribute("loginId"))) {
			return false;
		}
		else {
			return true;
		}
	}
}
