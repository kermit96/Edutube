package com.wing.mainApp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 	���ڿ��� ���õ� ����� �ܼ�ȭ ��Ű�� ���� ��ƿ��Ƽ Ŭ����
 */
public final class StringUtil {
	/*
	 * 	문자열이 널인지를 확인해 주는 함수
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
	 * 	날짜를 원하는 형태의 문자로 변환시켜주는 함수
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
	 * BR 태그로 변환시켜주는 함수
	 */
	public static String setBr(String data) {
		return data.replaceAll("\r\n", "<br>");
	}
	
}
