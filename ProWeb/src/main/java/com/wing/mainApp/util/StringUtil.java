package com.wing.mainApp.util;

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
