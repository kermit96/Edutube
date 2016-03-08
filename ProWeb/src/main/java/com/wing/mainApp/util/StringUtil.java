package com.wing.mainApp.util;

/*
 * 	문자열에 관련된 기능을 단순화 시키기 위한 유틸리티 클래스
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
	 * 
	 */
	public static String stringSplit(String data,int len){
		if(data.length() > len){
			return data.substring(0,len) + "...";
		}
		else{
			return data;
		}
	}
	
}
