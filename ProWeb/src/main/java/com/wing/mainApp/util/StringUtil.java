package com.wing.mainApp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 	���ڿ��� ���õ� ����� �ܼ�ȭ ��Ű�� ���� ��ƿ��Ƽ Ŭ����
 */
public final class StringUtil {
	/*
	 * 	���ڿ��� �������� Ȯ���� �ִ� �Լ�
	 */
	public static boolean isNull(String data) {
		if(data == null || data.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * 	��¥�� ���ϴ� ������ ���ڷ� ��ȯ�����ִ� �Լ�
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		return form.format(date);
	}
	public static String getDateTimeStr(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return form.format(date);
	}
	public static String getTimeStr(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss");
		return form.format(date);
	}
	public static String getDateStr(Date date, String pattern) {
		SimpleDateFormat form = new SimpleDateFormat(pattern);
		return form.format(date);
	}
	
	/*	
	 * BR �±׷� ��ȯ�����ִ� �Լ�
	 */
	public static String setBr(String data) {
		return data.replaceAll("\r\n", "<br>");
	}
}




