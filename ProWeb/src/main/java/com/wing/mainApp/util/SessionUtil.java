package com.wing.mainApp.util;

import javax.servlet.http.HttpSession;

/*
 * 	���ǿ� ���õ� ��ƿ��Ƽ Ŭ����
 */
public class SessionUtil {
	/*
	 * 	������ �ִ��� ���θ� �Ǵ����ִ� �Լ�
	 */
	public static boolean isSession(HttpSession session) {
		if(StringUtil.isNull((String)session.getAttribute("ID"))) {
			return false;
		}
		else {
			return true;
		}
	}
}
